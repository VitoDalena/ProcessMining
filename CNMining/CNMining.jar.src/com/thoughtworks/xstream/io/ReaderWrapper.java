/*    */ package com.thoughtworks.xstream.io;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.ErrorWriter;
/*    */ import java.util.Iterator;
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
/*    */ public abstract class ReaderWrapper
/*    */   implements HierarchicalStreamReader
/*    */ {
/*    */   protected HierarchicalStreamReader wrapped;
/*    */   
/*    */   protected ReaderWrapper(HierarchicalStreamReader reader)
/*    */   {
/* 28 */     this.wrapped = reader;
/*    */   }
/*    */   
/*    */   public boolean hasMoreChildren() {
/* 32 */     return this.wrapped.hasMoreChildren();
/*    */   }
/*    */   
/*    */   public void moveDown() {
/* 36 */     this.wrapped.moveDown();
/*    */   }
/*    */   
/*    */   public void moveUp() {
/* 40 */     this.wrapped.moveUp();
/*    */   }
/*    */   
/*    */   public String getNodeName() {
/* 44 */     return this.wrapped.getNodeName();
/*    */   }
/*    */   
/*    */   public String getValue() {
/* 48 */     return this.wrapped.getValue();
/*    */   }
/*    */   
/*    */   public String getAttribute(String name) {
/* 52 */     return this.wrapped.getAttribute(name);
/*    */   }
/*    */   
/*    */   public String getAttribute(int index) {
/* 56 */     return this.wrapped.getAttribute(index);
/*    */   }
/*    */   
/*    */   public int getAttributeCount() {
/* 60 */     return this.wrapped.getAttributeCount();
/*    */   }
/*    */   
/*    */   public String getAttributeName(int index) {
/* 64 */     return this.wrapped.getAttributeName(index);
/*    */   }
/*    */   
/*    */   public Iterator getAttributeNames() {
/* 68 */     return this.wrapped.getAttributeNames();
/*    */   }
/*    */   
/*    */   public void appendErrors(ErrorWriter errorWriter) {
/* 72 */     this.wrapped.appendErrors(errorWriter);
/*    */   }
/*    */   
/*    */   public void close() {
/* 76 */     this.wrapped.close();
/*    */   }
/*    */   
/*    */   public HierarchicalStreamReader underlyingReader() {
/* 80 */     return this.wrapped.underlyingReader();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/ReaderWrapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */