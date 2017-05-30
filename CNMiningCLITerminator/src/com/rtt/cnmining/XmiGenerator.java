package com.rtt.cnmining;

import org.processmining.models.flexiblemodel.Flex;
import org.processmining.models.graphbased.directed.DirectedGraph;

import java.util.*;

public class XmiGenerator {

    private Flex graph;
    private LogInspector log;
    private PatternMap patternMap;

    public XmiGenerator(Flex graph, LogInspector inspector)
    {
        this.graph = graph;
        this.log = inspector;
        this.patternMap = new PatternMap(this.log);
    }

    // Ritorna l'asociazione NomeAttività->Id
    private Dizionario<String, String> nodes(){
        Dizionario<String, String> result = new Dizionario<String, String>();

        ArrayList<String> attivita = this.log.activities();
        for(int i = 0; i < attivita.size(); i++){
            result.add(attivita.get(i), "Node-" + i);
        }

        return result;
    }

    public String toString(){
        return this.toString("ActivityDiagram");
    }

    public String toString(String name){
        StringBuilder xmi = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

        Dizionario<String, String> nodes = this.nodes();
        Random random = new Random();

        xmi.append("\n");
        xmi.append("<uml:Model xmi:version=\"20131001\" xmlns:xmi=\"http://www.omg.org/spec/XMI/20131001\" " +
                "xmlns:uml=\"http://www.eclipse.org/uml2/5.0.0/UML\" xmi:id=\"" +
                random.nextInt(99999999)
                + "\" name=\"" + name + "\">");
        xmi.append("\n");
        xmi.append("<packagedElement xmi:type=\"uml:Activity\" xmi:id=\"" +
                random.nextInt(99999999)
                + "\" name=\"" + name +
                "\" node=\"");
        for(String id: nodes.values){
            xmi.append(id + " ");
        }
        xmi.append("\">");

        for(int i = 0; i < nodes.size(); i++){
            String attivita = nodes.getKey(i);
            String attivitaId = nodes.getValue(i);

            xmi.append("\n");
            xmi.append("<node xmi:type=\"");

            if( this.log.predecessors(attivita).size() == 0){
                xmi.append("InitialNode");
            }
            else if(this.log.followers(attivita).size() == 0){
                xmi.append("ActivityFinalNode");
            }
            else xmi.append("OpaqueAction");

            xmi.append("\" xmi:id=\"" +
                    attivitaId +
                    "\" name=\"" +
                    attivita +
                    "\" incoming=\""
            );

            // incoming

            xmi.append("\" outgoing=\"");

            // outgoing

            xmi.append("\">");
            xmi.append("\n");
            xmi.append("<body>// TODO body of " + attivita + "</body>");
        }


        xmi.append("\n");
        xmi.append("</packagedElement>");

        return xmi.toString();
    }
}
