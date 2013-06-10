package gredora

class GredoraObject {

    String id
    String title
    String type

    static constraints = {
    }
    
    String toString() {
        return "Id: " + id + " | Title: " + title + " | Type: " + type;
    }
}
