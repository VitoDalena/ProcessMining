/*     */ package org.jfree.data.category;
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
/*     */ 
/*     */ public class SlidingCategoryDataset
/*     */   extends AbstractDataset
/*     */   implements CategoryDataset
/*     */ {
/*     */   private CategoryDataset underlying;
/*     */   private int firstCategoryIndex;
/*     */   private int maximumCategoryCount;
/*     */   
/*     */   public SlidingCategoryDataset(CategoryDataset underlying, int firstColumn, int maxColumns)
/*     */   {
/*  83 */     this.underlying = underlying;
/*  84 */     this.firstCategoryIndex = firstColumn;
/*  85 */     this.maximumCategoryCount = maxColumns;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CategoryDataset getUnderlyingDataset()
/*     */   {
/*  94 */     return this.underlying;
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
/* 105 */     return this.firstCategoryIndex;
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
/* 118 */     if ((first < 0) || (first >= this.underlying.getColumnCount())) {
/* 119 */       throw new IllegalArgumentException("Invalid index.");
/*     */     }
/* 121 */     this.firstCategoryIndex = first;
/* 122 */     fireDatasetChanged();
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
/* 133 */     return this.maximumCategoryCount;
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
/* 145 */     if (max < 0) {
/* 146 */       throw new IllegalArgumentException("Requires 'max' >= 0.");
/*     */     }
/* 148 */     this.maximumCategoryCount = max;
/* 149 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int lastCategoryIndex()
/*     */   {
/* 158 */     if (this.maximumCategoryCount == 0) {
/* 159 */       return -1;
/*     */     }
/* 161 */     return Math.min(this.firstCategoryIndex + this.maximumCategoryCount, this.underlying.getColumnCount()) - 1;
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
/* 173 */     int index = this.underlying.getColumnIndex(key);
/* 174 */     if ((index >= this.firstCategoryIndex) && (index <= lastCategoryIndex())) {
/* 175 */       return index - this.firstCategoryIndex;
/*     */     }
/* 177 */     return -1;
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
/* 190 */     return this.underlying.getColumnKey(column + this.firstCategoryIndex);
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
/* 201 */     List result = new ArrayList();
/* 202 */     int last = lastCategoryIndex();
/* 203 */     for (int i = this.firstCategoryIndex; i <= last; i++) {
/* 204 */       result.add(this.underlying.getColumnKey(i));
/*     */     }
/* 206 */     return Collections.unmodifiableList(result);
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
/* 217 */     return this.underlying.getRowIndex(key);
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
/* 230 */     return this.underlying.getRowKey(row);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getRowKeys()
/*     */   {
/* 239 */     return this.underlying.getRowKeys();
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
/* 253 */     int r = getRowIndex(rowKey);
/* 254 */     int c = getColumnIndex(columnKey);
/* 255 */     if (c != -1) {
/* 256 */       return this.underlying.getValue(r, c + this.firstCategoryIndex);
/*     */     }
/*     */     
/* 259 */     throw new UnknownKeyException("Unknown columnKey: " + columnKey);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getColumnCount()
/*     */   {
/* 269 */     int last = lastCategoryIndex();
/* 270 */     if (last == -1) {
/* 271 */       return 0;
/*     */     }
/*     */     
/* 274 */     return Math.max(last - this.firstCategoryIndex + 1, 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRowCount()
/*     */   {
/* 284 */     return this.underlying.getRowCount();
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
/* 296 */     return this.underlying.getValue(row, column + this.firstCategoryIndex);
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
/* 308 */     if (obj == this) {
/* 309 */       return true;
/*     */     }
/* 311 */     if (!(obj instanceof SlidingCategoryDataset)) {
/* 312 */       return false;
/*     */     }
/* 314 */     SlidingCategoryDataset that = (SlidingCategoryDataset)obj;
/* 315 */     if (this.firstCategoryIndex != that.firstCategoryIndex) {
/* 316 */       return false;
/*     */     }
/* 318 */     if (this.maximumCategoryCount != that.maximumCategoryCount) {
/* 319 */       return false;
/*     */     }
/* 321 */     if (!this.underlying.equals(that.underlying)) {
/* 322 */       return false;
/*     */     }
/* 324 */     return true;
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
/* 342 */     SlidingCategoryDataset clone = (SlidingCategoryDataset)super.clone();
/* 343 */     if ((this.underlying instanceof PublicCloneable)) {
/* 344 */       PublicCloneable pc = (PublicCloneable)this.underlying;
/* 345 */       clone.underlying = ((CategoryDataset)pc.clone());
/*     */     }
/* 347 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/category/SlidingCategoryDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */