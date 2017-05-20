/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jfree.data.KeyedObjects2D;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.RangeInfo;
/*     */ import org.jfree.data.general.AbstractDataset;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultBoxAndWhiskerCategoryDataset
/*     */   extends AbstractDataset
/*     */   implements BoxAndWhiskerCategoryDataset, RangeInfo, PublicCloneable
/*     */ {
/*     */   protected KeyedObjects2D data;
/*     */   private double minimumRangeValue;
/*     */   private int minimumRangeValueRow;
/*     */   private int minimumRangeValueColumn;
/*     */   private double maximumRangeValue;
/*     */   private int maximumRangeValueRow;
/*     */   private int maximumRangeValueColumn;
/*     */   
/*     */   public DefaultBoxAndWhiskerCategoryDataset()
/*     */   {
/* 105 */     this.data = new KeyedObjects2D();
/* 106 */     this.minimumRangeValue = NaN.0D;
/* 107 */     this.minimumRangeValueRow = -1;
/* 108 */     this.minimumRangeValueColumn = -1;
/* 109 */     this.maximumRangeValue = NaN.0D;
/* 110 */     this.maximumRangeValueRow = -1;
/* 111 */     this.maximumRangeValueColumn = -1;
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
/*     */   public void add(List list, Comparable rowKey, Comparable columnKey)
/*     */   {
/* 126 */     BoxAndWhiskerItem item = BoxAndWhiskerCalculator.calculateBoxAndWhiskerStatistics(list);
/*     */     
/* 128 */     add(item, rowKey, columnKey);
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
/*     */   public void add(BoxAndWhiskerItem item, Comparable rowKey, Comparable columnKey)
/*     */   {
/* 144 */     this.data.addObject(item, rowKey, columnKey);
/*     */     
/*     */ 
/* 147 */     int r = this.data.getRowIndex(rowKey);
/* 148 */     int c = this.data.getColumnIndex(columnKey);
/* 149 */     if (((this.maximumRangeValueRow == r) && (this.maximumRangeValueColumn == c)) || ((this.minimumRangeValueRow == r) && (this.minimumRangeValueColumn == c)))
/*     */     {
/*     */ 
/* 152 */       updateBounds();
/*     */     }
/*     */     else
/*     */     {
/* 156 */       double minval = NaN.0D;
/* 157 */       if (item.getMinOutlier() != null) {
/* 158 */         minval = item.getMinOutlier().doubleValue();
/*     */       }
/* 160 */       double maxval = NaN.0D;
/* 161 */       if (item.getMaxOutlier() != null) {
/* 162 */         maxval = item.getMaxOutlier().doubleValue();
/*     */       }
/*     */       
/* 165 */       if (Double.isNaN(this.maximumRangeValue)) {
/* 166 */         this.maximumRangeValue = maxval;
/* 167 */         this.maximumRangeValueRow = r;
/* 168 */         this.maximumRangeValueColumn = c;
/*     */       }
/* 170 */       else if (maxval > this.maximumRangeValue) {
/* 171 */         this.maximumRangeValue = maxval;
/* 172 */         this.maximumRangeValueRow = r;
/* 173 */         this.maximumRangeValueColumn = c;
/*     */       }
/*     */       
/* 176 */       if (Double.isNaN(this.minimumRangeValue)) {
/* 177 */         this.minimumRangeValue = minval;
/* 178 */         this.minimumRangeValueRow = r;
/* 179 */         this.minimumRangeValueColumn = c;
/*     */       }
/* 181 */       else if (minval < this.minimumRangeValue) {
/* 182 */         this.minimumRangeValue = minval;
/* 183 */         this.minimumRangeValueRow = r;
/* 184 */         this.minimumRangeValueColumn = c;
/*     */       }
/*     */     }
/*     */     
/* 188 */     fireDatasetChanged();
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
/*     */   public void remove(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 205 */     int r = getRowIndex(rowKey);
/* 206 */     int c = getColumnIndex(columnKey);
/* 207 */     this.data.removeObject(rowKey, columnKey);
/*     */     
/*     */ 
/*     */ 
/* 211 */     if (((this.maximumRangeValueRow == r) && (this.maximumRangeValueColumn == c)) || ((this.minimumRangeValueRow == r) && (this.minimumRangeValueColumn == c)))
/*     */     {
/*     */ 
/* 214 */       updateBounds();
/*     */     }
/*     */     
/* 217 */     fireDatasetChanged();
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
/*     */   public void removeRow(int rowIndex)
/*     */   {
/* 231 */     this.data.removeRow(rowIndex);
/* 232 */     updateBounds();
/* 233 */     fireDatasetChanged();
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
/* 247 */     this.data.removeRow(rowKey);
/* 248 */     updateBounds();
/* 249 */     fireDatasetChanged();
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
/* 263 */     this.data.removeColumn(columnIndex);
/* 264 */     updateBounds();
/* 265 */     fireDatasetChanged();
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
/* 279 */     this.data.removeColumn(columnKey);
/* 280 */     updateBounds();
/* 281 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 291 */     this.data.clear();
/* 292 */     updateBounds();
/* 293 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BoxAndWhiskerItem getItem(int row, int column)
/*     */   {
/* 305 */     return (BoxAndWhiskerItem)this.data.getObject(row, column);
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
/*     */   public Number getValue(int row, int column)
/*     */   {
/* 320 */     return getMedianValue(row, column);
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
/*     */   public Number getValue(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 335 */     return getMedianValue(rowKey, columnKey);
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
/*     */   public Number getMeanValue(int row, int column)
/*     */   {
/* 350 */     Number result = null;
/* 351 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(row, column);
/*     */     
/* 353 */     if (item != null) {
/* 354 */       result = item.getMean();
/*     */     }
/* 356 */     return result;
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
/*     */   public Number getMeanValue(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 371 */     Number result = null;
/* 372 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(rowKey, columnKey);
/*     */     
/* 374 */     if (item != null) {
/* 375 */       result = item.getMean();
/*     */     }
/* 377 */     return result;
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
/*     */   public Number getMedianValue(int row, int column)
/*     */   {
/* 391 */     Number result = null;
/* 392 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(row, column);
/*     */     
/* 394 */     if (item != null) {
/* 395 */       result = item.getMedian();
/*     */     }
/* 397 */     return result;
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
/*     */   public Number getMedianValue(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 411 */     Number result = null;
/* 412 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(rowKey, columnKey);
/*     */     
/* 414 */     if (item != null) {
/* 415 */       result = item.getMedian();
/*     */     }
/* 417 */     return result;
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
/*     */   public Number getQ1Value(int row, int column)
/*     */   {
/* 431 */     Number result = null;
/* 432 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(row, column);
/*     */     
/* 434 */     if (item != null) {
/* 435 */       result = item.getQ1();
/*     */     }
/* 437 */     return result;
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
/*     */   public Number getQ1Value(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 451 */     Number result = null;
/* 452 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(rowKey, columnKey);
/*     */     
/* 454 */     if (item != null) {
/* 455 */       result = item.getQ1();
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
/*     */ 
/*     */ 
/*     */   public Number getQ3Value(int row, int column)
/*     */   {
/* 471 */     Number result = null;
/* 472 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(row, column);
/*     */     
/* 474 */     if (item != null) {
/* 475 */       result = item.getQ3();
/*     */     }
/* 477 */     return result;
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
/*     */   public Number getQ3Value(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 491 */     Number result = null;
/* 492 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(rowKey, columnKey);
/*     */     
/* 494 */     if (item != null) {
/* 495 */       result = item.getQ3();
/*     */     }
/* 497 */     return result;
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
/*     */   public int getColumnIndex(Comparable key)
/*     */   {
/* 510 */     return this.data.getColumnIndex(key);
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
/* 523 */     return this.data.getColumnKey(column);
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
/* 534 */     return this.data.getColumnKeys();
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
/*     */   public int getRowIndex(Comparable key)
/*     */   {
/* 548 */     return this.data.getRowIndex(key);
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
/* 561 */     return this.data.getRowKey(row);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getRowKeys()
/*     */   {
/* 572 */     return this.data.getRowKeys();
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
/* 583 */     return this.data.getRowCount();
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
/* 594 */     return this.data.getColumnCount();
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
/* 608 */     return this.minimumRangeValue;
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
/*     */   public double getRangeUpperBound(boolean includeInterval)
/*     */   {
/* 622 */     return this.maximumRangeValue;
/*     */   }
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
/* 634 */     return new Range(this.minimumRangeValue, this.maximumRangeValue);
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
/*     */   public Number getMinRegularValue(int row, int column)
/*     */   {
/* 648 */     Number result = null;
/* 649 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(row, column);
/*     */     
/* 651 */     if (item != null) {
/* 652 */       result = item.getMinRegularValue();
/*     */     }
/* 654 */     return result;
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
/*     */   public Number getMinRegularValue(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 668 */     Number result = null;
/* 669 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(rowKey, columnKey);
/*     */     
/* 671 */     if (item != null) {
/* 672 */       result = item.getMinRegularValue();
/*     */     }
/* 674 */     return result;
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
/*     */   public Number getMaxRegularValue(int row, int column)
/*     */   {
/* 688 */     Number result = null;
/* 689 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(row, column);
/*     */     
/* 691 */     if (item != null) {
/* 692 */       result = item.getMaxRegularValue();
/*     */     }
/* 694 */     return result;
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
/*     */   public Number getMaxRegularValue(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 708 */     Number result = null;
/* 709 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(rowKey, columnKey);
/*     */     
/* 711 */     if (item != null) {
/* 712 */       result = item.getMaxRegularValue();
/*     */     }
/* 714 */     return result;
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
/*     */   public Number getMinOutlier(int row, int column)
/*     */   {
/* 728 */     Number result = null;
/* 729 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(row, column);
/*     */     
/* 731 */     if (item != null) {
/* 732 */       result = item.getMinOutlier();
/*     */     }
/* 734 */     return result;
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
/*     */   public Number getMinOutlier(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 748 */     Number result = null;
/* 749 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(rowKey, columnKey);
/*     */     
/* 751 */     if (item != null) {
/* 752 */       result = item.getMinOutlier();
/*     */     }
/* 754 */     return result;
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
/*     */   public Number getMaxOutlier(int row, int column)
/*     */   {
/* 768 */     Number result = null;
/* 769 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(row, column);
/*     */     
/* 771 */     if (item != null) {
/* 772 */       result = item.getMaxOutlier();
/*     */     }
/* 774 */     return result;
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
/*     */   public Number getMaxOutlier(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 788 */     Number result = null;
/* 789 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(rowKey, columnKey);
/*     */     
/* 791 */     if (item != null) {
/* 792 */       result = item.getMaxOutlier();
/*     */     }
/* 794 */     return result;
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
/*     */   public List getOutliers(int row, int column)
/*     */   {
/* 808 */     List result = null;
/* 809 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(row, column);
/*     */     
/* 811 */     if (item != null) {
/* 812 */       result = item.getOutliers();
/*     */     }
/* 814 */     return result;
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
/*     */   public List getOutliers(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 828 */     List result = null;
/* 829 */     BoxAndWhiskerItem item = (BoxAndWhiskerItem)this.data.getObject(rowKey, columnKey);
/*     */     
/* 831 */     if (item != null) {
/* 832 */       result = item.getOutliers();
/*     */     }
/* 834 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void updateBounds()
/*     */   {
/* 842 */     this.minimumRangeValue = NaN.0D;
/* 843 */     this.minimumRangeValueRow = -1;
/* 844 */     this.minimumRangeValueColumn = -1;
/* 845 */     this.maximumRangeValue = NaN.0D;
/* 846 */     this.maximumRangeValueRow = -1;
/* 847 */     this.maximumRangeValueColumn = -1;
/* 848 */     int rowCount = getRowCount();
/* 849 */     int columnCount = getColumnCount();
/* 850 */     for (int r = 0; r < rowCount; r++) {
/* 851 */       for (int c = 0; c < columnCount; c++) {
/* 852 */         BoxAndWhiskerItem item = getItem(r, c);
/* 853 */         if (item != null) {
/* 854 */           Number min = item.getMinOutlier();
/* 855 */           if (min != null) {
/* 856 */             double minv = min.doubleValue();
/* 857 */             if ((!Double.isNaN(minv)) && (
/* 858 */               (minv < this.minimumRangeValue) || (Double.isNaN(this.minimumRangeValue))))
/*     */             {
/* 860 */               this.minimumRangeValue = minv;
/* 861 */               this.minimumRangeValueRow = r;
/* 862 */               this.minimumRangeValueColumn = c;
/*     */             }
/*     */           }
/*     */           
/* 866 */           Number max = item.getMaxOutlier();
/* 867 */           if (max != null) {
/* 868 */             double maxv = max.doubleValue();
/* 869 */             if ((!Double.isNaN(maxv)) && (
/* 870 */               (maxv > this.maximumRangeValue) || (Double.isNaN(this.maximumRangeValue))))
/*     */             {
/* 872 */               this.maximumRangeValue = maxv;
/* 873 */               this.maximumRangeValueRow = r;
/* 874 */               this.maximumRangeValueColumn = c;
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 891 */     if (obj == this) {
/* 892 */       return true;
/*     */     }
/* 894 */     if ((obj instanceof DefaultBoxAndWhiskerCategoryDataset)) {
/* 895 */       DefaultBoxAndWhiskerCategoryDataset dataset = (DefaultBoxAndWhiskerCategoryDataset)obj;
/*     */       
/* 897 */       return ObjectUtilities.equal(this.data, dataset.data);
/*     */     }
/* 899 */     return false;
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
/* 910 */     DefaultBoxAndWhiskerCategoryDataset clone = (DefaultBoxAndWhiskerCategoryDataset)super.clone();
/*     */     
/* 912 */     clone.data = ((KeyedObjects2D)this.data.clone());
/* 913 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/statistics/DefaultBoxAndWhiskerCategoryDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */