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
/*     */ public class XYIntervalSeriesCollection
/*     */   extends AbstractIntervalXYDataset
/*     */   implements IntervalXYDataset, PublicCloneable, Serializable
/*     */ {
/*     */   private List data;
/*     */   
/*     */   public XYIntervalSeriesCollection()
/*     */   {
/*  72 */     this.data = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addSeries(XYIntervalSeries series)
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
/*     */   public XYIntervalSeries getSeries(int series)
/*     */   {
/* 110 */     if ((series < 0) || (series >= getSeriesCount())) {
/* 111 */       throw new IllegalArgumentException("Series index out of bounds");
/*     */     }
/* 113 */     return (XYIntervalSeries)this.data.get(series);
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
/* 156 */     XYIntervalSeries s = (XYIntervalSeries)this.data.get(series);
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
/*     */   public double getStartXValue(int series, int item)
/*     */   {
/* 170 */     XYIntervalSeries s = (XYIntervalSeries)this.data.get(series);
/* 171 */     return s.getXLowValue(item);
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
/*     */   public double getEndXValue(int series, int item)
/*     */   {
/* 184 */     XYIntervalSeries s = (XYIntervalSeries)this.data.get(series);
/* 185 */     return s.getXHighValue(item);
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
/* 198 */     XYIntervalSeries s = (XYIntervalSeries)this.data.get(series);
/* 199 */     return s.getYValue(item);
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
/* 212 */     XYIntervalSeries s = (XYIntervalSeries)this.data.get(series);
/* 213 */     return s.getYLowValue(item);
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
/* 226 */     XYIntervalSeries s = (XYIntervalSeries)this.data.get(series);
/* 227 */     return s.getYHighValue(item);
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
/* 239 */     return new Double(getYValue(series, item));
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
/* 251 */     return new Double(getStartXValue(series, item));
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
/* 263 */     return new Double(getEndXValue(series, item));
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
/*     */   public Number getStartY(int series, int item)
/*     */   {
/* 276 */     return new Double(getStartYValue(series, item));
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
/*     */   public Number getEndY(int series, int item)
/*     */   {
/* 289 */     return new Double(getEndYValue(series, item));
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
/* 301 */     if ((series < 0) || (series >= getSeriesCount())) {
/* 302 */       throw new IllegalArgumentException("Series index out of bounds.");
/*     */     }
/* 304 */     XYIntervalSeries ts = (XYIntervalSeries)this.data.get(series);
/* 305 */     ts.removeChangeListener(this);
/* 306 */     this.data.remove(series);
/* 307 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeSeries(XYIntervalSeries series)
/*     */   {
/* 319 */     if (series == null) {
/* 320 */       throw new IllegalArgumentException("Null 'series' argument.");
/*     */     }
/* 322 */     if (this.data.contains(series)) {
/* 323 */       series.removeChangeListener(this);
/* 324 */       this.data.remove(series);
/* 325 */       fireDatasetChanged();
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
/* 338 */     for (int i = 0; i < this.data.size(); i++) {
/* 339 */       XYIntervalSeries series = (XYIntervalSeries)this.data.get(i);
/* 340 */       series.removeChangeListener(this);
/*     */     }
/* 342 */     this.data.clear();
/* 343 */     fireDatasetChanged();
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
/* 354 */     if (obj == this) {
/* 355 */       return true;
/*     */     }
/* 357 */     if (!(obj instanceof XYIntervalSeriesCollection)) {
/* 358 */       return false;
/*     */     }
/* 360 */     XYIntervalSeriesCollection that = (XYIntervalSeriesCollection)obj;
/* 361 */     return ObjectUtilities.equal(this.data, that.data);
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
/* 372 */     XYIntervalSeriesCollection clone = (XYIntervalSeriesCollection)super.clone();
/*     */     
/* 374 */     int seriesCount = getSeriesCount();
/* 375 */     clone.data = new ArrayList(seriesCount);
/* 376 */     for (int i = 0; i < this.data.size(); i++) {
/* 377 */       clone.data.set(i, getSeries(i).clone());
/*     */     }
/* 379 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/XYIntervalSeriesCollection.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */