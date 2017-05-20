/*     */ package edu.uci.ics.jung.visualization.util;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.EdgeIndexFunction;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections15.Predicate;
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
/*     */ public class PredicatedParallelEdgeIndexFunction<V, E>
/*     */   implements EdgeIndexFunction<V, E>
/*     */ {
/*  36 */   protected Map<E, Integer> edge_index = new HashMap();
/*     */   
/*     */   protected Predicate<E> predicate;
/*     */   
/*     */ 
/*     */   public static <V, E> PredicatedParallelEdgeIndexFunction<V, E> getInstance()
/*     */   {
/*  43 */     return new PredicatedParallelEdgeIndexFunction();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getIndex(Graph<V, E> graph, E e)
/*     */   {
/*  52 */     if (this.predicate.evaluate(e)) {
/*  53 */       return 0;
/*     */     }
/*  55 */     Integer index = (Integer)this.edge_index.get(e);
/*  56 */     if (index == null) {
/*  57 */       Pair<V> endpoints = graph.getEndpoints(e);
/*  58 */       V u = endpoints.getFirst();
/*  59 */       V v = endpoints.getSecond();
/*  60 */       if (u.equals(v)) {
/*  61 */         index = Integer.valueOf(getIndex(graph, e, v));
/*     */       } else {
/*  63 */         index = Integer.valueOf(getIndex(graph, e, u, v));
/*     */       }
/*     */     }
/*  66 */     return index.intValue();
/*     */   }
/*     */   
/*     */   protected int getIndex(Graph<V, E> graph, E e, V v, V u) {
/*  70 */     Collection<E> commonEdgeSet = new HashSet(graph.getIncidentEdges(u));
/*  71 */     commonEdgeSet.retainAll(graph.getIncidentEdges(v));
/*  72 */     for (Iterator<E> iterator = commonEdgeSet.iterator(); iterator.hasNext();) {
/*  73 */       E edge = iterator.next();
/*  74 */       Pair<V> ep = graph.getEndpoints(edge);
/*  75 */       V first = ep.getFirst();
/*  76 */       V second = ep.getSecond();
/*     */       
/*  78 */       if (first.equals(second) == true) {
/*  79 */         iterator.remove();
/*     */       }
/*     */       
/*  82 */       if (!first.equals(v)) {
/*  83 */         iterator.remove();
/*     */       }
/*     */     }
/*  86 */     int count = 0;
/*  87 */     for (E other : commonEdgeSet) {
/*  88 */       if (!e.equals(other)) {
/*  89 */         this.edge_index.put(other, Integer.valueOf(count));
/*  90 */         count++;
/*     */       }
/*     */     }
/*  93 */     this.edge_index.put(e, Integer.valueOf(count));
/*  94 */     return count;
/*     */   }
/*     */   
/*     */   protected int getIndex(Graph<V, E> graph, E e, V v) {
/*  98 */     Collection<E> commonEdgeSet = new HashSet();
/*  99 */     for (E another : graph.getIncidentEdges(v)) {
/* 100 */       V u = graph.getOpposite(v, another);
/* 101 */       if (u.equals(v)) {
/* 102 */         commonEdgeSet.add(another);
/*     */       }
/*     */     }
/* 105 */     int count = 0;
/* 106 */     for (E other : commonEdgeSet) {
/* 107 */       if (!e.equals(other)) {
/* 108 */         this.edge_index.put(other, Integer.valueOf(count));
/* 109 */         count++;
/*     */       }
/*     */     }
/* 112 */     this.edge_index.put(e, Integer.valueOf(count));
/* 113 */     return count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Predicate<E> getPredicate()
/*     */   {
/* 120 */     return this.predicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setPredicate(Predicate<E> predicate)
/*     */   {
/* 127 */     this.predicate = predicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reset(Graph<V, E> graph, E e)
/*     */   {
/* 137 */     Pair<V> endpoints = graph.getEndpoints(e);
/* 138 */     getIndex(graph, e, endpoints.getFirst());
/* 139 */     getIndex(graph, e, endpoints.getFirst(), endpoints.getSecond());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reset()
/*     */   {
/* 148 */     this.edge_index.clear();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/util/PredicatedParallelEdgeIndexFunction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */