package org.processmining.plugins.cnet2ad.semantic;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

import org.deckfour.uitopia.api.event.TaskListener;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.framework.util.ui.widgets.ProMPropertiesPanel;

public class SettingsView {
	private UIPluginContext context;
	
	public SettingsView(UIPluginContext context){
		this.context = context;
	}
	
	public SemanticSettings show(){
		
		ProMPropertiesPanel viewContainer = new ProMPropertiesPanel("");
		
		final JCheckBox resourcesCheckbox = new JCheckBox("");
		resourcesCheckbox.setSelected(true);
		final JLabel label = new JLabel("Annotate Resources");
		
		viewContainer.add(resourcesCheckbox);
		viewContainer.add(label);
		
		// mostra la schermata di configurazione
	    TaskListener.InteractionResult result = context.showConfiguration("Settings", viewContainer);
	    if (result.equals(TaskListener.InteractionResult.CANCEL)) {
	      context.getFutureResult(0).cancel(true);
	    }
	    
	    // Ok 
	    SemanticSettings settings = new SemanticSettings();
	    
	    settings.annotateResources = resourcesCheckbox.isSelected();
	    
		return settings;
	}
}
