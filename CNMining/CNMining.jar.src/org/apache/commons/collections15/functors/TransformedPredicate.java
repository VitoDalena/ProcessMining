/*     */ package org.apache.commons.collections15.functors;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class TransformedPredicate<I, O>
/*     */   implements Predicate<I>, PredicateDecorator<I>, Serializable
/*     */ {
/*     */   static final long serialVersionUID = -5596090919668315834L;
/*     */   private final Transformer<? super I, ? extends O> iTransformer;
/*     */   private final Predicate<? super O> iPredicate;
/*     */   
/*     */   public static <I, O> Predicate<I> getInstance(Transformer<? super I, ? extends O> transformer, Predicate<? super O> predicate)
/*     */   {
/*  60 */     if (transformer == null) {
/*  61 */       throw new IllegalArgumentException("The transformer to call must not be null");
/*     */     }
/*  63 */     if (predicate == null) {
/*  64 */       throw new IllegalArgumentException("The predicate to call must not be null");
/*     */     }
/*  66 */     return new TransformedPredicate(transformer, predicate);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TransformedPredicate(Transformer<? super I, ? extends O> transformer, Predicate<? super O> predicate)
/*     */   {
/*  77 */     this.iTransformer = transformer;
/*  78 */     this.iPredicate = predicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean evaluate(I object)
/*     */   {
/*  89 */     O result = this.iTransformer.transform(object);
/*  90 */     return this.iPredicate.evaluate(result);
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
/*     */   public Predicate[] getPredicates()
/*     */   {
/* 103 */     return new Predicate[] { this.iPredicate };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Transformer<? super I, ? extends O> getTransformer()
/*     */   {
/* 112 */     return this.iTransformer;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/TransformedPredicate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */