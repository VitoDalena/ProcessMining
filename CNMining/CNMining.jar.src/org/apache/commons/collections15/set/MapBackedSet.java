/*     */ package org.apache.commons.collections15.set;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class MapBackedSet<K, V>
/*     */   implements Set<K>, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 6723912213766056587L;
/*     */   protected final Map<K, V> map;
/*     */   protected final V dummyValue;
/*     */   
/*     */   public static <K, V> Set<K> decorate(Map<K, V> map)
/*     */   {
/*  62 */     return decorate(map, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <K, V> Set<K> decorate(Map<K, V> map, V dummyValue)
/*     */   {
/*  73 */     if (map == null) {
/*  74 */       throw new IllegalArgumentException("The map must not be null");
/*     */     }
/*  76 */     return new MapBackedSet(map, dummyValue);
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
/*     */   private MapBackedSet(Map<K, V> map, V dummyValue)
/*     */   {
/*  89 */     this.map = map;
/*  90 */     this.dummyValue = dummyValue;
/*     */   }
/*     */   
/*     */   public int size()
/*     */   {
/*  95 */     return this.map.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  99 */     return this.map.isEmpty();
/*     */   }
/*     */   
/*     */   public Iterator<K> iterator() {
/* 103 */     return this.map.keySet().iterator();
/*     */   }
/*     */   
/*     */   public boolean contains(Object obj) {
/* 107 */     return this.map.containsKey(obj);
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> coll) {
/* 111 */     return this.map.keySet().containsAll(coll);
/*     */   }
/*     */   
/*     */   public boolean add(K obj) {
/* 115 */     int size = this.map.size();
/* 116 */     this.map.put(obj, this.dummyValue);
/* 117 */     return this.map.size() != size;
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends K> coll) {
/* 121 */     int size = this.map.size();
/* 122 */     for (Iterator<? extends K> it = coll.iterator(); it.hasNext();) {
/* 123 */       K obj = it.next();
/* 124 */       this.map.put(obj, this.dummyValue);
/*     */     }
/* 126 */     return this.map.size() != size;
/*     */   }
/*     */   
/*     */   public boolean remove(Object obj) {
/* 130 */     int size = this.map.size();
/* 131 */     this.map.remove(obj);
/* 132 */     return this.map.size() != size;
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> coll) {
/* 136 */     return this.map.keySet().removeAll(coll);
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> coll) {
/* 140 */     return this.map.keySet().retainAll(coll);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 144 */     this.map.clear();
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 148 */     return this.map.keySet().toArray();
/*     */   }
/*     */   
/*     */   public <T> T[] toArray(T[] array) {
/* 152 */     return this.map.keySet().toArray(array);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 156 */     return this.map.keySet().equals(obj);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 160 */     return this.map.keySet().hashCode();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/set/MapBackedSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */