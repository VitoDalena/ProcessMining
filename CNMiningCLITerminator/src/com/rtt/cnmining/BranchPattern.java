package com.rtt.cnmining;

import java.util.ArrayList;

public class BranchPattern {
    public String activity;
    public ArrayList<String> branches;
    public String type;

    public boolean split = true;

    public BranchPattern(){
        this.activity = "";
        this.branches = new ArrayList<>();
    }

    public BranchPattern(String activity){
        this.activity = activity;
        this.branches = new ArrayList<>();
    }

    public BranchPattern(String activity, ArrayList<String> branches){
        this.activity = activity;
        this.branches = branches;
    }

    public void AND(){
        this.AND(true);
    }

    public void AND(boolean split){
        this.type = "AND";
        this.split = split;
    }

    public void OR(){
        this.OR(true);
    }

    public void OR(boolean split){
        this.type = "OR";
        this.split = split;
    }

    public void XOR(){
        this.XOR(true);
    }

    public void XOR(boolean split){
        this.type = "XOR";
        this.split = split;
    }

    public String toString(){
        String result = "[" + this.type + "] ";
        if(split){
            result += this.activity + " -> [";
            String comma = "";
            for(int i = 0; i < branches.size(); i++)
            {
                result += comma + branches.get(i);
                comma = ", ";
            }
            result += "]";
        }
        else {
            String comma = "[";
            for(int i = 0; i < branches.size(); i++)
            {
                result += comma + branches.get(i);
                comma = ", ";
            }
            result += "]";
            result += " -> " + this.activity;
        }
        return result;
    }
}
