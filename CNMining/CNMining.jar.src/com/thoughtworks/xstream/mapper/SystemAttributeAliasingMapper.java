/*    */ package com.thoughtworks.xstream.mapper;
/*    */ 
/*    */ import java.util.Map;
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
/*    */ public class SystemAttributeAliasingMapper
/*    */   extends AbstractAttributeAliasingMapper
/*    */ {
/*    */   public SystemAttributeAliasingMapper(Mapper wrapped)
/*    */   {
/* 23 */     super(wrapped);
/*    */   }
/*    */   
/*    */   public String aliasForSystemAttribute(String attribute) {
/* 27 */     String alias = (String)this.nameToAlias.get(attribute);
/* 28 */     if ((alias == null) && (!this.nameToAlias.containsKey(attribute))) {
/* 29 */       alias = super.aliasForSystemAttribute(attribute);
/* 30 */       if (alias == attribute) {
/* 31 */         alias = super.aliasForAttribute(attribute);
/*    */       }
/*    */     }
/* 34 */     return alias;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/mapper/SystemAttributeAliasingMapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */