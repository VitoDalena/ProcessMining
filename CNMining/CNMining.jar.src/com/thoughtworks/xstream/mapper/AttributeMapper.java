/*     */ package com.thoughtworks.xstream.mapper;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.Converter;
/*     */ import com.thoughtworks.xstream.converters.ConverterLookup;
/*     */ import com.thoughtworks.xstream.converters.SingleValueConverter;
/*     */ import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AttributeMapper
/*     */   extends MapperWrapper
/*     */ {
/*  39 */   private final Map fieldNameToTypeMap = new HashMap();
/*  40 */   private final Set typeSet = new HashSet();
/*     */   private ConverterLookup converterLookup;
/*     */   private ReflectionProvider reflectionProvider;
/*  43 */   private final Set fieldToUseAsAttribute = new HashSet();
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public AttributeMapper(Mapper wrapped) {
/*  49 */     this(wrapped, null, null);
/*     */   }
/*     */   
/*     */   public AttributeMapper(Mapper wrapped, ConverterLookup converterLookup, ReflectionProvider refProvider) {
/*  53 */     super(wrapped);
/*  54 */     this.converterLookup = converterLookup;
/*  55 */     this.reflectionProvider = refProvider;
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public void setConverterLookup(ConverterLookup converterLookup) {
/*  62 */     this.converterLookup = converterLookup;
/*     */   }
/*     */   
/*     */   public void addAttributeFor(String fieldName, Class type) {
/*  66 */     this.fieldNameToTypeMap.put(fieldName, type);
/*     */   }
/*     */   
/*     */   public void addAttributeFor(Class type) {
/*  70 */     this.typeSet.add(type);
/*     */   }
/*     */   
/*     */   private SingleValueConverter getLocalConverterFromItemType(Class type) {
/*  74 */     Converter converter = this.converterLookup.lookupConverterForType(type);
/*  75 */     if ((converter != null) && ((converter instanceof SingleValueConverter))) {
/*  76 */       return (SingleValueConverter)converter;
/*     */     }
/*  78 */     return null;
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public SingleValueConverter getConverterFromItemType(String fieldName, Class type)
/*     */   {
/*  86 */     if (this.fieldNameToTypeMap.get(fieldName) == type) {
/*  87 */       return getLocalConverterFromItemType(type);
/*     */     }
/*  89 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public SingleValueConverter getConverterFromItemType(String fieldName, Class type, Class definedIn)
/*     */   {
/*  95 */     if (shouldLookForSingleValueConverter(fieldName, type, definedIn)) {
/*  96 */       SingleValueConverter converter = getLocalConverterFromItemType(type);
/*  97 */       if (converter != null) {
/*  98 */         return converter;
/*     */       }
/*     */     }
/* 101 */     return super.getConverterFromItemType(fieldName, type, definedIn);
/*     */   }
/*     */   
/*     */   public boolean shouldLookForSingleValueConverter(String fieldName, Class type, Class definedIn) {
/* 105 */     Field field = this.reflectionProvider.getField(definedIn, fieldName);
/* 106 */     return (this.fieldToUseAsAttribute.contains(field)) || (this.fieldNameToTypeMap.get(fieldName) == type) || (this.typeSet.contains(type));
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public SingleValueConverter getConverterFromItemType(Class type) {
/* 113 */     if (this.typeSet.contains(type)) {
/* 114 */       return getLocalConverterFromItemType(type);
/*     */     }
/* 116 */     return null;
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public SingleValueConverter getConverterFromAttribute(String attributeName)
/*     */   {
/* 124 */     SingleValueConverter converter = null;
/* 125 */     Class type = (Class)this.fieldNameToTypeMap.get(attributeName);
/* 126 */     if (type != null) {
/* 127 */       converter = getLocalConverterFromItemType(type);
/*     */     }
/* 129 */     return converter;
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public SingleValueConverter getConverterFromAttribute(Class definedIn, String attribute) {
/* 136 */     Field field = this.reflectionProvider.getField(definedIn, attribute);
/* 137 */     return getConverterFromAttribute(definedIn, attribute, field.getType());
/*     */   }
/*     */   
/*     */   public SingleValueConverter getConverterFromAttribute(Class definedIn, String attribute, Class type) {
/* 141 */     if (shouldLookForSingleValueConverter(attribute, type, definedIn)) {
/* 142 */       SingleValueConverter converter = getLocalConverterFromItemType(type);
/* 143 */       if (converter != null) {
/* 144 */         return converter;
/*     */       }
/*     */     }
/* 147 */     return super.getConverterFromAttribute(definedIn, attribute, type);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addAttributeFor(Field field)
/*     */   {
/* 157 */     this.fieldToUseAsAttribute.add(field);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addAttributeFor(Class definedIn, String fieldName)
/*     */   {
/* 169 */     this.fieldToUseAsAttribute.add(this.reflectionProvider.getField(definedIn, fieldName));
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/mapper/AttributeMapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */