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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClosenessCentrality<V, E>
/*    */   extends DistanceCentralityScorer<V, E>
/*    */ {
/*    */   public ClosenessCentrality(Hypergraph<V, E> graph, Distance<V> distance)
/*    */   {
/* 34 */     super(graph, distance, true);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public ClosenessCentrality(Hypergraph<V, E> graph, Transformer<E, ? extends Number> edge_weights)
/*    */   {
/* 44 */     super(graph, edge_weights, true);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public ClosenessCentrality(Hypergraph<V, E> graph)
/*    */   {
/* 53 */     super(graph, true);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/scoring/ClosenessCentrality.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */