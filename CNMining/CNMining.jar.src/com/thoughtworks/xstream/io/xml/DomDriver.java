/*     */ package com.thoughtworks.xstream.io.xml;
/*     */ 
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*     */ import com.thoughtworks.xstream.io.StreamException;
/*     */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Reader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.io.Writer;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.parsers.FactoryConfigurationError;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import org.w3c.dom.Document;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
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
/*     */ public class DomDriver
/*     */   extends AbstractXmlDriver
/*     */ {
/*     */   private final String encoding;
/*     */   private final DocumentBuilderFactory documentBuilderFactory;
/*     */   
/*     */   public DomDriver()
/*     */   {
/*  46 */     this(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DomDriver(String encoding)
/*     */   {
/*  54 */     this(encoding, new XmlFriendlyNameCoder());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DomDriver(String encoding, NameCoder nameCoder)
/*     */   {
/*  61 */     super(nameCoder);
/*  62 */     this.documentBuilderFactory = DocumentBuilderFactory.newInstance();
/*  63 */     this.encoding = encoding;
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public DomDriver(String encoding, XmlFriendlyReplacer replacer)
/*     */   {
/*  71 */     this(encoding, replacer);
/*     */   }
/*     */   
/*     */   public HierarchicalStreamReader createReader(Reader xml) {
/*  75 */     return createReader(new InputSource(xml));
/*     */   }
/*     */   
/*     */   public HierarchicalStreamReader createReader(InputStream xml) {
/*  79 */     return createReader(new InputSource(xml));
/*     */   }
/*     */   
/*     */   private HierarchicalStreamReader createReader(InputSource source) {
/*     */     try {
/*  84 */       DocumentBuilder documentBuilder = this.documentBuilderFactory.newDocumentBuilder();
/*  85 */       if (this.encoding != null) {
/*  86 */         source.setEncoding(this.encoding);
/*     */       }
/*  88 */       Document document = documentBuilder.parse(source);
/*  89 */       return new DomReader(document, getNameCoder());
/*     */     } catch (FactoryConfigurationError e) {
/*  91 */       throw new StreamException(e);
/*     */     } catch (ParserConfigurationException e) {
/*  93 */       throw new StreamException(e);
/*     */     } catch (SAXException e) {
/*  95 */       throw new StreamException(e);
/*     */     } catch (IOException e) {
/*  97 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public HierarchicalStreamWriter createWriter(Writer out) {
/* 102 */     return new PrettyPrintWriter(out, getNameCoder());
/*     */   }
/*     */   
/*     */   public HierarchicalStreamWriter createWriter(OutputStream out) {
/*     */     try {
/* 107 */       return createWriter(this.encoding != null ? new OutputStreamWriter(out, this.encoding) : new OutputStreamWriter(out));
/*     */     }
/*     */     catch (UnsupportedEncodingException e)
/*     */     {
/* 111 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/DomDriver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */