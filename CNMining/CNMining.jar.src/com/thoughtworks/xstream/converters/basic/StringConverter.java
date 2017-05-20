/*    */ package com.thoughtworks.xstream.converters.basic;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import java.util.Collections;
/*    */ import java.util.Map;
/*    */ import java.util.WeakHashMap;
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
/*    */ public class StringConverter
/*    */   extends AbstractSingleValueConverter
/*    */ {
/*    */   private final Map cache;
/*    */   
/*    */   public StringConverter(Map map)
/*    */   {
/* 41 */     this.cache = map;
/*    */   }
/*    */   
/*    */   public StringConverter() {
/* 45 */     this(Collections.synchronizedMap(new WeakHashMap()));
/*    */   }
/*    */   
/*    */   public boolean canConvert(Class type) {
/* 49 */     return type.equals(String.class);
/*    */   }
/*    */   
/*    */   public Object fromString(String str) {
/* 53 */     WeakReference ref = (WeakReference)this.cache.get(str);
/* 54 */     String s = (String)(ref == null ? null : ref.get());
/*    */     
/* 56 */     if (s == null)
/*    */     {
/* 58 */       this.cache.put(str, new WeakReference(str));
/*    */       
/* 60 */       s = str;
/*    */     }
/*    */     
/* 63 */     return s;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/basic/StringConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */