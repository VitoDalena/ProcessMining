package com.rtt.cnmining;

import org.deckfour.xes.extension.std.XConceptExtension;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import java.io.File;
import java.util.Set;

public class OntologyManager {
    private OWLOntologyManager manager;
    private OWLDataFactory dataFactory;
    private File file;
    private XLog log;
    private OWLOntology ontology;
    public OntologyManager(String path,XLog log) throws Exception
    {
        this.log=log;
        file=new File(path);
        manager= OWLManager.createOWLOntologyManager();
        ontology=manager.loadOntologyFromOntologyDocument(file);
        dataFactory=manager.getOWLDataFactory();
    }
    public void readData()
    {
        for (int i = 0; i < log.size(); i++) {
            XTrace trace = log.get(i);
            System.out.println("trace: " + XConceptExtension.instance().extractName(trace));
            for (XEvent activity : trace)
            {
                Set<String> keyset = activity.getAttributes().keySet();
                ADNodeAttribute nodo=new ADNodeAttribute();
                for(String key:keyset)
                {
                    String value=activity.getAttributes().get(key).toString();
                    if(key.equals("concept:name"))
                        nodo.nome_attivita=value;
                    else if(key.equals("org:resource"))
                        nodo.risorsa=value;
                    else if (key.equals("time:timestamp"))
                        nodo.timestamp=value;
                    else if(key.toLowerCase().contains("cost")||key.toLowerCase().contains("costs"))
                        nodo.costi=value;
                }


            }
            System.out.println(trace);
        }
    }
    public class ADNodeAttribute
    {
        public String nome_attivita,risorsa,costi,timestamp;
    }
}
