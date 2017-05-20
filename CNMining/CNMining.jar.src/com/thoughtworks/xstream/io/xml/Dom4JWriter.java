/*     */ package com.thoughtworks.xstream.io.xml;
/*     */ 
/*     */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*     */ import org.dom4j.Branch;
/*     */ import org.dom4j.DocumentFactory;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Dom4JWriter
/*     */   extends AbstractDocumentWriter
/*     */ {
/*     */   private final DocumentFactory documentFactory;
/*     */   
/*     */   public Dom4JWriter(Branch root, DocumentFactory factory, NameCoder nameCoder)
/*     */   {
/*  30 */     super(root, nameCoder);
/*  31 */     this.documentFactory = factory;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Dom4JWriter(DocumentFactory factory, NameCoder nameCoder)
/*     */   {
/*  38 */     this(null, factory, nameCoder);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Dom4JWriter(Branch root, NameCoder nameCoder)
/*     */   {
/*  45 */     this(root, new DocumentFactory(), nameCoder);
/*     */   }
/*     */   
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public Dom4JWriter(Branch root, DocumentFactory factory, XmlFriendlyReplacer replacer)
/*     */   {
/*  54 */     this(root, factory, replacer);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public Dom4JWriter(DocumentFactory factory, XmlFriendlyReplacer replacer)
/*     */   {
/*  62 */     this(null, factory, replacer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Dom4JWriter(DocumentFactory documentFactory)
/*     */   {
/*  69 */     this(documentFactory, new XmlFriendlyNameCoder());
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public Dom4JWriter(Branch root, XmlFriendlyReplacer replacer)
/*     */   {
/*  77 */     this(root, new DocumentFactory(), replacer);
/*     */   }
/*     */   
/*     */   public Dom4JWriter(Branch root) {
/*  81 */     this(root, new DocumentFactory(), new XmlFriendlyNameCoder());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Dom4JWriter()
/*     */   {
/*  88 */     this(new DocumentFactory(), new XmlFriendlyNameCoder());
/*     */   }
/*     */   
/*     */   protected Object createNode(String name) {
/*  92 */     Element element = this.documentFactory.createElement(encodeNode(name));
/*  93 */     Branch top = top();
/*  94 */     if (top != null) {
/*  95 */       top().add(element);
/*     */     }
/*  97 */     return element;
/*     */   }
/*     */   
/*     */   public void setValue(String text) {
/* 101 */     top().setText(text);
/*     */   }
/*     */   
/*     */   public void addAttribute(String key, String value) {
/* 105 */     ((Element)top()).addAttribute(encodeAttribute(key), value);
/*     */   }
/*     */   
/*     */   private Branch top() {
/* 109 */     return (Branch)getCurrent();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/Dom4JWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */