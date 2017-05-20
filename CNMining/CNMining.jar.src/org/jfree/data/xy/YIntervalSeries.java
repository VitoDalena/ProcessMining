/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import org.jfree.data.ComparableObjectItem;
/*     */ import org.jfree.data.ComparableObjectSeries;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class YIntervalSeries
/*     */   extends ComparableObjectSeries
/*     */ {
/*     */   public YIntervalSeries(Comparable key)
/*     */   {
/*  64 */     this(key, true, true);
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
/*     */   public YIntervalSeries(Comparable key, boolean autoSort, boolean allowDuplicateXValues)
/*     */   {
/*  79 */     super(key, autoSort, allowDuplicateXValues);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(double x, double y, double yLow, double yHigh)
/*     */   {
/*  91 */     super.add(new YIntervalDataItem(x, y, yLow, yHigh), true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getX(int index)
/*     */   {
/* 102 */     YIntervalDataItem item = (YIntervalDataItem)getDataItem(index);
/* 103 */     return item.getX();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getYValue(int index)
/*     */   {
/* 114 */     YIntervalDataItem item = (YIntervalDataItem)getDataItem(index);
/* 115 */     return item.getYValue();
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
/*     */   public double getYLowValue(int index)
/*     */   {
/* 129 */     YIntervalDataItem item = (YIntervalDataItem)getDataItem(index);
/* 130 */     return item.getYLowValue();
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
/*     */   public double getYHighValue(int index)
/*     */   {
/* 144 */     YIntervalDataItem item = (YIntervalDataItem)getDataItem(index);
/* 145 */     return item.getYHighValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ComparableObjectItem getDataItem(int index)
/*     */   {
/* 156 */     return super.getDataItem(index);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/YIntervalSeries.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */