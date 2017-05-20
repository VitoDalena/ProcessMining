/*     */ package com.thoughtworks.xstream.io.xml;
/*     */ 
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*     */ import com.thoughtworks.xstream.io.StreamException;
/*     */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Reader;
/*     */ import java.io.Writer;
/*     */ import javax.xml.stream.XMLInputFactory;
/*     */ import javax.xml.stream.XMLOutputFactory;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.XMLStreamReader;
/*     */ import javax.xml.stream.XMLStreamWriter;
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
/*     */ public class StaxDriver
/*     */   extends AbstractXmlDriver
/*     */ {
/*     */   private QNameMap qnameMap;
/*     */   private XMLInputFactory inputFactory;
/*     */   private XMLOutputFactory outputFactory;
/*     */   
/*     */   public StaxDriver()
/*     */   {
/*  44 */     this(new QNameMap());
/*     */   }
/*     */   
/*     */   public StaxDriver(QNameMap qnameMap) {
/*  48 */     this(qnameMap, new XmlFriendlyNameCoder());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public StaxDriver(QNameMap qnameMap, NameCoder nameCoder)
/*     */   {
/*  55 */     super(nameCoder);
/*  56 */     this.qnameMap = qnameMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public StaxDriver(NameCoder nameCoder)
/*     */   {
/*  63 */     this(new QNameMap(), nameCoder);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public StaxDriver(QNameMap qnameMap, XmlFriendlyReplacer replacer)
/*     */   {
/*  71 */     this(qnameMap, replacer);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public StaxDriver(XmlFriendlyReplacer replacer)
/*     */   {
/*  79 */     this(new QNameMap(), replacer);
/*     */   }
/*     */   
/*     */   public HierarchicalStreamReader createReader(Reader xml) {
/*     */     try {
/*  84 */       return createStaxReader(createParser(xml));
/*     */     }
/*     */     catch (XMLStreamException e) {
/*  87 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public HierarchicalStreamReader createReader(InputStream in) {
/*     */     try {
/*  93 */       return createStaxReader(createParser(in));
/*     */     }
/*     */     catch (XMLStreamException e) {
/*  96 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public HierarchicalStreamWriter createWriter(Writer out) {
/*     */     try {
/* 102 */       return createStaxWriter(getOutputFactory().createXMLStreamWriter(out));
/*     */     }
/*     */     catch (XMLStreamException e) {
/* 105 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public HierarchicalStreamWriter createWriter(OutputStream out) {
/*     */     try {
/* 111 */       return createStaxWriter(getOutputFactory().createXMLStreamWriter(out));
/*     */     }
/*     */     catch (XMLStreamException e) {
/* 114 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public AbstractPullReader createStaxReader(XMLStreamReader in) {
/* 119 */     return new StaxReader(this.qnameMap, in, getNameCoder());
/*     */   }
/*     */   
/*     */   public StaxWriter createStaxWriter(XMLStreamWriter out, boolean writeStartEndDocument) throws XMLStreamException {
/* 123 */     return new StaxWriter(this.qnameMap, out, writeStartEndDocument, isRepairingNamespace(), getNameCoder());
/*     */   }
/*     */   
/*     */   public StaxWriter createStaxWriter(XMLStreamWriter out) throws XMLStreamException {
/* 127 */     return createStaxWriter(out, true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public QNameMap getQnameMap()
/*     */   {
/* 134 */     return this.qnameMap;
/*     */   }
/*     */   
/*     */   public void setQnameMap(QNameMap qnameMap) {
/* 138 */     this.qnameMap = qnameMap;
/*     */   }
/*     */   
/*     */   public XMLInputFactory getInputFactory() {
/* 142 */     if (this.inputFactory == null) {
/* 143 */       this.inputFactory = createInputFactory();
/*     */     }
/* 145 */     return this.inputFactory;
/*     */   }
/*     */   
/*     */   public XMLOutputFactory getOutputFactory() {
/* 149 */     if (this.outputFactory == null) {
/* 150 */       this.outputFactory = createOutputFactory();
/*     */     }
/* 152 */     return this.outputFactory;
/*     */   }
/*     */   
/*     */   public boolean isRepairingNamespace() {
/* 156 */     return Boolean.TRUE.equals(getOutputFactory().getProperty("javax.xml.stream.isRepairingNamespaces"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRepairingNamespace(boolean repairing)
/*     */   {
/* 164 */     getOutputFactory().setProperty("javax.xml.stream.isRepairingNamespaces", repairing ? Boolean.TRUE : Boolean.FALSE);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected XMLStreamReader createParser(Reader xml)
/*     */     throws XMLStreamException
/*     */   {
/* 172 */     return getInputFactory().createXMLStreamReader(xml);
/*     */   }
/*     */   
/*     */   protected XMLStreamReader createParser(InputStream xml) throws XMLStreamException {
/* 176 */     return getInputFactory().createXMLStreamReader(xml);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected XMLInputFactory createInputFactory()
/*     */   {
/* 183 */     return XMLInputFactory.newInstance();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected XMLOutputFactory createOutputFactory()
/*     */   {
/* 190 */     return XMLOutputFactory.newInstance();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/StaxDriver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */