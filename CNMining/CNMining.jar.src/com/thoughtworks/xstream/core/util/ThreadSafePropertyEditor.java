/*    */ package com.thoughtworks.xstream.core.util;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.reflection.ObjectAccessException;
/*    */ import java.beans.PropertyEditor;
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
/*    */ public class ThreadSafePropertyEditor
/*    */ {
/*    */   private final Class editorType;
/*    */   private final Pool pool;
/*    */   
/*    */   public ThreadSafePropertyEditor(Class type, int initialPoolSize, int maxPoolSize)
/*    */   {
/* 39 */     if (!PropertyEditor.class.isAssignableFrom(type)) {
/* 40 */       throw new IllegalArgumentException(type.getName() + " is not a " + PropertyEditor.class.getName());
/*    */     }
/*    */     
/*    */ 
/* 44 */     this.editorType = type;
/* 45 */     this.pool = new Pool(initialPoolSize, maxPoolSize, new Pool.Factory() {
/*    */       public Object newInstance() {
/*    */         try {
/* 48 */           return ThreadSafePropertyEditor.this.editorType.newInstance();
/*    */         } catch (InstantiationException e) {
/* 50 */           throw new ObjectAccessException("Could not call default constructor of " + ThreadSafePropertyEditor.this.editorType.getName(), e);
/*    */         }
/*    */         catch (IllegalAccessException e) {
/* 53 */           throw new ObjectAccessException("Could not call default constructor of " + ThreadSafePropertyEditor.this.editorType.getName(), e);
/*    */         }
/*    */       }
/*    */     });
/*    */   }
/*    */   
/*    */ 
/*    */   public String getAsText(Object object)
/*    */   {
/* 62 */     PropertyEditor editor = fetchFromPool();
/*    */     try {
/* 64 */       editor.setValue(object);
/* 65 */       return editor.getAsText();
/*    */     } finally {
/* 67 */       this.pool.putInPool(editor);
/*    */     }
/*    */   }
/*    */   
/*    */   public Object setAsText(String str) {
/* 72 */     PropertyEditor editor = fetchFromPool();
/*    */     try {
/* 74 */       editor.setAsText(str);
/* 75 */       return editor.getValue();
/*    */     } finally {
/* 77 */       this.pool.putInPool(editor);
/*    */     }
/*    */   }
/*    */   
/*    */   private PropertyEditor fetchFromPool() {
/* 82 */     PropertyEditor editor = (PropertyEditor)this.pool.fetchFromPool();
/* 83 */     return editor;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/util/ThreadSafePropertyEditor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */