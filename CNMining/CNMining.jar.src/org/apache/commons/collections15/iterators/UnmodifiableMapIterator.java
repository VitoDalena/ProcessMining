/*    */ package org.apache.commons.collections15.iterators;
/*    */ 
/*    */ import org.apache.commons.collections15.MapIterator;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class UnmodifiableMapIterator<K, V>
/*    */   implements MapIterator<K, V>, Unmodifiable
/*    */ {
/*    */   private MapIterator<K, V> iterator;
/*    */   
/*    */   public static <K, V> MapIterator<K, V> decorate(MapIterator<K, V> iterator)
/*    */   {
/* 44 */     if (iterator == null) {
/* 45 */       throw new IllegalArgumentException("MapIterator must not be null");
/*    */     }
/* 47 */     if ((iterator instanceof Unmodifiable)) {
/* 48 */       return iterator;
/*    */     }
/* 50 */     return new UnmodifiableMapIterator(iterator);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private UnmodifiableMapIterator(MapIterator<K, V> iterator)
/*    */   {
/* 61 */     this.iterator = iterator;
/*    */   }
/*    */   
/*    */   public boolean hasNext()
/*    */   {
/* 66 */     return this.iterator.hasNext();
/*    */   }
/*    */   
/*    */   public K next() {
/* 70 */     return (K)this.iterator.next();
/*    */   }
/*    */   
/*    */   public K getKey() {
/* 74 */     return (K)this.iterator.getKey();
/*    */   }
/*    */   
/*    */   public V getValue() {
/* 78 */     return (V)this.iterator.getValue();
/*    */   }
/*    */   
/*    */   public V setValue(V value) {
/* 82 */     throw new UnsupportedOperationException("setValue() is not supported");
/*    */   }
/*    */   
/*    */   public void remove() {
/* 86 */     throw new UnsupportedOperationException("remove() is not supported");
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/UnmodifiableMapIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */