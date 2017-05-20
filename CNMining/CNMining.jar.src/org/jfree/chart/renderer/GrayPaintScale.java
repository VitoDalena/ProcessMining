/*     */ package org.jfree.chart.renderer;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GrayPaintScale
/*     */   implements PaintScale, PublicCloneable, Serializable
/*     */ {
/*     */   private double lowerBound;
/*     */   private double upperBound;
/*     */   private int alpha;
/*     */   
/*     */   public GrayPaintScale()
/*     */   {
/*  78 */     this(0.0D, 1.0D);
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
/*     */   public GrayPaintScale(double lowerBound, double upperBound)
/*     */   {
/*  91 */     this(lowerBound, upperBound, 255);
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
/*     */   public GrayPaintScale(double lowerBound, double upperBound, int alpha)
/*     */   {
/* 108 */     if (lowerBound >= upperBound) {
/* 109 */       throw new IllegalArgumentException("Requires lowerBound < upperBound.");
/*     */     }
/*     */     
/* 112 */     if ((alpha < 0) || (alpha > 255)) {
/* 113 */       throw new IllegalArgumentException("Requires alpha in the range 0 to 255.");
/*     */     }
/*     */     
/*     */ 
/* 117 */     this.lowerBound = lowerBound;
/* 118 */     this.upperBound = upperBound;
/* 119 */     this.alpha = alpha;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getLowerBound()
/*     */   {
/* 130 */     return this.lowerBound;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getUpperBound()
/*     */   {
/* 141 */     return this.upperBound;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getAlpha()
/*     */   {
/* 152 */     return this.alpha;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getPaint(double value)
/*     */   {
/* 164 */     double v = Math.max(value, this.lowerBound);
/* 165 */     v = Math.min(v, this.upperBound);
/* 166 */     int g = (int)((v - this.lowerBound) / (this.upperBound - this.lowerBound) * 255.0D);
/*     */     
/*     */ 
/*     */ 
/* 170 */     return new Color(g, g, g, this.alpha);
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 187 */     if (obj == this) {
/* 188 */       return true;
/*     */     }
/* 190 */     if (!(obj instanceof GrayPaintScale)) {
/* 191 */       return false;
/*     */     }
/* 193 */     GrayPaintScale that = (GrayPaintScale)obj;
/* 194 */     if (this.lowerBound != that.lowerBound) {
/* 195 */       return false;
/*     */     }
/* 197 */     if (this.upperBound != that.upperBound) {
/* 198 */       return false;
/*     */     }
/* 200 */     if (this.alpha != that.alpha) {
/* 201 */       return false;
/*     */     }
/* 203 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 212 */     int hash = 7;
/* 213 */     hash = HashUtilities.hashCode(hash, this.lowerBound);
/* 214 */     hash = HashUtilities.hashCode(hash, this.upperBound);
/* 215 */     hash = 43 * hash + this.alpha;
/* 216 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 228 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/GrayPaintScale.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */