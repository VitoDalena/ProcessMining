/*     */ package org.jfree.chart.axis;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class TickUnit
/*     */   implements Comparable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 510179855057013974L;
/*     */   private double size;
/*     */   private int minorTickCount;
/*     */   
/*     */   public TickUnit(double size)
/*     */   {
/*  84 */     this.size = size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TickUnit(double size, int minorTickCount)
/*     */   {
/*  96 */     this.size = size;
/*  97 */     this.minorTickCount = minorTickCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getSize()
/*     */   {
/* 106 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMinorTickCount()
/*     */   {
/* 117 */     return this.minorTickCount;
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
/*     */   public String valueToString(double value)
/*     */   {
/* 130 */     return String.valueOf(value);
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
/*     */   public int compareTo(Object object)
/*     */   {
/* 144 */     if ((object instanceof TickUnit)) {
/* 145 */       TickUnit other = (TickUnit)object;
/* 146 */       if (this.size > other.getSize()) {
/* 147 */         return 1;
/*     */       }
/* 149 */       if (this.size < other.getSize()) {
/* 150 */         return -1;
/*     */       }
/*     */       
/* 153 */       return 0;
/*     */     }
/*     */     
/*     */ 
/* 157 */     return -1;
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 170 */     if (obj == this) {
/* 171 */       return true;
/*     */     }
/* 173 */     if (!(obj instanceof TickUnit)) {
/* 174 */       return false;
/*     */     }
/* 176 */     TickUnit that = (TickUnit)obj;
/* 177 */     if (this.size != that.size) {
/* 178 */       return false;
/*     */     }
/* 180 */     if (this.minorTickCount != that.minorTickCount) {
/* 181 */       return false;
/*     */     }
/* 183 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 192 */     long temp = this.size != 0.0D ? Double.doubleToLongBits(this.size) : 0L;
/*     */     
/* 194 */     return (int)(temp ^ temp >>> 32);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/TickUnit.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */