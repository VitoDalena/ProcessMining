/*     */ package edu.oswego.cs.dl.util.concurrent;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.SortedMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SyncSortedMap
/*     */   extends SyncMap
/*     */   implements SortedMap
/*     */ {
/*     */   public SyncSortedMap(SortedMap paramSortedMap, Sync paramSync)
/*     */   {
/*  35 */     this(paramSortedMap, paramSync, paramSync);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SyncSortedMap(SortedMap paramSortedMap, ReadWriteLock paramReadWriteLock)
/*     */   {
/*  43 */     super(paramSortedMap, paramReadWriteLock.readLock(), paramReadWriteLock.writeLock());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SyncSortedMap(SortedMap paramSortedMap, Sync paramSync1, Sync paramSync2)
/*     */   {
/*  51 */     super(paramSortedMap, paramSync1, paramSync2);
/*     */   }
/*     */   
/*     */   protected SortedMap baseSortedMap()
/*     */   {
/*  56 */     return (SortedMap)this.c_;
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/*  60 */     boolean bool = beforeRead();
/*     */     try {
/*  62 */       return baseSortedMap().comparator();
/*     */     }
/*     */     finally {
/*  65 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public Object firstKey() {
/*  70 */     boolean bool = beforeRead();
/*     */     try {
/*  72 */       return baseSortedMap().firstKey();
/*     */     }
/*     */     finally {
/*  75 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public Object lastKey() {
/*  80 */     boolean bool = beforeRead();
/*     */     try {
/*  82 */       return baseSortedMap().lastKey();
/*     */     }
/*     */     finally {
/*  85 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public SortedMap subMap(Object paramObject1, Object paramObject2)
/*     */   {
/*  91 */     boolean bool = beforeRead();
/*     */     try {
/*  93 */       return new SyncSortedMap(baseSortedMap().subMap(paramObject1, paramObject2), this.rd_, this.wr_);
/*     */     }
/*     */     finally
/*     */     {
/*  97 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public SortedMap headMap(Object paramObject) {
/* 102 */     boolean bool = beforeRead();
/*     */     try {
/* 104 */       return new SyncSortedMap(baseSortedMap().headMap(paramObject), this.rd_, this.wr_);
/*     */     }
/*     */     finally
/*     */     {
/* 108 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public SortedMap tailMap(Object paramObject) {
/* 113 */     boolean bool = beforeRead();
/*     */     try {
/* 115 */       return new SyncSortedMap(baseSortedMap().tailMap(paramObject), this.rd_, this.wr_);
/*     */     }
/*     */     finally
/*     */     {
/* 119 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/SyncSortedMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */