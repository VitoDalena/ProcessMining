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
/*     */ public class DefaultOHLCDataset
/*     */   extends AbstractXYDataset
/*     */   implements OHLCDataset, PublicCloneable
/*     */ {
/*     */   private Comparable key;
/*     */   private OHLCDataItem[] data;
/*     */   
/*     */   public DefaultOHLCDataset(Comparable key, OHLCDataItem[] data)
/*     */   {
/*  73 */     this.key = key;
/*  74 */     this.data = data;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getSeriesKey(int series)
/*     */   {
/*  85 */     return this.key;
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
/*  97 */     return new Long(this.data[item].getDate().getTime());
/*     */   }
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
/* 109 */     return this.data[item].getDate();
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
/* 121 */     return getClose(series, item);
/*     */   }
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
/* 133 */     return this.data[item].getHigh();
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
/*     */   public double getHighValue(int series, int item)
/*     */   {
/* 146 */     double result = NaN.0D;
/* 147 */     Number high = getHigh(series, item);
/* 148 */     if (high != null) {
/* 149 */       result = high.doubleValue();
/*     */     }
/* 151 */     return result;
/*     */   }
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
/* 163 */     return this.data[item].getLow();
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
/*     */   public double getLowValue(int series, int item)
/*     */   {
/* 176 */     double result = NaN.0D;
/* 177 */     Number low = getLow(series, item);
/* 178 */     if (low != null) {
/* 179 */       result = low.doubleValue();
/*     */     }
/* 181 */     return result;
/*     */   }
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
/* 193 */     return this.data[item].getOpen();
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
/*     */   public double getOpenValue(int series, int item)
/*     */   {
/* 206 */     double result = NaN.0D;
/* 207 */     Number open = getOpen(series, item);
/* 208 */     if (open != null) {
/* 209 */       result = open.doubleValue();
/*     */     }
/* 211 */     return result;
/*     */   }
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
/* 223 */     return this.data[item].getClose();
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
/*     */   public double getCloseValue(int series, int item)
/*     */   {
/* 236 */     double result = NaN.0D;
/* 237 */     Number close = getClose(series, item);
/* 238 */     if (close != null) {
/* 239 */       result = close.doubleValue();
/*     */     }
/* 241 */     return result;
/*     */   }
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
/* 253 */     return this.data[item].getVolume();
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
/*     */   public double getVolumeValue(int series, int item)
/*     */   {
/* 266 */     double result = NaN.0D;
/* 267 */     Number volume = getVolume(series, item);
/* 268 */     if (volume != null) {
/* 269 */       result = volume.doubleValue();
/*     */     }
/* 271 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesCount()
/*     */   {
/* 280 */     return 1;
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
/* 291 */     return this.data.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void sortDataByDate()
/*     */   {
/* 298 */     Arrays.sort(this.data);
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
/* 309 */     if (this == obj) {
/* 310 */       return true;
/*     */     }
/* 312 */     if (!(obj instanceof DefaultOHLCDataset)) {
/* 313 */       return false;
/*     */     }
/* 315 */     DefaultOHLCDataset that = (DefaultOHLCDataset)obj;
/* 316 */     if (!this.key.equals(that.key)) {
/* 317 */       return false;
/*     */     }
/* 319 */     if (!Arrays.equals(this.data, that.data)) {
/* 320 */       return false;
/*     */     }
/* 322 */     return true;
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
/* 333 */     DefaultOHLCDataset clone = (DefaultOHLCDataset)super.clone();
/* 334 */     clone.data = new OHLCDataItem[this.data.length];
/* 335 */     System.arraycopy(this.data, 0, clone.data, 0, this.data.length);
/* 336 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/DefaultOHLCDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */