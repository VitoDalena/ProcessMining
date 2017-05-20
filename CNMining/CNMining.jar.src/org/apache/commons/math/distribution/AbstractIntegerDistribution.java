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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractIntegerDistribution
/*     */   extends AbstractDistribution
/*     */   implements IntegerDistribution, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -1146319659338487221L;
/*     */   
/*     */   public double cumulativeProbability(double x)
/*     */     throws MathException
/*     */   {
/*  60 */     return cumulativeProbability((int)Math.floor(x));
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
/*     */   public double cumulativeProbability(double x0, double x1)
/*     */     throws MathException
/*     */   {
/*  78 */     if (x0 > x1) {
/*  79 */       throw new IllegalArgumentException("lower endpoint must be less than or equal to upper endpoint");
/*     */     }
/*     */     
/*  82 */     if (Math.floor(x0) < x0) {
/*  83 */       return cumulativeProbability((int)Math.floor(x0) + 1, (int)Math.floor(x1));
/*     */     }
/*     */     
/*  86 */     return cumulativeProbability((int)Math.floor(x0), (int)Math.floor(x1));
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
/*     */   public abstract double cumulativeProbability(int paramInt)
/*     */     throws MathException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double probability(double x)
/*     */   {
/* 115 */     double fl = Math.floor(x);
/* 116 */     if (fl == x) {
/* 117 */       return probability((int)x);
/*     */     }
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
/*     */ 
/*     */ 
/*     */   public double cumulativeProbability(int x0, int x1)
/*     */     throws MathException
/*     */   {
/* 135 */     if (x0 > x1) {
/* 136 */       throw new IllegalArgumentException("lower endpoint must be less than or equal to upper endpoint");
/*     */     }
/*     */     
/* 139 */     return cumulativeProbability(x1) - cumulativeProbability(x0 - 1);
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
/*     */   public int inverseCumulativeProbability(double p)
/*     */     throws MathException
/*     */   {
/* 154 */     if ((p < 0.0D) || (p > 1.0D)) {
/* 155 */       throw new IllegalArgumentException("p must be between 0 and 1.0 (inclusive)");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 161 */     int x0 = getDomainLowerBound(p);
/* 162 */     int x1 = getDomainUpperBound(p);
/*     */     
/* 164 */     while (x0 < x1) {
/* 165 */       int xm = x0 + (x1 - x0) / 2;
/* 166 */       double pm = cumulativeProbability(xm);
/* 167 */       if (pm > p)
/*     */       {
/* 169 */         if (xm == x1)
/*     */         {
/*     */ 
/* 172 */           x1--;
/*     */         }
/*     */         else {
/* 175 */           x1 = xm;
/*     */         }
/*     */         
/*     */       }
/* 179 */       else if (xm == x0)
/*     */       {
/*     */ 
/* 182 */         x0++;
/*     */       }
/*     */       else {
/* 185 */         x0 = xm;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 191 */     double pm = cumulativeProbability(x0);
/* 192 */     while (pm > p) {
/* 193 */       x0--;
/* 194 */       pm = cumulativeProbability(x0);
/*     */     }
/*     */     
/* 197 */     return x0;
/*     */   }
/*     */   
/*     */   protected abstract int getDomainLowerBound(double paramDouble);
/*     */   
/*     */   protected abstract int getDomainUpperBound(double paramDouble);
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/AbstractIntegerDistribution.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */