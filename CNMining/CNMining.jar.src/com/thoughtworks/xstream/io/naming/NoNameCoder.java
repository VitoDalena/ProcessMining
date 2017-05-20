/*    */ package com.thoughtworks.xstream.io.naming;
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
/*    */ public class NoNameCoder
/*    */   implements NameCoder
/*    */ {
/*    */   public String decodeAttribute(String attributeName)
/*    */   {
/* 30 */     return attributeName;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String decodeNode(String nodeName)
/*    */   {
/* 37 */     return nodeName;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String encodeAttribute(String name)
/*    */   {
/* 44 */     return name;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String encodeNode(String name)
/*    */   {
/* 51 */     return name;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/naming/NoNameCoder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */