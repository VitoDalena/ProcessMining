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
/*     */ public class DefaultXYDataset
/*     */   extends AbstractXYDataset
/*     */   implements XYDataset, PublicCloneable
/*     */ {
/*     */   private List seriesKeys;
/*     */   private List seriesList;
/*     */   
/*     */   public DefaultXYDataset()
/*     */   {
/*  81 */     this.seriesKeys = new ArrayList();
/*  82 */     this.seriesList = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesCount()
/*     */   {
/*  91 */     return this.seriesList.size();
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
/* 106 */     if ((series < 0) || (series >= getSeriesCount())) {
/* 107 */       throw new IllegalArgumentException("Series index out of bounds");
/*     */     }
/* 109 */     return (Comparable)this.seriesKeys.get(series);
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
/* 121 */     return this.seriesKeys.indexOf(seriesKey);
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
/* 132 */     return DomainOrder.NONE;
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
/* 147 */     if ((series < 0) || (series >= getSeriesCount())) {
/* 148 */       throw new IllegalArgumentException("Series index out of bounds");
/*     */     }
/* 150 */     double[][] seriesArray = (double[][])this.seriesList.get(series);
/* 151 */     return seriesArray[0].length;
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
/* 172 */     double[][] seriesData = (double[][])this.seriesList.get(series);
/* 173 */     return seriesData[0][item];
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
/* 194 */     return new Double(getXValue(series, item));
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
/* 215 */     double[][] seriesData = (double[][])this.seriesList.get(series);
/* 216 */     return seriesData[1][item];
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
/* 237 */     return new Double(getYValue(series, item));
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
/*     */   public void addSeries(Comparable seriesKey, double[][] data)
/*     */   {
/* 251 */     if (seriesKey == null) {
/* 252 */       throw new IllegalArgumentException("The 'seriesKey' cannot be null.");
/*     */     }
/*     */     
/* 255 */     if (data == null) {
/* 256 */       throw new IllegalArgumentException("The 'data' is null.");
/*     */     }
/* 258 */     if (data.length != 2) {
/* 259 */       throw new IllegalArgumentException("The 'data' array must have length == 2.");
/*     */     }
/*     */     
/* 262 */     if (data[0].length != data[1].length) {
/* 263 */       throw new IllegalArgumentException("The 'data' array must contain two arrays with equal length.");
/*     */     }
/*     */     
/* 266 */     int seriesIndex = indexOf(seriesKey);
/* 267 */     if (seriesIndex == -1) {
/* 268 */       this.seriesKeys.add(seriesKey);
/* 269 */       this.seriesList.add(data);
/*     */     }
/*     */     else {
/* 272 */       this.seriesList.remove(seriesIndex);
/* 273 */       this.seriesList.add(seriesIndex, data);
/*     */     }
/* 275 */     notifyListeners(new DatasetChangeEvent(this, this));
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
/* 286 */     int seriesIndex = indexOf(seriesKey);
/* 287 */     if (seriesIndex >= 0) {
/* 288 */       this.seriesKeys.remove(seriesIndex);
/* 289 */       this.seriesList.remove(seriesIndex);
/* 290 */       notifyListeners(new DatasetChangeEvent(this, this));
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
/* 310 */     if (obj == this) {
/* 311 */       return true;
/*     */     }
/* 313 */     if (!(obj instanceof DefaultXYDataset)) {
/* 314 */       return false;
/*     */     }
/* 316 */     DefaultXYDataset that = (DefaultXYDataset)obj;
/* 317 */     if (!this.seriesKeys.equals(that.seriesKeys)) {
/* 318 */       return false;
/*     */     }
/* 320 */     for (int i = 0; i < this.seriesList.size(); i++) {
/* 321 */       double[][] d1 = (double[][])this.seriesList.get(i);
/* 322 */       double[][] d2 = (double[][])that.seriesList.get(i);
/* 323 */       double[] d1x = d1[0];
/* 324 */       double[] d2x = d2[0];
/* 325 */       if (!Arrays.equals(d1x, d2x)) {
/* 326 */         return false;
/*     */       }
/* 328 */       double[] d1y = d1[1];
/* 329 */       double[] d2y = d2[1];
/* 330 */       if (!Arrays.equals(d1y, d2y)) {
/* 331 */         return false;
/*     */       }
/*     */     }
/* 334 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 344 */     int result = this.seriesKeys.hashCode();
/* 345 */     result = 29 * result + this.seriesList.hashCode();
/* 346 */     return result;
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
/* 359 */     DefaultXYDataset clone = (DefaultXYDataset)super.clone();
/* 360 */     clone.seriesKeys = new ArrayList(this.seriesKeys);
/* 361 */     clone.seriesList = new ArrayList(this.seriesList.size());
/* 362 */     for (int i = 0; i < this.seriesList.size(); i++) {
/* 363 */       double[][] data = (double[][])this.seriesList.get(i);
/* 364 */       double[] x = data[0];
/* 365 */       double[] y = data[1];
/* 366 */       double[] xx = new double[x.length];
/* 367 */       double[] yy = new double[y.length];
/* 368 */       System.arraycopy(x, 0, xx, 0, x.length);
/* 369 */       System.arraycopy(y, 0, yy, 0, y.length);
/* 370 */       clone.seriesList.add(i, new double[][] { xx, yy });
/*     */     }
/* 372 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/DefaultXYDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */