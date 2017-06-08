package com.rttmining.web;

import org.deckfour.xes.in.XMxmlParser;
import org.deckfour.xes.model.XLog;
import org.processmining.models.flexiblemodel.Flex;
import org.processmining.models.graphbased.directed.bpmn.BPMNDiagram;
import org.processmining.plugins.cnmining.CNMining;
import org.processmining.plugins.cnmining.Settings;
import org.processmining.plugins.rttmining.*;

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

        -sigma Per impostare il sigma log noise
        -ff per impostare il fall factor
        -rtb per impostare il relative to best

        -constraints per impostare il percorso del contenente i vincoli
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

        Settings settings = new Settings();
        settings.sigmaLogNoise = 0.5;
        settings.fallFactor = 0.9;
        settings.relativeToBest = 0.75;

        if(argManager.param("-sigma").isEmpty() == false){
            double sigma = Double.parseDouble(argManager.param("-sigma"));
            sigma = Math.min(sigma, 1);
            sigma = Math.max(0, sigma);
            settings.sigmaLogNoise = sigma;
        }

        if(argManager.param("-ff").isEmpty() == false){
            double ff = Double.parseDouble(argManager.param("-ff"));
            ff = Math.min(ff, 1);
            ff = Math.max(0, ff);
            settings.fallFactor = ff;
        }

        if(argManager.param("-rtb").isEmpty() == false){
            double rtb = Double.parseDouble(argManager.param("-rtb"));
            rtb = Math.min(rtb, 1);
            rtb = Math.max(0, rtb);
            settings.relativeToBest = rtb;
        }

        if(argManager.param("-constraints").isEmpty() == false)
        {
            settings.constraintsEnabled = true;
            settings.constraintsFilename = argManager.param("-constraints");
        }

        XLog log = parseLog(logFilename);
        if( log == null ){
            System.out.println("Unable to parse the log");
            System.out.println("RTTminingResult=ERROR");
        }

        try{
            Object[] data = CNMining.startCNMining(null, log, settings, false);
            Flex causalnet = (Flex)data[0];

            BPMNDiagram bpmn = Flex2BPMN.convert(causalnet);

            //RTTmining mining = new RTTmining(causalnet);
            RTTminingBPMN mining = new RTTminingBPMN(bpmn);
            RTTgraph graph = mining.process();
            //System.out.println(graph);

            System.out.println("OutputDit = " + outputDir);
            if(exportJson)
                saveFile(outputDir + outputFilename + ".json", graph.toJson());
            saveFile(outputDir + outputFilename + ".uml", graph.toXMI());
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
