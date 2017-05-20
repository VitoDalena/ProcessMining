/*    */ package org.apache.commons.math.ode;
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
/*    */ public class ThreeEighthesIntegrator
/*    */   extends RungeKuttaIntegrator
/*    */ {
/*    */   private static final String methodName = "3/8";
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
/* 51 */   private static final double[] c = { 0.3333333333333333D, 0.6666666666666666D, 1.0D };
/*    */   
/*    */ 
/*    */ 
/*    */ 
/* 56 */   private static final double[][] a = { { 0.3333333333333333D }, { -0.3333333333333333D, 1.0D }, { 1.0D, -1.0D, 1.0D } };
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 63 */   private static final double[] b = { 0.125D, 0.375D, 0.375D, 0.125D };
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public ThreeEighthesIntegrator(double step)
/*    */   {
/* 72 */     super(c, a, b, new ThreeEighthesStepInterpolator(), step);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String getName()
/*    */   {
/* 79 */     return "3/8";
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/ThreeEighthesIntegrator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */