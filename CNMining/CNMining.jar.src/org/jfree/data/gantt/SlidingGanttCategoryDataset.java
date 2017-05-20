/*     */ package org.jfree.data.gantt;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.jfree.data.UnknownKeyException;
/*     */ import org.jfree.data.general.AbstractDataset;
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
/*     */ public class SlidingGanttCategoryDataset
/*     */   extends AbstractDataset
/*     */   implements GanttCategoryDataset
/*     */ {
/*     */   private GanttCategoryDataset underlying;
/*     */   private int firstCategoryIndex;
/*     */   private int maximumCategoryCount;
/*     */   
/*     */   public SlidingGanttCategoryDataset(GanttCategoryDataset underlying, int firstColumn, int maxColumns)
/*     */   {
/*  82 */     this.underlying = underlying;
/*  83 */     this.firstCategoryIndex = firstColumn;
/*  84 */     this.maximumCategoryCount = maxColumns;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public GanttCategoryDataset getUnderlyingDataset()
/*     */   {
/*  93 */     return this.underlying;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getFirstCategoryIndex()
/*     */   {
/* 104 */     return this.firstCategoryIndex;
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
/*     */   public void setFirstCategoryIndex(int first)
/*     */   {
/* 117 */     if ((first < 0) || (first >= this.underlying.getColumnCount())) {
/* 118 */       throw new IllegalArgumentException("Invalid index.");
/*     */     }
/* 120 */     this.firstCategoryIndex = first;
/* 121 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMaximumCategoryCount()
/*     */   {
/* 132 */     return this.maximumCategoryCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMaximumCategoryCount(int max)
/*     */   {
/* 144 */     if (max < 0) {
/* 145 */       throw new IllegalArgumentException("Requires 'max' >= 0.");
/*     */     }
/* 147 */     this.maximumCategoryCount = max;
/* 148 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int lastCategoryIndex()
/*     */   {
/* 157 */     if (this.maximumCategoryCount == 0) {
/* 158 */       return -1;
/*     */     }
/* 160 */     return Math.min(this.firstCategoryIndex + this.maximumCategoryCount, this.underlying.getColumnCount()) - 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getColumnIndex(Comparable key)
/*     */   {
/* 172 */     int index = this.underlying.getColumnIndex(key);
/* 173 */     if ((index >= this.firstCategoryIndex) && (index <= lastCategoryIndex())) {
/* 174 */       return index - this.firstCategoryIndex;
/*     */     }
/* 176 */     return -1;
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
/*     */   public Comparable getColumnKey(int column)
/*     */   {
/* 189 */     return this.underlying.getColumnKey(column + this.firstCategoryIndex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getColumnKeys()
/*     */   {
/* 200 */     List result = new ArrayList();
/* 201 */     int last = lastCategoryIndex();
/* 202 */     for (int i = this.firstCategoryIndex; i < last; i++) {
/* 203 */       result.add(this.underlying.getColumnKey(i));
/*     */     }
/* 205 */     return Collections.unmodifiableList(result);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRowIndex(Comparable key)
/*     */   {
/* 216 */     return this.underlying.getRowIndex(key);
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
/*     */   public Comparable getRowKey(int row)
/*     */   {
/* 229 */     return this.underlying.getRowKey(row);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getRowKeys()
/*     */   {
/* 238 */     return this.underlying.getRowKeys();
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
/*     */   public Number getValue(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 252 */     int r = getRowIndex(rowKey);
/* 253 */     int c = getColumnIndex(columnKey);
/* 254 */     if (c != -1) {
/* 255 */       return this.underlying.getValue(r, c + this.firstCategoryIndex);
/*     */     }
/*     */     
/* 258 */     throw new UnknownKeyException("Unknown columnKey: " + columnKey);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getColumnCount()
/*     */   {
/* 268 */     int last = lastCategoryIndex();
/* 269 */     if (last == -1) {
/* 270 */       return 0;
/*     */     }
/*     */     
/* 273 */     return Math.max(last - this.firstCategoryIndex + 1, 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRowCount()
/*     */   {
/* 283 */     return this.underlying.getRowCount();
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
/* 295 */     return this.underlying.getValue(row, column + this.firstCategoryIndex);
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
/* 307 */     int r = getRowIndex(rowKey);
/* 308 */     int c = getColumnIndex(columnKey);
/* 309 */     if (c != -1) {
/* 310 */       return this.underlying.getPercentComplete(r, c + this.firstCategoryIndex);
/*     */     }
/*     */     
/*     */ 
/* 314 */     throw new UnknownKeyException("Unknown columnKey: " + columnKey);
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
/*     */   public Number getPercentComplete(Comparable rowKey, Comparable columnKey, int subinterval)
/*     */   {
/* 331 */     int r = getRowIndex(rowKey);
/* 332 */     int c = getColumnIndex(columnKey);
/* 333 */     if (c != -1) {
/* 334 */       return this.underlying.getPercentComplete(r, c + this.firstCategoryIndex, subinterval);
/*     */     }
/*     */     
/*     */ 
/* 338 */     throw new UnknownKeyException("Unknown columnKey: " + columnKey);
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
/*     */   public Number getEndValue(Comparable rowKey, Comparable columnKey, int subinterval)
/*     */   {
/* 355 */     int r = getRowIndex(rowKey);
/* 356 */     int c = getColumnIndex(columnKey);
/* 357 */     if (c != -1) {
/* 358 */       return this.underlying.getEndValue(r, c + this.firstCategoryIndex, subinterval);
/*     */     }
/*     */     
/*     */ 
/* 362 */     throw new UnknownKeyException("Unknown columnKey: " + columnKey);
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
/*     */   public Number getEndValue(int row, int column, int subinterval)
/*     */   {
/* 378 */     return this.underlying.getEndValue(row, column + this.firstCategoryIndex, subinterval);
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
/*     */   public Number getPercentComplete(int series, int category)
/*     */   {
/* 391 */     return this.underlying.getPercentComplete(series, category + this.firstCategoryIndex);
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
/*     */   public Number getPercentComplete(int row, int column, int subinterval)
/*     */   {
/* 407 */     return this.underlying.getPercentComplete(row, column + this.firstCategoryIndex, subinterval);
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
/*     */   public Number getStartValue(Comparable rowKey, Comparable columnKey, int subinterval)
/*     */   {
/* 424 */     int r = getRowIndex(rowKey);
/* 425 */     int c = getColumnIndex(columnKey);
/* 426 */     if (c != -1) {
/* 427 */       return this.underlying.getStartValue(r, c + this.firstCategoryIndex, subinterval);
/*     */     }
/*     */     
/*     */ 
/* 431 */     throw new UnknownKeyException("Unknown columnKey: " + columnKey);
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
/*     */   public Number getStartValue(int row, int column, int subinterval)
/*     */   {
/* 447 */     return this.underlying.getStartValue(row, column + this.firstCategoryIndex, subinterval);
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
/*     */   public int getSubIntervalCount(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 462 */     int r = getRowIndex(rowKey);
/* 463 */     int c = getColumnIndex(columnKey);
/* 464 */     if (c != -1) {
/* 465 */       return this.underlying.getSubIntervalCount(r, c + this.firstCategoryIndex);
/*     */     }
/*     */     
/*     */ 
/* 469 */     throw new UnknownKeyException("Unknown columnKey: " + columnKey);
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
/*     */   public int getSubIntervalCount(int row, int column)
/*     */   {
/* 484 */     return this.underlying.getSubIntervalCount(row, column + this.firstCategoryIndex);
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
/*     */   public Number getStartValue(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 499 */     int r = getRowIndex(rowKey);
/* 500 */     int c = getColumnIndex(columnKey);
/* 501 */     if (c != -1) {
/* 502 */       return this.underlying.getStartValue(r, c + this.firstCategoryIndex);
/*     */     }
/*     */     
/* 505 */     throw new UnknownKeyException("Unknown columnKey: " + columnKey);
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
/*     */   public Number getStartValue(int row, int column)
/*     */   {
/* 520 */     return this.underlying.getStartValue(row, column + this.firstCategoryIndex);
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
/*     */   public Number getEndValue(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 535 */     int r = getRowIndex(rowKey);
/* 536 */     int c = getColumnIndex(columnKey);
/* 537 */     if (c != -1) {
/* 538 */       return this.underlying.getEndValue(r, c + this.firstCategoryIndex);
/*     */     }
/*     */     
/* 541 */     throw new UnknownKeyException("Unknown columnKey: " + columnKey);
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
/*     */   public Number getEndValue(int series, int category)
/*     */   {
/* 554 */     return this.underlying.getEndValue(series, category + this.firstCategoryIndex);
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 567 */     if (obj == this) {
/* 568 */       return true;
/*     */     }
/* 570 */     if (!(obj instanceof SlidingGanttCategoryDataset)) {
/* 571 */       return false;
/*     */     }
/* 573 */     SlidingGanttCategoryDataset that = (SlidingGanttCategoryDataset)obj;
/* 574 */     if (this.firstCategoryIndex != that.firstCategoryIndex) {
/* 575 */       return false;
/*     */     }
/* 577 */     if (this.maximumCategoryCount != that.maximumCategoryCount) {
/* 578 */       return false;
/*     */     }
/* 580 */     if (!this.underlying.equals(that.underlying)) {
/* 581 */       return false;
/*     */     }
/* 583 */     return true;
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
/* 601 */     SlidingGanttCategoryDataset clone = (SlidingGanttCategoryDataset)super.clone();
/*     */     
/* 603 */     if ((this.underlying instanceof PublicCloneable)) {
/* 604 */       PublicCloneable pc = (PublicCloneable)this.underlying;
/* 605 */       clone.underlying = ((GanttCategoryDataset)pc.clone());
/*     */     }
/* 607 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/gantt/SlidingGanttCategoryDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */