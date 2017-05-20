/*     */ package org.apache.commons.collections15.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.SortedMap;
/*     */ import org.apache.commons.collections15.Unmodifiable;
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
/*     */ public final class UnmodifiableSortedMap<K, V>
/*     */   extends AbstractSortedMapDecorator<K, V>
/*     */   implements Unmodifiable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 5805344239827376360L;
/*     */   
/*     */   public static <K, V> SortedMap<K, V> decorate(SortedMap<K, V> map)
/*     */   {
/*  52 */     if ((map instanceof Unmodifiable)) {
/*  53 */       return map;
/*     */     }
/*  55 */     return new UnmodifiableSortedMap(map);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private UnmodifiableSortedMap(SortedMap<K, V> map)
/*     */   {
/*  66 */     super(map);
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
/*  78 */     out.defaultWriteObject();
/*  79 */     out.writeObject(this.map);
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
/*  91 */     in.defaultReadObject();
/*  92 */     this.map = ((Map)in.readObject());
/*     */   }
/*     */   
/*     */   public void clear()
/*     */   {
/*  97 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public V put(K key, V value) {
/* 101 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends V> mapToCopy) {
/* 105 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public V remove(Object key) {
/* 109 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<K, V>> entrySet() {
/* 113 */     Set<Map.Entry<K, V>> set = super.entrySet();
/* 114 */     return UnmodifiableEntrySet.decorate(set);
/*     */   }
/*     */   
/*     */   public Set<K> keySet() {
/* 118 */     Set<K> set = super.keySet();
/* 119 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Collection<V> values() {
/* 123 */     Collection<V> coll = super.values();
/* 124 */     return UnmodifiableCollection.decorate(coll);
/*     */   }
/*     */   
/*     */   public K firstKey()
/*     */   {
/* 129 */     return (K)getSortedMap().firstKey();
/*     */   }
/*     */   
/*     */   public K lastKey() {
/* 133 */     return (K)getSortedMap().lastKey();
/*     */   }
/*     */   
/*     */   public Comparator<? super K> comparator() {
/* 137 */     return getSortedMap().comparator();
/*     */   }
/*     */   
/*     */   public SortedMap<K, V> subMap(K fromKey, K toKey) {
/* 141 */     SortedMap<K, V> map = getSortedMap().subMap(fromKey, toKey);
/* 142 */     return new UnmodifiableSortedMap(map);
/*     */   }
/*     */   
/*     */   public SortedMap<K, V> headMap(K toKey) {
/* 146 */     SortedMap<K, V> map = getSortedMap().headMap(toKey);
/* 147 */     return new UnmodifiableSortedMap(map);
/*     */   }
/*     */   
/*     */   public SortedMap<K, V> tailMap(K fromKey) {
/* 151 */     SortedMap<K, V> map = getSortedMap().tailMap(fromKey);
/* 152 */     return new UnmodifiableSortedMap(map);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/UnmodifiableSortedMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */