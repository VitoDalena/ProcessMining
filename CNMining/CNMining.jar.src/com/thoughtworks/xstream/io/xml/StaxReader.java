/*     */ package com.thoughtworks.xstream.io.xml;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.ErrorWriter;
/*     */ import com.thoughtworks.xstream.io.StreamException;
/*     */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.XMLStreamReader;
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
/*     */ public class StaxReader
/*     */   extends AbstractPullReader
/*     */ {
/*     */   private final QNameMap qnameMap;
/*     */   private final XMLStreamReader in;
/*     */   
/*     */   public StaxReader(QNameMap qnameMap, XMLStreamReader in)
/*     */   {
/*  35 */     this(qnameMap, in, new XmlFriendlyNameCoder());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public StaxReader(QNameMap qnameMap, XMLStreamReader in, NameCoder replacer)
/*     */   {
/*  42 */     super(replacer);
/*  43 */     this.qnameMap = qnameMap;
/*  44 */     this.in = in;
/*  45 */     moveDown();
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public StaxReader(QNameMap qnameMap, XMLStreamReader in, XmlFriendlyReplacer replacer)
/*     */   {
/*  53 */     this(qnameMap, in, replacer);
/*     */   }
/*     */   
/*     */   protected int pullNextEvent() {
/*     */     try {
/*  58 */       switch (this.in.next()) {
/*     */       case 1: 
/*     */       case 7: 
/*  61 */         return 1;
/*     */       case 2: 
/*     */       case 8: 
/*  64 */         return 2;
/*     */       case 4: 
/*  66 */         return 3;
/*     */       case 5: 
/*  68 */         return 4;
/*     */       }
/*  70 */       return 0;
/*     */     }
/*     */     catch (XMLStreamException e) {
/*  73 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   protected String pullElementName()
/*     */   {
/*  79 */     QName qname = this.in.getName();
/*  80 */     return this.qnameMap.getJavaClassName(qname);
/*     */   }
/*     */   
/*     */   protected String pullText() {
/*  84 */     return this.in.getText();
/*     */   }
/*     */   
/*     */   public String getAttribute(String name) {
/*  88 */     return this.in.getAttributeValue(null, encodeAttribute(name));
/*     */   }
/*     */   
/*     */   public String getAttribute(int index) {
/*  92 */     return this.in.getAttributeValue(index);
/*     */   }
/*     */   
/*     */   public int getAttributeCount() {
/*  96 */     return this.in.getAttributeCount();
/*     */   }
/*     */   
/*     */   public String getAttributeName(int index) {
/* 100 */     return decodeAttribute(this.in.getAttributeLocalName(index));
/*     */   }
/*     */   
/*     */   public void appendErrors(ErrorWriter errorWriter) {
/* 104 */     errorWriter.add("line number", String.valueOf(this.in.getLocation().getLineNumber()));
/*     */   }
/*     */   
/*     */   public void close() {
/*     */     try {
/* 109 */       this.in.close();
/*     */     } catch (XMLStreamException e) {
/* 111 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/StaxReader.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */