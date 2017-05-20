/*     */ package org.apache.commons.math.special;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math.MathException;
/*     */ import org.apache.commons.math.MaxIterationsExceededException;
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
/*     */ public class Gamma
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6587513359895466954L;
/*     */   private static final double DEFAULT_EPSILON = 1.0E-14D;
/*  40 */   private static double[] lanczos = { 0.9999999999999971D, 57.15623566586292D, -59.59796035547549D, 14.136097974741746D, -0.4919138160976202D, 3.399464998481189E-5D, 4.652362892704858E-5D, -9.837447530487956E-5D, 1.580887032249125E-4D, -2.1026444172410488E-4D, 2.1743961811521265E-4D, -1.643181065367639E-4D, 8.441822398385275E-5D, -2.6190838401581408E-5D, 3.6899182659531625E-6D };
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
/*  60 */   private static final double HALF_LOG_2_PI = 0.5D * Math.log(6.283185307179586D);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double logGamma(double x)
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
/*     */ 
/*  90 */     if ((Double.isNaN(x)) || (x <= 0.0D)) {
/*  91 */       ret = NaN.0D;
/*     */     } else {
/*  93 */       double g = 4.7421875D;
/*     */       
/*  95 */       double sum = 0.0D;
/*  96 */       for (int i = lanczos.length - 1; i > 0; i--) {
/*  97 */         sum += lanczos[i] / (x + i);
/*     */       }
/*  99 */       sum += lanczos[0];
/*     */       
/* 101 */       double tmp = x + g + 0.5D;
/* 102 */       ret = (x + 0.5D) * Math.log(tmp) - tmp + HALF_LOG_2_PI + Math.log(sum / x);
/*     */     }
/*     */     
/*     */ 
/* 106 */     return ret;
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
/*     */   public static double regularizedGammaP(double a, double x)
/*     */     throws MathException
/*     */   {
/* 120 */     return regularizedGammaP(a, x, 1.0E-14D, Integer.MAX_VALUE);
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
/*     */   public static double regularizedGammaP(double a, double x, double epsilon, int maxIterations)
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
/*     */ 
/*     */ 
/*     */ 
/* 158 */     if ((Double.isNaN(a)) || (Double.isNaN(x)) || (a <= 0.0D) || (x < 0.0D)) {
/* 159 */       ret = NaN.0D; } else { double ret;
/* 160 */       if (x == 0.0D) {
/* 161 */         ret = 0.0D; } else { double ret;
/* 162 */         if ((a >= 1.0D) && (x > a))
/*     */         {
/*     */ 
/* 165 */           ret = 1.0D - regularizedGammaQ(a, x, epsilon, maxIterations);
/*     */         }
/*     */         else {
/* 168 */           double n = 0.0D;
/* 169 */           double an = 1.0D / a;
/* 170 */           double sum = an;
/* 171 */           while ((Math.abs(an) > epsilon) && (n < maxIterations))
/*     */           {
/* 173 */             n += 1.0D;
/* 174 */             an *= x / (a + n);
/*     */             
/*     */ 
/* 177 */             sum += an;
/*     */           }
/* 179 */           if (n >= maxIterations) {
/* 180 */             throw new MaxIterationsExceededException(maxIterations);
/*     */           }
/* 182 */           ret = Math.exp(-x + a * Math.log(x) - logGamma(a)) * sum;
/*     */         }
/*     */       }
/*     */     }
/* 186 */     return ret;
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
/*     */   public static double regularizedGammaQ(double a, double x)
/*     */     throws MathException
/*     */   {
/* 200 */     return regularizedGammaQ(a, x, 1.0E-14D, Integer.MAX_VALUE);
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
/*     */   public static double regularizedGammaQ(double a, double x, double epsilon, int maxIterations)
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
/*     */ 
/* 233 */     if ((Double.isNaN(a)) || (Double.isNaN(x)) || (a <= 0.0D) || (x < 0.0D)) {
/* 234 */       ret = NaN.0D; } else { double ret;
/* 235 */       if (x == 0.0D) {
/* 236 */         ret = 1.0D; } else { double ret;
/* 237 */         if ((x < a) || (a < 1.0D))
/*     */         {
/*     */ 
/* 240 */           ret = 1.0D - regularizedGammaP(a, x, epsilon, maxIterations);
/*     */         }
/*     */         else {
/* 243 */           ContinuedFraction cf = new ContinuedFraction() {
/*     */             private static final long serialVersionUID = 5378525034886164398L;
/*     */             private final double val$a;
/*     */             
/*     */             protected double getA(int n, double x) {
/* 248 */               return 2.0D * n + 1.0D - this.val$a + x;
/*     */             }
/*     */             
/*     */             protected double getB(int n, double x) {
/* 252 */               return n * (this.val$a - n);
/*     */             }
/*     */             
/* 255 */           };
/* 256 */           ret = 1.0D / cf.evaluate(x, epsilon, maxIterations);
/* 257 */           ret = Math.exp(-x + a * Math.log(x) - logGamma(a)) * ret;
/*     */         }
/*     */       } }
/* 260 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/special/Gamma.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */