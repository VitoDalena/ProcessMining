/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.RangeInfo;
/*     */ import org.jfree.data.xy.AbstractXYDataset;
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
/*     */ public class DefaultBoxAndWhiskerXYDataset
/*     */   extends AbstractXYDataset
/*     */   implements BoxAndWhiskerXYDataset, RangeInfo
/*     */ {
/*     */   private Comparable seriesKey;
/*     */   private List dates;
/*     */   private List items;
/*     */   private Number minimumRangeValue;
/*     */   private Number maximumRangeValue;
/*     */   private Range rangeBounds;
/* 104 */   private double outlierCoefficient = 1.5D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 112 */   private double faroutCoefficient = 2.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultBoxAndWhiskerXYDataset(Comparable seriesKey)
/*     */   {
/* 123 */     this.seriesKey = seriesKey;
/* 124 */     this.dates = new ArrayList();
/* 125 */     this.items = new ArrayList();
/* 126 */     this.minimumRangeValue = null;
/* 127 */     this.maximumRangeValue = null;
/* 128 */     this.rangeBounds = null;
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
/*     */   public double getOutlierCoefficient()
/*     */   {
/* 144 */     return this.outlierCoefficient;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOutlierCoefficient(double outlierCoefficient)
/*     */   {
/* 156 */     this.outlierCoefficient = outlierCoefficient;
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
/*     */   public double getFaroutCoefficient()
/*     */   {
/* 169 */     return this.faroutCoefficient;
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
/*     */   public void setFaroutCoefficient(double faroutCoefficient)
/*     */   {
/* 183 */     if (faroutCoefficient > getOutlierCoefficient()) {
/* 184 */       this.faroutCoefficient = faroutCoefficient;
/*     */     }
/*     */     else {
/* 187 */       throw new IllegalArgumentException("Farout value must be greater than the outlier value, which is currently set at: (" + getOutlierCoefficient() + ")");
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
/*     */   public int getSeriesCount()
/*     */   {
/* 201 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getItemCount(int series)
/*     */   {
/* 212 */     return this.dates.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(Date date, BoxAndWhiskerItem item)
/*     */   {
/* 223 */     this.dates.add(date);
/* 224 */     this.items.add(item);
/* 225 */     if (this.minimumRangeValue == null) {
/* 226 */       this.minimumRangeValue = item.getMinRegularValue();
/*     */ 
/*     */     }
/* 229 */     else if (item.getMinRegularValue().doubleValue() < this.minimumRangeValue.doubleValue())
/*     */     {
/* 231 */       this.minimumRangeValue = item.getMinRegularValue();
/*     */     }
/*     */     
/* 234 */     if (this.maximumRangeValue == null) {
/* 235 */       this.maximumRangeValue = item.getMaxRegularValue();
/*     */ 
/*     */     }
/* 238 */     else if (item.getMaxRegularValue().doubleValue() > this.maximumRangeValue.doubleValue())
/*     */     {
/* 240 */       this.maximumRangeValue = item.getMaxRegularValue();
/*     */     }
/*     */     
/* 243 */     this.rangeBounds = new Range(this.minimumRangeValue.doubleValue(), this.maximumRangeValue.doubleValue());
/*     */     
/* 245 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getSeriesKey(int i)
/*     */   {
/* 256 */     return this.seriesKey;
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
/*     */   public BoxAndWhiskerItem getItem(int series, int item)
/*     */   {
/* 269 */     return (BoxAndWhiskerItem)this.items.get(item);
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
/*     */   public Number getX(int series, int item)
/*     */   {
/* 284 */     return new Long(((Date)this.dates.get(item)).getTime());
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
/*     */   public Date getXDate(int series, int item)
/*     */   {
/* 298 */     return (Date)this.dates.get(item);
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
/*     */   public Number getY(int series, int item)
/*     */   {
/* 313 */     return getMeanValue(series, item);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getMeanValue(int series, int item)
/*     */   {
/* 325 */     Number result = null;
/* 326 */     BoxAndWhiskerItem stats = (BoxAndWhiskerItem)this.items.get(item);
/* 327 */     if (stats != null) {
/* 328 */       result = stats.getMean();
/*     */     }
/* 330 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getMedianValue(int series, int item)
/*     */   {
/* 342 */     Number result = null;
/* 343 */     BoxAndWhiskerItem stats = (BoxAndWhiskerItem)this.items.get(item);
/* 344 */     if (stats != null) {
/* 345 */       result = stats.getMedian();
/*     */     }
/* 347 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getQ1Value(int series, int item)
/*     */   {
/* 359 */     Number result = null;
/* 360 */     BoxAndWhiskerItem stats = (BoxAndWhiskerItem)this.items.get(item);
/* 361 */     if (stats != null) {
/* 362 */       result = stats.getQ1();
/*     */     }
/* 364 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getQ3Value(int series, int item)
/*     */   {
/* 376 */     Number result = null;
/* 377 */     BoxAndWhiskerItem stats = (BoxAndWhiskerItem)this.items.get(item);
/* 378 */     if (stats != null) {
/* 379 */       result = stats.getQ3();
/*     */     }
/* 381 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getMinRegularValue(int series, int item)
/*     */   {
/* 393 */     Number result = null;
/* 394 */     BoxAndWhiskerItem stats = (BoxAndWhiskerItem)this.items.get(item);
/* 395 */     if (stats != null) {
/* 396 */       result = stats.getMinRegularValue();
/*     */     }
/* 398 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getMaxRegularValue(int series, int item)
/*     */   {
/* 410 */     Number result = null;
/* 411 */     BoxAndWhiskerItem stats = (BoxAndWhiskerItem)this.items.get(item);
/* 412 */     if (stats != null) {
/* 413 */       result = stats.getMaxRegularValue();
/*     */     }
/* 415 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getMinOutlier(int series, int item)
/*     */   {
/* 426 */     Number result = null;
/* 427 */     BoxAndWhiskerItem stats = (BoxAndWhiskerItem)this.items.get(item);
/* 428 */     if (stats != null) {
/* 429 */       result = stats.getMinOutlier();
/*     */     }
/* 431 */     return result;
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
/*     */   public Number getMaxOutlier(int series, int item)
/*     */   {
/* 444 */     Number result = null;
/* 445 */     BoxAndWhiskerItem stats = (BoxAndWhiskerItem)this.items.get(item);
/* 446 */     if (stats != null) {
/* 447 */       result = stats.getMaxOutlier();
/*     */     }
/* 449 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getOutliers(int series, int item)
/*     */   {
/* 461 */     List result = null;
/* 462 */     BoxAndWhiskerItem stats = (BoxAndWhiskerItem)this.items.get(item);
/* 463 */     if (stats != null) {
/* 464 */       result = stats.getOutliers();
/*     */     }
/* 466 */     return result;
/*     */   }
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
/* 478 */     double result = NaN.0D;
/* 479 */     if (this.minimumRangeValue != null) {
/* 480 */       result = this.minimumRangeValue.doubleValue();
/*     */     }
/* 482 */     return result;
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
/* 494 */     double result = NaN.0D;
/* 495 */     if (this.maximumRangeValue != null) {
/* 496 */       result = this.maximumRangeValue.doubleValue();
/*     */     }
/* 498 */     return result;
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
/* 510 */     return this.rangeBounds;
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
/* 521 */     if (obj == this) {
/* 522 */       return true;
/*     */     }
/* 524 */     if (!(obj instanceof DefaultBoxAndWhiskerXYDataset)) {
/* 525 */       return false;
/*     */     }
/* 527 */     DefaultBoxAndWhiskerXYDataset that = (DefaultBoxAndWhiskerXYDataset)obj;
/*     */     
/* 529 */     if (!ObjectUtilities.equal(this.seriesKey, that.seriesKey)) {
/* 530 */       return false;
/*     */     }
/* 532 */     if (!this.dates.equals(that.dates)) {
/* 533 */       return false;
/*     */     }
/* 535 */     if (!this.items.equals(that.items)) {
/* 536 */       return false;
/*     */     }
/* 538 */     return true;
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
/* 549 */     DefaultBoxAndWhiskerXYDataset clone = (DefaultBoxAndWhiskerXYDataset)super.clone();
/*     */     
/* 551 */     clone.dates = new ArrayList(this.dates);
/* 552 */     clone.items = new ArrayList(this.items);
/* 553 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/statistics/DefaultBoxAndWhiskerXYDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */