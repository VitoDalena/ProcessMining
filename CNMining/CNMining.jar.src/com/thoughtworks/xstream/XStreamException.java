/*    */ package com.thoughtworks.xstream;
/*    */ 
/*    */ import com.thoughtworks.xstream.core.BaseException;
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
/*    */ public class XStreamException
/*    */   extends BaseException
/*    */ {
/*    */   private Throwable cause;
/*    */   
/*    */   protected XStreamException()
/*    */   {
/* 33 */     this("", null);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public XStreamException(String message)
/*    */   {
/* 43 */     this(message, null);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public XStreamException(Throwable cause)
/*    */   {
/* 53 */     this("", cause);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public XStreamException(String message, Throwable cause)
/*    */   {
/* 65 */     super(message + (cause == null ? "" : new StringBuffer().append(" : ").append(cause.getMessage()).toString()));
/* 66 */     this.cause = cause;
/*    */   }
/*    */   
/*    */   public Throwable getCause() {
/* 70 */     return this.cause;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/XStreamException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */