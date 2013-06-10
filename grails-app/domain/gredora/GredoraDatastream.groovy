package gredora

class GredoraDatastream {
    
    String pid;
    String dsId;
    String dsLabel;
    String dsState;
    String dsLocation;
    String dsMIMEType;
    BigInteger size;
    String dsChecksum;
    String dsChecksumType;
    String dsControlGroup;
    String dsContent;
    String dsAccessUrl;

    static constraints = {
        size nullable: true
        dsLocation nullable: true
        dsChecksum nullable: true
        dsChecksumType nullable: true
        dsState nullable: true
    }
}
