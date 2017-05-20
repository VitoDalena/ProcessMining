/*     */ package org.apache.commons.collections15.keyvalue;
/*     */ 
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
/*     */ public class DefaultKeyValue<K, V>
/*     */   extends AbstractKeyValue<K, V>
/*     */ {
/*     */   public DefaultKeyValue()
/*     */   {
/*  42 */     super(null, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultKeyValue(K key, V value)
/*     */   {
/*  52 */     super(key, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultKeyValue(KeyValue<K, V> pair)
/*     */   {
/*  62 */     super(pair.getKey(), pair.getValue());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultKeyValue(Map.Entry<K, V> entry)
/*     */   {
/*  72 */     super(entry.getKey(), entry.getValue());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public K setKey(K key)
/*     */   {
/*  84 */     if (key == this) {
/*  85 */       throw new IllegalArgumentException("DefaultKeyValue may not contain itself as a key.");
/*     */     }
/*     */     
/*  88 */     K old = this.key;
/*  89 */     this.key = key;
/*  90 */     return old;
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
/* 101 */     if (value == this) {
/* 102 */       throw new IllegalArgumentException("DefaultKeyValue may not contain itself as a value.");
/*     */     }
/*     */     
/* 105 */     V old = this.value;
/* 106 */     this.value = value;
/* 107 */     return old;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Map.Entry<K, V> toMapEntry()
/*     */   {
/* 117 */     return new DefaultMapEntry(this);
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 131 */     if (obj == this) {
/* 132 */       return true;
/*     */     }
/* 134 */     if (!(obj instanceof DefaultKeyValue)) {
/* 135 */       return false;
/*     */     }
/*     */     
/* 138 */     DefaultKeyValue other = (DefaultKeyValue)obj;
/* 139 */     return (getKey() == null ? other.getKey() == null : getKey().equals(other.getKey())) && (getValue() == null ? other.getValue() == null : getValue().equals(other.getValue()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 151 */     return (getKey() == null ? 0 : getKey().hashCode()) ^ (getValue() == null ? 0 : getValue().hashCode());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/keyvalue/DefaultKeyValue.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */