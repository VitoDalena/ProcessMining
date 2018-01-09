package com.pmverification;

import java.util.ArrayList;
import java.util.List;

public class ProcessInstance {
    private String id;
    private String description;
    private List<AuditTrailEntry> entryList;
    private int numSimilarInstances;
    private int groupedIdentifiers;
    
    public ProcessInstance(){
        this.entryList = new ArrayList<>();
        this.numSimilarInstances = 1;
    }

    public String getId() { return id; }
    public void setId(String id){ this.id = id; }

    public String getDescription() { return description; }
    public void setDes(String des){
        this.description = des;
    }

    public List<AuditTrailEntry> getEntryList() { return entryList; }
    public void addEntry(AuditTrailEntry e){
        this.entryList.add(e);
    }

    public int getNumSimilarInstances() { return numSimilarInstances; }
    public void setNumSimInst(int n){
        this.numSimilarInstances = n;
    }

    public int getGroupedIdentifiers() { return groupedIdentifiers; }
    public void setGroupedId(int n){
        this.groupedIdentifiers = n;
    }
}
