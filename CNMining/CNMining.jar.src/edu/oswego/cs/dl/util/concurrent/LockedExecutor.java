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
/*    */ public class LockedExecutor
/*    */   implements Executor
/*    */ {
/*    */   protected final Sync mutex_;
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
/*    */   public LockedExecutor(Sync paramSync)
/*    */   {
/* 40 */     this.mutex_ = paramSync;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void execute(Runnable paramRunnable)
/*    */     throws InterruptedException
/*    */   {
/* 48 */     this.mutex_.acquire();
/*    */     try {
/* 50 */       paramRunnable.run();
/*    */     }
/*    */     finally {
/* 53 */       this.mutex_.release();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/LockedExecutor.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */