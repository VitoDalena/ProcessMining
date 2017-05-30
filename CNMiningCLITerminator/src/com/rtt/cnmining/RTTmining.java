package com.rtt.cnmining;

import java.util.ArrayList;

public class RTTmining {

    private LogInspector log;
    private FlexInspector causalnet;

    public RTTmining(LogInspector log, FlexInspector causalnet){
        this.log = log;
        this.causalnet = causalnet;
    }

    private boolean isValidGraph(){
        return true;
    }

    public RTTgraph process(){
        // Inizializza il grafo, inserendovi i nodi rappresentanti le attività
        RTTgraph graph = new RTTgraph();

        ArrayList<String> startActivities = this.causalnet.startActivities();
        ArrayList<String> endActivities = this.causalnet.endActivities();

        for (String activity: this.causalnet.activities()){
            RTTnode node = new RTTnode(activity);

            if(startActivities.contains(activity))
                node.initialNode();
            else if(endActivities.contains(activity))
                node.finalNode();

            graph.add(node);
        }

        findEdges(graph);

        return graph;
    }

    private void findEdges(RTTgraph graph){
        // Aggiungo per primi gli archi
        // i quali si presentano come unico outgoing nell'insieme
        // dei followers del nodo considerato
        for(RTTnode node: graph.nodes()){
            ArrayList<String> followers = this.causalnet.followers(node.name);

            if(followers.size() == 1){
                String activity = followers.get(0);

                RTTnode endNode = graph.node(activity);
                if(endNode == null)
                    continue;

                RTTedge edge = new RTTedge(node, endNode);
                graph.add(edge);
            }
        }
    }

}
