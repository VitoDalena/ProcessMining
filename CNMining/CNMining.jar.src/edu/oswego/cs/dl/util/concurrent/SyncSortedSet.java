/*     */ package edu.oswego.cs.dl.util.concurrent;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.SortedSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SyncSortedSet
/*     */   extends SyncSet
/*     */   implements SortedSet
/*     */ {
/*     */   public SyncSortedSet(SortedSet paramSortedSet, Sync paramSync)
/*     */   {
/*  35 */     super(paramSortedSet, paramSync);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SyncSortedSet(SortedSet paramSortedSet, ReadWriteLock paramReadWriteLock)
/*     */   {
/*  43 */     super(paramSortedSet, paramReadWriteLock.readLock(), paramReadWriteLock.writeLock());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SyncSortedSet(SortedSet paramSortedSet, Sync paramSync1, Sync paramSync2)
/*     */   {
/*  51 */     super(paramSortedSet, paramSync1, paramSync2);
/*     */   }
/*     */   
/*     */   protected SortedSet baseSortedSet()
/*     */   {
/*  56 */     return (SortedSet)this.c_;
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/*  60 */     boolean bool = beforeRead();
/*     */     try {
/*  62 */       return baseSortedSet().comparator();
/*     */     }
/*     */     finally {
/*  65 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public Object first() {
/*  70 */     boolean bool = beforeRead();
/*     */     try {
/*  72 */       return baseSortedSet().first();
/*     */     }
/*     */     finally {
/*  75 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public Object last() {
/*  80 */     boolean bool = beforeRead();
/*     */     try {
/*  82 */       return baseSortedSet().last();
/*     */     }
/*     */     finally {
/*  85 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public SortedSet subSet(Object paramObject1, Object paramObject2)
/*     */   {
/*  91 */     boolean bool = beforeRead();
/*     */     try {
/*  93 */       return new SyncSortedSet(baseSortedSet().subSet(paramObject1, paramObject2), this.rd_, this.wr_);
/*     */     }
/*     */     finally
/*     */     {
/*  97 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public SortedSet headSet(Object paramObject) {
/* 102 */     boolean bool = beforeRead();
/*     */     try {
/* 104 */       return new SyncSortedSet(baseSortedSet().headSet(paramObject), this.rd_, this.wr_);
/*     */     }
/*     */     finally
/*     */     {
/* 108 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public SortedSet tailSet(Object paramObject) {
/* 113 */     boolean bool = beforeRead();
/*     */     try {
/* 115 */       return new SyncSortedSet(baseSortedSet().tailSet(paramObject), this.rd_, this.wr_);
/*     */     }
/*     */     finally
/*     */     {
/* 119 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/SyncSortedSet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */