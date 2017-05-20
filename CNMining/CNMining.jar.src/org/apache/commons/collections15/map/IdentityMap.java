/*     */ package org.apache.commons.collections15.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
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
/*     */ public class IdentityMap<K, V>
/*     */   extends AbstractHashedMap<K, V>
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 2028493495224302329L;
/*     */   
/*     */   public IdentityMap()
/*     */   {
/*  48 */     super(16, 0.75F, 12);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IdentityMap(int initialCapacity)
/*     */   {
/*  58 */     super(initialCapacity);
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
/*     */   public IdentityMap(int initialCapacity, float loadFactor)
/*     */   {
/*  71 */     super(initialCapacity, loadFactor);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IdentityMap(Map<? extends K, ? extends V> map)
/*     */   {
/*  81 */     super(map);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int hash(Object key)
/*     */   {
/*  93 */     return System.identityHashCode(key);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean isEqualKey(Object key1, Object key2)
/*     */   {
/* 105 */     return key1 == key2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean isEqualValue(Object value1, Object value2)
/*     */   {
/* 117 */     return value1 == value2;
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
/*     */   protected AbstractHashedMap.HashEntry<K, V> createEntry(AbstractHashedMap.HashEntry<K, V> next, int hashCode, K key, V value)
/*     */   {
/* 131 */     return new IdentityEntry(next, hashCode, key, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected static class IdentityEntry<K, V>
/*     */     extends AbstractHashedMap.HashEntry<K, V>
/*     */   {
/*     */     protected IdentityEntry(AbstractHashedMap.HashEntry<K, V> next, int hashCode, K key, V value)
/*     */     {
/* 141 */       super(hashCode, key, value);
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/* 145 */       if (obj == this) {
/* 146 */         return true;
/*     */       }
/* 148 */       if (!(obj instanceof Map.Entry)) {
/* 149 */         return false;
/*     */       }
/* 151 */       Map.Entry other = (Map.Entry)obj;
/* 152 */       return (getKey() == other.getKey()) && (getValue() == other.getValue());
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 156 */       return System.identityHashCode(getKey()) ^ System.identityHashCode(getValue());
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
/* 167 */     return super.clone();
/*     */   }
/*     */   
/*     */ 
/*     */   private void writeObject(ObjectOutputStream out)
/*     */     throws IOException
/*     */   {
/* 174 */     out.defaultWriteObject();
/* 175 */     doWriteObject(out);
/*     */   }
/*     */   
/*     */ 
/*     */   private void readObject(ObjectInputStream in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 182 */     in.defaultReadObject();
/* 183 */     doReadObject(in);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/IdentityMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */