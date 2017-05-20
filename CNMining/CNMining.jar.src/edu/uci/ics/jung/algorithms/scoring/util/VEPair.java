/*    */ package edu.uci.ics.jung.algorithms.scoring.util;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VEPair<V, E>
/*    */ {
/*    */   private V v;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private E e;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public VEPair(V v, E e)
/*    */   {
/* 34 */     if ((v == null) || (e == null)) {
/* 35 */       throw new IllegalArgumentException("elements must be non-null");
/*    */     }
/* 37 */     this.v = v;
/* 38 */     this.e = e;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public V getV()
/*    */   {
/* 46 */     return (V)this.v;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public E getE()
/*    */   {
/* 54 */     return (E)this.e;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/scoring/util/VEPair.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */