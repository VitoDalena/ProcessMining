package org.processmining.plugins.cnet2ad.semantic;

import java.io.BufferedReader;
import java.io.FileReader;
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
	 * che verrà richiamato all'esecuzione del plugin
	 */	
	
	@Plugin(
        name = "SemanticCnet2AD", 
        parameterLabels = { "Log","ADGraph" }, 
        returnLabels = { "Cnet2AD Ontology" }, 
        returnTypes = { String.class,ADgraph.class }, 
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
    public static Object[] Process(UIPluginContext context, XLog log,ADgraph graph ) throws Exception {
		String ontology=null;
		OntologyManager ontologyManager=new OntologyManager(log);
		if(!ontologyManager.init("SemanticCnet2AD.ontology.base.owl", "SemanticCnet2AD.out.owl")){
			System.out.println("Cannot read ontology");
			return new Object[]{"Ontology error",graph};
		}
		ontologyManager.readData();
		for(ADnode node:graph.nodes())
		{
			if(node.isType(ADnode.Node))
			{
				ArrayList<String> resources=ontologyManager.resourceQuery(node.name);
				if(resources.size()>0)
					explodeNode(graph,node,resources);
			}
		}
		
		
		ontology = readFile("SemanticCnet2AD.owl");
		return new Object[]{ontology,graph};		
	}
	static void explodeNode(ADgraph graph, ADnode node, ArrayList<String> resources){
        if(graph.nodes().contains(node) == false)
            return;

        ArrayList<ADnode> nodes = new ArrayList<>();
        for(String res:resources){
            nodes.add(new ADnode(node.name + " | " + res));
        }

        // Rimpiazza il nodo corrente con i nuovi
        // tenendo presente una cosa
        // se ci sono piu risorse, bisogna mettere un fork prima e
        // un join dopo la lista di nodi rimpiazzati
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
	private static String readFile(String filename) {
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
	
}
