/*     */ package com.thoughtworks.xstream.io.json;
/*     */ 
/*     */ import com.thoughtworks.xstream.io.AbstractDriver;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*     */ import com.thoughtworks.xstream.io.StreamException;
/*     */ import com.thoughtworks.xstream.io.xml.QNameMap;
/*     */ import com.thoughtworks.xstream.io.xml.StaxReader;
/*     */ import com.thoughtworks.xstream.io.xml.StaxWriter;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Reader;
/*     */ import java.io.Writer;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import org.codehaus.jettison.mapped.Configuration;
/*     */ import org.codehaus.jettison.mapped.MappedNamespaceConvention;
/*     */ import org.codehaus.jettison.mapped.MappedXMLInputFactory;
/*     */ import org.codehaus.jettison.mapped.MappedXMLOutputFactory;
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
/*     */ public class JettisonMappedXmlDriver
/*     */   extends AbstractDriver
/*     */ {
/*     */   private final MappedXMLOutputFactory mof;
/*     */   private final MappedXMLInputFactory mif;
/*     */   private final MappedNamespaceConvention convention;
/*  45 */   private boolean useSerializeAsArray = true;
/*     */   
/*     */ 
/*     */ 
/*     */   public JettisonMappedXmlDriver()
/*     */   {
/*  51 */     this(new Configuration());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public JettisonMappedXmlDriver(Configuration config)
/*     */   {
/*  59 */     this(config, true);
/*     */   }
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
/*     */   public JettisonMappedXmlDriver(Configuration config, boolean useSerializeAsArray)
/*     */   {
/*  73 */     this.mof = new MappedXMLOutputFactory(config);
/*  74 */     this.mif = new MappedXMLInputFactory(config);
/*  75 */     this.convention = new MappedNamespaceConvention(config);
/*  76 */     this.useSerializeAsArray = useSerializeAsArray;
/*     */   }
/*     */   
/*     */   public HierarchicalStreamReader createReader(Reader reader) {
/*     */     try {
/*  81 */       return new StaxReader(new QNameMap(), this.mif.createXMLStreamReader(reader), getNameCoder());
/*     */     } catch (XMLStreamException e) {
/*  83 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public HierarchicalStreamReader createReader(InputStream input) {
/*     */     try {
/*  89 */       return new StaxReader(new QNameMap(), this.mif.createXMLStreamReader(input), getNameCoder());
/*     */     } catch (XMLStreamException e) {
/*  91 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public HierarchicalStreamWriter createWriter(Writer writer) {
/*     */     try {
/*  97 */       if (this.useSerializeAsArray) {
/*  98 */         return new JettisonStaxWriter(new QNameMap(), this.mof.createXMLStreamWriter(writer), getNameCoder(), this.convention);
/*     */       }
/* 100 */       return new StaxWriter(new QNameMap(), this.mof.createXMLStreamWriter(writer), getNameCoder());
/*     */     }
/*     */     catch (XMLStreamException e) {
/* 103 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public HierarchicalStreamWriter createWriter(OutputStream output) {
/*     */     try {
/* 109 */       if (this.useSerializeAsArray) {
/* 110 */         return new JettisonStaxWriter(new QNameMap(), this.mof.createXMLStreamWriter(output), getNameCoder(), this.convention);
/*     */       }
/* 112 */       return new StaxWriter(new QNameMap(), this.mof.createXMLStreamWriter(output), getNameCoder());
/*     */     }
/*     */     catch (XMLStreamException e) {
/* 115 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/json/JettisonMappedXmlDriver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */