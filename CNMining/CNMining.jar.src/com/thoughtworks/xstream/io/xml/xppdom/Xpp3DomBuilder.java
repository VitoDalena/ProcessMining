/*    */ package com.thoughtworks.xstream.io.xml.xppdom;
/*    */ 
/*    */ import java.io.Reader;
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
/*    */ /**
/*    */  * @deprecated
/*    */  */
/*    */ public class Xpp3DomBuilder
/*    */ {
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public static Xpp3Dom build(Reader reader)
/*    */     throws Exception
/*    */   {
/* 31 */     XmlPullParser parser = new MXParser();
/* 32 */     parser.setInput(reader);
/*    */     try {
/* 34 */       return (Xpp3Dom)XppDom.build(parser);
/*    */     } finally {
/* 36 */       reader.close();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/xppdom/Xpp3DomBuilder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */