package com.rtt.cnmining;

import java.util.ArrayList;
import java.util.Random;

public class RTTgraph {

    private ArrayList<RTTnode> nodes;
    private ArrayList<RTTedge> edges;
    private String name;

    public RTTgraph(){
        this.name = "Activity Diagram";
        this.nodes = new ArrayList<RTTnode>();
        this.edges = new ArrayList<RTTedge>();
    }

    public RTTgraph(String name){
        this.nodes = new ArrayList<RTTnode>();
        this.edges = new ArrayList<RTTedge>();
        this.name = name;
    }

    public void add(RTTnode node){
        if(this.nodes.contains(node) == false)
            this.nodes.add(node);
    }

    public void add(RTTedge edge){
        if(this.edges.contains(edge) == false)
            this.edges.add(edge);
    }

    public ArrayList<RTTnode> nodes(){
        return this.nodes;
    }

    public ArrayList<RTTnode> nodesByType(String type){
        ArrayList<RTTnode> result = new ArrayList<>();

        for(RTTnode node: this.nodes())
        {
            if(node.isType(type) && result.contains(node) == false)
                result.add(node);
        }

        return result;
    }

    public RTTnode node(String name){
        for(RTTnode node: this.nodes()){
            if(node.name.equals((name)))
                return node;
        }
        return null;
    }

    public RTTnode nodeById(String id){
        for(RTTnode node: this.nodes()){
            if(node.id.equals((id)))
                return node;
        }
        return null;
    }

    public ArrayList<RTTnode> followers(RTTnode node){
        ArrayList<RTTnode> result = new ArrayList<>();

        for(RTTedge edge: this.edgesStartWith(node)){
            if(result.contains(edge.end()) == false)
                result.add(edge.end());
        }

        return result;
    }

    public ArrayList<RTTnode> predecessors(RTTnode node){
        ArrayList<RTTnode> result = new ArrayList<>();

        for(RTTedge edge: this.edgesEndWith(node)){
            if(result.contains(edge.begin()) == false)
                result.add(edge.begin());
        }

        return result;
    }

    public ArrayList<RTTedge> edges(){
        return this.edges;
    }

    // Ritorna la lista di archi in uscita dal nodo specificato
    public ArrayList<RTTedge> edgesStartWith(RTTnode node){
        ArrayList<RTTedge> result = new ArrayList<>();

        if(this.nodes.contains(node) == false)
            return result;

        for(RTTedge edge: this.edges()){
            if(edge.begin().equals(node) && result.contains(edge) == false)
                result.add(edge);
        }

        return result;
    }

    // Ritorna la lista di archi in entrata dal nodo specificato
    public ArrayList<RTTedge> edgesEndWith(RTTnode node){
        ArrayList<RTTedge> result = new ArrayList<>();

        if(this.nodes.contains(node) == false)
            return result;

        for(RTTedge edge: this.edges()){
            if(edge.end().equals(node) && result.contains(edge) == false)
                result.add(edge);
        }

        return result;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();

        str.append("RTTgraph:" + this.name);
        str.append("\nNodes:");
        for (RTTnode node: this.nodes()) {
            str.append("\n");
            str.append(node);
        }

        str.append("\nEdges:");
        for (RTTedge edge: this.edges()) {
            str.append("\n");
            str.append(edge);
        }

        return str.toString();
    }

    public String toJson(){
        StringBuilder json = new StringBuilder();

        String comma = "";

        json.append("[\n");
        for (RTTnode node: this.nodes()) {
            json.append(comma + node.toJson());
            comma = ",\n";
        }
        json.append("\n]");

        json.append(",\n");

        comma = "";
        json.append("[\n");
        for (RTTedge edge: this.edges()) {
            json.append(comma + edge.toJson());
            comma = ",\n";
        }
        json.append("\n]");

        return json.toString();
    }

    public String toXMI(){
        StringBuilder xmi = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

        Random random = new Random();

        xmi.append("\n");
        xmi.append("<uml:Model xmi:version=\"20131001\" xmlns:xmi=\"http://www.omg.org/spec/XMI/20131001\" " +
                "xmlns:uml=\"http://www.eclipse.org/uml2/5.0.0/UML\" xmi:id=\"" +
                random.nextInt(99999999)
                + "\" name=\"" + this.name + "\">");
        xmi.append("\n");
        xmi.append("<packagedElement xmi:type=\"uml:Activity\" xmi:id=\"" +
                random.nextInt(99999999)
                + "\" name=\"" + this.name +
                "\" node=\"");
        for(RTTnode node: this.nodes()){
            xmi.append(node.id + " ");
        }
        xmi.append("\">");

        for(RTTnode node: this.nodes()){
            xmi.append("\n");
            xmi.append(node.toXMI());
        }


        xmi.append("\n");
        xmi.append("</packagedElement>");
        xmi.append("\n");
        xmi.append("</uml:Model>");

        return xmi.toString();
    }

}
