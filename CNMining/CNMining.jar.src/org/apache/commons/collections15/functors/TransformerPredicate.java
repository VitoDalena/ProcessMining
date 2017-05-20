/*    */ package org.apache.commons.collections15.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections15.FunctorException;
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
/*    */ public final class TransformerPredicate<T>
/*    */   implements Predicate<T>, Serializable
/*    */ {
/*    */   static final long serialVersionUID = -2407966402920578741L;
/*    */   private final Transformer<T, Boolean> iTransformer;
/*    */   
/*    */   public static <T> Predicate<T> getInstance(Transformer<T, Boolean> transformer)
/*    */   {
/* 52 */     if (transformer == null) {
/* 53 */       throw new IllegalArgumentException("The transformer to call must not be null");
/*    */     }
/* 55 */     return new TransformerPredicate(transformer);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public TransformerPredicate(Transformer<T, Boolean> transformer)
/*    */   {
/* 66 */     this.iTransformer = transformer;
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
/* 77 */     Boolean result = (Boolean)this.iTransformer.transform(object);
/* 78 */     if (result == null) {
/* 79 */       throw new FunctorException("Transformer must return an instanceof Boolean, it was a " + (result == null ? "null object" : result.getClass().getName()));
/*    */     }
/* 81 */     return result.booleanValue();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Transformer<T, Boolean> getTransformer()
/*    */   {
/* 91 */     return this.iTransformer;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/TransformerPredicate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */