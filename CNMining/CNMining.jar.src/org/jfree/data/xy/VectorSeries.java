/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ public class VectorSeries
/*     */   extends ComparableObjectSeries
/*     */ {
/*     */   public VectorSeries(Comparable key)
/*     */   {
/*  67 */     this(key, false, true);
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
/*     */   public VectorSeries(Comparable key, boolean autoSort, boolean allowDuplicateXValues)
/*     */   {
/*  82 */     super(key, autoSort, allowDuplicateXValues);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(double x, double y, double deltaX, double deltaY)
/*     */   {
/*  94 */     super.add(new VectorDataItem(x, y, deltaX, deltaY), true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ComparableObjectItem remove(int index)
/*     */   {
/* 106 */     VectorDataItem result = (VectorDataItem)this.data.remove(index);
/* 107 */     fireSeriesChanged();
/* 108 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getXValue(int index)
/*     */   {
/* 119 */     VectorDataItem item = (VectorDataItem)getDataItem(index);
/* 120 */     return item.getXValue();
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
/* 131 */     VectorDataItem item = (VectorDataItem)getDataItem(index);
/* 132 */     return item.getYValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getVectorXValue(int index)
/*     */   {
/* 143 */     VectorDataItem item = (VectorDataItem)getDataItem(index);
/* 144 */     return item.getVectorX();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getVectorYValue(int index)
/*     */   {
/* 155 */     VectorDataItem item = (VectorDataItem)getDataItem(index);
/* 156 */     return item.getVectorY();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ComparableObjectItem getDataItem(int index)
/*     */   {
/* 168 */     return super.getDataItem(index);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/VectorSeries.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */