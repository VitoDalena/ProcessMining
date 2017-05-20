/*    */ package edu.oswego.cs.dl.util.concurrent;
/*    */ 
/*    */ import java.util.Collection;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SyncSet
/*    */   extends SyncCollection
/*    */   implements Set
/*    */ {
/*    */   public SyncSet(Set paramSet, Sync paramSync)
/*    */   {
/* 35 */     super(paramSet, paramSync);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public SyncSet(Set paramSet, ReadWriteLock paramReadWriteLock)
/*    */   {
/* 43 */     super(paramSet, paramReadWriteLock.readLock(), paramReadWriteLock.writeLock());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public SyncSet(Set paramSet, Sync paramSync1, Sync paramSync2)
/*    */   {
/* 51 */     super(paramSet, paramSync1, paramSync2);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 55 */     boolean bool = beforeRead();
/*    */     try {
/* 57 */       return this.c_.hashCode();
/*    */     }
/*    */     finally {
/* 60 */       afterRead(bool);
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 65 */     boolean bool1 = beforeRead();
/*    */     try {
/* 67 */       return this.c_.equals(paramObject);
/*    */     }
/*    */     finally {
/* 70 */       afterRead(bool1);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/SyncSet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */