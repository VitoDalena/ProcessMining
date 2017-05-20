/*     */ package org.processmining.plugins.core;
/*     */ 
/*     */ import com.carrotsearch.hppc.ObjectArrayList;
/*     */ import com.carrotsearch.hppc.ObjectIntOpenHashMap;
/*     */ import com.carrotsearch.hppc.ObjectObjectOpenHashMap;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Date;
/*     */ import org.deckfour.xes.extension.std.XConceptExtension;
/*     */ import org.deckfour.xes.extension.std.XLifecycleExtension;
/*     */ import org.deckfour.xes.extension.std.XTimeExtension;
/*     */ import org.deckfour.xes.factory.XFactory;
/*     */ import org.deckfour.xes.factory.XFactoryNaiveImpl;
/*     */ import org.deckfour.xes.factory.XFactoryRegistry;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ import org.deckfour.xes.model.XEvent;
/*     */ import org.deckfour.xes.model.XLog;
/*     */ import org.deckfour.xes.model.XTrace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LogUnfolder
/*     */ {
/*     */   public static void aggiungiAttivitaFittizia(XLog xlog)
/*     */   {
/*  28 */     XFactory factory = (XFactory)XFactoryRegistry.instance().currentDefault();
/*     */     
/*  30 */     for (int i = 0; i < xlog.size(); i++)
/*     */     {
/*  32 */       XTrace trace = (XTrace)xlog.get(i);
/*  33 */       XEvent activity_first = (XEvent)trace.get(0);
/*  34 */       XEvent activity_last = (XEvent)trace.get(trace.size() - 1);
/*     */       
/*     */ 
/*     */ 
/*  38 */       String concept_name = activity_first.getAttributes().get("concept:name");
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*  43 */       if (concept_name.equals("_START_")) {
/*     */         break;
/*     */       }
/*     */       
/*  47 */       Date first_activity_ts = XTimeExtension.instance().extractTimestamp(activity_first);
/*     */       
/*  49 */       XEvent event_first = factory.createEvent();
/*     */       
/*  51 */       XConceptExtension.instance().assignName(event_first, "_START_");
/*  52 */       XLifecycleExtension.instance().assignTransition(event_first, "complete");
/*     */       
/*  54 */       if (first_activity_ts != null) {
/*  55 */         XTimeExtension.instance().assignTimestamp(event_first, new Date(first_activity_ts.getTime() - 10L));
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*  60 */       trace.add(0, event_first);
/*     */       
/*     */ 
/*     */ 
/*  64 */       Date last_activity_ts = XTimeExtension.instance().extractTimestamp(activity_last);
/*     */       
/*  66 */       XEvent event_last = factory.createEvent();
/*     */       
/*  68 */       XConceptExtension.instance().assignName(event_last, "_END_");
/*  69 */       XLifecycleExtension.instance().assignTransition(event_last, "complete");
/*     */       
/*  71 */       if (last_activity_ts != null) {
/*  72 */         XTimeExtension.instance().assignTimestamp(event_last, new Date(last_activity_ts.getTime() + 10L));
/*     */       }
/*  74 */       trace.add(event_last);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */     throws Exception
/*     */   {
/*  99 */     XLog log = Utils.parseLog(
/* 100 */       "E:/productRecallLogs/all_3_64k.xes", 
/* 101 */       new XFactoryNaiveImpl());
/*     */     
/* 103 */     aggiungiAttivitaFittizia(log);
/*     */     
/*     */ 
/* 106 */     Object[] array = unfold(log);
/*     */     
/* 108 */     ObjectIntOpenHashMap<String> map = (ObjectIntOpenHashMap)array[0];
/*     */     
/*     */ 
/* 111 */     ObjectObjectOpenHashMap<String, ObjectArrayList<String>> attivita_tracce = (ObjectObjectOpenHashMap)array[1];
/*     */     
/* 113 */     ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita = (ObjectObjectOpenHashMap)array[2];
/*     */     
/*     */ 
/* 116 */     System.out.println(array[3]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Object[] unfold(XLog log)
/*     */     throws Exception
/*     */   {
/* 127 */     long time = System.currentTimeMillis();
/*     */     
/* 129 */     ObjectIntOpenHashMap<String> map = new ObjectIntOpenHashMap();
/*     */     
/* 131 */     int count = 0;
/*     */     
/* 133 */     ObjectObjectOpenHashMap<String, ObjectArrayList<String>> attivita_tracce = new ObjectObjectOpenHashMap();
/*     */     
/* 135 */     ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita = new ObjectObjectOpenHashMap();
/*     */     
/*     */ 
/* 138 */     for (int i = 0; i < log.size(); i++) {
/* 139 */       XTrace trace = (XTrace)log.get(i);
/* 140 */       String traccia = trace.getAttributes().get("concept:name") + " # " + i;
/*     */       
/* 142 */       if (!traccia_attivita.containsKey(traccia)) {
/* 143 */         ObjectArrayList<String> lista = new ObjectArrayList();
/* 144 */         lista.trimToSize();
/* 145 */         traccia_attivita.put(traccia, lista);
/*     */       }
/*     */       
/* 148 */       for (XEvent activity : trace)
/*     */       {
/*     */ 
/*     */ 
/* 152 */         String nome_attivita = 
/* 153 */           activity.getAttributes().get("concept:name") + "#" + String.format("%04d", new Object[] { Integer.valueOf(0) });
/*     */         
/*     */ 
/*     */ 
/* 157 */         if (!((ObjectArrayList)traccia_attivita.get(traccia)).contains(nome_attivita)) {
/* 158 */           ((ObjectArrayList)traccia_attivita.get(traccia)).add(nome_attivita);
/*     */ 
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/*     */ 
/*     */ 
/* 166 */           int counter = -1;
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 174 */           for (int ii = ((ObjectArrayList)traccia_attivita.get(traccia)).size() - 1; ii >= 0; ii--)
/*     */           {
/* 176 */             String nome_attiv = (String)((ObjectArrayList)traccia_attivita.get(traccia)).get(ii);
/*     */             
/*     */ 
/* 179 */             String[] split = nome_attiv.split("#");
/*     */             
/* 181 */             if (split[0].equals(nome_attivita.split("#")[0]))
/*     */             {
/*     */ 
/* 184 */               counter = Integer.parseInt(split[1]) + 1;
/* 185 */               break;
/*     */             }
/*     */           }
/*     */           
/* 189 */           nome_attivita = 
/* 190 */             nome_attivita.split("#")[0] + "#" + String.format("%04d", new Object[] { Integer.valueOf(counter) });
/* 191 */           ((ObjectArrayList)traccia_attivita.get(traccia)).add(nome_attivita);
/*     */         }
/*     */         
/*     */ 
/* 195 */         if (!attivita_tracce.containsKey(nome_attivita)) {
/* 196 */           ObjectArrayList<String> lista_tracce = new ObjectArrayList();
/*     */           
/* 198 */           lista_tracce.add(traccia);
/* 199 */           attivita_tracce.put(nome_attivita, lista_tracce);
/*     */         }
/*     */         else
/*     */         {
/* 203 */           ((ObjectArrayList)attivita_tracce.get(nome_attivita)).add(traccia);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 209 */     Object[] keys = attivita_tracce.keys;
/*     */     
/* 211 */     boolean[] states = attivita_tracce.allocated;
/*     */     
/* 213 */     for (int i = 0; i < states.length; i++)
/*     */     {
/* 215 */       if (states[i] != 0)
/*     */       {
/* 217 */         if (!map.containsKey((String)keys[i])) {
/* 218 */           map.put((String)keys[i], count);
/* 219 */           count++;
/*     */         } else {
/* 221 */           System.out.println("PROBLEMA!");
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 249 */     time = System.currentTimeMillis() - time;
/*     */     
/* 251 */     return new Object[] { map, attivita_tracce, traccia_attivita, Long.valueOf(time) };
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/plugins/core/LogUnfolder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */