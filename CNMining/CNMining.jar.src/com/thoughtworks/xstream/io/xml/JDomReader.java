/*     */ package com.thoughtworks.xstream.io.xml;
/*     */ 
/*     */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*     */ import java.util.List;
/*     */ import org.jdom.Attribute;
/*     */ import org.jdom.Document;
/*     */ import org.jdom.Element;
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
/*     */ 
/*     */ 
/*     */ public class JDomReader
/*     */   extends AbstractDocumentReader
/*     */ {
/*     */   private Element currentElement;
/*     */   
/*     */   public JDomReader(Element root)
/*     */   {
/*  28 */     super(root);
/*     */   }
/*     */   
/*     */   public JDomReader(Document document) {
/*  32 */     super(document.getRootElement());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public JDomReader(Element root, NameCoder nameCoder)
/*     */   {
/*  39 */     super(root, nameCoder);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public JDomReader(Document document, NameCoder nameCoder)
/*     */   {
/*  46 */     super(document.getRootElement(), nameCoder);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public JDomReader(Element root, XmlFriendlyReplacer replacer)
/*     */   {
/*  54 */     this(root, replacer);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public JDomReader(Document document, XmlFriendlyReplacer replacer)
/*     */   {
/*  62 */     this(document.getRootElement(), replacer);
/*     */   }
/*     */   
/*     */   protected void reassignCurrentElement(Object current) {
/*  66 */     this.currentElement = ((Element)current);
/*     */   }
/*     */   
/*     */   protected Object getParent()
/*     */   {
/*  71 */     return this.currentElement.getParentElement();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Object getChild(int index)
/*     */   {
/*  82 */     return this.currentElement.getChildren().get(index);
/*     */   }
/*     */   
/*     */   protected int getChildCount() {
/*  86 */     return this.currentElement.getChildren().size();
/*     */   }
/*     */   
/*     */   public String getNodeName() {
/*  90 */     return decodeNode(this.currentElement.getName());
/*     */   }
/*     */   
/*     */   public String getValue() {
/*  94 */     return this.currentElement.getText();
/*     */   }
/*     */   
/*     */   public String getAttribute(String name) {
/*  98 */     return this.currentElement.getAttributeValue(encodeAttribute(name));
/*     */   }
/*     */   
/*     */   public String getAttribute(int index) {
/* 102 */     return ((Attribute)this.currentElement.getAttributes().get(index)).getValue();
/*     */   }
/*     */   
/*     */   public int getAttributeCount() {
/* 106 */     return this.currentElement.getAttributes().size();
/*     */   }
/*     */   
/*     */   public String getAttributeName(int index) {
/* 110 */     return decodeAttribute(((Attribute)this.currentElement.getAttributes().get(index)).getQualifiedName());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/JDomReader.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */