package com.pmverification;

import org.jgrapht.traverse.DepthFirstIterator;
import java.util.*;

public class Explorer {
    private UMLGraph graph;
    public int ignoreForkCount;
    public int ignoredForkTimes;

    public UMLGraph getGraph(){
        return graph;
    }

    Explorer(UMLGraph g){
        graph = g;
        ignoreForkCount = 0;
        ignoredForkTimes = 0;
    }

    //Obtains the possible followers of the node in a log, without any particular condition
    //It includes after-join nodes, which have as a condition that all other routes have been completed
    public Map<ArrayList<String>,ArrayList<String>> getNexts(Set<ArrayList<String>> k, Node node){
        Map<ArrayList<String>,ArrayList<String>> nexts = new HashMap<>();
        for (ArrayList<String> key : k) {
            nexts.put(key,new ArrayList<String>());
        }
        Set<Edge> out = graph.outgoingEdgesOf(node);
        for (Edge temp : out) {
            Node t = graph.getEdgeTarget(temp);
            if(t.getType().equals("uml:OpaqueAction")){
                for (ArrayList<String> key : k) {
                    ArrayList<String> refreshed = nexts.get(key);
                    refreshed.add(t.getName());
                    nexts.put(key,refreshed);
                }
            }else{
                if(t.getType().equals("uml:JoinNode")){
                    Set<ArrayList<String>> keys = findJoinKeys(node,t);
                    Map<ArrayList<String>,ArrayList<String>> succ = getNexts(keys,t);
                    Utilities.merge(nexts,succ);
                }else{
                    if(!t.getType().equals("uml:ActivityFinalNode")) {
                        Map<ArrayList<String>, ArrayList<String>> succ = getNexts(k, t);
                        Utilities.merge(nexts, succ);
                    }
                }
            }
        }
        return nexts;
    }

    //Find conditions to store a next, after-join, possible successor
    private Set<ArrayList<String>> findJoinKeys(Node start, Node join){
        Set<ArrayList<String>> keys = new HashSet<>();
        keys.add(new ArrayList<String>());
        Set<Edge> edges = graph.incomingEdgesOf(join);
        for (Edge edge : edges) {
            Node src = graph.getEdgeSource(edge);
            //In case of opaqueAction, we can store the condition
            if(src.getType().equals("uml:OpaqueAction") && src.getId() != start.getId()){
                for (ArrayList<String> el : keys) {
                    el.add(src.getName());
                }
            }
            //In case of joinNode, we must consider all joined routes as further conditions
            if(src.getType().equals("uml:JoinNode")){
                Set<ArrayList<String>> joinable = findJoinKeys(start,src);
                Set<ArrayList<String>> joined = new HashSet<>(keys);
                for(int i = 0;i<joinable.size()-1;i++)
                    joined.addAll(keys);
                Iterator<ArrayList<String>> iterator = joined.iterator();
                for (ArrayList<String> j : joinable) {
                    for(int i = 0;i<keys.size();i++){
                        Utilities.addAllNoDuplicates(iterator.next(),j);
                    }
                }
                keys = joined;
            }
            //In case of forkNode, the branch is empty and we can proceed further
            //In case of a decision node, only one path at a time is a condition
            if(src.getType().equals("uml:DecisionNode")){
                Set<ArrayList<String>> orKeys = findDecisionKeys(start, src);
                Set<ArrayList<String>> joined = new HashSet<>(keys);
                for(int i = 0;i<orKeys.size()-1;i++)
                    joined.addAll(keys);
                Iterator<ArrayList<String>> iterator = joined.iterator();
                for (ArrayList<String> j : orKeys) {
                    for(int i = 0;i<keys.size();i++){
                        Utilities.addAllNoDuplicates(iterator.next(),j);
                    }
                }
                keys = joined;
            }
        }
        return keys;
    }

    private Set<ArrayList<String>> findDecisionKeys(Node start, Node dec){
        Set<ArrayList<String>> keys = new HashSet<>();
        Set<Edge> edges = graph.incomingEdgesOf(dec);
        for (Edge edge : edges) {
            Node src = graph.getEdgeSource(edge);
            while(src.getType().equals("uml:ForkNode")){
                Set<Edge> backstep = graph.incomingEdgesOf(src);
                src = graph.getEdgeSource(backstep.iterator().next());
            }
            if(src.getType().equals("uml:OpaqueAction") && src.getId() != start.getId()){
                ArrayList<String> el = new ArrayList<>();
                el.add(src.getName());
                keys.add(el);
            }
            if(src.getType().equals("uml:JoinNode")){
                Set<ArrayList<String>> orable = findJoinKeys(start,src);
                keys.addAll(orable);
            }
            if(src.getType().equals("uml:DecisionNode")){
                Set<ArrayList<String>> orKeys = findDecisionKeys(start, src);
                keys.addAll(orKeys);
            }
        }
        return keys;
    }

    public HashSet<Node> exploreBackward(Node n){
        HashSet<Node> res = new HashSet<>();
        Set<Edge> edges = graph.incomingEdgesOf(n);
        for (Edge e : edges) {
            res.add(graph.getEdgeSource(e));
        }
        return res;
    }

    public boolean depthFirstSearch(Node start, Node target){
        DepthFirstIterator<Node, Edge> depthFirstIterator = new DepthFirstIterator<>(graph,start);
        while(depthFirstIterator.hasNext()){
            if(depthFirstIterator.next().equals(target))
                return true;
        }
        return false;
    }

    public int getBranchingEdges(Node n){
        return graph.outgoingEdgesOf(n).size();
    }
}
