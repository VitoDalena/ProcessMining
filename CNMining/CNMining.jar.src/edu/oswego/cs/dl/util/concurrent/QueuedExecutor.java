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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class QueuedExecutor
/*     */   extends ThreadFactoryUser
/*     */   implements Executor
/*     */ {
/*     */   protected Thread thread_;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  46 */   protected static Runnable ENDTASK = new Runnable()
/*     */   {
/*     */     public void run() {}
/*     */   };
/*     */   
/*     */   protected volatile boolean shutdown_;
/*     */   
/*     */   protected final Channel queue_;
/*     */   protected final RunLoop runLoop_;
/*     */   
/*     */   public synchronized Thread getThread()
/*     */   {
/*  58 */     return this.thread_;
/*     */   }
/*     */   
/*     */   protected synchronized void clearThread()
/*     */   {
/*  63 */     this.thread_ = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected class RunLoop
/*     */     implements Runnable
/*     */   {
/*     */     protected RunLoop() {}
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public void run()
/*     */     {
/*     */       try
/*     */       {
/*  81 */         while (!QueuedExecutor.this.shutdown_) {
/*  82 */           Runnable localRunnable = (Runnable)QueuedExecutor.this.queue_.take();
/*  83 */           if (localRunnable == QueuedExecutor.ENDTASK) {
/*  84 */             QueuedExecutor.this.shutdown_ = true;
/*  85 */             break;
/*     */           }
/*  87 */           if (localRunnable == null) break;
/*  88 */           localRunnable.run();
/*  89 */           localRunnable = null;
/*     */         }
/*     */         
/*     */ 
/*     */       }
/*     */       catch (InterruptedException localInterruptedException) {}finally
/*     */       {
/*     */ 
/*  97 */         QueuedExecutor.this.clearThread();
/*     */       }
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
/*     */   public QueuedExecutor(Channel paramChannel)
/*     */   {
/* 117 */     this.queue_ = paramChannel;
/* 118 */     this.runLoop_ = new RunLoop();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public QueuedExecutor()
/*     */   {
/* 128 */     this(new BoundedLinkedQueue());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void restart()
/*     */   {
/* 139 */     if ((this.thread_ == null) && (!this.shutdown_)) {
/* 140 */       this.thread_ = this.threadFactory_.newThread(this.runLoop_);
/* 141 */       this.thread_.start();
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
/*     */   public void execute(Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 156 */     restart();
/* 157 */     this.queue_.put(paramRunnable);
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
/*     */   public synchronized void shutdownAfterProcessingCurrentlyQueuedTasks()
/*     */   {
/* 170 */     if ((this.thread_ != null) && (!this.shutdown_)) {
/* 171 */       try { this.queue_.put(ENDTASK);
/*     */       } catch (InterruptedException localInterruptedException) {
/* 173 */         Thread.currentThread().interrupt();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void shutdownAfterProcessingCurrentTask()
/*     */   {
/* 185 */     this.shutdown_ = true;
/* 186 */     if (this.thread_ != null) {
/*     */       try {
/* 188 */         while (this.queue_.poll(0L) != null) {}
/* 189 */         this.queue_.put(ENDTASK);
/*     */       }
/*     */       catch (InterruptedException localInterruptedException) {
/* 192 */         Thread.currentThread().interrupt();
/*     */       }
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
/*     */   public synchronized void shutdownNow()
/*     */   {
/* 207 */     this.shutdown_ = true;
/* 208 */     if (this.thread_ != null) {
/* 209 */       this.thread_.interrupt();
/* 210 */       shutdownAfterProcessingCurrentTask();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/QueuedExecutor.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */