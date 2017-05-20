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
/*    */ 
/*    */ public class ClassicalRungeKuttaIntegrator
/*    */   extends RungeKuttaIntegrator
/*    */ {
/*    */   private static final String methodName = "classical Runge-Kutta";
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
/* 52 */   private static final double[] c = { 0.5D, 0.5D, 1.0D };
/*    */   
/*    */ 
/*    */ 
/*    */ 
/* 57 */   private static final double[][] a = { { 0.5D }, { 0.0D, 0.5D }, { 0.0D, 0.0D, 1.0D } };
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 64 */   private static final double[] b = { 0.16666666666666666D, 0.3333333333333333D, 0.3333333333333333D, 0.16666666666666666D };
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public ClassicalRungeKuttaIntegrator(double step)
/*    */   {
/* 74 */     super(c, a, b, new ClassicalRungeKuttaStepInterpolator(), step);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String getName()
/*    */   {
/* 81 */     return "classical Runge-Kutta";
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/ClassicalRungeKuttaIntegrator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */