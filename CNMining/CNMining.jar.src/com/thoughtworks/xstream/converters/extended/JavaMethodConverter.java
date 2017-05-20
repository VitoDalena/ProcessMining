/*     */ package com.thoughtworks.xstream.converters.extended;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.ConversionException;
/*     */ import com.thoughtworks.xstream.converters.Converter;
/*     */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*     */ import com.thoughtworks.xstream.converters.SingleValueConverter;
/*     */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ public class JavaMethodConverter
/*     */   implements Converter
/*     */ {
/*     */   private final SingleValueConverter javaClassConverter;
/*     */   
/*     */   public JavaMethodConverter(ClassLoader classLoader)
/*     */   {
/*  38 */     this.javaClassConverter = new JavaClassConverter(classLoader);
/*     */   }
/*     */   
/*     */   public boolean canConvert(Class type) {
/*  42 */     return (type.equals(Method.class)) || (type.equals(Constructor.class));
/*     */   }
/*     */   
/*     */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/*  46 */     if ((source instanceof Method)) {
/*  47 */       Method method = (Method)source;
/*  48 */       String declaringClassName = this.javaClassConverter.toString(method.getDeclaringClass());
/*  49 */       marshalMethod(writer, declaringClassName, method.getName(), method.getParameterTypes());
/*     */     } else {
/*  51 */       Constructor method = (Constructor)source;
/*  52 */       String declaringClassName = this.javaClassConverter.toString(method.getDeclaringClass());
/*  53 */       marshalMethod(writer, declaringClassName, null, method.getParameterTypes());
/*     */     }
/*     */   }
/*     */   
/*     */   private void marshalMethod(HierarchicalStreamWriter writer, String declaringClassName, String methodName, Class[] parameterTypes)
/*     */   {
/*  59 */     writer.startNode("class");
/*  60 */     writer.setValue(declaringClassName);
/*  61 */     writer.endNode();
/*     */     
/*  63 */     if (methodName != null)
/*     */     {
/*  65 */       writer.startNode("name");
/*  66 */       writer.setValue(methodName);
/*  67 */       writer.endNode();
/*     */     }
/*     */     
/*  70 */     writer.startNode("parameter-types");
/*  71 */     for (int i = 0; i < parameterTypes.length; i++) {
/*  72 */       writer.startNode("class");
/*  73 */       writer.setValue(this.javaClassConverter.toString(parameterTypes[i]));
/*  74 */       writer.endNode();
/*     */     }
/*  76 */     writer.endNode();
/*     */   }
/*     */   
/*     */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
/*     */     try {
/*  81 */       boolean isMethodNotConstructor = context.getRequiredType().equals(Method.class);
/*     */       
/*  83 */       reader.moveDown();
/*  84 */       String declaringClassName = reader.getValue();
/*  85 */       Class declaringClass = (Class)this.javaClassConverter.fromString(declaringClassName);
/*  86 */       reader.moveUp();
/*     */       
/*  88 */       String methodName = null;
/*  89 */       if (isMethodNotConstructor) {
/*  90 */         reader.moveDown();
/*  91 */         methodName = reader.getValue();
/*  92 */         reader.moveUp();
/*     */       }
/*     */       
/*  95 */       reader.moveDown();
/*  96 */       List parameterTypeList = new ArrayList();
/*  97 */       while (reader.hasMoreChildren()) {
/*  98 */         reader.moveDown();
/*  99 */         String parameterTypeName = reader.getValue();
/* 100 */         parameterTypeList.add(this.javaClassConverter.fromString(parameterTypeName));
/* 101 */         reader.moveUp();
/*     */       }
/* 103 */       Class[] parameterTypes = (Class[])parameterTypeList.toArray(new Class[parameterTypeList.size()]);
/* 104 */       reader.moveUp();
/*     */       
/* 106 */       if (isMethodNotConstructor) {
/* 107 */         return declaringClass.getDeclaredMethod(methodName, parameterTypes);
/*     */       }
/* 109 */       return declaringClass.getDeclaredConstructor(parameterTypes);
/*     */     }
/*     */     catch (NoSuchMethodException e) {
/* 112 */       throw new ConversionException(e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/extended/JavaMethodConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */