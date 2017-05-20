/*     */ package org.apache.commons.math.analysis;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math.DuplicateSampleAbscissaException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DividedDifferenceInterpolator
/*     */   implements UnivariateRealInterpolator, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 107049519551235069L;
/*     */   
/*     */   public UnivariateRealFunction interpolate(double[] x, double[] y)
/*     */     throws DuplicateSampleAbscissaException
/*     */   {
/*  60 */     PolynomialFunctionLagrangeForm.verifyInterpolationArray(x, y);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  70 */     double[] c = new double[x.length - 1];
/*  71 */     for (int i = 0; i < c.length; i++) {
/*  72 */       c[i] = x[i];
/*     */     }
/*  74 */     double[] a = computeDividedDifference(x, y);
/*     */     
/*     */ 
/*  77 */     PolynomialFunctionNewtonForm p = new PolynomialFunctionNewtonForm(a, c);
/*  78 */     return p;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static double[] computeDividedDifference(double[] x, double[] y)
/*     */     throws DuplicateSampleAbscissaException
/*     */   {
/* 102 */     PolynomialFunctionLagrangeForm.verifyInterpolationArray(x, y);
/*     */     
/* 104 */     int n = x.length;
/* 105 */     double[] divdiff = new double[n];
/* 106 */     for (int i = 0; i < n; i++) {
/* 107 */       divdiff[i] = y[i];
/*     */     }
/*     */     
/* 110 */     double[] a = new double[n];
/* 111 */     a[0] = divdiff[0];
/* 112 */     for (i = 1; i < n; i++) {
/* 113 */       for (int j = 0; j < n - i; j++) {
/* 114 */         double denominator = x[(j + i)] - x[j];
/* 115 */         if (denominator == 0.0D)
/*     */         {
/* 117 */           throw new DuplicateSampleAbscissaException(x[j], j, j + i);
/*     */         }
/* 119 */         divdiff[j] = ((divdiff[(j + 1)] - divdiff[j]) / denominator);
/*     */       }
/* 121 */       a[i] = divdiff[0];
/*     */     }
/*     */     
/* 124 */     return a;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/DividedDifferenceInterpolator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */