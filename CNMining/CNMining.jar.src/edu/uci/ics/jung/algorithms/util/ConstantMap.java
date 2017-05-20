/*    */ package edu.uci.ics.jung.algorithms.util;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import java.util.Set;
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
/*    */ public class ConstantMap<K, V>
/*    */   implements Map<K, V>
/*    */ {
/*    */   private Map<K, V> delegate;
/*    */   
/*    */   public ConstantMap(V value)
/*    */   {
/* 33 */     this.delegate = Collections.unmodifiableMap(Collections.singletonMap(null, value));
/*    */   }
/*    */   
/*    */   public V get(Object key) {
/* 37 */     return (V)this.delegate.get(null);
/*    */   }
/*    */   
/*    */   public void clear() {
/* 41 */     this.delegate.clear();
/*    */   }
/*    */   
/*    */   public boolean containsKey(Object key) {
/* 45 */     return true;
/*    */   }
/*    */   
/*    */   public boolean containsValue(Object value) {
/* 49 */     return this.delegate.containsValue(value);
/*    */   }
/*    */   
/*    */   public Set<Map.Entry<K, V>> entrySet() {
/* 53 */     return this.delegate.entrySet();
/*    */   }
/*    */   
/*    */   public boolean equals(Object o)
/*    */   {
/* 58 */     return this.delegate.equals(o);
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 63 */     return this.delegate.hashCode();
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 67 */     return this.delegate.isEmpty();
/*    */   }
/*    */   
/*    */   public Set<K> keySet() {
/* 71 */     return this.delegate.keySet();
/*    */   }
/*    */   
/*    */   public V put(K key, V value) {
/* 75 */     return (V)this.delegate.put(key, value);
/*    */   }
/*    */   
/*    */   public void putAll(Map<? extends K, ? extends V> t) {
/* 79 */     this.delegate.putAll(t);
/*    */   }
/*    */   
/*    */   public V remove(Object key) {
/* 83 */     return (V)this.delegate.remove(key);
/*    */   }
/*    */   
/*    */   public int size() {
/* 87 */     return this.delegate.size();
/*    */   }
/*    */   
/*    */   public Collection<V> values() {
/* 91 */     return this.delegate.values();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/util/ConstantMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */