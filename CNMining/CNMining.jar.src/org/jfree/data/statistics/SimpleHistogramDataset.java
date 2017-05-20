/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.data.DomainOrder;
/*     */ import org.jfree.data.general.DatasetChangeEvent;
/*     */ import org.jfree.data.xy.AbstractIntervalXYDataset;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ import org.jfree.util.ObjectUtilities;
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
/*     */ public class SimpleHistogramDataset
/*     */   extends AbstractIntervalXYDataset
/*     */   implements IntervalXYDataset, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7997996479768018443L;
/*     */   private Comparable key;
/*     */   private List bins;
/*     */   private boolean adjustForBinSize;
/*     */   
/*     */   public SimpleHistogramDataset(Comparable key)
/*     */   {
/*  89 */     if (key == null) {
/*  90 */       throw new IllegalArgumentException("Null 'key' argument.");
/*     */     }
/*  92 */     this.key = key;
/*  93 */     this.bins = new ArrayList();
/*  94 */     this.adjustForBinSize = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getAdjustForBinSize()
/*     */   {
/* 106 */     return this.adjustForBinSize;
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
/*     */   public void setAdjustForBinSize(boolean adjust)
/*     */   {
/* 119 */     this.adjustForBinSize = adjust;
/* 120 */     notifyListeners(new DatasetChangeEvent(this, this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesCount()
/*     */   {
/* 129 */     return 1;
/*     */   }
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
/* 141 */     return this.key;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DomainOrder getDomainOrder()
/*     */   {
/* 150 */     return DomainOrder.ASCENDING;
/*     */   }
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
/* 162 */     return this.bins.size();
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
/*     */   public void addBin(SimpleHistogramBin bin)
/*     */   {
/* 175 */     Iterator iterator = this.bins.iterator();
/* 176 */     while (iterator.hasNext()) {
/* 177 */       SimpleHistogramBin existingBin = (SimpleHistogramBin)iterator.next();
/*     */       
/* 179 */       if (bin.overlapsWith(existingBin)) {
/* 180 */         throw new RuntimeException("Overlapping bin");
/*     */       }
/*     */     }
/* 183 */     this.bins.add(bin);
/* 184 */     Collections.sort(this.bins);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addObservation(double value)
/*     */   {
/* 195 */     addObservation(value, true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addObservation(double value, boolean notify)
/*     */   {
/* 207 */     boolean placed = false;
/* 208 */     Iterator iterator = this.bins.iterator();
/* 209 */     while ((iterator.hasNext()) && (!placed)) {
/* 210 */       SimpleHistogramBin bin = (SimpleHistogramBin)iterator.next();
/* 211 */       if (bin.accepts(value)) {
/* 212 */         bin.setItemCount(bin.getItemCount() + 1);
/* 213 */         placed = true;
/*     */       }
/*     */     }
/* 216 */     if (!placed) {
/* 217 */       throw new RuntimeException("No bin.");
/*     */     }
/* 219 */     if (notify) {
/* 220 */       notifyListeners(new DatasetChangeEvent(this, this));
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
/*     */   public void addObservations(double[] values)
/*     */   {
/* 233 */     for (int i = 0; i < values.length; i++) {
/* 234 */       addObservation(values[i], false);
/*     */     }
/* 236 */     notifyListeners(new DatasetChangeEvent(this, this));
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
/*     */   public void clearObservations()
/*     */   {
/* 249 */     Iterator iterator = this.bins.iterator();
/* 250 */     while (iterator.hasNext()) {
/* 251 */       SimpleHistogramBin bin = (SimpleHistogramBin)iterator.next();
/* 252 */       bin.setItemCount(0);
/*     */     }
/* 254 */     notifyListeners(new DatasetChangeEvent(this, this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeAllBins()
/*     */   {
/* 266 */     this.bins = new ArrayList();
/* 267 */     notifyListeners(new DatasetChangeEvent(this, this));
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
/*     */   public Number getX(int series, int item)
/*     */   {
/* 281 */     return new Double(getXValue(series, item));
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
/* 293 */     SimpleHistogramBin bin = (SimpleHistogramBin)this.bins.get(item);
/* 294 */     return (bin.getLowerBound() + bin.getUpperBound()) / 2.0D;
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
/* 306 */     return new Double(getYValue(series, item));
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
/*     */   public double getYValue(int series, int item)
/*     */   {
/* 320 */     SimpleHistogramBin bin = (SimpleHistogramBin)this.bins.get(item);
/* 321 */     if (this.adjustForBinSize) {
/* 322 */       return bin.getItemCount() / (bin.getUpperBound() - bin.getLowerBound());
/*     */     }
/*     */     
/*     */ 
/* 326 */     return bin.getItemCount();
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
/*     */   public Number getStartX(int series, int item)
/*     */   {
/* 339 */     return new Double(getStartXValue(series, item));
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
/*     */   public double getStartXValue(int series, int item)
/*     */   {
/* 352 */     SimpleHistogramBin bin = (SimpleHistogramBin)this.bins.get(item);
/* 353 */     return bin.getLowerBound();
/*     */   }
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
/* 365 */     return new Double(getEndXValue(series, item));
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
/*     */   public double getEndXValue(int series, int item)
/*     */   {
/* 378 */     SimpleHistogramBin bin = (SimpleHistogramBin)this.bins.get(item);
/* 379 */     return bin.getUpperBound();
/*     */   }
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
/* 391 */     return getY(series, item);
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
/*     */   public double getStartYValue(int series, int item)
/*     */   {
/* 404 */     return getYValue(series, item);
/*     */   }
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
/* 416 */     return getY(series, item);
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
/*     */   public double getEndYValue(int series, int item)
/*     */   {
/* 429 */     return getYValue(series, item);
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
/* 440 */     if (obj == this) {
/* 441 */       return true;
/*     */     }
/* 443 */     if (!(obj instanceof SimpleHistogramDataset)) {
/* 444 */       return false;
/*     */     }
/* 446 */     SimpleHistogramDataset that = (SimpleHistogramDataset)obj;
/* 447 */     if (!this.key.equals(that.key)) {
/* 448 */       return false;
/*     */     }
/* 450 */     if (this.adjustForBinSize != that.adjustForBinSize) {
/* 451 */       return false;
/*     */     }
/* 453 */     if (!this.bins.equals(that.bins)) {
/* 454 */       return false;
/*     */     }
/* 456 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 468 */     SimpleHistogramDataset clone = (SimpleHistogramDataset)super.clone();
/* 469 */     clone.bins = ((List)ObjectUtilities.deepClone(this.bins));
/* 470 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/statistics/SimpleHistogramDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */