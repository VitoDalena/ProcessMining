/*    */ package com.thoughtworks.xstream.core.util;
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
/*    */ public class ClassLoaderReference
/*    */   extends ClassLoader
/*    */ {
/*    */   private transient ClassLoader reference;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public ClassLoaderReference(ClassLoader reference)
/*    */   {
/* 27 */     this.reference = reference;
/*    */   }
/*    */   
/*    */   public Class loadClass(String name) throws ClassNotFoundException {
/* 31 */     return this.reference.loadClass(name);
/*    */   }
/*    */   
/*    */   public ClassLoader getReference() {
/* 35 */     return this.reference;
/*    */   }
/*    */   
/*    */   public void setReference(ClassLoader reference) {
/* 39 */     this.reference = reference;
/*    */   }
/*    */   
/*    */   private Object writeReplace() {
/* 43 */     return new Replacement();
/*    */   }
/*    */   
/*    */   static class Replacement
/*    */   {
/*    */     private Object readResolve() {
/* 49 */       return new ClassLoaderReference(new CompositeClassLoader());
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/util/ClassLoaderReference.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */