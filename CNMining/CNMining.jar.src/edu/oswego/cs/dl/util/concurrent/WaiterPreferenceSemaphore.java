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
/*     */ 
/*     */ public final class WaiterPreferenceSemaphore
/*     */   extends Semaphore
/*     */ {
/*     */   public WaiterPreferenceSemaphore(long paramLong)
/*     */   {
/*  56 */     super(paramLong);
/*     */   }
/*     */   
/*  59 */   protected long waits_ = 0L;
/*     */   
/*     */   public void acquire() throws InterruptedException {
/*  62 */     if (Thread.interrupted()) throw new InterruptedException();
/*  63 */     synchronized (this)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*  68 */       if (this.permits_ > this.waits_) {
/*  69 */         this.permits_ -= 1L;
/*  70 */         return;
/*     */       }
/*     */       
/*  73 */       this.waits_ += 1L;
/*     */       try {
/*     */         do {
/*  76 */           wait();
/*  77 */         } while (this.permits_ <= 0L);
/*  78 */         this.waits_ -= 1L;
/*  79 */         this.permits_ -= 1L;
/*  80 */         return;
/*     */ 
/*     */       }
/*     */       catch (InterruptedException localInterruptedException)
/*     */       {
/*  85 */         this.waits_ -= 1L;
/*  86 */         notify();
/*  87 */         throw localInterruptedException;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean attempt(long paramLong) throws InterruptedException
/*     */   {
/*  94 */     if (Thread.interrupted()) { throw new InterruptedException();
/*     */     }
/*  96 */     synchronized (this) { boolean bool1;
/*  97 */       if (this.permits_ > this.waits_) {
/*  98 */         this.permits_ -= 1L;
/*  99 */         bool1 = true;return bool1;
/*     */       }
/* 101 */       if (paramLong <= 0L) {
/* 102 */         bool1 = false;return bool1;
/*     */       }
/* 104 */       this.waits_ += 1L;
/*     */       
/* 106 */       long l1 = System.currentTimeMillis();
/* 107 */       long l2 = paramLong;
/*     */       try
/*     */       {
/*     */         do {
/* 111 */           wait(l2);
/* 112 */           if (this.permits_ > 0L) {
/* 113 */             this.waits_ -= 1L;
/* 114 */             this.permits_ -= 1L;
/* 115 */             bool2 = true;return bool2;
/*     */           }
/*     */           
/* 118 */           l2 = paramLong - (System.currentTimeMillis() - l1);
/* 119 */         } while (l2 > 0L);
/* 120 */         this.waits_ -= 1L;
/* 121 */         boolean bool2 = false;return bool2;
/*     */ 
/*     */       }
/*     */       catch (InterruptedException localInterruptedException)
/*     */       {
/*     */ 
/* 127 */         this.waits_ -= 1L;
/* 128 */         notify();
/* 129 */         throw localInterruptedException;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public synchronized void release()
/*     */   {
/* 136 */     this.permits_ += 1L;
/* 137 */     notify();
/*     */   }
/*     */   
/*     */   public synchronized void release(long paramLong)
/*     */   {
/* 142 */     this.permits_ += paramLong;
/* 143 */     for (long l = 0L; l < paramLong; l += 1L) notify();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/WaiterPreferenceSemaphore.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */