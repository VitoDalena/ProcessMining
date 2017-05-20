/*    */ package edu.uci.ics.jung.algorithms.util;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map.Entry;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BasicMapEntry<K, V>
/*    */   implements Map.Entry<K, V>
/*    */ {
/*    */   final K key;
/*    */   V value;
/*    */   
/*    */   public BasicMapEntry(K k, V v)
/*    */   {
/* 20 */     this.value = v;
/* 21 */     this.key = k;
/*    */   }
/*    */   
/*    */   public K getKey() {
/* 25 */     return (K)this.key;
/*    */   }
/*    */   
/*    */   public V getValue() {
/* 29 */     return (V)this.value;
/*    */   }
/*    */   
/*    */   public V setValue(V newValue) {
/* 33 */     V oldValue = this.value;
/* 34 */     this.value = newValue;
/* 35 */     return oldValue;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean equals(Object o)
/*    */   {
/* 41 */     if (!(o instanceof Map.Entry))
/* 42 */       return false;
/* 43 */     Map.Entry e = (Map.Entry)o;
/* 44 */     Object k1 = getKey();
/* 45 */     Object k2 = e.getKey();
/* 46 */     if ((k1 == k2) || ((k1 != null) && (k1.equals(k2)))) {
/* 47 */       Object v1 = getValue();
/* 48 */       Object v2 = e.getValue();
/* 49 */       if ((v1 == v2) || ((v1 != null) && (v1.equals(v2))))
/* 50 */         return true;
/*    */     }
/* 52 */     return false;
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 57 */     return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : this.value.hashCode());
/*    */   }
/*    */   
/*    */ 
/*    */   public String toString()
/*    */   {
/* 63 */     return getKey() + "=" + getValue();
/*    */   }
/*    */   
/*    */   void recordAccess(HashMap<K, V> m) {}
/*    */   
/*    */   void recordRemoval(HashMap<K, V> m) {}
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/util/BasicMapEntry.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */