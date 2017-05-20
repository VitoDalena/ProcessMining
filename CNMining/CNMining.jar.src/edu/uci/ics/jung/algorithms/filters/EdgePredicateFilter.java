/*    */ package edu.uci.ics.jung.algorithms.filters;
/*    */ 
/*    */ import edu.uci.ics.jung.graph.Graph;
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
/*    */ public class EdgePredicateFilter<V, E>
/*    */   implements Filter<V, E>
/*    */ {
/*    */   protected Predicate<E> edge_pred;
/*    */   
/*    */   public EdgePredicateFilter(Predicate<E> edge_pred)
/*    */   {
/* 38 */     this.edge_pred = edge_pred;
/*    */   }
/*    */   
/*    */ 
/*    */   public Graph<V, E> transform(Graph<V, E> g)
/*    */   {
/*    */     Graph<V, E> filtered;
/*    */     try
/*    */     {
/* 47 */       filtered = (Graph)g.getClass().newInstance();
/*    */     }
/*    */     catch (InstantiationException e)
/*    */     {
/* 51 */       throw new RuntimeException("Unable to create copy of existing graph: ", e);
/*    */     }
/*    */     catch (IllegalAccessException e)
/*    */     {
/* 55 */       throw new RuntimeException("Unable to create copy of existing graph: ", e);
/*    */     }
/*    */     
/* 58 */     for (V v : g.getVertices()) {
/* 59 */       filtered.addVertex(v);
/*    */     }
/* 61 */     for (E e : g.getEdges())
/*    */     {
/* 63 */       if (this.edge_pred.evaluate(e)) {
/* 64 */         filtered.addEdge(e, g.getIncidentVertices(e));
/*    */       }
/*    */     }
/* 67 */     return filtered;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/filters/EdgePredicateFilter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */