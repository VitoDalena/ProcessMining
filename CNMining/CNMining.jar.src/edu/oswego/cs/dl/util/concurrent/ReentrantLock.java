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
/*     */ 
/*     */ 
/*     */ public class ReentrantLock
/*     */   implements Sync
/*     */ {
/*  29 */   protected Thread owner_ = null;
/*  30 */   protected long holds_ = 0L;
/*     */   
/*     */   public void acquire() throws InterruptedException {
/*  33 */     if (Thread.interrupted()) throw new InterruptedException();
/*  34 */     Thread localThread = Thread.currentThread();
/*  35 */     synchronized (this) {
/*  36 */       if (localThread == this.owner_) {
/*  37 */         this.holds_ += 1L;
/*     */       } else {
/*     */         try {
/*  40 */           while (this.owner_ != null) wait();
/*  41 */           this.owner_ = localThread;
/*  42 */           this.holds_ = 1L;
/*     */         }
/*     */         catch (InterruptedException localInterruptedException) {
/*  45 */           notify();
/*  46 */           throw localInterruptedException;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean attempt(long paramLong) throws InterruptedException
/*     */   {
/*  54 */     if (Thread.interrupted()) throw new InterruptedException();
/*  55 */     Thread localThread = Thread.currentThread();
/*  56 */     synchronized (this) { boolean bool1;
/*  57 */       if (localThread == this.owner_) {
/*  58 */         this.holds_ += 1L;
/*  59 */         bool1 = true;return bool1;
/*     */       }
/*  61 */       if (this.owner_ == null) {
/*  62 */         this.owner_ = localThread;
/*  63 */         this.holds_ = 1L;
/*  64 */         bool1 = true;return bool1;
/*     */       }
/*  66 */       if (paramLong <= 0L) {
/*  67 */         bool1 = false;return bool1;
/*     */       }
/*  69 */       long l1 = paramLong;
/*  70 */       long l2 = System.currentTimeMillis();
/*     */       try {
/*     */         do {
/*  73 */           wait(l1);
/*  74 */           if (localThread == this.owner_) {
/*  75 */             this.holds_ += 1L;
/*  76 */             bool2 = true;return bool2;
/*     */           }
/*  78 */           if (this.owner_ == null) {
/*  79 */             this.owner_ = localThread;
/*  80 */             this.holds_ = 1L;
/*  81 */             bool2 = true;return bool2;
/*     */           }
/*     */           
/*  84 */           l1 = paramLong - (System.currentTimeMillis() - l2);
/*  85 */         } while (l1 > 0L);
/*  86 */         boolean bool2 = false;return bool2;
/*     */ 
/*     */       }
/*     */       catch (InterruptedException localInterruptedException)
/*     */       {
/*  91 */         notify();
/*  92 */         throw localInterruptedException;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void release()
/*     */   {
/* 103 */     if (Thread.currentThread() != this.owner_) {
/* 104 */       throw new Error("Illegal Lock usage");
/*     */     }
/* 106 */     if (--this.holds_ == 0L) {
/* 107 */       this.owner_ = null;
/* 108 */       notify();
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
/*     */ 
/*     */ 
/*     */   public synchronized void release(long paramLong)
/*     */   {
/* 123 */     if ((Thread.currentThread() != this.owner_) || (paramLong > this.holds_)) {
/* 124 */       throw new Error("Illegal Lock usage");
/*     */     }
/* 126 */     this.holds_ -= paramLong;
/* 127 */     if (this.holds_ == 0L) {
/* 128 */       this.owner_ = null;
/* 129 */       notify();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized long holds()
/*     */   {
/* 140 */     if (Thread.currentThread() != this.owner_) return 0L;
/* 141 */     return this.holds_;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/ReentrantLock.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */