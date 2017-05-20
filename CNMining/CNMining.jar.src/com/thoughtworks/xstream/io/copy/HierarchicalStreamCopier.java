/*    */ package com.thoughtworks.xstream.io.copy;
/*    */ 
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
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
/*    */ public class HierarchicalStreamCopier
/*    */ {
/*    */   public void copy(HierarchicalStreamReader source, HierarchicalStreamWriter destination)
/*    */   {
/* 36 */     destination.startNode(source.getNodeName());
/* 37 */     int attributeCount = source.getAttributeCount();
/* 38 */     for (int i = 0; i < attributeCount; i++) {
/* 39 */       destination.addAttribute(source.getAttributeName(i), source.getAttribute(i));
/*    */     }
/* 41 */     String value = source.getValue();
/* 42 */     if ((value != null) && (value.length() > 0)) {
/* 43 */       destination.setValue(value);
/*    */     }
/* 45 */     while (source.hasMoreChildren()) {
/* 46 */       source.moveDown();
/* 47 */       copy(source, destination);
/* 48 */       source.moveUp();
/*    */     }
/* 50 */     destination.endNode();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/copy/HierarchicalStreamCopier.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */