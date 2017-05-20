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
/*    */ public class ThreadedExecutor
/*    */   extends ThreadFactoryUser
/*    */   implements Executor
/*    */ {
/*    */   public synchronized void execute(Runnable paramRunnable)
/*    */     throws InterruptedException
/*    */   {
/* 30 */     if (Thread.interrupted()) {
/* 31 */       throw new InterruptedException();
/*    */     }
/* 33 */     Thread localThread = getThreadFactory().newThread(paramRunnable);
/* 34 */     localThread.start();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/ThreadedExecutor.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */