/*    */ package com.thoughtworks.xstream.core.util;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
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
/*    */ public class PrioritizedList
/*    */ {
/*    */   private final Set set;
/*    */   private int lowestPriority;
/*    */   private int lastId;
/*    */   
/*    */   public PrioritizedList()
/*    */   {
/* 30 */     this.set = new TreeSet();
/*    */     
/* 32 */     this.lowestPriority = Integer.MAX_VALUE;
/*    */     
/* 34 */     this.lastId = 0;
/*    */   }
/*    */   
/* 37 */   public void add(Object item, int priority) { if (this.lowestPriority > priority) {
/* 38 */       this.lowestPriority = priority;
/*    */     }
/* 40 */     this.set.add(new PrioritizedItem(item, priority, ++this.lastId));
/*    */   }
/*    */   
/*    */   public Iterator iterator() {
/* 44 */     return new PrioritizedItemIterator(this.set.iterator());
/*    */   }
/*    */   
/*    */   private static class PrioritizedItem implements Comparable
/*    */   {
/*    */     final Object value;
/*    */     final int priority;
/*    */     final int id;
/*    */     
/*    */     public PrioritizedItem(Object value, int Prioritized, int id) {
/* 54 */       this.value = value;
/* 55 */       this.priority = Prioritized;
/* 56 */       this.id = id;
/*    */     }
/*    */     
/*    */     public int compareTo(Object o) {
/* 60 */       PrioritizedItem other = (PrioritizedItem)o;
/* 61 */       if (this.priority != other.priority) {
/* 62 */         return other.priority - this.priority;
/*    */       }
/* 64 */       return other.id - this.id;
/*    */     }
/*    */     
/*    */     public boolean equals(Object obj) {
/* 68 */       return this.id == ((PrioritizedItem)obj).id;
/*    */     }
/*    */   }
/*    */   
/*    */   private static class PrioritizedItemIterator implements Iterator
/*    */   {
/*    */     private Iterator iterator;
/*    */     
/*    */     public PrioritizedItemIterator(Iterator iterator)
/*    */     {
/* 78 */       this.iterator = iterator;
/*    */     }
/*    */     
/*    */     public void remove()
/*    */     {
/* 83 */       throw new UnsupportedOperationException();
/*    */     }
/*    */     
/*    */     public boolean hasNext() {
/* 87 */       return this.iterator.hasNext();
/*    */     }
/*    */     
/*    */     public Object next() {
/* 91 */       return ((PrioritizedList.PrioritizedItem)this.iterator.next()).value;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/util/PrioritizedList.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */