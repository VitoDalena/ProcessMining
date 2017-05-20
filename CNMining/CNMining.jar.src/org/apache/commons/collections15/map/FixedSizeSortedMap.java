/*     */ package org.apache.commons.collections15.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.SortedMap;
/*     */ import org.apache.commons.collections15.BoundedMap;
/*     */ import org.apache.commons.collections15.collection.UnmodifiableCollection;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FixedSizeSortedMap<K, V>
/*     */   extends AbstractSortedMapDecorator<K, V>
/*     */   implements SortedMap<K, V>, BoundedMap<K, V>, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3126019624511683653L;
/*     */   
/*     */   public static <K, V> SortedMap<K, V> decorate(SortedMap<K, V> map)
/*     */   {
/*  63 */     return new FixedSizeSortedMap(map);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected FixedSizeSortedMap(SortedMap<K, V> map)
/*     */   {
/*  74 */     super(map);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SortedMap<K, V> getSortedMap()
/*     */   {
/*  83 */     return (SortedMap)this.map;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void writeObject(ObjectOutputStream out)
/*     */     throws IOException
/*     */   {
/*  91 */     out.defaultWriteObject();
/*  92 */     out.writeObject(this.map);
/*     */   }
/*     */   
/*     */ 
/*     */   private void readObject(ObjectInputStream in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/*  99 */     in.defaultReadObject();
/* 100 */     this.map = ((Map)in.readObject());
/*     */   }
/*     */   
/*     */   public V put(K key, V value)
/*     */   {
/* 105 */     if (!this.map.containsKey(key)) {
/* 106 */       throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size");
/*     */     }
/* 108 */     return (V)this.map.put(key, value);
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends V> mapToCopy) {
/* 112 */     for (Iterator it = mapToCopy.keySet().iterator(); it.hasNext();) {
/* 113 */       if (!mapToCopy.containsKey(it.next())) {
/* 114 */         throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size");
/*     */       }
/*     */     }
/* 117 */     this.map.putAll(mapToCopy);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 121 */     throw new UnsupportedOperationException("Map is fixed size");
/*     */   }
/*     */   
/*     */   public V remove(Object key) {
/* 125 */     throw new UnsupportedOperationException("Map is fixed size");
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<K, V>> entrySet() {
/* 129 */     Set<Map.Entry<K, V>> set = this.map.entrySet();
/* 130 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Set<K> keySet() {
/* 134 */     Set<K> set = this.map.keySet();
/* 135 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Collection<V> values() {
/* 139 */     Collection<V> coll = this.map.values();
/* 140 */     return UnmodifiableCollection.decorate(coll);
/*     */   }
/*     */   
/*     */   public SortedMap<K, V> subMap(K fromKey, K toKey)
/*     */   {
/* 145 */     SortedMap<K, V> map = getSortedMap().subMap(fromKey, toKey);
/* 146 */     return new FixedSizeSortedMap(map);
/*     */   }
/*     */   
/*     */   public SortedMap<K, V> headMap(K toKey) {
/* 150 */     SortedMap<K, V> map = getSortedMap().headMap(toKey);
/* 151 */     return new FixedSizeSortedMap(map);
/*     */   }
/*     */   
/*     */   public SortedMap<K, V> tailMap(K fromKey) {
/* 155 */     SortedMap<K, V> map = getSortedMap().tailMap(fromKey);
/* 156 */     return new FixedSizeSortedMap(map);
/*     */   }
/*     */   
/*     */   public boolean isFull() {
/* 160 */     return true;
/*     */   }
/*     */   
/*     */   public int maxSize() {
/* 164 */     return size();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/FixedSizeSortedMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */