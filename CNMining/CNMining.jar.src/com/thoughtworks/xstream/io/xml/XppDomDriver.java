/*    */ package com.thoughtworks.xstream.io.xml;
/*    */ 
/*    */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*    */ import org.xmlpull.v1.XmlPullParser;
/*    */ import org.xmlpull.v1.XmlPullParserException;
/*    */ import org.xmlpull.v1.XmlPullParserFactory;
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
/*    */ public class XppDomDriver
/*    */   extends AbstractXppDomDriver
/*    */ {
/*    */   private static XmlPullParserFactory factory;
/*    */   
/*    */   public XppDomDriver()
/*    */   {
/* 32 */     super(new XmlFriendlyNameCoder());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public XppDomDriver(NameCoder nameCoder)
/*    */   {
/* 39 */     super(nameCoder);
/*    */   }
/*    */   
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public XppDomDriver(XmlFriendlyReplacer replacer)
/*    */   {
/* 47 */     super(replacer);
/*    */   }
/*    */   
/*    */ 
/*    */   protected synchronized XmlPullParser createParser()
/*    */     throws XmlPullParserException
/*    */   {
/* 54 */     if (factory == null) {
/* 55 */       factory = XmlPullParserFactory.newInstance(null, XppDriver.class);
/*    */     }
/* 57 */     return factory.newPullParser();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/XppDomDriver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */