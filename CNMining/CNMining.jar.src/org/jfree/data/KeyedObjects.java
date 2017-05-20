/*     */ package org.jfree.data;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
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
/*     */ public class KeyedObjects
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1321582394193530984L;
/*     */   private List data;
/*     */   
/*     */   public KeyedObjects()
/*     */   {
/*  67 */     this.data = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getItemCount()
/*     */   {
/*  76 */     return this.data.size();
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
/*     */   public Object getObject(int item)
/*     */   {
/*  89 */     Object result = null;
/*  90 */     KeyedObject kobj = (KeyedObject)this.data.get(item);
/*  91 */     if (kobj != null) {
/*  92 */       result = kobj.getObject();
/*     */     }
/*  94 */     return result;
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
/* 109 */     Comparable result = null;
/* 110 */     KeyedObject item = (KeyedObject)this.data.get(index);
/* 111 */     if (item != null) {
/* 112 */       result = item.getKey();
/*     */     }
/* 114 */     return result;
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
/*     */   public int getIndex(Comparable key)
/*     */   {
/* 127 */     if (key == null) {
/* 128 */       throw new IllegalArgumentException("Null 'key' argument.");
/*     */     }
/* 130 */     int i = 0;
/* 131 */     Iterator iterator = this.data.iterator();
/* 132 */     while (iterator.hasNext()) {
/* 133 */       KeyedObject ko = (KeyedObject)iterator.next();
/* 134 */       if (ko.getKey().equals(key)) {
/* 135 */         return i;
/*     */       }
/* 137 */       i++;
/*     */     }
/* 139 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getKeys()
/*     */   {
/* 148 */     List result = new ArrayList();
/* 149 */     Iterator iterator = this.data.iterator();
/* 150 */     while (iterator.hasNext()) {
/* 151 */       KeyedObject ko = (KeyedObject)iterator.next();
/* 152 */       result.add(ko.getKey());
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
/*     */   public Object getObject(Comparable key)
/*     */   {
/* 168 */     int index = getIndex(key);
/* 169 */     if (index < 0) {
/* 170 */       throw new UnknownKeyException("The key (" + key + ") is not recognised.");
/*     */     }
/*     */     
/* 173 */     return getObject(index);
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
/*     */   public void addObject(Comparable key, Object object)
/*     */   {
/* 186 */     setObject(key, object);
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
/*     */   public void setObject(Comparable key, Object object)
/*     */   {
/* 200 */     int keyIndex = getIndex(key);
/* 201 */     if (keyIndex >= 0) {
/* 202 */       KeyedObject ko = (KeyedObject)this.data.get(keyIndex);
/* 203 */       ko.setObject(object);
/*     */     }
/*     */     else {
/* 206 */       KeyedObject ko = new KeyedObject(key, object);
/* 207 */       this.data.add(ko);
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
/*     */ 
/*     */   public void insertValue(int position, Comparable key, Object value)
/*     */   {
/* 224 */     if ((position < 0) || (position > this.data.size())) {
/* 225 */       throw new IllegalArgumentException("'position' out of bounds.");
/*     */     }
/* 227 */     if (key == null) {
/* 228 */       throw new IllegalArgumentException("Null 'key' argument.");
/*     */     }
/* 230 */     int pos = getIndex(key);
/* 231 */     if (pos >= 0) {
/* 232 */       this.data.remove(pos);
/*     */     }
/* 234 */     KeyedObject item = new KeyedObject(key, value);
/* 235 */     if (position <= this.data.size()) {
/* 236 */       this.data.add(position, item);
/*     */     }
/*     */     else {
/* 239 */       this.data.add(item);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeValue(int index)
/*     */   {
/* 251 */     this.data.remove(index);
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
/*     */   public void removeValue(Comparable key)
/*     */   {
/* 265 */     int index = getIndex(key);
/* 266 */     if (index < 0) {
/* 267 */       throw new UnknownKeyException("The key (" + key.toString() + ") is not recognised.");
/*     */     }
/*     */     
/* 270 */     removeValue(index);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 279 */     this.data.clear();
/*     */   }
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
/* 292 */     KeyedObjects clone = (KeyedObjects)super.clone();
/* 293 */     clone.data = new ArrayList();
/* 294 */     Iterator iterator = this.data.iterator();
/* 295 */     while (iterator.hasNext()) {
/* 296 */       KeyedObject ko = (KeyedObject)iterator.next();
/* 297 */       clone.data.add(ko.clone());
/*     */     }
/* 299 */     return clone;
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
/* 311 */     if (obj == this) {
/* 312 */       return true;
/*     */     }
/* 314 */     if (!(obj instanceof KeyedObjects)) {
/* 315 */       return false;
/*     */     }
/* 317 */     KeyedObjects that = (KeyedObjects)obj;
/* 318 */     int count = getItemCount();
/* 319 */     if (count != that.getItemCount()) {
/* 320 */       return false;
/*     */     }
/*     */     
/* 323 */     for (int i = 0; i < count; i++) {
/* 324 */       Comparable k1 = getKey(i);
/* 325 */       Comparable k2 = that.getKey(i);
/* 326 */       if (!k1.equals(k2)) {
/* 327 */         return false;
/*     */       }
/* 329 */       Object o1 = getObject(i);
/* 330 */       Object o2 = that.getObject(i);
/* 331 */       if (o1 == null) {
/* 332 */         if (o2 != null) {
/* 333 */           return false;
/*     */         }
/*     */         
/*     */       }
/* 337 */       else if (!o1.equals(o2)) {
/* 338 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 342 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 352 */     return this.data != null ? this.data.hashCode() : 0;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/KeyedObjects.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */