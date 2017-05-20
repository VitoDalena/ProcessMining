/*     */ package org.apache.commons.collections15.collection;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractCollectionDecorator<E>
/*     */   implements Collection<E>
/*     */ {
/*     */   protected Collection<E> collection;
/*     */   
/*     */   protected AbstractCollectionDecorator() {}
/*     */   
/*     */   protected AbstractCollectionDecorator(Collection<E> coll)
/*     */   {
/*  65 */     if (coll == null) {
/*  66 */       throw new IllegalArgumentException("Collection must not be null");
/*     */     }
/*  68 */     this.collection = coll;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Collection<E> getCollection()
/*     */   {
/*  77 */     return this.collection;
/*     */   }
/*     */   
/*     */   public boolean add(E object)
/*     */   {
/*  82 */     return this.collection.add(object);
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends E> coll) {
/*  86 */     return this.collection.addAll(coll);
/*     */   }
/*     */   
/*     */   public void clear() {
/*  90 */     this.collection.clear();
/*     */   }
/*     */   
/*     */   public boolean contains(Object object) {
/*  94 */     return this.collection.contains(object);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  98 */     return this.collection.isEmpty();
/*     */   }
/*     */   
/*     */   public Iterator<E> iterator() {
/* 102 */     return this.collection.iterator();
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 106 */     return this.collection.remove(object);
/*     */   }
/*     */   
/*     */   public int size() {
/* 110 */     return this.collection.size();
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 114 */     return (Object[])this.collection.toArray();
/*     */   }
/*     */   
/*     */   public <T> T[] toArray(T[] object) {
/* 118 */     return (Object[])this.collection.toArray(object);
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> coll) {
/* 122 */     return this.collection.containsAll(coll);
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> coll) {
/* 126 */     return this.collection.removeAll(coll);
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> coll) {
/* 130 */     return this.collection.retainAll(coll);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 134 */     if (object == this) {
/* 135 */       return true;
/*     */     }
/* 137 */     return this.collection.equals(object);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 141 */     return this.collection.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 145 */     return this.collection.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/collection/AbstractCollectionDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */