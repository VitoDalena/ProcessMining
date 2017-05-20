/*    */ package com.thoughtworks.xstream.converters.extended;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.ConversionException;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StackTraceElementFactory
/*    */ {
/*    */   public StackTraceElement nativeMethodElement(String declaringClass, String methodName)
/*    */   {
/* 28 */     return create(declaringClass, methodName, "Native Method", -2);
/*    */   }
/*    */   
/*    */   public StackTraceElement unknownSourceElement(String declaringClass, String methodName) {
/* 32 */     return create(declaringClass, methodName, "Unknown Source", -1);
/*    */   }
/*    */   
/*    */   public StackTraceElement element(String declaringClass, String methodName, String fileName) {
/* 36 */     return create(declaringClass, methodName, fileName, -1);
/*    */   }
/*    */   
/*    */   public StackTraceElement element(String declaringClass, String methodName, String fileName, int lineNumber) {
/* 40 */     return create(declaringClass, methodName, fileName, lineNumber);
/*    */   }
/*    */   
/*    */   private StackTraceElement create(String declaringClass, String methodName, String fileName, int lineNumber) {
/* 44 */     StackTraceElement result = new Throwable().getStackTrace()[0];
/* 45 */     setField(result, "declaringClass", declaringClass);
/* 46 */     setField(result, "methodName", methodName);
/* 47 */     setField(result, "fileName", fileName);
/* 48 */     setField(result, "lineNumber", new Integer(lineNumber));
/* 49 */     return result;
/*    */   }
/*    */   
/*    */   private void setField(StackTraceElement element, String fieldName, Object value) {
/*    */     try {
/* 54 */       Field field = StackTraceElement.class.getDeclaredField(fieldName);
/* 55 */       field.setAccessible(true);
/* 56 */       field.set(element, value);
/*    */     } catch (Exception e) {
/* 58 */       throw new ConversionException(e);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/extended/StackTraceElementFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */