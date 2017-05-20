/*     */ package edu.uci.ics.jung.algorithms.filters;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
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
/*     */ public class KNeighborhoodFilter<V, E>
/*     */   implements Filter<V, E>
/*     */ {
/*     */   private Set<V> rootNodes;
/*     */   private int radiusK;
/*     */   private EdgeType edgeType;
/*     */   
/*     */   public static enum EdgeType
/*     */   {
/*  38 */     IN_OUT,  IN,  OUT;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     private EdgeType() {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public KNeighborhoodFilter(Set<V> rootNodes, int radiusK, EdgeType edgeType)
/*     */   {
/*  50 */     this.rootNodes = rootNodes;
/*  51 */     this.radiusK = radiusK;
/*  52 */     this.edgeType = edgeType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KNeighborhoodFilter(V rootNode, int radiusK, EdgeType edgeType)
/*     */   {
/*  62 */     this.rootNodes = new HashSet();
/*  63 */     this.rootNodes.add(rootNode);
/*  64 */     this.radiusK = radiusK;
/*  65 */     this.edgeType = edgeType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Graph<V, E> transform(Graph<V, E> graph)
/*     */   {
/*  75 */     int currentDepth = 0;
/*  76 */     List<V> currentVertices = new ArrayList();
/*  77 */     Set<V> visitedVertices = new HashSet();
/*  78 */     Set<E> visitedEdges = new HashSet();
/*  79 */     Set<V> acceptedVertices = new HashSet();
/*     */     
/*  81 */     for (V currentRoot : this.rootNodes)
/*     */     {
/*  83 */       visitedVertices.add(currentRoot);
/*  84 */       acceptedVertices.add(currentRoot);
/*  85 */       currentVertices.add(currentRoot);
/*     */     }
/*  87 */     ArrayList<V> newVertices = null;
/*     */     
/*  89 */     while (currentDepth < this.radiusK) {
/*  90 */       newVertices = new ArrayList();
/*  91 */       for (Iterator i$ = currentVertices.iterator(); i$.hasNext();) { currentVertex = i$.next();
/*     */         
/*  93 */         Collection<E> edges = null;
/*  94 */         switch (this.edgeType) {
/*     */         case IN_OUT: 
/*  96 */           edges = graph.getIncidentEdges(currentVertex);
/*  97 */           break;
/*     */         case IN: 
/*  99 */           edges = graph.getInEdges(currentVertex);
/* 100 */           break;
/*     */         case OUT: 
/* 102 */           edges = graph.getOutEdges(currentVertex);
/*     */         }
/*     */         
/* 105 */         for (E currentEdge : edges)
/*     */         {
/* 107 */           V currentNeighbor = graph.getOpposite(currentVertex, currentEdge);
/*     */           
/* 109 */           if (!visitedEdges.contains(currentEdge)) {
/* 110 */             visitedEdges.add(currentEdge);
/* 111 */             if (!visitedVertices.contains(currentNeighbor)) {
/* 112 */               visitedVertices.add(currentNeighbor);
/* 113 */               acceptedVertices.add(currentNeighbor);
/* 114 */               newVertices.add(currentNeighbor);
/*     */             }
/*     */           }
/*     */         } }
/*     */       V currentVertex;
/* 119 */       currentVertices = newVertices;
/* 120 */       currentDepth++;
/*     */     }
/* 122 */     Graph<V, E> ug = null;
/*     */     try {
/* 124 */       ug = (Graph)graph.getClass().newInstance();
/* 125 */       for (E edge : graph.getEdges()) {
/* 126 */         Pair<V> endpoints = graph.getEndpoints(edge);
/* 127 */         if (acceptedVertices.containsAll(endpoints)) {
/* 128 */           ug.addEdge(edge, endpoints.getFirst(), endpoints.getSecond());
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (InstantiationException e)
/*     */     {
/* 134 */       throw new RuntimeException("Unable to create copy of existing graph: ", e);
/*     */     }
/*     */     catch (IllegalAccessException e)
/*     */     {
/* 138 */       throw new RuntimeException("Unable to create copy of existing graph: ", e);
/*     */     }
/* 140 */     return ug;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/filters/KNeighborhoodFilter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */