/*     */ package org.apache.commons.math.distribution;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math.MathException;
/*     */ import org.apache.commons.math.special.Gamma;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GammaDistributionImpl
/*     */   extends AbstractContinuousDistribution
/*     */   implements GammaDistribution, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3239549463135430361L;
/*     */   private double alpha;
/*     */   private double beta;
/*     */   
/*     */   public GammaDistributionImpl(double alpha, double beta)
/*     */   {
/*  48 */     setAlpha(alpha);
/*  49 */     setBeta(beta);
/*     */   }
/*     */   
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
/*     */ 
/*     */     double ret;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  72 */     if (x <= 0.0D) {
/*  73 */       ret = 0.0D;
/*     */     } else {
/*  75 */       ret = Gamma.regularizedGammaP(getAlpha(), x / getBeta());
/*     */     }
/*     */     
/*  78 */     return ret;
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
/*  96 */     if (p == 0.0D) {
/*  97 */       return 0.0D;
/*     */     }
/*  99 */     if (p == 1.0D) {
/* 100 */       return Double.POSITIVE_INFINITY;
/*     */     }
/* 102 */     return super.inverseCumulativeProbability(p);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAlpha(double alpha)
/*     */   {
/* 111 */     if (alpha <= 0.0D) {
/* 112 */       throw new IllegalArgumentException("alpha must be positive");
/*     */     }
/* 114 */     this.alpha = alpha;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getAlpha()
/*     */   {
/* 122 */     return this.alpha;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBeta(double beta)
/*     */   {
/* 131 */     if (beta <= 0.0D) {
/* 132 */       throw new IllegalArgumentException("beta must be positive");
/*     */     }
/* 134 */     this.beta = beta;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getBeta()
/*     */   {
/* 142 */     return this.beta;
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
/*     */   protected double getDomainLowerBound(double p)
/*     */   {
/* 156 */     return Double.MIN_VALUE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double getDomainUpperBound(double p)
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
/* 175 */     if (p < 0.5D)
/*     */     {
/* 177 */       ret = getAlpha() * getBeta();
/*     */     }
/*     */     else {
/* 180 */       ret = Double.MAX_VALUE;
/*     */     }
/*     */     
/* 183 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double getInitialDomain(double p)
/*     */   {
/*     */     double ret;
/*     */     
/*     */ 
/*     */ 
/*     */     double ret;
/*     */     
/*     */ 
/*     */ 
/* 200 */     if (p < 0.5D)
/*     */     {
/* 202 */       ret = getAlpha() * getBeta() * 0.5D;
/*     */     }
/*     */     else {
/* 205 */       ret = getAlpha() * getBeta();
/*     */     }
/*     */     
/* 208 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/GammaDistributionImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */