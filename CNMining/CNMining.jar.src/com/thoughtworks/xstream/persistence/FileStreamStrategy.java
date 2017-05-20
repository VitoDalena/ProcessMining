/*     */ package com.thoughtworks.xstream.persistence;
/*     */ 
/*     */ import com.thoughtworks.xstream.XStream;
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
/*     */ /**
/*     */  * @deprecated
/*     */  */
/*     */ public class FileStreamStrategy
/*     */   extends AbstractFilePersistenceStrategy
/*     */   implements StreamStrategy
/*     */ {
/*     */   public FileStreamStrategy(File baseDirectory)
/*     */   {
/*  33 */     this(baseDirectory, new XStream());
/*     */   }
/*     */   
/*     */   public FileStreamStrategy(File baseDirectory, XStream xstream) {
/*  37 */     super(baseDirectory, xstream, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Object extractKey(String name)
/*     */   {
/*  47 */     String key = unescape(name.substring(0, name.length() - 4));
/*  48 */     return key.equals("\000") ? null : key;
/*     */   }
/*     */   
/*     */   protected String unescape(String name) {
/*  52 */     StringBuffer buffer = new StringBuffer();
/*  53 */     char lastC = 65535;
/*  54 */     int currentValue = -1;
/*     */     
/*  56 */     char[] array = name.toCharArray();
/*  57 */     for (int i = 0; i < array.length; i++) {
/*  58 */       char c = array[i];
/*  59 */       if ((c == '_') && (currentValue != -1)) {
/*  60 */         if (lastC == '_') {
/*  61 */           buffer.append('_');
/*     */         } else {
/*  63 */           buffer.append((char)currentValue);
/*     */         }
/*  65 */         currentValue = -1;
/*  66 */       } else if (c == '_') {
/*  67 */         currentValue = 0;
/*  68 */       } else if (currentValue != -1) {
/*  69 */         currentValue = currentValue * 16 + Integer.parseInt(String.valueOf(c), 16);
/*     */       } else {
/*  71 */         buffer.append(c);
/*     */       }
/*  73 */       lastC = c;
/*     */     }
/*  75 */     return buffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String getName(Object key)
/*     */   {
/*  85 */     return escape(key == null ? "\000" : key.toString()) + ".xml";
/*     */   }
/*     */   
/*     */   protected String escape(String key)
/*     */   {
/*  90 */     StringBuffer buffer = new StringBuffer();
/*  91 */     char[] array = key.toCharArray();
/*  92 */     for (int i = 0; i < array.length; i++) {
/*  93 */       char c = array[i];
/*  94 */       if ((Character.isDigit(c)) || ((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z'))) {
/*  95 */         buffer.append(c);
/*  96 */       } else if (c == '_') {
/*  97 */         buffer.append("__");
/*     */       } else {
/*  99 */         buffer.append("_" + Integer.toHexString(c) + "_");
/*     */       }
/*     */     }
/* 102 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/persistence/FileStreamStrategy.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */