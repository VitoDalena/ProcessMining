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
/*     */ public class Vector
/*     */   implements Serializable
/*     */ {
/*     */   private double x;
/*     */   private double y;
/*     */   
/*     */   public Vector(double x, double y)
/*     */   {
/*  68 */     this.x = x;
/*  69 */     this.y = y;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getX()
/*     */   {
/*  78 */     return this.x;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getY()
/*     */   {
/*  87 */     return this.y;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getLength()
/*     */   {
/*  96 */     return Math.sqrt(this.x * this.x + this.y * this.y);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getAngle()
/*     */   {
/* 105 */     return Math.atan2(this.y, this.x);
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
/* 116 */     if (obj == this) {
/* 117 */       return true;
/*     */     }
/* 119 */     if (!(obj instanceof Vector)) {
/* 120 */       return false;
/*     */     }
/* 122 */     Vector that = (Vector)obj;
/* 123 */     if (this.x != that.x) {
/* 124 */       return false;
/*     */     }
/* 126 */     if (this.y != that.y) {
/* 127 */       return false;
/*     */     }
/* 129 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 138 */     int result = 193;
/* 139 */     long temp = Double.doubleToLongBits(this.x);
/* 140 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 141 */     temp = Double.doubleToLongBits(this.y);
/* 142 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 143 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/Vector.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */