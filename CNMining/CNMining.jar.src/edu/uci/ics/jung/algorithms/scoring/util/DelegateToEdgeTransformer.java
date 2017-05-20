/*    */ package edu.uci.ics.jung.algorithms.scoring.util;
/*    */ 
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
/*    */ public class DelegateToEdgeTransformer<V, E>
/*    */   implements Transformer<VEPair<V, E>, Number>
/*    */ {
/*    */   protected Transformer<E, ? extends Number> delegate;
/*    */   
/*    */   public DelegateToEdgeTransformer(Transformer<E, ? extends Number> delegate)
/*    */   {
/* 37 */     this.delegate = delegate;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public Number transform(VEPair<V, E> arg0)
/*    */   {
/* 45 */     return (Number)this.delegate.transform(arg0.getE());
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/scoring/util/DelegateToEdgeTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */