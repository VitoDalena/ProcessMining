/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.data.general.Series;
/*     */ import org.jfree.data.general.SeriesException;
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
/*     */ public class XYSeries
/*     */   extends Series
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   static final long serialVersionUID = -5908509288197150436L;
/*     */   protected List data;
/* 110 */   private int maximumItemCount = Integer.MAX_VALUE;
/*     */   
/*     */ 
/*     */ 
/*     */   private boolean autoSort;
/*     */   
/*     */ 
/*     */ 
/*     */   private boolean allowDuplicateXValues;
/*     */   
/*     */ 
/*     */ 
/*     */   private double minX;
/*     */   
/*     */ 
/*     */ 
/*     */   private double maxX;
/*     */   
/*     */ 
/*     */ 
/*     */   private double minY;
/*     */   
/*     */ 
/*     */ 
/*     */   private double maxY;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public XYSeries(Comparable key)
/*     */   {
/* 141 */     this(key, true, true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XYSeries(Comparable key, boolean autoSort)
/*     */   {
/* 153 */     this(key, autoSort, true);
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
/*     */   public XYSeries(Comparable key, boolean autoSort, boolean allowDuplicateXValues)
/*     */   {
/* 168 */     super(key);
/* 169 */     this.data = new ArrayList();
/* 170 */     this.autoSort = autoSort;
/* 171 */     this.allowDuplicateXValues = allowDuplicateXValues;
/* 172 */     this.minX = NaN.0D;
/* 173 */     this.maxX = NaN.0D;
/* 174 */     this.minY = NaN.0D;
/* 175 */     this.maxY = NaN.0D;
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
/*     */   public double getMinX()
/*     */   {
/* 190 */     return this.minX;
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
/*     */   public double getMaxX()
/*     */   {
/* 205 */     return this.maxX;
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
/*     */   public double getMinY()
/*     */   {
/* 220 */     return this.minY;
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
/*     */   public double getMaxY()
/*     */   {
/* 235 */     return this.maxY;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void updateBoundsForAddedItem(XYDataItem item)
/*     */   {
/* 246 */     double x = item.getXValue();
/* 247 */     this.minX = minIgnoreNaN(this.minX, x);
/* 248 */     this.maxX = maxIgnoreNaN(this.maxX, x);
/* 249 */     if (item.getY() != null) {
/* 250 */       double y = item.getYValue();
/* 251 */       this.minY = minIgnoreNaN(this.minY, y);
/* 252 */       this.maxY = maxIgnoreNaN(this.maxY, y);
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
/*     */   private void updateBoundsForRemovedItem(XYDataItem item)
/*     */   {
/* 265 */     boolean itemContributesToXBounds = false;
/* 266 */     boolean itemContributesToYBounds = false;
/* 267 */     double x = item.getXValue();
/* 268 */     if ((!Double.isNaN(x)) && (
/* 269 */       (x <= this.minX) || (x >= this.maxX))) {
/* 270 */       itemContributesToXBounds = true;
/*     */     }
/*     */     
/* 273 */     if (item.getY() != null) {
/* 274 */       double y = item.getYValue();
/* 275 */       if ((!Double.isNaN(y)) && (
/* 276 */         (y <= this.minY) || (y >= this.maxY))) {
/* 277 */         itemContributesToYBounds = true;
/*     */       }
/*     */     }
/*     */     
/* 281 */     if (itemContributesToYBounds) {
/* 282 */       findBoundsByIteration();
/*     */     }
/* 284 */     else if (itemContributesToXBounds) {
/* 285 */       if (getAutoSort()) {
/* 286 */         this.minX = getX(0).doubleValue();
/* 287 */         this.maxX = getX(getItemCount() - 1).doubleValue();
/*     */       }
/*     */       else {
/* 290 */         findBoundsByIteration();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void findBoundsByIteration()
/*     */   {
/* 302 */     this.minX = NaN.0D;
/* 303 */     this.maxX = NaN.0D;
/* 304 */     this.minY = NaN.0D;
/* 305 */     this.maxY = NaN.0D;
/* 306 */     Iterator iterator = this.data.iterator();
/* 307 */     while (iterator.hasNext()) {
/* 308 */       XYDataItem item = (XYDataItem)iterator.next();
/* 309 */       updateBoundsForAddedItem(item);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getAutoSort()
/*     */   {
/* 321 */     return this.autoSort;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getAllowDuplicateXValues()
/*     */   {
/* 331 */     return this.allowDuplicateXValues;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getItemCount()
/*     */   {
/* 342 */     return this.data.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getItems()
/*     */   {
/* 352 */     return Collections.unmodifiableList(this.data);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMaximumItemCount()
/*     */   {
/* 364 */     return this.maximumItemCount;
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
/*     */   public void setMaximumItemCount(int maximum)
/*     */   {
/* 382 */     this.maximumItemCount = maximum;
/* 383 */     int remove = this.data.size() - maximum;
/* 384 */     if (remove > 0) {
/* 385 */       this.data.subList(0, remove).clear();
/* 386 */       findBoundsByIteration();
/* 387 */       fireSeriesChanged();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(XYDataItem item)
/*     */   {
/* 399 */     add(item, true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(double x, double y)
/*     */   {
/* 410 */     add(new Double(x), new Double(y), true);
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
/*     */   public void add(double x, double y, boolean notify)
/*     */   {
/* 424 */     add(new Double(x), new Double(y), notify);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(double x, Number y)
/*     */   {
/* 436 */     add(new Double(x), y);
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
/*     */   public void add(double x, Number y, boolean notify)
/*     */   {
/* 451 */     add(new Double(x), y, notify);
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
/*     */   public void add(Number x, Number y)
/*     */   {
/* 470 */     add(x, y, true);
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
/*     */   public void add(Number x, Number y, boolean notify)
/*     */   {
/* 488 */     XYDataItem item = new XYDataItem(x, y);
/* 489 */     add(item, notify);
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
/*     */   public void add(XYDataItem item, boolean notify)
/*     */   {
/* 502 */     if (item == null) {
/* 503 */       throw new IllegalArgumentException("Null 'item' argument.");
/*     */     }
/* 505 */     if (this.autoSort) {
/* 506 */       int index = Collections.binarySearch(this.data, item);
/* 507 */       if (index < 0) {
/* 508 */         this.data.add(-index - 1, item);
/*     */ 
/*     */       }
/* 511 */       else if (this.allowDuplicateXValues)
/*     */       {
/* 513 */         int size = this.data.size();
/*     */         
/* 515 */         while ((index < size) && (item.compareTo(this.data.get(index)) == 0)) {
/* 516 */           index++;
/*     */         }
/* 518 */         if (index < this.data.size()) {
/* 519 */           this.data.add(index, item);
/*     */         }
/*     */         else {
/* 522 */           this.data.add(item);
/*     */         }
/*     */       }
/*     */       else {
/* 526 */         throw new SeriesException("X-value already exists.");
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 531 */       if (!this.allowDuplicateXValues)
/*     */       {
/*     */ 
/* 534 */         int index = indexOf(item.getX());
/* 535 */         if (index >= 0) {
/* 536 */           throw new SeriesException("X-value already exists.");
/*     */         }
/*     */       }
/* 539 */       this.data.add(item);
/*     */     }
/* 541 */     updateBoundsForAddedItem(item);
/* 542 */     if (getItemCount() > this.maximumItemCount) {
/* 543 */       XYDataItem removed = (XYDataItem)this.data.remove(0);
/* 544 */       updateBoundsForRemovedItem(removed);
/*     */     }
/* 546 */     if (notify) {
/* 547 */       fireSeriesChanged();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void delete(int start, int end)
/*     */   {
/* 559 */     this.data.subList(start, end + 1).clear();
/* 560 */     findBoundsByIteration();
/* 561 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XYDataItem remove(int index)
/*     */   {
/* 573 */     XYDataItem removed = (XYDataItem)this.data.remove(index);
/* 574 */     updateBoundsForRemovedItem(removed);
/* 575 */     fireSeriesChanged();
/* 576 */     return removed;
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
/*     */   public XYDataItem remove(Number x)
/*     */   {
/* 590 */     return remove(indexOf(x));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 598 */     if (this.data.size() > 0) {
/* 599 */       this.data.clear();
/* 600 */       this.minX = NaN.0D;
/* 601 */       this.maxX = NaN.0D;
/* 602 */       this.minY = NaN.0D;
/* 603 */       this.maxY = NaN.0D;
/* 604 */       fireSeriesChanged();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XYDataItem getDataItem(int index)
/*     */   {
/* 616 */     return (XYDataItem)this.data.get(index);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getX(int index)
/*     */   {
/* 627 */     return getDataItem(index).getX();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getY(int index)
/*     */   {
/* 638 */     return getDataItem(index).getY();
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
/*     */   public void update(int index, Number y)
/*     */   {
/* 652 */     XYDataItem item = getDataItem(index);
/*     */     
/*     */ 
/* 655 */     boolean iterate = false;
/* 656 */     double oldY = item.getYValue();
/* 657 */     if (!Double.isNaN(oldY)) {
/* 658 */       iterate = (oldY <= this.minY) || (oldY >= this.maxY);
/*     */     }
/* 660 */     item.setY(y);
/*     */     
/* 662 */     if (iterate) {
/* 663 */       findBoundsByIteration();
/*     */     }
/* 665 */     else if (y != null) {
/* 666 */       double yy = y.doubleValue();
/* 667 */       this.minY = minIgnoreNaN(this.minY, yy);
/* 668 */       this.maxY = maxIgnoreNaN(this.maxY, yy);
/*     */     }
/* 670 */     fireSeriesChanged();
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
/*     */   private double minIgnoreNaN(double a, double b)
/*     */   {
/* 683 */     if (Double.isNaN(a)) {
/* 684 */       return b;
/*     */     }
/*     */     
/* 687 */     if (Double.isNaN(b)) {
/* 688 */       return a;
/*     */     }
/*     */     
/* 691 */     return Math.min(a, b);
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
/*     */   private double maxIgnoreNaN(double a, double b)
/*     */   {
/* 706 */     if (Double.isNaN(a)) {
/* 707 */       return b;
/*     */     }
/*     */     
/* 710 */     if (Double.isNaN(b)) {
/* 711 */       return a;
/*     */     }
/*     */     
/* 714 */     return Math.max(a, b);
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
/*     */   public void updateByIndex(int index, Number y)
/*     */   {
/* 729 */     update(index, y);
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
/*     */   public void update(Number x, Number y)
/*     */   {
/* 742 */     int index = indexOf(x);
/* 743 */     if (index < 0) {
/* 744 */       throw new SeriesException("No observation for x = " + x);
/*     */     }
/*     */     
/* 747 */     updateByIndex(index, y);
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
/*     */   public XYDataItem addOrUpdate(double x, double y)
/*     */   {
/* 763 */     return addOrUpdate(new Double(x), new Double(y));
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
/*     */   public XYDataItem addOrUpdate(Number x, Number y)
/*     */   {
/* 777 */     if (x == null) {
/* 778 */       throw new IllegalArgumentException("Null 'x' argument.");
/*     */     }
/* 780 */     if (this.allowDuplicateXValues) {
/* 781 */       add(x, y);
/* 782 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 786 */     XYDataItem overwritten = null;
/* 787 */     int index = indexOf(x);
/* 788 */     if (index >= 0) {
/* 789 */       XYDataItem existing = (XYDataItem)this.data.get(index);
/*     */       try {
/* 791 */         overwritten = (XYDataItem)existing.clone();
/*     */       }
/*     */       catch (CloneNotSupportedException e) {
/* 794 */         throw new SeriesException("Couldn't clone XYDataItem!");
/*     */       }
/*     */       
/* 797 */       boolean iterate = false;
/* 798 */       double oldY = existing.getYValue();
/* 799 */       if (!Double.isNaN(oldY)) {
/* 800 */         iterate = (oldY <= this.minY) || (oldY >= this.maxY);
/*     */       }
/* 802 */       existing.setY(y);
/*     */       
/* 804 */       if (iterate) {
/* 805 */         findBoundsByIteration();
/*     */       }
/* 807 */       else if (y != null) {
/* 808 */         double yy = y.doubleValue();
/* 809 */         this.minY = minIgnoreNaN(this.minY, yy);
/* 810 */         this.maxY = minIgnoreNaN(this.maxY, yy);
/*     */       }
/*     */       
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/* 818 */       XYDataItem item = new XYDataItem(x, y);
/* 819 */       if (this.autoSort) {
/* 820 */         this.data.add(-index - 1, item);
/*     */       }
/*     */       else {
/* 823 */         this.data.add(item);
/*     */       }
/* 825 */       updateBoundsForAddedItem(item);
/*     */       
/*     */ 
/* 828 */       if (getItemCount() > this.maximumItemCount) {
/* 829 */         XYDataItem removed = (XYDataItem)this.data.remove(0);
/* 830 */         updateBoundsForRemovedItem(removed);
/*     */       }
/*     */     }
/* 833 */     fireSeriesChanged();
/* 834 */     return overwritten;
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
/*     */   public int indexOf(Number x)
/*     */   {
/* 848 */     if (this.autoSort) {
/* 849 */       return Collections.binarySearch(this.data, new XYDataItem(x, null));
/*     */     }
/*     */     
/* 852 */     for (int i = 0; i < this.data.size(); i++) {
/* 853 */       XYDataItem item = (XYDataItem)this.data.get(i);
/* 854 */       if (item.getX().equals(x)) {
/* 855 */         return i;
/*     */       }
/*     */     }
/* 858 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[][] toArray()
/*     */   {
/* 870 */     int itemCount = getItemCount();
/* 871 */     double[][] result = new double[2][itemCount];
/* 872 */     for (int i = 0; i < itemCount; i++) {
/* 873 */       result[0][i] = getX(i).doubleValue();
/* 874 */       Number y = getY(i);
/* 875 */       if (y != null) {
/* 876 */         result[1][i] = y.doubleValue();
/*     */       }
/*     */       else {
/* 879 */         result[1][i] = NaN.0D;
/*     */       }
/*     */     }
/* 882 */     return result;
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
/* 893 */     XYSeries clone = (XYSeries)super.clone();
/* 894 */     clone.data = ((List)ObjectUtilities.deepClone(this.data));
/* 895 */     return clone;
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
/*     */   public XYSeries createCopy(int start, int end)
/*     */     throws CloneNotSupportedException
/*     */   {
/* 911 */     XYSeries copy = (XYSeries)super.clone();
/* 912 */     copy.data = new ArrayList();
/* 913 */     if (this.data.size() > 0) {
/* 914 */       for (int index = start; index <= end; index++) {
/* 915 */         XYDataItem item = (XYDataItem)this.data.get(index);
/* 916 */         XYDataItem clone = (XYDataItem)item.clone();
/*     */         try {
/* 918 */           copy.add(clone);
/*     */         }
/*     */         catch (SeriesException e) {
/* 921 */           System.err.println("Unable to add cloned data item.");
/*     */         }
/*     */       }
/*     */     }
/* 925 */     return copy;
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
/* 938 */     if (obj == this) {
/* 939 */       return true;
/*     */     }
/* 941 */     if (!(obj instanceof XYSeries)) {
/* 942 */       return false;
/*     */     }
/* 944 */     if (!super.equals(obj)) {
/* 945 */       return false;
/*     */     }
/* 947 */     XYSeries that = (XYSeries)obj;
/* 948 */     if (this.maximumItemCount != that.maximumItemCount) {
/* 949 */       return false;
/*     */     }
/* 951 */     if (this.autoSort != that.autoSort) {
/* 952 */       return false;
/*     */     }
/* 954 */     if (this.allowDuplicateXValues != that.allowDuplicateXValues) {
/* 955 */       return false;
/*     */     }
/* 957 */     if (!ObjectUtilities.equal(this.data, that.data)) {
/* 958 */       return false;
/*     */     }
/* 960 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 969 */     int result = super.hashCode();
/*     */     
/*     */ 
/* 972 */     int count = getItemCount();
/* 973 */     if (count > 0) {
/* 974 */       XYDataItem item = getDataItem(0);
/* 975 */       result = 29 * result + item.hashCode();
/*     */     }
/* 977 */     if (count > 1) {
/* 978 */       XYDataItem item = getDataItem(count - 1);
/* 979 */       result = 29 * result + item.hashCode();
/*     */     }
/* 981 */     if (count > 2) {
/* 982 */       XYDataItem item = getDataItem(count / 2);
/* 983 */       result = 29 * result + item.hashCode();
/*     */     }
/* 985 */     result = 29 * result + this.maximumItemCount;
/* 986 */     result = 29 * result + (this.autoSort ? 1 : 0);
/* 987 */     result = 29 * result + (this.allowDuplicateXValues ? 1 : 0);
/* 988 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/XYSeries.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */