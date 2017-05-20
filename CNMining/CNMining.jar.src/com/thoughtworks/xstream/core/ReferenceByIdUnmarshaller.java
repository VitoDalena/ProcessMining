/*    */ package com.thoughtworks.xstream.core;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.ConverterLookup;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.mapper.Mapper;
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
/*    */ public class ReferenceByIdUnmarshaller
/*    */   extends AbstractReferenceUnmarshaller
/*    */ {
/*    */   public ReferenceByIdUnmarshaller(Object root, HierarchicalStreamReader reader, ConverterLookup converterLookup, Mapper mapper)
/*    */   {
/* 22 */     super(root, reader, converterLookup, mapper);
/*    */   }
/*    */   
/*    */   protected Object getReferenceKey(String reference) {
/* 26 */     return reference;
/*    */   }
/*    */   
/*    */   protected Object getCurrentReferenceKey() {
/* 30 */     String attributeName = getMapper().aliasForSystemAttribute("id");
/* 31 */     return attributeName == null ? null : this.reader.getAttribute(attributeName);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/ReferenceByIdUnmarshaller.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */