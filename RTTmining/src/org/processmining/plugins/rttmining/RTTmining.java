package org.processmining.plugins.rttmining;

import javax.swing.JOptionPane;

import org.deckfour.xes.model.XLog;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.annotations.Plugin;

import com.carrotsearch.hppc.ObjectArrayList;

public class RTTmining {
	
	// Memorizza qui le configurazioni generali del plugin
	public static Settings settings;
	
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
	/*
	 * Consiste nel Main del plugin stesso, 
	 * l'esecutore di tutto e il gestore di input ed output
	 */
    public static String Process(UIPluginContext context, XLog log) throws Exception {
				
		// determina le impostazioni del plugin
		SettingsView settingsView = new SettingsView(context, log);
		settings = settingsView.show();
		
		// L'utente non ha annullato l'esecuzione del plugin
		// prosegui con la definizione della variabili utili

	    ObjectArrayList<Forbidden> lista_forbidden = new ObjectArrayList<Forbidden>();	    
	    ObjectArrayList<Constraint> vincoli_positivi = new ObjectArrayList<Constraint>();	    
	    ObjectArrayList<Constraint> vincoli_negati = new ObjectArrayList<Constraint>();
	    
	    System.out.println("sigma log noise " + settings.getSigmaLogNoise());
	    System.out.println("delta fall factor  " + settings.getFallFactor());
	    System.out.println("relative to best  " + settings.getRelativeToBest());
	    
		// Se ho dato il consenso al caricamento dei vincoli
		if( settings.isConstraintsEnabled() )
		{
			// procedo a eseguire il parsing
			// essendo questi in formato xml
			ConstraintParser constraintsParser = new ConstraintParser(settings.getConstraintsFilename());
			if(constraintsParser.parse()){
				 ObjectArrayList<Constraint> constraints = constraintsParser.getConstraints();
		         if (constraints.size() == 0) {
		        	 JOptionPane.showMessageDialog(null, "No constraints contained in the input file...");
		         }
		         for (int i = 0; i < constraints.size(); i++)
		         {
		        	 /*
		        	 Constraint constr = constraints.get(i);
		        	 if (constr.isPositiveConstraint())
		        	 {
		        		 vincoli_positivi.add(constr);
		        	 }
		        	 else
		        	 {
		        		 Iterator<String> localIterator2;
		        		 for (Iterator<String> localIterator1 = constr.getBodyList().iterator(); localIterator1.hasNext(); localIterator2.hasNext())
		        		 {
		        			 localIterator2 = constr.getHeadList().iterator(); 
		        			 // TODO: ho rimosso la parte sui forbidde
		        			 // riga 955
		        		 }
		        		 vincoli_negati.add(constr);
		        	 }
		        	 */
		         }
			}
			else {
				// procedi come se non fossero stati specificati vincoli
				JOptionPane.showMessageDialog(null, "Invalid constraints file\nThe algoritm will now run without constraints...");		          
			}			
		}
		
		return settings.getLogName();
    }
}
