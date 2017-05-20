/*     */ package org.apache.commons.collections15.functors;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections15.Closure;
/*     */ import org.apache.commons.collections15.Predicate;
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
/*     */ class FunctorUtils
/*     */ {
/*     */   static <T> Predicate<? super T>[] copy(Predicate<? super T>[] predicates)
/*     */   {
/*  49 */     if (predicates == null) {
/*  50 */       return null;
/*     */     }
/*  52 */     return (Predicate[])predicates.clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static <T> void validate(Predicate<? super T>[] predicates)
/*     */   {
/*  61 */     if (predicates == null) {
/*  62 */       throw new IllegalArgumentException("The predicate array must not be null");
/*     */     }
/*  64 */     for (int i = 0; i < predicates.length; i++) {
/*  65 */       if (predicates[i] == null) {
/*  66 */         throw new IllegalArgumentException("The predicate array must not contain a null predicate, index " + i + " was null");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static <T> void validateMin2(Predicate<? super T>[] predicates)
/*     */   {
/*  77 */     if (predicates == null) {
/*  78 */       throw new IllegalArgumentException("The predicate array must not be null");
/*     */     }
/*  80 */     if (predicates.length < 2) {
/*  81 */       throw new IllegalArgumentException("At least 2 predicates must be specified in the predicate array, size was " + predicates.length);
/*     */     }
/*  83 */     for (int i = 0; i < predicates.length; i++) {
/*  84 */       if (predicates[i] == null) {
/*  85 */         throw new IllegalArgumentException("The predicate array must not contain a null predicate, index " + i + " was null");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static <T> Predicate<? super T>[] validate(Collection<Predicate<? super T>> predicates)
/*     */   {
/*  97 */     if (predicates == null) {
/*  98 */       throw new IllegalArgumentException("The predicate collection must not be null");
/*     */     }
/* 100 */     if (predicates.size() < 2) {
/* 101 */       throw new IllegalArgumentException("At least 2 predicates must be specified in the predicate collection, size was " + predicates.size());
/*     */     }
/*     */     
/* 104 */     Predicate<? super T>[] preds = new Predicate[predicates.size()];
/* 105 */     int i = 0;
/* 106 */     for (Iterator<Predicate<? super T>> it = predicates.iterator(); it.hasNext();) {
/* 107 */       preds[i] = ((Predicate)it.next());
/* 108 */       if (preds[i] == null) {
/* 109 */         throw new IllegalArgumentException("The predicate collection must not contain a null predicate, index " + i + " was null");
/*     */       }
/* 111 */       i++;
/*     */     }
/* 113 */     return preds;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static <T> Closure<? super T>[] copy(Closure<? super T>[] closures)
/*     */   {
/* 123 */     if (closures == null) {
/* 124 */       return null;
/*     */     }
/* 126 */     return (Closure[])closures.clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static <T> void validate(Closure<? super T>[] closures)
/*     */   {
/* 135 */     if (closures == null) {
/* 136 */       throw new IllegalArgumentException("The closure array must not be null");
/*     */     }
/* 138 */     for (int i = 0; i < closures.length; i++) {
/* 139 */       if (closures[i] == null) {
/* 140 */         throw new IllegalArgumentException("The closure array must not contain a null closure, index " + i + " was null");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static <I, O> Transformer<? super I, ? extends O>[] copy(Transformer<? super I, ? extends O>[] transformers)
/*     */   {
/* 152 */     if (transformers == null) {
/* 153 */       return null;
/*     */     }
/* 155 */     return (Transformer[])transformers.clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static <I, O> void validate(Transformer<? super I, ? extends O>[] transformers)
/*     */   {
/* 164 */     if (transformers == null) {
/* 165 */       throw new IllegalArgumentException("The transformer array must not be null");
/*     */     }
/* 167 */     for (int i = 0; i < transformers.length; i++) {
/* 168 */       if (transformers[i] == null) {
/* 169 */         throw new IllegalArgumentException("The transformer array must not contain a null transformer, index " + i + " was null");
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/FunctorUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */