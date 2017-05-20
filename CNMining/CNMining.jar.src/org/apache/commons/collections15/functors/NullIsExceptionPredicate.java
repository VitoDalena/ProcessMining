/*    */ package org.apache.commons.collections15.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections15.FunctorException;
/*    */ import org.apache.commons.collections15.Predicate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class NullIsExceptionPredicate<T>
/*    */   implements Predicate<T>, PredicateDecorator<T>, Serializable
/*    */ {
/*    */   static final long serialVersionUID = 3243449850504576071L;
/*    */   private final Predicate<T> iPredicate;
/*    */   
/*    */   public static <T> Predicate<T> getInstance(Predicate<T> predicate)
/*    */   {
/* 51 */     if (predicate == null) {
/* 52 */       throw new IllegalArgumentException("Predicate must not be null");
/*    */     }
/* 54 */     return new NullIsExceptionPredicate(predicate);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public NullIsExceptionPredicate(Predicate<T> predicate)
/*    */   {
/* 65 */     this.iPredicate = predicate;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean evaluate(T object)
/*    */   {
/* 77 */     if (object == null) {
/* 78 */       throw new FunctorException("Input Object must not be null");
/*    */     }
/* 80 */     return this.iPredicate.evaluate(object);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Predicate<? super T>[] getPredicates()
/*    */   {
/* 90 */     return new Predicate[] { this.iPredicate };
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/NullIsExceptionPredicate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */