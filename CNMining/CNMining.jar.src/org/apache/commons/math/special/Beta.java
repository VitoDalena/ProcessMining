/*     */ package org.apache.commons.math.special;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math.MathException;
/*     */ import org.apache.commons.math.util.ContinuedFraction;
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
/*     */ public class Beta
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3833485397404128220L;
/*     */   private static final double DEFAULT_EPSILON = 1.0E-14D;
/*     */   
/*     */   public static double regularizedBeta(double x, double a, double b)
/*     */     throws MathException
/*     */   {
/*  59 */     return regularizedBeta(x, a, b, 1.0E-14D, Integer.MAX_VALUE);
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
/*     */   public static double regularizedBeta(double x, double a, double b, double epsilon)
/*     */     throws MathException
/*     */   {
/*  79 */     return regularizedBeta(x, a, b, epsilon, Integer.MAX_VALUE);
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
/*     */   public static double regularizedBeta(double x, double a, double b, int maxIterations)
/*     */     throws MathException
/*     */   {
/*  95 */     return regularizedBeta(x, a, b, 1.0E-14D, maxIterations);
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
/*     */   public static double regularizedBeta(double x, double a, double b, double epsilon, int maxIterations)
/*     */     throws MathException
/*     */   {
/*     */     double ret;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     double ret;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 126 */     if ((Double.isNaN(x)) || (Double.isNaN(a)) || (Double.isNaN(b)) || (x < 0.0D) || (x > 1.0D) || (a <= 0.0D) || (b <= 0.0D))
/*     */     {
/*     */ 
/* 129 */       ret = NaN.0D; } else { double ret;
/* 130 */       if (x > (a + 1.0D) / (a + b + 2.0D)) {
/* 131 */         ret = 1.0D - regularizedBeta(1.0D - x, b, a, epsilon, maxIterations);
/*     */       } else {
/* 133 */         ContinuedFraction fraction = new ContinuedFraction() {
/*     */           private static final long serialVersionUID = -7658917278956100597L;
/*     */           private final double val$b;
/*     */           private final double val$a;
/*     */           
/*     */           protected double getB(int n, double x) { double ret;
/*     */             double ret;
/* 140 */             if (n % 2 == 0) {
/* 141 */               double m = n / 2.0D;
/* 142 */               ret = m * (this.val$b - m) * x / ((this.val$a + 2.0D * m - 1.0D) * (this.val$a + 2.0D * m));
/*     */             }
/*     */             else {
/* 145 */               double m = (n - 1.0D) / 2.0D;
/* 146 */               ret = -((this.val$a + m) * (this.val$a + this.val$b + m) * x) / ((this.val$a + 2.0D * m) * (this.val$a + 2.0D * m + 1.0D));
/*     */             }
/*     */             
/* 149 */             return ret;
/*     */           }
/*     */           
/*     */           protected double getA(int n, double x) {
/* 153 */             return 1.0D;
/*     */           }
/* 155 */         };
/* 156 */         ret = Math.exp(a * Math.log(x) + b * Math.log(1.0D - x) - Math.log(a) - logBeta(a, b, epsilon, maxIterations)) * 1.0D / fraction.evaluate(x, epsilon, maxIterations);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 161 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double logBeta(double a, double b)
/*     */   {
/* 172 */     return logBeta(a, b, 1.0E-14D, Integer.MAX_VALUE);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double logBeta(double a, double b, double epsilon, int maxIterations)
/*     */   {
/*     */     double ret;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     double ret;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 197 */     if ((Double.isNaN(a)) || (Double.isNaN(b)) || (a <= 0.0D) || (b <= 0.0D)) {
/* 198 */       ret = NaN.0D;
/*     */     } else {
/* 200 */       ret = Gamma.logGamma(a) + Gamma.logGamma(b) - Gamma.logGamma(a + b);
/*     */     }
/*     */     
/*     */ 
/* 204 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/special/Beta.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */