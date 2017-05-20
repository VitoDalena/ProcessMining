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
/*     */ public class SynchronousChannel
/*     */   implements BoundedChannel
/*     */ {
/*  66 */   protected static final Object CANCELLED = new Object();
/*     */   
/*     */ 
/*     */   protected static class Queue
/*     */   {
/*     */     protected LinkedNode head;
/*     */     protected LinkedNode last;
/*     */     
/*     */     protected void enq(LinkedNode paramLinkedNode)
/*     */     {
/*  76 */       if (this.last == null) {
/*  77 */         this.last = (this.head = paramLinkedNode);
/*     */       } else
/*  79 */         this.last = (this.last.next = paramLinkedNode);
/*     */     }
/*     */     
/*     */     protected LinkedNode deq() {
/*  83 */       LinkedNode localLinkedNode = this.head;
/*  84 */       if ((localLinkedNode != null) && ((this.head = localLinkedNode.next) == null))
/*  85 */         this.last = null;
/*  86 */       return localLinkedNode;
/*     */     }
/*     */   }
/*     */   
/*  90 */   protected final Queue waitingPuts = new Queue();
/*  91 */   protected final Queue waitingTakes = new Queue();
/*     */   
/*     */ 
/*     */ 
/*     */   public int capacity()
/*     */   {
/*  97 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object peek()
/*     */   {
/* 103 */     return null;
/*     */   }
/*     */   
/*     */   public void put(Object paramObject) throws InterruptedException {
/* 107 */     if (paramObject == null) { throw new IllegalArgumentException();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     LinkedNode localLinkedNode2;
/*     */     
/*     */ 
/*     */     for (;;)
/*     */     {
/* 117 */       if (Thread.interrupted()) { throw new InterruptedException();
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 123 */       localLinkedNode2 = null;
/*     */       LinkedNode localLinkedNode1;
/* 125 */       synchronized (this)
/*     */       {
/* 127 */         localLinkedNode1 = this.waitingTakes.deq();
/*     */         
/*     */ 
/* 130 */         if (localLinkedNode1 == null) {
/* 131 */           this.waitingPuts.enq(localLinkedNode2 = new LinkedNode(paramObject));
/*     */         }
/*     */       }
/* 134 */       if (localLinkedNode1 != null)
/*     */       {
/*     */ 
/* 137 */         synchronized (localLinkedNode1) {
/* 138 */           if (localLinkedNode1.value != CANCELLED) {
/* 139 */             localLinkedNode1.value = paramObject;
/* 140 */             localLinkedNode1.notify();
/* 141 */             return;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 149 */     synchronized (localLinkedNode2) {
/*     */       try {
/* 151 */         while (localLinkedNode2.value != null)
/* 152 */           localLinkedNode2.wait();
/* 153 */         return;
/*     */       }
/*     */       catch (InterruptedException localInterruptedException)
/*     */       {
/* 157 */         if (localLinkedNode2.value == null) {
/* 158 */           Thread.currentThread().interrupt();
/* 159 */           return;
/*     */         }
/*     */         
/* 162 */         localLinkedNode2.value = CANCELLED;
/* 163 */         throw localInterruptedException;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public Object take() throws InterruptedException
/*     */   {
/*     */     LinkedNode localLinkedNode2;
/*     */     Object localObject2;
/*     */     Object localObject3;
/*     */     for (;;)
/*     */     {
/* 175 */       if (Thread.interrupted()) { throw new InterruptedException();
/*     */       }
/*     */       
/* 178 */       localLinkedNode2 = null;
/*     */       LinkedNode localLinkedNode1;
/* 180 */       synchronized (this) {
/* 181 */         localLinkedNode1 = this.waitingPuts.deq();
/* 182 */         if (localLinkedNode1 == null) {
/* 183 */           this.waitingTakes.enq(localLinkedNode2 = new LinkedNode());
/*     */         }
/*     */       }
/* 186 */       if (localLinkedNode1 != null) {
/* 187 */         synchronized (localLinkedNode1) {
/* 188 */           localObject2 = localLinkedNode1.value;
/* 189 */           if (localObject2 != CANCELLED) {
/* 190 */             localLinkedNode1.value = null;
/* 191 */             localLinkedNode1.next = null;
/* 192 */             localLinkedNode1.notify();
/* 193 */             localObject3 = localObject2;return localObject3;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 199 */     synchronized (localLinkedNode2) {
/*     */       try {
/*     */         for (;;) {
/* 202 */           localObject2 = localLinkedNode2.value;
/* 203 */           if (localObject2 != null) {
/* 204 */             localLinkedNode2.value = null;
/* 205 */             localLinkedNode2.next = null;
/* 206 */             localObject3 = localObject2;return localObject3;
/*     */           }
/*     */           
/* 209 */           localLinkedNode2.wait();
/*     */         }
/*     */       }
/*     */       catch (InterruptedException localInterruptedException) {
/* 213 */         localObject3 = localLinkedNode2.value;
/* 214 */         if (localObject3 != null) {
/* 215 */           localLinkedNode2.value = null;
/* 216 */           localLinkedNode2.next = null;
/* 217 */           Thread.currentThread().interrupt();
/* 218 */           Object localObject5 = localObject3;return localObject5;
/*     */         }
/*     */         
/* 221 */         localLinkedNode2.value = CANCELLED;
/* 222 */         throw localInterruptedException;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean offer(Object paramObject, long paramLong)
/*     */     throws InterruptedException
/*     */   {
/* 236 */     if (paramObject == null) throw new IllegalArgumentException();
/* 237 */     long l1 = paramLong;
/* 238 */     long l2 = 0L;
/*     */     LinkedNode localLinkedNode2;
/*     */     do {
/* 241 */       if (Thread.interrupted()) { throw new InterruptedException();
/*     */       }
/*     */       
/* 244 */       localLinkedNode2 = null;
/*     */       LinkedNode localLinkedNode1;
/* 246 */       synchronized (this) {
/* 247 */         localLinkedNode1 = this.waitingTakes.deq();
/* 248 */         if (localLinkedNode1 == null) {
/* 249 */           if (l1 <= 0L) {
/* 250 */             boolean bool1 = false;return bool1;
/*     */           }
/* 252 */           this.waitingPuts.enq(localLinkedNode2 = new LinkedNode(paramObject));
/*     */         }
/*     */       }
/*     */       
/* 256 */       if (localLinkedNode1 != null) {
/* 257 */         synchronized (localLinkedNode1) {
/* 258 */           if (localLinkedNode1.value != CANCELLED) {
/* 259 */             localLinkedNode1.value = paramObject;
/* 260 */             localLinkedNode1.notify();
/* 261 */             boolean bool2 = true;return bool2;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 266 */       long l3 = System.currentTimeMillis();
/* 267 */       if (l2 == 0L) {
/* 268 */         l2 = l3;
/*     */       } else {
/* 270 */         l1 = paramLong - (l3 - l2);
/*     */       }
/* 272 */     } while (localLinkedNode2 == null);
/* 273 */     synchronized (localLinkedNode2) {
/*     */       try {
/*     */         for (;;) {
/* 276 */           if (localLinkedNode2.value == null) {
/* 277 */             boolean bool3 = true;return bool3; }
/* 278 */           if (l1 <= 0L) {
/* 279 */             localLinkedNode2.value = CANCELLED;
/* 280 */             bool4 = false;return bool4;
/*     */           }
/* 282 */           localLinkedNode2.wait(l1);
/* 283 */           l1 = paramLong - (System.currentTimeMillis() - l2);
/*     */         }
/*     */       } catch (InterruptedException localInterruptedException) {
/*     */         boolean bool4;
/* 287 */         if (localLinkedNode2.value == null) {
/* 288 */           Thread.currentThread().interrupt();
/* 289 */           bool4 = true;return bool4;
/*     */         }
/*     */         
/* 292 */         localLinkedNode2.value = CANCELLED;
/* 293 */         throw localInterruptedException;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public Object poll(long paramLong)
/*     */     throws InterruptedException
/*     */   {
/* 302 */     long l1 = paramLong;
/* 303 */     long l2 = 0L;
/*     */     LinkedNode localLinkedNode2;
/*     */     do {
/* 306 */       if (Thread.interrupted()) { throw new InterruptedException();
/*     */       }
/*     */       
/* 309 */       localLinkedNode2 = null;
/*     */       LinkedNode localLinkedNode1;
/* 311 */       synchronized (this) {
/* 312 */         localLinkedNode1 = this.waitingPuts.deq();
/* 313 */         if (localLinkedNode1 == null) {
/* 314 */           if (l1 <= 0L) {
/* 315 */             ??? = null;return ???;
/*     */           }
/* 317 */           this.waitingTakes.enq(localLinkedNode2 = new LinkedNode());
/*     */         }
/*     */       }
/*     */       
/* 321 */       if (localLinkedNode1 != null) {
/* 322 */         synchronized (localLinkedNode1) {
/* 323 */           Object localObject3 = localLinkedNode1.value;
/* 324 */           if (localObject3 != CANCELLED) {
/* 325 */             localLinkedNode1.value = null;
/* 326 */             localLinkedNode1.next = null;
/* 327 */             localLinkedNode1.notify();
/* 328 */             ??? = localObject3;return ???;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 333 */       long l3 = System.currentTimeMillis();
/* 334 */       if (l2 == 0L) {
/* 335 */         l2 = l3;
/*     */       } else {
/* 337 */         l1 = paramLong - (l3 - l2);
/*     */       }
/* 339 */     } while (localLinkedNode2 == null);
/* 340 */     synchronized (localLinkedNode2) {
/*     */       try {
/*     */         for (;;) {
/* 343 */           Object localObject6 = localLinkedNode2.value;
/* 344 */           if (localObject6 != null) {
/* 345 */             localLinkedNode2.value = null;
/* 346 */             localLinkedNode2.next = null;
/* 347 */             localObject7 = localObject6;return localObject7;
/*     */           }
/* 349 */           if (l1 <= 0L) {
/* 350 */             localLinkedNode2.value = CANCELLED;
/* 351 */             localObject7 = null;return localObject7;
/*     */           }
/* 353 */           localLinkedNode2.wait(l1);
/* 354 */           l1 = paramLong - (System.currentTimeMillis() - l2);
/*     */         }
/*     */       }
/*     */       catch (InterruptedException localInterruptedException) {
/* 358 */         Object localObject7 = localLinkedNode2.value;
/* 359 */         if (localObject7 != null) {
/* 360 */           localLinkedNode2.value = null;
/* 361 */           localLinkedNode2.next = null;
/* 362 */           Thread.currentThread().interrupt();
/* 363 */           Object localObject8 = localObject7;return localObject8;
/*     */         }
/*     */         
/* 366 */         localLinkedNode2.value = CANCELLED;
/* 367 */         throw localInterruptedException;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/SynchronousChannel.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */