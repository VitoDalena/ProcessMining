package com.pmverification;

import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import org.jgrapht.traverse.DepthFirstIterator;

import java.util.*;

public class RuleBook {
    private Explorer explorer;

    RuleBook(UMLGraph g){
        explorer = new Explorer(g);
    }

    public Set<Map<String, Pattern<AuditTrailEntry,?>>> getRules(List<AuditTrailEntry> log){
        Set<Map<String, Pattern<AuditTrailEntry,?>>> rules = new HashSet<>();
        for (AuditTrailEntry l : log) {
            Node modelEntry = getModelEntry(l.workflowModelElement);
            rules.add(getRule(modelEntry,log,log.indexOf(l)));
        }
        return rules;
    }

    private Node getModelEntry(String l){
        //Ricerca nel grafo un nodo con le caratteristiche richieste
        DepthFirstIterator<Node, Edge> depthFirstIterator = new DepthFirstIterator<>(explorer.getGraph());
        boolean found = false;
        Node n = new Node();
        while(!found && depthFirstIterator.hasNext()){
            n = depthFirstIterator.next();
            if(n.name.equals(l))
                found = true;
        }
        if(!found)
            n.type = "Error_NotFound";
        return n;
    }

    private Map<String, Pattern<AuditTrailEntry,?>> getRule(Node start, List<AuditTrailEntry> log, int index){
        final String initialNode = start.name;
        HashMap<String, Pattern<AuditTrailEntry,?>> rules = new HashMap<>();
        //Trova i nexts diretti
        HashSet<ArrayList<String>> hs = new HashSet<>();
        hs.add(null);
        final Map<ArrayList<String>,ArrayList<String>> directNexts = explorer.getNexts(hs, start);
        //Trova i nexts dovuti a paralleli precedenti
        Node candidate;
        ArrayList<String> candidateMemory = new ArrayList<>();
        HashSet<Node> parallelMemory = new HashSet<>();
        Set<ArrayList<String>> keys;
        ArrayList<String> key;
        Map<ArrayList<String>, ArrayList<String>> temp;
        Map<ArrayList<String>, ArrayList<String>> advParallelNexts = new HashMap<>();
        for(int i = 0;i<index;i++) {
            candidate = getModelEntry(log.get(i).workflowModelElement);
            candidateMemory.add(candidate.name);
            if (isParallel(start, candidate)) {
                parallelMemory.add(candidate);
                keys = new HashSet<>();
                key = new ArrayList<>();
                key.add(candidate.name);
                keys.add(key);
                temp = explorer.getNexts(keys,candidate);
                Set<ArrayList<String>> tkey = new HashSet<>(temp.keySet());
                for (ArrayList<String> k : tkey) {
                    if(k.contains(start.name) || temp.get(k).isEmpty()){
                        temp.remove(k);
                        parallelMemory.remove(candidate);
                    }
                }
                Utilities.merge(advParallelNexts,temp);
            }
        }
        //Trova i nexts dovuti a paralleli non menzionati
        //Escludi da questi ultimi il proprio ramo e quelli del secondo punto
        Map<ArrayList<String>,ArrayList<String>> parallelNexts = new HashMap<>();
        HashSet<Node> forkingNodes = isForked(start);
        for (Node n : forkingNodes)
            Utilities.merge(parallelNexts,checkValid(n,start,parallelMemory,forkingNodes));
        Utilities.merge(advParallelNexts,parallelNexts);
        //Ottieni la sequenza precedente e carica la regola nel pattern
        //Esecuzione fuori tempo, da selezionare nel CEP
        Pattern sequence = Pattern.<AuditTrailEntry>begin("1").where(new SimpleCondition<AuditTrailEntry>() {
            @Override
            public boolean filter(AuditTrailEntry event) {
                return event.workflowModelElement.equals(initialNode);
            }
        }).next("2").where(new SimpleCondition<AuditTrailEntry>() {
            @Override
            public boolean filter(AuditTrailEntry event) {
                return false;
            }
        });
        for(ArrayList<String> k : directNexts.keySet()){
            if(k == null || candidateMemory.containsAll(k)){
                for (final String a : directNexts.get(k)) {
                    sequence.or(new SimpleCondition<AuditTrailEntry>() {
                        @Override
                        public boolean filter(AuditTrailEntry event) {
                            return event.workflowModelElement.equals(a);
                        }
                    });
                }
            }
        }
        rules.put("Sequence",sequence);
        //Esecuzione con risorsa occupata, da selezionare nel CEP
        Pattern<AuditTrailEntry,?> resourceOccupied = Pattern.<AuditTrailEntry>begin("1").where(new SimpleCondition<AuditTrailEntry>() {
            @Override
            public boolean filter(AuditTrailEntry event) {
                return event.workflowModelElement.equals(initialNode);
            }
        }).next("2").where(new SimpleCondition<AuditTrailEntry>() {
            @Override
            public boolean filter(AuditTrailEntry event) {
                return false;
            }
        });
        for(ArrayList<String> k : advParallelNexts.keySet()) {
            if (k == null || candidateMemory.containsAll(k)) {
                for (final String a : advParallelNexts.get(k)) {
                    sequence.or(new SimpleCondition<AuditTrailEntry>() {
                        @Override
                        public boolean filter(AuditTrailEntry event) {
                            return event.workflowModelElement.equals(a);
                        }
                    });
                }
            }
        }
        rules.put("ResourceOccupied",resourceOccupied);
        //Rottura di sequenza
        Utilities.merge(directNexts,advParallelNexts);
        Pattern<AuditTrailEntry,?> outOfSequence = Pattern.<AuditTrailEntry>begin("1").where(new SimpleCondition<AuditTrailEntry>() {
            @Override
            public boolean filter(AuditTrailEntry event) {
                return event.workflowModelElement.equals(initialNode);
            }
        });
        for(ArrayList<String> k : directNexts.keySet()){
            if(k == null || candidateMemory.containsAll(k)){
                for (final String a : directNexts.get(k)) {
                    outOfSequence.notNext(a).where(new SimpleCondition<AuditTrailEntry>() {
                        @Override
                        public boolean filter(AuditTrailEntry event) {
                            return event.workflowModelElement.equals(a);
                        }
                    });
                }
            }
        }
        rules.put("OutOfSequence",outOfSequence);
        return rules;
    }

    private Map<ArrayList<String>,ArrayList<String>> checkValid(Node n, Node start, Set<Node> parallelMemory, Set<Node> forkingNodes){
        Map<ArrayList<String>,ArrayList<String>> nexts;
        String c;
        HashSet<ArrayList<String>> hs = new HashSet<>();
        hs.add(null);
        nexts = explorer.getNexts(hs,n);
        if(nexts.get(null).contains(start.name)){
            nexts.get(null).remove(start.name);
        }
        for (Node p : parallelMemory) {
            if(nexts.get(null).contains(p.name)){
                nexts.get(null).remove(p.name);
            }
        }
        for (Node f : forkingNodes) {
            if(nexts.get(null).contains(f.name)){
                nexts.get(null).remove(f.name);
            }
        }
        for(int i = 0;i<nexts.get(null).size();i++) {
            c = nexts.get(null).get(i);
            if(!checkRoute(getModelEntry(c),start,parallelMemory,forkingNodes)){
                nexts.get(null).remove(c);
            }
        }
        return nexts;
    }

    private boolean checkRoute(Node n, Node start, Set<Node> parallelMemory, Set<Node> forkingNodes){
        HashSet<ArrayList<String>> hs = new HashSet<>();
        hs.add(null);
        Map<ArrayList<String>,ArrayList<String>> nexts = explorer.getNexts(hs,n);
        if(nexts.get(null).contains(start.name)){
            nexts.get(null).remove(start.name);
        }
        for (Node p : parallelMemory) {
            if(nexts.get(null).contains(p.name)){
                nexts.get(null).remove(p.name);
            }
        }
        for (Node f : forkingNodes) {
            if(nexts.get(null).contains(f.name)){
                nexts.get(null).remove(f.name);
            }
        }
        if(!nexts.keySet().equals(hs) && nexts.get(null).isEmpty())
            return true;
        else{
            if(nexts.get(null).isEmpty())
                return false;
            else{
                boolean postReturn = false;
                for (String c : nexts.get(null)) {
                    if(checkRoute(getModelEntry(c),start,parallelMemory,forkingNodes))
                        postReturn = true;
                }
                return postReturn;
            }
        }
    }

    private HashSet<Node> isForked(Node n){
        //Controlla se un nodo nel passato è un fork
        HashSet<Node> res = new HashSet<>();
        HashSet<Node> backs = explorer.exploreBackward(n);
        if(n.type.equals("uml:DecisionNode") || n.type.equals("uml:JoinNode")) {
            if(n.type.equals("uml:JoinNode"))
                explorer.ignoreForkCount++;
            for (Node b : backs) {
                HashSet<Node> temp = isForked(b);
                if (!temp.equals(new HashSet<>())) {
                    res.addAll(temp);
                }
            }
        }
        if(n.type.equals("uml:OpaqueAction")){
            HashSet<Node> temp = isForked(backs.iterator().next());
            if (!temp.equals(new HashSet<>())) {
                res.addAll(temp);
            }
        }
        //Se sì, ritorna tutti gli opaque subito prima
        if (n.type.equals("uml:ForkNode")) {
            if (explorer.ignoreForkCount > 0) {
                explorer.ignoreForkCount--;
                explorer.ignoredForkTimes++;
                explorer.ignoreForkCount = explorer.getBranchingEdges(n) - explorer.ignoredForkTimes;
                if (explorer.ignoreForkCount == 0) {
                    explorer.ignoredForkTimes = 0;
                    for (Node b : backs) {
                        HashSet<Node> temp = isForked(b);
                        if (!temp.equals(new HashSet<>())) {
                            res.addAll(temp);
                        }
                    }
                }
            } else {
                res.add(n);
            }
        }
        return res;
    }

    private boolean isParallel(Node a, Node b){
        //Trova i fork prima di a
        HashSet<Node> forks = isForked(a);
        //Controlla se da essi si può trovare b
        //Se sì ritorna true
        for (Node f : forks) {
            if (explorer.depthFirstSearch(f, b)) {
                return true;
            }
        }
        //Se non lo trovi, ritorna false
        return false;
    }
}
