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
/*    */ public class MidpointIntegrator
/*    */   extends RungeKuttaIntegrator
/*    */ {
/*    */   private static final String methodName = "midpoint";
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
/* 49 */   private static final double[] c = { 0.5D };
/*    */   
/*    */ 
/*    */ 
/*    */ 
/* 54 */   private static final double[][] a = { { 0.5D } };
/*    */   
/*    */ 
/*    */ 
/*    */ 
/* 59 */   private static final double[] b = { 0.0D, 1.0D };
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public MidpointIntegrator(double step)
/*    */   {
/* 68 */     super(c, a, b, new MidpointStepInterpolator(), step);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String getName()
/*    */   {
/* 75 */     return "midpoint";
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/MidpointIntegrator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */