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
/*    */ 
/*    */ 
/*    */ public class Xpp3Driver
/*    */   extends AbstractXppDriver
/*    */ {
/*    */   public Xpp3Driver()
/*    */   {
/* 35 */     super(new XmlFriendlyNameCoder());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Xpp3Driver(NameCoder nameCoder)
/*    */   {
/* 45 */     super(nameCoder);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected XmlPullParser createParser()
/*    */   {
/* 52 */     return new MXParser();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/Xpp3Driver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */