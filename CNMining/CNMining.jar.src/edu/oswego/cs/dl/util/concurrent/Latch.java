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
/*    */ public class Latch
/*    */   implements Sync
/*    */ {
/* 48 */   protected boolean latched_ = false;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void acquire()
/*    */     throws InterruptedException
/*    */   {
/* 61 */     if (Thread.interrupted()) throw new InterruptedException();
/* 62 */     synchronized (this) {
/* 63 */       while (!this.latched_)
/* 64 */         wait();
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean attempt(long paramLong) throws InterruptedException {
/* 69 */     if (Thread.interrupted()) throw new InterruptedException();
/* 70 */     synchronized (this) {
/* 71 */       if (this.latched_) {
/* 72 */         boolean bool1 = true;return bool1; }
/* 73 */       if (paramLong <= 0L) {
/* 74 */         boolean bool2 = false;return bool2;
/*    */       }
/* 76 */       long l1 = paramLong;
/* 77 */       long l2 = System.currentTimeMillis();
/*    */       do {
/* 79 */         wait(l1);
/* 80 */         if (this.latched_) {
/* 81 */           boolean bool3 = true;return bool3;
/*    */         }
/* 83 */         l1 = paramLong - (System.currentTimeMillis() - l2);
/* 84 */       } while (l1 > 0L);
/* 85 */       boolean bool4 = false;return bool4;
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public synchronized void release()
/*    */   {
/* 94 */     this.latched_ = true;
/* 95 */     notifyAll();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/Latch.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */