/*     */ package edu.oswego.cs.dl.util.concurrent;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Vector;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PooledExecutor
/*     */   extends ThreadFactoryUser
/*     */   implements Executor
/*     */ {
/*     */   public static final int DEFAULT_MAXIMUMPOOLSIZE = Integer.MAX_VALUE;
/*     */   public static final int DEFAULT_MINIMUMPOOLSIZE = 1;
/*     */   public static final long DEFAULT_KEEPALIVETIME = 60000L;
/* 350 */   protected int maximumPoolSize_ = Integer.MAX_VALUE;
/*     */   
/*     */ 
/* 353 */   protected int minimumPoolSize_ = 1;
/*     */   
/*     */ 
/* 356 */   protected int poolSize_ = 0;
/*     */   
/*     */ 
/* 359 */   protected long keepAliveTime_ = 60000L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 365 */   protected boolean shutdown_ = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final Channel handOff_;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final Map threads_;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected BlockedExecutionHandler blockedExecutionHandler_;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PooledExecutor()
/*     */   {
/* 388 */     this(new SynchronousChannel(), Integer.MAX_VALUE);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PooledExecutor(int paramInt)
/*     */   {
/* 397 */     this(new SynchronousChannel(), paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PooledExecutor(Channel paramChannel)
/*     */   {
/* 406 */     this(paramChannel, Integer.MAX_VALUE);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PooledExecutor(Channel paramChannel, int paramInt)
/*     */   {
/* 415 */     this.maximumPoolSize_ = paramInt;
/* 416 */     this.handOff_ = paramChannel;
/* 417 */     runWhenBlocked();
/* 418 */     this.threads_ = new HashMap();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized int getMaximumPoolSize()
/*     */   {
/* 427 */     return this.maximumPoolSize_;
/*     */   }
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
/*     */   public synchronized void setMaximumPoolSize(int paramInt)
/*     */   {
/* 441 */     if (paramInt <= 0) throw new IllegalArgumentException();
/* 442 */     this.maximumPoolSize_ = paramInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized int getMinimumPoolSize()
/*     */   {
/* 452 */     return this.minimumPoolSize_;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void setMinimumPoolSize(int paramInt)
/*     */   {
/* 463 */     if (paramInt < 0) throw new IllegalArgumentException();
/* 464 */     this.minimumPoolSize_ = paramInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized int getPoolSize()
/*     */   {
/* 473 */     return this.poolSize_;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized long getKeepAliveTime()
/*     */   {
/* 482 */     return this.keepAliveTime_;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void setKeepAliveTime(long paramLong)
/*     */   {
/* 491 */     this.keepAliveTime_ = paramLong;
/*     */   }
/*     */   
/*     */   protected synchronized BlockedExecutionHandler getBlockedExecutionHandler()
/*     */   {
/* 496 */     return this.blockedExecutionHandler_;
/*     */   }
/*     */   
/*     */   protected synchronized void setBlockedExecutionHandler(BlockedExecutionHandler paramBlockedExecutionHandler)
/*     */   {
/* 501 */     this.blockedExecutionHandler_ = paramBlockedExecutionHandler;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void addThread(Runnable paramRunnable)
/*     */   {
/* 509 */     Worker localWorker = new Worker(paramRunnable);
/* 510 */     Thread localThread = getThreadFactory().newThread(localWorker);
/* 511 */     this.threads_.put(localWorker, localThread);
/* 512 */     this.poolSize_ += 1;
/* 513 */     localThread.start();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int createThreads(int paramInt)
/*     */   {
/* 522 */     int i = 0;
/* 523 */     for (int j = 0; j < paramInt; j++) {
/* 524 */       synchronized (this) {
/* 525 */         if (this.poolSize_ < this.maximumPoolSize_) {
/* 526 */           addThread(null);
/* 527 */           i++;
/*     */         } else {
/*     */           break;
/*     */         }
/*     */       }
/*     */     }
/* 533 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void interruptAll()
/*     */   {
/* 544 */     for (Iterator localIterator = this.threads_.values().iterator(); localIterator.hasNext();) {
/* 545 */       Thread localThread = (Thread)localIterator.next();
/* 546 */       localThread.interrupt();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void shutdownNow()
/*     */   {
/* 556 */     shutdownNow(new DiscardWhenBlocked());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void shutdownNow(BlockedExecutionHandler paramBlockedExecutionHandler)
/*     */   {
/* 566 */     setBlockedExecutionHandler(paramBlockedExecutionHandler);
/* 567 */     this.shutdown_ = true;
/* 568 */     this.minimumPoolSize_ = (this.maximumPoolSize_ = 0);
/* 569 */     interruptAll();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void shutdownAfterProcessingCurrentlyQueuedTasks()
/*     */   {
/* 578 */     shutdownAfterProcessingCurrentlyQueuedTasks(new DiscardWhenBlocked());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void shutdownAfterProcessingCurrentlyQueuedTasks(BlockedExecutionHandler paramBlockedExecutionHandler)
/*     */   {
/* 588 */     setBlockedExecutionHandler(paramBlockedExecutionHandler);
/* 589 */     this.shutdown_ = true;
/* 590 */     if (this.poolSize_ == 0) {
/* 591 */       this.minimumPoolSize_ = (this.maximumPoolSize_ = 0);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized boolean isTerminatedAfterShutdown()
/*     */   {
/* 599 */     return (this.shutdown_) && (this.poolSize_ == 0);
/*     */   }
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
/*     */   public synchronized boolean awaitTerminationAfterShutdown(long paramLong)
/*     */     throws InterruptedException
/*     */   {
/* 614 */     if (!this.shutdown_)
/* 615 */       throw new IllegalStateException();
/* 616 */     if (this.poolSize_ == 0)
/* 617 */       return true;
/* 618 */     long l1 = paramLong;
/* 619 */     if (l1 <= 0L)
/* 620 */       return false;
/* 621 */     long l2 = System.currentTimeMillis();
/*     */     do {
/* 623 */       wait(l1);
/* 624 */       if (this.poolSize_ == 0)
/* 625 */         return true;
/* 626 */       l1 = paramLong - (System.currentTimeMillis() - l2);
/* 627 */     } while (l1 > 0L);
/* 628 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void awaitTerminationAfterShutdown()
/*     */     throws InterruptedException
/*     */   {
/* 641 */     if (!this.shutdown_)
/* 642 */       throw new IllegalStateException();
/* 643 */     while (this.poolSize_ > 0) {
/* 644 */       wait();
/*     */     }
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List drain()
/*     */   {
/* 665 */     int i = 0;
/* 666 */     Vector localVector = new Vector();
/*     */     try {
/*     */       for (;;) {
/* 669 */         Object localObject = this.handOff_.poll(0L);
/* 670 */         if (localObject == null) {
/*     */           break;
/*     */         }
/* 673 */         localVector.addElement(localObject);
/*     */       }
/*     */     } catch (InterruptedException localInterruptedException) {
/* 676 */       i = 1;
/*     */     }
/*     */     
/* 679 */     if (i != 0) Thread.currentThread().interrupt();
/* 680 */     return localVector;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected synchronized void workerDone(Worker paramWorker)
/*     */   {
/* 687 */     this.threads_.remove(paramWorker);
/* 688 */     if ((--this.poolSize_ == 0) && (this.shutdown_)) {
/* 689 */       this.maximumPoolSize_ = (this.minimumPoolSize_ = 0);
/* 690 */       notifyAll();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected Runnable getTask()
/*     */     throws InterruptedException
/*     */   {
/*     */     long l;
/* 699 */     synchronized (this) {
/* 700 */       if (this.poolSize_ > this.maximumPoolSize_) {
/* 701 */         Runnable localRunnable = null;return localRunnable; }
/* 702 */       l = this.shutdown_ ? 0L : this.keepAliveTime_;
/*     */     }
/* 704 */     if (l >= 0L) {
/* 705 */       return (Runnable)this.handOff_.poll(l);
/*     */     }
/* 707 */     return (Runnable)this.handOff_.take();
/*     */   }
/*     */   
/*     */ 
/*     */   protected class Worker
/*     */     implements Runnable
/*     */   {
/*     */     protected Runnable firstTask_;
/*     */     
/*     */ 
/* 717 */     protected Worker(Runnable paramRunnable) { this.firstTask_ = paramRunnable; }
/*     */     
/*     */     public void run() {
/*     */       try {
/* 721 */         Runnable localRunnable = this.firstTask_;
/* 722 */         this.firstTask_ = null;
/*     */         
/* 724 */         if (localRunnable != null) {
/* 725 */           localRunnable.run();
/*     */         }
/* 727 */         while ((localRunnable = PooledExecutor.this.getTask()) != null) {
/* 728 */           localRunnable.run();
/*     */         }
/*     */       }
/*     */       catch (InterruptedException localInterruptedException) {}finally
/*     */       {
/* 733 */         PooledExecutor.this.workerDone(this);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected static abstract interface BlockedExecutionHandler
/*     */   {
/*     */     public abstract boolean blockedAction(Runnable paramRunnable)
/*     */       throws InterruptedException;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected class RunWhenBlocked
/*     */     implements PooledExecutor.BlockedExecutionHandler
/*     */   {
/*     */     protected RunWhenBlocked() {}
/*     */     
/*     */ 
/*     */     public boolean blockedAction(Runnable paramRunnable)
/*     */     {
/* 756 */       paramRunnable.run();
/* 757 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 767 */   public void runWhenBlocked() { setBlockedExecutionHandler(new RunWhenBlocked()); }
/*     */   
/*     */   protected class WaitWhenBlocked implements PooledExecutor.BlockedExecutionHandler {
/*     */     protected WaitWhenBlocked() {}
/*     */     
/*     */     public boolean blockedAction(Runnable paramRunnable) throws InterruptedException {
/* 773 */       PooledExecutor.this.handOff_.put(paramRunnable);
/* 774 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void waitWhenBlocked()
/*     */   {
/* 783 */     setBlockedExecutionHandler(new WaitWhenBlocked());
/*     */   }
/*     */   
/*     */   protected class DiscardWhenBlocked implements PooledExecutor.BlockedExecutionHandler {
/*     */     protected DiscardWhenBlocked() {}
/*     */     
/* 789 */     public boolean blockedAction(Runnable paramRunnable) { return true; }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void discardWhenBlocked()
/*     */   {
/* 798 */     setBlockedExecutionHandler(new DiscardWhenBlocked());
/*     */   }
/*     */   
/*     */   protected class AbortWhenBlocked implements PooledExecutor.BlockedExecutionHandler {
/*     */     protected AbortWhenBlocked() {}
/*     */     
/*     */     public boolean blockedAction(Runnable paramRunnable) {
/* 805 */       throw new RuntimeException("Pool is blocked");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void abortWhenBlocked()
/*     */   {
/* 814 */     setBlockedExecutionHandler(new AbortWhenBlocked());
/*     */   }
/*     */   
/*     */ 
/*     */   protected class DiscardOldestWhenBlocked
/*     */     implements PooledExecutor.BlockedExecutionHandler
/*     */   {
/*     */     protected DiscardOldestWhenBlocked() {}
/*     */     
/*     */     public boolean blockedAction(Runnable paramRunnable)
/*     */       throws InterruptedException
/*     */     {
/* 826 */       PooledExecutor.this.handOff_.poll(0L);
/* 827 */       if (!PooledExecutor.this.handOff_.offer(paramRunnable, 0L))
/* 828 */         paramRunnable.run();
/* 829 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void discardOldestWhenBlocked()
/*     */   {
/* 838 */     setBlockedExecutionHandler(new DiscardOldestWhenBlocked());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void execute(Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/*     */     do
/*     */     {
/* 848 */       synchronized (this) {
/* 849 */         if (!this.shutdown_) {
/* 850 */           int i = this.poolSize_;
/*     */           
/*     */ 
/* 853 */           if (i < this.minimumPoolSize_) {
/* 854 */             addThread(paramRunnable);
/* 855 */             return;
/*     */           }
/*     */           
/*     */ 
/* 859 */           if (this.handOff_.offer(paramRunnable, 0L)) {
/* 860 */             return;
/*     */           }
/*     */           
/*     */ 
/* 864 */           if (i < this.maximumPoolSize_) {
/* 865 */             addThread(paramRunnable);
/* 866 */             return;
/*     */           }
/*     */           
/*     */         }
/*     */         
/*     */       }
/* 872 */     } while (!getBlockedExecutionHandler().blockedAction(paramRunnable));
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/PooledExecutor.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */