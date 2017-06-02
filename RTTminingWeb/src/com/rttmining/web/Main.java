package com.rttmining.web;

import org.deckfour.xes.in.XMxmlParser;
import org.deckfour.xes.model.XLog;
import org.processmining.models.flexiblemodel.Flex;
import org.processmining.plugins.cnmining.CNMining;
import org.processmining.plugins.cnmining.Settings;
import org.processmining.plugins.rttmining.FlexInspector;
import org.processmining.plugins.rttmining.LogInspector;
import org.processmining.plugins.rttmining.RTTgraph;
import org.processmining.plugins.rttmining.RTTmining;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Gestione dei parametri
        if(args.length == 0) {
            System.out.println("[ERROR] specify a log file");
            return;
        }

        String logFilename = args[0];
        XLog log = parseLog(logFilename);

        try{
            Settings settings = new Settings();
            settings.sigmaLogNoise = 0.5;
            settings.fallFactor = 0.9;
            settings.relativeToBest = 0.75;

            Object[] data = CNMining.startCNMining(null, log, settings, false);
            Flex cnminningGraph = (Flex)data[0];

            LogInspector logInspector = new LogInspector(log);
            FlexInspector flexInspector = new FlexInspector(cnminningGraph);

            RTTmining mining = new RTTmining(logInspector, flexInspector);
            RTTgraph graph = mining.process();
            //System.out.println(graph);

            saveFile("rttgraph.json", graph.toJson());
            saveFile("rttgraph.xmi", graph.toXMI());
            saveFile("rttgraph.txt", graph.toString());
        }
        catch(Exception e){
            System.out.println("Exception " + e.toString());
        }
    }

    static XLog parseLog(String filename){
        try {
            XMxmlParser parser = new XMxmlParser();
            File file = new File(filename);
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
}
