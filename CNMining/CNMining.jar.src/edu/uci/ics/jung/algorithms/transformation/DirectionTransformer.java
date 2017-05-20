/*     */ package edu.uci.ics.jung.algorithms.transformation;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.DirectedGraph;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.UndirectedGraph;
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import org.apache.commons.collections15.Factory;
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
/*     */ public class DirectionTransformer
/*     */ {
/*     */   public static <V, E> UndirectedGraph<V, E> toUndirected(Graph<V, E> graph, Factory<UndirectedGraph<V, E>> graph_factory, Factory<E> edge_factory, boolean create_new)
/*     */   {
/*  55 */     UndirectedGraph<V, E> out = (UndirectedGraph)graph_factory.create();
/*     */     
/*  57 */     for (V v : graph.getVertices()) {
/*  58 */       out.addVertex(v);
/*     */     }
/*  60 */     for (E e : graph.getEdges())
/*     */     {
/*  62 */       Pair<V> endpoints = graph.getEndpoints(e);
/*  63 */       V v1 = endpoints.getFirst();
/*  64 */       V v2 = endpoints.getSecond();
/*     */       E to_add;
/*  66 */       E to_add; if ((graph.getEdgeType(e) == EdgeType.DIRECTED) || (create_new)) {
/*  67 */         to_add = edge_factory.create();
/*     */       } else
/*  69 */         to_add = e;
/*  70 */       out.addEdge(to_add, v1, v2, EdgeType.UNDIRECTED);
/*     */     }
/*  72 */     return out;
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
/*     */   public static <V, E> Graph<V, E> toDirected(Graph<V, E> graph, Factory<DirectedGraph<V, E>> graph_factory, Factory<E> edge_factory, boolean create_new)
/*     */   {
/*  95 */     DirectedGraph<V, E> out = (DirectedGraph)graph_factory.create();
/*     */     
/*  97 */     for (V v : graph.getVertices()) {
/*  98 */       out.addVertex(v);
/*     */     }
/* 100 */     for (E e : graph.getEdges())
/*     */     {
/* 102 */       Pair<V> endpoints = graph.getEndpoints(e);
/* 103 */       if (graph.getEdgeType(e) == EdgeType.UNDIRECTED)
/*     */       {
/* 105 */         V v1 = endpoints.getFirst();
/* 106 */         V v2 = endpoints.getSecond();
/* 107 */         out.addEdge(edge_factory.create(), v1, v2, EdgeType.DIRECTED);
/* 108 */         out.addEdge(edge_factory.create(), v2, v1, EdgeType.DIRECTED);
/*     */       }
/*     */       else
/*     */       {
/* 112 */         V source = graph.getSource(e);
/* 113 */         V dest = graph.getDest(e);
/* 114 */         E to_add = create_new ? edge_factory.create() : e;
/* 115 */         out.addEdge(to_add, source, dest, EdgeType.DIRECTED);
/*     */       }
/*     */     }
/*     */     
/* 119 */     return out;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/transformation/DirectionTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */