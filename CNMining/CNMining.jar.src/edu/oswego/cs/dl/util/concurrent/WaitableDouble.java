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
/*     */ public class WaitableDouble
/*     */   extends SynchronizedDouble
/*     */ {
/*     */   public WaitableDouble(double paramDouble)
/*     */   {
/*  29 */     super(paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public WaitableDouble(double paramDouble, Object paramObject)
/*     */   {
/*  37 */     super(paramDouble, paramObject);
/*     */   }
/*     */   
/*     */   public double set(double paramDouble)
/*     */   {
/*  42 */     synchronized (this.lock_) {
/*  43 */       this.lock_.notifyAll();
/*  44 */       double d = super.set(paramDouble);return d;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean commit(double paramDouble1, double paramDouble2) {
/*  49 */     synchronized (this.lock_) {
/*  50 */       boolean bool1 = super.commit(paramDouble1, paramDouble2);
/*  51 */       if (bool1) this.lock_.notifyAll();
/*  52 */       boolean bool2 = bool1;return bool2;
/*     */     }
/*     */   }
/*     */   
/*     */   public double add(double paramDouble)
/*     */   {
/*  58 */     synchronized (this.lock_) {
/*  59 */       this.lock_.notifyAll();
/*  60 */       double d = super.add(paramDouble);return d;
/*     */     }
/*     */   }
/*     */   
/*     */   public double subtract(double paramDouble) {
/*  65 */     synchronized (this.lock_) {
/*  66 */       this.lock_.notifyAll();
/*  67 */       double d = super.subtract(paramDouble);return d;
/*     */     }
/*     */   }
/*     */   
/*     */   public double multiply(double paramDouble) {
/*  72 */     synchronized (this.lock_) {
/*  73 */       this.lock_.notifyAll();
/*  74 */       double d = super.multiply(paramDouble);return d;
/*     */     }
/*     */   }
/*     */   
/*     */   public double divide(double paramDouble) {
/*  79 */     synchronized (this.lock_) {
/*  80 */       this.lock_.notifyAll();
/*  81 */       double d = super.divide(paramDouble);return d;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void whenEqual(double paramDouble, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/*  92 */     synchronized (this.lock_) {
/*  93 */       while (this.value_ != paramDouble) this.lock_.wait();
/*  94 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenNotEqual(double paramDouble, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 103 */     synchronized (this.lock_) {
/* 104 */       while (this.value_ == paramDouble) this.lock_.wait();
/* 105 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenLessEqual(double paramDouble, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 114 */     synchronized (this.lock_) {
/* 115 */       while (this.value_ > paramDouble) this.lock_.wait();
/* 116 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenLess(double paramDouble, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 125 */     synchronized (this.lock_) {
/* 126 */       while (this.value_ >= paramDouble) this.lock_.wait();
/* 127 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenGreaterEqual(double paramDouble, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 136 */     synchronized (this.lock_) {
/* 137 */       while (this.value_ < paramDouble) this.lock_.wait();
/* 138 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenGreater(double paramDouble, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 147 */     synchronized (this.lock_) {
/* 148 */       while (this.value_ <= paramDouble) this.lock_.wait();
/* 149 */       if (paramRunnable != null) paramRunnable.run();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/WaitableDouble.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */