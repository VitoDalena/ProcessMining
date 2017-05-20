/*    */ package com.thoughtworks.xstream.io;
/*    */ 
/*    */ import com.thoughtworks.xstream.XStreamException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StreamException
/*    */   extends XStreamException
/*    */ {
/*    */   public StreamException(Throwable e)
/*    */   {
/* 18 */     super(e);
/*    */   }
/*    */   
/*    */   public StreamException(String message) {
/* 22 */     super(message);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public StreamException(String message, Throwable cause)
/*    */   {
/* 29 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/StreamException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */