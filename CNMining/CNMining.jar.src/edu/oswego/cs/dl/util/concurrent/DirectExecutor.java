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
/*    */ public class DirectExecutor
/*    */   implements Executor
/*    */ {
/*    */   public void execute(Runnable paramRunnable)
/*    */     throws InterruptedException
/*    */   {
/* 28 */     if (Thread.interrupted()) { throw new InterruptedException();
/*    */     }
/* 30 */     paramRunnable.run();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/DirectExecutor.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */