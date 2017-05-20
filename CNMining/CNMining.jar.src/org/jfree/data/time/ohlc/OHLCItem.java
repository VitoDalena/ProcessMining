/*     */ package org.jfree.data.time.ohlc;
/*     */ 
/*     */ import org.jfree.data.ComparableObjectItem;
/*     */ import org.jfree.data.time.RegularTimePeriod;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OHLCItem
/*     */   extends ComparableObjectItem
/*     */ {
/*     */   public OHLCItem(RegularTimePeriod period, double open, double high, double low, double close)
/*     */   {
/*  64 */     super(period, new OHLC(open, high, low, close));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RegularTimePeriod getPeriod()
/*     */   {
/*  73 */     return (RegularTimePeriod)getComparable();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getYValue()
/*     */   {
/*  82 */     return getCloseValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getOpenValue()
/*     */   {
/*  91 */     OHLC ohlc = (OHLC)getObject();
/*  92 */     if (ohlc != null) {
/*  93 */       return ohlc.getOpen();
/*     */     }
/*     */     
/*  96 */     return NaN.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getHighValue()
/*     */   {
/* 106 */     OHLC ohlc = (OHLC)getObject();
/* 107 */     if (ohlc != null) {
/* 108 */       return ohlc.getHigh();
/*     */     }
/*     */     
/* 111 */     return NaN.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getLowValue()
/*     */   {
/* 121 */     OHLC ohlc = (OHLC)getObject();
/* 122 */     if (ohlc != null) {
/* 123 */       return ohlc.getLow();
/*     */     }
/*     */     
/* 126 */     return NaN.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getCloseValue()
/*     */   {
/* 136 */     OHLC ohlc = (OHLC)getObject();
/* 137 */     if (ohlc != null) {
/* 138 */       return ohlc.getClose();
/*     */     }
/*     */     
/* 141 */     return NaN.0D;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/ohlc/OHLCItem.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */