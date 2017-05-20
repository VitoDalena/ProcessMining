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
/*    */ public class TimedCallable
/*    */   extends ThreadFactoryUser
/*    */   implements Callable
/*    */ {
/*    */   private final Callable function;
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
/*    */   private final long millis;
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
/*    */   public TimedCallable(Callable paramCallable, long paramLong)
/*    */   {
/* 42 */     this.function = paramCallable;
/* 43 */     this.millis = paramLong;
/*    */   }
/*    */   
/*    */   public Object call() throws Exception
/*    */   {
/* 48 */     FutureResult localFutureResult = new FutureResult();
/*    */     
/* 50 */     Thread localThread = getThreadFactory().newThread(localFutureResult.setter(this.function));
/*    */     
/* 52 */     localThread.start();
/*    */     try
/*    */     {
/* 55 */       return localFutureResult.timedGet(this.millis);
/*    */ 
/*    */     }
/*    */     catch (InterruptedException localInterruptedException)
/*    */     {
/* 60 */       localThread.interrupt();
/* 61 */       throw localInterruptedException;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/TimedCallable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */