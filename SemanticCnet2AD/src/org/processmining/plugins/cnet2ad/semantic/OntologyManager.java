package org.processmining.plugins.cnet2ad.semantic;

import org.deckfour.xes.extension.std.XConceptExtension;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

import java.io.File;
import java.util.Set;

public class OntologyManager {
    private OWLOntologyManager manager;
    private OWLDataFactory dataFactory;
    private File file;
    private String base_iri="urn:absolute:Cnet2AD#";
    private XLog log;
    private OWLOntology ontology;
    private String outputFilename;
    
    public OntologyManager(XLog log){
    	this.log = log;
    }
    
    public boolean init(String path, String output)
    {
    	this.outputFilename = output;
       try{
    	   file=new File(path);
           manager= OWLManager.createOWLOntologyManager();
           ontology=manager.loadOntologyFromOntologyDocument(file);
           dataFactory=manager.getOWLDataFactory();
       }
       catch(Exception e){
    	   System.out.println("[OntologyManager:init] " + e.toString());
    	   return false;
       }
       return true;       
    }
    public void readData()
    {
        for (int i = 0; i < log.size(); i++) {
            XTrace xtrace = log.get(i);
            String trace=XConceptExtension.instance().extractName(xtrace);
            for (XEvent activity : xtrace)
            {
                Set<String> keyset = activity.getAttributes().keySet();
                ADNodeAttribute nodo=new ADNodeAttribute();
                for(String key:keyset)
                {
                    String value=activity.getAttributes().get(key).toString();
                    if(key.equals("concept:name")||key.equals("NAME_TASK"))
                        nodo.nome_attivita=value;
                    else if(key.equals("org:resource")||key.equals("START_USER_PROC"))
                        nodo.risorsa=value;
                    else if (key.equals("time:timestamp"))
                        nodo.timestamp=value;
                    else if(key.toLowerCase().contains("cost")||key.toLowerCase().contains("costs")||key.contains("DURATION_TASK"))
                        nodo.costi=value;
                }
                //inizio scrittura degli assiomi nell'ontologia, istanze delle tre classi: case, resource e activity
                if(trace!=null) {
                    OWLClass caseID = dataFactory.getOWLClass(IRI.create(base_iri + "Case"));
                    OWLIndividual caseIndividual = dataFactory.getOWLNamedIndividual(IRI.create(base_iri + "Case:"+trace.replace(" ","")));
                    OWLClassAssertionAxiom classAssertion = dataFactory.getOWLClassAssertionAxiom(caseID, caseIndividual);
                    manager.addAxiom(ontology, classAssertion);
                }
                if(nodo.nome_attivita!=null)
                {
                    OWLClass attivita=dataFactory.getOWLClass(IRI.create(base_iri+"Activity"));
                    OWLIndividual activityIndividual = dataFactory.getOWLNamedIndividual(IRI.create(base_iri+ "Activity:"+nodo.nome_attivita.replace(" ","")));
                    OWLClassAssertionAxiom classAssertion = dataFactory.getOWLClassAssertionAxiom(attivita,activityIndividual);
                    manager.addAxiom(ontology, classAssertion);
                }
                if(nodo.risorsa!=null)
                {
                    OWLClass resource=dataFactory.getOWLClass(IRI.create(base_iri+"Resource"));
                    OWLIndividual resourceIndividual = dataFactory.getOWLNamedIndividual(IRI.create(base_iri+"Resource:"+nodo.risorsa.replace(" ","")));
                    OWLClassAssertionAxiom classAssertion = dataFactory.getOWLClassAssertionAxiom(resource,resourceIndividual);
                    manager.addAxiom(ontology, classAssertion);
                }
                // resource has caseID
                if(nodo.risorsa!=null&&trace!=null)
                {
                    OWLNamedIndividual caseIndividual = dataFactory.getOWLNamedIndividual(IRI.create(base_iri + "Case:"+trace.replace(" ","")));
                    OWLIndividual resourceIndividual = dataFactory.getOWLNamedIndividual(IRI.create(base_iri+"Resource:"+nodo.risorsa.replace(" ","")));
                    OWLObjectProperty hasResource = dataFactory.getOWLObjectProperty(IRI.create(base_iri + "hasResource"));
                    OWLObjectPropertyAssertionAxiom dataPropertyAssertion = dataFactory
                            .getOWLObjectPropertyAssertionAxiom(hasResource,caseIndividual, resourceIndividual);
                    manager.addAxiom(ontology, dataPropertyAssertion);
                }
                //resource has activity
                if(nodo.nome_attivita!=null&&nodo.risorsa!=null) {
                    OWLNamedIndividual activityIndividual = dataFactory.getOWLNamedIndividual(IRI.create(base_iri+ "Activity:"+nodo.nome_attivita.replace(" ","")));
                    OWLIndividual resourceIndividual = dataFactory.getOWLNamedIndividual(IRI.create(base_iri+"Resource:"+nodo.risorsa.replace(" ","")));
                    OWLObjectProperty hasActivity = dataFactory.getOWLObjectProperty(IRI.create(base_iri + "hasActivity"));
                    OWLObjectPropertyAssertionAxiom dataPropertyAssertion = dataFactory
                            .getOWLObjectPropertyAssertionAxiom(hasActivity, resourceIndividual, activityIndividual);
                    manager.addAxiom(ontology, dataPropertyAssertion);
                }
                // activity has caseID
                if(nodo.nome_attivita!=null&&trace!=null)
                {
                    OWLNamedIndividual caseIndividual = dataFactory.getOWLNamedIndividual(IRI.create(base_iri + "Case:"+trace.replace(" ","")));
                    OWLIndividual activityIndividual = dataFactory.getOWLNamedIndividual(IRI.create(base_iri+ "Activity:"+nodo.nome_attivita.replace(" ","")));
                    OWLObjectProperty hasActivity = dataFactory.getOWLObjectProperty(IRI.create(base_iri + "hasActivity"));
                    OWLObjectPropertyAssertionAxiom dataPropertyAssertion = dataFactory
                            .getOWLObjectPropertyAssertionAxiom(hasActivity,caseIndividual, activityIndividual);
                    manager.addAxiom(ontology, dataPropertyAssertion);
                }
                //activity has cost
                if(nodo.costi!=null&&nodo.nome_attivita!=null)
                {
                    OWLNamedIndividual attivita = dataFactory.getOWLNamedIndividual(IRI.create(base_iri+ "Activity:"+nodo.nome_attivita.replace(" ","")));
                    OWLDataProperty hasCost = dataFactory.getOWLDataProperty(IRI.create(base_iri+"hasCost"));
                    OWLDataPropertyAssertionAxiom dataPropertyAssertion = dataFactory
                            .getOWLDataPropertyAssertionAxiom(hasCost,attivita, new Integer(nodo.costi));
                    manager.addAxiom(ontology, dataPropertyAssertion);
                }
                //activity has timestamp
                if(nodo.timestamp!=null&&nodo.nome_attivita!=null)
                {
                    OWLNamedIndividual attivita = dataFactory.getOWLNamedIndividual(IRI.create(base_iri+ "Activity:"+nodo.nome_attivita.replace(" ","")));
                    OWLDataProperty activityDuration = dataFactory.getOWLDataProperty(IRI.create(base_iri+"activityTime"));
                    OWLDataPropertyAssertionAxiom dataPropertyAssertion = dataFactory
                            .getOWLDataPropertyAssertionAxiom(activityDuration,attivita, nodo.timestamp);
                    manager.addAxiom(ontology, dataPropertyAssertion);
                }
                //case hasName
                if(trace!=null)
                {
                    OWLNamedIndividual caseID = dataFactory.getOWLNamedIndividual(IRI.create(base_iri+"Case:"+trace.replace(" ","")));
                    OWLDataProperty hasName = dataFactory.getOWLDataProperty(IRI.create(base_iri+"hasName"));
                    OWLDataPropertyAssertionAxiom dataPropertyAssertion = dataFactory
                            .getOWLDataPropertyAssertionAxiom(hasName,caseID, "Case: "+trace);
                    manager.addAxiom(ontology, dataPropertyAssertion);
                }
                //activity hasName
                if(nodo.nome_attivita!=null)
                {
                    OWLNamedIndividual attivita = dataFactory.getOWLNamedIndividual(IRI.create(base_iri+ "Activity:"+nodo.nome_attivita.replace(" ","")));
                    OWLDataProperty hasName = dataFactory.getOWLDataProperty(IRI.create(base_iri+"hasName"));
                    OWLDataPropertyAssertionAxiom dataPropertyAssertion = dataFactory
                            .getOWLDataPropertyAssertionAxiom(hasName,attivita, nodo.nome_attivita);
                    manager.addAxiom(ontology, dataPropertyAssertion);
                }
                //resource hasName
                if(nodo.risorsa!=null)
                {
                    OWLNamedIndividual risorsa = dataFactory.getOWLNamedIndividual(IRI.create(base_iri+"Resource:"+nodo.risorsa.replace(" ","")));
                    OWLDataProperty hasName = dataFactory.getOWLDataProperty(IRI.create(base_iri+"hasName"));
                    OWLDataPropertyAssertionAxiom dataPropertyAssertion = dataFactory
                            .getOWLDataPropertyAssertionAxiom(hasName,risorsa, nodo.risorsa);
                    manager.addAxiom(ontology, dataPropertyAssertion);
                }
            }
        }
        try {
            File f = new File(outputFilename);
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
