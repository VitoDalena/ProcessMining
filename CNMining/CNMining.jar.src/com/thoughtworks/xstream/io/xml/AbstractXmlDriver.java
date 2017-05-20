/*    */ package com.thoughtworks.xstream.io.xml;
/*    */ 
/*    */ import com.thoughtworks.xstream.io.AbstractDriver;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ /**
/*    */  * @deprecated
/*    */  */
/*    */ public abstract class AbstractXmlDriver
/*    */   extends AbstractDriver
/*    */ {
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public AbstractXmlDriver()
/*    */   {
/* 35 */     this(new XmlFriendlyNameCoder());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public AbstractXmlDriver(NameCoder nameCoder)
/*    */   {
/* 43 */     super(nameCoder);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public AbstractXmlDriver(XmlFriendlyReplacer replacer)
/*    */   {
/* 53 */     this(replacer);
/*    */   }
/*    */   
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   protected XmlFriendlyReplacer xmlFriendlyReplacer() {
/* 60 */     NameCoder nameCoder = getNameCoder();
/* 61 */     return (nameCoder instanceof XmlFriendlyReplacer) ? (XmlFriendlyReplacer)nameCoder : null;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/AbstractXmlDriver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */