package com.pmverification;

public class Edge {
    String type;
    String id;
    Guard guard;
    Weight weight;


    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setId(String id){
        this.id = id;
    }

    public int getId(){
        return Integer.parseInt(this.id.substring(5));
    }
    public void setGuard(Guard g){
        this.guard = g;
    }
    public Guard getGuard(){
        return this.guard;
    }
    public void setWeight(Weight w){
        this.weight = w;
    }
    public Weight getWeight(){
        return this.weight;
    }
}
class Guard {
    String type;
    String id;
    String name;
    String value;
    public Guard(String t,String id, String n, String v){
        this.type = t;
        this.id = id;
        this.name = n;
        this.value = v;
    }
}

class Weight {
    String type;
    String id;
    String name;
    
    public Weight(String t,String id,String name){
        this.type = t;
        this.id = id;
        this.name = name;
    }
}