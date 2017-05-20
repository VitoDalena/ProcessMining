/*     */ package org.xmlpull.v1.builder.impl;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.xmlpull.v1.XmlPullParser;
/*     */ import org.xmlpull.v1.XmlPullParserException;
/*     */ import org.xmlpull.v1.XmlSerializer;
/*     */ import org.xmlpull.v1.builder.XmlAttribute;
/*     */ import org.xmlpull.v1.builder.XmlBuilderException;
/*     */ import org.xmlpull.v1.builder.XmlCharacters;
/*     */ import org.xmlpull.v1.builder.XmlComment;
/*     */ import org.xmlpull.v1.builder.XmlContainer;
/*     */ import org.xmlpull.v1.builder.XmlDocument;
/*     */ import org.xmlpull.v1.builder.XmlElement;
/*     */ import org.xmlpull.v1.builder.XmlInfosetBuilder;
/*     */ import org.xmlpull.v1.builder.XmlNamespace;
/*     */ import org.xmlpull.v1.builder.XmlSerializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XmlInfosetBuilderImpl
/*     */   extends XmlInfosetBuilder
/*     */ {
/*     */   private static final String PROPERTY_XMLDECL_STANDALONE = "http://xmlpull.org/v1/doc/properties.html#xmldecl-standalone";
/*     */   private static final String PROPERTY_XMLDECL_VERSION = "http://xmlpull.org/v1/doc/properties.html#xmldecl-version";
/*     */   
/*     */   public XmlDocument newDocument(String version, Boolean standalone, String characterEncoding)
/*     */   {
/*  45 */     return new XmlDocumentImpl(version, standalone, characterEncoding);
/*     */   }
/*     */   
/*     */   public XmlElement newFragment(String elementName)
/*     */   {
/*  50 */     return new XmlElementImpl((XmlNamespace)null, elementName);
/*     */   }
/*     */   
/*     */   public XmlElement newFragment(String elementNamespaceName, String elementName)
/*     */   {
/*  55 */     return new XmlElementImpl(elementNamespaceName, elementName);
/*     */   }
/*     */   
/*     */   public XmlElement newFragment(XmlNamespace elementNamespace, String elementName)
/*     */   {
/*  60 */     return new XmlElementImpl(elementNamespace, elementName);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public XmlNamespace newNamespace(String namespaceName)
/*     */   {
/*  68 */     return new XmlNamespaceImpl(null, namespaceName);
/*     */   }
/*     */   
/*     */   public XmlNamespace newNamespace(String prefix, String namespaceName)
/*     */   {
/*  73 */     return new XmlNamespaceImpl(prefix, namespaceName);
/*     */   }
/*     */   
/*     */   public XmlDocument parse(XmlPullParser pp)
/*     */   {
/*  78 */     XmlDocument doc = parseDocumentStart(pp);
/*  79 */     XmlElement root = parseFragment(pp);
/*  80 */     doc.setDocumentElement(root);
/*     */     
/*  82 */     return doc;
/*     */   }
/*     */   
/*     */   public Object parseItem(XmlPullParser pp)
/*     */   {
/*     */     try {
/*  88 */       int eventType = pp.getEventType();
/*  89 */       if (eventType == 2)
/*  90 */         return parseStartTag(pp);
/*  91 */       if (eventType == 4)
/*  92 */         return pp.getText();
/*  93 */       if (eventType == 0) {
/*  94 */         return parseDocumentStart(pp);
/*     */       }
/*  96 */       throw new XmlBuilderException("currently unsupported event type " + XmlPullParser.TYPES[eventType] + pp.getPositionDescription());
/*     */     }
/*     */     catch (XmlPullParserException e)
/*     */     {
/* 100 */       throw new XmlBuilderException("could not parse XML item", e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private XmlDocument parseDocumentStart(XmlPullParser pp)
/*     */   {
/* 110 */     XmlDocument doc = null;
/*     */     try {
/* 112 */       if (pp.getEventType() != 0) {
/* 113 */         throw new XmlBuilderException("parser must be positioned on beginning of document and not " + pp.getPositionDescription());
/*     */       }
/*     */       
/*     */ 
/* 117 */       pp.next();
/* 118 */       String xmlDeclVersion = (String)pp.getProperty("http://xmlpull.org/v1/doc/properties.html#xmldecl-version");
/* 119 */       Boolean xmlDeclStandalone = (Boolean)pp.getProperty("http://xmlpull.org/v1/doc/properties.html#xmldecl-standalone");
/* 120 */       String characterEncoding = pp.getInputEncoding();
/* 121 */       doc = new XmlDocumentImpl(xmlDeclVersion, xmlDeclStandalone, characterEncoding);
/*     */     } catch (XmlPullParserException e) {
/* 123 */       throw new XmlBuilderException("could not parse XML document prolog", e);
/*     */     } catch (IOException e) {
/* 125 */       throw new XmlBuilderException("could not read XML document prolog", e);
/*     */     }
/* 127 */     return doc;
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public XmlElement parseFragment(XmlPullParser pp)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: invokeinterface 39 1 0
/*     */     //   6: istore_2
/*     */     //   7: aload_1
/*     */     //   8: invokeinterface 13 1 0
/*     */     //   13: istore_3
/*     */     //   14: iload_3
/*     */     //   15: iconst_2
/*     */     //   16: if_icmpeq +43 -> 59
/*     */     //   19: new 16	org/xmlpull/v1/builder/XmlBuilderException
/*     */     //   22: dup
/*     */     //   23: new 17	java/lang/StringBuffer
/*     */     //   26: dup
/*     */     //   27: invokespecial 18	java/lang/StringBuffer:<init>	()V
/*     */     //   30: ldc 40
/*     */     //   32: invokevirtual 20	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*     */     //   35: getstatic 21	org/xmlpull/v1/XmlPullParser:TYPES	[Ljava/lang/String;
/*     */     //   38: iload_3
/*     */     //   39: aaload
/*     */     //   40: invokevirtual 20	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*     */     //   43: aload_1
/*     */     //   44: invokeinterface 22 1 0
/*     */     //   49: invokevirtual 20	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*     */     //   52: invokevirtual 23	java/lang/StringBuffer:toString	()Ljava/lang/String;
/*     */     //   55: invokespecial 24	org/xmlpull/v1/builder/XmlBuilderException:<init>	(Ljava/lang/String;)V
/*     */     //   58: athrow
/*     */     //   59: aload_0
/*     */     //   60: aload_1
/*     */     //   61: invokevirtual 14	org/xmlpull/v1/builder/impl/XmlInfosetBuilderImpl:parseStartTag	(Lorg/xmlpull/v1/XmlPullParser;)Lorg/xmlpull/v1/builder/XmlElement;
/*     */     //   64: astore 4
/*     */     //   66: aload_1
/*     */     //   67: invokeinterface 29 1 0
/*     */     //   72: istore_3
/*     */     //   73: iload_3
/*     */     //   74: iconst_2
/*     */     //   75: if_icmpne +27 -> 102
/*     */     //   78: aload_0
/*     */     //   79: aload_1
/*     */     //   80: invokevirtual 14	org/xmlpull/v1/builder/impl/XmlInfosetBuilderImpl:parseStartTag	(Lorg/xmlpull/v1/XmlPullParser;)Lorg/xmlpull/v1/builder/XmlElement;
/*     */     //   83: astore 5
/*     */     //   85: aload 4
/*     */     //   87: aload 5
/*     */     //   89: invokeinterface 41 2 0
/*     */     //   94: pop
/*     */     //   95: aload 5
/*     */     //   97: astore 4
/*     */     //   99: goto -33 -> 66
/*     */     //   102: iload_3
/*     */     //   103: iconst_3
/*     */     //   104: if_icmpne +72 -> 176
/*     */     //   107: aload 4
/*     */     //   109: invokeinterface 42 1 0
/*     */     //   114: astore 5
/*     */     //   116: aload 5
/*     */     //   118: ifnonnull +48 -> 166
/*     */     //   121: aload_1
/*     */     //   122: invokeinterface 39 1 0
/*     */     //   127: iload_2
/*     */     //   128: if_icmpeq +35 -> 163
/*     */     //   131: new 16	org/xmlpull/v1/builder/XmlBuilderException
/*     */     //   134: dup
/*     */     //   135: new 17	java/lang/StringBuffer
/*     */     //   138: dup
/*     */     //   139: invokespecial 18	java/lang/StringBuffer:<init>	()V
/*     */     //   142: ldc 43
/*     */     //   144: invokevirtual 20	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*     */     //   147: aload_1
/*     */     //   148: invokeinterface 22 1 0
/*     */     //   153: invokevirtual 20	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*     */     //   156: invokevirtual 23	java/lang/StringBuffer:toString	()Ljava/lang/String;
/*     */     //   159: invokespecial 24	org/xmlpull/v1/builder/XmlBuilderException:<init>	(Ljava/lang/String;)V
/*     */     //   162: athrow
/*     */     //   163: aload 4
/*     */     //   165: areturn
/*     */     //   166: aload 5
/*     */     //   168: checkcast 44	org/xmlpull/v1/builder/XmlElement
/*     */     //   171: astore 4
/*     */     //   173: goto -107 -> 66
/*     */     //   176: iload_3
/*     */     //   177: iconst_4
/*     */     //   178: if_icmpne -112 -> 66
/*     */     //   181: aload 4
/*     */     //   183: aload_1
/*     */     //   184: invokeinterface 15 1 0
/*     */     //   189: invokeinterface 45 2 0
/*     */     //   194: goto -128 -> 66
/*     */     //   197: astore_2
/*     */     //   198: new 16	org/xmlpull/v1/builder/XmlBuilderException
/*     */     //   201: dup
/*     */     //   202: ldc 46
/*     */     //   204: aload_2
/*     */     //   205: invokespecial 27	org/xmlpull/v1/builder/XmlBuilderException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
/*     */     //   208: athrow
/*     */     //   209: astore_2
/*     */     //   210: new 16	org/xmlpull/v1/builder/XmlBuilderException
/*     */     //   213: dup
/*     */     //   214: ldc 47
/*     */     //   216: aload_2
/*     */     //   217: invokespecial 27	org/xmlpull/v1/builder/XmlBuilderException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
/*     */     //   220: athrow
/*     */     // Line number table:
/*     */     //   Java source line #135	-> byte code offset #0
/*     */     //   Java source line #136	-> byte code offset #7
/*     */     //   Java source line #137	-> byte code offset #14
/*     */     //   Java source line #138	-> byte code offset #19
/*     */     //   Java source line #143	-> byte code offset #59
/*     */     //   Java source line #145	-> byte code offset #66
/*     */     //   Java source line #146	-> byte code offset #73
/*     */     //   Java source line #147	-> byte code offset #78
/*     */     //   Java source line #148	-> byte code offset #85
/*     */     //   Java source line #149	-> byte code offset #95
/*     */     //   Java source line #150	-> byte code offset #99
/*     */     //   Java source line #151	-> byte code offset #107
/*     */     //   Java source line #152	-> byte code offset #116
/*     */     //   Java source line #153	-> byte code offset #121
/*     */     //   Java source line #154	-> byte code offset #131
/*     */     //   Java source line #157	-> byte code offset #163
/*     */     //   Java source line #159	-> byte code offset #166
/*     */     //   Java source line #162	-> byte code offset #173
/*     */     //   Java source line #163	-> byte code offset #181
/*     */     //   Java source line #164	-> byte code offset #194
/*     */     //   Java source line #166	-> byte code offset #197
/*     */     //   Java source line #167	-> byte code offset #198
/*     */     //   Java source line #168	-> byte code offset #209
/*     */     //   Java source line #169	-> byte code offset #210
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	221	0	this	XmlInfosetBuilderImpl
/*     */     //   0	221	1	pp	XmlPullParser
/*     */     //   6	122	2	depth	int
/*     */     //   197	8	2	e	XmlPullParserException
/*     */     //   209	8	2	e	IOException
/*     */     //   13	164	3	eventType	int
/*     */     //   64	118	4	curElem	XmlElement
/*     */     //   83	13	5	child	XmlElement
/*     */     //   114	53	5	parent	XmlContainer
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   0	165	197	org/xmlpull/v1/XmlPullParserException
/*     */     //   166	197	197	org/xmlpull/v1/XmlPullParserException
/*     */     //   0	165	209	java/io/IOException
/*     */     //   166	197	209	java/io/IOException
/*     */   }
/*     */   
/*     */   public XmlElement parseStartTag(XmlPullParser pp)
/*     */   {
/*     */     try
/*     */     {
/* 177 */       if (pp.getEventType() != 2) {
/* 178 */         throw new XmlBuilderException("parser must be on START_TAG and not " + pp.getPositionDescription());
/*     */       }
/*     */       
/*     */ 
/* 182 */       String elNsPrefix = pp.getPrefix();
/* 183 */       XmlNamespace elementNs = new XmlNamespaceImpl(elNsPrefix, pp.getNamespace());
/* 184 */       XmlElement el = new XmlElementImpl(elementNs, pp.getName());
/*     */       
/* 186 */       for (int i = pp.getNamespaceCount(pp.getDepth() - 1); 
/* 187 */           i < pp.getNamespaceCount(pp.getDepth()); i++)
/*     */       {
/*     */ 
/* 190 */         String prefix = pp.getNamespacePrefix(i);
/* 191 */         el.declareNamespace(prefix == null ? "" : prefix, pp.getNamespaceUri(i));
/*     */       }
/*     */       
/* 194 */       for (int i = 0; i < pp.getAttributeCount(); i++)
/*     */       {
/* 196 */         el.addAttribute(pp.getAttributeType(i), pp.getAttributePrefix(i), pp.getAttributeNamespace(i), pp.getAttributeName(i), pp.getAttributeValue(i), !pp.isAttributeDefault(i));
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 203 */       return el;
/*     */     } catch (XmlPullParserException e) {
/* 205 */       throw new XmlBuilderException("could not parse XML start tag", e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public XmlDocument parseLocation(String locationUrl)
/*     */   {
/* 214 */     URL url = null;
/*     */     try {
/* 216 */       url = new URL(locationUrl);
/*     */     } catch (MalformedURLException e) {
/* 218 */       throw new XmlBuilderException("could not parse URL " + locationUrl, e);
/*     */     }
/*     */     try {
/* 221 */       return parseInputStream(url.openStream());
/*     */     } catch (IOException e) {
/* 223 */       throw new XmlBuilderException("could not open connection to URL " + locationUrl, e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void serialize(Object item, XmlSerializer serializer) {
/*     */     Iterator i;
/* 229 */     if ((item instanceof Collection)) {
/* 230 */       Collection c = (Collection)item;
/* 231 */       for (i = c.iterator(); i.hasNext();) {
/* 232 */         serialize(i.next(), serializer);
/*     */       }
/*     */     }
/* 235 */     else if ((item instanceof XmlContainer)) {
/* 236 */       serializeContainer((XmlContainer)item, serializer);
/*     */     } else {
/* 238 */       serializeItem(item, serializer);
/*     */     }
/*     */   }
/*     */   
/*     */   private void serializeContainer(XmlContainer node, XmlSerializer serializer)
/*     */   {
/* 244 */     if ((node instanceof XmlSerializable)) {
/*     */       try {
/* 246 */         ((XmlSerializable)node).serialize(serializer);
/*     */       } catch (IOException e) {
/* 248 */         throw new XmlBuilderException("could not serialize node " + node + ": " + e, e);
/*     */       }
/* 250 */     } else if ((node instanceof XmlDocument)) {
/* 251 */       serializeDocument((XmlDocument)node, serializer);
/* 252 */     } else if ((node instanceof XmlElement)) {
/* 253 */       serializeFragment((XmlElement)node, serializer);
/*     */     } else {
/* 255 */       throw new IllegalArgumentException("could not serialzie unknown XML container " + node.getClass());
/*     */     }
/*     */   }
/*     */   
/*     */   public void serializeItem(Object item, XmlSerializer ser)
/*     */   {
/*     */     try
/*     */     {
/* 263 */       if ((item instanceof XmlSerializable)) {
/*     */         try
/*     */         {
/* 266 */           ((XmlSerializable)item).serialize(ser);
/*     */         } catch (IOException e) {
/* 268 */           throw new XmlBuilderException("could not serialize item " + item + ": " + e, e);
/*     */         }
/* 270 */       } else if ((item instanceof String)) {
/* 271 */         ser.text(item.toString());
/* 272 */       } else if ((item instanceof XmlCharacters)) {
/* 273 */         ser.text(((XmlCharacters)item).getText());
/* 274 */       } else if ((item instanceof XmlComment)) {
/* 275 */         ser.comment(((XmlComment)item).getContent());
/*     */       } else {
/* 277 */         throw new IllegalArgumentException("could not serialize " + (item != null ? item.getClass() : item));
/*     */       }
/*     */     } catch (IOException e) {
/* 280 */       throw new XmlBuilderException("serializing XML start tag failed", e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void serializeStartTag(XmlElement el, XmlSerializer ser)
/*     */   {
/*     */     try {
/* 287 */       XmlNamespace elNamespace = el.getNamespace();
/* 288 */       String elPrefix = elNamespace != null ? elNamespace.getPrefix() : "";
/* 289 */       if (elPrefix == null) {
/* 290 */         elPrefix = "";
/*     */       }
/* 292 */       String nToDeclare = null;
/* 293 */       if (el.hasNamespaceDeclarations()) {
/* 294 */         Iterator iter = el.namespaces();
/* 295 */         while (iter.hasNext())
/*     */         {
/* 297 */           XmlNamespace n = (XmlNamespace)iter.next();
/* 298 */           String nPrefix = n.getPrefix();
/* 299 */           if (!elPrefix.equals(nPrefix)) {
/* 300 */             ser.setPrefix(nPrefix, n.getNamespaceName());
/*     */           } else {
/* 302 */             nToDeclare = n.getNamespaceName();
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 307 */       if (nToDeclare != null) {
/* 308 */         ser.setPrefix(elPrefix, nToDeclare);
/*     */       }
/* 310 */       else if (elNamespace != null)
/*     */       {
/* 312 */         String namespaceName = elNamespace.getNamespaceName();
/* 313 */         if (namespaceName == null) {
/* 314 */           namespaceName = "";
/*     */         }
/*     */         
/* 317 */         String serPrefix = null;
/* 318 */         if (namespaceName.length() > 0) {
/* 319 */           ser.getPrefix(namespaceName, false);
/*     */         }
/* 321 */         if (serPrefix == null) {
/* 322 */           serPrefix = "";
/*     */         }
/* 324 */         if ((serPrefix != elPrefix) && (!serPrefix.equals(elPrefix)))
/*     */         {
/* 326 */           ser.setPrefix(elPrefix, namespaceName);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 331 */       ser.startTag(el.getNamespaceName(), el.getName());
/* 332 */       if (el.hasAttributes()) {
/* 333 */         Iterator iter = el.attributes();
/* 334 */         while (iter.hasNext())
/*     */         {
/* 336 */           XmlAttribute a = (XmlAttribute)iter.next();
/* 337 */           if ((a instanceof XmlSerializable)) {
/* 338 */             ((XmlSerializable)a).serialize(ser);
/*     */           } else {
/* 340 */             ser.attribute(a.getNamespaceName(), a.getName(), a.getValue());
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (IOException e) {
/* 345 */       throw new XmlBuilderException("serializing XML start tag failed", e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void serializeEndTag(XmlElement el, XmlSerializer ser) {
/*     */     try {
/* 351 */       ser.endTag(el.getNamespaceName(), el.getName());
/*     */     } catch (IOException e) {
/* 353 */       throw new XmlBuilderException("serializing XML end tag failed", e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void serializeDocument(XmlDocument doc, XmlSerializer ser)
/*     */   {
/*     */     try
/*     */     {
/* 362 */       ser.startDocument(doc.getCharacterEncodingScheme(), doc.isStandalone());
/*     */     } catch (IOException e) {
/* 364 */       throw new XmlBuilderException("serializing XML document start failed", e);
/*     */     }
/* 366 */     if (doc.getDocumentElement() != null) {
/* 367 */       serializeFragment(doc.getDocumentElement(), ser);
/*     */     } else {
/* 369 */       throw new XmlBuilderException("could not serialize document without root element " + doc + ": ");
/*     */     }
/*     */     try {
/* 372 */       ser.endDocument();
/*     */     } catch (IOException e) {
/* 374 */       throw new XmlBuilderException("serializing XML document end failed", e);
/*     */     }
/*     */   }
/*     */   
/*     */   private void serializeFragment(XmlElement el, XmlSerializer ser)
/*     */   {
/* 380 */     serializeStartTag(el, ser);
/*     */     
/* 382 */     if (el.hasChildren()) {
/* 383 */       Iterator iter = el.children();
/* 384 */       while (iter.hasNext())
/*     */       {
/* 386 */         Object child = iter.next();
/* 387 */         if ((child instanceof XmlSerializable)) {
/*     */           try
/*     */           {
/* 390 */             ((XmlSerializable)child).serialize(ser);
/*     */           } catch (IOException e) {
/* 392 */             throw new XmlBuilderException("could not serialize item " + child + ": " + e, e);
/*     */           }
/*     */           
/* 395 */         } else if ((child instanceof XmlElement)) {
/* 396 */           serializeFragment((XmlElement)child, ser);
/*     */         } else {
/* 398 */           serializeItem(child, ser);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 405 */     serializeEndTag(el, ser);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/xmlpull/v1/builder/impl/XmlInfosetBuilderImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */