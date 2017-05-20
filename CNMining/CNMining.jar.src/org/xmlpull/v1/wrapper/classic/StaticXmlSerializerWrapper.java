/*     */ package org.xmlpull.v1.wrapper.classic;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.StringReader;
/*     */ import org.xmlpull.v1.XmlPullParser;
/*     */ import org.xmlpull.v1.XmlPullParserException;
/*     */ import org.xmlpull.v1.XmlSerializer;
/*     */ import org.xmlpull.v1.wrapper.XmlPullParserWrapper;
/*     */ import org.xmlpull.v1.wrapper.XmlPullWrapperFactory;
/*     */ import org.xmlpull.v1.wrapper.XmlSerializerWrapper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StaticXmlSerializerWrapper
/*     */   extends XmlSerializerDelegate
/*     */   implements XmlSerializerWrapper
/*     */ {
/*     */   private static final String PROPERTY_XMLDECL_STANDALONE = "http://xmlpull.org/v1/doc/features.html#xmldecl-standalone";
/*     */   private static final boolean TRACE_SIZING = false;
/*     */   protected String currentNs;
/*     */   protected XmlPullWrapperFactory wf;
/*     */   protected XmlPullParserWrapper fragmentParser;
/*     */   
/*     */   public StaticXmlSerializerWrapper(XmlSerializer xs, XmlPullWrapperFactory wf)
/*     */   {
/*  33 */     super(xs);
/*  34 */     this.wf = wf;
/*     */   }
/*     */   
/*  37 */   public String getCurrentNamespaceForElements() { return this.currentNs; }
/*     */   
/*     */   public String setCurrentNamespaceForElements(String value)
/*     */   {
/*  41 */     String old = this.currentNs;
/*  42 */     this.currentNs = value;
/*  43 */     return old;
/*     */   }
/*     */   
/*     */   public XmlSerializerWrapper attribute(String name, String value)
/*     */     throws IOException, IllegalArgumentException, IllegalStateException
/*     */   {
/*  49 */     this.xs.attribute(null, name, value);
/*  50 */     return this;
/*     */   }
/*     */   
/*     */   public XmlSerializerWrapper startTag(String name)
/*     */     throws IOException, IllegalArgumentException, IllegalStateException
/*     */   {
/*  56 */     this.xs.startTag(this.currentNs, name);
/*  57 */     return this;
/*     */   }
/*     */   
/*     */   public XmlSerializerWrapper endTag(String name)
/*     */     throws IOException, IllegalArgumentException, IllegalStateException
/*     */   {
/*  63 */     endTag(this.currentNs, name);
/*  64 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public XmlSerializerWrapper element(String elementName, String elementText)
/*     */     throws IOException, XmlPullParserException
/*     */   {
/*  71 */     return element(this.currentNs, elementName, elementText);
/*     */   }
/*     */   
/*     */ 
/*     */   public XmlSerializerWrapper element(String namespace, String elementName, String elementText)
/*     */     throws IOException, XmlPullParserException
/*     */   {
/*  78 */     if (elementName == null) {
/*  79 */       throw new XmlPullParserException("name for element can not be null");
/*     */     }
/*     */     
/*  82 */     this.xs.startTag(namespace, elementName);
/*  83 */     if (elementText == null) {
/*  84 */       this.xs.attribute("http://www.w3.org/2001/XMLSchema-instance", "nil", "true");
/*     */     }
/*     */     else {
/*  87 */       this.xs.text(elementText);
/*     */     }
/*  89 */     this.xs.endTag(namespace, elementName);
/*  90 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  98 */   protected int namespaceEnd = 0;
/*  99 */   protected String[] namespacePrefix = new String[8];
/* 100 */   protected String[] namespaceUri = new String[this.namespacePrefix.length];
/* 101 */   protected int[] namespaceDepth = new int[this.namespacePrefix.length];
/*     */   
/*     */   private void ensureNamespacesCapacity() {
/* 104 */     int newSize = this.namespaceEnd > 7 ? 2 * this.namespaceEnd : 8;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 109 */     String[] newNamespacePrefix = new String[newSize];
/* 110 */     String[] newNamespaceUri = new String[newSize];
/* 111 */     int[] newNamespaceDepth = new int[newSize];
/* 112 */     if (this.namespacePrefix != null) {
/* 113 */       System.arraycopy(this.namespacePrefix, 0, newNamespacePrefix, 0, this.namespaceEnd);
/* 114 */       System.arraycopy(this.namespaceUri, 0, newNamespaceUri, 0, this.namespaceEnd);
/* 115 */       System.arraycopy(this.namespaceDepth, 0, newNamespaceDepth, 0, this.namespaceEnd);
/*     */     }
/* 117 */     this.namespacePrefix = newNamespacePrefix;
/* 118 */     this.namespaceUri = newNamespaceUri;
/* 119 */     this.namespaceDepth = newNamespaceDepth;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPrefix(String prefix, String namespace)
/*     */     throws IOException, IllegalArgumentException, IllegalStateException
/*     */   {
/* 132 */     this.xs.setPrefix(prefix, namespace);
/*     */     
/* 134 */     int depth = getDepth();
/* 135 */     for (int pos = this.namespaceEnd - 1; pos >= 0; pos--) {
/* 136 */       if (this.namespaceDepth[pos] <= depth) {
/*     */         break;
/*     */       }
/* 139 */       this.namespaceEnd -= 1;
/*     */     }
/*     */     
/* 142 */     if (this.namespaceEnd >= this.namespacePrefix.length) {
/* 143 */       ensureNamespacesCapacity();
/*     */     }
/* 145 */     this.namespacePrefix[this.namespaceEnd] = prefix;
/* 146 */     this.namespaceUri[this.namespaceEnd] = namespace;
/* 147 */     this.namespaceEnd += 1;
/*     */   }
/*     */   
/*     */ 
/*     */   public void fragment(String xmlFragment)
/*     */     throws IOException, IllegalArgumentException, IllegalStateException, XmlPullParserException
/*     */   {
/* 154 */     StringBuffer buf = new StringBuffer(xmlFragment.length() + this.namespaceEnd * 30);
/* 155 */     buf.append("<fragment");
/*     */     label140:
/* 157 */     for (int pos = this.namespaceEnd - 1; pos >= 0; pos--) {
/* 158 */       String prefix = this.namespacePrefix[pos];
/* 159 */       for (int i = this.namespaceEnd - 1; i > pos; i--) {
/* 160 */         if (prefix.equals(this.namespacePrefix[i])) {
/*     */           break label140;
/*     */         }
/*     */       }
/* 164 */       buf.append(" xmlns");
/* 165 */       if (prefix.length() > 0) {
/* 166 */         buf.append(':').append(prefix);
/*     */       }
/* 168 */       buf.append("='");
/* 169 */       buf.append(escapeAttributeValue(this.namespaceUri[pos]));
/* 170 */       buf.append("'");
/*     */     }
/*     */     
/* 173 */     buf.append(">");
/* 174 */     buf.append(xmlFragment);
/* 175 */     buf.append("</fragment>");
/*     */     
/* 177 */     if (this.fragmentParser == null) {
/* 178 */       this.fragmentParser = this.wf.newPullParserWrapper();
/*     */     }
/* 180 */     String s = buf.toString();
/*     */     
/* 182 */     this.fragmentParser.setInput(new StringReader(s));
/* 183 */     this.fragmentParser.nextTag();
/* 184 */     this.fragmentParser.require(2, null, "fragment");
/*     */     for (;;) {
/* 186 */       this.fragmentParser.nextToken();
/* 187 */       if ((this.fragmentParser.getDepth() == 1) && (this.fragmentParser.getEventType() == 3)) {
/*     */         break;
/*     */       }
/*     */       
/*     */ 
/* 192 */       event(this.fragmentParser);
/*     */     }
/* 194 */     this.fragmentParser.require(3, null, "fragment");
/*     */   }
/*     */   
/*     */   public void event(XmlPullParser pp) throws XmlPullParserException, IOException {
/* 198 */     int eventType = pp.getEventType();
/* 199 */     switch (eventType)
/*     */     {
/*     */     case 0: 
/* 202 */       Boolean standalone = (Boolean)pp.getProperty("http://xmlpull.org/v1/doc/features.html#xmldecl-standalone");
/* 203 */       startDocument(pp.getInputEncoding(), standalone);
/* 204 */       break;
/*     */     
/*     */     case 1: 
/* 207 */       endDocument();
/* 208 */       break;
/*     */     
/*     */     case 2: 
/* 211 */       writeStartTag(pp);
/* 212 */       break;
/*     */     
/*     */     case 3: 
/* 215 */       endTag(pp.getNamespace(), pp.getName());
/* 216 */       break;
/*     */     
/*     */ 
/*     */     case 7: 
/* 220 */       String s = pp.getText();
/* 221 */       ignorableWhitespace(s);
/* 222 */       break;
/*     */     
/*     */     case 4: 
/* 225 */       if (pp.getDepth() > 0) {
/* 226 */         text(pp.getText());
/*     */       } else {
/* 228 */         ignorableWhitespace(pp.getText());
/*     */       }
/* 230 */       break;
/*     */     
/*     */     case 6: 
/* 233 */       entityRef(pp.getName());
/* 234 */       break;
/*     */     
/*     */     case 5: 
/* 237 */       cdsect(pp.getText());
/* 238 */       break;
/*     */     
/*     */     case 8: 
/* 241 */       processingInstruction(pp.getText());
/* 242 */       break;
/*     */     
/*     */     case 9: 
/* 245 */       comment(pp.getText());
/* 246 */       break;
/*     */     
/*     */     case 10: 
/* 249 */       docdecl(pp.getText());
/*     */     }
/*     */   }
/*     */   
/*     */   private void writeStartTag(XmlPullParser pp) throws XmlPullParserException, IOException
/*     */   {
/* 255 */     if (!pp.getFeature("http://xmlpull.org/v1/doc/features.html#report-namespace-prefixes")) {
/* 256 */       int nsStart = pp.getNamespaceCount(pp.getDepth() - 1);
/* 257 */       int nsEnd = pp.getNamespaceCount(pp.getDepth());
/* 258 */       for (int i = nsStart; i < nsEnd; i++) {
/* 259 */         String prefix = pp.getNamespacePrefix(i);
/* 260 */         String ns = pp.getNamespaceUri(i);
/* 261 */         setPrefix(prefix, ns);
/*     */       }
/*     */     }
/* 264 */     startTag(pp.getNamespace(), pp.getName());
/*     */     
/* 266 */     for (int i = 0; i < pp.getAttributeCount(); i++) {
/* 267 */       attribute(pp.getAttributeNamespace(i), pp.getAttributeName(i), pp.getAttributeValue(i));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String escapeAttributeValue(String value)
/*     */   {
/* 279 */     int posLt = value.indexOf('<');
/* 280 */     int posAmp = value.indexOf('&');
/* 281 */     int posQuot = value.indexOf('"');
/* 282 */     int posApos = value.indexOf('\'');
/* 283 */     if ((posLt == -1) && (posAmp == -1) && (posQuot == -1) && (posApos == -1)) {
/* 284 */       return value;
/*     */     }
/* 286 */     StringBuffer buf = new StringBuffer(value.length() + 10);
/*     */     
/*     */ 
/* 289 */     int pos = 0; for (int len = value.length(); pos < len; pos++) {
/* 290 */       char ch = value.charAt(pos);
/* 291 */       switch (ch) {
/*     */       case '<': 
/* 293 */         buf.append("&lt;");
/* 294 */         break;
/*     */       case '&': 
/* 296 */         buf.append("&amp;");
/* 297 */         break;
/*     */       case '\'': 
/* 299 */         buf.append("&apos;");
/* 300 */         break;
/*     */       case '"': 
/* 302 */         buf.append("&quot;");
/* 303 */         break;
/*     */       default: 
/* 305 */         buf.append(ch);
/*     */       }
/*     */     }
/* 308 */     return buf.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String escapeText(String text)
/*     */   {
/* 316 */     int posLt = text.indexOf('<');
/* 317 */     int posAmp = text.indexOf('&');
/* 318 */     if ((posLt == -1) && (posAmp == -1)) {
/* 319 */       return text;
/*     */     }
/* 321 */     StringBuffer buf = new StringBuffer(text.length() + 10);
/*     */     
/* 323 */     int pos = 0;
/*     */     for (;;) {
/* 325 */       if ((posLt == -1) && (posAmp == -1)) {
/* 326 */         buf.append(text.substring(pos));
/*     */         break label243; }
/* 328 */       if ((posLt == -1) || ((posLt != -1) && (posAmp != -1) && (posAmp < posLt)))
/*     */       {
/*     */ 
/* 331 */         if (pos < posAmp) buf.append(text.substring(pos, posAmp));
/* 332 */         buf.append("&amp;");
/* 333 */         pos = posAmp + 1;
/* 334 */         posAmp = text.indexOf('&', pos);
/* 335 */       } else { if ((posAmp != -1) && ((posLt == -1) || (posAmp == -1) || (posLt >= posAmp))) {
/*     */           break;
/*     */         }
/* 338 */         if (pos < posLt) buf.append(text.substring(pos, posLt));
/* 339 */         buf.append("&lt;");
/* 340 */         pos = posLt + 1;
/* 341 */         posLt = text.indexOf('<', pos);
/*     */       } }
/* 343 */     throw new IllegalStateException("wrong state posLt=" + posLt + " posAmp=" + posAmp + " for " + text);
/*     */     
/*     */     label243:
/*     */     
/* 347 */     return buf.toString();
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeDouble(double d)
/*     */     throws XmlPullParserException, IOException, IllegalArgumentException
/*     */   {
/* 354 */     if (d == Double.POSITIVE_INFINITY) {
/* 355 */       this.xs.text("INF");
/* 356 */     } else if (d == Double.NEGATIVE_INFINITY) {
/* 357 */       this.xs.text("-INF");
/*     */     } else {
/* 359 */       this.xs.text(Double.toString(d));
/*     */     }
/*     */   }
/*     */   
/*     */   public void writeFloat(float f)
/*     */     throws XmlPullParserException, IOException, IllegalArgumentException
/*     */   {
/* 366 */     if (f == Float.POSITIVE_INFINITY) {
/* 367 */       this.xs.text("INF");
/* 368 */     } else if (f == Float.NEGATIVE_INFINITY) {
/* 369 */       this.xs.text("-INF");
/*     */     } else {
/* 371 */       this.xs.text(Float.toString(f));
/*     */     }
/*     */   }
/*     */   
/*     */   public void writeInt(int i)
/*     */     throws XmlPullParserException, IOException, IllegalArgumentException
/*     */   {
/* 378 */     this.xs.text(Integer.toString(i));
/*     */   }
/*     */   
/*     */   public void writeString(String s)
/*     */     throws XmlPullParserException, IOException, IllegalArgumentException
/*     */   {
/* 384 */     if (s == null) {
/* 385 */       throw new IllegalArgumentException("null string can not be written");
/*     */     }
/* 387 */     this.xs.text(s);
/*     */   }
/*     */   
/*     */   public void writeDoubleElement(String namespace, String name, double d)
/*     */     throws XmlPullParserException, IOException, IllegalArgumentException
/*     */   {
/* 393 */     this.xs.startTag(namespace, name);
/* 394 */     writeDouble(d);
/* 395 */     this.xs.endTag(namespace, name);
/*     */   }
/*     */   
/*     */   public void writeFloatElement(String namespace, String name, float f)
/*     */     throws XmlPullParserException, IOException, IllegalArgumentException
/*     */   {
/* 401 */     this.xs.startTag(namespace, name);
/* 402 */     writeFloat(f);
/* 403 */     this.xs.endTag(namespace, name);
/*     */   }
/*     */   
/*     */   public void writeIntElement(String namespace, String name, int i)
/*     */     throws XmlPullParserException, IOException, IllegalArgumentException
/*     */   {
/* 409 */     this.xs.startTag(namespace, name);
/* 410 */     writeInt(i);
/* 411 */     this.xs.endTag(namespace, name);
/*     */   }
/*     */   
/*     */   public void writeStringElement(String namespace, String name, String s)
/*     */     throws XmlPullParserException, IOException, IllegalArgumentException
/*     */   {
/* 417 */     this.xs.startTag(namespace, name);
/* 418 */     if (s == null) {
/* 419 */       this.xs.attribute("http://www.w3.org/2001/XMLSchema", "nil", "true");
/*     */     } else {
/* 421 */       writeString(s);
/*     */     }
/* 423 */     this.xs.endTag(namespace, name);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/xmlpull/v1/wrapper/classic/StaticXmlSerializerWrapper.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */