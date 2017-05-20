/*     */ package org.apache.commons.collections15.map;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class AbstractInputCheckedMapDecorator<K, V>
/*     */   extends AbstractMapDecorator<K, V>
/*     */ {
/*     */   protected AbstractInputCheckedMapDecorator() {}
/*     */   
/*     */   protected AbstractInputCheckedMapDecorator(Map<K, V> map)
/*     */   {
/*  62 */     super(map);
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
/*     */   protected abstract V checkSetValue(V paramV);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean isSetValueChecking()
/*     */   {
/*  91 */     return true;
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<K, V>> entrySet()
/*     */   {
/*  96 */     if (isSetValueChecking()) {
/*  97 */       return new EntrySet(this.map.entrySet(), this);
/*     */     }
/*  99 */     return this.map.entrySet();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   static class EntrySet<K, V>
/*     */     extends AbstractSetDecorator<Map.Entry<K, V>>
/*     */   {
/*     */     private final AbstractInputCheckedMapDecorator<K, V> parent;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     protected EntrySet(Set<Map.Entry<K, V>> set, AbstractInputCheckedMapDecorator<K, V> parent)
/*     */     {
/* 115 */       super();
/* 116 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public Iterator<Map.Entry<K, V>> iterator() {
/* 120 */       return new AbstractInputCheckedMapDecorator.EntrySetIterator(this.collection.iterator(), this.parent);
/*     */     }
/*     */     
/*     */     public Object[] toArray() {
/* 124 */       Object[] array = this.collection.toArray();
/* 125 */       for (int i = 0; i < array.length; i++) {
/* 126 */         array[i] = new AbstractInputCheckedMapDecorator.MapEntry((Map.Entry)array[i], this.parent);
/*     */       }
/* 128 */       return array;
/*     */     }
/*     */     
/*     */     public <T> T[] toArray(T[] array) {
/* 132 */       Object[] result = array;
/* 133 */       if (array.length > 0)
/*     */       {
/*     */ 
/* 136 */         result = (Object[])Array.newInstance(array.getClass().getComponentType(), 0);
/*     */       }
/* 138 */       result = this.collection.toArray(result);
/* 139 */       for (int i = 0; i < result.length; i++) {
/* 140 */         result[i] = new AbstractInputCheckedMapDecorator.MapEntry((Map.Entry)result[i], this.parent);
/*     */       }
/*     */       
/*     */ 
/* 144 */       if (result.length > array.length) {
/* 145 */         return (Object[])result;
/*     */       }
/*     */       
/*     */ 
/* 149 */       System.arraycopy(result, 0, array, 0, result.length);
/* 150 */       if (array.length > result.length) {
/* 151 */         array[result.length] = null;
/*     */       }
/* 153 */       return (Object[])array;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   static class EntrySetIterator<K, V>
/*     */     extends AbstractIteratorDecorator<Map.Entry<K, V>>
/*     */   {
/*     */     private final AbstractInputCheckedMapDecorator<K, V> parent;
/*     */     
/*     */ 
/*     */ 
/*     */     protected EntrySetIterator(Iterator<Map.Entry<K, V>> iterator, AbstractInputCheckedMapDecorator<K, V> parent)
/*     */     {
/* 168 */       super();
/* 169 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public Map.Entry<K, V> next() {
/* 173 */       Map.Entry<K, V> entry = (Map.Entry)this.iterator.next();
/* 174 */       return new AbstractInputCheckedMapDecorator.MapEntry(entry, this.parent);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   static class MapEntry<K, V>
/*     */     extends AbstractMapEntryDecorator<K, V>
/*     */   {
/*     */     private final AbstractInputCheckedMapDecorator<K, V> parent;
/*     */     
/*     */ 
/*     */ 
/*     */     protected MapEntry(Map.Entry<K, V> entry, AbstractInputCheckedMapDecorator<K, V> parent)
/*     */     {
/* 189 */       super();
/* 190 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public V setValue(V value) {
/* 194 */       value = this.parent.checkSetValue(value);
/* 195 */       return (V)this.entry.setValue(value);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/AbstractInputCheckedMapDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */