package com.pmverification;

import java.util.ArrayList;
import java.util.List;

public class ProcessInstance {
    String id;
    String description;
    List<AuditTrailEntry> entryList;
    int numSimilarInstances;
    int groupedIdentifiers;
    
    public ProcessInstance(){
        this.entryList = new ArrayList<AuditTrailEntry>();
    }
    
    public void setId(String id){
        this.id = id;
    }
    public void setDes(String des){
        this.description = des;
    }
    public void addEntry(AuditTrailEntry e){
        this.entryList.add(e);
    }
    public void setNumSimInst(int n){
        this.numSimilarInstances = n;
    }
    public void setGroupedId(int n){
        this.groupedIdentifiers = n;
    }
}
