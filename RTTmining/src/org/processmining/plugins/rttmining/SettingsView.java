package org.processmining.plugins.rttmining;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

import org.deckfour.uitopia.api.event.TaskListener;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.framework.util.ui.widgets.ProMPropertiesPanel;

public class SettingsView {
	
	private ProMPropertiesPanel viewContainer;
	private boolean jsonEnabled = false;
	
	private UIPluginContext context;
	
	public SettingsView(UIPluginContext context){
		this.context = context;
	}
	
	public Settings show(){
		
		viewContainer = new ProMPropertiesPanel("");

		PannelloOntologia pannelloOntologia = new PannelloOntologia();		
		
		final JCheckBox exportToJson = new JCheckBox("");
		exportToJson.setSelected(false);
		exportToJson.addActionListener(new ActionListener()
   		{
   			public void actionPerformed(ActionEvent e)
   			{
   				if (exportToJson.isSelected())
   				{
   					SettingsView.this.jsonEnabled = true;
   				}
   				else
   				{
   					SettingsView.this.jsonEnabled = false;
   				}
   			}
   		});
		
		// Posiziona gli elementi grafici
		viewContainer.add(pannelloOntologia);
		viewContainer.add(new JLabel("Export to Json "));
		viewContainer.add(exportToJson);
				
	    // mostra la schermata di configurazione
	    TaskListener.InteractionResult result = context.showConfiguration("Settings", viewContainer);
	    if (result.equals(TaskListener.InteractionResult.CANCEL)) {
	      context.getFutureResult(0).cancel(true);
	    }
	    
	    // Raggruppe le configurazioni del plugin
	    Settings settings = new Settings();
		settings.ontologyFilename = pannelloOntologia.getFilePath();
		settings.exportJson = this.jsonEnabled;
	    
		return settings;
	}

}
