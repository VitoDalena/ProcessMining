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
/*     */ public class BoundedLinkedQueue
/*     */   implements BoundedChannel
/*     */ {
/*     */   protected LinkedNode head_;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected LinkedNode last_;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  74 */   protected final Object putGuard_ = new Object();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  80 */   protected final Object takeGuard_ = new Object();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int capacity_;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int putSidePutPermits_;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 104 */   protected int takeSidePutPermits_ = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BoundedLinkedQueue(int paramInt)
/*     */   {
/* 112 */     if (paramInt <= 0) throw new IllegalArgumentException();
/* 113 */     this.capacity_ = paramInt;
/* 114 */     this.putSidePutPermits_ = paramInt;
/* 115 */     this.head_ = new LinkedNode(null);
/* 116 */     this.last_ = this.head_;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public BoundedLinkedQueue()
/*     */   {
/* 124 */     this(DefaultChannelCapacity.get());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final int reconcilePutPermits()
/*     */   {
/* 133 */     this.putSidePutPermits_ += this.takeSidePutPermits_;
/* 134 */     this.takeSidePutPermits_ = 0;
/* 135 */     return this.putSidePutPermits_;
/*     */   }
/*     */   
/*     */   public synchronized int capacity()
/*     */   {
/* 140 */     return this.capacity_;
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
/*     */   public synchronized int size()
/*     */   {
/* 157 */     return this.capacity_ - (this.takeSidePutPermits_ + this.putSidePutPermits_);
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
/*     */   public void setCapacity(int paramInt)
/*     */   {
/* 171 */     if (paramInt <= 0) throw new IllegalArgumentException();
/* 172 */     synchronized (this.putGuard_) {
/* 173 */       synchronized (this) {
/* 174 */         this.takeSidePutPermits_ += paramInt - this.capacity_;
/* 175 */         this.capacity_ = paramInt;
/*     */         
/*     */ 
/* 178 */         reconcilePutPermits();
/* 179 */         notifyAll();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected synchronized Object extract()
/*     */   {
/* 187 */     synchronized (this.head_) {
/* 188 */       Object localObject1 = null;
/* 189 */       LinkedNode localLinkedNode2 = this.head_.next;
/* 190 */       if (localLinkedNode2 != null) {
/* 191 */         localObject1 = localLinkedNode2.value;
/* 192 */         localLinkedNode2.value = null;
/* 193 */         this.head_ = localLinkedNode2;
/* 194 */         this.takeSidePutPermits_ += 1;
/* 195 */         notify();
/*     */       }
/* 197 */       Object localObject2 = localObject1;return localObject2;
/*     */     }
/*     */   }
/*     */   
/*     */   public Object peek() {
/* 202 */     synchronized (this.head_) {
/* 203 */       LinkedNode localLinkedNode2 = this.head_.next;
/* 204 */       if (localLinkedNode2 != null) {
/* 205 */         Object localObject1 = localLinkedNode2.value;return localObject1;
/*     */       }
/* 207 */       Object localObject2 = null;return localObject2;
/*     */     }
/*     */   }
/*     */   
/*     */   public Object take() throws InterruptedException {
/* 212 */     if (Thread.interrupted()) throw new InterruptedException();
/* 213 */     Object localObject1 = extract();
/* 214 */     if (localObject1 != null) {
/* 215 */       return localObject1;
/*     */     }
/* 217 */     synchronized (this.takeGuard_) {
/*     */       try {
/*     */         for (;;) {
/* 220 */           localObject1 = extract();
/* 221 */           if (localObject1 != null) {
/* 222 */             Object localObject3 = localObject1;return localObject3;
/*     */           }
/*     */           
/* 225 */           this.takeGuard_.wait();
/*     */         }
/*     */       }
/*     */       catch (InterruptedException localInterruptedException)
/*     */       {
/* 230 */         this.takeGuard_.notify();
/* 231 */         throw localInterruptedException;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public Object poll(long paramLong) throws InterruptedException
/*     */   {
/* 238 */     if (Thread.interrupted()) throw new InterruptedException();
/* 239 */     Object localObject1 = extract();
/* 240 */     if (localObject1 != null) {
/* 241 */       return localObject1;
/*     */     }
/* 243 */     synchronized (this.takeGuard_) {
/*     */       try {
/* 245 */         long l1 = paramLong;
/* 246 */         long l2 = paramLong <= 0L ? 0L : System.currentTimeMillis();
/*     */         for (;;) {
/* 248 */           localObject1 = extract();
/* 249 */           if ((localObject1 != null) || (l1 <= 0L)) {
/* 250 */             Object localObject3 = localObject1;return localObject3;
/*     */           }
/*     */           
/* 253 */           this.takeGuard_.wait(l1);
/* 254 */           l1 = paramLong - (System.currentTimeMillis() - l2);
/*     */         }
/*     */       }
/*     */       catch (InterruptedException localInterruptedException)
/*     */       {
/* 259 */         this.takeGuard_.notify();
/* 260 */         throw localInterruptedException;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected final void allowTake()
/*     */   {
/* 268 */     synchronized (this.takeGuard_) {
/* 269 */       this.takeGuard_.notify();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void insert(Object paramObject)
/*     */   {
/* 279 */     this.putSidePutPermits_ -= 1;
/* 280 */     LinkedNode localLinkedNode1 = new LinkedNode(paramObject);
/* 281 */     synchronized (this.last_) {
/* 282 */       this.last_.next = localLinkedNode1;
/* 283 */       this.last_ = localLinkedNode1;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void put(Object paramObject)
/*     */     throws InterruptedException
/*     */   {
/* 293 */     if (paramObject == null) throw new IllegalArgumentException();
/* 294 */     if (Thread.interrupted()) { throw new InterruptedException();
/*     */     }
/* 296 */     synchronized (this.putGuard_)
/*     */     {
/* 298 */       if (this.putSidePutPermits_ <= 0) {
/* 299 */         synchronized (this) {
/* 300 */           if (reconcilePutPermits() <= 0) {
/*     */             try {
/*     */               for (;;) {
/* 303 */                 wait();
/* 304 */                 if (reconcilePutPermits() > 0) {
/*     */                   break;
/*     */                 }
/*     */               }
/*     */             }
/*     */             catch (InterruptedException localInterruptedException) {
/* 310 */               notify();
/* 311 */               throw localInterruptedException;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 316 */       insert(paramObject);
/*     */     }
/*     */     
/* 319 */     allowTake();
/*     */   }
/*     */   
/*     */   public boolean offer(Object paramObject, long paramLong) throws InterruptedException {
/* 323 */     if (paramObject == null) throw new IllegalArgumentException();
/* 324 */     if (Thread.interrupted()) { throw new InterruptedException();
/*     */     }
/* 326 */     synchronized (this.putGuard_)
/*     */     {
/* 328 */       if (this.putSidePutPermits_ <= 0) {
/* 329 */         synchronized (this) {
/* 330 */           if (reconcilePutPermits() <= 0) {
/* 331 */             if (paramLong <= 0L) {
/* 332 */               boolean bool1 = false;return bool1;
/*     */             }
/*     */             try {
/* 335 */               long l1 = paramLong;
/* 336 */               long l2 = System.currentTimeMillis();
/*     */               do
/*     */               {
/* 339 */                 wait(l1);
/* 340 */                 if (reconcilePutPermits() > 0) {
/*     */                   break;
/*     */                 }
/*     */                 
/* 344 */                 l1 = paramLong - (System.currentTimeMillis() - l2);
/* 345 */               } while (l1 > 0L);
/* 346 */               boolean bool2 = false;return bool2;
/*     */ 
/*     */             }
/*     */             catch (InterruptedException localInterruptedException)
/*     */             {
/*     */ 
/* 352 */               notify();
/* 353 */               throw localInterruptedException;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 360 */       insert(paramObject);
/*     */     }
/*     */     
/* 363 */     allowTake();
/* 364 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 368 */     synchronized (this.head_) {
/* 369 */       boolean bool = this.head_.next == null;return bool;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/BoundedLinkedQueue.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */