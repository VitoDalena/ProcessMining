package com.pmverification;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Utilities {

    static void merge(Map<ArrayList<String>,ArrayList<String>> m1, Map<ArrayList<String>,ArrayList<String>> m2){
        Set<ArrayList<String>> keys1 = m1.keySet();
        Set<ArrayList<String>> keys2 = m2.keySet();
        for (ArrayList<String> key : keys2) {
            if(keys1.contains(key)){
                addAllNoDuplicates(m1.get(key), m2.get(key));
            }else{
                m1.put(key,m2.get(key));
            }
        }
    }

    static void addAllNoDuplicates(ArrayList<String> dest, ArrayList<String> news){
        dest.addAll(news);
        for (int i = 0;i<dest.size();i++) {
            String temp = dest.remove(i);
            if(!dest.contains(temp)){
                dest.add(i,temp);
            }
        }
    }


//    static UMLGraph createTestGraph(){
//        UMLGraph graph = new UMLGraph(Edge.class);
//        Node init = new Node();
//        init.type = "uml:InitialNode";
//        init.name = "start";
//        init.id = 0;
//        init.activityTime = "";
//        init.hasCost = "";
//        init.hasResource = "";
//        Node A = new Node();
//        A.type = "uml:OpaqueAction";
//        A.name = "A";
//        A.id = 1;
//        A.activityTime = "";
//        A.hasCost = "";
//        A.hasResource = "";
//        Node dec = new Node();
//        dec.type = "uml:DecisionNode";
//        dec.name = "dec1";
//        dec.id = 2;
//        dec.activityTime = "";
//        dec.hasCost = "";
//        dec.hasResource = "";
//        Node B = new Node();
//        B.type = "uml:OpaqueAction";
//        B.name = "B";
//        B.id = 3;
//        B.activityTime = "";
//        B.hasCost = "";
//        B.hasResource = "";
//        Node fork = new Node();
//        fork.type = "uml:ForkNode";
//        fork.name = "fork1";
//        fork.id = 4;
//        fork.activityTime = "";
//        fork.hasCost = "";
//        fork.hasResource = "";
//        Node C = new Node();
//        C.type = "uml:OpaqueAction";
//        C.name = "C";
//        C.id = 5;
//        C.activityTime = "";
//        C.hasCost = "";
//        C.hasResource = "";
//        Node D = new Node();
//        D.type = "uml:OpaqueAction";
//        D.name = "D";
//        D.id = 6;
//        D.activityTime = "";
//        D.hasCost = "";
//        D.hasResource = "";
//        Node E = new Node();
//        E.type = "uml:OpaqueAction";
//        E.name = "E";
//        E.id = 7;
//        E.activityTime = "";
//        E.hasCost = "";
//        E.hasResource = "";
//        Node join1 = new Node();
//        join1.type = "uml:JoinNode";
//        join1.name = "join1";
//        join1.id = 8;
//        join1.activityTime = "";
//        join1.hasCost = "";
//        join1.hasResource = "";
//        Node dec2 = new Node();
//        dec2.type = "uml:DecisionNode";
//        dec2.name = "dec2";
//        dec2.id = 9;
//        dec2.activityTime = "";
//        dec2.hasCost = "";
//        dec2.hasResource = "";
//        Node F = new Node();
//        F.type = "uml:OpaqueAction";
//        F.name = "F";
//        F.id = 10;
//        F.activityTime = "";
//        F.hasCost = "";
//        F.hasResource = "";
//        Node fin = new Node();
//        fin.type = "uml:ActivityFinalNode";
//        fin.name = "end";
//        fin.id = 11;
//        fin.activityTime = "";
//        fin.hasCost = "";
//        fin.hasResource = "";
//        graph.addVertex(init);
//        graph.addVertex(A);
//        graph.addVertex(B);
//        graph.addVertex(C);
//        graph.addVertex(D);
//        graph.addVertex(E);
//        graph.addVertex(F);
//        graph.addVertex(join1);
//        graph.addVertex(dec2);
//        graph.addVertex(fork);
//        graph.addVertex(dec);
//        graph.addVertex(fin);
//        graph.addEdge(init,A,new Edge(1));
//        graph.addEdge(A,dec,new Edge(2));
//        graph.addEdge(dec,B,new Edge(3));
//        graph.addEdge(dec,fork,new Edge(4));
//        graph.addEdge(dec,C,new Edge(5));
//        graph.addEdge(B,dec2,new Edge(6));
//        graph.addEdge(fork,D,new Edge(7));
//        graph.addEdge(fork,E,new Edge(8));
//        graph.addEdge(D,join1,new Edge(9));
//        graph.addEdge(E,join1,new Edge(10));
//        graph.addEdge(join1,dec2,new Edge(11));
//        graph.addEdge(C,dec2,new Edge(12));
//        graph.addEdge(dec2,F,new Edge(13));
//        graph.addEdge(F,fin,new Edge(14));
//        return graph;
//    }
//
//    static List<ProcessInstance> createTestList(){
//        AuditTrailEntry A = new AuditTrailEntry();
//        A.originator = "Vito";
//        A.timestamp = new Date(1);
//        A.eventType = "";
//        A.workflowModelElement = "A";
//        AuditTrailEntry B = new AuditTrailEntry();
//        B.originator = "Vito";
//        B.timestamp = new Date(2);
//        B.eventType = "";
//        B.workflowModelElement = "B";
//        AuditTrailEntry Bwrong = new AuditTrailEntry();
//        Bwrong.originator = "Vito";
//        Bwrong.timestamp = new Date(0);
//        Bwrong.eventType = "";
//        Bwrong.workflowModelElement = "B";
//        AuditTrailEntry C = new AuditTrailEntry();
//        C.originator = "Vito";
//        C.timestamp = new Date(2);
//        C.eventType = "";
//        C.workflowModelElement = "C";
//        AuditTrailEntry D = new AuditTrailEntry();
//        D.originator = "Vito";
//        D.timestamp = new Date(2);
//        D.eventType = "";
//        D.workflowModelElement = "D";
//        AuditTrailEntry E = new AuditTrailEntry();
//        E.originator = "Marco";
//        E.timestamp = new Date(2);
//        E.eventType = "";
//        E.workflowModelElement = "E";
//        AuditTrailEntry Ewrong = new AuditTrailEntry();
//        Ewrong.originator = "Vito";
//        Ewrong.timestamp = new Date(2);
//        Ewrong.eventType = "";
//        Ewrong.workflowModelElement = "E";
//        AuditTrailEntry F = new AuditTrailEntry();
//        F.originator = "Marco";
//        F.timestamp = new Date(4);
//        F.eventType = "";
//        F.workflowModelElement = "F";
//        ArrayList<ProcessInstance> log = new ArrayList<>();
//        ProcessInstance _1 = new ProcessInstance();
//        _1.numSimilarInstances = 10;
//        _1.description = "";
//        _1.groupedIdentifiers = 0;
//        _1.id = "0";
//        _1.entryList = new ArrayList<>();
//        _1.entryList.add(A);
//        _1.entryList.add(B);
//        _1.entryList.add(F);
//        ProcessInstance _2 = new ProcessInstance();
//        _2.numSimilarInstances = 10;
//        _2.description = "";
//        _2.groupedIdentifiers = 1;
//        _2.id = "1";
//        _2.entryList = new ArrayList<>();
//        _2.entryList.add(A);
//        _2.entryList.add(C);
//        _2.entryList.add(F);
//        ProcessInstance _3 = new ProcessInstance();
//        _3.numSimilarInstances = 10;
//        _3.description = "";
//        _3.groupedIdentifiers = 2;
//        _3.id = "2";
//        _3.entryList = new ArrayList<>();
//        _3.entryList.add(A);
//        _3.entryList.add(C);
//        _3.entryList.add(B);
//        _3.entryList.add(F);
//        ProcessInstance _4 = new ProcessInstance();
//        _4.numSimilarInstances = 10;
//        _4.description = "";
//        _4.groupedIdentifiers = 3;
//        _4.id = "3";
//        _4.entryList = new ArrayList<>();
//        _4.entryList.add(A);
//        _4.entryList.add(D);
//        _4.entryList.add(E);
//        _4.entryList.add(F);
//        ProcessInstance _5 = new ProcessInstance();
//        _5.numSimilarInstances = 10;
//        _5.description = "";
//        _5.groupedIdentifiers = 4;
//        _5.id = "4";
//        _5.entryList = new ArrayList<>();
//        _5.entryList.add(A);
//        _5.entryList.add(D);
//        _5.entryList.add(F);
//        ProcessInstance _6 = new ProcessInstance();
//        _6.numSimilarInstances = 10;
//        _6.description = "";
//        _6.groupedIdentifiers = 5;
//        _6.id = "5";
//        _6.entryList = new ArrayList<>();
//        _6.entryList.add(A);
//        _6.entryList.add(D);
//        _6.entryList.add(C);
//        _6.entryList.add(F);
//        ProcessInstance _7 = new ProcessInstance();
//        _7.numSimilarInstances = 10;
//        _7.description = "";
//        _7.groupedIdentifiers = 6;
//        _7.id = "6";
//        _7.entryList = new ArrayList<>();
//        _7.entryList.add(A);
//        _7.entryList.add(Ewrong);
//        _7.entryList.add(D);
//        _7.entryList.add(F);
//        ProcessInstance _8 = new ProcessInstance();
//        _8.numSimilarInstances = 10;
//        _8.description = "";
//        _8.groupedIdentifiers = 7;
//        _8.id = "7";
//        _8.entryList = new ArrayList<>();
//        _8.entryList.add(A);
//        _8.entryList.add(Bwrong);
//        _8.entryList.add(F);
//        log.add(_1);
//        log.add(_2);
//        log.add(_3);
//        log.add(_4);
//        log.add(_5);
//        log.add(_6);
//        log.add(_7);
//        log.add(_8);
//        return log;
//    }
    static List<ProcessInstance> parseLog(String logfile){
        //Log parsing
        //System.out.println("LOG PARSING");
        File log = new File(logfile);
        List<ProcessInstance> processlist= new ArrayList<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(log);
            NodeList nlist = doc.getElementsByTagName("ProcessInstance");
            for(int i = 0;i<nlist.getLength();i++){
                Node curnode = nlist.item(i);
                Element curelem = (Element) curnode;
                ProcessInstance process = new ProcessInstance();
                process.setId(curelem.getAttribute("id"));
                process.setDes(curelem.getAttribute("description"));
                //Set numsimlarinstances and/or grouped instances
                NodeList children = curelem.getElementsByTagName("Attribute");
                for(int y = 0;y<children.getLength();y++){
                    Element child = (Element) children.item(y);
                    //System.out.println(child.getNodeName());
                    if(child.getAttribute("name").equals("numSimilarInstances")){
                        process.setNumSimInst(Integer.parseInt(child.getTextContent()));
                        
                    }else if(child.getAttribute("name").equals("GroupedIdentifiers")){
                        process.setGroupedId(Integer.parseInt(child.getTextContent()));
                        
                    }
                }
                children = curelem.getElementsByTagName("AuditTrailEntry");
                //Set AuditTrailEntry
                for(int y = 0;y<children.getLength();y++){
                    Element child = (Element) children.item(y);
                    AuditTrailEntry entry = new AuditTrailEntry();
                    NodeList entrychildren = child.getChildNodes();
                    for(int x = 0;x<entrychildren.getLength();x++){
                        Node entrychild  = entrychildren.item(x);
                        if (entrychild.getNodeType() == Node.ELEMENT_NODE){
                            String nodename = entrychild.getNodeName();
                            //System.out.println(nodename);
                            switch (nodename) {
                                case "WorkflowModelElement":
                                    entry.setWorkflow(entrychild.getTextContent());
                                    //System.out.println(entrychild.getTextContent());
                                    break;
                                case "EventType":
                                    entry.setEvent(entrychild.getTextContent());
                                    break;
                                case "Originator":
                                    entry.setOriginator(entrychild.getTextContent());
                                    break;
                                case "Timestamp":
                                    entry.setTimestamp(entrychild.getTextContent());
                                    break;
                                default:
                                    //System.out.println("ERROR:" + nodename + " not implemented!");//Should not happen
                                    break;
                            }
                        }
                    }
                    process.addEntry(entry);
                }
                processlist.add(process);
            }
        }catch(ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();
        }
        return processlist;

    }
}
