/*     */ package org.jfree.data;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
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
/*     */ public class DefaultKeyedValues2D
/*     */   implements KeyedValues2D, PublicCloneable, Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -5514169970951994748L;
/*     */   private List rowKeys;
/*     */   private List columnKeys;
/*     */   private List rows;
/*     */   private boolean sortRowKeys;
/*     */   
/*     */   public DefaultKeyedValues2D()
/*     */   {
/*  95 */     this(false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultKeyedValues2D(boolean sortRowKeys)
/*     */   {
/* 104 */     this.rowKeys = new ArrayList();
/* 105 */     this.columnKeys = new ArrayList();
/* 106 */     this.rows = new ArrayList();
/* 107 */     this.sortRowKeys = sortRowKeys;
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
/* 118 */     return this.rowKeys.size();
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
/* 129 */     return this.columnKeys.size();
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
/*     */   public Number getValue(int row, int column)
/*     */   {
/* 143 */     Number result = null;
/* 144 */     DefaultKeyedValues rowData = (DefaultKeyedValues)this.rows.get(row);
/* 145 */     if (rowData != null) {
/* 146 */       Comparable columnKey = (Comparable)this.columnKeys.get(column);
/*     */       
/*     */ 
/* 149 */       int index = rowData.getIndex(columnKey);
/* 150 */       if (index >= 0) {
/* 151 */         result = rowData.getValue(index);
/*     */       }
/*     */     }
/* 154 */     return result;
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
/* 168 */     return (Comparable)this.rowKeys.get(row);
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
/* 182 */     if (key == null) {
/* 183 */       throw new IllegalArgumentException("Null 'key' argument.");
/*     */     }
/* 185 */     if (this.sortRowKeys) {
/* 186 */       return Collections.binarySearch(this.rowKeys, key);
/*     */     }
/*     */     
/* 189 */     return this.rowKeys.indexOf(key);
/*     */   }
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
/* 201 */     return Collections.unmodifiableList(this.rowKeys);
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
/*     */   public Comparable getColumnKey(int column)
/*     */   {
/* 216 */     return (Comparable)this.columnKeys.get(column);
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
/* 230 */     if (key == null) {
/* 231 */       throw new IllegalArgumentException("Null 'key' argument.");
/*     */     }
/* 233 */     return this.columnKeys.indexOf(key);
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
/* 244 */     return Collections.unmodifiableList(this.columnKeys);
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
/*     */   public Number getValue(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 261 */     if (rowKey == null) {
/* 262 */       throw new IllegalArgumentException("Null 'rowKey' argument.");
/*     */     }
/* 264 */     if (columnKey == null) {
/* 265 */       throw new IllegalArgumentException("Null 'columnKey' argument.");
/*     */     }
/*     */     
/*     */ 
/* 269 */     if (!this.columnKeys.contains(columnKey)) {
/* 270 */       throw new UnknownKeyException("Unrecognised columnKey: " + columnKey);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 277 */     int row = getRowIndex(rowKey);
/* 278 */     if (row >= 0) {
/* 279 */       DefaultKeyedValues rowData = (DefaultKeyedValues)this.rows.get(row);
/*     */       
/* 281 */       int col = rowData.getIndex(columnKey);
/* 282 */       return col >= 0 ? rowData.getValue(col) : null;
/*     */     }
/*     */     
/* 285 */     throw new UnknownKeyException("Unrecognised rowKey: " + rowKey);
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
/*     */   public void addValue(Number value, Comparable rowKey, Comparable columnKey)
/*     */   {
/* 303 */     setValue(value, rowKey, columnKey);
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
/*     */   public void setValue(Number value, Comparable rowKey, Comparable columnKey)
/*     */   {
/* 320 */     int rowIndex = getRowIndex(rowKey);
/*     */     DefaultKeyedValues row;
/* 322 */     DefaultKeyedValues row; if (rowIndex >= 0) {
/* 323 */       row = (DefaultKeyedValues)this.rows.get(rowIndex);
/*     */     }
/*     */     else {
/* 326 */       row = new DefaultKeyedValues();
/* 327 */       if (this.sortRowKeys) {
/* 328 */         rowIndex = -rowIndex - 1;
/* 329 */         this.rowKeys.add(rowIndex, rowKey);
/* 330 */         this.rows.add(rowIndex, row);
/*     */       }
/*     */       else {
/* 333 */         this.rowKeys.add(rowKey);
/* 334 */         this.rows.add(row);
/*     */       }
/*     */     }
/* 337 */     row.setValue(columnKey, value);
/*     */     
/* 339 */     int columnIndex = this.columnKeys.indexOf(columnKey);
/* 340 */     if (columnIndex < 0) {
/* 341 */       this.columnKeys.add(columnKey);
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
/*     */   public void removeValue(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 356 */     setValue(null, rowKey, columnKey);
/*     */     
/*     */ 
/* 359 */     boolean allNull = true;
/* 360 */     int rowIndex = getRowIndex(rowKey);
/* 361 */     DefaultKeyedValues row = (DefaultKeyedValues)this.rows.get(rowIndex);
/*     */     
/* 363 */     int item = 0; for (int itemCount = row.getItemCount(); item < itemCount; 
/* 364 */         item++) {
/* 365 */       if (row.getValue(item) != null) {
/* 366 */         allNull = false;
/* 367 */         break;
/*     */       }
/*     */     }
/*     */     
/* 371 */     if (allNull) {
/* 372 */       this.rowKeys.remove(rowIndex);
/* 373 */       this.rows.remove(rowIndex);
/*     */     }
/*     */     
/*     */ 
/* 377 */     allNull = true;
/*     */     
/*     */ 
/* 380 */     int item = 0; for (int itemCount = this.rows.size(); item < itemCount; 
/* 381 */         item++) {
/* 382 */       row = (DefaultKeyedValues)this.rows.get(item);
/* 383 */       int columnIndex = row.getIndex(columnKey);
/* 384 */       if ((columnIndex >= 0) && (row.getValue(columnIndex) != null)) {
/* 385 */         allNull = false;
/* 386 */         break;
/*     */       }
/*     */     }
/*     */     
/* 390 */     if (allNull) {
/* 391 */       int item = 0; for (int itemCount = this.rows.size(); item < itemCount; 
/* 392 */           item++) {
/* 393 */         row = (DefaultKeyedValues)this.rows.get(item);
/* 394 */         int columnIndex = row.getIndex(columnKey);
/* 395 */         if (columnIndex >= 0) {
/* 396 */           row.removeValue(columnIndex);
/*     */         }
/*     */       }
/* 399 */       this.columnKeys.remove(columnKey);
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
/*     */   public void removeRow(int rowIndex)
/*     */   {
/* 412 */     this.rowKeys.remove(rowIndex);
/* 413 */     this.rows.remove(rowIndex);
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
/*     */   public void removeRow(Comparable rowKey)
/*     */   {
/* 428 */     if (rowKey == null) {
/* 429 */       throw new IllegalArgumentException("Null 'rowKey' argument.");
/*     */     }
/* 431 */     int index = getRowIndex(rowKey);
/* 432 */     if (index >= 0) {
/* 433 */       removeRow(index);
/*     */     }
/*     */     else {
/* 436 */       throw new UnknownKeyException("Unknown key: " + rowKey);
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
/*     */   public void removeColumn(int columnIndex)
/*     */   {
/* 449 */     Comparable columnKey = getColumnKey(columnIndex);
/* 450 */     removeColumn(columnKey);
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
/*     */   public void removeColumn(Comparable columnKey)
/*     */   {
/* 467 */     if (columnKey == null) {
/* 468 */       throw new IllegalArgumentException("Null 'columnKey' argument.");
/*     */     }
/* 470 */     if (!this.columnKeys.contains(columnKey)) {
/* 471 */       throw new UnknownKeyException("Unknown key: " + columnKey);
/*     */     }
/* 473 */     Iterator iterator = this.rows.iterator();
/* 474 */     while (iterator.hasNext()) {
/* 475 */       DefaultKeyedValues rowData = (DefaultKeyedValues)iterator.next();
/* 476 */       int index = rowData.getIndex(columnKey);
/* 477 */       if (index >= 0) {
/* 478 */         rowData.removeValue(columnKey);
/*     */       }
/*     */     }
/* 481 */     this.columnKeys.remove(columnKey);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 488 */     this.rowKeys.clear();
/* 489 */     this.columnKeys.clear();
/* 490 */     this.rows.clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 502 */     if (o == null) {
/* 503 */       return false;
/*     */     }
/* 505 */     if (o == this) {
/* 506 */       return true;
/*     */     }
/*     */     
/* 509 */     if (!(o instanceof KeyedValues2D)) {
/* 510 */       return false;
/*     */     }
/* 512 */     KeyedValues2D kv2D = (KeyedValues2D)o;
/* 513 */     if (!getRowKeys().equals(kv2D.getRowKeys())) {
/* 514 */       return false;
/*     */     }
/* 516 */     if (!getColumnKeys().equals(kv2D.getColumnKeys())) {
/* 517 */       return false;
/*     */     }
/* 519 */     int rowCount = getRowCount();
/* 520 */     if (rowCount != kv2D.getRowCount()) {
/* 521 */       return false;
/*     */     }
/*     */     
/* 524 */     int colCount = getColumnCount();
/* 525 */     if (colCount != kv2D.getColumnCount()) {
/* 526 */       return false;
/*     */     }
/*     */     
/* 529 */     for (int r = 0; r < rowCount; r++) {
/* 530 */       for (int c = 0; c < colCount; c++) {
/* 531 */         Number v1 = getValue(r, c);
/* 532 */         Number v2 = kv2D.getValue(r, c);
/* 533 */         if (v1 == null) {
/* 534 */           if (v2 != null) {
/* 535 */             return false;
/*     */           }
/*     */           
/*     */         }
/* 539 */         else if (!v1.equals(v2)) {
/* 540 */           return false;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 545 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 555 */     int result = this.rowKeys.hashCode();
/* 556 */     result = 29 * result + this.columnKeys.hashCode();
/* 557 */     result = 29 * result + this.rows.hashCode();
/* 558 */     return result;
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
/* 570 */     DefaultKeyedValues2D clone = (DefaultKeyedValues2D)super.clone();
/*     */     
/*     */ 
/* 573 */     clone.columnKeys = new ArrayList(this.columnKeys);
/* 574 */     clone.rowKeys = new ArrayList(this.rowKeys);
/*     */     
/*     */ 
/* 577 */     clone.rows = ((List)ObjectUtilities.deepClone(this.rows));
/* 578 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/DefaultKeyedValues2D.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */