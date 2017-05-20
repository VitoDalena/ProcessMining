/*     */ package org.apache.lucene.store;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.lucene.index.IndexWriter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Lock
/*     */ {
/*  37 */   public static long LOCK_POLL_INTERVAL = 1000L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract boolean obtain()
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean obtain(long lockWaitTimeout)
/*     */     throws IOException
/*     */   {
/*  53 */     boolean locked = obtain();
/*  54 */     int maxSleepCount = (int)(lockWaitTimeout / LOCK_POLL_INTERVAL);
/*  55 */     int sleepCount = 0;
/*  56 */     while (!locked) {
/*  57 */       sleepCount++; if (sleepCount == maxSleepCount) {
/*  58 */         throw new IOException("Lock obtain timed out: " + toString());
/*     */       }
/*     */       try {
/*  61 */         Thread.sleep(LOCK_POLL_INTERVAL);
/*     */       } catch (InterruptedException e) {
/*  63 */         throw new IOException(e.toString());
/*     */       }
/*  65 */       locked = obtain();
/*     */     }
/*  67 */     return locked;
/*     */   }
/*     */   
/*     */ 
/*     */   public abstract void release();
/*     */   
/*     */ 
/*     */   public abstract boolean isLocked();
/*     */   
/*     */ 
/*     */   public static abstract class With
/*     */   {
/*     */     private Lock lock;
/*     */     
/*     */     private long lockWaitTimeout;
/*     */     
/*     */ 
/*     */     /**
/*     */      * @deprecated
/*     */      */
/*     */     public With(Lock lock)
/*     */     {
/*  89 */       this(lock, IndexWriter.COMMIT_LOCK_TIMEOUT);
/*     */     }
/*     */     
/*     */     public With(Lock lock, long lockWaitTimeout)
/*     */     {
/*  94 */       this.lock = lock;
/*  95 */       this.lockWaitTimeout = lockWaitTimeout;
/*     */     }
/*     */     
/*     */ 
/*     */     protected abstract Object doBody()
/*     */       throws IOException;
/*     */     
/*     */ 
/*     */     public Object run()
/*     */       throws IOException
/*     */     {
/* 106 */       boolean locked = false;
/*     */       try {
/* 108 */         locked = this.lock.obtain(this.lockWaitTimeout);
/* 109 */         return doBody();
/*     */       } finally {
/* 111 */         if (locked) {
/* 112 */           this.lock.release();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/store/Lock.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */