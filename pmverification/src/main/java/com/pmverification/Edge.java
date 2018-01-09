package com.pmverification;

public class Edge {
    private String type;
    private String id;
    private Guard guard;
    private Weight weight;


    public String getType(){
        return this.type;
    }
    public void setType(String type){ this.type = type; }

    public int getId(){
        return Integer.parseInt(this.id.substring(5));
    }
    public void setId(String id){
        this.id = id;
    }

    public Guard getGuard(){
        return this.guard;
    }
    public void setGuard(Guard g){
        this.guard = g;
    }

    public Weight getWeight(){
        return this.weight;
    }
    public void setWeight(Weight w){
        this.weight = w;
    }
}
class Guard {
    private String type;
    private String id;
    private String name;
    private String value;
    public Guard(String t,String id, String n, String v){
        this.type = t;
        this.id = id;
        this.name = n;
        this.value = v;
    }
}

class Weight {
    private String type;
    private String id;
    private String name;
    
    public Weight(String t,String id,String name){
        this.type = t;
        this.id = id;
        this.name = name;
    }
}