/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jfree.util.ObjectUtilities;
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
/*     */ public class YIntervalSeriesCollection
/*     */   extends AbstractIntervalXYDataset
/*     */   implements IntervalXYDataset, PublicCloneable, Serializable
/*     */ {
/*     */   private List data;
/*     */   
/*     */   public YIntervalSeriesCollection()
/*     */   {
/*  72 */     this.data = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addSeries(YIntervalSeries series)
/*     */   {
/*  82 */     if (series == null) {
/*  83 */       throw new IllegalArgumentException("Null 'series' argument.");
/*     */     }
/*  85 */     this.data.add(series);
/*  86 */     series.addChangeListener(this);
/*  87 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesCount()
/*     */   {
/*  96 */     return this.data.size();
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
/*     */   public YIntervalSeries getSeries(int series)
/*     */   {
/* 110 */     if ((series < 0) || (series >= getSeriesCount())) {
/* 111 */       throw new IllegalArgumentException("Series index out of bounds");
/*     */     }
/* 113 */     return (YIntervalSeries)this.data.get(series);
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
/*     */ 
/*     */   public Comparable getSeriesKey(int series)
/*     */   {
/* 129 */     return getSeries(series).getKey();
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
/*     */   public int getItemCount(int series)
/*     */   {
/* 144 */     return getSeries(series).getItemCount();
/*     */   }
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
/* 156 */     YIntervalSeries s = (YIntervalSeries)this.data.get(series);
/* 157 */     return s.getX(item);
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
/*     */   public double getYValue(int series, int item)
/*     */   {
/* 170 */     YIntervalSeries s = (YIntervalSeries)this.data.get(series);
/* 171 */     return s.getYValue(item);
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
/*     */   public double getStartYValue(int series, int item)
/*     */   {
/* 184 */     YIntervalSeries s = (YIntervalSeries)this.data.get(series);
/* 185 */     return s.getYLowValue(item);
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
/*     */   public double getEndYValue(int series, int item)
/*     */   {
/* 198 */     YIntervalSeries s = (YIntervalSeries)this.data.get(series);
/* 199 */     return s.getYHighValue(item);
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
/* 211 */     YIntervalSeries s = (YIntervalSeries)this.data.get(series);
/* 212 */     return new Double(s.getYValue(item));
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
/*     */   public Number getStartX(int series, int item)
/*     */   {
/* 225 */     return getX(series, item);
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
/*     */   public Number getEndX(int series, int item)
/*     */   {
/* 238 */     return getX(series, item);
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
/* 250 */     YIntervalSeries s = (YIntervalSeries)this.data.get(series);
/* 251 */     return new Double(s.getYLowValue(item));
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
/* 263 */     YIntervalSeries s = (YIntervalSeries)this.data.get(series);
/* 264 */     return new Double(s.getYHighValue(item));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeSeries(int series)
/*     */   {
/* 276 */     if ((series < 0) || (series >= getSeriesCount())) {
/* 277 */       throw new IllegalArgumentException("Series index out of bounds.");
/*     */     }
/* 279 */     YIntervalSeries ts = (YIntervalSeries)this.data.get(series);
/* 280 */     ts.removeChangeListener(this);
/* 281 */     this.data.remove(series);
/* 282 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeSeries(YIntervalSeries series)
/*     */   {
/* 294 */     if (series == null) {
/* 295 */       throw new IllegalArgumentException("Null 'series' argument.");
/*     */     }
/* 297 */     if (this.data.contains(series)) {
/* 298 */       series.removeChangeListener(this);
/* 299 */       this.data.remove(series);
/* 300 */       fireDatasetChanged();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeAllSeries()
/*     */   {
/* 313 */     for (int i = 0; i < this.data.size(); i++) {
/* 314 */       YIntervalSeries series = (YIntervalSeries)this.data.get(i);
/* 315 */       series.removeChangeListener(this);
/*     */     }
/* 317 */     this.data.clear();
/* 318 */     fireDatasetChanged();
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
/* 329 */     if (obj == this) {
/* 330 */       return true;
/*     */     }
/* 332 */     if (!(obj instanceof YIntervalSeriesCollection)) {
/* 333 */       return false;
/*     */     }
/* 335 */     YIntervalSeriesCollection that = (YIntervalSeriesCollection)obj;
/* 336 */     return ObjectUtilities.equal(this.data, that.data);
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
/* 347 */     YIntervalSeriesCollection clone = (YIntervalSeriesCollection)super.clone();
/*     */     
/* 349 */     clone.data = ((List)ObjectUtilities.deepClone(this.data));
/* 350 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/YIntervalSeriesCollection.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */