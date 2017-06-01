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

    public void begin(RTTnode value){
        this.beginNode = value;
    }

    public RTTnode end(){
        return this.endNode;
    }

    public void end(RTTnode value){
        this.endNode = value;
    }

    public String toXMI(){
        return null;
    }

    public String toJson(){
        StringBuilder json = new StringBuilder("{");

        json.append("from: \"");
        json.append(this.begin().name);
        json.append("\"");

        json.append(", ");

        json.append("to: \"");
        json.append(this.end().name);
        json.append("\"");

        json.append("}");

        return json.toString();
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
