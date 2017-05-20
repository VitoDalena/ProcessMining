/*     */ package org.apache.commons.math.analysis;
/*     */ 
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
/*     */ public class BisectionSolver
/*     */   extends UnivariateRealSolverImpl
/*     */ {
/*     */   private static final long serialVersionUID = 4963578633786538912L;
/*     */   
/*     */   public BisectionSolver(UnivariateRealFunction f)
/*     */   {
/*  41 */     super(f, 100, 1.0E-6D);
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
/*     */   public double solve(double min, double max, double initial)
/*     */     throws MaxIterationsExceededException, FunctionEvaluationException
/*     */   {
/*  59 */     return solve(min, max);
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
/*  76 */     clearResult();
/*  77 */     verifyInterval(min, max);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  82 */     int i = 0;
/*  83 */     while (i < this.maximalIterationCount) {
/*  84 */       double m = UnivariateRealSolverUtils.midpoint(min, max);
/*  85 */       double fmin = this.f.value(min);
/*  86 */       double fm = this.f.value(m);
/*     */       
/*  88 */       if (fm * fmin > 0.0D)
/*     */       {
/*  90 */         min = m;
/*     */       }
/*     */       else {
/*  93 */         max = m;
/*     */       }
/*     */       
/*  96 */       if (Math.abs(max - min) <= this.absoluteAccuracy) {
/*  97 */         m = UnivariateRealSolverUtils.midpoint(min, max);
/*  98 */         setResult(m, i);
/*  99 */         return m;
/*     */       }
/* 101 */       i++;
/*     */     }
/*     */     
/* 104 */     throw new MaxIterationsExceededException(this.maximalIterationCount);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/BisectionSolver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */