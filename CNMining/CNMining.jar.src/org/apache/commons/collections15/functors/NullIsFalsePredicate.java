/*    */ package org.apache.commons.collections15.functors;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ public final class NullIsFalsePredicate<T>
/*    */   implements Predicate<T>, PredicateDecorator<T>, Serializable
/*    */ {
/*    */   static final long serialVersionUID = -2997501534564735525L;
/*    */   private final Predicate<T> iPredicate;
/*    */   
/*    */   public static <T> Predicate<T> getInstance(Predicate<T> predicate)
/*    */   {
/* 50 */     if (predicate == null) {
/* 51 */       throw new IllegalArgumentException("Predicate must not be null");
/*    */     }
/* 53 */     return new NullIsFalsePredicate(predicate);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public NullIsFalsePredicate(Predicate<T> predicate)
/*    */   {
/* 64 */     this.iPredicate = predicate;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean evaluate(T object)
/*    */   {
/* 75 */     if (object == null) {
/* 76 */       return false;
/*    */     }
/* 78 */     return this.iPredicate.evaluate(object);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Predicate<? super T>[] getPredicates()
/*    */   {
/* 88 */     return new Predicate[] { this.iPredicate };
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/NullIsFalsePredicate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */