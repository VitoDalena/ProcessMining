/*     */ package edu.oswego.cs.dl.util.concurrent;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ReentrantWriterPreferenceReadWriteLock
/*     */   extends WriterPreferenceReadWriteLock
/*     */ {
/*  67 */   protected long writeHolds_ = 0L;
/*     */   
/*     */ 
/*  70 */   protected HashMap readers_ = new HashMap();
/*     */   
/*     */ 
/*  73 */   protected static final Integer IONE = new Integer(1);
/*     */   
/*     */   protected boolean allowReader()
/*     */   {
/*  77 */     return ((this.activeWriter_ == null) && (this.waitingWriters_ == 0L)) || (this.activeWriter_ == Thread.currentThread());
/*     */   }
/*     */   
/*     */   protected synchronized boolean startRead()
/*     */   {
/*  82 */     Thread localThread = Thread.currentThread();
/*  83 */     Object localObject = this.readers_.get(localThread);
/*  84 */     if (localObject != null) {
/*  85 */       this.readers_.put(localThread, new Integer(((Integer)localObject).intValue() + 1));
/*  86 */       this.activeReaders_ += 1L;
/*  87 */       return true;
/*     */     }
/*  89 */     if (allowReader()) {
/*  90 */       this.readers_.put(localThread, IONE);
/*  91 */       this.activeReaders_ += 1L;
/*  92 */       return true;
/*     */     }
/*     */     
/*  95 */     return false;
/*     */   }
/*     */   
/*     */   protected synchronized boolean startWrite() {
/*  99 */     if (this.activeWriter_ == Thread.currentThread()) {
/* 100 */       this.writeHolds_ += 1L;
/* 101 */       return true;
/*     */     }
/* 103 */     if (this.writeHolds_ == 0L) {
/* 104 */       if ((this.activeReaders_ == 0L) || ((this.readers_.size() == 1) && (this.readers_.get(Thread.currentThread()) != null)))
/*     */       {
/*     */ 
/* 107 */         this.activeWriter_ = Thread.currentThread();
/* 108 */         this.writeHolds_ = 1L;
/* 109 */         return true;
/*     */       }
/*     */       
/* 112 */       return false;
/*     */     }
/*     */     
/* 115 */     return false;
/*     */   }
/*     */   
/*     */   protected synchronized WriterPreferenceReadWriteLock.Signaller endRead()
/*     */   {
/* 120 */     this.activeReaders_ -= 1L;
/* 121 */     Thread localThread = Thread.currentThread();
/* 122 */     Object localObject = this.readers_.get(localThread);
/* 123 */     if (localObject != IONE) {
/* 124 */       int i = ((Integer)localObject).intValue() - 1;
/* 125 */       Integer localInteger = i == 1 ? IONE : new Integer(i);
/* 126 */       this.readers_.put(localThread, localInteger);
/* 127 */       return null;
/*     */     }
/*     */     
/* 130 */     this.readers_.remove(localThread);
/*     */     
/* 132 */     if (this.writeHolds_ > 0L)
/* 133 */       return null;
/* 134 */     if ((this.activeReaders_ == 0L) && (this.waitingWriters_ > 0L)) {
/* 135 */       return this.writerLock_;
/*     */     }
/* 137 */     return null;
/*     */   }
/*     */   
/*     */   protected synchronized WriterPreferenceReadWriteLock.Signaller endWrite()
/*     */   {
/* 142 */     this.writeHolds_ -= 1L;
/* 143 */     if (this.writeHolds_ > 0L) {
/* 144 */       return null;
/*     */     }
/* 146 */     this.activeWriter_ = null;
/* 147 */     if ((this.waitingReaders_ > 0L) && (allowReader()))
/* 148 */       return this.readerLock_;
/* 149 */     if (this.waitingWriters_ > 0L) {
/* 150 */       return this.writerLock_;
/*     */     }
/* 152 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/ReentrantWriterPreferenceReadWriteLock.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */