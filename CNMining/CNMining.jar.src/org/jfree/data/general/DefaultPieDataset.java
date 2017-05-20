/*     */ package org.jfree.data.general;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.jfree.data.DefaultKeyedValues;
/*     */ import org.jfree.data.KeyedValues;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ import org.jfree.util.SortOrder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultPieDataset
/*     */   extends AbstractDataset
/*     */   implements PieDataset, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2904745139106540618L;
/*     */   private DefaultKeyedValues data;
/*     */   
/*     */   public DefaultPieDataset()
/*     */   {
/*  85 */     this.data = new DefaultKeyedValues();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultPieDataset(KeyedValues data)
/*     */   {
/*  95 */     if (data == null) {
/*  96 */       throw new IllegalArgumentException("Null 'data' argument.");
/*     */     }
/*  98 */     this.data = new DefaultKeyedValues();
/*  99 */     for (int i = 0; i < data.getItemCount(); i++) {
/* 100 */       this.data.addValue(data.getKey(i), data.getValue(i));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getItemCount()
/*     */   {
/* 110 */     return this.data.getItemCount();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getKeys()
/*     */   {
/* 120 */     return Collections.unmodifiableList(this.data.getKeys());
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
/*     */   public Comparable getKey(int item)
/*     */   {
/* 135 */     return this.data.getKey(item);
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
/*     */   public int getIndex(Comparable key)
/*     */   {
/* 149 */     return this.data.getIndex(key);
/*     */   }
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
/* 161 */     Number result = null;
/* 162 */     if (getItemCount() > item) {
/* 163 */       result = this.data.getValue(item);
/*     */     }
/* 165 */     return result;
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
/*     */   public Number getValue(Comparable key)
/*     */   {
/* 179 */     if (key == null) {
/* 180 */       throw new IllegalArgumentException("Null 'key' argument.");
/*     */     }
/* 182 */     return this.data.getValue(key);
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
/*     */   public void setValue(Comparable key, Number value)
/*     */   {
/* 196 */     this.data.setValue(key, value);
/* 197 */     fireDatasetChanged();
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
/*     */   public void setValue(Comparable key, double value)
/*     */   {
/* 211 */     setValue(key, new Double(value));
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
/*     */   public void insertValue(int position, Comparable key, double value)
/*     */   {
/* 228 */     insertValue(position, key, new Double(value));
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
/*     */   public void insertValue(int position, Comparable key, Number value)
/*     */   {
/* 245 */     this.data.insertValue(position, key, value);
/* 246 */     fireDatasetChanged();
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
/*     */   public void remove(Comparable key)
/*     */   {
/* 259 */     this.data.removeValue(key);
/* 260 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 270 */     if (getItemCount() > 0) {
/* 271 */       this.data.clear();
/* 272 */       fireDatasetChanged();
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
/*     */   public void sortByKeys(SortOrder order)
/*     */   {
/* 285 */     this.data.sortByKeys(order);
/* 286 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void sortByValues(SortOrder order)
/*     */   {
/* 298 */     this.data.sortByValues(order);
/* 299 */     fireDatasetChanged();
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
/* 310 */     if (obj == this) {
/* 311 */       return true;
/*     */     }
/*     */     
/* 314 */     if (!(obj instanceof PieDataset)) {
/* 315 */       return false;
/*     */     }
/* 317 */     PieDataset that = (PieDataset)obj;
/* 318 */     int count = getItemCount();
/* 319 */     if (that.getItemCount() != count) {
/* 320 */       return false;
/*     */     }
/*     */     
/* 323 */     for (int i = 0; i < count; i++) {
/* 324 */       Comparable k1 = getKey(i);
/* 325 */       Comparable k2 = that.getKey(i);
/* 326 */       if (!k1.equals(k2)) {
/* 327 */         return false;
/*     */       }
/*     */       
/* 330 */       Number v1 = getValue(i);
/* 331 */       Number v2 = that.getValue(i);
/* 332 */       if (v1 == null) {
/* 333 */         if (v2 != null) {
/* 334 */           return false;
/*     */         }
/*     */         
/*     */       }
/* 338 */       else if (!v1.equals(v2)) {
/* 339 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 343 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 353 */     return this.data.hashCode();
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
/* 365 */     DefaultPieDataset clone = (DefaultPieDataset)super.clone();
/* 366 */     clone.data = ((DefaultKeyedValues)this.data.clone());
/* 367 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/general/DefaultPieDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */