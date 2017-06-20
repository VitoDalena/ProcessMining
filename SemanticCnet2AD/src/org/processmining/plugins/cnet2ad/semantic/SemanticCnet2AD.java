package org.processmining.plugins.cnet2ad.semantic;

import java.io.BufferedReader;
import java.io.FileReader;

import org.deckfour.xes.model.XLog;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.annotations.Plugin;

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
        parameterLabels = { "Log" }, 
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
	/*
	 * Consiste nel Main del plugin stesso, 
	 * l'esecutore di tutto e il gestore di input ed output
	 */
    public static String Process(UIPluginContext context, XLog log) throws Exception {
		String ontology=null;
		OntologyManager ontologyManager=new OntologyManager(log);
		if(!ontologyManager.init("SemanticCnet2AD.ontology.base.owl", "SemanticCnet2AD.out.owl")){
			System.out.println("Cannot read ontology");
			return "Error, cannot create ontology";
		}
		ontologyManager.readData();

		ontology = readFile("SemanticCnet2AD.owl");
		return ontology;		
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
