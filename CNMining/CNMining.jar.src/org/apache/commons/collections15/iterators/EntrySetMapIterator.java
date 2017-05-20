/*     */ package org.apache.commons.collections15.iterators;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.MapIterator;
/*     */ import org.apache.commons.collections15.ResettableIterator;
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
/*     */ public class EntrySetMapIterator<K, V>
/*     */   implements MapIterator<K, V>, ResettableIterator<K>
/*     */ {
/*     */   private final Map<K, V> map;
/*     */   private Iterator<Map.Entry<K, V>> iterator;
/*     */   private Map.Entry<K, V> last;
/*  46 */   private boolean canRemove = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public EntrySetMapIterator(Map<K, V> map)
/*     */   {
/*  55 */     this.map = map;
/*  56 */     this.iterator = map.entrySet().iterator();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean hasNext()
/*     */   {
/*  66 */     return this.iterator.hasNext();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public K next()
/*     */   {
/*  77 */     this.last = ((Map.Entry)this.iterator.next());
/*  78 */     this.canRemove = true;
/*  79 */     return (K)this.last.getKey();
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
/*     */ 
/*     */   public void remove()
/*     */   {
/*  94 */     if (!this.canRemove) {
/*  95 */       throw new IllegalStateException("Iterator remove() can only be called once after next()");
/*     */     }
/*  97 */     this.iterator.remove();
/*  98 */     this.last = null;
/*  99 */     this.canRemove = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public K getKey()
/*     */   {
/* 111 */     if (this.last == null) {
/* 112 */       throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
/*     */     }
/* 114 */     return (K)this.last.getKey();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public V getValue()
/*     */   {
/* 125 */     if (this.last == null) {
/* 126 */       throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
/*     */     }
/* 128 */     return (V)this.last.getValue();
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
/*     */   public V setValue(V value)
/*     */   {
/* 142 */     if (this.last == null) {
/* 143 */       throw new IllegalStateException("Iterator setValue() can only be called after next() and before remove()");
/*     */     }
/* 145 */     return (V)this.last.setValue(value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reset()
/*     */   {
/* 153 */     this.iterator = this.map.entrySet().iterator();
/* 154 */     this.last = null;
/* 155 */     this.canRemove = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 164 */     if (this.last != null) {
/* 165 */       return "MapIterator[" + getKey() + "=" + getValue() + "]";
/*     */     }
/* 167 */     return "MapIterator[]";
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/EntrySetMapIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */