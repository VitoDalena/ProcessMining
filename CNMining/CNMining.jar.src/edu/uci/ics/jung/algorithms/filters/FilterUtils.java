/*    */ package edu.uci.ics.jung.algorithms.filters;
/*    */ 
/*    */ import edu.uci.ics.jung.graph.Hypergraph;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FilterUtils
/*    */ {
/*    */   public static <V, E, G extends Hypergraph<V, E>> G createInducedSubgraph(Collection<V> vertices, G graph)
/*    */   {
/* 44 */     G subgraph = null;
/*    */     try
/*    */     {
/* 47 */       subgraph = (Hypergraph)graph.getClass().newInstance();
/*    */       
/* 49 */       for (V v : vertices)
/*    */       {
/* 51 */         if (!graph.containsVertex(v)) {
/* 52 */           throw new IllegalArgumentException("Vertex " + v + " is not an element of " + graph);
/*    */         }
/* 54 */         subgraph.addVertex(v);
/*    */       }
/*    */       
/* 57 */       for (E e : graph.getEdges())
/*    */       {
/* 59 */         Collection<V> incident = graph.getIncidentVertices(e);
/* 60 */         if (vertices.containsAll(incident)) {
/* 61 */           subgraph.addEdge(e, incident, graph.getEdgeType(e));
/*    */         }
/*    */       }
/*    */     }
/*    */     catch (InstantiationException e) {
/* 66 */       throw new RuntimeException("Unable to create copy of existing graph: ", e);
/*    */     }
/*    */     catch (IllegalAccessException e)
/*    */     {
/* 70 */       throw new RuntimeException("Unable to create copy of existing graph: ", e);
/*    */     }
/* 72 */     return subgraph;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static <V, E, G extends Hypergraph<V, E>> Collection<G> createAllInducedSubgraphs(Collection<? extends Collection<V>> vertex_collections, G graph)
/*    */   {
/* 91 */     Collection<G> subgraphs = new ArrayList();
/*    */     
/* 93 */     for (Collection<V> vertex_set : vertex_collections) {
/* 94 */       subgraphs.add(createInducedSubgraph(vertex_set, graph));
/*    */     }
/* 96 */     return subgraphs;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/filters/FilterUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */