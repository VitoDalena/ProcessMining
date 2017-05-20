/*     */ package com.thoughtworks.xstream.io.xml;
/*     */ 
/*     */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.Text;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DomReader
/*     */   extends AbstractDocumentReader
/*     */ {
/*     */   private Element currentElement;
/*     */   private StringBuffer textBuffer;
/*     */   private List childElements;
/*     */   
/*     */   public DomReader(Element rootElement)
/*     */   {
/*  33 */     this(rootElement, new XmlFriendlyNameCoder());
/*     */   }
/*     */   
/*     */   public DomReader(Document document) {
/*  37 */     this(document.getDocumentElement());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DomReader(Element rootElement, NameCoder nameCoder)
/*     */   {
/*  44 */     super(rootElement, nameCoder);
/*  45 */     this.textBuffer = new StringBuffer();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DomReader(Document document, NameCoder nameCoder)
/*     */   {
/*  52 */     this(document.getDocumentElement(), nameCoder);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public DomReader(Element rootElement, XmlFriendlyReplacer replacer)
/*     */   {
/*  60 */     this(rootElement, replacer);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public DomReader(Document document, XmlFriendlyReplacer replacer)
/*     */   {
/*  68 */     this(document.getDocumentElement(), replacer);
/*     */   }
/*     */   
/*     */   public String getNodeName() {
/*  72 */     return decodeNode(this.currentElement.getTagName());
/*     */   }
/*     */   
/*     */   public String getValue() {
/*  76 */     NodeList childNodes = this.currentElement.getChildNodes();
/*  77 */     this.textBuffer.setLength(0);
/*  78 */     int length = childNodes.getLength();
/*  79 */     for (int i = 0; i < length; i++) {
/*  80 */       Node childNode = childNodes.item(i);
/*  81 */       if ((childNode instanceof Text)) {
/*  82 */         Text text = (Text)childNode;
/*  83 */         this.textBuffer.append(text.getData());
/*     */       }
/*     */     }
/*  86 */     return this.textBuffer.toString();
/*     */   }
/*     */   
/*     */   public String getAttribute(String name) {
/*  90 */     Attr attribute = this.currentElement.getAttributeNode(name);
/*  91 */     return attribute == null ? null : attribute.getValue();
/*     */   }
/*     */   
/*     */   public String getAttribute(int index) {
/*  95 */     return ((Attr)this.currentElement.getAttributes().item(index)).getValue();
/*     */   }
/*     */   
/*     */   public int getAttributeCount() {
/*  99 */     return this.currentElement.getAttributes().getLength();
/*     */   }
/*     */   
/*     */   public String getAttributeName(int index) {
/* 103 */     return decodeAttribute(((Attr)this.currentElement.getAttributes().item(index)).getName());
/*     */   }
/*     */   
/*     */   protected Object getParent() {
/* 107 */     return this.currentElement.getParentNode();
/*     */   }
/*     */   
/*     */   protected Object getChild(int index) {
/* 111 */     return this.childElements.get(index);
/*     */   }
/*     */   
/*     */   protected int getChildCount() {
/* 115 */     return this.childElements.size();
/*     */   }
/*     */   
/*     */   protected void reassignCurrentElement(Object current) {
/* 119 */     this.currentElement = ((Element)current);
/* 120 */     NodeList childNodes = this.currentElement.getChildNodes();
/* 121 */     this.childElements = new ArrayList();
/* 122 */     for (int i = 0; i < childNodes.getLength(); i++) {
/* 123 */       Node node = childNodes.item(i);
/* 124 */       if ((node instanceof Element)) {
/* 125 */         this.childElements.add(node);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/DomReader.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */