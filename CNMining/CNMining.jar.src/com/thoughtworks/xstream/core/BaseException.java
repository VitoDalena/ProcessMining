/*    */ package com.thoughtworks.xstream.core;
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
/*    */ /**
/*    */  * @deprecated
/*    */  */
/*    */ public abstract class BaseException
/*    */   extends RuntimeException
/*    */ {
/*    */   protected BaseException(String message)
/*    */   {
/* 23 */     super(message);
/*    */   }
/*    */   
/*    */   public abstract Throwable getCause();
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/BaseException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */