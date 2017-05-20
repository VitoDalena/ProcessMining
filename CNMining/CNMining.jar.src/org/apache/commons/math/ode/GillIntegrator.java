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
/*    */ public class GillIntegrator
/*    */   extends RungeKuttaIntegrator
/*    */ {
/*    */   private static final String methodName = "Gill";
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
/* 51 */   private static final double[] c = { 0.5D, 0.5D, 1.0D };
/*    */   
/*    */ 
/*    */ 
/*    */ 
/* 56 */   private static final double[][] a = { { 0.5D }, { (Math.sqrt(2.0D) - 1.0D) / 2.0D, (2.0D - Math.sqrt(2.0D)) / 2.0D }, { 0.0D, -Math.sqrt(2.0D) / 2.0D, (2.0D + Math.sqrt(2.0D)) / 2.0D } };
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 63 */   private static final double[] b = { 0.16666666666666666D, (2.0D - Math.sqrt(2.0D)) / 6.0D, (2.0D + Math.sqrt(2.0D)) / 6.0D, 0.16666666666666666D };
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public GillIntegrator(double step)
/*    */   {
/* 72 */     super(c, a, b, new GillStepInterpolator(), step);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String getName()
/*    */   {
/* 79 */     return "Gill";
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/GillIntegrator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */