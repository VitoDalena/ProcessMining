/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.TimeZone;
/*     */ import org.jfree.data.DefaultKeyedValues2D;
/*     */ import org.jfree.data.DomainInfo;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.general.DatasetChangeEvent;
/*     */ import org.jfree.data.xy.AbstractIntervalXYDataset;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ import org.jfree.data.xy.TableXYDataset;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TimeTableXYDataset
/*     */   extends AbstractIntervalXYDataset
/*     */   implements Cloneable, PublicCloneable, IntervalXYDataset, DomainInfo, TableXYDataset
/*     */ {
/*     */   private DefaultKeyedValues2D values;
/*     */   private boolean domainIsPointsInTime;
/*     */   private TimePeriodAnchor xPosition;
/*     */   private Calendar workingCalendar;
/*     */   
/*     */   public TimeTableXYDataset()
/*     */   {
/* 116 */     this(TimeZone.getDefault(), Locale.getDefault());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TimeTableXYDataset(TimeZone zone)
/*     */   {
/* 126 */     this(zone, Locale.getDefault());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TimeTableXYDataset(TimeZone zone, Locale locale)
/*     */   {
/* 136 */     if (zone == null) {
/* 137 */       throw new IllegalArgumentException("Null 'zone' argument.");
/*     */     }
/* 139 */     if (locale == null) {
/* 140 */       throw new IllegalArgumentException("Null 'locale' argument.");
/*     */     }
/* 142 */     this.values = new DefaultKeyedValues2D(true);
/* 143 */     this.workingCalendar = Calendar.getInstance(zone, locale);
/* 144 */     this.xPosition = TimePeriodAnchor.START;
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
/*     */   public boolean getDomainIsPointsInTime()
/*     */   {
/* 161 */     return this.domainIsPointsInTime;
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
/*     */   public void setDomainIsPointsInTime(boolean flag)
/*     */   {
/* 174 */     this.domainIsPointsInTime = flag;
/* 175 */     notifyListeners(new DatasetChangeEvent(this, this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TimePeriodAnchor getXPosition()
/*     */   {
/* 187 */     return this.xPosition;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setXPosition(TimePeriodAnchor anchor)
/*     */   {
/* 199 */     if (anchor == null) {
/* 200 */       throw new IllegalArgumentException("Null 'anchor' argument.");
/*     */     }
/* 202 */     this.xPosition = anchor;
/* 203 */     notifyListeners(new DatasetChangeEvent(this, this));
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
/*     */   public void add(TimePeriod period, double y, String seriesName)
/*     */   {
/* 217 */     add(period, new Double(y), seriesName, true);
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
/*     */   public void add(TimePeriod period, Number y, String seriesName, boolean notify)
/*     */   {
/* 234 */     this.values.addValue(y, period, seriesName);
/* 235 */     if (notify) {
/* 236 */       fireDatasetChanged();
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
/*     */   public void remove(TimePeriod period, String seriesName)
/*     */   {
/* 251 */     remove(period, seriesName, true);
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
/*     */   public void remove(TimePeriod period, String seriesName, boolean notify)
/*     */   {
/* 267 */     this.values.removeValue(period, seriesName);
/* 268 */     if (notify) {
/* 269 */       fireDatasetChanged();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 280 */     if (this.values.getRowCount() > 0) {
/* 281 */       this.values.clear();
/* 282 */       fireDatasetChanged();
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
/*     */   public TimePeriod getTimePeriod(int item)
/*     */   {
/* 295 */     return (TimePeriod)this.values.getRowKey(item);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getItemCount()
/*     */   {
/* 304 */     return this.values.getRowCount();
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
/*     */   public int getItemCount(int series)
/*     */   {
/* 317 */     return getItemCount();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesCount()
/*     */   {
/* 326 */     return this.values.getColumnCount();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getSeriesKey(int series)
/*     */   {
/* 337 */     return this.values.getColumnKey(series);
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
/* 351 */     return new Double(getXValue(series, item));
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
/* 363 */     TimePeriod period = (TimePeriod)this.values.getRowKey(item);
/* 364 */     return getXValue(period);
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
/*     */   public Number getStartX(int series, int item)
/*     */   {
/* 378 */     return new Double(getStartXValue(series, item));
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
/* 391 */     TimePeriod period = (TimePeriod)this.values.getRowKey(item);
/* 392 */     return period.getStart().getTime();
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
/*     */   public Number getEndX(int series, int item)
/*     */   {
/* 406 */     return new Double(getEndXValue(series, item));
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
/* 419 */     TimePeriod period = (TimePeriod)this.values.getRowKey(item);
/* 420 */     return period.getEnd().getTime();
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
/* 432 */     return this.values.getValue(item, series);
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
/* 444 */     return getY(series, item);
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
/* 456 */     return getY(series, item);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private long getXValue(TimePeriod period)
/*     */   {
/* 467 */     long result = 0L;
/* 468 */     if (this.xPosition == TimePeriodAnchor.START) {
/* 469 */       result = period.getStart().getTime();
/*     */     }
/* 471 */     else if (this.xPosition == TimePeriodAnchor.MIDDLE) {
/* 472 */       long t0 = period.getStart().getTime();
/* 473 */       long t1 = period.getEnd().getTime();
/* 474 */       result = t0 + (t1 - t0) / 2L;
/*     */     }
/* 476 */     else if (this.xPosition == TimePeriodAnchor.END) {
/* 477 */       result = period.getEnd().getTime();
/*     */     }
/* 479 */     return result;
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
/* 491 */     double result = NaN.0D;
/* 492 */     Range r = getDomainBounds(includeInterval);
/* 493 */     if (r != null) {
/* 494 */       result = r.getLowerBound();
/*     */     }
/* 496 */     return result;
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
/* 508 */     double result = NaN.0D;
/* 509 */     Range r = getDomainBounds(includeInterval);
/* 510 */     if (r != null) {
/* 511 */       result = r.getUpperBound();
/*     */     }
/* 513 */     return result;
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
/* 525 */     List keys = this.values.getRowKeys();
/* 526 */     if (keys.isEmpty()) {
/* 527 */       return null;
/*     */     }
/*     */     
/* 530 */     TimePeriod first = (TimePeriod)keys.get(0);
/* 531 */     TimePeriod last = (TimePeriod)keys.get(keys.size() - 1);
/*     */     
/* 533 */     if ((!includeInterval) || (this.domainIsPointsInTime)) {
/* 534 */       return new Range(getXValue(first), getXValue(last));
/*     */     }
/*     */     
/* 537 */     return new Range(first.getStart().getTime(), last.getEnd().getTime());
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 550 */     if (obj == this) {
/* 551 */       return true;
/*     */     }
/* 553 */     if (!(obj instanceof TimeTableXYDataset)) {
/* 554 */       return false;
/*     */     }
/* 556 */     TimeTableXYDataset that = (TimeTableXYDataset)obj;
/* 557 */     if (this.domainIsPointsInTime != that.domainIsPointsInTime) {
/* 558 */       return false;
/*     */     }
/* 560 */     if (this.xPosition != that.xPosition) {
/* 561 */       return false;
/*     */     }
/* 563 */     if (!this.workingCalendar.getTimeZone().equals(that.workingCalendar.getTimeZone()))
/*     */     {
/*     */ 
/* 566 */       return false;
/*     */     }
/* 568 */     if (!this.values.equals(that.values)) {
/* 569 */       return false;
/*     */     }
/* 571 */     return true;
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
/* 582 */     TimeTableXYDataset clone = (TimeTableXYDataset)super.clone();
/* 583 */     clone.values = ((DefaultKeyedValues2D)this.values.clone());
/* 584 */     clone.workingCalendar = ((Calendar)this.workingCalendar.clone());
/* 585 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/TimeTableXYDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */