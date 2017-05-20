/*     */ package com.thoughtworks.xstream.io.xml;
/*     */ 
/*     */ import com.thoughtworks.xstream.XStream;
/*     */ import com.thoughtworks.xstream.io.StreamException;
/*     */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.DTDHandler;
/*     */ import org.xml.sax.EntityResolver;
/*     */ import org.xml.sax.ErrorHandler;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXNotRecognizedException;
/*     */ import org.xml.sax.SAXNotSupportedException;
/*     */ import org.xml.sax.XMLReader;
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
/*     */ public final class SaxWriter
/*     */   extends AbstractXmlWriter
/*     */   implements XMLReader
/*     */ {
/*     */   public static final String CONFIGURED_XSTREAM_PROPERTY = "http://com.thoughtworks.xstream/sax/property/configured-xstream";
/*     */   public static final String SOURCE_OBJECT_LIST_PROPERTY = "http://com.thoughtworks.xstream/sax/property/source-object-list";
/*  86 */   private EntityResolver entityResolver = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  91 */   private DTDHandler dtdHandler = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  96 */   private ContentHandler contentHandler = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 101 */   private ErrorHandler errorHandler = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 111 */   private Map features = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 116 */   private final Map properties = new HashMap();
/*     */   
/*     */ 
/*     */   private final boolean includeEnclosingDocument;
/*     */   
/*     */ 
/*     */ 
/*     */   public SaxWriter(NameCoder nameCoder)
/*     */   {
/* 125 */     this(true, nameCoder);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SaxWriter(boolean includeEnclosingDocument, NameCoder nameCoder)
/*     */   {
/* 133 */     super(nameCoder);
/* 134 */     this.includeEnclosingDocument = includeEnclosingDocument;
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public SaxWriter(XmlFriendlyReplacer replacer)
/*     */   {
/* 142 */     this(true, replacer);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public SaxWriter(boolean includeEnclosingDocument, XmlFriendlyReplacer replacer)
/*     */   {
/* 150 */     this(includeEnclosingDocument, replacer);
/*     */   }
/*     */   
/*     */   public SaxWriter(boolean includeEnclosingDocument) {
/* 154 */     this(includeEnclosingDocument, new XmlFriendlyNameCoder());
/*     */   }
/*     */   
/*     */   public SaxWriter() {
/* 158 */     this(true);
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
/*     */   public void setFeature(String name, boolean value)
/*     */     throws SAXNotRecognizedException
/*     */   {
/* 191 */     if ((name.equals("http://xml.org/sax/features/namespaces")) || (name.equals("http://xml.org/sax/features/namespace-prefixes")))
/*     */     {
/* 193 */       this.features.put(name, value ? Boolean.TRUE : Boolean.FALSE);
/*     */     } else {
/* 195 */       throw new SAXNotRecognizedException(name);
/*     */     }
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
/*     */   public boolean getFeature(String name)
/*     */     throws SAXNotRecognizedException
/*     */   {
/* 228 */     if ((name.equals("http://xml.org/sax/features/namespaces")) || (name.equals("http://xml.org/sax/features/namespace-prefixes")))
/*     */     {
/* 230 */       Boolean value = (Boolean)this.features.get(name);
/*     */       
/* 232 */       if (value == null) {
/* 233 */         value = Boolean.FALSE;
/*     */       }
/* 235 */       return value.booleanValue();
/*     */     }
/* 237 */     throw new SAXNotRecognizedException(name);
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
/*     */   public void setProperty(String name, Object value)
/*     */     throws SAXNotRecognizedException, SAXNotSupportedException
/*     */   {
/* 275 */     if (name.equals("http://com.thoughtworks.xstream/sax/property/configured-xstream")) {
/* 276 */       if (!(value instanceof XStream)) {
/* 277 */         throw new SAXNotSupportedException("Value for property \"http://com.thoughtworks.xstream/sax/property/configured-xstream\" must be a non-null XStream object");
/*     */       }
/*     */       
/*     */     }
/* 281 */     else if (name.equals("http://com.thoughtworks.xstream/sax/property/source-object-list")) {
/* 282 */       if ((value instanceof List)) {
/* 283 */         List list = (List)value;
/*     */         
/* 285 */         if (list.isEmpty()) {
/* 286 */           throw new SAXNotSupportedException("Value for property \"http://com.thoughtworks.xstream/sax/property/source-object-list\" shall not be an empty list");
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 292 */         value = Collections.unmodifiableList(new ArrayList(list));
/*     */       }
/*     */       else {
/* 295 */         throw new SAXNotSupportedException("Value for property \"http://com.thoughtworks.xstream/sax/property/source-object-list\" must be a non-null List object");
/*     */       }
/*     */       
/*     */     }
/*     */     else {
/* 300 */       throw new SAXNotRecognizedException(name);
/*     */     }
/* 302 */     this.properties.put(name, value);
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
/*     */   public Object getProperty(String name)
/*     */     throws SAXNotRecognizedException
/*     */   {
/* 330 */     if ((name.equals("http://com.thoughtworks.xstream/sax/property/configured-xstream")) || (name.equals("http://com.thoughtworks.xstream/sax/property/source-object-list")))
/*     */     {
/* 332 */       return this.properties.get(name);
/*     */     }
/* 334 */     throw new SAXNotRecognizedException(name);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEntityResolver(EntityResolver resolver)
/*     */   {
/* 358 */     if (resolver == null) {
/* 359 */       throw new NullPointerException("resolver");
/*     */     }
/* 361 */     this.entityResolver = resolver;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public EntityResolver getEntityResolver()
/*     */   {
/* 373 */     return this.entityResolver;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDTDHandler(DTDHandler handler)
/*     */   {
/* 392 */     if (handler == null) {
/* 393 */       throw new NullPointerException("handler");
/*     */     }
/* 395 */     this.dtdHandler = handler;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DTDHandler getDTDHandler()
/*     */   {
/* 407 */     return this.dtdHandler;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setContentHandler(ContentHandler handler)
/*     */   {
/* 427 */     if (handler == null) {
/* 428 */       throw new NullPointerException("handler");
/*     */     }
/* 430 */     this.contentHandler = handler;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ContentHandler getContentHandler()
/*     */   {
/* 442 */     return this.contentHandler;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setErrorHandler(ErrorHandler handler)
/*     */   {
/* 464 */     if (handler == null) {
/* 465 */       throw new NullPointerException("handler");
/*     */     }
/* 467 */     this.errorHandler = handler;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ErrorHandler getErrorHandler()
/*     */   {
/* 479 */     return this.errorHandler;
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
/*     */   public void parse(String systemId)
/*     */     throws SAXException
/*     */   {
/* 513 */     parse();
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
/*     */ 
/*     */ 
/*     */   public void parse(InputSource input)
/*     */     throws SAXException
/*     */   {
/* 555 */     parse();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void parse()
/*     */     throws SAXException
/*     */   {
/* 566 */     XStream xstream = (XStream)this.properties.get("http://com.thoughtworks.xstream/sax/property/configured-xstream");
/* 567 */     if (xstream == null) {
/* 568 */       xstream = new XStream();
/*     */     }
/*     */     
/* 571 */     List source = (List)this.properties.get("http://com.thoughtworks.xstream/sax/property/source-object-list");
/* 572 */     if ((source == null) || (source.isEmpty())) {
/* 573 */       throw new SAXException("Missing or empty source object list. Setting property \"http://com.thoughtworks.xstream/sax/property/source-object-list\" is mandatory");
/*     */     }
/*     */     
/*     */     try
/*     */     {
/* 578 */       startDocument(true);
/* 579 */       for (Iterator i = source.iterator(); i.hasNext();) {
/* 580 */         xstream.marshal(i.next(), this);
/*     */       }
/* 582 */       endDocument(true);
/*     */     } catch (StreamException e) {
/* 584 */       if ((e.getCause() instanceof SAXException)) {
/* 585 */         throw ((SAXException)e.getCause());
/*     */       }
/* 587 */       throw new SAXException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 597 */   private int depth = 0;
/* 598 */   private List elementStack = new LinkedList();
/* 599 */   private char[] buffer = new char[''];
/* 600 */   private boolean startTagInProgress = false;
/* 601 */   private final AttributesImpl attributeList = new AttributesImpl();
/*     */   
/*     */   public void startNode(String name) {
/*     */     try {
/* 605 */       if (this.depth != 0) {
/* 606 */         flushStartTag();
/* 607 */       } else if (this.includeEnclosingDocument) {
/* 608 */         startDocument(false);
/*     */       }
/* 610 */       this.elementStack.add(0, escapeXmlName(name));
/*     */       
/* 612 */       this.startTagInProgress = true;
/* 613 */       this.depth += 1;
/*     */     } catch (SAXException e) {
/* 615 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addAttribute(String name, String value) {
/* 620 */     if (this.startTagInProgress) {
/* 621 */       String escapedName = escapeXmlName(name);
/* 622 */       this.attributeList.addAttribute("", escapedName, escapedName, "CDATA", value);
/*     */     } else {
/* 624 */       throw new StreamException(new IllegalStateException("No startElement being processed"));
/*     */     }
/*     */   }
/*     */   
/*     */   public void setValue(String text) {
/*     */     try {
/* 630 */       flushStartTag();
/*     */       
/* 632 */       int lg = text.length();
/* 633 */       if (lg > this.buffer.length) {
/* 634 */         this.buffer = new char[lg];
/*     */       }
/* 636 */       text.getChars(0, lg, this.buffer, 0);
/*     */       
/* 638 */       this.contentHandler.characters(this.buffer, 0, lg);
/*     */     } catch (SAXException e) {
/* 640 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void endNode() {
/*     */     try {
/* 646 */       flushStartTag();
/*     */       
/* 648 */       String tagName = (String)this.elementStack.remove(0);
/*     */       
/* 650 */       this.contentHandler.endElement("", tagName, tagName);
/*     */       
/* 652 */       this.depth -= 1;
/* 653 */       if ((this.depth == 0) && (this.includeEnclosingDocument)) {
/* 654 */         endDocument(false);
/*     */       }
/*     */     } catch (SAXException e) {
/* 657 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void startDocument(boolean multiObjectMode)
/*     */     throws SAXException
/*     */   {
/* 671 */     if (this.depth == 0)
/*     */     {
/* 673 */       this.contentHandler.startDocument();
/*     */       
/* 675 */       if (multiObjectMode)
/*     */       {
/*     */ 
/* 678 */         this.depth += 1;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void endDocument(boolean multiObjectMode)
/*     */     throws SAXException
/*     */   {
/* 693 */     if ((this.depth == 0) || ((this.depth == 1) && (multiObjectMode))) {
/* 694 */       this.contentHandler.endDocument();
/* 695 */       this.depth = 0;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void flushStartTag()
/*     */     throws SAXException
/*     */   {
/* 706 */     if (this.startTagInProgress) {
/* 707 */       String tagName = (String)this.elementStack.get(0);
/*     */       
/* 709 */       this.contentHandler.startElement("", tagName, tagName, this.attributeList);
/*     */       
/* 711 */       this.attributeList.clear();
/* 712 */       this.startTagInProgress = false;
/*     */     }
/*     */   }
/*     */   
/*     */   public void flush() {}
/*     */   
/*     */   public void close() {}
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/SaxWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */