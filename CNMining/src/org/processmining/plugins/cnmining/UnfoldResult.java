package org.processmining.plugins.cnmining;

import com.carrotsearch.hppc.ObjectArrayList;
import com.carrotsearch.hppc.ObjectIntOpenHashMap;
import com.carrotsearch.hppc.ObjectObjectOpenHashMap;

public class UnfoldResult {
	public ObjectIntOpenHashMap<String> map;
	public ObjectObjectOpenHashMap<String, ObjectArrayList<String>> attivita_tracce;	    
	public ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita;		
	
	public UnfoldResult(){
		map = new ObjectIntOpenHashMap<String>();	    
		attivita_tracce = new ObjectObjectOpenHashMap<String, ObjectArrayList<String>>();
		traccia_attivita = new ObjectObjectOpenHashMap<String, ObjectArrayList<String>>();
	}
}
