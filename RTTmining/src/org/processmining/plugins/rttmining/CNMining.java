package org.processmining.plugins.rttmining;

import java.sql.Date;

import org.deckfour.xes.extension.std.XConceptExtension;
import org.deckfour.xes.extension.std.XLifecycleExtension;
import org.deckfour.xes.extension.std.XTimeExtension;
import org.deckfour.xes.factory.XFactory;
import org.deckfour.xes.factory.XFactoryRegistry;
import org.deckfour.xes.model.XAttribute;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;

import com.carrotsearch.hppc.ObjectArrayList;
import com.carrotsearch.hppc.ObjectIntOpenHashMap;
import com.carrotsearch.hppc.ObjectObjectOpenHashMap;

public class CNMining {
	
	public void aggiungiAttivitaFittizia(XLog xlog)
	{
		XFactory factory = XFactoryRegistry.instance().currentDefault();
	    for (int i = 0; i < xlog.size(); i++)
	    {
	    	XTrace trace = xlog.get(i);
	    	XEvent activity_first = trace.get(0);
	    	XEvent activity_last = trace.get(trace.size() - 1);
	      
	    	XAttribute concept_name = activity_first.getAttributes().get("concept:name");
	    	if (concept_name.equals("_START_")) {
	    		break;
	    	}
	    	Date first_activity_ts = (Date) XTimeExtension.instance().extractTimestamp(activity_first);
	      
	    	XEvent event_first = factory.createEvent();
	      
	    	XConceptExtension.instance().assignName(event_first, "_START_");
	    	XLifecycleExtension.instance().assignTransition(event_first, "complete");
	      	if (first_activity_ts != null) {
	      		XTimeExtension.instance().assignTimestamp(event_first, new Date(first_activity_ts.getTime() - 10L));
	      	}
	      	trace.add(0, event_first);
	      
	      	Date last_activity_ts = (Date) XTimeExtension.instance().extractTimestamp(activity_last);
	      
	      	XEvent event_last = factory.createEvent();
	      
	      	XConceptExtension.instance().assignName(event_last, "_END_");
	      	XLifecycleExtension.instance().assignTransition(event_last, "complete");
	      	if (last_activity_ts != null) {
	      		XTimeExtension.instance().assignTimestamp(event_last, new Date(last_activity_ts.getTime() + 10L));
	      	}
	      	trace.add(event_last);
	    }
	}
	
	public double[][] calcoloMatriceDeiCausalScore(XLog log, ObjectIntOpenHashMap<String> map, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita, double ff)
	{
		return null;
	}
}
