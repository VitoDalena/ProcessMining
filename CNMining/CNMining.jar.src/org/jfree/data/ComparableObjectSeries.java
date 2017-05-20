/*     */ package org.jfree.data;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
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
/*     */ public class ComparableObjectSeries
/*     */   extends Series
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   protected List data;
/*  66 */   private int maximumItemCount = Integer.MAX_VALUE;
/*     */   
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
/*     */ 
/*     */   public ComparableObjectSeries(Comparable key)
/*     */   {
/*  82 */     this(key, true, true);
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
/*     */   public ComparableObjectSeries(Comparable key, boolean autoSort, boolean allowDuplicateXValues)
/*     */   {
/*  97 */     super(key);
/*  98 */     this.data = new ArrayList();
/*  99 */     this.autoSort = autoSort;
/* 100 */     this.allowDuplicateXValues = allowDuplicateXValues;
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
/* 111 */     return this.autoSort;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getAllowDuplicateXValues()
/*     */   {
/* 121 */     return this.allowDuplicateXValues;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getItemCount()
/*     */   {
/* 130 */     return this.data.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMaximumItemCount()
/*     */   {
/* 141 */     return this.maximumItemCount;
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
/* 159 */     this.maximumItemCount = maximum;
/* 160 */     boolean dataRemoved = false;
/* 161 */     while (this.data.size() > maximum) {
/* 162 */       this.data.remove(0);
/* 163 */       dataRemoved = true;
/*     */     }
/* 165 */     if (dataRemoved) {
/* 166 */       fireSeriesChanged();
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
/*     */   protected void add(Comparable x, Object y)
/*     */   {
/* 182 */     add(x, y, true);
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
/*     */   protected void add(Comparable x, Object y, boolean notify)
/*     */   {
/* 200 */     ComparableObjectItem item = new ComparableObjectItem(x, y);
/* 201 */     add(item, notify);
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
/*     */   protected void add(ComparableObjectItem item, boolean notify)
/*     */   {
/* 215 */     if (item == null) {
/* 216 */       throw new IllegalArgumentException("Null 'item' argument.");
/*     */     }
/*     */     
/* 219 */     if (this.autoSort) {
/* 220 */       int index = Collections.binarySearch(this.data, item);
/* 221 */       if (index < 0) {
/* 222 */         this.data.add(-index - 1, item);
/*     */ 
/*     */       }
/* 225 */       else if (this.allowDuplicateXValues)
/*     */       {
/* 227 */         int size = this.data.size();
/*     */         
/* 229 */         while ((index < size) && (item.compareTo(this.data.get(index)) == 0)) {
/* 230 */           index++;
/*     */         }
/* 232 */         if (index < this.data.size()) {
/* 233 */           this.data.add(index, item);
/*     */         }
/*     */         else {
/* 236 */           this.data.add(item);
/*     */         }
/*     */       }
/*     */       else {
/* 240 */         throw new SeriesException("X-value already exists.");
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 245 */       if (!this.allowDuplicateXValues)
/*     */       {
/*     */ 
/* 248 */         int index = indexOf(item.getComparable());
/* 249 */         if (index >= 0) {
/* 250 */           throw new SeriesException("X-value already exists.");
/*     */         }
/*     */       }
/* 253 */       this.data.add(item);
/*     */     }
/* 255 */     if (getItemCount() > this.maximumItemCount) {
/* 256 */       this.data.remove(0);
/*     */     }
/* 258 */     if (notify) {
/* 259 */       fireSeriesChanged();
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
/*     */   public int indexOf(Comparable x)
/*     */   {
/* 274 */     if (this.autoSort) {
/* 275 */       return Collections.binarySearch(this.data, new ComparableObjectItem(x, null));
/*     */     }
/*     */     
/*     */ 
/* 279 */     for (int i = 0; i < this.data.size(); i++) {
/* 280 */       ComparableObjectItem item = (ComparableObjectItem)this.data.get(i);
/*     */       
/* 282 */       if (item.getComparable().equals(x)) {
/* 283 */         return i;
/*     */       }
/*     */     }
/* 286 */     return -1;
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
/*     */   protected void update(Comparable x, Object y)
/*     */   {
/* 300 */     int index = indexOf(x);
/* 301 */     if (index < 0) {
/* 302 */       throw new SeriesException("No observation for x = " + x);
/*     */     }
/*     */     
/* 305 */     ComparableObjectItem item = getDataItem(index);
/* 306 */     item.setObject(y);
/* 307 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void updateByIndex(int index, Object y)
/*     */   {
/* 319 */     ComparableObjectItem item = getDataItem(index);
/* 320 */     item.setObject(y);
/* 321 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected ComparableObjectItem getDataItem(int index)
/*     */   {
/* 332 */     return (ComparableObjectItem)this.data.get(index);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void delete(int start, int end)
/*     */   {
/* 343 */     for (int i = start; i <= end; i++) {
/* 344 */       this.data.remove(start);
/*     */     }
/* 346 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 355 */     if (this.data.size() > 0) {
/* 356 */       this.data.clear();
/* 357 */       fireSeriesChanged();
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
/*     */   protected ComparableObjectItem remove(int index)
/*     */   {
/* 370 */     ComparableObjectItem result = (ComparableObjectItem)this.data.remove(index);
/*     */     
/* 372 */     fireSeriesChanged();
/* 373 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ComparableObjectItem remove(Comparable x)
/*     */   {
/* 385 */     return remove(indexOf(x));
/*     */   }
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
/* 397 */     if (obj == this) {
/* 398 */       return true;
/*     */     }
/* 400 */     if (!(obj instanceof ComparableObjectSeries)) {
/* 401 */       return false;
/*     */     }
/* 403 */     if (!super.equals(obj)) {
/* 404 */       return false;
/*     */     }
/* 406 */     ComparableObjectSeries that = (ComparableObjectSeries)obj;
/* 407 */     if (this.maximumItemCount != that.maximumItemCount) {
/* 408 */       return false;
/*     */     }
/* 410 */     if (this.autoSort != that.autoSort) {
/* 411 */       return false;
/*     */     }
/* 413 */     if (this.allowDuplicateXValues != that.allowDuplicateXValues) {
/* 414 */       return false;
/*     */     }
/* 416 */     if (!ObjectUtilities.equal(this.data, that.data)) {
/* 417 */       return false;
/*     */     }
/* 419 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 428 */     int result = super.hashCode();
/*     */     
/*     */ 
/* 431 */     int count = getItemCount();
/* 432 */     if (count > 0) {
/* 433 */       ComparableObjectItem item = getDataItem(0);
/* 434 */       result = 29 * result + item.hashCode();
/*     */     }
/* 436 */     if (count > 1) {
/* 437 */       ComparableObjectItem item = getDataItem(count - 1);
/* 438 */       result = 29 * result + item.hashCode();
/*     */     }
/* 440 */     if (count > 2) {
/* 441 */       ComparableObjectItem item = getDataItem(count / 2);
/* 442 */       result = 29 * result + item.hashCode();
/*     */     }
/* 444 */     result = 29 * result + this.maximumItemCount;
/* 445 */     result = 29 * result + (this.autoSort ? 1 : 0);
/* 446 */     result = 29 * result + (this.allowDuplicateXValues ? 1 : 0);
/* 447 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/ComparableObjectSeries.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */