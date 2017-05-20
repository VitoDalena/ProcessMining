/*     */ package edu.uci.ics.jung.algorithms.shortestpath;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Hypergraph;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ public class BFSDistanceLabeler<V, E>
/*     */ {
/*  33 */   private Map<V, Number> distanceDecorator = new HashMap();
/*     */   
/*     */   private List<V> mCurrentList;
/*     */   
/*     */   private Set<V> mUnvisitedVertices;
/*     */   
/*     */   private List<V> mVerticesInOrderVisited;
/*     */   private Map<V, HashSet<V>> mPredecessorMap;
/*     */   
/*     */   public BFSDistanceLabeler()
/*     */   {
/*  44 */     this.mPredecessorMap = new HashMap();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<V> getVerticesInOrderVisited()
/*     */   {
/*  52 */     return this.mVerticesInOrderVisited;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<V> getUnvisitedVertices()
/*     */   {
/*  60 */     return this.mUnvisitedVertices;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getDistance(Hypergraph<V, E> g, V v)
/*     */   {
/*  69 */     if (!g.getVertices().contains(v)) {
/*  70 */       throw new IllegalArgumentException("Vertex is not contained in the graph.");
/*     */     }
/*     */     
/*  73 */     return ((Number)this.distanceDecorator.get(v)).intValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<V> getPredecessors(V v)
/*     */   {
/*  82 */     return (Set)this.mPredecessorMap.get(v);
/*     */   }
/*     */   
/*     */   protected void initialize(Hypergraph<V, E> g, Set<V> rootSet) {
/*  86 */     this.mVerticesInOrderVisited = new ArrayList();
/*  87 */     this.mUnvisitedVertices = new HashSet();
/*  88 */     for (V currentVertex : g.getVertices()) {
/*  89 */       this.mUnvisitedVertices.add(currentVertex);
/*  90 */       this.mPredecessorMap.put(currentVertex, new HashSet());
/*     */     }
/*     */     
/*  93 */     this.mCurrentList = new ArrayList();
/*  94 */     for (V v : rootSet) {
/*  95 */       this.distanceDecorator.put(v, new Integer(0));
/*  96 */       this.mCurrentList.add(v);
/*  97 */       this.mUnvisitedVertices.remove(v);
/*  98 */       this.mVerticesInOrderVisited.add(v);
/*     */     }
/*     */   }
/*     */   
/*     */   private void addPredecessor(V predecessor, V sucessor) {
/* 103 */     HashSet<V> predecessors = (HashSet)this.mPredecessorMap.get(sucessor);
/* 104 */     predecessors.add(predecessor);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void labelDistances(Hypergraph<V, E> graph, Set<V> rootSet)
/*     */   {
/* 116 */     initialize(graph, rootSet);
/*     */     
/* 118 */     int distance = 1;
/*     */     for (;;) {
/* 120 */       List<V> newList = new ArrayList();
/* 121 */       for (Iterator i$ = this.mCurrentList.iterator(); i$.hasNext();) { currentVertex = i$.next();
/* 122 */         if (graph.containsVertex(currentVertex)) {
/* 123 */           for (V next : graph.getSuccessors(currentVertex))
/* 124 */             visitNewVertex(currentVertex, next, distance, newList);
/*     */         }
/*     */       }
/*     */       V currentVertex;
/* 128 */       if (newList.size() == 0) break;
/* 129 */       this.mCurrentList = newList;
/* 130 */       distance++;
/*     */     }
/*     */     
/* 133 */     for (V v : this.mUnvisitedVertices) {
/* 134 */       this.distanceDecorator.put(v, new Integer(-1));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void labelDistances(Hypergraph<V, E> graph, V root)
/*     */   {
/* 145 */     labelDistances(graph, Collections.singleton(root));
/*     */   }
/*     */   
/*     */   private void visitNewVertex(V predecessor, V neighbor, int distance, List<V> newList) {
/* 149 */     if (this.mUnvisitedVertices.contains(neighbor)) {
/* 150 */       this.distanceDecorator.put(neighbor, new Integer(distance));
/* 151 */       newList.add(neighbor);
/* 152 */       this.mVerticesInOrderVisited.add(neighbor);
/* 153 */       this.mUnvisitedVertices.remove(neighbor);
/*     */     }
/* 155 */     int predecessorDistance = ((Number)this.distanceDecorator.get(predecessor)).intValue();
/* 156 */     int successorDistance = ((Number)this.distanceDecorator.get(neighbor)).intValue();
/* 157 */     if (predecessorDistance < successorDistance) {
/* 158 */       addPredecessor(predecessor, neighbor);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Map<V, Number> getDistanceDecorator()
/*     */   {
/* 167 */     return this.distanceDecorator;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/shortestpath/BFSDistanceLabeler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */