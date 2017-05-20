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
/*     */ public class TrapezoidIntegrator
/*     */   extends UnivariateRealIntegratorImpl
/*     */ {
/*     */   private static final long serialVersionUID = 4978222553983172543L;
/*     */   private double s;
/*     */   
/*     */   public TrapezoidIntegrator(UnivariateRealFunction f)
/*     */   {
/*  47 */     super(f, 64);
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
/*     */   double stage(double min, double max, int n)
/*     */     throws FunctionEvaluationException
/*     */   {
/*  70 */     double sum = 0.0D;
/*     */     
/*  72 */     if (n == 0) {
/*  73 */       this.s = (0.5D * (max - min) * (this.f.value(min) + this.f.value(max)));
/*  74 */       return this.s;
/*     */     }
/*  76 */     long np = 1L << n - 1;
/*  77 */     double spacing = (max - min) / np;
/*  78 */     double x = min + 0.5D * spacing;
/*  79 */     for (long i = 0L; i < np; i += 1L) {
/*  80 */       sum += this.f.value(x);
/*  81 */       x += spacing;
/*     */     }
/*     */     
/*  84 */     this.s = (0.5D * (this.s + sum * spacing));
/*  85 */     return this.s;
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
/*     */   public double integrate(double min, double max)
/*     */     throws MaxIterationsExceededException, FunctionEvaluationException, IllegalArgumentException
/*     */   {
/* 104 */     int i = 1;
/*     */     
/*     */ 
/* 107 */     clearResult();
/* 108 */     verifyInterval(min, max);
/* 109 */     verifyIterationCount();
/*     */     
/* 111 */     double oldt = stage(min, max, 0);
/* 112 */     while (i <= this.maximalIterationCount) {
/* 113 */       double t = stage(min, max, i);
/* 114 */       if ((i >= this.minimalIterationCount) && 
/* 115 */         (Math.abs(t - oldt) <= Math.abs(this.relativeAccuracy * oldt))) {
/* 116 */         setResult(t, i);
/* 117 */         return this.result;
/*     */       }
/*     */       
/* 120 */       oldt = t;
/* 121 */       i++;
/*     */     }
/* 123 */     throw new MaxIterationsExceededException(this.maximalIterationCount);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void verifyIterationCount()
/*     */     throws IllegalArgumentException
/*     */   {
/* 132 */     super.verifyIterationCount();
/*     */     
/* 134 */     if (this.maximalIterationCount > 64) {
/* 135 */       throw new IllegalArgumentException("Iteration upper limit out of [0, 64] range: " + this.maximalIterationCount);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/TrapezoidIntegrator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */