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
/*    */ public final class IdentityPredicate<T>
/*    */   implements Predicate<T>, Serializable
/*    */ {
/*    */   static final long serialVersionUID = -89901658494523293L;
/*    */   private final T iValue;
/*    */   
/*    */   public static <T> Predicate<T> getInstance(T object)
/*    */   {
/* 52 */     if (object == null) {
/* 53 */       return NullPredicate.INSTANCE;
/*    */     }
/* 55 */     return new IdentityPredicate(object);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public IdentityPredicate(T object)
/*    */   {
/* 66 */     this.iValue = object;
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
/* 77 */     return this.iValue == object;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public T getValue()
/*    */   {
/* 87 */     return (T)this.iValue;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/IdentityPredicate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */