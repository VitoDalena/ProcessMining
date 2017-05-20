/*     */ package org.deckfour.xes.model.buffered;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ import org.deckfour.xes.model.impl.XAttributeMapImpl;
/*     */ import org.deckfour.xes.model.impl.XAttributeMapLazyImpl;
/*     */ import org.deckfour.xes.nikefs2.NikeFS2RandomAccessStorage;
/*     */ import org.deckfour.xes.nikefs2.NikeFS2StorageProvider;
/*     */ import org.deckfour.xes.nikefs2.NikeFS2VirtualFileSystem;
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
/*     */ public abstract class XAbstractAttributeMapBufferedImpl
/*     */   implements XAttributeMap
/*     */ {
/*  70 */   private int size = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  75 */   private NikeFS2RandomAccessStorage storage = null;
/*     */   
/*     */ 
/*     */ 
/*  79 */   private NikeFS2StorageProvider provider = null;
/*     */   
/*     */ 
/*     */ 
/*  83 */   private XAttributeMapSerializer serializer = null;
/*     */   
/*     */ 
/*     */ 
/*  87 */   private WeakReference<XAttributeMap> cacheMap = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected XAbstractAttributeMapBufferedImpl(XAttributeMapSerializer serializer)
/*     */   {
/*  96 */     this(NikeFS2VirtualFileSystem.instance(), serializer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected XAbstractAttributeMapBufferedImpl(NikeFS2StorageProvider provider, XAttributeMapSerializer serializer)
/*     */   {
/* 107 */     synchronized (this) {
/* 108 */       this.size = 0;
/* 109 */       this.provider = provider;
/* 110 */       this.serializer = serializer;
/*     */       try {
/* 112 */         this.storage = provider.createStorage();
/*     */       }
/*     */       catch (IOException e) {
/* 115 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized XAttributeMap deserialize()
/*     */     throws IOException
/*     */   {
/* 129 */     if (this.size == 0) {
/* 130 */       return new XAttributeMapLazyImpl(XAttributeMapImpl.class);
/*     */     }
/* 132 */     if ((this.cacheMap != null) && (this.cacheMap.get() != null)) {
/* 133 */       return (XAttributeMap)this.cacheMap.get();
/*     */     }
/* 135 */     this.storage.seek(0L);
/* 136 */     XAttributeMap deserialized = this.serializer.deserialize(this.storage);
/* 137 */     this.cacheMap = new WeakReference(deserialized);
/* 138 */     return deserialized;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void serialize(XAttributeMap map)
/*     */     throws IOException
/*     */   {
/* 149 */     this.storage.seek(0L);
/* 150 */     this.serializer.serialize(map, this.storage);
/* 151 */     this.cacheMap = new WeakReference(map);
/* 152 */     this.size = map.size();
/* 153 */     map = null;
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized void clear()
/*     */   {
/*     */     try
/*     */     {
/* 161 */       this.storage.close();
/* 162 */       this.storage = this.provider.createStorage();
/*     */     }
/*     */     catch (IOException e) {
/* 165 */       e.printStackTrace();
/*     */     }
/* 167 */     this.size = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized boolean containsKey(Object key)
/*     */   {
/* 174 */     if (this.size == 0) {
/* 175 */       return false;
/*     */     }
/*     */     try {
/* 178 */       XAttributeMap map = deserialize();
/* 179 */       return map.containsKey(key);
/*     */     } catch (IOException e) {
/* 181 */       e.printStackTrace(); }
/* 182 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized boolean containsValue(Object value)
/*     */   {
/* 191 */     if (this.size == 0) {
/* 192 */       return false;
/*     */     }
/*     */     try {
/* 195 */       XAttributeMap map = deserialize();
/* 196 */       return map.containsValue(value);
/*     */     } catch (IOException e) {
/* 198 */       e.printStackTrace(); }
/* 199 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized Set<Map.Entry<String, XAttribute>> entrySet()
/*     */   {
/*     */     try
/*     */     {
/* 209 */       return deserialize().entrySet();
/*     */     } catch (IOException e) {
/* 211 */       e.printStackTrace(); }
/* 212 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized XAttribute get(Object key)
/*     */   {
/* 220 */     if (this.size == 0) {
/* 221 */       return null;
/*     */     }
/*     */     try {
/* 224 */       XAttributeMap map = deserialize();
/* 225 */       return (XAttribute)map.get(key);
/*     */     } catch (IOException e) {
/* 227 */       e.printStackTrace(); }
/* 228 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized boolean isEmpty()
/*     */   {
/* 237 */     return this.size == 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized Set<String> keySet()
/*     */   {
/* 244 */     if (this.size == 0) {
/* 245 */       return new HashSet(0);
/*     */     }
/*     */     try {
/* 248 */       XAttributeMap map = deserialize();
/* 249 */       return map.keySet();
/*     */     } catch (IOException e) {
/* 251 */       e.printStackTrace(); }
/* 252 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized XAttribute put(String key, XAttribute value)
/*     */   {
/*     */     try
/*     */     {
/* 262 */       XAttributeMap map = deserialize();
/* 263 */       map.put(key, value);
/* 264 */       serialize(map);
/* 265 */       return value;
/*     */     } catch (IOException e) {
/* 267 */       e.printStackTrace(); }
/* 268 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void putAll(Map<? extends String, ? extends XAttribute> t)
/*     */   {
/*     */     try
/*     */     {
/* 277 */       XAttributeMap map = deserialize();
/* 278 */       map.putAll(t);
/* 279 */       serialize(map);
/*     */     } catch (IOException e) {
/* 281 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized XAttribute remove(Object key)
/*     */   {
/*     */     try
/*     */     {
/* 290 */       XAttributeMap map = deserialize();
/* 291 */       XAttribute retVal = (XAttribute)map.remove(key);
/* 292 */       serialize(map);
/* 293 */       return retVal;
/*     */     } catch (IOException e) {
/* 295 */       e.printStackTrace(); }
/* 296 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized int size()
/*     */   {
/* 304 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized Collection<XAttribute> values()
/*     */   {
/*     */     try
/*     */     {
/* 312 */       XAttributeMap map = deserialize();
/* 313 */       return map.values();
/*     */     } catch (IOException e) {
/* 315 */       e.printStackTrace(); }
/* 316 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized Object clone()
/*     */   {
/*     */     try
/*     */     {
/* 325 */       XAbstractAttributeMapBufferedImpl clone = (XAbstractAttributeMapBufferedImpl)super.clone();
/* 326 */       clone.storage = this.storage.copy();
/* 327 */       return clone;
/*     */     } catch (CloneNotSupportedException e) {
/* 329 */       e.printStackTrace();
/* 330 */       return null;
/*     */     } catch (IOException e) {
/* 332 */       e.printStackTrace(); }
/* 333 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 343 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void finalize()
/*     */     throws Throwable
/*     */   {
/* 351 */     super.finalize();
/*     */     
/* 353 */     this.storage.close();
/* 354 */     this.storage = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 364 */     return super.hashCode();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 372 */     return "XAttributeMap buffered implementation, size: " + this.size;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/buffered/XAbstractAttributeMapBufferedImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */