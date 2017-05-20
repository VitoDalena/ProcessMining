/*     */ package org.apache.commons.math.analysis;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math.FunctionEvaluationException;
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
/*     */ public class SecantSolver
/*     */   extends UnivariateRealSolverImpl
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1984971194738974867L;
/*     */   
/*     */   public SecantSolver(UnivariateRealFunction f)
/*     */   {
/*  51 */     super(f, 100, 1.0E-6D);
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
/*     */   public double solve(double min, double max, double initial)
/*     */     throws MaxIterationsExceededException, FunctionEvaluationException
/*     */   {
/*  70 */     return solve(min, max);
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
/*     */   public double solve(double min, double max)
/*     */     throws MaxIterationsExceededException, FunctionEvaluationException
/*     */   {
/*  87 */     clearResult();
/*  88 */     verifyInterval(min, max);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  95 */     double x0 = min;
/*  96 */     double x1 = max;
/*  97 */     double y0 = this.f.value(x0);
/*  98 */     double y1 = this.f.value(x1);
/*     */     
/*     */ 
/* 101 */     if (y0 * y1 >= 0.0D) {
/* 102 */       throw new IllegalArgumentException("Function values at endpoints do not have different signs.  Endpoints: [" + min + "," + max + "]" + "  Values: [" + y0 + "," + y1 + "]");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 108 */     double x2 = x0;
/* 109 */     double y2 = y0;
/* 110 */     double oldDelta = x2 - x1;
/* 111 */     int i = 0;
/* 112 */     while (i < this.maximalIterationCount) {
/* 113 */       if (Math.abs(y2) < Math.abs(y1)) {
/* 114 */         x0 = x1;
/* 115 */         x1 = x2;
/* 116 */         x2 = x0;
/* 117 */         y0 = y1;
/* 118 */         y1 = y2;
/* 119 */         y2 = y0;
/*     */       }
/* 121 */       if (Math.abs(y1) <= this.functionValueAccuracy) {
/* 122 */         setResult(x1, i);
/* 123 */         return this.result;
/*     */       }
/* 125 */       if (Math.abs(oldDelta) < Math.max(this.relativeAccuracy * Math.abs(x1), this.absoluteAccuracy))
/*     */       {
/* 127 */         setResult(x1, i);
/* 128 */         return this.result; }
/*     */       double delta;
/*     */       double delta;
/* 131 */       if (Math.abs(y1) > Math.abs(y0))
/*     */       {
/* 133 */         delta = 0.5D * oldDelta;
/*     */       } else {
/* 135 */         delta = (x0 - x1) / (1.0D - y0 / y1);
/* 136 */         if (delta / oldDelta > 1.0D)
/*     */         {
/*     */ 
/* 139 */           delta = 0.5D * oldDelta;
/*     */         }
/*     */       }
/* 142 */       x0 = x1;
/* 143 */       y0 = y1;
/* 144 */       x1 += delta;
/* 145 */       y1 = this.f.value(x1);
/* 146 */       if ((y1 > 0.0D ? 1 : 0) == (y2 > 0.0D ? 1 : 0))
/*     */       {
/* 148 */         x2 = x0;
/* 149 */         y2 = y0;
/*     */       }
/* 151 */       oldDelta = x2 - x1;
/* 152 */       i++;
/*     */     }
/* 154 */     throw new MaxIterationsExceededException(this.maximalIterationCount);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/SecantSolver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */