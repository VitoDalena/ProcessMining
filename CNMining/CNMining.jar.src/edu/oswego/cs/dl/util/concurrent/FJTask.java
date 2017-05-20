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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class FJTask
/*     */   implements Runnable
/*     */ {
/*     */   private volatile boolean done;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static FJTaskRunner getFJTaskRunner()
/*     */   {
/* 154 */     return (FJTaskRunner)Thread.currentThread();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static FJTaskRunnerGroup getFJTaskRunnerGroup()
/*     */   {
/* 163 */     return getFJTaskRunner().getGroup();
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
/*     */   public final boolean isDone()
/*     */   {
/* 176 */     return this.done;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final void setDone()
/*     */   {
/* 184 */     this.done = true;
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
/*     */   public void cancel()
/*     */   {
/* 197 */     setDone();
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
/*     */   public void reset()
/*     */   {
/* 210 */     this.done = false;
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
/*     */   public void start()
/*     */   {
/* 225 */     getFJTaskRunnerGroup().executeTask(this);
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
/*     */   public void fork()
/*     */   {
/* 250 */     getFJTaskRunner().push(this);
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
/*     */   public static void yield()
/*     */   {
/* 279 */     getFJTaskRunner().taskYield();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void join()
/*     */   {
/* 288 */     getFJTaskRunner().taskJoin(this);
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
/*     */   public static void invoke(FJTask paramFJTask)
/*     */   {
/* 302 */     if (!paramFJTask.isDone()) {
/* 303 */       paramFJTask.run();
/* 304 */       paramFJTask.setDone();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void coInvoke(FJTask paramFJTask1, FJTask paramFJTask2)
/*     */   {
/* 368 */     getFJTaskRunner().coInvoke(paramFJTask1, paramFJTask2);
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
/*     */   public static void coInvoke(FJTask[] paramArrayOfFJTask)
/*     */   {
/* 382 */     getFJTaskRunner().coInvoke(paramArrayOfFJTask);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static class Wrap
/*     */     extends FJTask
/*     */   {
/*     */     protected final Runnable runnable;
/*     */     
/*     */ 
/* 393 */     public Wrap(Runnable paramRunnable) { this.runnable = paramRunnable; }
/* 394 */     public void run() { this.runnable.run(); }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static class Seq
/*     */     extends FJTask
/*     */   {
/*     */     protected final FJTask[] tasks;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Seq(FJTask[] paramArrayOfFJTask)
/*     */     {
/* 412 */       this.tasks = paramArrayOfFJTask;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public Seq(FJTask paramFJTask1, FJTask paramFJTask2)
/*     */     {
/* 419 */       this.tasks = new FJTask[] { paramFJTask1, paramFJTask2 };
/*     */     }
/*     */     
/*     */     public void run() {
/* 423 */       for (int i = 0; i < this.tasks.length; i++) { FJTask.invoke(this.tasks[i]);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static FJTask seq(FJTask[] paramArrayOfFJTask)
/*     */   {
/* 433 */     return new Seq(paramArrayOfFJTask);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static class Par
/*     */     extends FJTask
/*     */   {
/*     */     protected final FJTask[] tasks;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Par(FJTask[] paramArrayOfFJTask)
/*     */     {
/* 451 */       this.tasks = paramArrayOfFJTask;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public Par(FJTask paramFJTask1, FJTask paramFJTask2)
/*     */     {
/* 458 */       this.tasks = new FJTask[] { paramFJTask1, paramFJTask2 };
/*     */     }
/*     */     
/*     */     public void run()
/*     */     {
/* 463 */       FJTask.coInvoke(this.tasks);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static FJTask par(FJTask[] paramArrayOfFJTask)
/*     */   {
/* 473 */     return new Par(paramArrayOfFJTask);
/*     */   }
/*     */   
/*     */ 
/*     */   public static class Seq2
/*     */     extends FJTask
/*     */   {
/*     */     protected final FJTask fst;
/*     */     
/*     */     protected final FJTask snd;
/*     */     
/*     */     public Seq2(FJTask paramFJTask1, FJTask paramFJTask2)
/*     */     {
/* 486 */       this.fst = paramFJTask1;
/* 487 */       this.snd = paramFJTask2;
/*     */     }
/*     */     
/* 490 */     public void run() { FJTask.invoke(this.fst);
/* 491 */       FJTask.invoke(this.snd);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static FJTask seq(FJTask paramFJTask1, FJTask paramFJTask2)
/*     */   {
/* 501 */     return new Seq2(paramFJTask1, paramFJTask2);
/*     */   }
/*     */   
/*     */ 
/*     */   public static class Par2
/*     */     extends FJTask
/*     */   {
/*     */     protected final FJTask fst;
/*     */     
/*     */     protected final FJTask snd;
/*     */     
/*     */     public Par2(FJTask paramFJTask1, FJTask paramFJTask2)
/*     */     {
/* 514 */       this.fst = paramFJTask1;
/* 515 */       this.snd = paramFJTask2;
/*     */     }
/*     */     
/* 518 */     public void run() { FJTask.coInvoke(this.fst, this.snd); }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static FJTask par(FJTask paramFJTask1, FJTask paramFJTask2)
/*     */   {
/* 528 */     return new Par2(paramFJTask1, paramFJTask2);
/*     */   }
/*     */   
/*     */   public abstract void run();
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/FJTask.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */