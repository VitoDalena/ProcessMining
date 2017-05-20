/*     */ package org.apache.commons.collections15.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.MapIterator;
/*     */ import org.apache.commons.collections15.OrderedMap;
/*     */ import org.apache.commons.collections15.OrderedMapIterator;
/*     */ import org.apache.commons.collections15.Unmodifiable;
/*     */ import org.apache.commons.collections15.collection.UnmodifiableCollection;
/*     */ import org.apache.commons.collections15.iterators.UnmodifiableMapIterator;
/*     */ import org.apache.commons.collections15.iterators.UnmodifiableOrderedMapIterator;
/*     */ import org.apache.commons.collections15.set.UnmodifiableSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class UnmodifiableOrderedMap<K, V>
/*     */   extends AbstractOrderedMapDecorator<K, V>
/*     */   implements Unmodifiable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8136428161720526266L;
/*     */   
/*     */   public static <K, V> OrderedMap<K, V> decorate(OrderedMap<K, V> map)
/*     */   {
/*  59 */     if ((map instanceof Unmodifiable)) {
/*  60 */       return map;
/*     */     }
/*  62 */     return new UnmodifiableOrderedMap(map);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private UnmodifiableOrderedMap(OrderedMap<K, V> map)
/*     */   {
/*  73 */     super(map);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void writeObject(ObjectOutputStream out)
/*     */     throws IOException
/*     */   {
/*  85 */     out.defaultWriteObject();
/*  86 */     out.writeObject(this.map);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void readObject(ObjectInputStream in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/*  98 */     in.defaultReadObject();
/*  99 */     this.map = ((Map)in.readObject());
/*     */   }
/*     */   
/*     */   public MapIterator<K, V> mapIterator()
/*     */   {
/* 104 */     MapIterator<K, V> it = getOrderedMap().mapIterator();
/* 105 */     return UnmodifiableMapIterator.decorate(it);
/*     */   }
/*     */   
/*     */   public OrderedMapIterator<K, V> orderedMapIterator() {
/* 109 */     OrderedMapIterator<K, V> it = getOrderedMap().orderedMapIterator();
/* 110 */     return UnmodifiableOrderedMapIterator.decorate(it);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 114 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public V put(K key, V value) {
/* 118 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends V> mapToCopy) {
/* 122 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public V remove(Object key) {
/* 126 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<K, V>> entrySet() {
/* 130 */     Set<Map.Entry<K, V>> set = super.entrySet();
/* 131 */     return UnmodifiableEntrySet.decorate(set);
/*     */   }
/*     */   
/*     */   public Set<K> keySet() {
/* 135 */     Set<K> set = super.keySet();
/* 136 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Collection<V> values() {
/* 140 */     Collection<V> coll = super.values();
/* 141 */     return UnmodifiableCollection.decorate(coll);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/UnmodifiableOrderedMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */