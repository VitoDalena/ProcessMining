/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import java.util.TimeZone;
/*     */ import org.jfree.data.DomainInfo;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.RangeInfo;
/*     */ import org.jfree.data.general.SeriesChangeEvent;
/*     */ import org.jfree.data.xy.AbstractIntervalXYDataset;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DynamicTimeSeriesCollection
/*     */   extends AbstractIntervalXYDataset
/*     */   implements IntervalXYDataset, DomainInfo, RangeInfo
/*     */ {
/*     */   public static final int START = 0;
/*     */   public static final int MIDDLE = 1;
/*     */   public static final int END = 2;
/* 106 */   private int maximumItemCount = 2000;
/*     */   
/*     */ 
/*     */   protected int historyCount;
/*     */   
/*     */ 
/*     */   private Comparable[] seriesKeys;
/*     */   
/*     */ 
/* 115 */   private Class timePeriodClass = Minute.class;
/*     */   protected RegularTimePeriod[] pointsInTime;
/*     */   private int seriesCount;
/*     */   protected ValueSequence[] valueHistory;
/*     */   protected Calendar workingCalendar;
/*     */   private int position;
/*     */   private boolean domainIsPointsInTime;
/*     */   private int oldestAt;
/*     */   private int newestAt;
/*     */   private long deltaTime;
/*     */   private Long domainStart;
/*     */   private Long domainEnd;
/*     */   private Range domainRange;
/*     */   
/*     */   protected class ValueSequence
/*     */   {
/*     */     float[] dataPoints;
/*     */     
/*     */     public ValueSequence()
/*     */     {
/* 135 */       this(DynamicTimeSeriesCollection.this.maximumItemCount);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public ValueSequence(int length)
/*     */     {
/* 144 */       this.dataPoints = new float[length];
/* 145 */       for (int i = 0; i < length; i++) {
/* 146 */         this.dataPoints[i] = 0.0F;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void enterData(int index, float value)
/*     */     {
/* 157 */       this.dataPoints[index] = value;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public float getData(int index)
/*     */     {
/* 168 */       return this.dataPoints[index];
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 215 */   private Float minValue = new Float(0.0F);
/*     */   
/*     */ 
/* 218 */   private Float maxValue = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Range valueRange;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DynamicTimeSeriesCollection(int nSeries, int nMoments)
/*     */   {
/* 232 */     this(nSeries, nMoments, new Millisecond(), TimeZone.getDefault());
/* 233 */     this.newestAt = (nMoments - 1);
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
/*     */   public DynamicTimeSeriesCollection(int nSeries, int nMoments, TimeZone zone)
/*     */   {
/* 246 */     this(nSeries, nMoments, new Millisecond(), zone);
/* 247 */     this.newestAt = (nMoments - 1);
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
/*     */   public DynamicTimeSeriesCollection(int nSeries, int nMoments, RegularTimePeriod timeSample)
/*     */   {
/* 260 */     this(nSeries, nMoments, timeSample, TimeZone.getDefault());
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
/*     */   public DynamicTimeSeriesCollection(int nSeries, int nMoments, RegularTimePeriod timeSample, TimeZone zone)
/*     */   {
/* 277 */     this.maximumItemCount = nMoments;
/* 278 */     this.historyCount = nMoments;
/* 279 */     this.seriesKeys = new Comparable[nSeries];
/*     */     
/* 281 */     for (int i = 0; i < nSeries; i++) {
/* 282 */       this.seriesKeys[i] = "";
/*     */     }
/* 284 */     this.newestAt = (nMoments - 1);
/* 285 */     this.valueHistory = new ValueSequence[nSeries];
/* 286 */     this.timePeriodClass = timeSample.getClass();
/*     */     
/*     */ 
/* 289 */     if (this.timePeriodClass == Second.class) {
/* 290 */       this.pointsInTime = new Second[nMoments];
/*     */     }
/* 292 */     else if (this.timePeriodClass == Minute.class) {
/* 293 */       this.pointsInTime = new Minute[nMoments];
/*     */     }
/* 295 */     else if (this.timePeriodClass == Hour.class) {
/* 296 */       this.pointsInTime = new Hour[nMoments];
/*     */     }
/*     */     
/* 299 */     this.workingCalendar = Calendar.getInstance(zone);
/* 300 */     this.position = 0;
/* 301 */     this.domainIsPointsInTime = true;
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
/*     */   public synchronized long setTimeBase(RegularTimePeriod start)
/*     */   {
/* 317 */     if (this.pointsInTime[0] == null) {
/* 318 */       this.pointsInTime[0] = start;
/* 319 */       for (int i = 1; i < this.historyCount; i++) {
/* 320 */         this.pointsInTime[i] = this.pointsInTime[(i - 1)].next();
/*     */       }
/*     */     }
/* 323 */     long oldestL = this.pointsInTime[0].getFirstMillisecond(this.workingCalendar);
/*     */     
/*     */ 
/* 326 */     long nextL = this.pointsInTime[1].getFirstMillisecond(this.workingCalendar);
/*     */     
/*     */ 
/* 329 */     this.deltaTime = (nextL - oldestL);
/* 330 */     this.oldestAt = 0;
/* 331 */     this.newestAt = (this.historyCount - 1);
/* 332 */     findDomainLimits();
/* 333 */     return this.deltaTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void findDomainLimits()
/*     */   {
/* 343 */     long startL = getOldestTime().getFirstMillisecond(this.workingCalendar);
/*     */     long endL;
/* 345 */     long endL; if (this.domainIsPointsInTime) {
/* 346 */       endL = getNewestTime().getFirstMillisecond(this.workingCalendar);
/*     */     }
/*     */     else {
/* 349 */       endL = getNewestTime().getLastMillisecond(this.workingCalendar);
/*     */     }
/* 351 */     this.domainStart = new Long(startL);
/* 352 */     this.domainEnd = new Long(endL);
/* 353 */     this.domainRange = new Range(startL, endL);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getPosition()
/*     */   {
/* 363 */     return this.position;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPosition(int position)
/*     */   {
/* 372 */     this.position = position;
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
/*     */   public void addSeries(float[] values, int seriesNumber, Comparable seriesKey)
/*     */   {
/* 389 */     invalidateRangeInfo();
/*     */     
/* 391 */     if (values == null) {
/* 392 */       throw new IllegalArgumentException("TimeSeriesDataset.addSeries(): cannot add null array of values.");
/*     */     }
/*     */     
/* 395 */     if (seriesNumber >= this.valueHistory.length) {
/* 396 */       throw new IllegalArgumentException("TimeSeriesDataset.addSeries(): cannot add more series than specified in c'tor");
/*     */     }
/*     */     
/* 399 */     if (this.valueHistory[seriesNumber] == null) {
/* 400 */       this.valueHistory[seriesNumber] = new ValueSequence(this.historyCount);
/*     */       
/* 402 */       this.seriesCount += 1;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 407 */     int srcLength = values.length;
/* 408 */     int copyLength = this.historyCount;
/* 409 */     boolean fillNeeded = false;
/* 410 */     if (srcLength < this.historyCount) {
/* 411 */       fillNeeded = true;
/* 412 */       copyLength = srcLength;
/*     */     }
/*     */     
/* 415 */     for (int i = 0; i < copyLength; i++)
/*     */     {
/* 417 */       this.valueHistory[seriesNumber].enterData(i, values[i]);
/*     */     }
/* 419 */     if (fillNeeded) {
/* 420 */       for (i = copyLength; i < this.historyCount; i++) {
/* 421 */         this.valueHistory[seriesNumber].enterData(i, 0.0F);
/*     */       }
/*     */     }
/*     */     
/* 425 */     if (seriesKey != null) {
/* 426 */       this.seriesKeys[seriesNumber] = seriesKey;
/*     */     }
/* 428 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSeriesKey(int seriesNumber, Comparable key)
/*     */   {
/* 439 */     this.seriesKeys[seriesNumber] = key;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addValue(int seriesNumber, int index, float value)
/*     */   {
/* 451 */     invalidateRangeInfo();
/* 452 */     if (seriesNumber >= this.valueHistory.length) {
/* 453 */       throw new IllegalArgumentException("TimeSeriesDataset.addValue(): series #" + seriesNumber + "unspecified in c'tor");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 458 */     if (this.valueHistory[seriesNumber] == null) {
/* 459 */       this.valueHistory[seriesNumber] = new ValueSequence(this.historyCount);
/*     */       
/* 461 */       this.seriesCount += 1;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 466 */     this.valueHistory[seriesNumber].enterData(index, value);
/*     */     
/* 468 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesCount()
/*     */   {
/* 477 */     return this.seriesCount;
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
/*     */   public int getItemCount(int series)
/*     */   {
/* 491 */     return this.historyCount;
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
/*     */   protected int translateGet(int toFetch)
/*     */   {
/* 504 */     if (this.oldestAt == 0) {
/* 505 */       return toFetch;
/*     */     }
/*     */     
/* 508 */     int newIndex = toFetch + this.oldestAt;
/* 509 */     if (newIndex >= this.historyCount) {
/* 510 */       newIndex -= this.historyCount;
/*     */     }
/* 512 */     return newIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int offsetFromNewest(int delta)
/*     */   {
/* 523 */     return wrapOffset(this.newestAt + delta);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int offsetFromOldest(int delta)
/*     */   {
/* 534 */     return wrapOffset(this.oldestAt + delta);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int wrapOffset(int protoIndex)
/*     */   {
/* 545 */     int tmp = protoIndex;
/* 546 */     if (tmp >= this.historyCount) {
/* 547 */       tmp -= this.historyCount;
/*     */     }
/* 549 */     else if (tmp < 0) {
/* 550 */       tmp += this.historyCount;
/*     */     }
/* 552 */     return tmp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized RegularTimePeriod advanceTime()
/*     */   {
/* 563 */     RegularTimePeriod nextInstant = this.pointsInTime[this.newestAt].next();
/* 564 */     this.newestAt = this.oldestAt;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 571 */     boolean extremaChanged = false;
/* 572 */     float oldMax = 0.0F;
/* 573 */     if (this.maxValue != null) {
/* 574 */       oldMax = this.maxValue.floatValue();
/*     */     }
/* 576 */     for (int s = 0; s < getSeriesCount(); s++) {
/* 577 */       if (this.valueHistory[s].getData(this.oldestAt) == oldMax) {
/* 578 */         extremaChanged = true;
/*     */       }
/* 580 */       if (extremaChanged) {
/*     */         break;
/*     */       }
/*     */     }
/* 584 */     if (extremaChanged) {
/* 585 */       invalidateRangeInfo();
/*     */     }
/*     */     
/* 588 */     float wiper = 0.0F;
/* 589 */     for (int s = 0; s < getSeriesCount(); s++) {
/* 590 */       this.valueHistory[s].enterData(this.newestAt, wiper);
/*     */     }
/*     */     
/* 593 */     this.pointsInTime[this.newestAt] = nextInstant;
/*     */     
/* 595 */     this.oldestAt += 1;
/* 596 */     if (this.oldestAt >= this.historyCount) {
/* 597 */       this.oldestAt = 0;
/*     */     }
/*     */     
/* 600 */     long startL = this.domainStart.longValue();
/* 601 */     this.domainStart = new Long(startL + this.deltaTime);
/* 602 */     long endL = this.domainEnd.longValue();
/* 603 */     this.domainEnd = new Long(endL + this.deltaTime);
/* 604 */     this.domainRange = new Range(startL, endL);
/* 605 */     fireSeriesChanged();
/* 606 */     return nextInstant;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void invalidateRangeInfo()
/*     */   {
/* 615 */     this.maxValue = null;
/* 616 */     this.valueRange = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double findMaxValue()
/*     */   {
/* 625 */     double max = 0.0D;
/* 626 */     for (int s = 0; s < getSeriesCount(); s++) {
/* 627 */       for (int i = 0; i < this.historyCount; i++) {
/* 628 */         double tmp = getYValue(s, i);
/* 629 */         if (tmp > max) {
/* 630 */           max = tmp;
/*     */         }
/*     */       }
/*     */     }
/* 634 */     return max;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getOldestIndex()
/*     */   {
/* 645 */     return this.oldestAt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getNewestIndex()
/*     */   {
/* 654 */     return this.newestAt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void appendData(float[] newData)
/*     */   {
/* 665 */     int nDataPoints = newData.length;
/* 666 */     if (nDataPoints > this.valueHistory.length) {
/* 667 */       throw new IllegalArgumentException("More data than series to put them in");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 672 */     for (int s = 0; s < nDataPoints; s++)
/*     */     {
/*     */ 
/* 675 */       if (this.valueHistory[s] == null) {
/* 676 */         this.valueHistory[s] = new ValueSequence(this.historyCount);
/*     */       }
/* 678 */       this.valueHistory[s].enterData(this.newestAt, newData[s]);
/*     */     }
/* 680 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void appendData(float[] newData, int insertionIndex, int refresh)
/*     */   {
/* 692 */     int nDataPoints = newData.length;
/* 693 */     if (nDataPoints > this.valueHistory.length) {
/* 694 */       throw new IllegalArgumentException("More data than series to put them in");
/*     */     }
/*     */     
/*     */ 
/* 698 */     for (int s = 0; s < nDataPoints; s++) {
/* 699 */       if (this.valueHistory[s] == null) {
/* 700 */         this.valueHistory[s] = new ValueSequence(this.historyCount);
/*     */       }
/* 702 */       this.valueHistory[s].enterData(insertionIndex, newData[s]);
/*     */     }
/* 704 */     if (refresh > 0) {
/* 705 */       insertionIndex++;
/* 706 */       if (insertionIndex % refresh == 0) {
/* 707 */         fireSeriesChanged();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RegularTimePeriod getNewestTime()
/*     */   {
/* 718 */     return this.pointsInTime[this.newestAt];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RegularTimePeriod getOldestTime()
/*     */   {
/* 727 */     return this.pointsInTime[this.oldestAt];
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
/* 741 */     RegularTimePeriod tp = this.pointsInTime[translateGet(item)];
/* 742 */     return new Long(getX(tp));
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
/*     */   public double getYValue(int series, int item)
/*     */   {
/* 756 */     ValueSequence values = this.valueHistory[series];
/* 757 */     return values.getData(translateGet(item));
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
/* 769 */     return new Float(getYValue(series, item));
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
/* 781 */     RegularTimePeriod tp = this.pointsInTime[translateGet(item)];
/* 782 */     return new Long(tp.getFirstMillisecond(this.workingCalendar));
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
/* 794 */     RegularTimePeriod tp = this.pointsInTime[translateGet(item)];
/* 795 */     return new Long(tp.getLastMillisecond(this.workingCalendar));
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
/* 807 */     return getY(series, item);
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
/* 819 */     return getY(series, item);
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
/*     */ 
/*     */   public Comparable getSeriesKey(int series)
/*     */   {
/* 841 */     return this.seriesKeys[series];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void fireSeriesChanged()
/*     */   {
/* 848 */     seriesChanged(new SeriesChangeEvent(this));
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
/*     */   public double getDomainLowerBound(boolean includeInterval)
/*     */   {
/* 865 */     return this.domainStart.doubleValue();
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
/* 878 */     return this.domainEnd.doubleValue();
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
/*     */   public Range getDomainBounds(boolean includeInterval)
/*     */   {
/* 891 */     if (this.domainRange == null) {
/* 892 */       findDomainLimits();
/*     */     }
/* 894 */     return this.domainRange;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private long getX(RegularTimePeriod period)
/*     */   {
/* 905 */     switch (this.position) {
/*     */     case 0: 
/* 907 */       return period.getFirstMillisecond(this.workingCalendar);
/*     */     case 1: 
/* 909 */       return period.getMiddleMillisecond(this.workingCalendar);
/*     */     case 2: 
/* 911 */       return period.getLastMillisecond(this.workingCalendar);
/*     */     }
/* 913 */     return period.getMiddleMillisecond(this.workingCalendar);
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
/*     */   public double getRangeLowerBound(boolean includeInterval)
/*     */   {
/* 932 */     double result = NaN.0D;
/* 933 */     if (this.minValue != null) {
/* 934 */       result = this.minValue.doubleValue();
/*     */     }
/* 936 */     return result;
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
/* 948 */     double result = NaN.0D;
/* 949 */     if (this.maxValue != null) {
/* 950 */       result = this.maxValue.doubleValue();
/*     */     }
/* 952 */     return result;
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
/* 964 */     if (this.valueRange == null) {
/* 965 */       double max = getRangeUpperBound(includeInterval);
/* 966 */       this.valueRange = new Range(0.0D, max);
/*     */     }
/* 968 */     return this.valueRange;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/DynamicTimeSeriesCollection.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */