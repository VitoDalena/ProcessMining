/*    */ package com.thoughtworks.xstream.annotations;
/*    */ 
/*    */ import java.lang.annotation.Annotation;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class AnnotationProvider
/*    */ {
/*    */   @Deprecated
/*    */   public <T extends Annotation> T getAnnotation(Field field, Class<T> annotationClass)
/*    */   {
/* 36 */     return field.getAnnotation(annotationClass);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/annotations/AnnotationProvider.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */