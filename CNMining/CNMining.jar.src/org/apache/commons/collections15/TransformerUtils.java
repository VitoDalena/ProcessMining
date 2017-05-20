/*     */ package org.apache.commons.collections15;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.functors.ChainedTransformer;
/*     */ import org.apache.commons.collections15.functors.CloneTransformer;
/*     */ import org.apache.commons.collections15.functors.ClosureTransformer;
/*     */ import org.apache.commons.collections15.functors.ConstantTransformer;
/*     */ import org.apache.commons.collections15.functors.EqualPredicate;
/*     */ import org.apache.commons.collections15.functors.ExceptionTransformer;
/*     */ import org.apache.commons.collections15.functors.FactoryTransformer;
/*     */ import org.apache.commons.collections15.functors.InstantiateTransformer;
/*     */ import org.apache.commons.collections15.functors.InvokerTransformer;
/*     */ import org.apache.commons.collections15.functors.MapTransformer;
/*     */ import org.apache.commons.collections15.functors.NOPTransformer;
/*     */ import org.apache.commons.collections15.functors.PredicateTransformer;
/*     */ import org.apache.commons.collections15.functors.StringValueTransformer;
/*     */ import org.apache.commons.collections15.functors.SwitchTransformer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TransformerUtils
/*     */ {
/*     */   public static Transformer exceptionTransformer()
/*     */   {
/*  69 */     return ExceptionTransformer.INSTANCE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Transformer nullTransformer()
/*     */   {
/*  79 */     return ConstantTransformer.NULL_INSTANCE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Transformer nopTransformer()
/*     */   {
/*  91 */     return NOPTransformer.INSTANCE;
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
/*     */   public static Transformer cloneTransformer()
/*     */   {
/* 108 */     return CloneTransformer.INSTANCE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <T> Transformer<Object, T> constantTransformer(T constantToReturn)
/*     */   {
/* 120 */     return ConstantTransformer.getInstance(constantToReturn);
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
/*     */   public static <T> Transformer<T, T> asTransformer(Closure<T> closure)
/*     */   {
/* 133 */     return ClosureTransformer.getInstance(closure);
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
/*     */   public static <T> Transformer<T, Boolean> asTransformer(Predicate<T> predicate)
/*     */   {
/* 146 */     return PredicateTransformer.getInstance(predicate);
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
/*     */   public static <T> Transformer<Object, T> asTransformer(Factory<T> factory)
/*     */   {
/* 159 */     return FactoryTransformer.getInstance(factory);
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
/*     */   public static <I, M, O> Transformer<I, O> chainedTransformer(Transformer<I, ? extends M> transformer1, Transformer<? super M, O> transformer2)
/*     */   {
/* 173 */     return ChainedTransformer.getInstance(transformer1, transformer2);
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
/*     */   public static <I, O> Transformer<I, O> chainedTransformer(Transformer[] transformers)
/*     */   {
/* 189 */     return ChainedTransformer.getInstance(transformers);
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
/*     */   public static <I, O> Transformer<I, O> chainedTransformer(Collection transformers)
/*     */   {
/* 206 */     return ChainedTransformer.getInstance(transformers);
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
/*     */   public static <I, O> Transformer<I, O> switchTransformer(Predicate<I> predicate, Transformer<? super I, ? extends O> trueTransformer, Transformer<? super I, ? extends O> falseTransformer)
/*     */   {
/* 222 */     return SwitchTransformer.getInstance(new Predicate[] { predicate }, new Transformer[] { trueTransformer }, falseTransformer);
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
/*     */   public static <I, O> Transformer<I, O> switchTransformer(Predicate<? super I>[] predicates, Transformer<? super I, ? extends O>[] transformers)
/*     */   {
/* 241 */     return SwitchTransformer.getInstance(predicates, transformers, null);
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
/*     */   public static <I, O> Transformer<I, O> switchTransformer(Predicate<? super I>[] predicates, Transformer<? super I, ? extends O>[] transformers, Transformer<? super I, ? extends O> defaultTransformer)
/*     */   {
/* 262 */     return SwitchTransformer.getInstance(predicates, transformers, defaultTransformer);
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
/*     */   public static <I, O> Transformer<I, O> switchTransformer(Map<Predicate<? super I>, Transformer<? super I, ? extends O>> predicatesAndTransformers)
/*     */   {
/* 286 */     return SwitchTransformer.getInstance(predicatesAndTransformers);
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
/*     */   public static <I, O> Transformer<I, O> switchMapTransformer(Map<I, Transformer<? super I, ? extends O>> objectsAndTransformers)
/*     */   {
/* 306 */     Transformer[] trs = null;
/* 307 */     Predicate[] preds = null;
/* 308 */     if (objectsAndTransformers == null) {
/* 309 */       throw new IllegalArgumentException("The object and transformer map must not be null");
/*     */     }
/* 311 */     Transformer<? super I, ? extends O> def = (Transformer)objectsAndTransformers.remove(null);
/* 312 */     int size = objectsAndTransformers.size();
/* 313 */     trs = new Transformer[size];
/* 314 */     preds = new Predicate[size];
/* 315 */     int i = 0;
/* 316 */     for (Iterator<Map.Entry<I, Transformer<? super I, ? extends O>>> it = objectsAndTransformers.entrySet().iterator(); it.hasNext();) {
/* 317 */       Map.Entry<I, Transformer<? super I, ? extends O>> entry = (Map.Entry)it.next();
/* 318 */       preds[i] = EqualPredicate.getInstance(entry.getKey());
/* 319 */       trs[i] = ((Transformer)entry.getValue());
/* 320 */       i++;
/*     */     }
/* 322 */     return switchTransformer(preds, trs, def);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Transformer<Class, Object> instantiateTransformer()
/*     */   {
/* 332 */     return InstantiateTransformer.NO_ARG_INSTANCE;
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
/*     */   public static Transformer<Class, Object> instantiateTransformer(Class[] paramTypes, Object[] args)
/*     */   {
/* 347 */     return InstantiateTransformer.getInstance(paramTypes, args);
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
/*     */   public static <I, O> Transformer<I, O> mapTransformer(Map<I, O> map)
/*     */   {
/* 360 */     return MapTransformer.getInstance(map);
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
/*     */   public static Transformer invokerTransformer(String methodName)
/*     */   {
/* 380 */     return InvokerTransformer.getInstance(methodName, null, null);
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
/*     */   public static Transformer invokerTransformer(String methodName, Class[] paramTypes, Object[] args)
/*     */   {
/* 399 */     return InvokerTransformer.getInstance(methodName, paramTypes, args);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <T> Transformer<T, String> stringValueTransformer()
/*     */   {
/* 411 */     return StringValueTransformer.getInstance();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/TransformerUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */