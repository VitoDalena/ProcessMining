/*    */ package com.thoughtworks.xstream.io;
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
/*    */ public class ExtendedHierarchicalStreamWriterHelper
/*    */ {
/*    */   public static void startNode(HierarchicalStreamWriter writer, String name, Class clazz)
/*    */   {
/* 16 */     if ((writer instanceof ExtendedHierarchicalStreamWriter)) {
/* 17 */       ((ExtendedHierarchicalStreamWriter)writer).startNode(name, clazz);
/*    */     } else {
/* 19 */       writer.startNode(name);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/ExtendedHierarchicalStreamWriterHelper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */