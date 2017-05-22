package org.processmining.plugins.cnmining;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import org.deckfour.xes.extension.std.XConceptExtension;
import org.deckfour.xes.extension.std.XTimeExtension;
import org.deckfour.xes.factory.XFactory;
import org.deckfour.xes.factory.XFactoryNaiveImpl;
import org.deckfour.xes.factory.XFactoryRegistry;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;

import com.carrotsearch.hppc.ObjectArrayList;
import com.carrotsearch.hppc.ObjectIntOpenHashMap;
import com.carrotsearch.hppc.ObjectObjectOpenHashMap;

public class LogUnfolderV2
{
	public static void aggiungiAttivitaFittizia(XLog xlog)
	{
		XFactory factory = XFactoryRegistry.instance().currentDefault();
		for (int i = 0; i < xlog.size(); i++)
		{
			XTrace trace = xlog.get(i);
			XEvent activity_first = trace.get(0);
			XEvent activity_last = trace.get(trace.size() - 1);
      
			Date first_activity_ts = XTimeExtension.instance().extractTimestamp(activity_first);
      
			XEvent event_first = factory.createEvent();
      
			XConceptExtension.instance().assignName(event_first, "_START_");
      
			XTimeExtension.instance().assignTimestamp(
				event_first, 
				new Date(first_activity_ts.getTime() - 10L)
			);
      
			trace.add(0, event_first);
      
			Date last_activity_ts = XTimeExtension.instance().extractTimestamp(
				activity_last
			);
      
			XEvent event_last = factory.createEvent();
      
			XConceptExtension.instance().assignName(event_last, "_END_");
      
			XTimeExtension.instance().assignTimestamp(
				event_last, 
				new Date(last_activity_ts.getTime() + 10L)
			);
      
			trace.add(event_last);
		}
	}
  
	public static void main(String[] args) throws Exception
	{
		XLog log = Utils.parseLog(
			"/home/frank/Desktop/repair_loop.xes", 
			new XFactoryNaiveImpl()
		);
    
		aggiungiAttivitaFittizia(log);
    
		Object[] array = unfold(log);
    
		Map<String, Integer> map = (Map<String, Integer>)array[0];
    
		Map<String, LinkedList<String>> attivita_tracce = (Map<String, LinkedList<String>>)array[1];
    
		Map<String, LinkedList<String>> traccia_attivita = (Map<String, LinkedList<String>>)array[2];
    
		Iterator<?> localIterator1 = attivita_tracce.entrySet().iterator();
		while (localIterator1.hasNext())
		{
			Map.Entry<String, LinkedList<String>> entry = (Map.Entry)localIterator1.next();
			String key = entry.getKey();
			LinkedList<String> value = entry.getValue();
      
			System.out.println(key + " => ");
			for (String s : value) {
				System.out.print(s + " ; ");
			}
			System.out.println();
		}
	}
  
	public static Object[] unfold(XLog log) throws Exception
	{
		Map<String, Integer> map = new TreeMap<String, Integer>();
		Map<String, Integer> mapOri = new TreeMap<String, Integer>();
    
		int count = 0;
		int foldedCount = 0;
    
		Map<String, LinkedList<String>> attivita_tracce = new TreeMap<String, LinkedList<String>>();
		Map<String, LinkedList<String>> attivita_tracceOri = new TreeMap<String, LinkedList<String>>();
    
		Map<String, LinkedList<String>> traccia_attivita = new TreeMap<String, LinkedList<String>>();
		Map<String, LinkedList<String>> traccia_attivitaOri = new TreeMap<String, LinkedList<String>>();
    
		Random r = new Random();
		for (int i = 0; i < log.size(); i++)
		{
			// TODO: modifica messo tipo XTrace
			XTrace trace = log.get(i);
			String traccia = trace.getAttributes().get("concept:name") + " # " + i;
			if (!traccia_attivita.containsKey(traccia)) {
				traccia_attivita.put(traccia, new LinkedList<String>());
			}
			if (!traccia_attivitaOri.containsKey(traccia)) {
				traccia_attivitaOri.put(traccia, new LinkedList<String>());
			}
			for (XEvent activity : trace)
			{
				// TODO: aggiunto toString
				String nome_attivita = activity.getAttributes().get("concept:name").toString();
				if (!mapOri.containsKey(nome_attivita))
				{
					mapOri.put(nome_attivita, Integer.valueOf(foldedCount));
					foldedCount++;
				}
				if (!attivita_tracceOri.containsKey(nome_attivita))
				{
					LinkedList<String> lista_tracce = new LinkedList<String>();
					lista_tracce.add(traccia);
					attivita_tracceOri.put(nome_attivita, lista_tracce);
				}
				else if (!((LinkedList<?>)attivita_tracceOri.get(nome_attivita)).contains(traccia))
				{
					((LinkedList<String>)attivita_tracceOri.get(nome_attivita)).add(traccia);
				}
				((LinkedList<String>)traccia_attivitaOri.get(traccia)).add(nome_attivita);
        
				nome_attivita = nome_attivita + "#" + String.format("%04d", new Object[] { Integer.valueOf(0) });
				if (!((LinkedList<?>)traccia_attivita.get(traccia)).contains(nome_attivita))
				{
					((LinkedList<String>)traccia_attivita.get(traccia)).add(nome_attivita);
				}
				else
				{
					Iterator<String> it = ((LinkedList<String>)traccia_attivita.get(traccia)).descendingIterator();
          
					int counter = -1;
					while (it.hasNext())
					{
						String nome_attiv = it.next();
            
						String[] split = nome_attiv.split("#");
						if (split[0].equals(nome_attivita.split("#")[0]))
						{
							counter = Integer.parseInt(split[1]) + 1;
							break;
						}
					}
					nome_attivita = 
							nome_attivita.split("#")[0] + "#" + String.format("%04d", new Object[] { Integer.valueOf(counter) });
					((LinkedList<String>)traccia_attivita.get(traccia)).add(nome_attivita);
				}
				if (!attivita_tracce.containsKey(nome_attivita))
				{
					LinkedList<String> lista_tracce = new LinkedList<String>();
					lista_tracce.add(traccia);
					attivita_tracce.put(nome_attivita, lista_tracce);
				}
				else if (!((LinkedList<?>)attivita_tracce.get(nome_attivita)).contains(traccia))
				{
					((LinkedList<String>)attivita_tracce.get(nome_attivita)).add(traccia);
				}
			}
		}
		
		//TODO: aggiunto il tipo di variabile
		// spero sia giusto
		Iterator<Entry<String, LinkedList<String>>> trace1 = attivita_tracce.entrySet().iterator();
		while (trace1.hasNext())
		{
			Map.Entry<String, LinkedList<String>> entry = trace1.next();
			String key = entry.getKey();
			if (!map.containsKey(key))
			{
				map.put(key, Integer.valueOf(count));
				count++;
			}
			else
			{
				System.out.println("PROBLEMA!");
			}
		}
		return new Object[] { map, attivita_tracce, traccia_attivita, mapOri, attivita_tracceOri, traccia_attivitaOri };
	}
  
	public static Object[] buildDataStructure(XLog log) throws Exception
	{
		long time = System.currentTimeMillis();
    
		ObjectIntOpenHashMap<String> map = new ObjectIntOpenHashMap<String>();
    
		int count = 0;
    
		ObjectObjectOpenHashMap<String, ObjectArrayList<String>> attivita_tracce = new ObjectObjectOpenHashMap<String, ObjectArrayList<String>>();
    
		ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita = new ObjectObjectOpenHashMap<String, ObjectArrayList<String>>();
		for (int i = 0; i < log.size(); i++)
		{
			XTrace trace = log.get(i);
			String traccia = trace.getAttributes().get("concept:name") + " # " + i;
			if (!traccia_attivita.containsKey(traccia))
			{
				ObjectArrayList<String> lista = new ObjectArrayList<String>();
				lista.trimToSize();
				traccia_attivita.put(traccia, lista);
			}
			for (XEvent activity : trace)
			{
				// TODO: aggiunto toString
				String nome_attivita = activity.getAttributes().get("concept:name").toString();
				if (!map.containsKey(nome_attivita))
				{
					map.put(nome_attivita, count);
					count++;
				}
				if (!attivita_tracce.containsKey(nome_attivita))
				{
					ObjectArrayList<String> lista_tracce = new ObjectArrayList<String>();
					lista_tracce.add(traccia);
					attivita_tracce.put(nome_attivita, lista_tracce);
				}
				else if (!((ObjectArrayList<String>)attivita_tracce.get(nome_attivita)).contains(traccia))
				{
					((ObjectArrayList<String>)attivita_tracce.get(nome_attivita)).add(traccia);
				}
				((ObjectArrayList<String>)traccia_attivita.get(traccia)).add(nome_attivita);
			}
		}
		time = System.currentTimeMillis() - time;
    
		return new Object[] { map, attivita_tracce, traccia_attivita, Long.valueOf(time) };
	}
}
