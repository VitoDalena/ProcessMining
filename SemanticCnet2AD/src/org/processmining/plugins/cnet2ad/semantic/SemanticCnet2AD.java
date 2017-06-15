package org.processmining.plugins.cnet2ad.semantic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;

import org.deckfour.xes.model.XLog;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.models.cnet2ad.ADgraph;

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
        parameterLabels = { "Log", /*"ADgraph"*/ }, 
        returnLabels = { /*"Semantic XMI", "Semantic ADgraph",*/"Cnet2AD Ontology" }, 
        returnTypes = { /*String.class, ADgraph.class,*/ String.class }, 
        userAccessible = true, 
        help = "Produces XMI"
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
    public static /*Object[]*/ String Process(UIPluginContext context, XLog log/*, ADgraph graph*/) throws Exception {
		String ontology=null;
		OntologyManager ontologyManager=new OntologyManager(log);
		if(!ontologyManager.init("SemanticCnet2AD.ontology.base.owl", "SemanticCnet2AD.out.owl")){
			System.out.println("Cannot read ontology");
			return "Error, cannot create ontology";
		}
		ontologyManager.readData();

		ontology = readFile("SemanticCnet2AD.owl");
		//return new Object[]{ graph.toXMI(), graph, ontology };
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
