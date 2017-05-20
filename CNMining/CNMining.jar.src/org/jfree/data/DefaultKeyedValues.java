/*     */ package org.jfree.data;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultKeyedValues
/*     */   implements KeyedValues, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8468154364608194797L;
/*     */   private ArrayList keys;
/*     */   private ArrayList values;
/*     */   private HashMap indexMap;
/*     */   
/*     */   public DefaultKeyedValues()
/*     */   {
/*  98 */     this.keys = new ArrayList();
/*  99 */     this.values = new ArrayList();
/* 100 */     this.indexMap = new HashMap();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getItemCount()
/*     */   {
/* 109 */     return this.indexMap.size();
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
/*     */   public Number getValue(int item)
/*     */   {
/* 122 */     return (Number)this.values.get(item);
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
/*     */   public Comparable getKey(int index)
/*     */   {
/* 135 */     return (Comparable)this.keys.get(index);
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
/* 149 */     if (key == null) {
/* 150 */       throw new IllegalArgumentException("Null 'key' argument.");
/*     */     }
/* 152 */     Integer i = (Integer)this.indexMap.get(key);
/* 153 */     if (i == null) {
/* 154 */       return -1;
/*     */     }
/* 156 */     return i.intValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getKeys()
/*     */   {
/* 165 */     return (List)this.keys.clone();
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
/*     */   public Number getValue(Comparable key)
/*     */   {
/* 180 */     int index = getIndex(key);
/* 181 */     if (index < 0) {
/* 182 */       throw new UnknownKeyException("Key not found: " + key);
/*     */     }
/* 184 */     return getValue(index);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addValue(Comparable key, double value)
/*     */   {
/* 196 */     addValue(key, new Double(value));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addValue(Comparable key, Number value)
/*     */   {
/* 208 */     setValue(key, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValue(Comparable key, double value)
/*     */   {
/* 218 */     setValue(key, new Double(value));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValue(Comparable key, Number value)
/*     */   {
/* 228 */     if (key == null) {
/* 229 */       throw new IllegalArgumentException("Null 'key' argument.");
/*     */     }
/* 231 */     int keyIndex = getIndex(key);
/* 232 */     if (keyIndex >= 0) {
/* 233 */       this.keys.set(keyIndex, key);
/* 234 */       this.values.set(keyIndex, value);
/*     */     }
/*     */     else {
/* 237 */       this.keys.add(key);
/* 238 */       this.values.add(value);
/* 239 */       this.indexMap.put(key, new Integer(this.keys.size() - 1));
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
/*     */   public void insertValue(int position, Comparable key, double value)
/*     */   {
/* 255 */     insertValue(position, key, new Double(value));
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
/*     */   public void insertValue(int position, Comparable key, Number value)
/*     */   {
/* 270 */     if ((position < 0) || (position > getItemCount())) {
/* 271 */       throw new IllegalArgumentException("'position' out of bounds.");
/*     */     }
/* 273 */     if (key == null) {
/* 274 */       throw new IllegalArgumentException("Null 'key' argument.");
/*     */     }
/* 276 */     int pos = getIndex(key);
/* 277 */     if (pos == position) {
/* 278 */       this.keys.set(pos, key);
/* 279 */       this.values.set(pos, value);
/*     */     }
/*     */     else {
/* 282 */       if (pos >= 0) {
/* 283 */         this.keys.remove(pos);
/* 284 */         this.values.remove(pos);
/*     */       }
/*     */       
/* 287 */       this.keys.add(position, key);
/* 288 */       this.values.add(position, value);
/* 289 */       rebuildIndex();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void rebuildIndex()
/*     */   {
/* 298 */     this.indexMap.clear();
/* 299 */     for (int i = 0; i < this.keys.size(); i++) {
/* 300 */       Object key = this.keys.get(i);
/* 301 */       this.indexMap.put(key, new Integer(i));
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
/*     */   public void removeValue(int index)
/*     */   {
/* 315 */     this.keys.remove(index);
/* 316 */     this.values.remove(index);
/* 317 */     rebuildIndex();
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
/*     */   public void removeValue(Comparable key)
/*     */   {
/* 330 */     int index = getIndex(key);
/* 331 */     if (index < 0) {
/* 332 */       throw new UnknownKeyException("The key (" + key + ") is not recognised.");
/*     */     }
/*     */     
/* 335 */     removeValue(index);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 344 */     this.keys.clear();
/* 345 */     this.values.clear();
/* 346 */     this.indexMap.clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void sortByKeys(SortOrder order)
/*     */   {
/* 355 */     int size = this.keys.size();
/* 356 */     DefaultKeyedValue[] data = new DefaultKeyedValue[size];
/*     */     
/* 358 */     for (int i = 0; i < size; i++) {
/* 359 */       data[i] = new DefaultKeyedValue((Comparable)this.keys.get(i), (Number)this.values.get(i));
/*     */     }
/*     */     
/*     */ 
/* 363 */     Comparator comparator = new KeyedValueComparator(KeyedValueComparatorType.BY_KEY, order);
/*     */     
/* 365 */     Arrays.sort(data, comparator);
/* 366 */     clear();
/*     */     
/* 368 */     for (int i = 0; i < data.length; i++) {
/* 369 */       DefaultKeyedValue value = data[i];
/* 370 */       addValue(value.getKey(), value.getValue());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void sortByValues(SortOrder order)
/*     */   {
/* 382 */     int size = this.keys.size();
/* 383 */     DefaultKeyedValue[] data = new DefaultKeyedValue[size];
/* 384 */     for (int i = 0; i < size; i++) {
/* 385 */       data[i] = new DefaultKeyedValue((Comparable)this.keys.get(i), (Number)this.values.get(i));
/*     */     }
/*     */     
/*     */ 
/* 389 */     Comparator comparator = new KeyedValueComparator(KeyedValueComparatorType.BY_VALUE, order);
/*     */     
/* 391 */     Arrays.sort(data, comparator);
/*     */     
/* 393 */     clear();
/* 394 */     for (int i = 0; i < data.length; i++) {
/* 395 */       DefaultKeyedValue value = data[i];
/* 396 */       addValue(value.getKey(), value.getValue());
/*     */     }
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
/* 408 */     if (obj == this) {
/* 409 */       return true;
/*     */     }
/*     */     
/* 412 */     if (!(obj instanceof KeyedValues)) {
/* 413 */       return false;
/*     */     }
/*     */     
/* 416 */     KeyedValues that = (KeyedValues)obj;
/* 417 */     int count = getItemCount();
/* 418 */     if (count != that.getItemCount()) {
/* 419 */       return false;
/*     */     }
/*     */     
/* 422 */     for (int i = 0; i < count; i++) {
/* 423 */       Comparable k1 = getKey(i);
/* 424 */       Comparable k2 = that.getKey(i);
/* 425 */       if (!k1.equals(k2)) {
/* 426 */         return false;
/*     */       }
/* 428 */       Number v1 = getValue(i);
/* 429 */       Number v2 = that.getValue(i);
/* 430 */       if (v1 == null) {
/* 431 */         if (v2 != null) {
/* 432 */           return false;
/*     */         }
/*     */         
/*     */       }
/* 436 */       else if (!v1.equals(v2)) {
/* 437 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 441 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 450 */     return this.keys != null ? this.keys.hashCode() : 0;
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
/* 462 */     DefaultKeyedValues clone = (DefaultKeyedValues)super.clone();
/* 463 */     clone.keys = ((ArrayList)this.keys.clone());
/* 464 */     clone.values = ((ArrayList)this.values.clone());
/* 465 */     clone.indexMap = ((HashMap)this.indexMap.clone());
/* 466 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/DefaultKeyedValues.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */