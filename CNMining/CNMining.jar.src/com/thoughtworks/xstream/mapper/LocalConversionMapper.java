/*    */ package com.thoughtworks.xstream.mapper;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.Converter;
/*    */ import com.thoughtworks.xstream.converters.SingleValueConverter;
/*    */ import com.thoughtworks.xstream.core.util.FastField;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LocalConversionMapper
/*    */   extends MapperWrapper
/*    */ {
/* 29 */   private final Map localConverters = new HashMap();
/*    */   
/*    */ 
/*    */ 
/*    */   private transient AttributeMapper attributeMapper;
/*    */   
/*    */ 
/*    */ 
/*    */   public LocalConversionMapper(Mapper wrapped)
/*    */   {
/* 39 */     super(wrapped);
/* 40 */     readResolve();
/*    */   }
/*    */   
/*    */   public void registerLocalConverter(Class definedIn, String fieldName, Converter converter) {
/* 44 */     this.localConverters.put(new FastField(definedIn, fieldName), converter);
/*    */   }
/*    */   
/*    */   public Converter getLocalConverter(Class definedIn, String fieldName) {
/* 48 */     return (Converter)this.localConverters.get(new FastField(definedIn, fieldName));
/*    */   }
/*    */   
/*    */   public SingleValueConverter getConverterFromAttribute(Class definedIn, String attribute, Class type)
/*    */   {
/* 53 */     SingleValueConverter converter = getLocalSingleValueConverter(definedIn, attribute, type);
/*    */     
/* 55 */     return converter == null ? super.getConverterFromAttribute(definedIn, attribute, type) : converter;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public SingleValueConverter getConverterFromItemType(String fieldName, Class type, Class definedIn)
/*    */   {
/* 62 */     SingleValueConverter converter = getLocalSingleValueConverter(definedIn, fieldName, type);
/*    */     
/* 64 */     return converter == null ? super.getConverterFromItemType(fieldName, type, definedIn) : converter;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   private SingleValueConverter getLocalSingleValueConverter(Class definedIn, String fieldName, Class type)
/*    */   {
/* 71 */     if ((this.attributeMapper != null) && (this.attributeMapper.shouldLookForSingleValueConverter(fieldName, type, definedIn)))
/*    */     {
/* 73 */       Converter converter = getLocalConverter(definedIn, fieldName);
/* 74 */       if ((converter != null) && ((converter instanceof SingleValueConverter))) {
/* 75 */         return (SingleValueConverter)converter;
/*    */       }
/*    */     }
/* 78 */     return null;
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 82 */     this.attributeMapper = ((AttributeMapper)lookupMapperOfType(AttributeMapper.class));
/* 83 */     return this;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/mapper/LocalConversionMapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */