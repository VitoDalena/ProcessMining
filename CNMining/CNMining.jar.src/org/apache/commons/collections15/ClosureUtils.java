/*     */ package org.apache.commons.collections15;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.functors.ChainedClosure;
/*     */ import org.apache.commons.collections15.functors.EqualPredicate;
/*     */ import org.apache.commons.collections15.functors.ExceptionClosure;
/*     */ import org.apache.commons.collections15.functors.ForClosure;
/*     */ import org.apache.commons.collections15.functors.IfClosure;
/*     */ import org.apache.commons.collections15.functors.InvokerTransformer;
/*     */ import org.apache.commons.collections15.functors.NOPClosure;
/*     */ import org.apache.commons.collections15.functors.SwitchClosure;
/*     */ import org.apache.commons.collections15.functors.TransformerClosure;
/*     */ import org.apache.commons.collections15.functors.WhileClosure;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClosureUtils
/*     */ {
/*     */   public static Closure exceptionClosure()
/*     */   {
/*  63 */     return ExceptionClosure.INSTANCE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Closure nopClosure()
/*     */   {
/*  74 */     return NOPClosure.INSTANCE;
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
/*     */   public static <I, O> Closure<I> asClosure(Transformer<I, O> transformer)
/*     */   {
/*  87 */     return TransformerClosure.getInstance(transformer);
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
/*     */   public static <T> Closure<T> forClosure(int count, Closure<T> closure)
/*     */   {
/* 101 */     return ForClosure.getInstance(count, closure);
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
/*     */   public static <T> Closure<T> whileClosure(Predicate<? super T> predicate, Closure<? super T> closure)
/*     */   {
/* 115 */     return WhileClosure.getInstance(predicate, closure, false);
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
/*     */   public static <T> Closure<T> doWhileClosure(Closure<? super T> closure, Predicate<? super T> predicate)
/*     */   {
/* 129 */     return WhileClosure.getInstance(predicate, closure, true);
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
/*     */   public static Closure invokerClosure(String methodName)
/*     */   {
/* 144 */     return asClosure(InvokerTransformer.getInstance(methodName));
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
/*     */   public static Closure invokerClosure(String methodName, Class[] paramTypes, Object[] args)
/*     */   {
/* 162 */     return asClosure(InvokerTransformer.getInstance(methodName, paramTypes, args));
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
/*     */   public static <T> Closure<T> chainedClosure(Closure<T> closure1, Closure<T> closure2)
/*     */   {
/* 176 */     return ChainedClosure.getInstance(closure1, closure2);
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
/*     */   public static <T> Closure<T> chainedClosure(Closure<T>[] closures)
/*     */   {
/* 190 */     return ChainedClosure.getInstance(closures);
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
/*     */   public static <T> Closure<T> chainedClosure(Collection<T> closures)
/*     */   {
/* 206 */     return ChainedClosure.getInstance(closures);
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
/*     */   public static <T> Closure<T> ifClosure(Predicate<? super T> predicate, Closure<? super T> trueClosure, Closure<? super T> falseClosure)
/*     */   {
/* 222 */     return IfClosure.getInstance(predicate, trueClosure, falseClosure);
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
/*     */   public static <T> Closure<T> switchClosure(Predicate<? super T>[] predicates, Closure<? super T>[] closures)
/*     */   {
/* 242 */     return SwitchClosure.getInstance(predicates, closures, null);
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
/*     */   public static <T> Closure<T> switchClosure(Predicate<? super T>[] predicates, Closure<? super T>[] closures, Closure<? super T> defaultClosure)
/*     */   {
/* 264 */     return SwitchClosure.getInstance(predicates, closures, defaultClosure);
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
/*     */   public static <T> Closure<T> switchClosure(Map<Predicate<? super T>, Closure<? super T>> predicatesAndClosures)
/*     */   {
/* 287 */     return SwitchClosure.getInstance(predicatesAndClosures);
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
/*     */   public static <T> Closure<T> switchMapClosure(Map<T, Closure<T>> objectsAndClosures)
/*     */   {
/* 307 */     Closure[] trs = null;
/* 308 */     Predicate[] preds = null;
/* 309 */     if (objectsAndClosures == null) {
/* 310 */       throw new IllegalArgumentException("The object and closure map must not be null");
/*     */     }
/* 312 */     Closure def = (Closure)objectsAndClosures.remove(null);
/* 313 */     int size = objectsAndClosures.size();
/* 314 */     trs = new Closure[size];
/* 315 */     preds = new Predicate[size];
/* 316 */     int i = 0;
/* 317 */     for (Iterator it = objectsAndClosures.entrySet().iterator(); it.hasNext();) {
/* 318 */       Map.Entry entry = (Map.Entry)it.next();
/* 319 */       preds[i] = EqualPredicate.getInstance(entry.getKey());
/* 320 */       trs[i] = ((Closure)entry.getValue());
/* 321 */       i++;
/*     */     }
/* 323 */     return switchClosure(preds, trs, def);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/ClosureUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */