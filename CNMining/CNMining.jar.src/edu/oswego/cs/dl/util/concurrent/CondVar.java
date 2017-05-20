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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CondVar
/*     */ {
/*     */   protected final Sync mutex_;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CondVar(Sync paramSync)
/*     */   {
/* 150 */     this.mutex_ = paramSync;
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
/*     */   public void await()
/*     */     throws InterruptedException
/*     */   {
/* 165 */     if (Thread.interrupted()) throw new InterruptedException();
/*     */     try {
/* 167 */       synchronized (this) {
/* 168 */         this.mutex_.release();
/*     */         try {
/* 170 */           wait();
/*     */         }
/*     */         catch (InterruptedException localInterruptedException1) {
/* 173 */           notify();
/* 174 */           throw localInterruptedException1;
/*     */         }
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/* 180 */       int i = 0;
/*     */       for (;;) {
/*     */         try {
/* 183 */           this.mutex_.acquire();
/*     */         }
/*     */         catch (InterruptedException localInterruptedException2)
/*     */         {
/* 187 */           i = 1;
/*     */         }
/*     */       }
/* 190 */       if (i != 0) {
/* 191 */         Thread.currentThread().interrupt();
/*     */       }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean timedwait(long paramLong)
/*     */     throws InterruptedException
/*     */   {
/* 214 */     if (Thread.interrupted()) throw new InterruptedException();
/* 215 */     boolean bool = false;
/*     */     try {
/* 217 */       synchronized (this) {
/* 218 */         this.mutex_.release();
/*     */         try {
/* 220 */           if (paramLong > 0L) {
/* 221 */             long l = System.currentTimeMillis();
/* 222 */             wait(paramLong);
/* 223 */             bool = System.currentTimeMillis() - l <= paramLong;
/*     */           }
/*     */         }
/*     */         catch (InterruptedException localInterruptedException1) {
/* 227 */           notify();
/* 228 */           throw localInterruptedException1;
/*     */         }
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/* 234 */       int i = 0;
/*     */       for (;;) {
/*     */         try {
/* 237 */           this.mutex_.acquire();
/*     */         }
/*     */         catch (InterruptedException localInterruptedException2)
/*     */         {
/* 241 */           i = 1;
/*     */         }
/*     */       }
/* 244 */       if (i != 0) {
/* 245 */         Thread.currentThread().interrupt();
/*     */       }
/*     */     }
/* 248 */     return bool;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void signal()
/*     */   {
/* 257 */     notify();
/*     */   }
/*     */   
/*     */   public synchronized void broadcast()
/*     */   {
/* 262 */     notifyAll();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/CondVar.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */