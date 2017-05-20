/*     */ package org.processmining.plugins.core;
/*     */ 
/*     */ import com.carrotsearch.hppc.ObjectArrayList;
/*     */ import com.carrotsearch.hppc.ObjectIntOpenHashMap;
/*     */ import com.carrotsearch.hppc.ObjectObjectOpenHashMap;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import org.deckfour.xes.extension.std.XConceptExtension;
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
/*     */ public class LogUnfolderV2
/*     */ {
/*     */   public static void aggiungiAttivitaFittizia(XLog xlog)
/*     */   {
/*  33 */     XFactory factory = (XFactory)XFactoryRegistry.instance().currentDefault();
/*     */     
/*  35 */     for (int i = 0; i < xlog.size(); i++)
/*     */     {
/*  37 */       XTrace trace = (XTrace)xlog.get(i);
/*  38 */       XEvent activity_first = (XEvent)trace.get(0);
/*  39 */       XEvent activity_last = (XEvent)trace.get(trace.size() - 1);
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
/*  50 */       Date first_activity_ts = XTimeExtension.instance()
/*  51 */         .extractTimestamp(activity_first);
/*     */       
/*  53 */       XEvent event_first = factory.createEvent();
/*     */       
/*  55 */       XConceptExtension.instance().assignName(event_first, "_START_");
/*     */       
/*  57 */       XTimeExtension.instance().assignTimestamp(event_first, 
/*  58 */         new Date(first_activity_ts.getTime() - 10L));
/*     */       
/*     */ 
/*     */ 
/*  62 */       trace.add(0, event_first);
/*     */       
/*     */ 
/*     */ 
/*  66 */       Date last_activity_ts = XTimeExtension.instance().extractTimestamp(
/*  67 */         activity_last);
/*     */       
/*  69 */       XEvent event_last = factory.createEvent();
/*     */       
/*  71 */       XConceptExtension.instance().assignName(event_last, "_END_");
/*     */       
/*  73 */       XTimeExtension.instance().assignTimestamp(event_last, 
/*  74 */         new Date(last_activity_ts.getTime() + 10L));
/*     */       
/*  76 */       trace.add(event_last);
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
/* 110 */     XLog log = Utils.parseLog(
/* 111 */       "/home/frank/Desktop/repair_loop.xes", 
/* 112 */       new XFactoryNaiveImpl());
/*     */     
/* 114 */     aggiungiAttivitaFittizia(log);
/*     */     
/*     */ 
/* 117 */     Object[] array = unfold(log);
/*     */     
/* 119 */     Map<String, Integer> map = (Map)array[0];
/*     */     
/*     */ 
/* 122 */     Map<String, LinkedList<String>> attivita_tracce = (Map)array[1];
/*     */     
/* 124 */     Map<String, LinkedList<String>> traccia_attivita = (Map)array[2];
/*     */     
/*     */ 
/* 127 */     Iterator localIterator1 = attivita_tracce.entrySet().iterator();
/* 126 */     while (localIterator1.hasNext()) {
/* 127 */       Map.Entry<String, LinkedList<String>> entry = (Map.Entry)localIterator1.next();
/* 128 */       String key = (String)entry.getKey();
/* 129 */       LinkedList<String> value = (LinkedList)entry.getValue();
/*     */       
/* 131 */       System.out.println(key + " => ");
/* 132 */       for (String s : value)
/* 133 */         System.out.print(s + " ; ");
/* 134 */       System.out.println();
/*     */     }
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
/* 146 */     Map<String, Integer> map = new TreeMap();
/* 147 */     Map<String, Integer> mapOri = new TreeMap();
/*     */     
/* 149 */     int count = 0;
/* 150 */     int foldedCount = 0;
/*     */     
/* 152 */     Map<String, LinkedList<String>> attivita_tracce = new TreeMap();
/* 153 */     Map<String, LinkedList<String>> attivita_tracceOri = new TreeMap();
/*     */     
/* 155 */     Map<String, LinkedList<String>> traccia_attivita = new TreeMap();
/* 156 */     Map<String, LinkedList<String>> traccia_attivitaOri = new TreeMap();
/*     */     
/* 158 */     Random r = new Random();
/*     */     
/* 160 */     for (int i = 0; i < log.size(); i++) {
/* 161 */       trace = (XTrace)log.get(i);
/* 162 */       String traccia = trace.getAttributes().get("concept:name") + " # " + i;
/*     */       
/* 164 */       if (!traccia_attivita.containsKey(traccia)) {
/* 165 */         traccia_attivita.put(traccia, new LinkedList());
/*     */       }
/* 167 */       if (!traccia_attivitaOri.containsKey(traccia)) {
/* 168 */         traccia_attivitaOri.put(traccia, new LinkedList());
/*     */       }
/* 170 */       for (XEvent activity : trace)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 176 */         String nome_attivita = 
/* 177 */           activity.getAttributes().get("concept:name");
/*     */         
/*     */ 
/* 180 */         if (!mapOri.containsKey(nome_attivita)) {
/* 181 */           mapOri.put(nome_attivita, Integer.valueOf(foldedCount));
/* 182 */           foldedCount++;
/*     */         }
/*     */         
/*     */ 
/* 186 */         if (!attivita_tracceOri.containsKey(nome_attivita)) {
/* 187 */           LinkedList<String> lista_tracce = new LinkedList();
/* 188 */           lista_tracce.add(traccia);
/* 189 */           attivita_tracceOri.put(nome_attivita, lista_tracce);
/* 190 */         } else if (!((LinkedList)attivita_tracceOri.get(nome_attivita)).contains(traccia)) {
/* 191 */           ((LinkedList)attivita_tracceOri.get(nome_attivita)).add(traccia);
/*     */         }
/* 193 */         ((LinkedList)traccia_attivitaOri.get(traccia)).add(nome_attivita);
/*     */         
/*     */ 
/*     */ 
/* 197 */         nome_attivita = nome_attivita + "#" + String.format("%04d", new Object[] { Integer.valueOf(0) });
/*     */         
/*     */ 
/*     */ 
/* 201 */         if (!((LinkedList)traccia_attivita.get(traccia)).contains(nome_attivita)) {
/* 202 */           ((LinkedList)traccia_attivita.get(traccia)).add(nome_attivita);
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/*     */ 
/* 208 */           Iterator<String> it = ((LinkedList)traccia_attivita.get(traccia))
/* 209 */             .descendingIterator();
/*     */           
/* 211 */           int counter = -1;
/*     */           
/* 213 */           while (it.hasNext())
/*     */           {
/* 215 */             String nome_attiv = (String)it.next();
/*     */             
/*     */ 
/* 218 */             String[] split = nome_attiv.split("#");
/*     */             
/* 220 */             if (split[0].equals(nome_attivita.split("#")[0]))
/*     */             {
/*     */ 
/* 223 */               counter = Integer.parseInt(split[1]) + 1;
/* 224 */               break;
/*     */             }
/*     */           }
/*     */           
/* 228 */           nome_attivita = 
/* 229 */             nome_attivita.split("#")[0] + "#" + String.format("%04d", new Object[] { Integer.valueOf(counter) });
/* 230 */           ((LinkedList)traccia_attivita.get(traccia)).add(nome_attivita);
/*     */         }
/*     */         
/*     */ 
/* 234 */         if (!attivita_tracce.containsKey(nome_attivita)) {
/* 235 */           LinkedList<String> lista_tracce = new LinkedList();
/* 236 */           lista_tracce.add(traccia);
/* 237 */           attivita_tracce.put(nome_attivita, lista_tracce);
/*     */         }
/* 239 */         else if (!((LinkedList)attivita_tracce.get(nome_attivita)).contains(traccia)) {
/* 240 */           ((LinkedList)attivita_tracce.get(nome_attivita)).add(traccia);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 247 */     XTrace trace = attivita_tracce.entrySet().iterator();
/* 246 */     while (trace.hasNext()) {
/* 247 */       Map.Entry<String, LinkedList<String>> entry = (Map.Entry)trace.next();
/* 248 */       String key = (String)entry.getKey();
/*     */       
/* 250 */       if (!map.containsKey(key)) {
/* 251 */         map.put(key, Integer.valueOf(count));
/* 252 */         count++;
/*     */       } else {
/* 254 */         System.out.println("PROBLEMA!");
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
/* 268 */     return new Object[] { map, attivita_tracce, traccia_attivita, mapOri, attivita_tracceOri, traccia_attivitaOri };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Object[] buildDataStructure(XLog log)
/*     */     throws Exception
/*     */   {
/* 281 */     long time = System.currentTimeMillis();
/*     */     
/* 283 */     ObjectIntOpenHashMap<String> map = new ObjectIntOpenHashMap();
/*     */     
/* 285 */     int count = 0;
/*     */     
/* 287 */     ObjectObjectOpenHashMap<String, ObjectArrayList<String>> attivita_tracce = new ObjectObjectOpenHashMap();
/*     */     
/* 289 */     ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita = new ObjectObjectOpenHashMap();
/*     */     
/*     */ 
/* 292 */     for (int i = 0; i < log.size(); i++) {
/* 293 */       XTrace trace = (XTrace)log.get(i);
/* 294 */       String traccia = trace.getAttributes().get("concept:name") + " # " + i;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 299 */       if (!traccia_attivita.containsKey(traccia)) {
/* 300 */         ObjectArrayList<String> lista = new ObjectArrayList();
/* 301 */         lista.trimToSize();
/* 302 */         traccia_attivita.put(traccia, lista);
/*     */       }
/*     */       
/*     */ 
/* 306 */       for (XEvent activity : trace)
/*     */       {
/*     */ 
/*     */ 
/* 310 */         String nome_attivita = activity.getAttributes().get("concept:name");
/*     */         
/* 312 */         if (!map.containsKey(nome_attivita)) {
/* 313 */           map.put(nome_attivita, count);
/* 314 */           count++;
/*     */         }
/*     */         
/*     */ 
/* 318 */         if (!attivita_tracce.containsKey(nome_attivita)) {
/* 319 */           ObjectArrayList<String> lista_tracce = new ObjectArrayList();
/* 320 */           lista_tracce.add(traccia);
/* 321 */           attivita_tracce.put(nome_attivita, lista_tracce);
/* 322 */         } else if (!((ObjectArrayList)attivita_tracce.get(nome_attivita)).contains(traccia)) {
/* 323 */           ((ObjectArrayList)attivita_tracce.get(nome_attivita)).add(traccia);
/*     */         }
/*     */         
/*     */ 
/* 327 */         ((ObjectArrayList)traccia_attivita.get(traccia)).add(nome_attivita);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 333 */     time = System.currentTimeMillis() - time;
/*     */     
/* 335 */     return new Object[] { map, attivita_tracce, traccia_attivita, Long.valueOf(time) };
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/plugins/core/LogUnfolderV2.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */