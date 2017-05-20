/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.TimeZone;
/*     */ import org.jfree.data.DomainInfo;
/*     */ import org.jfree.data.DomainOrder;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.general.DatasetChangeEvent;
/*     */ import org.jfree.data.xy.AbstractIntervalXYDataset;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TimeSeriesCollection
/*     */   extends AbstractIntervalXYDataset
/*     */   implements XYDataset, IntervalXYDataset, DomainInfo, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 834149929022371137L;
/*     */   private List data;
/*     */   private Calendar workingCalendar;
/*     */   private TimePeriodAnchor xPosition;
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   private boolean domainIsPointsInTime;
/*     */   
/*     */   public TimeSeriesCollection()
/*     */   {
/* 140 */     this(null, TimeZone.getDefault());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TimeSeriesCollection(TimeZone zone)
/*     */   {
/* 151 */     this(null, zone);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TimeSeriesCollection(TimeSeries series)
/*     */   {
/* 161 */     this(series, TimeZone.getDefault());
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
/*     */   public TimeSeriesCollection(TimeSeries series, TimeZone zone)
/*     */   {
/* 175 */     if (zone == null) {
/* 176 */       zone = TimeZone.getDefault();
/*     */     }
/* 178 */     this.workingCalendar = Calendar.getInstance(zone);
/* 179 */     this.data = new ArrayList();
/* 180 */     if (series != null) {
/* 181 */       this.data.add(series);
/* 182 */       series.addChangeListener(this);
/*     */     }
/* 184 */     this.xPosition = TimePeriodAnchor.START;
/* 185 */     this.domainIsPointsInTime = true;
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
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public boolean getDomainIsPointsInTime()
/*     */   {
/* 201 */     return this.domainIsPointsInTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public void setDomainIsPointsInTime(boolean flag)
/*     */   {
/* 215 */     this.domainIsPointsInTime = flag;
/* 216 */     notifyListeners(new DatasetChangeEvent(this, this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DomainOrder getDomainOrder()
/*     */   {
/* 225 */     return DomainOrder.ASCENDING;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TimePeriodAnchor getXPosition()
/*     */   {
/* 236 */     return this.xPosition;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setXPosition(TimePeriodAnchor anchor)
/*     */   {
/* 247 */     if (anchor == null) {
/* 248 */       throw new IllegalArgumentException("Null 'anchor' argument.");
/*     */     }
/* 250 */     this.xPosition = anchor;
/* 251 */     notifyListeners(new DatasetChangeEvent(this, this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getSeries()
/*     */   {
/* 260 */     return Collections.unmodifiableList(this.data);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesCount()
/*     */   {
/* 269 */     return this.data.size();
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
/*     */   public int indexOf(TimeSeries series)
/*     */   {
/* 283 */     if (series == null) {
/* 284 */       throw new IllegalArgumentException("Null 'series' argument.");
/*     */     }
/* 286 */     return this.data.indexOf(series);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TimeSeries getSeries(int series)
/*     */   {
/* 297 */     if ((series < 0) || (series >= getSeriesCount())) {
/* 298 */       throw new IllegalArgumentException("The 'series' argument is out of bounds (" + series + ").");
/*     */     }
/*     */     
/* 301 */     return (TimeSeries)this.data.get(series);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TimeSeries getSeries(Comparable key)
/*     */   {
/* 313 */     TimeSeries result = null;
/* 314 */     Iterator iterator = this.data.iterator();
/* 315 */     while (iterator.hasNext()) {
/* 316 */       TimeSeries series = (TimeSeries)iterator.next();
/* 317 */       Comparable k = series.getKey();
/* 318 */       if ((k != null) && (k.equals(key))) {
/* 319 */         result = series;
/*     */       }
/*     */     }
/* 322 */     return result;
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
/*     */   public Comparable getSeriesKey(int series)
/*     */   {
/* 335 */     return getSeries(series).getKey();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addSeries(TimeSeries series)
/*     */   {
/* 345 */     if (series == null) {
/* 346 */       throw new IllegalArgumentException("Null 'series' argument.");
/*     */     }
/* 348 */     this.data.add(series);
/* 349 */     series.addChangeListener(this);
/* 350 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeSeries(TimeSeries series)
/*     */   {
/* 360 */     if (series == null) {
/* 361 */       throw new IllegalArgumentException("Null 'series' argument.");
/*     */     }
/* 363 */     this.data.remove(series);
/* 364 */     series.removeChangeListener(this);
/* 365 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeSeries(int index)
/*     */   {
/* 374 */     TimeSeries series = getSeries(index);
/* 375 */     if (series != null) {
/* 376 */       removeSeries(series);
/*     */     }
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
/* 388 */     for (int i = 0; i < this.data.size(); i++) {
/* 389 */       TimeSeries series = (TimeSeries)this.data.get(i);
/* 390 */       series.removeChangeListener(this);
/*     */     }
/*     */     
/*     */ 
/* 394 */     this.data.clear();
/* 395 */     fireDatasetChanged();
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
/* 408 */     return getSeries(series).getItemCount();
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
/* 420 */     TimeSeries s = (TimeSeries)this.data.get(series);
/* 421 */     TimeSeriesDataItem i = s.getDataItem(item);
/* 422 */     RegularTimePeriod period = i.getPeriod();
/* 423 */     return getX(period);
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
/* 435 */     TimeSeries ts = (TimeSeries)this.data.get(series);
/* 436 */     TimeSeriesDataItem dp = ts.getDataItem(item);
/* 437 */     RegularTimePeriod period = dp.getPeriod();
/* 438 */     return new Long(getX(period));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized long getX(RegularTimePeriod period)
/*     */   {
/* 449 */     long result = 0L;
/* 450 */     if (this.xPosition == TimePeriodAnchor.START) {
/* 451 */       result = period.getFirstMillisecond(this.workingCalendar);
/*     */     }
/* 453 */     else if (this.xPosition == TimePeriodAnchor.MIDDLE) {
/* 454 */       result = period.getMiddleMillisecond(this.workingCalendar);
/*     */     }
/* 456 */     else if (this.xPosition == TimePeriodAnchor.END) {
/* 457 */       result = period.getLastMillisecond(this.workingCalendar);
/*     */     }
/* 459 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized Number getStartX(int series, int item)
/*     */   {
/* 471 */     TimeSeries ts = (TimeSeries)this.data.get(series);
/* 472 */     TimeSeriesDataItem dp = ts.getDataItem(item);
/* 473 */     return new Long(dp.getPeriod().getFirstMillisecond(this.workingCalendar));
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
/*     */   public synchronized Number getEndX(int series, int item)
/*     */   {
/* 486 */     TimeSeries ts = (TimeSeries)this.data.get(series);
/* 487 */     TimeSeriesDataItem dp = ts.getDataItem(item);
/* 488 */     return new Long(dp.getPeriod().getLastMillisecond(this.workingCalendar));
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
/*     */   public Number getY(int series, int item)
/*     */   {
/* 501 */     TimeSeries ts = (TimeSeries)this.data.get(series);
/* 502 */     TimeSeriesDataItem dp = ts.getDataItem(item);
/* 503 */     return dp.getValue();
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
/* 515 */     return getY(series, item);
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
/* 527 */     return getY(series, item);
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
/*     */   public int[] getSurroundingItems(int series, long milliseconds)
/*     */   {
/* 542 */     int[] result = { -1, -1 };
/* 543 */     TimeSeries timeSeries = getSeries(series);
/* 544 */     for (int i = 0; i < timeSeries.getItemCount(); i++) {
/* 545 */       Number x = getX(series, i);
/* 546 */       long m = x.longValue();
/* 547 */       if (m <= milliseconds) {
/* 548 */         result[0] = i;
/*     */       }
/* 550 */       if (m >= milliseconds) {
/* 551 */         result[1] = i;
/* 552 */         break;
/*     */       }
/*     */     }
/* 555 */     return result;
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
/* 567 */     double result = NaN.0D;
/* 568 */     Range r = getDomainBounds(includeInterval);
/* 569 */     if (r != null) {
/* 570 */       result = r.getLowerBound();
/*     */     }
/* 572 */     return result;
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
/* 584 */     double result = NaN.0D;
/* 585 */     Range r = getDomainBounds(includeInterval);
/* 586 */     if (r != null) {
/* 587 */       result = r.getUpperBound();
/*     */     }
/* 589 */     return result;
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
/* 601 */     Range result = null;
/* 602 */     Iterator iterator = this.data.iterator();
/* 603 */     while (iterator.hasNext()) {
/* 604 */       TimeSeries series = (TimeSeries)iterator.next();
/* 605 */       int count = series.getItemCount();
/* 606 */       if (count > 0) {
/* 607 */         RegularTimePeriod start = series.getTimePeriod(0);
/* 608 */         RegularTimePeriod end = series.getTimePeriod(count - 1);
/*     */         Range temp;
/* 610 */         Range temp; if (!includeInterval) {
/* 611 */           temp = new Range(getX(start), getX(end));
/*     */         }
/*     */         else {
/* 614 */           temp = new Range(start.getFirstMillisecond(this.workingCalendar), end.getLastMillisecond(this.workingCalendar));
/*     */         }
/*     */         
/*     */ 
/* 618 */         result = Range.combine(result, temp);
/*     */       }
/*     */     }
/* 621 */     return result;
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
/* 632 */     if (obj == this) {
/* 633 */       return true;
/*     */     }
/* 635 */     if (!(obj instanceof TimeSeriesCollection)) {
/* 636 */       return false;
/*     */     }
/* 638 */     TimeSeriesCollection that = (TimeSeriesCollection)obj;
/* 639 */     if (this.xPosition != that.xPosition) {
/* 640 */       return false;
/*     */     }
/* 642 */     if (this.domainIsPointsInTime != that.domainIsPointsInTime) {
/* 643 */       return false;
/*     */     }
/* 645 */     if (!ObjectUtilities.equal(this.data, that.data)) {
/* 646 */       return false;
/*     */     }
/* 648 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 658 */     int result = this.data.hashCode();
/* 659 */     result = 29 * result + (this.workingCalendar != null ? this.workingCalendar.hashCode() : 0);
/*     */     
/* 661 */     result = 29 * result + (this.xPosition != null ? this.xPosition.hashCode() : 0);
/*     */     
/* 663 */     result = 29 * result + (this.domainIsPointsInTime ? 1 : 0);
/* 664 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/TimeSeriesCollection.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */