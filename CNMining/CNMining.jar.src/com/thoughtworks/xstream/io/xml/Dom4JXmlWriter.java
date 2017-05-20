/*     */ package com.thoughtworks.xstream.io.xml;
/*     */ 
/*     */ import com.thoughtworks.xstream.core.util.FastStack;
/*     */ import com.thoughtworks.xstream.io.StreamException;
/*     */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*     */ import java.io.IOException;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.io.XMLWriter;
/*     */ import org.dom4j.tree.DefaultElement;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.AttributesImpl;
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
/*     */ public class Dom4JXmlWriter
/*     */   extends AbstractXmlWriter
/*     */ {
/*     */   private final XMLWriter writer;
/*     */   private final FastStack elementStack;
/*     */   private AttributesImpl attributes;
/*     */   private boolean started;
/*     */   private boolean children;
/*     */   
/*     */   public Dom4JXmlWriter(XMLWriter writer)
/*     */   {
/*  36 */     this(writer, new XmlFriendlyNameCoder());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Dom4JXmlWriter(XMLWriter writer, NameCoder nameCoder)
/*     */   {
/*  43 */     super(nameCoder);
/*  44 */     this.writer = writer;
/*  45 */     this.elementStack = new FastStack(16);
/*  46 */     this.attributes = new AttributesImpl();
/*     */     try {
/*  48 */       writer.startDocument();
/*     */     } catch (SAXException e) {
/*  50 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public Dom4JXmlWriter(XMLWriter writer, XmlFriendlyReplacer replacer)
/*     */   {
/*  59 */     this(writer, replacer);
/*     */   }
/*     */   
/*     */   public void startNode(String name) {
/*  63 */     if (this.elementStack.size() > 0) {
/*     */       try {
/*  65 */         startElement();
/*     */       } catch (SAXException e) {
/*  67 */         throw new StreamException(e);
/*     */       }
/*  69 */       this.started = false;
/*     */     }
/*  71 */     this.elementStack.push(encodeNode(name));
/*  72 */     this.children = false;
/*     */   }
/*     */   
/*     */   public void setValue(String text) {
/*  76 */     char[] value = text.toCharArray();
/*  77 */     if (value.length > 0) {
/*     */       try {
/*  79 */         startElement();
/*  80 */         this.writer.characters(value, 0, value.length);
/*     */       } catch (SAXException e) {
/*  82 */         throw new StreamException(e);
/*     */       }
/*  84 */       this.children = true;
/*     */     }
/*     */   }
/*     */   
/*     */   public void addAttribute(String key, String value) {
/*  89 */     this.attributes.addAttribute("", "", encodeAttribute(key), "string", value);
/*     */   }
/*     */   
/*     */   public void endNode() {
/*     */     try {
/*  94 */       if (!this.children) {
/*  95 */         Element element = new DefaultElement((String)this.elementStack.pop());
/*  96 */         for (int i = 0; i < this.attributes.getLength(); i++) {
/*  97 */           element.addAttribute(this.attributes.getQName(i), this.attributes.getValue(i));
/*     */         }
/*  99 */         this.writer.write(element);
/* 100 */         this.attributes.clear();
/* 101 */         this.children = true;
/* 102 */         this.started = true;
/*     */       } else {
/* 104 */         startElement();
/* 105 */         this.writer.endElement("", "", (String)this.elementStack.pop());
/*     */       }
/*     */     } catch (SAXException e) {
/* 108 */       throw new StreamException(e);
/*     */     } catch (IOException e) {
/* 110 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void flush() {}
/*     */   
/*     */   public void close()
/*     */   {
/*     */     try
/*     */     {
/* 120 */       this.writer.endDocument();
/*     */     } catch (SAXException e) {
/* 122 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   private void startElement() throws SAXException {
/* 127 */     if (!this.started) {
/* 128 */       this.writer.startElement("", "", (String)this.elementStack.peek(), this.attributes);
/* 129 */       this.attributes.clear();
/* 130 */       this.started = true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/Dom4JXmlWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */