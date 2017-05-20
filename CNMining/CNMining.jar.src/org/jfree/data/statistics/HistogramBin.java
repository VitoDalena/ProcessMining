/*     */ package org.jfree.data.statistics;
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
/*     */ public class HistogramBin
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7614685080015589931L;
/*     */   private int count;
/*     */   private double startBoundary;
/*     */   private double endBoundary;
/*     */   
/*     */   public HistogramBin(double startBoundary, double endBoundary)
/*     */   {
/*  73 */     if (startBoundary > endBoundary) {
/*  74 */       throw new IllegalArgumentException("HistogramBin():  startBoundary > endBoundary.");
/*     */     }
/*     */     
/*  77 */     this.count = 0;
/*  78 */     this.startBoundary = startBoundary;
/*  79 */     this.endBoundary = endBoundary;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getCount()
/*     */   {
/*  88 */     return this.count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void incrementCount()
/*     */   {
/*  95 */     this.count += 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getStartBoundary()
/*     */   {
/* 104 */     return this.startBoundary;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getEndBoundary()
/*     */   {
/* 113 */     return this.endBoundary;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getBinWidth()
/*     */   {
/* 122 */     return this.endBoundary - this.startBoundary;
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
/* 133 */     if (obj == null) {
/* 134 */       return false;
/*     */     }
/* 136 */     if (obj == this) {
/* 137 */       return true;
/*     */     }
/* 139 */     if ((obj instanceof HistogramBin)) {
/* 140 */       HistogramBin bin = (HistogramBin)obj;
/* 141 */       boolean b0 = bin.startBoundary == this.startBoundary;
/* 142 */       boolean b1 = bin.endBoundary == this.endBoundary;
/* 143 */       boolean b2 = bin.count == this.count;
/* 144 */       return (b0) && (b1) && (b2);
/*     */     }
/* 146 */     return false;
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
/* 157 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/statistics/HistogramBin.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */