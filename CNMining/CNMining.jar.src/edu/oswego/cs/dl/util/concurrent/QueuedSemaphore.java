/*     */ package edu.oswego.cs.dl.util.concurrent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class QueuedSemaphore
/*     */   extends Semaphore
/*     */ {
/*     */   protected final WaitQueue wq_;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   QueuedSemaphore(WaitQueue paramWaitQueue, long paramLong)
/*     */   {
/*  30 */     super(paramLong);
/*  31 */     this.wq_ = paramWaitQueue;
/*     */   }
/*     */   
/*     */   public void acquire() throws InterruptedException {
/*  35 */     if (Thread.interrupted()) throw new InterruptedException();
/*  36 */     if (precheck()) return;
/*  37 */     QueuedSemaphore.WaitQueue.WaitNode localWaitNode = new QueuedSemaphore.WaitQueue.WaitNode();
/*  38 */     localWaitNode.doWait(this);
/*     */   }
/*     */   
/*     */   public boolean attempt(long paramLong) throws InterruptedException {
/*  42 */     if (Thread.interrupted()) throw new InterruptedException();
/*  43 */     if (precheck()) return true;
/*  44 */     if (paramLong <= 0L) { return false;
/*     */     }
/*  46 */     QueuedSemaphore.WaitQueue.WaitNode localWaitNode = new QueuedSemaphore.WaitQueue.WaitNode();
/*  47 */     return localWaitNode.doTimedWait(this, paramLong);
/*     */   }
/*     */   
/*     */   protected synchronized boolean precheck() {
/*  51 */     boolean bool = this.permits_ > 0L;
/*  52 */     if (bool) this.permits_ -= 1L;
/*  53 */     return bool;
/*     */   }
/*     */   
/*     */   protected synchronized boolean recheck(QueuedSemaphore.WaitQueue.WaitNode paramWaitNode) {
/*  57 */     boolean bool = this.permits_ > 0L;
/*  58 */     if (bool) this.permits_ -= 1L; else
/*  59 */       this.wq_.insert(paramWaitNode);
/*  60 */     return bool;
/*     */   }
/*     */   
/*     */   protected synchronized QueuedSemaphore.WaitQueue.WaitNode getSignallee()
/*     */   {
/*  65 */     QueuedSemaphore.WaitQueue.WaitNode localWaitNode = this.wq_.extract();
/*  66 */     if (localWaitNode == null) this.permits_ += 1L;
/*  67 */     return localWaitNode;
/*     */   }
/*     */   
/*     */   public void release() {
/*     */     QueuedSemaphore.WaitQueue.WaitNode localWaitNode;
/*  72 */     do { localWaitNode = getSignallee();
/*  73 */       if (localWaitNode == null) return;
/*  74 */     } while (!localWaitNode.signal());
/*     */   }
/*     */   
/*     */ 
/*     */   public void release(long paramLong)
/*     */   {
/*  80 */     if (paramLong < 0L) { throw new IllegalArgumentException("Negative argument");
/*     */     }
/*  82 */     for (long l = 0L; l < paramLong; l += 1L) { release();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected static abstract class WaitQueue
/*     */   {
/*     */     protected abstract void insert(WaitNode paramWaitNode);
/*     */     
/*     */ 
/*     */     protected abstract WaitNode extract();
/*     */     
/*     */     protected static class WaitNode
/*     */     {
/*  96 */       boolean waiting = true;
/*  97 */       WaitNode next = null;
/*     */       
/*     */       protected synchronized boolean signal() {
/* 100 */         boolean bool = this.waiting;
/* 101 */         if (bool) {
/* 102 */           this.waiting = false;
/* 103 */           notify();
/*     */         }
/* 105 */         return bool;
/*     */       }
/*     */       
/*     */       protected synchronized boolean doTimedWait(QueuedSemaphore paramQueuedSemaphore, long paramLong)
/*     */         throws InterruptedException
/*     */       {
/* 111 */         if ((paramQueuedSemaphore.recheck(this)) || (!this.waiting))
/* 112 */           return true;
/* 113 */         if (paramLong <= 0L) {
/* 114 */           this.waiting = false;
/* 115 */           return false;
/*     */         }
/*     */         
/* 118 */         long l1 = paramLong;
/* 119 */         long l2 = System.currentTimeMillis();
/*     */         try
/*     */         {
/*     */           do {
/* 123 */             wait(l1);
/* 124 */             if (!this.waiting) {
/* 125 */               return true;
/*     */             }
/* 127 */             l1 = paramLong - (System.currentTimeMillis() - l2);
/* 128 */           } while (l1 > 0L);
/* 129 */           this.waiting = false;
/* 130 */           return false;
/*     */ 
/*     */         }
/*     */         catch (InterruptedException localInterruptedException)
/*     */         {
/*     */ 
/* 136 */           if (this.waiting) {
/* 137 */             this.waiting = false;
/* 138 */             throw localInterruptedException;
/*     */           }
/*     */           
/* 141 */           Thread.currentThread().interrupt(); }
/* 142 */         return true;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */       protected synchronized void doWait(QueuedSemaphore paramQueuedSemaphore)
/*     */         throws InterruptedException
/*     */       {
/* 150 */         if (!paramQueuedSemaphore.recheck(this)) {
/*     */           try {
/* 152 */             while (this.waiting) wait();
/*     */           }
/*     */           catch (InterruptedException localInterruptedException) {
/* 155 */             if (this.waiting) {
/* 156 */               this.waiting = false;
/* 157 */               throw localInterruptedException;
/*     */             }
/*     */             
/* 160 */             Thread.currentThread().interrupt();
/* 161 */             return;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/QueuedSemaphore.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */