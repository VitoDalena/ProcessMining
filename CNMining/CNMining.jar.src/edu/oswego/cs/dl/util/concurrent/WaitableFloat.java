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
/*     */ public class WaitableFloat
/*     */   extends SynchronizedFloat
/*     */ {
/*     */   public WaitableFloat(float paramFloat)
/*     */   {
/*  29 */     super(paramFloat);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public WaitableFloat(float paramFloat, Object paramObject)
/*     */   {
/*  37 */     super(paramFloat, paramObject);
/*     */   }
/*     */   
/*     */   public float set(float paramFloat)
/*     */   {
/*  42 */     synchronized (this.lock_) {
/*  43 */       this.lock_.notifyAll();
/*  44 */       float f = super.set(paramFloat);return f;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean commit(float paramFloat1, float paramFloat2) {
/*  49 */     synchronized (this.lock_) {
/*  50 */       boolean bool1 = super.commit(paramFloat1, paramFloat2);
/*  51 */       if (bool1) this.lock_.notifyAll();
/*  52 */       boolean bool2 = bool1;return bool2;
/*     */     }
/*     */   }
/*     */   
/*     */   public float add(float paramFloat)
/*     */   {
/*  58 */     synchronized (this.lock_) {
/*  59 */       this.lock_.notifyAll();
/*  60 */       float f = super.add(paramFloat);return f;
/*     */     }
/*     */   }
/*     */   
/*     */   public float subtract(float paramFloat) {
/*  65 */     synchronized (this.lock_) {
/*  66 */       this.lock_.notifyAll();
/*  67 */       float f = super.subtract(paramFloat);return f;
/*     */     }
/*     */   }
/*     */   
/*     */   public float multiply(float paramFloat) {
/*  72 */     synchronized (this.lock_) {
/*  73 */       this.lock_.notifyAll();
/*  74 */       float f = super.multiply(paramFloat);return f;
/*     */     }
/*     */   }
/*     */   
/*     */   public float divide(float paramFloat) {
/*  79 */     synchronized (this.lock_) {
/*  80 */       this.lock_.notifyAll();
/*  81 */       float f = super.divide(paramFloat);return f;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void whenEqual(float paramFloat, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/*  92 */     synchronized (this.lock_) {
/*  93 */       while (this.value_ != paramFloat) this.lock_.wait();
/*  94 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenNotEqual(float paramFloat, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 103 */     synchronized (this.lock_) {
/* 104 */       while (this.value_ == paramFloat) this.lock_.wait();
/* 105 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenLessEqual(float paramFloat, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 114 */     synchronized (this.lock_) {
/* 115 */       while (this.value_ > paramFloat) this.lock_.wait();
/* 116 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenLess(float paramFloat, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 125 */     synchronized (this.lock_) {
/* 126 */       while (this.value_ >= paramFloat) this.lock_.wait();
/* 127 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenGreaterEqual(float paramFloat, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 136 */     synchronized (this.lock_) {
/* 137 */       while (this.value_ < paramFloat) this.lock_.wait();
/* 138 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenGreater(float paramFloat, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 147 */     synchronized (this.lock_) {
/* 148 */       while (this.value_ <= paramFloat) this.lock_.wait();
/* 149 */       if (paramRunnable != null) paramRunnable.run();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/WaitableFloat.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */