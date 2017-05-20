/*    */ package edu.oswego.cs.dl.util.concurrent;
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
/*    */ public class NullSync
/*    */   implements Sync
/*    */ {
/*    */   public synchronized void acquire()
/*    */     throws InterruptedException
/*    */   {
/* 34 */     if (Thread.interrupted()) throw new InterruptedException();
/*    */   }
/*    */   
/*    */   public synchronized boolean attempt(long paramLong) throws InterruptedException {
/* 38 */     if (Thread.interrupted()) throw new InterruptedException();
/* 39 */     return true;
/*    */   }
/*    */   
/*    */   public synchronized void release() {}
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/NullSync.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */