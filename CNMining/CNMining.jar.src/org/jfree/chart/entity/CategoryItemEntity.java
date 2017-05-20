/*     */ package org.jfree.chart.entity;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CategoryItemEntity
/*     */   extends ChartEntity
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8657249457902337349L;
/*     */   private CategoryDataset dataset;
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   private int series;
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   private Object category;
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   private int categoryIndex;
/*     */   private Comparable rowKey;
/*     */   private Comparable columnKey;
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public CategoryItemEntity(Shape area, String toolTipText, String urlText, CategoryDataset dataset, int series, Object category, int categoryIndex)
/*     */   {
/* 129 */     super(area, toolTipText, urlText);
/* 130 */     if (dataset == null) {
/* 131 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*     */     }
/* 133 */     this.dataset = dataset;
/* 134 */     this.series = series;
/* 135 */     this.category = category;
/* 136 */     this.categoryIndex = categoryIndex;
/* 137 */     this.rowKey = dataset.getRowKey(series);
/* 138 */     this.columnKey = dataset.getColumnKey(categoryIndex);
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
/*     */   public CategoryItemEntity(Shape area, String toolTipText, String urlText, CategoryDataset dataset, Comparable rowKey, Comparable columnKey)
/*     */   {
/* 155 */     super(area, toolTipText, urlText);
/* 156 */     if (dataset == null) {
/* 157 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*     */     }
/* 159 */     this.dataset = dataset;
/* 160 */     this.rowKey = rowKey;
/* 161 */     this.columnKey = columnKey;
/*     */     
/*     */ 
/* 164 */     this.series = dataset.getRowIndex(rowKey);
/* 165 */     this.category = columnKey;
/* 166 */     this.categoryIndex = dataset.getColumnIndex(columnKey);
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
/*     */   public CategoryDataset getDataset()
/*     */   {
/* 179 */     return this.dataset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataset(CategoryDataset dataset)
/*     */   {
/* 190 */     if (dataset == null) {
/* 191 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*     */     }
/* 193 */     this.dataset = dataset;
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
/*     */   public Comparable getRowKey()
/*     */   {
/* 206 */     return this.rowKey;
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
/*     */   public void setRowKey(Comparable rowKey)
/*     */   {
/* 219 */     this.rowKey = rowKey;
/*     */     
/* 221 */     this.series = this.dataset.getRowIndex(rowKey);
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
/*     */   public Comparable getColumnKey()
/*     */   {
/* 234 */     return this.columnKey;
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
/*     */   public void setColumnKey(Comparable columnKey)
/*     */   {
/* 247 */     this.columnKey = columnKey;
/*     */     
/* 249 */     this.category = columnKey;
/* 250 */     this.categoryIndex = this.dataset.getColumnIndex(columnKey);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public int getSeries()
/*     */   {
/* 264 */     return this.series;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public void setSeries(int series)
/*     */   {
/* 278 */     this.series = series;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public Object getCategory()
/*     */   {
/* 293 */     return this.category;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public void setCategory(Object category)
/*     */   {
/* 306 */     this.category = category;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public int getCategoryIndex()
/*     */   {
/* 320 */     return this.categoryIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public void setCategoryIndex(int index)
/*     */   {
/* 334 */     this.categoryIndex = index;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 344 */     return "CategoryItemEntity: rowKey=" + this.rowKey + ", columnKey=" + this.columnKey + ", dataset=" + this.dataset;
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
/* 356 */     if (obj == this) {
/* 357 */       return true;
/*     */     }
/* 359 */     if (!(obj instanceof CategoryItemEntity)) {
/* 360 */       return false;
/*     */     }
/* 362 */     CategoryItemEntity that = (CategoryItemEntity)obj;
/* 363 */     if (!this.rowKey.equals(that.rowKey)) {
/* 364 */       return false;
/*     */     }
/* 366 */     if (!this.columnKey.equals(that.columnKey)) {
/* 367 */       return false;
/*     */     }
/* 369 */     if (!ObjectUtilities.equal(this.dataset, that.dataset)) {
/* 370 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 374 */     if (this.categoryIndex != that.categoryIndex) {
/* 375 */       return false;
/*     */     }
/* 377 */     if (this.series != that.series) {
/* 378 */       return false;
/*     */     }
/* 380 */     if (!ObjectUtilities.equal(this.category, that.category)) {
/* 381 */       return false;
/*     */     }
/* 383 */     return super.equals(obj);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/entity/CategoryItemEntity.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */