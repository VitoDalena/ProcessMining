/*     */ package org.apache.commons.math.stat.descriptive.moment;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math.stat.descriptive.AbstractStorelessUnivariateStatistic;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Skewness
/*     */   extends AbstractStorelessUnivariateStatistic
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7101857578996691352L;
/*  46 */   protected ThirdMoment moment = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean incMoment;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Skewness()
/*     */   {
/*  60 */     this.incMoment = true;
/*  61 */     this.moment = new ThirdMoment();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Skewness(ThirdMoment m3)
/*     */   {
/*  69 */     this.incMoment = false;
/*  70 */     this.moment = m3;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void increment(double d)
/*     */   {
/*  77 */     if (this.incMoment) {
/*  78 */       this.moment.increment(d);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getResult()
/*     */   {
/*  91 */     if (this.moment.n < 3L) {
/*  92 */       return NaN.0D;
/*     */     }
/*  94 */     double variance = this.moment.m2 / (this.moment.n - 1L);
/*  95 */     if (variance < 1.0E-19D) {
/*  96 */       return 0.0D;
/*     */     }
/*  98 */     double n0 = this.moment.getN();
/*  99 */     return n0 * this.moment.m3 / ((n0 - 1.0D) * (n0 - 2.0D) * Math.sqrt(variance) * variance);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getN()
/*     */   {
/* 108 */     return this.moment.getN();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 115 */     if (this.incMoment) {
/* 116 */       this.moment.clear();
/*     */     }
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
/*     */ 
/*     */   public double evaluate(double[] values, int begin, int length)
/*     */   {
/* 140 */     double skew = NaN.0D;
/*     */     
/* 142 */     if ((test(values, begin, length)) && (length > 2)) {
/* 143 */       Mean mean = new Mean();
/*     */       
/* 145 */       double m = mean.evaluate(values, begin, length);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 150 */       double accum = 0.0D;
/* 151 */       double accum2 = 0.0D;
/* 152 */       for (int i = begin; i < begin + length; i++) {
/* 153 */         accum += Math.pow(values[i] - m, 2.0D);
/* 154 */         accum2 += values[i] - m;
/*     */       }
/* 156 */       double stdDev = Math.sqrt((accum - Math.pow(accum2, 2.0D) / length) / (length - 1));
/*     */       
/*     */ 
/* 159 */       double accum3 = 0.0D;
/* 160 */       for (int i = begin; i < begin + length; i++) {
/* 161 */         accum3 += Math.pow(values[i] - m, 3.0D);
/*     */       }
/* 163 */       accum3 /= Math.pow(stdDev, 3.0D);
/*     */       
/*     */ 
/* 166 */       double n0 = length;
/*     */       
/*     */ 
/* 169 */       skew = n0 / ((n0 - 1.0D) * (n0 - 2.0D)) * accum3;
/*     */     }
/* 171 */     return skew;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/moment/Skewness.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */