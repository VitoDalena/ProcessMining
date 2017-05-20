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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RombergIntegrator
/*     */   extends UnivariateRealIntegratorImpl
/*     */ {
/*     */   private static final long serialVersionUID = -1058849527738180243L;
/*     */   
/*     */   public RombergIntegrator(UnivariateRealFunction f)
/*     */   {
/*  46 */     super(f, 32);
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
/*     */   public double integrate(double min, double max)
/*     */     throws MaxIterationsExceededException, FunctionEvaluationException, IllegalArgumentException
/*     */   {
/*  64 */     int i = 1;int m = this.maximalIterationCount + 1;
/*     */     
/*     */ 
/*  67 */     double[][] t = new double[m][m];
/*     */     
/*  69 */     clearResult();
/*  70 */     verifyInterval(min, max);
/*  71 */     verifyIterationCount();
/*     */     
/*  73 */     TrapezoidIntegrator qtrap = new TrapezoidIntegrator(this.f);
/*  74 */     t[0][0] = qtrap.stage(min, max, 0);
/*  75 */     double olds = t[0][0];
/*  76 */     while (i <= this.maximalIterationCount) {
/*  77 */       t[i][0] = qtrap.stage(min, max, i);
/*  78 */       for (int j = 1; j <= i; j++)
/*     */       {
/*  80 */         double r = (1L << 2 * j) - 1L;
/*  81 */         t[i][j] = (t[i][(j - 1)] + (t[i][(j - 1)] - t[(i - 1)][(j - 1)]) / r);
/*     */       }
/*  83 */       double s = t[i][i];
/*  84 */       if ((i >= this.minimalIterationCount) && 
/*  85 */         (Math.abs(s - olds) <= Math.abs(this.relativeAccuracy * olds))) {
/*  86 */         setResult(s, i);
/*  87 */         return this.result;
/*     */       }
/*     */       
/*  90 */       olds = s;
/*  91 */       i++;
/*     */     }
/*  93 */     throw new MaxIterationsExceededException(this.maximalIterationCount);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void verifyIterationCount()
/*     */     throws IllegalArgumentException
/*     */   {
/* 102 */     super.verifyIterationCount();
/*     */     
/* 104 */     if (this.maximalIterationCount > 32) {
/* 105 */       throw new IllegalArgumentException("Iteration upper limit out of [0, 32] range: " + this.maximalIterationCount);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/RombergIntegrator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */