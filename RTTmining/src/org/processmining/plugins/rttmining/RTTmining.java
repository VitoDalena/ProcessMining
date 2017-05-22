package org.processmining.plugins.rttmining;

import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;

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
        parameterLabels = {}, 
        returnLabels = { "Hello world string" }, 
        returnTypes = { String.class }, 
        userAccessible = true, 
        help = "Produces the string: 'Hello world'"
    )
    @UITopiaVariant(
        affiliation = "degree project", 
        author = "Riccardi, Tagliente, Tota", 
        email = ""
    )
    public static String Process(PluginContext context) {
		
		Settings s = new Settings();
	    
	    s.setConstraintsEnabled(false);
	    s.setConstr_file_name("");
	    s.setSigmaLogNoise(0.0D);
	    s.setLogName("log");
	    s.setFallFactor(0.0D);
	    s.setRelativeToBest(0.0D);
	    
	    context.log(s.getLogName());
	    
		
		return "Hello RTTmining";
    }
}
