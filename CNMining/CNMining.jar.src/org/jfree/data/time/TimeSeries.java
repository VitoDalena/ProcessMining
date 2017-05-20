/*      */ package org.jfree.data.time;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Date;
/*      */ import java.util.List;
/*      */ import java.util.TimeZone;
/*      */ import org.jfree.data.general.Series;
/*      */ import org.jfree.data.general.SeriesException;
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
/*      */ public class TimeSeries
/*      */   extends Series
/*      */   implements Cloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = -5032960206869675528L;
/*      */   protected static final String DEFAULT_DOMAIN_DESCRIPTION = "Time";
/*      */   protected static final String DEFAULT_RANGE_DESCRIPTION = "Value";
/*      */   private String domain;
/*      */   private String range;
/*      */   protected Class timePeriodClass;
/*      */   protected List data;
/*      */   private int maximumItemCount;
/*      */   private long maximumItemAge;
/*      */   
/*      */   public TimeSeries(Comparable name)
/*      */   {
/*  145 */     this(name, "Time", "Value");
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
/*      */   public TimeSeries(Comparable name, String domain, String range)
/*      */   {
/*  162 */     super(name);
/*  163 */     this.domain = domain;
/*  164 */     this.range = range;
/*  165 */     this.timePeriodClass = null;
/*  166 */     this.data = new ArrayList();
/*  167 */     this.maximumItemCount = Integer.MAX_VALUE;
/*  168 */     this.maximumItemAge = Long.MAX_VALUE;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getDomainDescription()
/*      */   {
/*  179 */     return this.domain;
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
/*      */   public void setDomainDescription(String description)
/*      */   {
/*  192 */     String old = this.domain;
/*  193 */     this.domain = description;
/*  194 */     firePropertyChange("Domain", old, description);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getRangeDescription()
/*      */   {
/*  205 */     return this.range;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeDescription(String description)
/*      */   {
/*  217 */     String old = this.range;
/*  218 */     this.range = description;
/*  219 */     firePropertyChange("Range", old, description);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getItemCount()
/*      */   {
/*  228 */     return this.data.size();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public List getItems()
/*      */   {
/*  238 */     return Collections.unmodifiableList(this.data);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMaximumItemCount()
/*      */   {
/*  250 */     return this.maximumItemCount;
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
/*      */   public void setMaximumItemCount(int maximum)
/*      */   {
/*  265 */     if (maximum < 0) {
/*  266 */       throw new IllegalArgumentException("Negative 'maximum' argument.");
/*      */     }
/*  268 */     this.maximumItemCount = maximum;
/*  269 */     int count = this.data.size();
/*  270 */     if (count > maximum) {
/*  271 */       delete(0, count - maximum - 1);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getMaximumItemAge()
/*      */   {
/*  283 */     return this.maximumItemAge;
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
/*      */   public void setMaximumItemAge(long periods)
/*      */   {
/*  299 */     if (periods < 0L) {
/*  300 */       throw new IllegalArgumentException("Negative 'periods' argument.");
/*      */     }
/*  302 */     this.maximumItemAge = periods;
/*  303 */     removeAgedItems(true);
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
/*      */   public Class getTimePeriodClass()
/*      */   {
/*  317 */     return this.timePeriodClass;
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
/*      */   public TimeSeriesDataItem getDataItem(int index)
/*      */   {
/*  330 */     return (TimeSeriesDataItem)this.data.get(index);
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
/*      */   public TimeSeriesDataItem getDataItem(RegularTimePeriod period)
/*      */   {
/*  344 */     int index = getIndex(period);
/*  345 */     if (index >= 0) {
/*  346 */       return (TimeSeriesDataItem)this.data.get(index);
/*      */     }
/*      */     
/*  349 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RegularTimePeriod getTimePeriod(int index)
/*      */   {
/*  361 */     return getDataItem(index).getPeriod();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RegularTimePeriod getNextTimePeriod()
/*      */   {
/*  371 */     RegularTimePeriod last = getTimePeriod(getItemCount() - 1);
/*  372 */     return last.next();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Collection getTimePeriods()
/*      */   {
/*  381 */     Collection result = new ArrayList();
/*  382 */     for (int i = 0; i < getItemCount(); i++) {
/*  383 */       result.add(getTimePeriod(i));
/*      */     }
/*  385 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Collection getTimePeriodsUniqueToOtherSeries(TimeSeries series)
/*      */   {
/*  397 */     Collection result = new ArrayList();
/*  398 */     for (int i = 0; i < series.getItemCount(); i++) {
/*  399 */       RegularTimePeriod period = series.getTimePeriod(i);
/*  400 */       int index = getIndex(period);
/*  401 */       if (index < 0) {
/*  402 */         result.add(period);
/*      */       }
/*      */     }
/*  405 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getIndex(RegularTimePeriod period)
/*      */   {
/*  417 */     if (period == null) {
/*  418 */       throw new IllegalArgumentException("Null 'period' argument.");
/*      */     }
/*  420 */     TimeSeriesDataItem dummy = new TimeSeriesDataItem(period, -2.147483648E9D);
/*      */     
/*  422 */     return Collections.binarySearch(this.data, dummy);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Number getValue(int index)
/*      */   {
/*  433 */     return getDataItem(index).getValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Number getValue(RegularTimePeriod period)
/*      */   {
/*  445 */     int index = getIndex(period);
/*  446 */     if (index >= 0) {
/*  447 */       return getValue(index);
/*      */     }
/*      */     
/*  450 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void add(TimeSeriesDataItem item)
/*      */   {
/*  462 */     add(item, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void add(TimeSeriesDataItem item, boolean notify)
/*      */   {
/*  474 */     if (item == null) {
/*  475 */       throw new IllegalArgumentException("Null 'item' argument.");
/*      */     }
/*  477 */     Class c = item.getPeriod().getClass();
/*  478 */     if (this.timePeriodClass == null) {
/*  479 */       this.timePeriodClass = c;
/*      */     }
/*  481 */     else if (!this.timePeriodClass.equals(c)) {
/*  482 */       StringBuffer b = new StringBuffer();
/*  483 */       b.append("You are trying to add data where the time period class ");
/*  484 */       b.append("is ");
/*  485 */       b.append(item.getPeriod().getClass().getName());
/*  486 */       b.append(", but the TimeSeries is expecting an instance of ");
/*  487 */       b.append(this.timePeriodClass.getName());
/*  488 */       b.append(".");
/*  489 */       throw new SeriesException(b.toString());
/*      */     }
/*      */     
/*      */ 
/*  493 */     boolean added = false;
/*  494 */     int count = getItemCount();
/*  495 */     if (count == 0) {
/*  496 */       this.data.add(item);
/*  497 */       added = true;
/*      */     }
/*      */     else {
/*  500 */       RegularTimePeriod last = getTimePeriod(getItemCount() - 1);
/*  501 */       if (item.getPeriod().compareTo(last) > 0) {
/*  502 */         this.data.add(item);
/*  503 */         added = true;
/*      */       }
/*      */       else {
/*  506 */         int index = Collections.binarySearch(this.data, item);
/*  507 */         if (index < 0) {
/*  508 */           this.data.add(-index - 1, item);
/*  509 */           added = true;
/*      */         }
/*      */         else {
/*  512 */           StringBuffer b = new StringBuffer();
/*  513 */           b.append("You are attempting to add an observation for ");
/*  514 */           b.append("the time period ");
/*  515 */           b.append(item.getPeriod().toString());
/*  516 */           b.append(" but the series already contains an observation");
/*  517 */           b.append(" for that time period. Duplicates are not ");
/*  518 */           b.append("permitted.  Try using the addOrUpdate() method.");
/*  519 */           throw new SeriesException(b.toString());
/*      */         }
/*      */       }
/*      */     }
/*  523 */     if (added)
/*      */     {
/*  525 */       if (getItemCount() > this.maximumItemCount) {
/*  526 */         this.data.remove(0);
/*      */       }
/*      */       
/*  529 */       removeAgedItems(false);
/*      */       
/*      */ 
/*  532 */       if (notify) {
/*  533 */         fireSeriesChanged();
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
/*      */   public void add(RegularTimePeriod period, double value)
/*      */   {
/*  548 */     add(period, value, true);
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
/*      */   public void add(RegularTimePeriod period, double value, boolean notify)
/*      */   {
/*  561 */     TimeSeriesDataItem item = new TimeSeriesDataItem(period, value);
/*  562 */     add(item, notify);
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
/*      */   public void add(RegularTimePeriod period, Number value)
/*      */   {
/*  575 */     add(period, value, true);
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
/*      */   public void add(RegularTimePeriod period, Number value, boolean notify)
/*      */   {
/*  588 */     TimeSeriesDataItem item = new TimeSeriesDataItem(period, value);
/*  589 */     add(item, notify);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void update(RegularTimePeriod period, Number value)
/*      */   {
/*  600 */     TimeSeriesDataItem temp = new TimeSeriesDataItem(period, value);
/*  601 */     int index = Collections.binarySearch(this.data, temp);
/*  602 */     if (index >= 0) {
/*  603 */       TimeSeriesDataItem pair = (TimeSeriesDataItem)this.data.get(index);
/*  604 */       pair.setValue(value);
/*  605 */       fireSeriesChanged();
/*      */     }
/*      */     else {
/*  608 */       throw new SeriesException("There is no existing value for the specified 'period'.");
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
/*      */   public void update(int index, Number value)
/*      */   {
/*  621 */     TimeSeriesDataItem item = getDataItem(index);
/*  622 */     item.setValue(value);
/*  623 */     fireSeriesChanged();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TimeSeries addAndOrUpdate(TimeSeries series)
/*      */   {
/*  635 */     TimeSeries overwritten = new TimeSeries("Overwritten values from: " + getKey());
/*      */     
/*  637 */     for (int i = 0; i < series.getItemCount(); i++) {
/*  638 */       TimeSeriesDataItem item = series.getDataItem(i);
/*  639 */       TimeSeriesDataItem oldItem = addOrUpdate(item.getPeriod(), item.getValue());
/*      */       
/*  641 */       if (oldItem != null) {
/*  642 */         overwritten.add(oldItem);
/*      */       }
/*      */     }
/*  645 */     return overwritten;
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
/*      */   public TimeSeriesDataItem addOrUpdate(RegularTimePeriod period, double value)
/*      */   {
/*  662 */     return addOrUpdate(period, new Double(value));
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
/*      */   public TimeSeriesDataItem addOrUpdate(RegularTimePeriod period, Number value)
/*      */   {
/*  680 */     if (period == null) {
/*  681 */       throw new IllegalArgumentException("Null 'period' argument.");
/*      */     }
/*  683 */     TimeSeriesDataItem overwritten = null;
/*      */     
/*  685 */     TimeSeriesDataItem key = new TimeSeriesDataItem(period, value);
/*  686 */     int index = Collections.binarySearch(this.data, key);
/*  687 */     if (index >= 0) {
/*  688 */       TimeSeriesDataItem existing = (TimeSeriesDataItem)this.data.get(index);
/*      */       
/*  690 */       overwritten = (TimeSeriesDataItem)existing.clone();
/*  691 */       existing.setValue(value);
/*  692 */       removeAgedItems(false);
/*      */       
/*      */ 
/*  695 */       fireSeriesChanged();
/*      */     }
/*      */     else {
/*  698 */       this.data.add(-index - 1, new TimeSeriesDataItem(period, value));
/*  699 */       this.timePeriodClass = period.getClass();
/*      */       
/*      */ 
/*  702 */       if (getItemCount() > this.maximumItemCount) {
/*  703 */         this.data.remove(0);
/*  704 */         if (this.data.isEmpty()) {
/*  705 */           this.timePeriodClass = null;
/*      */         }
/*      */       }
/*      */       
/*  709 */       removeAgedItems(false);
/*      */       
/*      */ 
/*  712 */       fireSeriesChanged();
/*      */     }
/*  714 */     return overwritten;
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
/*      */   public void removeAgedItems(boolean notify)
/*      */   {
/*  729 */     if (getItemCount() > 1) {
/*  730 */       long latest = getTimePeriod(getItemCount() - 1).getSerialIndex();
/*  731 */       boolean removed = false;
/*      */       
/*  733 */       while (latest - getTimePeriod(0).getSerialIndex() > this.maximumItemAge) {
/*  734 */         this.data.remove(0);
/*  735 */         removed = true;
/*      */       }
/*  737 */       if ((removed) && (notify)) {
/*  738 */         fireSeriesChanged();
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
/*      */   public void removeAgedItems(long latest, boolean notify)
/*      */   {
/*  754 */     if (this.data.isEmpty()) {
/*  755 */       return;
/*      */     }
/*      */     
/*  758 */     long index = Long.MAX_VALUE;
/*      */     try {
/*  760 */       Method m = class$org$jfree$data$time$RegularTimePeriod.getDeclaredMethod("createInstance", new Class[] { Class.class, Date.class, TimeZone.class });
/*      */       
/*      */ 
/*  763 */       RegularTimePeriod newest = (RegularTimePeriod)m.invoke(this.timePeriodClass, new Object[] { this.timePeriodClass, new Date(latest), TimeZone.getDefault() });
/*      */       
/*      */ 
/*  766 */       index = newest.getSerialIndex();
/*      */     }
/*      */     catch (NoSuchMethodException e) {
/*  769 */       e.printStackTrace();
/*      */     }
/*      */     catch (IllegalAccessException e) {
/*  772 */       e.printStackTrace();
/*      */     }
/*      */     catch (InvocationTargetException e) {
/*  775 */       e.printStackTrace();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  780 */     boolean removed = false;
/*      */     
/*  782 */     while ((getItemCount() > 0) && (index - getTimePeriod(0).getSerialIndex() > this.maximumItemAge)) {
/*  783 */       this.data.remove(0);
/*  784 */       removed = true;
/*      */     }
/*  786 */     if ((removed) && (notify)) {
/*  787 */       fireSeriesChanged();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clear()
/*      */   {
/*  796 */     if (this.data.size() > 0) {
/*  797 */       this.data.clear();
/*  798 */       this.timePeriodClass = null;
/*  799 */       fireSeriesChanged();
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
/*      */   public void delete(RegularTimePeriod period)
/*      */   {
/*  812 */     int index = getIndex(period);
/*  813 */     if (index >= 0) {
/*  814 */       this.data.remove(index);
/*  815 */       if (this.data.isEmpty()) {
/*  816 */         this.timePeriodClass = null;
/*      */       }
/*  818 */       fireSeriesChanged();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void delete(int start, int end)
/*      */   {
/*  829 */     if (end < start) {
/*  830 */       throw new IllegalArgumentException("Requires start <= end.");
/*      */     }
/*  832 */     for (int i = 0; i <= end - start; i++) {
/*  833 */       this.data.remove(start);
/*      */     }
/*  835 */     if (this.data.isEmpty()) {
/*  836 */       this.timePeriodClass = null;
/*      */     }
/*  838 */     fireSeriesChanged();
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
/*      */   public Object clone()
/*      */     throws CloneNotSupportedException
/*      */   {
/*  857 */     TimeSeries clone = (TimeSeries)super.clone();
/*  858 */     clone.data = ((List)ObjectUtilities.deepClone(this.data));
/*  859 */     return clone;
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
/*      */   public TimeSeries createCopy(int start, int end)
/*      */     throws CloneNotSupportedException
/*      */   {
/*  877 */     if (start < 0) {
/*  878 */       throw new IllegalArgumentException("Requires start >= 0.");
/*      */     }
/*  880 */     if (end < start) {
/*  881 */       throw new IllegalArgumentException("Requires start <= end.");
/*      */     }
/*  883 */     TimeSeries copy = (TimeSeries)super.clone();
/*      */     
/*  885 */     copy.data = new ArrayList();
/*  886 */     if (this.data.size() > 0) {
/*  887 */       for (int index = start; index <= end; index++) {
/*  888 */         TimeSeriesDataItem item = (TimeSeriesDataItem)this.data.get(index);
/*      */         
/*  890 */         TimeSeriesDataItem clone = (TimeSeriesDataItem)item.clone();
/*      */         try {
/*  892 */           copy.add(clone);
/*      */         }
/*      */         catch (SeriesException e) {
/*  895 */           e.printStackTrace();
/*      */         }
/*      */       }
/*      */     }
/*  899 */     return copy;
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
/*      */   public TimeSeries createCopy(RegularTimePeriod start, RegularTimePeriod end)
/*      */     throws CloneNotSupportedException
/*      */   {
/*  919 */     if (start == null) {
/*  920 */       throw new IllegalArgumentException("Null 'start' argument.");
/*      */     }
/*  922 */     if (end == null) {
/*  923 */       throw new IllegalArgumentException("Null 'end' argument.");
/*      */     }
/*  925 */     if (start.compareTo(end) > 0) {
/*  926 */       throw new IllegalArgumentException("Requires start on or before end.");
/*      */     }
/*      */     
/*  929 */     boolean emptyRange = false;
/*  930 */     int startIndex = getIndex(start);
/*  931 */     if (startIndex < 0) {
/*  932 */       startIndex = -(startIndex + 1);
/*  933 */       if (startIndex == this.data.size()) {
/*  934 */         emptyRange = true;
/*      */       }
/*      */     }
/*  937 */     int endIndex = getIndex(end);
/*  938 */     if (endIndex < 0) {
/*  939 */       endIndex = -(endIndex + 1);
/*  940 */       endIndex -= 1;
/*      */     }
/*  942 */     if ((endIndex < 0) || (endIndex < startIndex)) {
/*  943 */       emptyRange = true;
/*      */     }
/*  945 */     if (emptyRange) {
/*  946 */       TimeSeries copy = (TimeSeries)super.clone();
/*  947 */       copy.data = new ArrayList();
/*  948 */       return copy;
/*      */     }
/*      */     
/*  951 */     return createCopy(startIndex, endIndex);
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
/*      */   public boolean equals(Object object)
/*      */   {
/*  964 */     if (object == this) {
/*  965 */       return true;
/*      */     }
/*  967 */     if (!(object instanceof TimeSeries)) {
/*  968 */       return false;
/*      */     }
/*  970 */     TimeSeries that = (TimeSeries)object;
/*  971 */     if (!ObjectUtilities.equal(getDomainDescription(), that.getDomainDescription()))
/*      */     {
/*  973 */       return false;
/*      */     }
/*  975 */     if (!ObjectUtilities.equal(getRangeDescription(), that.getRangeDescription()))
/*      */     {
/*  977 */       return false;
/*      */     }
/*  979 */     if (!ObjectUtilities.equal(this.timePeriodClass, that.timePeriodClass))
/*      */     {
/*  981 */       return false;
/*      */     }
/*  983 */     if (getMaximumItemAge() != that.getMaximumItemAge()) {
/*  984 */       return false;
/*      */     }
/*  986 */     if (getMaximumItemCount() != that.getMaximumItemCount()) {
/*  987 */       return false;
/*      */     }
/*  989 */     int count = getItemCount();
/*  990 */     if (count != that.getItemCount()) {
/*  991 */       return false;
/*      */     }
/*  993 */     for (int i = 0; i < count; i++) {
/*  994 */       if (!getDataItem(i).equals(that.getDataItem(i))) {
/*  995 */         return false;
/*      */       }
/*      */     }
/*  998 */     return super.equals(object);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int hashCode()
/*      */   {
/* 1007 */     int result = super.hashCode();
/* 1008 */     result = 29 * result + (this.domain != null ? this.domain.hashCode() : 0);
/*      */     
/* 1010 */     result = 29 * result + (this.range != null ? this.range.hashCode() : 0);
/* 1011 */     result = 29 * result + (this.timePeriodClass != null ? this.timePeriodClass.hashCode() : 0);
/*      */     
/*      */ 
/*      */ 
/* 1015 */     int count = getItemCount();
/* 1016 */     if (count > 0) {
/* 1017 */       TimeSeriesDataItem item = getDataItem(0);
/* 1018 */       result = 29 * result + item.hashCode();
/*      */     }
/* 1020 */     if (count > 1) {
/* 1021 */       TimeSeriesDataItem item = getDataItem(count - 1);
/* 1022 */       result = 29 * result + item.hashCode();
/*      */     }
/* 1024 */     if (count > 2) {
/* 1025 */       TimeSeriesDataItem item = getDataItem(count / 2);
/* 1026 */       result = 29 * result + item.hashCode();
/*      */     }
/* 1028 */     result = 29 * result + this.maximumItemCount;
/* 1029 */     result = 29 * result + (int)this.maximumItemAge;
/* 1030 */     return result;
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public TimeSeries(Comparable name, Class timePeriodClass)
/*      */   {
/* 1046 */     this(name, "Time", "Value", timePeriodClass);
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public TimeSeries(Comparable name, String domain, String range, Class timePeriodClass)
/*      */   {
/* 1069 */     super(name);
/* 1070 */     this.domain = domain;
/* 1071 */     this.range = range;
/* 1072 */     this.timePeriodClass = timePeriodClass;
/* 1073 */     this.data = new ArrayList();
/* 1074 */     this.maximumItemCount = Integer.MAX_VALUE;
/* 1075 */     this.maximumItemAge = Long.MAX_VALUE;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/TimeSeries.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */