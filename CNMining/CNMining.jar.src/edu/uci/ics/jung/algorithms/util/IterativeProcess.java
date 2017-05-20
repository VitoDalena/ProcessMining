/*     */ package edu.uci.ics.jung.algorithms.util;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class IterativeProcess
/*     */   implements IterativeContext
/*     */ {
/*     */   private int iterations;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  42 */   private int maximumIterations = 50;
/*     */   
/*     */ 
/*     */ 
/*  46 */   private double desiredPrecision = Double.MIN_VALUE;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double precision;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void evaluate()
/*     */   {
/*  65 */     this.iterations = 0;
/*  66 */     initializeIterations();
/*  67 */     while (this.iterations++ < this.maximumIterations) {
/*  68 */       step();
/*  69 */       this.precision = getPrecision();
/*  70 */       if (hasConverged())
/*     */         break;
/*     */     }
/*  73 */     finalizeIterations();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract void step();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void finalizeIterations() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getDesiredPrecision()
/*     */   {
/*  92 */     return this.desiredPrecision;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getIterations()
/*     */   {
/*  99 */     return this.iterations;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getMaximumIterations()
/*     */   {
/* 106 */     return this.maximumIterations;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getPrecision()
/*     */   {
/* 113 */     return this.precision;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setPrecision(double precision)
/*     */   {
/* 120 */     this.precision = precision;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean hasConverged()
/*     */   {
/* 129 */     return this.precision < this.desiredPrecision;
/*     */   }
/*     */   
/*     */   public boolean done() {
/* 133 */     return hasConverged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void initializeIterations() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reset() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double relativePrecision(double epsilon, double x)
/*     */   {
/* 154 */     return x > this.desiredPrecision ? epsilon / x : epsilon;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setDesiredPrecision(double prec)
/*     */     throws IllegalArgumentException
/*     */   {
/* 161 */     if (prec <= 0.0D)
/* 162 */       throw new IllegalArgumentException("Non-positive precision: " + prec);
/* 163 */     this.desiredPrecision = prec;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setMaximumIterations(int maxIter)
/*     */     throws IllegalArgumentException
/*     */   {
/* 170 */     if (maxIter < 1)
/* 171 */       throw new IllegalArgumentException("Non-positive maximum iteration: " + maxIter);
/* 172 */     this.maximumIterations = maxIter;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/util/IterativeProcess.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */