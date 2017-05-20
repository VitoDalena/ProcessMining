/*    */ package com.thoughtworks.xstream.mapper;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import java.util.Collections;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
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
/*    */ public class CachingMapper
/*    */   extends MapperWrapper
/*    */ {
/*    */   private transient Map realClassCache;
/*    */   
/*    */   public CachingMapper(Mapper wrapped)
/*    */   {
/* 29 */     super(wrapped);
/* 30 */     readResolve();
/*    */   }
/*    */   
/*    */   public Class realClass(String elementName) {
/* 34 */     WeakReference reference = (WeakReference)this.realClassCache.get(elementName);
/* 35 */     if (reference != null) {
/* 36 */       Class cached = (Class)reference.get();
/* 37 */       if (cached != null) {
/* 38 */         return cached;
/*    */       }
/*    */     }
/*    */     
/* 42 */     Class result = super.realClass(elementName);
/* 43 */     this.realClassCache.put(elementName, new WeakReference(result));
/* 44 */     return result;
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 48 */     this.realClassCache = Collections.synchronizedMap(new HashMap(128));
/* 49 */     return this;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/mapper/CachingMapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */