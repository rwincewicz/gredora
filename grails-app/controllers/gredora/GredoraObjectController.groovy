package gredora

import org.springframework.dao.DataIntegrityViolationException
import com.yourmediashelf.fedora.*
import com.yourmediashelf.fedora.client.*
import com.sun.jersey.api.client.config.DefaultClientConfig
import com.yourmediashelf.fedora.client.request.*
import com.yourmediashelf.fedora.client.response.*
import sun.net.www.protocol.http.HttpURLConnection.HttpInputStream
import javax.xml.parsers.DocumentBuilderFactory
import com.yourmediashelf.fedora.generated.management.DatastreamProfile

class GredoraObjectController {
    def grailsApplication;
    
    def client;
       
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def connect() {
        URL fedoraUrl = new URL("http://" + grailsApplication.config.grails.fedora.host + ":" + grailsApplication.config.grails.fedora.port + "/fedora");
        FedoraCredentials fc = new FedoraCredentials(fedoraUrl, grailsApplication.config.grails.fedora.username, grailsApplication.config.grails.fedora.password);
        client = new FedoraClient(fc);
    }
    
    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        connect();
        FindObjects fo = client.findObjects();
        fo.pid();
        fo.query("");
        FindObjectsResponse foresp = fo.execute(client);
        List<String> pids = foresp.getPids();
        List<GredoraObject> objectList = new ArrayList<GredoraObject>();
        for (String pid: pids) {
            GetObjectXML objectXML = client.getObjectXML(pid);
            FedoraResponse xmlResp = objectXML.execute(client);
            GetObjectProfile objectProfile = client.getObjectProfile(pid);
            GetObjectProfileResponse gopr = objectProfile.execute(client);
            log.error(gopr.getLabel());
            GredoraObject object = new GredoraObject();
            object.id = gopr.getPid();
            object.title = gopr.getLabel();
            object.type = gopr.getState();
            objectList.add(object);
        }
        
        params.max = Math.min(max ?: 10, 100)
        [gredoraObjectInstanceList: GredoraObject.list(params), gredoraObjectInstanceTotal: GredoraObject.count(), objectList: objectList]
    }

    def create() {
        connect();
        GetNextPID nextPid = client.getNextPID();
        GetNextPIDResponse gnpResponse = nextPid.execute(client);
        String id = gnpResponse.getPid();
        log.error("Create: " + id);
        GredoraObject gredoraObject = new GredoraObject();
        gredoraObject.id = id;
        params.id = id;
        [gredoraObject: gredoraObject]
    }

    def save() {
        connect();
        Ingest ingest = client.ingest(params.pid);
        ingest.label(params.title);
        IngestResponse ingestResponse;
        try {
            ingestResponse = ingest.execute(client);
        } catch (FedoraClientException fce) {
            log.error("Object Creation failed - " + fce);
            flash.message = "Could not create new object with pid " + params.pid;
            redirect(controller: "gredoraObject", action: "list");
            return;
        }
        String newPid = ingestResponse.getPid();
        if (!newPid) {
            flash.message = "Could not create new object!";
            render(view: "create")
            return;
        }

        ModifyObject modifyObject = client.modifyObject(newPid);
        modifyObject.state(params.type);
        FedoraResponse modifyResponse = modifyObject.execute(client);
        if (modifyResponse.getStatus() != 200) {
            flash.message = "Could not modify object status!";
            render(view: "create");
            return;
        }
        flash.message = message(code: 'default.created.message', args: [message(code: 'gredoraObject.label', default: 'GredoraObject'), newPid])
        redirect(action: "show", id: newPid)
    }

    def show(String id) {
        connect();
        GetObjectProfile objectProfile = client.getObjectProfile(id);
        GetObjectProfileResponse gopr = objectProfile.execute(client);
        GredoraObject gredoraObject = new GredoraObject();
        gredoraObject.id = id;
        gredoraObject.title = gopr.getLabel();
        gredoraObject.type = gopr.getState();
        GetDatastreams datastreams = client.getDatastreams(id);
        GetDatastreamsResponse datastreamsResponse = datastreams.execute(client);
        List<DatastreamProfile> dsList = datastreamsResponse.getDatastreamProfiles();
        List<GredoraDatastream> gredoraDsList = new ArrayList<GredoraDatastream>();
        for (DatastreamProfile profile : dsList) {
            GredoraDatastream ds = new GredoraDatastream();
            ds.pid = id;
            ds.dsId = profile.getDsID();
            ds.dsLabel = profile.getDsLabel();
            gredoraDsList.add(ds);
        }            

        [gredoraObject: gredoraObject, ds: gredoraDsList]
    }

    def edit(String id) {
        connect();
        GetObjectProfile objectProfile = client.getObjectProfile(id);
        GetObjectProfileResponse gopr = objectProfile.execute(client);
        GredoraObject gredoraObject = new GredoraObject();
        gredoraObject.id = id;
        gredoraObject.title = gopr.getLabel();
        gredoraObject.type = gopr.getState();
        if (!gredoraObject) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'gredoraObject.label', default: 'GredoraObject'), id])
            redirect(action: "list")
            return
        }

        [gredoraObject: gredoraObject]
    }

    def update(String id) {
        connect();
        ModifyObject modifyObject = client.modifyObject(id);
        modifyObject.label(params.title);
        modifyObject.state(params.type);
        FedoraResponse updateResponse = modifyObject.execute(client);
        Integer updateResponseCode = updateResponse.getStatus();
        if (updateResponseCode == 200) {
            flash.message = message(code: 'default.updated.message', args: [message(code: 'gredoraObject.label', default: 'GredoraObject'), id])    
        } else {
            flash.message = "Update failed!";
        }
        
        redirect(action: "show", id: id)
    }

    def delete(String id) {
        connect();
        PurgeObject purgeObject = client.purgeObject(id);
        FedoraResponse purgeResponse = purgeObject.execute(client);
        Integer purgeResponseCode = purgeResponse.getStatus();
        if (purgeResponseCode == 200) {
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'gredoraObject.label', default: 'GredoraObject'), id]);
            redirect(action: "list")
        } else {
            flash.message = "Object could not be deleted!";
            redirect(action: "show", id: id)
        }
    }
}
