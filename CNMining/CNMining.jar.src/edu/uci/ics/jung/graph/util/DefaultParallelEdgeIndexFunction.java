/*     */ package edu.uci.ics.jung.graph.util;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultParallelEdgeIndexFunction<V, E>
/*     */   implements EdgeIndexFunction<V, E>
/*     */ {
/*  38 */   protected Map<Context<Graph<V, E>, E>, Integer> edge_index = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <V, E> DefaultParallelEdgeIndexFunction<V, E> getInstance()
/*     */   {
/*  49 */     return new DefaultParallelEdgeIndexFunction();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getIndex(Graph<V, E> graph, E e)
/*     */   {
/*  60 */     Integer index = (Integer)this.edge_index.get(Context.getInstance(graph, e));
/*     */     
/*  62 */     if (index == null) {
/*  63 */       Pair<V> endpoints = graph.getEndpoints(e);
/*  64 */       V u = endpoints.getFirst();
/*  65 */       V v = endpoints.getSecond();
/*  66 */       if (u.equals(v)) {
/*  67 */         index = Integer.valueOf(getIndex(graph, e, v));
/*     */       } else {
/*  69 */         index = Integer.valueOf(getIndex(graph, e, u, v));
/*     */       }
/*     */     }
/*  72 */     return index.intValue();
/*     */   }
/*     */   
/*     */   protected int getIndex(Graph<V, E> graph, E e, V v, V u) {
/*  76 */     Collection<E> commonEdgeSet = new HashSet(graph.getIncidentEdges(u));
/*  77 */     commonEdgeSet.retainAll(graph.getIncidentEdges(v));
/*  78 */     for (Iterator<E> iterator = commonEdgeSet.iterator(); iterator.hasNext();) {
/*  79 */       E edge = iterator.next();
/*  80 */       Pair<V> ep = graph.getEndpoints(edge);
/*  81 */       V first = ep.getFirst();
/*  82 */       V second = ep.getSecond();
/*     */       
/*  84 */       if (first.equals(second) == true) {
/*  85 */         iterator.remove();
/*     */       }
/*     */       
/*  88 */       if (!first.equals(v)) {
/*  89 */         iterator.remove();
/*     */       }
/*     */     }
/*  92 */     int count = 0;
/*  93 */     for (E other : commonEdgeSet) {
/*  94 */       if (!e.equals(other)) {
/*  95 */         this.edge_index.put(Context.getInstance(graph, other), Integer.valueOf(count));
/*  96 */         count++;
/*     */       }
/*     */     }
/*  99 */     this.edge_index.put(Context.getInstance(graph, e), Integer.valueOf(count));
/* 100 */     return count;
/*     */   }
/*     */   
/*     */   protected int getIndex(Graph<V, E> graph, E e, V v) {
/* 104 */     Collection<E> commonEdgeSet = new HashSet();
/* 105 */     for (E another : graph.getIncidentEdges(v)) {
/* 106 */       V u = graph.getOpposite(v, another);
/* 107 */       if (u.equals(v)) {
/* 108 */         commonEdgeSet.add(another);
/*     */       }
/*     */     }
/* 111 */     int count = 0;
/* 112 */     for (E other : commonEdgeSet) {
/* 113 */       if (!e.equals(other)) {
/* 114 */         this.edge_index.put(Context.getInstance(graph, other), Integer.valueOf(count));
/* 115 */         count++;
/*     */       }
/*     */     }
/* 118 */     this.edge_index.put(Context.getInstance(graph, e), Integer.valueOf(count));
/* 119 */     return count;
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
/* 130 */     Pair<V> endpoints = graph.getEndpoints(e);
/* 131 */     getIndex(graph, e, endpoints.getFirst());
/* 132 */     getIndex(graph, e, endpoints.getFirst(), endpoints.getSecond());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reset()
/*     */   {
/* 141 */     this.edge_index.clear();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/util/DefaultParallelEdgeIndexFunction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */