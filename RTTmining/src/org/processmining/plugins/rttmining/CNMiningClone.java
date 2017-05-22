package org.processmining.plugins.rttmining;

import org.deckfour.xes.extension.std.XConceptExtension;
import org.deckfour.xes.model.XLog;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.models.causalnet.CausalNetAnnotations;
import org.processmining.models.flexiblemodel.EndTaskNodesSet;
import org.processmining.models.flexiblemodel.Flex;
import org.processmining.models.flexiblemodel.StartTaskNodesSet;
import org.processmining.plugins.core.CNMining;
import org.processmining.plugins.core.Settings;

public class CNMiningClone {	
	@Plugin(
			name="CNMiningProva", 
			parameterLabels={"log"}, 
			returnLabels={
					"CausalNet", 
					"StartTaskNodesSet", 
					"EndTaskNodesSet", 
					"CausalNetAnnotations"
					}, 
			returnTypes={
					Flex.class, 
					StartTaskNodesSet.class, 
					EndTaskNodesSet.class, 
					CausalNetAnnotations.class
					}, 
			userAccessible=true, 
			help="?? "
		)
		@UITopiaVariant(
	        affiliation = "DIMES University of Calabria", 
	        author = "F. Lupia", 
	        email = ""
	    )
		public static Object[] run(UIPluginContext context, XLog log) throws Exception{
			XConceptExtension conceptExtension = XConceptExtension.instance();
			String logName = conceptExtension.extractName(log);
		
			// call the plugin processing
			Settings s = new Settings();
		 	s.setConstraintsEnabled(false);
		    s.setConstr_file_name("prova");
		    s.setSigmaLogNoise(0.0D);
		    s.setLogName(logName);
		    s.setFallFactor(0.0D);
		    s.setRelativeToBest(0.0D);
		    
		    return CNMining.startCNMining(context, log, s);
		}
}
