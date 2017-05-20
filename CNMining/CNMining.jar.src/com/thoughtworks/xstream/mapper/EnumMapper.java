/*     */ package com.thoughtworks.xstream.mapper;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.ConverterLookup;
/*     */ import com.thoughtworks.xstream.converters.SingleValueConverter;
/*     */ import com.thoughtworks.xstream.converters.enums.EnumSingleValueConverter;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Map;
/*     */ import java.util.WeakHashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EnumMapper
/*     */   extends MapperWrapper
/*     */ {
/*     */   private transient AttributeMapper attributeMapper;
/*     */   private transient Map<Class, SingleValueConverter> enumConverterMap;
/*     */   
/*     */   @Deprecated
/*     */   public EnumMapper(Mapper wrapped, ConverterLookup lookup)
/*     */   {
/*  42 */     super(wrapped);
/*  43 */     readResolve();
/*     */   }
/*     */   
/*     */   public EnumMapper(Mapper wrapped) {
/*  47 */     super(wrapped);
/*  48 */     readResolve();
/*     */   }
/*     */   
/*     */   public String serializedClass(Class type)
/*     */   {
/*  53 */     if (type == null) {
/*  54 */       return super.serializedClass(type);
/*     */     }
/*  56 */     if ((Enum.class.isAssignableFrom(type)) && (type.getSuperclass() != Enum.class))
/*  57 */       return super.serializedClass(type.getSuperclass());
/*  58 */     if (EnumSet.class.isAssignableFrom(type)) {
/*  59 */       return super.serializedClass(EnumSet.class);
/*     */     }
/*  61 */     return super.serializedClass(type);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isImmutableValueType(Class type)
/*     */   {
/*  67 */     return (Enum.class.isAssignableFrom(type)) || (super.isImmutableValueType(type));
/*     */   }
/*     */   
/*     */ 
/*     */   public SingleValueConverter getConverterFromItemType(String fieldName, Class type, Class definedIn)
/*     */   {
/*  73 */     SingleValueConverter converter = getLocalConverter(fieldName, type, definedIn);
/*  74 */     return converter == null ? super.getConverterFromItemType(fieldName, type, definedIn) : converter;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SingleValueConverter getConverterFromAttribute(Class definedIn, String attribute, Class type)
/*     */   {
/*  82 */     SingleValueConverter converter = getLocalConverter(attribute, type, definedIn);
/*  83 */     return converter == null ? super.getConverterFromAttribute(definedIn, attribute, type) : converter;
/*     */   }
/*     */   
/*     */ 
/*     */   private SingleValueConverter getLocalConverter(String fieldName, Class type, Class definedIn)
/*     */   {
/*  89 */     if ((this.attributeMapper != null) && (Enum.class.isAssignableFrom(type)) && (this.attributeMapper.shouldLookForSingleValueConverter(fieldName, type, definedIn)))
/*     */     {
/*     */ 
/*  92 */       synchronized (this.enumConverterMap) {
/*  93 */         SingleValueConverter singleValueConverter = (SingleValueConverter)this.enumConverterMap.get(type);
/*  94 */         if (singleValueConverter == null) {
/*  95 */           singleValueConverter = super.getConverterFromItemType(fieldName, type, definedIn);
/*  96 */           if (singleValueConverter == null) {
/*  97 */             singleValueConverter = new EnumSingleValueConverter(type);
/*     */           }
/*  99 */           this.enumConverterMap.put(type, singleValueConverter);
/*     */         }
/* 101 */         return singleValueConverter;
/*     */       }
/*     */     }
/* 104 */     return null;
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 108 */     this.enumConverterMap = new WeakHashMap();
/* 109 */     this.attributeMapper = ((AttributeMapper)lookupMapperOfType(AttributeMapper.class));
/* 110 */     return this;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/mapper/EnumMapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */