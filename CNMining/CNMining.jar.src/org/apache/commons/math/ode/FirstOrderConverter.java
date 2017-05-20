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
/*    */ public class FirstOrderConverter
/*    */   implements FirstOrderDifferentialEquations
/*    */ {
/*    */   private SecondOrderDifferentialEquations equations;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private int dimension;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private double[] z;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private double[] zDot;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private double[] zDDot;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public FirstOrderConverter(SecondOrderDifferentialEquations equations)
/*    */   {
/* 64 */     this.equations = equations;
/* 65 */     this.dimension = equations.getDimension();
/* 66 */     this.z = new double[this.dimension];
/* 67 */     this.zDot = new double[this.dimension];
/* 68 */     this.zDDot = new double[this.dimension];
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int getDimension()
/*    */   {
/* 77 */     return 2 * this.dimension;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void computeDerivatives(double t, double[] y, double[] yDot)
/*    */     throws DerivativeException
/*    */   {
/* 91 */     System.arraycopy(y, 0, this.z, 0, this.dimension);
/* 92 */     System.arraycopy(y, this.dimension, this.zDot, 0, this.dimension);
/*    */     
/*    */ 
/* 95 */     this.equations.computeSecondDerivatives(t, this.z, this.zDot, this.zDDot);
/*    */     
/*    */ 
/* 98 */     System.arraycopy(this.zDot, 0, yDot, 0, this.dimension);
/* 99 */     System.arraycopy(this.zDDot, 0, yDot, this.dimension, this.dimension);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/FirstOrderConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */