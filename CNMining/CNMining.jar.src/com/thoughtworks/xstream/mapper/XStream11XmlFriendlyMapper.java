/*    */ package com.thoughtworks.xstream.mapper;
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
/*    */ public class XStream11XmlFriendlyMapper
/*    */   extends AbstractXmlFriendlyMapper
/*    */ {
/*    */   public XStream11XmlFriendlyMapper(Mapper wrapped)
/*    */   {
/* 30 */     super(wrapped);
/*    */   }
/*    */   
/*    */   public Class realClass(String elementName) {
/* 34 */     return super.realClass(unescapeClassName(elementName));
/*    */   }
/*    */   
/*    */   public String realMember(Class type, String serialized) {
/* 38 */     return unescapeFieldName(super.realMember(type, serialized));
/*    */   }
/*    */   
/*    */   public String mapNameFromXML(String xmlName) {
/* 42 */     return unescapeFieldName(xmlName);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/mapper/XStream11XmlFriendlyMapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */