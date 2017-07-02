package com.rtt.cnmining;

import de.derivo.sparqldlapi.*;
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
    private OWLOntologyManager baseOntologyManager, businessOntologyManager;
    private OWLDataFactory baseOntologyDataFactory, businessOntologyDataFactory;
    private File file;
    private String base_iri="urn:absolute:Cnet2AD#";
    private String business_base_iri="urn:absolute:cnet2ADbusiness#";
    private String businessOntologyFileName= "SemanticCnet2AD.BusinessOntology.base.owl";
    private XLog log;
    private QueryEngine queryEngine;
    private OWLOntology baseOntology,businessOntology;
    private StructuralReasonerFactory reasonerFactory;
    private OWLReasoner baseOntologyReasoner, businessOntologyReasoner;
    public OntologyManager(String path,XLog log) throws Exception
    {
        this.log=log;
        file=new File(path);
        //carico l'ontologia in memoria
        baseOntologyManager = OWLManager.createOWLOntologyManager();
        businessOntologyManager= OWLManager.createOWLOntologyManager();
        baseOntology = baseOntologyManager.loadOntologyFromOntologyDocument(file);
        path=businessOntologyFileName;
        file=new File(path);
        businessOntology= businessOntologyManager.loadOntologyFromOntologyDocument(file);
        baseOntologyDataFactory = baseOntologyManager.getOWLDataFactory();
        businessOntologyDataFactory= businessOntologyManager.getOWLDataFactory();
        //inizializzazione dei reasonoer per le SPARQL query engine
        reasonerFactory = new StructuralReasonerFactory();
        baseOntologyReasoner = reasonerFactory.createReasoner(baseOntology);
        businessOntologyReasoner= reasonerFactory.createReasoner(businessOntology);
        //queryEngine = QueryEngine.create(baseOntologyManager, baseOntologyReasoner);

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
                    OWLClass caseID = baseOntologyDataFactory.getOWLClass(IRI.create(base_iri + "Case"));
                    OWLIndividual caseIndividual = baseOntologyDataFactory.getOWLNamedIndividual(IRI.create(base_iri + "Case:"+trace.replace(" ","")));
                    OWLClassAssertionAxiom classAssertion = baseOntologyDataFactory.getOWLClassAssertionAxiom(caseID, caseIndividual);
                    baseOntologyManager.addAxiom(baseOntology, classAssertion);
                }
                if(nodo.nome_attivita!=null)
                {
                    //baseOntology
                    OWLClass attivita= baseOntologyDataFactory.getOWLClass(IRI.create(base_iri+"Activity"));
                    OWLIndividual activityIndividual = baseOntologyDataFactory.getOWLNamedIndividual(IRI.create(base_iri+ "Activity:"+nodo.nome_attivita.replace(" ","")));
                    OWLClassAssertionAxiom classAssertion = baseOntologyDataFactory.getOWLClassAssertionAxiom(attivita,activityIndividual);
                    baseOntologyManager.addAxiom(baseOntology, classAssertion);
                }
                if(nodo.risorsa!=null)
                {
                    //baseOntology
                    OWLClass resource= baseOntologyDataFactory.getOWLClass(IRI.create(base_iri+"Resource"));
                    OWLIndividual resourceIndividual = baseOntologyDataFactory.getOWLNamedIndividual(IRI.create(base_iri+"Resource:"+nodo.risorsa.replace(" ","")));
                    OWLClassAssertionAxiom classAssertion = baseOntologyDataFactory.getOWLClassAssertionAxiom(resource,resourceIndividual);
                    baseOntologyManager.addAxiom(baseOntology, classAssertion);
                }
                // resource has caseID
                if(nodo.risorsa!=null&&trace!=null)
                {
                    OWLNamedIndividual caseIndividual = baseOntologyDataFactory.getOWLNamedIndividual(IRI.create(base_iri + "Case:"+trace.replace(" ","")));
                    OWLIndividual resourceIndividual = baseOntologyDataFactory.getOWLNamedIndividual(IRI.create(base_iri+"Resource:"+nodo.risorsa.replace(" ","")));
                    OWLObjectProperty hasResource = baseOntologyDataFactory.getOWLObjectProperty(IRI.create(base_iri + "hasResource"));
                    OWLObjectPropertyAssertionAxiom dataPropertyAssertion = baseOntologyDataFactory
                            .getOWLObjectPropertyAssertionAxiom(hasResource,caseIndividual, resourceIndividual);
                    baseOntologyManager.addAxiom(baseOntology, dataPropertyAssertion);
                }
                //resource has activity e activity has resource
                if(nodo.nome_attivita!=null&&nodo.risorsa!=null) {
                    //baseOntology
                    OWLNamedIndividual activityIndividual = baseOntologyDataFactory.getOWLNamedIndividual(IRI.create(base_iri+ "Activity:"+nodo.nome_attivita.replace(" ","")));
                    OWLIndividual resourceIndividual = baseOntologyDataFactory.getOWLNamedIndividual(IRI.create(base_iri+"Resource:"+nodo.risorsa.replace(" ","")));
                    OWLObjectProperty hasActivity = baseOntologyDataFactory.getOWLObjectProperty(IRI.create(base_iri + "hasActivity"));
                    OWLObjectProperty hasResource = baseOntologyDataFactory.getOWLObjectProperty(IRI.create(base_iri + "hasResource"));
                    OWLObjectPropertyAssertionAxiom dataPropertyAssertion = baseOntologyDataFactory
                            .getOWLObjectPropertyAssertionAxiom(hasActivity, resourceIndividual, activityIndividual);
                    baseOntologyManager.addAxiom(baseOntology, dataPropertyAssertion);
                   dataPropertyAssertion = baseOntologyDataFactory
                            .getOWLObjectPropertyAssertionAxiom(hasResource, activityIndividual,resourceIndividual);
                    baseOntologyManager.addAxiom(baseOntology, dataPropertyAssertion);
                    //businessOntology
                    activityIndividual = businessOntologyDataFactory.getOWLNamedIndividual(IRI.create(business_base_iri+ "Activity:"+nodo.nome_attivita.replace(" ","")));
                    resourceIndividual = businessOntologyDataFactory.getOWLNamedIndividual(IRI.create(business_base_iri+"Resource:"+nodo.risorsa.replace(" ","")));
                    OWLObjectProperty perform = businessOntologyDataFactory.getOWLObjectProperty(IRI.create(business_base_iri + "perform"));
                    OWLObjectProperty performed = businessOntologyDataFactory.getOWLObjectProperty(IRI.create(business_base_iri + "performed"));
                    dataPropertyAssertion = businessOntologyDataFactory
                            .getOWLObjectPropertyAssertionAxiom(perform, resourceIndividual, activityIndividual);
                    businessOntologyManager.addAxiom(businessOntology, dataPropertyAssertion);
                    dataPropertyAssertion = businessOntologyDataFactory
                            .getOWLObjectPropertyAssertionAxiom(performed, activityIndividual,resourceIndividual);
                    businessOntologyManager.addAxiom(businessOntology, dataPropertyAssertion);
                }
                // activity has caseID
                if(nodo.nome_attivita!=null&&trace!=null)
                {
                    OWLNamedIndividual caseIndividual = baseOntologyDataFactory.getOWLNamedIndividual(IRI.create(base_iri + "Case:"+trace.replace(" ","")));
                    OWLIndividual activityIndividual = baseOntologyDataFactory.getOWLNamedIndividual(IRI.create(base_iri+ "Activity:"+nodo.nome_attivita.replace(" ","")));
                    OWLObjectProperty hasActivity = baseOntologyDataFactory.getOWLObjectProperty(IRI.create(base_iri + "hasActivity"));
                    OWLObjectPropertyAssertionAxiom dataPropertyAssertion = baseOntologyDataFactory
                            .getOWLObjectPropertyAssertionAxiom(hasActivity,caseIndividual, activityIndividual);
                    baseOntologyManager.addAxiom(baseOntology, dataPropertyAssertion);
                }
                //activity has cost
                if(nodo.costi!=null&&nodo.nome_attivita!=null)
                {
                    OWLNamedIndividual attivita = baseOntologyDataFactory.getOWLNamedIndividual(IRI.create(base_iri+ "Activity:"+nodo.nome_attivita.replace(" ","")));
                    OWLDataProperty hasCost = baseOntologyDataFactory.getOWLDataProperty(IRI.create(base_iri+"hasCost"));
                    OWLDataPropertyAssertionAxiom dataPropertyAssertion = baseOntologyDataFactory
                            .getOWLDataPropertyAssertionAxiom(hasCost,attivita, new Integer(nodo.costi));
                    baseOntologyManager.addAxiom(baseOntology, dataPropertyAssertion);
                }
                //activity has timestamp
                if(nodo.timestamp!=null&&nodo.nome_attivita!=null)
                {
                    OWLNamedIndividual attivita = baseOntologyDataFactory.getOWLNamedIndividual(IRI.create(base_iri+ "Activity:"+nodo.nome_attivita.replace(" ","")));
                    OWLDataProperty activityDuration = baseOntologyDataFactory.getOWLDataProperty(IRI.create(base_iri+"activityTime"));
                    OWLDataPropertyAssertionAxiom dataPropertyAssertion = baseOntologyDataFactory
                            .getOWLDataPropertyAssertionAxiom(activityDuration,attivita, nodo.timestamp);
                    baseOntologyManager.addAxiom(baseOntology, dataPropertyAssertion);
                }
                //case hasName
                if(trace!=null)
                {
                    OWLNamedIndividual caseID = baseOntologyDataFactory.getOWLNamedIndividual(IRI.create(base_iri+"Case:"+trace.replace(" ","")));
                    OWLDataProperty hasName = baseOntologyDataFactory.getOWLDataProperty(IRI.create(base_iri+"hasName"));
                    OWLDataPropertyAssertionAxiom dataPropertyAssertion = baseOntologyDataFactory
                            .getOWLDataPropertyAssertionAxiom(hasName,caseID, "Case: "+trace);
                    baseOntologyManager.addAxiom(baseOntology, dataPropertyAssertion);
                }
                //activity hasName
                if(nodo.nome_attivita!=null)
                {
                    OWLNamedIndividual attivita = baseOntologyDataFactory.getOWLNamedIndividual(IRI.create(base_iri+ "Activity:"+nodo.nome_attivita.replace(" ","")));
                    OWLDataProperty hasName = baseOntologyDataFactory.getOWLDataProperty(IRI.create(base_iri+"hasName"));
                    OWLDataPropertyAssertionAxiom dataPropertyAssertion = baseOntologyDataFactory
                            .getOWLDataPropertyAssertionAxiom(hasName,attivita, nodo.nome_attivita);
                    baseOntologyManager.addAxiom(baseOntology, dataPropertyAssertion);
                }
                //resource hasName
                if(nodo.risorsa!=null)
                {
                    OWLNamedIndividual risorsa = baseOntologyDataFactory.getOWLNamedIndividual(IRI.create(base_iri+"Resource:"+nodo.risorsa.replace(" ","")));
                    OWLDataProperty hasName = baseOntologyDataFactory.getOWLDataProperty(IRI.create(base_iri+"hasName"));
                    OWLDataPropertyAssertionAxiom dataPropertyAssertion = baseOntologyDataFactory
                            .getOWLDataPropertyAssertionAxiom(hasName,risorsa, nodo.risorsa);
                    baseOntologyManager.addAxiom(baseOntology, dataPropertyAssertion);
                }
            }
        }
        try {
            File f = new File("owlOntologyOut.owl");
            IRI documentIRI2 = IRI.create(f);
            baseOntologyManager.saveOntology(baseOntology,documentIRI2);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public ArrayList<String> resourceQuery(String activity) {
        queryEngine = QueryEngine.create(baseOntologyManager, baseOntologyReasoner);
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
    public ArrayList<String> roleQuery(String resource)
    {
        queryEngine = QueryEngine.create(businessOntologyManager, businessOntologyReasoner);
        ArrayList<String> roles = new ArrayList<String>();
        String nl="\n";
        String queryString="PREFIX base: <"+business_base_iri+">"+nl+
                "SELECT ?role"+nl+
                "WHERE {Type(base:Resource:"+resource+",?role)}";
        try {
            Query query = Query.create(queryString.toString());
            System.out.println("Starting query...");
            String result = queryEngine.execute(query).toString();
            int index=0;
            //extracting value from the query result
            while((index=result.indexOf("business#"))>0)
            {
                int endIndex=result.indexOf(("\n"));
                roles.add(result.substring(index+9,endIndex));
                result=result.substring(endIndex+1);
            }
        }
        catch(Exception e)
        {
            System.out.println("Query Exception:" + e..toString());
        }
        return roles;

    }
    //data una classe torna la sottoclasse o Owl#Nothing se non possiede figli
    public ArrayList<String> subClassQuery(String baseClass)
    {
        queryEngine = QueryEngine.create(businessOntologyManager, businessOntologyReasoner);
        ArrayList<String> classes = new ArrayList<String>();
        String nl="\n";
        String queryString="PREFIX base: <"+business_base_iri+">"+nl+
                "SELECT ?b"+nl+
                "WHERE { DirectSubClassOf(?b,base:"+baseClass+") }";
        try {
            Query query = Query.create(queryString.toString());
            System.out.println("Starting query...");
            String result = queryEngine.execute(query).toString();
            int index=0;
            //extracting value from the query result
            if(result.contains("owl#Nothing"))
                return null;
            while((index=result.indexOf("business#"))>0)
            {
                int endIndex=result.indexOf(("\n"));
                classes.add(result.substring(index+9,endIndex));
                result=result.substring(endIndex+1);
            }
        }
        catch(Exception e)
        {
            System.out.println("Query Exception:" + e.toString());
        }
        return classes;

    }
    public class ADNodeAttribute
    {
        public String nome_attivita=null,risorsa=null,costi=null,timestamp=null;
    }
}
