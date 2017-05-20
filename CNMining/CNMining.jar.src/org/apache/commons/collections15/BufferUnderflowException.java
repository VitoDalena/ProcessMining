/*    */ package org.apache.commons.collections15;
/*    */ 
/*    */ import java.util.NoSuchElementException;
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
/*    */ public class BufferUnderflowException
/*    */   extends NoSuchElementException
/*    */ {
/*    */   private final Throwable throwable;
/*    */   
/*    */   public BufferUnderflowException()
/*    */   {
/* 46 */     this.throwable = null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public BufferUnderflowException(String message)
/*    */   {
/* 55 */     this(message, null);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public BufferUnderflowException(String message, Throwable exception)
/*    */   {
/* 65 */     super(message);
/* 66 */     this.throwable = exception;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public final Throwable getCause()
/*    */   {
/* 75 */     return this.throwable;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/BufferUnderflowException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */