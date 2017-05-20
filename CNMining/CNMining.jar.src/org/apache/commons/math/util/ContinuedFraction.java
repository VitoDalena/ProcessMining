/*     */ package org.apache.commons.math.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math.ConvergenceException;
/*     */ import org.apache.commons.math.MathException;
/*     */ import org.apache.commons.math.MaxIterationsExceededException;
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
/*     */ 
/*     */ 
/*     */ public abstract class ContinuedFraction
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1768555336266158242L;
/*     */   private static final double DEFAULT_EPSILON = 1.0E-8D;
/*     */   
/*     */   protected abstract double getA(int paramInt, double paramDouble);
/*     */   
/*     */   protected abstract double getB(int paramInt, double paramDouble);
/*     */   
/*     */   public double evaluate(double x)
/*     */     throws MathException
/*     */   {
/*  79 */     return evaluate(x, 1.0E-8D, Integer.MAX_VALUE);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double evaluate(double x, double epsilon)
/*     */     throws MathException
/*     */   {
/*  90 */     return evaluate(x, epsilon, Integer.MAX_VALUE);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double evaluate(double x, int maxIterations)
/*     */     throws MathException
/*     */   {
/* 101 */     return evaluate(x, 1.0E-8D, maxIterations);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double evaluate(double x, double epsilon, int maxIterations)
/*     */     throws MathException
/*     */   {
/* 133 */     double p0 = 1.0D;
/* 134 */     double p1 = getA(0, x);
/* 135 */     double q0 = 0.0D;
/* 136 */     double q1 = 1.0D;
/* 137 */     double c = p1 / q1;
/* 138 */     int n = 0;
/* 139 */     double relativeError = Double.MAX_VALUE;
/* 140 */     while ((n < maxIterations) && (relativeError > epsilon)) {
/* 141 */       n++;
/* 142 */       double a = getA(n, x);
/* 143 */       double b = getB(n, x);
/* 144 */       double p2 = a * p1 + b * p0;
/* 145 */       double q2 = a * q1 + b * q0;
/* 146 */       if ((Double.isInfinite(p2)) || (Double.isInfinite(q2)))
/*     */       {
/* 148 */         if (a != 0.0D) {
/* 149 */           p2 = p1 + b / a * p0;
/* 150 */           q2 = q1 + b / a * q0;
/* 151 */         } else if (b != 0.0D) {
/* 152 */           p2 = a / b * p1 + p0;
/* 153 */           q2 = a / b * q1 + q0;
/*     */         }
/*     */         else {
/* 156 */           throw new ConvergenceException("Continued fraction convergents diverged to +/- infinity for value {0}", new Object[] { new Double(x) });
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 161 */       double r = p2 / q2;
/* 162 */       relativeError = Math.abs(r / c - 1.0D);
/*     */       
/*     */ 
/* 165 */       c = p2 / q2;
/* 166 */       p0 = p1;
/* 167 */       p1 = p2;
/* 168 */       q0 = q1;
/* 169 */       q1 = q2;
/*     */     }
/*     */     
/* 172 */     if (n >= maxIterations) {
/* 173 */       throw new MaxIterationsExceededException(maxIterations, "Continued fraction convergents failed to converge for value {0}", new Object[] { new Double(x) });
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 178 */     return c;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/util/ContinuedFraction.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */