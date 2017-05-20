/*     */ package org.apache.commons.collections15.set;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.SortedSet;
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
/*     */ public class TransformedSortedSet
/*     */   extends TransformedSet
/*     */   implements SortedSet
/*     */ {
/*     */   private static final long serialVersionUID = -1675486811351124386L;
/*     */   
/*     */   public static <I, O> SortedSet<O> decorate(SortedSet<I> set, Transformer<? super I, ? extends O> transformer)
/*     */   {
/*  56 */     return new TransformedSortedSet(set, transformer);
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
/*     */   protected TransformedSortedSet(SortedSet set, Transformer transformer)
/*     */   {
/*  71 */     super(set, transformer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SortedSet getSortedSet()
/*     */   {
/*  80 */     return (SortedSet)this.collection;
/*     */   }
/*     */   
/*     */   public Object first()
/*     */   {
/*  85 */     return getSortedSet().first();
/*     */   }
/*     */   
/*     */   public Object last() {
/*  89 */     return getSortedSet().last();
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/*  93 */     return getSortedSet().comparator();
/*     */   }
/*     */   
/*     */   public SortedSet subSet(Object fromElement, Object toElement)
/*     */   {
/*  98 */     SortedSet set = getSortedSet().subSet(fromElement, toElement);
/*  99 */     return new TransformedSortedSet(set, this.transformer);
/*     */   }
/*     */   
/*     */   public SortedSet headSet(Object toElement) {
/* 103 */     SortedSet set = getSortedSet().headSet(toElement);
/* 104 */     return new TransformedSortedSet(set, this.transformer);
/*     */   }
/*     */   
/*     */   public SortedSet tailSet(Object fromElement) {
/* 108 */     SortedSet set = getSortedSet().tailSet(fromElement);
/* 109 */     return new TransformedSortedSet(set, this.transformer);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/set/TransformedSortedSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */