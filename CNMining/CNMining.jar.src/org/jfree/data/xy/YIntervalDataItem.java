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
/*     */ public class YIntervalDataItem
/*     */   extends ComparableObjectItem
/*     */ {
/*     */   public YIntervalDataItem(double x, double y, double yLow, double yHigh)
/*     */   {
/*  61 */     super(new Double(x), new YInterval(y, yLow, yHigh));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Double getX()
/*     */   {
/*  70 */     return (Double)getComparable();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getYValue()
/*     */   {
/*  79 */     YInterval interval = (YInterval)getObject();
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
/*     */   public double getYLowValue()
/*     */   {
/*  94 */     YInterval interval = (YInterval)getObject();
/*  95 */     if (interval != null) {
/*  96 */       return interval.getYLow();
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
/*     */   public double getYHighValue()
/*     */   {
/* 109 */     YInterval interval = (YInterval)getObject();
/* 110 */     if (interval != null) {
/* 111 */       return interval.getYHigh();
/*     */     }
/*     */     
/* 114 */     return NaN.0D;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/YIntervalDataItem.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */