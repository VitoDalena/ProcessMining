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
/*    */ public class BEAStaxDriver
/*    */   extends StaxDriver
/*    */ {
/*    */   public BEAStaxDriver() {}
/*    */   
/*    */   public BEAStaxDriver(QNameMap qnameMap, XmlFriendlyNameCoder nameCoder)
/*    */   {
/* 32 */     super(qnameMap, nameCoder);
/*    */   }
/*    */   
/*    */   public BEAStaxDriver(QNameMap qnameMap) {
/* 36 */     super(qnameMap);
/*    */   }
/*    */   
/*    */   public BEAStaxDriver(XmlFriendlyNameCoder nameCoder) {
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/BEAStaxDriver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */