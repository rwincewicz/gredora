package gredora

import org.springframework.dao.DataIntegrityViolationException
import com.yourmediashelf.fedora.client.FedoraCredentials
import com.yourmediashelf.fedora.client.FedoraClient
import com.yourmediashelf.fedora.client.request.GetDatastream
import com.yourmediashelf.fedora.client.response.GetDatastreamResponse
import com.yourmediashelf.fedora.generated.management.DatastreamProfile
import com.yourmediashelf.fedora.client.request.GetDatastreams
import com.yourmediashelf.fedora.client.response.GetDatastreamsResponse
import com.yourmediashelf.fedora.client.request.ModifyDatastream
import com.yourmediashelf.fedora.client.response.ModifyDatastreamResponse
import com.yourmediashelf.fedora.client.request.PurgeDatastream
import com.yourmediashelf.fedora.client.response.PurgeDatastreamResponse
import com.yourmediashelf.fedora.client.FedoraClientException
import com.yourmediashelf.fedora.client.request.AddDatastream
import com.yourmediashelf.fedora.client.response.AddDatastreamResponse
import com.yourmediashelf.fedora.client.request.GetDatastreamDissemination
import com.yourmediashelf.fedora.client.response.FedoraResponse
import com.yourmediashelf.fedora.util.XmlSerializer
import javax.xml.transform.Transformer
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.TransformerFactory
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.DocumentBuilder
import javax.xml.transform.OutputKeys
import org.w3c.dom.Document
import org.xml.sax.InputSource

class GredoraDatastreamController {
    
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

    def list(String pid) {
        connect();
        GetDatastreams datastreams = client.getDatastreams(pid);
        GetDatastreamsResponse datastreamsResponse = datastreams.execute(client);
        List<DatastreamProfile> dsList = datastreamsResponse.getDatastreamProfiles();
        List<GredoraDatastream> gredoraDsList = new ArrayList<GredoraDatastream>();
        for (DatastreamProfile profile : dsList) {
            GredoraDatastream ds = new GredoraDatastream();
            ds.pid = pid;
            ds.dsId = profile.getDsID();
            ds.dsLabel = profile.getDsLabel();
            gredoraDsList.add(ds);
        }   
        [pid: pid, dsList: gredoraDsList]
    }

    def create(String pid) {
        connect();
        GredoraDatastream gds = new GredoraDatastream();
        gds.pid = pid;
        
        [gds: gds]
    }

    def save() {
        connect();
        log.error(params);
        AddDatastream addDatastream = client.addDatastream(params.pid, params.formDsId);
        addDatastream.dsLabel(params.dsLabel);
        addDatastream.checksum(params.dsChecksumType);
        addDatastream.dsState(params.type);
        addDatastream.mimeType(params.dsMIMEType);
        addDatastream.controlGroup(params.dsControlGroup);
        addDatastream.content("<xml></xml>");
        AddDatastreamResponse addDsResponse;
        try {
            addDsResponse = addDatastream.execute(client);
        } catch (FedoraClientException fce) {
            log.error("DS Creation failed - " + fce);
            flash.message = "Datastream " + params.formDsId + " on object " + params.pid + " could not be created!";
            redirect(controller: "gredoraObject", action: "show", params: [id: params.pid]);
            return;
        }
        log.error("Response: " + addDsResponse.getStatus());
        if (addDsResponse.getStatus() == 201) {
            flash.message = "Datastream " + params.formDsId + " on object " + params.pid + " was created successfully";
            redirect(action: "show", params: [id: params.pid, dsId: params.formDsId]);
        } else {
            flash.message = "Datastream " + params.formDsId + " on object " + params.pid + " could not be created!";
            redirect(controller: "gredoraObject", action: "show", params: [id: params.pid])
        }
    }

    def show(String id, String dsId) {
        connect();
        GetDatastream ds = client.getDatastream(id, dsId);
        GetDatastreamResponse dsr = ds.execute(client);
        DatastreamProfile dsProfile = dsr.getDatastreamProfile();
        GredoraDatastream gds = new GredoraDatastream();
        gds.pid = id;
        gds.dsId = dsId;
        gds.dsLabel = dsProfile.getDsLabel();
        gds.dsMIMEType = dsProfile.getDsMIME();
        gds.dsState = dsProfile.getDsState();
        gds.size = dsProfile.getDsSize();
        gds.dsChecksum = dsProfile.getDsChecksum();
        gds.dsChecksumType = dsProfile.getDsChecksumType();
        gds.dsControlGroup = dsProfile.getDsControlGroup();
        if ("X".equals(gds.dsControlGroup)) {
            GetDatastreamDissemination dss = client.getDatastreamDissemination(id, dsId);
            FedoraResponse dssResponse = dss.execute(client);
            gds.dsContent = dssResponse.getEntity(String.class);
        } else {
            gds.dsAccessUrl = buildAccessUrl(id, dsId);
        }
        
        [gds: gds]
    }

    def edit(String id, String dsId) {
        connect();
        log.error(params);
        GetDatastream ds = client.getDatastream(id, dsId);
        GetDatastreamResponse dsr = ds.execute(client);
        DatastreamProfile dsProfile = dsr.getDatastreamProfile();
        GredoraDatastream gds = new GredoraDatastream();
        gds.pid = id;
        gds.dsId = dsId;
        gds.dsLabel = dsProfile.getDsLabel();
        gds.dsMIMEType = dsProfile.getDsMIME();
        gds.dsState = dsProfile.getDsState();
        gds.size = dsProfile.getDsSize();
        gds.dsChecksum = dsProfile.getDsChecksum();
        gds.dsChecksumType = dsProfile.getDsChecksumType();

        [gds: gds]
    }

    def update() {
        connect();
        ModifyDatastream modifyDatastream = client.modifyDatastream(params.pid, params.dsId);
        modifyDatastream.dsLabel(params.dsLabel);
        modifyDatastream.dsState(params.type);
        modifyDatastream.checksumType(params.dsChecksumType);
        ModifyDatastreamResponse modifyDsResponse = modifyDatastream.execute(client);
        if (modifyDsResponse.getStatus() == 200) {
            flash.message = params.dsId + " in " + params.pid + " modified sucessfully";
        } else {
            flash.message = params.dsId + " in " + params.pid + " could not be modified!";
        }
        redirect(url: "/" + params.pid + "/datastream/" + params.dsId);
    }

    def delete() {
        connect();
        log.error(params);
        PurgeDatastream purgeDatastream = client.purgeDatastream(params.id, params.dsId);
        PurgeDatastreamResponse purgeDsResponse;
        try {
            purgeDsResponse = purgeDatastream.execute(client);
        } catch (FedoraClientException fce) {
            flash.message = params.dsId + " in " + params.id + " could not be deleted!";
            redirect(url: "/" + params.id + "/datastream/" + params.dsId);
        }
        if (purgeDsResponse?.getStatus() == 200) {
            flash.message = params.dsId + " in " + params.id + " deleted successfully";
            redirect(controller: "GredoraObject", action: "show", id: params.id);
        }
    }
    
    public String buildAccessUrl(String pid, String dsId) {
        return grailsApplication.config.grails.fedora.url + "/fedora/objects/" + pid + "/datastreams/" + dsId + "/content";
    }
}
