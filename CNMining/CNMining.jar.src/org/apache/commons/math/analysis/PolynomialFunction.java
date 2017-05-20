/*     */ package org.apache.commons.math.analysis;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public class PolynomialFunction
/*     */   implements DifferentiableUnivariateRealFunction, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3322454535052136809L;
/*     */   private double[] coefficients;
/*     */   
/*     */   public PolynomialFunction(double[] c)
/*     */   {
/*  56 */     if (c.length < 1) {
/*  57 */       throw new IllegalArgumentException("Polynomial coefficient array must have postive length.");
/*     */     }
/*  59 */     this.coefficients = new double[c.length];
/*  60 */     System.arraycopy(c, 0, this.coefficients, 0, c.length);
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
/*     */   public double value(double x)
/*     */   {
/*  75 */     return evaluate(this.coefficients, x);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int degree()
/*     */   {
/*  85 */     return this.coefficients.length - 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[] getCoefficients()
/*     */   {
/*  97 */     double[] out = new double[this.coefficients.length];
/*  98 */     System.arraycopy(this.coefficients, 0, out, 0, this.coefficients.length);
/*  99 */     return out;
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
/*     */   protected static double evaluate(double[] coefficients, double argument)
/*     */   {
/* 113 */     int n = coefficients.length;
/* 114 */     if (n < 1) {
/* 115 */       throw new IllegalArgumentException("Coefficient array must have positive length for evaluation");
/*     */     }
/* 117 */     double result = coefficients[(n - 1)];
/* 118 */     for (int j = n - 2; j >= 0; j--) {
/* 119 */       result = argument * result + coefficients[j];
/*     */     }
/* 121 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static double[] differentiate(double[] coefficients)
/*     */   {
/* 133 */     int n = coefficients.length;
/* 134 */     if (n < 1) {
/* 135 */       throw new IllegalArgumentException("Coefficient array must have positive length for differentiation");
/*     */     }
/* 137 */     if (n == 1) {
/* 138 */       return new double[] { 0.0D };
/*     */     }
/* 140 */     double[] result = new double[n - 1];
/* 141 */     for (int i = n - 1; i > 0; i--) {
/* 142 */       result[(i - 1)] = (i * coefficients[i]);
/*     */     }
/* 144 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PolynomialFunction polynomialDerivative()
/*     */   {
/* 153 */     return new PolynomialFunction(differentiate(this.coefficients));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public UnivariateRealFunction derivative()
/*     */   {
/* 162 */     return polynomialDerivative();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/PolynomialFunction.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */