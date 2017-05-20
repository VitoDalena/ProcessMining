/*     */ package edu.uci.ics.jung.algorithms.shortestpath;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Forest;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.Factory;
/*     */ import org.apache.commons.collections15.functors.ConstantTransformer;
/*     */ import org.apache.commons.collections15.map.LazyMap;
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
/*     */ public class MinimumSpanningForest<V, E>
/*     */ {
/*     */   protected Graph<V, E> graph;
/*     */   protected Forest<V, E> forest;
/*     */   protected Map<E, Double> weights;
/*     */   
/*     */   public MinimumSpanningForest(Graph<V, E> graph, Factory<Forest<V, E>> factory, V root, Map<E, Double> weights)
/*     */   {
/*  49 */     this(graph, (Forest)factory.create(), root, weights);
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
/*     */   public MinimumSpanningForest(Graph<V, E> graph, Forest<V, E> forest, V root, Map<E, Double> weights)
/*     */   {
/*  68 */     if (forest.getVertexCount() != 0) {
/*  69 */       throw new IllegalArgumentException("Supplied Forest must be empty");
/*     */     }
/*  71 */     this.graph = graph;
/*  72 */     this.forest = forest;
/*  73 */     if (weights != null) {
/*  74 */       this.weights = weights;
/*     */     }
/*  76 */     Set<E> unfinishedEdges = new HashSet(graph.getEdges());
/*  77 */     if (graph.getVertices().contains(root)) {
/*  78 */       this.forest.addVertex(root);
/*     */     }
/*  80 */     updateForest(forest.getVertices(), unfinishedEdges);
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
/*     */   public MinimumSpanningForest(Graph<V, E> graph, Forest<V, E> forest, V root)
/*     */   {
/*  99 */     if (forest.getVertexCount() != 0) {
/* 100 */       throw new IllegalArgumentException("Supplied Forest must be empty");
/*     */     }
/* 102 */     this.graph = graph;
/* 103 */     this.forest = forest;
/* 104 */     this.weights = LazyMap.decorate(new HashMap(), new ConstantTransformer(Integer.valueOf(1)));
/*     */     
/* 106 */     Set<E> unfinishedEdges = new HashSet(graph.getEdges());
/* 107 */     if (graph.getVertices().contains(root)) {
/* 108 */       this.forest.addVertex(root);
/*     */     }
/* 110 */     updateForest(forest.getVertices(), unfinishedEdges);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Forest<V, E> getForest()
/*     */   {
/* 117 */     return this.forest;
/*     */   }
/*     */   
/*     */   protected void updateForest(Collection<V> tv, Collection<E> unfinishedEdges) {
/* 121 */     double minCost = Double.MAX_VALUE;
/* 122 */     E nextEdge = null;
/* 123 */     V nextVertex = null;
/* 124 */     V currentVertex = null;
/* 125 */     for (E e : unfinishedEdges)
/*     */     {
/* 127 */       if (!this.forest.getEdges().contains(e))
/*     */       {
/*     */ 
/* 130 */         Pair<V> endpoints = this.graph.getEndpoints(e);
/* 131 */         V first = endpoints.getFirst();
/* 132 */         V second = endpoints.getSecond();
/* 133 */         if ((tv.contains(first) == true) && (!tv.contains(second)) && 
/* 134 */           (((Double)this.weights.get(e)).doubleValue() < minCost)) {
/* 135 */           minCost = ((Double)this.weights.get(e)).doubleValue();
/* 136 */           nextEdge = e;
/* 137 */           currentVertex = first;
/* 138 */           nextVertex = second;
/*     */         }
/*     */         
/* 141 */         if ((this.graph.getEdgeType(e) == EdgeType.UNDIRECTED) && (tv.contains(second) == true) && (!tv.contains(first)))
/*     */         {
/* 143 */           if (((Double)this.weights.get(e)).doubleValue() < minCost) {
/* 144 */             minCost = ((Double)this.weights.get(e)).doubleValue();
/* 145 */             nextEdge = e;
/* 146 */             currentVertex = second;
/* 147 */             nextVertex = first;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 152 */     if ((nextVertex != null) && (nextEdge != null)) {
/* 153 */       unfinishedEdges.remove(nextEdge);
/* 154 */       this.forest.addEdge(nextEdge, currentVertex, nextVertex);
/* 155 */       updateForest(this.forest.getVertices(), unfinishedEdges);
/*     */     }
/* 157 */     Collection<V> leftovers = new HashSet(this.graph.getVertices());
/* 158 */     leftovers.removeAll(this.forest.getVertices());
/* 159 */     if (leftovers.size() > 0) {
/* 160 */       V anotherRoot = leftovers.iterator().next();
/* 161 */       this.forest.addVertex(anotherRoot);
/* 162 */       updateForest(this.forest.getVertices(), unfinishedEdges);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/shortestpath/MinimumSpanningForest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */