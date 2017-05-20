/*    */ package org.apache.commons.collections15.keyvalue;
/*    */ 
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractKeyValue<K, V>
/*    */   implements KeyValue<K, V>
/*    */ {
/*    */   protected K key;
/*    */   protected V value;
/*    */   
/*    */   protected AbstractKeyValue(K key, V value)
/*    */   {
/* 50 */     this.key = key;
/* 51 */     this.value = value;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public K getKey()
/*    */   {
/* 60 */     return (K)this.key;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public V getValue()
/*    */   {
/* 69 */     return (V)this.value;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String toString()
/*    */   {
/* 78 */     return getKey() + '=' + getValue();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/keyvalue/AbstractKeyValue.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */