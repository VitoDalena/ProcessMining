/*    */ package com.thoughtworks.xstream.converters.extended;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.ConversionException;
/*    */ import com.thoughtworks.xstream.converters.Converter;
/*    */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*    */ import com.thoughtworks.xstream.converters.SingleValueConverter;
/*    */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import java.lang.reflect.Field;
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
/*    */ public class JavaFieldConverter
/*    */   implements Converter
/*    */ {
/*    */   private final SingleValueConverter javaClassConverter;
/*    */   
/*    */   public JavaFieldConverter(ClassLoader classLoader)
/*    */   {
/* 33 */     this.javaClassConverter = new JavaClassConverter(classLoader);
/*    */   }
/*    */   
/*    */   public boolean canConvert(Class type) {
/* 37 */     return type.equals(Field.class);
/*    */   }
/*    */   
/*    */   public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
/* 41 */     Field field = (Field)source;
/*    */     
/* 43 */     writer.startNode("name");
/* 44 */     writer.setValue(field.getName());
/* 45 */     writer.endNode();
/*    */     
/* 47 */     writer.startNode("clazz");
/* 48 */     writer.setValue(this.javaClassConverter.toString(field.getDeclaringClass()));
/* 49 */     writer.endNode();
/*    */   }
/*    */   
/*    */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
/* 53 */     String methodName = null;
/* 54 */     String declaringClassName = null;
/*    */     
/* 56 */     while (((methodName == null) || (declaringClassName == null)) && (reader.hasMoreChildren())) {
/* 57 */       reader.moveDown();
/*    */       
/* 59 */       if (reader.getNodeName().equals("name")) {
/* 60 */         methodName = reader.getValue();
/* 61 */       } else if (reader.getNodeName().equals("clazz")) {
/* 62 */         declaringClassName = reader.getValue();
/*    */       }
/* 64 */       reader.moveUp();
/*    */     }
/*    */     
/* 67 */     Class declaringClass = (Class)this.javaClassConverter.fromString(declaringClassName);
/*    */     try {
/* 69 */       return declaringClass.getDeclaredField(methodName);
/*    */     } catch (NoSuchFieldException e) {
/* 71 */       throw new ConversionException(e);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/extended/JavaFieldConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */