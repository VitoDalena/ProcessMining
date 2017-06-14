package org.processmining.plugins.cnet2ad.semantic;

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
        parameterLabels = { "Log", "ADgraph" }, 
        returnLabels = { "XMI", "ADgraph" }, 
        returnTypes = { String.class, ADgraph.class }, 
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
    public static Object[] Process(UIPluginContext context, XLog log, ADgraph graph) throws Exception {
				

		context.getProvidedObjectManager().createProvidedObject("SemanticADgraph", graph, context);
		
		return new Object[]{ graph.toXMI(), graph };
		
	}
}
