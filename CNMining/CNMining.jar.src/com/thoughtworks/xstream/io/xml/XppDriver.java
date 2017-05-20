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
/*    */ 
/*    */ 
/*    */ public class XppDriver
/*    */   extends AbstractXppDriver
/*    */ {
/*    */   private static XmlPullParserFactory factory;
/*    */   
/*    */   public XppDriver()
/*    */   {
/* 34 */     super(new XmlFriendlyNameCoder());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public XppDriver(NameCoder nameCoder)
/*    */   {
/* 41 */     super(nameCoder);
/*    */   }
/*    */   
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public XppDriver(XmlFriendlyReplacer replacer)
/*    */   {
/* 49 */     this(replacer);
/*    */   }
/*    */   
/*    */ 
/*    */   protected synchronized XmlPullParser createParser()
/*    */     throws XmlPullParserException
/*    */   {
/* 56 */     if (factory == null) {
/* 57 */       factory = XmlPullParserFactory.newInstance(null, XppDriver.class);
/*    */     }
/* 59 */     return factory.newPullParser();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/XppDriver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */