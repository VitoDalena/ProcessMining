/*    */ package com.thoughtworks.xstream.io;
/*    */ 
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
/*    */ public class AttributeNameIterator
/*    */   implements Iterator
/*    */ {
/*    */   private int current;
/*    */   private final int count;
/*    */   private final HierarchicalStreamReader reader;
/*    */   
/*    */   public AttributeNameIterator(HierarchicalStreamReader reader)
/*    */   {
/* 28 */     this.reader = reader;
/* 29 */     this.count = reader.getAttributeCount();
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 33 */     return this.current < this.count;
/*    */   }
/*    */   
/*    */   public Object next() {
/* 37 */     return this.reader.getAttributeName(this.current++);
/*    */   }
/*    */   
/*    */   public void remove() {
/* 41 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/AttributeNameIterator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */