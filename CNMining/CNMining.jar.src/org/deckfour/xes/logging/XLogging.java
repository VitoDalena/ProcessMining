/*    */ package org.deckfour.xes.logging;
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
/*    */ public class XLogging
/*    */ {
/*    */   public static enum Importance
/*    */   {
/* 57 */     DEBUG,  INFO,  WARNING,  ERROR;
/*    */     
/*    */ 
/*    */     private Importance() {}
/*    */   }
/*    */   
/* 63 */   private static XLoggingListener listener = new XStdoutLoggingListener();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void setListener(XLoggingListener listener)
/*    */   {
/* 71 */     listener = listener;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void log(String message)
/*    */   {
/* 80 */     log(message, Importance.DEBUG);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void log(String message, Importance importance)
/*    */   {
/* 90 */     if (listener != null) {
/* 91 */       listener.log(message, importance);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/logging/XLogging.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */