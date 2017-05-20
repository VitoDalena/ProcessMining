/*     */ package org.jfree.data.gantt;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.data.general.AbstractSeriesDataset;
/*     */ import org.jfree.data.general.SeriesChangeEvent;
/*     */ import org.jfree.data.time.TimePeriod;
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
/*     */ public class TaskSeriesCollection
/*     */   extends AbstractSeriesDataset
/*     */   implements GanttCategoryDataset, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2065799050738449903L;
/*     */   private List keys;
/*     */   private List data;
/*     */   
/*     */   public TaskSeriesCollection()
/*     */   {
/*  87 */     this.keys = new ArrayList();
/*  88 */     this.data = new ArrayList();
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
/*     */   public TaskSeries getSeries(Comparable key)
/*     */   {
/* 101 */     if (key == null) {
/* 102 */       throw new NullPointerException("Null 'key' argument.");
/*     */     }
/* 104 */     TaskSeries result = null;
/* 105 */     int index = getRowIndex(key);
/* 106 */     if (index >= 0) {
/* 107 */       result = getSeries(index);
/*     */     }
/* 109 */     return result;
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
/*     */   public TaskSeries getSeries(int series)
/*     */   {
/* 122 */     if ((series < 0) || (series >= getSeriesCount())) {
/* 123 */       throw new IllegalArgumentException("Series index out of bounds");
/*     */     }
/* 125 */     return (TaskSeries)this.data.get(series);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesCount()
/*     */   {
/* 134 */     return getRowCount();
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
/* 145 */     TaskSeries ts = (TaskSeries)this.data.get(series);
/* 146 */     return ts.getKey();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRowCount()
/*     */   {
/* 155 */     return this.data.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getRowKeys()
/*     */   {
/* 164 */     return this.data;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getColumnCount()
/*     */   {
/* 173 */     return this.keys.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getColumnKeys()
/*     */   {
/* 182 */     return this.keys;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getColumnKey(int index)
/*     */   {
/* 193 */     return (Comparable)this.keys.get(index);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getColumnIndex(Comparable columnKey)
/*     */   {
/* 204 */     if (columnKey == null) {
/* 205 */       throw new IllegalArgumentException("Null 'columnKey' argument.");
/*     */     }
/* 207 */     return this.keys.indexOf(columnKey);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRowIndex(Comparable rowKey)
/*     */   {
/* 218 */     int result = -1;
/* 219 */     int count = this.data.size();
/* 220 */     for (int i = 0; i < count; i++) {
/* 221 */       TaskSeries s = (TaskSeries)this.data.get(i);
/* 222 */       if (s.getKey().equals(rowKey)) {
/* 223 */         result = i;
/* 224 */         break;
/*     */       }
/*     */     }
/* 227 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getRowKey(int index)
/*     */   {
/* 238 */     TaskSeries series = (TaskSeries)this.data.get(index);
/* 239 */     return series.getKey();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(TaskSeries series)
/*     */   {
/* 250 */     if (series == null) {
/* 251 */       throw new IllegalArgumentException("Null 'series' argument.");
/*     */     }
/* 253 */     this.data.add(series);
/* 254 */     series.addChangeListener(this);
/*     */     
/*     */ 
/* 257 */     Iterator iterator = series.getTasks().iterator();
/* 258 */     while (iterator.hasNext()) {
/* 259 */       Task task = (Task)iterator.next();
/* 260 */       String key = task.getDescription();
/* 261 */       int index = this.keys.indexOf(key);
/* 262 */       if (index < 0) {
/* 263 */         this.keys.add(key);
/*     */       }
/*     */     }
/* 266 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void remove(TaskSeries series)
/*     */   {
/* 277 */     if (series == null) {
/* 278 */       throw new IllegalArgumentException("Null 'series' argument.");
/*     */     }
/* 280 */     if (this.data.contains(series)) {
/* 281 */       series.removeChangeListener(this);
/* 282 */       this.data.remove(series);
/* 283 */       fireDatasetChanged();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void remove(int series)
/*     */   {
/* 295 */     if ((series < 0) || (series >= getSeriesCount())) {
/* 296 */       throw new IllegalArgumentException("TaskSeriesCollection.remove(): index outside valid range.");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 301 */     TaskSeries ts = (TaskSeries)this.data.get(series);
/* 302 */     ts.removeChangeListener(this);
/* 303 */     this.data.remove(series);
/* 304 */     fireDatasetChanged();
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
/*     */   public void removeAll()
/*     */   {
/* 317 */     Iterator iterator = this.data.iterator();
/* 318 */     while (iterator.hasNext()) {
/* 319 */       TaskSeries series = (TaskSeries)iterator.next();
/* 320 */       series.removeChangeListener(this);
/*     */     }
/*     */     
/*     */ 
/* 324 */     this.data.clear();
/* 325 */     fireDatasetChanged();
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
/*     */   public Number getValue(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 338 */     return getStartValue(rowKey, columnKey);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getValue(int row, int column)
/*     */   {
/* 350 */     return getStartValue(row, column);
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
/*     */   public Number getStartValue(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 363 */     Number result = null;
/* 364 */     int row = getRowIndex(rowKey);
/* 365 */     TaskSeries series = (TaskSeries)this.data.get(row);
/* 366 */     Task task = series.get(columnKey.toString());
/* 367 */     if (task != null) {
/* 368 */       TimePeriod duration = task.getDuration();
/* 369 */       if (duration != null) {
/* 370 */         result = new Long(duration.getStart().getTime());
/*     */       }
/*     */     }
/* 373 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getStartValue(int row, int column)
/*     */   {
/* 385 */     Comparable rowKey = getRowKey(row);
/* 386 */     Comparable columnKey = getColumnKey(column);
/* 387 */     return getStartValue(rowKey, columnKey);
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
/*     */   public Number getEndValue(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 400 */     Number result = null;
/* 401 */     int row = getRowIndex(rowKey);
/* 402 */     TaskSeries series = (TaskSeries)this.data.get(row);
/* 403 */     Task task = series.get(columnKey.toString());
/* 404 */     if (task != null) {
/* 405 */       TimePeriod duration = task.getDuration();
/* 406 */       if (duration != null) {
/* 407 */         result = new Long(duration.getEnd().getTime());
/*     */       }
/*     */     }
/* 410 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getEndValue(int row, int column)
/*     */   {
/* 422 */     Comparable rowKey = getRowKey(row);
/* 423 */     Comparable columnKey = getColumnKey(column);
/* 424 */     return getEndValue(rowKey, columnKey);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getPercentComplete(int row, int column)
/*     */   {
/* 436 */     Comparable rowKey = getRowKey(row);
/* 437 */     Comparable columnKey = getColumnKey(column);
/* 438 */     return getPercentComplete(rowKey, columnKey);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getPercentComplete(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 450 */     Number result = null;
/* 451 */     int row = getRowIndex(rowKey);
/* 452 */     TaskSeries series = (TaskSeries)this.data.get(row);
/* 453 */     Task task = series.get(columnKey.toString());
/* 454 */     if (task != null) {
/* 455 */       result = task.getPercentComplete();
/*     */     }
/* 457 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSubIntervalCount(int row, int column)
/*     */   {
/* 469 */     Comparable rowKey = getRowKey(row);
/* 470 */     Comparable columnKey = getColumnKey(column);
/* 471 */     return getSubIntervalCount(rowKey, columnKey);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSubIntervalCount(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 483 */     int result = 0;
/* 484 */     int row = getRowIndex(rowKey);
/* 485 */     TaskSeries series = (TaskSeries)this.data.get(row);
/* 486 */     Task task = series.get(columnKey.toString());
/* 487 */     if (task != null) {
/* 488 */       result = task.getSubtaskCount();
/*     */     }
/* 490 */     return result;
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
/*     */   public Number getStartValue(int row, int column, int subinterval)
/*     */   {
/* 503 */     Comparable rowKey = getRowKey(row);
/* 504 */     Comparable columnKey = getColumnKey(column);
/* 505 */     return getStartValue(rowKey, columnKey, subinterval);
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
/*     */   public Number getStartValue(Comparable rowKey, Comparable columnKey, int subinterval)
/*     */   {
/* 519 */     Number result = null;
/* 520 */     int row = getRowIndex(rowKey);
/* 521 */     TaskSeries series = (TaskSeries)this.data.get(row);
/* 522 */     Task task = series.get(columnKey.toString());
/* 523 */     if (task != null) {
/* 524 */       Task sub = task.getSubtask(subinterval);
/* 525 */       if (sub != null) {
/* 526 */         TimePeriod duration = sub.getDuration();
/* 527 */         result = new Long(duration.getStart().getTime());
/*     */       }
/*     */     }
/* 530 */     return result;
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
/*     */   public Number getEndValue(int row, int column, int subinterval)
/*     */   {
/* 543 */     Comparable rowKey = getRowKey(row);
/* 544 */     Comparable columnKey = getColumnKey(column);
/* 545 */     return getEndValue(rowKey, columnKey, subinterval);
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
/*     */   public Number getEndValue(Comparable rowKey, Comparable columnKey, int subinterval)
/*     */   {
/* 559 */     Number result = null;
/* 560 */     int row = getRowIndex(rowKey);
/* 561 */     TaskSeries series = (TaskSeries)this.data.get(row);
/* 562 */     Task task = series.get(columnKey.toString());
/* 563 */     if (task != null) {
/* 564 */       Task sub = task.getSubtask(subinterval);
/* 565 */       if (sub != null) {
/* 566 */         TimePeriod duration = sub.getDuration();
/* 567 */         result = new Long(duration.getEnd().getTime());
/*     */       }
/*     */     }
/* 570 */     return result;
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
/*     */   public Number getPercentComplete(int row, int column, int subinterval)
/*     */   {
/* 583 */     Comparable rowKey = getRowKey(row);
/* 584 */     Comparable columnKey = getColumnKey(column);
/* 585 */     return getPercentComplete(rowKey, columnKey, subinterval);
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
/*     */   public Number getPercentComplete(Comparable rowKey, Comparable columnKey, int subinterval)
/*     */   {
/* 599 */     Number result = null;
/* 600 */     int row = getRowIndex(rowKey);
/* 601 */     TaskSeries series = (TaskSeries)this.data.get(row);
/* 602 */     Task task = series.get(columnKey.toString());
/* 603 */     if (task != null) {
/* 604 */       Task sub = task.getSubtask(subinterval);
/* 605 */       if (sub != null) {
/* 606 */         result = sub.getPercentComplete();
/*     */       }
/*     */     }
/* 609 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void seriesChanged(SeriesChangeEvent event)
/*     */   {
/* 618 */     refreshKeys();
/* 619 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void refreshKeys()
/*     */   {
/* 627 */     this.keys.clear();
/* 628 */     for (int i = 0; i < getSeriesCount(); i++) {
/* 629 */       TaskSeries series = (TaskSeries)this.data.get(i);
/*     */       
/* 631 */       Iterator iterator = series.getTasks().iterator();
/* 632 */       while (iterator.hasNext()) {
/* 633 */         Task task = (Task)iterator.next();
/* 634 */         String key = task.getDescription();
/* 635 */         int index = this.keys.indexOf(key);
/* 636 */         if (index < 0) {
/* 637 */           this.keys.add(key);
/*     */         }
/*     */       }
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 652 */     if (obj == this) {
/* 653 */       return true;
/*     */     }
/* 655 */     if (!(obj instanceof TaskSeriesCollection)) {
/* 656 */       return false;
/*     */     }
/* 658 */     TaskSeriesCollection that = (TaskSeriesCollection)obj;
/* 659 */     if (!ObjectUtilities.equal(this.data, that.data)) {
/* 660 */       return false;
/*     */     }
/* 662 */     return true;
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
/* 674 */     TaskSeriesCollection clone = (TaskSeriesCollection)super.clone();
/* 675 */     clone.data = ((List)ObjectUtilities.deepClone(this.data));
/* 676 */     clone.keys = new ArrayList(this.keys);
/* 677 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/gantt/TaskSeriesCollection.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */