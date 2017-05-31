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
        // Inizializza il grafo inserendovi i nodi rappresentanti le attività
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

        // lista dei nodi che non devono essere più processati
        ArrayList<RTTnode> residui = findDirectEdges(graph);

        if(residui.size() == 0){
            // Siamo stati fortunati, non sono presenti diramazioni
            // o fork per attività parallele
            return graph;
        }

        // Invece, sono presenti diramazioni.
        // Occorre individuare i nodi di fork e branch.

        for(RTTnode node: residui){
            this.findOutgoingEdges(graph, node);
        }

        return graph;
    }

    /*
        Trova tutti gli archi diretti,
        li dove un nodo presenza un insieme di followers di cardinalità 1.
        Ritorna la lista di nodi ai quali non è stato possibile mappare gli
        archi outgoing, in quanto seguito da diramazioni o fork.
     */
    private ArrayList<RTTnode> findDirectEdges(RTTgraph graph) {
        ArrayList<RTTnode> result = new ArrayList<RTTnode>();

        for (RTTnode node : graph.nodes()) {
            ArrayList<String> followers = this.causalnet.followers(node.name);

            if (followers.size() == 1) {
                String activity = followers.get(0);

                RTTnode endNode = graph.node(activity);
                if (endNode == null)
                    continue;

                RTTedge edge = new RTTedge(node, endNode);
                graph.add(edge);
            }
            else if(followers.size() == 0){
                // Si tratta del nodo finale
                // vale come completato
            }
            else {
                // il nodo in esame non è stato mappato
                // inseriscilo tra i residui
                if (result.contains(node) == false)
                    result.add(node);
            }
        }

        return result;
    }

    /*

     */
    private boolean findOutgoingEdges(RTTgraph graph, RTTnode node){
        ArrayList<String> followers = this.causalnet.followers(node.name);

        if(followers.size() <= 1)
            return false;

        PatternMap map = new PatternMap(this.log);
        BranchPattern pattern = map.ANDsplit(node.name);

        if(pattern != null){
            // cè un ANDsplit
            // dobbiamo verificare se è solo un AND o un OR -> (arco1, arco2, ..., arcoN, AND)
            ArrayList<String> andList = new ArrayList<>();

            // Controllo che tutti i follower siano inclusi nel pattern
            for(String activity: followers){
                if(pattern.branches.contains(activity) && andList.contains(activity) == false)
                    andList.add(activity);
            }

            // Sono tutti in AND
            if(andList.size() == followers.size()){
                RTTnode forkNode = new RTTnode("Fork" + node.name);
                forkNode.fork();

                graph.add(forkNode);

                RTTedge firstEdge = new RTTedge(node, forkNode);
                graph.add(firstEdge);

                for(String activity: followers){
                    RTTnode endNode = graph.node(activity);

                    if(endNode == null)
                        continue;

                    RTTedge endEdge = new RTTedge(forkNode, endNode);
                    graph.add(endEdge);
                }
            }
            // Ho la casistica OR -> arco1, arco2,... arcoN, AND
            else {
                System.out.println("ORAND");
                // Aggiungo prima il nodo OR
                RTTnode branchNode = new RTTnode("Branch" + node.name);
                branchNode.branch();

                graph.add(branchNode);

                // Aggiungo il nodo Fork
                RTTnode forkNode = new RTTnode("Fork" + node.name);
                forkNode.fork();

                graph.add(forkNode);

                // Aggiungo l'arco che collega il nodo in esame con l'OR
                graph.add(new RTTedge(node, branchNode));
                // Collego l'OR con il fork
                graph.add(new RTTedge(branchNode, forkNode));

                for(String activity: followers){
                    RTTnode endNode = graph.node(activity);
                    if(endNode == null)
                        continue;

                    if(andList.contains(activity))
                        graph.add(new RTTedge(forkNode, endNode));
                    else graph.add(new RTTedge(branchNode, endNode));
                }
            }
        }
        else {
            RTTnode branchNode = new RTTnode("Branch" + node.name);
            branchNode.branch();

            graph.add(branchNode);

            RTTedge firstEdge = new RTTedge(node, branchNode);
            graph.add(firstEdge);

            for(String activity: followers){
                RTTnode endNode = graph.node(activity);

                if(endNode != null)
                    continue;

                RTTedge endEdge = new RTTedge(branchNode, endNode);
                graph.add(endEdge);
            }
        }

        return true;
    }
}
