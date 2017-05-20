/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HistogramDataset
/*     */   extends AbstractIntervalXYDataset
/*     */   implements IntervalXYDataset, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6341668077370231153L;
/*     */   private List list;
/*     */   private HistogramType type;
/*     */   
/*     */   public HistogramDataset()
/*     */   {
/*  96 */     this.list = new ArrayList();
/*  97 */     this.type = HistogramType.FREQUENCY;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public HistogramType getType()
/*     */   {
/* 106 */     return this.type;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setType(HistogramType type)
/*     */   {
/* 116 */     if (type == null) {
/* 117 */       throw new IllegalArgumentException("Null 'type' argument");
/*     */     }
/* 119 */     this.type = type;
/* 120 */     notifyListeners(new DatasetChangeEvent(this, this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addSeries(Comparable key, double[] values, int bins)
/*     */   {
/* 132 */     double minimum = getMinimum(values);
/* 133 */     double maximum = getMaximum(values);
/* 134 */     addSeries(key, values, bins, minimum, maximum);
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
/*     */   public void addSeries(Comparable key, double[] values, int bins, double minimum, double maximum)
/*     */   {
/* 155 */     if (key == null) {
/* 156 */       throw new IllegalArgumentException("Null 'key' argument.");
/*     */     }
/* 158 */     if (values == null) {
/* 159 */       throw new IllegalArgumentException("Null 'values' argument.");
/*     */     }
/* 161 */     if (bins < 1) {
/* 162 */       throw new IllegalArgumentException("The 'bins' value must be at least 1.");
/*     */     }
/*     */     
/* 165 */     double binWidth = (maximum - minimum) / bins;
/*     */     
/* 167 */     double lower = minimum;
/*     */     
/* 169 */     List binList = new ArrayList(bins);
/* 170 */     for (int i = 0; i < bins; i++)
/*     */     {
/*     */       HistogramBin bin;
/*     */       
/*     */       HistogramBin bin;
/* 175 */       if (i == bins - 1) {
/* 176 */         bin = new HistogramBin(lower, maximum);
/*     */       }
/*     */       else {
/* 179 */         double upper = minimum + (i + 1) * binWidth;
/* 180 */         bin = new HistogramBin(lower, upper);
/* 181 */         lower = upper;
/*     */       }
/* 183 */       binList.add(bin);
/*     */     }
/*     */     
/* 186 */     for (int i = 0; i < values.length; i++) {
/* 187 */       int binIndex = bins - 1;
/* 188 */       if (values[i] < maximum) {
/* 189 */         double fraction = (values[i] - minimum) / (maximum - minimum);
/* 190 */         if (fraction < 0.0D) {
/* 191 */           fraction = 0.0D;
/*     */         }
/* 193 */         binIndex = (int)(fraction * bins);
/*     */         
/*     */ 
/*     */ 
/* 197 */         if (binIndex >= bins) {
/* 198 */           binIndex = bins - 1;
/*     */         }
/*     */       }
/* 201 */       HistogramBin bin = (HistogramBin)binList.get(binIndex);
/* 202 */       bin.incrementCount();
/*     */     }
/*     */     
/* 205 */     Map map = new HashMap();
/* 206 */     map.put("key", key);
/* 207 */     map.put("bins", binList);
/* 208 */     map.put("values.length", new Integer(values.length));
/* 209 */     map.put("bin width", new Double(binWidth));
/* 210 */     this.list.add(map);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double getMinimum(double[] values)
/*     */   {
/* 222 */     if ((values == null) || (values.length < 1)) {
/* 223 */       throw new IllegalArgumentException("Null or zero length 'values' argument.");
/*     */     }
/*     */     
/* 226 */     double min = Double.MAX_VALUE;
/* 227 */     for (int i = 0; i < values.length; i++) {
/* 228 */       if (values[i] < min) {
/* 229 */         min = values[i];
/*     */       }
/*     */     }
/* 232 */     return min;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double getMaximum(double[] values)
/*     */   {
/* 244 */     if ((values == null) || (values.length < 1)) {
/* 245 */       throw new IllegalArgumentException("Null or zero length 'values' argument.");
/*     */     }
/*     */     
/* 248 */     double max = -1.7976931348623157E308D;
/* 249 */     for (int i = 0; i < values.length; i++) {
/* 250 */       if (values[i] > max) {
/* 251 */         max = values[i];
/*     */       }
/*     */     }
/* 254 */     return max;
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
/*     */   List getBins(int series)
/*     */   {
/* 269 */     Map map = (Map)this.list.get(series);
/* 270 */     return (List)map.get("bins");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int getTotal(int series)
/*     */   {
/* 281 */     Map map = (Map)this.list.get(series);
/* 282 */     return ((Integer)map.get("values.length")).intValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double getBinWidth(int series)
/*     */   {
/* 293 */     Map map = (Map)this.list.get(series);
/* 294 */     return ((Double)map.get("bin width")).doubleValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesCount()
/*     */   {
/* 303 */     return this.list.size();
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
/*     */   public Comparable getSeriesKey(int series)
/*     */   {
/* 318 */     Map map = (Map)this.list.get(series);
/* 319 */     return (Comparable)map.get("key");
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
/* 334 */     return getBins(series).size();
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
/* 353 */     List bins = getBins(series);
/* 354 */     HistogramBin bin = (HistogramBin)bins.get(item);
/* 355 */     double x = (bin.getStartBoundary() + bin.getEndBoundary()) / 2.0D;
/* 356 */     return new Double(x);
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
/* 373 */     List bins = getBins(series);
/* 374 */     HistogramBin bin = (HistogramBin)bins.get(item);
/* 375 */     double total = getTotal(series);
/* 376 */     double binWidth = getBinWidth(series);
/*     */     
/* 378 */     if (this.type == HistogramType.FREQUENCY) {
/* 379 */       return new Double(bin.getCount());
/*     */     }
/* 381 */     if (this.type == HistogramType.RELATIVE_FREQUENCY) {
/* 382 */       return new Double(bin.getCount() / total);
/*     */     }
/* 384 */     if (this.type == HistogramType.SCALE_AREA_TO_1) {
/* 385 */       return new Double(bin.getCount() / (binWidth * total));
/*     */     }
/*     */     
/* 388 */     throw new IllegalStateException();
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
/*     */   public Number getStartX(int series, int item)
/*     */   {
/* 405 */     List bins = getBins(series);
/* 406 */     HistogramBin bin = (HistogramBin)bins.get(item);
/* 407 */     return new Double(bin.getStartBoundary());
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
/*     */   public Number getEndX(int series, int item)
/*     */   {
/* 423 */     List bins = getBins(series);
/* 424 */     HistogramBin bin = (HistogramBin)bins.get(item);
/* 425 */     return new Double(bin.getEndBoundary());
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
/*     */   public Number getStartY(int series, int item)
/*     */   {
/* 443 */     return getY(series, item);
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
/*     */   public Number getEndY(int series, int item)
/*     */   {
/* 461 */     return getY(series, item);
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
/* 472 */     if (obj == this) {
/* 473 */       return true;
/*     */     }
/* 475 */     if (!(obj instanceof HistogramDataset)) {
/* 476 */       return false;
/*     */     }
/* 478 */     HistogramDataset that = (HistogramDataset)obj;
/* 479 */     if (!ObjectUtilities.equal(this.type, that.type)) {
/* 480 */       return false;
/*     */     }
/* 482 */     if (!ObjectUtilities.equal(this.list, that.list)) {
/* 483 */       return false;
/*     */     }
/* 485 */     return true;
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
/* 496 */     HistogramDataset clone = (HistogramDataset)super.clone();
/* 497 */     int seriesCount = getSeriesCount();
/* 498 */     clone.list = new ArrayList(seriesCount);
/* 499 */     for (int i = 0; i < seriesCount; i++) {
/* 500 */       clone.list.add(new HashMap((Map)this.list.get(i)));
/*     */     }
/* 502 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/statistics/HistogramDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */