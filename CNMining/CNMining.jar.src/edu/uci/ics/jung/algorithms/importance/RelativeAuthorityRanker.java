/*    */ package edu.uci.ics.jung.algorithms.importance;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
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
/*    */ public abstract class RelativeAuthorityRanker<V, E>
/*    */   extends AbstractRanker<V, E>
/*    */ {
/*    */   private Set<V> mPriors;
/* 33 */   protected Map<V, Number> priorRankScoreMap = new HashMap();
/*    */   
/*    */ 
/*    */ 
/*    */   protected void finalizeIterations()
/*    */   {
/* 39 */     super.finalizeIterations();
/* 40 */     this.priorRankScoreMap.clear();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected double getPriorRankScore(V v)
/*    */   {
/* 49 */     return ((Number)this.priorRankScoreMap.get(v)).doubleValue();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setPriorRankScore(V v, double value)
/*    */   {
/* 59 */     this.priorRankScoreMap.put(v, Double.valueOf(value));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected Set<V> getPriors()
/*    */   {
/* 66 */     return this.mPriors;
/*    */   }
/*    */   
/*    */ 
/*    */   protected void setPriors(Set<V> priors)
/*    */   {
/* 72 */     this.mPriors = priors;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/importance/RelativeAuthorityRanker.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */