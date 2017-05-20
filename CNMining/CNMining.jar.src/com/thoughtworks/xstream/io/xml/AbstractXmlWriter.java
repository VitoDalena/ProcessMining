/*    */ package com.thoughtworks.xstream.io.xml;
/*    */ 
/*    */ import com.thoughtworks.xstream.io.AbstractWriter;
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
/*    */ /**
/*    */  * @deprecated
/*    */  */
/*    */ public abstract class AbstractXmlWriter
/*    */   extends AbstractWriter
/*    */   implements XmlFriendlyWriter
/*    */ {
/*    */   protected AbstractXmlWriter()
/*    */   {
/* 29 */     this(new XmlFriendlyNameCoder());
/*    */   }
/*    */   
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   protected AbstractXmlWriter(XmlFriendlyReplacer replacer) {
/* 36 */     this(replacer);
/*    */   }
/*    */   
/*    */   protected AbstractXmlWriter(NameCoder nameCoder) {
/* 40 */     super(nameCoder);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public String escapeXmlName(String name)
/*    */   {
/* 51 */     return super.encodeNode(name);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/AbstractXmlWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */