/*    */ package com.thoughtworks.xstream.converters.extended;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.SingleValueConverter;
/*    */ import com.thoughtworks.xstream.core.util.ThreadSafePropertyEditor;
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
/*    */ public class PropertyEditorCapableConverter
/*    */   implements SingleValueConverter
/*    */ {
/*    */   private final ThreadSafePropertyEditor editor;
/*    */   private final Class type;
/*    */   
/*    */   public PropertyEditorCapableConverter(Class propertyEditorType, Class type)
/*    */   {
/* 33 */     this.type = type;
/* 34 */     this.editor = new ThreadSafePropertyEditor(propertyEditorType, 2, 5);
/*    */   }
/*    */   
/*    */   public boolean canConvert(Class type) {
/* 38 */     return this.type == type;
/*    */   }
/*    */   
/*    */   public Object fromString(String str) {
/* 42 */     return this.editor.setAsText(str);
/*    */   }
/*    */   
/*    */   public String toString(Object obj) {
/* 46 */     return this.editor.getAsText(obj);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/extended/PropertyEditorCapableConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */