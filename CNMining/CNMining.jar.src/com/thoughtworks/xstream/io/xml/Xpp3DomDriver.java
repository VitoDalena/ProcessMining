/*    */ package com.thoughtworks.xstream.io.xml;
/*    */ 
/*    */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*    */ import org.xmlpull.mxp1.MXParser;
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
/*    */ public class Xpp3DomDriver
/*    */   extends AbstractXppDomDriver
/*    */ {
/*    */   public Xpp3DomDriver()
/*    */   {
/* 33 */     super(new XmlFriendlyNameCoder());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Xpp3DomDriver(NameCoder nameCoder)
/*    */   {
/* 43 */     super(nameCoder);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected XmlPullParser createParser()
/*    */   {
/* 50 */     return new MXParser();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/Xpp3DomDriver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */