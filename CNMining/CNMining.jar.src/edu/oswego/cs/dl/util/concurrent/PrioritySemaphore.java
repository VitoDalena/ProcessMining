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
/*    */ public class PrioritySemaphore
/*    */   extends QueuedSemaphore
/*    */ {
/*    */   public PrioritySemaphore(long paramLong)
/*    */   {
/* 43 */     super(new PriorityWaitQueue(), paramLong);
/*    */   }
/*    */   
/*    */ 
/*    */   protected static class PriorityWaitQueue
/*    */     extends QueuedSemaphore.WaitQueue
/*    */   {
/* 50 */     protected final FIFOSemaphore.FIFOWaitQueue[] cells_ = new FIFOSemaphore.FIFOWaitQueue[10];
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 59 */     protected int maxIndex_ = -1;
/*    */     
/*    */     protected PriorityWaitQueue() {
/* 62 */       for (int i = 0; i < this.cells_.length; i++)
/* 63 */         this.cells_[i] = new FIFOSemaphore.FIFOWaitQueue();
/*    */     }
/*    */     
/*    */     protected void insert(QueuedSemaphore.WaitQueue.WaitNode paramWaitNode) {
/* 67 */       int i = Thread.currentThread().getPriority() - 1;
/* 68 */       this.cells_[i].insert(paramWaitNode);
/* 69 */       if (i > this.maxIndex_) this.maxIndex_ = i;
/*    */     }
/*    */     
/*    */     protected QueuedSemaphore.WaitQueue.WaitNode extract() {
/*    */       for (;;) {
/* 74 */         int i = this.maxIndex_;
/* 75 */         if (i < 0)
/* 76 */           return null;
/* 77 */         QueuedSemaphore.WaitQueue.WaitNode localWaitNode = this.cells_[i].extract();
/* 78 */         if (localWaitNode != null) {
/* 79 */           return localWaitNode;
/*    */         }
/* 81 */         this.maxIndex_ -= 1;
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/PrioritySemaphore.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */