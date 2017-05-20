/*     */ package org.apache.commons.collections15.map;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.SortedMap;
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
/*     */ public class TransformedSortedMap
/*     */   extends TransformedMap
/*     */   implements SortedMap
/*     */ {
/*     */   private static final long serialVersionUID = -8751771676410385778L;
/*     */   
/*     */   public static SortedMap decorate(SortedMap map, Transformer keyTransformer, Transformer valueTransformer)
/*     */   {
/*  59 */     return new TransformedSortedMap(map, keyTransformer, valueTransformer);
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
/*     */   protected TransformedSortedMap(SortedMap map, Transformer keyTransformer, Transformer valueTransformer)
/*     */   {
/*  75 */     super(map, keyTransformer, valueTransformer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SortedMap getSortedMap()
/*     */   {
/*  85 */     return (SortedMap)this.map;
/*     */   }
/*     */   
/*     */   public Object firstKey()
/*     */   {
/*  90 */     return getSortedMap().firstKey();
/*     */   }
/*     */   
/*     */   public Object lastKey() {
/*  94 */     return getSortedMap().lastKey();
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/*  98 */     return getSortedMap().comparator();
/*     */   }
/*     */   
/*     */   public SortedMap subMap(Object fromKey, Object toKey) {
/* 102 */     SortedMap map = getSortedMap().subMap(fromKey, toKey);
/* 103 */     return new TransformedSortedMap(map, this.keyTransformer, this.valueTransformer);
/*     */   }
/*     */   
/*     */   public SortedMap headMap(Object toKey) {
/* 107 */     SortedMap map = getSortedMap().headMap(toKey);
/* 108 */     return new TransformedSortedMap(map, this.keyTransformer, this.valueTransformer);
/*     */   }
/*     */   
/*     */   public SortedMap tailMap(Object fromKey) {
/* 112 */     SortedMap map = getSortedMap().tailMap(fromKey);
/* 113 */     return new TransformedSortedMap(map, this.keyTransformer, this.valueTransformer);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/TransformedSortedMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */