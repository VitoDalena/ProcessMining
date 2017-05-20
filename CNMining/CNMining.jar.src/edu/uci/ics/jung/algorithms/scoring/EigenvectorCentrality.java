/*    */ package edu.uci.ics.jung.algorithms.scoring;
/*    */ 
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EigenvectorCentrality<V, E>
/*    */   extends PageRank<V, E>
/*    */ {
/*    */   public EigenvectorCentrality(Hypergraph<V, E> graph, Transformer<E, ? extends Number> edge_weights)
/*    */   {
/* 38 */     super(graph, edge_weights, 0.0D);
/* 39 */     acceptDisconnectedGraph(false);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public EigenvectorCentrality(Hypergraph<V, E> graph)
/*    */   {
/* 49 */     super(graph, 0.0D);
/* 50 */     acceptDisconnectedGraph(false);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/scoring/EigenvectorCentrality.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */