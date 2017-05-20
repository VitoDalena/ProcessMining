/*     */ package org.apache.commons.math.stat.descriptive.rank;
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
/*     */ public class Min
/*     */   extends AbstractStorelessUnivariateStatistic
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2941995784909003131L;
/*     */   private long n;
/*     */   private double value;
/*     */   
/*     */   public Min()
/*     */   {
/*  55 */     this.n = 0L;
/*  56 */     this.value = NaN.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void increment(double d)
/*     */   {
/*  63 */     if ((d < this.value) || (Double.isNaN(this.value))) {
/*  64 */       this.value = d;
/*     */     }
/*  66 */     this.n += 1L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/*  73 */     this.value = NaN.0D;
/*  74 */     this.n = 0L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getResult()
/*     */   {
/*  81 */     return this.value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public long getN()
/*     */   {
/*  88 */     return this.n;
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
/*     */   public double evaluate(double[] values, int begin, int length)
/*     */   {
/* 114 */     double min = NaN.0D;
/* 115 */     if (test(values, begin, length)) {
/* 116 */       min = values[begin];
/* 117 */       for (int i = begin; i < begin + length; i++) {
/* 118 */         if (!Double.isNaN(values[i])) {
/* 119 */           min = min < values[i] ? min : values[i];
/*     */         }
/*     */       }
/*     */     }
/* 123 */     return min;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/rank/Min.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */