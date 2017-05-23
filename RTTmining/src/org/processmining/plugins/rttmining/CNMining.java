package org.processmining.plugins.rttmining;

import java.util.Iterator;

import org.deckfour.xes.model.XLog;

import com.carrotsearch.hppc.IntArrayList;
import com.carrotsearch.hppc.ObjectArrayList;
import com.carrotsearch.hppc.ObjectIntOpenHashMap;
import com.carrotsearch.hppc.ObjectObjectOpenHashMap;
import com.carrotsearch.hppc.cursors.ObjectCursor;

public class CNMining {		

	
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
