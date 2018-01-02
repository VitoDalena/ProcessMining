package com.pmverification;

public class Edge {
    String type;
    int id;
    Guard guard;
    Weight weight;

    public Edge(int id){
        this.id = id;
    }
}

class Guard {
    String type;
    String id;
    String name;
    String value;
}

class Weight {
    String type;
    String id;
    String name;
}
