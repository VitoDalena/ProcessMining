/*    */ package cern.jet.math;
/*    */ 
/*    */ import cern.colt.function.DoubleDoubleFunction;
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
/*    */ public final class PlusMult
/*    */   implements DoubleDoubleFunction
/*    */ {
/*    */   public double multiplicator;
/*    */   
/*    */   protected PlusMult(double paramDouble)
/*    */   {
/* 33 */     this.multiplicator = paramDouble;
/*    */   }
/*    */   
/*    */ 
/*    */   public final double apply(double paramDouble1, double paramDouble2)
/*    */   {
/* 39 */     return paramDouble1 + paramDouble2 * this.multiplicator;
/*    */   }
/*    */   
/*    */ 
/*    */   public static PlusMult minusDiv(double paramDouble)
/*    */   {
/* 45 */     return new PlusMult(-1.0D / paramDouble);
/*    */   }
/*    */   
/*    */ 
/*    */   public static PlusMult minusMult(double paramDouble)
/*    */   {
/* 51 */     return new PlusMult(-paramDouble);
/*    */   }
/*    */   
/*    */ 
/*    */   public static PlusMult plusDiv(double paramDouble)
/*    */   {
/* 57 */     return new PlusMult(1.0D / paramDouble);
/*    */   }
/*    */   
/*    */ 
/*    */   public static PlusMult plusMult(double paramDouble)
/*    */   {
/* 63 */     return new PlusMult(paramDouble);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/math/PlusMult.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */