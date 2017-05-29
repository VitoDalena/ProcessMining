package com.rtt.cnmining;

import org.deckfour.xes.extension.std.XConceptExtension;
import org.deckfour.xes.in.XMxmlParser;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.processmining.plugins.cnmining.CNMining;
import org.processmining.plugins.cnmining.Settings;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args){

        XLog log = parse();
        //printLog(log);

        try {

            Settings settings = new Settings();
            settings.sigmaLogNoise = 0.5;
            settings.fallFactor = 0.9;
            settings.relativeToBest = 0.75;

            Object[] data = CNMining.startCNMining(null, log, settings, false);

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
            }
            System.out.println(trace);
        }
    }

    static XLog parse(){
        try {
            XMxmlParser parser = new XMxmlParser();
            File file = new File("logs/log.mxml");
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
}
