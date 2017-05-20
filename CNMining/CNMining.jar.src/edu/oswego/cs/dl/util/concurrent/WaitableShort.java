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
/*     */ public class WaitableShort
/*     */   extends SynchronizedShort
/*     */ {
/*     */   public WaitableShort(short paramShort)
/*     */   {
/*  29 */     super(paramShort);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public WaitableShort(short paramShort, Object paramObject)
/*     */   {
/*  37 */     super(paramShort, paramObject);
/*     */   }
/*     */   
/*     */   public short set(short paramShort)
/*     */   {
/*  42 */     synchronized (this.lock_) {
/*  43 */       this.lock_.notifyAll();
/*  44 */       short s = super.set(paramShort);return s;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean commit(short paramShort1, short paramShort2) {
/*  49 */     synchronized (this.lock_) {
/*  50 */       boolean bool1 = super.commit(paramShort1, paramShort2);
/*  51 */       if (bool1) this.lock_.notifyAll();
/*  52 */       boolean bool2 = bool1;return bool2;
/*     */     }
/*     */   }
/*     */   
/*     */   public short increment() {
/*  57 */     synchronized (this.lock_) {
/*  58 */       this.lock_.notifyAll();
/*  59 */       short s = super.increment();return s;
/*     */     }
/*     */   }
/*     */   
/*     */   public short decrement() {
/*  64 */     synchronized (this.lock_) {
/*  65 */       this.lock_.notifyAll();
/*  66 */       short s = super.decrement();return s;
/*     */     }
/*     */   }
/*     */   
/*     */   public short add(short paramShort) {
/*  71 */     synchronized (this.lock_) {
/*  72 */       this.lock_.notifyAll();
/*  73 */       short s = super.add(paramShort);return s;
/*     */     }
/*     */   }
/*     */   
/*     */   public short subtract(short paramShort) {
/*  78 */     synchronized (this.lock_) {
/*  79 */       this.lock_.notifyAll();
/*  80 */       short s = super.subtract(paramShort);return s;
/*     */     }
/*     */   }
/*     */   
/*     */   public short multiply(short paramShort) {
/*  85 */     synchronized (this.lock_) {
/*  86 */       this.lock_.notifyAll();
/*  87 */       short s = super.multiply(paramShort);return s;
/*     */     }
/*     */   }
/*     */   
/*     */   public short divide(short paramShort) {
/*  92 */     synchronized (this.lock_) {
/*  93 */       this.lock_.notifyAll();
/*  94 */       short s = super.divide(paramShort);return s;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void whenEqual(short paramShort, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 105 */     synchronized (this.lock_) {
/* 106 */       while (this.value_ != paramShort) this.lock_.wait();
/* 107 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenNotEqual(short paramShort, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 116 */     synchronized (this.lock_) {
/* 117 */       while (this.value_ == paramShort) this.lock_.wait();
/* 118 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenLessEqual(short paramShort, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 127 */     synchronized (this.lock_) {
/* 128 */       while (this.value_ > paramShort) this.lock_.wait();
/* 129 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenLess(short paramShort, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 138 */     synchronized (this.lock_) {
/* 139 */       while (this.value_ >= paramShort) this.lock_.wait();
/* 140 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenGreaterEqual(short paramShort, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 149 */     synchronized (this.lock_) {
/* 150 */       while (this.value_ < paramShort) this.lock_.wait();
/* 151 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenGreater(short paramShort, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 160 */     synchronized (this.lock_) {
/* 161 */       while (this.value_ <= paramShort) this.lock_.wait();
/* 162 */       if (paramRunnable != null) paramRunnable.run();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/WaitableShort.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */