package org.processmining.plugins.rttmining;

import java.util.Iterator;

import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;

import com.carrotsearch.hppc.IntArrayList;
import com.carrotsearch.hppc.ObjectArrayList;
import com.carrotsearch.hppc.ObjectContainer;
import com.carrotsearch.hppc.ObjectIntOpenHashMap;
import com.carrotsearch.hppc.ObjectObjectOpenHashMap;
import com.carrotsearch.hppc.ObjectOpenHashSet;
import com.carrotsearch.hppc.cursors.ObjectCursor;

public class CNMining {		
	
	public static String attivita_iniziale = "_START_";
	public static String attivita_finale = "_END_";

	public ObjectArrayList<FakeDependency> getAttivitaParallele(double[][] m, Graph graph, ObjectIntOpenHashMap<String> map, ObjectArrayList<Constraint> vincoli_positivi, ObjectIntOpenHashMap<String> folded_map, Graph folded_g)
	{
		// TODO: modificato dall'originale
		// CNMining.java riga 4280
		ObjectArrayList<FakeDependency> lista_attivita_parallele = new ObjectArrayList<FakeDependency>();
		
		Iterator<ObjectCursor<Node>> listaIterator = graph.listaNodi().iterator();
		Iterator<ObjectCursor<Node>> adiacentiIterator;
		
		do {
			ObjectCursor<Node> np = listaIterator.next();		      
			adiacentiIterator = graph.adjacentNodes(np.value).iterator();

			ObjectCursor<Node> nr = adiacentiIterator.next();
			
			if (this.bfs(graph, nr.value, np.value, null, null))
			{
				boolean vincoliSoddisfatti = verificaVincoliPositivi(
					folded_g, 
					folded_g.getNode((np.value).getNomeAttivita().split("#")[0], 
					folded_map.get((np.value).getNomeAttivita().split("#")[0])), 
					folded_g.getNode((nr.value).getNomeAttivita().split("#")[0], 
					folded_map.get((nr.value).getNomeAttivita().split("#")[0])), 
					vincoli_positivi, folded_map
				);
				if (vincoliSoddisfatti) {
					lista_attivita_parallele.add(
						new FakeDependency((np.value).getID_attivita(),
						(nr.value).getID_attivita())
					);
				}
			}
			for (int ni = 0; ni < graph.listaNodi().size(); ni++)
			{
				Node n = graph.listaNodi().get(ni);
				n.setMark(false);
			}
		}
		while(listaIterator.hasNext() && adiacentiIterator.hasNext());
		
		return lista_attivita_parallele;
	}
	
	private boolean bfs(Graph graph, Node x, Node y, Node f, ObjectArrayList<Node> path)
	{
		boolean atLeastOnePath = false;
		if (x.equals(y))
		{
			if (graph.isConnected(x, y)) {
				return true;
			}
			if (path == null) {
				path = new ObjectArrayList<Node>();
			}
		}
		ObjectArrayList<Node> nodes = new ObjectArrayList<Node>();
		nodes.add(x);
		x.setMark(true);
		Node t;
		int i = 0;
		// TODO: ennesima modifica, fa sti for a cazzo
		// CNMining.java riga 3180
		do
		{
			t = nodes.remove(0);
			if (path != null) {
				path.add(t);
			}
			if (t.equals(y)) {
				if (x.equals(y))
				{
					if (path.size() > 1) {
						atLeastOnePath = true;
					}
				}
				else {
					atLeastOnePath = true;
				}
			}
			Node k = graph.adjacentNodes(t).get(i);
			if ((!k.isMarked()) && (!k.equals(f)))
			{
				k.setMark(true);
				nodes.add(k);
			}
			i++;
		}
		while(!nodes.isEmpty() && i < graph.adjacentNodes(t).size());
		return atLeastOnePath;
	}
	
	public boolean verificaVincoliPositivi(Graph graph, Node np, Node nr, ObjectArrayList<Constraint> vincoliPositivi, ObjectIntOpenHashMap<String> map)
	{
		if ((np != null) && (nr != null)) {
			graph.removeEdge(np, nr);
		}
		for (ObjectCursor<Constraint> vincoloPositivo : vincoliPositivi)
		{
			Constraint vincolo = vincoloPositivo.value;
      
			boolean path_constraint = vincolo.isPathConstraint();
      
			boolean vincolo_soddisfatto = false;
			
			Iterator<String> headIterator = vincolo.getHeadList().iterator();
			Iterator<String> bodyIterator = vincolo.getBodyList().iterator();
			
			// TODO: interpretazione mia, riga 5400 CNMining.java 
			while(headIterator.hasNext() && bodyIterator.hasNext())
			{
				String head = headIterator.next();
				String body = bodyIterator.next();

				Node nHead = graph.getNode(head, map.get(head));
				Node nBody = graph.getNode(body, map.get(body));
				
				if (graph.isConnected(nBody, nHead))
				{
					vincolo_soddisfatto = true;          
					break;
				}
				if (path_constraint)
				{
					for (int ni = 0; ni < graph.listaNodi().size(); ni++)
					{
						Node n = graph.listaNodi().get(ni);
						n.setMark(false);
					}
					if (bfs(graph, nBody, nHead, null, null))
					{
						vincolo_soddisfatto = true;            
						break;
					}
				}
			}
			if (!vincolo_soddisfatto)
			{
				if ((np != null) && (nr != null)) {
					graph.addEdge(np, nr, false);
				}
				return false;
			}
		}
		if ((np != null) && (nr != null)) {
			graph.addEdge(np, nr, false);
		}
		return true;
	}

	public Graph costruisciGrafoFolded(Graph g, XLog log, ObjectIntOpenHashMap<String> map, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> attivita_tracce, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita)
	{
		int count = 0;
		for (int i = 0; i < log.size(); i++)
		{
			XTrace trace = log.get(i);
			String traccia = trace.getAttributes().get("concept:name") + " # " + i;
			if (!traccia_attivita.containsKey(traccia)) {
				traccia_attivita.put(traccia, new ObjectArrayList());
			}
			for (XEvent activity : trace)
			{
				String nome_attivita = activity.getAttributes().get("concept:name").toString();
				if (!map.containsKey(nome_attivita))
				{
					map.put(nome_attivita, count);
					count++;
				}
				if (!attivita_tracce.containsKey(nome_attivita))
				{
					ObjectArrayList<String> lista_tracce = new ObjectArrayList();
					lista_tracce.add(traccia);
					attivita_tracce.put(nome_attivita, lista_tracce);
				}
				((ObjectArrayList)attivita_tracce.get(nome_attivita)).add(traccia);
  
				((ObjectArrayList)traccia_attivita.get(traccia)).add(nome_attivita);
			}
		}
		ObjectArrayList<Edge> lista_archi_unfolded = g.getLista_archi();

		Graph graph = new Graph();

		Object[] keys = map.keys;
		int[] values = map.values;
		for (int i = 0; i < map.allocated.length; i++) {
			if (map.allocated[i] != false)
			{
				String key = (String)keys[i];
				Integer value = Integer.valueOf(values[i]);
				Node node = new Node(key, value.intValue());
				if (!graph.getMap().containsKey(node)) {
					graph.getMap().put(node, new ObjectOpenHashSet());
				}
			}
		}
		keys = g.getMap().keys;

		Object[] vals = g.getMap().values;
		for (int i = 0; i < g.getMap().allocated.length; i++) {
			if (g.getMap().allocated[i] != false)
			{
				Node n = (Node)keys[i];
    
				ObjectOpenHashSet<Node> n_adjacents = (ObjectOpenHashSet)vals[i];
    
				int it1 = 0;
				while (it1 < graph.listaNodi().size())
				{
					Node newnode = graph.listaNodi().get(it1);
					if (newnode.getNomeAttivita().equals(n.getNomeAttivita().split("#")[0])) {
						for (ObjectCursor<Node> n_k : n_adjacents)
						{
							int it = 0;
							while (it < graph.listaNodi().size())
							{
								Node new_n_k = graph.listaNodi().get(it);
								if (new_n_k.getNomeAttivita().equals(((Node)n_k.value).getNomeAttivita().split("#")[0]))
								{
									if (((ObjectOpenHashSet)graph.getMap().get(newnode)).contains(new_n_k)) {
										break;
									}
									for (ObjectCursor<Edge> e : lista_archi_unfolded) {
										if (e.value.equals(new Edge(n, (Node)n_k.value)))
										{
											graph.addEdge(newnode, new_n_k, ((Edge)e.value).isFlag());
											
											newnode.incr_Outer_degree();
											new_n_k.incr_Inner_degree();
											break;
										}
									}
									break;
								}
								it++;
							}
						}
					}
					it1++;
				}
			}
		}
    
		return graph;
	}
	
	public Graph costruisciGrafoUnfolded(ObjectIntOpenHashMap<String> map, double[][] bestNextMatrix){
		Graph graph = new Graph();
		    
	    Object[] keys = map.keys;
	    int[] values = map.values;
	    boolean[] states = map.allocated;
	    for (int iii = 0; iii < states.length; iii++) {
	    	if (states[iii] != false)
	    	{
	    		Node node = new Node((String)keys[iii], values[iii]);
		        Object[] nKeys = graph.getMap().keys;
		        boolean[] nStates = graph.getMap().allocated;
		        
		        boolean found = false;
		        for (int jj = 0; jj < nStates.length; jj++) {
		        	if ((nStates[jj] != false) && (nKeys[jj].equals(node)))
		        	{
		        		found = true;
		        		break;
		        	}
		        }
		        if (!found) {
		        	graph.getMap().put(node, new ObjectOpenHashSet());
		        }
	    	}
    	}
	    for (int p = 0; p < bestNextMatrix.length; p++) {
	    	for (int r = 0; r < bestNextMatrix[0].length; r++) {
	    		if (bestNextMatrix[p][r] > 0.0D)
		        {
	    			Node np = graph.getNode(this.getKeyByValue(map, p), p);
		          
	    			Node nr = graph.getNode(this.getKeyByValue(map, r), r);
		          
	    			graph.addEdge(np, nr, false);
		          
	    			np.incr_Outer_degree();
	    			nr.incr_Inner_degree();
		        }
	    	}
	    }
		
		return graph;
	}
	
	private String getKeyByValue(ObjectIntOpenHashMap<String> map, int value)
	{
		Object[] keys = map.keys;
		for (int i = 0; i < map.allocated.length; i++) {
			if ((map.allocated[i] != false) && 
					(value == map.values[i])) {
				return (String)keys[i];
			}
		}
		System.out.println("Errore key non trovata per id " + value);
		return null;
	}
	
	public boolean verificaConsistenzaVincoli(ObjectArrayList<Constraint> vincoli_positivi, ObjectArrayList<Constraint> vincoli_negati)
	{
		for (int i = 0; i < vincoli_positivi.size(); i++)
		{
			Constraint c = vincoli_positivi.get(i);
			for (int j = 0; j < vincoli_negati.size(); j++)
			{
				Constraint f = vincoli_negati.get(j);
				if ((c.equals(f)) && (((c.isPathConstraint()) && (f.isPathConstraint())) || ((!c.isPathConstraint()) && (!f.isPathConstraint())))) {
					return false;
				}
			}
		}
		return true;
	}
	
	/*
	 * TODO: non funziona
	 */
	public void creaVincoliUnfold(ConstraintsManager vincoli, LogUnfolderResult result)
	{
		System.out.println("Creazione vincoli unfold");
		int i = 0;
		Iterator<ObjectCursor<Constraint>> positiviIterator = vincoli.positivi.iterator();
		Iterator<ObjectCursor<Constraint>> negatiIterator = vincoli.negati.iterator();
	    
		while(positiviIterator.hasNext() && i < result.map.allocated.length){
			ObjectCursor<Constraint> vincolo = positiviIterator.next();
			Object[] keys = result.map.keys;
			
			if (result.map.allocated[i] != false)
	     	{
				String unfolded_head = (String)keys[i];
	     		if ((vincolo.value).getHeadList().contains(unfolded_head.split("#")[0]))
	     		{
	     			Constraint vincoloUnfolded = new Constraint();	          
	     			vincoloUnfolded.setConstraintType(vincolo.value.isPositiveConstraint());
	     			vincoloUnfolded.setPathConstraint(vincolo.value.isPathConstraint());
	     			vincoloUnfolded.addHead(unfolded_head);

	     			for (int j = 0; j < result.map.allocated.length; j++) {
	     				if (result.map.allocated[j] != false)
	     				{
	     					String unfolded_body = (String)keys[j];
	     					if ((vincolo.value).getBodyList().contains(unfolded_body.split("#")[0])) {
	     						vincoloUnfolded.addBody(unfolded_body);
	     					}
	     				}
	     			}
	     			vincoli.positiviUnfolded.add(vincoloUnfolded);
	     		}
	     	}
			i++;
		}
		
		i = 0;
		
		while(negatiIterator.hasNext() && i < result.map.allocated.length){
			ObjectCursor<Constraint> vincolo = negatiIterator.next();
			Object[] keys = result.map.keys;
			
	    	if (result.map.allocated[i] != false)
	    	{
	    		String unfolded_head = (String)keys[i];
	    		if ((vincolo.value).getHeadList().contains(unfolded_head.split("#")[0]))
	    		{
	    			Constraint vincoloUnfolded = new Constraint();
	          
	    			vincoloUnfolded.setConstraintType(vincolo.value.isPositiveConstraint());
	    			vincoloUnfolded.setPathConstraint(vincolo.value.isPathConstraint());
	    			vincoloUnfolded.addHead(unfolded_head);
	    			for (int j = 0; j < result.map.allocated.length; j++) {
	    				if (result.map.allocated[j] != false)
	    				{
	    					String unfolded_body = (String)keys[j];
	    					if ((vincolo.value).getBodyList().contains(unfolded_body.split("#")[0]))
	    					{
	    						vincoloUnfolded.addBody(unfolded_body);
	    						vincoli.forbidden.add(new Forbidden(unfolded_body, unfolded_head));
	    					}
	    				}
	    			}
	    			vincoli.negatiUnfolded.add(vincoloUnfolded);
	    		}
	     	}
	    	i++;
		}
	}
	
	public double[][] calcoloMatriceDeiCausalScore(XLog log, ObjectIntOpenHashMap<String> map, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita, double ff)
	{
		ObjectArrayList<IntArrayList> vlog = new ObjectArrayList<IntArrayList>();
		    
	    Object[] values = traccia_attivita.values;
	    ObjectCursor<String> s;
	    for (int i = 0; i < traccia_attivita.allocated.length; i++) {
	    	if (traccia_attivita.allocated[i] != false)
	    	{
	    		IntArrayList t1 = new IntArrayList();
		        ObjectArrayList<String> vals = (ObjectArrayList<String>)values[i];
		        
		        Iterator<ObjectCursor<String>> iterator = vals.iterator();
		        while(iterator.hasNext()){
		        	s = iterator.next();
		        	t1.add(map.get(s.value));
		        }
		        vlog.add(t1);
	    	}
	    }
	    double[][] weightMatrix = null;
	    try
	    {
	    	WeightEstimator.CLOSEST_OCCURRENCE_ONLY = true;
	    	WeightEstimator weightEstimator = new WeightEstimator(map.size(), -1, ff, 1);
		    for (ObjectCursor<IntArrayList> t : vlog) {
		    	weightEstimator.addTraceContribution(t.value);
		    }
		    weightEstimator.computeWeigths();
		    weightMatrix = weightEstimator.getDependencyMatrix();
	    }
	    catch (Exception e)
	    {
	    	e.printStackTrace();
	    }
	    return weightMatrix;
	}
	
	public double[][] buildBestNextMatrix(XLog log, ObjectIntOpenHashMap<String> map, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita, double[][] cs, ObjectArrayList<Forbidden> lista_forbidden_unfolded)
	{
		double[][] mNext = new double[map.size()][map.size()];
	    
	    Object[] values = traccia_attivita.values;
	    for (int i = 0; i < traccia_attivita.allocated.length; i++) {
	    	if (traccia_attivita.allocated[i] != false)
	    	{
	    		ObjectArrayList<String> value = (ObjectArrayList<String>)values[i];
	        
	    		ObjectArrayList<String> predecessors = new ObjectArrayList<String>();
	    		ObjectArrayList<String> successors = new ObjectArrayList<String>(value);
	        
	    		int j = 0;
	    		while (j < value.size())
	    		{
	    			String activity_x = value.get(j);
	          
	    			successors.removeFirstOccurrence(activity_x);
	          
	    			String bestPred = "";
	    			
	    			String bestSucc = "";
	          
	    			double bestPredCS = Double.MIN_VALUE;
	    			double bestSuccCS = Double.MIN_VALUE;
	    			if (predecessors.size() > 0)
	    			{
	    				int itPred = 0;
	    				Object[] buffer = predecessors.buffer;
	    				while (itPred < predecessors.size())
	    				{
	    					String pred = (String)buffer[itPred];
	              
	    					double predCS = cs[map.get(pred)][map.get(activity_x)];
	    					if ((predCS > bestPredCS) && (!lista_forbidden_unfolded.contains(new Forbidden(pred, activity_x))))
	    					{
	    						bestPred = pred;
	    						bestPredCS = predCS;
	    					}
	    					itPred++;
	    				}
	    				int x = map.get(bestPred);
	    				int y = map.get(activity_x);
	    				mNext[x][y] += 1.0D;
	    			}
	    			if (successors.size() > 0)
	    			{
	    				int itSucc = 0;
	    				Object[] buffer = successors.buffer;
	    				while (itSucc < successors.size())
	    				{
	    					String succ = (String)buffer[itSucc];
	    					double succCS = cs[map.get(activity_x)][map.get(succ)];
	    					if ((succCS > bestSuccCS) && (!lista_forbidden_unfolded.contains(new Forbidden(activity_x, succ))))
	    					{
	    						bestSucc = succ;
	    						bestSuccCS = succCS;
	    					}
	    					itSucc++;
	    				}
	    				int x = map.get(activity_x);
	            
	    				int y = map.get(bestSucc);
	            
	    				mNext[x][y] += 1.0D;
	    			}
	    			predecessors.add(activity_x);
	    			j++;
	    		}
	    	}
    	}
    	return mNext;
	}
	
	// ###########################################
	//			Sezione algoritmo 2
	// ###########################################
	
	// CNMining.java riga 2432
	public void algoritmo2(double[][] m, Graph graph, ObjectIntOpenHashMap<String> map, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> attivita_tracce, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita, double[][] csm, double sigma_1, ObjectIntOpenHashMap<String> folded_map, ObjectArrayList<Forbidden> lista_forbidden, ObjectArrayList<Constraint> vincoli_positivi, ObjectArrayList<Constraint> vincoli_negati)
	{
		ObjectArrayList<FakeDependency> ap_rimosse = new ObjectArrayList<FakeDependency>();
		ap_rimosse.trimToSize();
		int k = 1;
		for (;;)
		{
			Graph folded_g = this.costruisciGrafoFolded(
				graph, null, folded_map, null, null
			);
      
			ObjectArrayList<FakeDependency> attivita_parallele = getAttivitaParallele(
				m, graph, map, vincoli_positivi,
				folded_map, folded_g
			);
			for (int i = 0; i < ap_rimosse.size(); i++) {
				attivita_parallele.removeFirstOccurrence(ap_rimosse.get(i));
			}
			if (attivita_parallele.size() == 0) {
				return;
			}
			FakeDependency best_ap = null;
      
			double best_causal_score = Double.MAX_VALUE;
			for (int i = 0; i < attivita_parallele.size(); i++)
			{
				FakeDependency current_ap = attivita_parallele.get(i);
        
				double current_ap_cs = csm[current_ap.getAttivita_x()][current_ap.getAttivita_y()];
				if (current_ap_cs < best_causal_score)
				{
					best_causal_score = current_ap_cs;
					best_ap = current_ap;
				}
			}
			Node nx = graph.getNode(getKeyByValue(map, best_ap.getAttivita_x()), best_ap.getAttivita_x());
      
			Node ny = graph.getNode(getKeyByValue(map, best_ap.getAttivita_y()), best_ap.getAttivita_y());
      	
			graph.removeEdge(nx, ny);
			m[best_ap.getAttivita_x()][best_ap.getAttivita_y()] = 0.0D;
      
			nx.decr_Outer_degree();
			ny.decr_Inner_degree();
      
			ObjectOpenHashSet<String> lista_candidati_best_pred = null;
      
			lista_candidati_best_pred = bestPred_Folded(
				ny.getID_attivita(), nx.getID_attivita(), 
				map, attivita_tracce, 
				traccia_attivita
			);
      
			String best_pred = attivita_iniziale + "#" + String.format("%04d", new Object[] { Integer.valueOf(0) });
			if (lista_candidati_best_pred != null) {
				if (lista_candidati_best_pred.size() > 0)
				{
					ObjectArrayList<String> lista_candidati_best_pred_unfolded = new ObjectArrayList<String>();
					Object[] keys = lista_candidati_best_pred.keys;
					for (int i = 0; i < lista_candidati_best_pred.allocated.length; i++) {
						if (lista_candidati_best_pred.allocated[i] != false)
						{
							String activity = (String)keys[i];
							String best_unfolded_item = "";
							double best_unfolded_cs = -1.0D;
          
							keys = map.keys;
							boolean[] values = map.allocated;
							for (int j = 0; j < values.length; j++) {
								if (values[j] != false)
								{
									String unfolded_item = (String)keys[j];
									if (unfolded_item != null) {
										if ((unfolded_item.split("#")[0].equals(activity)) && 
												(csm[map.get(unfolded_item)][ny.getID_attivita()] > best_unfolded_cs))
										{
											best_unfolded_item = unfolded_item;
											best_unfolded_cs = csm[map.get(unfolded_item)][ny.getID_attivita()];
										}
									}
								}
							}
							lista_candidati_best_pred_unfolded.add(best_unfolded_item);
						}
					}
					best_pred = getFinalBestPred(
						graph, csm, ny, map, 
						lista_candidati_best_pred_unfolded, 
						vincoli_negati, lista_forbidden, folded_g, 
						folded_map, false
					);
				}
				else
				{
					System.out.println("FALLIMENTO BEST PRED NON TROVATO!!!");
				}
			}
			ObjectOpenHashSet<String> lista_candidati_best_succ = null;
  
			lista_candidati_best_succ = bestSucc_Folded(
				best_ap.getAttivita_x(), best_ap.getAttivita_y(), 
				map, attivita_tracce, traccia_attivita
			);
  
			String best_succ = attivita_finale + "#" + String.format("%04d", new Object[] { Integer.valueOf(0) });
			if (lista_candidati_best_succ != null) {
				if (lista_candidati_best_succ.size() > 0)
				{
					ObjectArrayList<String> lista_candidati_best_succ_unfolded = new ObjectArrayList<String>();
      
					Iterator<ObjectCursor<String>> it = lista_candidati_best_succ.iterator();
					while (it.hasNext())
					{
						String activity = (it.next()).value;
        
						String best_unfolded_item = "";
						double best_unfolded_cs = -1.0D;
        
						boolean[] states = map.allocated;
        
						Object[] keys = map.keys;
						for (int j = 0; j < states.length; j++) {
							if (states[j] != false)
							{
								String unfolded_item = (String)keys[j];
								if (unfolded_item != null) {
									if ((unfolded_item.split("#")[0].equals(activity)) && 
											(csm[nx.getID_attivita()][map.get(unfolded_item)] > best_unfolded_cs))
									{
										best_unfolded_item = unfolded_item;
										best_unfolded_cs = csm[nx.getID_attivita()][map.get(unfolded_item)];
									}
								}
							}
						}
						if (best_unfolded_item.equals(""))
						{
							System.out.println(activity);
							System.out.println("errore best succ ");
							throw new RuntimeException("ciao");
						}
						lista_candidati_best_succ_unfolded.add(best_unfolded_item);
					}
					best_succ = getFinalBestSucc(
						graph, csm, nx, map, 
						lista_candidati_best_succ_unfolded, 
						vincoli_negati, lista_forbidden, 
						folded_g, folded_map, false
					);
				}
				else
				{
					System.out.println("FALLIMENTO BEST SUCC NON TROVATO!!!");
				}
			}
			if (!best_pred.equals(""))
			{
				Node nz = graph.getNode(getKeyByValue(map, map.get(best_pred)), map.get(best_pred));
				if (!graph.isConnected(nz, ny))
				{
					m[map.get(best_pred)][best_ap.getAttivita_y()] = 1.0D;
					graph.addEdge(nz, ny, false);
      
					nz.incr_Outer_degree();
					ny.incr_Inner_degree();
				}
			}
			if (!best_succ.equals(""))
			{
				Node nw = graph.getNode(getKeyByValue(map, map.get(best_succ)), map.get(best_succ));
    
				System.out.println();
				if (!graph.isConnected(nx, nw))
				{
					m[best_ap.getAttivita_x()][map.get(best_succ)] = 1.0D;
					graph.addEdge(nx, nw, false);
      
					nx.incr_Outer_degree();
					nw.incr_Inner_degree();
				}
			}
			ap_rimosse.add(best_ap);
			if (graph.isConnected(ny, nx))
			{
				boolean soddisfa_vincoli_positivi = verificaVincoliPositivi(
					folded_g, 
					folded_g.getNode(ny.getNomeAttivita().split("#")[0], 
					folded_map.get(ny.getNomeAttivita().split("#")[0])), 
					folded_g.getNode(nx.getNomeAttivita().split("#")[0], 
					folded_map.get(nx.getNomeAttivita().split("#")[0])), 
					vincoli_positivi, folded_map
				);
				if (soddisfa_vincoli_positivi)
				{
					System.out.println();
					FakeDependency best_ap_yx = new FakeDependency(ny.getID_attivita(), nx.getID_attivita());
      
					graph.removeEdge(ny, nx);
					m[best_ap.getAttivita_y()][best_ap.getAttivita_x()] = 0.0D;
      
					ny.decr_Outer_degree();
					nx.decr_Inner_degree();
      
					ObjectOpenHashSet<String> lista_candidati_best_pred_yx = null;
      
					lista_candidati_best_pred_yx = bestPred_Folded(
						nx.getID_attivita(), ny.getID_attivita(), map, 
						attivita_tracce, traccia_attivita
					);
      
					String best_pred_yx = attivita_iniziale + "#" + String.format("%04d", new Object[] { Integer.valueOf(0) });
					if (lista_candidati_best_pred_yx != null) {
						if (lista_candidati_best_pred_yx.size() > 0)
						{
							ObjectArrayList<String> lista_candidati_best_pred_unfolded = new ObjectArrayList<String>();
          
							Iterator<ObjectCursor<String>> it = lista_candidati_best_pred_yx.iterator();
							while (it.hasNext())
							{
								String activity = (it.next()).value;
								String best_unfolded_item = "";
								double best_unfolded_cs = -1.0D;
            
								boolean[] states = map.allocated;
								Object[] keys = map.keys;
								for (int j = 0; j < states.length; j++) {
									if (states[j] != false)
									{
										String unfolded_item = (String)keys[j];
										if (unfolded_item != null) {
											if ((unfolded_item.split("#")[0].equals(activity)) && 
													(csm[map.get(unfolded_item)][nx.getID_attivita()] > best_unfolded_cs))
											{
												best_unfolded_item = unfolded_item;
												best_unfolded_cs = csm[map.get(unfolded_item)][nx.getID_attivita()];
											}
										}
									}
								}
								if (!best_unfolded_item.equals("")) {
									lista_candidati_best_pred_unfolded.add(best_unfolded_item);
								}
							}
							best_pred_yx = getFinalBestPred(
								graph, csm, nx, map, 
								lista_candidati_best_pred_unfolded, 
								vincoli_negati, lista_forbidden, 
								folded_g, folded_map, false
							);
						}
						else
						{
							System.out.println("FALLIMENTO BEST PRED YX NON TROVATO!!!");
						}
					}
					ObjectOpenHashSet<String> lista_candidati_best_succ_yx = null;
      
					lista_candidati_best_succ_yx = bestSucc_Folded(
						best_ap.getAttivita_y(), best_ap.getAttivita_x(), 
						map, attivita_tracce, traccia_attivita
					);
      
					String best_succ_yx = attivita_finale + "#" + String.format("%04d", new Object[] { Integer.valueOf(0) });
					if (lista_candidati_best_succ_yx != null) {
						if (lista_candidati_best_succ_yx.size() > 0)
						{
							ObjectArrayList<String> lista_candidati_best_succ_unfolded = new ObjectArrayList<String>();
          
							Iterator<ObjectCursor<String>> it = lista_candidati_best_succ.iterator();
							while (it.hasNext())
							{
								String activity = (it.next()).value;
								String best_unfolded_item = "";
								double best_unfolded_cs = -1.0D;
            
								Object[] keys = map.keys;
            
								boolean[] states = map.allocated;
								for (int j = 0; j < states.length; j++) {
									if (states[j] != false)
									{
										String unfolded_item = (String)keys[j];
											if (unfolded_item != null) {
												if ((unfolded_item.split("#")[0].equals(activity)) && 
														(csm[ny.getID_attivita()][map.get(unfolded_item)] > best_unfolded_cs))
												{
													best_unfolded_item = unfolded_item;
													best_unfolded_cs = csm[ny.getID_attivita()][map.get(unfolded_item)];
												}
											}
									}
								}
								if (!best_unfolded_item.equals("")) {
									lista_candidati_best_succ_unfolded.add(best_unfolded_item);
								}
							}
							best_succ_yx = getFinalBestSucc(
								graph, csm, ny, map, 
								lista_candidati_best_succ_unfolded, 
								vincoli_negati, lista_forbidden, 
								folded_g, folded_map, false
							);
						}
						else
						{
							System.out.println("FALLIMENTO BEST SUCC YX NON TROVATO!!!");
						}
					}
					if (!best_pred_yx.equals(""))
					{
						Node nz = graph.getNode(getKeyByValue(map, map.get(best_pred_yx)), map.get(best_pred_yx));
						if (!graph.isConnected(nz, nx))
						{
							m[map.get(best_pred_yx)][best_ap.getAttivita_x()] = 1.0D;
							graph.addEdge(nz, nx, false);
          
							nz.incr_Outer_degree();
							nx.incr_Inner_degree();
						}
					}
					if (!best_succ_yx.equals(""))
					{
						Node nw = graph.getNode(getKeyByValue(map, map.get(best_succ_yx)), map.get(best_succ_yx));
						if (!graph.isConnected(ny, nw))
						{
							m[best_ap.getAttivita_y()][map.get(best_succ)] = 1.0D;
							graph.addEdge(ny, nw, false);
              
							ny.incr_Outer_degree();
							nw.incr_Inner_degree();
						}
					}
					ap_rimosse.add(best_ap_yx);
				}
			}
		}
	}
	
	// CNMining.java riga 3095
	public ObjectOpenHashSet<String> bestPred_Folded(int x, int y, ObjectIntOpenHashMap<String> map, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> attivita_tracce, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita)
	{
		String attivita_x = getKeyByValue(map, x);    
		String attivita_y = getKeyByValue(map, y);
    
		ObjectArrayList<String> lista_tracce_x = new ObjectArrayList<String>(
			(ObjectContainer)attivita_tracce.get(attivita_x)
		);
    	ObjectOpenHashSet<String> lista_tracce_y = new ObjectOpenHashSet<String>(
    		(ObjectContainer)attivita_tracce.get(attivita_y)
    	);
    
		lista_tracce_x.retainAll(lista_tracce_y);
    
		ObjectOpenHashSet<String> attivita_candidate = null;
    
		String trace_1 = "";
		if (lista_tracce_x.size() > 0)
		{
			trace_1 = lista_tracce_x.get(0);
			attivita_candidate = getPredecessors_FoldedLocal(trace_1, attivita_x, attivita_y, traccia_attivita);
		}
		else
		{
			attivita_candidate = new ObjectOpenHashSet<String>();
			attivita_candidate.add(attivita_iniziale);
		}
		for (int i = 1; i < lista_tracce_x.size(); i++)
		{
			String trace = lista_tracce_x.get(i);
      	
			ObjectOpenHashSet<String> predecessors = getPredecessors_FoldedLocal(trace, attivita_x, attivita_y, traccia_attivita);
      
			attivita_candidate.retainAll(predecessors);
		}
		return attivita_candidate;
	}
	
	private ObjectOpenHashSet<String> getPredecessors_FoldedLocal(String trace, String activity_x, String activity_y, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita)
	{
		ObjectOpenHashSet<String> predecessors_traccia = new ObjectOpenHashSet<String>();
    
		int i = 0;
		while (i < (traccia_attivita.get(trace)).size())
		{
			String activity_Z = (traccia_attivita.get(trace)).get(i);
			if (activity_Z.equals(activity_x)) {
				break;
			}
			if (!activity_Z.split("#")[0].equals(activity_y.split("#")[0])) {
				if (!predecessors_traccia.contains(activity_Z.split("#")[0])) {
					predecessors_traccia.add(activity_Z.split("#")[0]);
				}
			}
			i++;
		}
		return predecessors_traccia;
	}
	
	// CNMining.java riga 4360
	private String getFinalBestPred(Graph graph, double[][] csm, Node ny, ObjectIntOpenHashMap<String> map, ObjectArrayList<String> lista_candidati_best_pred_unfolded, ObjectArrayList<Constraint> vincoli_negati, ObjectArrayList<Forbidden> lista_forbidden, Graph folded_g, ObjectIntOpenHashMap<String> folded_map, boolean onlyNotPath)
	{
		for (ObjectCursor<Node> n : folded_g.listaNodi()) {
			((Node)n.value).setMark(false);
		}
		String best_pred = attivita_iniziale + "#" + String.format("%04d", new Object[] { Integer.valueOf(0) });
    
		double best_pred_cs = 0.0D;
    
		double minZ = Double.MAX_VALUE;
		if (onlyNotPath) {
			minZ = 0.0D;
		}
		for (ObjectCursor<String> attivita_zCursor : lista_candidati_best_pred_unfolded)
		{
			String attivita_z = (String)attivita_zCursor.value;
      
			ObjectArrayList<Node> c_nodes = new ObjectArrayList();
      
			int violations_counter = 0;
      
			Forbidden f = new Forbidden(attivita_z.split("#")[0], ny.getNomeAttivita().split("#")[0]);
			if (!lista_forbidden.contains(f))
			{
				for (ObjectCursor<Constraint> cpn : vincoli_negati) {
					if (((Constraint)cpn.value).isPathConstraint()) {
						if (((Constraint)cpn.value).getBodyList().contains(attivita_z.split("#")[0])) {
							for (String head : ((Constraint)cpn.value).getHeadList()) {
								c_nodes.add(new Node(head.split("#")[0], folded_map.get(head.split("#")[0])));
							}
						}
					}
				}
				Iterator localIterator5;
				for (??? = c_nodes.iterator(); ???.hasNext(); localIterator5.hasNext())
				{
					ObjectCursor<Node> c = (ObjectCursor)???.next();
					for (ObjectCursor<Node> n : folded_g.listaNodi()) {
						((Node)n.value).setMark(false);
					}
					path_violated = bfs(folded_g, folded_g.getNode(ny.getNomeAttivita().split("#")[0], folded_map.get(ny.getNomeAttivita().split("#")[0])), (Node)c.value, null, null);
					if (path_violated) {
						violations_counter++;
					}
					localIterator5 = folded_g.listaNodi().iterator(); continue;Object n = (ObjectCursor)localIterator5.next();
					((Node)((ObjectCursor)n).value).setMark(false);
				}
				Node z = new Node(attivita_z.split("#")[0], folded_map.get(attivita_z.split("#")[0]));
				for (boolean path_violated = folded_g.listaNodi().iterator(); path_violated.hasNext(); localIterator5.hasNext())
				{
					Object n = (ObjectCursor)path_violated.next();
					if (bfs(folded_g, (Node)((ObjectCursor)n).value, z, null, null)) {
						for (Object cpn : vincoli_negati) {
							if (((Constraint)((ObjectCursor)cpn).value).isPathConstraint()) {
								if ((((Constraint)((ObjectCursor)cpn).value).getBodyList().contains(((Node)n.value).getNomeAttivita().split("#")[0])) && (((Constraint)((ObjectCursor)cpn).value).getHeadList().contains(ny.getNomeAttivita().split("#")[0]))) {
									violations_counter++;
								}
							}
						}
					}
					localIterator5 = folded_g.listaNodi().iterator(); continue;Object nn = (ObjectCursor)localIterator5.next();
					((Node)((ObjectCursor)nn).value).setMark(false);
				}
				if (violations_counter < minZ)
				{
					minZ = violations_counter;
          
					best_pred = attivita_z;
					best_pred_cs = csm[map.get(attivita_z)][ny.getID_attivita()];
				}
				else if (violations_counter == minZ)
				{
					if (csm[map.get(attivita_z)][ny.getID_attivita()] > best_pred_cs)
					{
						best_pred = attivita_z;
						best_pred_cs = csm[map.get(attivita_z)][ny.getID_attivita()];
					}
				}
			}
		}
		return best_pred;
	}
	
	private String getFinalBestSucc(Graph graph, double[][] csm, Node nx, ObjectIntOpenHashMap<String> map, ObjectArrayList<String> lista_candidati_best_succ_unfolded, ObjectArrayList<Constraint> vincoli_negati, ObjectArrayList<Forbidden> lista_forbidden, Graph folded_g, ObjectIntOpenHashMap<String> folded_map, boolean notPathOnly)
	{
		for (ObjectCursor<Node> n : folded_g.listaNodi()) {
			(n.value).setMark(false);
		}
		Node x = folded_g.getNode(nx.getNomeAttivita().split("#")[0], folded_map.get(nx.getNomeAttivita().split("#")[0]));
    
		String best_succ = attivita_finale + "#" + String.format("%04d", new Object[] { Integer.valueOf(0) });
    
		double best_succ_cs = 0.0D;
		double minW = Double.MAX_VALUE;
		if (notPathOnly) {
			minW = 0.0D;
		}
		ObjectArrayList<Node> c_nodes = new ObjectArrayList<Node>();
		for (ObjectCursor<Constraint> cpn : vincoli_negati) {
			if ((cpn.value).isPathConstraint()) {
				if ((cpn.value).getBodyList().contains(nx.getNomeAttivita().split("#")[0])) {
					for (String head : (cpn.value).getHeadList()) {
						c_nodes.add(new Node(head.split("#")[0], folded_map.get(head.split("#")[0])));
					}
				}
			}
		}
		for (ObjectCursor<String> attivita_w : lista_candidati_best_succ_unfolded)
		{
			int violations_counter = 0;
      
			Forbidden f = new Forbidden(nx.getNomeAttivita().split("#")[0], (attivita_w.value).split("#")[0]);
			if (!lista_forbidden.contains(f))
			{
				Node nw = folded_g.getNode((attivita_w.value).split("#")[0], folded_map.get((attivita_w.value).split("#")[0]));
				Iterator localIterator5;
				ObjectCursor<Node> n;
				for (Iterator localIterator4 = c_nodes.iterator(); localIterator4.hasNext(); localIterator5.hasNext())
				{
					ObjectCursor<Node> c = (ObjectCursor)localIterator4.next();
          
					boolean path_violated = bfs(folded_g, nw, (Node)c.value, null, null);
					if (path_violated) {
						violations_counter++;
					}
					localIterator5 = folded_g.listaNodi().iterator(); continue;n = (ObjectCursor)localIterator5.next();
					((Node)n.value).setMark(false);
				}
				for (localIterator4 = folded_g.listaNodi().iterator(); localIterator4.hasNext(); n.hasNext())
				{
					ObjectCursor<Node> n = (ObjectCursor)localIterator4.next();
					if (bfs(folded_g, (Node)n.value, x, null, null)) {
						for (ObjectCursor<Constraint> cpn : vincoli_negati) {
							if ((((Constraint)cpn.value).isPathConstraint()) && 
									(((Constraint)cpn.value).getBodyList().contains(((Node)n.value).getNomeAttivita().split("#")[0])) && (((Constraint)cpn.value).getHeadList().contains(((String)attivita_w.value).split("#")[0]))) {
								violations_counter++;
							}
						}
					}
					n = folded_g.listaNodi().iterator(); continue;ObjectCursor<Node> nn = (ObjectCursor)n.next();
					((Node)nn.value).setMark(false);
				}
				if (violations_counter < minW)
				{
					best_succ = attivita_w.value;
					best_succ_cs = csm[nx.getID_attivita()][map.get(attivita_w.value)];
          
					minW = violations_counter;
				}
				else if ((violations_counter == minW) && 
						(csm[nx.getID_attivita()][map.get(attivita_w.value)] > best_succ_cs))
				{
					best_succ = attivita_w.value;
					best_succ_cs = csm[nx.getID_attivita()][map.get(attivita_w.value)];
				}
			}
		}
		return best_succ;
	}
	
	// CNMining.java riga 3133
	public ObjectOpenHashSet<String> bestSucc_Folded(int x, int y, ObjectIntOpenHashMap<String> map, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> attivita_tracce, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita)
	{
		String attivita_x = getKeyByValue(map, x);    
		String attivita_y = getKeyByValue(map, y);
    
		ObjectArrayList<String> lista_tracce_x = new ObjectArrayList(
			(ObjectContainer)attivita_tracce.get(attivita_x)
		);    
		ObjectOpenHashSet<String> lista_tracce_y = new ObjectOpenHashSet(
			(ObjectContainer)attivita_tracce.get(attivita_y)
		);
		
		lista_tracce_x.retainAll(lista_tracce_y);
    
		ObjectOpenHashSet<String> attivita_candidate = null;
    
		String trace_1 = "";
		if (lista_tracce_x.size() > 0)
		{
			trace_1 = lista_tracce_x.get(0);
			attivita_candidate = getSuccessors_FoldedLocal(trace_1, attivita_x, attivita_y, traccia_attivita);
		}
		else
		{
			attivita_candidate = new ObjectOpenHashSet<String>();
			attivita_candidate.add(attivita_finale);
		}
		int i = 1;
		while (i < lista_tracce_x.size())
		{
			String trace = lista_tracce_x.get(i);
      
			ObjectOpenHashSet<String> successors = getSuccessors_FoldedLocal(trace, attivita_x, attivita_y, traccia_attivita);
      
			attivita_candidate.retainAll(successors);
			i++;
		}
		return attivita_candidate;
	}
	
	private ObjectOpenHashSet<String> getSuccessors_FoldedLocal(String trace, String activity_x, String activity_y, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita)
	{
		ObjectOpenHashSet<String> successors_traccia = new ObjectOpenHashSet<String>();
    
		int i = (traccia_attivita.get(trace)).size() - 1;
		while (i >= 0)
		{
			String activity_W = (traccia_attivita.get(trace)).get(i);
			if (activity_W.equals(activity_x)) {
				break;
			}
			if (!activity_W.split("#")[0].equals(activity_y.split("#")[0])) {
				if (!successors_traccia.contains(activity_W.split("#")[0])) {
					successors_traccia.add(activity_W.split("#")[0]);
				}
			}
			i--;
		}
		return successors_traccia;
	}
}
