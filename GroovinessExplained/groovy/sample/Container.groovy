package sample

class Container implements Serializable {

    private static final long serialVersionUID = 42000L

    List list
    Map map
    
    boolean equals(Container other) {
        this.list == other.list && this.map == other.map
    }
}