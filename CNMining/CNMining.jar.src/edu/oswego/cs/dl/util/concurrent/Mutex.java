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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Mutex
/*     */   implements Sync
/*     */ {
/* 112 */   protected boolean inuse_ = false;
/*     */   
/*     */   public void acquire() throws InterruptedException {
/* 115 */     if (Thread.interrupted()) throw new InterruptedException();
/* 116 */     synchronized (this) {
/*     */       try {
/* 118 */         while (this.inuse_) wait();
/* 119 */         this.inuse_ = true;
/*     */       }
/*     */       catch (InterruptedException localInterruptedException) {
/* 122 */         notify();
/* 123 */         throw localInterruptedException;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public synchronized void release() {
/* 129 */     this.inuse_ = false;
/* 130 */     notify();
/*     */   }
/*     */   
/*     */   public boolean attempt(long paramLong) throws InterruptedException
/*     */   {
/* 135 */     if (Thread.interrupted()) throw new InterruptedException();
/* 136 */     synchronized (this) { boolean bool1;
/* 137 */       if (!this.inuse_) {
/* 138 */         this.inuse_ = true;
/* 139 */         bool1 = true;return bool1;
/*     */       }
/* 141 */       if (paramLong <= 0L) {
/* 142 */         bool1 = false;return bool1;
/*     */       }
/* 144 */       long l1 = paramLong;
/* 145 */       long l2 = System.currentTimeMillis();
/*     */       try {
/*     */         do {
/* 148 */           wait(l1);
/* 149 */           if (!this.inuse_) {
/* 150 */             this.inuse_ = true;
/* 151 */             bool2 = true;return bool2;
/*     */           }
/*     */           
/* 154 */           l1 = paramLong - (System.currentTimeMillis() - l2);
/* 155 */         } while (l1 > 0L);
/* 156 */         boolean bool2 = false;return bool2;
/*     */ 
/*     */       }
/*     */       catch (InterruptedException localInterruptedException)
/*     */       {
/* 161 */         notify();
/* 162 */         throw localInterruptedException;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/Mutex.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */