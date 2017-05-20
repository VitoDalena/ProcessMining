/*    */ package com.thoughtworks.xstream.converters.reflection;
/*    */ 
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
/*    */ public class ReflectionProviderWrapper
/*    */   implements ReflectionProvider
/*    */ {
/*    */   protected final ReflectionProvider wrapped;
/*    */   
/*    */   public ReflectionProviderWrapper(ReflectionProvider wrapper)
/*    */   {
/* 26 */     this.wrapped = wrapper;
/*    */   }
/*    */   
/*    */   public boolean fieldDefinedInClass(String fieldName, Class type) {
/* 30 */     return this.wrapped.fieldDefinedInClass(fieldName, type);
/*    */   }
/*    */   
/*    */   public Field getField(Class definedIn, String fieldName) {
/* 34 */     return this.wrapped.getField(definedIn, fieldName);
/*    */   }
/*    */   
/*    */   public Class getFieldType(Object object, String fieldName, Class definedIn) {
/* 38 */     return this.wrapped.getFieldType(object, fieldName, definedIn);
/*    */   }
/*    */   
/*    */   public Object newInstance(Class type) {
/* 42 */     return this.wrapped.newInstance(type);
/*    */   }
/*    */   
/*    */   public void visitSerializableFields(Object object, ReflectionProvider.Visitor visitor) {
/* 46 */     this.wrapped.visitSerializableFields(object, visitor);
/*    */   }
/*    */   
/*    */   public void writeField(Object object, String fieldName, Object value, Class definedIn) {
/* 50 */     this.wrapped.writeField(object, fieldName, value, definedIn);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/reflection/ReflectionProviderWrapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */