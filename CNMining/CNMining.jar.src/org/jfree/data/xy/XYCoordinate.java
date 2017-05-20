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
/*     */ public class XYCoordinate
/*     */   implements Comparable, Serializable
/*     */ {
/*     */   private double x;
/*     */   private double y;
/*     */   
/*     */   public XYCoordinate()
/*     */   {
/*  63 */     this(0.0D, 0.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XYCoordinate(double x, double y)
/*     */   {
/*  73 */     this.x = x;
/*  74 */     this.y = y;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getX()
/*     */   {
/*  83 */     return this.x;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getY()
/*     */   {
/*  92 */     return this.y;
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
/* 103 */     if (obj == this) {
/* 104 */       return true;
/*     */     }
/* 106 */     if (!(obj instanceof XYCoordinate)) {
/* 107 */       return false;
/*     */     }
/* 109 */     XYCoordinate that = (XYCoordinate)obj;
/* 110 */     if (this.x != that.x) {
/* 111 */       return false;
/*     */     }
/* 113 */     if (this.y != that.y) {
/* 114 */       return false;
/*     */     }
/* 116 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 125 */     int result = 193;
/* 126 */     long temp = Double.doubleToLongBits(this.x);
/* 127 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 128 */     temp = Double.doubleToLongBits(this.y);
/* 129 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 130 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 140 */     return "(" + this.x + ", " + this.y + ")";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int compareTo(Object obj)
/*     */   {
/* 151 */     if (!(obj instanceof XYCoordinate)) {
/* 152 */       throw new IllegalArgumentException("Incomparable object.");
/*     */     }
/* 154 */     XYCoordinate that = (XYCoordinate)obj;
/* 155 */     if (this.x > that.x) {
/* 156 */       return 1;
/*     */     }
/* 158 */     if (this.x < that.x) {
/* 159 */       return -1;
/*     */     }
/*     */     
/* 162 */     if (this.y > that.y) {
/* 163 */       return 1;
/*     */     }
/* 165 */     if (this.y < that.y) {
/* 166 */       return -1;
/*     */     }
/*     */     
/* 169 */     return 0;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/XYCoordinate.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */