/*     */ package com.thoughtworks.xstream.persistence;
/*     */ 
/*     */ import com.thoughtworks.xstream.XStream;
/*     */ import com.thoughtworks.xstream.converters.Converter;
/*     */ import com.thoughtworks.xstream.converters.ConverterLookup;
/*     */ import com.thoughtworks.xstream.converters.SingleValueConverter;
/*     */ import com.thoughtworks.xstream.io.StreamException;
/*     */ import com.thoughtworks.xstream.io.xml.DomDriver;
/*     */ import com.thoughtworks.xstream.mapper.Mapper;
/*     */ import java.io.File;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FilePersistenceStrategy
/*     */   extends AbstractFilePersistenceStrategy
/*     */ {
/*     */   private final String illegalChars;
/*     */   
/*     */   public FilePersistenceStrategy(File baseDirectory)
/*     */   {
/*  46 */     this(baseDirectory, new XStream(new DomDriver()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FilePersistenceStrategy(File baseDirectory, XStream xstream)
/*     */   {
/*  57 */     this(baseDirectory, xstream, "utf-8", "<>?:/\\\"|*%");
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
/*     */   public FilePersistenceStrategy(File baseDirectory, XStream xstream, String encoding, String illegalChars)
/*     */   {
/*  74 */     super(baseDirectory, xstream, encoding);
/*  75 */     this.illegalChars = illegalChars;
/*     */   }
/*     */   
/*     */   protected boolean isValid(File dir, String name) {
/*  79 */     return (super.isValid(dir, name)) && (name.indexOf('@') > 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Object extractKey(String name)
/*     */   {
/*  89 */     String key = unescape(name.substring(0, name.length() - 4));
/*  90 */     if ("null@null".equals(key)) {
/*  91 */       return null;
/*     */     }
/*  93 */     int idx = key.indexOf('@');
/*  94 */     if (idx < 0) {
/*  95 */       throw new StreamException("Not a valid key: " + key);
/*     */     }
/*  97 */     Class type = getMapper().realClass(key.substring(0, idx));
/*  98 */     Converter converter = getConverterLookup().lookupConverterForType(type);
/*  99 */     if ((converter instanceof SingleValueConverter)) {
/* 100 */       SingleValueConverter svConverter = (SingleValueConverter)converter;
/* 101 */       return svConverter.fromString(key.substring(idx + 1));
/*     */     }
/* 103 */     throw new StreamException("No SingleValueConverter for type " + type.getName() + " available");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected String unescape(String name)
/*     */   {
/* 110 */     StringBuffer buffer = new StringBuffer();
/* 111 */     for (int idx = name.indexOf('%'); idx >= 0; idx = name.indexOf('%')) {
/* 112 */       buffer.append(name.substring(0, idx));
/* 113 */       int c = Integer.parseInt(name.substring(idx + 1, idx + 3), 16);
/* 114 */       buffer.append((char)c);
/* 115 */       name = name.substring(idx + 3);
/*     */     }
/* 117 */     buffer.append(name);
/* 118 */     return buffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String getName(Object key)
/*     */   {
/* 128 */     if (key == null) {
/* 129 */       return "null@null.xml";
/*     */     }
/* 131 */     Class type = key.getClass();
/* 132 */     Converter converter = getConverterLookup().lookupConverterForType(type);
/* 133 */     if ((converter instanceof SingleValueConverter)) {
/* 134 */       SingleValueConverter svConverter = (SingleValueConverter)converter;
/* 135 */       return getMapper().serializedClass(type) + '@' + escape(svConverter.toString(key)) + ".xml";
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 140 */     throw new StreamException("No SingleValueConverter for type " + type.getName() + " available");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected String escape(String key)
/*     */   {
/* 147 */     StringBuffer buffer = new StringBuffer();
/* 148 */     char[] array = key.toCharArray();
/* 149 */     for (int i = 0; i < array.length; i++) {
/* 150 */       char c = array[i];
/* 151 */       if ((c >= ' ') && (this.illegalChars.indexOf(c) < 0)) {
/* 152 */         buffer.append(c);
/*     */       } else {
/* 154 */         buffer.append("%" + Integer.toHexString(c).toUpperCase());
/*     */       }
/*     */     }
/* 157 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/persistence/FilePersistenceStrategy.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */