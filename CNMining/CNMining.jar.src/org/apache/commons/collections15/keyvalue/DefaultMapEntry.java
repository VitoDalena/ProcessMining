/*    */ package org.apache.commons.collections15.keyvalue;
/*    */ 
/*    */ import java.util.Map.Entry;
/*    */ import org.apache.commons.collections15.KeyValue;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class DefaultMapEntry<K, V>
/*    */   extends AbstractMapEntry<K, V>
/*    */ {
/*    */   public DefaultMapEntry(K key, V value)
/*    */   {
/* 43 */     super(key, value);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public DefaultMapEntry(KeyValue<K, V> pair)
/*    */   {
/* 53 */     super(pair.getKey(), pair.getValue());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public DefaultMapEntry(Map.Entry<K, V> entry)
/*    */   {
/* 63 */     super(entry.getKey(), entry.getValue());
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/keyvalue/DefaultMapEntry.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */