package com.rtt.cnmining;

import java.util.ArrayList;

public class RTTnode {

    private String name;
    private String id;
    private ArrayList<RTTedge> incoming;
    private ArrayList<RTTedge> outcoming;
    private String type;

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

    public void split(){
        this.type = "split";
    }

    public void join(){
        this.type = "join";
    }

    public void type(String value){
        this.type = value;
    }

    public String type(){
        return this.type;
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
