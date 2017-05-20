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
/*     */ public class SimpsonIntegrator
/*     */   extends UnivariateRealIntegratorImpl
/*     */ {
/*     */   private static final long serialVersionUID = 3405465123320678216L;
/*     */   
/*     */   public SimpsonIntegrator(UnivariateRealFunction f)
/*     */   {
/*  45 */     super(f, 64);
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
/*  63 */     int i = 1;
/*     */     
/*     */ 
/*  66 */     clearResult();
/*  67 */     verifyInterval(min, max);
/*  68 */     verifyIterationCount();
/*     */     
/*  70 */     TrapezoidIntegrator qtrap = new TrapezoidIntegrator(this.f);
/*  71 */     if (this.minimalIterationCount == 1) {
/*  72 */       double s = (4.0D * qtrap.stage(min, max, 1) - qtrap.stage(min, max, 0)) / 3.0D;
/*  73 */       setResult(s, 1);
/*  74 */       return this.result;
/*     */     }
/*     */     
/*  77 */     double olds = 0.0D;
/*  78 */     double oldt = qtrap.stage(min, max, 0);
/*  79 */     while (i <= this.maximalIterationCount) {
/*  80 */       double t = qtrap.stage(min, max, i);
/*  81 */       double s = (4.0D * t - oldt) / 3.0D;
/*  82 */       if ((i >= this.minimalIterationCount) && 
/*  83 */         (Math.abs(s - olds) <= Math.abs(this.relativeAccuracy * olds))) {
/*  84 */         setResult(s, i);
/*  85 */         return this.result;
/*     */       }
/*     */       
/*  88 */       olds = s;
/*  89 */       oldt = t;
/*  90 */       i++;
/*     */     }
/*  92 */     throw new MaxIterationsExceededException(this.maximalIterationCount);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void verifyIterationCount()
/*     */     throws IllegalArgumentException
/*     */   {
/* 101 */     super.verifyIterationCount();
/*     */     
/* 103 */     if (this.maximalIterationCount > 64) {
/* 104 */       throw new IllegalArgumentException("Iteration upper limit out of [0, 64] range: " + this.maximalIterationCount);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/SimpsonIntegrator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */