/*     */ package org.processmining.plugins.core;
/*     */ 
/*     */ import com.carrotsearch.hppc.ObjectIntOpenHashMap;
/*     */ import com.carrotsearch.hppc.ObjectObjectOpenHashMap;
/*     */ import com.carrotsearch.hppc.ObjectOpenHashSet;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import org.processmining.models.graphbased.directed.petrinet.Petrinet;
/*     */ import org.processmining.models.graphbased.directed.petrinet.PetrinetNode;
/*     */ import org.processmining.models.graphbased.directed.petrinet.elements.Transition;
/*     */ import org.processmining.models.graphbased.directed.petrinet.impl.PetrinetFactory;
/*     */ import org.processmining.plugins.petrinet.importing.tpn.ParseException;
/*     */ import org.processmining.plugins.petrinet.importing.tpn.TpnParser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PetrinetParser
/*     */ {
/*     */   public Graph createGraphFromTPN(String filename, ObjectIntOpenHashMap<String> folded_map)
/*     */     throws IOException, ParseException
/*     */   {
/*  29 */     Graph g = new Graph();
/*     */     
/*  31 */     String INVISIBLE_EVENT_TYPE = "$invisible$";
/*  32 */     Petrinet petrinet = PetrinetFactory.newPetrinet(filename);
/*  33 */     TpnParser parser = new TpnParser(new FileInputStream(new File(filename)));
/*     */     
/*  35 */     parser.start(petrinet);
/*     */     
/*  37 */     Iterator<? extends Transition> it = petrinet.getTransitions().iterator();
/*     */     
/*  39 */     HashMap<PetrinetNode, Node> hashmap = new HashMap();
/*     */     int x;
/*  41 */     while (it.hasNext()) {
/*  42 */       Transition t = (Transition)it.next();
/*  43 */       String s = t.getLabel();
/*  44 */       String DELIM = "\\n";
/*  45 */       System.out.println(s);
/*     */       
/*  47 */       x = s.indexOf(DELIM);
/*     */       
/*  49 */       if (s.contains(INVISIBLE_EVENT_TYPE)) {
/*  50 */         t.setInvisible(true);
/*  51 */       } else if ((x == s.lastIndexOf(DELIM)) && (x > 0))
/*     */       {
/*  53 */         String s2 = s.substring(x + DELIM.length(), s.length());
/*     */         
/*  55 */         s = s.substring(0, x);
/*     */       }
/*     */       
/*     */ 
/*  59 */       if (!t.isInvisible())
/*     */       {
/*  61 */         Node n = new Node(s, folded_map.get(s));
/*  62 */         g.getMap().put(n, new ObjectOpenHashSet());
/*     */         
/*  64 */         hashmap.put(t, n);
/*     */       }
/*     */     }
/*     */     
/*  68 */     it = petrinet.getTransitions().iterator();
/*     */     
/*  70 */     while (it.hasNext()) {
/*  71 */       Transition t = (Transition)it.next();
/*  72 */       if (!t.isInvisible()) {
/*  73 */         Node n = (Node)hashmap.get(t);
/*  74 */         for (Transition successor : t.getVisibleSuccessors()) {
/*  75 */           if (!successor.isInvisible()) {
/*  76 */             Node adjacent = (Node)hashmap.get(successor);
/*  77 */             g.addEdge(n, adjacent, false);
/*     */           }
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
/* 101 */     return g;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/plugins/core/PetrinetParser.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */