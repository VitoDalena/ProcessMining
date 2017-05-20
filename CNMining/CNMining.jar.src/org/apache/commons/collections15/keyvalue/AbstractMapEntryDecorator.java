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
/*    */ public abstract class AbstractMapEntryDecorator<K, V>
/*    */   implements Map.Entry<K, V>, KeyValue<K, V>
/*    */ {
/*    */   protected final Map.Entry<K, V> entry;
/*    */   
/*    */   public AbstractMapEntryDecorator(Map.Entry<K, V> entry)
/*    */   {
/* 45 */     if (entry == null) {
/* 46 */       throw new IllegalArgumentException("Map Entry must not be null");
/*    */     }
/* 48 */     this.entry = entry;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected Map.Entry<K, V> getMapEntry()
/*    */   {
/* 57 */     return this.entry;
/*    */   }
/*    */   
/*    */   public K getKey()
/*    */   {
/* 62 */     return (K)this.entry.getKey();
/*    */   }
/*    */   
/*    */   public V getValue() {
/* 66 */     return (V)this.entry.getValue();
/*    */   }
/*    */   
/*    */   public V setValue(V object) {
/* 70 */     return (V)this.entry.setValue(object);
/*    */   }
/*    */   
/*    */   public boolean equals(Object object) {
/* 74 */     if (object == this) {
/* 75 */       return true;
/*    */     }
/* 77 */     return this.entry.equals(object);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 81 */     return this.entry.hashCode();
/*    */   }
/*    */   
/*    */   public String toString() {
/* 85 */     return this.entry.toString();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/keyvalue/AbstractMapEntryDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */