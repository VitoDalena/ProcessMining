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
/*     */ public class WriterPreferenceReadWriteLock
/*     */   implements ReadWriteLock
/*     */ {
/*  37 */   protected long activeReaders_ = 0L;
/*  38 */   protected Thread activeWriter_ = null;
/*  39 */   protected long waitingReaders_ = 0L;
/*  40 */   protected long waitingWriters_ = 0L;
/*     */   
/*     */ 
/*  43 */   protected final ReaderLock readerLock_ = new ReaderLock();
/*  44 */   protected final WriterLock writerLock_ = new WriterLock();
/*     */   
/*  46 */   public Sync writeLock() { return this.writerLock_; }
/*  47 */   public Sync readLock() { return this.readerLock_; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  56 */   protected synchronized void cancelledWaitingReader() { this.waitingReaders_ -= 1L; }
/*  57 */   protected synchronized void cancelledWaitingWriter() { this.waitingWriters_ -= 1L; }
/*     */   
/*     */ 
/*     */   protected boolean allowReader()
/*     */   {
/*  62 */     return (this.activeWriter_ == null) && (this.waitingWriters_ == 0L);
/*     */   }
/*     */   
/*     */   protected synchronized boolean startRead()
/*     */   {
/*  67 */     boolean bool = allowReader();
/*  68 */     if (bool) this.activeReaders_ += 1L;
/*  69 */     return bool;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized boolean startWrite()
/*     */   {
/*  77 */     boolean bool = (this.activeWriter_ == null) && (this.activeReaders_ == 0L);
/*  78 */     if (bool) this.activeWriter_ = Thread.currentThread();
/*  79 */     return bool;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized boolean startReadFromNewReader()
/*     */   {
/*  91 */     boolean bool = startRead();
/*  92 */     if (!bool) this.waitingReaders_ += 1L;
/*  93 */     return bool;
/*     */   }
/*     */   
/*     */   protected synchronized boolean startWriteFromNewWriter() {
/*  97 */     boolean bool = startWrite();
/*  98 */     if (!bool) this.waitingWriters_ += 1L;
/*  99 */     return bool;
/*     */   }
/*     */   
/*     */   protected synchronized boolean startReadFromWaitingReader() {
/* 103 */     boolean bool = startRead();
/* 104 */     if (bool) this.waitingReaders_ -= 1L;
/* 105 */     return bool;
/*     */   }
/*     */   
/*     */   protected synchronized boolean startWriteFromWaitingWriter() {
/* 109 */     boolean bool = startWrite();
/* 110 */     if (bool) this.waitingWriters_ -= 1L;
/* 111 */     return bool;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized Signaller endRead()
/*     */   {
/* 119 */     if ((--this.activeReaders_ == 0L) && (this.waitingWriters_ > 0L)) {
/* 120 */       return this.writerLock_;
/*     */     }
/* 122 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized Signaller endWrite()
/*     */   {
/* 131 */     this.activeWriter_ = null;
/* 132 */     if ((this.waitingReaders_ > 0L) && (allowReader()))
/* 133 */       return this.readerLock_;
/* 134 */     if (this.waitingWriters_ > 0L) {
/* 135 */       return this.writerLock_;
/*     */     }
/* 137 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected abstract class Signaller
/*     */   {
/*     */     protected Signaller() {}
/*     */     
/*     */ 
/*     */     abstract void signalWaiters();
/*     */   }
/*     */   
/*     */   protected class ReaderLock
/*     */     extends WriterPreferenceReadWriteLock.Signaller
/*     */     implements Sync
/*     */   {
/* 154 */     protected ReaderLock() { super(); }
/*     */     
/*     */     public void acquire() throws InterruptedException {
/* 157 */       if (Thread.interrupted()) throw new InterruptedException();
/* 158 */       Object localObject1 = null;
/* 159 */       synchronized (this) {
/* 160 */         if (!WriterPreferenceReadWriteLock.this.startReadFromNewReader()) {
/*     */           try {
/*     */             for (;;) {
/* 163 */               wait();
/* 164 */               if (WriterPreferenceReadWriteLock.this.startReadFromWaitingReader())
/* 165 */                 return;
/*     */             }
/*     */           } catch (InterruptedException localInterruptedException) {
/* 168 */             WriterPreferenceReadWriteLock.this.cancelledWaitingReader();
/* 169 */             localObject1 = localInterruptedException;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 175 */       if (localObject1 != null)
/*     */       {
/*     */ 
/*     */ 
/* 179 */         WriterPreferenceReadWriteLock.this.writerLock_.signalWaiters();
/* 180 */         throw ((Throwable)localObject1);
/*     */       }
/*     */     }
/*     */     
/*     */     public void release()
/*     */     {
/* 186 */       WriterPreferenceReadWriteLock.Signaller localSignaller = WriterPreferenceReadWriteLock.this.endRead();
/* 187 */       if (localSignaller != null) { localSignaller.signalWaiters();
/*     */       }
/*     */     }
/*     */     
/* 191 */     synchronized void signalWaiters() { notifyAll(); }
/*     */     
/*     */     public boolean attempt(long paramLong) throws InterruptedException {
/* 194 */       if (Thread.interrupted()) throw new InterruptedException();
/* 195 */       Object localObject1 = null;
/* 196 */       synchronized (this) {
/* 197 */         if (paramLong <= 0L) {
/* 198 */           boolean bool1 = WriterPreferenceReadWriteLock.this.startRead();return bool1; }
/* 199 */         if (WriterPreferenceReadWriteLock.this.startReadFromNewReader()) {
/* 200 */           boolean bool2 = true;return bool2;
/*     */         }
/* 202 */         long l1 = paramLong;
/* 203 */         long l2 = System.currentTimeMillis();
/*     */         do {
/* 205 */           try { wait(l1);
/*     */           } catch (InterruptedException localInterruptedException) {
/* 207 */             WriterPreferenceReadWriteLock.this.cancelledWaitingReader();
/* 208 */             localObject1 = localInterruptedException;
/* 209 */             break;
/*     */           }
/* 211 */           if (WriterPreferenceReadWriteLock.this.startReadFromWaitingReader()) {
/* 212 */             boolean bool3 = true;return bool3;
/*     */           }
/* 214 */           l1 = paramLong - (System.currentTimeMillis() - l2);
/* 215 */         } while (l1 > 0L);
/* 216 */         WriterPreferenceReadWriteLock.this.cancelledWaitingReader();
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 224 */       WriterPreferenceReadWriteLock.this.writerLock_.signalWaiters();
/* 225 */       if (localObject1 != null) throw ((Throwable)localObject1);
/* 226 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   protected class WriterLock extends WriterPreferenceReadWriteLock.Signaller implements Sync {
/* 231 */     protected WriterLock() { super(); }
/*     */     
/*     */     public void acquire() throws InterruptedException {
/* 234 */       if (Thread.interrupted()) throw new InterruptedException();
/* 235 */       Object localObject1 = null;
/* 236 */       synchronized (this) {
/* 237 */         if (!WriterPreferenceReadWriteLock.this.startWriteFromNewWriter()) {
/*     */           try {
/*     */             for (;;) {
/* 240 */               wait();
/* 241 */               if (WriterPreferenceReadWriteLock.this.startWriteFromWaitingWriter())
/* 242 */                 return;
/*     */             }
/*     */           } catch (InterruptedException localInterruptedException) {
/* 245 */             WriterPreferenceReadWriteLock.this.cancelledWaitingWriter();
/* 246 */             notify();
/* 247 */             localObject1 = localInterruptedException;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 253 */       if (localObject1 != null)
/*     */       {
/*     */ 
/*     */ 
/* 257 */         WriterPreferenceReadWriteLock.this.readerLock_.signalWaiters();
/* 258 */         throw ((Throwable)localObject1);
/*     */       }
/*     */     }
/*     */     
/*     */     public void release() {
/* 263 */       WriterPreferenceReadWriteLock.Signaller localSignaller = WriterPreferenceReadWriteLock.this.endWrite();
/* 264 */       if (localSignaller != null) localSignaller.signalWaiters();
/*     */     }
/*     */     
/* 267 */     synchronized void signalWaiters() { notify(); }
/*     */     
/*     */     public boolean attempt(long paramLong) throws InterruptedException {
/* 270 */       if (Thread.interrupted()) throw new InterruptedException();
/* 271 */       Object localObject1 = null;
/* 272 */       synchronized (this) {
/* 273 */         if (paramLong <= 0L) {
/* 274 */           boolean bool1 = WriterPreferenceReadWriteLock.this.startWrite();return bool1; }
/* 275 */         if (WriterPreferenceReadWriteLock.this.startWriteFromNewWriter()) {
/* 276 */           boolean bool2 = true;return bool2;
/*     */         }
/* 278 */         long l1 = paramLong;
/* 279 */         long l2 = System.currentTimeMillis();
/*     */         do {
/* 281 */           try { wait(l1);
/*     */           } catch (InterruptedException localInterruptedException) {
/* 283 */             WriterPreferenceReadWriteLock.this.cancelledWaitingWriter();
/* 284 */             notify();
/* 285 */             localObject1 = localInterruptedException;
/* 286 */             break;
/*     */           }
/* 288 */           if (WriterPreferenceReadWriteLock.this.startWriteFromWaitingWriter()) {
/* 289 */             boolean bool3 = true;return bool3;
/*     */           }
/* 291 */           l1 = paramLong - (System.currentTimeMillis() - l2);
/* 292 */         } while (l1 > 0L);
/* 293 */         WriterPreferenceReadWriteLock.this.cancelledWaitingWriter();
/* 294 */         notify();
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 302 */       WriterPreferenceReadWriteLock.this.readerLock_.signalWaiters();
/* 303 */       if (localObject1 != null) throw ((Throwable)localObject1);
/* 304 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/WriterPreferenceReadWriteLock.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */