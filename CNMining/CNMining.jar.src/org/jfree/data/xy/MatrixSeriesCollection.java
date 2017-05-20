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
/*     */ public class MatrixSeriesCollection
/*     */   extends AbstractXYZDataset
/*     */   implements XYZDataset, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3197705779242543945L;
/*     */   private List seriesList;
/*     */   
/*     */   public MatrixSeriesCollection()
/*     */   {
/*  73 */     this(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MatrixSeriesCollection(MatrixSeries series)
/*     */   {
/*  83 */     this.seriesList = new ArrayList();
/*     */     
/*  85 */     if (series != null) {
/*  86 */       this.seriesList.add(series);
/*  87 */       series.addChangeListener(this);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getItemCount(int seriesIndex)
/*     */   {
/*  99 */     return getSeries(seriesIndex).getItemCount();
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
/*     */   public MatrixSeries getSeries(int seriesIndex)
/*     */   {
/* 113 */     if ((seriesIndex < 0) || (seriesIndex > getSeriesCount())) {
/* 114 */       throw new IllegalArgumentException("Index outside valid range.");
/*     */     }
/*     */     
/* 117 */     MatrixSeries series = (MatrixSeries)this.seriesList.get(seriesIndex);
/*     */     
/* 119 */     return series;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesCount()
/*     */   {
/* 129 */     return this.seriesList.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getSeriesKey(int seriesIndex)
/*     */   {
/* 141 */     return getSeries(seriesIndex).getKey();
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
/*     */   public Number getX(int seriesIndex, int itemIndex)
/*     */   {
/* 157 */     MatrixSeries series = (MatrixSeries)this.seriesList.get(seriesIndex);
/* 158 */     int x = series.getItemColumn(itemIndex);
/*     */     
/* 160 */     return new Integer(x);
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
/*     */   public Number getY(int seriesIndex, int itemIndex)
/*     */   {
/* 176 */     MatrixSeries series = (MatrixSeries)this.seriesList.get(seriesIndex);
/* 177 */     int y = series.getItemRow(itemIndex);
/*     */     
/* 179 */     return new Integer(y);
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
/*     */   public Number getZ(int seriesIndex, int itemIndex)
/*     */   {
/* 195 */     MatrixSeries series = (MatrixSeries)this.seriesList.get(seriesIndex);
/* 196 */     Number z = series.getItem(itemIndex);
/* 197 */     return z;
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
/*     */   public void addSeries(MatrixSeries series)
/*     */   {
/* 213 */     if (series == null) {
/* 214 */       throw new IllegalArgumentException("Cannot add null series.");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 219 */     this.seriesList.add(series);
/* 220 */     series.addChangeListener(this);
/* 221 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 233 */     if (obj == null) {
/* 234 */       return false;
/*     */     }
/*     */     
/* 237 */     if (obj == this) {
/* 238 */       return true;
/*     */     }
/*     */     
/* 241 */     if ((obj instanceof MatrixSeriesCollection)) {
/* 242 */       MatrixSeriesCollection c = (MatrixSeriesCollection)obj;
/*     */       
/* 244 */       return ObjectUtilities.equal(this.seriesList, c.seriesList);
/*     */     }
/*     */     
/* 247 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 256 */     return this.seriesList != null ? this.seriesList.hashCode() : 0;
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
/* 267 */     MatrixSeriesCollection clone = (MatrixSeriesCollection)super.clone();
/* 268 */     clone.seriesList = ((List)ObjectUtilities.deepClone(this.seriesList));
/* 269 */     return clone;
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
/* 281 */     for (int i = 0; i < this.seriesList.size(); i++) {
/* 282 */       MatrixSeries series = (MatrixSeries)this.seriesList.get(i);
/* 283 */       series.removeChangeListener(this);
/*     */     }
/*     */     
/*     */ 
/* 287 */     this.seriesList.clear();
/* 288 */     fireDatasetChanged();
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
/*     */   public void removeSeries(MatrixSeries series)
/*     */   {
/* 304 */     if (series == null) {
/* 305 */       throw new IllegalArgumentException("Cannot remove null series.");
/*     */     }
/*     */     
/*     */ 
/* 309 */     if (this.seriesList.contains(series)) {
/* 310 */       series.removeChangeListener(this);
/* 311 */       this.seriesList.remove(series);
/* 312 */       fireDatasetChanged();
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeSeries(int seriesIndex)
/*     */   {
/* 328 */     if ((seriesIndex < 0) || (seriesIndex > getSeriesCount())) {
/* 329 */       throw new IllegalArgumentException("Index outside valid range.");
/*     */     }
/*     */     
/*     */ 
/* 333 */     MatrixSeries series = (MatrixSeries)this.seriesList.get(seriesIndex);
/* 334 */     series.removeChangeListener(this);
/* 335 */     this.seriesList.remove(seriesIndex);
/* 336 */     fireDatasetChanged();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/MatrixSeriesCollection.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */