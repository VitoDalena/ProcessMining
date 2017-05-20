/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.jfree.data.DomainOrder;
/*     */ import org.jfree.data.general.DatasetChangeEvent;
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
/*     */ public class DefaultXYZDataset
/*     */   extends AbstractXYZDataset
/*     */   implements XYZDataset, PublicCloneable
/*     */ {
/*     */   private List seriesKeys;
/*     */   private List seriesList;
/*     */   
/*     */   public DefaultXYZDataset()
/*     */   {
/*  82 */     this.seriesKeys = new ArrayList();
/*  83 */     this.seriesList = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesCount()
/*     */   {
/*  92 */     return this.seriesList.size();
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
/*     */   public Comparable getSeriesKey(int series)
/*     */   {
/* 107 */     if ((series < 0) || (series >= getSeriesCount())) {
/* 108 */       throw new IllegalArgumentException("Series index out of bounds");
/*     */     }
/* 110 */     return (Comparable)this.seriesKeys.get(series);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int indexOf(Comparable seriesKey)
/*     */   {
/* 122 */     return this.seriesKeys.indexOf(seriesKey);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DomainOrder getDomainOrder()
/*     */   {
/* 133 */     return DomainOrder.NONE;
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
/* 148 */     if ((series < 0) || (series >= getSeriesCount())) {
/* 149 */       throw new IllegalArgumentException("Series index out of bounds");
/*     */     }
/* 151 */     double[][] seriesArray = (double[][])this.seriesList.get(series);
/* 152 */     return seriesArray[0].length;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getXValue(int series, int item)
/*     */   {
/* 173 */     double[][] seriesData = (double[][])this.seriesList.get(series);
/* 174 */     return seriesData[0][item];
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getX(int series, int item)
/*     */   {
/* 195 */     return new Double(getXValue(series, item));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getYValue(int series, int item)
/*     */   {
/* 216 */     double[][] seriesData = (double[][])this.seriesList.get(series);
/* 217 */     return seriesData[1][item];
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getY(int series, int item)
/*     */   {
/* 238 */     return new Double(getYValue(series, item));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getZValue(int series, int item)
/*     */   {
/* 259 */     double[][] seriesData = (double[][])this.seriesList.get(series);
/* 260 */     return seriesData[2][item];
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getZ(int series, int item)
/*     */   {
/* 281 */     return new Double(getZValue(series, item));
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
/*     */   public void addSeries(Comparable seriesKey, double[][] data)
/*     */   {
/* 296 */     if (seriesKey == null) {
/* 297 */       throw new IllegalArgumentException("The 'seriesKey' cannot be null.");
/*     */     }
/*     */     
/* 300 */     if (data == null) {
/* 301 */       throw new IllegalArgumentException("The 'data' is null.");
/*     */     }
/* 303 */     if (data.length != 3) {
/* 304 */       throw new IllegalArgumentException("The 'data' array must have length == 3.");
/*     */     }
/*     */     
/* 307 */     if ((data[0].length != data[1].length) || (data[0].length != data[2].length))
/*     */     {
/* 309 */       throw new IllegalArgumentException("The 'data' array must contain three arrays all having the same length.");
/*     */     }
/*     */     
/* 312 */     int seriesIndex = indexOf(seriesKey);
/* 313 */     if (seriesIndex == -1) {
/* 314 */       this.seriesKeys.add(seriesKey);
/* 315 */       this.seriesList.add(data);
/*     */     }
/*     */     else {
/* 318 */       this.seriesList.remove(seriesIndex);
/* 319 */       this.seriesList.add(seriesIndex, data);
/*     */     }
/* 321 */     notifyListeners(new DatasetChangeEvent(this, this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeSeries(Comparable seriesKey)
/*     */   {
/* 332 */     int seriesIndex = indexOf(seriesKey);
/* 333 */     if (seriesIndex >= 0) {
/* 334 */       this.seriesKeys.remove(seriesIndex);
/* 335 */       this.seriesList.remove(seriesIndex);
/* 336 */       notifyListeners(new DatasetChangeEvent(this, this));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 356 */     if (obj == this) {
/* 357 */       return true;
/*     */     }
/* 359 */     if (!(obj instanceof DefaultXYZDataset)) {
/* 360 */       return false;
/*     */     }
/* 362 */     DefaultXYZDataset that = (DefaultXYZDataset)obj;
/* 363 */     if (!this.seriesKeys.equals(that.seriesKeys)) {
/* 364 */       return false;
/*     */     }
/* 366 */     for (int i = 0; i < this.seriesList.size(); i++) {
/* 367 */       double[][] d1 = (double[][])this.seriesList.get(i);
/* 368 */       double[][] d2 = (double[][])that.seriesList.get(i);
/* 369 */       double[] d1x = d1[0];
/* 370 */       double[] d2x = d2[0];
/* 371 */       if (!Arrays.equals(d1x, d2x)) {
/* 372 */         return false;
/*     */       }
/* 374 */       double[] d1y = d1[1];
/* 375 */       double[] d2y = d2[1];
/* 376 */       if (!Arrays.equals(d1y, d2y)) {
/* 377 */         return false;
/*     */       }
/* 379 */       double[] d1z = d1[2];
/* 380 */       double[] d2z = d2[2];
/* 381 */       if (!Arrays.equals(d1z, d2z)) {
/* 382 */         return false;
/*     */       }
/*     */     }
/* 385 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 395 */     int result = this.seriesKeys.hashCode();
/* 396 */     result = 29 * result + this.seriesList.hashCode();
/* 397 */     return result;
/*     */   }
/*     */   
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
/* 410 */     DefaultXYZDataset clone = (DefaultXYZDataset)super.clone();
/* 411 */     clone.seriesKeys = new ArrayList(this.seriesKeys);
/* 412 */     clone.seriesList = new ArrayList(this.seriesList.size());
/* 413 */     for (int i = 0; i < this.seriesList.size(); i++) {
/* 414 */       double[][] data = (double[][])this.seriesList.get(i);
/* 415 */       double[] x = data[0];
/* 416 */       double[] y = data[1];
/* 417 */       double[] z = data[2];
/* 418 */       double[] xx = new double[x.length];
/* 419 */       double[] yy = new double[y.length];
/* 420 */       double[] zz = new double[z.length];
/* 421 */       System.arraycopy(x, 0, xx, 0, x.length);
/* 422 */       System.arraycopy(y, 0, yy, 0, y.length);
/* 423 */       System.arraycopy(z, 0, zz, 0, z.length);
/* 424 */       clone.seriesList.add(i, new double[][] { xx, yy, zz });
/*     */     }
/* 426 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/DefaultXYZDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */