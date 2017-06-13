package com.rtt.cnmining;

import org.deckfour.xes.extension.std.XConceptExtension;
import org.deckfour.xes.in.XMxmlParser;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.processmining.models.flexiblemodel.Flex;
import org.processmining.models.graphbased.directed.bpmn.BPMNDiagram;
import org.processmining.models.graphbased.directed.bpmn.BPMNEdge;
import org.processmining.models.graphbased.directed.bpmn.BPMNNode;
import org.processmining.models.graphbased.directed.bpmn.elements.Flow;
import org.processmining.models.graphbased.directed.bpmn.elements.Gateway;
import org.processmining.plugins.cnmining.CNMining;
import org.processmining.plugins.cnmining.Settings;
import org.processmining.plugins.converters.FlexToBPMNConversionPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args){

        XLog log = parse("logs/running-example.mxml");


        try {
            Settings settings = new Settings();
            settings.sigmaLogNoise = 0.05;
            settings.fallFactor = 0.9;
            settings.relativeToBest = 0.75;

            Object[] data = CNMining.startCNMining(null, log, settings, false);
            Flex cnminningGraph = (Flex)data[0];
            //CNParser parser = new CNParser("ExtendedCausalNet.xml");
            //Flex cnminningGraph = parser.parse();

            BPMNDiagram bpmn = Flex2BPMN.convert(cnminningGraph);
            for(BPMNNode node : bpmn.getNodes()){
                System.out.println();
                System.out.println(node.getLabel());
                System.out.println();
                System.out.println("outcoming bindings...");
                Collection<BPMNEdge<? extends BPMNNode, ? extends BPMNNode>> edges = bpmn.getOutEdges(node);
                Iterator<BPMNEdge<? extends BPMNNode, ? extends BPMNNode>> i = edges.iterator();
                while(i.hasNext()){
                    BPMNEdge e = i.next();
                    System.out.println(e.getSource() + " -> " + e.getTarget());
                }
                System.out.println();
                System.out.println("incoming bindings...");
                edges = bpmn.getInEdges(node);
                i = edges.iterator();
                while(i.hasNext()){
                    BPMNEdge e = i.next();
                    System.out.println(e.getSource() + " -> " + e.getTarget());
                }
                System.out.println();
                System.out.println("flows...");
                Collection<Flow> flows = bpmn.getFlows();
                Iterator<Flow> fi = flows.iterator();
                while(fi.hasNext()){
                    Flow f = fi.next();
                    System.out.println(f.getSource() + " -> " + f.getTarget());
                }
                System.out.println();
                System.out.println("Gateways...");
                Collection<Gateway> gateways = bpmn.getGateways();
                Iterator<Gateway> g = gateways.iterator();
                while(g.hasNext()){
                    Gateway gateway = g.next();
                    System.out.println(gateway.getLabel() + gateway.getGatewayType());
                }
            }

            RTTminingBPMN m = new RTTminingBPMN(bpmn);
            RTTgraph g = m.process();

            System.out.println();
            System.out.println(g.toString());
            saveFile("rttgraph.js", "var data = [" + g.toJson() + "]");

            if(true)
                return;

            RTTmining mining = new RTTmining(cnminningGraph);
            RTTgraph graph = mining.process();
            System.out.println();
            System.out.println(graph);

            System.out.println();
            saveFile("rttgraph.json", graph.toJson());
            saveFile("rttgraph.uml", graph.toXMI());
            saveFile("rttgraph.txt", graph.toString());
            saveFile("rttgraph.js", "var data = [" + graph.toJson() + "]");

        }
        catch(Exception e){
            System.out.println("Exception " + e.toString());
        }

    }

    static void printLog(XLog log){
        for (int i = 0; i < log.size(); i++) {
            XTrace trace = log.get(i);
            System.out.println("trace: " + XConceptExtension.instance().extractName(trace));
            for (XEvent activity : trace)
            {
                String nome_attivita = activity.getAttributes().get("concept:name").toString();
                System.out.println(nome_attivita);
                Set<String> a = activity.getAttributes().keySet();
                for(String b:a)
                {
                    System.out.println(b+"-"+activity.getAttributes().get(b).toString());
                }
            }
            System.out.println(trace);
        }
    }

    static XLog parse(String name){
        try {
            XMxmlParser parser = new XMxmlParser();
            File file = new File(name);
            System.out.println(file.exists());
            System.out.println(parser.canParse(file));
            List<XLog> logs = parser.parse(file);
            System.out.println(logs.size());

            return logs.iterator().next();
        }
        catch(Exception e){
            System.out.println("exception" + e.toString());
            return null;
        }
    }

    public static void saveFile(String filename, String content) throws Exception {
        System.out.println("Exporting File: " + filename + "...");
        File ec = new File(filename);
        if (ec.exists()) {
            ec.delete();
        }
        ec.createNewFile();
        try
        {
            Files.write(FileSystems.getDefault().getPath(
                    ".", new String[] { filename }),
                    content.getBytes(), new OpenOption[] {
                            StandardOpenOption.APPEND
                    }
            );
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    static void printFlex(Flex graph){

    }
}
