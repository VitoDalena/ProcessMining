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
/*    */ /**
/*    */  * @deprecated
/*    */  */
/*    */ public class XmlFriendlyMapper
/*    */   extends AbstractXmlFriendlyMapper
/*    */ {
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public XmlFriendlyMapper(Mapper wrapped)
/*    */   {
/* 34 */     super(wrapped);
/*    */   }
/*    */   
/*    */   public String serializedClass(Class type) {
/* 38 */     return escapeClassName(super.serializedClass(type));
/*    */   }
/*    */   
/*    */   public Class realClass(String elementName) {
/* 42 */     return super.realClass(unescapeClassName(elementName));
/*    */   }
/*    */   
/*    */   public String serializedMember(Class type, String memberName) {
/* 46 */     return escapeFieldName(super.serializedMember(type, memberName));
/*    */   }
/*    */   
/*    */   public String realMember(Class type, String serialized) {
/* 50 */     return unescapeFieldName(super.realMember(type, serialized));
/*    */   }
/*    */   
/*    */   public String mapNameToXML(String javaName) {
/* 54 */     return escapeFieldName(javaName);
/*    */   }
/*    */   
/*    */   public String mapNameFromXML(String xmlName) {
/* 58 */     return unescapeFieldName(xmlName);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/mapper/XmlFriendlyMapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */