package com.pmverification;

import java.io.Serializable;

public class Node implements Serializable {
    protected String type;
    protected String id;
    protected String name;
    protected String hasResource;
    protected String hasCost;
    protected String activityTime;

    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }

    public int getId(){ return Integer.parseInt(this.id.substring(5)); }
    public void setId(String id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getHasResource(){ if(hasResource != null) return this.hasResource; else return ""; }
    public void setHasResource(String hr){
        this.hasResource = hr;
    }

    public String getHasCost(){ if(hasCost != null) return this.hasCost; else return ""; }
    public void setHasCost(String hc){
        this.hasCost = hc;
    }

    public String getActivityTime(){ if(activityTime != null) return this.activityTime; else return "0"; }
    public void setActivityTime(String at){
        this.activityTime = at;
    }
}
