package com.rtt.cnmining;

public class RTTedge {

    private static int idCounter = 0;

    private RTTnode beginNode, endNode;
    public String id;
    public String name;

    public RTTedge(RTTnode start, RTTnode end){
        this.beginNode = start;
        this.endNode = end;
        this.id = "Edge-" + idCounter;
        idCounter++;
    }

    public RTTedge(String id, RTTnode start, RTTnode end){
        this.beginNode = start;
        this.endNode = end;
        this.id = id;
    }
    public RTTnode begin(){
        return this.beginNode;
    }

    public RTTnode end(){
        return this.endNode;
    }

    public String toXMI(){
        return null;
    }

    public String toString(){
        StringBuilder str = new StringBuilder("{ ");

        str.append(this.begin().toString());
        str.append(" -> ");
        str.append(this.end().toString());

        str.append(" }");

        return str.toString();
    }

}
