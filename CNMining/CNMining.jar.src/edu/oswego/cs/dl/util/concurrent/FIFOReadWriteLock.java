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
/*     */ public class FIFOReadWriteLock
/*     */   implements ReadWriteLock
/*     */ {
/*  46 */   protected final FIFOSemaphore entryLock = new FIFOSemaphore(1L);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected volatile int readers;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int exreaders;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void acquireRead()
/*     */     throws InterruptedException
/*     */   {
/*  68 */     this.entryLock.acquire();
/*  69 */     this.readers += 1;
/*  70 */     this.entryLock.release();
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
/*     */   protected synchronized void releaseRead()
/*     */   {
/*  86 */     if (++this.exreaders == this.readers) {
/*  87 */       notify();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void acquireWrite() throws InterruptedException
/*     */   {
/*  93 */     this.entryLock.acquire();
/*     */     
/*     */ 
/*     */ 
/*  97 */     int i = this.readers;
/*     */     try
/*     */     {
/* 100 */       synchronized (this) {
/* 101 */         while (this.exreaders != i) {
/* 102 */           wait();
/*     */         }
/*     */       }
/*     */     } catch (InterruptedException localInterruptedException) {
/* 106 */       this.entryLock.release();
/* 107 */       throw localInterruptedException;
/*     */     }
/*     */   }
/*     */   
/*     */   protected void releaseWrite() {
/* 112 */     this.entryLock.release();
/*     */   }
/*     */   
/*     */   protected boolean attemptRead(long paramLong) throws InterruptedException {
/* 116 */     if (!this.entryLock.attempt(paramLong)) {
/* 117 */       return false;
/*     */     }
/* 119 */     this.readers += 1;
/* 120 */     this.entryLock.release();
/* 121 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean attemptWrite(long paramLong) throws InterruptedException {
/* 125 */     long l1 = paramLong <= 0L ? 0L : System.currentTimeMillis();
/*     */     
/* 127 */     if (!this.entryLock.attempt(paramLong)) {
/* 128 */       return false;
/*     */     }
/* 130 */     int i = this.readers;
/*     */     try
/*     */     {
/* 133 */       synchronized (this) {
/* 134 */         while (this.exreaders != i) {
/* 135 */           long l2 = paramLong <= 0L ? 0L : paramLong - (System.currentTimeMillis() - l1);
/*     */           
/*     */ 
/* 138 */           if (l2 <= 0L) {
/* 139 */             this.entryLock.release();
/* 140 */             boolean bool2 = false;return bool2;
/*     */           }
/*     */           
/* 143 */           wait(l2);
/*     */         }
/* 145 */         boolean bool1 = true;return bool1;
/*     */       }
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {
/* 149 */       this.entryLock.release();
/* 150 */       throw localInterruptedException;
/*     */     }
/*     */   }
/*     */   
/*     */   protected class ReaderSync implements Sync {
/*     */     protected ReaderSync() {}
/*     */     
/*     */     public void acquire() throws InterruptedException {
/* 158 */       FIFOReadWriteLock.this.acquireRead();
/*     */     }
/*     */     
/* 161 */     public void release() { FIFOReadWriteLock.this.releaseRead(); }
/*     */     
/*     */ 
/* 164 */     public boolean attempt(long paramLong) throws InterruptedException { return FIFOReadWriteLock.this.attemptRead(paramLong); }
/*     */   }
/*     */   
/*     */   protected class WriterSync implements Sync {
/*     */     protected WriterSync() {}
/*     */     
/* 170 */     public void acquire() throws InterruptedException { FIFOReadWriteLock.this.acquireWrite(); }
/*     */     
/*     */     public void release() {
/* 173 */       FIFOReadWriteLock.this.releaseWrite();
/*     */     }
/*     */     
/* 176 */     public boolean attempt(long paramLong) throws InterruptedException { return FIFOReadWriteLock.this.attemptWrite(paramLong); }
/*     */   }
/*     */   
/*     */ 
/* 180 */   protected final Sync readerSync = new ReaderSync();
/* 181 */   protected final Sync writerSync = new WriterSync();
/*     */   
/* 183 */   public Sync writeLock() { return this.writerSync; }
/* 184 */   public Sync readLock() { return this.readerSync; }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/FIFOReadWriteLock.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */