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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class OrPredicate<T>
/*    */   implements Predicate<T>, PredicateDecorator<T>, Serializable
/*    */ {
/*    */   static final long serialVersionUID = -8791518325735182855L;
/*    */   private final Predicate<? super T> iPredicate1;
/*    */   private final Predicate<? super T> iPredicate2;
/*    */   
/*    */   public static <T> Predicate<T> getInstance(Predicate<? super T> predicate1, Predicate<? super T> predicate2)
/*    */   {
/* 55 */     if ((predicate1 == null) || (predicate2 == null)) {
/* 56 */       throw new IllegalArgumentException("Predicate must not be null");
/*    */     }
/* 58 */     return new OrPredicate(predicate1, predicate2);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public OrPredicate(Predicate<? super T> predicate1, Predicate<? super T> predicate2)
/*    */   {
/* 70 */     this.iPredicate1 = predicate1;
/* 71 */     this.iPredicate2 = predicate2;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean evaluate(T object)
/*    */   {
/* 81 */     return (this.iPredicate1.evaluate(object)) || (this.iPredicate2.evaluate(object));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Predicate<? super T>[] getPredicates()
/*    */   {
/* 91 */     return new Predicate[] { this.iPredicate1, this.iPredicate2 };
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/OrPredicate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */