/*    */ package edu.uci.ics.jung.algorithms.scoring.util;
/*    */ 
/*    */ import edu.uci.ics.jung.algorithms.scoring.VertexScorer;
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
/*    */ public class VertexScoreTransformer<V, S>
/*    */   implements Transformer<V, S>
/*    */ {
/*    */   protected VertexScorer<V, S> vs;
/*    */   
/*    */   public VertexScoreTransformer(VertexScorer<V, S> vs)
/*    */   {
/* 33 */     this.vs = vs;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public S transform(V v)
/*    */   {
/* 41 */     return (S)this.vs.getVertexScore(v);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/scoring/util/VertexScoreTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */