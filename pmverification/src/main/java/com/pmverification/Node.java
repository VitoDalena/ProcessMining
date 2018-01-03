package com.pmverification;

public class Node {
    String type;
    String id;
    String name;
    String hasResource;
    String hasCost;
    String activityTime;

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
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setHasResource(String hr){
        this.hasResource = hr;
    }
    public String getHasResource(){
        return this.hasResource;
    }
    public void setHasCost(String hc){
        this.hasCost = hc;
    }
    public String getHasCost(){
        return this.hasCost;
    }      
    public void setActivityTime(String at){
        this.activityTime = at;
    }    
    public String getActivityTime(){
        return this.activityTime;
    } 
}
