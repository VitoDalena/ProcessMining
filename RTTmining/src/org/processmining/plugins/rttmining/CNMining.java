package org.processmining.plugins.rttmining;

import java.util.Iterator;

import org.deckfour.xes.model.XLog;

import com.carrotsearch.hppc.ObjectArrayList;
import com.carrotsearch.hppc.ObjectIntOpenHashMap;
import com.carrotsearch.hppc.ObjectObjectOpenHashMap;
import com.carrotsearch.hppc.cursors.ObjectCursor;

public class CNMining {		

	public void creaVincoliUnfolded(ConstraintsManager vincoli, LogUnfolderResult result)
	{
		int i = 0;
	    for (Iterator<ObjectCursor<Constraint>> localIterator = vincoli.positivi.iterator(); localIterator.hasNext(); i < result.map.allocated.length)
	    {
	    	ObjectCursor<Constraint> c = (ObjectCursor)localIterator.next();
	      
	     	Object[] keys = result.map.keys;
	      
	     	i = 0; continue;
	     	if (result.map.allocated[i] != false)
	     	{
	     		String unfolded_head = (String)keys[i];
	     		if (c.value.getHeadList().contains(unfolded_head.split("#")[0]))
	     		{
	     			Constraint unfolded_c = new Constraint();
	          
	     			unfolded_c.setConstraintType(c.value.isPositiveConstraint());
	     			unfolded_c.setPathConstraint(c.value.isPathConstraint());
	     			unfolded_c.addHead(unfolded_head);
	     			for (int j = 0; j < result.map.allocated.length; j++) {
	     				if (result.map.allocated[j] != false)
	     				{
	     					String unfolded_body = (String)keys[j];
	     					if (c.value.getBodyList().contains(unfolded_body.split("#")[0])) {
	     						unfolded_c.addBody(unfolded_body);
	     					}
	     				}
	     			}
	     			vincoli.positiviUnfolded.add(unfolded_c);
	     		}
	     	}
	     	i++;
		}
	    int i;
	    for (localIterator = vincoli_negati.iterator(); localIterator.hasNext(); i < map.allocated.length)
	    {
	    	ObjectCursor<Constraint> c = (ObjectCursor)localIterator.next();
	      
	    	Object[] keys2 = result.map.keys;
	      
	    	i = 0; continue;
	    	if (result.map.allocated[i] != false)
	    	{
	    		String unfolded_head = (String)keys2[i];
	    		if (c.value.getHeadList().contains(unfolded_head.split("#")[0]))
	    		{
	    			Constraint unfolded_c = new Constraint();
	          
	    			unfolded_c.setConstraintType(c.value.isPositiveConstraint());
	    			unfolded_c.setPathConstraint(c.value.isPathConstraint());
	    			unfolded_c.addHead(unfolded_head);
	    			for (int j = 0; j < result.map.allocated.length; j++) {
	    				if (result.map.allocated[j] != false)
	    				{
	    					String unfolded_body = (String)keys2[j];
	    					if (c.value.getBodyList().contains(unfolded_body.split("#")[0]))
	    					{
	    						unfolded_c.addBody(unfolded_body);
	    						vincoli.forbidden.add(new Forbidden(unfolded_body, unfolded_head));
	    					}
	    				}
	    			}
	    			vincoli.negatiUnfolded.add(unfolded_c);
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
