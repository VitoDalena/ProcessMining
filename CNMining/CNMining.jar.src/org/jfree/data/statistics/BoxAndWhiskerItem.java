/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
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
/*     */ public class BoxAndWhiskerItem
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7329649623148167423L;
/*     */   private Number mean;
/*     */   private Number median;
/*     */   private Number q1;
/*     */   private Number q3;
/*     */   private Number minRegularValue;
/*     */   private Number maxRegularValue;
/*     */   private Number minOutlier;
/*     */   private Number maxOutlier;
/*     */   private List outliers;
/*     */   
/*     */   public BoxAndWhiskerItem(Number mean, Number median, Number q1, Number q3, Number minRegularValue, Number maxRegularValue, Number minOutlier, Number maxOutlier, List outliers)
/*     */   {
/* 114 */     this.mean = mean;
/* 115 */     this.median = median;
/* 116 */     this.q1 = q1;
/* 117 */     this.q3 = q3;
/* 118 */     this.minRegularValue = minRegularValue;
/* 119 */     this.maxRegularValue = maxRegularValue;
/* 120 */     this.minOutlier = minOutlier;
/* 121 */     this.maxOutlier = maxOutlier;
/* 122 */     this.outliers = outliers;
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
/*     */   public BoxAndWhiskerItem(double mean, double median, double q1, double q3, double minRegularValue, double maxRegularValue, double minOutlier, double maxOutlier, List outliers)
/*     */   {
/* 146 */     this(new Double(mean), new Double(median), new Double(q1), new Double(q3), new Double(minRegularValue), new Double(maxRegularValue), new Double(minOutlier), new Double(maxOutlier), outliers);
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
/*     */   public Number getMean()
/*     */   {
/* 159 */     return this.mean;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getMedian()
/*     */   {
/* 168 */     return this.median;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getQ1()
/*     */   {
/* 177 */     return this.q1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getQ3()
/*     */   {
/* 186 */     return this.q3;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getMinRegularValue()
/*     */   {
/* 195 */     return this.minRegularValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getMaxRegularValue()
/*     */   {
/* 204 */     return this.maxRegularValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getMinOutlier()
/*     */   {
/* 213 */     return this.minOutlier;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getMaxOutlier()
/*     */   {
/* 222 */     return this.maxOutlier;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getOutliers()
/*     */   {
/* 231 */     if (this.outliers == null) {
/* 232 */       return null;
/*     */     }
/* 234 */     return Collections.unmodifiableList(this.outliers);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 244 */     return super.toString() + "[mean=" + this.mean + ",median=" + this.median + ",q1=" + this.q1 + ",q3=" + this.q3 + "]";
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
/* 257 */     if (obj == this) {
/* 258 */       return true;
/*     */     }
/* 260 */     if (!(obj instanceof BoxAndWhiskerItem)) {
/* 261 */       return false;
/*     */     }
/* 263 */     BoxAndWhiskerItem that = (BoxAndWhiskerItem)obj;
/* 264 */     if (!ObjectUtilities.equal(this.mean, that.mean)) {
/* 265 */       return false;
/*     */     }
/* 267 */     if (!ObjectUtilities.equal(this.median, that.median)) {
/* 268 */       return false;
/*     */     }
/* 270 */     if (!ObjectUtilities.equal(this.q1, that.q1)) {
/* 271 */       return false;
/*     */     }
/* 273 */     if (!ObjectUtilities.equal(this.q3, that.q3)) {
/* 274 */       return false;
/*     */     }
/* 276 */     if (!ObjectUtilities.equal(this.minRegularValue, that.minRegularValue))
/*     */     {
/* 278 */       return false;
/*     */     }
/* 280 */     if (!ObjectUtilities.equal(this.maxRegularValue, that.maxRegularValue))
/*     */     {
/* 282 */       return false;
/*     */     }
/* 284 */     if (!ObjectUtilities.equal(this.minOutlier, that.minOutlier)) {
/* 285 */       return false;
/*     */     }
/* 287 */     if (!ObjectUtilities.equal(this.maxOutlier, that.maxOutlier)) {
/* 288 */       return false;
/*     */     }
/* 290 */     if (!ObjectUtilities.equal(this.outliers, that.outliers)) {
/* 291 */       return false;
/*     */     }
/* 293 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/statistics/BoxAndWhiskerItem.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */