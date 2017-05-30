package com.rtt.cnmining;

public class RTTedge {

    private static int idCounter = 0;

    private RTTnode beginNode, endNode;
    private String id;
    private String name;

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

    public String id(){
        return this.id;
    }

    public void name(String value){
        this.name = value;
    }

    public String name(){
        return this.name;
    }

    public String toXMI(){
        return null;
    }

    public String toString(){
        return this.begin().toString() + " -> " + this.end().toString();
    }

}
