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
/*     */ public class Semaphore
/*     */   implements Sync
/*     */ {
/*     */   protected long permits_;
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
/*     */   public Semaphore(long paramLong)
/*     */   {
/* 100 */     this.permits_ = paramLong;
/*     */   }
/*     */   
/*     */   public void acquire() throws InterruptedException
/*     */   {
/* 105 */     if (Thread.interrupted()) throw new InterruptedException();
/* 106 */     synchronized (this) {
/*     */       try {
/* 108 */         while (this.permits_ <= 0L) wait();
/* 109 */         this.permits_ -= 1L;
/*     */       }
/*     */       catch (InterruptedException localInterruptedException) {
/* 112 */         notify();
/* 113 */         throw localInterruptedException;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean attempt(long paramLong) throws InterruptedException
/*     */   {
/* 120 */     if (Thread.interrupted()) { throw new InterruptedException();
/*     */     }
/* 122 */     synchronized (this) { boolean bool1;
/* 123 */       if (this.permits_ > 0L) {
/* 124 */         this.permits_ -= 1L;
/* 125 */         bool1 = true;return bool1;
/*     */       }
/* 127 */       if (paramLong <= 0L) {
/* 128 */         bool1 = false;return bool1;
/*     */       }
/*     */       try {
/* 131 */         long l1 = System.currentTimeMillis();
/* 132 */         long l2 = paramLong;
/*     */         do
/*     */         {
/* 135 */           wait(l2);
/* 136 */           if (this.permits_ > 0L) {
/* 137 */             this.permits_ -= 1L;
/* 138 */             bool2 = true;return bool2;
/*     */           }
/*     */           
/* 141 */           l2 = paramLong - (System.currentTimeMillis() - l1);
/* 142 */         } while (l2 > 0L);
/* 143 */         boolean bool2 = false;return bool2;
/*     */ 
/*     */       }
/*     */       catch (InterruptedException localInterruptedException)
/*     */       {
/* 148 */         notify();
/* 149 */         throw localInterruptedException;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized void release()
/*     */   {
/* 157 */     this.permits_ += 1L;
/* 158 */     notify();
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
/*     */ 
/*     */   public synchronized void release(long paramLong)
/*     */   {
/* 173 */     if (paramLong < 0L) { throw new IllegalArgumentException("Negative argument");
/*     */     }
/* 175 */     this.permits_ += paramLong;
/* 176 */     for (long l = 0L; l < paramLong; l += 1L) { notify();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized long permits()
/*     */   {
/* 185 */     return this.permits_;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/Semaphore.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */