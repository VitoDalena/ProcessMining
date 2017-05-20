/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.data.DomainInfo;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.xy.AbstractIntervalXYDataset;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
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
/*     */ public class TimePeriodValuesCollection
/*     */   extends AbstractIntervalXYDataset
/*     */   implements IntervalXYDataset, DomainInfo, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3077934065236454199L;
/*     */   private List data;
/*     */   private TimePeriodAnchor xPosition;
/*     */   private boolean domainIsPointsInTime;
/*     */   
/*     */   public TimePeriodValuesCollection()
/*     */   {
/*  96 */     this((TimePeriodValues)null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TimePeriodValuesCollection(TimePeriodValues series)
/*     */   {
/* 106 */     this.data = new ArrayList();
/* 107 */     this.xPosition = TimePeriodAnchor.MIDDLE;
/* 108 */     this.domainIsPointsInTime = false;
/* 109 */     if (series != null) {
/* 110 */       this.data.add(series);
/* 111 */       series.addChangeListener(this);
/*     */     }
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
/* 123 */     return this.xPosition;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setXPosition(TimePeriodAnchor position)
/*     */   {
/* 134 */     if (position == null) {
/* 135 */       throw new IllegalArgumentException("Null 'position' argument.");
/*     */     }
/* 137 */     this.xPosition = position;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesCount()
/*     */   {
/* 146 */     return this.data.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TimePeriodValues getSeries(int series)
/*     */   {
/* 157 */     if ((series < 0) || (series >= getSeriesCount())) {
/* 158 */       throw new IllegalArgumentException("Index 'series' out of range.");
/*     */     }
/* 160 */     return (TimePeriodValues)this.data.get(series);
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
/* 172 */     return getSeries(series).getKey();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addSeries(TimePeriodValues series)
/*     */   {
/* 184 */     if (series == null) {
/* 185 */       throw new IllegalArgumentException("Null 'series' argument.");
/*     */     }
/*     */     
/* 188 */     this.data.add(series);
/* 189 */     series.addChangeListener(this);
/* 190 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeSeries(TimePeriodValues series)
/*     */   {
/* 201 */     if (series == null) {
/* 202 */       throw new IllegalArgumentException("Null 'series' argument.");
/*     */     }
/* 204 */     this.data.remove(series);
/* 205 */     series.removeChangeListener(this);
/* 206 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeSeries(int index)
/*     */   {
/* 216 */     TimePeriodValues series = getSeries(index);
/* 217 */     if (series != null) {
/* 218 */       removeSeries(series);
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
/*     */   public int getItemCount(int series)
/*     */   {
/* 232 */     return getSeries(series).getItemCount();
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
/* 244 */     TimePeriodValues ts = (TimePeriodValues)this.data.get(series);
/* 245 */     TimePeriodValue dp = ts.getDataItem(item);
/* 246 */     TimePeriod period = dp.getPeriod();
/* 247 */     return new Long(getX(period));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private long getX(TimePeriod period)
/*     */   {
/* 259 */     if (this.xPosition == TimePeriodAnchor.START) {
/* 260 */       return period.getStart().getTime();
/*     */     }
/* 262 */     if (this.xPosition == TimePeriodAnchor.MIDDLE) {
/* 263 */       return period.getStart().getTime() / 2L + period.getEnd().getTime() / 2L;
/*     */     }
/*     */     
/* 266 */     if (this.xPosition == TimePeriodAnchor.END) {
/* 267 */       return period.getEnd().getTime();
/*     */     }
/*     */     
/* 270 */     throw new IllegalStateException("TimePeriodAnchor unknown.");
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
/* 284 */     TimePeriodValues ts = (TimePeriodValues)this.data.get(series);
/* 285 */     TimePeriodValue dp = ts.getDataItem(item);
/* 286 */     return new Long(dp.getPeriod().getStart().getTime());
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
/* 298 */     TimePeriodValues ts = (TimePeriodValues)this.data.get(series);
/* 299 */     TimePeriodValue dp = ts.getDataItem(item);
/* 300 */     return new Long(dp.getPeriod().getEnd().getTime());
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
/* 312 */     TimePeriodValues ts = (TimePeriodValues)this.data.get(series);
/* 313 */     TimePeriodValue dp = ts.getDataItem(item);
/* 314 */     return dp.getValue();
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
/* 326 */     return getY(series, item);
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
/* 338 */     return getY(series, item);
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
/* 350 */     double result = NaN.0D;
/* 351 */     Range r = getDomainBounds(includeInterval);
/* 352 */     if (r != null) {
/* 353 */       result = r.getLowerBound();
/*     */     }
/* 355 */     return result;
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
/* 367 */     double result = NaN.0D;
/* 368 */     Range r = getDomainBounds(includeInterval);
/* 369 */     if (r != null) {
/* 370 */       result = r.getUpperBound();
/*     */     }
/* 372 */     return result;
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
/* 384 */     boolean interval = (includeInterval) || (this.domainIsPointsInTime);
/* 385 */     Range result = null;
/* 386 */     Range temp = null;
/* 387 */     Iterator iterator = this.data.iterator();
/* 388 */     while (iterator.hasNext()) {
/* 389 */       TimePeriodValues series = (TimePeriodValues)iterator.next();
/* 390 */       int count = series.getItemCount();
/* 391 */       if (count > 0) {
/* 392 */         TimePeriod start = series.getTimePeriod(series.getMinStartIndex());
/*     */         
/* 394 */         TimePeriod end = series.getTimePeriod(series.getMaxEndIndex());
/* 395 */         if (!interval) {
/* 396 */           if (this.xPosition == TimePeriodAnchor.START) {
/* 397 */             TimePeriod maxStart = series.getTimePeriod(series.getMaxStartIndex());
/*     */             
/* 399 */             temp = new Range(start.getStart().getTime(), maxStart.getStart().getTime());
/*     */ 
/*     */           }
/* 402 */           else if (this.xPosition == TimePeriodAnchor.MIDDLE) {
/* 403 */             TimePeriod minMiddle = series.getTimePeriod(series.getMinMiddleIndex());
/*     */             
/* 405 */             long s1 = minMiddle.getStart().getTime();
/* 406 */             long e1 = minMiddle.getEnd().getTime();
/* 407 */             TimePeriod maxMiddle = series.getTimePeriod(series.getMaxMiddleIndex());
/*     */             
/* 409 */             long s2 = maxMiddle.getStart().getTime();
/* 410 */             long e2 = maxMiddle.getEnd().getTime();
/* 411 */             temp = new Range(s1 + (e1 - s1) / 2L, s2 + (e2 - s2) / 2L);
/*     */ 
/*     */           }
/* 414 */           else if (this.xPosition == TimePeriodAnchor.END) {
/* 415 */             TimePeriod minEnd = series.getTimePeriod(series.getMinEndIndex());
/*     */             
/* 417 */             temp = new Range(minEnd.getEnd().getTime(), end.getEnd().getTime());
/*     */           }
/*     */           
/*     */         }
/*     */         else {
/* 422 */           temp = new Range(start.getStart().getTime(), end.getEnd().getTime());
/*     */         }
/*     */         
/* 425 */         result = Range.combine(result, temp);
/*     */       }
/*     */     }
/* 428 */     return result;
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
/* 439 */     if (obj == this) {
/* 440 */       return true;
/*     */     }
/* 442 */     if (!(obj instanceof TimePeriodValuesCollection)) {
/* 443 */       return false;
/*     */     }
/* 445 */     TimePeriodValuesCollection that = (TimePeriodValuesCollection)obj;
/* 446 */     if (this.domainIsPointsInTime != that.domainIsPointsInTime) {
/* 447 */       return false;
/*     */     }
/* 449 */     if (this.xPosition != that.xPosition) {
/* 450 */       return false;
/*     */     }
/* 452 */     if (!ObjectUtilities.equal(this.data, that.data)) {
/* 453 */       return false;
/*     */     }
/* 455 */     return true;
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
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public boolean getDomainIsPointsInTime()
/*     */   {
/* 473 */     return this.domainIsPointsInTime;
/*     */   }
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
/* 486 */     this.domainIsPointsInTime = flag;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/TimePeriodValuesCollection.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */