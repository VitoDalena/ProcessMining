/*     */ package org.apache.commons.collections15;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.collections15.functors.AllPredicate;
/*     */ import org.apache.commons.collections15.functors.AndPredicate;
/*     */ import org.apache.commons.collections15.functors.AnyPredicate;
/*     */ import org.apache.commons.collections15.functors.EqualPredicate;
/*     */ import org.apache.commons.collections15.functors.ExceptionPredicate;
/*     */ import org.apache.commons.collections15.functors.FalsePredicate;
/*     */ import org.apache.commons.collections15.functors.IdentityPredicate;
/*     */ import org.apache.commons.collections15.functors.InstanceofPredicate;
/*     */ import org.apache.commons.collections15.functors.InvokerTransformer;
/*     */ import org.apache.commons.collections15.functors.NonePredicate;
/*     */ import org.apache.commons.collections15.functors.NotNullPredicate;
/*     */ import org.apache.commons.collections15.functors.NotPredicate;
/*     */ import org.apache.commons.collections15.functors.NullIsExceptionPredicate;
/*     */ import org.apache.commons.collections15.functors.NullIsFalsePredicate;
/*     */ import org.apache.commons.collections15.functors.NullIsTruePredicate;
/*     */ import org.apache.commons.collections15.functors.NullPredicate;
/*     */ import org.apache.commons.collections15.functors.OnePredicate;
/*     */ import org.apache.commons.collections15.functors.OrPredicate;
/*     */ import org.apache.commons.collections15.functors.TransformedPredicate;
/*     */ import org.apache.commons.collections15.functors.TransformerPredicate;
/*     */ import org.apache.commons.collections15.functors.TruePredicate;
/*     */ import org.apache.commons.collections15.functors.UniquePredicate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PredicateUtils
/*     */ {
/*     */   public static Predicate exceptionPredicate()
/*     */   {
/*  73 */     return ExceptionPredicate.INSTANCE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <T> Predicate<T> truePredicate()
/*     */   {
/*  83 */     return TruePredicate.getInstance();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <T> Predicate<T> falsePredicate()
/*     */   {
/*  93 */     return FalsePredicate.getInstance();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <T> Predicate<T> nullPredicate()
/*     */   {
/* 103 */     return NullPredicate.getInstance();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <T> Predicate<T> notNullPredicate()
/*     */   {
/* 113 */     return NotNullPredicate.getInstance();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <T> Predicate<T> equalPredicate(T value)
/*     */   {
/* 125 */     return EqualPredicate.getInstance(value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <T> Predicate<T> identityPredicate(T value)
/*     */   {
/* 137 */     return IdentityPredicate.getInstance(value);
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
/*     */   public static Predicate instanceofPredicate(Class type)
/*     */   {
/* 152 */     return InstanceofPredicate.getInstance(type);
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
/*     */   public static <T> Predicate<T> uniquePredicate()
/*     */   {
/* 167 */     return UniquePredicate.getInstance();
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
/*     */   public static Predicate invokerPredicate(String methodName)
/*     */   {
/* 188 */     return asPredicate(InvokerTransformer.getInstance(methodName));
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
/*     */   public static Predicate invokerPredicate(String methodName, Class[] paramTypes, Object[] args)
/*     */   {
/* 212 */     return asPredicate(InvokerTransformer.getInstance(methodName, paramTypes, args));
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
/*     */   public static <T> Predicate<T> andPredicate(Predicate<? super T> predicate1, Predicate<? super T> predicate2)
/*     */   {
/* 229 */     return AndPredicate.getInstance(predicate1, predicate2);
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
/*     */   public static <T> Predicate<T> allPredicate(Predicate<? super T>... predicates)
/*     */   {
/* 244 */     return AllPredicate.getInstance(predicates);
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
/*     */   public static <T> Predicate<T> allPredicate(Collection<Predicate<? super T>> predicates)
/*     */   {
/* 259 */     return AllPredicate.getInstance(predicates);
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
/*     */   public static <T> Predicate<T> orPredicate(Predicate<? super T> predicate1, Predicate<? super T> predicate2)
/*     */   {
/* 273 */     return OrPredicate.getInstance(predicate1, predicate2);
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
/*     */   public static <T> Predicate<T> anyPredicate(Predicate<? super T>... predicates)
/*     */   {
/* 288 */     return AnyPredicate.getInstance(predicates);
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
/*     */   public static <T> Predicate<T> anyPredicate(Collection<Predicate<? super T>> predicates)
/*     */   {
/* 303 */     return AnyPredicate.getInstance(predicates);
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
/*     */   public static <T> Predicate<T> eitherPredicate(Predicate<? super T> predicate1, Predicate<? super T> predicate2)
/*     */   {
/* 318 */     return onePredicate(new Predicate[] { predicate1, predicate2 });
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
/*     */   public static <T> Predicate<T> onePredicate(Predicate<? super T>... predicates)
/*     */   {
/* 333 */     return OnePredicate.getInstance(predicates);
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
/*     */   public static <T> Predicate<T> onePredicate(Collection<Predicate<? super T>> predicates)
/*     */   {
/* 348 */     return OnePredicate.getInstance(predicates);
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
/*     */   public static <T> Predicate<T> neitherPredicate(Predicate<? super T> predicate1, Predicate<? super T> predicate2)
/*     */   {
/* 363 */     return nonePredicate(new Predicate[] { predicate1, predicate2 });
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
/*     */   public static <T> Predicate<T> nonePredicate(Predicate<? super T>... predicates)
/*     */   {
/* 378 */     return NonePredicate.getInstance(predicates);
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
/*     */   public static <T> Predicate<T> nonePredicate(Collection<Predicate<? super T>> predicates)
/*     */   {
/* 393 */     return NonePredicate.getInstance(predicates);
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
/*     */   public static <T> Predicate<T> notPredicate(Predicate<T> predicate)
/*     */   {
/* 406 */     return NotPredicate.getInstance(predicate);
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
/*     */   public static <T> Predicate<T> asPredicate(Transformer<T, Boolean> transformer)
/*     */   {
/* 423 */     return TransformerPredicate.getInstance(transformer);
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
/*     */   public static <T> Predicate<T> nullIsExceptionPredicate(Predicate<T> predicate)
/*     */   {
/* 440 */     return NullIsExceptionPredicate.getInstance(predicate);
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
/*     */   public static <T> Predicate<T> nullIsFalsePredicate(Predicate<T> predicate)
/*     */   {
/* 454 */     return NullIsFalsePredicate.getInstance(predicate);
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
/*     */   public static <T> Predicate<T> nullIsTruePredicate(Predicate<T> predicate)
/*     */   {
/* 468 */     return NullIsTruePredicate.getInstance(predicate);
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
/*     */   public static <I, O> Predicate<I> transformedPredicate(Transformer<I, ? extends O> transformer, Predicate<? super O> predicate)
/*     */   {
/* 485 */     return TransformedPredicate.getInstance(transformer, predicate);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/PredicateUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */