/*    */ package edu.uci.ics.jung.algorithms.filters;
/*    */ 
/*    */ import edu.uci.ics.jung.graph.Graph;
/*    */ import java.util.Collection;
/*    */ import org.apache.commons.collections15.Predicate;
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
/*    */ public class VertexPredicateFilter<V, E>
/*    */   implements Filter<V, E>
/*    */ {
/*    */   protected Predicate<V> vertex_pred;
/*    */   
/*    */   public VertexPredicateFilter(Predicate<V> vertex_pred)
/*    */   {
/* 39 */     this.vertex_pred = vertex_pred;
/*    */   }
/*    */   
/*    */ 
/*    */   public Graph<V, E> transform(Graph<V, E> g)
/*    */   {
/*    */     Graph<V, E> filtered;
/*    */     try
/*    */     {
/* 48 */       filtered = (Graph)g.getClass().newInstance();
/*    */     }
/*    */     catch (InstantiationException e)
/*    */     {
/* 52 */       throw new RuntimeException("Unable to create copy of existing graph: ", e);
/*    */     }
/*    */     catch (IllegalAccessException e)
/*    */     {
/* 56 */       throw new RuntimeException("Unable to create copy of existing graph: ", e);
/*    */     }
/*    */     
/* 59 */     for (V v : g.getVertices()) {
/* 60 */       if (this.vertex_pred.evaluate(v))
/* 61 */         filtered.addVertex(v);
/*    */     }
/* 63 */     Collection<V> filtered_vertices = filtered.getVertices();
/*    */     
/* 65 */     for (E e : g.getEdges())
/*    */     {
/* 67 */       Collection<V> incident = g.getIncidentVertices(e);
/* 68 */       if (filtered_vertices.containsAll(incident)) {
/* 69 */         filtered.addEdge(e, incident);
/*    */       }
/*    */     }
/* 72 */     return filtered;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/filters/VertexPredicateFilter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */