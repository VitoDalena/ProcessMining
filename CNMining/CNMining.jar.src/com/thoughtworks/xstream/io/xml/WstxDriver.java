/*    */ package com.thoughtworks.xstream.io.xml;
/*    */ 
/*    */ import com.bea.xml.stream.MXParserFactory;
/*    */ import com.bea.xml.stream.XMLOutputFactoryBase;
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
/*    */ public class WstxDriver
/*    */   extends StaxDriver
/*    */ {
/*    */   public WstxDriver() {}
/*    */   
/*    */   public WstxDriver(QNameMap qnameMap, XmlFriendlyNameCoder nameCoder)
/*    */   {
/* 32 */     super(qnameMap, nameCoder);
/*    */   }
/*    */   
/*    */   public WstxDriver(QNameMap qnameMap) {
/* 36 */     super(qnameMap);
/*    */   }
/*    */   
/*    */   public WstxDriver(XmlFriendlyNameCoder nameCoder) {
/* 40 */     super(nameCoder);
/*    */   }
/*    */   
/*    */   protected XMLInputFactory createInputFactory() {
/* 44 */     return new MXParserFactory();
/*    */   }
/*    */   
/*    */   protected XMLOutputFactory createOutputFactory() {
/* 48 */     return new XMLOutputFactoryBase();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/WstxDriver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */