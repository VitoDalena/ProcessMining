/*     */ package com.thoughtworks.xstream.io.xml;
/*     */ 
/*     */ import com.thoughtworks.xstream.io.StreamException;
/*     */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.XMLStreamException;
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
/*     */ public class StaxWriter
/*     */   extends AbstractXmlWriter
/*     */ {
/*     */   private final QNameMap qnameMap;
/*     */   private final XMLStreamWriter out;
/*     */   private final boolean writeEnclosingDocument;
/*     */   private boolean namespaceRepairingMode;
/*     */   private int tagDepth;
/*     */   
/*     */   public StaxWriter(QNameMap qnameMap, XMLStreamWriter out)
/*     */     throws XMLStreamException
/*     */   {
/*  37 */     this(qnameMap, out, true, true);
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
/*     */   public StaxWriter(QNameMap qnameMap, XMLStreamWriter out, NameCoder nameCoder)
/*     */     throws XMLStreamException
/*     */   {
/*  51 */     this(qnameMap, out, true, true, nameCoder);
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
/*     */ 
/*     */ 
/*     */   public StaxWriter(QNameMap qnameMap, XMLStreamWriter out, boolean writeEnclosingDocument, boolean namespaceRepairingMode, NameCoder nameCoder)
/*     */     throws XMLStreamException
/*     */   {
/*  68 */     super(nameCoder);
/*  69 */     this.qnameMap = qnameMap;
/*  70 */     this.out = out;
/*  71 */     this.writeEnclosingDocument = writeEnclosingDocument;
/*  72 */     this.namespaceRepairingMode = namespaceRepairingMode;
/*  73 */     if (writeEnclosingDocument) {
/*  74 */       out.writeStartDocument();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StaxWriter(QNameMap qnameMap, XMLStreamWriter out, boolean writeEnclosingDocument, boolean namespaceRepairingMode)
/*     */     throws XMLStreamException
/*     */   {
/*  87 */     this(qnameMap, out, writeEnclosingDocument, namespaceRepairingMode, new XmlFriendlyNameCoder());
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
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public StaxWriter(QNameMap qnameMap, XMLStreamWriter out, boolean writeEnclosingDocument, boolean namespaceRepairingMode, XmlFriendlyReplacer replacer)
/*     */     throws XMLStreamException
/*     */   {
/* 104 */     this(qnameMap, out, writeEnclosingDocument, namespaceRepairingMode, replacer);
/*     */   }
/*     */   
/*     */   public void flush() {
/*     */     try {
/* 109 */       this.out.flush();
/*     */     }
/*     */     catch (XMLStreamException e) {
/* 112 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void close()
/*     */   {
/*     */     try
/*     */     {
/* 121 */       this.out.close();
/*     */     }
/*     */     catch (XMLStreamException e) {
/* 124 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addAttribute(String name, String value) {
/*     */     try {
/* 130 */       this.out.writeAttribute(encodeAttribute(name), value);
/*     */     }
/*     */     catch (XMLStreamException e) {
/* 133 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void endNode() {
/*     */     try {
/* 139 */       this.tagDepth -= 1;
/* 140 */       this.out.writeEndElement();
/* 141 */       if ((this.tagDepth == 0) && (this.writeEnclosingDocument)) {
/* 142 */         this.out.writeEndDocument();
/*     */       }
/*     */     }
/*     */     catch (XMLStreamException e) {
/* 146 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setValue(String text) {
/*     */     try {
/* 152 */       this.out.writeCharacters(text);
/*     */     }
/*     */     catch (XMLStreamException e) {
/* 155 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void startNode(String name) {
/*     */     try {
/* 161 */       QName qname = this.qnameMap.getQName(encodeNode(name));
/* 162 */       String prefix = qname.getPrefix();
/* 163 */       String uri = qname.getNamespaceURI();
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 168 */       boolean hasPrefix = (prefix != null) && (prefix.length() > 0);
/* 169 */       boolean hasURI = (uri != null) && (uri.length() > 0);
/* 170 */       boolean writeNamespace = false;
/*     */       
/* 172 */       if (hasURI) {
/* 173 */         if (hasPrefix) {
/* 174 */           String currentNamespace = this.out.getNamespaceContext().getNamespaceURI(prefix);
/* 175 */           if ((currentNamespace == null) || (!currentNamespace.equals(uri))) {
/* 176 */             writeNamespace = true;
/*     */           }
/*     */         }
/*     */         else {
/* 180 */           String defaultNamespace = this.out.getNamespaceContext().getNamespaceURI("");
/* 181 */           if ((defaultNamespace == null) || (!defaultNamespace.equals(uri))) {
/* 182 */             writeNamespace = true;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 187 */       if (hasPrefix) {
/* 188 */         this.out.setPrefix(prefix, uri);
/*     */       }
/* 190 */       else if ((hasURI) && 
/* 191 */         (writeNamespace)) {
/* 192 */         this.out.setDefaultNamespace(uri);
/*     */       }
/*     */       
/* 195 */       this.out.writeStartElement(prefix, qname.getLocalPart(), uri);
/* 196 */       if ((hasURI) && (writeNamespace) && (!isNamespaceRepairingMode())) {
/* 197 */         if (hasPrefix) {
/* 198 */           this.out.writeNamespace(prefix, uri);
/*     */         }
/*     */         else {
/* 201 */           this.out.writeDefaultNamespace(uri);
/*     */         }
/*     */       }
/* 204 */       this.tagDepth += 1;
/*     */     }
/*     */     catch (XMLStreamException e) {
/* 207 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isNamespaceRepairingMode()
/*     */   {
/* 215 */     return this.namespaceRepairingMode;
/*     */   }
/*     */   
/*     */   protected QNameMap getQNameMap() {
/* 219 */     return this.qnameMap;
/*     */   }
/*     */   
/*     */   protected XMLStreamWriter getXMLStreamWriter() {
/* 223 */     return this.out;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/StaxWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */