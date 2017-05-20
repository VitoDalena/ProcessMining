/*     */ package com.thoughtworks.xstream.io.json;
/*     */ 
/*     */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*     */ import com.thoughtworks.xstream.io.xml.QNameMap;
/*     */ import com.thoughtworks.xstream.io.xml.StaxWriter;
/*     */ import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.XMLStreamWriter;
/*     */ import org.codehaus.jettison.AbstractXMLStreamWriter;
/*     */ import org.codehaus.jettison.mapped.MappedNamespaceConvention;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JettisonStaxWriter
/*     */   extends StaxWriter
/*     */ {
/*     */   private final MappedNamespaceConvention convention;
/*     */   
/*     */   public JettisonStaxWriter(QNameMap qnameMap, XMLStreamWriter out, boolean writeEnclosingDocument, boolean namespaceRepairingMode, NameCoder nameCoder, MappedNamespaceConvention convention)
/*     */     throws XMLStreamException
/*     */   {
/*  46 */     super(qnameMap, out, writeEnclosingDocument, namespaceRepairingMode, nameCoder);
/*  47 */     this.convention = convention;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public JettisonStaxWriter(QNameMap qnameMap, XMLStreamWriter out, boolean writeEnclosingDocument, boolean namespaceRepairingMode, XmlFriendlyReplacer replacer, MappedNamespaceConvention convention)
/*     */     throws XMLStreamException
/*     */   {
/*  59 */     this(qnameMap, out, writeEnclosingDocument, namespaceRepairingMode, replacer, convention);
/*     */   }
/*     */   
/*     */ 
/*     */   public JettisonStaxWriter(QNameMap qnameMap, XMLStreamWriter out, boolean writeEnclosingDocument, boolean namespaceRepairingMode, MappedNamespaceConvention convention)
/*     */     throws XMLStreamException
/*     */   {
/*  66 */     super(qnameMap, out, writeEnclosingDocument, namespaceRepairingMode);
/*  67 */     this.convention = convention;
/*     */   }
/*     */   
/*     */   public JettisonStaxWriter(QNameMap qnameMap, XMLStreamWriter out, MappedNamespaceConvention convention)
/*     */     throws XMLStreamException
/*     */   {
/*  73 */     super(qnameMap, out);
/*  74 */     this.convention = convention;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public JettisonStaxWriter(QNameMap qnameMap, XMLStreamWriter out, NameCoder nameCoder, MappedNamespaceConvention convention)
/*     */     throws XMLStreamException
/*     */   {
/*  83 */     super(qnameMap, out, nameCoder);
/*  84 */     this.convention = convention;
/*     */   }
/*     */   
/*     */   public void startNode(String name, Class clazz) {
/*  88 */     XMLStreamWriter out = getXMLStreamWriter();
/*  89 */     if ((clazz != null) && ((out instanceof AbstractXMLStreamWriter)) && (
/*  90 */       (Collection.class.isAssignableFrom(clazz)) || (Map.class.isAssignableFrom(clazz)) || (clazz.isArray())))
/*     */     {
/*     */ 
/*  93 */       QName qname = getQNameMap().getQName(encodeNode(name));
/*  94 */       String prefix = qname.getPrefix();
/*  95 */       String uri = qname.getNamespaceURI();
/*  96 */       String key = this.convention.createKey(prefix, uri, qname.getLocalPart());
/*  97 */       if (!((AbstractXMLStreamWriter)out).getSerializedAsArrays().contains(key))
/*     */       {
/*  99 */         ((AbstractXMLStreamWriter)out).seriliazeAsArray(key);
/*     */       }
/*     */     }
/*     */     
/* 103 */     startNode(name);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/json/JettisonStaxWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */