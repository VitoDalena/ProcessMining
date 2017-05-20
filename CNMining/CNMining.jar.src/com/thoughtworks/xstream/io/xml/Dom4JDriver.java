/*     */ package com.thoughtworks.xstream.io.xml;
/*     */ 
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*     */ import com.thoughtworks.xstream.io.StreamException;
/*     */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*     */ import java.io.FilterWriter;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Reader;
/*     */ import java.io.Writer;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentException;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.io.OutputFormat;
/*     */ import org.dom4j.io.SAXReader;
/*     */ import org.dom4j.io.XMLWriter;
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
/*     */ public class Dom4JDriver
/*     */   extends AbstractXmlDriver
/*     */ {
/*     */   private DocumentFactory documentFactory;
/*     */   private OutputFormat outputFormat;
/*     */   
/*     */   public Dom4JDriver()
/*     */   {
/*  39 */     this(new DocumentFactory(), OutputFormat.createPrettyPrint());
/*  40 */     this.outputFormat.setTrimText(false);
/*     */   }
/*     */   
/*     */   public Dom4JDriver(DocumentFactory documentFactory, OutputFormat outputFormat) {
/*  44 */     this(documentFactory, outputFormat, new XmlFriendlyNameCoder());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Dom4JDriver(DocumentFactory documentFactory, OutputFormat outputFormat, NameCoder nameCoder)
/*     */   {
/*  51 */     super(nameCoder);
/*  52 */     this.documentFactory = documentFactory;
/*  53 */     this.outputFormat = outputFormat;
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public Dom4JDriver(DocumentFactory documentFactory, OutputFormat outputFormat, XmlFriendlyReplacer replacer)
/*     */   {
/*  61 */     this(documentFactory, outputFormat, replacer);
/*     */   }
/*     */   
/*     */   public DocumentFactory getDocumentFactory()
/*     */   {
/*  66 */     return this.documentFactory;
/*     */   }
/*     */   
/*     */   public void setDocumentFactory(DocumentFactory documentFactory) {
/*  70 */     this.documentFactory = documentFactory;
/*     */   }
/*     */   
/*     */   public OutputFormat getOutputFormat() {
/*  74 */     return this.outputFormat;
/*     */   }
/*     */   
/*     */   public void setOutputFormat(OutputFormat outputFormat) {
/*  78 */     this.outputFormat = outputFormat;
/*     */   }
/*     */   
/*     */   public HierarchicalStreamReader createReader(Reader text) {
/*     */     try {
/*  83 */       SAXReader reader = new SAXReader();
/*  84 */       Document document = reader.read(text);
/*  85 */       return new Dom4JReader(document, getNameCoder());
/*     */     } catch (DocumentException e) {
/*  87 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public HierarchicalStreamReader createReader(InputStream in) {
/*     */     try {
/*  93 */       SAXReader reader = new SAXReader();
/*  94 */       Document document = reader.read(in);
/*  95 */       return new Dom4JReader(document, getNameCoder());
/*     */     } catch (DocumentException e) {
/*  97 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public HierarchicalStreamWriter createWriter(Writer out) {
/* 102 */     HierarchicalStreamWriter[] writer = new HierarchicalStreamWriter[1];
/* 103 */     FilterWriter filter = new FilterWriter(out) { private final HierarchicalStreamWriter[] val$writer;
/*     */       
/* 105 */       public void close() { this.val$writer[0].close();
/*     */       }
/* 107 */     };
/* 108 */     writer[0] = new Dom4JXmlWriter(new XMLWriter(filter, this.outputFormat), getNameCoder());
/* 109 */     return writer[0];
/*     */   }
/*     */   
/*     */   public HierarchicalStreamWriter createWriter(OutputStream out) {
/* 113 */     Writer writer = new OutputStreamWriter(out);
/* 114 */     return createWriter(writer);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/Dom4JDriver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */