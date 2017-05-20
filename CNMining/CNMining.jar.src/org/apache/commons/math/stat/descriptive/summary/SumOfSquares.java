/*     */ package org.apache.commons.math.stat.descriptive.summary;
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
/*     */ public class SumOfSquares
/*     */   extends AbstractStorelessUnivariateStatistic
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1460986908574398008L;
/*     */   private long n;
/*     */   private double value;
/*     */   
/*     */   public SumOfSquares()
/*     */   {
/*  53 */     this.n = 0L;
/*  54 */     this.value = NaN.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void increment(double d)
/*     */   {
/*  61 */     if (this.n == 0L) {
/*  62 */       this.value = (d * d);
/*     */     } else {
/*  64 */       this.value += d * d;
/*     */     }
/*  66 */     this.n += 1L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getResult()
/*     */   {
/*  73 */     return this.value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public long getN()
/*     */   {
/*  80 */     return this.n;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/*  87 */     this.value = NaN.0D;
/*  88 */     this.n = 0L;
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
/*     */   public double evaluate(double[] values, int begin, int length)
/*     */   {
/* 106 */     double sumSq = NaN.0D;
/* 107 */     if (test(values, begin, length)) {
/* 108 */       sumSq = 0.0D;
/* 109 */       for (int i = begin; i < begin + length; i++) {
/* 110 */         sumSq += values[i] * values[i];
/*     */       }
/*     */     }
/* 113 */     return sumSq;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/summary/SumOfSquares.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */