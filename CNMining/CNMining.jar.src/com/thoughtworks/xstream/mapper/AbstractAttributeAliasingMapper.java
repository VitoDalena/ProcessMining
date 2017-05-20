/*    */ package com.thoughtworks.xstream.mapper;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
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
/*    */ public abstract class AbstractAttributeAliasingMapper
/*    */   extends MapperWrapper
/*    */ {
/* 25 */   protected final Map aliasToName = new HashMap();
/* 26 */   protected transient Map nameToAlias = new HashMap();
/*    */   
/*    */   public AbstractAttributeAliasingMapper(Mapper wrapped) {
/* 29 */     super(wrapped);
/*    */   }
/*    */   
/*    */   public void addAliasFor(String attributeName, String alias) {
/* 33 */     this.aliasToName.put(alias, attributeName);
/* 34 */     this.nameToAlias.put(attributeName, alias);
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 38 */     this.nameToAlias = new HashMap();
/* 39 */     for (Iterator iter = this.aliasToName.keySet().iterator(); iter.hasNext();) {
/* 40 */       Object alias = iter.next();
/* 41 */       this.nameToAlias.put(this.aliasToName.get(alias), alias);
/*    */     }
/* 43 */     return this;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/mapper/AbstractAttributeAliasingMapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */