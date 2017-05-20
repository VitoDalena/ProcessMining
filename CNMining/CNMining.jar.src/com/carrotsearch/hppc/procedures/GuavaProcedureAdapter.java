/*    */ package com.carrotsearch.hppc.procedures;
/*    */ 
/*    */ import com.google.common.base.Predicate;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuavaProcedureAdapter<KType>
/*    */   implements ObjectProcedure<KType>
/*    */ {
/*    */   private final Predicate<? super KType> predicate;
/*    */   
/*    */   public GuavaProcedureAdapter(Predicate<? super KType> predicate)
/*    */   {
/* 14 */     this.predicate = predicate;
/*    */   }
/*    */   
/*    */   public void apply(KType value)
/*    */   {
/* 19 */     this.predicate.apply(value);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/procedures/GuavaProcedureAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */