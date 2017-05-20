/*    */ package org.apache.commons.math.analysis;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.math.MathException;
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
/*    */ public class NevilleInterpolator
/*    */   implements UnivariateRealInterpolator, Serializable
/*    */ {
/*    */   static final long serialVersionUID = 3003707660147873733L;
/*    */   
/*    */   public UnivariateRealFunction interpolate(double[] x, double[] y)
/*    */     throws MathException
/*    */   {
/* 52 */     PolynomialFunctionLagrangeForm p = new PolynomialFunctionLagrangeForm(x, y);
/* 53 */     return p;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/NevilleInterpolator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */