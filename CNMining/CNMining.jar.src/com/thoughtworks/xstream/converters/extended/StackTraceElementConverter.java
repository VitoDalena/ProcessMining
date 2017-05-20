/*    */ package com.thoughtworks.xstream.converters.extended;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.ConversionException;
/*    */ import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
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
/*    */ 
/*    */ public class StackTraceElementConverter
/*    */   extends AbstractSingleValueConverter
/*    */ {
/* 34 */   private static final Pattern PATTERN = Pattern.compile("^(.+)\\.([^\\(]+)\\(([^:]*)(:(\\d+))?\\)$");
/* 35 */   private static final StackTraceElementFactory FACTORY = new StackTraceElementFactory();
/*    */   
/*    */   public boolean canConvert(Class type) {
/* 38 */     return StackTraceElement.class.equals(type);
/*    */   }
/*    */   
/*    */   public String toString(Object obj) {
/* 42 */     String s = super.toString(obj);
/*    */     
/* 44 */     return s.replaceFirst(":\\?\\?\\?", "");
/*    */   }
/*    */   
/*    */   public Object fromString(String str) {
/* 48 */     Matcher matcher = PATTERN.matcher(str);
/* 49 */     if (matcher.matches()) {
/* 50 */       String declaringClass = matcher.group(1);
/* 51 */       String methodName = matcher.group(2);
/* 52 */       String fileName = matcher.group(3);
/* 53 */       if (fileName.equals("Unknown Source"))
/* 54 */         return FACTORY.unknownSourceElement(declaringClass, methodName);
/* 55 */       if (fileName.equals("Native Method")) {
/* 56 */         return FACTORY.nativeMethodElement(declaringClass, methodName);
/*    */       }
/* 58 */       if (matcher.group(4) != null) {
/* 59 */         int lineNumber = Integer.parseInt(matcher.group(5));
/* 60 */         return FACTORY.element(declaringClass, methodName, fileName, lineNumber);
/*    */       }
/* 62 */       return FACTORY.element(declaringClass, methodName, fileName);
/*    */     }
/*    */     
/*    */ 
/* 66 */     throw new ConversionException("Could not parse StackTraceElement : " + str);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/extended/StackTraceElementConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */