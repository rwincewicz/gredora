package gredora

import com.yourmediashelf.fedora.client.FedoraCredentials
import com.yourmediashelf.fedora.client.FedoraClient

class FedoraConnectorService {
    
    def grailsApplication;

    def connect() {
        URL fedoraUrl = new URL("http://" + grailsApplication.config.grails.fedora.host + ":" + grailsApplication.config.grails.fedora.port + "/fedora");
        FedoraCredentials fc = new FedoraCredentials(fedoraUrl, grailsApplication.config.grails.fedora.username, grailsApplication.config.grails.fedora.password);
        return new FedoraClient(fc);
    }
}
