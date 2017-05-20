/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import org.jfree.data.general.DatasetChangeEvent;
/*     */ import org.jfree.data.general.DatasetChangeListener;
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
/*     */ public class XYBarDataset
/*     */   extends AbstractIntervalXYDataset
/*     */   implements IntervalXYDataset, DatasetChangeListener, PublicCloneable
/*     */ {
/*     */   private XYDataset underlying;
/*     */   private double barWidth;
/*     */   
/*     */   public XYBarDataset(XYDataset underlying, double barWidth)
/*     */   {
/*  77 */     this.underlying = underlying;
/*  78 */     this.underlying.addChangeListener(this);
/*  79 */     this.barWidth = barWidth;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XYDataset getUnderlyingDataset()
/*     */   {
/*  90 */     return this.underlying;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getBarWidth()
/*     */   {
/* 102 */     return this.barWidth;
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
/*     */   public void setBarWidth(double barWidth)
/*     */   {
/* 115 */     this.barWidth = barWidth;
/* 116 */     notifyListeners(new DatasetChangeEvent(this, this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesCount()
/*     */   {
/* 125 */     return this.underlying.getSeriesCount();
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
/* 137 */     return this.underlying.getSeriesKey(series);
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
/* 148 */     return this.underlying.getItemCount(series);
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
/* 162 */     return this.underlying.getX(series, item);
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
/*     */   public double getXValue(int series, int item)
/*     */   {
/* 176 */     return this.underlying.getXValue(series, item);
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
/*     */   public Number getY(int series, int item)
/*     */   {
/* 190 */     return this.underlying.getY(series, item);
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
/* 204 */     return this.underlying.getYValue(series, item);
/*     */   }
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
/* 216 */     Number result = null;
/* 217 */     Number xnum = this.underlying.getX(series, item);
/* 218 */     if (xnum != null) {
/* 219 */       result = new Double(xnum.doubleValue() - this.barWidth / 2.0D);
/*     */     }
/* 221 */     return result;
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
/*     */   public double getStartXValue(int series, int item)
/*     */   {
/* 236 */     return getXValue(series, item) - this.barWidth / 2.0D;
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
/* 248 */     Number result = null;
/* 249 */     Number xnum = this.underlying.getX(series, item);
/* 250 */     if (xnum != null) {
/* 251 */       result = new Double(xnum.doubleValue() + this.barWidth / 2.0D);
/*     */     }
/* 253 */     return result;
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
/*     */   public double getEndXValue(int series, int item)
/*     */   {
/* 268 */     return getXValue(series, item) + this.barWidth / 2.0D;
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
/* 280 */     return this.underlying.getY(series, item);
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
/*     */   public double getStartYValue(int series, int item)
/*     */   {
/* 295 */     return getYValue(series, item);
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
/* 307 */     return this.underlying.getY(series, item);
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
/*     */   public double getEndYValue(int series, int item)
/*     */   {
/* 322 */     return getYValue(series, item);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void datasetChanged(DatasetChangeEvent event)
/*     */   {
/* 331 */     notifyListeners(event);
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
/* 342 */     if (obj == this) {
/* 343 */       return true;
/*     */     }
/* 345 */     if (!(obj instanceof XYBarDataset)) {
/* 346 */       return false;
/*     */     }
/* 348 */     XYBarDataset that = (XYBarDataset)obj;
/* 349 */     if (!this.underlying.equals(that.underlying)) {
/* 350 */       return false;
/*     */     }
/* 352 */     if (this.barWidth != that.barWidth) {
/* 353 */       return false;
/*     */     }
/* 355 */     return true;
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
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 373 */     XYBarDataset clone = (XYBarDataset)super.clone();
/* 374 */     if ((this.underlying instanceof PublicCloneable)) {
/* 375 */       PublicCloneable pc = (PublicCloneable)this.underlying;
/* 376 */       clone.underlying = ((XYDataset)pc.clone());
/*     */     }
/* 378 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/XYBarDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */