/*     */ package org.apache.commons.collections15.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
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
/*     */ public final class OnePredicate<T>
/*     */   implements Predicate<T>, PredicateDecorator<T>, Serializable
/*     */ {
/*     */   static final long serialVersionUID = -8125389089924745785L;
/*     */   private final Predicate<? super T>[] iPredicates;
/*     */   
/*     */   public static <T> Predicate<T> getInstance(Predicate<? super T>[] predicates)
/*     */   {
/*  53 */     FunctorUtils.validateMin2(predicates);
/*  54 */     predicates = FunctorUtils.copy(predicates);
/*  55 */     return new OnePredicate(predicates);
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
/*     */   public static <T> Predicate<T> getInstance(Collection<Predicate<? super T>> predicates)
/*     */   {
/*  68 */     Predicate[] preds = FunctorUtils.validate(predicates);
/*  69 */     return new OnePredicate(preds);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public OnePredicate(Predicate<? super T>[] predicates)
/*     */   {
/*  80 */     this.iPredicates = predicates;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean evaluate(T object)
/*     */   {
/*  91 */     boolean match = false;
/*  92 */     for (int i = 0; i < this.iPredicates.length; i++) {
/*  93 */       if (this.iPredicates[i].evaluate(object)) {
/*  94 */         if (match) {
/*  95 */           return false;
/*     */         }
/*  97 */         match = true;
/*     */       }
/*     */     }
/* 100 */     return match;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Predicate<? super T>[] getPredicates()
/*     */   {
/* 110 */     return this.iPredicates;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/OnePredicate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */