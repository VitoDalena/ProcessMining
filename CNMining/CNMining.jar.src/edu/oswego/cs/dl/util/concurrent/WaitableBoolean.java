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
/*     */ public class WaitableBoolean
/*     */   extends SynchronizedBoolean
/*     */ {
/*     */   public WaitableBoolean(boolean paramBoolean)
/*     */   {
/*  25 */     super(paramBoolean);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public WaitableBoolean(boolean paramBoolean, Object paramObject)
/*     */   {
/*  33 */     super(paramBoolean, paramObject);
/*     */   }
/*     */   
/*     */   public boolean set(boolean paramBoolean)
/*     */   {
/*  38 */     synchronized (this.lock_) {
/*  39 */       this.lock_.notifyAll();
/*  40 */       boolean bool = super.set(paramBoolean);return bool;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean commit(boolean paramBoolean1, boolean paramBoolean2) {
/*  45 */     synchronized (this.lock_) {
/*  46 */       boolean bool1 = super.commit(paramBoolean1, paramBoolean2);
/*  47 */       if (bool1) this.lock_.notifyAll();
/*  48 */       boolean bool2 = bool1;return bool2;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean complement() {
/*  53 */     synchronized (this.lock_) {
/*  54 */       this.lock_.notifyAll();
/*  55 */       boolean bool = super.complement();return bool;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean and(boolean paramBoolean) {
/*  60 */     synchronized (this.lock_) {
/*  61 */       this.lock_.notifyAll();
/*  62 */       boolean bool = super.and(paramBoolean);return bool;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean or(boolean paramBoolean) {
/*  67 */     synchronized (this.lock_) {
/*  68 */       this.lock_.notifyAll();
/*  69 */       boolean bool = super.or(paramBoolean);return bool;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean xor(boolean paramBoolean) {
/*  74 */     synchronized (this.lock_) {
/*  75 */       this.lock_.notifyAll();
/*  76 */       boolean bool = super.xor(paramBoolean);return bool;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void whenFalse(Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/*  86 */     synchronized (this.lock_) {
/*  87 */       while (this.value_) this.lock_.wait();
/*  88 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenTrue(Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/*  97 */     synchronized (this.lock_) {
/*  98 */       while (!this.value_) this.lock_.wait();
/*  99 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void whenEqual(boolean paramBoolean, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 109 */     synchronized (this.lock_) {
/* 110 */       while (this.value_ != paramBoolean) this.lock_.wait();
/* 111 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenNotEqual(boolean paramBoolean, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 120 */     synchronized (this.lock_) {
/* 121 */       while (this.value_ == paramBoolean) this.lock_.wait();
/* 122 */       if (paramRunnable != null) paramRunnable.run();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/WaitableBoolean.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */