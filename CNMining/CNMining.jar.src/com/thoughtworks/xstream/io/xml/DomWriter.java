/*     */ package com.thoughtworks.xstream.io.xml;
/*     */ 
/*     */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
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
/*     */ 
/*     */ 
/*     */ public class DomWriter
/*     */   extends AbstractDocumentWriter
/*     */ {
/*     */   private final Document document;
/*     */   private boolean hasRootElement;
/*     */   
/*     */   public DomWriter(Document document)
/*     */   {
/*  29 */     this(document, new XmlFriendlyNameCoder());
/*     */   }
/*     */   
/*     */   public DomWriter(Element rootElement) {
/*  33 */     this(rootElement, new XmlFriendlyNameCoder());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DomWriter(Document document, NameCoder nameCoder)
/*     */   {
/*  40 */     this(document.getDocumentElement(), document, nameCoder);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DomWriter(Element element, Document document, NameCoder nameCoder)
/*     */   {
/*  47 */     super(element, nameCoder);
/*  48 */     this.document = document;
/*  49 */     this.hasRootElement = (document.getDocumentElement() != null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DomWriter(Element rootElement, NameCoder nameCoder)
/*     */   {
/*  56 */     this(rootElement.getOwnerDocument(), nameCoder);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public DomWriter(Document document, XmlFriendlyReplacer replacer)
/*     */   {
/*  64 */     this(document.getDocumentElement(), document, replacer);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public DomWriter(Element element, Document document, XmlFriendlyReplacer replacer)
/*     */   {
/*  72 */     this(element, document, replacer);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public DomWriter(Element rootElement, XmlFriendlyReplacer replacer)
/*     */   {
/*  80 */     this(rootElement.getOwnerDocument(), replacer);
/*     */   }
/*     */   
/*     */   protected Object createNode(String name) {
/*  84 */     Element child = this.document.createElement(encodeNode(name));
/*  85 */     Element top = top();
/*  86 */     if (top != null) {
/*  87 */       top().appendChild(child);
/*  88 */     } else if (!this.hasRootElement) {
/*  89 */       this.document.appendChild(child);
/*  90 */       this.hasRootElement = true;
/*     */     }
/*  92 */     return child;
/*     */   }
/*     */   
/*     */   public void addAttribute(String name, String value) {
/*  96 */     top().setAttribute(encodeAttribute(name), value);
/*     */   }
/*     */   
/*     */   public void setValue(String text) {
/* 100 */     top().appendChild(this.document.createTextNode(text));
/*     */   }
/*     */   
/*     */   private Element top() {
/* 104 */     return (Element)getCurrent();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/DomWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */