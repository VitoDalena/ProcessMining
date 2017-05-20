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
/*     */ public class CountDown
/*     */   implements Sync
/*     */ {
/*     */   protected final int initialCount_;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int count_;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CountDown(int paramInt)
/*     */   {
/*  60 */     this.count_ = (this.initialCount_ = paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void acquire()
/*     */     throws InterruptedException
/*     */   {
/*  69 */     if (Thread.interrupted()) throw new InterruptedException();
/*  70 */     synchronized (this) {
/*  71 */       while (this.count_ > 0) {
/*  72 */         wait();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean attempt(long paramLong) throws InterruptedException {
/*  78 */     if (Thread.interrupted()) throw new InterruptedException();
/*  79 */     synchronized (this) {
/*  80 */       if (this.count_ <= 0) {
/*  81 */         boolean bool1 = true;return bool1; }
/*  82 */       if (paramLong <= 0L) {
/*  83 */         boolean bool2 = false;return bool2;
/*     */       }
/*  85 */       long l1 = paramLong;
/*  86 */       long l2 = System.currentTimeMillis();
/*     */       do {
/*  88 */         wait(l1);
/*  89 */         if (this.count_ <= 0) {
/*  90 */           boolean bool3 = true;return bool3;
/*     */         }
/*  92 */         l1 = paramLong - (System.currentTimeMillis() - l2);
/*  93 */       } while (l1 > 0L);
/*  94 */       boolean bool4 = false;return bool4;
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
/*     */   public synchronized void release()
/*     */   {
/* 107 */     if (--this.count_ == 0)
/* 108 */       notifyAll();
/*     */   }
/*     */   
/*     */   public int initialCount() {
/* 112 */     return this.initialCount_;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized int currentCount()
/*     */   {
/* 120 */     return this.count_;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/CountDown.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */