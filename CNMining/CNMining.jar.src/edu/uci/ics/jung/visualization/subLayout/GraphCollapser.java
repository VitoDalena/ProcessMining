/*     */ package edu.uci.ics.jung.visualization.subLayout;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import java.util.Collection;
/*     */ import java.util.logging.Logger;
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
/*     */ public class GraphCollapser
/*     */ {
/*  20 */   private static final Logger logger = Logger.getLogger(GraphCollapser.class.getClass().getName());
/*     */   private Graph originalGraph;
/*     */   
/*     */   public GraphCollapser(Graph originalGraph) {
/*  24 */     this.originalGraph = originalGraph;
/*     */   }
/*     */   
/*     */   Graph createGraph() throws InstantiationException, IllegalAccessException {
/*  28 */     return (Graph)this.originalGraph.getClass().newInstance();
/*     */   }
/*     */   
/*     */   public Graph collapse(Graph inGraph, Graph clusterGraph)
/*     */   {
/*  33 */     if (clusterGraph.getVertexCount() < 2) { return inGraph;
/*     */     }
/*  35 */     Graph graph = inGraph;
/*     */     try {
/*  37 */       graph = createGraph();
/*     */     } catch (Exception ex) {
/*  39 */       ex.printStackTrace();
/*     */     }
/*  41 */     Collection cluster = clusterGraph.getVertices();
/*     */     
/*     */ 
/*     */ 
/*  45 */     for (Object v : inGraph.getVertices()) {
/*  46 */       if (!cluster.contains(v)) {
/*  47 */         graph.addVertex(v);
/*     */       }
/*     */     }
/*     */     
/*  51 */     graph.addVertex(clusterGraph);
/*     */     
/*     */ 
/*     */ 
/*  55 */     for (Object e : inGraph.getEdges()) {
/*  56 */       Pair endpoints = inGraph.getEndpoints(e);
/*     */       
/*  58 */       if (!cluster.containsAll(endpoints))
/*     */       {
/*  60 */         if (cluster.contains(endpoints.getFirst())) {
/*  61 */           graph.addEdge(e, clusterGraph, endpoints.getSecond(), inGraph.getEdgeType(e));
/*     */         }
/*  63 */         else if (cluster.contains(endpoints.getSecond())) {
/*  64 */           graph.addEdge(e, endpoints.getFirst(), clusterGraph, inGraph.getEdgeType(e));
/*     */         }
/*     */         else {
/*  67 */           graph.addEdge(e, endpoints.getFirst(), endpoints.getSecond(), inGraph.getEdgeType(e));
/*     */         }
/*     */       }
/*     */     }
/*  71 */     return graph;
/*     */   }
/*     */   
/*     */   public Graph expand(Graph inGraph, Graph clusterGraph) {
/*  75 */     Graph graph = inGraph;
/*     */     try {
/*  77 */       graph = createGraph();
/*     */     } catch (Exception ex) {
/*  79 */       ex.printStackTrace();
/*     */     }
/*  81 */     Collection cluster = clusterGraph.getVertices();
/*  82 */     logger.fine("cluster to expand is " + cluster);
/*     */     
/*     */ 
/*  85 */     for (Object v : cluster) {
/*  86 */       graph.addVertex(v);
/*  87 */       for (Object edge : clusterGraph.getIncidentEdges(v)) {
/*  88 */         Pair endpoints = clusterGraph.getEndpoints(edge);
/*  89 */         graph.addEdge(edge, endpoints.getFirst(), endpoints.getSecond(), clusterGraph.getEdgeType(edge));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  94 */     for (Object v : inGraph.getVertices()) {
/*  95 */       if (!v.equals(clusterGraph)) {
/*  96 */         graph.addVertex(v);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 103 */     for (Object v : inGraph.getVertices()) {
/* 104 */       if (!v.equals(clusterGraph)) {
/* 105 */         for (Object edge : inGraph.getIncidentEdges(v)) {
/* 106 */           Pair endpoints = inGraph.getEndpoints(edge);
/* 107 */           Object v1 = endpoints.getFirst();
/* 108 */           Object v2 = endpoints.getSecond();
/* 109 */           if (!cluster.containsAll(endpoints)) {
/* 110 */             if (clusterGraph.equals(v1))
/*     */             {
/* 112 */               Object originalV1 = this.originalGraph.getEndpoints(edge).getFirst();
/* 113 */               Object newV1 = findVertex(graph, originalV1);
/* 114 */               assert (newV1 != null) : ("newV1 for " + originalV1 + " was not found!");
/* 115 */               graph.addEdge(edge, newV1, v2, inGraph.getEdgeType(edge));
/* 116 */             } else if (clusterGraph.equals(v2))
/*     */             {
/* 118 */               Object originalV2 = this.originalGraph.getEndpoints(edge).getSecond();
/* 119 */               Object newV2 = findVertex(graph, originalV2);
/* 120 */               assert (newV2 != null) : ("newV2 for " + originalV2 + " was not found!");
/* 121 */               graph.addEdge(edge, v1, newV2, inGraph.getEdgeType(edge));
/*     */             } else {
/* 123 */               graph.addEdge(edge, v1, v2, inGraph.getEdgeType(edge));
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 129 */     return graph;
/*     */   }
/*     */   
/* 132 */   Object findVertex(Graph inGraph, Object vertex) { Collection vertices = inGraph.getVertices();
/* 133 */     if (vertices.contains(vertex)) {
/* 134 */       return vertex;
/*     */     }
/* 136 */     for (Object v : vertices) {
/* 137 */       if ((v instanceof Graph)) {
/* 138 */         Graph g = (Graph)v;
/* 139 */         if (contains(g, vertex)) {
/* 140 */           return v;
/*     */         }
/*     */       }
/*     */     }
/* 144 */     return null;
/*     */   }
/*     */   
/*     */   private boolean contains(Graph inGraph, Object vertex) {
/* 148 */     boolean contained = false;
/* 149 */     if (inGraph.getVertices().contains(vertex)) return true;
/* 150 */     for (Object v : inGraph.getVertices()) {
/* 151 */       if ((v instanceof Graph)) {
/* 152 */         contained |= contains((Graph)v, vertex);
/*     */       }
/*     */     }
/* 155 */     return contained;
/*     */   }
/*     */   
/*     */   public Graph getClusterGraph(Graph inGraph, Collection picked) {
/*     */     Graph clusterGraph;
/*     */     try {
/* 161 */       clusterGraph = createGraph();
/*     */     }
/*     */     catch (InstantiationException e) {
/* 164 */       e.printStackTrace();
/* 165 */       return null;
/*     */     }
/*     */     catch (IllegalAccessException e) {
/* 168 */       e.printStackTrace();
/* 169 */       return null;
/*     */     }
/* 171 */     for (Object v : picked) {
/* 172 */       clusterGraph.addVertex(v);
/* 173 */       Collection edges = inGraph.getIncidentEdges(v);
/* 174 */       for (Object edge : edges) {
/* 175 */         Pair endpoints = inGraph.getEndpoints(edge);
/* 176 */         Object v1 = endpoints.getFirst();
/* 177 */         Object v2 = endpoints.getSecond();
/* 178 */         if (picked.containsAll(endpoints)) {
/* 179 */           clusterGraph.addEdge(edge, v1, v2, inGraph.getEdgeType(edge));
/*     */         }
/*     */       }
/*     */     }
/* 183 */     return clusterGraph;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/subLayout/GraphCollapser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */