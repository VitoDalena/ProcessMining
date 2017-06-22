package com.rtt.cnmining;

import de.derivo.sparqldlapi.*;
import de.derivo.sparqldlapi.impl.QueryBindingImpl;
import org.deckfour.xes.extension.std.XConceptExtension;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

public class OntologyManager {
    private OWLOntologyManager manager;
    private OWLDataFactory dataFactory;
    private File file;
    private String base_iri="urn:absolute:Cnet2AD#";
    private XLog log;
    private QueryEngine queryEngine;
    private OWLOntology ontology;
    private StructuralReasonerFactory reasonerFactory;
    private OWLReasoner reasoner;
    public OntologyManager(String path,XLog log) throws Exception
    {
        this.log=log;
        file=new File(path);
        //carico l'ontologia in memoria
        manager= OWLManager.createOWLOntologyManager();
        ontology=manager.loadOntologyFromOntologyDocument(file);
        dataFactory=manager.getOWLDataFactory();
        //inizializzazione del reasoner per il SPARQL query engine
        reasonerFactory = new StructuralReasonerFactory();
        reasoner = reasonerFactory.createReasoner(ontology);
        queryEngine = QueryEngine.create(manager, reasoner);
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
                //resource has activity e activity has resource
                if(nodo.nome_attivita!=null&&nodo.risorsa!=null) {
                    OWLNamedIndividual activityIndividual = dataFactory.getOWLNamedIndividual(IRI.create(base_iri+ "Activity:"+nodo.nome_attivita.replace(" ","")));
                    OWLIndividual resourceIndividual = dataFactory.getOWLNamedIndividual(IRI.create(base_iri+"Resource:"+nodo.risorsa.replace(" ","")));
                    OWLObjectProperty hasActivity = dataFactory.getOWLObjectProperty(IRI.create(base_iri + "hasActivity"));
                    OWLObjectProperty hasResource = dataFactory.getOWLObjectProperty(IRI.create(base_iri + "hasResource"));
                    OWLObjectPropertyAssertionAxiom dataPropertyAssertion = dataFactory
                            .getOWLObjectPropertyAssertionAxiom(hasActivity, resourceIndividual, activityIndividual);
                    manager.addAxiom(ontology, dataPropertyAssertion);
                   dataPropertyAssertion = dataFactory
                            .getOWLObjectPropertyAssertionAxiom(hasResource, activityIndividual,resourceIndividual);
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
            File f = new File("owlOntologyOut.owl");
            IRI documentIRI2 = IRI.create(f);
            manager.saveOntology(ontology,documentIRI2);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public ArrayList<String> resourceQuery(String activity) {
        ArrayList<String> resources = new ArrayList<String>();
        String nl="\n";
        String queryString="PREFIX base: <"+base_iri+">"+nl+
                "SELECT ?resource"+nl+
                "WHERE {PropertyValue(base:Activity:"+activity+", base:hasResource, "+"?resource)}";
        try {
            Query query = Query.create(queryString.toString());
            System.out.println("Starting query...");
            String result = queryEngine.execute(query).toString();
            int index=0;
            //extracting value from the query result
            while((index=result.indexOf("#Resource:"))>0)
            {
                int endIndex=result.indexOf(("\n"));
                resources.add(result.substring(index+10,endIndex));
                result=result.substring(endIndex+1);
            }
            }
        catch(Exception e)
        {
            System.out.println("Query Exception:" + e.toString());
        }
        return resources;
    }
    public class ADNodeAttribute
    {
        public String nome_attivita=null,risorsa=null,costi=null,timestamp=null;
    }
}
