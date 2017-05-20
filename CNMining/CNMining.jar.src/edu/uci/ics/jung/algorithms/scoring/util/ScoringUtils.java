/*    */ package edu.uci.ics.jung.algorithms.scoring.util;
/*    */ 
/*    */ import edu.uci.ics.jung.algorithms.scoring.HITS.Scores;
/*    */ import java.util.Collection;
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
/*    */ public class ScoringUtils
/*    */ {
/*    */   public static <V> Transformer<V, Double> getUniformRootPrior(Collection<V> roots)
/*    */   {
/* 34 */     Collection<V> inner_roots = roots;
/* 35 */     Transformer<V, Double> distribution = new Transformer()
/*    */     {
/*    */       public Double transform(V input)
/*    */       {
/* 39 */         if (this.val$inner_roots.contains(input)) {
/* 40 */           return new Double(1.0D / this.val$inner_roots.size());
/*    */         }
/* 42 */         return Double.valueOf(0.0D);
/*    */       }
/*    */       
/* 45 */     };
/* 46 */     return distribution;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static <V> Transformer<V, HITS.Scores> getHITSUniformRootPrior(Collection<V> roots)
/*    */   {
/* 58 */     Collection<V> inner_roots = roots;
/* 59 */     Transformer<V, HITS.Scores> distribution = new Transformer()
/*    */     {
/*    */ 
/*    */       public HITS.Scores transform(V input)
/*    */       {
/* 64 */         if (this.val$inner_roots.contains(input)) {
/* 65 */           return new HITS.Scores(1.0D / this.val$inner_roots.size(), 1.0D / this.val$inner_roots.size());
/*    */         }
/* 67 */         return new HITS.Scores(0.0D, 0.0D);
/*    */       }
/* 69 */     };
/* 70 */     return distribution;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/scoring/util/ScoringUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */