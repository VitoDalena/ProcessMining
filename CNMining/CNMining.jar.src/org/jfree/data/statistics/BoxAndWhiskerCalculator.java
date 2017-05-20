/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BoxAndWhiskerCalculator
/*     */ {
/*     */   public static BoxAndWhiskerItem calculateBoxAndWhiskerStatistics(List values)
/*     */   {
/*  74 */     return calculateBoxAndWhiskerStatistics(values, true);
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
/*     */   public static BoxAndWhiskerItem calculateBoxAndWhiskerStatistics(List values, boolean stripNullAndNaNItems)
/*     */   {
/*  95 */     if (values == null) {
/*  96 */       throw new IllegalArgumentException("Null 'values' argument.");
/*     */     }
/*     */     
/*     */     List vlist;
/* 100 */     if (stripNullAndNaNItems) {
/* 101 */       List vlist = new ArrayList(values.size());
/* 102 */       Iterator iterator = values.listIterator();
/* 103 */       while (iterator.hasNext()) {
/* 104 */         Object obj = iterator.next();
/* 105 */         if ((obj instanceof Number)) {
/* 106 */           Number n = (Number)obj;
/* 107 */           double v = n.doubleValue();
/* 108 */           if (!Double.isNaN(v)) {
/* 109 */             vlist.add(n);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/* 115 */       vlist = values;
/*     */     }
/* 117 */     Collections.sort(vlist);
/*     */     
/* 119 */     double mean = Statistics.calculateMean(vlist, false);
/* 120 */     double median = Statistics.calculateMedian(vlist, false);
/* 121 */     double q1 = calculateQ1(vlist);
/* 122 */     double q3 = calculateQ3(vlist);
/*     */     
/* 124 */     double interQuartileRange = q3 - q1;
/*     */     
/* 126 */     double upperOutlierThreshold = q3 + interQuartileRange * 1.5D;
/* 127 */     double lowerOutlierThreshold = q1 - interQuartileRange * 1.5D;
/*     */     
/* 129 */     double upperFaroutThreshold = q3 + interQuartileRange * 2.0D;
/* 130 */     double lowerFaroutThreshold = q1 - interQuartileRange * 2.0D;
/*     */     
/* 132 */     double minRegularValue = Double.POSITIVE_INFINITY;
/* 133 */     double maxRegularValue = Double.NEGATIVE_INFINITY;
/* 134 */     double minOutlier = Double.POSITIVE_INFINITY;
/* 135 */     double maxOutlier = Double.NEGATIVE_INFINITY;
/* 136 */     List outliers = new ArrayList();
/*     */     
/* 138 */     Iterator iterator = vlist.iterator();
/* 139 */     while (iterator.hasNext()) {
/* 140 */       Number number = (Number)iterator.next();
/* 141 */       double value = number.doubleValue();
/* 142 */       if (value > upperOutlierThreshold) {
/* 143 */         outliers.add(number);
/* 144 */         if ((value > maxOutlier) && (value <= upperFaroutThreshold)) {
/* 145 */           maxOutlier = value;
/*     */         }
/*     */       }
/* 148 */       else if (value < lowerOutlierThreshold) {
/* 149 */         outliers.add(number);
/* 150 */         if ((value < minOutlier) && (value >= lowerFaroutThreshold)) {
/* 151 */           minOutlier = value;
/*     */         }
/*     */       }
/*     */       else {
/* 155 */         minRegularValue = Math.min(minRegularValue, value);
/* 156 */         maxRegularValue = Math.max(maxRegularValue, value);
/*     */       }
/* 158 */       minOutlier = Math.min(minOutlier, minRegularValue);
/* 159 */       maxOutlier = Math.max(maxOutlier, maxRegularValue);
/*     */     }
/*     */     
/* 162 */     return new BoxAndWhiskerItem(new Double(mean), new Double(median), new Double(q1), new Double(q3), new Double(minRegularValue), new Double(maxRegularValue), new Double(minOutlier), new Double(maxOutlier), outliers);
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
/*     */   public static double calculateQ1(List values)
/*     */   {
/* 182 */     if (values == null) {
/* 183 */       throw new IllegalArgumentException("Null 'values' argument.");
/*     */     }
/*     */     
/* 186 */     double result = NaN.0D;
/* 187 */     int count = values.size();
/* 188 */     if (count > 0) {
/* 189 */       if (count % 2 == 1) {
/* 190 */         if (count > 1) {
/* 191 */           result = Statistics.calculateMedian(values, 0, count / 2);
/*     */         }
/*     */         else {
/* 194 */           result = Statistics.calculateMedian(values, 0, 0);
/*     */         }
/*     */       }
/*     */       else {
/* 198 */         result = Statistics.calculateMedian(values, 0, count / 2 - 1);
/*     */       }
/*     */     }
/*     */     
/* 202 */     return result;
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
/*     */   public static double calculateQ3(List values)
/*     */   {
/* 217 */     if (values == null) {
/* 218 */       throw new IllegalArgumentException("Null 'values' argument.");
/*     */     }
/* 220 */     double result = NaN.0D;
/* 221 */     int count = values.size();
/* 222 */     if (count > 0) {
/* 223 */       if (count % 2 == 1) {
/* 224 */         if (count > 1) {
/* 225 */           result = Statistics.calculateMedian(values, count / 2, count - 1);
/*     */         }
/*     */         else
/*     */         {
/* 229 */           result = Statistics.calculateMedian(values, 0, 0);
/*     */         }
/*     */       }
/*     */       else {
/* 233 */         result = Statistics.calculateMedian(values, count / 2, count - 1);
/*     */       }
/*     */     }
/*     */     
/* 237 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/statistics/BoxAndWhiskerCalculator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */