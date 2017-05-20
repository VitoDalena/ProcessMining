/*     */ package org.apache.commons.math.analysis;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math.FunctionEvaluationException;
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
/*     */ public class PolynomialFunctionNewtonForm
/*     */   implements UnivariateRealFunction, Serializable
/*     */ {
/*     */   static final long serialVersionUID = -3353896576191389897L;
/*     */   private double[] coefficients;
/*     */   private double[] a;
/*     */   private double[] c;
/*     */   private boolean coefficientsComputed;
/*     */   
/*     */   PolynomialFunctionNewtonForm(double[] a, double[] c)
/*     */     throws IllegalArgumentException
/*     */   {
/*  74 */     verifyInputArray(a, c);
/*  75 */     this.a = new double[a.length];
/*  76 */     this.c = new double[c.length];
/*  77 */     System.arraycopy(a, 0, this.a, 0, a.length);
/*  78 */     System.arraycopy(c, 0, this.c, 0, c.length);
/*  79 */     this.coefficientsComputed = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double value(double z)
/*     */     throws FunctionEvaluationException
/*     */   {
/*  91 */     return evaluate(this.a, this.c, z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int degree()
/*     */   {
/* 100 */     return this.c.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[] getNewtonCoefficients()
/*     */   {
/* 111 */     double[] out = new double[this.a.length];
/* 112 */     System.arraycopy(this.a, 0, out, 0, this.a.length);
/* 113 */     return out;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[] getCenters()
/*     */   {
/* 124 */     double[] out = new double[this.c.length];
/* 125 */     System.arraycopy(this.c, 0, out, 0, this.c.length);
/* 126 */     return out;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[] getCoefficients()
/*     */   {
/* 137 */     if (!this.coefficientsComputed) {
/* 138 */       computeCoefficients();
/*     */     }
/* 140 */     double[] out = new double[this.coefficients.length];
/* 141 */     System.arraycopy(this.coefficients, 0, out, 0, this.coefficients.length);
/* 142 */     return out;
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
/*     */   public static double evaluate(double[] a, double[] c, double z)
/*     */     throws FunctionEvaluationException, IllegalArgumentException
/*     */   {
/* 160 */     verifyInputArray(a, c);
/*     */     
/* 162 */     int n = c.length;
/* 163 */     double value = a[n];
/* 164 */     for (int i = n - 1; i >= 0; i--) {
/* 165 */       value = a[i] + (z - c[i]) * value;
/*     */     }
/*     */     
/* 168 */     return value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void computeCoefficients()
/*     */   {
/* 176 */     int n = degree();
/*     */     
/* 178 */     this.coefficients = new double[n + 1];
/* 179 */     for (int i = 0; i <= n; i++) {
/* 180 */       this.coefficients[i] = 0.0D;
/*     */     }
/*     */     
/* 183 */     this.coefficients[0] = this.a[n];
/* 184 */     for (i = n - 1; i >= 0; i--) {
/* 185 */       for (int j = n - i; j > 0; j--) {
/* 186 */         this.coefficients[j] = (this.coefficients[(j - 1)] - this.c[i] * this.coefficients[j]);
/*     */       }
/* 188 */       this.coefficients[0] = (this.a[i] - this.c[i] * this.coefficients[0]);
/*     */     }
/*     */     
/* 191 */     this.coefficientsComputed = true;
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
/*     */   protected static void verifyInputArray(double[] a, double[] c)
/*     */     throws IllegalArgumentException
/*     */   {
/* 209 */     if ((a.length < 1) || (c.length < 1)) {
/* 210 */       throw new IllegalArgumentException("Input arrays must not be empty.");
/*     */     }
/*     */     
/* 213 */     if (a.length != c.length + 1) {
/* 214 */       throw new IllegalArgumentException("Bad input array sizes, should have difference 1.");
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/PolynomialFunctionNewtonForm.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */