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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AttributeAliasingMapper
/*    */   extends AbstractAttributeAliasingMapper
/*    */ {
/*    */   public AttributeAliasingMapper(Mapper wrapped)
/*    */   {
/* 26 */     super(wrapped);
/*    */   }
/*    */   
/*    */   public String aliasForAttribute(String attribute) {
/* 30 */     String alias = (String)this.nameToAlias.get(attribute);
/* 31 */     return alias == null ? super.aliasForAttribute(attribute) : alias;
/*    */   }
/*    */   
/*    */   public String attributeForAlias(String alias) {
/* 35 */     String name = (String)this.aliasToName.get(alias);
/* 36 */     return name == null ? super.attributeForAlias(alias) : name;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/mapper/AttributeAliasingMapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */