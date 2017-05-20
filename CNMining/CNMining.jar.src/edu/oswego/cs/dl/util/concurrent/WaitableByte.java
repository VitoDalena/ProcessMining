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
/*     */ public class WaitableByte
/*     */   extends SynchronizedByte
/*     */ {
/*     */   public WaitableByte(byte paramByte)
/*     */   {
/*  29 */     super(paramByte);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public WaitableByte(byte paramByte, Object paramObject)
/*     */   {
/*  37 */     super(paramByte, paramObject);
/*     */   }
/*     */   
/*     */   public byte set(byte paramByte)
/*     */   {
/*  42 */     synchronized (this.lock_) {
/*  43 */       this.lock_.notifyAll();
/*  44 */       byte b = super.set(paramByte);return b;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean commit(byte paramByte1, byte paramByte2) {
/*  49 */     synchronized (this.lock_) {
/*  50 */       boolean bool1 = super.commit(paramByte1, paramByte2);
/*  51 */       if (bool1) this.lock_.notifyAll();
/*  52 */       boolean bool2 = bool1;return bool2;
/*     */     }
/*     */   }
/*     */   
/*     */   public byte increment() {
/*  57 */     synchronized (this.lock_) {
/*  58 */       this.lock_.notifyAll();
/*  59 */       byte b = super.increment();return b;
/*     */     }
/*     */   }
/*     */   
/*     */   public byte decrement() {
/*  64 */     synchronized (this.lock_) {
/*  65 */       this.lock_.notifyAll();
/*  66 */       byte b = super.decrement();return b;
/*     */     }
/*     */   }
/*     */   
/*     */   public byte add(byte paramByte) {
/*  71 */     synchronized (this.lock_) {
/*  72 */       this.lock_.notifyAll();
/*  73 */       byte b = super.add(paramByte);return b;
/*     */     }
/*     */   }
/*     */   
/*     */   public byte subtract(byte paramByte) {
/*  78 */     synchronized (this.lock_) {
/*  79 */       this.lock_.notifyAll();
/*  80 */       byte b = super.subtract(paramByte);return b;
/*     */     }
/*     */   }
/*     */   
/*     */   public byte multiply(byte paramByte) {
/*  85 */     synchronized (this.lock_) {
/*  86 */       this.lock_.notifyAll();
/*  87 */       byte b = super.multiply(paramByte);return b;
/*     */     }
/*     */   }
/*     */   
/*     */   public byte divide(byte paramByte) {
/*  92 */     synchronized (this.lock_) {
/*  93 */       this.lock_.notifyAll();
/*  94 */       byte b = super.divide(paramByte);return b;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void whenEqual(byte paramByte, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 105 */     synchronized (this.lock_) {
/* 106 */       while (this.value_ != paramByte) this.lock_.wait();
/* 107 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenNotEqual(byte paramByte, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 116 */     synchronized (this.lock_) {
/* 117 */       while (this.value_ == paramByte) this.lock_.wait();
/* 118 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenLessEqual(byte paramByte, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 127 */     synchronized (this.lock_) {
/* 128 */       while (this.value_ > paramByte) this.lock_.wait();
/* 129 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenLess(byte paramByte, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 138 */     synchronized (this.lock_) {
/* 139 */       while (this.value_ >= paramByte) this.lock_.wait();
/* 140 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenGreaterEqual(byte paramByte, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 149 */     synchronized (this.lock_) {
/* 150 */       while (this.value_ < paramByte) this.lock_.wait();
/* 151 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenGreater(byte paramByte, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 160 */     synchronized (this.lock_) {
/* 161 */       while (this.value_ <= paramByte) this.lock_.wait();
/* 162 */       if (paramRunnable != null) paramRunnable.run();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/WaitableByte.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */