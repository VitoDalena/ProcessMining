/*     */ package com.thoughtworks.xstream.converters.enums;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.ConversionException;
/*     */ import com.thoughtworks.xstream.converters.Converter;
/*     */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*     */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*     */ import com.thoughtworks.xstream.core.util.Fields;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*     */ import com.thoughtworks.xstream.mapper.Mapper;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Iterator;
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
/*     */ public class EnumSetConverter
/*     */   implements Converter
/*     */ {
/*     */   private static final Field typeField;
/*     */   private final Mapper mapper;
/*     */   
/*     */   static
/*     */   {
/*  45 */     Field assumedTypeField = null;
/*     */     try {
/*  47 */       Field[] fields = EnumSet.class.getDeclaredFields();
/*  48 */       for (int i = 0; i < fields.length; i++) {
/*  49 */         if (fields[i].getType() == Class.class)
/*     */         {
/*  51 */           assumedTypeField = fields[i];
/*  52 */           assumedTypeField.setAccessible(true);
/*  53 */           break;
/*     */         }
/*     */       }
/*  56 */       if (assumedTypeField == null) {
/*  57 */         throw new ExceptionInInitializerError("Cannot detect element type of EnumSet");
/*     */       }
/*     */     }
/*     */     catch (SecurityException ex) {}
/*     */     
/*  62 */     typeField = assumedTypeField;
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumSetConverter(Mapper mapper)
/*     */   {
/*  68 */     this.mapper = mapper;
/*     */   }
/*     */   
/*     */   public boolean canConvert(Class type) {
/*  72 */     return (typeField != null) && (EnumSet.class.isAssignableFrom(type));
/*     */   }
/*     */   
/*     */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/*  76 */     EnumSet set = (EnumSet)source;
/*  77 */     Class enumTypeForSet = (Class)Fields.read(typeField, set);
/*  78 */     String attributeName = this.mapper.aliasForSystemAttribute("enum-type");
/*  79 */     if (attributeName != null) {
/*  80 */       writer.addAttribute(attributeName, this.mapper.serializedClass(enumTypeForSet));
/*     */     }
/*  82 */     writer.setValue(joinEnumValues(set));
/*     */   }
/*     */   
/*     */   private String joinEnumValues(EnumSet set) {
/*  86 */     boolean seenFirst = false;
/*  87 */     StringBuffer result = new StringBuffer();
/*  88 */     for (Iterator iterator = set.iterator(); iterator.hasNext();) {
/*  89 */       Enum value = (Enum)iterator.next();
/*  90 */       if (seenFirst) {
/*  91 */         result.append(',');
/*     */       } else {
/*  93 */         seenFirst = true;
/*     */       }
/*  95 */       result.append(value.name());
/*     */     }
/*  97 */     return result.toString();
/*     */   }
/*     */   
/*     */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context)
/*     */   {
/* 102 */     String attributeName = this.mapper.aliasForSystemAttribute("enum-type");
/* 103 */     if (attributeName == null) {
/* 104 */       throw new ConversionException("No EnumType specified for EnumSet");
/*     */     }
/* 106 */     Class enumTypeForSet = this.mapper.realClass(reader.getAttribute(attributeName));
/* 107 */     EnumSet set = EnumSet.noneOf(enumTypeForSet);
/* 108 */     String[] enumValues = reader.getValue().split(",");
/* 109 */     for (int i = 0; i < enumValues.length; i++) {
/* 110 */       String enumValue = enumValues[i];
/* 111 */       if (enumValue.length() > 0) {
/* 112 */         set.add(Enum.valueOf(enumTypeForSet, enumValue));
/*     */       }
/*     */     }
/* 115 */     return set;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/enums/EnumSetConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */