/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class YInterval
/*     */   implements Serializable
/*     */ {
/*     */   private double y;
/*     */   private double yLow;
/*     */   private double yHigh;
/*     */   
/*     */   public YInterval(double y, double yLow, double yHigh)
/*     */   {
/*  70 */     this.y = y;
/*  71 */     this.yLow = yLow;
/*  72 */     this.yHigh = yHigh;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getY()
/*     */   {
/*  81 */     return this.y;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getYLow()
/*     */   {
/*  90 */     return this.yLow;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getYHigh()
/*     */   {
/*  99 */     return this.yHigh;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 110 */     if (obj == this) {
/* 111 */       return true;
/*     */     }
/* 113 */     if (!(obj instanceof YInterval)) {
/* 114 */       return false;
/*     */     }
/* 116 */     YInterval that = (YInterval)obj;
/* 117 */     if (this.y != that.y) {
/* 118 */       return false;
/*     */     }
/* 120 */     if (this.yLow != that.yLow) {
/* 121 */       return false;
/*     */     }
/* 123 */     if (this.yHigh != that.yHigh) {
/* 124 */       return false;
/*     */     }
/* 126 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/YInterval.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */