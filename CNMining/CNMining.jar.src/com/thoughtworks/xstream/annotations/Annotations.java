/*    */ package com.thoughtworks.xstream.annotations;
/*    */ 
/*    */ import com.thoughtworks.xstream.XStream;
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
/*    */ @Deprecated
/*    */ public class Annotations
/*    */ {
/*    */   @Deprecated
/*    */   public static synchronized void configureAliases(XStream xstream, Class<?>... topLevelClasses)
/*    */   {
/* 48 */     xstream.processAnnotations(topLevelClasses);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/annotations/Annotations.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */