/*     */ package org.apache.commons.math.stat.descriptive.moment;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Kurtosis
/*     */   extends AbstractStorelessUnivariateStatistic
/*     */ {
/*     */   private static final long serialVersionUID = 2784465764798260919L;
/*     */   protected FourthMoment moment;
/*     */   protected boolean incMoment;
/*     */   
/*     */   public Kurtosis()
/*     */   {
/*  61 */     this.incMoment = true;
/*  62 */     this.moment = new FourthMoment();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Kurtosis(FourthMoment m4)
/*     */   {
/*  71 */     this.incMoment = false;
/*  72 */     this.moment = m4;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void increment(double d)
/*     */   {
/*  79 */     if (this.incMoment) {
/*  80 */       this.moment.increment(d);
/*     */     } else {
/*  82 */       throw new IllegalStateException("Statistics constructed from external moments cannot be incremented");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getResult()
/*     */   {
/*  91 */     double kurtosis = NaN.0D;
/*  92 */     if (this.moment.getN() > 3L) {
/*  93 */       double variance = this.moment.m2 / (this.moment.n - 1L);
/*  94 */       if ((this.moment.n <= 3L) || (variance < 1.0E-19D)) {
/*  95 */         kurtosis = 0.0D;
/*     */       } else {
/*  97 */         double n = this.moment.n;
/*  98 */         kurtosis = (n * (n + 1.0D) * this.moment.m4 - 3.0D * this.moment.m2 * this.moment.m2 * (n - 1.0D)) / ((n - 1.0D) * (n - 2.0D) * (n - 3.0D) * variance * variance);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 104 */     return kurtosis;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 111 */     if (this.incMoment) {
/* 112 */       this.moment.clear();
/*     */     } else {
/* 114 */       throw new IllegalStateException("Statistics constructed from external moments cannot be cleared");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getN()
/*     */   {
/* 123 */     return this.moment.getN();
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
/* 146 */     double kurt = NaN.0D;
/*     */     
/* 148 */     if ((test(values, begin, length)) && (length > 3))
/*     */     {
/*     */ 
/* 151 */       Variance variance = new Variance();
/* 152 */       variance.incrementAll(values, begin, length);
/* 153 */       double mean = variance.moment.m1;
/* 154 */       double stdDev = Math.sqrt(variance.getResult());
/*     */       
/*     */ 
/*     */ 
/* 158 */       double accum3 = 0.0D;
/* 159 */       for (int i = begin; i < begin + length; i++) {
/* 160 */         accum3 += Math.pow(values[i] - mean, 4.0D);
/*     */       }
/* 162 */       accum3 /= Math.pow(stdDev, 4.0D);
/*     */       
/*     */ 
/* 165 */       double n0 = length;
/*     */       
/* 167 */       double coefficientOne = n0 * (n0 + 1.0D) / ((n0 - 1.0D) * (n0 - 2.0D) * (n0 - 3.0D));
/*     */       
/* 169 */       double termTwo = 3.0D * Math.pow(n0 - 1.0D, 2.0D) / ((n0 - 2.0D) * (n0 - 3.0D));
/*     */       
/*     */ 
/*     */ 
/* 173 */       kurt = coefficientOne * accum3 - termTwo;
/*     */     }
/* 175 */     return kurt;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/moment/Kurtosis.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */