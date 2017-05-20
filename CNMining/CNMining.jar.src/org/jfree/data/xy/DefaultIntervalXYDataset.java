/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultIntervalXYDataset
/*     */   extends AbstractIntervalXYDataset
/*     */   implements PublicCloneable
/*     */ {
/*     */   private List seriesKeys;
/*     */   private List seriesList;
/*     */   
/*     */   public DefaultIntervalXYDataset()
/*     */   {
/*  85 */     this.seriesKeys = new ArrayList();
/*  86 */     this.seriesList = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesCount()
/*     */   {
/*  95 */     return this.seriesList.size();
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
/* 110 */     if ((series < 0) || (series >= getSeriesCount())) {
/* 111 */       throw new IllegalArgumentException("Series index out of bounds");
/*     */     }
/* 113 */     return (Comparable)this.seriesKeys.get(series);
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
/* 128 */     if ((series < 0) || (series >= getSeriesCount())) {
/* 129 */       throw new IllegalArgumentException("Series index out of bounds");
/*     */     }
/* 131 */     double[][] seriesArray = (double[][])this.seriesList.get(series);
/* 132 */     return seriesArray[0].length;
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
/* 153 */     double[][] seriesData = (double[][])this.seriesList.get(series);
/* 154 */     return seriesData[0][item];
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
/* 175 */     double[][] seriesData = (double[][])this.seriesList.get(series);
/* 176 */     return seriesData[3][item];
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
/*     */   public double getStartXValue(int series, int item)
/*     */   {
/* 197 */     double[][] seriesData = (double[][])this.seriesList.get(series);
/* 198 */     return seriesData[1][item];
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
/*     */   public double getEndXValue(int series, int item)
/*     */   {
/* 219 */     double[][] seriesData = (double[][])this.seriesList.get(series);
/* 220 */     return seriesData[2][item];
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
/*     */   public double getStartYValue(int series, int item)
/*     */   {
/* 241 */     double[][] seriesData = (double[][])this.seriesList.get(series);
/* 242 */     return seriesData[4][item];
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
/*     */   public double getEndYValue(int series, int item)
/*     */   {
/* 263 */     double[][] seriesData = (double[][])this.seriesList.get(series);
/* 264 */     return seriesData[5][item];
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
/*     */   public Number getEndX(int series, int item)
/*     */   {
/* 285 */     return new Double(getEndXValue(series, item));
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
/*     */   public Number getEndY(int series, int item)
/*     */   {
/* 306 */     return new Double(getEndYValue(series, item));
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
/*     */   public Number getStartX(int series, int item)
/*     */   {
/* 327 */     return new Double(getStartXValue(series, item));
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
/*     */   public Number getStartY(int series, int item)
/*     */   {
/* 348 */     return new Double(getStartYValue(series, item));
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
/* 369 */     return new Double(getXValue(series, item));
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
/* 390 */     return new Double(getYValue(series, item));
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
/* 404 */     if (seriesKey == null) {
/* 405 */       throw new IllegalArgumentException("The 'seriesKey' cannot be null.");
/*     */     }
/*     */     
/* 408 */     if (data == null) {
/* 409 */       throw new IllegalArgumentException("The 'data' is null.");
/*     */     }
/* 411 */     if (data.length != 6) {
/* 412 */       throw new IllegalArgumentException("The 'data' array must have length == 6.");
/*     */     }
/*     */     
/* 415 */     int length = data[0].length;
/* 416 */     if ((length != data[1].length) || (length != data[2].length) || (length != data[3].length) || (length != data[4].length) || (length != data[5].length))
/*     */     {
/*     */ 
/* 419 */       throw new IllegalArgumentException("The 'data' array must contain two arrays with equal length.");
/*     */     }
/*     */     
/* 422 */     int seriesIndex = indexOf(seriesKey);
/* 423 */     if (seriesIndex == -1) {
/* 424 */       this.seriesKeys.add(seriesKey);
/* 425 */       this.seriesList.add(data);
/*     */     }
/*     */     else {
/* 428 */       this.seriesList.remove(seriesIndex);
/* 429 */       this.seriesList.add(seriesIndex, data);
/*     */     }
/* 431 */     notifyListeners(new DatasetChangeEvent(this, this));
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 451 */     if (obj == this) {
/* 452 */       return true;
/*     */     }
/* 454 */     if (!(obj instanceof DefaultIntervalXYDataset)) {
/* 455 */       return false;
/*     */     }
/* 457 */     DefaultIntervalXYDataset that = (DefaultIntervalXYDataset)obj;
/* 458 */     if (!this.seriesKeys.equals(that.seriesKeys)) {
/* 459 */       return false;
/*     */     }
/* 461 */     for (int i = 0; i < this.seriesList.size(); i++) {
/* 462 */       double[][] d1 = (double[][])this.seriesList.get(i);
/* 463 */       double[][] d2 = (double[][])that.seriesList.get(i);
/* 464 */       double[] d1x = d1[0];
/* 465 */       double[] d2x = d2[0];
/* 466 */       if (!Arrays.equals(d1x, d2x)) {
/* 467 */         return false;
/*     */       }
/* 469 */       double[] d1xs = d1[1];
/* 470 */       double[] d2xs = d2[1];
/* 471 */       if (!Arrays.equals(d1xs, d2xs)) {
/* 472 */         return false;
/*     */       }
/* 474 */       double[] d1xe = d1[2];
/* 475 */       double[] d2xe = d2[2];
/* 476 */       if (!Arrays.equals(d1xe, d2xe)) {
/* 477 */         return false;
/*     */       }
/* 479 */       double[] d1y = d1[3];
/* 480 */       double[] d2y = d2[3];
/* 481 */       if (!Arrays.equals(d1y, d2y)) {
/* 482 */         return false;
/*     */       }
/* 484 */       double[] d1ys = d1[4];
/* 485 */       double[] d2ys = d2[4];
/* 486 */       if (!Arrays.equals(d1ys, d2ys)) {
/* 487 */         return false;
/*     */       }
/* 489 */       double[] d1ye = d1[5];
/* 490 */       double[] d2ye = d2[5];
/* 491 */       if (!Arrays.equals(d1ye, d2ye)) {
/* 492 */         return false;
/*     */       }
/*     */     }
/* 495 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 505 */     int result = this.seriesKeys.hashCode();
/* 506 */     result = 29 * result + this.seriesList.hashCode();
/* 507 */     return result;
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
/* 519 */     DefaultIntervalXYDataset clone = (DefaultIntervalXYDataset)super.clone();
/*     */     
/* 521 */     clone.seriesKeys = new ArrayList(this.seriesKeys);
/* 522 */     clone.seriesList = new ArrayList(this.seriesList.size());
/* 523 */     for (int i = 0; i < this.seriesList.size(); i++) {
/* 524 */       double[][] data = (double[][])this.seriesList.get(i);
/* 525 */       double[] x = data[0];
/* 526 */       double[] xStart = data[1];
/* 527 */       double[] xEnd = data[2];
/* 528 */       double[] y = data[3];
/* 529 */       double[] yStart = data[4];
/* 530 */       double[] yEnd = data[5];
/* 531 */       double[] xx = new double[x.length];
/* 532 */       double[] xxStart = new double[xStart.length];
/* 533 */       double[] xxEnd = new double[xEnd.length];
/* 534 */       double[] yy = new double[y.length];
/* 535 */       double[] yyStart = new double[yStart.length];
/* 536 */       double[] yyEnd = new double[yEnd.length];
/* 537 */       System.arraycopy(x, 0, xx, 0, x.length);
/* 538 */       System.arraycopy(xStart, 0, xxStart, 0, xStart.length);
/* 539 */       System.arraycopy(xEnd, 0, xxEnd, 0, xEnd.length);
/* 540 */       System.arraycopy(y, 0, yy, 0, y.length);
/* 541 */       System.arraycopy(yStart, 0, yyStart, 0, yStart.length);
/* 542 */       System.arraycopy(yEnd, 0, yyEnd, 0, yEnd.length);
/* 543 */       clone.seriesList.add(i, new double[][] { xx, xxStart, xxEnd, yy, yyStart, yyEnd });
/*     */     }
/*     */     
/* 546 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/DefaultIntervalXYDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */