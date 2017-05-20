/*     */ package org.apache.commons.math.distribution;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math.MathException;
/*     */ import org.apache.commons.math.special.Beta;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TDistributionImpl
/*     */   extends AbstractContinuousDistribution
/*     */   implements TDistribution, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -5852615386664158222L;
/*     */   private double degreesOfFreedom;
/*     */   
/*     */   public TDistributionImpl(double degreesOfFreedom)
/*     */   {
/*  46 */     setDegreesOfFreedom(degreesOfFreedom);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDegreesOfFreedom(double degreesOfFreedom)
/*     */   {
/*  54 */     if (degreesOfFreedom <= 0.0D) {
/*  55 */       throw new IllegalArgumentException("degrees of freedom must be positive.");
/*     */     }
/*  57 */     this.degreesOfFreedom = degreesOfFreedom;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getDegreesOfFreedom()
/*     */   {
/*  65 */     return this.degreesOfFreedom;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double cumulativeProbability(double x)
/*     */     throws MathException
/*     */   {
/*     */     double ret;
/*     */     
/*     */     double ret;
/*     */     
/*  77 */     if (x == 0.0D) {
/*  78 */       ret = 0.5D;
/*     */     } else {
/*  80 */       double t = Beta.regularizedBeta(getDegreesOfFreedom() / (getDegreesOfFreedom() + x * x), 0.5D * getDegreesOfFreedom(), 0.5D);
/*     */       
/*     */ 
/*     */       double ret;
/*     */       
/*  85 */       if (x < 0.0D) {
/*  86 */         ret = 0.5D * t;
/*     */       } else {
/*  88 */         ret = 1.0D - 0.5D * t;
/*     */       }
/*     */     }
/*     */     
/*  92 */     return ret;
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
/*     */   public double inverseCumulativeProbability(double p)
/*     */     throws MathException
/*     */   {
/* 111 */     if (p == 0.0D) {
/* 112 */       return Double.NEGATIVE_INFINITY;
/*     */     }
/* 114 */     if (p == 1.0D) {
/* 115 */       return Double.POSITIVE_INFINITY;
/*     */     }
/* 117 */     return super.inverseCumulativeProbability(p);
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
/* 130 */     return -1.7976931348623157E308D;
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
/*     */   protected double getDomainUpperBound(double p)
/*     */   {
/* 143 */     return Double.MAX_VALUE;
/*     */   }
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
/* 155 */     return 0.0D;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/TDistributionImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */