package com.rtt.cnmining;

import org.deckfour.xes.extension.std.XConceptExtension;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

import java.io.File;
import java.util.Set;

public class OntologyManager {
    private OWLOntologyManager manager;
    private OWLDataFactory dataFactory;
    private File file;
    private String base_iri="urn:absolute:Cnet2AD#";
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
                //inizio scrittura degli assiomi nell'ontologia
                if(nodo.nome_attivita!=null)
                {
                    OWLClass attivita=dataFactory.getOWLClass(IRI.create(base_iri+"Activity"));
                    OWLIndividual activityIndividual = dataFactory.getOWLNamedIndividual(IRI.create(base_iri+ nodo.nome_attivita));
                    OWLClassAssertionAxiom classAssertion = dataFactory.getOWLClassAssertionAxiom(attivita,activityIndividual);
                    manager.addAxiom(ontology, classAssertion);
                }
                if(nodo.risorsa!=null)
                {
                    OWLClass resource=dataFactory.getOWLClass(IRI.create(base_iri+"Resource"));
                    OWLIndividual resourceIndividual = dataFactory.getOWLNamedIndividual(IRI.create(base_iri + nodo.risorsa));
                    OWLClassAssertionAxiom classAssertion = dataFactory.getOWLClassAssertionAxiom(resource,resourceIndividual);
                    manager.addAxiom(ontology, classAssertion);
                }
                if(nodo.costi!=null&&nodo.nome_attivita!=null)
                {
                    OWLNamedIndividual attivita = dataFactory.getOWLNamedIndividual(IRI.create(base_iri+nodo.nome_attivita));
                    OWLDataProperty hasCost = dataFactory.getOWLDataProperty(IRI.create(base_iri+"hasCost"));
                    OWLDataPropertyAssertionAxiom dataPropertyAssertion = dataFactory
                            .getOWLDataPropertyAssertionAxiom(hasCost,attivita, new Integer(nodo.costi));
                    manager.addAxiom(ontology, dataPropertyAssertion);
                }
                if(nodo.timestamp!=null&&nodo.nome_attivita!=null)
                {
                    OWLNamedIndividual attivita = dataFactory.getOWLNamedIndividual(IRI.create(base_iri+nodo.nome_attivita));
                    OWLDataProperty activityDuration = dataFactory.getOWLDataProperty(IRI.create(base_iri+"activityDuration"));
                    OWLDataPropertyAssertionAxiom dataPropertyAssertion = dataFactory
                            .getOWLDataPropertyAssertionAxiom(activityDuration,attivita, nodo.timestamp);
                    manager.addAxiom(ontology, dataPropertyAssertion);
                }
            }
        }
        try {
            File f = new File("owlOntologyOut.owl");
            IRI documentIRI2 = IRI.create(f);
            manager.saveOntology(ontology,documentIRI2);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public class ADNodeAttribute
    {
        public String nome_attivita=null,risorsa=null,costi=null,timestamp=null;
    }
}
