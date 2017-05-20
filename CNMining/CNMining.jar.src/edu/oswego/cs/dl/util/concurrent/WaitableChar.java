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
/*     */ public class WaitableChar
/*     */   extends SynchronizedChar
/*     */ {
/*     */   public WaitableChar(char paramChar)
/*     */   {
/*  29 */     super(paramChar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public WaitableChar(char paramChar, Object paramObject)
/*     */   {
/*  37 */     super(paramChar, paramObject);
/*     */   }
/*     */   
/*     */   public char set(char paramChar)
/*     */   {
/*  42 */     synchronized (this.lock_) {
/*  43 */       this.lock_.notifyAll();
/*  44 */       char c = super.set(paramChar);return c;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean commit(char paramChar1, char paramChar2) {
/*  49 */     synchronized (this.lock_) {
/*  50 */       boolean bool1 = super.commit(paramChar1, paramChar2);
/*  51 */       if (bool1) this.lock_.notifyAll();
/*  52 */       boolean bool2 = bool1;return bool2;
/*     */     }
/*     */   }
/*     */   
/*     */   public char add(char paramChar)
/*     */   {
/*  58 */     synchronized (this.lock_) {
/*  59 */       this.lock_.notifyAll();
/*  60 */       char c = super.add(paramChar);return c;
/*     */     }
/*     */   }
/*     */   
/*     */   public char subtract(char paramChar) {
/*  65 */     synchronized (this.lock_) {
/*  66 */       this.lock_.notifyAll();
/*  67 */       char c = super.subtract(paramChar);return c;
/*     */     }
/*     */   }
/*     */   
/*     */   public char multiply(char paramChar) {
/*  72 */     synchronized (this.lock_) {
/*  73 */       this.lock_.notifyAll();
/*  74 */       char c = super.multiply(paramChar);return c;
/*     */     }
/*     */   }
/*     */   
/*     */   public char divide(char paramChar) {
/*  79 */     synchronized (this.lock_) {
/*  80 */       this.lock_.notifyAll();
/*  81 */       char c = super.divide(paramChar);return c;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void whenEqual(char paramChar, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/*  92 */     synchronized (this.lock_) {
/*  93 */       while (this.value_ != paramChar) this.lock_.wait();
/*  94 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenNotEqual(char paramChar, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 103 */     synchronized (this.lock_) {
/* 104 */       while (this.value_ == paramChar) this.lock_.wait();
/* 105 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenLessEqual(char paramChar, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 114 */     synchronized (this.lock_) {
/* 115 */       while (this.value_ > paramChar) this.lock_.wait();
/* 116 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenLess(char paramChar, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 125 */     synchronized (this.lock_) {
/* 126 */       while (this.value_ >= paramChar) this.lock_.wait();
/* 127 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenGreaterEqual(char paramChar, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 136 */     synchronized (this.lock_) {
/* 137 */       while (this.value_ < paramChar) this.lock_.wait();
/* 138 */       if (paramRunnable != null) { paramRunnable.run();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void whenGreater(char paramChar, Runnable paramRunnable)
/*     */     throws InterruptedException
/*     */   {
/* 147 */     synchronized (this.lock_) {
/* 148 */       while (this.value_ <= paramChar) this.lock_.wait();
/* 149 */       if (paramRunnable != null) paramRunnable.run();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/WaitableChar.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */