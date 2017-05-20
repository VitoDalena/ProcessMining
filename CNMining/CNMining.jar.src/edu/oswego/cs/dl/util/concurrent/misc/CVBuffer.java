/*     */ package edu.oswego.cs.dl.util.concurrent.misc;
/*     */ 
/*     */ import edu.oswego.cs.dl.util.concurrent.CondVar;
/*     */ 
/*     */ public class CVBuffer implements edu.oswego.cs.dl.util.concurrent.BoundedChannel
/*     */ {
/*     */   private final edu.oswego.cs.dl.util.concurrent.Mutex mutex;
/*     */   private final CondVar notFull;
/*     */   private final CondVar notEmpty;
/*  10 */   private int count = 0;
/*  11 */   private int takePtr = 0;
/*  12 */   private int putPtr = 0;
/*     */   private final Object[] array;
/*     */   
/*     */   public CVBuffer(int paramInt) {
/*  16 */     this.array = new Object[paramInt];
/*  17 */     this.mutex = new edu.oswego.cs.dl.util.concurrent.Mutex();
/*  18 */     this.notFull = new CondVar(this.mutex);
/*  19 */     this.notEmpty = new CondVar(this.mutex);
/*     */   }
/*     */   
/*     */   public CVBuffer() {
/*  23 */     this(edu.oswego.cs.dl.util.concurrent.DefaultChannelCapacity.get());
/*     */   }
/*     */   
/*  26 */   public int capacity() { return this.array.length; }
/*     */   
/*     */   public void put(Object paramObject) throws InterruptedException {
/*  29 */     this.mutex.acquire();
/*     */     try {
/*  31 */       while (this.count == this.array.length) {
/*  32 */         this.notFull.await();
/*     */       }
/*  34 */       this.array[this.putPtr] = paramObject;
/*  35 */       this.putPtr = ((this.putPtr + 1) % this.array.length);
/*  36 */       this.count += 1;
/*  37 */       this.notEmpty.signal();
/*     */     }
/*     */     finally {
/*  40 */       this.mutex.release();
/*     */     }
/*     */   }
/*     */   
/*     */   public Object take() throws InterruptedException {
/*  45 */     Object localObject1 = null;
/*  46 */     this.mutex.acquire();
/*     */     try {
/*  48 */       while (this.count == 0) {
/*  49 */         this.notEmpty.await();
/*     */       }
/*  51 */       localObject1 = this.array[this.takePtr];
/*  52 */       this.array[this.takePtr] = null;
/*  53 */       this.takePtr = ((this.takePtr + 1) % this.array.length);
/*  54 */       this.count -= 1;
/*  55 */       this.notFull.signal();
/*     */     }
/*     */     finally {
/*  58 */       this.mutex.release();
/*     */     }
/*  60 */     return localObject1;
/*     */   }
/*     */   
/*     */   public boolean offer(Object paramObject, long paramLong) throws InterruptedException {
/*  64 */     this.mutex.acquire();
/*     */     try { boolean bool;
/*  66 */       if (this.count == this.array.length) {
/*  67 */         this.notFull.timedwait(paramLong);
/*  68 */         if (this.count == this.array.length)
/*  69 */           return false;
/*     */       }
/*  71 */       this.array[this.putPtr] = paramObject;
/*  72 */       this.putPtr = ((this.putPtr + 1) % this.array.length);
/*  73 */       this.count += 1;
/*  74 */       this.notEmpty.signal();
/*  75 */       return true;
/*     */     }
/*     */     finally {
/*  78 */       this.mutex.release();
/*     */     }
/*     */   }
/*     */   
/*     */   public Object poll(long paramLong) throws InterruptedException {
/*  83 */     Object localObject1 = null;
/*  84 */     this.mutex.acquire();
/*     */     try {
/*  86 */       if (this.count == 0) {
/*  87 */         this.notEmpty.timedwait(paramLong);
/*  88 */         if (this.count == 0)
/*  89 */           return null;
/*     */       }
/*  91 */       localObject1 = this.array[this.takePtr];
/*  92 */       this.array[this.takePtr] = null;
/*  93 */       this.takePtr = ((this.takePtr + 1) % this.array.length);
/*  94 */       this.count -= 1;
/*  95 */       this.notFull.signal();
/*     */     }
/*     */     finally {
/*  98 */       this.mutex.release();
/*     */     }
/* 100 */     return localObject1;
/*     */   }
/*     */   
/*     */   public Object peek() {
/*     */     try {
/* 105 */       this.mutex.acquire();
/*     */       try {
/* 107 */         if (this.count == 0) {
/* 108 */           return null;
/*     */         }
/* 110 */         return this.array[this.takePtr];
/*     */       }
/*     */       finally {
/* 113 */         this.mutex.release();
/*     */       }
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {
/* 117 */       Thread.currentThread().interrupt(); }
/* 118 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/misc/CVBuffer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */