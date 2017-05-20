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
/*    */ 
/*    */ 
/*    */ public class KXml2Driver
/*    */   extends AbstractXppDriver
/*    */ {
/*    */   public KXml2Driver()
/*    */   {
/* 35 */     super(new XmlFriendlyNameCoder());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public KXml2Driver(NameCoder nameCoder)
/*    */   {
/* 45 */     super(nameCoder);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected XmlPullParser createParser()
/*    */   {
/* 52 */     return new KXmlParser();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/KXml2Driver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */