package org.processmining.plugins.rttmining;

import org.deckfour.uitopia.api.event.TaskListener;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.framework.util.ui.widgets.ProMPropertiesPanel;

public class SettingsView {
	
	private UIPluginContext context;
	
	public SettingsView(UIPluginContext context){
		this.context = context;
	}
	
	public Settings show(){
		
		ProMPropertiesPanel viewContainer = new ProMPropertiesPanel("");
		PannelloOntologia pannelloOntologia = new PannelloOntologia();
		
		// posiziona i controlli grafici
	    viewContainer.add(pannelloOntologia);
	    
	    // mostra la schermata di configurazione
	    TaskListener.InteractionResult result = context.showConfiguration("Settings", viewContainer);
	    if (result.equals(TaskListener.InteractionResult.CANCEL)) {
	      context.getFutureResult(0).cancel(true);
	    }
	    
	    // Raggruppe le configurazioni del plugin
	    Settings settings = new Settings();
		settings.ontologyFilename = pannelloOntologia.getFilePath();
	    
		return settings;
	}

}
