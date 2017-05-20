/*     */ package edu.uci.ics.jung.graph.util;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
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
/*     */ public class IncidentEdgeIndexFunction<V, E>
/*     */   implements EdgeIndexFunction<V, E>
/*     */ {
/*  29 */   protected Map<E, Integer> edge_index = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <V, E> IncidentEdgeIndexFunction<V, E> getInstance()
/*     */   {
/*  40 */     return new IncidentEdgeIndexFunction();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getIndex(Graph<V, E> graph, E e)
/*     */   {
/*  50 */     Integer index = (Integer)this.edge_index.get(e);
/*  51 */     if (index == null) {
/*  52 */       Pair<V> endpoints = graph.getEndpoints(e);
/*  53 */       V u = endpoints.getFirst();
/*  54 */       V v = endpoints.getSecond();
/*  55 */       if (u.equals(v)) {
/*  56 */         index = Integer.valueOf(getIndex(graph, e, v));
/*     */       } else {
/*  58 */         index = Integer.valueOf(getIndex(graph, e, u, v));
/*     */       }
/*     */     }
/*  61 */     return index.intValue();
/*     */   }
/*     */   
/*     */   protected int getIndex(Graph<V, E> graph, E e, V u, V v) {
/*  65 */     Collection<E> commonEdgeSet = new HashSet(graph.getIncidentEdges(u));
/*  66 */     int count = 0;
/*  67 */     for (E other : commonEdgeSet) {
/*  68 */       if (!e.equals(other)) {
/*  69 */         this.edge_index.put(other, Integer.valueOf(count));
/*  70 */         count++;
/*     */       }
/*     */     }
/*  73 */     this.edge_index.put(e, Integer.valueOf(count));
/*  74 */     return count;
/*     */   }
/*     */   
/*     */   protected int getIndex(Graph<V, E> graph, E e, V v) {
/*  78 */     Collection<E> commonEdgeSet = new HashSet();
/*  79 */     for (E another : graph.getIncidentEdges(v)) {
/*  80 */       V u = graph.getOpposite(v, another);
/*  81 */       if (u.equals(v)) {
/*  82 */         commonEdgeSet.add(another);
/*     */       }
/*     */     }
/*  85 */     int count = 0;
/*  86 */     for (E other : commonEdgeSet) {
/*  87 */       if (!e.equals(other)) {
/*  88 */         this.edge_index.put(other, Integer.valueOf(count));
/*  89 */         count++;
/*     */       }
/*     */     }
/*  92 */     this.edge_index.put(e, Integer.valueOf(count));
/*  93 */     return count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reset(Graph<V, E> graph, E e)
/*     */   {
/* 104 */     Pair<V> endpoints = graph.getEndpoints(e);
/* 105 */     getIndex(graph, e, endpoints.getFirst());
/* 106 */     getIndex(graph, e, endpoints.getFirst(), endpoints.getSecond());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reset()
/*     */   {
/* 115 */     this.edge_index.clear();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/util/IncidentEdgeIndexFunction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */