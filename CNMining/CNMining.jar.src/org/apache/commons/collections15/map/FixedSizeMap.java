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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FixedSizeMap<K, V>
/*     */   extends AbstractMapDecorator<K, V>
/*     */   implements Map<K, V>, BoundedMap<K, V>, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7450927208116179316L;
/*     */   
/*     */   public static <K, V> Map<K, V> decorate(Map<K, V> map)
/*     */   {
/*  66 */     return new FixedSizeMap(map);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected FixedSizeMap(Map<K, V> map)
/*     */   {
/*  77 */     super(map);
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
/*  89 */     out.defaultWriteObject();
/*  90 */     out.writeObject(this.map);
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
/* 102 */     in.defaultReadObject();
/* 103 */     this.map = ((Map)in.readObject());
/*     */   }
/*     */   
/*     */   public V put(K key, V value)
/*     */   {
/* 108 */     if (!this.map.containsKey(key)) {
/* 109 */       throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size");
/*     */     }
/* 111 */     return (V)this.map.put(key, value);
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends V> mapToCopy) {
/* 115 */     for (Iterator it = mapToCopy.keySet().iterator(); it.hasNext();) {
/* 116 */       if (!mapToCopy.containsKey(it.next())) {
/* 117 */         throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size");
/*     */       }
/*     */     }
/* 120 */     this.map.putAll(mapToCopy);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 124 */     throw new UnsupportedOperationException("Map is fixed size");
/*     */   }
/*     */   
/*     */   public V remove(Object key) {
/* 128 */     throw new UnsupportedOperationException("Map is fixed size");
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<K, V>> entrySet() {
/* 132 */     Set<Map.Entry<K, V>> set = this.map.entrySet();
/*     */     
/* 134 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Set<K> keySet() {
/* 138 */     Set<K> set = this.map.keySet();
/* 139 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Collection<V> values() {
/* 143 */     Collection<V> coll = this.map.values();
/* 144 */     return UnmodifiableCollection.decorate(coll);
/*     */   }
/*     */   
/*     */   public boolean isFull() {
/* 148 */     return true;
/*     */   }
/*     */   
/*     */   public int maxSize() {
/* 152 */     return size();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/FixedSizeMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */