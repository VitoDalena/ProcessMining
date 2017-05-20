/*     */ package org.apache.commons.collections15;
/*     */ 
/*     */ import org.apache.commons.collections15.bag.HashBag;
/*     */ import org.apache.commons.collections15.bag.PredicatedBag;
/*     */ import org.apache.commons.collections15.bag.PredicatedSortedBag;
/*     */ import org.apache.commons.collections15.bag.SynchronizedBag;
/*     */ import org.apache.commons.collections15.bag.SynchronizedSortedBag;
/*     */ import org.apache.commons.collections15.bag.TransformedBag;
/*     */ import org.apache.commons.collections15.bag.TransformedSortedBag;
/*     */ import org.apache.commons.collections15.bag.TreeBag;
/*     */ import org.apache.commons.collections15.bag.TypedBag;
/*     */ import org.apache.commons.collections15.bag.TypedSortedBag;
/*     */ import org.apache.commons.collections15.bag.UnmodifiableBag;
/*     */ import org.apache.commons.collections15.bag.UnmodifiableSortedBag;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BagUtils
/*     */ {
/*  37 */   public static final Bag EMPTY_BAG = UnmodifiableBag.decorate(new HashBag());
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  42 */   public static final Bag EMPTY_SORTED_BAG = UnmodifiableSortedBag.decorate(new TreeBag());
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <E> Bag<E> synchronizedBag(Bag<E> bag)
/*     */   {
/*  79 */     return SynchronizedBag.decorate(bag);
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
/*     */   public static <E> Bag<E> unmodifiableBag(Bag<E> bag)
/*     */   {
/*  92 */     return UnmodifiableBag.decorate(bag);
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
/*     */ 
/*     */   public static <E> Bag<E> predicatedBag(Bag<E> bag, Predicate<? super E> predicate)
/*     */   {
/* 109 */     return PredicatedBag.decorate(bag, predicate);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static <E> Bag<E> typedBag(Bag<E> bag, Class<E> type)
/*     */   {
/* 123 */     return TypedBag.decorate(bag, type);
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
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static <I, O> Bag<O> transformedBag(Bag<I> bag, Transformer<I, O> transformer)
/*     */   {
/* 140 */     return TransformedBag.decorate(bag, transformer);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <E> SortedBag<E> synchronizedSortedBag(SortedBag<E> bag)
/*     */   {
/* 172 */     return SynchronizedSortedBag.decorate(bag);
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
/*     */   public static <E> SortedBag<E> unmodifiableSortedBag(SortedBag<E> bag)
/*     */   {
/* 185 */     return UnmodifiableSortedBag.decorate(bag);
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
/*     */ 
/*     */   public static <E> SortedBag<E> predicatedSortedBag(SortedBag<E> bag, Predicate<? super E> predicate)
/*     */   {
/* 202 */     return PredicatedSortedBag.decorate(bag, predicate);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static <E> SortedBag<E> typedSortedBag(SortedBag<E> bag, Class<E> type)
/*     */   {
/* 216 */     return TypedSortedBag.decorate(bag, type);
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
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static <I, O> SortedBag<O> transformedSortedBag(SortedBag<I> bag, Transformer<I, O> transformer)
/*     */   {
/* 233 */     return TransformedSortedBag.decorate(bag, transformer);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/BagUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */