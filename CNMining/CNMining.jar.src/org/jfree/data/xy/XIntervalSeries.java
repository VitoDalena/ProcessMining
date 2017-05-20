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
/*     */ public class XIntervalSeries
/*     */   extends ComparableObjectSeries
/*     */ {
/*     */   public XIntervalSeries(Comparable key)
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
/*     */   public XIntervalSeries(Comparable key, boolean autoSort, boolean allowDuplicateXValues)
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
/*     */   public void add(double x, double xLow, double xHigh, double y)
/*     */   {
/*  91 */     super.add(new XIntervalDataItem(x, xLow, xHigh, y), true);
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
/* 102 */     XIntervalDataItem item = (XIntervalDataItem)getDataItem(index);
/* 103 */     return item.getX();
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
/*     */   public double getXLowValue(int index)
/*     */   {
/* 116 */     XIntervalDataItem item = (XIntervalDataItem)getDataItem(index);
/* 117 */     return item.getXLowValue();
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
/*     */   public double getXHighValue(int index)
/*     */   {
/* 130 */     XIntervalDataItem item = (XIntervalDataItem)getDataItem(index);
/* 131 */     return item.getXHighValue();
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
/* 142 */     XIntervalDataItem item = (XIntervalDataItem)getDataItem(index);
/* 143 */     return item.getYValue();
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
/* 154 */     return super.getDataItem(index);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/XIntervalSeries.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */