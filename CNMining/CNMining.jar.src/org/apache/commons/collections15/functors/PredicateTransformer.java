/*    */ package org.apache.commons.collections15.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections15.Predicate;
/*    */ import org.apache.commons.collections15.Transformer;
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
/*    */ public class PredicateTransformer<T>
/*    */   implements Transformer<T, Boolean>, Serializable
/*    */ {
/*    */   static final long serialVersionUID = 5278818408044349346L;
/*    */   private final Predicate<T> iPredicate;
/*    */   
/*    */   public static <T> Transformer<T, Boolean> getInstance(Predicate<T> predicate)
/*    */   {
/* 52 */     if (predicate == null) {
/* 53 */       throw new IllegalArgumentException("Predicate must not be null");
/*    */     }
/* 55 */     return new PredicateTransformer(predicate);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public PredicateTransformer(Predicate<T> predicate)
/*    */   {
/* 66 */     this.iPredicate = predicate;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Boolean transform(T input)
/*    */   {
/* 76 */     return this.iPredicate.evaluate(input) ? Boolean.TRUE : Boolean.FALSE;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Predicate<T> getPredicate()
/*    */   {
/* 86 */     return this.iPredicate;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/PredicateTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */