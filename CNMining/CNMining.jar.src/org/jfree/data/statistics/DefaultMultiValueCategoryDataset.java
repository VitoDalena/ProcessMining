/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
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
/*     */ public class DefaultMultiValueCategoryDataset
/*     */   extends AbstractDataset
/*     */   implements MultiValueCategoryDataset, RangeInfo, PublicCloneable
/*     */ {
/*     */   protected KeyedObjects2D data;
/*     */   private Number minimumRangeValue;
/*     */   private Number maximumRangeValue;
/*     */   private Range rangeBounds;
/*     */   
/*     */   public DefaultMultiValueCategoryDataset()
/*     */   {
/*  87 */     this.data = new KeyedObjects2D();
/*  88 */     this.minimumRangeValue = null;
/*  89 */     this.maximumRangeValue = null;
/*  90 */     this.rangeBounds = new Range(0.0D, 0.0D);
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
/*     */   public void add(List values, Comparable rowKey, Comparable columnKey)
/*     */   {
/* 104 */     if (values == null) {
/* 105 */       throw new IllegalArgumentException("Null 'values' argument.");
/*     */     }
/* 107 */     if (rowKey == null) {
/* 108 */       throw new IllegalArgumentException("Null 'rowKey' argument.");
/*     */     }
/* 110 */     if (columnKey == null) {
/* 111 */       throw new IllegalArgumentException("Null 'columnKey' argument.");
/*     */     }
/* 113 */     List vlist = new ArrayList(values.size());
/* 114 */     Iterator iterator = values.listIterator();
/* 115 */     while (iterator.hasNext()) {
/* 116 */       Object obj = iterator.next();
/* 117 */       if ((obj instanceof Number)) {
/* 118 */         Number n = (Number)obj;
/* 119 */         double v = n.doubleValue();
/* 120 */         if (!Double.isNaN(v)) {
/* 121 */           vlist.add(n);
/*     */         }
/*     */       }
/*     */     }
/* 125 */     Collections.sort(vlist);
/* 126 */     this.data.addObject(vlist, rowKey, columnKey);
/*     */     
/* 128 */     if (vlist.size() > 0) {
/* 129 */       double maxval = Double.NEGATIVE_INFINITY;
/* 130 */       double minval = Double.POSITIVE_INFINITY;
/* 131 */       for (int i = 0; i < vlist.size(); i++) {
/* 132 */         Number n = (Number)vlist.get(i);
/* 133 */         double v = n.doubleValue();
/* 134 */         minval = Math.min(minval, v);
/* 135 */         maxval = Math.max(maxval, v);
/*     */       }
/*     */       
/*     */ 
/* 139 */       if (this.maximumRangeValue == null) {
/* 140 */         this.maximumRangeValue = new Double(maxval);
/*     */       }
/* 142 */       else if (maxval > this.maximumRangeValue.doubleValue()) {
/* 143 */         this.maximumRangeValue = new Double(maxval);
/*     */       }
/*     */       
/* 146 */       if (this.minimumRangeValue == null) {
/* 147 */         this.minimumRangeValue = new Double(minval);
/*     */       }
/* 149 */       else if (minval < this.minimumRangeValue.doubleValue()) {
/* 150 */         this.minimumRangeValue = new Double(minval);
/*     */       }
/* 152 */       this.rangeBounds = new Range(this.minimumRangeValue.doubleValue(), this.maximumRangeValue.doubleValue());
/*     */     }
/*     */     
/*     */ 
/* 156 */     fireDatasetChanged();
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
/*     */   public List getValues(int row, int column)
/*     */   {
/* 169 */     List values = (List)this.data.getObject(row, column);
/* 170 */     if (values != null) {
/* 171 */       return Collections.unmodifiableList(values);
/*     */     }
/*     */     
/* 174 */     return Collections.EMPTY_LIST;
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
/*     */   public List getValues(Comparable rowKey, Comparable columnKey)
/*     */   {
/* 188 */     return Collections.unmodifiableList((List)this.data.getObject(rowKey, columnKey));
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
/*     */   public Number getValue(Comparable row, Comparable column)
/*     */   {
/* 201 */     List l = (List)this.data.getObject(row, column);
/* 202 */     double average = 0.0D;
/* 203 */     int count = 0;
/* 204 */     if ((l != null) && (l.size() > 0)) {
/* 205 */       for (int i = 0; i < l.size(); i++) {
/* 206 */         Number n = (Number)l.get(i);
/* 207 */         average += n.doubleValue();
/* 208 */         count++;
/*     */       }
/* 210 */       if (count > 0) {
/* 211 */         average /= count;
/*     */       }
/*     */     }
/* 214 */     if (count == 0) {
/* 215 */       return null;
/*     */     }
/* 217 */     return new Double(average);
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
/* 229 */     List l = (List)this.data.getObject(row, column);
/* 230 */     double average = 0.0D;
/* 231 */     int count = 0;
/* 232 */     if ((l != null) && (l.size() > 0)) {
/* 233 */       for (int i = 0; i < l.size(); i++) {
/* 234 */         Number n = (Number)l.get(i);
/* 235 */         average += n.doubleValue();
/* 236 */         count++;
/*     */       }
/* 238 */       if (count > 0) {
/* 239 */         average /= count;
/*     */       }
/*     */     }
/* 242 */     if (count == 0) {
/* 243 */       return null;
/*     */     }
/* 245 */     return new Double(average);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getColumnIndex(Comparable key)
/*     */   {
/* 256 */     return this.data.getColumnIndex(key);
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
/* 267 */     return this.data.getColumnKey(column);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getColumnKeys()
/*     */   {
/* 276 */     return this.data.getColumnKeys();
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
/* 287 */     return this.data.getRowIndex(key);
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
/* 298 */     return this.data.getRowKey(row);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getRowKeys()
/*     */   {
/* 307 */     return this.data.getRowKeys();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRowCount()
/*     */   {
/* 316 */     return this.data.getRowCount();
/*     */   }
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
/*     */   public double getRangeLowerBound(boolean includeInterval)
/*     */   {
/* 337 */     double result = NaN.0D;
/* 338 */     if (this.minimumRangeValue != null) {
/* 339 */       result = this.minimumRangeValue.doubleValue();
/*     */     }
/* 341 */     return result;
/*     */   }
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
/* 353 */     double result = NaN.0D;
/* 354 */     if (this.maximumRangeValue != null) {
/* 355 */       result = this.maximumRangeValue.doubleValue();
/*     */     }
/* 357 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Range getRangeBounds(boolean includeInterval)
/*     */   {
/* 368 */     return this.rangeBounds;
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
/* 379 */     if (obj == this) {
/* 380 */       return true;
/*     */     }
/* 382 */     if (!(obj instanceof DefaultMultiValueCategoryDataset)) {
/* 383 */       return false;
/*     */     }
/* 385 */     DefaultMultiValueCategoryDataset that = (DefaultMultiValueCategoryDataset)obj;
/*     */     
/* 387 */     return this.data.equals(that.data);
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
/* 398 */     DefaultMultiValueCategoryDataset clone = (DefaultMultiValueCategoryDataset)super.clone();
/*     */     
/* 400 */     clone.data = ((KeyedObjects2D)this.data.clone());
/* 401 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/statistics/DefaultMultiValueCategoryDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */