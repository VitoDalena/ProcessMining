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
/*    */ public class NameCoderWrapper
/*    */   implements NameCoder
/*    */ {
/*    */   private final NameCoder wrapped;
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
/*    */   public NameCoderWrapper(NameCoder inner)
/*    */   {
/* 30 */     this.wrapped = inner;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String decodeAttribute(String attributeName)
/*    */   {
/* 37 */     return this.wrapped.decodeAttribute(attributeName);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String decodeNode(String nodeName)
/*    */   {
/* 44 */     return this.wrapped.decodeNode(nodeName);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String encodeAttribute(String name)
/*    */   {
/* 51 */     return this.wrapped.encodeAttribute(name);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String encodeNode(String name)
/*    */   {
/* 58 */     return this.wrapped.encodeNode(name);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/naming/NameCoderWrapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */