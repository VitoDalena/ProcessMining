/*     */ package com.thoughtworks.xstream.core.util;
/*     */ 
/*     */ import java.lang.ref.ReferenceQueue;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ObjectIdDictionary
/*     */ {
/*     */   private final Map map;
/*     */   private final ReferenceQueue queue;
/*     */   
/*     */   public ObjectIdDictionary()
/*     */   {
/*  30 */     this.map = new HashMap();
/*  31 */     this.queue = new ReferenceQueue();
/*     */   }
/*     */   
/*     */   private static abstract interface Wrapper { public abstract int hashCode();
/*     */     
/*     */     public abstract boolean equals(Object paramObject);
/*     */     
/*     */     public abstract String toString();
/*     */     
/*     */     public abstract Object get();
/*     */   }
/*     */   
/*     */   private static class IdWrapper implements ObjectIdDictionary.Wrapper { private final Object obj;
/*     */     private final int hashCode;
/*     */     
/*  46 */     public IdWrapper(Object obj) { this.hashCode = System.identityHashCode(obj);
/*  47 */       this.obj = obj;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  51 */       return this.hashCode;
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/*  55 */       return this.obj == ((ObjectIdDictionary.Wrapper)other).get();
/*     */     }
/*     */     
/*     */     public String toString() {
/*  59 */       return this.obj.toString();
/*     */     }
/*     */     
/*     */     public Object get() {
/*  63 */       return this.obj;
/*     */     }
/*     */   }
/*     */   
/*     */   private class WeakIdWrapper extends WeakReference implements ObjectIdDictionary.Wrapper
/*     */   {
/*     */     private final int hashCode;
/*     */     
/*     */     public WeakIdWrapper(Object obj) {
/*  72 */       super(ObjectIdDictionary.this.queue);
/*  73 */       this.hashCode = System.identityHashCode(obj);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  77 */       return this.hashCode;
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/*  81 */       return get() == ((ObjectIdDictionary.Wrapper)other).get();
/*     */     }
/*     */     
/*     */     public String toString() {
/*  85 */       Object obj = get();
/*  86 */       return obj == null ? "(null)" : obj.toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public void associateId(Object obj, Object id) {
/*  91 */     this.map.put(new WeakIdWrapper(obj), id);
/*  92 */     cleanup();
/*     */   }
/*     */   
/*     */   public Object lookupId(Object obj) {
/*  96 */     Object id = this.map.get(new IdWrapper(obj));
/*  97 */     return id;
/*     */   }
/*     */   
/*     */   public boolean containsId(Object item) {
/* 101 */     boolean b = this.map.containsKey(new IdWrapper(item));
/* 102 */     return b;
/*     */   }
/*     */   
/*     */   public void removeId(Object item) {
/* 106 */     this.map.remove(new IdWrapper(item));
/* 107 */     cleanup();
/*     */   }
/*     */   
/*     */   public int size() {
/* 111 */     cleanup();
/* 112 */     return this.map.size();
/*     */   }
/*     */   
/*     */   private void cleanup() {
/*     */     WeakIdWrapper wrapper;
/* 117 */     while ((wrapper = (WeakIdWrapper)this.queue.poll()) != null)
/*     */     {
/* 119 */       this.map.remove(wrapper);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/util/ObjectIdDictionary.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */