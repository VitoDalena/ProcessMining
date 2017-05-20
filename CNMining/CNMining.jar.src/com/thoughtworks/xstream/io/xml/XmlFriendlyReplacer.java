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
/*    */ /**
/*    */  * @deprecated
/*    */  */
/*    */ public class XmlFriendlyReplacer
/*    */   extends XmlFriendlyNameCoder
/*    */ {
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public XmlFriendlyReplacer()
/*    */   {
/* 37 */     this("_-", "__");
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public XmlFriendlyReplacer(String dollarReplacement, String underscoreReplacement)
/*    */   {
/* 48 */     super(dollarReplacement, underscoreReplacement);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public String escapeName(String name)
/*    */   {
/* 59 */     return super.encodeNode(name);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public String unescapeName(String name)
/*    */   {
/* 70 */     return super.decodeNode(name);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/XmlFriendlyReplacer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */