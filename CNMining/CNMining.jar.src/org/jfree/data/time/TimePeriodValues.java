/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
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
/*     */ public class TimePeriodValues
/*     */   extends Series
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = -2210593619794989709L;
/*     */   protected static final String DEFAULT_DOMAIN_DESCRIPTION = "Time";
/*     */   protected static final String DEFAULT_RANGE_DESCRIPTION = "Value";
/*     */   private String domain;
/*     */   private String range;
/*     */   private List data;
/*  88 */   private int minStartIndex = -1;
/*     */   
/*     */ 
/*  91 */   private int maxStartIndex = -1;
/*     */   
/*     */ 
/*  94 */   private int minMiddleIndex = -1;
/*     */   
/*     */ 
/*  97 */   private int maxMiddleIndex = -1;
/*     */   
/*     */ 
/* 100 */   private int minEndIndex = -1;
/*     */   
/*     */ 
/* 103 */   private int maxEndIndex = -1;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TimePeriodValues(String name)
/*     */   {
/* 111 */     this(name, "Time", "Value");
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
/*     */   public TimePeriodValues(String name, String domain, String range)
/*     */   {
/* 126 */     super(name);
/* 127 */     this.domain = domain;
/* 128 */     this.range = range;
/* 129 */     this.data = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDomainDescription()
/*     */   {
/* 141 */     return this.domain;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDomainDescription(String description)
/*     */   {
/* 153 */     String old = this.domain;
/* 154 */     this.domain = description;
/* 155 */     firePropertyChange("Domain", old, description);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getRangeDescription()
/*     */   {
/* 167 */     return this.range;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRangeDescription(String description)
/*     */   {
/* 179 */     String old = this.range;
/* 180 */     this.range = description;
/* 181 */     firePropertyChange("Range", old, description);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getItemCount()
/*     */   {
/* 190 */     return this.data.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TimePeriodValue getDataItem(int index)
/*     */   {
/* 202 */     return (TimePeriodValue)this.data.get(index);
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
/*     */   public TimePeriod getTimePeriod(int index)
/*     */   {
/* 216 */     return getDataItem(index).getPeriod();
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
/*     */   public Number getValue(int index)
/*     */   {
/* 230 */     return getDataItem(index).getValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(TimePeriodValue item)
/*     */   {
/* 240 */     if (item == null) {
/* 241 */       throw new IllegalArgumentException("Null item not allowed.");
/*     */     }
/* 243 */     this.data.add(item);
/* 244 */     updateBounds(item.getPeriod(), this.data.size() - 1);
/* 245 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void updateBounds(TimePeriod period, int index)
/*     */   {
/* 256 */     long start = period.getStart().getTime();
/* 257 */     long end = period.getEnd().getTime();
/* 258 */     long middle = start + (end - start) / 2L;
/*     */     
/* 260 */     if (this.minStartIndex >= 0) {
/* 261 */       long minStart = getDataItem(this.minStartIndex).getPeriod().getStart().getTime();
/*     */       
/* 263 */       if (start < minStart) {
/* 264 */         this.minStartIndex = index;
/*     */       }
/*     */     }
/*     */     else {
/* 268 */       this.minStartIndex = index;
/*     */     }
/*     */     
/* 271 */     if (this.maxStartIndex >= 0) {
/* 272 */       long maxStart = getDataItem(this.maxStartIndex).getPeriod().getStart().getTime();
/*     */       
/* 274 */       if (start > maxStart) {
/* 275 */         this.maxStartIndex = index;
/*     */       }
/*     */     }
/*     */     else {
/* 279 */       this.maxStartIndex = index;
/*     */     }
/*     */     
/* 282 */     if (this.minMiddleIndex >= 0) {
/* 283 */       long s = getDataItem(this.minMiddleIndex).getPeriod().getStart().getTime();
/*     */       
/* 285 */       long e = getDataItem(this.minMiddleIndex).getPeriod().getEnd().getTime();
/*     */       
/* 287 */       long minMiddle = s + (e - s) / 2L;
/* 288 */       if (middle < minMiddle) {
/* 289 */         this.minMiddleIndex = index;
/*     */       }
/*     */     }
/*     */     else {
/* 293 */       this.minMiddleIndex = index;
/*     */     }
/*     */     
/* 296 */     if (this.maxMiddleIndex >= 0) {
/* 297 */       long s = getDataItem(this.maxMiddleIndex).getPeriod().getStart().getTime();
/*     */       
/* 299 */       long e = getDataItem(this.maxMiddleIndex).getPeriod().getEnd().getTime();
/*     */       
/* 301 */       long maxMiddle = s + (e - s) / 2L;
/* 302 */       if (middle > maxMiddle) {
/* 303 */         this.maxMiddleIndex = index;
/*     */       }
/*     */     }
/*     */     else {
/* 307 */       this.maxMiddleIndex = index;
/*     */     }
/*     */     
/* 310 */     if (this.minEndIndex >= 0) {
/* 311 */       long minEnd = getDataItem(this.minEndIndex).getPeriod().getEnd().getTime();
/*     */       
/* 313 */       if (end < minEnd) {
/* 314 */         this.minEndIndex = index;
/*     */       }
/*     */     }
/*     */     else {
/* 318 */       this.minEndIndex = index;
/*     */     }
/*     */     
/* 321 */     if (this.maxEndIndex >= 0) {
/* 322 */       long maxEnd = getDataItem(this.maxEndIndex).getPeriod().getEnd().getTime();
/*     */       
/* 324 */       if (end > maxEnd) {
/* 325 */         this.maxEndIndex = index;
/*     */       }
/*     */     }
/*     */     else {
/* 329 */       this.maxEndIndex = index;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void recalculateBounds()
/*     */   {
/* 338 */     this.minStartIndex = -1;
/* 339 */     this.minMiddleIndex = -1;
/* 340 */     this.minEndIndex = -1;
/* 341 */     this.maxStartIndex = -1;
/* 342 */     this.maxMiddleIndex = -1;
/* 343 */     this.maxEndIndex = -1;
/* 344 */     for (int i = 0; i < this.data.size(); i++) {
/* 345 */       TimePeriodValue tpv = (TimePeriodValue)this.data.get(i);
/* 346 */       updateBounds(tpv.getPeriod(), i);
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
/*     */   public void add(TimePeriod period, double value)
/*     */   {
/* 360 */     TimePeriodValue item = new TimePeriodValue(period, value);
/* 361 */     add(item);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(TimePeriod period, Number value)
/*     */   {
/* 372 */     TimePeriodValue item = new TimePeriodValue(period, value);
/* 373 */     add(item);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void update(int index, Number value)
/*     */   {
/* 384 */     TimePeriodValue item = getDataItem(index);
/* 385 */     item.setValue(value);
/* 386 */     fireSeriesChanged();
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
/* 397 */     for (int i = 0; i <= end - start; i++) {
/* 398 */       this.data.remove(start);
/*     */     }
/* 400 */     recalculateBounds();
/* 401 */     fireSeriesChanged();
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
/* 412 */     if (obj == this) {
/* 413 */       return true;
/*     */     }
/* 415 */     if (!(obj instanceof TimePeriodValues)) {
/* 416 */       return false;
/*     */     }
/* 418 */     if (!super.equals(obj)) {
/* 419 */       return false;
/*     */     }
/* 421 */     TimePeriodValues that = (TimePeriodValues)obj;
/* 422 */     if (!ObjectUtilities.equal(getDomainDescription(), that.getDomainDescription()))
/*     */     {
/* 424 */       return false;
/*     */     }
/* 426 */     if (!ObjectUtilities.equal(getRangeDescription(), that.getRangeDescription()))
/*     */     {
/* 428 */       return false;
/*     */     }
/* 430 */     int count = getItemCount();
/* 431 */     if (count != that.getItemCount()) {
/* 432 */       return false;
/*     */     }
/* 434 */     for (int i = 0; i < count; i++) {
/* 435 */       if (!getDataItem(i).equals(that.getDataItem(i))) {
/* 436 */         return false;
/*     */       }
/*     */     }
/* 439 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 449 */     int result = this.domain != null ? this.domain.hashCode() : 0;
/* 450 */     result = 29 * result + (this.range != null ? this.range.hashCode() : 0);
/* 451 */     result = 29 * result + this.data.hashCode();
/* 452 */     result = 29 * result + this.minStartIndex;
/* 453 */     result = 29 * result + this.maxStartIndex;
/* 454 */     result = 29 * result + this.minMiddleIndex;
/* 455 */     result = 29 * result + this.maxMiddleIndex;
/* 456 */     result = 29 * result + this.minEndIndex;
/* 457 */     result = 29 * result + this.maxEndIndex;
/* 458 */     return result;
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
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 477 */     Object clone = createCopy(0, getItemCount() - 1);
/* 478 */     return clone;
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
/*     */   public TimePeriodValues createCopy(int start, int end)
/*     */     throws CloneNotSupportedException
/*     */   {
/* 495 */     TimePeriodValues copy = (TimePeriodValues)super.clone();
/*     */     
/* 497 */     copy.data = new ArrayList();
/* 498 */     if (this.data.size() > 0) {
/* 499 */       for (int index = start; index <= end; index++) {
/* 500 */         TimePeriodValue item = (TimePeriodValue)this.data.get(index);
/* 501 */         TimePeriodValue clone = (TimePeriodValue)item.clone();
/*     */         try {
/* 503 */           copy.add(clone);
/*     */         }
/*     */         catch (SeriesException e) {
/* 506 */           System.err.println("Failed to add cloned item.");
/*     */         }
/*     */       }
/*     */     }
/* 510 */     return copy;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMinStartIndex()
/*     */   {
/* 520 */     return this.minStartIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMaxStartIndex()
/*     */   {
/* 529 */     return this.maxStartIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMinMiddleIndex()
/*     */   {
/* 539 */     return this.minMiddleIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMaxMiddleIndex()
/*     */   {
/* 549 */     return this.maxMiddleIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMinEndIndex()
/*     */   {
/* 558 */     return this.minEndIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMaxEndIndex()
/*     */   {
/* 567 */     return this.maxEndIndex;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/TimePeriodValues.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */