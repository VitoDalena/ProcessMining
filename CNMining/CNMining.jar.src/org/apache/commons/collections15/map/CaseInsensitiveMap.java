/*     */ package org.apache.commons.collections15.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
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
/*     */ public class CaseInsensitiveMap<V>
/*     */   extends AbstractHashedMap<String, V>
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = -7074655917369299456L;
/*     */   
/*     */   public CaseInsensitiveMap()
/*     */   {
/*  66 */     super(16, 0.75F, 12);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CaseInsensitiveMap(int initialCapacity)
/*     */   {
/*  76 */     super(initialCapacity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CaseInsensitiveMap(int initialCapacity, float loadFactor)
/*     */   {
/*  89 */     super(initialCapacity, loadFactor);
/*     */   }
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
/*     */   public CaseInsensitiveMap(Map<? extends String, ? extends V> map)
/*     */   {
/* 103 */     super(map);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String convertKey(String key)
/*     */   {
/* 116 */     if (key != null) {
/* 117 */       return key.toString().toLowerCase();
/*     */     }
/* 119 */     return null;
/*     */   }
/*     */   
/*     */   public V get(Object key)
/*     */   {
/* 124 */     if (!(key instanceof String)) {
/* 125 */       return (V)super.get(key);
/*     */     }
/* 127 */     return (V)super.get(convertKey((String)key));
/*     */   }
/*     */   
/*     */   public V put(String s, V v) {
/* 131 */     return (V)super.put(convertKey(s), v);
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends String, ? extends V> map) {
/* 135 */     Set entries = map.entrySet();
/* 136 */     for (Iterator<Map.Entry<? extends String, ? extends V>> iterator = entries.iterator(); iterator.hasNext();) {
/* 137 */       Map.Entry<? extends String, ? extends V> entry = (Map.Entry)iterator.next();
/* 138 */       put((String)entry.getKey(), entry.getValue());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 149 */     return super.clone();
/*     */   }
/*     */   
/*     */ 
/*     */   private void writeObject(ObjectOutputStream out)
/*     */     throws IOException
/*     */   {
/* 156 */     out.defaultWriteObject();
/* 157 */     doWriteObject(out);
/*     */   }
/*     */   
/*     */ 
/*     */   private void readObject(ObjectInputStream in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 164 */     in.defaultReadObject();
/* 165 */     doReadObject(in);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/CaseInsensitiveMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */