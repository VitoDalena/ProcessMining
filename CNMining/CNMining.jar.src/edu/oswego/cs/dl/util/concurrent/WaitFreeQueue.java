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
/*     */ public class WaitFreeQueue
/*     */   implements Channel
/*     */ {
/*     */   protected static final class Node
/*     */   {
/*     */     protected final Object value;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected volatile Node next;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected Node(Object paramObject)
/*     */     {
/*  64 */       this.value = paramObject;
/*     */     }
/*     */     
/*     */     protected synchronized boolean CASNext(Node paramNode1, Node paramNode2) {
/*  68 */       if (this.next == paramNode1) {
/*  69 */         this.next = paramNode2;
/*  70 */         return true;
/*     */       }
/*     */       
/*  73 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*  78 */   protected volatile Node head = new Node(null);
/*     */   
/*  80 */   protected volatile Node tail = this.head;
/*     */   
/*     */ 
/*  83 */   protected final Object tailLock = new Object();
/*     */   
/*     */   protected synchronized boolean CASHead(Node paramNode1, Node paramNode2)
/*     */   {
/*  87 */     if (this.head == paramNode1) {
/*  88 */       this.head = paramNode2;
/*  89 */       return true;
/*     */     }
/*     */     
/*  92 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean CASTail(Node paramNode1, Node paramNode2)
/*     */   {
/*  97 */     synchronized (this.tailLock) {
/*  98 */       if (this.tail == paramNode1) {
/*  99 */         this.tail = paramNode2;
/* 100 */         bool = true;return bool;
/*     */       }
/*     */       
/* 103 */       boolean bool = false;return bool;
/*     */     }
/*     */   }
/*     */   
/*     */   public void put(Object paramObject) throws InterruptedException {
/* 108 */     if (paramObject == null) throw new IllegalArgumentException();
/* 109 */     if (Thread.interrupted()) throw new InterruptedException();
/* 110 */     Node localNode1 = new Node(paramObject);
/*     */     for (;;)
/*     */     {
/* 113 */       Node localNode2 = this.tail;
/*     */       
/* 115 */       if (localNode2.CASNext(null, localNode1))
/*     */       {
/*     */ 
/* 118 */         CASTail(localNode2, localNode1);
/* 119 */         return;
/*     */       }
/*     */       
/*     */ 
/* 123 */       CASTail(localNode2, localNode2.next);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean offer(Object paramObject, long paramLong) throws InterruptedException {
/* 128 */     put(paramObject);
/* 129 */     return true;
/*     */   }
/*     */   
/*     */   protected Object extract() throws InterruptedException { Node localNode1;
/*     */     Node localNode2;
/*     */     Object localObject;
/* 135 */     do { localNode1 = this.head;
/* 136 */       localNode2 = localNode1.next;
/*     */       
/* 138 */       if (localNode2 == null) {
/* 139 */         return null;
/*     */       }
/* 141 */       localObject = localNode2.value;
/* 142 */     } while (!CASHead(localNode1, localNode2));
/* 143 */     return localObject;
/*     */   }
/*     */   
/*     */   public Object peek()
/*     */   {
/* 148 */     Node localNode = this.head.next;
/*     */     
/* 150 */     if (localNode == null) {
/* 151 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 156 */     synchronized (this) {
/* 157 */       Object localObject1 = localNode.value;return localObject1;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object take()
/*     */     throws InterruptedException
/*     */   {
/* 170 */     if (Thread.interrupted()) throw new InterruptedException();
/*     */     for (;;) {
/* 172 */       Object localObject = extract();
/* 173 */       if (localObject != null) {
/* 174 */         return localObject;
/*     */       }
/* 176 */       Thread.sleep(0L);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object poll(long paramLong)
/*     */     throws InterruptedException
/*     */   {
/* 186 */     if (Thread.interrupted()) throw new InterruptedException();
/* 187 */     if (paramLong <= 0L) {
/* 188 */       return extract();
/*     */     }
/* 190 */     long l = System.currentTimeMillis();
/*     */     for (;;) {
/* 192 */       Object localObject = extract();
/* 193 */       if (localObject != null)
/* 194 */         return localObject;
/* 195 */       if (System.currentTimeMillis() - l >= paramLong) {
/* 196 */         return null;
/*     */       }
/* 198 */       Thread.sleep(0L);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/WaitFreeQueue.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */