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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XYInterval
/*     */   implements Serializable
/*     */ {
/*     */   private double xLow;
/*     */   private double xHigh;
/*     */   private double y;
/*     */   private double yLow;
/*     */   private double yHigh;
/*     */   
/*     */   public XYInterval(double xLow, double xHigh, double y, double yLow, double yHigh)
/*     */   {
/*  79 */     this.xLow = xLow;
/*  80 */     this.xHigh = xHigh;
/*  81 */     this.y = y;
/*  82 */     this.yLow = yLow;
/*  83 */     this.yHigh = yHigh;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getXLow()
/*     */   {
/*  92 */     return this.xLow;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getXHigh()
/*     */   {
/* 101 */     return this.xHigh;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getY()
/*     */   {
/* 110 */     return this.y;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getYLow()
/*     */   {
/* 119 */     return this.yLow;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getYHigh()
/*     */   {
/* 128 */     return this.yHigh;
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
/* 139 */     if (obj == this) {
/* 140 */       return true;
/*     */     }
/* 142 */     if (!(obj instanceof XYInterval)) {
/* 143 */       return false;
/*     */     }
/* 145 */     XYInterval that = (XYInterval)obj;
/* 146 */     if (this.xLow != that.xLow) {
/* 147 */       return false;
/*     */     }
/* 149 */     if (this.xHigh != that.xHigh) {
/* 150 */       return false;
/*     */     }
/* 152 */     if (this.y != that.y) {
/* 153 */       return false;
/*     */     }
/* 155 */     if (this.yLow != that.yLow) {
/* 156 */       return false;
/*     */     }
/* 158 */     if (this.yHigh != that.yHigh) {
/* 159 */       return false;
/*     */     }
/* 161 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/XYInterval.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */