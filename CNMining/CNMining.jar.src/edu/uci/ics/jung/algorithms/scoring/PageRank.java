/*    */ package edu.uci.ics.jung.algorithms.scoring;
/*    */ 
/*    */ import edu.uci.ics.jung.algorithms.scoring.util.ScoringUtils;
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
/*    */ public class PageRank<V, E>
/*    */   extends PageRankWithPriors<V, E>
/*    */ {
/*    */   public PageRank(Hypergraph<V, E> graph, Transformer<E, ? extends Number> edge_weight, double alpha)
/*    */   {
/* 57 */     super(graph, edge_weight, ScoringUtils.getUniformRootPrior(graph.getVertices()), alpha);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public PageRank(Hypergraph<V, E> graph, double alpha)
/*    */   {
/* 68 */     super(graph, ScoringUtils.getUniformRootPrior(graph.getVertices()), alpha);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/scoring/PageRank.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */