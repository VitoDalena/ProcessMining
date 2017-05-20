/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public class MeanAndStandardDeviation
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7413468697315721515L;
/*     */   private Number mean;
/*     */   private Number standardDeviation;
/*     */   
/*     */   public MeanAndStandardDeviation(double mean, double standardDeviation)
/*     */   {
/*  73 */     this(new Double(mean), new Double(standardDeviation));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MeanAndStandardDeviation(Number mean, Number standardDeviation)
/*     */   {
/*  84 */     this.mean = mean;
/*  85 */     this.standardDeviation = standardDeviation;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getMean()
/*     */   {
/*  94 */     return this.mean;
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
/*     */   public double getMeanValue()
/*     */   {
/* 108 */     double result = NaN.0D;
/* 109 */     if (this.mean != null) {
/* 110 */       result = this.mean.doubleValue();
/*     */     }
/* 112 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getStandardDeviation()
/*     */   {
/* 121 */     return this.standardDeviation;
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
/*     */   public double getStandardDeviationValue()
/*     */   {
/* 134 */     double result = NaN.0D;
/* 135 */     if (this.standardDeviation != null) {
/* 136 */       result = this.standardDeviation.doubleValue();
/*     */     }
/* 138 */     return result;
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
/* 149 */     if (obj == this) {
/* 150 */       return true;
/*     */     }
/* 152 */     if (!(obj instanceof MeanAndStandardDeviation)) {
/* 153 */       return false;
/*     */     }
/* 155 */     MeanAndStandardDeviation that = (MeanAndStandardDeviation)obj;
/* 156 */     if (!ObjectUtilities.equal(this.mean, that.mean)) {
/* 157 */       return false;
/*     */     }
/* 159 */     if (!ObjectUtilities.equal(this.standardDeviation, that.standardDeviation))
/*     */     {
/*     */ 
/* 162 */       return false;
/*     */     }
/* 164 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 175 */     return "[" + this.mean + ", " + this.standardDeviation + "]";
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/statistics/MeanAndStandardDeviation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */