/*    */ package com.thoughtworks.xstream.io;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class WriterWrapper
/*    */   implements ExtendedHierarchicalStreamWriter
/*    */ {
/*    */   protected HierarchicalStreamWriter wrapped;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected WriterWrapper(HierarchicalStreamWriter wrapped)
/*    */   {
/* 24 */     this.wrapped = wrapped;
/*    */   }
/*    */   
/*    */   public void startNode(String name) {
/* 28 */     this.wrapped.startNode(name);
/*    */   }
/*    */   
/*    */   public void startNode(String name, Class clazz)
/*    */   {
/* 33 */     ((ExtendedHierarchicalStreamWriter)this.wrapped).startNode(name, clazz);
/*    */   }
/*    */   
/*    */   public void endNode() {
/* 37 */     this.wrapped.endNode();
/*    */   }
/*    */   
/*    */   public void addAttribute(String key, String value) {
/* 41 */     this.wrapped.addAttribute(key, value);
/*    */   }
/*    */   
/*    */   public void setValue(String text) {
/* 45 */     this.wrapped.setValue(text);
/*    */   }
/*    */   
/*    */   public void flush() {
/* 49 */     this.wrapped.flush();
/*    */   }
/*    */   
/*    */   public void close() {
/* 53 */     this.wrapped.close();
/*    */   }
/*    */   
/*    */   public HierarchicalStreamWriter underlyingWriter() {
/* 57 */     return this.wrapped.underlyingWriter();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/WriterWrapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */