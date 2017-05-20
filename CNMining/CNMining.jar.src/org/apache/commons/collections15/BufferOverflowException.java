/*    */ package org.apache.commons.collections15;
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
/*    */ public class BufferOverflowException
/*    */   extends RuntimeException
/*    */ {
/*    */   private final Throwable throwable;
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
/*    */   public BufferOverflowException()
/*    */   {
/* 43 */     this.throwable = null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public BufferOverflowException(String message)
/*    */   {
/* 52 */     this(message, null);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public BufferOverflowException(String message, Throwable exception)
/*    */   {
/* 62 */     super(message);
/* 63 */     this.throwable = exception;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public final Throwable getCause()
/*    */   {
/* 72 */     return this.throwable;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/BufferOverflowException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */