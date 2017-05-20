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
/*     */ public class FirstMoment
/*     */   extends AbstractStorelessUnivariateStatistic
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -803343206421984070L;
/*     */   protected long n;
/*     */   protected double m1;
/*     */   protected double dev;
/*     */   protected double nDev;
/*     */   
/*     */   public FirstMoment()
/*     */   {
/*  77 */     this.n = 0L;
/*  78 */     this.m1 = NaN.0D;
/*  79 */     this.dev = NaN.0D;
/*  80 */     this.nDev = NaN.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void increment(double d)
/*     */   {
/*  87 */     if (this.n == 0L) {
/*  88 */       this.m1 = 0.0D;
/*     */     }
/*  90 */     this.n += 1L;
/*  91 */     double n0 = this.n;
/*  92 */     this.dev = (d - this.m1);
/*  93 */     this.nDev = (this.dev / n0);
/*  94 */     this.m1 += this.nDev;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 101 */     this.m1 = NaN.0D;
/* 102 */     this.n = 0L;
/* 103 */     this.dev = NaN.0D;
/* 104 */     this.nDev = NaN.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getResult()
/*     */   {
/* 111 */     return this.m1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public long getN()
/*     */   {
/* 118 */     return this.n;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/moment/FirstMoment.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */