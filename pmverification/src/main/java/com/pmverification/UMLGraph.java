package com.pmverification;

import org.jgrapht.graph.DirectedAcyclicGraph;

public class UMLGraph extends DirectedAcyclicGraph<Node,Edge> {

    public UMLGraph(Class<? extends Edge> edgeClass) {
        super(edgeClass);
    }
}
