/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
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
/*     */ public abstract class Statistics
/*     */ {
/*     */   public static double calculateMean(Number[] values)
/*     */   {
/*  71 */     return calculateMean(values, true);
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
/*     */   public static double calculateMean(Number[] values, boolean includeNullAndNaN)
/*     */   {
/*  90 */     if (values == null) {
/*  91 */       throw new IllegalArgumentException("Null 'values' argument.");
/*     */     }
/*  93 */     double sum = 0.0D;
/*     */     
/*  95 */     int counter = 0;
/*  96 */     for (int i = 0; i < values.length; i++) { double current;
/*     */       double current;
/*  98 */       if (values[i] != null) {
/*  99 */         current = values[i].doubleValue();
/*     */       }
/*     */       else {
/* 102 */         current = NaN.0D;
/*     */       }
/*     */       
/* 105 */       if ((includeNullAndNaN) || (!Double.isNaN(current))) {
/* 106 */         sum += current;
/* 107 */         counter++;
/*     */       }
/*     */     }
/* 110 */     double result = sum / counter;
/* 111 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double calculateMean(Collection values)
/*     */   {
/* 122 */     return calculateMean(values, true);
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
/*     */   public static double calculateMean(Collection values, boolean includeNullAndNaN)
/*     */   {
/* 141 */     if (values == null) {
/* 142 */       throw new IllegalArgumentException("Null 'values' argument.");
/*     */     }
/* 144 */     int count = 0;
/* 145 */     double total = 0.0D;
/* 146 */     Iterator iterator = values.iterator();
/* 147 */     while (iterator.hasNext()) {
/* 148 */       Object object = iterator.next();
/* 149 */       if (object == null) {
/* 150 */         if (includeNullAndNaN) {
/* 151 */           return NaN.0D;
/*     */         }
/*     */         
/*     */       }
/* 155 */       else if ((object instanceof Number)) {
/* 156 */         Number number = (Number)object;
/* 157 */         double value = number.doubleValue();
/* 158 */         if (Double.isNaN(value)) {
/* 159 */           if (includeNullAndNaN) {
/* 160 */             return NaN.0D;
/*     */           }
/*     */         }
/*     */         else {
/* 164 */           total += number.doubleValue();
/* 165 */           count += 1;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 170 */     return total / count;
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
/*     */   public static double calculateMedian(List values)
/*     */   {
/* 185 */     return calculateMedian(values, true);
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
/*     */   public static double calculateMedian(List values, boolean copyAndSort)
/*     */   {
/* 201 */     double result = NaN.0D;
/* 202 */     if (values != null) {
/* 203 */       if (copyAndSort) {
/* 204 */         int itemCount = values.size();
/* 205 */         List copy = new ArrayList(itemCount);
/* 206 */         for (int i = 0; i < itemCount; i++) {
/* 207 */           copy.add(i, values.get(i));
/*     */         }
/* 209 */         Collections.sort(copy);
/* 210 */         values = copy;
/*     */       }
/* 212 */       int count = values.size();
/* 213 */       if (count > 0) {
/* 214 */         if (count % 2 == 1) {
/* 215 */           if (count > 1) {
/* 216 */             Number value = (Number)values.get((count - 1) / 2);
/* 217 */             result = value.doubleValue();
/*     */           }
/*     */           else {
/* 220 */             Number value = (Number)values.get(0);
/* 221 */             result = value.doubleValue();
/*     */           }
/*     */         }
/*     */         else {
/* 225 */           Number value1 = (Number)values.get(count / 2 - 1);
/* 226 */           Number value2 = (Number)values.get(count / 2);
/* 227 */           result = (value1.doubleValue() + value2.doubleValue()) / 2.0D;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 232 */     return result;
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
/*     */   public static double calculateMedian(List values, int start, int end)
/*     */   {
/* 247 */     return calculateMedian(values, start, end, true);
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
/*     */   public static double calculateMedian(List values, int start, int end, boolean copyAndSort)
/*     */   {
/* 266 */     double result = NaN.0D;
/* 267 */     if (copyAndSort) {
/* 268 */       List working = new ArrayList(end - start + 1);
/* 269 */       for (int i = start; i <= end; i++) {
/* 270 */         working.add(values.get(i));
/*     */       }
/* 272 */       Collections.sort(working);
/* 273 */       result = calculateMedian(working, false);
/*     */     }
/*     */     else {
/* 276 */       int count = end - start + 1;
/* 277 */       if (count > 0) {
/* 278 */         if (count % 2 == 1) {
/* 279 */           if (count > 1) {
/* 280 */             Number value = (Number)values.get(start + (count - 1) / 2);
/*     */             
/* 282 */             result = value.doubleValue();
/*     */           }
/*     */           else {
/* 285 */             Number value = (Number)values.get(start);
/* 286 */             result = value.doubleValue();
/*     */           }
/*     */         }
/*     */         else {
/* 290 */           Number value1 = (Number)values.get(start + count / 2 - 1);
/* 291 */           Number value2 = (Number)values.get(start + count / 2);
/* 292 */           result = (value1.doubleValue() + value2.doubleValue()) / 2.0D;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 297 */     return result;
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
/*     */   public static double getStdDev(Number[] data)
/*     */   {
/* 310 */     if (data == null) {
/* 311 */       throw new IllegalArgumentException("Null 'data' array.");
/*     */     }
/* 313 */     if (data.length == 0) {
/* 314 */       throw new IllegalArgumentException("Zero length 'data' array.");
/*     */     }
/* 316 */     double avg = calculateMean(data);
/* 317 */     double sum = 0.0D;
/*     */     
/* 319 */     for (int counter = 0; counter < data.length; counter++) {
/* 320 */       double diff = data[counter].doubleValue() - avg;
/* 321 */       sum += diff * diff;
/*     */     }
/* 323 */     return Math.sqrt(sum / (data.length - 1));
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
/*     */   public static double[] getLinearFit(Number[] xData, Number[] yData)
/*     */   {
/* 337 */     if (xData == null) {
/* 338 */       throw new IllegalArgumentException("Null 'xData' argument.");
/*     */     }
/* 340 */     if (yData == null) {
/* 341 */       throw new IllegalArgumentException("Null 'yData' argument.");
/*     */     }
/* 343 */     if (xData.length != yData.length) {
/* 344 */       throw new IllegalArgumentException("Statistics.getLinearFit(): array lengths must be equal.");
/*     */     }
/*     */     
/*     */ 
/* 348 */     double[] result = new double[2];
/*     */     
/* 350 */     result[1] = getSlope(xData, yData);
/*     */     
/* 352 */     result[0] = (calculateMean(yData) - result[1] * calculateMean(xData));
/*     */     
/* 354 */     return result;
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
/*     */   public static double getSlope(Number[] xData, Number[] yData)
/*     */   {
/* 368 */     if (xData == null) {
/* 369 */       throw new IllegalArgumentException("Null 'xData' argument.");
/*     */     }
/* 371 */     if (yData == null) {
/* 372 */       throw new IllegalArgumentException("Null 'yData' argument.");
/*     */     }
/* 374 */     if (xData.length != yData.length) {
/* 375 */       throw new IllegalArgumentException("Array lengths must be equal.");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 387 */     double sx = 0.0D;double sxx = 0.0D;double sxy = 0.0D;double sy = 0.0D;
/*     */     
/* 389 */     for (int counter = 0; counter < xData.length; counter++) {
/* 390 */       sx += xData[counter].doubleValue();
/* 391 */       sxx += Math.pow(xData[counter].doubleValue(), 2.0D);
/* 392 */       sxy += yData[counter].doubleValue() * xData[counter].doubleValue();
/*     */       
/* 394 */       sy += yData[counter].doubleValue();
/*     */     }
/* 396 */     return (sxy - sx * sy / counter) / (sxx - sx * sx / counter);
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
/*     */   public static double getCorrelation(Number[] data1, Number[] data2)
/*     */   {
/* 414 */     if (data1 == null) {
/* 415 */       throw new IllegalArgumentException("Null 'data1' argument.");
/*     */     }
/* 417 */     if (data2 == null) {
/* 418 */       throw new IllegalArgumentException("Null 'data2' argument.");
/*     */     }
/* 420 */     if (data1.length != data2.length) {
/* 421 */       throw new IllegalArgumentException("'data1' and 'data2' arrays must have same length.");
/*     */     }
/*     */     
/*     */ 
/* 425 */     int n = data1.length;
/* 426 */     double sumX = 0.0D;
/* 427 */     double sumY = 0.0D;
/* 428 */     double sumX2 = 0.0D;
/* 429 */     double sumY2 = 0.0D;
/* 430 */     double sumXY = 0.0D;
/* 431 */     for (int i = 0; i < n; i++) {
/* 432 */       double x = 0.0D;
/* 433 */       if (data1[i] != null) {
/* 434 */         x = data1[i].doubleValue();
/*     */       }
/* 436 */       double y = 0.0D;
/* 437 */       if (data2[i] != null) {
/* 438 */         y = data2[i].doubleValue();
/*     */       }
/* 440 */       sumX += x;
/* 441 */       sumY += y;
/* 442 */       sumXY += x * y;
/* 443 */       sumX2 += x * x;
/* 444 */       sumY2 += y * y;
/*     */     }
/* 446 */     return (n * sumXY - sumX * sumY) / Math.pow((n * sumX2 - sumX * sumX) * (n * sumY2 - sumY * sumY), 0.5D);
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
/*     */   public static double[][] getMovingAverage(Number[] xData, Number[] yData, int period)
/*     */   {
/* 465 */     if (xData.length != yData.length) {
/* 466 */       throw new IllegalArgumentException("Array lengths must be equal.");
/*     */     }
/*     */     
/* 469 */     if (period > xData.length) {
/* 470 */       throw new IllegalArgumentException("Period can't be longer than dataset.");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 475 */     double[][] result = new double[xData.length - period][2];
/* 476 */     for (int i = 0; i < result.length; i++) {
/* 477 */       result[i][0] = xData[(i + period)].doubleValue();
/*     */       
/* 479 */       double sum = 0.0D;
/* 480 */       for (int j = 0; j < period; j++) {
/* 481 */         sum += yData[(i + j)].doubleValue();
/*     */       }
/* 483 */       sum /= period;
/* 484 */       result[i][1] = sum;
/*     */     }
/* 486 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/statistics/Statistics.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */