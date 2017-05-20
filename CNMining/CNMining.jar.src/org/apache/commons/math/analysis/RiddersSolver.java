/*     */ package org.apache.commons.math.analysis;
/*     */ 
/*     */ import org.apache.commons.math.FunctionEvaluationException;
/*     */ import org.apache.commons.math.MaxIterationsExceededException;
/*     */ import org.apache.commons.math.util.MathUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RiddersSolver
/*     */   extends UnivariateRealSolverImpl
/*     */ {
/*     */   private static final long serialVersionUID = -4703139035737911735L;
/*     */   
/*     */   public RiddersSolver(UnivariateRealFunction f)
/*     */   {
/*  46 */     super(f, 100, 1.0E-6D);
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
/*     */   public double solve(double min, double max, double initial)
/*     */     throws MaxIterationsExceededException, FunctionEvaluationException
/*     */   {
/*  67 */     if (this.f.value(min) == 0.0D) return min;
/*  68 */     if (this.f.value(max) == 0.0D) return max;
/*  69 */     if (this.f.value(initial) == 0.0D) { return initial;
/*     */     }
/*  71 */     verifyBracketing(min, max, this.f);
/*  72 */     verifySequence(min, initial, max);
/*  73 */     if (isBracketing(min, initial, this.f)) {
/*  74 */       return solve(min, initial);
/*     */     }
/*  76 */     return solve(initial, max);
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
/*     */   public double solve(double min, double max)
/*     */     throws MaxIterationsExceededException, FunctionEvaluationException
/*     */   {
/* 101 */     double x1 = min;double y1 = this.f.value(x1);
/* 102 */     double x2 = max;double y2 = this.f.value(x2);
/*     */     
/*     */ 
/* 105 */     if (y1 == 0.0D) return min;
/* 106 */     if (y2 == 0.0D) return max;
/* 107 */     verifyBracketing(min, max, this.f);
/*     */     
/* 109 */     int i = 1;
/* 110 */     double oldx = Double.POSITIVE_INFINITY;
/* 111 */     while (i <= this.maximalIterationCount)
/*     */     {
/* 113 */       double x3 = 0.5D * (x1 + x2);
/* 114 */       double y3 = this.f.value(x3);
/* 115 */       if (Math.abs(y3) <= this.functionValueAccuracy) {
/* 116 */         setResult(x3, i);
/* 117 */         return this.result;
/*     */       }
/* 119 */       double delta = 1.0D - y1 * y2 / (y3 * y3);
/* 120 */       double correction = MathUtils.sign(y2) * MathUtils.sign(y3) * (x3 - x1) / Math.sqrt(delta);
/*     */       
/* 122 */       double x = x3 - correction;
/* 123 */       double y = this.f.value(x);
/*     */       
/*     */ 
/* 126 */       double tolerance = Math.max(this.relativeAccuracy * Math.abs(x), this.absoluteAccuracy);
/* 127 */       if (Math.abs(x - oldx) <= tolerance) {
/* 128 */         setResult(x, i);
/* 129 */         return this.result;
/*     */       }
/* 131 */       if (Math.abs(y) <= this.functionValueAccuracy) {
/* 132 */         setResult(x, i);
/* 133 */         return this.result;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 138 */       if (correction > 0.0D) {
/* 139 */         if (MathUtils.sign(y1) + MathUtils.sign(y) == 0.0D) {
/* 140 */           x2 = x;y2 = y;
/*     */         } else {
/* 142 */           x1 = x;x2 = x3;
/* 143 */           y1 = y;y2 = y3;
/*     */         }
/*     */       }
/* 146 */       else if (MathUtils.sign(y2) + MathUtils.sign(y) == 0.0D) {
/* 147 */         x1 = x;y1 = y;
/*     */       } else {
/* 149 */         x1 = x3;x2 = x;
/* 150 */         y1 = y3;y2 = y;
/*     */       }
/*     */       
/* 153 */       oldx = x;
/* 154 */       i++;
/*     */     }
/* 156 */     throw new MaxIterationsExceededException(this.maximalIterationCount);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/RiddersSolver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */