/*     */ package org.jfree.data.time.ohlc;
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
/*     */ public class OHLC
/*     */   implements Serializable
/*     */ {
/*     */   private double open;
/*     */   private double close;
/*     */   private double high;
/*     */   private double low;
/*     */   
/*     */   public OHLC(double open, double high, double low, double close)
/*     */   {
/*  74 */     this.open = open;
/*  75 */     this.close = close;
/*  76 */     this.high = high;
/*  77 */     this.low = low;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getOpen()
/*     */   {
/*  86 */     return this.open;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getClose()
/*     */   {
/*  95 */     return this.close;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getHigh()
/*     */   {
/* 104 */     return this.high;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getLow()
/*     */   {
/* 113 */     return this.low;
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
/* 124 */     if (obj == this) {
/* 125 */       return true;
/*     */     }
/* 127 */     if (!(obj instanceof OHLC)) {
/* 128 */       return false;
/*     */     }
/* 130 */     OHLC that = (OHLC)obj;
/* 131 */     if (this.open != that.open) {
/* 132 */       return false;
/*     */     }
/* 134 */     if (this.close != that.close) {
/* 135 */       return false;
/*     */     }
/* 137 */     if (this.high != that.high) {
/* 138 */       return false;
/*     */     }
/* 140 */     if (this.low != that.low) {
/* 141 */       return false;
/*     */     }
/* 143 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/ohlc/OHLC.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */