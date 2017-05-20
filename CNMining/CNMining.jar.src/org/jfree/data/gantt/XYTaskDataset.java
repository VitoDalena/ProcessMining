/*     */ package org.jfree.data.gantt;
/*     */ 
/*     */ import java.util.Date;
/*     */ import org.jfree.data.general.DatasetChangeEvent;
/*     */ import org.jfree.data.general.DatasetChangeListener;
/*     */ import org.jfree.data.time.TimePeriod;
/*     */ import org.jfree.data.xy.AbstractXYDataset;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XYTaskDataset
/*     */   extends AbstractXYDataset
/*     */   implements IntervalXYDataset, DatasetChangeListener
/*     */ {
/*     */   private TaskSeriesCollection underlying;
/*     */   private double seriesWidth;
/*     */   private boolean transposed;
/*     */   
/*     */   public XYTaskDataset(TaskSeriesCollection tasks)
/*     */   {
/*  81 */     if (tasks == null) {
/*  82 */       throw new IllegalArgumentException("Null 'tasks' argument.");
/*     */     }
/*  84 */     this.underlying = tasks;
/*  85 */     this.seriesWidth = 0.8D;
/*  86 */     this.underlying.addChangeListener(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TaskSeriesCollection getTasks()
/*     */   {
/*  96 */     return this.underlying;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getSeriesWidth()
/*     */   {
/* 107 */     return this.seriesWidth;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSeriesWidth(double w)
/*     */   {
/* 119 */     if (w <= 0.0D) {
/* 120 */       throw new IllegalArgumentException("Requires 'w' > 0.0.");
/*     */     }
/* 122 */     this.seriesWidth = w;
/* 123 */     fireDatasetChanged();
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
/*     */   public boolean isTransposed()
/*     */   {
/* 138 */     return this.transposed;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTransposed(boolean transposed)
/*     */   {
/* 150 */     this.transposed = transposed;
/* 151 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesCount()
/*     */   {
/* 160 */     return this.underlying.getSeriesCount();
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
/* 171 */     return this.underlying.getSeriesKey(series);
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
/* 182 */     return this.underlying.getSeries(series).getItemCount();
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
/* 194 */     if (!this.transposed) {
/* 195 */       return getSeriesValue(series);
/*     */     }
/*     */     
/* 198 */     return getItemValue(series, item);
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
/* 213 */     if (!this.transposed) {
/* 214 */       return getSeriesStartValue(series);
/*     */     }
/*     */     
/* 217 */     return getItemStartValue(series, item);
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
/* 232 */     if (!this.transposed) {
/* 233 */       return getSeriesEndValue(series);
/*     */     }
/*     */     
/* 236 */     return getItemEndValue(series, item);
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
/*     */   public Number getX(int series, int item)
/*     */   {
/* 249 */     return new Double(getXValue(series, item));
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
/*     */   public Number getStartX(int series, int item)
/*     */   {
/* 263 */     return new Double(getStartXValue(series, item));
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
/*     */   public Number getEndX(int series, int item)
/*     */   {
/* 277 */     return new Double(getEndXValue(series, item));
/*     */   }
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
/* 289 */     if (!this.transposed) {
/* 290 */       return getItemValue(series, item);
/*     */     }
/*     */     
/* 293 */     return getSeriesValue(series);
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
/*     */   public double getStartYValue(int series, int item)
/*     */   {
/* 307 */     if (!this.transposed) {
/* 308 */       return getItemStartValue(series, item);
/*     */     }
/*     */     
/* 311 */     return getSeriesStartValue(series);
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
/*     */   public double getEndYValue(int series, int item)
/*     */   {
/* 325 */     if (!this.transposed) {
/* 326 */       return getItemEndValue(series, item);
/*     */     }
/*     */     
/* 329 */     return getSeriesEndValue(series);
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
/* 344 */     return new Double(getYValue(series, item));
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
/* 357 */     return new Double(getStartYValue(series, item));
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
/* 370 */     return new Double(getEndYValue(series, item));
/*     */   }
/*     */   
/*     */   private double getSeriesValue(int series) {
/* 374 */     return series;
/*     */   }
/*     */   
/*     */   private double getSeriesStartValue(int series) {
/* 378 */     return series - this.seriesWidth / 2.0D;
/*     */   }
/*     */   
/*     */   private double getSeriesEndValue(int series) {
/* 382 */     return series + this.seriesWidth / 2.0D;
/*     */   }
/*     */   
/*     */   private double getItemValue(int series, int item) {
/* 386 */     TaskSeries s = this.underlying.getSeries(series);
/* 387 */     Task t = s.get(item);
/* 388 */     TimePeriod duration = t.getDuration();
/* 389 */     Date start = duration.getStart();
/* 390 */     Date end = duration.getEnd();
/* 391 */     return (start.getTime() + end.getTime()) / 2.0D;
/*     */   }
/*     */   
/*     */   private double getItemStartValue(int series, int item) {
/* 395 */     TaskSeries s = this.underlying.getSeries(series);
/* 396 */     Task t = s.get(item);
/* 397 */     TimePeriod duration = t.getDuration();
/* 398 */     Date start = duration.getStart();
/* 399 */     return start.getTime();
/*     */   }
/*     */   
/*     */   private double getItemEndValue(int series, int item) {
/* 403 */     TaskSeries s = this.underlying.getSeries(series);
/* 404 */     Task t = s.get(item);
/* 405 */     TimePeriod duration = t.getDuration();
/* 406 */     Date end = duration.getEnd();
/* 407 */     return end.getTime();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void datasetChanged(DatasetChangeEvent event)
/*     */   {
/* 418 */     fireDatasetChanged();
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
/* 429 */     if (obj == this) {
/* 430 */       return true;
/*     */     }
/* 432 */     if (!(obj instanceof XYTaskDataset)) {
/* 433 */       return false;
/*     */     }
/* 435 */     XYTaskDataset that = (XYTaskDataset)obj;
/* 436 */     if (this.seriesWidth != that.seriesWidth) {
/* 437 */       return false;
/*     */     }
/* 439 */     if (this.transposed != that.transposed) {
/* 440 */       return false;
/*     */     }
/* 442 */     if (!this.underlying.equals(that.underlying)) {
/* 443 */       return false;
/*     */     }
/* 445 */     return true;
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
/* 456 */     XYTaskDataset clone = (XYTaskDataset)super.clone();
/* 457 */     clone.underlying = ((TaskSeriesCollection)this.underlying.clone());
/* 458 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/gantt/XYTaskDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */