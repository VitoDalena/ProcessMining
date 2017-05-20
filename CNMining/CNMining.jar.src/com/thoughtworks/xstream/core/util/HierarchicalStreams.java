/*    */ package com.thoughtworks.xstream.core.util;
/*    */ 
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HierarchicalStreams
/*    */ {
/*    */   public static Class readClassType(HierarchicalStreamReader reader, Mapper mapper)
/*    */   {
/* 26 */     String classAttribute = readClassAttribute(reader, mapper);
/*    */     Class type;
/* 28 */     Class type; if (classAttribute == null) {
/* 29 */       type = mapper.realClass(reader.getNodeName());
/*    */     } else {
/* 31 */       type = mapper.realClass(classAttribute);
/*    */     }
/* 33 */     return type;
/*    */   }
/*    */   
/*    */   public static String readClassAttribute(HierarchicalStreamReader reader, Mapper mapper) {
/* 37 */     String attributeName = mapper.aliasForSystemAttribute("resolves-to");
/* 38 */     String classAttribute = attributeName == null ? null : reader.getAttribute(attributeName);
/* 39 */     if (classAttribute == null) {
/* 40 */       attributeName = mapper.aliasForSystemAttribute("class");
/* 41 */       if (attributeName != null) {
/* 42 */         classAttribute = reader.getAttribute(attributeName);
/*    */       }
/*    */     }
/* 45 */     return classAttribute;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/util/HierarchicalStreams.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */