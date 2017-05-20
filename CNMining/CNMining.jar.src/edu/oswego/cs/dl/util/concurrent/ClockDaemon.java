/*     */ package edu.oswego.cs.dl.util.concurrent;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClockDaemon
/*     */   extends ThreadFactoryUser
/*     */ {
/*  57 */   protected final Heap heap_ = new Heap(DefaultChannelCapacity.get());
/*     */   
/*     */   protected Thread thread_;
/*     */   
/*     */   protected final RunLoop runLoop_;
/*     */   
/*     */   protected static class TaskNode
/*     */     implements Comparable
/*     */   {
/*     */     final Runnable command;
/*     */     final long period;
/*     */     private long timeToRun_;
/*  69 */     private boolean cancelled_ = false;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  74 */     synchronized void setCancelled() { this.cancelled_ = true; }
/*  75 */     synchronized boolean getCancelled() { return this.cancelled_; }
/*     */     
/*  77 */     synchronized void setTimeToRun(long paramLong) { this.timeToRun_ = paramLong; }
/*  78 */     synchronized long getTimeToRun() { return this.timeToRun_; }
/*     */     
/*     */     public int compareTo(Object paramObject)
/*     */     {
/*  82 */       long l1 = getTimeToRun();
/*  83 */       long l2 = ((TaskNode)paramObject).getTimeToRun();
/*  84 */       return l1 == l2 ? 0 : l1 < l2 ? -1 : 1;
/*     */     }
/*     */     
/*     */     TaskNode(long paramLong1, Runnable paramRunnable, long paramLong2) {
/*  88 */       this.timeToRun_ = paramLong1;this.command = paramRunnable;this.period = paramLong2;
/*     */     }
/*     */     
/*  91 */     TaskNode(long paramLong, Runnable paramRunnable) { this(paramLong, paramRunnable, -1L); }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object executeAt(Date paramDate, Runnable paramRunnable)
/*     */   {
/* 103 */     TaskNode localTaskNode = new TaskNode(paramDate.getTime(), paramRunnable);
/* 104 */     this.heap_.insert(localTaskNode);
/* 105 */     restart();
/* 106 */     return localTaskNode;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object executeAfterDelay(long paramLong, Runnable paramRunnable)
/*     */   {
/* 161 */     long l = System.currentTimeMillis() + paramLong;
/* 162 */     TaskNode localTaskNode = new TaskNode(l, paramRunnable);
/* 163 */     this.heap_.insert(localTaskNode);
/* 164 */     restart();
/* 165 */     return localTaskNode;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object executePeriodically(long paramLong, Runnable paramRunnable, boolean paramBoolean)
/*     */   {
/* 228 */     if (paramLong <= 0L) { throw new IllegalArgumentException();
/*     */     }
/* 230 */     long l = System.currentTimeMillis();
/* 231 */     if (!paramBoolean) { l += paramLong;
/*     */     }
/* 233 */     TaskNode localTaskNode = new TaskNode(l, paramRunnable, paramLong);
/* 234 */     this.heap_.insert(localTaskNode);
/* 235 */     restart();
/* 236 */     return localTaskNode;
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
/*     */   public static void cancel(Object paramObject)
/*     */   {
/* 253 */     ((TaskNode)paramObject).setCancelled();
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
/*     */   public synchronized Thread getThread()
/*     */   {
/* 268 */     return this.thread_;
/*     */   }
/*     */   
/*     */   protected synchronized void clearThread()
/*     */   {
/* 273 */     this.thread_ = null;
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
/* 284 */     if (this.thread_ == null) {
/* 285 */       this.thread_ = this.threadFactory_.newThread(this.runLoop_);
/* 286 */       this.thread_.start();
/*     */     }
/*     */     else {
/* 289 */       notify();
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
/*     */   public synchronized void shutDown()
/*     */   {
/* 302 */     this.heap_.clear();
/* 303 */     if (this.thread_ != null)
/* 304 */       this.thread_.interrupt();
/* 305 */     this.thread_ = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected synchronized TaskNode nextTask()
/*     */   {
/*     */     try
/*     */     {
/* 314 */       while (!Thread.interrupted())
/*     */       {
/*     */ 
/*     */ 
/* 318 */         TaskNode localTaskNode = (TaskNode)this.heap_.peek();
/*     */         
/* 320 */         if (localTaskNode == null) {
/* 321 */           wait();
/*     */         }
/*     */         else {
/* 324 */           long l1 = System.currentTimeMillis();
/* 325 */           long l2 = localTaskNode.getTimeToRun();
/*     */           
/* 327 */           if (l2 > l1) {
/* 328 */             wait(l2 - l1);
/*     */           }
/*     */           else {
/* 331 */             localTaskNode = (TaskNode)this.heap_.extract();
/*     */             
/* 333 */             if (!localTaskNode.getCancelled())
/*     */             {
/* 335 */               if (localTaskNode.period > 0L) {
/* 336 */                 localTaskNode.setTimeToRun(l1 + localTaskNode.period);
/* 337 */                 this.heap_.insert(localTaskNode);
/*     */               }
/*     */               
/* 340 */               return localTaskNode;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {}
/*     */     
/* 348 */     return null;
/*     */   }
/*     */   
/*     */   protected class RunLoop
/*     */     implements Runnable
/*     */   {
/*     */     protected RunLoop() {}
/*     */     
/*     */     public void run()
/*     */     {
/*     */       try
/*     */       {
/*     */         for (;;)
/*     */         {
/* 362 */           ClockDaemon.TaskNode localTaskNode = ClockDaemon.this.nextTask();
/* 363 */           if (localTaskNode == null) break;
/* 364 */           localTaskNode.command.run();
/*     */         }
/*     */         
/*     */       }
/*     */       finally
/*     */       {
/* 370 */         ClockDaemon.this.clearThread();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ClockDaemon()
/*     */   {
/* 382 */     this.runLoop_ = new RunLoop();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/ClockDaemon.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */