/*     */ package edu.uci.ics.jung.algorithms.layout.util;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.util.IterativeContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VisRunner
/*     */   implements Relaxer, Runnable
/*     */ {
/*     */   protected boolean running;
/*     */   protected IterativeContext process;
/*     */   protected boolean stop;
/*     */   protected boolean manualSuspend;
/*     */   protected Thread thread;
/*  34 */   protected long sleepTime = 100L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public VisRunner(IterativeContext process)
/*     */   {
/*  41 */     this.process = process;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public long getSleepTime()
/*     */   {
/*  48 */     return this.sleepTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setSleepTime(long sleepTime)
/*     */   {
/*  55 */     this.sleepTime = sleepTime;
/*     */   }
/*     */   
/*     */   public void prerelax() {
/*  59 */     this.manualSuspend = true;
/*  60 */     long timeNow = System.currentTimeMillis();
/*  61 */     while ((System.currentTimeMillis() - timeNow < 500L) && (!this.process.done())) {
/*  62 */       this.process.step();
/*     */     }
/*  64 */     this.manualSuspend = false;
/*     */   }
/*     */   
/*     */   public void pause() {
/*  68 */     this.manualSuspend = true;
/*     */   }
/*     */   
/*     */   public void relax()
/*     */   {
/*  73 */     stop();
/*  74 */     this.stop = false;
/*  75 */     this.thread = new Thread(this);
/*  76 */     this.thread.setPriority(1);
/*  77 */     this.thread.start();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  83 */   public Object pauseObject = new String("PAUSE OBJECT");
/*     */   
/*     */   public void resume() {
/*  86 */     this.manualSuspend = false;
/*  87 */     if (!this.running) {
/*  88 */       prerelax();
/*  89 */       relax();
/*     */     } else {
/*  91 */       synchronized (this.pauseObject) {
/*  92 */         this.pauseObject.notifyAll();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public synchronized void stop() {
/*  98 */     if (this.thread != null) {
/*  99 */       this.manualSuspend = false;
/* 100 */       this.stop = true;
/*     */       try
/*     */       {
/* 103 */         this.thread.interrupt();
/*     */       }
/*     */       catch (Exception ex) {
/*     */         try {
/* 107 */           Thread.sleep(1000L);
/*     */         } catch (InterruptedException ie) {}
/*     */       }
/* 110 */       synchronized (this.pauseObject) {
/* 111 */         this.pauseObject.notifyAll();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void run() {
/* 117 */     this.running = true;
/*     */     try {
/* 119 */       while ((!this.process.done()) && (!this.stop)) {
/* 120 */         synchronized (this.pauseObject) {
/* 121 */           while ((this.manualSuspend) && (!this.stop)) {
/*     */             try {
/* 123 */               this.pauseObject.wait();
/*     */             }
/*     */             catch (InterruptedException e) {}
/*     */           }
/*     */         }
/*     */         
/* 129 */         this.process.step();
/*     */         
/* 131 */         if (this.stop) {
/*     */           return;
/*     */         }
/*     */         try {
/* 135 */           Thread.sleep(this.sleepTime);
/*     */         }
/*     */         catch (InterruptedException ie) {}
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/* 142 */       this.running = false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/layout/util/VisRunner.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */