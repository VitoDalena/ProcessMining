package org.processmining.plugins.cnet2ad.semantic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import org.deckfour.xes.model.XLog;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.models.cnet2ad.ADedge;
import org.processmining.models.cnet2ad.ADgraph;
import org.processmining.models.cnet2ad.ADnode;

public class SemanticCnet2AD {
			
	/*
	 * Queste notazioni specificano le informazioni di contesto
	 * del plugin, come parametri di input e output
	 * 
	 * Da notare che sono associate ad un metodo statico,
	 * che verr√† richiamato all'esecuzione del plugin
	 */	
	
	@Plugin(
        name = "SemanticCnet2AD", 
        parameterLabels = { "Log", "ADGraph" }, 
        returnLabels = { "Cnet2AD Ontology", "Semantic ADgraph" }, 
        returnTypes = { String.class, ADgraph.class }, 
        userAccessible = true, 
        help = "Produces Ontology"
    )
    @UITopiaVariant(
        affiliation = "Cnet2AD with Semantic", 
        author = "Riccardi, Tagliente, Tota", 
        email = "??"
    )
	/*
	 * Consiste nel Main del plugin stesso, 
	 * l'esecutore di tutto e il gestore di input ed output
	 */
    public static Object[] Process(UIPluginContext context, XLog log, ADgraph graph ) throws Exception {
		
		SettingsView settingsView = new SettingsView(context);
		SemanticSettings settings = settingsView.show();
		
		SemanticCnet2AD algorithm =  new SemanticCnet2AD(log);
		String ontology = algorithm.annotate();
		
		if(ontology.equals("ERROR") == false ){

			System.out.println("Annotate Resources = " + settings.annotateResources);
			if( settings.annotateResources ){
				
				algorithm.annotateResources(graph);
			}
		}
		
		saveFile("semanticadgraph.xmi", graph.toXMI());
        saveFile("semanticadgraph.txt", graph.toString());
		saveFile("semanticadgraph.json", graph.toJson());
		
		return new Object[]{ ontology, graph };		
	}
	
	@Plugin(
	        name = "SemanticCnet2AD1111", 
	        parameterLabels = { }, 
	        returnLabels = { "Cnet2AD Ontology" }, 
	        returnTypes = { String.class }, 
	        userAccessible = true, 
	        help = "Produces Ontology"
	    )
	    @UITopiaVariant(
	        affiliation = "Cnet2AD with Semantic", 
	        author = "Riccardi, Tagliente, Tota", 
	        email = "??"
	    )
	public static String Foo(){
		return "Foo";
	}
	
	// Algorithm
	private OntologyManager ontologyManager;
	private String ontologyBase;
	private String ontologyOut;
	private XLog log;
	
	public SemanticCnet2AD(XLog log){
		this.log = log;
	}
	
	public String annotate(){
		return this.annotate("SemanticCnet2AD.ontology.base.owl", "SemanticCnet2AD.out.owl");
	}

	public String annotate(String ontologyBase, String ontologyOut){
		this.ontologyBase = ontologyBase;
		this.ontologyOut = ontologyOut;
		
		String ontology=null;
		this.ontologyManager=new OntologyManager(this.log);
		if(!this.ontologyManager.init(this.ontologyBase, this.ontologyOut)){
			System.out.println("Cannot read ontology");
			return "ERROR";
		}
		ontologyManager.readData();
		
		ontology = readFile(this.ontologyOut);
		return ontology;
	}
	
	//Chiamata all'interno di ProM
	public void annotateResources(ADgraph graph){
			System.out.println("Annotate Resources...");
			ArrayList<String> activities = new ArrayList<String>();
			for(ADnode node: graph.nodes()){
				activities.add(node.name);
			}
			
			for(String activity_name:activities)
			{
				ADnode node = graph.node(activity_name);
				
				if(node != null && node.isType(ADnode.Node))
				{
					System.out.println("Activity:" + node.name);
					ArrayList<String> resources=ontologyManager.resourceQuery(node.name);
					/*Activity activity = this.ontologyManager.data().activity(node.name);
					if( activity == null ) 
						continue;
					ArrayList<String> resources = activity.resources();*/
					
					System.out.println("Found Resources:");
					System.out.println(resources);
					if(resources.size() > 0)
						explodeNode(graph,node,resources);
				}
			}

	}
	
	public void annotateResources(ADgraph graph, String level){
		
		annotateResources(graph);
		if(level.equals("2"))
		{
			//Grafo di lvl 2 ottenuto
			
		}else if (level.equals("3"))
		{
			ArrayList<ADnode> nodes=graph.nodes();
			
			for(ADnode node:nodes)
			{
				if(node.isType(ADnode.Node))
				{
					String name, res,role="";
					name= node.name.substring(0, node.name.lastIndexOf("|")-1);
					res= node.name.substring(node.name.lastIndexOf("|")+2);
					//System.out.println("Il nome Ë:*"+name+"*");
					//System.out.println("Il res Ë:*"+res+"*");
					ArrayList<String> roles=ontologyManager.roleQuery(res);
					System.out.println("I ruoli sono: " + roles);
					for(String rol:roles)
					{
						if(ontologyManager.subClassQuery(rol)==null)
							{System.out.println("Il ruolo della risorsa Ë: "+rol);
							role=rol;
							}
					}
					node.name=name+"("+role+","+res+")";
				}
			}
		}else if (level.equals("4"))
		{		
			System.out.println("NOT IMPLEMENTED YET -  LEVEL 4");
			//TODO: Implement
		}
	}
	
	private void explodeNode(ADgraph graph, ADnode node, ArrayList<String> resources){
        if(graph.nodes().contains(node) == false)
            return;

        ArrayList<ADnode> nodes = new ArrayList<ADnode>();
        for(String res:resources){
            nodes.add(new ADnode(node.name + " | " + res));
        }

        // Rimpiazza il nodo corrente con i nuovi
        // tenendo presente una cosa
        // se ci sono piu risorse, bisogna mettere un fork prima e
        // un join dopo la lista di nodi rimpiazzat
        System.out.println("Exploding Node:" + node);
        
        if(resources.size() > 1){
            // add fork
            ADnode fork = new ADnode("ForkResources" + node.name);
            fork.fork();
            // add join
            ADnode join = new ADnode("JoinResources" + node.name);
            join.join();

            graph.add(fork);
            graph.add(join);

            for(ADedge edge:graph.edgesEndWith(node))
                edge.end(fork);
            for(ADedge edge:graph.edgesStartWith(node))
                edge.begin(join);

            // aggiungi i nodi e per ognuno definisci gli archi
            for(ADnode n:nodes){
                graph.add(n);

                graph.add(new ADedge(fork, n));
                graph.add(new ADedge(n, join));
            }

            // rimuovi il nodo rimpiazzato
            graph.nodes().remove(node);
        }
        else node.name += " | " + resources.get(0);
    }
	
	public static String readFile(String filename) {
		try {
			BufferedReader rd = new BufferedReader(new FileReader(filename));
			String line = null;
			StringBuilder str = new StringBuilder();
			String ls = System.getProperty("line.separator");
			while((line = rd.readLine()) != null){
				str.append(line);
				str.append(ls);
			}
			
			rd.close();
			
			return str.toString();
		}
		catch(Exception e){
			return "";
		}
	}
	
	private static void saveFile(String filename, String content) throws Exception {
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
