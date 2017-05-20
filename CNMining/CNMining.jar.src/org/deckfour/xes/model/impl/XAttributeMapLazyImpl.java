/*     */ package org.deckfour.xes.model.impl;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeMap;
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
/*     */ public class XAttributeMapLazyImpl<T extends XAttributeMap>
/*     */   implements XAttributeMap
/*     */ {
/*  77 */   private static final Set<Map.Entry<String, XAttribute>> EMPTY_ENTRYSET = Collections.unmodifiableSet(new HashSet(0));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  83 */   private static final Set<String> EMPTY_KEYSET = Collections.unmodifiableSet(new HashSet(0));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  89 */   private static final Collection<XAttribute> EMPTY_ENTRIES = Collections.unmodifiableCollection(new ArrayList(0));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Class<T> backingStoreClass;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 102 */   private T backingStore = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XAttributeMapLazyImpl(Class<T> implementingClass)
/*     */   {
/* 111 */     this.backingStoreClass = implementingClass;
/* 112 */     this.backingStore = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Class<T> getBackingStoreClass()
/*     */   {
/* 123 */     return this.backingStoreClass;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void clear()
/*     */   {
/* 130 */     this.backingStore = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized boolean containsKey(Object key)
/*     */   {
/* 137 */     if (this.backingStore != null) {
/* 138 */       return this.backingStore.containsKey(key);
/*     */     }
/* 140 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized boolean containsValue(Object value)
/*     */   {
/* 148 */     if (this.backingStore != null) {
/* 149 */       return this.backingStore.containsValue(value);
/*     */     }
/* 151 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized Set<Map.Entry<String, XAttribute>> entrySet()
/*     */   {
/* 159 */     if (this.backingStore != null) {
/* 160 */       return this.backingStore.entrySet();
/*     */     }
/* 162 */     return EMPTY_ENTRYSET;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized XAttribute get(Object key)
/*     */   {
/* 170 */     if (this.backingStore != null) {
/* 171 */       return (XAttribute)this.backingStore.get(key);
/*     */     }
/* 173 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized boolean isEmpty()
/*     */   {
/* 181 */     if (this.backingStore != null) {
/* 182 */       return this.backingStore.isEmpty();
/*     */     }
/* 184 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized Set<String> keySet()
/*     */   {
/* 192 */     if (this.backingStore != null) {
/* 193 */       return this.backingStore.keySet();
/*     */     }
/* 195 */     return EMPTY_KEYSET;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized XAttribute put(String key, XAttribute value)
/*     */   {
/* 203 */     if (this.backingStore == null) {
/*     */       try {
/* 205 */         this.backingStore = ((XAttributeMap)this.backingStoreClass.newInstance());
/*     */       }
/*     */       catch (Exception e) {
/* 208 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 211 */     return (XAttribute)this.backingStore.put(key, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void putAll(Map<? extends String, ? extends XAttribute> t)
/*     */   {
/* 218 */     if (t.size() > 0) {
/* 219 */       if (this.backingStore == null) {
/*     */         try {
/* 221 */           this.backingStore = ((XAttributeMap)this.backingStoreClass.newInstance());
/*     */         }
/*     */         catch (Exception e) {
/* 224 */           e.printStackTrace();
/*     */         }
/*     */       }
/* 227 */       this.backingStore.putAll(t);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized XAttribute remove(Object key)
/*     */   {
/* 235 */     if (this.backingStore != null) {
/* 236 */       return (XAttribute)this.backingStore.remove(key);
/*     */     }
/* 238 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized int size()
/*     */   {
/* 246 */     if (this.backingStore != null) {
/* 247 */       return this.backingStore.size();
/*     */     }
/* 249 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized Collection<XAttribute> values()
/*     */   {
/* 257 */     if (this.backingStore != null) {
/* 258 */       return this.backingStore.values();
/*     */     }
/* 260 */     return EMPTY_ENTRIES;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/*     */     try
/*     */     {
/* 271 */       XAttributeMapLazyImpl<T> clone = (XAttributeMapLazyImpl)super.clone();
/* 272 */       if (this.backingStore != null) {
/* 273 */         clone.backingStore = ((XAttributeMap)this.backingStore.clone());
/*     */       }
/* 275 */       return clone;
/*     */     }
/*     */     catch (CloneNotSupportedException e) {
/* 278 */       e.printStackTrace(); }
/* 279 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/impl/XAttributeMapLazyImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */