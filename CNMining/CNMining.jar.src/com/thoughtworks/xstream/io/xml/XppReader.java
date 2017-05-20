/*     */ package com.thoughtworks.xstream.io.xml;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.ErrorWriter;
/*     */ import com.thoughtworks.xstream.io.StreamException;
/*     */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import org.xmlpull.v1.XmlPullParser;
/*     */ import org.xmlpull.v1.XmlPullParserException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XppReader
/*     */   extends AbstractPullReader
/*     */ {
/*     */   private final XmlPullParser parser;
/*     */   private final Reader reader;
/*     */   
/*     */   public XppReader(Reader reader, XmlPullParser parser)
/*     */   {
/*  43 */     this(reader, parser, new XmlFriendlyNameCoder());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XppReader(Reader reader, XmlPullParser parser, NameCoder nameCoder)
/*     */   {
/*  55 */     super(nameCoder);
/*  56 */     this.parser = parser;
/*  57 */     this.reader = reader;
/*     */     try {
/*  59 */       parser.setInput(this.reader);
/*     */     } catch (XmlPullParserException e) {
/*  61 */       throw new StreamException(e);
/*     */     }
/*  63 */     moveDown();
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public XppReader(Reader reader) {
/*  70 */     this(reader, new XmlFriendlyReplacer());
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public XppReader(Reader reader, XmlFriendlyReplacer replacer)
/*     */   {
/*  78 */     super(replacer);
/*     */     try {
/*  80 */       this.parser = createParser();
/*  81 */       this.reader = reader;
/*  82 */       this.parser.setInput(this.reader);
/*  83 */       moveDown();
/*     */     } catch (XmlPullParserException e) {
/*  85 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   protected XmlPullParser createParser()
/*     */   {
/*  94 */     Exception exception = null;
/*     */     try {
/*  96 */       return (XmlPullParser)Class.forName("org.xmlpull.mxp1.MXParser", true, XmlPullParser.class.getClassLoader()).newInstance();
/*     */     } catch (InstantiationException e) {
/*  98 */       exception = e;
/*     */     } catch (IllegalAccessException e) {
/* 100 */       exception = e;
/*     */     } catch (ClassNotFoundException e) {
/* 102 */       exception = e;
/*     */     }
/* 104 */     throw new StreamException("Cannot create Xpp3 parser instance.", exception);
/*     */   }
/*     */   
/*     */   protected int pullNextEvent() {
/*     */     try {
/* 109 */       switch (this.parser.next()) {
/*     */       case 0: 
/*     */       case 2: 
/* 112 */         return 1;
/*     */       case 1: 
/*     */       case 3: 
/* 115 */         return 2;
/*     */       case 4: 
/* 117 */         return 3;
/*     */       case 9: 
/* 119 */         return 4;
/*     */       }
/* 121 */       return 0;
/*     */     }
/*     */     catch (XmlPullParserException e) {
/* 124 */       throw new StreamException(e);
/*     */     } catch (IOException e) {
/* 126 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   protected String pullElementName() {
/* 131 */     return this.parser.getName();
/*     */   }
/*     */   
/*     */   protected String pullText() {
/* 135 */     return this.parser.getText();
/*     */   }
/*     */   
/*     */   public String getAttribute(String name) {
/* 139 */     return this.parser.getAttributeValue(null, encodeAttribute(name));
/*     */   }
/*     */   
/*     */   public String getAttribute(int index) {
/* 143 */     return this.parser.getAttributeValue(index);
/*     */   }
/*     */   
/*     */   public int getAttributeCount() {
/* 147 */     return this.parser.getAttributeCount();
/*     */   }
/*     */   
/*     */   public String getAttributeName(int index) {
/* 151 */     return decodeAttribute(this.parser.getAttributeName(index));
/*     */   }
/*     */   
/*     */   public void appendErrors(ErrorWriter errorWriter) {
/* 155 */     errorWriter.add("line number", String.valueOf(this.parser.getLineNumber()));
/*     */   }
/*     */   
/*     */   public void close() {
/*     */     try {
/* 160 */       this.reader.close();
/*     */     } catch (IOException e) {
/* 162 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/XppReader.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */