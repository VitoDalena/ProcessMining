/*     */ package com.thoughtworks.xstream.io.xml;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.ErrorWriter;
/*     */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*     */ import java.util.List;
/*     */ import org.dom4j.Attribute;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Dom4JReader
/*     */   extends AbstractDocumentReader
/*     */ {
/*     */   private Element currentElement;
/*     */   
/*     */   public Dom4JReader(Element rootElement)
/*     */   {
/*  25 */     this(rootElement, new XmlFriendlyNameCoder());
/*     */   }
/*     */   
/*     */   public Dom4JReader(Document document) {
/*  29 */     this(document.getRootElement());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Dom4JReader(Element rootElement, NameCoder nameCoder)
/*     */   {
/*  36 */     super(rootElement, nameCoder);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Dom4JReader(Document document, NameCoder nameCoder)
/*     */   {
/*  43 */     this(document.getRootElement(), nameCoder);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public Dom4JReader(Element rootElement, XmlFriendlyReplacer replacer)
/*     */   {
/*  51 */     this(rootElement, replacer);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public Dom4JReader(Document document, XmlFriendlyReplacer replacer)
/*     */   {
/*  59 */     this(document.getRootElement(), replacer);
/*     */   }
/*     */   
/*     */   public String getNodeName() {
/*  63 */     return decodeNode(this.currentElement.getName());
/*     */   }
/*     */   
/*     */   public String getValue() {
/*  67 */     return this.currentElement.getText();
/*     */   }
/*     */   
/*     */   public String getAttribute(String name) {
/*  71 */     return this.currentElement.attributeValue(name);
/*     */   }
/*     */   
/*     */   public String getAttribute(int index) {
/*  75 */     return this.currentElement.attribute(index).getValue();
/*     */   }
/*     */   
/*     */   public int getAttributeCount() {
/*  79 */     return this.currentElement.attributeCount();
/*     */   }
/*     */   
/*     */   public String getAttributeName(int index) {
/*  83 */     return decodeAttribute(this.currentElement.attribute(index).getQualifiedName());
/*     */   }
/*     */   
/*     */   protected Object getParent() {
/*  87 */     return this.currentElement.getParent();
/*     */   }
/*     */   
/*     */   protected Object getChild(int index) {
/*  91 */     return this.currentElement.elements().get(index);
/*     */   }
/*     */   
/*     */   protected int getChildCount() {
/*  95 */     return this.currentElement.elements().size();
/*     */   }
/*     */   
/*     */   protected void reassignCurrentElement(Object current) {
/*  99 */     this.currentElement = ((Element)current);
/*     */   }
/*     */   
/*     */   public void appendErrors(ErrorWriter errorWriter) {
/* 103 */     errorWriter.add("xpath", this.currentElement.getPath());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/Dom4JReader.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */