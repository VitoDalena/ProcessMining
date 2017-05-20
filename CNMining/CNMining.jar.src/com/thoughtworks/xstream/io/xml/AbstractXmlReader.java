/*    */ package com.thoughtworks.xstream.io.xml;
/*    */ 
/*    */ import com.thoughtworks.xstream.io.AbstractReader;
/*    */ import com.thoughtworks.xstream.io.naming.NameCoder;
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
/*    */ public abstract class AbstractXmlReader
/*    */   extends AbstractReader
/*    */   implements XmlFriendlyReader
/*    */ {
/*    */   protected AbstractXmlReader()
/*    */   {
/* 30 */     this(new XmlFriendlyNameCoder());
/*    */   }
/*    */   
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   protected AbstractXmlReader(XmlFriendlyReplacer replacer) {
/* 37 */     this(replacer);
/*    */   }
/*    */   
/*    */   protected AbstractXmlReader(NameCoder nameCoder) {
/* 41 */     super(nameCoder);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public String unescapeXmlName(String name)
/*    */   {
/* 52 */     return decodeNode(name);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   protected String escapeXmlName(String name)
/*    */   {
/* 63 */     return encodeNode(name);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/AbstractXmlReader.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */