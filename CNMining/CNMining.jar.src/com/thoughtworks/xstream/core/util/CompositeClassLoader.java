/*    */ package com.thoughtworks.xstream.core.util;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
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
/*    */ public class CompositeClassLoader
/*    */   extends ClassLoader
/*    */ {
/* 48 */   private final List classLoaders = Collections.synchronizedList(new ArrayList());
/*    */   
/*    */   public CompositeClassLoader() {
/* 51 */     add(Object.class.getClassLoader());
/* 52 */     add(getClass().getClassLoader());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void add(ClassLoader classLoader)
/*    */   {
/* 60 */     if (classLoader != null) {
/* 61 */       this.classLoaders.add(0, classLoader);
/*    */     }
/*    */   }
/*    */   
/*    */   public Class loadClass(String name) throws ClassNotFoundException {
/* 66 */     for (Iterator iterator = this.classLoaders.iterator(); iterator.hasNext();) {
/* 67 */       ClassLoader classLoader = (ClassLoader)iterator.next();
/*    */       try {
/* 69 */         return classLoader.loadClass(name);
/*    */       }
/*    */       catch (ClassNotFoundException notFound) {}
/*    */     }
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 77 */     ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
/* 78 */     if (contextClassLoader != null) {
/* 79 */       return contextClassLoader.loadClass(name);
/*    */     }
/* 81 */     throw new ClassNotFoundException(name);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/util/CompositeClassLoader.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */