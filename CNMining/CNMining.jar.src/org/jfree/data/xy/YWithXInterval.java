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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class YWithXInterval
/*     */   implements Serializable
/*     */ {
/*     */   private double y;
/*     */   private double xLow;
/*     */   private double xHigh;
/*     */   
/*     */   public YWithXInterval(double y, double xLow, double xHigh)
/*     */   {
/*  74 */     this.y = y;
/*  75 */     this.xLow = xLow;
/*  76 */     this.xHigh = xHigh;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getY()
/*     */   {
/*  85 */     return this.y;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getXLow()
/*     */   {
/*  94 */     return this.xLow;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getXHigh()
/*     */   {
/* 103 */     return this.xHigh;
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
/* 114 */     if (obj == this) {
/* 115 */       return true;
/*     */     }
/* 117 */     if (!(obj instanceof YWithXInterval)) {
/* 118 */       return false;
/*     */     }
/* 120 */     YWithXInterval that = (YWithXInterval)obj;
/* 121 */     if (this.y != that.y) {
/* 122 */       return false;
/*     */     }
/* 124 */     if (this.xLow != that.xLow) {
/* 125 */       return false;
/*     */     }
/* 127 */     if (this.xHigh != that.xHigh) {
/* 128 */       return false;
/*     */     }
/* 130 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/YWithXInterval.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */