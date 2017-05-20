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
/*    */ public final class NotNullPredicate<T>
/*    */   implements Predicate<T>, Serializable
/*    */ {
/*    */   static final long serialVersionUID = 7533784454832764388L;
/* 40 */   public static final Predicate INSTANCE = new NotNullPredicate();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static <T> Predicate<T> getInstance()
/*    */   {
/* 49 */     return INSTANCE;
/*    */   }
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
/*    */   public boolean evaluate(T object)
/*    */   {
/* 66 */     return object != null;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/NotNullPredicate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */