/*     */ package org.apache.commons.collections15.map;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.SortedMap;
/*     */ import org.apache.commons.collections15.Predicate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PredicatedSortedMap<K, V>
/*     */   extends PredicatedMap<K, V>
/*     */   implements SortedMap<K, V>
/*     */ {
/*     */   private static final long serialVersionUID = 3359846175935304332L;
/*     */   
/*     */   public static <K, V> SortedMap<K, V> decorate(SortedMap<K, V> map, Predicate<? super K> keyPredicate, Predicate<? super V> valuePredicate)
/*     */   {
/*  61 */     return new PredicatedSortedMap(map, keyPredicate, valuePredicate);
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
/*     */   protected PredicatedSortedMap(SortedMap<K, V> map, Predicate<? super K> keyPredicate, Predicate<? super V> valuePredicate)
/*     */   {
/*  74 */     super(map, keyPredicate, valuePredicate);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SortedMap<K, V> getSortedMap()
/*     */   {
/*  84 */     return (SortedMap)this.map;
/*     */   }
/*     */   
/*     */   public K firstKey()
/*     */   {
/*  89 */     return (K)getSortedMap().firstKey();
/*     */   }
/*     */   
/*     */   public K lastKey() {
/*  93 */     return (K)getSortedMap().lastKey();
/*     */   }
/*     */   
/*     */   public Comparator<? super K> comparator() {
/*  97 */     return getSortedMap().comparator();
/*     */   }
/*     */   
/*     */   public SortedMap<K, V> subMap(K fromKey, K toKey) {
/* 101 */     SortedMap<K, V> map = getSortedMap().subMap(fromKey, toKey);
/* 102 */     return new PredicatedSortedMap(map, this.keyPredicate, this.valuePredicate);
/*     */   }
/*     */   
/*     */   public SortedMap<K, V> headMap(K toKey) {
/* 106 */     SortedMap map = getSortedMap().headMap(toKey);
/* 107 */     return new PredicatedSortedMap(map, this.keyPredicate, this.valuePredicate);
/*     */   }
/*     */   
/*     */   public SortedMap<K, V> tailMap(K fromKey) {
/* 111 */     SortedMap<K, V> map = getSortedMap().tailMap(fromKey);
/* 112 */     return new PredicatedSortedMap(map, this.keyPredicate, this.valuePredicate);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/PredicatedSortedMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */