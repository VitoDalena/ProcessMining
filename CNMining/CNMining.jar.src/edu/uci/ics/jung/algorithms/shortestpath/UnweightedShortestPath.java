/*     */ package edu.uci.ics.jung.algorithms.shortestpath;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Hypergraph;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
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
/*     */ public class UnweightedShortestPath<V, E>
/*     */   implements ShortestPath<V, E>, Distance<V>
/*     */ {
/*     */   private Map<V, Map<V, Number>> mDistanceMap;
/*     */   private Map<V, Map<V, E>> mIncomingEdgeMap;
/*     */   private Hypergraph<V, E> mGraph;
/*  28 */   private Map<V, Number> distances = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public UnweightedShortestPath(Hypergraph<V, E> g)
/*     */   {
/*  36 */     this.mDistanceMap = new HashMap();
/*  37 */     this.mIncomingEdgeMap = new HashMap();
/*  38 */     this.mGraph = g;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getDistance(V source, V target)
/*     */   {
/*  46 */     Map<V, Number> sourceSPMap = getDistanceMap(source);
/*  47 */     return (Number)sourceSPMap.get(target);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Map<V, Number> getDistanceMap(V source)
/*     */   {
/*  55 */     Map<V, Number> sourceSPMap = (Map)this.mDistanceMap.get(source);
/*  56 */     if (sourceSPMap == null)
/*     */     {
/*  58 */       computeShortestPathsFromSource(source);
/*  59 */       sourceSPMap = (Map)this.mDistanceMap.get(source);
/*     */     }
/*  61 */     return sourceSPMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Map<V, E> getIncomingEdgeMap(V source)
/*     */   {
/*  69 */     Map<V, E> sourceIEMap = (Map)this.mIncomingEdgeMap.get(source);
/*  70 */     if (sourceIEMap == null)
/*     */     {
/*  72 */       computeShortestPathsFromSource(source);
/*  73 */       sourceIEMap = (Map)this.mIncomingEdgeMap.get(source);
/*     */     }
/*  75 */     return sourceIEMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void computeShortestPathsFromSource(V source)
/*     */   {
/*  85 */     BFSDistanceLabeler<V, E> labeler = new BFSDistanceLabeler();
/*  86 */     labeler.labelDistances(this.mGraph, source);
/*  87 */     this.distances = labeler.getDistanceDecorator();
/*  88 */     Map<V, Number> currentSourceSPMap = new HashMap();
/*  89 */     Map<V, E> currentSourceEdgeMap = new HashMap();
/*     */     
/*  91 */     for (Iterator i$ = this.mGraph.getVertices().iterator(); i$.hasNext();) { vertex = i$.next();
/*     */       
/*  93 */       Number distanceVal = (Number)this.distances.get(vertex);
/*     */       
/*     */ 
/*  96 */       if ((distanceVal != null) && (distanceVal.intValue() >= 0))
/*     */       {
/*  98 */         currentSourceSPMap.put(vertex, distanceVal);
/*  99 */         minDistance = distanceVal.intValue();
/* 100 */         for (i$ = this.mGraph.getInEdges(vertex).iterator(); i$.hasNext();) { incomingEdge = i$.next();
/*     */           
/* 102 */           for (V neighbor : this.mGraph.getIncidentVertices(incomingEdge))
/*     */           {
/* 104 */             if (!neighbor.equals(vertex))
/*     */             {
/*     */ 
/*     */ 
/* 108 */               Number predDistanceVal = (Number)this.distances.get(neighbor);
/*     */               
/* 110 */               int pred_distance = predDistanceVal.intValue();
/* 111 */               if ((pred_distance < minDistance) && (pred_distance >= 0))
/*     */               {
/* 113 */                 minDistance = predDistanceVal.intValue();
/* 114 */                 currentSourceEdgeMap.put(vertex, incomingEdge);
/*     */               } } } } } }
/*     */     V vertex;
/*     */     int minDistance;
/*     */     Iterator i$;
/*     */     E incomingEdge;
/* 120 */     this.mDistanceMap.put(source, currentSourceSPMap);
/* 121 */     this.mIncomingEdgeMap.put(source, currentSourceEdgeMap);
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
/*     */   public void reset()
/*     */   {
/* 135 */     this.mDistanceMap.clear();
/* 136 */     this.mIncomingEdgeMap.clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reset(V v)
/*     */   {
/* 148 */     this.mDistanceMap.remove(v);
/* 149 */     this.mIncomingEdgeMap.remove(v);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/shortestpath/UnweightedShortestPath.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */