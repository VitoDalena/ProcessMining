package org.processmining.plugins.rttmining;

import org.deckfour.xes.model.XLog;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.models.causalnet.CausalNetAnnotations;
import org.processmining.models.flexiblemodel.EndTaskNodesSet;
import org.processmining.models.flexiblemodel.Flex;
import org.processmining.models.flexiblemodel.StartTaskNodesSet;

public class CNMining {
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
	public static Object[] run(UIPluginContext context, XLog log){
		return null;
		
	}

}
