/*    */ package com.thoughtworks.xstream.converters.extended;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.ConversionException;
/*    */ import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
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
/*    */ public class JavaClassConverter
/*    */   extends AbstractSingleValueConverter
/*    */ {
/*    */   private ClassLoader classLoader;
/*    */   
/*    */   public JavaClassConverter(ClassLoader classLoader)
/*    */   {
/* 30 */     this.classLoader = classLoader;
/*    */   }
/*    */   
/*    */   public boolean canConvert(Class clazz) {
/* 34 */     return Class.class.equals(clazz);
/*    */   }
/*    */   
/*    */   public String toString(Object obj) {
/* 38 */     return ((Class)obj).getName();
/*    */   }
/*    */   
/*    */   public Object fromString(String str) {
/*    */     try {
/* 43 */       return loadClass(str);
/*    */     } catch (ClassNotFoundException e) {
/* 45 */       throw new ConversionException("Cannot load java class " + str, e);
/*    */     }
/*    */   }
/*    */   
/*    */   private Class loadClass(String className) throws ClassNotFoundException {
/* 50 */     Class resultingClass = primitiveClassForName(className);
/* 51 */     if (resultingClass != null) {
/* 52 */       return resultingClass;
/*    */     }
/*    */     
/* 55 */     for (int dimension = 0; className.charAt(dimension) == '['; dimension++) {}
/* 56 */     if (dimension > 0) { ClassLoader classLoaderToUse;
/*    */       ClassLoader classLoaderToUse;
/* 58 */       if (className.charAt(dimension) == 'L') {
/* 59 */         String componentTypeName = className.substring(dimension + 1, className.length() - 1);
/* 60 */         classLoaderToUse = this.classLoader.loadClass(componentTypeName).getClassLoader();
/*    */       } else {
/* 62 */         classLoaderToUse = null;
/*    */       }
/* 64 */       return Class.forName(className, false, classLoaderToUse);
/*    */     }
/* 66 */     return this.classLoader.loadClass(className);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   private Class primitiveClassForName(String name)
/*    */   {
/* 73 */     return name.equals("double") ? Double.TYPE : name.equals("float") ? Float.TYPE : name.equals("long") ? Long.TYPE : name.equals("int") ? Integer.TYPE : name.equals("short") ? Short.TYPE : name.equals("char") ? Character.TYPE : name.equals("byte") ? Byte.TYPE : name.equals("boolean") ? Boolean.TYPE : name.equals("void") ? Void.TYPE : null;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/extended/JavaClassConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */