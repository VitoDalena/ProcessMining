package com.rtt.cnmining;

import java.util.ArrayList;

public class RTTnode {

    private String name;
    private String id;
    ArrayList<RTTedge> incoming;
    ArrayList<RTTedge> outcoming;

    private static int idCounter = 0;

    public RTTnode(String name){
        this.name = name;
        this.id = "Node-" + idCounter;
        idCounter++;
    }

    public RTTnode(String name, String id){
        this.name = name;
        this.id = id;
    }

    public ArrayList<RTTedge> outcoming(){
        return this.outcoming;
    }

    public ArrayList<RTTedge> incoming(){
        return this.incoming;
    }

    public String name(){
        return this.name;
    }

    public String id(){
        return this.id;
    }

    public String toXMI(){
        StringBuilder str = new StringBuilder();

        return str.toString();
    }

    public String toString(){
        StringBuilder str = new StringBuilder();



        return str.toString();
    }

}
