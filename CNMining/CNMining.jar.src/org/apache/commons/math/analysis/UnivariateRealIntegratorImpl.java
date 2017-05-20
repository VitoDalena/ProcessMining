/*     */ package org.apache.commons.math.analysis;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class UnivariateRealIntegratorImpl
/*     */   implements UnivariateRealIntegrator, Serializable
/*     */ {
/*     */   static final long serialVersionUID = -3365294665201465048L;
/*     */   protected double relativeAccuracy;
/*     */   protected int maximalIterationCount;
/*     */   protected int minimalIterationCount;
/*     */   protected double defaultRelativeAccuracy;
/*     */   protected int defaultMaximalIterationCount;
/*     */   protected int defaultMinimalIterationCount;
/*  52 */   protected boolean resultComputed = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double result;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int iterationCount;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected UnivariateRealFunction f;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected UnivariateRealIntegratorImpl(UnivariateRealFunction f, int defaultMaximalIterationCount)
/*     */     throws IllegalArgumentException
/*     */   {
/*  75 */     if (f == null) {
/*  76 */       throw new IllegalArgumentException("Function can not be null.");
/*     */     }
/*     */     
/*  79 */     this.f = f;
/*     */     
/*  81 */     this.defaultMaximalIterationCount = defaultMaximalIterationCount;
/*  82 */     this.maximalIterationCount = defaultMaximalIterationCount;
/*     */     
/*  84 */     this.defaultRelativeAccuracy = 1.0E-6D;
/*  85 */     this.relativeAccuracy = this.defaultRelativeAccuracy;
/*  86 */     this.defaultMinimalIterationCount = 3;
/*  87 */     this.minimalIterationCount = this.defaultMinimalIterationCount;
/*     */     
/*  89 */     verifyIterationCount();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getResult()
/*     */     throws IllegalStateException
/*     */   {
/*  99 */     if (this.resultComputed) {
/* 100 */       return this.result;
/*     */     }
/* 102 */     throw new IllegalStateException("No result available.");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getIterationCount()
/*     */     throws IllegalStateException
/*     */   {
/* 113 */     if (this.resultComputed) {
/* 114 */       return this.iterationCount;
/*     */     }
/* 116 */     throw new IllegalStateException("No result available.");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final void setResult(double result, int iterationCount)
/*     */   {
/* 127 */     this.result = result;
/* 128 */     this.iterationCount = iterationCount;
/* 129 */     this.resultComputed = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected final void clearResult()
/*     */   {
/* 136 */     this.resultComputed = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMaximalIterationCount(int count)
/*     */   {
/* 145 */     this.maximalIterationCount = count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMaximalIterationCount()
/*     */   {
/* 154 */     return this.maximalIterationCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void resetMaximalIterationCount()
/*     */   {
/* 161 */     this.maximalIterationCount = this.defaultMaximalIterationCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMinimalIterationCount(int count)
/*     */   {
/* 170 */     this.minimalIterationCount = count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMinimalIterationCount()
/*     */   {
/* 179 */     return this.minimalIterationCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void resetMinimalIterationCount()
/*     */   {
/* 186 */     this.minimalIterationCount = this.defaultMinimalIterationCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRelativeAccuracy(double accuracy)
/*     */   {
/* 197 */     this.relativeAccuracy = accuracy;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getRelativeAccuracy()
/*     */   {
/* 206 */     return this.relativeAccuracy;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void resetRelativeAccuracy()
/*     */   {
/* 213 */     this.relativeAccuracy = this.defaultRelativeAccuracy;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean isSequence(double start, double mid, double end)
/*     */   {
/* 225 */     return (start < mid) && (mid < end);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void verifyInterval(double lower, double upper)
/*     */     throws IllegalArgumentException
/*     */   {
/* 237 */     if (lower >= upper) {
/* 238 */       throw new IllegalArgumentException("Endpoints do not specify an interval: [" + lower + ", " + upper + "]");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void verifyIterationCount()
/*     */     throws IllegalArgumentException
/*     */   {
/* 250 */     if (!isSequence(0.0D, this.minimalIterationCount, this.maximalIterationCount + 1)) {
/* 251 */       throw new IllegalArgumentException("Invalid iteration limits: min=" + this.minimalIterationCount + " max=" + this.maximalIterationCount);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/UnivariateRealIntegratorImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */