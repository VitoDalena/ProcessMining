/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import org.jfree.data.DefaultKeyedValues2D;
/*     */ import org.jfree.data.DomainInfo;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.general.DatasetUtilities;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CategoryTableXYDataset
/*     */   extends AbstractIntervalXYDataset
/*     */   implements TableXYDataset, IntervalXYDataset, DomainInfo, PublicCloneable
/*     */ {
/*     */   private DefaultKeyedValues2D values;
/*     */   private IntervalXYDelegate intervalDelegate;
/*     */   
/*     */   public CategoryTableXYDataset()
/*     */   {
/*  87 */     this.values = new DefaultKeyedValues2D(true);
/*  88 */     this.intervalDelegate = new IntervalXYDelegate(this);
/*  89 */     addChangeListener(this.intervalDelegate);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(double x, double y, String seriesName)
/*     */   {
/* 101 */     add(new Double(x), new Double(y), seriesName, true);
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
/*     */   public void add(Number x, Number y, String seriesName, boolean notify)
/*     */   {
/* 114 */     this.values.addValue(y, (Comparable)x, seriesName);
/* 115 */     if (notify) {
/* 116 */       fireDatasetChanged();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void remove(double x, String seriesName)
/*     */   {
/* 127 */     remove(new Double(x), seriesName, true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void remove(Number x, String seriesName, boolean notify)
/*     */   {
/* 138 */     this.values.removeValue((Comparable)x, seriesName);
/* 139 */     if (notify) {
/* 140 */       fireDatasetChanged();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesCount()
/*     */   {
/* 151 */     return this.values.getColumnCount();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getSeriesKey(int series)
/*     */   {
/* 162 */     return this.values.getColumnKey(series);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getItemCount()
/*     */   {
/* 171 */     return this.values.getRowCount();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getItemCount(int series)
/*     */   {
/* 183 */     return getItemCount();
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
/*     */   public Number getX(int series, int item)
/*     */   {
/* 196 */     return (Number)this.values.getRowKey(item);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getStartX(int series, int item)
/*     */   {
/* 208 */     return this.intervalDelegate.getStartX(series, item);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getEndX(int series, int item)
/*     */   {
/* 220 */     return this.intervalDelegate.getEndX(series, item);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getY(int series, int item)
/*     */   {
/* 232 */     return this.values.getValue(item, series);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getStartY(int series, int item)
/*     */   {
/* 244 */     return getY(series, item);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getEndY(int series, int item)
/*     */   {
/* 256 */     return getY(series, item);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getDomainLowerBound(boolean includeInterval)
/*     */   {
/* 268 */     return this.intervalDelegate.getDomainLowerBound(includeInterval);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getDomainUpperBound(boolean includeInterval)
/*     */   {
/* 280 */     return this.intervalDelegate.getDomainUpperBound(includeInterval);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Range getDomainBounds(boolean includeInterval)
/*     */   {
/* 292 */     if (includeInterval) {
/* 293 */       return this.intervalDelegate.getDomainBounds(includeInterval);
/*     */     }
/*     */     
/* 296 */     return DatasetUtilities.iterateDomainBounds(this, includeInterval);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getIntervalPositionFactor()
/*     */   {
/* 306 */     return this.intervalDelegate.getIntervalPositionFactor();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setIntervalPositionFactor(double d)
/*     */   {
/* 318 */     this.intervalDelegate.setIntervalPositionFactor(d);
/* 319 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getIntervalWidth()
/*     */   {
/* 328 */     return this.intervalDelegate.getIntervalWidth();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setIntervalWidth(double d)
/*     */   {
/* 338 */     this.intervalDelegate.setFixedIntervalWidth(d);
/* 339 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAutoWidth()
/*     */   {
/* 348 */     return this.intervalDelegate.isAutoWidth();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAutoWidth(boolean b)
/*     */   {
/* 358 */     this.intervalDelegate.setAutoWidth(b);
/* 359 */     fireDatasetChanged();
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
/* 370 */     if (!(obj instanceof CategoryTableXYDataset)) {
/* 371 */       return false;
/*     */     }
/* 373 */     CategoryTableXYDataset that = (CategoryTableXYDataset)obj;
/* 374 */     if (!this.intervalDelegate.equals(that.intervalDelegate)) {
/* 375 */       return false;
/*     */     }
/* 377 */     if (!this.values.equals(that.values)) {
/* 378 */       return false;
/*     */     }
/* 380 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 392 */     CategoryTableXYDataset clone = (CategoryTableXYDataset)super.clone();
/* 393 */     clone.values = ((DefaultKeyedValues2D)this.values.clone());
/* 394 */     clone.intervalDelegate = new IntervalXYDelegate(clone);
/*     */     
/* 396 */     clone.intervalDelegate.setFixedIntervalWidth(getIntervalWidth());
/* 397 */     clone.intervalDelegate.setAutoWidth(isAutoWidth());
/* 398 */     clone.intervalDelegate.setIntervalPositionFactor(getIntervalPositionFactor());
/*     */     
/* 400 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/CategoryTableXYDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */