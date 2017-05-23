package org.processmining.plugins.rttmining;

import java.util.Iterator;

import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;

import com.carrotsearch.hppc.IntArrayList;
import com.carrotsearch.hppc.ObjectArrayList;
import com.carrotsearch.hppc.ObjectIntOpenHashMap;
import com.carrotsearch.hppc.ObjectObjectOpenHashMap;
import com.carrotsearch.hppc.ObjectOpenHashSet;
import com.carrotsearch.hppc.cursors.ObjectCursor;

public class CNMining {		

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
}
