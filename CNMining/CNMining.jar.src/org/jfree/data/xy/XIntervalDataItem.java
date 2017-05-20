/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import org.jfree.data.ComparableObjectItem;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XIntervalDataItem
/*     */   extends ComparableObjectItem
/*     */ {
/*     */   public XIntervalDataItem(double x, double xLow, double xHigh, double y)
/*     */   {
/*  61 */     super(new Double(x), new YWithXInterval(y, xLow, xHigh));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getX()
/*     */   {
/*  70 */     return (Number)getComparable();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getYValue()
/*     */   {
/*  79 */     YWithXInterval interval = (YWithXInterval)getObject();
/*  80 */     if (interval != null) {
/*  81 */       return interval.getY();
/*     */     }
/*     */     
/*  84 */     return NaN.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getXLowValue()
/*     */   {
/*  94 */     YWithXInterval interval = (YWithXInterval)getObject();
/*  95 */     if (interval != null) {
/*  96 */       return interval.getXLow();
/*     */     }
/*     */     
/*  99 */     return NaN.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getXHighValue()
/*     */   {
/* 109 */     YWithXInterval interval = (YWithXInterval)getObject();
/* 110 */     if (interval != null) {
/* 111 */       return interval.getXHigh();
/*     */     }
/*     */     
/* 114 */     return NaN.0D;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/XIntervalDataItem.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */