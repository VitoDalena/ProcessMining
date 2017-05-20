/*     */ package org.apache.commons.collections15;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import org.apache.commons.collections15.comparators.BooleanComparator;
/*     */ import org.apache.commons.collections15.comparators.ComparableComparator;
/*     */ import org.apache.commons.collections15.comparators.ComparatorChain;
/*     */ import org.apache.commons.collections15.comparators.NullComparator;
/*     */ import org.apache.commons.collections15.comparators.ReverseComparator;
/*     */ import org.apache.commons.collections15.comparators.TransformingComparator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ComparatorUtils
/*     */ {
/*  51 */   public static final Comparator NATURAL_COMPARATOR = ;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Comparator naturalComparator()
/*     */   {
/*  59 */     return NATURAL_COMPARATOR;
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
/*     */   public static <T> Comparator<T> chainedComparator(Comparator<T> comparator1, Comparator<T> comparator2)
/*     */   {
/*  74 */     return chainedComparator(new Comparator[] { comparator1, comparator2 });
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
/*     */   public static <T> Comparator<T> chainedComparator(Comparator<T>[] comparators)
/*     */   {
/*  87 */     ComparatorChain chain = new ComparatorChain();
/*  88 */     for (int i = 0; i < comparators.length; i++) {
/*  89 */       if (comparators[i] == null) {
/*  90 */         throw new NullPointerException("Comparator cannot be null");
/*     */       }
/*  92 */       chain.addComparator(comparators[i]);
/*     */     }
/*  94 */     return chain;
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
/*     */   public static <T> Comparator<T> chainedComparator(Collection<T> comparators)
/*     */   {
/* 109 */     return chainedComparator((Comparator[])comparators.toArray(new Comparator[comparators.size()]));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <T> Comparator<T> reversedComparator(Comparator<T> comparator)
/*     */   {
/* 120 */     if (comparator == null) {
/* 121 */       comparator = NATURAL_COMPARATOR;
/*     */     }
/* 123 */     return new ReverseComparator(comparator);
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
/*     */   public static Comparator<Boolean> booleanComparator(boolean trueFirst)
/*     */   {
/* 139 */     return BooleanComparator.getBooleanComparator(trueFirst);
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
/*     */   public static <T> Comparator<T> nullLowComparator(Comparator<T> comparator)
/*     */   {
/* 154 */     if (comparator == null) {
/* 155 */       comparator = NATURAL_COMPARATOR;
/*     */     }
/* 157 */     return new NullComparator(comparator, false);
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
/*     */   public static <T> Comparator<T> nullHighComparator(Comparator<T> comparator)
/*     */   {
/* 172 */     if (comparator == null) {
/* 173 */       comparator = NATURAL_COMPARATOR;
/*     */     }
/* 175 */     return new NullComparator(comparator, true);
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
/*     */   public static <I, O> Comparator<O> transformedComparator(Comparator<I> comparator, Transformer<I, O> transformer)
/*     */   {
/* 191 */     if (comparator == null) {
/* 192 */       comparator = NATURAL_COMPARATOR;
/*     */     }
/* 194 */     return new TransformingComparator(transformer, comparator);
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
/*     */   public static <T> T min(T o1, T o2, Comparator<T> comparator)
/*     */   {
/* 208 */     if (comparator == null) {
/* 209 */       comparator = NATURAL_COMPARATOR;
/*     */     }
/* 211 */     int c = comparator.compare(o1, o2);
/* 212 */     return c < 0 ? o1 : o2;
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
/*     */   public static <T> T max(T o1, T o2, Comparator<T> comparator)
/*     */   {
/* 226 */     if (comparator == null) {
/* 227 */       comparator = NATURAL_COMPARATOR;
/*     */     }
/* 229 */     int c = comparator.compare(o1, o2);
/* 230 */     return c > 0 ? o1 : o2;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/ComparatorUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */