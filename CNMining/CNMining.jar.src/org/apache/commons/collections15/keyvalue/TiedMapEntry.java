/*     */ package org.apache.commons.collections15.keyvalue;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import org.apache.commons.collections15.KeyValue;
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
/*     */ public class TiedMapEntry<K, V>
/*     */   implements Map.Entry<K, V>, KeyValue<K, V>, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8453869361373831205L;
/*     */   private final Map<K, V> map;
/*     */   private final K key;
/*     */   
/*     */   public TiedMapEntry(Map<K, V> map, K key)
/*     */   {
/*  58 */     this.map = map;
/*  59 */     this.key = key;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public K getKey()
/*     */   {
/*  70 */     return (K)this.key;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public V getValue()
/*     */   {
/*  79 */     return (V)this.map.get(this.key);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public V setValue(V value)
/*     */   {
/*  90 */     if (value == this) {
/*  91 */       throw new IllegalArgumentException("Cannot set value to this map entry");
/*     */     }
/*     */     
/*  94 */     return (V)this.map.put(this.key, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 106 */     if (obj == this) {
/* 107 */       return true;
/*     */     }
/* 109 */     if (!(obj instanceof Map.Entry)) {
/* 110 */       return false;
/*     */     }
/* 112 */     Map.Entry other = (Map.Entry)obj;
/* 113 */     Object value = getValue();
/* 114 */     return (this.key == null ? other.getKey() == null : this.key.equals(other.getKey())) && (value == null ? other.getValue() == null : value.equals(other.getValue()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 125 */     Object value = getValue();
/* 126 */     return (getKey() == null ? 0 : getKey().hashCode()) ^ (value == null ? 0 : value.hashCode());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 135 */     return getKey() + "=" + getValue();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/keyvalue/TiedMapEntry.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */