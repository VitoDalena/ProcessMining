/*      */ package org.jfree.chart.axis;
/*      */ 
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.font.FontRenderContext;
/*      */ import java.awt.font.LineMetrics;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.Serializable;
/*      */ import java.text.DateFormat;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.TimeZone;
/*      */ import org.jfree.chart.event.AxisChangeEvent;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.chart.plot.ValueAxisPlot;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.time.DateRange;
/*      */ import org.jfree.data.time.Month;
/*      */ import org.jfree.data.time.RegularTimePeriod;
/*      */ import org.jfree.data.time.Year;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.ui.TextAnchor;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class DateAxis
/*      */   extends ValueAxis
/*      */   implements Cloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = -1013460999649007604L;
/*  181 */   public static final DateRange DEFAULT_DATE_RANGE = new DateRange();
/*      */   
/*      */ 
/*      */ 
/*      */   public static final double DEFAULT_AUTO_RANGE_MINIMUM_SIZE_IN_MILLISECONDS = 2.0D;
/*      */   
/*      */ 
/*  188 */   public static final DateTickUnit DEFAULT_DATE_TICK_UNIT = new DateTickUnit(DateTickUnitType.DAY, 1, new SimpleDateFormat());
/*      */   
/*      */ 
/*      */ 
/*  192 */   public static final Date DEFAULT_ANCHOR_DATE = new Date();
/*      */   
/*      */ 
/*      */ 
/*      */   private DateTickUnit tickUnit;
/*      */   
/*      */ 
/*      */ 
/*      */   private DateFormat dateFormatOverride;
/*      */   
/*      */ 
/*      */ 
/*  204 */   private DateTickMarkPosition tickMarkPosition = DateTickMarkPosition.START;
/*      */   
/*      */   private static class DefaultTimeline implements Timeline, Serializable
/*      */   {
/*      */     DefaultTimeline(DateAxis.1 x0)
/*      */     {
/*  210 */       this();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public long toTimelineValue(long millisecond)
/*      */     {
/*  220 */       return millisecond;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public long toTimelineValue(Date date)
/*      */     {
/*  231 */       return date.getTime();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public long toMillisecond(long value)
/*      */     {
/*  243 */       return value;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public boolean containsDomainValue(long millisecond)
/*      */     {
/*  255 */       return true;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public boolean containsDomainValue(Date date)
/*      */     {
/*  267 */       return true;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public boolean containsDomainRange(long from, long to)
/*      */     {
/*  280 */       return true;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public boolean containsDomainRange(Date from, Date to)
/*      */     {
/*  293 */       return true;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public boolean equals(Object object)
/*      */     {
/*  304 */       if (object == null) {
/*  305 */         return false;
/*      */       }
/*  307 */       if (object == this) {
/*  308 */         return true;
/*      */       }
/*  310 */       if ((object instanceof DefaultTimeline)) {
/*  311 */         return true;
/*      */       }
/*  313 */       return false;
/*      */     }
/*      */     
/*      */     private DefaultTimeline() {} }
/*      */   
/*  318 */   private static final Timeline DEFAULT_TIMELINE = new DefaultTimeline(null);
/*      */   
/*      */ 
/*      */ 
/*      */   private TimeZone timeZone;
/*      */   
/*      */ 
/*      */ 
/*      */   private Locale locale;
/*      */   
/*      */ 
/*      */ 
/*      */   private Timeline timeline;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateAxis()
/*      */   {
/*  337 */     this(null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateAxis(String label)
/*      */   {
/*  346 */     this(label, TimeZone.getDefault());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public DateAxis(String label, TimeZone zone)
/*      */   {
/*  363 */     this(label, zone, Locale.getDefault());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateAxis(String label, TimeZone zone, Locale locale)
/*      */   {
/*  380 */     super(label, createStandardDateTickUnits(zone, locale));
/*  381 */     setTickUnit(DEFAULT_DATE_TICK_UNIT, false, false);
/*  382 */     setAutoRangeMinimumSize(2.0D);
/*      */     
/*  384 */     setRange(DEFAULT_DATE_RANGE, false, false);
/*  385 */     this.dateFormatOverride = null;
/*  386 */     this.timeZone = zone;
/*  387 */     this.locale = locale;
/*  388 */     this.timeline = DEFAULT_TIMELINE;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TimeZone getTimeZone()
/*      */   {
/*  401 */     return this.timeZone;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTimeZone(TimeZone zone)
/*      */   {
/*  415 */     if (zone == null) {
/*  416 */       throw new IllegalArgumentException("Null 'zone' argument.");
/*      */     }
/*  418 */     if (!this.timeZone.equals(zone)) {
/*  419 */       this.timeZone = zone;
/*  420 */       setStandardTickUnits(createStandardDateTickUnits(zone, this.locale));
/*      */       
/*  422 */       notifyListeners(new AxisChangeEvent(this));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Timeline getTimeline()
/*      */   {
/*  432 */     return this.timeline;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTimeline(Timeline timeline)
/*      */   {
/*  444 */     if (this.timeline != timeline) {
/*  445 */       this.timeline = timeline;
/*  446 */       notifyListeners(new AxisChangeEvent(this));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTickUnit getTickUnit()
/*      */   {
/*  464 */     return this.tickUnit;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTickUnit(DateTickUnit unit)
/*      */   {
/*  478 */     setTickUnit(unit, true, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTickUnit(DateTickUnit unit, boolean notify, boolean turnOffAutoSelection)
/*      */   {
/*  493 */     this.tickUnit = unit;
/*  494 */     if (turnOffAutoSelection) {
/*  495 */       setAutoTickUnitSelection(false, false);
/*      */     }
/*  497 */     if (notify) {
/*  498 */       notifyListeners(new AxisChangeEvent(this));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateFormat getDateFormatOverride()
/*      */   {
/*  510 */     return this.dateFormatOverride;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDateFormatOverride(DateFormat formatter)
/*      */   {
/*  520 */     this.dateFormatOverride = formatter;
/*  521 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRange(Range range)
/*      */   {
/*  532 */     setRange(range, true, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRange(Range range, boolean turnOffAutoRange, boolean notify)
/*      */   {
/*  548 */     if (range == null) {
/*  549 */       throw new IllegalArgumentException("Null 'range' argument.");
/*      */     }
/*      */     
/*      */ 
/*  553 */     if (!(range instanceof DateRange)) {
/*  554 */       range = new DateRange(range);
/*      */     }
/*  556 */     super.setRange(range, turnOffAutoRange, notify);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRange(Date lower, Date upper)
/*      */   {
/*  567 */     if (lower.getTime() >= upper.getTime()) {
/*  568 */       throw new IllegalArgumentException("Requires 'lower' < 'upper'.");
/*      */     }
/*  570 */     setRange(new DateRange(lower, upper));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRange(double lower, double upper)
/*      */   {
/*  581 */     if (lower >= upper) {
/*  582 */       throw new IllegalArgumentException("Requires 'lower' < 'upper'.");
/*      */     }
/*  584 */     setRange(new DateRange(lower, upper));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Date getMinimumDate()
/*      */   {
/*  596 */     Date result = null;
/*  597 */     Range range = getRange();
/*  598 */     if ((range instanceof DateRange)) {
/*  599 */       DateRange r = (DateRange)range;
/*  600 */       result = r.getLowerDate();
/*      */     }
/*      */     else {
/*  603 */       result = new Date(range.getLowerBound());
/*      */     }
/*  605 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMinimumDate(Date date)
/*      */   {
/*  621 */     if (date == null) {
/*  622 */       throw new IllegalArgumentException("Null 'date' argument.");
/*      */     }
/*      */     
/*  625 */     Date maxDate = getMaximumDate();
/*  626 */     long maxMillis = maxDate.getTime();
/*  627 */     long newMinMillis = date.getTime();
/*  628 */     if (maxMillis <= newMinMillis) {
/*  629 */       Date oldMin = getMinimumDate();
/*  630 */       long length = maxMillis - oldMin.getTime();
/*  631 */       maxDate = new Date(newMinMillis + length);
/*      */     }
/*  633 */     setRange(new DateRange(date, maxDate), true, false);
/*  634 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Date getMaximumDate()
/*      */   {
/*  646 */     Date result = null;
/*  647 */     Range range = getRange();
/*  648 */     if ((range instanceof DateRange)) {
/*  649 */       DateRange r = (DateRange)range;
/*  650 */       result = r.getUpperDate();
/*      */     }
/*      */     else {
/*  653 */       result = new Date(range.getUpperBound());
/*      */     }
/*  655 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMaximumDate(Date maximumDate)
/*      */   {
/*  671 */     if (maximumDate == null) {
/*  672 */       throw new IllegalArgumentException("Null 'maximumDate' argument.");
/*      */     }
/*      */     
/*  675 */     Date minDate = getMinimumDate();
/*  676 */     long minMillis = minDate.getTime();
/*  677 */     long newMaxMillis = maximumDate.getTime();
/*  678 */     if (minMillis >= newMaxMillis) {
/*  679 */       Date oldMax = getMaximumDate();
/*  680 */       long length = oldMax.getTime() - minMillis;
/*  681 */       minDate = new Date(newMaxMillis - length);
/*      */     }
/*  683 */     setRange(new DateRange(minDate, maximumDate), true, false);
/*  684 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTickMarkPosition getTickMarkPosition()
/*      */   {
/*  693 */     return this.tickMarkPosition;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTickMarkPosition(DateTickMarkPosition position)
/*      */   {
/*  703 */     if (position == null) {
/*  704 */       throw new IllegalArgumentException("Null 'position' argument.");
/*      */     }
/*  706 */     this.tickMarkPosition = position;
/*  707 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void configure()
/*      */   {
/*  715 */     if (isAutoRange()) {
/*  716 */       autoAdjustRange();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isHiddenValue(long millis)
/*      */   {
/*  729 */     return !this.timeline.containsDomainValue(new Date(millis));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double valueToJava2D(double value, Rectangle2D area, RectangleEdge edge)
/*      */   {
/*  746 */     value = this.timeline.toTimelineValue(value);
/*      */     
/*  748 */     DateRange range = (DateRange)getRange();
/*  749 */     double axisMin = this.timeline.toTimelineValue(range.getLowerMillis());
/*  750 */     double axisMax = this.timeline.toTimelineValue(range.getUpperMillis());
/*  751 */     double result = 0.0D;
/*  752 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  753 */       double minX = area.getX();
/*  754 */       double maxX = area.getMaxX();
/*  755 */       if (isInverted()) {
/*  756 */         result = maxX + (value - axisMin) / (axisMax - axisMin) * (minX - maxX);
/*      */       }
/*      */       else
/*      */       {
/*  760 */         result = minX + (value - axisMin) / (axisMax - axisMin) * (maxX - minX);
/*      */       }
/*      */       
/*      */     }
/*  764 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/*  765 */       double minY = area.getMinY();
/*  766 */       double maxY = area.getMaxY();
/*  767 */       if (isInverted()) {
/*  768 */         result = minY + (value - axisMin) / (axisMax - axisMin) * (maxY - minY);
/*      */       }
/*      */       else
/*      */       {
/*  772 */         result = maxY - (value - axisMin) / (axisMax - axisMin) * (maxY - minY);
/*      */       }
/*      */     }
/*      */     
/*  776 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double dateToJava2D(Date date, Rectangle2D area, RectangleEdge edge)
/*      */   {
/*  793 */     double value = date.getTime();
/*  794 */     return valueToJava2D(value, area, edge);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double java2DToValue(double java2DValue, Rectangle2D area, RectangleEdge edge)
/*      */   {
/*  812 */     DateRange range = (DateRange)getRange();
/*  813 */     double axisMin = this.timeline.toTimelineValue(range.getLowerMillis());
/*  814 */     double axisMax = this.timeline.toTimelineValue(range.getUpperMillis());
/*      */     
/*  816 */     double min = 0.0D;
/*  817 */     double max = 0.0D;
/*  818 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  819 */       min = area.getX();
/*  820 */       max = area.getMaxX();
/*      */     }
/*  822 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/*  823 */       min = area.getMaxY();
/*  824 */       max = area.getY();
/*      */     }
/*      */     double result;
/*      */     double result;
/*  828 */     if (isInverted()) {
/*  829 */       result = axisMax - (java2DValue - min) / (max - min) * (axisMax - axisMin);
/*      */     }
/*      */     else
/*      */     {
/*  833 */       result = axisMin + (java2DValue - min) / (max - min) * (axisMax - axisMin);
/*      */     }
/*      */     
/*      */ 
/*  837 */     return this.timeline.toMillisecond(result);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Date calculateLowestVisibleTickValue(DateTickUnit unit)
/*      */   {
/*  848 */     return nextStandardDate(getMinimumDate(), unit);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Date calculateHighestVisibleTickValue(DateTickUnit unit)
/*      */   {
/*  859 */     return previousStandardDate(getMaximumDate(), unit);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Date previousStandardDate(Date date, DateTickUnit unit)
/*      */   {
/*  880 */     Calendar calendar = Calendar.getInstance(this.timeZone, this.locale);
/*  881 */     calendar.setTime(date);
/*  882 */     int count = unit.getCount();
/*  883 */     int current = calendar.get(unit.getCalendarField());
/*  884 */     int value = count * (current / count);
/*      */     int years;
/*  886 */     int months; int days; int hours; int minutes; int seconds; int seconds; int minutes; int seconds; switch (unit.getUnit())
/*      */     {
/*      */     case 6: 
/*  889 */       years = calendar.get(1);
/*  890 */       months = calendar.get(2);
/*  891 */       days = calendar.get(5);
/*  892 */       hours = calendar.get(11);
/*  893 */       minutes = calendar.get(12);
/*  894 */       seconds = calendar.get(13);
/*  895 */       calendar.set(years, months, days, hours, minutes, seconds);
/*  896 */       calendar.set(14, value);
/*  897 */       Date mm = calendar.getTime();
/*  898 */       if (mm.getTime() >= date.getTime()) {
/*  899 */         calendar.set(14, value - 1);
/*  900 */         mm = calendar.getTime();
/*      */       }
/*  902 */       return mm;
/*      */     
/*      */     case 5: 
/*  905 */       years = calendar.get(1);
/*  906 */       months = calendar.get(2);
/*  907 */       days = calendar.get(5);
/*  908 */       hours = calendar.get(11);
/*  909 */       minutes = calendar.get(12);
/*  910 */       int milliseconds; int milliseconds; if (this.tickMarkPosition == DateTickMarkPosition.START) {
/*  911 */         milliseconds = 0;
/*      */       } else { int milliseconds;
/*  913 */         if (this.tickMarkPosition == DateTickMarkPosition.MIDDLE) {
/*  914 */           milliseconds = 500;
/*      */         }
/*      */         else
/*  917 */           milliseconds = 999;
/*      */       }
/*  919 */       calendar.set(14, milliseconds);
/*  920 */       calendar.set(years, months, days, hours, minutes, value);
/*  921 */       Date dd = calendar.getTime();
/*  922 */       if (dd.getTime() >= date.getTime()) {
/*  923 */         calendar.set(13, value - 1);
/*  924 */         dd = calendar.getTime();
/*      */       }
/*  926 */       return dd;
/*      */     
/*      */     case 4: 
/*  929 */       years = calendar.get(1);
/*  930 */       months = calendar.get(2);
/*  931 */       days = calendar.get(5);
/*  932 */       hours = calendar.get(11);
/*  933 */       if (this.tickMarkPosition == DateTickMarkPosition.START) {
/*  934 */         seconds = 0;
/*      */       } else { int seconds;
/*  936 */         if (this.tickMarkPosition == DateTickMarkPosition.MIDDLE) {
/*  937 */           seconds = 30;
/*      */         }
/*      */         else
/*  940 */           seconds = 59;
/*      */       }
/*  942 */       calendar.clear(14);
/*  943 */       calendar.set(years, months, days, hours, value, seconds);
/*  944 */       Date d0 = calendar.getTime();
/*  945 */       if (d0.getTime() >= date.getTime()) {
/*  946 */         calendar.set(12, value - 1);
/*  947 */         d0 = calendar.getTime();
/*      */       }
/*  949 */       return d0;
/*      */     
/*      */     case 3: 
/*  952 */       years = calendar.get(1);
/*  953 */       months = calendar.get(2);
/*  954 */       days = calendar.get(5);
/*  955 */       if (this.tickMarkPosition == DateTickMarkPosition.START) {
/*  956 */         minutes = 0;
/*  957 */         seconds = 0;
/*      */       } else { int seconds;
/*  959 */         if (this.tickMarkPosition == DateTickMarkPosition.MIDDLE) {
/*  960 */           int minutes = 30;
/*  961 */           seconds = 0;
/*      */         }
/*      */         else {
/*  964 */           minutes = 59;
/*  965 */           seconds = 59;
/*      */         } }
/*  967 */       calendar.clear(14);
/*  968 */       calendar.set(years, months, days, value, minutes, seconds);
/*  969 */       Date d1 = calendar.getTime();
/*  970 */       if (d1.getTime() >= date.getTime()) {
/*  971 */         calendar.set(11, value - 1);
/*  972 */         d1 = calendar.getTime();
/*      */       }
/*  974 */       return d1;
/*      */     
/*      */     case 2: 
/*  977 */       years = calendar.get(1);
/*  978 */       months = calendar.get(2);
/*  979 */       int hours; int seconds; if (this.tickMarkPosition == DateTickMarkPosition.START) {
/*  980 */         hours = 0;
/*  981 */         minutes = 0;
/*  982 */         seconds = 0;
/*      */       } else { int seconds;
/*  984 */         if (this.tickMarkPosition == DateTickMarkPosition.MIDDLE) {
/*  985 */           int hours = 12;
/*  986 */           int minutes = 0;
/*  987 */           seconds = 0;
/*      */         }
/*      */         else {
/*  990 */           hours = 23;
/*  991 */           int minutes = 59;
/*  992 */           seconds = 59;
/*      */         } }
/*  994 */       calendar.clear(14);
/*  995 */       calendar.set(years, months, value, hours, 0, 0);
/*      */       
/*      */ 
/*  998 */       Date d2 = calendar.getTime();
/*  999 */       if (d2.getTime() >= date.getTime()) {
/* 1000 */         calendar.set(5, value - 1);
/* 1001 */         d2 = calendar.getTime();
/*      */       }
/* 1003 */       return d2;
/*      */     
/*      */     case 1: 
/* 1006 */       years = calendar.get(1);
/* 1007 */       calendar.clear(14);
/* 1008 */       calendar.set(years, value, 1, 0, 0, 0);
/* 1009 */       Month month = new Month(calendar.getTime(), this.timeZone, this.locale);
/*      */       
/* 1011 */       Date standardDate = calculateDateForPosition(month, this.tickMarkPosition);
/*      */       
/* 1013 */       long millis = standardDate.getTime();
/* 1014 */       if (millis >= date.getTime()) {
/* 1015 */         month = (Month)month.previous();
/*      */         
/*      */ 
/* 1018 */         month.peg(Calendar.getInstance(this.timeZone));
/* 1019 */         standardDate = calculateDateForPosition(month, this.tickMarkPosition);
/*      */       }
/*      */       
/* 1022 */       return standardDate;
/*      */     case 0:  int months;
/*      */       int days;
/* 1025 */       if (this.tickMarkPosition == DateTickMarkPosition.START) {
/* 1026 */         months = 0;
/* 1027 */         days = 1;
/*      */       } else { int days;
/* 1029 */         if (this.tickMarkPosition == DateTickMarkPosition.MIDDLE) {
/* 1030 */           int months = 6;
/* 1031 */           days = 1;
/*      */         }
/*      */         else {
/* 1034 */           months = 11;
/* 1035 */           days = 31;
/*      */         } }
/* 1037 */       calendar.clear(14);
/* 1038 */       calendar.set(value, months, days, 0, 0, 0);
/* 1039 */       Date d3 = calendar.getTime();
/* 1040 */       if (d3.getTime() >= date.getTime()) {
/* 1041 */         calendar.set(1, value - 1);
/* 1042 */         d3 = calendar.getTime();
/*      */       }
/* 1044 */       return d3;
/*      */     }
/* 1046 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Date calculateDateForPosition(RegularTimePeriod period, DateTickMarkPosition position)
/*      */   {
/* 1064 */     if (position == null) {
/* 1065 */       throw new IllegalArgumentException("Null 'position' argument.");
/*      */     }
/* 1067 */     Date result = null;
/* 1068 */     if (position == DateTickMarkPosition.START) {
/* 1069 */       result = new Date(period.getFirstMillisecond());
/*      */     }
/* 1071 */     else if (position == DateTickMarkPosition.MIDDLE) {
/* 1072 */       result = new Date(period.getMiddleMillisecond());
/*      */     }
/* 1074 */     else if (position == DateTickMarkPosition.END) {
/* 1075 */       result = new Date(period.getLastMillisecond());
/*      */     }
/* 1077 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Date nextStandardDate(Date date, DateTickUnit unit)
/*      */   {
/* 1091 */     Date previous = previousStandardDate(date, unit);
/* 1092 */     Calendar calendar = Calendar.getInstance(this.timeZone, this.locale);
/* 1093 */     calendar.setTime(previous);
/* 1094 */     calendar.add(unit.getCalendarField(), unit.getMultiple());
/* 1095 */     return calendar.getTime();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static TickUnitSource createStandardDateTickUnits()
/*      */   {
/* 1108 */     return createStandardDateTickUnits(TimeZone.getDefault(), Locale.getDefault());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public static TickUnitSource createStandardDateTickUnits(TimeZone zone)
/*      */   {
/* 1128 */     return createStandardDateTickUnits(zone, Locale.getDefault());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static TickUnitSource createStandardDateTickUnits(TimeZone zone, Locale locale)
/*      */   {
/* 1148 */     if (zone == null) {
/* 1149 */       throw new IllegalArgumentException("Null 'zone' argument.");
/*      */     }
/* 1151 */     if (locale == null) {
/* 1152 */       throw new IllegalArgumentException("Null 'locale' argument.");
/*      */     }
/* 1154 */     TickUnits units = new TickUnits();
/*      */     
/*      */ 
/* 1157 */     DateFormat f1 = new SimpleDateFormat("HH:mm:ss.SSS", locale);
/* 1158 */     DateFormat f2 = new SimpleDateFormat("HH:mm:ss", locale);
/* 1159 */     DateFormat f3 = new SimpleDateFormat("HH:mm", locale);
/* 1160 */     DateFormat f4 = new SimpleDateFormat("d-MMM, HH:mm", locale);
/* 1161 */     DateFormat f5 = new SimpleDateFormat("d-MMM", locale);
/* 1162 */     DateFormat f6 = new SimpleDateFormat("MMM-yyyy", locale);
/* 1163 */     DateFormat f7 = new SimpleDateFormat("yyyy", locale);
/*      */     
/* 1165 */     f1.setTimeZone(zone);
/* 1166 */     f2.setTimeZone(zone);
/* 1167 */     f3.setTimeZone(zone);
/* 1168 */     f4.setTimeZone(zone);
/* 1169 */     f5.setTimeZone(zone);
/* 1170 */     f6.setTimeZone(zone);
/* 1171 */     f7.setTimeZone(zone);
/*      */     
/*      */ 
/* 1174 */     units.add(new DateTickUnit(DateTickUnitType.MILLISECOND, 1, f1));
/* 1175 */     units.add(new DateTickUnit(DateTickUnitType.MILLISECOND, 5, DateTickUnitType.MILLISECOND, 1, f1));
/*      */     
/* 1177 */     units.add(new DateTickUnit(DateTickUnitType.MILLISECOND, 10, DateTickUnitType.MILLISECOND, 1, f1));
/*      */     
/* 1179 */     units.add(new DateTickUnit(DateTickUnitType.MILLISECOND, 25, DateTickUnitType.MILLISECOND, 5, f1));
/*      */     
/* 1181 */     units.add(new DateTickUnit(DateTickUnitType.MILLISECOND, 50, DateTickUnitType.MILLISECOND, 10, f1));
/*      */     
/* 1183 */     units.add(new DateTickUnit(DateTickUnitType.MILLISECOND, 100, DateTickUnitType.MILLISECOND, 10, f1));
/*      */     
/* 1185 */     units.add(new DateTickUnit(DateTickUnitType.MILLISECOND, 250, DateTickUnitType.MILLISECOND, 10, f1));
/*      */     
/* 1187 */     units.add(new DateTickUnit(DateTickUnitType.MILLISECOND, 500, DateTickUnitType.MILLISECOND, 50, f1));
/*      */     
/*      */ 
/*      */ 
/* 1191 */     units.add(new DateTickUnit(DateTickUnitType.SECOND, 1, DateTickUnitType.MILLISECOND, 50, f2));
/*      */     
/* 1193 */     units.add(new DateTickUnit(DateTickUnitType.SECOND, 5, DateTickUnitType.SECOND, 1, f2));
/*      */     
/* 1195 */     units.add(new DateTickUnit(DateTickUnitType.SECOND, 10, DateTickUnitType.SECOND, 1, f2));
/*      */     
/* 1197 */     units.add(new DateTickUnit(DateTickUnitType.SECOND, 30, DateTickUnitType.SECOND, 5, f2));
/*      */     
/*      */ 
/*      */ 
/* 1201 */     units.add(new DateTickUnit(DateTickUnitType.MINUTE, 1, DateTickUnitType.SECOND, 5, f3));
/*      */     
/* 1203 */     units.add(new DateTickUnit(DateTickUnitType.MINUTE, 2, DateTickUnitType.SECOND, 10, f3));
/*      */     
/* 1205 */     units.add(new DateTickUnit(DateTickUnitType.MINUTE, 5, DateTickUnitType.MINUTE, 1, f3));
/*      */     
/* 1207 */     units.add(new DateTickUnit(DateTickUnitType.MINUTE, 10, DateTickUnitType.MINUTE, 1, f3));
/*      */     
/* 1209 */     units.add(new DateTickUnit(DateTickUnitType.MINUTE, 15, DateTickUnitType.MINUTE, 5, f3));
/*      */     
/* 1211 */     units.add(new DateTickUnit(DateTickUnitType.MINUTE, 20, DateTickUnitType.MINUTE, 5, f3));
/*      */     
/* 1213 */     units.add(new DateTickUnit(DateTickUnitType.MINUTE, 30, DateTickUnitType.MINUTE, 5, f3));
/*      */     
/*      */ 
/*      */ 
/* 1217 */     units.add(new DateTickUnit(DateTickUnitType.HOUR, 1, DateTickUnitType.MINUTE, 5, f3));
/*      */     
/* 1219 */     units.add(new DateTickUnit(DateTickUnitType.HOUR, 2, DateTickUnitType.MINUTE, 10, f3));
/*      */     
/* 1221 */     units.add(new DateTickUnit(DateTickUnitType.HOUR, 4, DateTickUnitType.MINUTE, 30, f3));
/*      */     
/* 1223 */     units.add(new DateTickUnit(DateTickUnitType.HOUR, 6, DateTickUnitType.HOUR, 1, f3));
/*      */     
/* 1225 */     units.add(new DateTickUnit(DateTickUnitType.HOUR, 12, DateTickUnitType.HOUR, 1, f4));
/*      */     
/*      */ 
/*      */ 
/* 1229 */     units.add(new DateTickUnit(DateTickUnitType.DAY, 1, DateTickUnitType.HOUR, 1, f5));
/*      */     
/* 1231 */     units.add(new DateTickUnit(DateTickUnitType.DAY, 2, DateTickUnitType.HOUR, 1, f5));
/*      */     
/* 1233 */     units.add(new DateTickUnit(DateTickUnitType.DAY, 7, DateTickUnitType.DAY, 1, f5));
/*      */     
/* 1235 */     units.add(new DateTickUnit(DateTickUnitType.DAY, 15, DateTickUnitType.DAY, 1, f5));
/*      */     
/*      */ 
/*      */ 
/* 1239 */     units.add(new DateTickUnit(DateTickUnitType.MONTH, 1, DateTickUnitType.DAY, 1, f6));
/*      */     
/* 1241 */     units.add(new DateTickUnit(DateTickUnitType.MONTH, 2, DateTickUnitType.DAY, 1, f6));
/*      */     
/* 1243 */     units.add(new DateTickUnit(DateTickUnitType.MONTH, 3, DateTickUnitType.MONTH, 1, f6));
/*      */     
/* 1245 */     units.add(new DateTickUnit(DateTickUnitType.MONTH, 4, DateTickUnitType.MONTH, 1, f6));
/*      */     
/* 1247 */     units.add(new DateTickUnit(DateTickUnitType.MONTH, 6, DateTickUnitType.MONTH, 1, f6));
/*      */     
/*      */ 
/*      */ 
/* 1251 */     units.add(new DateTickUnit(DateTickUnitType.YEAR, 1, DateTickUnitType.MONTH, 1, f7));
/*      */     
/* 1253 */     units.add(new DateTickUnit(DateTickUnitType.YEAR, 2, DateTickUnitType.MONTH, 3, f7));
/*      */     
/* 1255 */     units.add(new DateTickUnit(DateTickUnitType.YEAR, 5, DateTickUnitType.YEAR, 1, f7));
/*      */     
/* 1257 */     units.add(new DateTickUnit(DateTickUnitType.YEAR, 10, DateTickUnitType.YEAR, 1, f7));
/*      */     
/* 1259 */     units.add(new DateTickUnit(DateTickUnitType.YEAR, 25, DateTickUnitType.YEAR, 5, f7));
/*      */     
/* 1261 */     units.add(new DateTickUnit(DateTickUnitType.YEAR, 50, DateTickUnitType.YEAR, 10, f7));
/*      */     
/* 1263 */     units.add(new DateTickUnit(DateTickUnitType.YEAR, 100, DateTickUnitType.YEAR, 20, f7));
/*      */     
/*      */ 
/* 1266 */     return units;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void autoAdjustRange()
/*      */   {
/* 1275 */     Plot plot = getPlot();
/*      */     
/* 1277 */     if (plot == null) {
/* 1278 */       return;
/*      */     }
/*      */     
/* 1281 */     if ((plot instanceof ValueAxisPlot)) {
/* 1282 */       ValueAxisPlot vap = (ValueAxisPlot)plot;
/*      */       
/* 1284 */       Range r = vap.getDataRange(this);
/* 1285 */       if (r == null) {
/* 1286 */         if ((this.timeline instanceof SegmentedTimeline))
/*      */         {
/* 1288 */           r = new DateRange(((SegmentedTimeline)this.timeline).getStartTime(), ((SegmentedTimeline)this.timeline).getStartTime() + 1L);
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/* 1294 */           r = new DateRange();
/*      */         }
/*      */       }
/*      */       
/* 1298 */       long upper = this.timeline.toTimelineValue(r.getUpperBound());
/*      */       
/*      */ 
/* 1301 */       long fixedAutoRange = getFixedAutoRange();
/* 1302 */       long lower; if (fixedAutoRange > 0.0D) {
/* 1303 */         lower = upper - fixedAutoRange;
/*      */       }
/*      */       else {
/* 1306 */         lower = this.timeline.toTimelineValue(r.getLowerBound());
/* 1307 */         double range = upper - lower;
/* 1308 */         long minRange = getAutoRangeMinimumSize();
/* 1309 */         if (range < minRange) {
/* 1310 */           long expand = (minRange - range) / 2L;
/* 1311 */           upper += expand;
/* 1312 */           lower -= expand;
/*      */         }
/* 1314 */         upper += (range * getUpperMargin());
/* 1315 */         lower -= (range * getLowerMargin());
/*      */       }
/*      */       
/* 1318 */       upper = this.timeline.toMillisecond(upper);
/* 1319 */       long lower = this.timeline.toMillisecond(lower);
/* 1320 */       DateRange dr = new DateRange(new Date(lower), new Date(upper));
/* 1321 */       setRange(dr, false, false);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void selectAutoTickUnit(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/* 1339 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 1340 */       selectHorizontalAutoTickUnit(g2, dataArea, edge);
/*      */     }
/* 1342 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/* 1343 */       selectVerticalAutoTickUnit(g2, dataArea, edge);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void selectHorizontalAutoTickUnit(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/* 1360 */     long shift = 0L;
/* 1361 */     if ((this.timeline instanceof SegmentedTimeline)) {
/* 1362 */       shift = ((SegmentedTimeline)this.timeline).getStartTime();
/*      */     }
/* 1364 */     double zero = valueToJava2D(shift + 0.0D, dataArea, edge);
/* 1365 */     double tickLabelWidth = estimateMaximumTickLabelWidth(g2, getTickUnit());
/*      */     
/*      */ 
/*      */ 
/* 1369 */     TickUnitSource tickUnits = getStandardTickUnits();
/* 1370 */     TickUnit unit1 = tickUnits.getCeilingTickUnit(getTickUnit());
/* 1371 */     double x1 = valueToJava2D(shift + unit1.getSize(), dataArea, edge);
/* 1372 */     double unit1Width = Math.abs(x1 - zero);
/*      */     
/*      */ 
/* 1375 */     double guess = tickLabelWidth / unit1Width * unit1.getSize();
/* 1376 */     DateTickUnit unit2 = (DateTickUnit)tickUnits.getCeilingTickUnit(guess);
/* 1377 */     double x2 = valueToJava2D(shift + unit2.getSize(), dataArea, edge);
/* 1378 */     double unit2Width = Math.abs(x2 - zero);
/* 1379 */     tickLabelWidth = estimateMaximumTickLabelWidth(g2, unit2);
/* 1380 */     if (tickLabelWidth > unit2Width) {
/* 1381 */       unit2 = (DateTickUnit)tickUnits.getLargerTickUnit(unit2);
/*      */     }
/* 1383 */     setTickUnit(unit2, false, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void selectVerticalAutoTickUnit(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/* 1400 */     TickUnitSource tickUnits = getStandardTickUnits();
/* 1401 */     double zero = valueToJava2D(0.0D, dataArea, edge);
/*      */     
/*      */ 
/* 1404 */     double estimate1 = getRange().getLength() / 10.0D;
/* 1405 */     DateTickUnit candidate1 = (DateTickUnit)tickUnits.getCeilingTickUnit(estimate1);
/*      */     
/* 1407 */     double labelHeight1 = estimateMaximumTickLabelHeight(g2, candidate1);
/* 1408 */     double y1 = valueToJava2D(candidate1.getSize(), dataArea, edge);
/* 1409 */     double candidate1UnitHeight = Math.abs(y1 - zero);
/*      */     
/*      */ 
/* 1412 */     double estimate2 = labelHeight1 / candidate1UnitHeight * candidate1.getSize();
/*      */     
/* 1414 */     DateTickUnit candidate2 = (DateTickUnit)tickUnits.getCeilingTickUnit(estimate2);
/*      */     
/* 1416 */     double labelHeight2 = estimateMaximumTickLabelHeight(g2, candidate2);
/* 1417 */     double y2 = valueToJava2D(candidate2.getSize(), dataArea, edge);
/* 1418 */     double unit2Height = Math.abs(y2 - zero);
/*      */     
/*      */     DateTickUnit finalUnit;
/*      */     DateTickUnit finalUnit;
/* 1422 */     if (labelHeight2 < unit2Height) {
/* 1423 */       finalUnit = candidate2;
/*      */     }
/*      */     else {
/* 1426 */       finalUnit = (DateTickUnit)tickUnits.getLargerTickUnit(candidate2);
/*      */     }
/* 1428 */     setTickUnit(finalUnit, false, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private double estimateMaximumTickLabelWidth(Graphics2D g2, DateTickUnit unit)
/*      */   {
/* 1448 */     RectangleInsets tickLabelInsets = getTickLabelInsets();
/* 1449 */     double result = tickLabelInsets.getLeft() + tickLabelInsets.getRight();
/*      */     
/* 1451 */     Font tickLabelFont = getTickLabelFont();
/* 1452 */     FontRenderContext frc = g2.getFontRenderContext();
/* 1453 */     LineMetrics lm = tickLabelFont.getLineMetrics("ABCxyz", frc);
/* 1454 */     if (isVerticalTickLabels())
/*      */     {
/*      */ 
/* 1457 */       result += lm.getHeight();
/*      */     }
/*      */     else
/*      */     {
/* 1461 */       DateRange range = (DateRange)getRange();
/* 1462 */       Date lower = range.getLowerDate();
/* 1463 */       Date upper = range.getUpperDate();
/* 1464 */       String lowerStr = null;
/* 1465 */       String upperStr = null;
/* 1466 */       DateFormat formatter = getDateFormatOverride();
/* 1467 */       if (formatter != null) {
/* 1468 */         lowerStr = formatter.format(lower);
/* 1469 */         upperStr = formatter.format(upper);
/*      */       }
/*      */       else {
/* 1472 */         lowerStr = unit.dateToString(lower);
/* 1473 */         upperStr = unit.dateToString(upper);
/*      */       }
/* 1475 */       FontMetrics fm = g2.getFontMetrics(tickLabelFont);
/* 1476 */       double w1 = fm.stringWidth(lowerStr);
/* 1477 */       double w2 = fm.stringWidth(upperStr);
/* 1478 */       result += Math.max(w1, w2);
/*      */     }
/*      */     
/* 1481 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private double estimateMaximumTickLabelHeight(Graphics2D g2, DateTickUnit unit)
/*      */   {
/* 1501 */     RectangleInsets tickLabelInsets = getTickLabelInsets();
/* 1502 */     double result = tickLabelInsets.getTop() + tickLabelInsets.getBottom();
/*      */     
/* 1504 */     Font tickLabelFont = getTickLabelFont();
/* 1505 */     FontRenderContext frc = g2.getFontRenderContext();
/* 1506 */     LineMetrics lm = tickLabelFont.getLineMetrics("ABCxyz", frc);
/* 1507 */     if (!isVerticalTickLabels())
/*      */     {
/*      */ 
/* 1510 */       result += lm.getHeight();
/*      */     }
/*      */     else
/*      */     {
/* 1514 */       DateRange range = (DateRange)getRange();
/* 1515 */       Date lower = range.getLowerDate();
/* 1516 */       Date upper = range.getUpperDate();
/* 1517 */       String lowerStr = null;
/* 1518 */       String upperStr = null;
/* 1519 */       DateFormat formatter = getDateFormatOverride();
/* 1520 */       if (formatter != null) {
/* 1521 */         lowerStr = formatter.format(lower);
/* 1522 */         upperStr = formatter.format(upper);
/*      */       }
/*      */       else {
/* 1525 */         lowerStr = unit.dateToString(lower);
/* 1526 */         upperStr = unit.dateToString(upper);
/*      */       }
/* 1528 */       FontMetrics fm = g2.getFontMetrics(tickLabelFont);
/* 1529 */       double w1 = fm.stringWidth(lowerStr);
/* 1530 */       double w2 = fm.stringWidth(upperStr);
/* 1531 */       result += Math.max(w1, w2);
/*      */     }
/*      */     
/* 1534 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public List refreshTicks(Graphics2D g2, AxisState state, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/* 1554 */     List result = null;
/* 1555 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 1556 */       result = refreshTicksHorizontal(g2, dataArea, edge);
/*      */     }
/* 1558 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/* 1559 */       result = refreshTicksVertical(g2, dataArea, edge);
/*      */     }
/* 1561 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Date correctTickDateForPosition(Date time, DateTickUnit unit, DateTickMarkPosition position)
/*      */   {
/* 1576 */     Date result = time;
/* 1577 */     switch (unit.getUnit()) {
/*      */     case 2: 
/*      */     case 3: 
/*      */     case 4: 
/*      */     case 5: 
/*      */     case 6: 
/*      */       break;
/*      */     case 1: 
/* 1585 */       result = calculateDateForPosition(new Month(time, this.timeZone, this.locale), position);
/*      */       
/* 1587 */       break;
/*      */     case 0: 
/* 1589 */       result = calculateDateForPosition(new Year(time, this.timeZone, this.locale), position);
/*      */       
/* 1591 */       break;
/*      */     }
/*      */     
/*      */     
/* 1595 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected List refreshTicksHorizontal(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/* 1610 */     List result = new ArrayList();
/*      */     
/* 1612 */     Font tickLabelFont = getTickLabelFont();
/* 1613 */     g2.setFont(tickLabelFont);
/*      */     
/* 1615 */     if (isAutoTickUnitSelection()) {
/* 1616 */       selectAutoTickUnit(g2, dataArea, edge);
/*      */     }
/*      */     
/* 1619 */     DateTickUnit unit = getTickUnit();
/* 1620 */     Date tickDate = calculateLowestVisibleTickValue(unit);
/* 1621 */     Date upperDate = getMaximumDate();
/*      */     
/* 1623 */     while (tickDate.before(upperDate))
/*      */     {
/* 1625 */       tickDate = correctTickDateForPosition(tickDate, unit, this.tickMarkPosition);
/*      */       
/*      */ 
/* 1628 */       long lowestTickTime = tickDate.getTime();
/* 1629 */       long distance = unit.addToDate(tickDate, this.timeZone).getTime() - lowestTickTime;
/*      */       
/* 1631 */       int minorTickSpaces = getMinorTickCount();
/* 1632 */       if (minorTickSpaces <= 0) {
/* 1633 */         minorTickSpaces = unit.getMinorTickCount();
/*      */       }
/* 1635 */       for (int minorTick = 1; minorTick < minorTickSpaces; minorTick++) {
/* 1636 */         long minorTickTime = lowestTickTime - distance * minorTick / minorTickSpaces;
/*      */         
/* 1638 */         if ((minorTickTime > 0L) && (getRange().contains(minorTickTime)) && (!isHiddenValue(minorTickTime)))
/*      */         {
/* 1640 */           result.add(new DateTick(TickType.MINOR, new Date(minorTickTime), "", TextAnchor.TOP_CENTER, TextAnchor.CENTER, 0.0D));
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1646 */       if (!isHiddenValue(tickDate.getTime()))
/*      */       {
/*      */ 
/* 1649 */         DateFormat formatter = getDateFormatOverride();
/* 1650 */         String tickLabel; String tickLabel; if (formatter != null) {
/* 1651 */           tickLabel = formatter.format(tickDate);
/*      */         }
/*      */         else {
/* 1654 */           tickLabel = this.tickUnit.dateToString(tickDate);
/*      */         }
/* 1656 */         TextAnchor anchor = null;
/* 1657 */         TextAnchor rotationAnchor = null;
/* 1658 */         double angle = 0.0D;
/* 1659 */         if (isVerticalTickLabels()) {
/* 1660 */           anchor = TextAnchor.CENTER_RIGHT;
/* 1661 */           rotationAnchor = TextAnchor.CENTER_RIGHT;
/* 1662 */           if (edge == RectangleEdge.TOP) {
/* 1663 */             angle = 1.5707963267948966D;
/*      */           }
/*      */           else {
/* 1666 */             angle = -1.5707963267948966D;
/*      */           }
/*      */           
/*      */         }
/* 1670 */         else if (edge == RectangleEdge.TOP) {
/* 1671 */           anchor = TextAnchor.BOTTOM_CENTER;
/* 1672 */           rotationAnchor = TextAnchor.BOTTOM_CENTER;
/*      */         }
/*      */         else {
/* 1675 */           anchor = TextAnchor.TOP_CENTER;
/* 1676 */           rotationAnchor = TextAnchor.TOP_CENTER;
/*      */         }
/*      */         
/*      */ 
/* 1680 */         Tick tick = new DateTick(tickDate, tickLabel, anchor, rotationAnchor, angle);
/*      */         
/* 1682 */         result.add(tick);
/*      */         
/* 1684 */         long currentTickTime = tickDate.getTime();
/* 1685 */         tickDate = unit.addToDate(tickDate, this.timeZone);
/* 1686 */         long nextTickTime = tickDate.getTime();
/* 1687 */         for (int minorTick = 1; minorTick < minorTickSpaces; 
/* 1688 */             minorTick++) {
/* 1689 */           long minorTickTime = currentTickTime + (nextTickTime - currentTickTime) * minorTick / minorTickSpaces;
/*      */           
/*      */ 
/* 1692 */           if ((getRange().contains(minorTickTime)) && (!isHiddenValue(minorTickTime)))
/*      */           {
/* 1694 */             result.add(new DateTick(TickType.MINOR, new Date(minorTickTime), "", TextAnchor.TOP_CENTER, TextAnchor.CENTER, 0.0D));
/*      */           }
/*      */           
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1703 */         tickDate = unit.rollDate(tickDate, this.timeZone);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1708 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected List refreshTicksVertical(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/* 1724 */     List result = new ArrayList();
/*      */     
/* 1726 */     Font tickLabelFont = getTickLabelFont();
/* 1727 */     g2.setFont(tickLabelFont);
/*      */     
/* 1729 */     if (isAutoTickUnitSelection()) {
/* 1730 */       selectAutoTickUnit(g2, dataArea, edge);
/*      */     }
/* 1732 */     DateTickUnit unit = getTickUnit();
/* 1733 */     Date tickDate = calculateLowestVisibleTickValue(unit);
/* 1734 */     Date upperDate = getMaximumDate();
/*      */     
/* 1736 */     while (tickDate.before(upperDate))
/*      */     {
/*      */ 
/* 1739 */       tickDate = correctTickDateForPosition(tickDate, unit, this.tickMarkPosition);
/*      */       
/*      */ 
/* 1742 */       long lowestTickTime = tickDate.getTime();
/* 1743 */       long distance = unit.addToDate(tickDate, this.timeZone).getTime() - lowestTickTime;
/*      */       
/* 1745 */       int minorTickSpaces = getMinorTickCount();
/* 1746 */       if (minorTickSpaces <= 0) {
/* 1747 */         minorTickSpaces = unit.getMinorTickCount();
/*      */       }
/* 1749 */       for (int minorTick = 1; minorTick < minorTickSpaces; minorTick++) {
/* 1750 */         long minorTickTime = lowestTickTime - distance * minorTick / minorTickSpaces;
/*      */         
/* 1752 */         if ((minorTickTime > 0L) && (getRange().contains(minorTickTime)) && (!isHiddenValue(minorTickTime)))
/*      */         {
/* 1754 */           result.add(new DateTick(TickType.MINOR, new Date(minorTickTime), "", TextAnchor.TOP_CENTER, TextAnchor.CENTER, 0.0D));
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1759 */       if (!isHiddenValue(tickDate.getTime()))
/*      */       {
/*      */ 
/* 1762 */         DateFormat formatter = getDateFormatOverride();
/* 1763 */         String tickLabel; String tickLabel; if (formatter != null) {
/* 1764 */           tickLabel = formatter.format(tickDate);
/*      */         }
/*      */         else {
/* 1767 */           tickLabel = this.tickUnit.dateToString(tickDate);
/*      */         }
/* 1769 */         TextAnchor anchor = null;
/* 1770 */         TextAnchor rotationAnchor = null;
/* 1771 */         double angle = 0.0D;
/* 1772 */         if (isVerticalTickLabels()) {
/* 1773 */           anchor = TextAnchor.BOTTOM_CENTER;
/* 1774 */           rotationAnchor = TextAnchor.BOTTOM_CENTER;
/* 1775 */           if (edge == RectangleEdge.LEFT) {
/* 1776 */             angle = -1.5707963267948966D;
/*      */           }
/*      */           else {
/* 1779 */             angle = 1.5707963267948966D;
/*      */           }
/*      */           
/*      */         }
/* 1783 */         else if (edge == RectangleEdge.LEFT) {
/* 1784 */           anchor = TextAnchor.CENTER_RIGHT;
/* 1785 */           rotationAnchor = TextAnchor.CENTER_RIGHT;
/*      */         }
/*      */         else {
/* 1788 */           anchor = TextAnchor.CENTER_LEFT;
/* 1789 */           rotationAnchor = TextAnchor.CENTER_LEFT;
/*      */         }
/*      */         
/*      */ 
/* 1793 */         Tick tick = new DateTick(tickDate, tickLabel, anchor, rotationAnchor, angle);
/*      */         
/* 1795 */         result.add(tick);
/* 1796 */         long currentTickTime = tickDate.getTime();
/* 1797 */         tickDate = unit.addToDate(tickDate, this.timeZone);
/* 1798 */         long nextTickTime = tickDate.getTime();
/* 1799 */         for (int minorTick = 1; minorTick < minorTickSpaces; 
/* 1800 */             minorTick++) {
/* 1801 */           long minorTickTime = currentTickTime + (nextTickTime - currentTickTime) * minorTick / minorTickSpaces;
/*      */           
/*      */ 
/* 1804 */           if ((getRange().contains(minorTickTime)) && (!isHiddenValue(minorTickTime)))
/*      */           {
/* 1806 */             result.add(new DateTick(TickType.MINOR, new Date(minorTickTime), "", TextAnchor.TOP_CENTER, TextAnchor.CENTER, 0.0D));
/*      */           }
/*      */           
/*      */         }
/*      */         
/*      */       }
/*      */       else
/*      */       {
/* 1814 */         tickDate = unit.rollDate(tickDate, this.timeZone);
/*      */       }
/*      */     }
/* 1817 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public AxisState draw(Graphics2D g2, double cursor, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, PlotRenderingInfo plotState)
/*      */   {
/* 1841 */     if (!isVisible()) {
/* 1842 */       AxisState state = new AxisState(cursor);
/*      */       
/*      */ 
/* 1845 */       List ticks = refreshTicks(g2, state, dataArea, edge);
/* 1846 */       state.setTicks(ticks);
/* 1847 */       return state;
/*      */     }
/*      */     
/*      */ 
/* 1851 */     AxisState state = drawTickMarksAndLabels(g2, cursor, plotArea, dataArea, edge);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1856 */     state = drawLabel(getLabel(), g2, plotArea, dataArea, edge, state);
/* 1857 */     createAndAddEntity(cursor, state, dataArea, edge, plotState);
/* 1858 */     return state;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void zoomRange(double lowerPercent, double upperPercent)
/*      */   {
/* 1869 */     double start = this.timeline.toTimelineValue(getRange().getLowerBound());
/*      */     
/*      */ 
/* 1872 */     double length = this.timeline.toTimelineValue(getRange().getUpperBound()) - this.timeline.toTimelineValue(getRange().getLowerBound());
/*      */     
/*      */ 
/*      */ 
/* 1876 */     Range adjusted = null;
/* 1877 */     if (isInverted()) {
/* 1878 */       adjusted = new DateRange(this.timeline.toMillisecond((start + length * (1.0D - upperPercent))), this.timeline.toMillisecond((start + length * (1.0D - lowerPercent))));
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/* 1884 */       adjusted = new DateRange(this.timeline.toMillisecond((start + length * lowerPercent)), this.timeline.toMillisecond((start + length * upperPercent)));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1889 */     setRange(adjusted);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Object obj)
/*      */   {
/* 1900 */     if (obj == this) {
/* 1901 */       return true;
/*      */     }
/* 1903 */     if (!(obj instanceof DateAxis)) {
/* 1904 */       return false;
/*      */     }
/* 1906 */     DateAxis that = (DateAxis)obj;
/* 1907 */     if (!ObjectUtilities.equal(this.tickUnit, that.tickUnit)) {
/* 1908 */       return false;
/*      */     }
/* 1910 */     if (!ObjectUtilities.equal(this.dateFormatOverride, that.dateFormatOverride))
/*      */     {
/* 1912 */       return false;
/*      */     }
/* 1914 */     if (!ObjectUtilities.equal(this.tickMarkPosition, that.tickMarkPosition))
/*      */     {
/* 1916 */       return false;
/*      */     }
/* 1918 */     if (!ObjectUtilities.equal(this.timeline, that.timeline)) {
/* 1919 */       return false;
/*      */     }
/* 1921 */     return super.equals(obj);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int hashCode()
/*      */   {
/* 1930 */     if (getLabel() != null) {
/* 1931 */       return getLabel().hashCode();
/*      */     }
/*      */     
/* 1934 */     return 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object clone()
/*      */     throws CloneNotSupportedException
/*      */   {
/* 1947 */     DateAxis clone = (DateAxis)super.clone();
/*      */     
/* 1949 */     if (this.dateFormatOverride != null) {
/* 1950 */       clone.dateFormatOverride = ((DateFormat)this.dateFormatOverride.clone());
/*      */     }
/*      */     
/*      */ 
/* 1954 */     return clone;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/DateAxis.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */