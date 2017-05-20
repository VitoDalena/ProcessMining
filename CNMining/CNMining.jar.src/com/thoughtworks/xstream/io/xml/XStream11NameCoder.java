/*    */ package com.thoughtworks.xstream.io.xml;
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
/*    */ public class XStream11NameCoder
/*    */   extends XmlFriendlyNameCoder
/*    */ {
/*    */   public String decodeAttribute(String attributeName)
/*    */   {
/* 27 */     return attributeName;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public String decodeNode(String elementName)
/*    */   {
/* 35 */     return elementName;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/XStream11NameCoder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */