/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.data.DomainInfo;
/*     */ import org.jfree.data.DomainOrder;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.RangeInfo;
/*     */ import org.jfree.data.UnknownKeyException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XYSeriesCollection
/*     */   extends AbstractIntervalXYDataset
/*     */   implements IntervalXYDataset, DomainInfo, RangeInfo, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7590013825931496766L;
/*     */   private List data;
/*     */   private IntervalXYDelegate intervalDelegate;
/*     */   
/*     */   public XYSeriesCollection()
/*     */   {
/* 100 */     this(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XYSeriesCollection(XYSeries series)
/*     */   {
/* 109 */     this.data = new ArrayList();
/* 110 */     this.intervalDelegate = new IntervalXYDelegate(this, false);
/* 111 */     addChangeListener(this.intervalDelegate);
/* 112 */     if (series != null) {
/* 113 */       this.data.add(series);
/* 114 */       series.addChangeListener(this);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DomainOrder getDomainOrder()
/*     */   {
/* 124 */     int seriesCount = getSeriesCount();
/* 125 */     for (int i = 0; i < seriesCount; i++) {
/* 126 */       XYSeries s = getSeries(i);
/* 127 */       if (!s.getAutoSort()) {
/* 128 */         return DomainOrder.NONE;
/*     */       }
/*     */     }
/* 131 */     return DomainOrder.ASCENDING;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addSeries(XYSeries series)
/*     */   {
/* 141 */     if (series == null) {
/* 142 */       throw new IllegalArgumentException("Null 'series' argument.");
/*     */     }
/* 144 */     this.data.add(series);
/* 145 */     series.addChangeListener(this);
/* 146 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeSeries(int series)
/*     */   {
/* 156 */     if ((series < 0) || (series >= getSeriesCount())) {
/* 157 */       throw new IllegalArgumentException("Series index out of bounds.");
/*     */     }
/*     */     
/*     */ 
/* 161 */     XYSeries ts = (XYSeries)this.data.get(series);
/* 162 */     ts.removeChangeListener(this);
/* 163 */     this.data.remove(series);
/* 164 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeSeries(XYSeries series)
/*     */   {
/* 174 */     if (series == null) {
/* 175 */       throw new IllegalArgumentException("Null 'series' argument.");
/*     */     }
/* 177 */     if (this.data.contains(series)) {
/* 178 */       series.removeChangeListener(this);
/* 179 */       this.data.remove(series);
/* 180 */       fireDatasetChanged();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeAllSeries()
/*     */   {
/* 191 */     for (int i = 0; i < this.data.size(); i++) {
/* 192 */       XYSeries series = (XYSeries)this.data.get(i);
/* 193 */       series.removeChangeListener(this);
/*     */     }
/*     */     
/*     */ 
/* 197 */     this.data.clear();
/* 198 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesCount()
/*     */   {
/* 207 */     return this.data.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getSeries()
/*     */   {
/* 216 */     return Collections.unmodifiableList(this.data);
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
/*     */   public int indexOf(XYSeries series)
/*     */   {
/* 230 */     if (series == null) {
/* 231 */       throw new IllegalArgumentException("Null 'series' argument.");
/*     */     }
/* 233 */     return this.data.indexOf(series);
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
/*     */   public XYSeries getSeries(int series)
/*     */   {
/* 247 */     if ((series < 0) || (series >= getSeriesCount())) {
/* 248 */       throw new IllegalArgumentException("Series index out of bounds");
/*     */     }
/* 250 */     return (XYSeries)this.data.get(series);
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
/*     */   public XYSeries getSeries(Comparable key)
/*     */   {
/* 266 */     if (key == null) {
/* 267 */       throw new IllegalArgumentException("Null 'key' argument.");
/*     */     }
/* 269 */     Iterator iterator = this.data.iterator();
/* 270 */     while (iterator.hasNext()) {
/* 271 */       XYSeries series = (XYSeries)iterator.next();
/* 272 */       if (key.equals(series.getKey())) {
/* 273 */         return series;
/*     */       }
/*     */     }
/* 276 */     throw new UnknownKeyException("Key not found: " + key);
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
/* 292 */     return getSeries(series).getKey();
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
/* 307 */     return getSeries(series).getItemCount();
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
/* 319 */     XYSeries ts = (XYSeries)this.data.get(series);
/* 320 */     XYDataItem xyItem = ts.getDataItem(item);
/* 321 */     return xyItem.getX();
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
/* 333 */     return this.intervalDelegate.getStartX(series, item);
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
/* 345 */     return this.intervalDelegate.getEndX(series, item);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getY(int series, int index)
/*     */   {
/* 357 */     XYSeries ts = (XYSeries)this.data.get(series);
/* 358 */     XYDataItem xyItem = ts.getDataItem(index);
/* 359 */     return xyItem.getY();
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
/* 371 */     return getY(series, item);
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
/* 383 */     return getY(series, item);
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
/* 394 */     if (obj == this) {
/* 395 */       return true;
/*     */     }
/* 397 */     if (!(obj instanceof XYSeriesCollection)) {
/* 398 */       return false;
/*     */     }
/* 400 */     XYSeriesCollection that = (XYSeriesCollection)obj;
/* 401 */     if (!this.intervalDelegate.equals(that.intervalDelegate)) {
/* 402 */       return false;
/*     */     }
/* 404 */     return ObjectUtilities.equal(this.data, that.data);
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
/* 415 */     XYSeriesCollection clone = (XYSeriesCollection)super.clone();
/* 416 */     clone.data = ((List)ObjectUtilities.deepClone(this.data));
/* 417 */     clone.intervalDelegate = ((IntervalXYDelegate)this.intervalDelegate.clone());
/*     */     
/* 419 */     return clone;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 428 */     int hash = 5;
/* 429 */     hash = HashUtilities.hashCode(hash, this.intervalDelegate);
/* 430 */     hash = HashUtilities.hashCode(hash, this.data);
/* 431 */     return hash;
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
/* 443 */     if (includeInterval) {
/* 444 */       return this.intervalDelegate.getDomainLowerBound(includeInterval);
/*     */     }
/*     */     
/* 447 */     double result = NaN.0D;
/* 448 */     int seriesCount = getSeriesCount();
/* 449 */     for (int s = 0; s < seriesCount; s++) {
/* 450 */       XYSeries series = getSeries(s);
/* 451 */       double lowX = series.getMinX();
/* 452 */       if (Double.isNaN(result)) {
/* 453 */         result = lowX;
/*     */ 
/*     */       }
/* 456 */       else if (!Double.isNaN(lowX)) {
/* 457 */         result = Math.min(result, lowX);
/*     */       }
/*     */     }
/*     */     
/* 461 */     return result;
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
/*     */   public double getDomainUpperBound(boolean includeInterval)
/*     */   {
/* 474 */     if (includeInterval) {
/* 475 */       return this.intervalDelegate.getDomainUpperBound(includeInterval);
/*     */     }
/*     */     
/* 478 */     double result = NaN.0D;
/* 479 */     int seriesCount = getSeriesCount();
/* 480 */     for (int s = 0; s < seriesCount; s++) {
/* 481 */       XYSeries series = getSeries(s);
/* 482 */       double hiX = series.getMaxX();
/* 483 */       if (Double.isNaN(result)) {
/* 484 */         result = hiX;
/*     */ 
/*     */       }
/* 487 */       else if (!Double.isNaN(hiX)) {
/* 488 */         result = Math.max(result, hiX);
/*     */       }
/*     */     }
/*     */     
/* 492 */     return result;
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
/*     */   public Range getDomainBounds(boolean includeInterval)
/*     */   {
/* 506 */     if (includeInterval) {
/* 507 */       return this.intervalDelegate.getDomainBounds(includeInterval);
/*     */     }
/*     */     
/* 510 */     double lower = Double.POSITIVE_INFINITY;
/* 511 */     double upper = Double.NEGATIVE_INFINITY;
/* 512 */     int seriesCount = getSeriesCount();
/* 513 */     for (int s = 0; s < seriesCount; s++) {
/* 514 */       XYSeries series = getSeries(s);
/* 515 */       double minX = series.getMinX();
/* 516 */       if (!Double.isNaN(minX)) {
/* 517 */         lower = Math.min(lower, minX);
/*     */       }
/* 519 */       double maxX = series.getMaxX();
/* 520 */       if (!Double.isNaN(maxX)) {
/* 521 */         upper = Math.max(upper, maxX);
/*     */       }
/*     */     }
/* 524 */     if (lower > upper) {
/* 525 */       return null;
/*     */     }
/*     */     
/* 528 */     return new Range(lower, upper);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getIntervalWidth()
/*     */   {
/* 540 */     return this.intervalDelegate.getIntervalWidth();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setIntervalWidth(double width)
/*     */   {
/* 550 */     if (width < 0.0D) {
/* 551 */       throw new IllegalArgumentException("Negative 'width' argument.");
/*     */     }
/* 553 */     this.intervalDelegate.setFixedIntervalWidth(width);
/* 554 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getIntervalPositionFactor()
/*     */   {
/* 563 */     return this.intervalDelegate.getIntervalPositionFactor();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setIntervalPositionFactor(double factor)
/*     */   {
/* 574 */     this.intervalDelegate.setIntervalPositionFactor(factor);
/* 575 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAutoWidth()
/*     */   {
/* 584 */     return this.intervalDelegate.isAutoWidth();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAutoWidth(boolean b)
/*     */   {
/* 594 */     this.intervalDelegate.setAutoWidth(b);
/* 595 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Range getRangeBounds(boolean includeInterval)
/*     */   {
/* 607 */     double lower = Double.POSITIVE_INFINITY;
/* 608 */     double upper = Double.NEGATIVE_INFINITY;
/* 609 */     int seriesCount = getSeriesCount();
/* 610 */     for (int s = 0; s < seriesCount; s++) {
/* 611 */       XYSeries series = getSeries(s);
/* 612 */       double minY = series.getMinY();
/* 613 */       if (!Double.isNaN(minY)) {
/* 614 */         lower = Math.min(lower, minY);
/*     */       }
/* 616 */       double maxY = series.getMaxY();
/* 617 */       if (!Double.isNaN(maxY)) {
/* 618 */         upper = Math.max(upper, maxY);
/*     */       }
/*     */     }
/* 621 */     if (lower > upper) {
/* 622 */       return null;
/*     */     }
/*     */     
/* 625 */     return new Range(lower, upper);
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
/*     */   public double getRangeLowerBound(boolean includeInterval)
/*     */   {
/* 638 */     double result = NaN.0D;
/* 639 */     int seriesCount = getSeriesCount();
/* 640 */     for (int s = 0; s < seriesCount; s++) {
/* 641 */       XYSeries series = getSeries(s);
/* 642 */       double lowY = series.getMinY();
/* 643 */       if (Double.isNaN(result)) {
/* 644 */         result = lowY;
/*     */ 
/*     */       }
/* 647 */       else if (!Double.isNaN(lowY)) {
/* 648 */         result = Math.min(result, lowY);
/*     */       }
/*     */     }
/*     */     
/* 652 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getRangeUpperBound(boolean includeInterval)
/*     */   {
/* 664 */     double result = NaN.0D;
/* 665 */     int seriesCount = getSeriesCount();
/* 666 */     for (int s = 0; s < seriesCount; s++) {
/* 667 */       XYSeries series = getSeries(s);
/* 668 */       double hiY = series.getMaxY();
/* 669 */       if (Double.isNaN(result)) {
/* 670 */         result = hiY;
/*     */ 
/*     */       }
/* 673 */       else if (!Double.isNaN(hiY)) {
/* 674 */         result = Math.max(result, hiY);
/*     */       }
/*     */     }
/*     */     
/* 678 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/XYSeriesCollection.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */