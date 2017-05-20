/*     */ package edu.oswego.cs.dl.util.concurrent;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ public class SyncMap
/*     */   implements Map
/*     */ {
/*     */   protected final Map c_;
/*     */   protected final Sync rd_;
/*     */   protected final Sync wr_;
/*  50 */   protected final SynchronizedLong syncFailures_ = new SynchronizedLong(0L);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SyncMap(Map paramMap, Sync paramSync)
/*     */   {
/*  59 */     this(paramMap, paramSync, paramSync);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SyncMap(Map paramMap, ReadWriteLock paramReadWriteLock)
/*     */   {
/*  68 */     this(paramMap, paramReadWriteLock.readLock(), paramReadWriteLock.writeLock());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SyncMap(Map paramMap, Sync paramSync1, Sync paramSync2)
/*     */   {
/*  76 */     this.c_ = paramMap;
/*  77 */     this.rd_ = paramSync1;
/*  78 */     this.wr_ = paramSync2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Sync readerSync()
/*     */   {
/*  86 */     return this.rd_;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Sync writerSync()
/*     */   {
/*  94 */     return this.wr_;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public long syncFailures()
/*     */   {
/* 101 */     return this.syncFailures_.get();
/*     */   }
/*     */   
/*     */   protected boolean beforeRead()
/*     */   {
/*     */     try
/*     */     {
/* 108 */       this.rd_.acquire();
/* 109 */       return false;
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {
/* 112 */       this.syncFailures_.increment(); }
/* 113 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void afterRead(boolean paramBoolean)
/*     */   {
/* 119 */     if (paramBoolean) {
/* 120 */       Thread.currentThread().interrupt();
/*     */     }
/*     */     else {
/* 123 */       this.rd_.release();
/*     */     }
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 129 */     boolean bool = beforeRead();
/*     */     try {
/* 131 */       return this.c_.hashCode();
/*     */     }
/*     */     finally {
/* 134 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean equals(Object paramObject) {
/* 139 */     boolean bool1 = beforeRead();
/*     */     try {
/* 141 */       return this.c_.equals(paramObject);
/*     */     }
/*     */     finally {
/* 144 */       afterRead(bool1);
/*     */     }
/*     */   }
/*     */   
/*     */   public int size() {
/* 149 */     boolean bool = beforeRead();
/*     */     try {
/* 151 */       return this.c_.size();
/*     */     }
/*     */     finally {
/* 154 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 159 */     boolean bool1 = beforeRead();
/*     */     try {
/* 161 */       return this.c_.isEmpty();
/*     */     }
/*     */     finally {
/* 164 */       afterRead(bool1);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object paramObject) {
/* 169 */     boolean bool1 = beforeRead();
/*     */     try {
/* 171 */       return this.c_.containsKey(paramObject);
/*     */     }
/*     */     finally {
/* 174 */       afterRead(bool1);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object paramObject) {
/* 179 */     boolean bool1 = beforeRead();
/*     */     try {
/* 181 */       return this.c_.containsValue(paramObject);
/*     */     }
/*     */     finally {
/* 184 */       afterRead(bool1);
/*     */     }
/*     */   }
/*     */   
/*     */   public Object get(Object paramObject) {
/* 189 */     boolean bool = beforeRead();
/*     */     try {
/* 191 */       return this.c_.get(paramObject);
/*     */     }
/*     */     finally {
/* 194 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public Object put(Object paramObject1, Object paramObject2)
/*     */   {
/*     */     try {
/* 201 */       this.wr_.acquire();
/*     */       try {
/* 203 */         return this.c_.put(paramObject1, paramObject2);
/*     */       }
/*     */       finally {
/* 206 */         this.wr_.release();
/*     */       }
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {
/* 210 */       Thread.currentThread().interrupt();
/* 211 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*     */   public Object remove(Object paramObject) {
/*     */     try {
/* 217 */       this.wr_.acquire();
/*     */       try {
/* 219 */         return this.c_.remove(paramObject);
/*     */       }
/*     */       finally {
/* 222 */         this.wr_.release();
/*     */       }
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {
/* 226 */       Thread.currentThread().interrupt();
/* 227 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*     */   public void putAll(Map paramMap) {
/*     */     try {
/* 233 */       this.wr_.acquire();
/*     */       try {
/* 235 */         this.c_.putAll(paramMap);
/*     */       }
/*     */       finally {
/* 238 */         this.wr_.release();
/*     */       }
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {
/* 242 */       Thread.currentThread().interrupt();
/* 243 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*     */   public void clear()
/*     */   {
/*     */     try {
/* 250 */       this.wr_.acquire();
/*     */       try {
/* 252 */         this.c_.clear();
/*     */       }
/*     */       finally {
/* 255 */         this.wr_.release();
/*     */       }
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {
/* 259 */       Thread.currentThread().interrupt();
/* 260 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/* 264 */   private transient Set keySet_ = null;
/* 265 */   private transient Set entrySet_ = null;
/* 266 */   private transient Collection values_ = null;
/*     */   
/*     */   public Set keySet() {
/* 269 */     boolean bool = beforeRead();
/*     */     try {
/* 271 */       if (this.keySet_ == null)
/* 272 */         this.keySet_ = new SyncSet(this.c_.keySet(), this.rd_, this.wr_);
/* 273 */       return this.keySet_;
/*     */     }
/*     */     finally {
/* 276 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 281 */     boolean bool = beforeRead();
/*     */     try {
/* 283 */       if (this.entrySet_ == null)
/* 284 */         this.entrySet_ = new SyncSet(this.c_.entrySet(), this.rd_, this.wr_);
/* 285 */       return this.entrySet_;
/*     */     }
/*     */     finally {
/* 288 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public Collection values()
/*     */   {
/* 294 */     boolean bool = beforeRead();
/*     */     try {
/* 296 */       if (this.values_ == null)
/* 297 */         this.values_ = new SyncCollection(this.c_.values(), this.rd_, this.wr_);
/* 298 */       return this.values_;
/*     */     }
/*     */     finally {
/* 301 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/SyncMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */