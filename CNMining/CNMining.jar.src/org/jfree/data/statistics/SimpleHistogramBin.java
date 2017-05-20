/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimpleHistogramBin
/*     */   implements Comparable, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3480862537505941742L;
/*     */   private double lowerBound;
/*     */   private double upperBound;
/*     */   private boolean includeLowerBound;
/*     */   private boolean includeUpperBound;
/*     */   private int itemCount;
/*     */   
/*     */   public SimpleHistogramBin(double lowerBound, double upperBound)
/*     */   {
/*  84 */     this(lowerBound, upperBound, true, true);
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
/*     */   public SimpleHistogramBin(double lowerBound, double upperBound, boolean includeLowerBound, boolean includeUpperBound)
/*     */   {
/*  98 */     if (lowerBound >= upperBound) {
/*  99 */       throw new IllegalArgumentException("Invalid bounds");
/*     */     }
/* 101 */     this.lowerBound = lowerBound;
/* 102 */     this.upperBound = upperBound;
/* 103 */     this.includeLowerBound = includeLowerBound;
/* 104 */     this.includeUpperBound = includeUpperBound;
/* 105 */     this.itemCount = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getLowerBound()
/*     */   {
/* 114 */     return this.lowerBound;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getUpperBound()
/*     */   {
/* 123 */     return this.upperBound;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getItemCount()
/*     */   {
/* 132 */     return this.itemCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setItemCount(int count)
/*     */   {
/* 141 */     this.itemCount = count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean accepts(double value)
/*     */   {
/* 153 */     if (Double.isNaN(value)) {
/* 154 */       return false;
/*     */     }
/* 156 */     if (value < this.lowerBound) {
/* 157 */       return false;
/*     */     }
/* 159 */     if (value > this.upperBound) {
/* 160 */       return false;
/*     */     }
/* 162 */     if (value == this.lowerBound) {
/* 163 */       return this.includeLowerBound;
/*     */     }
/* 165 */     if (value == this.upperBound) {
/* 166 */       return this.includeUpperBound;
/*     */     }
/* 168 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean overlapsWith(SimpleHistogramBin bin)
/*     */   {
/* 180 */     if (this.upperBound < bin.lowerBound) {
/* 181 */       return false;
/*     */     }
/* 183 */     if (this.lowerBound > bin.upperBound) {
/* 184 */       return false;
/*     */     }
/* 186 */     if (this.upperBound == bin.lowerBound) {
/* 187 */       return (this.includeUpperBound) && (bin.includeLowerBound);
/*     */     }
/* 189 */     if (this.lowerBound == bin.upperBound) {
/* 190 */       return (this.includeLowerBound) && (bin.includeUpperBound);
/*     */     }
/* 192 */     return true;
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
/*     */   public int compareTo(Object obj)
/*     */   {
/* 205 */     if (!(obj instanceof SimpleHistogramBin)) {
/* 206 */       return 0;
/*     */     }
/* 208 */     SimpleHistogramBin bin = (SimpleHistogramBin)obj;
/* 209 */     if (this.lowerBound < bin.lowerBound) {
/* 210 */       return -1;
/*     */     }
/* 212 */     if (this.lowerBound > bin.lowerBound) {
/* 213 */       return 1;
/*     */     }
/*     */     
/* 216 */     if (this.upperBound < bin.upperBound) {
/* 217 */       return -1;
/*     */     }
/* 219 */     if (this.upperBound > bin.upperBound) {
/* 220 */       return 1;
/*     */     }
/* 222 */     return 0;
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
/* 233 */     if (!(obj instanceof SimpleHistogramBin)) {
/* 234 */       return false;
/*     */     }
/* 236 */     SimpleHistogramBin that = (SimpleHistogramBin)obj;
/* 237 */     if (this.lowerBound != that.lowerBound) {
/* 238 */       return false;
/*     */     }
/* 240 */     if (this.upperBound != that.upperBound) {
/* 241 */       return false;
/*     */     }
/* 243 */     if (this.includeLowerBound != that.includeLowerBound) {
/* 244 */       return false;
/*     */     }
/* 246 */     if (this.includeUpperBound != that.includeUpperBound) {
/* 247 */       return false;
/*     */     }
/* 249 */     if (this.itemCount != that.itemCount) {
/* 250 */       return false;
/*     */     }
/* 252 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 263 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/statistics/SimpleHistogramBin.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */