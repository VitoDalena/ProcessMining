/*     */ package org.apache.lucene.util;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class PriorityQueue
/*     */ {
/*     */   private Object[] heap;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private int size;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private int maxSize;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract boolean lessThan(Object paramObject1, Object paramObject2);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final void initialize(int maxSize)
/*     */   {
/*  33 */     this.size = 0;
/*  34 */     int heapSize = maxSize + 1;
/*  35 */     this.heap = new Object[heapSize];
/*  36 */     this.maxSize = maxSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void put(Object element)
/*     */   {
/*  45 */     this.size += 1;
/*  46 */     this.heap[this.size] = element;
/*  47 */     upHeap();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean insert(Object element)
/*     */   {
/*  57 */     if (this.size < this.maxSize) {
/*  58 */       put(element);
/*  59 */       return true;
/*     */     }
/*  61 */     if ((this.size > 0) && (!lessThan(element, top()))) {
/*  62 */       this.heap[1] = element;
/*  63 */       adjustTop();
/*  64 */       return true;
/*     */     }
/*     */     
/*  67 */     return false;
/*     */   }
/*     */   
/*     */   public final Object top()
/*     */   {
/*  72 */     if (this.size > 0) {
/*  73 */       return this.heap[1];
/*     */     }
/*  75 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public final Object pop()
/*     */   {
/*  81 */     if (this.size > 0) {
/*  82 */       Object result = this.heap[1];
/*  83 */       this.heap[1] = this.heap[this.size];
/*  84 */       this.heap[this.size] = null;
/*  85 */       this.size -= 1;
/*  86 */       downHeap();
/*  87 */       return result;
/*     */     }
/*  89 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void adjustTop()
/*     */   {
/* 100 */     downHeap();
/*     */   }
/*     */   
/*     */ 
/*     */   public final int size()
/*     */   {
/* 106 */     return this.size;
/*     */   }
/*     */   
/*     */   public final void clear()
/*     */   {
/* 111 */     for (int i = 0; i <= this.size; i++)
/* 112 */       this.heap[i] = null;
/* 113 */     this.size = 0;
/*     */   }
/*     */   
/*     */   private final void upHeap() {
/* 117 */     int i = this.size;
/* 118 */     Object node = this.heap[i];
/* 119 */     int j = i >>> 1;
/* 120 */     while ((j > 0) && (lessThan(node, this.heap[j]))) {
/* 121 */       this.heap[i] = this.heap[j];
/* 122 */       i = j;
/* 123 */       j >>>= 1;
/*     */     }
/* 125 */     this.heap[i] = node;
/*     */   }
/*     */   
/*     */   private final void downHeap() {
/* 129 */     int i = 1;
/* 130 */     Object node = this.heap[i];
/* 131 */     int j = i << 1;
/* 132 */     int k = j + 1;
/* 133 */     if ((k <= this.size) && (lessThan(this.heap[k], this.heap[j]))) {
/* 134 */       j = k;
/*     */     }
/* 136 */     while ((j <= this.size) && (lessThan(this.heap[j], node))) {
/* 137 */       this.heap[i] = this.heap[j];
/* 138 */       i = j;
/* 139 */       j = i << 1;
/* 140 */       k = j + 1;
/* 141 */       if ((k <= this.size) && (lessThan(this.heap[k], this.heap[j]))) {
/* 142 */         j = k;
/*     */       }
/*     */     }
/* 145 */     this.heap[i] = node;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/util/PriorityQueue.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */