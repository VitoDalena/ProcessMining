/*     */ package edu.oswego.cs.dl.util.concurrent;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Heap
/*     */ {
/*     */   protected Object[] nodes_;
/*  31 */   protected int count_ = 0;
/*     */   
/*     */ 
/*     */   protected final Comparator cmp_;
/*     */   
/*     */ 
/*     */ 
/*     */   public Heap(int paramInt, Comparator paramComparator)
/*     */     throws IllegalArgumentException
/*     */   {
/*  41 */     if (paramInt <= 0) throw new IllegalArgumentException();
/*  42 */     this.nodes_ = new Object[paramInt];
/*  43 */     this.cmp_ = paramComparator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Heap(int paramInt)
/*     */   {
/*  52 */     this(paramInt, null);
/*     */   }
/*     */   
/*     */ 
/*     */   protected int compare(Object paramObject1, Object paramObject2)
/*     */   {
/*  58 */     if (this.cmp_ == null) {
/*  59 */       return ((Comparable)paramObject1).compareTo(paramObject2);
/*     */     }
/*  61 */     return this.cmp_.compare(paramObject1, paramObject2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*  66 */   protected final int parent(int paramInt) { return (paramInt - 1) / 2; }
/*  67 */   protected final int left(int paramInt) { return 2 * paramInt + 1; }
/*  68 */   protected final int right(int paramInt) { return 2 * (paramInt + 1); }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void insert(Object paramObject)
/*     */   {
/*  74 */     if (this.count_ >= this.nodes_.length) {
/*  75 */       i = 3 * this.nodes_.length / 2 + 1;
/*  76 */       Object[] arrayOfObject = new Object[i];
/*  77 */       System.arraycopy(this.nodes_, 0, arrayOfObject, 0, this.nodes_.length);
/*  78 */       this.nodes_ = arrayOfObject;
/*     */     }
/*     */     
/*  81 */     int i = this.count_;
/*  82 */     this.count_ += 1;
/*  83 */     while (i > 0) {
/*  84 */       int j = parent(i);
/*  85 */       if (compare(paramObject, this.nodes_[j]) >= 0) break;
/*  86 */       this.nodes_[i] = this.nodes_[j];
/*  87 */       i = j;
/*     */     }
/*     */     
/*     */ 
/*  91 */     this.nodes_[i] = paramObject;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized Object extract()
/*     */   {
/* 100 */     if (this.count_ < 1) { return null;
/*     */     }
/* 102 */     int i = 0;
/* 103 */     Object localObject1 = this.nodes_[i];
/* 104 */     this.count_ -= 1;
/* 105 */     Object localObject2 = this.nodes_[this.count_];
/*     */     for (;;) {
/* 107 */       int j = left(i);
/* 108 */       if (j >= this.count_) {
/*     */         break;
/*     */       }
/* 111 */       int k = right(i);
/* 112 */       int m = (k >= this.count_) || (compare(this.nodes_[j], this.nodes_[k]) < 0) ? j : k;
/* 113 */       if (compare(localObject2, this.nodes_[m]) <= 0) break;
/* 114 */       this.nodes_[i] = this.nodes_[m];
/* 115 */       i = m;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 120 */     this.nodes_[i] = localObject2;
/* 121 */     return localObject1;
/*     */   }
/*     */   
/*     */   public synchronized Object peek()
/*     */   {
/* 126 */     if (this.count_ > 0) {
/* 127 */       return this.nodes_[0];
/*     */     }
/* 129 */     return null;
/*     */   }
/*     */   
/*     */   public synchronized int size()
/*     */   {
/* 134 */     return this.count_;
/*     */   }
/*     */   
/*     */   public synchronized void clear()
/*     */   {
/* 139 */     this.count_ = 0;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/Heap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */