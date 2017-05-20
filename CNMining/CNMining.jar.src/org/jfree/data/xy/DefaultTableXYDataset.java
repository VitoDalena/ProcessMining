/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.data.DomainInfo;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ import org.jfree.data.general.SeriesChangeEvent;
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
/*     */ public class DefaultTableXYDataset
/*     */   extends AbstractIntervalXYDataset
/*     */   implements TableXYDataset, IntervalXYDataset, DomainInfo, PublicCloneable
/*     */ {
/*  89 */   private List data = null;
/*     */   
/*     */ 
/*  92 */   private HashSet xPoints = null;
/*     */   
/*     */ 
/*  95 */   private boolean propagateEvents = true;
/*     */   
/*     */ 
/*  98 */   private boolean autoPrune = false;
/*     */   
/*     */ 
/*     */   private IntervalXYDelegate intervalDelegate;
/*     */   
/*     */ 
/*     */ 
/*     */   public DefaultTableXYDataset()
/*     */   {
/* 107 */     this(false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultTableXYDataset(boolean autoPrune)
/*     */   {
/* 118 */     this.autoPrune = autoPrune;
/* 119 */     this.data = new ArrayList();
/* 120 */     this.xPoints = new HashSet();
/* 121 */     this.intervalDelegate = new IntervalXYDelegate(this, false);
/* 122 */     addChangeListener(this.intervalDelegate);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAutoPrune()
/*     */   {
/* 132 */     return this.autoPrune;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addSeries(XYSeries series)
/*     */   {
/* 143 */     if (series == null) {
/* 144 */       throw new IllegalArgumentException("Null 'series' argument.");
/*     */     }
/* 146 */     if (series.getAllowDuplicateXValues()) {
/* 147 */       throw new IllegalArgumentException("Cannot accept XYSeries that allow duplicate values. Use XYSeries(seriesName, <sort>, false) constructor.");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 152 */     updateXPoints(series);
/* 153 */     this.data.add(series);
/* 154 */     series.addChangeListener(this);
/* 155 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void updateXPoints(XYSeries series)
/*     */   {
/* 165 */     if (series == null) {
/* 166 */       throw new IllegalArgumentException("Null 'series' not permitted.");
/*     */     }
/* 168 */     HashSet seriesXPoints = new HashSet();
/* 169 */     boolean savedState = this.propagateEvents;
/* 170 */     this.propagateEvents = false;
/* 171 */     for (int itemNo = 0; itemNo < series.getItemCount(); itemNo++) {
/* 172 */       Number xValue = series.getX(itemNo);
/* 173 */       seriesXPoints.add(xValue);
/* 174 */       if (!this.xPoints.contains(xValue)) {
/* 175 */         this.xPoints.add(xValue);
/* 176 */         int seriesCount = this.data.size();
/* 177 */         for (int seriesNo = 0; seriesNo < seriesCount; seriesNo++) {
/* 178 */           XYSeries dataSeries = (XYSeries)this.data.get(seriesNo);
/* 179 */           if (!dataSeries.equals(series)) {
/* 180 */             dataSeries.add(xValue, null);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 185 */     Iterator iterator = this.xPoints.iterator();
/* 186 */     while (iterator.hasNext()) {
/* 187 */       Number xPoint = (Number)iterator.next();
/* 188 */       if (!seriesXPoints.contains(xPoint)) {
/* 189 */         series.add(xPoint, null);
/*     */       }
/*     */     }
/* 192 */     this.propagateEvents = savedState;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void updateXPoints()
/*     */   {
/* 199 */     this.propagateEvents = false;
/* 200 */     for (int s = 0; s < this.data.size(); s++) {
/* 201 */       updateXPoints((XYSeries)this.data.get(s));
/*     */     }
/* 203 */     if (this.autoPrune) {
/* 204 */       prune();
/*     */     }
/* 206 */     this.propagateEvents = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesCount()
/*     */   {
/* 215 */     return this.data.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getItemCount()
/*     */   {
/* 224 */     if (this.xPoints == null) {
/* 225 */       return 0;
/*     */     }
/*     */     
/* 228 */     return this.xPoints.size();
/*     */   }
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
/* 240 */     if ((series < 0) || (series >= getSeriesCount())) {
/* 241 */       throw new IllegalArgumentException("Index outside valid range.");
/*     */     }
/* 243 */     return (XYSeries)this.data.get(series);
/*     */   }
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
/* 255 */     return getSeries(series).getKey();
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
/* 267 */     return getSeries(series).getItemCount();
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
/* 279 */     XYSeries s = (XYSeries)this.data.get(series);
/* 280 */     XYDataItem dataItem = s.getDataItem(item);
/* 281 */     return dataItem.getX();
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
/* 293 */     return this.intervalDelegate.getStartX(series, item);
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
/* 305 */     return this.intervalDelegate.getEndX(series, item);
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
/*     */   public Number getY(int series, int index)
/*     */   {
/* 318 */     XYSeries ts = (XYSeries)this.data.get(series);
/* 319 */     XYDataItem dataItem = ts.getDataItem(index);
/* 320 */     return dataItem.getY();
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
/* 332 */     return getY(series, item);
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
/* 344 */     return getY(series, item);
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
/* 355 */     for (int i = 0; i < this.data.size(); i++) {
/* 356 */       XYSeries series = (XYSeries)this.data.get(i);
/* 357 */       series.removeChangeListener(this);
/*     */     }
/*     */     
/*     */ 
/* 361 */     this.data.clear();
/* 362 */     this.xPoints.clear();
/* 363 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeSeries(XYSeries series)
/*     */   {
/* 375 */     if (series == null) {
/* 376 */       throw new IllegalArgumentException("Null 'series' argument.");
/*     */     }
/*     */     
/*     */ 
/* 380 */     if (this.data.contains(series)) {
/* 381 */       series.removeChangeListener(this);
/* 382 */       this.data.remove(series);
/* 383 */       if (this.data.size() == 0) {
/* 384 */         this.xPoints.clear();
/*     */       }
/* 386 */       fireDatasetChanged();
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
/*     */   public void removeSeries(int series)
/*     */   {
/* 400 */     if ((series < 0) || (series > getSeriesCount())) {
/* 401 */       throw new IllegalArgumentException("Index outside valid range.");
/*     */     }
/*     */     
/*     */ 
/* 405 */     XYSeries s = (XYSeries)this.data.get(series);
/* 406 */     s.removeChangeListener(this);
/* 407 */     this.data.remove(series);
/* 408 */     if (this.data.size() == 0) {
/* 409 */       this.xPoints.clear();
/*     */     }
/* 411 */     else if (this.autoPrune) {
/* 412 */       prune();
/*     */     }
/* 414 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeAllValuesForX(Number x)
/*     */   {
/* 424 */     if (x == null) {
/* 425 */       throw new IllegalArgumentException("Null 'x' argument.");
/*     */     }
/* 427 */     boolean savedState = this.propagateEvents;
/* 428 */     this.propagateEvents = false;
/* 429 */     for (int s = 0; s < this.data.size(); s++) {
/* 430 */       XYSeries series = (XYSeries)this.data.get(s);
/* 431 */       series.remove(x);
/*     */     }
/* 433 */     this.propagateEvents = savedState;
/* 434 */     this.xPoints.remove(x);
/* 435 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean canPrune(Number x)
/*     */   {
/* 447 */     for (int s = 0; s < this.data.size(); s++) {
/* 448 */       XYSeries series = (XYSeries)this.data.get(s);
/* 449 */       if (series.getY(series.indexOf(x)) != null) {
/* 450 */         return false;
/*     */       }
/*     */     }
/* 453 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void prune()
/*     */   {
/* 460 */     HashSet hs = (HashSet)this.xPoints.clone();
/* 461 */     Iterator iterator = hs.iterator();
/* 462 */     while (iterator.hasNext()) {
/* 463 */       Number x = (Number)iterator.next();
/* 464 */       if (canPrune(x)) {
/* 465 */         removeAllValuesForX(x);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void seriesChanged(SeriesChangeEvent event)
/*     */   {
/* 478 */     if (this.propagateEvents) {
/* 479 */       updateXPoints();
/* 480 */       fireDatasetChanged();
/*     */     }
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
/* 492 */     if (obj == this) {
/* 493 */       return true;
/*     */     }
/* 495 */     if (!(obj instanceof DefaultTableXYDataset)) {
/* 496 */       return false;
/*     */     }
/* 498 */     DefaultTableXYDataset that = (DefaultTableXYDataset)obj;
/* 499 */     if (this.autoPrune != that.autoPrune) {
/* 500 */       return false;
/*     */     }
/* 502 */     if (this.propagateEvents != that.propagateEvents) {
/* 503 */       return false;
/*     */     }
/* 505 */     if (!this.intervalDelegate.equals(that.intervalDelegate)) {
/* 506 */       return false;
/*     */     }
/* 508 */     if (!ObjectUtilities.equal(this.data, that.data)) {
/* 509 */       return false;
/*     */     }
/* 511 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 521 */     int result = this.data != null ? this.data.hashCode() : 0;
/* 522 */     result = 29 * result + (this.xPoints != null ? this.xPoints.hashCode() : 0);
/*     */     
/* 524 */     result = 29 * result + (this.propagateEvents ? 1 : 0);
/* 525 */     result = 29 * result + (this.autoPrune ? 1 : 0);
/* 526 */     return result;
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
/* 538 */     DefaultTableXYDataset clone = (DefaultTableXYDataset)super.clone();
/* 539 */     int seriesCount = this.data.size();
/* 540 */     clone.data = new ArrayList(seriesCount);
/* 541 */     for (int i = 0; i < seriesCount; i++) {
/* 542 */       XYSeries series = (XYSeries)this.data.get(i);
/* 543 */       clone.data.add(series.clone());
/*     */     }
/*     */     
/* 546 */     clone.intervalDelegate = new IntervalXYDelegate(clone);
/*     */     
/* 548 */     clone.intervalDelegate.setFixedIntervalWidth(getIntervalWidth());
/* 549 */     clone.intervalDelegate.setAutoWidth(isAutoWidth());
/* 550 */     clone.intervalDelegate.setIntervalPositionFactor(getIntervalPositionFactor());
/*     */     
/* 552 */     clone.updateXPoints();
/* 553 */     return clone;
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
/* 565 */     return this.intervalDelegate.getDomainLowerBound(includeInterval);
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
/* 577 */     return this.intervalDelegate.getDomainUpperBound(includeInterval);
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
/* 589 */     if (includeInterval) {
/* 590 */       return this.intervalDelegate.getDomainBounds(includeInterval);
/*     */     }
/*     */     
/* 593 */     return DatasetUtilities.iterateDomainBounds(this, includeInterval);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getIntervalPositionFactor()
/*     */   {
/* 603 */     return this.intervalDelegate.getIntervalPositionFactor();
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
/* 615 */     this.intervalDelegate.setIntervalPositionFactor(d);
/* 616 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getIntervalWidth()
/*     */   {
/* 625 */     return this.intervalDelegate.getIntervalWidth();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setIntervalWidth(double d)
/*     */   {
/* 635 */     this.intervalDelegate.setFixedIntervalWidth(d);
/* 636 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAutoWidth()
/*     */   {
/* 646 */     return this.intervalDelegate.isAutoWidth();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAutoWidth(boolean b)
/*     */   {
/* 656 */     this.intervalDelegate.setAutoWidth(b);
/* 657 */     fireDatasetChanged();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/DefaultTableXYDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */