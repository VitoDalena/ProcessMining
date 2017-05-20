/*     */ package org.jfree.data.category;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import org.jfree.data.DefaultKeyedValues2D;
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
/*     */ public class DefaultCategoryDataset
/*     */   extends AbstractDataset
/*     */   implements CategoryDataset, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8168173757291644622L;
/*     */   private DefaultKeyedValues2D data;
/*     */   
/*     */   public DefaultCategoryDataset()
/*     */   {
/*  76 */     this.data = new DefaultKeyedValues2D();
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
/*  87 */     return this.data.getRowCount();
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
/*  98 */     return this.data.getColumnCount();
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
/* 113 */     return this.data.getValue(row, column);
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
/*     */   public Comparable getRowKey(int row)
/*     */   {
/* 128 */     return this.data.getRowKey(row);
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
/* 142 */     return this.data.getRowIndex(key);
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
/* 153 */     return this.data.getRowKeys();
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
/* 166 */     return this.data.getColumnKey(column);
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
/*     */   public int getColumnIndex(Comparable key)
/*     */   {
/* 180 */     return this.data.getColumnIndex(key);
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
/* 191 */     return this.data.getColumnKeys();
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
/*     */   public Number getValue(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 207 */     return this.data.getValue(rowKey, columnKey);
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
/*     */   public void addValue(Number value, Comparable rowKey, Comparable columnKey)
/*     */   {
/* 222 */     this.data.addValue(value, rowKey, columnKey);
/* 223 */     fireDatasetChanged();
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
/*     */   public void addValue(double value, Comparable rowKey, Comparable columnKey)
/*     */   {
/* 237 */     addValue(new Double(value), rowKey, columnKey);
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
/*     */   public void setValue(Number value, Comparable rowKey, Comparable columnKey)
/*     */   {
/* 252 */     this.data.setValue(value, rowKey, columnKey);
/* 253 */     fireDatasetChanged();
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
/*     */   public void setValue(double value, Comparable rowKey, Comparable columnKey)
/*     */   {
/* 268 */     setValue(new Double(value), rowKey, columnKey);
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
/*     */   public void incrementValue(double value, Comparable rowKey, Comparable columnKey)
/*     */   {
/* 284 */     double existing = 0.0D;
/* 285 */     Number n = getValue(rowKey, columnKey);
/* 286 */     if (n != null) {
/* 287 */       existing = n.doubleValue();
/*     */     }
/* 289 */     setValue(existing + value, rowKey, columnKey);
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
/*     */   public void removeValue(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 302 */     this.data.removeValue(rowKey, columnKey);
/* 303 */     fireDatasetChanged();
/*     */   }
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
/* 315 */     this.data.removeRow(rowIndex);
/* 316 */     fireDatasetChanged();
/*     */   }
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
/* 328 */     this.data.removeRow(rowKey);
/* 329 */     fireDatasetChanged();
/*     */   }
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
/* 341 */     this.data.removeColumn(columnIndex);
/* 342 */     fireDatasetChanged();
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
/*     */   public void removeColumn(Comparable columnKey)
/*     */   {
/* 357 */     this.data.removeColumn(columnKey);
/* 358 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 366 */     this.data.clear();
/* 367 */     fireDatasetChanged();
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
/* 378 */     if (obj == this) {
/* 379 */       return true;
/*     */     }
/* 381 */     if (!(obj instanceof CategoryDataset)) {
/* 382 */       return false;
/*     */     }
/* 384 */     CategoryDataset that = (CategoryDataset)obj;
/* 385 */     if (!getRowKeys().equals(that.getRowKeys())) {
/* 386 */       return false;
/*     */     }
/* 388 */     if (!getColumnKeys().equals(that.getColumnKeys())) {
/* 389 */       return false;
/*     */     }
/* 391 */     int rowCount = getRowCount();
/* 392 */     int colCount = getColumnCount();
/* 393 */     for (int r = 0; r < rowCount; r++) {
/* 394 */       for (int c = 0; c < colCount; c++) {
/* 395 */         Number v1 = getValue(r, c);
/* 396 */         Number v2 = that.getValue(r, c);
/* 397 */         if (v1 == null) {
/* 398 */           if (v2 != null) {
/* 399 */             return false;
/*     */           }
/*     */         }
/* 402 */         else if (!v1.equals(v2)) {
/* 403 */           return false;
/*     */         }
/*     */       }
/*     */     }
/* 407 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 416 */     return this.data.hashCode();
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
/* 428 */     DefaultCategoryDataset clone = (DefaultCategoryDataset)super.clone();
/* 429 */     clone.data = ((DefaultKeyedValues2D)this.data.clone());
/* 430 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/category/DefaultCategoryDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */