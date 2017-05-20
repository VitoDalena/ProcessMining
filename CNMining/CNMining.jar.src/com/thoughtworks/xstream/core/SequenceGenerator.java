/*    */ package com.thoughtworks.xstream.core;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SequenceGenerator
/*    */   implements ReferenceByIdMarshaller.IDGenerator
/*    */ {
/*    */   private int counter;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public SequenceGenerator(int startsAt)
/*    */   {
/* 19 */     this.counter = startsAt;
/*    */   }
/*    */   
/*    */   public String next(Object item) {
/* 23 */     return String.valueOf(this.counter++);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/SequenceGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */