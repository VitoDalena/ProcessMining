package org.processmining.plugins.cnet2ad;

import org.processmining.models.graphbased.directed.bpmn.BPMNDiagram;
import org.processmining.models.graphbased.directed.bpmn.BPMNNode;
import org.processmining.models.graphbased.directed.bpmn.elements.Flow;
import org.processmining.models.graphbased.directed.bpmn.elements.Gateway;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Cnet2ADWithBPMN {

    BPMNDiagram model;

    public Cnet2ADWithBPMN(BPMNDiagram diagram){
        this.model = diagram;
    }

    public ADgraph process(){
        // Inizializza il grafo inserendovi i nodi rappresentanti le attività
        ADgraph graph = new ADgraph();

        this.computeNodes(graph);
        this.computeEdges(graph);
        this.fixOutcomingEdges(graph);
        this.fixIncomingEdges(graph);

        return graph;
    }

    private void computeNodes(ADgraph graph){
        for(BPMNNode node:this.model.getNodes()){
            ADnode n = new ADnode(node.getLabel());

            if(node.getLabel().equals("start")){
                n.initialNode();
                graph.add(n);
                continue;
            }

            if(node.getLabel().equals("end")){
                n.finalNode();
                graph.add(n);
                continue;
            }

            // Controllo se si tratta di un nodo speciale
            Collection<Gateway> gateways = this.model.getGateways();
            Iterator<Gateway> g = gateways.iterator();
            while(g.hasNext()){
                Gateway gateway = g.next();

                if(gateway.getLabel().equals(node.getLabel())){
                    if(gateway.getGatewayType() == Gateway.GatewayType.PARALLEL)
                        n.fork();
                    else n.branch();

                    break;
                }
            }

            // Siccome non posso esmainare esattamente se è un fork o un join
            // verifico, se ho in output un solo arco, è un join
            if(n.isType(ADnode.ForkNode) && this.model.getOutEdges(node).size() == 1)
                n.join();

            graph.add(n);
        }
    }

    private void computeEdges(ADgraph graph){
        Collection<Flow> flows = this.model.getFlows();
        Iterator<Flow> i = flows.iterator();
        while(i.hasNext()){
            Flow flow = i.next();

            ADnode source = graph.node(flow.getSource().getLabel());
            ADnode target = graph.node(flow.getTarget().getLabel());

            if(source != null && target != null )
                graph.add(new ADedge(source, target));
            else System.out.println("[Warning::computeEdges] " +
                    flow.getSource().getLabel() + " -> " + flow.getTarget().getLabel()
            );
        }
    }

    private void fixOutcomingEdges(ADgraph graph){
        ArrayList<ADnode> addAtTheEnd = new ArrayList<ADnode>();
        for(ADnode node: graph.nodes()){

            if(node.isType(ADnode.Node) == false)
                continue;

            ArrayList<ADedge> edges = graph.edgesStartWith(node);
            boolean foundFork = false;
            if(edges.size() > 1){
                for(ADedge e: edges){
                    if(e.end().isType(ADnode.ForkNode))
                    {
                        foundFork = true;
                        break;
                    }
                }
            }
            else continue;

            if(foundFork)
                continue;

            ADnode forkNode = new ADnode("Fork" + node.name);
            forkNode.fork();
            addAtTheEnd.add(forkNode);

            for(ADedge e: edges){
                e.begin(forkNode);
            }
            graph.add(new ADedge(node, forkNode));
        }

        for(ADnode forkNode:addAtTheEnd)
            graph.add(forkNode);
    }

    private void fixIncomingEdges(ADgraph graph){
        ArrayList<ADnode> addAtTheEnd = new ArrayList<ADnode>();
        for(ADnode node: graph.nodes()){

            if(node.isType(ADnode.Node) == false)
                continue;

            ArrayList<ADedge> edges = graph.edgesEndWith(node);
            if(edges.size() > 1){
                ADnode branchNode = new ADnode("BranchIn" + node.name);
                branchNode.branch();
                addAtTheEnd.add(branchNode);

                for(ADedge e: edges){
                    e.end(branchNode);
                }
                graph.add(new ADedge(branchNode, node));
            }
            else continue;
        }

        for(ADnode branchNode:addAtTheEnd)
            graph.add(branchNode);
    }
}
