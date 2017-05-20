/*     */ package org.apache.commons.collections15;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.SortedSet;
/*     */ import java.util.TreeSet;
/*     */ import org.apache.commons.collections15.set.ListOrderedSet;
/*     */ import org.apache.commons.collections15.set.PredicatedSet;
/*     */ import org.apache.commons.collections15.set.PredicatedSortedSet;
/*     */ import org.apache.commons.collections15.set.SynchronizedSet;
/*     */ import org.apache.commons.collections15.set.SynchronizedSortedSet;
/*     */ import org.apache.commons.collections15.set.TransformedSet;
/*     */ import org.apache.commons.collections15.set.TransformedSortedSet;
/*     */ import org.apache.commons.collections15.set.TypedSet;
/*     */ import org.apache.commons.collections15.set.TypedSortedSet;
/*     */ import org.apache.commons.collections15.set.UnmodifiableSet;
/*     */ import org.apache.commons.collections15.set.UnmodifiableSortedSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SetUtils
/*     */ {
/*  41 */   public static final Set EMPTY_SET = Collections.EMPTY_SET;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  46 */   public static final SortedSet EMPTY_SORTED_SET = UnmodifiableSortedSet.decorate(new TreeSet());
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isEqualSet(Collection set1, Collection set2)
/*     */   {
/*  84 */     if (set1 == set2) {
/*  85 */       return true;
/*     */     }
/*  87 */     if ((set1 == null) || (set2 == null) || (set1.size() != set2.size())) {
/*  88 */       return false;
/*     */     }
/*     */     
/*  91 */     return set1.containsAll(set2);
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
/*     */   public static int hashCodeForSet(Collection set)
/*     */   {
/* 107 */     if (set == null) {
/* 108 */       return 0;
/*     */     }
/* 110 */     int hashCode = 0;
/* 111 */     Iterator it = set.iterator();
/* 112 */     Object obj = null;
/*     */     
/* 114 */     while (it.hasNext()) {
/* 115 */       obj = it.next();
/* 116 */       if (obj != null) {
/* 117 */         hashCode += obj.hashCode();
/*     */       }
/*     */     }
/* 120 */     return hashCode;
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
/*     */   public static <E> Set<E> synchronizedSet(Set<E> set)
/*     */   {
/* 147 */     return SynchronizedSet.decorate(set);
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
/*     */   public static <E> Set<E> unmodifiableSet(Set<E> set)
/*     */   {
/* 160 */     return UnmodifiableSet.decorate(set);
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
/*     */   public static <E> Set<E> predicatedSet(Set<E> set, Predicate<? super E> predicate)
/*     */   {
/* 177 */     return PredicatedSet.decorate(set, predicate);
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
/*     */   public static <E> Set<E> typedSet(Set<E> set, Class<E> type)
/*     */   {
/* 191 */     return TypedSet.decorate(set, type);
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
/*     */   public static <I, O> Set<O> transformedSet(Set<I> set, Transformer<? super I, ? extends O> transformer)
/*     */   {
/* 207 */     return TransformedSet.decorate(set, transformer);
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
/*     */   public static <E> Set<E> orderedSet(Set<E> set)
/*     */   {
/* 222 */     return ListOrderedSet.decorate(set);
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
/*     */   public static <E> SortedSet<E> synchronizedSortedSet(SortedSet<E> set)
/*     */   {
/* 249 */     return SynchronizedSortedSet.decorate(set);
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
/*     */   public static <E> SortedSet<E> unmodifiableSortedSet(SortedSet<E> set)
/*     */   {
/* 262 */     return UnmodifiableSortedSet.decorate(set);
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
/*     */   public static <E> SortedSet<E> predicatedSortedSet(SortedSet<E> set, Predicate<? super E> predicate)
/*     */   {
/* 279 */     return PredicatedSortedSet.decorate(set, predicate);
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
/*     */   public static <E> SortedSet<E> typedSortedSet(SortedSet<E> set, Class<E> type)
/*     */   {
/* 293 */     return TypedSortedSet.decorate(set, type);
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
/*     */   public static <I, O> SortedSet<O> transformedSortedSet(SortedSet<I> set, Transformer<? super I, ? extends O> transformer)
/*     */   {
/* 309 */     return TransformedSortedSet.decorate(set, transformer);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/SetUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */