/*    */ package org.apache.commons.collections15.bidimap;
/*    */ 
/*    */ import java.util.Set;
/*    */ import org.apache.commons.collections15.BidiMap;
/*    */ import org.apache.commons.collections15.MapIterator;
/*    */ import org.apache.commons.collections15.map.AbstractMapDecorator;
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
/*    */ public abstract class AbstractBidiMapDecorator<K, V>
/*    */   extends AbstractMapDecorator<K, V>
/*    */   implements BidiMap<K, V>
/*    */ {
/*    */   protected AbstractBidiMapDecorator(BidiMap<K, V> map)
/*    */   {
/* 50 */     super(map);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected BidiMap<K, V> getBidiMap()
/*    */   {
/* 59 */     return (BidiMap)this.map;
/*    */   }
/*    */   
/*    */   public MapIterator<K, V> mapIterator()
/*    */   {
/* 64 */     return getBidiMap().mapIterator();
/*    */   }
/*    */   
/*    */   public K getKey(Object value) {
/* 68 */     return (K)getBidiMap().getKey(value);
/*    */   }
/*    */   
/*    */   public K removeValue(Object value) {
/* 72 */     return (K)getBidiMap().removeValue(value);
/*    */   }
/*    */   
/*    */   public BidiMap<V, K> inverseBidiMap() {
/* 76 */     return getBidiMap().inverseBidiMap();
/*    */   }
/*    */   
/*    */   public Set<V> values() {
/* 80 */     return getBidiMap().values();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/bidimap/AbstractBidiMapDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */