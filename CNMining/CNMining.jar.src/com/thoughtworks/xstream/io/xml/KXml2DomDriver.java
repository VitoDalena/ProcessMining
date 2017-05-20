/*    */ package com.thoughtworks.xstream.io.xml;
/*    */ 
/*    */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*    */ import org.kxml2.io.KXmlParser;
/*    */ import org.xmlpull.v1.XmlPullParser;
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
/*    */ public class KXml2DomDriver
/*    */   extends AbstractXppDomDriver
/*    */ {
/*    */   public KXml2DomDriver()
/*    */   {
/* 33 */     super(new XmlFriendlyNameCoder());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public KXml2DomDriver(NameCoder nameCoder)
/*    */   {
/* 43 */     super(nameCoder);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected XmlPullParser createParser()
/*    */   {
/* 50 */     return new KXmlParser();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/KXml2DomDriver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */