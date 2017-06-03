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


/*
    Parametri:
        -json per abilitare lesportazione in json
        -o per definire il nome dei file di output
            esempio -o foo produrrà: foo.txt, foo.xmi, foo.json
        -dir per specificare la directory di output
            esempio -dir ../
        filename del log da processare
 */

public class Main {

    public static void main(String[] args) {

        // Gestione dei parametri
        if(args.length == 0) {
            System.out.println("[ERROR] specify a log file");
            return;
        }

        ArgManager argManager = new ArgManager(args);
        String logFilename = argManager.log();
        if(logFilename.isEmpty()){
            System.out.println("[ERROR] Need a log");
            return;
        }
        System.out.println("[LOG] " + logFilename);

        boolean exportJson = argManager.flag("-json");
        String outputFilename = argManager.param("-o");
        if(outputFilename.isEmpty())
            outputFilename = "rttmining";

        String outputDir = argManager.param("-dir");
        if(outputDir.isEmpty() == false){
            if(outputDir.endsWith("/") == false)
                outputDir += "/";
        }

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

            System.out.println("OutputDit = " + outputDir);
            if(exportJson)
                saveFile(outputDir + outputFilename + ".json", graph.toJson());
            saveFile(outputDir + outputFilename + ".xmi", graph.toXMI());
            saveFile(outputDir + outputFilename + ".txt", graph.toString());

            System.out.println("RTTminingResult=SUCCESS");
        }
        catch(Exception e){
            System.out.println("Exception " + e.toString());
            System.out.println("RTTminingResult=ERROR");
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
