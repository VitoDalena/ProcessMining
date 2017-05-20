/*     */ package com.thoughtworks.xstream.io.xml;
/*     */ 
/*     */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*     */ import nu.xom.Attribute;
/*     */ import nu.xom.Document;
/*     */ import nu.xom.Element;
/*     */ import nu.xom.Elements;
/*     */ import nu.xom.Node;
/*     */ import nu.xom.Text;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XomReader
/*     */   extends AbstractDocumentReader
/*     */ {
/*     */   private Element currentElement;
/*     */   
/*     */   public XomReader(Element rootElement)
/*     */   {
/*  26 */     super(rootElement);
/*     */   }
/*     */   
/*     */   public XomReader(Document document) {
/*  30 */     super(document.getRootElement());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public XomReader(Element rootElement, NameCoder nameCoder)
/*     */   {
/*  37 */     super(rootElement, nameCoder);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public XomReader(Document document, NameCoder nameCoder)
/*     */   {
/*  44 */     super(document.getRootElement(), nameCoder);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public XomReader(Element rootElement, XmlFriendlyReplacer replacer)
/*     */   {
/*  52 */     this(rootElement, replacer);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public XomReader(Document document, XmlFriendlyReplacer replacer)
/*     */   {
/*  60 */     this(document.getRootElement(), replacer);
/*     */   }
/*     */   
/*     */   public String getNodeName() {
/*  64 */     return decodeNode(this.currentElement.getLocalName());
/*     */   }
/*     */   
/*     */   public String getValue()
/*     */   {
/*  69 */     StringBuffer result = new StringBuffer();
/*  70 */     int childCount = this.currentElement.getChildCount();
/*  71 */     for (int i = 0; i < childCount; i++) {
/*  72 */       Node child = this.currentElement.getChild(i);
/*  73 */       if ((child instanceof Text)) {
/*  74 */         Text text = (Text)child;
/*  75 */         result.append(text.getValue());
/*     */       }
/*     */     }
/*  78 */     return result.toString();
/*     */   }
/*     */   
/*     */   public String getAttribute(String name) {
/*  82 */     return this.currentElement.getAttributeValue(encodeAttribute(name));
/*     */   }
/*     */   
/*     */   public String getAttribute(int index) {
/*  86 */     return this.currentElement.getAttribute(index).getValue();
/*     */   }
/*     */   
/*     */   public int getAttributeCount() {
/*  90 */     return this.currentElement.getAttributeCount();
/*     */   }
/*     */   
/*     */   public String getAttributeName(int index) {
/*  94 */     return decodeAttribute(this.currentElement.getAttribute(index).getQualifiedName());
/*     */   }
/*     */   
/*     */   protected int getChildCount() {
/*  98 */     return this.currentElement.getChildElements().size();
/*     */   }
/*     */   
/*     */   protected Object getParent() {
/* 102 */     return this.currentElement.getParent();
/*     */   }
/*     */   
/*     */   protected Object getChild(int index) {
/* 106 */     return this.currentElement.getChildElements().get(index);
/*     */   }
/*     */   
/*     */   protected void reassignCurrentElement(Object current) {
/* 110 */     this.currentElement = ((Element)current);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/XomReader.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */