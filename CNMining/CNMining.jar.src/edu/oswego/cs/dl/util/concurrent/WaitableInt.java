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
/*     */ public class WaitableInt
/*     */   extends SynchronizedInt
/*     */ {
/*     */   public WaitableInt(int paramInt)
/*     */   {
/*  29 */     super(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public WaitableInt(int paramInt, Object paramObject)
/*     */   {
/*  37 */     super(paramInt, paramObject);
/*     */   }
/*     */   
/*     */   public int set(int paramInt)
/*     */   {
/*  42 */     synchronized (this.lock_) {
/*  43 */       this.lock_.notifyAll();
/*  44 */       int i = super.set(paramInt);return i;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean commit(int paramInt1, int paramInt2) {
/*  49 */     synchronized (this.lock_) {
/*  50 */       boolean bool1 = super.commit(paramInt1, paramInt2);
/*  51 */       if (bool1) this.lock_.notifyAll();
/*  52 */       boolean bool2 = bool1;return bool2;
/*     */     }
/*     */   }
/*     */   
/*     */   public int increment() {
/*  57 */     synchronized (this.lock_) {
/*  58 */       this.lock_.notifyAll();
/*  59 */       int i = super.increment();return i;
/*     */     }
/*     */   }
/*     */   
/*     */   public int decrement() {
/*  64 */     synchronized (this.lock_) {
/*  65 */       this.lock_.notifyAll();
/*  66 */       int i = super.decrement();return i;
/*     */     }
/*     */   }
/*     */   
/*     */   public int add(int paramInt) {
/*  71 */     synchronized (this.lock_) {
/*  72 */       this.lock_.notifyAll();
/*  73 */       int i = super.add(paramInt);return i;
/*     */     }
/*     */   }
/*     */   
/*     */   public int subtract(int paramInt) {
/*  78 */     synchronized (this.lock_) {
/*  79 */       this.lock_.notifyAll();
/*  80 */       int i = super.subtract(paramInt);return i;
/*     */     }
/*     */   }
/*     */   
/*     */   public int multiply(int paramInt) {
/*  85 */     synchronized (this.lock_) {
/*  86 */       this.lock_.notifyAll();
/*  87 */       int i = super.multiply(paramInt);return i;
/*     */     }
/*     */   }
/*     */   
/*     */   public int divide(int paramInt) {
/*  92 */     synchronized (this.lock_) {
/*  93 */       this.lock_.notifyAll();
/*  94 */       int i = super.divide(paramInt);return i;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void whenEqual(int paramInt, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 105 */     synchronized (this.lock_) {
/* 106 */       while (this.value_ != paramInt) this.lock_.wait();
/* 107 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenNotEqual(int paramInt, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 116 */     synchronized (this.lock_) {
/* 117 */       while (this.value_ == paramInt) this.lock_.wait();
/* 118 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenLessEqual(int paramInt, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 127 */     synchronized (this.lock_) {
/* 128 */       while (this.value_ > paramInt) this.lock_.wait();
/* 129 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenLess(int paramInt, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 138 */     synchronized (this.lock_) {
/* 139 */       while (this.value_ >= paramInt) this.lock_.wait();
/* 140 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenGreaterEqual(int paramInt, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 149 */     synchronized (this.lock_) {
/* 150 */       while (this.value_ < paramInt) this.lock_.wait();
/* 151 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenGreater(int paramInt, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 160 */     synchronized (this.lock_) {
/* 161 */       while (this.value_ <= paramInt) this.lock_.wait();
/* 162 */       if (paramRunnable != null) paramRunnable.run();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/WaitableInt.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */