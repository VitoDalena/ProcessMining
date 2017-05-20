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
/*     */ public abstract class MapperWrapper
/*     */   implements Mapper
/*     */ {
/*     */   private final Mapper wrapped;
/*     */   
/*     */   public MapperWrapper(Mapper wrapped)
/*     */   {
/*  22 */     this.wrapped = wrapped;
/*     */   }
/*     */   
/*     */   public String serializedClass(Class type) {
/*  26 */     return this.wrapped.serializedClass(type);
/*     */   }
/*     */   
/*     */   public Class realClass(String elementName) {
/*  30 */     return this.wrapped.realClass(elementName);
/*     */   }
/*     */   
/*     */   public String serializedMember(Class type, String memberName) {
/*  34 */     return this.wrapped.serializedMember(type, memberName);
/*     */   }
/*     */   
/*     */   public String realMember(Class type, String serialized) {
/*  38 */     return this.wrapped.realMember(type, serialized);
/*     */   }
/*     */   
/*     */   public boolean isImmutableValueType(Class type) {
/*  42 */     return this.wrapped.isImmutableValueType(type);
/*     */   }
/*     */   
/*     */   public Class defaultImplementationOf(Class type) {
/*  46 */     return this.wrapped.defaultImplementationOf(type);
/*     */   }
/*     */   
/*     */   public String aliasForAttribute(String attribute) {
/*  50 */     return this.wrapped.aliasForAttribute(attribute);
/*     */   }
/*     */   
/*     */   public String attributeForAlias(String alias) {
/*  54 */     return this.wrapped.attributeForAlias(alias);
/*     */   }
/*     */   
/*     */   public String aliasForSystemAttribute(String attribute) {
/*  58 */     return this.wrapped.aliasForSystemAttribute(attribute);
/*     */   }
/*     */   
/*     */   public String getFieldNameForItemTypeAndName(Class definedIn, Class itemType, String itemFieldName) {
/*  62 */     return this.wrapped.getFieldNameForItemTypeAndName(definedIn, itemType, itemFieldName);
/*     */   }
/*     */   
/*     */   public Class getItemTypeForItemFieldName(Class definedIn, String itemFieldName) {
/*  66 */     return this.wrapped.getItemTypeForItemFieldName(definedIn, itemFieldName);
/*     */   }
/*     */   
/*     */   public Mapper.ImplicitCollectionMapping getImplicitCollectionDefForFieldName(Class itemType, String fieldName) {
/*  70 */     return this.wrapped.getImplicitCollectionDefForFieldName(itemType, fieldName);
/*     */   }
/*     */   
/*     */   public boolean shouldSerializeMember(Class definedIn, String fieldName) {
/*  74 */     return this.wrapped.shouldSerializeMember(definedIn, fieldName);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public SingleValueConverter getConverterFromItemType(String fieldName, Class type) {
/*  81 */     return this.wrapped.getConverterFromItemType(fieldName, type);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public SingleValueConverter getConverterFromItemType(Class type) {
/*  88 */     return this.wrapped.getConverterFromItemType(type);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public SingleValueConverter getConverterFromAttribute(String name) {
/*  95 */     return this.wrapped.getConverterFromAttribute(name);
/*     */   }
/*     */   
/*     */   public Converter getLocalConverter(Class definedIn, String fieldName) {
/*  99 */     return this.wrapped.getLocalConverter(definedIn, fieldName);
/*     */   }
/*     */   
/*     */   public Mapper lookupMapperOfType(Class type) {
/* 103 */     return type.isAssignableFrom(getClass()) ? this : this.wrapped.lookupMapperOfType(type);
/*     */   }
/*     */   
/*     */   public SingleValueConverter getConverterFromItemType(String fieldName, Class type, Class definedIn) {
/* 107 */     return this.wrapped.getConverterFromItemType(fieldName, type, definedIn);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public String aliasForAttribute(Class definedIn, String fieldName) {
/* 114 */     return this.wrapped.aliasForAttribute(definedIn, fieldName);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public String attributeForAlias(Class definedIn, String alias) {
/* 121 */     return this.wrapped.attributeForAlias(definedIn, alias);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public SingleValueConverter getConverterFromAttribute(Class type, String attribute) {
/* 128 */     return this.wrapped.getConverterFromAttribute(type, attribute);
/*     */   }
/*     */   
/*     */   public SingleValueConverter getConverterFromAttribute(Class definedIn, String attribute, Class type) {
/* 132 */     return this.wrapped.getConverterFromAttribute(definedIn, attribute, type);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/mapper/MapperWrapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */