/*     */ package org.apache.commons.collections15.set;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.SortedSet;
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
/*     */ public class PredicatedSortedSet<E>
/*     */   extends PredicatedSet<E>
/*     */   implements SortedSet<E>
/*     */ {
/*     */   private static final long serialVersionUID = -9110948148132275052L;
/*     */   
/*     */   public static <E> SortedSet<E> decorate(SortedSet<E> set, Predicate<? super E> predicate)
/*     */   {
/*  61 */     return new PredicatedSortedSet(set, predicate);
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
/*     */   protected PredicatedSortedSet(SortedSet<E> set, Predicate<? super E> predicate)
/*     */   {
/*  77 */     super(set, predicate);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private SortedSet<E> getSortedSet()
/*     */   {
/*  86 */     return (SortedSet)getCollection();
/*     */   }
/*     */   
/*     */   public SortedSet<E> subSet(E fromElement, E toElement)
/*     */   {
/*  91 */     SortedSet sub = getSortedSet().subSet(fromElement, toElement);
/*  92 */     return new PredicatedSortedSet(sub, this.predicate);
/*     */   }
/*     */   
/*     */   public SortedSet<E> headSet(E toElement) {
/*  96 */     SortedSet sub = getSortedSet().headSet(toElement);
/*  97 */     return new PredicatedSortedSet(sub, this.predicate);
/*     */   }
/*     */   
/*     */   public SortedSet<E> tailSet(E fromElement) {
/* 101 */     SortedSet sub = getSortedSet().tailSet(fromElement);
/* 102 */     return new PredicatedSortedSet(sub, this.predicate);
/*     */   }
/*     */   
/*     */   public E first() {
/* 106 */     return (E)getSortedSet().first();
/*     */   }
/*     */   
/*     */   public E last() {
/* 110 */     return (E)getSortedSet().last();
/*     */   }
/*     */   
/*     */   public Comparator<? super E> comparator() {
/* 114 */     return getSortedSet().comparator();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/set/PredicatedSortedSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */