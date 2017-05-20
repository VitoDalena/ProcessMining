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
/*     */ public class CyclicBarrier
/*     */   implements Barrier
/*     */ {
/*     */   protected final int parties_;
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
/*  89 */   protected boolean broken_ = false;
/*  90 */   protected Runnable barrierCommand_ = null;
/*     */   protected int count_;
/*  92 */   protected int resets_ = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CyclicBarrier(int paramInt)
/*     */   {
/* 100 */     this(paramInt, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CyclicBarrier(int paramInt, Runnable paramRunnable)
/*     */   {
/* 109 */     if (paramInt <= 0) throw new IllegalArgumentException();
/* 110 */     this.parties_ = paramInt;
/* 111 */     this.count_ = paramInt;
/* 112 */     this.barrierCommand_ = paramRunnable;
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
/*     */   public synchronized Runnable setBarrierCommand(Runnable paramRunnable)
/*     */   {
/* 125 */     Runnable localRunnable = this.barrierCommand_;
/* 126 */     this.barrierCommand_ = paramRunnable;
/* 127 */     return localRunnable;
/*     */   }
/*     */   
/* 130 */   public synchronized boolean broken() { return this.broken_; }
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
/*     */   public synchronized void restart()
/*     */   {
/* 143 */     this.broken_ = false;
/* 144 */     this.resets_ += 1;
/* 145 */     this.count_ = this.parties_;
/* 146 */     notifyAll();
/*     */   }
/*     */   
/*     */   public int parties() {
/* 150 */     return this.parties_;
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
/*     */   public int barrier()
/*     */     throws InterruptedException, BrokenBarrierException
/*     */   {
/* 178 */     return doBarrier(false, 0L);
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
/*     */   public int attemptBarrier(long paramLong)
/*     */     throws InterruptedException, TimeoutException, BrokenBarrierException
/*     */   {
/* 211 */     return doBarrier(true, paramLong);
/*     */   }
/*     */   
/*     */   protected synchronized int doBarrier(boolean paramBoolean, long paramLong)
/*     */     throws InterruptedException, TimeoutException, BrokenBarrierException
/*     */   {
/* 217 */     int i = --this.count_;
/*     */     
/* 219 */     if (this.broken_) {
/* 220 */       throw new BrokenBarrierException(i);
/*     */     }
/* 222 */     if (Thread.interrupted()) {
/* 223 */       this.broken_ = true;
/* 224 */       notifyAll();
/* 225 */       throw new InterruptedException();
/*     */     }
/* 227 */     if (i == 0) {
/* 228 */       this.count_ = this.parties_;
/* 229 */       this.resets_ += 1;
/* 230 */       notifyAll();
/*     */       try {
/* 232 */         if (this.barrierCommand_ != null)
/* 233 */           this.barrierCommand_.run();
/* 234 */         return 0;
/*     */       }
/*     */       catch (RuntimeException localRuntimeException) {
/* 237 */         this.broken_ = true;
/* 238 */         return 0;
/*     */       }
/*     */     }
/* 241 */     if ((paramBoolean) && (paramLong <= 0L)) {
/* 242 */       this.broken_ = true;
/* 243 */       notifyAll();
/* 244 */       throw new TimeoutException(paramLong);
/*     */     }
/*     */     
/* 247 */     int j = this.resets_;
/* 248 */     long l1 = paramBoolean ? System.currentTimeMillis() : 0L;
/* 249 */     long l2 = paramLong;
/*     */     do {
/*     */       do {
/* 252 */         try { wait(l2);
/*     */         }
/*     */         catch (InterruptedException localInterruptedException)
/*     */         {
/* 256 */           if (this.resets_ == j) {
/* 257 */             this.broken_ = true;
/* 258 */             notifyAll();
/* 259 */             throw localInterruptedException;
/*     */           }
/*     */           
/* 262 */           Thread.currentThread().interrupt();
/*     */         }
/*     */         
/*     */ 
/* 266 */         if (this.broken_) {
/* 267 */           throw new BrokenBarrierException(i);
/*     */         }
/* 269 */         if (j != this.resets_) {
/* 270 */           return i;
/*     */         }
/* 272 */       } while (!paramBoolean);
/* 273 */       l2 = paramLong - (System.currentTimeMillis() - l1);
/* 274 */     } while (l2 > 0L);
/* 275 */     this.broken_ = true;
/* 276 */     notifyAll();
/* 277 */     throw new TimeoutException(paramLong);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/CyclicBarrier.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */