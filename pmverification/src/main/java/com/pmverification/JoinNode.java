package com.pmverification;

public class JoinNode extends com.pmverification.Node {
    String joinSpec_type;
    String joinSpec_id;
    String joinSpec_name;
    String joinSPec_value;

	public JoinNode(com.pmverification.Node node){
        this.type = node.type;
        this.id = node.id;
        this.name = node.name;
        this.hasResource = node.hasResource;
        this.hasCost = node.hasCost;
        this.activityTime = node.activityTime;
    }   
    void setJType(String type){
        this.joinSpec_type = type;
    }
    void setJId(String id){
        this.joinSpec_id = id;
    }
    void setJName(String name){
        this.joinSpec_name = name;
    }
    void setJValue(String value){
        this.joinSPec_value = value;
    }
}