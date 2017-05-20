/*      */ package org.jfree.chart.axis;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Collections;
/*      */ import java.util.Date;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.SimpleTimeZone;
/*      */ import java.util.TimeZone;
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
/*      */ public class SegmentedTimeline
/*      */   implements Timeline, Cloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 1093779862539903110L;
/*      */   public static final long DAY_SEGMENT_SIZE = 86400000L;
/*      */   public static final long HOUR_SEGMENT_SIZE = 3600000L;
/*      */   public static final long FIFTEEN_MINUTE_SEGMENT_SIZE = 900000L;
/*      */   public static final long MINUTE_SEGMENT_SIZE = 60000L;
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public static long FIRST_MONDAY_AFTER_1900;
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public static TimeZone NO_DST_TIME_ZONE;
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*  223 */   public static TimeZone DEFAULT_TIME_ZONE = ;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Calendar workingCalendarNoDST;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  234 */   private Calendar workingCalendar = Calendar.getInstance();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private long segmentSize;
/*      */   
/*      */ 
/*      */ 
/*      */   private int segmentsIncluded;
/*      */   
/*      */ 
/*      */ 
/*      */   private int segmentsExcluded;
/*      */   
/*      */ 
/*      */ 
/*      */   private int groupSegmentCount;
/*      */   
/*      */ 
/*      */ 
/*      */   private long startTime;
/*      */   
/*      */ 
/*      */ 
/*      */   private long segmentsIncludedSize;
/*      */   
/*      */ 
/*      */ 
/*      */   private long segmentsExcludedSize;
/*      */   
/*      */ 
/*      */ 
/*      */   private long segmentsGroupSize;
/*      */   
/*      */ 
/*      */ 
/*  271 */   private List exceptionSegments = new ArrayList();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private SegmentedTimeline baseTimeline;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  286 */   private boolean adjustForDaylightSaving = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static
/*      */   {
/*  294 */     int offset = TimeZone.getDefault().getRawOffset();
/*  295 */     NO_DST_TIME_ZONE = new SimpleTimeZone(offset, "UTC-" + offset);
/*      */     
/*      */ 
/*      */ 
/*  299 */     Calendar cal = new GregorianCalendar(NO_DST_TIME_ZONE);
/*  300 */     cal.set(1900, 0, 1, 0, 0, 0);
/*  301 */     cal.set(14, 0);
/*  302 */     while (cal.get(7) != 2) {
/*  303 */       cal.add(5, 1);
/*      */     }
/*      */     
/*      */ 
/*  307 */     FIRST_MONDAY_AFTER_1900 = cal.getTime().getTime();
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
/*      */   public SegmentedTimeline(long segmentSize, int segmentsIncluded, int segmentsExcluded)
/*      */   {
/*  331 */     this.segmentSize = segmentSize;
/*  332 */     this.segmentsIncluded = segmentsIncluded;
/*  333 */     this.segmentsExcluded = segmentsExcluded;
/*      */     
/*  335 */     this.groupSegmentCount = (this.segmentsIncluded + this.segmentsExcluded);
/*  336 */     this.segmentsIncludedSize = (this.segmentsIncluded * this.segmentSize);
/*  337 */     this.segmentsExcludedSize = (this.segmentsExcluded * this.segmentSize);
/*  338 */     this.segmentsGroupSize = (this.segmentsIncludedSize + this.segmentsExcludedSize);
/*      */     
/*  340 */     int offset = TimeZone.getDefault().getRawOffset();
/*  341 */     TimeZone z = new SimpleTimeZone(offset, "UTC-" + offset);
/*  342 */     this.workingCalendarNoDST = new GregorianCalendar(z, Locale.getDefault());
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
/*      */   public static long firstMondayAfter1900()
/*      */   {
/*  355 */     int offset = TimeZone.getDefault().getRawOffset();
/*  356 */     TimeZone z = new SimpleTimeZone(offset, "UTC-" + offset);
/*      */     
/*      */ 
/*      */ 
/*  360 */     Calendar cal = new GregorianCalendar(z);
/*  361 */     cal.set(1900, 0, 1, 0, 0, 0);
/*  362 */     cal.set(14, 0);
/*  363 */     while (cal.get(7) != 2) {
/*  364 */       cal.add(5, 1);
/*      */     }
/*      */     
/*      */ 
/*  368 */     return cal.getTime().getTime();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static SegmentedTimeline newMondayThroughFridayTimeline()
/*      */   {
/*  380 */     SegmentedTimeline timeline = new SegmentedTimeline(86400000L, 5, 2);
/*      */     
/*  382 */     timeline.setStartTime(firstMondayAfter1900());
/*  383 */     return timeline;
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
/*      */   public static SegmentedTimeline newFifteenMinuteTimeline()
/*      */   {
/*  404 */     SegmentedTimeline timeline = new SegmentedTimeline(900000L, 28, 68);
/*      */     
/*  406 */     timeline.setStartTime(firstMondayAfter1900() + 36L * timeline.getSegmentSize());
/*      */     
/*  408 */     timeline.setBaseTimeline(newMondayThroughFridayTimeline());
/*  409 */     return timeline;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getAdjustForDaylightSaving()
/*      */   {
/*  419 */     return this.adjustForDaylightSaving;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAdjustForDaylightSaving(boolean adjust)
/*      */   {
/*  429 */     this.adjustForDaylightSaving = adjust;
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
/*      */   public void setStartTime(long millisecond)
/*      */   {
/*  443 */     this.startTime = millisecond;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getStartTime()
/*      */   {
/*  453 */     return this.startTime;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getSegmentsExcluded()
/*      */   {
/*  462 */     return this.segmentsExcluded;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getSegmentsExcludedSize()
/*      */   {
/*  472 */     return this.segmentsExcludedSize;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getGroupSegmentCount()
/*      */   {
/*  482 */     return this.groupSegmentCount;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getSegmentsGroupSize()
/*      */   {
/*  492 */     return this.segmentsGroupSize;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getSegmentsIncluded()
/*      */   {
/*  501 */     return this.segmentsIncluded;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getSegmentsIncludedSize()
/*      */   {
/*  510 */     return this.segmentsIncludedSize;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getSegmentSize()
/*      */   {
/*  519 */     return this.segmentSize;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public List getExceptionSegments()
/*      */   {
/*  529 */     return Collections.unmodifiableList(this.exceptionSegments);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setExceptionSegments(List exceptionSegments)
/*      */   {
/*  538 */     this.exceptionSegments = exceptionSegments;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public SegmentedTimeline getBaseTimeline()
/*      */   {
/*  547 */     return this.baseTimeline;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBaseTimeline(SegmentedTimeline baseTimeline)
/*      */   {
/*  558 */     if (baseTimeline != null) {
/*  559 */       if (baseTimeline.getSegmentSize() < this.segmentSize) {
/*  560 */         throw new IllegalArgumentException("baseTimeline.getSegmentSize() is smaller than segmentSize");
/*      */       }
/*      */       
/*      */ 
/*  564 */       if (baseTimeline.getStartTime() > this.startTime) {
/*  565 */         throw new IllegalArgumentException("baseTimeline.getStartTime() is after startTime");
/*      */       }
/*      */       
/*  568 */       if (baseTimeline.getSegmentSize() % this.segmentSize != 0L) {
/*  569 */         throw new IllegalArgumentException("baseTimeline.getSegmentSize() is not multiple of segmentSize");
/*      */       }
/*      */       
/*      */ 
/*  573 */       if ((this.startTime - baseTimeline.getStartTime()) % this.segmentSize != 0L)
/*      */       {
/*  575 */         throw new IllegalArgumentException("baseTimeline is not aligned");
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  580 */     this.baseTimeline = baseTimeline;
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
/*      */   public long toTimelineValue(long millisecond)
/*      */   {
/*  595 */     long rawMilliseconds = millisecond - this.startTime;
/*  596 */     long groupMilliseconds = rawMilliseconds % this.segmentsGroupSize;
/*  597 */     long groupIndex = rawMilliseconds / this.segmentsGroupSize;
/*      */     long result;
/*  599 */     long result; if (groupMilliseconds >= this.segmentsIncludedSize) {
/*  600 */       result = toTimelineValue(this.startTime + this.segmentsGroupSize * (groupIndex + 1L));
/*      */     }
/*      */     else
/*      */     {
/*  604 */       Segment segment = getSegment(millisecond);
/*  605 */       long result; if (segment.inExceptionSegments()) {
/*      */         int p;
/*  607 */         while ((p = binarySearchExceptionSegments(segment)) >= 0) {
/*  608 */           segment = getSegment(millisecond = ((Segment)this.exceptionSegments.get(p)).getSegmentEnd() + 1L);
/*      */         }
/*      */         
/*  611 */         result = toTimelineValue(millisecond);
/*      */       }
/*      */       else {
/*  614 */         long shiftedSegmentedValue = millisecond - this.startTime;
/*  615 */         long x = shiftedSegmentedValue % this.segmentsGroupSize;
/*  616 */         long y = shiftedSegmentedValue / this.segmentsGroupSize;
/*      */         
/*  618 */         long wholeExceptionsBeforeDomainValue = getExceptionSegmentCount(this.startTime, millisecond - 1L);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */         long result;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  628 */         if (x < this.segmentsIncludedSize) {
/*  629 */           result = this.segmentsIncludedSize * y + x - wholeExceptionsBeforeDomainValue * this.segmentSize;
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/*  635 */           result = this.segmentsIncludedSize * (y + 1L) - wholeExceptionsBeforeDomainValue * this.segmentSize;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  643 */     return result;
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
/*      */   public long toTimelineValue(Date date)
/*      */   {
/*  656 */     return toTimelineValue(getTime(date));
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
/*      */   public long toMillisecond(long timelineValue)
/*      */   {
/*  670 */     Segment result = new Segment(this.startTime + timelineValue + timelineValue / this.segmentsIncludedSize * this.segmentsExcludedSize);
/*      */     
/*      */ 
/*      */ 
/*  674 */     long lastIndex = this.startTime;
/*      */     
/*      */ 
/*  677 */     while (lastIndex <= result.segmentStart)
/*      */     {
/*      */       long exceptionSegmentCount;
/*      */       
/*      */ 
/*      */ 
/*  683 */       while ((exceptionSegmentCount = getExceptionSegmentCount(lastIndex, result.millisecond / this.segmentSize * this.segmentSize - 1L)) > 0L)
/*      */       {
/*  685 */         lastIndex = result.segmentStart;
/*      */         
/*      */ 
/*  688 */         for (int i = 0; i < exceptionSegmentCount; i++) {
/*      */           do {
/*  690 */             result.inc();
/*      */           }
/*  692 */           while (result.inExcludeSegments());
/*      */         }
/*      */       }
/*  695 */       lastIndex = result.segmentStart;
/*      */       
/*      */ 
/*  698 */       while ((result.inExceptionSegments()) || (result.inExcludeSegments())) {
/*  699 */         result.inc();
/*  700 */         lastIndex += this.segmentSize;
/*      */       }
/*      */       
/*  703 */       lastIndex += 1L;
/*      */     }
/*      */     
/*  706 */     return getTimeFromLong(result.millisecond);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getTimeFromLong(long date)
/*      */   {
/*  717 */     long result = date;
/*  718 */     if (this.adjustForDaylightSaving) {
/*  719 */       this.workingCalendarNoDST.setTime(new Date(date));
/*  720 */       this.workingCalendar.set(this.workingCalendarNoDST.get(1), this.workingCalendarNoDST.get(2), this.workingCalendarNoDST.get(5), this.workingCalendarNoDST.get(11), this.workingCalendarNoDST.get(12), this.workingCalendarNoDST.get(13));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  728 */       this.workingCalendar.set(14, this.workingCalendarNoDST.get(14));
/*      */       
/*      */ 
/*      */ 
/*  732 */       result = this.workingCalendar.getTime().getTime();
/*      */     }
/*  734 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean containsDomainValue(long millisecond)
/*      */   {
/*  745 */     Segment segment = getSegment(millisecond);
/*  746 */     return segment.inIncludeSegments();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean containsDomainValue(Date date)
/*      */   {
/*  757 */     return containsDomainValue(getTime(date));
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
/*      */   public boolean containsDomainRange(long domainValueStart, long domainValueEnd)
/*      */   {
/*  772 */     if (domainValueEnd < domainValueStart) {
/*  773 */       throw new IllegalArgumentException("domainValueEnd (" + domainValueEnd + ") < domainValueStart (" + domainValueStart + ")");
/*      */     }
/*      */     
/*      */ 
/*  777 */     Segment segment = getSegment(domainValueStart);
/*  778 */     boolean contains = true;
/*      */     do {
/*  780 */       contains = segment.inIncludeSegments();
/*  781 */       if (segment.contains(domainValueEnd)) {
/*      */         break;
/*      */       }
/*      */       
/*  785 */       segment.inc();
/*      */ 
/*      */     }
/*  788 */     while (contains);
/*  789 */     return contains;
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
/*      */   public boolean containsDomainRange(Date dateDomainValueStart, Date dateDomainValueEnd)
/*      */   {
/*  804 */     return containsDomainRange(getTime(dateDomainValueStart), getTime(dateDomainValueEnd));
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
/*      */   public void addException(long millisecond)
/*      */   {
/*  821 */     addException(new Segment(millisecond));
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
/*      */   public void addException(long fromDomainValue, long toDomainValue)
/*      */   {
/*  840 */     addException(new SegmentRange(fromDomainValue, toDomainValue));
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
/*      */   public void addException(Date exceptionDate)
/*      */   {
/*  855 */     addException(getTime(exceptionDate));
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
/*      */   public void addExceptions(List exceptionList)
/*      */   {
/*  872 */     for (Iterator iter = exceptionList.iterator(); iter.hasNext();) {
/*  873 */       addException((Date)iter.next());
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
/*      */   private void addException(Segment segment)
/*      */   {
/*  888 */     if (segment.inIncludeSegments()) {
/*  889 */       int p = binarySearchExceptionSegments(segment);
/*  890 */       this.exceptionSegments.add(-(p + 1), segment);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addBaseTimelineException(long domainValue)
/*      */   {
/*  912 */     Segment baseSegment = this.baseTimeline.getSegment(domainValue);
/*  913 */     if (baseSegment.inIncludeSegments())
/*      */     {
/*      */ 
/*      */ 
/*  917 */       Segment segment = getSegment(baseSegment.getSegmentStart());
/*  918 */       while (segment.getSegmentStart() <= baseSegment.getSegmentEnd()) {
/*  919 */         if (segment.inIncludeSegments())
/*      */         {
/*      */ 
/*  922 */           long fromDomainValue = segment.getSegmentStart();
/*      */           long toDomainValue;
/*      */           do {
/*  925 */             toDomainValue = segment.getSegmentEnd();
/*  926 */             segment.inc();
/*      */           }
/*  928 */           while (segment.inIncludeSegments());
/*      */           
/*      */ 
/*  931 */           addException(fromDomainValue, toDomainValue);
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*  936 */           segment.inc();
/*      */         }
/*      */       }
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
/*      */   public void addBaseTimelineException(Date date)
/*      */   {
/*  955 */     addBaseTimelineException(getTime(date));
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
/*      */   public void addBaseTimelineExclusions(long fromBaseDomainValue, long toBaseDomainValue)
/*      */   {
/*  971 */     Segment baseSegment = this.baseTimeline.getSegment(fromBaseDomainValue);
/*      */     
/*  973 */     while ((baseSegment.getSegmentStart() <= toBaseDomainValue) && (!baseSegment.inExcludeSegments()))
/*      */     {
/*  975 */       baseSegment.inc();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  980 */     while (baseSegment.getSegmentStart() <= toBaseDomainValue)
/*      */     {
/*  982 */       long baseExclusionRangeEnd = baseSegment.getSegmentStart() + this.baseTimeline.getSegmentsExcluded() * this.baseTimeline.getSegmentSize() - 1L;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  988 */       Segment segment = getSegment(baseSegment.getSegmentStart());
/*  989 */       while (segment.getSegmentStart() <= baseExclusionRangeEnd) {
/*  990 */         if (segment.inIncludeSegments())
/*      */         {
/*      */ 
/*  993 */           long fromDomainValue = segment.getSegmentStart();
/*      */           long toDomainValue;
/*      */           do {
/*  996 */             toDomainValue = segment.getSegmentEnd();
/*  997 */             segment.inc();
/*      */           }
/*  999 */           while (segment.inIncludeSegments());
/*      */           
/*      */ 
/* 1002 */           addException(new BaseTimelineSegmentRange(fromDomainValue, toDomainValue));
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/* 1007 */           segment.inc();
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1012 */       baseSegment.inc(this.baseTimeline.getGroupSegmentCount());
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
/*      */   public long getExceptionSegmentCount(long fromMillisecond, long toMillisecond)
/*      */   {
/* 1027 */     if (toMillisecond < fromMillisecond) {
/* 1028 */       return 0L;
/*      */     }
/*      */     
/* 1031 */     int n = 0;
/* 1032 */     Iterator iter = this.exceptionSegments.iterator();
/* 1033 */     while (iter.hasNext()) {
/* 1034 */       Segment segment = (Segment)iter.next();
/* 1035 */       Segment intersection = segment.intersect(fromMillisecond, toMillisecond);
/*      */       
/* 1037 */       if (intersection != null) {
/* 1038 */         n = (int)(n + intersection.getSegmentCount());
/*      */       }
/*      */     }
/*      */     
/* 1042 */     return n;
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
/*      */   public Segment getSegment(long millisecond)
/*      */   {
/* 1057 */     return new Segment(millisecond);
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
/*      */   public Segment getSegment(Date date)
/*      */   {
/* 1075 */     return getSegment(getTime(date));
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
/*      */   private boolean equals(Object o, Object p)
/*      */   {
/* 1089 */     return (o == p) || ((o != null) && (o.equals(p)));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Object o)
/*      */   {
/* 1100 */     if ((o instanceof SegmentedTimeline)) {
/* 1101 */       SegmentedTimeline other = (SegmentedTimeline)o;
/*      */       
/* 1103 */       boolean b0 = this.segmentSize == other.getSegmentSize();
/* 1104 */       boolean b1 = this.segmentsIncluded == other.getSegmentsIncluded();
/* 1105 */       boolean b2 = this.segmentsExcluded == other.getSegmentsExcluded();
/* 1106 */       boolean b3 = this.startTime == other.getStartTime();
/* 1107 */       boolean b4 = equals(this.exceptionSegments, other.getExceptionSegments());
/*      */       
/* 1109 */       return (b0) && (b1) && (b2) && (b3) && (b4);
/*      */     }
/*      */     
/* 1112 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int hashCode()
/*      */   {
/* 1122 */     int result = 19;
/* 1123 */     result = 37 * result + (int)(this.segmentSize ^ this.segmentSize >>> 32);
/*      */     
/* 1125 */     result = 37 * result + (int)(this.startTime ^ this.startTime >>> 32);
/* 1126 */     return result;
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
/*      */   private int binarySearchExceptionSegments(Segment segment)
/*      */   {
/* 1145 */     int low = 0;
/* 1146 */     int high = this.exceptionSegments.size() - 1;
/*      */     
/* 1148 */     while (low <= high) {
/* 1149 */       int mid = (low + high) / 2;
/* 1150 */       Segment midSegment = (Segment)this.exceptionSegments.get(mid);
/*      */       
/*      */ 
/* 1153 */       if ((segment.contains(midSegment)) || (midSegment.contains(segment))) {
/* 1154 */         return mid;
/*      */       }
/*      */       
/* 1157 */       if (midSegment.before(segment)) {
/* 1158 */         low = mid + 1;
/*      */       }
/* 1160 */       else if (midSegment.after(segment)) {
/* 1161 */         high = mid - 1;
/*      */       }
/*      */       else {
/* 1164 */         throw new IllegalStateException("Invalid condition.");
/*      */       }
/*      */     }
/* 1167 */     return -(low + 1);
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
/*      */   public long getTime(Date date)
/*      */   {
/* 1181 */     long result = date.getTime();
/* 1182 */     if (this.adjustForDaylightSaving) {
/* 1183 */       this.workingCalendar.setTime(date);
/* 1184 */       this.workingCalendarNoDST.set(this.workingCalendar.get(1), this.workingCalendar.get(2), this.workingCalendar.get(5), this.workingCalendar.get(11), this.workingCalendar.get(12), this.workingCalendar.get(13));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1191 */       this.workingCalendarNoDST.set(14, this.workingCalendar.get(14));
/*      */       
/* 1193 */       Date revisedDate = this.workingCalendarNoDST.getTime();
/* 1194 */       result = revisedDate.getTime();
/*      */     }
/*      */     
/* 1197 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Date getDate(long value)
/*      */   {
/* 1208 */     this.workingCalendarNoDST.setTime(new Date(value));
/* 1209 */     return this.workingCalendarNoDST.getTime();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object clone()
/*      */     throws CloneNotSupportedException
/*      */   {
/* 1220 */     SegmentedTimeline clone = (SegmentedTimeline)super.clone();
/* 1221 */     return clone;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public class Segment
/*      */     implements Comparable, Cloneable, Serializable
/*      */   {
/*      */     protected long segmentNumber;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     protected long segmentStart;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     protected long segmentEnd;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     protected long millisecond;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected Segment() {}
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     protected Segment(long millisecond)
/*      */     {
/* 1259 */       this.segmentNumber = calculateSegmentNumber(millisecond);
/* 1260 */       this.segmentStart = (SegmentedTimeline.this.startTime + this.segmentNumber * SegmentedTimeline.this.segmentSize);
/*      */       
/* 1262 */       this.segmentEnd = (this.segmentStart + SegmentedTimeline.this.segmentSize - 1L);
/*      */       
/* 1264 */       this.millisecond = millisecond;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public long calculateSegmentNumber(long millis)
/*      */     {
/* 1275 */       if (millis >= SegmentedTimeline.this.startTime) {
/* 1276 */         return (millis - SegmentedTimeline.this.startTime) / SegmentedTimeline.this.segmentSize;
/*      */       }
/*      */       
/*      */ 
/* 1280 */       return (millis - SegmentedTimeline.this.startTime) / SegmentedTimeline.this.segmentSize - 1L;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public long getSegmentNumber()
/*      */     {
/* 1291 */       return this.segmentNumber;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public long getSegmentCount()
/*      */     {
/* 1301 */       return 1L;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public long getSegmentStart()
/*      */     {
/* 1310 */       return this.segmentStart;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public long getSegmentEnd()
/*      */     {
/* 1319 */       return this.segmentEnd;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public long getMillisecond()
/*      */     {
/* 1329 */       return this.millisecond;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public Date getDate()
/*      */     {
/* 1339 */       return SegmentedTimeline.this.getDate(this.millisecond);
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
/*      */     public boolean contains(long millis)
/*      */     {
/* 1352 */       return (this.segmentStart <= millis) && (millis <= this.segmentEnd);
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
/*      */ 
/*      */     public boolean contains(long from, long to)
/*      */     {
/* 1366 */       return (this.segmentStart <= from) && (to <= this.segmentEnd);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public boolean contains(Segment segment)
/*      */     {
/* 1378 */       return contains(segment.getSegmentStart(), segment.getSegmentEnd());
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
/*      */ 
/*      */     public boolean contained(long from, long to)
/*      */     {
/* 1392 */       return (from <= this.segmentStart) && (this.segmentEnd <= to);
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
/*      */     public Segment intersect(long from, long to)
/*      */     {
/* 1405 */       if ((from <= this.segmentStart) && (this.segmentEnd <= to)) {
/* 1406 */         return this;
/*      */       }
/*      */       
/* 1409 */       return null;
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
/*      */     public boolean before(Segment other)
/*      */     {
/* 1422 */       return this.segmentEnd < other.getSegmentStart();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public boolean after(Segment other)
/*      */     {
/* 1434 */       return this.segmentStart > other.getSegmentEnd();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public boolean equals(Object object)
/*      */     {
/* 1446 */       if ((object instanceof Segment)) {
/* 1447 */         Segment other = (Segment)object;
/* 1448 */         return (this.segmentNumber == other.getSegmentNumber()) && (this.segmentStart == other.getSegmentStart()) && (this.segmentEnd == other.getSegmentEnd()) && (this.millisecond == other.getMillisecond());
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1454 */       return false;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public Segment copy()
/*      */     {
/*      */       try
/*      */       {
/* 1466 */         return (Segment)clone();
/*      */       }
/*      */       catch (CloneNotSupportedException e) {}
/* 1469 */       return null;
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
/*      */ 
/*      */     public int compareTo(Object object)
/*      */     {
/* 1483 */       Segment other = (Segment)object;
/* 1484 */       if (before(other)) {
/* 1485 */         return -1;
/*      */       }
/* 1487 */       if (after(other)) {
/* 1488 */         return 1;
/*      */       }
/*      */       
/* 1491 */       return 0;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public boolean inIncludeSegments()
/*      */     {
/* 1502 */       if (getSegmentNumberRelativeToGroup() < SegmentedTimeline.this.segmentsIncluded)
/*      */       {
/* 1504 */         return !inExceptionSegments();
/*      */       }
/*      */       
/* 1507 */       return false;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public boolean inExcludeSegments()
/*      */     {
/* 1517 */       return getSegmentNumberRelativeToGroup() >= SegmentedTimeline.this.segmentsIncluded;
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
/*      */     private long getSegmentNumberRelativeToGroup()
/*      */     {
/* 1530 */       long p = this.segmentNumber % SegmentedTimeline.this.groupSegmentCount;
/*      */       
/* 1532 */       if (p < 0L) {
/* 1533 */         p += SegmentedTimeline.this.groupSegmentCount;
/*      */       }
/* 1535 */       return p;
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
/*      */ 
/*      */ 
/*      */     public boolean inExceptionSegments()
/*      */     {
/* 1550 */       return SegmentedTimeline.this.binarySearchExceptionSegments(this) >= 0;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public void inc(long n)
/*      */     {
/* 1560 */       this.segmentNumber += n;
/* 1561 */       long m = n * SegmentedTimeline.this.segmentSize;
/* 1562 */       this.segmentStart += m;
/* 1563 */       this.segmentEnd += m;
/* 1564 */       this.millisecond += m;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     public void inc()
/*      */     {
/* 1572 */       inc(1L);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public void dec(long n)
/*      */     {
/* 1582 */       this.segmentNumber -= n;
/* 1583 */       long m = n * SegmentedTimeline.this.segmentSize;
/* 1584 */       this.segmentStart -= m;
/* 1585 */       this.segmentEnd -= m;
/* 1586 */       this.millisecond -= m;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     public void dec()
/*      */     {
/* 1594 */       dec(1L);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     public void moveIndexToStart()
/*      */     {
/* 1601 */       this.millisecond = this.segmentStart;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     public void moveIndexToEnd()
/*      */     {
/* 1608 */       this.millisecond = this.segmentEnd;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected class SegmentRange
/*      */     extends SegmentedTimeline.Segment
/*      */   {
/*      */     private long segmentCount;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public SegmentRange(long fromMillisecond, long toMillisecond)
/*      */     {
/* 1631 */       super();
/*      */       
/* 1633 */       SegmentedTimeline.Segment start = SegmentedTimeline.this.getSegment(fromMillisecond);
/* 1634 */       SegmentedTimeline.Segment end = SegmentedTimeline.this.getSegment(toMillisecond);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1641 */       this.millisecond = fromMillisecond;
/* 1642 */       this.segmentNumber = calculateSegmentNumber(fromMillisecond);
/* 1643 */       this.segmentStart = start.segmentStart;
/* 1644 */       this.segmentEnd = end.segmentEnd;
/* 1645 */       this.segmentCount = (end.getSegmentNumber() - start.getSegmentNumber() + 1L);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public long getSegmentCount()
/*      */     {
/* 1655 */       return this.segmentCount;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public SegmentedTimeline.Segment intersect(long from, long to)
/*      */     {
/* 1673 */       long start = Math.max(from, this.segmentStart);
/* 1674 */       long end = Math.min(to, this.segmentEnd);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1679 */       if (start <= end) {
/* 1680 */         return new SegmentRange(SegmentedTimeline.this, start, end);
/*      */       }
/*      */       
/* 1683 */       return null;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public boolean inIncludeSegments()
/*      */     {
/* 1694 */       SegmentedTimeline.Segment segment = SegmentedTimeline.this.getSegment(this.segmentStart);
/* 1695 */       for (; segment.getSegmentStart() < this.segmentEnd; 
/* 1696 */           segment.inc()) {
/* 1697 */         if (!segment.inIncludeSegments()) {
/* 1698 */           return false;
/*      */         }
/*      */       }
/* 1701 */       return true;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public boolean inExcludeSegments()
/*      */     {
/* 1710 */       SegmentedTimeline.Segment segment = SegmentedTimeline.this.getSegment(this.segmentStart);
/* 1711 */       for (; segment.getSegmentStart() < this.segmentEnd; 
/* 1712 */           segment.inc()) {
/* 1713 */         if (!segment.inExceptionSegments()) {
/* 1714 */           return false;
/*      */         }
/*      */       }
/* 1717 */       return true;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public void inc(long n)
/*      */     {
/* 1727 */       throw new IllegalArgumentException("Not implemented in SegmentRange");
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
/*      */   protected class BaseTimelineSegmentRange
/*      */     extends SegmentedTimeline.SegmentRange
/*      */   {
/*      */     public BaseTimelineSegmentRange(long fromDomainValue, long toDomainValue)
/*      */     {
/* 1746 */       super(fromDomainValue, toDomainValue);
/*      */     }
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/SegmentedTimeline.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */