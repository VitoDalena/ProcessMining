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
/*    */ public class TimeoutSync
/*    */   implements Sync
/*    */ {
/*    */   protected final Sync sync_;
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
/*    */   protected final long timeout_;
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
/*    */   public TimeoutSync(Sync paramSync, long paramLong)
/*    */   {
/* 53 */     this.sync_ = paramSync;
/* 54 */     this.timeout_ = paramLong;
/*    */   }
/*    */   
/*    */   public void acquire() throws InterruptedException {
/* 58 */     if (!this.sync_.attempt(this.timeout_)) throw new TimeoutException(this.timeout_);
/*    */   }
/*    */   
/*    */   public boolean attempt(long paramLong) throws InterruptedException {
/* 62 */     return this.sync_.attempt(paramLong);
/*    */   }
/*    */   
/*    */   public void release() {
/* 66 */     this.sync_.release();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/TimeoutSync.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */