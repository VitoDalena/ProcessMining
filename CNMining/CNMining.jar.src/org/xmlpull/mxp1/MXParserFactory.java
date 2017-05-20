/*    */ package org.xmlpull.mxp1;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import java.util.Hashtable;
/*    */ import org.xmlpull.mxp1_serializer.MXSerializer;
/*    */ import org.xmlpull.v1.XmlPullParser;
/*    */ import org.xmlpull.v1.XmlPullParserException;
/*    */ import org.xmlpull.v1.XmlPullParserFactory;
/*    */ import org.xmlpull.v1.XmlSerializer;
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
/*    */ public class MXParserFactory
/*    */   extends XmlPullParserFactory
/*    */ {
/* 30 */   protected static boolean stringCachedParserAvailable = true;
/*    */   
/*    */   public XmlPullParser newPullParser() throws XmlPullParserException {
/* 33 */     XmlPullParser pp = null;
/* 34 */     if (stringCachedParserAvailable) {
/*    */       try {
/* 36 */         pp = new MXParserCachingStrings();
/*    */       } catch (Exception ex) {
/* 38 */         stringCachedParserAvailable = false;
/*    */       }
/*    */     }
/* 41 */     if (pp == null) {
/* 42 */       pp = new MXParser();
/*    */     }
/* 44 */     for (Enumeration e = this.features.keys(); e.hasMoreElements();) {
/* 45 */       String key = (String)e.nextElement();
/* 46 */       Boolean value = (Boolean)this.features.get(key);
/* 47 */       if ((value != null) && (value.booleanValue())) {
/* 48 */         pp.setFeature(key, true);
/*    */       }
/*    */     }
/* 51 */     return pp;
/*    */   }
/*    */   
/*    */   public XmlSerializer newSerializer() throws XmlPullParserException
/*    */   {
/* 56 */     return new MXSerializer();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/xmlpull/mxp1/MXParserFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */