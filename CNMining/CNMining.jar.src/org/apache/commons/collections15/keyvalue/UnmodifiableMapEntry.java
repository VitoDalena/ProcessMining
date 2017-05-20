/*    */ package org.apache.commons.collections15.keyvalue;
/*    */ 
/*    */ import java.util.Map.Entry;
/*    */ import org.apache.commons.collections15.KeyValue;
/*    */ import org.apache.commons.collections15.Unmodifiable;
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
/*    */ public final class UnmodifiableMapEntry<K, V>
/*    */   extends AbstractMapEntry<K, V>
/*    */   implements Unmodifiable
/*    */ {
/*    */   public UnmodifiableMapEntry(K key, V value)
/*    */   {
/* 41 */     super(key, value);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public UnmodifiableMapEntry(KeyValue<K, V> pair)
/*    */   {
/* 51 */     super(pair.getKey(), pair.getValue());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public UnmodifiableMapEntry(Map.Entry<K, V> entry)
/*    */   {
/* 61 */     super(entry.getKey(), entry.getValue());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public V setValue(V value)
/*    */   {
/* 72 */     throw new UnsupportedOperationException("setValue() is not supported");
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/keyvalue/UnmodifiableMapEntry.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */