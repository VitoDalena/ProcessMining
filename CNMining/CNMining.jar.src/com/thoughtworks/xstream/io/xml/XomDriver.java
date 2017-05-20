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
/*     */ import java.io.Writer;
/*     */ import nu.xom.Builder;
/*     */ import nu.xom.Document;
/*     */ import nu.xom.ParsingException;
/*     */ import nu.xom.ValidityException;
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
/*     */ public class XomDriver
/*     */   extends AbstractXmlDriver
/*     */ {
/*     */   private final Builder builder;
/*     */   
/*     */   public XomDriver()
/*     */   {
/*  36 */     this(new Builder());
/*     */   }
/*     */   
/*     */   public XomDriver(Builder builder) {
/*  40 */     this(builder, new XmlFriendlyNameCoder());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public XomDriver(NameCoder nameCoder)
/*     */   {
/*  47 */     this(new Builder(), nameCoder);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public XomDriver(Builder builder, NameCoder nameCoder)
/*     */   {
/*  54 */     super(nameCoder);
/*  55 */     this.builder = builder;
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public XomDriver(XmlFriendlyReplacer replacer)
/*     */   {
/*  63 */     this(new Builder(), replacer);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public XomDriver(Builder builder, XmlFriendlyReplacer replacer)
/*     */   {
/*  71 */     this(replacer);
/*     */   }
/*     */   
/*     */   protected Builder getBuilder() {
/*  75 */     return this.builder;
/*     */   }
/*     */   
/*     */   public HierarchicalStreamReader createReader(Reader text) {
/*     */     try {
/*  80 */       Document document = this.builder.build(text);
/*  81 */       return new XomReader(document, getNameCoder());
/*     */     } catch (ValidityException e) {
/*  83 */       throw new StreamException(e);
/*     */     } catch (ParsingException e) {
/*  85 */       throw new StreamException(e);
/*     */     } catch (IOException e) {
/*  87 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public HierarchicalStreamReader createReader(InputStream in) {
/*     */     try {
/*  93 */       Document document = this.builder.build(in);
/*  94 */       return new XomReader(document, getNameCoder());
/*     */     } catch (ValidityException e) {
/*  96 */       throw new StreamException(e);
/*     */     } catch (ParsingException e) {
/*  98 */       throw new StreamException(e);
/*     */     } catch (IOException e) {
/* 100 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public HierarchicalStreamWriter createWriter(Writer out) {
/* 105 */     return new PrettyPrintWriter(out, getNameCoder());
/*     */   }
/*     */   
/*     */   public HierarchicalStreamWriter createWriter(OutputStream out) {
/* 109 */     return new PrettyPrintWriter(new OutputStreamWriter(out), getNameCoder());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/XomDriver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */