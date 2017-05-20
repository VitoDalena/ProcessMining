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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Variance
/*     */   extends AbstractStorelessUnivariateStatistic
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -9111962718267217978L;
/*  71 */   protected SecondMoment moment = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  78 */   protected boolean incMoment = true;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  85 */   private boolean isBiasCorrected = true;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Variance()
/*     */   {
/*  92 */     this.moment = new SecondMoment();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Variance(SecondMoment m2)
/*     */   {
/* 102 */     this.incMoment = false;
/* 103 */     this.moment = m2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Variance(boolean isBiasCorrected)
/*     */   {
/* 115 */     this.moment = new SecondMoment();
/* 116 */     this.isBiasCorrected = isBiasCorrected;
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
/*     */   public Variance(boolean isBiasCorrected, SecondMoment m2)
/*     */   {
/* 129 */     this.incMoment = false;
/* 130 */     this.moment = m2;
/* 131 */     this.isBiasCorrected = isBiasCorrected;
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
/*     */   public void increment(double d)
/*     */   {
/* 144 */     if (this.incMoment) {
/* 145 */       this.moment.increment(d);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getResult()
/*     */   {
/* 153 */     if (this.moment.n == 0L)
/* 154 */       return NaN.0D;
/* 155 */     if (this.moment.n == 1L) {
/* 156 */       return 0.0D;
/*     */     }
/* 158 */     if (this.isBiasCorrected) {
/* 159 */       return this.moment.m2 / (this.moment.n - 1.0D);
/*     */     }
/* 161 */     return this.moment.m2 / this.moment.n;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getN()
/*     */   {
/* 170 */     return this.moment.getN();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 177 */     if (this.incMoment) {
/* 178 */       this.moment.clear();
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
/*     */   public double evaluate(double[] values)
/*     */   {
/* 199 */     if (values == null) {
/* 200 */       throw new IllegalArgumentException("input values array is null");
/*     */     }
/* 202 */     return evaluate(values, 0, values.length);
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
/*     */ 
/*     */ 
/*     */   public double evaluate(double[] values, int begin, int length)
/*     */   {
/* 227 */     double var = NaN.0D;
/*     */     
/* 229 */     if (test(values, begin, length)) {
/* 230 */       clear();
/* 231 */       if (length == 1) {
/* 232 */         var = 0.0D;
/* 233 */       } else if (length > 1) {
/* 234 */         Mean mean = new Mean();
/* 235 */         double m = mean.evaluate(values, begin, length);
/* 236 */         var = evaluate(values, m, begin, length);
/*     */       }
/*     */     }
/* 239 */     return var;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double evaluate(double[] values, double mean, int begin, int length)
/*     */   {
/* 271 */     double var = NaN.0D;
/*     */     
/* 273 */     if (test(values, begin, length)) {
/* 274 */       if (length == 1) {
/* 275 */         var = 0.0D;
/* 276 */       } else if (length > 1) {
/* 277 */         double accum = 0.0D;
/* 278 */         double dev = 0.0D;
/* 279 */         double accum2 = 0.0D;
/* 280 */         for (int i = begin; i < begin + length; i++) {
/* 281 */           dev = values[i] - mean;
/* 282 */           accum += dev * dev;
/* 283 */           accum2 += dev;
/*     */         }
/* 285 */         double len = length;
/* 286 */         if (this.isBiasCorrected) {
/* 287 */           var = (accum - accum2 * accum2 / len) / (len - 1.0D);
/*     */         } else {
/* 289 */           var = (accum - accum2 * accum2 / len) / len;
/*     */         }
/*     */       }
/*     */     }
/* 293 */     return var;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double evaluate(double[] values, double mean)
/*     */   {
/* 322 */     return evaluate(values, mean, 0, values.length);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isBiasCorrected()
/*     */   {
/* 329 */     return this.isBiasCorrected;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setBiasCorrected(boolean isBiasCorrected)
/*     */   {
/* 336 */     this.isBiasCorrected = isBiasCorrected;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/moment/Variance.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */