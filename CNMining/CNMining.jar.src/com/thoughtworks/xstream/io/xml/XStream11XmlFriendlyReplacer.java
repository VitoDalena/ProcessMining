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
/*    */ public class XStream11XmlFriendlyReplacer
/*    */   extends XmlFriendlyReplacer
/*    */ {
/*    */   public String decodeAttribute(String attributeName)
/*    */   {
/* 37 */     return attributeName;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public String decodeNode(String elementName)
/*    */   {
/* 45 */     return elementName;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String unescapeName(String name)
/*    */   {
/* 55 */     return name;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/XStream11XmlFriendlyReplacer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */