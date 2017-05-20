/*     */ package org.jfree.data.category;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import org.jfree.chart.util.ResourceBundleWrapper;
/*     */ import org.jfree.data.DataUtilities;
/*     */ import org.jfree.data.UnknownKeyException;
/*     */ import org.jfree.data.general.AbstractSeriesDataset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultIntervalCategoryDataset
/*     */   extends AbstractSeriesDataset
/*     */   implements IntervalCategoryDataset
/*     */ {
/*     */   private Comparable[] seriesKeys;
/*     */   private Comparable[] categoryKeys;
/*     */   private Number[][] startData;
/*     */   private Number[][] endData;
/*     */   
/*     */   public DefaultIntervalCategoryDataset(double[][] starts, double[][] ends)
/*     */   {
/*  93 */     this(DataUtilities.createNumberArray2D(starts), DataUtilities.createNumberArray2D(ends));
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
/*     */   public DefaultIntervalCategoryDataset(Number[][] starts, Number[][] ends)
/*     */   {
/* 109 */     this(null, null, starts, ends);
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
/*     */   public DefaultIntervalCategoryDataset(String[] seriesNames, Number[][] starts, Number[][] ends)
/*     */   {
/* 128 */     this(seriesNames, null, starts, ends);
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
/*     */   public DefaultIntervalCategoryDataset(Comparable[] seriesKeys, Comparable[] categoryKeys, Number[][] starts, Number[][] ends)
/*     */   {
/* 149 */     this.startData = starts;
/* 150 */     this.endData = ends;
/*     */     
/* 152 */     if ((starts != null) && (ends != null))
/*     */     {
/* 154 */       String baseName = "org.jfree.data.resources.DataPackageResources";
/* 155 */       ResourceBundle resources = ResourceBundleWrapper.getBundle(baseName);
/*     */       
/*     */ 
/* 158 */       int seriesCount = starts.length;
/* 159 */       if (seriesCount != ends.length) {
/* 160 */         String errMsg = "DefaultIntervalCategoryDataset: the number of series in the start value dataset does not match the number of series in the end value dataset.";
/*     */         
/*     */ 
/*     */ 
/* 164 */         throw new IllegalArgumentException(errMsg);
/*     */       }
/* 166 */       if (seriesCount > 0)
/*     */       {
/*     */ 
/* 169 */         if (seriesKeys != null)
/*     */         {
/* 171 */           if (seriesKeys.length != seriesCount) {
/* 172 */             throw new IllegalArgumentException("The number of series keys does not match the number of series in the data.");
/*     */           }
/*     */           
/*     */ 
/*     */ 
/* 177 */           this.seriesKeys = seriesKeys;
/*     */         }
/*     */         else {
/* 180 */           String prefix = resources.getString("series.default-prefix") + " ";
/*     */           
/* 182 */           this.seriesKeys = generateKeys(seriesCount, prefix);
/*     */         }
/*     */         
/*     */ 
/* 186 */         int categoryCount = starts[0].length;
/* 187 */         if (categoryCount != ends[0].length) {
/* 188 */           String errMsg = "DefaultIntervalCategoryDataset: the number of categories in the start value dataset does not match the number of categories in the end value dataset.";
/*     */           
/*     */ 
/*     */ 
/* 192 */           throw new IllegalArgumentException(errMsg);
/*     */         }
/* 194 */         if (categoryKeys != null) {
/* 195 */           if (categoryKeys.length != categoryCount) {
/* 196 */             throw new IllegalArgumentException("The number of category keys does not match the number of categories in the data.");
/*     */           }
/*     */           
/*     */ 
/* 200 */           this.categoryKeys = categoryKeys;
/*     */         }
/*     */         else {
/* 203 */           String prefix = resources.getString("categories.default-prefix") + " ";
/*     */           
/* 205 */           this.categoryKeys = generateKeys(categoryCount, prefix);
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 210 */         this.seriesKeys = new Comparable[0];
/* 211 */         this.categoryKeys = new Comparable[0];
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
/*     */   public int getSeriesCount()
/*     */   {
/* 226 */     int result = 0;
/* 227 */     if (this.startData != null) {
/* 228 */       result = this.startData.length;
/*     */     }
/* 230 */     return result;
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
/*     */   public int getSeriesIndex(Comparable seriesKey)
/*     */   {
/* 244 */     int result = -1;
/* 245 */     for (int i = 0; i < this.seriesKeys.length; i++) {
/* 246 */       if (seriesKey.equals(this.seriesKeys[i])) {
/* 247 */         result = i;
/* 248 */         break;
/*     */       }
/*     */     }
/* 251 */     return result;
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
/*     */   public Comparable getSeriesKey(int series)
/*     */   {
/* 264 */     if ((series >= getSeriesCount()) || (series < 0)) {
/* 265 */       throw new IllegalArgumentException("No such series : " + series);
/*     */     }
/* 267 */     return this.seriesKeys[series];
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
/*     */   public void setSeriesKeys(Comparable[] seriesKeys)
/*     */   {
/* 280 */     if (seriesKeys == null) {
/* 281 */       throw new IllegalArgumentException("Null 'seriesKeys' argument.");
/*     */     }
/* 283 */     if (seriesKeys.length != getSeriesCount()) {
/* 284 */       throw new IllegalArgumentException("The number of series keys does not match the data.");
/*     */     }
/*     */     
/* 287 */     this.seriesKeys = seriesKeys;
/* 288 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getCategoryCount()
/*     */   {
/* 299 */     int result = 0;
/* 300 */     if ((this.startData != null) && 
/* 301 */       (getSeriesCount() > 0)) {
/* 302 */       result = this.startData[0].length;
/*     */     }
/*     */     
/* 305 */     return result;
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
/*     */   public List getColumnKeys()
/*     */   {
/* 319 */     if (this.categoryKeys == null) {
/* 320 */       return new ArrayList();
/*     */     }
/*     */     
/* 323 */     return Collections.unmodifiableList(Arrays.asList(this.categoryKeys));
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
/*     */   public void setCategoryKeys(Comparable[] categoryKeys)
/*     */   {
/* 338 */     if (categoryKeys == null) {
/* 339 */       throw new IllegalArgumentException("Null 'categoryKeys' argument.");
/*     */     }
/* 341 */     if (categoryKeys.length != getCategoryCount()) {
/* 342 */       throw new IllegalArgumentException("The number of categories does not match the data.");
/*     */     }
/*     */     
/* 345 */     for (int i = 0; i < categoryKeys.length; i++) {
/* 346 */       if (categoryKeys[i] == null) {
/* 347 */         throw new IllegalArgumentException("DefaultIntervalCategoryDataset.setCategoryKeys(): null category not permitted.");
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 352 */     this.categoryKeys = categoryKeys;
/* 353 */     fireDatasetChanged();
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
/*     */   public Number getValue(Comparable series, Comparable category)
/*     */   {
/* 370 */     int seriesIndex = getSeriesIndex(series);
/* 371 */     if (seriesIndex < 0) {
/* 372 */       throw new UnknownKeyException("Unknown 'series' key.");
/*     */     }
/* 374 */     int itemIndex = getColumnIndex(category);
/* 375 */     if (itemIndex < 0) {
/* 376 */       throw new UnknownKeyException("Unknown 'category' key.");
/*     */     }
/* 378 */     return getValue(seriesIndex, itemIndex);
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
/*     */   public Number getValue(int series, int category)
/*     */   {
/* 395 */     return getEndValue(series, category);
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
/*     */   public Number getStartValue(Comparable series, Comparable category)
/*     */   {
/* 410 */     int seriesIndex = getSeriesIndex(series);
/* 411 */     if (seriesIndex < 0) {
/* 412 */       throw new UnknownKeyException("Unknown 'series' key.");
/*     */     }
/* 414 */     int itemIndex = getColumnIndex(category);
/* 415 */     if (itemIndex < 0) {
/* 416 */       throw new UnknownKeyException("Unknown 'category' key.");
/*     */     }
/* 418 */     return getStartValue(seriesIndex, itemIndex);
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
/*     */   public Number getStartValue(int series, int category)
/*     */   {
/* 435 */     if ((series < 0) || (series >= getSeriesCount())) {
/* 436 */       throw new IllegalArgumentException("DefaultIntervalCategoryDataset.getValue(): series index out of range.");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 441 */     if ((category < 0) || (category >= getCategoryCount())) {
/* 442 */       throw new IllegalArgumentException("DefaultIntervalCategoryDataset.getValue(): category index out of range.");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 448 */     return this.startData[series][category];
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
/*     */   public Number getEndValue(Comparable series, Comparable category)
/*     */   {
/* 463 */     int seriesIndex = getSeriesIndex(series);
/* 464 */     if (seriesIndex < 0) {
/* 465 */       throw new UnknownKeyException("Unknown 'series' key.");
/*     */     }
/* 467 */     int itemIndex = getColumnIndex(category);
/* 468 */     if (itemIndex < 0) {
/* 469 */       throw new UnknownKeyException("Unknown 'category' key.");
/*     */     }
/* 471 */     return getEndValue(seriesIndex, itemIndex);
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
/*     */   public Number getEndValue(int series, int category)
/*     */   {
/* 485 */     if ((series < 0) || (series >= getSeriesCount())) {
/* 486 */       throw new IllegalArgumentException("DefaultIntervalCategoryDataset.getValue(): series index out of range.");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 491 */     if ((category < 0) || (category >= getCategoryCount())) {
/* 492 */       throw new IllegalArgumentException("DefaultIntervalCategoryDataset.getValue(): category index out of range.");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 497 */     return this.endData[series][category];
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
/*     */   public void setStartValue(int series, Comparable category, Number value)
/*     */   {
/* 513 */     if ((series < 0) || (series > getSeriesCount() - 1)) {
/* 514 */       throw new IllegalArgumentException("DefaultIntervalCategoryDataset.setValue: series outside valid range.");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 520 */     int categoryIndex = getCategoryIndex(category);
/* 521 */     if (categoryIndex < 0) {
/* 522 */       throw new IllegalArgumentException("DefaultIntervalCategoryDataset.setValue: unrecognised category.");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 528 */     this.startData[series][categoryIndex] = value;
/* 529 */     fireDatasetChanged();
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
/*     */   public void setEndValue(int series, Comparable category, Number value)
/*     */   {
/* 546 */     if ((series < 0) || (series > getSeriesCount() - 1)) {
/* 547 */       throw new IllegalArgumentException("DefaultIntervalCategoryDataset.setValue: series outside valid range.");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 553 */     int categoryIndex = getCategoryIndex(category);
/* 554 */     if (categoryIndex < 0) {
/* 555 */       throw new IllegalArgumentException("DefaultIntervalCategoryDataset.setValue: unrecognised category.");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 561 */     this.endData[series][categoryIndex] = value;
/* 562 */     fireDatasetChanged();
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
/*     */   public int getCategoryIndex(Comparable category)
/*     */   {
/* 576 */     int result = -1;
/* 577 */     for (int i = 0; i < this.categoryKeys.length; i++) {
/* 578 */       if (category.equals(this.categoryKeys[i])) {
/* 579 */         result = i;
/* 580 */         break;
/*     */       }
/*     */     }
/* 583 */     return result;
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
/*     */   private Comparable[] generateKeys(int count, String prefix)
/*     */   {
/* 596 */     Comparable[] result = new Comparable[count];
/*     */     
/* 598 */     for (int i = 0; i < count; i++) {
/* 599 */       String name = prefix + (i + 1);
/* 600 */       result[i] = name;
/*     */     }
/* 602 */     return result;
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
/* 615 */     return this.categoryKeys[column];
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
/*     */   public int getColumnIndex(Comparable columnKey)
/*     */   {
/* 628 */     if (columnKey == null) {
/* 629 */       throw new IllegalArgumentException("Null 'columnKey' argument.");
/*     */     }
/* 631 */     return getCategoryIndex(columnKey);
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
/*     */   public int getRowIndex(Comparable rowKey)
/*     */   {
/* 644 */     return getSeriesIndex(rowKey);
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
/*     */   public List getRowKeys()
/*     */   {
/* 658 */     if (this.seriesKeys == null) {
/* 659 */       return new ArrayList();
/*     */     }
/*     */     
/* 662 */     return Collections.unmodifiableList(Arrays.asList(this.seriesKeys));
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
/*     */   public Comparable getRowKey(int row)
/*     */   {
/* 676 */     if ((row >= getRowCount()) || (row < 0)) {
/* 677 */       throw new IllegalArgumentException("The 'row' argument is out of bounds.");
/*     */     }
/*     */     
/* 680 */     return this.seriesKeys[row];
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
/*     */   public int getColumnCount()
/*     */   {
/* 693 */     return this.categoryKeys.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRowCount()
/*     */   {
/* 705 */     return this.seriesKeys.length;
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
/* 716 */     if (obj == this) {
/* 717 */       return true;
/*     */     }
/* 719 */     if (!(obj instanceof DefaultIntervalCategoryDataset)) {
/* 720 */       return false;
/*     */     }
/* 722 */     DefaultIntervalCategoryDataset that = (DefaultIntervalCategoryDataset)obj;
/*     */     
/* 724 */     if (!Arrays.equals(this.seriesKeys, that.seriesKeys)) {
/* 725 */       return false;
/*     */     }
/* 727 */     if (!Arrays.equals(this.categoryKeys, that.categoryKeys)) {
/* 728 */       return false;
/*     */     }
/* 730 */     if (!equal(this.startData, that.startData)) {
/* 731 */       return false;
/*     */     }
/* 733 */     if (!equal(this.endData, that.endData)) {
/* 734 */       return false;
/*     */     }
/*     */     
/* 737 */     return true;
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
/* 749 */     DefaultIntervalCategoryDataset clone = (DefaultIntervalCategoryDataset)super.clone();
/*     */     
/* 751 */     clone.categoryKeys = ((Comparable[])this.categoryKeys.clone());
/* 752 */     clone.seriesKeys = ((Comparable[])this.seriesKeys.clone());
/* 753 */     clone.startData = clone(this.startData);
/* 754 */     clone.endData = clone(this.endData);
/* 755 */     return clone;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static boolean equal(Number[][] array1, Number[][] array2)
/*     */   {
/* 767 */     if (array1 == null) {
/* 768 */       return array2 == null;
/*     */     }
/* 770 */     if (array2 == null) {
/* 771 */       return false;
/*     */     }
/* 773 */     if (array1.length != array2.length) {
/* 774 */       return false;
/*     */     }
/* 776 */     for (int i = 0; i < array1.length; i++) {
/* 777 */       if (!Arrays.equals(array1[i], array2[i])) {
/* 778 */         return false;
/*     */       }
/*     */     }
/* 781 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static Number[][] clone(Number[][] array)
/*     */   {
/* 792 */     if (array == null) {
/* 793 */       throw new IllegalArgumentException("Null 'array' argument.");
/*     */     }
/* 795 */     Number[][] result = new Number[array.length][];
/* 796 */     for (int i = 0; i < array.length; i++) {
/* 797 */       Number[] child = array[i];
/* 798 */       Number[] copychild = new Number[child.length];
/* 799 */       System.arraycopy(child, 0, copychild, 0, child.length);
/* 800 */       result[i] = copychild;
/*     */     }
/* 802 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public List getSeries()
/*     */   {
/* 813 */     if (this.seriesKeys == null) {
/* 814 */       return new ArrayList();
/*     */     }
/*     */     
/* 817 */     return Collections.unmodifiableList(Arrays.asList(this.seriesKeys));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public List getCategories()
/*     */   {
/* 829 */     return getColumnKeys();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public int getItemCount()
/*     */   {
/* 840 */     return this.categoryKeys.length;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/category/DefaultIntervalCategoryDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */