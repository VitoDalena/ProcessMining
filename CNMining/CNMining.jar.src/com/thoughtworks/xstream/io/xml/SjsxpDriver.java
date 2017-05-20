/*    */ package com.thoughtworks.xstream.io.xml;
/*    */ 
/*    */ import com.thoughtworks.xstream.io.StreamException;
/*    */ import javax.xml.stream.XMLInputFactory;
/*    */ import javax.xml.stream.XMLOutputFactory;
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
/*    */ public class SjsxpDriver
/*    */   extends StaxDriver
/*    */ {
/*    */   public SjsxpDriver() {}
/*    */   
/*    */   public SjsxpDriver(QNameMap qnameMap, XmlFriendlyNameCoder nameCoder)
/*    */   {
/* 31 */     super(qnameMap, nameCoder);
/*    */   }
/*    */   
/*    */   public SjsxpDriver(QNameMap qnameMap) {
/* 35 */     super(qnameMap);
/*    */   }
/*    */   
/*    */   public SjsxpDriver(XmlFriendlyNameCoder nameCoder) {
/* 39 */     super(nameCoder);
/*    */   }
/*    */   
/*    */   protected XMLInputFactory createInputFactory() {
/* 43 */     Exception exception = null;
/*    */     try {
/* 45 */       return (XMLInputFactory)Class.forName("com.sun.xml.internal.stream.XMLInputFactoryImpl").newInstance();
/*    */     } catch (InstantiationException e) {
/* 47 */       exception = e;
/*    */     } catch (IllegalAccessException e) {
/* 49 */       exception = e;
/*    */     } catch (ClassNotFoundException e) {
/* 51 */       exception = e;
/*    */     }
/* 53 */     throw new StreamException("Cannot create SJSXP (Sun JDK 6 StAX) XMLInputFaqctory instance.", exception);
/*    */   }
/*    */   
/*    */   protected XMLOutputFactory createOutputFactory() {
/* 57 */     Exception exception = null;
/*    */     try {
/* 59 */       return (XMLOutputFactory)Class.forName("com.sun.xml.internal.stream.XMLOutputFactoryImpl").newInstance();
/*    */     } catch (InstantiationException e) {
/* 61 */       exception = e;
/*    */     } catch (IllegalAccessException e) {
/* 63 */       exception = e;
/*    */     } catch (ClassNotFoundException e) {
/* 65 */       exception = e;
/*    */     }
/* 67 */     throw new StreamException("Cannot create SJSXP (Sun JDK 6 StAX) XMLOutputFaqctory instance.", exception);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/SjsxpDriver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */