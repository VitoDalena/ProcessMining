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
/*     */ public class BoundedBuffer
/*     */   implements BoundedChannel
/*     */ {
/*     */   protected final Object[] array_;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  29 */   protected int takePtr_ = 0;
/*  30 */   protected int putPtr_ = 0;
/*     */   
/*  32 */   protected int usedSlots_ = 0;
/*     */   
/*     */ 
/*     */   protected int emptySlots_;
/*     */   
/*     */ 
/*  38 */   protected final Object putMonitor_ = new Object();
/*     */   
/*     */ 
/*     */ 
/*     */   public BoundedBuffer(int paramInt)
/*     */     throws IllegalArgumentException
/*     */   {
/*  45 */     if (paramInt <= 0) throw new IllegalArgumentException();
/*  46 */     this.array_ = new Object[paramInt];
/*  47 */     this.emptySlots_ = paramInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public BoundedBuffer()
/*     */   {
/*  55 */     this(DefaultChannelCapacity.get());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  63 */   public synchronized int size() { return this.usedSlots_; }
/*     */   
/*  65 */   public int capacity() { return this.array_.length; }
/*     */   
/*     */   protected void incEmptySlots() {
/*  68 */     synchronized (this.putMonitor_) {
/*  69 */       this.emptySlots_ += 1;
/*  70 */       this.putMonitor_.notify();
/*     */     }
/*     */   }
/*     */   
/*     */   protected synchronized void incUsedSlots() {
/*  75 */     this.usedSlots_ += 1;
/*  76 */     notify();
/*     */   }
/*     */   
/*     */   protected final void insert(Object paramObject) {
/*  80 */     this.emptySlots_ -= 1;
/*  81 */     this.array_[this.putPtr_] = paramObject;
/*  82 */     if (++this.putPtr_ >= this.array_.length) this.putPtr_ = 0;
/*     */   }
/*     */   
/*     */   protected final Object extract() {
/*  86 */     this.usedSlots_ -= 1;
/*  87 */     Object localObject = this.array_[this.takePtr_];
/*  88 */     this.array_[this.takePtr_] = null;
/*  89 */     if (++this.takePtr_ >= this.array_.length) this.takePtr_ = 0;
/*  90 */     return localObject;
/*     */   }
/*     */   
/*     */   public Object peek() {
/*  94 */     synchronized (this) {
/*  95 */       if (this.usedSlots_ > 0) {
/*  96 */         Object localObject1 = this.array_[this.takePtr_];return localObject1;
/*     */       }
/*  98 */       Object localObject2 = null;return localObject2;
/*     */     }
/*     */   }
/*     */   
/*     */   public void put(Object paramObject) throws InterruptedException
/*     */   {
/* 104 */     if (paramObject == null) throw new IllegalArgumentException();
/* 105 */     if (Thread.interrupted()) { throw new InterruptedException();
/*     */     }
/* 107 */     synchronized (this.putMonitor_) {
/* 108 */       while (this.emptySlots_ <= 0) {
/* 109 */         try { this.putMonitor_.wait();
/*     */         } catch (InterruptedException localInterruptedException) {
/* 111 */           this.putMonitor_.notify();
/* 112 */           throw localInterruptedException;
/*     */         }
/*     */       }
/* 115 */       insert(paramObject);
/*     */     }
/* 117 */     incUsedSlots();
/*     */   }
/*     */   
/*     */   public boolean offer(Object paramObject, long paramLong) throws InterruptedException {
/* 121 */     if (paramObject == null) throw new IllegalArgumentException();
/* 122 */     if (Thread.interrupted()) { throw new InterruptedException();
/*     */     }
/* 124 */     synchronized (this.putMonitor_) {
/* 125 */       long l1 = paramLong <= 0L ? 0L : System.currentTimeMillis();
/* 126 */       long l2 = paramLong;
/* 127 */       while (this.emptySlots_ <= 0) {
/* 128 */         if (l2 <= 0L) { boolean bool = false;return bool; }
/* 129 */         try { this.putMonitor_.wait(l2);
/*     */         } catch (InterruptedException localInterruptedException) {
/* 131 */           this.putMonitor_.notify();
/* 132 */           throw localInterruptedException;
/*     */         }
/* 134 */         l2 = paramLong - (System.currentTimeMillis() - l1);
/*     */       }
/* 136 */       insert(paramObject);
/*     */     }
/* 138 */     incUsedSlots();
/* 139 */     return true;
/*     */   }
/*     */   
/*     */   public Object take()
/*     */     throws InterruptedException
/*     */   {
/* 145 */     if (Thread.interrupted()) throw new InterruptedException();
/* 146 */     Object localObject1 = null;
/* 147 */     synchronized (this) {
/* 148 */       while (this.usedSlots_ <= 0) {
/* 149 */         try { wait();
/*     */         } catch (InterruptedException localInterruptedException) {
/* 151 */           notify();
/* 152 */           throw localInterruptedException;
/*     */         }
/*     */       }
/* 155 */       localObject1 = extract();
/*     */     }
/* 157 */     incEmptySlots();
/* 158 */     return localObject1;
/*     */   }
/*     */   
/*     */   public Object poll(long paramLong) throws InterruptedException {
/* 162 */     if (Thread.interrupted()) throw new InterruptedException();
/* 163 */     Object localObject1 = null;
/* 164 */     synchronized (this) {
/* 165 */       long l1 = paramLong <= 0L ? 0L : System.currentTimeMillis();
/* 166 */       long l2 = paramLong;
/*     */       
/* 168 */       while (this.usedSlots_ <= 0) {
/* 169 */         if (l2 <= 0L) { Object localObject2 = null;return localObject2; }
/* 170 */         try { wait(l2);
/*     */         } catch (InterruptedException localInterruptedException) {
/* 172 */           notify();
/* 173 */           throw localInterruptedException;
/*     */         }
/* 175 */         l2 = paramLong - (System.currentTimeMillis() - l1);
/*     */       }
/*     */       
/* 178 */       localObject1 = extract();
/*     */     }
/* 180 */     incEmptySlots();
/* 181 */     return localObject1;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/BoundedBuffer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */