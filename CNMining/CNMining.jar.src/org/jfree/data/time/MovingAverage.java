/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.data.xy.XYSeries;
/*     */ import org.jfree.data.xy.XYSeriesCollection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MovingAverage
/*     */ {
/*     */   public static TimeSeriesCollection createMovingAverage(TimeSeriesCollection source, String suffix, int periodCount, int skip)
/*     */   {
/*  77 */     if (source == null) {
/*  78 */       throw new IllegalArgumentException("Null 'source' argument.");
/*     */     }
/*  80 */     if (periodCount < 1) {
/*  81 */       throw new IllegalArgumentException("periodCount must be greater than or equal to 1.");
/*     */     }
/*     */     
/*     */ 
/*  85 */     TimeSeriesCollection result = new TimeSeriesCollection();
/*  86 */     for (int i = 0; i < source.getSeriesCount(); i++) {
/*  87 */       TimeSeries sourceSeries = source.getSeries(i);
/*  88 */       TimeSeries maSeries = createMovingAverage(sourceSeries, sourceSeries.getKey() + suffix, periodCount, skip);
/*     */       
/*  90 */       result.addSeries(maSeries);
/*     */     }
/*  92 */     return result;
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
/*     */   public static TimeSeries createMovingAverage(TimeSeries source, String name, int periodCount, int skip)
/*     */   {
/* 112 */     if (source == null) {
/* 113 */       throw new IllegalArgumentException("Null source.");
/*     */     }
/* 115 */     if (periodCount < 1) {
/* 116 */       throw new IllegalArgumentException("periodCount must be greater than or equal to 1.");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 121 */     TimeSeries result = new TimeSeries(name);
/*     */     
/* 123 */     if (source.getItemCount() > 0)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 128 */       long firstSerial = source.getDataItem(0).getPeriod().getSerialIndex() + skip;
/*     */       
/*     */ 
/* 131 */       for (int i = source.getItemCount() - 1; i >= 0; i--)
/*     */       {
/*     */ 
/* 134 */         TimeSeriesDataItem current = source.getDataItem(i);
/* 135 */         RegularTimePeriod period = current.getPeriod();
/* 136 */         long serial = period.getSerialIndex();
/*     */         
/* 138 */         if (serial >= firstSerial)
/*     */         {
/* 140 */           int n = 0;
/* 141 */           double sum = 0.0D;
/* 142 */           long serialLimit = period.getSerialIndex() - periodCount;
/* 143 */           int offset = 0;
/* 144 */           boolean finished = false;
/*     */           
/* 146 */           while ((offset < periodCount) && (!finished)) {
/* 147 */             if (i - offset >= 0) {
/* 148 */               TimeSeriesDataItem item = source.getDataItem(i - offset);
/*     */               
/* 150 */               RegularTimePeriod p = item.getPeriod();
/* 151 */               Number v = item.getValue();
/* 152 */               long currentIndex = p.getSerialIndex();
/* 153 */               if (currentIndex > serialLimit) {
/* 154 */                 if (v != null) {
/* 155 */                   sum += v.doubleValue();
/* 156 */                   n += 1;
/*     */                 }
/*     */               }
/*     */               else {
/* 160 */                 finished = true;
/*     */               }
/*     */             }
/* 163 */             offset += 1;
/*     */           }
/* 165 */           if (n > 0) {
/* 166 */             result.add(period, sum / n);
/*     */           }
/*     */           else {
/* 169 */             result.add(period, null);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 176 */     return result;
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
/*     */   public static TimeSeries createPointMovingAverage(TimeSeries source, String name, int pointCount)
/*     */   {
/* 198 */     if (source == null) {
/* 199 */       throw new IllegalArgumentException("Null 'source'.");
/*     */     }
/* 201 */     if (pointCount < 2) {
/* 202 */       throw new IllegalArgumentException("periodCount must be greater than or equal to 2.");
/*     */     }
/*     */     
/*     */ 
/* 206 */     TimeSeries result = new TimeSeries(name);
/* 207 */     double rollingSumForPeriod = 0.0D;
/* 208 */     for (int i = 0; i < source.getItemCount(); i++)
/*     */     {
/* 210 */       TimeSeriesDataItem current = source.getDataItem(i);
/* 211 */       RegularTimePeriod period = current.getPeriod();
/* 212 */       rollingSumForPeriod += current.getValue().doubleValue();
/*     */       
/* 214 */       if (i > pointCount - 1)
/*     */       {
/* 216 */         TimeSeriesDataItem startOfMovingAvg = source.getDataItem(i - pointCount);
/*     */         
/* 218 */         rollingSumForPeriod -= startOfMovingAvg.getValue().doubleValue();
/*     */         
/* 220 */         result.add(period, rollingSumForPeriod / pointCount);
/*     */       }
/* 222 */       else if (i == pointCount - 1) {
/* 223 */         result.add(period, rollingSumForPeriod / pointCount);
/*     */       }
/*     */     }
/* 226 */     return result;
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
/*     */   public static XYDataset createMovingAverage(XYDataset source, String suffix, long period, long skip)
/*     */   {
/* 244 */     return createMovingAverage(source, suffix, period, skip);
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
/*     */   public static XYDataset createMovingAverage(XYDataset source, String suffix, double period, double skip)
/*     */   {
/* 265 */     if (source == null) {
/* 266 */       throw new IllegalArgumentException("Null source (XYDataset).");
/*     */     }
/*     */     
/* 269 */     XYSeriesCollection result = new XYSeriesCollection();
/*     */     
/* 271 */     for (int i = 0; i < source.getSeriesCount(); i++) {
/* 272 */       XYSeries s = createMovingAverage(source, i, source.getSeriesKey(i) + suffix, period, skip);
/*     */       
/* 274 */       result.addSeries(s);
/*     */     }
/*     */     
/* 277 */     return result;
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
/*     */   public static XYSeries createMovingAverage(XYDataset source, int series, String name, double period, double skip)
/*     */   {
/* 296 */     if (source == null) {
/* 297 */       throw new IllegalArgumentException("Null source (XYDataset).");
/*     */     }
/* 299 */     if (period < Double.MIN_VALUE) {
/* 300 */       throw new IllegalArgumentException("period must be positive.");
/*     */     }
/* 302 */     if (skip < 0.0D) {
/* 303 */       throw new IllegalArgumentException("skip must be >= 0.0.");
/*     */     }
/*     */     
/*     */ 
/* 307 */     XYSeries result = new XYSeries(name);
/*     */     
/* 309 */     if (source.getItemCount(series) > 0)
/*     */     {
/*     */ 
/*     */ 
/* 313 */       double first = source.getXValue(series, 0) + skip;
/*     */       
/* 315 */       for (int i = source.getItemCount(series) - 1; i >= 0; i--)
/*     */       {
/*     */ 
/* 318 */         double x = source.getXValue(series, i);
/*     */         
/* 320 */         if (x >= first)
/*     */         {
/* 322 */           int n = 0;
/* 323 */           double sum = 0.0D;
/* 324 */           double limit = x - period;
/* 325 */           int offset = 0;
/* 326 */           boolean finished = false;
/*     */           
/* 328 */           while (!finished) {
/* 329 */             if (i - offset >= 0) {
/* 330 */               double xx = source.getXValue(series, i - offset);
/* 331 */               Number yy = source.getY(series, i - offset);
/* 332 */               if (xx > limit) {
/* 333 */                 if (yy != null) {
/* 334 */                   sum += yy.doubleValue();
/* 335 */                   n += 1;
/*     */                 }
/*     */               }
/*     */               else {
/* 339 */                 finished = true;
/*     */               }
/*     */             }
/*     */             else {
/* 343 */               finished = true;
/*     */             }
/* 345 */             offset += 1;
/*     */           }
/* 347 */           if (n > 0) {
/* 348 */             result.add(x, sum / n);
/*     */           }
/*     */           else {
/* 351 */             result.add(x, null);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 358 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/MovingAverage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */