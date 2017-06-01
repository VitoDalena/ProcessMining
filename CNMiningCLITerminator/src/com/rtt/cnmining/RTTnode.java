package com.rtt.cnmining;

import java.util.ArrayList;

public class RTTnode {

    public static final String JoinNode = "JoinNode";
    public static final String ForkNode = "ForkNode";
    public static final String BranchNode = "BranchNode";
    public static final String Node = "Node";
    public static final String InitialNode = "InitialNode";
    public static final String FinalNode = "FinalNode";

    public String name;
    public String id;
    private String type;

    private static int idCounter = 0;

    public RTTnode(String name){
        this.name = name;
        this.id = "Node-" + idCounter;
        idCounter++;
        this.node();
    }

    public RTTnode(String name, String id){
        this.name = name;
        this.id = id;
        this.node();
    }

    public void node(){
        this.type = Node;
    }

    public void fork(){
        this.type = ForkNode;
    }

    public void join(){
        this.type = JoinNode;
    }

    public void branch(){
        this.type = BranchNode;
    }

    public boolean isType(String value){
        return this.type.equals(value);
    }

    public void initialNode(){
        this.type = InitialNode;
    }

    public void finalNode(){
        this.type = FinalNode;
    }

    public String toXMI(){
        return this.toXMI("", "");
    }

    public String toXMI(String outcoming, String incoming){
        StringBuilder str = new StringBuilder();

        str.append("<node xmi:type=\"uml:");
        if(this.isType(FinalNode))
            str.append("FinalNode");
        else if(this.isType(InitialNode))
            str.append("InitialNode");
        else str.append("OpaqueAction");

        str.append("\" xmi:id=\"");
        str.append(this.id);

        str.append("\" name=\"");
        str.append(this.name);
        str.append("\"");

        if(outcoming.isEmpty() == false) {
            str.append(" outgoing=\"");
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

        json.append(", ");

        json.append("type: \"");
        json.append(this.type);
        json.append("\"");

        json.append(", ");

        json.append("color: \"");
        if(isType(ForkNode))
            json.append("red");
        else if(isType(JoinNode))
            json.append("pink");
        else if(isType(BranchNode))
            json.append("cyan");
        else if(isType(InitialNode) || isType(FinalNode))
            json.append("black");
        else json.append("orange");
        json.append("\"");

        json.append("}");

        return json.toString();
    }

    public String toString(){
        StringBuilder str = new StringBuilder();

        str.append(this.type + ": " + this.name + ", id: " + this.id);

        return str.toString();
    }

}
