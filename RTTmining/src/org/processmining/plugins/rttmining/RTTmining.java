package org.processmining.plugins.rttmining;

import java.util.Iterator;

import javax.swing.JOptionPane;

import org.deckfour.xes.model.XLog;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.annotations.Plugin;

import com.carrotsearch.hppc.ObjectArrayList;

public class RTTmining {
	
	// Memorizza qui le configurazioni generali del plugin
	public static Settings settings;
	// Gestore dei vincoli
	public static ConstraintsManager vincoli;
	
	private static UIPluginContext context;
	private static XLog log;
	
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
        affiliation = "Process Mining with CSP", 
        author = "Riccardi, Tagliente, Tota", 
        email = "??"
    )
	/*
	 * Consiste nel Main del plugin stesso, 
	 * l'esecutore di tutto e il gestore di input ed output
	 */
    public static String Process(UIPluginContext context, XLog log) throws Exception {
		// Rendi il contesto e l'input globale a tutto il plugin
		RTTmining.context = context;
		RTTmining.log = log;		
		
		// determina le impostazioni del plugin
		SettingsView settingsView = new SettingsView(context, log);
		settings = settingsView.show();
		
		System.out.println("\n\nRTTmining\n\nSettings:\n");
	    System.out.println("sigma log noise " + settings.sigmaLogNoise);
	    System.out.println("delta fall factor  " + settings.fallFactor);
	    System.out.println("relative to best  " + settings.relativeToBest);
	    
	    // Inizializzo le variabili utili all'algoritmo
	    // 1. Il gestore dei vincoli
	    vincoli = new ConstraintsManager();
	    // 2. Inizializza l'oggetto che si occupa di gestire
	    // l'algoritmo di cnmining
	    CNMining cnmining = new CNMining();
	    
	    // esegui il parsing dei vincoli, se richiesto
		caricaVincoli();
		
		// Prepara l'ambiente di lavoro		
		LogUnfolder.aggiungiAttivitaFittizia(log);
		// rimozione dei cicli
	    LogUnfolderResult unfoldResult = LogUnfolder.unfold(log);
		// l'operazione di unfold mi produce 4 output
	    // Riga 922
	    cnmining.creaVincoliUnfold(vincoli, unfoldResult);
	    
	    System.out.println("Causal Score Matrix");
	    
	    // Crea matrice dei causal score
	    double[][] causalScoreMatrix = cnmining.calcoloMatriceDeiCausalScore(
    		log, unfoldResult.map, 
    		unfoldResult.traccia_attivita, settings.fallFactor
	    );
	    
	    System.out.println("Best Next Matrix");
	    
	    // crea matrice dei best next
	    double[][] bestNextMatrix = cnmining.buildBestNextMatrix(
    		log, unfoldResult.map, 
    		unfoldResult.traccia_attivita, causalScoreMatrix, 
    		vincoli.forbidden
	    );
	    if (settings.sigmaLogNoise > 0.0D) {
	    	for (int i = 0; i < bestNextMatrix.length; i++) {
	    		for (int j = 0; j < bestNextMatrix.length; j++) {
	    			if (bestNextMatrix[i][j] <= settings.sigmaLogNoise * unfoldResult.traccia_attivita.size()) {
	    				bestNextMatrix[i][j] = 0.0D;
	    			}
	    		}
        	}
	    }
	    
	    // Costruisco il grafo unfolded
	    // Utilizzando solo le informazioni contenute nel log
	    System.out.println("Costruzione del grafo unfolded... ");
	    Graph grafoUnfolded = cnmining.costruisciGrafoUnfolded(
    		unfoldResult.map, bestNextMatrix
	    );
	    
	    System.out.println("Costruzione del grafo folded...");
	    LogUnfolderResult foldResult = new LogUnfolderResult();
	    Graph grafoFolded = cnmining.costruisciGrafoFolded(
	    	grafoUnfolded, log, foldResult.map, 
	    	foldResult.attivita_tracce, foldResult.traccia_attivita
	    );
	    
	    if (cnmining.verificaConsistenzaVincoli(vincoli.positivi, vincoli.negati) == false)
	    {
	    	System.out.println("\n\nImpossibile proseguire\nI Vincoli non sono consistenti");
	    	System.exit(0);
	    }
	    else System.out.println("I Vincoli sono consistenti");
	    
	    if (settings.areConstraintsAvailable())
	    {
	    	System.out.println("Stampa il grafo folded");
	      
	    	// TODO: riga 1124
	    	// è una cosa di debug grafico su consolo
	    	// per ora evito	
	    }
	    
	    // trovo le attività parallele
	    ObjectArrayList<FakeDependency> attivitaParallele = cnmining.getAttivitaParallele(
    		bestNextMatrix, grafoUnfolded, unfoldResult.map, 
    		vincoli.positivi, foldResult.map, grafoFolded
	    );
	    
	    // CNMining.java riga 1162
	    System.out.println("Algoritmo fase 2...");
	    
	    cnmining.algoritmo2(
    		bestNextMatrix, grafoUnfolded, unfoldResult.map,
    		unfoldResult.attivita_tracce, unfoldResult.traccia_attivita,
    		causalScoreMatrix, settings.sigmaUpCsDiff, foldResult.map, 
    		vincoli.forbidden, vincoli.positivi, vincoli.negati
    	);
	    	
	    // Riaggiorno le risorse precedentemente calcolate
	    // facendo, ora, riferimento ai risultati dell'algoritmo 2
	    System.out.println("Ricostruisco grafo folded dopo algoritmo 2");
	    
	    grafoFolded = cnmining.costruisciGrafoFolded(
	    	grafoUnfolded, log, foldResult.map, 
	    	foldResult.attivita_tracce, foldResult.traccia_attivita
	    );
	    for (int i = 0; i < grafoUnfolded.listaNodi().size(); i++)
	    {
	    	Node n = grafoUnfolded.listaNodi().get(i);
	    	n.setMark(false);
	    }
	    ObjectArrayList<FakeDependency> attivitaParalleleResidue = cnmining.getAttivitaParallele(
	    	bestNextMatrix, grafoUnfolded, unfoldResult.map, 
	    	vincoli.positivi, foldResult.map, grafoFolded
	    );

	    // Verifica sul folding
	    for (int i = 0; i < grafoFolded.getLista_archi().size(); i++)
	    {
	    	Edge e = grafoFolded.getLista_archi().get(i);
	    	for (int y = 0; y < vincoli.positivi.size(); y++)
	    	{
	    		Constraint c = vincoli.positivi.get(y);
	    		if ((c.getBodyList().contains(e.getX().getNomeAttivita())) && 
	    			(c.getHeadList().contains(e.getY().getNomeAttivita()))
	    		)
	    		{
	    			e.setFlag(true);
	    			System.out.println(e + " OK!!!!!!");
	    			break;
	    		}
	    		System.out.println("NOT OK!!!!!!!");
	    	}
    	}
	    System.out.println("GRAFO FOLDED!");
	    
	    // Riga 1220
	    double[][] causalScoreMatrixResidua = cnmining.calcoloMatriceDeiCausalScore(
	    	log, foldResult.map, foldResult.traccia_attivita, settings.fallFactor
	    );

	    System.out.println("POST-PROCESSING RIMOZIONE DIPENDENZE INDIRETTE... ");
	    
	    cnmining.rimuoviDipendezeIndirette(
	    	grafoFolded, foldResult.map, 
	    	foldResult.attivita_tracce, foldResult.traccia_attivita, 
	    	causalScoreMatrixResidua, settings.sigmaLogNoise, vincoli.positivi
	    );
	    
	    Node start = new Node(
	    	cnmining.attivita_iniziale, 
	    	foldResult.map.get(cnmining.attivita_iniziale)
	    );
	    Node end = new Node(
	    	cnmining.attivita_finale, 
	    	foldResult.map.get(cnmining.attivita_finale)
	    );
	    
	    ObjectArrayList<Node> startActivities = new ObjectArrayList<Node>();	    
	    ObjectArrayList<Node> endActivities = new ObjectArrayList<Node>();
	    
	    grafoFolded = cnmining.rimuoviAttivitaFittizie(
	    	grafoFolded, foldResult.map, 
	    	foldResult.traccia_attivita, foldResult.attivita_tracce, 
	    	start, end, log, startActivities, endActivities
	    );
	    	    
	    cnmining.computeBindings(
	    	grafoFolded, foldResult.traccia_attivita, foldResult.map
	    );
	    
	    // TODO: riga 2184, sposto tutto in una funzione
	    System.out.println("PROCEDURA REMOVABLE-EDGES ");
	    
	    causalScoreMatrixResidua = cnmining.calcoloMatriceDeiCausalScore(
		    log, foldResult.map, foldResult.traccia_attivita, settings.fallFactor
		);
	    
	    cnmining.rimuoviArchiRimovibili(
	    	grafoFolded, causalScoreMatrixResidua, 
	    	vincoli.positivi, foldResult.map, settings.relativeToBest
	    );
		cnmining.rimuoviNodiRimuovibili(grafoFolded);
		
		// Riga 1382, inizio rappresentazione grafica
	    
		return "Hello RTTMining";
	}
	
	/*
	 * Esegui il parsing dei vincoli da file xml
	 * e caricali sulle variabili locali
	 */
	
	private static void caricaVincoli(){
		// Se ho dato il consenso al caricamento dei vincoli
		if( settings.areConstraintsAvailable() )
		{
			// procedo a eseguire il parsing
			// essendo questi in formato xml
			ConstraintParser constraintsParser = new ConstraintParser(settings.constraintsFilename);
			if(constraintsParser.parse()){
				 ObjectArrayList<Constraint> constraints = constraintsParser.getConstraints();
		         if (constraints.size() == 0) {
		        	 JOptionPane.showMessageDialog(null, "No constraints contained in the input file...");
		         }
		         for (int i = 0; i < constraints.size(); i++)
		         {
		        	 Constraint vincolo = constraints.get(i);
		        	 if (vincolo.isPositiveConstraint())
		        		 vincoli.positivi.add(vincolo);
		        	 else
		        	 {
		        		 vincoli.negati.add(vincolo);
		        		 
		                 // TODO: riga 955 CNMining, controllare bene
		        		 
		        		 // Questa implementazione è di mia interpretazione
		        		 Iterator<String> headIterator, bodyIterator;
		        		 headIterator = vincolo.getHeadList().iterator();
		        		 bodyIterator = vincolo.getBodyList().iterator();
		        		 
		        		 // trova la testa e la coda
		        		 while(headIterator.hasNext())
		        			 headIterator.next();
		        		 while(bodyIterator.hasNext())
		        			 bodyIterator.next();
		        		 
		        		 vincoli.forbidden.add(new Forbidden(bodyIterator.toString(), headIterator.toString()));		        		 
		        	 }		        		 		     
		         }
			}
			else {
				// procedi come se non fossero stati specificati vincoli
				JOptionPane.showMessageDialog(null, "Invalid constraints file\nThe algoritm will now run without constraints...");		          
			}			
		}
	}
	
}
