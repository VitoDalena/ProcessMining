/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.data.DomainInfo;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.general.DatasetChangeEvent;
/*     */ import org.jfree.data.general.DatasetChangeListener;
/*     */ import org.jfree.data.general.DatasetUtilities;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IntervalXYDelegate
/*     */   implements DatasetChangeListener, DomainInfo, Serializable, Cloneable, PublicCloneable
/*     */ {
/*     */   private static final long serialVersionUID = -685166711639592857L;
/*     */   private XYDataset dataset;
/*     */   private boolean autoWidth;
/*     */   private double intervalPositionFactor;
/*     */   private double fixedIntervalWidth;
/*     */   private double autoIntervalWidth;
/*     */   
/*     */   public IntervalXYDelegate(XYDataset dataset)
/*     */   {
/* 121 */     this(dataset, true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IntervalXYDelegate(XYDataset dataset, boolean autoWidth)
/*     */   {
/* 132 */     if (dataset == null) {
/* 133 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*     */     }
/* 135 */     this.dataset = dataset;
/* 136 */     this.autoWidth = autoWidth;
/* 137 */     this.intervalPositionFactor = 0.5D;
/* 138 */     this.autoIntervalWidth = Double.POSITIVE_INFINITY;
/* 139 */     this.fixedIntervalWidth = 1.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAutoWidth()
/*     */   {
/* 149 */     return this.autoWidth;
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
/*     */   public void setAutoWidth(boolean b)
/*     */   {
/* 164 */     this.autoWidth = b;
/* 165 */     if (b) {
/* 166 */       this.autoIntervalWidth = recalculateInterval();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getIntervalPositionFactor()
/*     */   {
/* 176 */     return this.intervalPositionFactor;
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
/*     */   public void setIntervalPositionFactor(double d)
/*     */   {
/* 196 */     if ((d < 0.0D) || (1.0D < d)) {
/* 197 */       throw new IllegalArgumentException("Argument 'd' outside valid range.");
/*     */     }
/*     */     
/* 200 */     this.intervalPositionFactor = d;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getFixedIntervalWidth()
/*     */   {
/* 209 */     return this.fixedIntervalWidth;
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
/*     */   public void setFixedIntervalWidth(double w)
/*     */   {
/* 224 */     if (w < 0.0D) {
/* 225 */       throw new IllegalArgumentException("Negative 'w' argument.");
/*     */     }
/* 227 */     this.fixedIntervalWidth = w;
/* 228 */     this.autoWidth = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getIntervalWidth()
/*     */   {
/* 239 */     if ((isAutoWidth()) && (!Double.isInfinite(this.autoIntervalWidth)))
/*     */     {
/*     */ 
/* 242 */       return this.autoIntervalWidth;
/*     */     }
/*     */     
/*     */ 
/* 246 */     return this.fixedIntervalWidth;
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
/*     */   public Number getStartX(int series, int item)
/*     */   {
/* 261 */     Number startX = null;
/* 262 */     Number x = this.dataset.getX(series, item);
/* 263 */     if (x != null) {
/* 264 */       startX = new Double(x.doubleValue() - getIntervalPositionFactor() * getIntervalWidth());
/*     */     }
/*     */     
/* 267 */     return startX;
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
/*     */   public double getStartXValue(int series, int item)
/*     */   {
/* 281 */     return this.dataset.getXValue(series, item) - getIntervalPositionFactor() * getIntervalWidth();
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
/*     */   public Number getEndX(int series, int item)
/*     */   {
/* 296 */     Number endX = null;
/* 297 */     Number x = this.dataset.getX(series, item);
/* 298 */     if (x != null) {
/* 299 */       endX = new Double(x.doubleValue() + (1.0D - getIntervalPositionFactor()) * getIntervalWidth());
/*     */     }
/*     */     
/* 302 */     return endX;
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
/*     */   public double getEndXValue(int series, int item)
/*     */   {
/* 316 */     return this.dataset.getXValue(series, item) + (1.0D - getIntervalPositionFactor()) * getIntervalWidth();
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
/*     */   public double getDomainLowerBound(boolean includeInterval)
/*     */   {
/* 329 */     double result = NaN.0D;
/* 330 */     Range r = getDomainBounds(includeInterval);
/* 331 */     if (r != null) {
/* 332 */       result = r.getLowerBound();
/*     */     }
/* 334 */     return result;
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
/* 346 */     double result = NaN.0D;
/* 347 */     Range r = getDomainBounds(includeInterval);
/* 348 */     if (r != null) {
/* 349 */       result = r.getUpperBound();
/*     */     }
/* 351 */     return result;
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
/*     */   public Range getDomainBounds(boolean includeInterval)
/*     */   {
/* 366 */     Range range = DatasetUtilities.findDomainBounds(this.dataset, false);
/* 367 */     if ((includeInterval) && (range != null)) {
/* 368 */       double lowerAdj = getIntervalWidth() * getIntervalPositionFactor();
/* 369 */       double upperAdj = getIntervalWidth() - lowerAdj;
/* 370 */       range = new Range(range.getLowerBound() - lowerAdj, range.getUpperBound() + upperAdj);
/*     */     }
/*     */     
/* 373 */     return range;
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
/*     */   public void datasetChanged(DatasetChangeEvent e)
/*     */   {
/* 388 */     if (this.autoWidth) {
/* 389 */       this.autoIntervalWidth = recalculateInterval();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double recalculateInterval()
/*     */   {
/* 399 */     double result = Double.POSITIVE_INFINITY;
/* 400 */     int seriesCount = this.dataset.getSeriesCount();
/* 401 */     for (int series = 0; series < seriesCount; series++) {
/* 402 */       result = Math.min(result, calculateIntervalForSeries(series));
/*     */     }
/* 404 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double calculateIntervalForSeries(int series)
/*     */   {
/* 415 */     double result = Double.POSITIVE_INFINITY;
/* 416 */     int itemCount = this.dataset.getItemCount(series);
/* 417 */     if (itemCount > 1) {
/* 418 */       double prev = this.dataset.getXValue(series, 0);
/* 419 */       for (int item = 1; item < itemCount; item++) {
/* 420 */         double x = this.dataset.getXValue(series, item);
/* 421 */         result = Math.min(result, x - prev);
/* 422 */         prev = x;
/*     */       }
/*     */     }
/* 425 */     return result;
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 440 */     if (obj == this) {
/* 441 */       return true;
/*     */     }
/* 443 */     if (!(obj instanceof IntervalXYDelegate)) {
/* 444 */       return false;
/*     */     }
/* 446 */     IntervalXYDelegate that = (IntervalXYDelegate)obj;
/* 447 */     if (this.autoWidth != that.autoWidth) {
/* 448 */       return false;
/*     */     }
/* 450 */     if (this.intervalPositionFactor != that.intervalPositionFactor) {
/* 451 */       return false;
/*     */     }
/* 453 */     if (this.fixedIntervalWidth != that.fixedIntervalWidth) {
/* 454 */       return false;
/*     */     }
/* 456 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 465 */     return super.clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 474 */     int hash = 5;
/* 475 */     hash = HashUtilities.hashCode(hash, this.autoWidth);
/* 476 */     hash = HashUtilities.hashCode(hash, this.intervalPositionFactor);
/* 477 */     hash = HashUtilities.hashCode(hash, this.fixedIntervalWidth);
/* 478 */     return hash;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/IntervalXYDelegate.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */