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
/*    */ public class FIFOSemaphore
/*    */   extends QueuedSemaphore
/*    */ {
/*    */   public FIFOSemaphore(long paramLong)
/*    */   {
/* 44 */     super(new FIFOWaitQueue(), paramLong);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected static class FIFOWaitQueue
/*    */     extends QueuedSemaphore.WaitQueue
/*    */   {
/* 53 */     protected QueuedSemaphore.WaitQueue.WaitNode head_ = null;
/* 54 */     protected QueuedSemaphore.WaitQueue.WaitNode tail_ = null;
/*    */     
/*    */     protected void insert(QueuedSemaphore.WaitQueue.WaitNode paramWaitNode) {
/* 57 */       if (this.tail_ == null) {
/* 58 */         this.head_ = (this.tail_ = paramWaitNode);
/*    */       } else {
/* 60 */         this.tail_.next = paramWaitNode;
/* 61 */         this.tail_ = paramWaitNode;
/*    */       }
/*    */     }
/*    */     
/*    */     protected QueuedSemaphore.WaitQueue.WaitNode extract() {
/* 66 */       if (this.head_ == null) {
/* 67 */         return null;
/*    */       }
/* 69 */       QueuedSemaphore.WaitQueue.WaitNode localWaitNode = this.head_;
/* 70 */       this.head_ = localWaitNode.next;
/* 71 */       if (this.head_ == null) this.tail_ = null;
/* 72 */       localWaitNode.next = null;
/* 73 */       return localWaitNode;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/FIFOSemaphore.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */