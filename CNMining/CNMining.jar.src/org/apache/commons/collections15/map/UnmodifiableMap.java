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
/*     */ import org.apache.commons.collections15.IterableMap;
/*     */ import org.apache.commons.collections15.MapIterator;
/*     */ import org.apache.commons.collections15.Unmodifiable;
/*     */ import org.apache.commons.collections15.collection.UnmodifiableCollection;
/*     */ import org.apache.commons.collections15.iterators.EntrySetMapIterator;
/*     */ import org.apache.commons.collections15.iterators.UnmodifiableMapIterator;
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
/*     */ public final class UnmodifiableMap<K, V>
/*     */   extends AbstractMapDecorator<K, V>
/*     */   implements IterableMap<K, V>, Unmodifiable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2737023427269031941L;
/*     */   
/*     */   public static <K, V> Map<K, V> decorate(Map<K, V> map)
/*     */   {
/*  58 */     if ((map instanceof Unmodifiable)) {
/*  59 */       return map;
/*     */     }
/*  61 */     return new UnmodifiableMap(map);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private UnmodifiableMap(Map<K, V> map)
/*     */   {
/*  72 */     super(map);
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
/*  84 */     out.defaultWriteObject();
/*  85 */     out.writeObject(this.map);
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
/*  97 */     in.defaultReadObject();
/*  98 */     this.map = ((Map)in.readObject());
/*     */   }
/*     */   
/*     */   public void clear()
/*     */   {
/* 103 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public V put(K key, V value) {
/* 107 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends V> mapToCopy) {
/* 111 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public V remove(Object key) {
/* 115 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public MapIterator<K, V> mapIterator() {
/* 119 */     if ((this.map instanceof IterableMap)) {
/* 120 */       MapIterator<K, V> it = ((IterableMap)this.map).mapIterator();
/* 121 */       return UnmodifiableMapIterator.decorate(it);
/*     */     }
/* 123 */     MapIterator<K, V> it = new EntrySetMapIterator(this.map);
/* 124 */     return UnmodifiableMapIterator.decorate(it);
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<K, V>> entrySet()
/*     */   {
/* 129 */     Set<Map.Entry<K, V>> set = super.entrySet();
/* 130 */     return UnmodifiableEntrySet.decorate(set);
/*     */   }
/*     */   
/*     */   public Set<K> keySet() {
/* 134 */     Set<K> set = super.keySet();
/* 135 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Collection<V> values() {
/* 139 */     Collection<V> coll = super.values();
/* 140 */     return UnmodifiableCollection.decorate(coll);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/UnmodifiableMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */