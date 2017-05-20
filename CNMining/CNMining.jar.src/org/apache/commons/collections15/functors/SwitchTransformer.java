/*     */ package org.apache.commons.collections15.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
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
/*     */ public class SwitchTransformer<I, O>
/*     */   implements Transformer<I, O>, Serializable
/*     */ {
/*     */   static final long serialVersionUID = -6404460890903469332L;
/*     */   private final Predicate<? super I>[] iPredicates;
/*     */   private final Transformer<? super I, ? extends O>[] iTransformers;
/*     */   private final Transformer<? super I, ? extends O> iDefault;
/*     */   
/*     */   public static <I, O> Transformer<I, O> getInstance(Predicate<? super I>[] predicates, Transformer<? super I, ? extends O>[] transformers, Transformer<? super I, ? extends O> defaultTransformer)
/*     */   {
/*  65 */     FunctorUtils.validate(predicates);
/*  66 */     FunctorUtils.validate(transformers);
/*  67 */     if (predicates.length != transformers.length) {
/*  68 */       throw new IllegalArgumentException("The predicate and transformer arrays must be the same size");
/*     */     }
/*  70 */     if (predicates.length == 0) {
/*  71 */       return defaultTransformer == null ? ConstantTransformer.NULL_INSTANCE : defaultTransformer;
/*     */     }
/*  73 */     predicates = FunctorUtils.copy(predicates);
/*  74 */     transformers = FunctorUtils.copy(transformers);
/*  75 */     return new SwitchTransformer(predicates, transformers, defaultTransformer);
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
/*     */   public static <I, O> Transformer<I, O> getInstance(Map<Predicate<? super I>, Transformer<? super I, ? extends O>> predicatesAndTransformers)
/*     */   {
/*  96 */     Transformer<? super I, ? extends O>[] transformers = null;
/*  97 */     Predicate<? super I>[] preds = null;
/*  98 */     if (predicatesAndTransformers == null) {
/*  99 */       throw new IllegalArgumentException("The predicate and transformer map must not be null");
/*     */     }
/* 101 */     if (predicatesAndTransformers.size() == 0) {
/* 102 */       return ConstantTransformer.NULL_INSTANCE;
/*     */     }
/*     */     
/* 105 */     Transformer<? super I, ? extends O> defaultTransformer = (Transformer)predicatesAndTransformers.remove(null);
/* 106 */     int size = predicatesAndTransformers.size();
/* 107 */     if (size == 0) {
/* 108 */       return defaultTransformer == null ? ConstantTransformer.NULL_INSTANCE : defaultTransformer;
/*     */     }
/* 110 */     transformers = new Transformer[size];
/* 111 */     preds = new Predicate[size];
/* 112 */     int i = 0;
/* 113 */     for (Iterator<Map.Entry<Predicate<? super I>, Transformer<? super I, ? extends O>>> it = predicatesAndTransformers.entrySet().iterator(); it.hasNext();) {
/* 114 */       Map.Entry<Predicate<? super I>, Transformer<? super I, ? extends O>> entry = (Map.Entry)it.next();
/* 115 */       preds[i] = ((Predicate)entry.getKey());
/* 116 */       transformers[i] = ((Transformer)entry.getValue());
/* 117 */       i++;
/*     */     }
/* 119 */     return new SwitchTransformer(preds, transformers, defaultTransformer);
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
/*     */   public SwitchTransformer(Predicate<? super I>[] predicates, Transformer<? super I, ? extends O>[] transformers, Transformer<? super I, ? extends O> defaultTransformer)
/*     */   {
/* 132 */     this.iPredicates = predicates;
/* 133 */     this.iTransformers = transformers;
/* 134 */     this.iDefault = (defaultTransformer == null ? ConstantTransformer.NULL_INSTANCE : defaultTransformer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public O transform(I input)
/*     */   {
/* 145 */     for (int i = 0; i < this.iPredicates.length; i++) {
/* 146 */       if (this.iPredicates[i].evaluate(input) == true) {
/* 147 */         return (O)this.iTransformers[i].transform(input);
/*     */       }
/*     */     }
/* 150 */     return (O)this.iDefault.transform(input);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Predicate<? super I>[] getPredicates()
/*     */   {
/* 160 */     return this.iPredicates;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Transformer<? super I, ? extends O>[] getTransformers()
/*     */   {
/* 170 */     return this.iTransformers;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Transformer<? super I, ? extends O> getDefaultTransformer()
/*     */   {
/* 180 */     return this.iDefault;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/SwitchTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */