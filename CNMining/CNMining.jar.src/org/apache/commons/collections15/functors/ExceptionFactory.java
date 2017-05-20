/*    */ package org.apache.commons.collections15.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections15.Factory;
/*    */ import org.apache.commons.collections15.FunctorException;
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
/*    */ public final class ExceptionFactory<T>
/*    */   implements Factory<T>, Serializable
/*    */ {
/*    */   static final long serialVersionUID = 7179106032121985545L;
/* 42 */   public static final Factory INSTANCE = new ExceptionFactory();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static <T> Factory<T> getInstance()
/*    */   {
/* 51 */     return INSTANCE;
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
/*    */   public T create()
/*    */   {
/* 68 */     throw new FunctorException("ExceptionFactory invoked");
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/ExceptionFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */