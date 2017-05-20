/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jfree.data.KeyedObjects2D;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.RangeInfo;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultStatisticalCategoryDataset
/*     */   extends AbstractDataset
/*     */   implements StatisticalCategoryDataset, RangeInfo, PublicCloneable
/*     */ {
/*     */   private KeyedObjects2D data;
/*     */   private double minimumRangeValue;
/*     */   private int minimumRangeValueRow;
/*     */   private int minimumRangeValueColumn;
/*     */   private double minimumRangeValueIncStdDev;
/*     */   private int minimumRangeValueIncStdDevRow;
/*     */   private int minimumRangeValueIncStdDevColumn;
/*     */   private double maximumRangeValue;
/*     */   private int maximumRangeValueRow;
/*     */   private int maximumRangeValueColumn;
/*     */   private double maximumRangeValueIncStdDev;
/*     */   private int maximumRangeValueIncStdDevRow;
/*     */   private int maximumRangeValueIncStdDevColumn;
/*     */   
/*     */   public DefaultStatisticalCategoryDataset()
/*     */   {
/* 129 */     this.data = new KeyedObjects2D();
/* 130 */     this.minimumRangeValue = NaN.0D;
/* 131 */     this.minimumRangeValueRow = -1;
/* 132 */     this.minimumRangeValueColumn = -1;
/* 133 */     this.maximumRangeValue = NaN.0D;
/* 134 */     this.maximumRangeValueRow = -1;
/* 135 */     this.maximumRangeValueColumn = -1;
/* 136 */     this.minimumRangeValueIncStdDev = NaN.0D;
/* 137 */     this.minimumRangeValueIncStdDevRow = -1;
/* 138 */     this.minimumRangeValueIncStdDevColumn = -1;
/* 139 */     this.maximumRangeValueIncStdDev = NaN.0D;
/* 140 */     this.maximumRangeValueIncStdDevRow = -1;
/* 141 */     this.maximumRangeValueIncStdDevColumn = -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getMeanValue(int row, int column)
/*     */   {
/* 153 */     Number result = null;
/* 154 */     MeanAndStandardDeviation masd = (MeanAndStandardDeviation)this.data.getObject(row, column);
/*     */     
/* 156 */     if (masd != null) {
/* 157 */       result = masd.getMean();
/*     */     }
/* 159 */     return result;
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
/*     */   public Number getValue(int row, int column)
/*     */   {
/* 172 */     return getMeanValue(row, column);
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
/* 185 */     return getMeanValue(rowKey, columnKey);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getMeanValue(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 197 */     Number result = null;
/* 198 */     MeanAndStandardDeviation masd = (MeanAndStandardDeviation)this.data.getObject(rowKey, columnKey);
/*     */     
/* 200 */     if (masd != null) {
/* 201 */       result = masd.getMean();
/*     */     }
/* 203 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getStdDevValue(int row, int column)
/*     */   {
/* 215 */     Number result = null;
/* 216 */     MeanAndStandardDeviation masd = (MeanAndStandardDeviation)this.data.getObject(row, column);
/*     */     
/* 218 */     if (masd != null) {
/* 219 */       result = masd.getStandardDeviation();
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
/*     */   public Number getStdDevValue(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 233 */     Number result = null;
/* 234 */     MeanAndStandardDeviation masd = (MeanAndStandardDeviation)this.data.getObject(rowKey, columnKey);
/*     */     
/* 236 */     if (masd != null) {
/* 237 */       result = masd.getStandardDeviation();
/*     */     }
/* 239 */     return result;
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
/* 251 */     return this.data.getColumnIndex(key);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getColumnKey(int column)
/*     */   {
/* 262 */     return this.data.getColumnKey(column);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getColumnKeys()
/*     */   {
/* 271 */     return this.data.getColumnKeys();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRowIndex(Comparable key)
/*     */   {
/* 283 */     return this.data.getRowIndex(key);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getRowKey(int row)
/*     */   {
/* 294 */     return this.data.getRowKey(row);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getRowKeys()
/*     */   {
/* 303 */     return this.data.getRowKeys();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRowCount()
/*     */   {
/* 314 */     return this.data.getRowCount();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getColumnCount()
/*     */   {
/* 325 */     return this.data.getColumnCount();
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
/*     */   public void add(double mean, double standardDeviation, Comparable rowKey, Comparable columnKey)
/*     */   {
/* 338 */     add(new Double(mean), new Double(standardDeviation), rowKey, columnKey);
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
/*     */   public void add(Number mean, Number standardDeviation, Comparable rowKey, Comparable columnKey)
/*     */   {
/* 351 */     MeanAndStandardDeviation item = new MeanAndStandardDeviation(mean, standardDeviation);
/*     */     
/* 353 */     this.data.addObject(item, rowKey, columnKey);
/*     */     
/* 355 */     double m = NaN.0D;
/* 356 */     double sd = NaN.0D;
/* 357 */     if (mean != null) {
/* 358 */       m = mean.doubleValue();
/*     */     }
/* 360 */     if (standardDeviation != null) {
/* 361 */       sd = standardDeviation.doubleValue();
/*     */     }
/*     */     
/*     */ 
/* 365 */     int r = this.data.getColumnIndex(columnKey);
/* 366 */     int c = this.data.getRowIndex(rowKey);
/* 367 */     if (((r == this.maximumRangeValueRow) && (c == this.maximumRangeValueColumn)) || ((r == this.maximumRangeValueIncStdDevRow) && (c == this.maximumRangeValueIncStdDevColumn)) || ((r == this.minimumRangeValueRow) && (c == this.minimumRangeValueColumn)) || ((r == this.minimumRangeValueIncStdDevRow) && (c == this.minimumRangeValueIncStdDevColumn)))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 377 */       updateBounds();
/*     */     }
/*     */     else {
/* 380 */       if ((!Double.isNaN(m)) && (
/* 381 */         (Double.isNaN(this.maximumRangeValue)) || (m > this.maximumRangeValue)))
/*     */       {
/* 383 */         this.maximumRangeValue = m;
/* 384 */         this.maximumRangeValueRow = r;
/* 385 */         this.maximumRangeValueColumn = c;
/*     */       }
/*     */       
/*     */ 
/* 389 */       if ((!Double.isNaN(m + sd)) && (
/* 390 */         (Double.isNaN(this.maximumRangeValueIncStdDev)) || (m + sd > this.maximumRangeValueIncStdDev)))
/*     */       {
/* 392 */         this.maximumRangeValueIncStdDev = (m + sd);
/* 393 */         this.maximumRangeValueIncStdDevRow = r;
/* 394 */         this.maximumRangeValueIncStdDevColumn = c;
/*     */       }
/*     */       
/*     */ 
/* 398 */       if ((!Double.isNaN(m)) && (
/* 399 */         (Double.isNaN(this.minimumRangeValue)) || (m < this.minimumRangeValue)))
/*     */       {
/* 401 */         this.minimumRangeValue = m;
/* 402 */         this.minimumRangeValueRow = r;
/* 403 */         this.minimumRangeValueColumn = c;
/*     */       }
/*     */       
/*     */ 
/* 407 */       if ((!Double.isNaN(m - sd)) && (
/* 408 */         (Double.isNaN(this.minimumRangeValueIncStdDev)) || (m - sd < this.minimumRangeValueIncStdDev)))
/*     */       {
/* 410 */         this.minimumRangeValueIncStdDev = (m - sd);
/* 411 */         this.minimumRangeValueIncStdDevRow = r;
/* 412 */         this.minimumRangeValueIncStdDevColumn = c;
/*     */       }
/*     */     }
/*     */     
/* 416 */     fireDatasetChanged();
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
/*     */   public void remove(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 432 */     int r = getRowIndex(rowKey);
/* 433 */     int c = getColumnIndex(columnKey);
/* 434 */     this.data.removeObject(rowKey, columnKey);
/*     */     
/*     */ 
/*     */ 
/* 438 */     if (((r == this.maximumRangeValueRow) && (c == this.maximumRangeValueColumn)) || ((r == this.maximumRangeValueIncStdDevRow) && (c == this.maximumRangeValueIncStdDevColumn)) || ((r == this.minimumRangeValueRow) && (c == this.minimumRangeValueColumn)) || ((r == this.minimumRangeValueIncStdDevRow) && (c == this.minimumRangeValueIncStdDevColumn)))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 448 */       updateBounds();
/*     */     }
/*     */     
/* 451 */     fireDatasetChanged();
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
/*     */   public void removeRow(int rowIndex)
/*     */   {
/* 466 */     this.data.removeRow(rowIndex);
/* 467 */     updateBounds();
/* 468 */     fireDatasetChanged();
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
/*     */   public void removeRow(Comparable rowKey)
/*     */   {
/* 482 */     this.data.removeRow(rowKey);
/* 483 */     updateBounds();
/* 484 */     fireDatasetChanged();
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
/*     */   public void removeColumn(int columnIndex)
/*     */   {
/* 498 */     this.data.removeColumn(columnIndex);
/* 499 */     updateBounds();
/* 500 */     fireDatasetChanged();
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
/*     */   public void removeColumn(Comparable columnKey)
/*     */   {
/* 514 */     this.data.removeColumn(columnKey);
/* 515 */     updateBounds();
/* 516 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 526 */     this.data.clear();
/* 527 */     updateBounds();
/* 528 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void updateBounds()
/*     */   {
/* 535 */     this.maximumRangeValue = NaN.0D;
/* 536 */     this.maximumRangeValueRow = -1;
/* 537 */     this.maximumRangeValueColumn = -1;
/* 538 */     this.minimumRangeValue = NaN.0D;
/* 539 */     this.minimumRangeValueRow = -1;
/* 540 */     this.minimumRangeValueColumn = -1;
/* 541 */     this.maximumRangeValueIncStdDev = NaN.0D;
/* 542 */     this.maximumRangeValueIncStdDevRow = -1;
/* 543 */     this.maximumRangeValueIncStdDevColumn = -1;
/* 544 */     this.minimumRangeValueIncStdDev = NaN.0D;
/* 545 */     this.minimumRangeValueIncStdDevRow = -1;
/* 546 */     this.minimumRangeValueIncStdDevColumn = -1;
/*     */     
/* 548 */     int rowCount = this.data.getRowCount();
/* 549 */     int columnCount = this.data.getColumnCount();
/* 550 */     for (int r = 0; r < rowCount; r++) {
/* 551 */       for (int c = 0; c < columnCount; c++) {
/* 552 */         double m = NaN.0D;
/* 553 */         double sd = NaN.0D;
/* 554 */         MeanAndStandardDeviation masd = (MeanAndStandardDeviation)this.data.getObject(r, c);
/*     */         
/* 556 */         if (masd != null)
/*     */         {
/*     */ 
/* 559 */           m = masd.getMeanValue();
/* 560 */           sd = masd.getStandardDeviationValue();
/*     */           
/* 562 */           if (!Double.isNaN(m))
/*     */           {
/*     */ 
/* 565 */             if (Double.isNaN(this.maximumRangeValue)) {
/* 566 */               this.maximumRangeValue = m;
/* 567 */               this.maximumRangeValueRow = r;
/* 568 */               this.maximumRangeValueColumn = c;
/*     */ 
/*     */             }
/* 571 */             else if (m > this.maximumRangeValue) {
/* 572 */               this.maximumRangeValue = m;
/* 573 */               this.maximumRangeValueRow = r;
/* 574 */               this.maximumRangeValueColumn = c;
/*     */             }
/*     */             
/*     */ 
/*     */ 
/* 579 */             if (Double.isNaN(this.minimumRangeValue)) {
/* 580 */               this.minimumRangeValue = m;
/* 581 */               this.minimumRangeValueRow = r;
/* 582 */               this.minimumRangeValueColumn = c;
/*     */ 
/*     */             }
/* 585 */             else if (m < this.minimumRangeValue) {
/* 586 */               this.minimumRangeValue = m;
/* 587 */               this.minimumRangeValueRow = r;
/* 588 */               this.minimumRangeValueColumn = c;
/*     */             }
/*     */             
/*     */ 
/* 592 */             if (!Double.isNaN(sd))
/*     */             {
/* 594 */               if (Double.isNaN(this.maximumRangeValueIncStdDev)) {
/* 595 */                 this.maximumRangeValueIncStdDev = (m + sd);
/* 596 */                 this.maximumRangeValueIncStdDevRow = r;
/* 597 */                 this.maximumRangeValueIncStdDevColumn = c;
/*     */ 
/*     */               }
/* 600 */               else if (m + sd > this.maximumRangeValueIncStdDev) {
/* 601 */                 this.maximumRangeValueIncStdDev = (m + sd);
/* 602 */                 this.maximumRangeValueIncStdDevRow = r;
/* 603 */                 this.maximumRangeValueIncStdDevColumn = c;
/*     */               }
/*     */               
/*     */ 
/*     */ 
/* 608 */               if (Double.isNaN(this.minimumRangeValueIncStdDev)) {
/* 609 */                 this.minimumRangeValueIncStdDev = (m - sd);
/* 610 */                 this.minimumRangeValueIncStdDevRow = r;
/* 611 */                 this.minimumRangeValueIncStdDevColumn = c;
/*     */ 
/*     */               }
/* 614 */               else if (m - sd < this.minimumRangeValueIncStdDev) {
/* 615 */                 this.minimumRangeValueIncStdDev = (m - sd);
/* 616 */                 this.minimumRangeValueIncStdDevRow = r;
/* 617 */                 this.minimumRangeValueIncStdDevColumn = c;
/*     */               }
/*     */             }
/*     */           }
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
/*     */ 
/*     */ 
/*     */   public double getRangeLowerBound(boolean includeInterval)
/*     */   {
/* 637 */     if (includeInterval) {
/* 638 */       return this.minimumRangeValueIncStdDev;
/*     */     }
/*     */     
/* 641 */     return this.minimumRangeValue;
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
/*     */   public double getRangeUpperBound(boolean includeInterval)
/*     */   {
/* 656 */     if (includeInterval) {
/* 657 */       return this.maximumRangeValueIncStdDev;
/*     */     }
/*     */     
/* 660 */     return this.maximumRangeValue;
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
/*     */   public Range getRangeBounds(boolean includeInterval)
/*     */   {
/* 673 */     Range result = null;
/* 674 */     if (includeInterval) {
/* 675 */       if ((!Double.isNaN(this.minimumRangeValueIncStdDev)) && (!Double.isNaN(this.maximumRangeValueIncStdDev)))
/*     */       {
/* 677 */         result = new Range(this.minimumRangeValueIncStdDev, this.maximumRangeValueIncStdDev);
/*     */       }
/*     */       
/*     */ 
/*     */     }
/* 682 */     else if ((!Double.isNaN(this.minimumRangeValue)) && (!Double.isNaN(this.maximumRangeValue)))
/*     */     {
/* 684 */       result = new Range(this.minimumRangeValue, this.maximumRangeValue);
/*     */     }
/*     */     
/*     */ 
/* 688 */     return result;
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
/* 699 */     if (obj == this) {
/* 700 */       return true;
/*     */     }
/* 702 */     if (!(obj instanceof DefaultStatisticalCategoryDataset)) {
/* 703 */       return false;
/*     */     }
/* 705 */     DefaultStatisticalCategoryDataset that = (DefaultStatisticalCategoryDataset)obj;
/*     */     
/* 707 */     if (!this.data.equals(that.data)) {
/* 708 */       return false;
/*     */     }
/* 710 */     return true;
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
/* 721 */     DefaultStatisticalCategoryDataset clone = (DefaultStatisticalCategoryDataset)super.clone();
/*     */     
/* 723 */     clone.data = ((KeyedObjects2D)this.data.clone());
/* 724 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/statistics/DefaultStatisticalCategoryDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */