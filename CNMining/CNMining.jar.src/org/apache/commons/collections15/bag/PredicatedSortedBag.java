/*     */ package org.apache.commons.collections15.bag;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import org.apache.commons.collections15.Predicate;
/*     */ import org.apache.commons.collections15.SortedBag;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PredicatedSortedBag<E>
/*     */   extends PredicatedBag<E>
/*     */   implements SortedBag<E>
/*     */ {
/*     */   private static final long serialVersionUID = 3448581314086406616L;
/*     */   
/*     */   public static <E> SortedBag<E> decorate(SortedBag<E> bag, Predicate<? super E> predicate)
/*     */   {
/*  62 */     return new PredicatedSortedBag(bag, predicate);
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
/*     */   protected PredicatedSortedBag(SortedBag<E> bag, Predicate<? super E> predicate)
/*     */   {
/*  78 */     super(bag, predicate);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SortedBag<E> getSortedBag()
/*     */   {
/*  87 */     return (SortedBag)getCollection();
/*     */   }
/*     */   
/*     */   public E first()
/*     */   {
/*  92 */     return (E)getSortedBag().first();
/*     */   }
/*     */   
/*     */   public E last() {
/*  96 */     return (E)getSortedBag().last();
/*     */   }
/*     */   
/*     */   public Comparator<? super E> comparator() {
/* 100 */     return getSortedBag().comparator();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/bag/PredicatedSortedBag.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */