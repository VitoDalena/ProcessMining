package org.processmining.plugins.rttmining;

import org.deckfour.xes.model.XLog;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.models.flexiblemodel.Flex;
import org.processmining.plugins.cnmining.CNMining;

public class RTTmining {
			
	/*
	 * Queste notazioni specificano le informazioni di contesto
	 * del plugin, come parametri di input e output
	 * 
	 * Da notare che sono associate ad un metodo statico,
	 * che verrà richiamato all'esecuzione del plugin
	 */	
	
	@Plugin(
        name = "RTTmining Plugin", 
        parameterLabels = { "Log file" }, 
        returnLabels = { "Extended CausalNet" }, 
        returnTypes = { Flex.class }, 
        userAccessible = true, 
        help = "Produces Extended CausalNet"
    )
    @UITopiaVariant(
        affiliation = "Process Mining with CSP", 
        author = "Riccardi, Tagliente, Tota", 
        email = "??"
    )
	/*
	 * Consiste nel Main del plugin stesso, 
	 * l'esecutore di tutto e il gestore di input ed output
	 */
    public static Flex Process(UIPluginContext context, XLog log) throws Exception {
		
		Object[] result = CNMining.run(context, log);
		return (Flex)result[0];
		
	}	
}
