/*    */ package com.carrotsearch.hppc.predicates;
/*    */ 
/*    */ import com.google.common.base.Predicate;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuavaPredicateAdapter<KType>
/*    */   implements ObjectPredicate<KType>
/*    */ {
/*    */   private final Predicate<? super KType> predicate;
/*    */   
/*    */   public GuavaPredicateAdapter(Predicate<? super KType> predicate)
/*    */   {
/* 14 */     this.predicate = predicate;
/*    */   }
/*    */   
/*    */   public boolean apply(KType value)
/*    */   {
/* 19 */     return this.predicate.apply(value);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/predicates/GuavaPredicateAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */