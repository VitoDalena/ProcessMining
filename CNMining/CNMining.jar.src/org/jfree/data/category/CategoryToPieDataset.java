/*     */ package org.jfree.data.category;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.jfree.data.general.AbstractDataset;
/*     */ import org.jfree.data.general.DatasetChangeEvent;
/*     */ import org.jfree.data.general.DatasetChangeListener;
/*     */ import org.jfree.data.general.PieDataset;
/*     */ import org.jfree.util.TableOrder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CategoryToPieDataset
/*     */   extends AbstractDataset
/*     */   implements PieDataset, DatasetChangeListener
/*     */ {
/*     */   static final long serialVersionUID = 5516396319762189617L;
/*     */   private CategoryDataset source;
/*     */   private TableOrder extract;
/*     */   private int index;
/*     */   
/*     */   public CategoryToPieDataset(CategoryDataset source, TableOrder extract, int index)
/*     */   {
/*  94 */     if (extract == null) {
/*  95 */       throw new IllegalArgumentException("Null 'extract' argument.");
/*     */     }
/*  97 */     this.source = source;
/*  98 */     if (this.source != null) {
/*  99 */       this.source.addChangeListener(this);
/*     */     }
/* 101 */     this.extract = extract;
/* 102 */     this.index = index;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CategoryDataset getUnderlyingDataset()
/*     */   {
/* 113 */     return this.source;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TableOrder getExtractType()
/*     */   {
/* 125 */     return this.extract;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getExtractIndex()
/*     */   {
/* 136 */     return this.index;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getItemCount()
/*     */   {
/* 146 */     int result = 0;
/* 147 */     if (this.source != null) {
/* 148 */       if (this.extract == TableOrder.BY_ROW) {
/* 149 */         result = this.source.getColumnCount();
/*     */       }
/* 151 */       else if (this.extract == TableOrder.BY_COLUMN) {
/* 152 */         result = this.source.getRowCount();
/*     */       }
/*     */     }
/* 155 */     return result;
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
/*     */   public Number getValue(int item)
/*     */   {
/* 169 */     Number result = null;
/* 170 */     if ((item < 0) || (item >= getItemCount()))
/*     */     {
/* 172 */       throw new IndexOutOfBoundsException("The 'item' index is out of bounds.");
/*     */     }
/*     */     
/* 175 */     if (this.extract == TableOrder.BY_ROW) {
/* 176 */       result = this.source.getValue(this.index, item);
/*     */     }
/* 178 */     else if (this.extract == TableOrder.BY_COLUMN) {
/* 179 */       result = this.source.getValue(item, this.index);
/*     */     }
/* 181 */     return result;
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
/*     */   public Comparable getKey(int index)
/*     */   {
/* 196 */     Comparable result = null;
/* 197 */     if ((index < 0) || (index >= getItemCount()))
/*     */     {
/* 199 */       throw new IndexOutOfBoundsException("Invalid 'index': " + index);
/*     */     }
/* 201 */     if (this.extract == TableOrder.BY_ROW) {
/* 202 */       result = this.source.getColumnKey(index);
/*     */     }
/* 204 */     else if (this.extract == TableOrder.BY_COLUMN) {
/* 205 */       result = this.source.getRowKey(index);
/*     */     }
/* 207 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getIndex(Comparable key)
/*     */   {
/* 219 */     int result = -1;
/* 220 */     if (this.source != null) {
/* 221 */       if (this.extract == TableOrder.BY_ROW) {
/* 222 */         result = this.source.getColumnIndex(key);
/*     */       }
/* 224 */       else if (this.extract == TableOrder.BY_COLUMN) {
/* 225 */         result = this.source.getRowIndex(key);
/*     */       }
/*     */     }
/* 228 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getKeys()
/*     */   {
/* 240 */     List result = Collections.EMPTY_LIST;
/* 241 */     if (this.source != null) {
/* 242 */       if (this.extract == TableOrder.BY_ROW) {
/* 243 */         result = this.source.getColumnKeys();
/*     */       }
/* 245 */       else if (this.extract == TableOrder.BY_COLUMN) {
/* 246 */         result = this.source.getRowKeys();
/*     */       }
/*     */     }
/* 249 */     return result;
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
/*     */   public Number getValue(Comparable key)
/*     */   {
/* 262 */     Number result = null;
/* 263 */     int keyIndex = getIndex(key);
/* 264 */     if (keyIndex != -1) {
/* 265 */       if (this.extract == TableOrder.BY_ROW) {
/* 266 */         result = this.source.getValue(this.index, keyIndex);
/*     */       }
/* 268 */       else if (this.extract == TableOrder.BY_COLUMN) {
/* 269 */         result = this.source.getValue(keyIndex, this.index);
/*     */       }
/*     */     }
/* 272 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void datasetChanged(DatasetChangeEvent event)
/*     */   {
/* 283 */     fireDatasetChanged();
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
/* 296 */     if (obj == this) {
/* 297 */       return true;
/*     */     }
/* 299 */     if (!(obj instanceof PieDataset)) {
/* 300 */       return false;
/*     */     }
/* 302 */     PieDataset that = (PieDataset)obj;
/* 303 */     int count = getItemCount();
/* 304 */     if (that.getItemCount() != count) {
/* 305 */       return false;
/*     */     }
/* 307 */     for (int i = 0; i < count; i++) {
/* 308 */       Comparable k1 = getKey(i);
/* 309 */       Comparable k2 = that.getKey(i);
/* 310 */       if (!k1.equals(k2)) {
/* 311 */         return false;
/*     */       }
/*     */       
/* 314 */       Number v1 = getValue(i);
/* 315 */       Number v2 = that.getValue(i);
/* 316 */       if (v1 == null) {
/* 317 */         if (v2 != null) {
/* 318 */           return false;
/*     */         }
/*     */         
/*     */       }
/* 322 */       else if (!v1.equals(v2)) {
/* 323 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 327 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/category/CategoryToPieDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */