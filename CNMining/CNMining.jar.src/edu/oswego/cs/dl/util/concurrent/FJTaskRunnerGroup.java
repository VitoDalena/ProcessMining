/*     */ package edu.oswego.cs.dl.util.concurrent;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FJTaskRunnerGroup
/*     */   implements Executor
/*     */ {
/*     */   protected final FJTaskRunner[] threads;
/* 128 */   protected final LinkedQueue entryQueue = new LinkedQueue();
/*     */   
/*     */ 
/* 131 */   protected int activeCount = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 136 */   protected int nstarted = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static final boolean COLLECT_STATS = true;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 151 */   long initTime = 0L;
/*     */   
/*     */ 
/* 154 */   int entries = 0;
/*     */   
/*     */ 
/*     */   static final int DEFAULT_SCAN_PRIORITY = 2;
/*     */   
/*     */ 
/*     */   static final long SCANS_PER_SLEEP = 15L;
/*     */   
/*     */ 
/*     */   static final long MAX_SLEEP_TIME = 100L;
/*     */   
/*     */ 
/*     */ 
/*     */   public FJTaskRunnerGroup(int paramInt)
/*     */   {
/* 169 */     this.threads = new FJTaskRunner[paramInt];
/* 170 */     initializeThreads();
/* 171 */     this.initTime = System.currentTimeMillis();
/*     */   }
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
/* 184 */     if ((paramRunnable instanceof FJTask)) {
/* 185 */       this.entryQueue.put((FJTask)paramRunnable);
/*     */     }
/*     */     else {
/* 188 */       this.entryQueue.put(new FJTask.Wrap(paramRunnable));
/*     */     }
/* 190 */     signalNewTask();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void executeTask(FJTask paramFJTask)
/*     */   {
/*     */     try
/*     */     {
/* 199 */       this.entryQueue.put(paramFJTask);
/* 200 */       signalNewTask();
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {
/* 203 */       Thread.currentThread().interrupt();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void invoke(Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 215 */     InvokableFJTask localInvokableFJTask = new InvokableFJTask(paramRunnable);
/* 216 */     this.entryQueue.put(localInvokableFJTask);
/* 217 */     signalNewTask();
/* 218 */     localInvokableFJTask.awaitTermination();
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
/*     */   public void interruptAll()
/*     */   {
/* 237 */     Thread localThread = Thread.currentThread();
/* 238 */     int i = 0;
/*     */     
/* 240 */     for (int j = 0; j < this.threads.length; j++) {
/* 241 */       FJTaskRunner localFJTaskRunner = this.threads[j];
/* 242 */       if (localFJTaskRunner == localThread) {
/* 243 */         i = 1;
/*     */       } else
/* 245 */         localFJTaskRunner.interrupt();
/*     */     }
/* 247 */     if (i != 0) {
/* 248 */       localThread.interrupt();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void setScanPriorities(int paramInt)
/*     */   {
/* 260 */     for (int i = 0; i < this.threads.length; i++) {
/* 261 */       FJTaskRunner localFJTaskRunner = this.threads[i];
/* 262 */       localFJTaskRunner.setScanPriority(paramInt);
/* 263 */       if (!localFJTaskRunner.active) { localFJTaskRunner.setPriority(paramInt);
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
/*     */   public synchronized void setRunPriorities(int paramInt)
/*     */   {
/* 277 */     for (int i = 0; i < this.threads.length; i++) {
/* 278 */       FJTaskRunner localFJTaskRunner = this.threads[i];
/* 279 */       localFJTaskRunner.setRunPriority(paramInt);
/* 280 */       if (localFJTaskRunner.active) { localFJTaskRunner.setPriority(paramInt);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public int size()
/*     */   {
/* 288 */     return this.threads.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized int getActiveCount()
/*     */   {
/* 298 */     return this.activeCount;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void stats()
/*     */   {
/* 368 */     long l1 = System.currentTimeMillis() - this.initTime;
/* 369 */     double d = l1 / 1000.0D;
/* 370 */     long l2 = 0L;
/* 371 */     long l3 = 0L;
/* 372 */     long l4 = 0L;
/*     */     
/* 374 */     System.out.print("Thread\tQ Cap\tScans\tNew\tRuns\n");
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 381 */     for (int i = 0; i < this.threads.length; i++) {
/* 382 */       FJTaskRunner localFJTaskRunner = this.threads[i];
/* 383 */       int j = localFJTaskRunner.runs;
/* 384 */       l2 += j;
/*     */       
/* 386 */       int k = localFJTaskRunner.scans;
/* 387 */       l3 += k;
/*     */       
/* 389 */       int m = localFJTaskRunner.steals;
/* 390 */       l4 += m;
/*     */       
/* 392 */       String str = getActive(localFJTaskRunner) ? "*" : " ";
/*     */       
/*     */ 
/* 395 */       System.out.print("T" + i + str + "\t" + localFJTaskRunner.deqSize() + "\t" + k + "\t" + m + "\t" + j + "\n");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 403 */     System.out.print("Total\t    \t" + l3 + "\t" + l4 + "\t" + l2 + "\n");
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 410 */     System.out.print("Execute: " + this.entries);
/*     */     
/* 412 */     System.out.print("\tTime: " + d);
/*     */     
/* 414 */     long l5 = 0L;
/* 415 */     if (d != 0.0D) { l5 = Math.round(l2 / d);
/*     */     }
/* 417 */     System.out.println("\tRate: " + l5);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected FJTaskRunner[] getArray()
/*     */   {
/* 429 */     return this.threads;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected FJTask pollEntryQueue()
/*     */   {
/*     */     try
/*     */     {
/* 439 */       return (FJTask)this.entryQueue.poll(0L);
/*     */     }
/*     */     catch (InterruptedException localInterruptedException)
/*     */     {
/* 443 */       Thread.currentThread().interrupt(); }
/* 444 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized boolean getActive(FJTaskRunner paramFJTaskRunner)
/*     */   {
/* 456 */     return paramFJTaskRunner.active;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void setActive(FJTaskRunner paramFJTaskRunner)
/*     */   {
/* 466 */     if (!paramFJTaskRunner.active) {
/* 467 */       paramFJTaskRunner.active = true;
/* 468 */       this.activeCount += 1;
/* 469 */       if (this.nstarted < this.threads.length) {
/* 470 */         this.threads[(this.nstarted++)].start();
/*     */       } else {
/* 472 */         notifyAll();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected synchronized void setInactive(FJTaskRunner paramFJTaskRunner)
/*     */   {
/* 481 */     if (paramFJTaskRunner.active) {
/* 482 */       paramFJTaskRunner.active = false;
/* 483 */       this.activeCount -= 1;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void checkActive(FJTaskRunner paramFJTaskRunner, long paramLong)
/*     */   {
/* 536 */     setInactive(paramFJTaskRunner);
/*     */     
/*     */     try
/*     */     {
/* 540 */       if ((this.activeCount == 0) && (this.entryQueue.peek() == null)) {
/* 541 */         wait();
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/* 547 */         long l = paramLong / 15L;
/* 548 */         if (l > 100L) l = 100L;
/* 549 */         int i = l == 0L ? 1 : 0;
/* 550 */         wait(l, i);
/*     */       }
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {
/* 554 */       notify();
/* 555 */       Thread.currentThread().interrupt();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void signalNewTask()
/*     */   {
/* 566 */     this.entries += 1;
/* 567 */     if (this.nstarted < this.threads.length) {
/* 568 */       this.threads[(this.nstarted++)].start();
/*     */     } else {
/* 570 */       notify();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void initializeThreads()
/*     */   {
/* 578 */     for (int i = 0; i < this.threads.length; i++) { this.threads[i] = new FJTaskRunner(this);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected static final class InvokableFJTask
/*     */     extends FJTask
/*     */   {
/*     */     protected final Runnable wrapped;
/*     */     
/*     */ 
/* 590 */     protected boolean terminated = false;
/*     */     
/* 592 */     protected InvokableFJTask(Runnable paramRunnable) { this.wrapped = paramRunnable; }
/*     */     
/*     */     public void run() {
/*     */       try {
/* 596 */         if ((this.wrapped instanceof FJTask)) {
/* 597 */           FJTask.invoke((FJTask)this.wrapped);
/*     */         } else {
/* 599 */           this.wrapped.run();
/*     */         }
/*     */       } finally {
/* 602 */         setTerminated();
/*     */       }
/*     */     }
/*     */     
/*     */     protected synchronized void setTerminated() {
/* 607 */       this.terminated = true;
/* 608 */       notifyAll();
/*     */     }
/*     */     
/*     */     protected synchronized void awaitTermination() throws InterruptedException {
/* 612 */       while (!this.terminated) wait();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/FJTaskRunnerGroup.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */