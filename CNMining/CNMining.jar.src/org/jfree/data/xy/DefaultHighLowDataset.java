/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Date;
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
/*     */ public class DefaultHighLowDataset
/*     */   extends AbstractXYDataset
/*     */   implements OHLCDataset, PublicCloneable
/*     */ {
/*     */   private Comparable seriesKey;
/*     */   private Date[] date;
/*     */   private Number[] high;
/*     */   private Number[] low;
/*     */   private Number[] open;
/*     */   private Number[] close;
/*     */   private Number[] volume;
/*     */   
/*     */   public DefaultHighLowDataset(Comparable seriesKey, Date[] date, double[] high, double[] low, double[] open, double[] close, double[] volume)
/*     */   {
/* 104 */     if (seriesKey == null) {
/* 105 */       throw new IllegalArgumentException("Null 'series' argument.");
/*     */     }
/* 107 */     if (date == null) {
/* 108 */       throw new IllegalArgumentException("Null 'date' argument.");
/*     */     }
/* 110 */     this.seriesKey = seriesKey;
/* 111 */     this.date = date;
/* 112 */     this.high = createNumberArray(high);
/* 113 */     this.low = createNumberArray(low);
/* 114 */     this.open = createNumberArray(open);
/* 115 */     this.close = createNumberArray(close);
/* 116 */     this.volume = createNumberArray(volume);
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
/*     */   public Comparable getSeriesKey(int series)
/*     */   {
/* 129 */     return this.seriesKey;
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
/*     */   public Number getX(int series, int item)
/*     */   {
/* 147 */     return new Long(this.date[item].getTime());
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
/*     */   public Date getXDate(int series, int item)
/*     */   {
/* 163 */     return this.date[item];
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
/*     */   public Number getY(int series, int item)
/*     */   {
/* 180 */     return getClose(series, item);
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
/*     */   public Number getHigh(int series, int item)
/*     */   {
/* 194 */     return this.high[item];
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
/*     */   public double getHighValue(int series, int item)
/*     */   {
/* 209 */     double result = NaN.0D;
/* 210 */     Number high = getHigh(series, item);
/* 211 */     if (high != null) {
/* 212 */       result = high.doubleValue();
/*     */     }
/* 214 */     return result;
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
/*     */   public Number getLow(int series, int item)
/*     */   {
/* 228 */     return this.low[item];
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
/*     */   public double getLowValue(int series, int item)
/*     */   {
/* 243 */     double result = NaN.0D;
/* 244 */     Number low = getLow(series, item);
/* 245 */     if (low != null) {
/* 246 */       result = low.doubleValue();
/*     */     }
/* 248 */     return result;
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
/*     */   public Number getOpen(int series, int item)
/*     */   {
/* 262 */     return this.open[item];
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
/*     */   public double getOpenValue(int series, int item)
/*     */   {
/* 277 */     double result = NaN.0D;
/* 278 */     Number open = getOpen(series, item);
/* 279 */     if (open != null) {
/* 280 */       result = open.doubleValue();
/*     */     }
/* 282 */     return result;
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
/*     */   public Number getClose(int series, int item)
/*     */   {
/* 296 */     return this.close[item];
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
/*     */   public double getCloseValue(int series, int item)
/*     */   {
/* 311 */     double result = NaN.0D;
/* 312 */     Number close = getClose(series, item);
/* 313 */     if (close != null) {
/* 314 */       result = close.doubleValue();
/*     */     }
/* 316 */     return result;
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
/*     */   public Number getVolume(int series, int item)
/*     */   {
/* 330 */     return this.volume[item];
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
/*     */   public double getVolumeValue(int series, int item)
/*     */   {
/* 345 */     double result = NaN.0D;
/* 346 */     Number volume = getVolume(series, item);
/* 347 */     if (volume != null) {
/* 348 */       result = volume.doubleValue();
/*     */     }
/* 350 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesCount()
/*     */   {
/* 361 */     return 1;
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
/* 372 */     return this.date.length;
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
/* 383 */     if (obj == this) {
/* 384 */       return true;
/*     */     }
/* 386 */     if (!(obj instanceof DefaultHighLowDataset)) {
/* 387 */       return false;
/*     */     }
/* 389 */     DefaultHighLowDataset that = (DefaultHighLowDataset)obj;
/* 390 */     if (!this.seriesKey.equals(that.seriesKey)) {
/* 391 */       return false;
/*     */     }
/* 393 */     if (!Arrays.equals(this.date, that.date)) {
/* 394 */       return false;
/*     */     }
/* 396 */     if (!Arrays.equals(this.open, that.open)) {
/* 397 */       return false;
/*     */     }
/* 399 */     if (!Arrays.equals(this.high, that.high)) {
/* 400 */       return false;
/*     */     }
/* 402 */     if (!Arrays.equals(this.low, that.low)) {
/* 403 */       return false;
/*     */     }
/* 405 */     if (!Arrays.equals(this.close, that.close)) {
/* 406 */       return false;
/*     */     }
/* 408 */     if (!Arrays.equals(this.volume, that.volume)) {
/* 409 */       return false;
/*     */     }
/* 411 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Number[] createNumberArray(double[] data)
/*     */   {
/* 423 */     Number[] result = new Number[data.length];
/* 424 */     for (int i = 0; i < data.length; i++) {
/* 425 */       result[i] = new Double(data[i]);
/*     */     }
/* 427 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/DefaultHighLowDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */