/*     */ package org.jfree.data.time.ohlc;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jfree.data.general.DatasetChangeEvent;
/*     */ import org.jfree.data.time.RegularTimePeriod;
/*     */ import org.jfree.data.time.TimePeriodAnchor;
/*     */ import org.jfree.data.xy.AbstractXYDataset;
/*     */ import org.jfree.data.xy.OHLCDataset;
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
/*     */ public class OHLCSeriesCollection
/*     */   extends AbstractXYDataset
/*     */   implements OHLCDataset, Serializable
/*     */ {
/*     */   private List data;
/*  67 */   private TimePeriodAnchor xPosition = TimePeriodAnchor.MIDDLE;
/*     */   
/*     */ 
/*     */ 
/*     */   public OHLCSeriesCollection()
/*     */   {
/*  73 */     this.data = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TimePeriodAnchor getXPosition()
/*     */   {
/*  85 */     return this.xPosition;
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
/*     */   public void setXPosition(TimePeriodAnchor anchor)
/*     */   {
/*  98 */     if (anchor == null) {
/*  99 */       throw new IllegalArgumentException("Null 'anchor' argument.");
/*     */     }
/* 101 */     this.xPosition = anchor;
/* 102 */     notifyListeners(new DatasetChangeEvent(this, this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addSeries(OHLCSeries series)
/*     */   {
/* 112 */     if (series == null) {
/* 113 */       throw new IllegalArgumentException("Null 'series' argument.");
/*     */     }
/* 115 */     this.data.add(series);
/* 116 */     series.addChangeListener(this);
/* 117 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesCount()
/*     */   {
/* 126 */     return this.data.size();
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
/*     */   public OHLCSeries getSeries(int series)
/*     */   {
/* 140 */     if ((series < 0) || (series >= getSeriesCount())) {
/* 141 */       throw new IllegalArgumentException("Series index out of bounds");
/*     */     }
/* 143 */     return (OHLCSeries)this.data.get(series);
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
/*     */   public Comparable getSeriesKey(int series)
/*     */   {
/* 159 */     return getSeries(series).getKey();
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
/*     */   public int getItemCount(int series)
/*     */   {
/* 174 */     return getSeries(series).getItemCount();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized long getX(RegularTimePeriod period)
/*     */   {
/* 185 */     long result = 0L;
/* 186 */     if (this.xPosition == TimePeriodAnchor.START) {
/* 187 */       result = period.getFirstMillisecond();
/*     */     }
/* 189 */     else if (this.xPosition == TimePeriodAnchor.MIDDLE) {
/* 190 */       result = period.getMiddleMillisecond();
/*     */     }
/* 192 */     else if (this.xPosition == TimePeriodAnchor.END) {
/* 193 */       result = period.getLastMillisecond();
/*     */     }
/* 195 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getXValue(int series, int item)
/*     */   {
/* 207 */     OHLCSeries s = (OHLCSeries)this.data.get(series);
/* 208 */     OHLCItem di = (OHLCItem)s.getDataItem(item);
/* 209 */     RegularTimePeriod period = di.getPeriod();
/* 210 */     return getX(period);
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
/* 222 */     return new Double(getXValue(series, item));
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
/* 234 */     OHLCSeries s = (OHLCSeries)this.data.get(series);
/* 235 */     OHLCItem di = (OHLCItem)s.getDataItem(item);
/* 236 */     return new Double(di.getYValue());
/*     */   }
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
/* 248 */     OHLCSeries s = (OHLCSeries)this.data.get(series);
/* 249 */     OHLCItem di = (OHLCItem)s.getDataItem(item);
/* 250 */     return di.getOpenValue();
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
/* 262 */     return new Double(getOpenValue(series, item));
/*     */   }
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
/* 274 */     OHLCSeries s = (OHLCSeries)this.data.get(series);
/* 275 */     OHLCItem di = (OHLCItem)s.getDataItem(item);
/* 276 */     return di.getCloseValue();
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
/* 288 */     return new Double(getCloseValue(series, item));
/*     */   }
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
/* 300 */     OHLCSeries s = (OHLCSeries)this.data.get(series);
/* 301 */     OHLCItem di = (OHLCItem)s.getDataItem(item);
/* 302 */     return di.getHighValue();
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
/* 314 */     return new Double(getHighValue(series, item));
/*     */   }
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
/* 326 */     OHLCSeries s = (OHLCSeries)this.data.get(series);
/* 327 */     OHLCItem di = (OHLCItem)s.getDataItem(item);
/* 328 */     return di.getLowValue();
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
/* 340 */     return new Double(getLowValue(series, item));
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
/*     */   public Number getVolume(int series, int item)
/*     */   {
/* 353 */     return null;
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
/* 366 */     return NaN.0D;
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
/* 377 */     if (obj == this) {
/* 378 */       return true;
/*     */     }
/* 380 */     if (!(obj instanceof OHLCSeriesCollection)) {
/* 381 */       return false;
/*     */     }
/* 383 */     OHLCSeriesCollection that = (OHLCSeriesCollection)obj;
/* 384 */     if (!this.xPosition.equals(that.xPosition)) {
/* 385 */       return false;
/*     */     }
/* 387 */     return ObjectUtilities.equal(this.data, that.data);
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
/* 398 */     OHLCSeriesCollection clone = (OHLCSeriesCollection)super.clone();
/*     */     
/* 400 */     clone.data = ((List)ObjectUtilities.deepClone(this.data));
/* 401 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/ohlc/OHLCSeriesCollection.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */