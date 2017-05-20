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
/*     */ public class WaitableLong
/*     */   extends SynchronizedLong
/*     */ {
/*     */   public WaitableLong(long paramLong)
/*     */   {
/*  29 */     super(paramLong);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public WaitableLong(long paramLong, Object paramObject)
/*     */   {
/*  37 */     super(paramLong, paramObject);
/*     */   }
/*     */   
/*     */   public long set(long paramLong)
/*     */   {
/*  42 */     synchronized (this.lock_) {
/*  43 */       this.lock_.notifyAll();
/*  44 */       long l = super.set(paramLong);return l;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean commit(long paramLong1, long paramLong2) {
/*  49 */     synchronized (this.lock_) {
/*  50 */       boolean bool1 = super.commit(paramLong1, paramLong2);
/*  51 */       if (bool1) this.lock_.notifyAll();
/*  52 */       boolean bool2 = bool1;return bool2;
/*     */     }
/*     */   }
/*     */   
/*     */   public long increment() {
/*  57 */     synchronized (this.lock_) {
/*  58 */       this.lock_.notifyAll();
/*  59 */       long l = super.increment();return l;
/*     */     }
/*     */   }
/*     */   
/*     */   public long decrement() {
/*  64 */     synchronized (this.lock_) {
/*  65 */       this.lock_.notifyAll();
/*  66 */       long l = super.decrement();return l;
/*     */     }
/*     */   }
/*     */   
/*     */   public long add(long paramLong) {
/*  71 */     synchronized (this.lock_) {
/*  72 */       this.lock_.notifyAll();
/*  73 */       long l = super.add(paramLong);return l;
/*     */     }
/*     */   }
/*     */   
/*     */   public long subtract(long paramLong) {
/*  78 */     synchronized (this.lock_) {
/*  79 */       this.lock_.notifyAll();
/*  80 */       long l = super.subtract(paramLong);return l;
/*     */     }
/*     */   }
/*     */   
/*     */   public long multiply(long paramLong) {
/*  85 */     synchronized (this.lock_) {
/*  86 */       this.lock_.notifyAll();
/*  87 */       long l = super.multiply(paramLong);return l;
/*     */     }
/*     */   }
/*     */   
/*     */   public long divide(long paramLong) {
/*  92 */     synchronized (this.lock_) {
/*  93 */       this.lock_.notifyAll();
/*  94 */       long l = super.divide(paramLong);return l;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void whenEqual(long paramLong, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 105 */     synchronized (this.lock_) {
/* 106 */       while (this.value_ != paramLong) this.lock_.wait();
/* 107 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenNotEqual(long paramLong, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 116 */     synchronized (this.lock_) {
/* 117 */       while (this.value_ == paramLong) this.lock_.wait();
/* 118 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenLessEqual(long paramLong, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 127 */     synchronized (this.lock_) {
/* 128 */       while (this.value_ > paramLong) this.lock_.wait();
/* 129 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenLess(long paramLong, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 138 */     synchronized (this.lock_) {
/* 139 */       while (this.value_ >= paramLong) this.lock_.wait();
/* 140 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenGreaterEqual(long paramLong, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 149 */     synchronized (this.lock_) {
/* 150 */       while (this.value_ < paramLong) this.lock_.wait();
/* 151 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenGreater(long paramLong, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 160 */     synchronized (this.lock_) {
/* 161 */       while (this.value_ <= paramLong) this.lock_.wait();
/* 162 */       if (paramRunnable != null) paramRunnable.run();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/WaitableLong.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */