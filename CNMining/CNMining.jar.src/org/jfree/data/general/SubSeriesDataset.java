/*     */ package org.jfree.data.general;
/*     */ 
/*     */ import org.jfree.data.xy.AbstractIntervalXYDataset;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ import org.jfree.data.xy.OHLCDataset;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ /**
/*     */  * @deprecated
/*     */  */
/*     */ public class SubSeriesDataset
/*     */   extends AbstractIntervalXYDataset
/*     */   implements OHLCDataset, IntervalXYDataset, CombinationDataset
/*     */ {
/*  74 */   private SeriesDataset parent = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private int[] map;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SubSeriesDataset(SeriesDataset parent, int[] map)
/*     */   {
/*  87 */     this.parent = parent;
/*  88 */     this.map = map;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SubSeriesDataset(SeriesDataset parent, int series)
/*     */   {
/*  99 */     this(parent, new int[] { series });
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
/*     */   public Number getHigh(int series, int item)
/*     */   {
/* 118 */     return ((OHLCDataset)this.parent).getHigh(this.map[series], item);
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
/* 131 */     double result = NaN.0D;
/* 132 */     Number high = getHigh(series, item);
/* 133 */     if (high != null) {
/* 134 */       result = high.doubleValue();
/*     */     }
/* 136 */     return result;
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
/*     */   public Number getLow(int series, int item)
/*     */   {
/* 151 */     return ((OHLCDataset)this.parent).getLow(this.map[series], item);
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
/* 164 */     double result = NaN.0D;
/* 165 */     Number low = getLow(series, item);
/* 166 */     if (low != null) {
/* 167 */       result = low.doubleValue();
/*     */     }
/* 169 */     return result;
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
/*     */   public Number getOpen(int series, int item)
/*     */   {
/* 184 */     return ((OHLCDataset)this.parent).getOpen(this.map[series], item);
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
/* 197 */     double result = NaN.0D;
/* 198 */     Number open = getOpen(series, item);
/* 199 */     if (open != null) {
/* 200 */       result = open.doubleValue();
/*     */     }
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
/*     */   public Number getClose(int series, int item)
/*     */   {
/* 217 */     return ((OHLCDataset)this.parent).getClose(this.map[series], item);
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
/* 230 */     double result = NaN.0D;
/* 231 */     Number close = getClose(series, item);
/* 232 */     if (close != null) {
/* 233 */       result = close.doubleValue();
/*     */     }
/* 235 */     return result;
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
/*     */   public Number getVolume(int series, int item)
/*     */   {
/* 250 */     return ((OHLCDataset)this.parent).getVolume(this.map[series], item);
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
/* 263 */     double result = NaN.0D;
/* 264 */     Number volume = getVolume(series, item);
/* 265 */     if (volume != null) {
/* 266 */       result = volume.doubleValue();
/*     */     }
/* 268 */     return result;
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
/*     */   public Number getX(int series, int item)
/*     */   {
/* 287 */     return ((XYDataset)this.parent).getX(this.map[series], item);
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
/* 302 */     return ((XYDataset)this.parent).getY(this.map[series], item);
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
/*     */   public int getItemCount(int series)
/*     */   {
/* 316 */     return ((XYDataset)this.parent).getItemCount(this.map[series]);
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
/* 329 */     return this.map.length;
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
/* 340 */     return this.parent.getSeriesKey(this.map[series]);
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
/*     */   public Number getStartX(int series, int item)
/*     */   {
/* 356 */     if ((this.parent instanceof IntervalXYDataset)) {
/* 357 */       return ((IntervalXYDataset)this.parent).getStartX(this.map[series], item);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 362 */     return getX(series, item);
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
/*     */   public Number getEndX(int series, int item)
/*     */   {
/* 375 */     if ((this.parent instanceof IntervalXYDataset)) {
/* 376 */       return ((IntervalXYDataset)this.parent).getEndX(this.map[series], item);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 381 */     return getX(series, item);
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
/*     */   public Number getStartY(int series, int item)
/*     */   {
/* 394 */     if ((this.parent instanceof IntervalXYDataset)) {
/* 395 */       return ((IntervalXYDataset)this.parent).getStartY(this.map[series], item);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 400 */     return getY(series, item);
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
/*     */   public Number getEndY(int series, int item)
/*     */   {
/* 413 */     if ((this.parent instanceof IntervalXYDataset)) {
/* 414 */       return ((IntervalXYDataset)this.parent).getEndY(this.map[series], item);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 419 */     return getY(series, item);
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
/*     */   public SeriesDataset getParent()
/*     */   {
/* 433 */     return this.parent;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int[] getMap()
/*     */   {
/* 442 */     return (int[])this.map.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/general/SubSeriesDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */