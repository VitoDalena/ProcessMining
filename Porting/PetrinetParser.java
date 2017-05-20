package org.processmining.plugins.rttmining;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.processmining.models.graphbased.directed.petrinet.Petrinet;
import org.processmining.models.graphbased.directed.petrinet.PetrinetNode;
import org.processmining.models.graphbased.directed.petrinet.elements.Transition;
import org.processmining.models.graphbased.directed.petrinet.impl.PetrinetFactory;
import org.processmining.plugins.petrinet.importing.tpn.ParseException;
import org.processmining.plugins.petrinet.importing.tpn.TpnParser;

import com.carrotsearch.hppc.ObjectIntOpenHashMap;
import com.carrotsearch.hppc.ObjectOpenHashSet;

public class PetrinetParser
{
	public Graph createGraphFromTPN(String filename, ObjectIntOpenHashMap<String> folded_map)
			throws IOException, ParseException
	{
		Graph g = new Graph();
    
		String INVISIBLE_EVENT_TYPE = "$invisible$";
		Petrinet petrinet = PetrinetFactory.newPetrinet(filename);
	    TpnParser parser = new TpnParser(new FileInputStream(new File(filename)));
	    
	    parser.start(petrinet);
    
	    Iterator<? extends Transition> it = petrinet.getTransitions().iterator();
    
	    HashMap<PetrinetNode, Node> hashmap = new HashMap();
	    int x;
	    while (it.hasNext())
	    {
	    	Transition t = (Transition)it.next();
	    	String s = t.getLabel();
	    	String DELIM = "\\n";
	    	System.out.println(s);
      
	    	x = s.indexOf(DELIM);
	    	if (s.contains(INVISIBLE_EVENT_TYPE))
	    	{
	    		t.setInvisible(true);
	    	}
	    	else if ((x == s.lastIndexOf(DELIM)) && (x > 0))
	    	{
	    		String s2 = s.substring(x + DELIM.length(), s.length());
        
	    		s = s.substring(0, x);
	    	}
	    	if (!t.isInvisible())
	    	{
	    		Node n = new Node(s, folded_map.get(s));
	    		g.getMap().put(n, new ObjectOpenHashSet());
        
	    		hashmap.put(t, n);
	    	}
	    }
	    it = petrinet.getTransitions().iterator();
	    while (it.hasNext())
	    {
	    	Transition t = (Transition)it.next();
	    	if (!t.isInvisible())
	    	{
    			Node n = (Node)hashmap.get(t);
    			for (Transition successor : t.getVisibleSuccessors()) {
    				if (!successor.isInvisible())
    				{
    					Node adjacent = (Node)hashmap.get(successor);
    					g.addEdge(n, adjacent, false);
    				}
    			}
	    	}
	    }
	    return g;
	}
}

