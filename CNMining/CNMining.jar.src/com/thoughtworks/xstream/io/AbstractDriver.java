/*    */ package com.thoughtworks.xstream.io;
/*    */ 
/*    */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*    */ import com.thoughtworks.xstream.io.naming.NoNameCoder;
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
/*    */ public abstract class AbstractDriver
/*    */   implements HierarchicalStreamDriver
/*    */ {
/*    */   private NameCoder replacer;
/*    */   
/*    */   public AbstractDriver()
/*    */   {
/* 33 */     this(new NoNameCoder());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public AbstractDriver(NameCoder nameCoder)
/*    */   {
/* 42 */     this.replacer = nameCoder;
/*    */   }
/*    */   
/*    */   protected NameCoder getNameCoder() {
/* 46 */     return this.replacer;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/AbstractDriver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */