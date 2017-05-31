package com.rtt.cnmining;

import java.util.ArrayList;

public class RTTnode {

    public String name;
    public String id;
    private String type;
    private boolean isInitial;
    private boolean isFinal;

    private static int idCounter = 0;

    public RTTnode(String name){
        this.name = name;
        this.id = "Node-" + idCounter;
        idCounter++;
        this.node();
        this.isFinal = false;
        this.isInitial = false;
    }

    public RTTnode(String name, String id){
        this.name = name;
        this.id = id;
        this.node();
        this.isFinal = false;
        this.isInitial = false;
    }

    public void node(){
        this.type = "Node";
    }

    public void fork(){
        this.type = "ForkNode";
    }

    public void join(){
        this.type = "JoinNode";
    }

    public void branch(){
        this.type = "BranchNode";
    }

    public void initialNode(){
        this.isInitial = true;
        this.isFinal = false;
    }

    public void finalNode(){
        this.isInitial = false;
        this.isFinal = true;
    }

    public String toXMI(){
        return this.toXMI("", "");
    }

    public String toXMI(String outcoming, String incoming){
        StringBuilder str = new StringBuilder();

        str.append("<node xmi:type=\"uml:");
        if(this.isFinal)
            str.append("FinalNode");
        else if(this.isInitial)
            str.append("InitialNode");
        else str.append("OpaqueAction");

        str.append("\" xmi:id=\"");
        str.append(this.id);

        str.append("\" name=\"");
        str.append(this.name);

        if(outcoming.isEmpty() == false) {
            str.append("\" outgoing=\"");
            str.append(outcoming);
            str.append("\"");
        }

        if(incoming.isEmpty() == false) {
            str.append("\" incoming=\"");
            str.append(incoming);
            str.append("\"");
        }

        str.append("/>");

        return str.toString();
    }

    public String toJson(){
        StringBuilder json = new StringBuilder("{");

        json.append("key: \"");
        json.append(this.name);
        json.append("\"");

        json.append(" ");

        json.append("type: \"");
        json.append(this.type);
        json.append("\"");

        json.append("}");

        return json.toString();
    }

    public String toString(){
        StringBuilder str = new StringBuilder();

        str.append(this.type + ": " + this.name + ", id: " + this.id);
        if(isInitial)
            str.append(" [InitialNode]");
        else if(isFinal)
            str.append(" [FinalNode]");

        return str.toString();
    }

}
