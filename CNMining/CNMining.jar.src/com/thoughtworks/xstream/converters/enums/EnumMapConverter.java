/*    */ package com.thoughtworks.xstream.converters.enums;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.ConversionException;
/*    */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*    */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*    */ import com.thoughtworks.xstream.converters.collections.MapConverter;
/*    */ import com.thoughtworks.xstream.core.util.Fields;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import com.thoughtworks.xstream.mapper.Mapper;
/*    */ import java.lang.reflect.Field;
/*    */ import java.util.EnumMap;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnumMapConverter
/*    */   extends MapConverter
/*    */ {
/*    */   private static final Field typeField;
/*    */   
/*    */   static
/*    */   {
/* 43 */     Field assumedTypeField = null;
/*    */     try {
/* 45 */       Field[] fields = EnumMap.class.getDeclaredFields();
/* 46 */       for (int i = 0; i < fields.length; i++) {
/* 47 */         if (fields[i].getType() == Class.class)
/*    */         {
/* 49 */           assumedTypeField = fields[i];
/* 50 */           assumedTypeField.setAccessible(true);
/* 51 */           break;
/*    */         }
/*    */       }
/* 54 */       if (assumedTypeField == null) {
/* 55 */         throw new ExceptionInInitializerError("Cannot detect key type of EnumMap");
/*    */       }
/*    */     }
/*    */     catch (SecurityException ex) {}
/*    */     
/*    */ 
/* 61 */     typeField = assumedTypeField;
/*    */   }
/*    */   
/*    */   public EnumMapConverter(Mapper mapper) {
/* 65 */     super(mapper);
/*    */   }
/*    */   
/*    */   public boolean canConvert(Class type) {
/* 69 */     return (typeField != null) && (type == EnumMap.class);
/*    */   }
/*    */   
/*    */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/* 73 */     Class type = (Class)Fields.read(typeField, source);
/* 74 */     String attributeName = mapper().aliasForSystemAttribute("enum-type");
/* 75 */     if (attributeName != null) {
/* 76 */       writer.addAttribute(attributeName, mapper().serializedClass(type));
/*    */     }
/* 78 */     super.marshal(source, writer, context);
/*    */   }
/*    */   
/*    */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context)
/*    */   {
/* 83 */     String attributeName = mapper().aliasForSystemAttribute("enum-type");
/* 84 */     if (attributeName == null) {
/* 85 */       throw new ConversionException("No EnumType specified for EnumMap");
/*    */     }
/* 87 */     Class type = mapper().realClass(reader.getAttribute(attributeName));
/* 88 */     EnumMap map = new EnumMap(type);
/* 89 */     populateMap(reader, context, map);
/* 90 */     return map;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/enums/EnumMapConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */