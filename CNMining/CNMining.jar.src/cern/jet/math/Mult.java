/*    */ package cern.jet.math;
/*    */ 
/*    */ import cern.colt.function.DoubleFunction;
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
/*    */ public final class Mult
/*    */   implements DoubleFunction
/*    */ {
/*    */   public double multiplicator;
/*    */   
/*    */   protected Mult(double paramDouble)
/*    */   {
/* 31 */     this.multiplicator = paramDouble;
/*    */   }
/*    */   
/*    */ 
/*    */   public final double apply(double paramDouble)
/*    */   {
/* 37 */     return paramDouble * this.multiplicator;
/*    */   }
/*    */   
/*    */ 
/*    */   public static Mult div(double paramDouble)
/*    */   {
/* 43 */     return mult(1.0D / paramDouble);
/*    */   }
/*    */   
/*    */ 
/*    */   public static Mult mult(double paramDouble)
/*    */   {
/* 49 */     return new Mult(paramDouble);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/math/Mult.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */