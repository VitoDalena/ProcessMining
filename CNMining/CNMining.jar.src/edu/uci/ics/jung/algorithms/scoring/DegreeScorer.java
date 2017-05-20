/*    */ package edu.uci.ics.jung.algorithms.scoring;
/*    */ 
/*    */ import edu.uci.ics.jung.graph.Hypergraph;
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
/*    */ public class DegreeScorer<V>
/*    */   implements VertexScorer<V, Integer>
/*    */ {
/*    */   protected Hypergraph<V, ?> graph;
/*    */   
/*    */   public DegreeScorer(Hypergraph<V, ?> graph)
/*    */   {
/* 34 */     this.graph = graph;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Integer getVertexScore(V v)
/*    */   {
/* 43 */     return Integer.valueOf(this.graph.degree(v));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/scoring/DegreeScorer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */