/*     */ package com.thoughtworks.xstream.mapper;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
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
/*     */ public class ArrayMapper
/*     */   extends MapperWrapper
/*     */ {
/*  25 */   private static final Collection BOXED_TYPES = Arrays.asList(new Class[] { Boolean.class, Byte.class, Character.class, Short.class, Integer.class, Long.class, Float.class, Double.class });
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
/*     */   public ArrayMapper(Mapper wrapped)
/*     */   {
/*  38 */     super(wrapped);
/*     */   }
/*     */   
/*     */   public String serializedClass(Class type) {
/*  42 */     StringBuffer arraySuffix = new StringBuffer();
/*  43 */     String name = null;
/*  44 */     while (type.isArray()) {
/*  45 */       name = super.serializedClass(type);
/*  46 */       if (!type.getName().equals(name)) break;
/*  47 */       type = type.getComponentType();
/*  48 */       arraySuffix.append("-array");
/*  49 */       name = null;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  54 */     if (name == null) {
/*  55 */       name = boxedTypeName(type);
/*     */     }
/*  57 */     if (name == null) {
/*  58 */       name = super.serializedClass(type);
/*     */     }
/*  60 */     if (arraySuffix.length() > 0) {
/*  61 */       return name + arraySuffix;
/*     */     }
/*  63 */     return name;
/*     */   }
/*     */   
/*     */   public Class realClass(String elementName)
/*     */   {
/*  68 */     int dimensions = 0;
/*     */     
/*     */ 
/*  71 */     while (elementName.endsWith("-array")) {
/*  72 */       elementName = elementName.substring(0, elementName.length() - 6);
/*  73 */       dimensions++;
/*     */     }
/*     */     
/*  76 */     if (dimensions > 0) {
/*  77 */       Class componentType = primitiveClassNamed(elementName);
/*  78 */       if (componentType == null) {
/*  79 */         componentType = super.realClass(elementName);
/*     */       }
/*  81 */       while (componentType.isArray()) {
/*  82 */         componentType = componentType.getComponentType();
/*  83 */         dimensions++;
/*     */       }
/*  85 */       return super.realClass(arrayType(dimensions, componentType));
/*     */     }
/*  87 */     return super.realClass(elementName);
/*     */   }
/*     */   
/*     */   private String arrayType(int dimensions, Class componentType)
/*     */   {
/*  92 */     StringBuffer className = new StringBuffer();
/*  93 */     for (int i = 0; i < dimensions; i++) {
/*  94 */       className.append('[');
/*     */     }
/*  96 */     if (componentType.isPrimitive()) {
/*  97 */       className.append(charThatJavaUsesToRepresentPrimitiveArrayType(componentType));
/*  98 */       return className.toString();
/*     */     }
/* 100 */     className.append('L').append(componentType.getName()).append(';');
/* 101 */     return className.toString();
/*     */   }
/*     */   
/*     */   private Class primitiveClassNamed(String name)
/*     */   {
/* 106 */     return name.equals("double") ? Double.TYPE : name.equals("float") ? Float.TYPE : name.equals("long") ? Long.TYPE : name.equals("int") ? Integer.TYPE : name.equals("short") ? Short.TYPE : name.equals("char") ? Character.TYPE : name.equals("byte") ? Byte.TYPE : name.equals("boolean") ? Boolean.TYPE : name.equals("void") ? Void.TYPE : null;
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
/*     */   private char charThatJavaUsesToRepresentPrimitiveArrayType(Class primvCls)
/*     */   {
/* 120 */     return primvCls == Double.TYPE ? 'D' : primvCls == Float.TYPE ? 'F' : primvCls == Long.TYPE ? 'J' : primvCls == Integer.TYPE ? 'I' : primvCls == Short.TYPE ? 'S' : primvCls == Character.TYPE ? 'C' : primvCls == Byte.TYPE ? 'B' : primvCls == Boolean.TYPE ? 'Z' : '\000';
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
/*     */   private String boxedTypeName(Class type)
/*     */   {
/* 133 */     return BOXED_TYPES.contains(type) ? type.getName() : null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/mapper/ArrayMapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */