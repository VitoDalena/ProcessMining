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
/*    */ public class ThreadFactoryUser
/*    */ {
/* 26 */   protected ThreadFactory threadFactory_ = new DefaultThreadFactory();
/*    */   
/*    */   protected static class DefaultThreadFactory implements ThreadFactory {
/*    */     public Thread newThread(Runnable paramRunnable) {
/* 30 */       return new Thread(paramRunnable);
/*    */     }
/*    */   }
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
/*    */   public synchronized ThreadFactory setThreadFactory(ThreadFactory paramThreadFactory)
/*    */   {
/* 46 */     ThreadFactory localThreadFactory = this.threadFactory_;
/* 47 */     this.threadFactory_ = paramThreadFactory;
/* 48 */     return localThreadFactory;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public synchronized ThreadFactory getThreadFactory()
/*    */   {
/* 55 */     return this.threadFactory_;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/ThreadFactoryUser.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */