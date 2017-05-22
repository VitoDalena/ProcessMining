package org.processmining.plugins.rttmining;

import java.util.Iterator;

import org.deckfour.xes.model.XLog;

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
			
			if (result.map.allocated[i] != false)
	     	{
				String unfolded_head = result.map.keys[i];
	     		if (vincolo.value.getHeadList().contains(unfolded_head.split("#")[0]))
	     		{
	     			Constraint vincoloUnfolded = new Constraint();
	          
	     			vincoloUnfolded.setConstraintType(vincolo.value.isPositiveConstraint());
	     			vincoloUnfolded.setPathConstraint(vincolo.value.isPathConstraint());
	     			vincoloUnfolded.addHead(unfolded_head);
	     			for (int j = 0; j < result.map.allocated.length; j++) {
	     				if (result.map.allocated[j] != false)
	     				{
	     					String unfolded_body = result.map.keys[j];
	     					if (vincolo.value.getBodyList().contains(unfolded_body.split("#")[0])) {
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
			
	    	if (result.map.allocated[i] != false)
	    	{
	    		String unfolded_head = result.map.keys[i];
	    		if (vincolo.value.getHeadList().contains(unfolded_head.split("#")[0]))
	    		{
	    			Constraint vincoloUnfolded = new Constraint();
	          
	    			vincoloUnfolded.setConstraintType(vincolo.value.isPositiveConstraint());
	    			vincoloUnfolded.setPathConstraint(vincolo.value.isPathConstraint());
	    			vincoloUnfolded.addHead(unfolded_head);
	    			for (int j = 0; j < result.map.allocated.length; j++) {
	    				if (result.map.allocated[j] != false)
	    				{
	    					String unfolded_body = result.map.keys[j];
	    					if (vincolo.value.getBodyList().contains(unfolded_body.split("#")[0]))
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
		return null;
	}
}
