package org.processmining.plugins.rttmining;

import java.util.Iterator;

import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;

import com.carrotsearch.hppc.ObjectArrayList;
import com.carrotsearch.hppc.ObjectIntOpenHashMap;
import com.carrotsearch.hppc.ObjectObjectOpenHashMap;
import com.carrotsearch.hppc.cursors.ObjectCursor;

public class ConsoleDebugger {
	
	public static void logVincolo(ObjectArrayList<Constraint> vincolo, String name){		
		System.out.println("\n");
		System.out.println("Debug Vincolo:" + name);
		
		Iterator<ObjectCursor<Constraint>> i = vincolo.iterator();
		while(i.hasNext()){
			ObjectCursor<Constraint> c = i.next();
			System.out.println("ciao");
			System.out.println(c.value.toString());
		}
		System.out.println("\n");
	}
	
	public static void logForbidden(ObjectArrayList<Forbidden> vincolo, String name){
		System.out.println("\n");
		System.out.println("Debug Vincolo:" + name);
		
		Iterator<ObjectCursor<Forbidden>> i = vincolo.iterator();
		while(i.hasNext()){
			ObjectCursor<Forbidden> c = i.next();
			
			System.out.println(c.value.toString());
		}
		System.out.println("\n");
	}
	
	public static void logMap(ObjectIntOpenHashMap<String> map){
		
	}
	
	public static void logAttivitaTracce(ObjectObjectOpenHashMap<String, ObjectArrayList<String>> attivita_tracce){
		
	}
	
	public static void logTracciaAttivita(ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita){
		
	}
	
	public static void logAttivita(XLog xlog){
		System.out.println("Log Attività");
		for (int i = 0; i < xlog.size(); i++)
		{
			XTrace trace = xlog.get(i);
			System.out.println(trace.toString());
		}
	}
	
	public static void logMatrice(double[][] m, String name){
		System.out.println("\nLog Matrice: " + name);
		for (int i = 0; i < m.length; i++) {
			String row = "";
    		for (int j = 0; j < m.length; j++) {
    			row += " " + m[i][j];
    		}
    		System.out.println(row);    		
    	}
	}
	
}
