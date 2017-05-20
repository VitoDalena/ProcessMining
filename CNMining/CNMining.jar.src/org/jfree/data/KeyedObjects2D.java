/*     */ package org.jfree.data;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KeyedObjects2D
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -1015873563138522374L;
/*     */   private List rowKeys;
/*     */   private List columnKeys;
/*     */   private List rows;
/*     */   
/*     */   public KeyedObjects2D()
/*     */   {
/*  74 */     this.rowKeys = new ArrayList();
/*  75 */     this.columnKeys = new ArrayList();
/*  76 */     this.rows = new ArrayList();
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
/*  87 */     return this.rowKeys.size();
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
/*  98 */     return this.columnKeys.size();
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
/*     */   public Object getObject(int row, int column)
/*     */   {
/* 112 */     Object result = null;
/* 113 */     KeyedObjects rowData = (KeyedObjects)this.rows.get(row);
/* 114 */     if (rowData != null) {
/* 115 */       Comparable columnKey = (Comparable)this.columnKeys.get(column);
/* 116 */       if (columnKey != null) {
/* 117 */         int index = rowData.getIndex(columnKey);
/* 118 */         if (index >= 0) {
/* 119 */           result = rowData.getObject(columnKey);
/*     */         }
/*     */       }
/*     */     }
/* 123 */     return result;
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
/* 136 */     return (Comparable)this.rowKeys.get(row);
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
/* 150 */     if (key == null) {
/* 151 */       throw new IllegalArgumentException("Null 'key' argument.");
/*     */     }
/* 153 */     return this.rowKeys.indexOf(key);
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
/* 164 */     return Collections.unmodifiableList(this.rowKeys);
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
/* 177 */     return (Comparable)this.columnKeys.get(column);
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
/* 191 */     if (key == null) {
/* 192 */       throw new IllegalArgumentException("Null 'key' argument.");
/*     */     }
/* 194 */     return this.columnKeys.indexOf(key);
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
/* 205 */     return Collections.unmodifiableList(this.columnKeys);
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
/*     */   public Object getObject(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 222 */     if (rowKey == null) {
/* 223 */       throw new IllegalArgumentException("Null 'rowKey' argument.");
/*     */     }
/* 225 */     if (columnKey == null) {
/* 226 */       throw new IllegalArgumentException("Null 'columnKey' argument.");
/*     */     }
/* 228 */     int row = this.rowKeys.indexOf(rowKey);
/* 229 */     if (row < 0) {
/* 230 */       throw new UnknownKeyException("Row key (" + rowKey + ") not recognised.");
/*     */     }
/*     */     
/* 233 */     int column = this.columnKeys.indexOf(columnKey);
/* 234 */     if (column < 0) {
/* 235 */       throw new UnknownKeyException("Column key (" + columnKey + ") not recognised.");
/*     */     }
/*     */     
/* 238 */     KeyedObjects rowData = (KeyedObjects)this.rows.get(row);
/* 239 */     int index = rowData.getIndex(columnKey);
/* 240 */     if (index >= 0) {
/* 241 */       return rowData.getObject(index);
/*     */     }
/*     */     
/* 244 */     return null;
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
/*     */   public void addObject(Object object, Comparable rowKey, Comparable columnKey)
/*     */   {
/* 257 */     setObject(object, rowKey, columnKey);
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
/*     */   public void setObject(Object object, Comparable rowKey, Comparable columnKey)
/*     */   {
/* 270 */     if (rowKey == null) {
/* 271 */       throw new IllegalArgumentException("Null 'rowKey' argument.");
/*     */     }
/* 273 */     if (columnKey == null) {
/* 274 */       throw new IllegalArgumentException("Null 'columnKey' argument.");
/*     */     }
/*     */     
/* 277 */     int rowIndex = this.rowKeys.indexOf(rowKey);
/* 278 */     KeyedObjects row; KeyedObjects row; if (rowIndex >= 0) {
/* 279 */       row = (KeyedObjects)this.rows.get(rowIndex);
/*     */     }
/*     */     else {
/* 282 */       this.rowKeys.add(rowKey);
/* 283 */       row = new KeyedObjects();
/* 284 */       this.rows.add(row);
/*     */     }
/* 286 */     row.setObject(columnKey, object);
/* 287 */     int columnIndex = this.columnKeys.indexOf(columnKey);
/* 288 */     if (columnIndex < 0) {
/* 289 */       this.columnKeys.add(columnKey);
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
/*     */ 
/*     */   public void removeObject(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 305 */     int rowIndex = getRowIndex(rowKey);
/* 306 */     if (rowIndex < 0) {
/* 307 */       throw new UnknownKeyException("Row key (" + rowKey + ") not recognised.");
/*     */     }
/*     */     
/* 310 */     int columnIndex = getColumnIndex(columnKey);
/* 311 */     if (columnIndex < 0) {
/* 312 */       throw new UnknownKeyException("Column key (" + columnKey + ") not recognised.");
/*     */     }
/*     */     
/* 315 */     setObject(null, rowKey, columnKey);
/*     */     
/*     */ 
/* 318 */     boolean allNull = true;
/* 319 */     KeyedObjects row = (KeyedObjects)this.rows.get(rowIndex);
/*     */     
/* 321 */     int item = 0; for (int itemCount = row.getItemCount(); item < itemCount; 
/* 322 */         item++) {
/* 323 */       if (row.getObject(item) != null) {
/* 324 */         allNull = false;
/* 325 */         break;
/*     */       }
/*     */     }
/*     */     
/* 329 */     if (allNull) {
/* 330 */       this.rowKeys.remove(rowIndex);
/* 331 */       this.rows.remove(rowIndex);
/*     */     }
/*     */     
/*     */ 
/* 335 */     allNull = true;
/*     */     
/* 337 */     int item = 0; for (int itemCount = this.rows.size(); item < itemCount; 
/* 338 */         item++) {
/* 339 */       row = (KeyedObjects)this.rows.get(item);
/* 340 */       int colIndex = row.getIndex(columnKey);
/* 341 */       if ((colIndex >= 0) && (row.getObject(colIndex) != null)) {
/* 342 */         allNull = false;
/* 343 */         break;
/*     */       }
/*     */     }
/*     */     
/* 347 */     if (allNull) {
/* 348 */       int item = 0; for (int itemCount = this.rows.size(); item < itemCount; 
/* 349 */           item++) {
/* 350 */         row = (KeyedObjects)this.rows.get(item);
/* 351 */         int colIndex = row.getIndex(columnKey);
/* 352 */         if (colIndex >= 0) {
/* 353 */           row.removeValue(colIndex);
/*     */         }
/*     */       }
/* 356 */       this.columnKeys.remove(columnKey);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeRow(int rowIndex)
/*     */   {
/* 368 */     this.rowKeys.remove(rowIndex);
/* 369 */     this.rows.remove(rowIndex);
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
/*     */   public void removeRow(Comparable rowKey)
/*     */   {
/* 382 */     int index = getRowIndex(rowKey);
/* 383 */     if (index < 0) {
/* 384 */       throw new UnknownKeyException("Row key (" + rowKey + ") not recognised.");
/*     */     }
/*     */     
/* 387 */     removeRow(index);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeColumn(int columnIndex)
/*     */   {
/* 398 */     Comparable columnKey = getColumnKey(columnIndex);
/* 399 */     removeColumn(columnKey);
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
/*     */   public void removeColumn(Comparable columnKey)
/*     */   {
/* 412 */     int index = getColumnIndex(columnKey);
/* 413 */     if (index < 0) {
/* 414 */       throw new UnknownKeyException("Column key (" + columnKey + ") not recognised.");
/*     */     }
/*     */     
/* 417 */     Iterator iterator = this.rows.iterator();
/* 418 */     while (iterator.hasNext()) {
/* 419 */       KeyedObjects rowData = (KeyedObjects)iterator.next();
/* 420 */       int i = rowData.getIndex(columnKey);
/* 421 */       if (i >= 0) {
/* 422 */         rowData.removeValue(i);
/*     */       }
/*     */     }
/* 425 */     this.columnKeys.remove(columnKey);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 434 */     this.rowKeys.clear();
/* 435 */     this.columnKeys.clear();
/* 436 */     this.rows.clear();
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
/* 447 */     if (obj == this) {
/* 448 */       return true;
/*     */     }
/* 450 */     if (!(obj instanceof KeyedObjects2D)) {
/* 451 */       return false;
/*     */     }
/*     */     
/* 454 */     KeyedObjects2D that = (KeyedObjects2D)obj;
/* 455 */     if (!getRowKeys().equals(that.getRowKeys())) {
/* 456 */       return false;
/*     */     }
/* 458 */     if (!getColumnKeys().equals(that.getColumnKeys())) {
/* 459 */       return false;
/*     */     }
/* 461 */     int rowCount = getRowCount();
/* 462 */     if (rowCount != that.getRowCount()) {
/* 463 */       return false;
/*     */     }
/* 465 */     int colCount = getColumnCount();
/* 466 */     if (colCount != that.getColumnCount()) {
/* 467 */       return false;
/*     */     }
/* 469 */     for (int r = 0; r < rowCount; r++) {
/* 470 */       for (int c = 0; c < colCount; c++) {
/* 471 */         Object v1 = getObject(r, c);
/* 472 */         Object v2 = that.getObject(r, c);
/* 473 */         if (v1 == null) {
/* 474 */           if (v2 != null) {
/* 475 */             return false;
/*     */           }
/*     */           
/*     */         }
/* 479 */         else if (!v1.equals(v2)) {
/* 480 */           return false;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 485 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 495 */     int result = this.rowKeys.hashCode();
/* 496 */     result = 29 * result + this.columnKeys.hashCode();
/* 497 */     result = 29 * result + this.rows.hashCode();
/* 498 */     return result;
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
/* 510 */     KeyedObjects2D clone = (KeyedObjects2D)super.clone();
/* 511 */     clone.columnKeys = new ArrayList(this.columnKeys);
/* 512 */     clone.rowKeys = new ArrayList(this.rowKeys);
/* 513 */     clone.rows = new ArrayList(this.rows.size());
/* 514 */     Iterator iterator = this.rows.iterator();
/* 515 */     while (iterator.hasNext()) {
/* 516 */       KeyedObjects row = (KeyedObjects)iterator.next();
/* 517 */       clone.rows.add(row.clone());
/*     */     }
/* 519 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/KeyedObjects2D.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */