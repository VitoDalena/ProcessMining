package org.processmining.plugins.rttmining;

import org.deckfour.xes.model.XAttribute;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.models.flexiblemodel.Flex;
import org.processmining.plugins.cnmining.CNMining;
import org.processmining.plugins.cnmining.LogUnfolder;
import org.processmining.plugins.cnmining.Settings;
import org.processmining.plugins.cnmining.SettingsView;
import org.processmining.plugins.cnmining.UnfoldResult;

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
        returnLabels = { "Hello World" }, 
        returnTypes = { String.class }, 
        userAccessible = true, 
        help = "Produces XMI"
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
    public static String Process(UIPluginContext context, XLog log) throws Exception {
		
		UnfoldResult result = LogUnfolder.unfold(log);
		System.out.println("map");
		System.out.println(result.map);
		System.out.println("traccia_attivita");
		System.out.println(result.traccia_attivita);
		System.out.println("attivita_tracce");
		System.out.println(result.attivita_tracce);

		return "Hello RTTmining";
		
	}	
}
