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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FDistributionImpl
/*     */   extends AbstractContinuousDistribution
/*     */   implements FDistribution, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8516354193418641566L;
/*     */   private double numeratorDegreesOfFreedom;
/*     */   private double denominatorDegreesOfFreedom;
/*     */   
/*     */   public FDistributionImpl(double numeratorDegreesOfFreedom, double denominatorDegreesOfFreedom)
/*     */   {
/*  51 */     setNumeratorDegreesOfFreedom(numeratorDegreesOfFreedom);
/*  52 */     setDenominatorDegreesOfFreedom(denominatorDegreesOfFreedom);
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
/*  72 */     if (x <= 0.0D) {
/*  73 */       ret = 0.0D;
/*     */     } else {
/*  75 */       double n = getNumeratorDegreesOfFreedom();
/*  76 */       double m = getDenominatorDegreesOfFreedom();
/*     */       
/*  78 */       ret = Beta.regularizedBeta(n * x / (m + n * x), 0.5D * n, 0.5D * m);
/*     */     }
/*     */     
/*     */ 
/*  82 */     return ret;
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
/* 119 */     return 0.0D;
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
/* 132 */     return Double.MAX_VALUE;
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
/* 144 */     return getDenominatorDegreesOfFreedom() / (getDenominatorDegreesOfFreedom() - 2.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNumeratorDegreesOfFreedom(double degreesOfFreedom)
/*     */   {
/* 155 */     if (degreesOfFreedom <= 0.0D) {
/* 156 */       throw new IllegalArgumentException("degrees of freedom must be positive.");
/*     */     }
/*     */     
/* 159 */     this.numeratorDegreesOfFreedom = degreesOfFreedom;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getNumeratorDegreesOfFreedom()
/*     */   {
/* 167 */     return this.numeratorDegreesOfFreedom;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDenominatorDegreesOfFreedom(double degreesOfFreedom)
/*     */   {
/* 177 */     if (degreesOfFreedom <= 0.0D) {
/* 178 */       throw new IllegalArgumentException("degrees of freedom must be positive.");
/*     */     }
/*     */     
/* 181 */     this.denominatorDegreesOfFreedom = degreesOfFreedom;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getDenominatorDegreesOfFreedom()
/*     */   {
/* 189 */     return this.denominatorDegreesOfFreedom;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/FDistributionImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */