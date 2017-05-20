/*     */ package edu.oswego.cs.dl.util.concurrent;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SyncCollection
/*     */   implements Collection
/*     */ {
/*     */   protected final Collection c_;
/*     */   protected final Sync rd_;
/*     */   protected final Sync wr_;
/* 193 */   protected final SynchronizedLong syncFailures_ = new SynchronizedLong(0L);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SyncCollection(Collection paramCollection, Sync paramSync)
/*     */   {
/* 207 */     this(paramCollection, paramSync, paramSync);
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
/*     */   public SyncCollection(Collection paramCollection, ReadWriteLock paramReadWriteLock)
/*     */   {
/* 222 */     this(paramCollection, paramReadWriteLock.readLock(), paramReadWriteLock.writeLock());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SyncCollection(Collection paramCollection, Sync paramSync1, Sync paramSync2)
/*     */   {
/* 230 */     this.c_ = paramCollection;
/* 231 */     this.rd_ = paramSync1;
/* 232 */     this.wr_ = paramSync2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Sync readerSync()
/*     */   {
/* 240 */     return this.rd_;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Sync writerSync()
/*     */   {
/* 248 */     return this.wr_;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public long syncFailures()
/*     */   {
/* 255 */     return this.syncFailures_.get();
/*     */   }
/*     */   
/*     */   protected boolean beforeRead()
/*     */   {
/*     */     try
/*     */     {
/* 262 */       this.rd_.acquire();
/* 263 */       return false;
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {
/* 266 */       this.syncFailures_.increment(); }
/* 267 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void afterRead(boolean paramBoolean)
/*     */   {
/* 273 */     if (paramBoolean) {
/* 274 */       Thread.currentThread().interrupt();
/*     */     }
/*     */     else {
/* 277 */       this.rd_.release();
/*     */     }
/*     */   }
/*     */   
/*     */   public int size()
/*     */   {
/* 283 */     boolean bool = beforeRead();
/*     */     try {
/* 285 */       return this.c_.size();
/*     */     }
/*     */     finally {
/* 288 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 293 */     boolean bool1 = beforeRead();
/*     */     try {
/* 295 */       return this.c_.isEmpty();
/*     */     }
/*     */     finally {
/* 298 */       afterRead(bool1);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean contains(Object paramObject) {
/* 303 */     boolean bool1 = beforeRead();
/*     */     try {
/* 305 */       return this.c_.contains(paramObject);
/*     */     }
/*     */     finally {
/* 308 */       afterRead(bool1);
/*     */     }
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 313 */     boolean bool = beforeRead();
/*     */     try {
/* 315 */       return this.c_.toArray();
/*     */     }
/*     */     finally {
/* 318 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] paramArrayOfObject) {
/* 323 */     boolean bool = beforeRead();
/*     */     try {
/* 325 */       return this.c_.toArray(paramArrayOfObject);
/*     */     }
/*     */     finally {
/* 328 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection paramCollection) {
/* 333 */     boolean bool1 = beforeRead();
/*     */     try {
/* 335 */       return this.c_.containsAll(paramCollection);
/*     */     }
/*     */     finally {
/* 338 */       afterRead(bool1);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean add(Object paramObject)
/*     */   {
/*     */     try {
/* 345 */       this.wr_.acquire();
/*     */       try {
/* 347 */         return this.c_.add(paramObject);
/*     */       }
/*     */       finally {
/* 350 */         this.wr_.release();
/*     */       }
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {
/* 354 */       Thread.currentThread().interrupt();
/* 355 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean remove(Object paramObject) {
/*     */     try {
/* 361 */       this.wr_.acquire();
/*     */       try {
/* 363 */         return this.c_.remove(paramObject);
/*     */       }
/*     */       finally {
/* 366 */         this.wr_.release();
/*     */       }
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {
/* 370 */       Thread.currentThread().interrupt();
/* 371 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection paramCollection) {
/*     */     try {
/* 377 */       this.wr_.acquire();
/*     */       try {
/* 379 */         return this.c_.addAll(paramCollection);
/*     */       }
/*     */       finally {
/* 382 */         this.wr_.release();
/*     */       }
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {
/* 386 */       Thread.currentThread().interrupt();
/* 387 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection paramCollection) {
/*     */     try {
/* 393 */       this.wr_.acquire();
/*     */       try {
/* 395 */         return this.c_.removeAll(paramCollection);
/*     */       }
/*     */       finally {
/* 398 */         this.wr_.release();
/*     */       }
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {
/* 402 */       Thread.currentThread().interrupt();
/* 403 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection paramCollection)
/*     */   {
/*     */     try {
/* 410 */       this.wr_.acquire();
/*     */       try {
/* 412 */         return this.c_.retainAll(paramCollection);
/*     */       }
/*     */       finally {
/* 415 */         this.wr_.release();
/*     */       }
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {
/* 419 */       Thread.currentThread().interrupt();
/* 420 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*     */   public void clear()
/*     */   {
/*     */     try {
/* 427 */       this.wr_.acquire();
/*     */       try {
/* 429 */         this.c_.clear();
/*     */       }
/*     */       finally {
/* 432 */         this.wr_.release();
/*     */       }
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {
/* 436 */       Thread.currentThread().interrupt();
/* 437 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public Iterator unprotectedIterator()
/*     */   {
/* 444 */     boolean bool = beforeRead();
/*     */     try {
/* 446 */       return this.c_.iterator();
/*     */     }
/*     */     finally {
/* 449 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 454 */     boolean bool = beforeRead();
/*     */     try {
/* 456 */       return new SyncCollectionIterator(this.c_.iterator());
/*     */     }
/*     */     finally {
/* 459 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SyncCollectionIterator implements Iterator {
/*     */     protected final Iterator baseIterator_;
/*     */     
/*     */     SyncCollectionIterator(Iterator paramIterator) {
/* 467 */       this.baseIterator_ = paramIterator;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 471 */       boolean bool1 = SyncCollection.this.beforeRead();
/*     */       try {
/* 473 */         return this.baseIterator_.hasNext();
/*     */       }
/*     */       finally {
/* 476 */         SyncCollection.this.afterRead(bool1);
/*     */       }
/*     */     }
/*     */     
/*     */     public Object next() {
/* 481 */       boolean bool = SyncCollection.this.beforeRead();
/*     */       try {
/* 483 */         return this.baseIterator_.next();
/*     */       }
/*     */       finally {
/* 486 */         SyncCollection.this.afterRead(bool);
/*     */       }
/*     */     }
/*     */     
/*     */     public void remove() {
/*     */       try {
/* 492 */         SyncCollection.this.wr_.acquire();
/*     */         try {
/* 494 */           this.baseIterator_.remove();
/*     */         }
/*     */         finally {
/* 497 */           SyncCollection.this.wr_.release();
/*     */         }
/*     */       }
/*     */       catch (InterruptedException localInterruptedException) {
/* 501 */         Thread.currentThread().interrupt();
/* 502 */         throw new UnsupportedOperationException();
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/SyncCollection.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */