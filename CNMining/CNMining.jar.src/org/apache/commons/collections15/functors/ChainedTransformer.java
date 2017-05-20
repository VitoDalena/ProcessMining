/*     */ package org.apache.commons.collections15.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
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
/*     */ public class ChainedTransformer<I, O>
/*     */   implements Transformer<I, O>, Serializable
/*     */ {
/*     */   static final long serialVersionUID = 3514945074733160196L;
/*     */   private final Transformer[] iTransformers;
/*     */   
/*     */   public static <I, O> Transformer<I, O> getInstance(Transformer[] transformers)
/*     */   {
/*  56 */     FunctorUtils.validate(transformers);
/*  57 */     if (transformers.length == 0) {
/*  58 */       return NOPTransformer.INSTANCE;
/*     */     }
/*  60 */     transformers = FunctorUtils.copy(transformers);
/*  61 */     return new ChainedTransformer(transformers);
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
/*     */   public static <I, O> Transformer<I, O> getInstance(Collection<Transformer> transformers)
/*     */   {
/*  75 */     if (transformers == null) {
/*  76 */       throw new IllegalArgumentException("Transformer collection must not be null");
/*     */     }
/*  78 */     if (transformers.size() == 0) {
/*  79 */       return NOPTransformer.INSTANCE;
/*     */     }
/*     */     
/*  82 */     Transformer[] cmds = new Transformer[transformers.size()];
/*  83 */     int i = 0;
/*  84 */     for (Iterator<Transformer> it = transformers.iterator(); it.hasNext();) {
/*  85 */       cmds[(i++)] = ((Transformer)it.next());
/*     */     }
/*  87 */     FunctorUtils.validate(cmds);
/*  88 */     return new ChainedTransformer(cmds);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <I, M, O> Transformer<I, O> getInstance(Transformer<I, ? extends M> transformer1, Transformer<? super M, O> transformer2)
/*     */   {
/* 100 */     if ((transformer1 == null) || (transformer2 == null)) {
/* 101 */       throw new IllegalArgumentException("Transformers must not be null");
/*     */     }
/* 103 */     Transformer[] transformers = { transformer1, transformer2 };
/* 104 */     return new ChainedTransformer(transformers);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ChainedTransformer(Transformer[] transformers)
/*     */   {
/* 115 */     this.iTransformers = transformers;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public O transform(I object)
/*     */   {
/* 125 */     Object intermediate = object;
/* 126 */     for (int i = 0; i < this.iTransformers.length; i++) {
/* 127 */       intermediate = this.iTransformers[i].transform(intermediate);
/*     */     }
/* 129 */     return (O)intermediate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Transformer[] getTransformers()
/*     */   {
/* 139 */     return this.iTransformers;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/ChainedTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */