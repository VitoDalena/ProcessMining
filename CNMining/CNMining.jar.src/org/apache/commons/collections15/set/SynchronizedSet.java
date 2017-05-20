/*    */ package org.apache.commons.collections15.set;
/*    */ 
/*    */ import java.util.Set;
/*    */ import org.apache.commons.collections15.collection.SynchronizedCollection;
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
/*    */ public class SynchronizedSet<E>
/*    */   extends SynchronizedCollection<E>
/*    */   implements Set<E>
/*    */ {
/*    */   private static final long serialVersionUID = -8304417378626543635L;
/*    */   
/*    */   public static <E> Set<E> decorate(Set<E> set)
/*    */   {
/* 49 */     return new SynchronizedSet(set);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected SynchronizedSet(Set<E> set)
/*    */   {
/* 60 */     super(set);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected SynchronizedSet(Set<E> set, Object lock)
/*    */   {
/* 71 */     super(set, lock);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected Set<E> getSet()
/*    */   {
/* 80 */     return (Set)this.collection;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/set/SynchronizedSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */