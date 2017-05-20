/*     */ package org.apache.commons.math.distribution;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math.MathException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExponentialDistributionImpl
/*     */   extends AbstractContinuousDistribution
/*     */   implements ExponentialDistribution, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2401296428283614780L;
/*     */   private double mean;
/*     */   
/*     */   public ExponentialDistributionImpl(double mean)
/*     */   {
/*  43 */     setMean(mean);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMean(double mean)
/*     */   {
/*  52 */     if (mean <= 0.0D) {
/*  53 */       throw new IllegalArgumentException("mean must be positive.");
/*     */     }
/*  55 */     this.mean = mean;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMean()
/*     */   {
/*  63 */     return this.mean;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double cumulativeProbability(double x)
/*     */     throws MathException
/*     */   {
/*     */     double ret;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     double ret;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  83 */     if (x <= 0.0D) {
/*  84 */       ret = 0.0D;
/*     */     } else {
/*  86 */       ret = 1.0D - Math.exp(-x / getMean());
/*     */     }
/*  88 */     return ret;
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
/*     */   public double inverseCumulativeProbability(double p)
/*     */     throws MathException
/*     */   {
/* 106 */     if ((p < 0.0D) || (p > 1.0D))
/* 107 */       throw new IllegalArgumentException("probability argument must be between 0 and 1 (inclusive)");
/*     */     double ret;
/* 109 */     double ret; if (p == 1.0D) {
/* 110 */       ret = Double.POSITIVE_INFINITY;
/*     */     } else {
/* 112 */       ret = -getMean() * Math.log(1.0D - p);
/*     */     }
/*     */     
/* 115 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double getDomainLowerBound(double p)
/*     */   {
/* 127 */     return 0.0D;
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
/*     */   protected double getDomainUpperBound(double p)
/*     */   {
/* 142 */     if (p < 0.5D)
/*     */     {
/* 144 */       return getMean();
/*     */     }
/*     */     
/* 147 */     return Double.MAX_VALUE;
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
/*     */   protected double getInitialDomain(double p)
/*     */   {
/* 161 */     if (p < 0.5D)
/*     */     {
/* 163 */       return getMean() * 0.5D;
/*     */     }
/*     */     
/* 166 */     return getMean();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/ExponentialDistributionImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */