/*     */ package com.thoughtworks.xstream.io.xml;
/*     */ 
/*     */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*     */ import org.jdom.DefaultJDOMFactory;
/*     */ import org.jdom.Element;
/*     */ import org.jdom.JDOMFactory;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JDomWriter
/*     */   extends AbstractDocumentWriter
/*     */ {
/*     */   private final JDOMFactory documentFactory;
/*     */   
/*     */   public JDomWriter(Element container, JDOMFactory factory, NameCoder nameCoder)
/*     */   {
/*  34 */     super(container, nameCoder);
/*  35 */     this.documentFactory = factory;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public JDomWriter(Element container, JDOMFactory factory, XmlFriendlyReplacer replacer)
/*     */   {
/*  45 */     this(container, factory, replacer);
/*     */   }
/*     */   
/*     */   public JDomWriter(Element container, JDOMFactory factory) {
/*  49 */     this(container, factory, new XmlFriendlyNameCoder());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public JDomWriter(JDOMFactory factory, NameCoder nameCoder)
/*     */   {
/*  56 */     this(null, factory, nameCoder);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public JDomWriter(JDOMFactory factory, XmlFriendlyReplacer replacer)
/*     */   {
/*  64 */     this(null, factory, replacer);
/*     */   }
/*     */   
/*     */   public JDomWriter(JDOMFactory factory) {
/*  68 */     this(null, factory);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public JDomWriter(Element container, NameCoder nameCoder)
/*     */   {
/*  75 */     this(container, new DefaultJDOMFactory(), nameCoder);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public JDomWriter(Element container, XmlFriendlyReplacer replacer)
/*     */   {
/*  83 */     this(container, new DefaultJDOMFactory(), replacer);
/*     */   }
/*     */   
/*     */   public JDomWriter(Element container) {
/*  87 */     this(container, new DefaultJDOMFactory());
/*     */   }
/*     */   
/*     */   public JDomWriter() {
/*  91 */     this(new DefaultJDOMFactory());
/*     */   }
/*     */   
/*     */   protected Object createNode(String name) {
/*  95 */     Element element = this.documentFactory.element(encodeNode(name));
/*  96 */     Element parent = top();
/*  97 */     if (parent != null) {
/*  98 */       parent.addContent(element);
/*     */     }
/* 100 */     return element;
/*     */   }
/*     */   
/*     */   public void setValue(String text) {
/* 104 */     top().addContent(this.documentFactory.text(text));
/*     */   }
/*     */   
/*     */   public void addAttribute(String key, String value) {
/* 108 */     top().setAttribute(this.documentFactory.attribute(encodeAttribute(key), value));
/*     */   }
/*     */   
/*     */   private Element top() {
/* 112 */     return (Element)getCurrent();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/JDomWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */