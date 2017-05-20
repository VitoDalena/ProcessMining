/*     */ package com.thoughtworks.xstream.mapper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AbstractXmlFriendlyMapper
/*     */   extends MapperWrapper
/*     */ {
/*  30 */   private char dollarReplacementInClass = '-';
/*  31 */   private String dollarReplacementInField = "_DOLLAR_";
/*  32 */   private String underscoreReplacementInField = "__";
/*  33 */   private String noPackagePrefix = "default";
/*     */   
/*     */   protected AbstractXmlFriendlyMapper(Mapper wrapped) {
/*  36 */     super(wrapped);
/*     */   }
/*     */   
/*     */   protected String escapeClassName(String className)
/*     */   {
/*  41 */     className = className.replace('$', this.dollarReplacementInClass);
/*     */     
/*     */ 
/*  44 */     if (className.charAt(0) == this.dollarReplacementInClass) {
/*  45 */       className = this.noPackagePrefix + className;
/*     */     }
/*     */     
/*  48 */     return className;
/*     */   }
/*     */   
/*     */   protected String unescapeClassName(String className)
/*     */   {
/*  53 */     if (className.startsWith(this.noPackagePrefix + this.dollarReplacementInClass)) {
/*  54 */       className = className.substring(this.noPackagePrefix.length());
/*     */     }
/*     */     
/*     */ 
/*  58 */     className = className.replace(this.dollarReplacementInClass, '$');
/*     */     
/*  60 */     return className;
/*     */   }
/*     */   
/*     */   protected String escapeFieldName(String fieldName) {
/*  64 */     StringBuffer result = new StringBuffer();
/*  65 */     int length = fieldName.length();
/*  66 */     for (int i = 0; i < length; i++) {
/*  67 */       char c = fieldName.charAt(i);
/*  68 */       if (c == '$') {
/*  69 */         result.append(this.dollarReplacementInField);
/*  70 */       } else if (c == '_') {
/*  71 */         result.append(this.underscoreReplacementInField);
/*     */       } else {
/*  73 */         result.append(c);
/*     */       }
/*     */     }
/*  76 */     return result.toString();
/*     */   }
/*     */   
/*     */   protected String unescapeFieldName(String xmlName) {
/*  80 */     StringBuffer result = new StringBuffer();
/*  81 */     int length = xmlName.length();
/*  82 */     for (int i = 0; i < length; i++) {
/*  83 */       char c = xmlName.charAt(i);
/*  84 */       if (stringFoundAt(xmlName, i, this.underscoreReplacementInField)) {
/*  85 */         i += this.underscoreReplacementInField.length() - 1;
/*  86 */         result.append('_');
/*  87 */       } else if (stringFoundAt(xmlName, i, this.dollarReplacementInField)) {
/*  88 */         i += this.dollarReplacementInField.length() - 1;
/*  89 */         result.append('$');
/*     */       } else {
/*  91 */         result.append(c);
/*     */       }
/*     */     }
/*  94 */     return result.toString();
/*     */   }
/*     */   
/*     */   private boolean stringFoundAt(String name, int i, String replacement) {
/*  98 */     if ((name.length() >= i + replacement.length()) && (name.substring(i, i + replacement.length()).equals(replacement)))
/*     */     {
/* 100 */       return true;
/*     */     }
/* 102 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/mapper/AbstractXmlFriendlyMapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */