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
/*     */ public class XYIntervalSeries
/*     */   extends ComparableObjectSeries
/*     */ {
/*     */   public XYIntervalSeries(Comparable key)
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
/*     */   public XYIntervalSeries(Comparable key, boolean autoSort, boolean allowDuplicateXValues)
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(double x, double xLow, double xHigh, double y, double yLow, double yHigh)
/*     */   {
/*  94 */     super.add(new XYIntervalDataItem(x, xLow, xHigh, y, yLow, yHigh), true);
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
/* 105 */     XYIntervalDataItem item = (XYIntervalDataItem)getDataItem(index);
/* 106 */     return item.getX();
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
/*     */   public double getXLowValue(int index)
/*     */   {
/* 120 */     XYIntervalDataItem item = (XYIntervalDataItem)getDataItem(index);
/* 121 */     return item.getXLowValue();
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
/*     */   public double getXHighValue(int index)
/*     */   {
/* 135 */     XYIntervalDataItem item = (XYIntervalDataItem)getDataItem(index);
/* 136 */     return item.getXHighValue();
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
/* 147 */     XYIntervalDataItem item = (XYIntervalDataItem)getDataItem(index);
/* 148 */     return item.getYValue();
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
/* 162 */     XYIntervalDataItem item = (XYIntervalDataItem)getDataItem(index);
/* 163 */     return item.getYLowValue();
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
/* 177 */     XYIntervalDataItem item = (XYIntervalDataItem)getDataItem(index);
/* 178 */     return item.getYHighValue();
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
/* 189 */     return super.getDataItem(index);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/XYIntervalSeries.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */