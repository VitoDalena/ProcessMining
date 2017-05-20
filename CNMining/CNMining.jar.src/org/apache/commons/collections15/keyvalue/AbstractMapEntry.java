/*    */ package org.apache.commons.collections15.keyvalue;
/*    */ 
/*    */ import java.util.Map.Entry;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractMapEntry<K, V>
/*    */   extends AbstractKeyValue<K, V>
/*    */   implements Map.Entry<K, V>
/*    */ {
/*    */   protected AbstractMapEntry(K key, V value)
/*    */   {
/* 40 */     super(key, value);
/*    */   }
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
/*    */   public V setValue(V value)
/*    */   {
/* 54 */     V answer = this.value;
/* 55 */     this.value = value;
/* 56 */     return answer;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean equals(Object obj)
/*    */   {
/* 68 */     if (obj == this) {
/* 69 */       return true;
/*    */     }
/* 71 */     if (!(obj instanceof Map.Entry)) {
/* 72 */       return false;
/*    */     }
/* 74 */     Map.Entry other = (Map.Entry)obj;
/* 75 */     return (getKey() == null ? other.getKey() == null : getKey().equals(other.getKey())) && (getValue() == null ? other.getValue() == null : getValue().equals(other.getValue()));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 86 */     return (getKey() == null ? 0 : getKey().hashCode()) ^ (getValue() == null ? 0 : getValue().hashCode());
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/keyvalue/AbstractMapEntry.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */