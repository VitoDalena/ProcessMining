/*    */ package com.thoughtworks.xstream.io.xml;
/*    */ 
/*    */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*    */ import com.thoughtworks.xstream.io.xml.xppdom.XppDom;
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
/*    */ public class XppDomReader
/*    */   extends AbstractDocumentReader
/*    */ {
/*    */   private XppDom currentElement;
/*    */   
/*    */   public XppDomReader(XppDom xppDom)
/*    */   {
/* 25 */     super(xppDom);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public XppDomReader(XppDom xppDom, NameCoder nameCoder)
/*    */   {
/* 32 */     super(xppDom, nameCoder);
/*    */   }
/*    */   
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public XppDomReader(XppDom xppDom, XmlFriendlyReplacer replacer)
/*    */   {
/* 40 */     this(xppDom, replacer);
/*    */   }
/*    */   
/*    */   public String getNodeName() {
/* 44 */     return decodeNode(this.currentElement.getName());
/*    */   }
/*    */   
/*    */   public String getValue() {
/* 48 */     String text = null;
/*    */     try
/*    */     {
/* 51 */       text = this.currentElement.getValue();
/*    */     }
/*    */     catch (Exception e) {}
/*    */     
/*    */ 
/* 56 */     return text == null ? "" : text;
/*    */   }
/*    */   
/*    */   public String getAttribute(String attributeName) {
/* 60 */     return this.currentElement.getAttribute(encodeAttribute(attributeName));
/*    */   }
/*    */   
/*    */   public String getAttribute(int index) {
/* 64 */     return this.currentElement.getAttribute(this.currentElement.getAttributeNames()[index]);
/*    */   }
/*    */   
/*    */   public int getAttributeCount() {
/* 68 */     return this.currentElement.getAttributeNames().length;
/*    */   }
/*    */   
/*    */   public String getAttributeName(int index) {
/* 72 */     return decodeAttribute(this.currentElement.getAttributeNames()[index]);
/*    */   }
/*    */   
/*    */   protected Object getParent() {
/* 76 */     return this.currentElement.getParent();
/*    */   }
/*    */   
/*    */   protected Object getChild(int index) {
/* 80 */     return this.currentElement.getChild(index);
/*    */   }
/*    */   
/*    */   protected int getChildCount() {
/* 84 */     return this.currentElement.getChildCount();
/*    */   }
/*    */   
/*    */   protected void reassignCurrentElement(Object current) {
/* 88 */     this.currentElement = ((XppDom)current);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/XppDomReader.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */