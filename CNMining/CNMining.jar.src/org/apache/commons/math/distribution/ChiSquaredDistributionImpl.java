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
/*     */ public class ChiSquaredDistributionImpl
/*     */   extends AbstractContinuousDistribution
/*     */   implements ChiSquaredDistribution, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8352658048349159782L;
/*     */   private GammaDistribution gamma;
/*     */   
/*     */   public ChiSquaredDistributionImpl(double df)
/*     */   {
/*  43 */     this(df, new GammaDistributionImpl(df / 2.0D, 2.0D));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ChiSquaredDistributionImpl(double df, GammaDistribution g)
/*     */   {
/*  54 */     setGamma(g);
/*  55 */     setDegreesOfFreedom(df);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDegreesOfFreedom(double degreesOfFreedom)
/*     */   {
/*  63 */     getGamma().setAlpha(degreesOfFreedom / 2.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getDegreesOfFreedom()
/*     */   {
/*  71 */     return getGamma().getAlpha() * 2.0D;
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
/*  82 */     return getGamma().cumulativeProbability(x);
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
/* 100 */     if (p == 0.0D) {
/* 101 */       return 0.0D;
/*     */     }
/* 103 */     if (p == 1.0D) {
/* 104 */       return Double.POSITIVE_INFINITY;
/*     */     }
/* 106 */     return super.inverseCumulativeProbability(p);
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
/*     */   protected double getDomainLowerBound(double p)
/*     */   {
/* 119 */     return Double.MIN_VALUE * getGamma().getBeta();
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
/* 137 */     if (p < 0.5D)
/*     */     {
/* 139 */       ret = getDegreesOfFreedom();
/*     */     }
/*     */     else {
/* 142 */       ret = Double.MAX_VALUE;
/*     */     }
/*     */     
/* 145 */     return ret;
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
/* 162 */     if (p < 0.5D)
/*     */     {
/* 164 */       ret = getDegreesOfFreedom() * 0.5D;
/*     */     }
/*     */     else {
/* 167 */       ret = getDegreesOfFreedom();
/*     */     }
/*     */     
/* 170 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGamma(GammaDistribution g)
/*     */   {
/* 180 */     this.gamma = g;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private GammaDistribution getGamma()
/*     */   {
/* 189 */     return this.gamma;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/ChiSquaredDistributionImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */