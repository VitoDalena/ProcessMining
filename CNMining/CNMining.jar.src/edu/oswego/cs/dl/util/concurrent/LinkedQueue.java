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
/*     */ public class LinkedQueue
/*     */   implements Channel
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
/*  43 */   protected final Object putLock_ = new Object();
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
/*  57 */   protected int waitingForTake_ = 0;
/*     */   
/*     */   public LinkedQueue() {
/*  60 */     this.head_ = new LinkedNode(null);
/*  61 */     this.last_ = this.head_;
/*     */   }
/*     */   
/*     */   protected void insert(Object paramObject)
/*     */   {
/*  66 */     synchronized (this.putLock_) {
/*  67 */       LinkedNode localLinkedNode1 = new LinkedNode(paramObject);
/*  68 */       synchronized (this.last_) {
/*  69 */         this.last_.next = localLinkedNode1;
/*  70 */         this.last_ = localLinkedNode1;
/*     */       }
/*  72 */       if (this.waitingForTake_ > 0) {
/*  73 */         this.putLock_.notify();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected synchronized Object extract() {
/*  79 */     synchronized (this.head_) {
/*  80 */       Object localObject1 = null;
/*  81 */       LinkedNode localLinkedNode2 = this.head_.next;
/*  82 */       if (localLinkedNode2 != null) {
/*  83 */         localObject1 = localLinkedNode2.value;
/*  84 */         localLinkedNode2.value = null;
/*  85 */         this.head_ = localLinkedNode2;
/*     */       }
/*  87 */       Object localObject2 = localObject1;return localObject2;
/*     */     }
/*     */   }
/*     */   
/*     */   public void put(Object paramObject) throws InterruptedException
/*     */   {
/*  93 */     if (paramObject == null) throw new IllegalArgumentException();
/*  94 */     if (Thread.interrupted()) throw new InterruptedException();
/*  95 */     insert(paramObject);
/*     */   }
/*     */   
/*     */   public boolean offer(Object paramObject, long paramLong) throws InterruptedException {
/*  99 */     if (paramObject == null) throw new IllegalArgumentException();
/* 100 */     if (Thread.interrupted()) throw new InterruptedException();
/* 101 */     insert(paramObject);
/* 102 */     return true;
/*     */   }
/*     */   
/*     */   public Object take() throws InterruptedException {
/* 106 */     if (Thread.interrupted()) { throw new InterruptedException();
/*     */     }
/* 108 */     Object localObject1 = extract();
/* 109 */     if (localObject1 != null) {
/* 110 */       return localObject1;
/*     */     }
/* 112 */     synchronized (this.putLock_) {
/*     */       try {
/* 114 */         this.waitingForTake_ += 1;
/*     */         for (;;) {
/* 116 */           localObject1 = extract();
/* 117 */           if (localObject1 != null) {
/* 118 */             this.waitingForTake_ -= 1;
/* 119 */             Object localObject3 = localObject1;return localObject3;
/*     */           }
/*     */           
/* 122 */           this.putLock_.wait();
/*     */         }
/*     */       }
/*     */       catch (InterruptedException localInterruptedException)
/*     */       {
/* 127 */         this.waitingForTake_ -= 1;
/* 128 */         this.putLock_.notify();
/* 129 */         throw localInterruptedException;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public Object peek()
/*     */   {
/* 136 */     synchronized (this.head_) {
/* 137 */       LinkedNode localLinkedNode2 = this.head_.next;
/* 138 */       if (localLinkedNode2 != null) {
/* 139 */         Object localObject1 = localLinkedNode2.value;return localObject1;
/*     */       }
/* 141 */       Object localObject2 = null;return localObject2;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isEmpty()
/*     */   {
/* 147 */     synchronized (this.head_) {
/* 148 */       boolean bool = this.head_.next == null;return bool;
/*     */     }
/*     */   }
/*     */   
/*     */   public Object poll(long paramLong) throws InterruptedException {
/* 153 */     if (Thread.interrupted()) throw new InterruptedException();
/* 154 */     Object localObject1 = extract();
/* 155 */     if (localObject1 != null) {
/* 156 */       return localObject1;
/*     */     }
/* 158 */     synchronized (this.putLock_) {
/*     */       try {
/* 160 */         long l1 = paramLong;
/* 161 */         long l2 = paramLong <= 0L ? 0L : System.currentTimeMillis();
/* 162 */         this.waitingForTake_ += 1;
/*     */         for (;;) {
/* 164 */           localObject1 = extract();
/* 165 */           if ((localObject1 != null) || (l1 <= 0L)) {
/* 166 */             this.waitingForTake_ -= 1;
/* 167 */             Object localObject3 = localObject1;return localObject3;
/*     */           }
/*     */           
/* 170 */           this.putLock_.wait(l1);
/* 171 */           l1 = paramLong - (System.currentTimeMillis() - l2);
/*     */         }
/*     */       }
/*     */       catch (InterruptedException localInterruptedException)
/*     */       {
/* 176 */         this.waitingForTake_ -= 1;
/* 177 */         this.putLock_.notify();
/* 178 */         throw localInterruptedException;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/LinkedQueue.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */