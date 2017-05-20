/*     */ package com.thoughtworks.xstream.mapper;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.Converter;
/*     */ import com.thoughtworks.xstream.converters.SingleValueConverter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultMapper
/*     */   implements Mapper
/*     */ {
/*     */   private final ClassLoader classLoader;
/*     */   
/*     */   public DefaultMapper(ClassLoader classLoader)
/*     */   {
/*  30 */     this.classLoader = classLoader;
/*     */   }
/*     */   
/*     */   public String serializedClass(Class type) {
/*  34 */     return type.getName();
/*     */   }
/*     */   
/*     */   public Class realClass(String elementName) {
/*     */     try {
/*  39 */       if (elementName.charAt(0) != '[')
/*  40 */         return this.classLoader.loadClass(elementName);
/*  41 */       if (elementName.endsWith(";")) {
/*  42 */         return Class.forName(elementName.toString(), false, this.classLoader);
/*     */       }
/*  44 */       return Class.forName(elementName.toString());
/*     */     }
/*     */     catch (ClassNotFoundException e) {
/*  47 */       throw new CannotResolveClassException(elementName);
/*     */     }
/*     */   }
/*     */   
/*     */   public Class defaultImplementationOf(Class type) {
/*  52 */     return type;
/*     */   }
/*     */   
/*     */   public String aliasForAttribute(String attribute) {
/*  56 */     return attribute;
/*     */   }
/*     */   
/*     */   public String attributeForAlias(String alias) {
/*  60 */     return alias;
/*     */   }
/*     */   
/*     */   public String aliasForSystemAttribute(String attribute) {
/*  64 */     return attribute;
/*     */   }
/*     */   
/*     */   public boolean isImmutableValueType(Class type) {
/*  68 */     return false;
/*     */   }
/*     */   
/*     */   public String getFieldNameForItemTypeAndName(Class definedIn, Class itemType, String itemFieldName) {
/*  72 */     return null;
/*     */   }
/*     */   
/*     */   public Class getItemTypeForItemFieldName(Class definedIn, String itemFieldName) {
/*  76 */     return null;
/*     */   }
/*     */   
/*     */   public Mapper.ImplicitCollectionMapping getImplicitCollectionDefForFieldName(Class itemType, String fieldName) {
/*  80 */     return null;
/*     */   }
/*     */   
/*     */   public boolean shouldSerializeMember(Class definedIn, String fieldName) {
/*  84 */     return true;
/*     */   }
/*     */   
/*     */   public String lookupName(Class type) {
/*  88 */     return serializedClass(type);
/*     */   }
/*     */   
/*     */   public Class lookupType(String elementName) {
/*  92 */     return realClass(elementName);
/*     */   }
/*     */   
/*     */   public String serializedMember(Class type, String memberName) {
/*  96 */     return memberName;
/*     */   }
/*     */   
/*     */   public String realMember(Class type, String serialized) {
/* 100 */     return serialized;
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public SingleValueConverter getConverterFromAttribute(String name) {
/* 107 */     return null;
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public SingleValueConverter getConverterFromItemType(String fieldName, Class type) {
/* 114 */     return null;
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public SingleValueConverter getConverterFromItemType(Class type) {
/* 121 */     return null;
/*     */   }
/*     */   
/*     */   public SingleValueConverter getConverterFromItemType(String fieldName, Class type, Class definedIn)
/*     */   {
/* 126 */     return null;
/*     */   }
/*     */   
/*     */   public Converter getLocalConverter(Class definedIn, String fieldName) {
/* 130 */     return null;
/*     */   }
/*     */   
/*     */   public Mapper lookupMapperOfType(Class type) {
/* 134 */     return null;
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public String aliasForAttribute(Class definedIn, String fieldName) {
/* 141 */     return fieldName;
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public String attributeForAlias(Class definedIn, String alias) {
/* 148 */     return alias;
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public SingleValueConverter getConverterFromAttribute(Class definedIn, String attribute) {
/* 155 */     return null;
/*     */   }
/*     */   
/*     */   public SingleValueConverter getConverterFromAttribute(Class definedIn, String attribute, Class type) {
/* 159 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/mapper/DefaultMapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */