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
/*     */ public class XIntervalSeriesCollection
/*     */   extends AbstractIntervalXYDataset
/*     */   implements IntervalXYDataset, PublicCloneable, Serializable
/*     */ {
/*     */   private List data;
/*     */   
/*     */   public XIntervalSeriesCollection()
/*     */   {
/*  70 */     this.data = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addSeries(XIntervalSeries series)
/*     */   {
/*  80 */     if (series == null) {
/*  81 */       throw new IllegalArgumentException("Null 'series' argument.");
/*     */     }
/*  83 */     this.data.add(series);
/*  84 */     series.addChangeListener(this);
/*  85 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesCount()
/*     */   {
/*  94 */     return this.data.size();
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
/*     */   public XIntervalSeries getSeries(int series)
/*     */   {
/* 108 */     if ((series < 0) || (series >= getSeriesCount())) {
/* 109 */       throw new IllegalArgumentException("Series index out of bounds");
/*     */     }
/* 111 */     return (XIntervalSeries)this.data.get(series);
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
/* 127 */     return getSeries(series).getKey();
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
/* 142 */     return getSeries(series).getItemCount();
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
/* 154 */     XIntervalSeries s = (XIntervalSeries)this.data.get(series);
/* 155 */     XIntervalDataItem di = (XIntervalDataItem)s.getDataItem(item);
/* 156 */     return di.getX();
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
/* 169 */     XIntervalSeries s = (XIntervalSeries)this.data.get(series);
/* 170 */     return s.getXLowValue(item);
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
/* 183 */     XIntervalSeries s = (XIntervalSeries)this.data.get(series);
/* 184 */     return s.getXHighValue(item);
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
/* 197 */     XIntervalSeries s = (XIntervalSeries)this.data.get(series);
/* 198 */     return s.getYValue(item);
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
/* 210 */     XIntervalSeries s = (XIntervalSeries)this.data.get(series);
/* 211 */     XIntervalDataItem di = (XIntervalDataItem)s.getDataItem(item);
/* 212 */     return new Double(di.getYValue());
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
/* 224 */     XIntervalSeries s = (XIntervalSeries)this.data.get(series);
/* 225 */     XIntervalDataItem di = (XIntervalDataItem)s.getDataItem(item);
/* 226 */     return new Double(di.getXLowValue());
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
/* 238 */     XIntervalSeries s = (XIntervalSeries)this.data.get(series);
/* 239 */     XIntervalDataItem di = (XIntervalDataItem)s.getDataItem(item);
/* 240 */     return new Double(di.getXHighValue());
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
/* 253 */     return getY(series, item);
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
/* 266 */     return getY(series, item);
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
/* 278 */     if ((series < 0) || (series >= getSeriesCount())) {
/* 279 */       throw new IllegalArgumentException("Series index out of bounds.");
/*     */     }
/* 281 */     XIntervalSeries ts = (XIntervalSeries)this.data.get(series);
/* 282 */     ts.removeChangeListener(this);
/* 283 */     this.data.remove(series);
/* 284 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeSeries(XIntervalSeries series)
/*     */   {
/* 296 */     if (series == null) {
/* 297 */       throw new IllegalArgumentException("Null 'series' argument.");
/*     */     }
/* 299 */     if (this.data.contains(series)) {
/* 300 */       series.removeChangeListener(this);
/* 301 */       this.data.remove(series);
/* 302 */       fireDatasetChanged();
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
/* 315 */     for (int i = 0; i < this.data.size(); i++) {
/* 316 */       XIntervalSeries series = (XIntervalSeries)this.data.get(i);
/* 317 */       series.removeChangeListener(this);
/*     */     }
/* 319 */     this.data.clear();
/* 320 */     fireDatasetChanged();
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
/* 331 */     if (obj == this) {
/* 332 */       return true;
/*     */     }
/* 334 */     if (!(obj instanceof XIntervalSeriesCollection)) {
/* 335 */       return false;
/*     */     }
/* 337 */     XIntervalSeriesCollection that = (XIntervalSeriesCollection)obj;
/* 338 */     return ObjectUtilities.equal(this.data, that.data);
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
/* 349 */     XIntervalSeriesCollection clone = (XIntervalSeriesCollection)super.clone();
/*     */     
/* 351 */     clone.data = ((List)ObjectUtilities.deepClone(this.data));
/* 352 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/XIntervalSeriesCollection.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */