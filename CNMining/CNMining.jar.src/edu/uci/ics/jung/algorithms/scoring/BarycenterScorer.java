/*    */ package edu.uci.ics.jung.algorithms.scoring;
/*    */ 
/*    */ import edu.uci.ics.jung.algorithms.shortestpath.Distance;
/*    */ import edu.uci.ics.jung.graph.Hypergraph;
/*    */ import org.apache.commons.collections15.Transformer;
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
/*    */ public class BarycenterScorer<V, E>
/*    */   extends DistanceCentralityScorer<V, E>
/*    */ {
/*    */   public BarycenterScorer(Hypergraph<V, E> graph, Distance<V> distance)
/*    */   {
/* 31 */     super(graph, distance, false);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public BarycenterScorer(Hypergraph<V, E> graph, Transformer<E, ? extends Number> edge_weights)
/*    */   {
/* 42 */     super(graph, edge_weights, false);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public BarycenterScorer(Hypergraph<V, E> graph)
/*    */   {
/* 53 */     super(graph, false);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/scoring/BarycenterScorer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */