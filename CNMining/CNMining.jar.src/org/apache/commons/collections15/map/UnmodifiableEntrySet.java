/*     */ package org.apache.commons.collections15.map;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.Unmodifiable;
/*     */ import org.apache.commons.collections15.iterators.AbstractIteratorDecorator;
/*     */ import org.apache.commons.collections15.keyvalue.AbstractMapEntryDecorator;
/*     */ import org.apache.commons.collections15.set.AbstractSetDecorator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class UnmodifiableEntrySet<K, V>
/*     */   extends AbstractSetDecorator<Map.Entry<K, V>>
/*     */   implements Unmodifiable
/*     */ {
/*     */   public static <K, V> Set<Map.Entry<K, V>> decorate(Set<Map.Entry<K, V>> set)
/*     */   {
/*  43 */     if ((set instanceof Unmodifiable)) {
/*  44 */       return set;
/*     */     }
/*  46 */     return new UnmodifiableEntrySet(set);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private UnmodifiableEntrySet(Set<Map.Entry<K, V>> set)
/*     */   {
/*  57 */     super(set);
/*     */   }
/*     */   
/*     */   public boolean add(Map.Entry<K, V> object)
/*     */   {
/*  62 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends Map.Entry<K, V>> coll) {
/*  66 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/*  70 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/*  74 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> coll) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> coll) {
/*  82 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Iterator<Map.Entry<K, V>> iterator()
/*     */   {
/*  87 */     return new UnmodifiableEntrySetIterator(this.collection.iterator());
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/*  91 */     Object[] array = this.collection.toArray();
/*  92 */     for (int i = 0; i < array.length; i++) {
/*  93 */       array[i] = new UnmodifiableEntry((Map.Entry)array[i]);
/*     */     }
/*  95 */     return array;
/*     */   }
/*     */   
/*     */   public <T> T[] toArray(T[] array) {
/*  99 */     T[] result = array;
/* 100 */     if (array.length > 0)
/*     */     {
/*     */ 
/* 103 */       result = (Object[])Array.newInstance(array.getClass().getComponentType(), 0);
/*     */     }
/* 105 */     result = this.collection.toArray(result);
/* 106 */     Collection<UnmodifiableEntry<K, V>> newCollection = new ArrayList();
/* 107 */     for (int i = 0; i < result.length; i++)
/*     */     {
/* 109 */       newCollection.add(new UnmodifiableEntry((Map.Entry)result[i]));
/*     */     }
/* 111 */     result = newCollection.toArray(result);
/*     */     
/*     */ 
/* 114 */     if (result.length > array.length) {
/* 115 */       return result;
/*     */     }
/*     */     
/*     */ 
/* 119 */     System.arraycopy(result, 0, array, 0, result.length);
/* 120 */     if (array.length > result.length) {
/* 121 */       array[result.length] = null;
/*     */     }
/* 123 */     return array;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   static final class UnmodifiableEntrySetIterator<K, V>
/*     */     extends AbstractIteratorDecorator<Map.Entry<K, V>>
/*     */   {
/*     */     protected UnmodifiableEntrySetIterator(Iterator<Map.Entry<K, V>> iterator)
/*     */     {
/* 133 */       super();
/*     */     }
/*     */     
/*     */     public Map.Entry<K, V> next() {
/* 137 */       Map.Entry entry = (Map.Entry)this.iterator.next();
/* 138 */       return new UnmodifiableEntrySet.UnmodifiableEntry(entry);
/*     */     }
/*     */     
/*     */     public void remove() {
/* 142 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   static final class UnmodifiableEntry<K, V>
/*     */     extends AbstractMapEntryDecorator<K, V>
/*     */   {
/*     */     protected UnmodifiableEntry(Map.Entry<K, V> entry)
/*     */     {
/* 153 */       super();
/*     */     }
/*     */     
/*     */     public V setValue(V obj) {
/* 157 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/UnmodifiableEntrySet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */