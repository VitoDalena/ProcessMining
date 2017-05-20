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
/*     */ public class VectorSeriesCollection
/*     */   extends AbstractXYDataset
/*     */   implements VectorXYDataset, PublicCloneable, Serializable
/*     */ {
/*     */   private List data;
/*     */   
/*     */   public VectorSeriesCollection()
/*     */   {
/*  69 */     this.data = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addSeries(VectorSeries series)
/*     */   {
/*  79 */     if (series == null) {
/*  80 */       throw new IllegalArgumentException("Null 'series' argument.");
/*     */     }
/*  82 */     this.data.add(series);
/*  83 */     series.addChangeListener(this);
/*  84 */     fireDatasetChanged();
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
/*     */   public boolean removeSeries(VectorSeries series)
/*     */   {
/*  97 */     if (series == null) {
/*  98 */       throw new IllegalArgumentException("Null 'series' argument.");
/*     */     }
/* 100 */     boolean removed = this.data.remove(series);
/* 101 */     if (removed) {
/* 102 */       series.removeChangeListener(this);
/* 103 */       fireDatasetChanged();
/*     */     }
/* 105 */     return removed;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeAllSeries()
/*     */   {
/* 116 */     for (int i = 0; i < this.data.size(); i++) {
/* 117 */       VectorSeries series = (VectorSeries)this.data.get(i);
/* 118 */       series.removeChangeListener(this);
/*     */     }
/*     */     
/*     */ 
/* 122 */     this.data.clear();
/* 123 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesCount()
/*     */   {
/* 133 */     return this.data.size();
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
/*     */   public VectorSeries getSeries(int series)
/*     */   {
/* 147 */     if ((series < 0) || (series >= getSeriesCount())) {
/* 148 */       throw new IllegalArgumentException("Series index out of bounds");
/*     */     }
/* 150 */     return (VectorSeries)this.data.get(series);
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
/* 166 */     return getSeries(series).getKey();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int indexOf(VectorSeries series)
/*     */   {
/* 178 */     if (series == null) {
/* 179 */       throw new IllegalArgumentException("Null 'series' argument.");
/*     */     }
/* 181 */     return this.data.indexOf(series);
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
/* 196 */     return getSeries(series).getItemCount();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getXValue(int series, int item)
/*     */   {
/* 208 */     VectorSeries s = (VectorSeries)this.data.get(series);
/* 209 */     VectorDataItem di = (VectorDataItem)s.getDataItem(item);
/* 210 */     return di.getXValue();
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
/*     */   public Number getX(int series, int item)
/*     */   {
/* 224 */     return new Double(getXValue(series, item));
/*     */   }
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
/* 236 */     VectorSeries s = (VectorSeries)this.data.get(series);
/* 237 */     VectorDataItem di = (VectorDataItem)s.getDataItem(item);
/* 238 */     return di.getYValue();
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
/*     */   public Number getY(int series, int item)
/*     */   {
/* 252 */     return new Double(getYValue(series, item));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Vector getVector(int series, int item)
/*     */   {
/* 264 */     VectorSeries s = (VectorSeries)this.data.get(series);
/* 265 */     VectorDataItem di = (VectorDataItem)s.getDataItem(item);
/* 266 */     return di.getVector();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getVectorXValue(int series, int item)
/*     */   {
/* 278 */     VectorSeries s = (VectorSeries)this.data.get(series);
/* 279 */     VectorDataItem di = (VectorDataItem)s.getDataItem(item);
/* 280 */     return di.getVectorX();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getVectorYValue(int series, int item)
/*     */   {
/* 292 */     VectorSeries s = (VectorSeries)this.data.get(series);
/* 293 */     VectorDataItem di = (VectorDataItem)s.getDataItem(item);
/* 294 */     return di.getVectorY();
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
/* 305 */     if (obj == this) {
/* 306 */       return true;
/*     */     }
/* 308 */     if (!(obj instanceof VectorSeriesCollection)) {
/* 309 */       return false;
/*     */     }
/* 311 */     VectorSeriesCollection that = (VectorSeriesCollection)obj;
/* 312 */     return ObjectUtilities.equal(this.data, that.data);
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
/* 323 */     VectorSeriesCollection clone = (VectorSeriesCollection)super.clone();
/*     */     
/* 325 */     clone.data = ((List)ObjectUtilities.deepClone(this.data));
/* 326 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/VectorSeriesCollection.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */