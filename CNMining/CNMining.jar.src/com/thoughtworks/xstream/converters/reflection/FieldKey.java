/*    */ package com.thoughtworks.xstream.converters.reflection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FieldKey
/*    */ {
/*    */   private final String fieldName;
/*    */   
/*    */ 
/*    */ 
/*    */   private final Class declaringClass;
/*    */   
/*    */ 
/*    */ 
/*    */   private final int depth;
/*    */   
/*    */ 
/*    */ 
/*    */   private final int order;
/*    */   
/*    */ 
/*    */ 
/*    */   public FieldKey(String fieldName, Class declaringClass, int order)
/*    */   {
/* 26 */     if ((fieldName == null) || (declaringClass == null)) {
/* 27 */       throw new IllegalArgumentException("fieldName or declaringClass is null");
/*    */     }
/* 29 */     this.fieldName = fieldName;
/* 30 */     this.declaringClass = declaringClass;
/* 31 */     this.order = order;
/* 32 */     Class c = declaringClass;
/* 33 */     int i = 0;
/* 34 */     while (c.getSuperclass() != null) {
/* 35 */       i++;
/* 36 */       c = c.getSuperclass();
/*    */     }
/* 38 */     this.depth = i;
/*    */   }
/*    */   
/*    */   public String getFieldName() {
/* 42 */     return this.fieldName;
/*    */   }
/*    */   
/*    */   public Class getDeclaringClass() {
/* 46 */     return this.declaringClass;
/*    */   }
/*    */   
/*    */   public int getDepth() {
/* 50 */     return this.depth;
/*    */   }
/*    */   
/*    */   public int getOrder() {
/* 54 */     return this.order;
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 58 */     if (this == o) return true;
/* 59 */     if (!(o instanceof FieldKey)) { return false;
/*    */     }
/* 61 */     FieldKey fieldKey = (FieldKey)o;
/*    */     
/* 63 */     if (!this.declaringClass.equals(fieldKey.declaringClass))
/* 64 */       return false;
/* 65 */     if (!this.fieldName.equals(fieldKey.fieldName)) {
/* 66 */       return false;
/*    */     }
/* 68 */     return true;
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 73 */     int result = this.fieldName.hashCode();
/* 74 */     result = 29 * result + this.declaringClass.hashCode();
/* 75 */     return result;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 79 */     return "FieldKey{order=" + this.order + ", writer=" + this.depth + ", declaringClass=" + this.declaringClass + ", fieldName='" + this.fieldName + "'" + "}";
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/reflection/FieldKey.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */