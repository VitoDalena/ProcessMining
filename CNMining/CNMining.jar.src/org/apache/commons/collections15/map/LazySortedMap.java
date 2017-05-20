/*     */ package org.apache.commons.collections15.map;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.SortedMap;
/*     */ import org.apache.commons.collections15.Factory;
/*     */ import org.apache.commons.collections15.Transformer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LazySortedMap<K, V>
/*     */   extends LazyMap<K, V>
/*     */   implements SortedMap<K, V>
/*     */ {
/*     */   private static final long serialVersionUID = 2715322183617658933L;
/*     */   
/*     */   public static <K, V> SortedMap<K, V> decorate(SortedMap<K, V> map, Factory<V> factory)
/*     */   {
/*  69 */     return new LazySortedMap(map, factory);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <K, V> SortedMap<K, V> decorate(SortedMap<K, V> map, Transformer<K, V> transformer)
/*     */   {
/*  80 */     return new LazySortedMap(map, transformer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected LazySortedMap(SortedMap<K, V> map, Factory<V> factory)
/*     */   {
/*  92 */     super(map, factory);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected LazySortedMap(SortedMap<K, V> map, Transformer<K, V> transformer)
/*     */   {
/* 103 */     super(map, transformer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SortedMap<K, V> getSortedMap()
/*     */   {
/* 113 */     return (SortedMap)this.map;
/*     */   }
/*     */   
/*     */   public K firstKey()
/*     */   {
/* 118 */     return (K)getSortedMap().firstKey();
/*     */   }
/*     */   
/*     */   public K lastKey() {
/* 122 */     return (K)getSortedMap().lastKey();
/*     */   }
/*     */   
/*     */   public Comparator<? super K> comparator() {
/* 126 */     return getSortedMap().comparator();
/*     */   }
/*     */   
/*     */   public SortedMap<K, V> subMap(K fromKey, K toKey) {
/* 130 */     SortedMap<K, V> map = getSortedMap().subMap(fromKey, toKey);
/* 131 */     return new LazySortedMap(map, this.transformer);
/*     */   }
/*     */   
/*     */   public SortedMap<K, V> headMap(K toKey) {
/* 135 */     SortedMap<K, V> map = getSortedMap().headMap(toKey);
/* 136 */     return new LazySortedMap(map, this.transformer);
/*     */   }
/*     */   
/*     */   public SortedMap<K, V> tailMap(K fromKey) {
/* 140 */     SortedMap<K, V> map = getSortedMap().tailMap(fromKey);
/* 141 */     return new LazySortedMap(map, this.transformer);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/LazySortedMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */