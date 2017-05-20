/*    */ package com.thoughtworks.xstream.io;
/*    */ 
/*    */ import com.thoughtworks.xstream.core.util.Cloneables;
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
/*    */ 
/*    */ 
/*    */ public abstract class AbstractWriter
/*    */   implements ExtendedHierarchicalStreamWriter
/*    */ {
/*    */   private NameCoder nameCoder;
/*    */   
/*    */   protected AbstractWriter()
/*    */   {
/* 36 */     this(new NoNameCoder());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected AbstractWriter(NameCoder nameCoder)
/*    */   {
/* 46 */     this.nameCoder = ((NameCoder)Cloneables.cloneIfPossible(nameCoder));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void startNode(String name, Class clazz)
/*    */   {
/* 53 */     startNode(name);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public HierarchicalStreamWriter underlyingWriter()
/*    */   {
/* 60 */     return this;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String encodeNode(String name)
/*    */   {
/* 71 */     return this.nameCoder.encodeNode(name);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String encodeAttribute(String name)
/*    */   {
/* 82 */     return this.nameCoder.encodeAttribute(name);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/AbstractWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */