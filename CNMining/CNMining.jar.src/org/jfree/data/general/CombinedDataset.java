/*     */ package org.jfree.data.general;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ /**
/*     */  * @deprecated
/*     */  */
/*     */ public class CombinedDataset
/*     */   extends AbstractIntervalXYDataset
/*     */   implements XYDataset, OHLCDataset, IntervalXYDataset, CombinationDataset
/*     */ {
/*  80 */   private List datasetInfo = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CombinedDataset() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CombinedDataset(SeriesDataset[] data)
/*     */   {
/*  96 */     add(data);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(SeriesDataset data)
/*     */   {
/* 106 */     fastAdd(data);
/* 107 */     DatasetChangeEvent event = new DatasetChangeEvent(this, this);
/* 108 */     notifyListeners(event);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(SeriesDataset[] data)
/*     */   {
/* 119 */     for (int i = 0; i < data.length; i++) {
/* 120 */       fastAdd(data[i]);
/*     */     }
/* 122 */     DatasetChangeEvent event = new DatasetChangeEvent(this, this);
/* 123 */     notifyListeners(event);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(SeriesDataset data, int series)
/*     */   {
/* 135 */     add(new SubSeriesDataset(data, series));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void fastAdd(SeriesDataset data)
/*     */   {
/* 144 */     for (int i = 0; i < data.getSeriesCount(); i++) {
/* 145 */       this.datasetInfo.add(new DatasetInfo(data, i));
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
/* 159 */     return this.datasetInfo.size();
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
/* 170 */     DatasetInfo di = getDatasetInfo(series);
/* 171 */     return di.data.getSeriesKey(di.series);
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
/* 190 */     DatasetInfo di = getDatasetInfo(series);
/* 191 */     return ((XYDataset)di.data).getX(di.series, item);
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
/* 206 */     DatasetInfo di = getDatasetInfo(series);
/* 207 */     return ((XYDataset)di.data).getY(di.series, item);
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
/* 221 */     DatasetInfo di = getDatasetInfo(series);
/* 222 */     return ((XYDataset)di.data).getItemCount(di.series);
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
/* 241 */     DatasetInfo di = getDatasetInfo(series);
/* 242 */     return ((OHLCDataset)di.data).getHigh(di.series, item);
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
/* 255 */     double result = NaN.0D;
/* 256 */     Number high = getHigh(series, item);
/* 257 */     if (high != null) {
/* 258 */       result = high.doubleValue();
/*     */     }
/* 260 */     return result;
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
/* 275 */     DatasetInfo di = getDatasetInfo(series);
/* 276 */     return ((OHLCDataset)di.data).getLow(di.series, item);
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
/* 289 */     double result = NaN.0D;
/* 290 */     Number low = getLow(series, item);
/* 291 */     if (low != null) {
/* 292 */       result = low.doubleValue();
/*     */     }
/* 294 */     return result;
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
/* 309 */     DatasetInfo di = getDatasetInfo(series);
/* 310 */     return ((OHLCDataset)di.data).getOpen(di.series, item);
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
/* 323 */     double result = NaN.0D;
/* 324 */     Number open = getOpen(series, item);
/* 325 */     if (open != null) {
/* 326 */       result = open.doubleValue();
/*     */     }
/* 328 */     return result;
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
/* 343 */     DatasetInfo di = getDatasetInfo(series);
/* 344 */     return ((OHLCDataset)di.data).getClose(di.series, item);
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
/* 357 */     double result = NaN.0D;
/* 358 */     Number close = getClose(series, item);
/* 359 */     if (close != null) {
/* 360 */       result = close.doubleValue();
/*     */     }
/* 362 */     return result;
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
/* 377 */     DatasetInfo di = getDatasetInfo(series);
/* 378 */     return ((OHLCDataset)di.data).getVolume(di.series, item);
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
/* 391 */     double result = NaN.0D;
/* 392 */     Number volume = getVolume(series, item);
/* 393 */     if (volume != null) {
/* 394 */       result = volume.doubleValue();
/*     */     }
/* 396 */     return result;
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
/* 412 */     DatasetInfo di = getDatasetInfo(series);
/* 413 */     if ((di.data instanceof IntervalXYDataset)) {
/* 414 */       return ((IntervalXYDataset)di.data).getStartX(di.series, item);
/*     */     }
/*     */     
/* 417 */     return getX(series, item);
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
/* 430 */     DatasetInfo di = getDatasetInfo(series);
/* 431 */     if ((di.data instanceof IntervalXYDataset)) {
/* 432 */       return ((IntervalXYDataset)di.data).getEndX(di.series, item);
/*     */     }
/*     */     
/* 435 */     return getX(series, item);
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
/* 448 */     DatasetInfo di = getDatasetInfo(series);
/* 449 */     if ((di.data instanceof IntervalXYDataset)) {
/* 450 */       return ((IntervalXYDataset)di.data).getStartY(di.series, item);
/*     */     }
/*     */     
/* 453 */     return getY(series, item);
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
/* 466 */     DatasetInfo di = getDatasetInfo(series);
/* 467 */     if ((di.data instanceof IntervalXYDataset)) {
/* 468 */       return ((IntervalXYDataset)di.data).getEndY(di.series, item);
/*     */     }
/*     */     
/* 471 */     return getY(series, item);
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
/*     */   public SeriesDataset getParent()
/*     */   {
/* 488 */     SeriesDataset parent = null;
/* 489 */     for (int i = 0; i < this.datasetInfo.size(); i++) {
/* 490 */       SeriesDataset child = getDatasetInfo(i).data;
/* 491 */       if ((child instanceof CombinationDataset)) {
/* 492 */         SeriesDataset childParent = ((CombinationDataset)child).getParent();
/*     */         
/* 494 */         if (parent == null) {
/* 495 */           parent = childParent;
/*     */         }
/* 497 */         else if (parent != childParent) {
/* 498 */           return null;
/*     */         }
/*     */       }
/*     */       else {
/* 502 */         return null;
/*     */       }
/*     */     }
/* 505 */     return parent;
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
/*     */   public int[] getMap()
/*     */   {
/* 521 */     int[] map = null;
/* 522 */     for (int i = 0; i < this.datasetInfo.size(); i++) {
/* 523 */       SeriesDataset child = getDatasetInfo(i).data;
/* 524 */       if ((child instanceof CombinationDataset)) {
/* 525 */         int[] childMap = ((CombinationDataset)child).getMap();
/* 526 */         if (childMap == null) {
/* 527 */           return null;
/*     */         }
/* 529 */         map = joinMap(map, childMap);
/*     */       }
/*     */       else {
/* 532 */         return null;
/*     */       }
/*     */     }
/* 535 */     return map;
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
/*     */   public int getChildPosition(Dataset child)
/*     */   {
/* 551 */     int n = 0;
/* 552 */     for (int i = 0; i < this.datasetInfo.size(); i++) {
/* 553 */       SeriesDataset childDataset = getDatasetInfo(i).data;
/* 554 */       if ((childDataset instanceof CombinedDataset)) {
/* 555 */         int m = ((CombinedDataset)childDataset).getChildPosition(child);
/*     */         
/* 557 */         if (m >= 0) {
/* 558 */           return n + m;
/*     */         }
/* 560 */         n++;
/*     */       }
/*     */       else {
/* 563 */         if (child == childDataset) {
/* 564 */           return n;
/*     */         }
/* 566 */         n++;
/*     */       }
/*     */     }
/* 569 */     return -1;
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
/*     */   private DatasetInfo getDatasetInfo(int series)
/*     */   {
/* 584 */     return (DatasetInfo)this.datasetInfo.get(series);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int[] joinMap(int[] a, int[] b)
/*     */   {
/* 596 */     if (a == null) {
/* 597 */       return b;
/*     */     }
/* 599 */     if (b == null) {
/* 600 */       return a;
/*     */     }
/* 602 */     int[] result = new int[a.length + b.length];
/* 603 */     System.arraycopy(a, 0, result, 0, a.length);
/* 604 */     System.arraycopy(b, 0, result, a.length, b.length);
/* 605 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private class DatasetInfo
/*     */   {
/*     */     private SeriesDataset data;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     private int series;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     DatasetInfo(SeriesDataset data, int series)
/*     */     {
/* 627 */       this.data = data;
/* 628 */       this.series = series;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/general/CombinedDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */