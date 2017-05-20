/*    */ package com.thoughtworks.xstream.core.util;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class FastField
/*    */ {
/*    */   private final String name;
/*    */   
/*    */ 
/*    */ 
/*    */   private final String declaringClass;
/*    */   
/*    */ 
/*    */ 
/*    */   public FastField(String definedIn, String name)
/*    */   {
/* 18 */     this.name = name;
/* 19 */     this.declaringClass = definedIn;
/*    */   }
/*    */   
/*    */   public FastField(Class definedIn, String name) {
/* 23 */     this(definedIn == null ? null : definedIn.getName(), name);
/*    */   }
/*    */   
/*    */   public String getName() {
/* 27 */     return this.name;
/*    */   }
/*    */   
/*    */   public String getDeclaringClass() {
/* 31 */     return this.declaringClass;
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj) {
/* 35 */     if (this == obj) {
/* 36 */       return true;
/*    */     }
/* 38 */     if (obj == null) {
/* 39 */       return false;
/*    */     }
/* 41 */     if ((obj instanceof FastField)) {
/* 42 */       FastField field = (FastField)obj;
/* 43 */       if (((this.declaringClass == null) && (field.declaringClass != null)) || ((this.declaringClass != null) && (field.declaringClass == null)))
/*    */       {
/* 45 */         return false;
/*    */       }
/* 47 */       return (this.name.equals(field.getName())) && ((this.declaringClass == null) || (this.declaringClass.equals(field.getDeclaringClass())));
/*    */     }
/*    */     
/* 50 */     return false;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 54 */     return this.name.hashCode() ^ (this.declaringClass == null ? 0 : this.declaringClass.hashCode());
/*    */   }
/*    */   
/*    */   public String toString() {
/* 58 */     return (this.declaringClass == null ? "" : new StringBuffer().append(this.declaringClass).append(".").toString()) + this.name;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/util/FastField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */