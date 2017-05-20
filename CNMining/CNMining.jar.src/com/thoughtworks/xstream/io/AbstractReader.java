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
/*    */ public abstract class AbstractReader
/*    */   implements HierarchicalStreamReader
/*    */ {
/*    */   private NameCoder nameCoder;
/*    */   
/*    */   protected AbstractReader()
/*    */   {
/* 36 */     this(new NoNameCoder());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected AbstractReader(NameCoder nameCoder)
/*    */   {
/* 46 */     this.nameCoder = ((NameCoder)Cloneables.cloneIfPossible(nameCoder));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public HierarchicalStreamReader underlyingReader()
/*    */   {
/* 53 */     return this;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String decodeNode(String name)
/*    */   {
/* 64 */     return this.nameCoder.decodeNode(name);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String decodeAttribute(String name)
/*    */   {
/* 75 */     return this.nameCoder.decodeAttribute(name);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected String encodeNode(String name)
/*    */   {
/* 86 */     return this.nameCoder.encodeNode(name);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected String encodeAttribute(String name)
/*    */   {
/* 97 */     return this.nameCoder.encodeAttribute(name);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/AbstractReader.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */