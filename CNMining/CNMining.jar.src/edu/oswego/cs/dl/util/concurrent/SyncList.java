/*     */ package edu.oswego.cs.dl.util.concurrent;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
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
/*     */ public class SyncList
/*     */   extends SyncCollection
/*     */   implements List
/*     */ {
/*     */   public SyncList(List paramList, Sync paramSync)
/*     */   {
/*  38 */     super(paramList, paramSync);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SyncList(List paramList, ReadWriteLock paramReadWriteLock)
/*     */   {
/*  46 */     super(paramList, paramReadWriteLock.readLock(), paramReadWriteLock.writeLock());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SyncList(List paramList, Sync paramSync1, Sync paramSync2)
/*     */   {
/*  54 */     super(paramList, paramSync1, paramSync2);
/*     */   }
/*     */   
/*     */   protected List baseList()
/*     */   {
/*  59 */     return (List)this.c_;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/*  64 */     boolean bool = beforeRead();
/*     */     try {
/*  66 */       return this.c_.hashCode();
/*     */     }
/*     */     finally {
/*  69 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean equals(Object paramObject) {
/*  74 */     boolean bool1 = beforeRead();
/*     */     try {
/*  76 */       return this.c_.equals(paramObject);
/*     */     }
/*     */     finally {
/*  79 */       afterRead(bool1);
/*     */     }
/*     */   }
/*     */   
/*     */   public Object get(int paramInt) {
/*  84 */     boolean bool = beforeRead();
/*     */     try {
/*  86 */       return baseList().get(paramInt);
/*     */     }
/*     */     finally {
/*  89 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public int indexOf(Object paramObject) {
/*  94 */     boolean bool = beforeRead();
/*     */     try {
/*  96 */       return baseList().indexOf(paramObject);
/*     */     }
/*     */     finally {
/*  99 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object paramObject)
/*     */   {
/* 105 */     boolean bool = beforeRead();
/*     */     try {
/* 107 */       return baseList().lastIndexOf(paramObject);
/*     */     }
/*     */     finally {
/* 110 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public List subList(int paramInt1, int paramInt2)
/*     */   {
/* 117 */     boolean bool = beforeRead();
/*     */     try {
/* 119 */       return new SyncList(baseList().subList(paramInt1, paramInt2), this.rd_, this.wr_);
/*     */     }
/*     */     finally {
/* 122 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public Object set(int paramInt, Object paramObject) {
/*     */     try {
/* 128 */       this.wr_.acquire();
/*     */       try {
/* 130 */         return baseList().set(paramInt, paramObject);
/*     */       }
/*     */       finally {
/* 133 */         this.wr_.release();
/*     */       }
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {
/* 137 */       Thread.currentThread().interrupt();
/* 138 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*     */   public Object remove(int paramInt)
/*     */   {
/*     */     try {
/* 145 */       this.wr_.acquire();
/*     */       try {
/* 147 */         return baseList().remove(paramInt);
/*     */       }
/*     */       finally {
/* 150 */         this.wr_.release();
/*     */       }
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {
/* 154 */       Thread.currentThread().interrupt();
/* 155 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*     */   public void add(int paramInt, Object paramObject) {
/*     */     try {
/* 161 */       this.wr_.acquire();
/*     */       try {
/* 163 */         baseList().add(paramInt, paramObject);
/*     */       }
/*     */       finally {
/* 166 */         this.wr_.release();
/*     */       }
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {
/* 170 */       Thread.currentThread().interrupt();
/* 171 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean addAll(int paramInt, Collection paramCollection) {
/*     */     try {
/* 177 */       this.wr_.acquire();
/*     */       try {
/* 179 */         return baseList().addAll(paramInt, paramCollection);
/*     */       }
/*     */       finally {
/* 182 */         this.wr_.release();
/*     */       }
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {
/* 186 */       Thread.currentThread().interrupt();
/* 187 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*     */   public ListIterator unprotectedListIterator() {
/* 192 */     boolean bool = beforeRead();
/*     */     try {
/* 194 */       return baseList().listIterator();
/*     */     }
/*     */     finally {
/* 197 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public ListIterator listIterator() {
/* 202 */     boolean bool = beforeRead();
/*     */     try {
/* 204 */       return new SyncCollectionListIterator(baseList().listIterator());
/*     */     }
/*     */     finally {
/* 207 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public ListIterator unprotectedListIterator(int paramInt) {
/* 212 */     boolean bool = beforeRead();
/*     */     try {
/* 214 */       return baseList().listIterator(paramInt);
/*     */     }
/*     */     finally {
/* 217 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public ListIterator listIterator(int paramInt) {
/* 222 */     boolean bool = beforeRead();
/*     */     try {
/* 224 */       return new SyncCollectionListIterator(baseList().listIterator(paramInt));
/*     */     }
/*     */     finally {
/* 227 */       afterRead(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SyncCollectionListIterator extends SyncCollection.SyncCollectionIterator implements ListIterator
/*     */   {
/*     */     SyncCollectionListIterator(Iterator paramIterator)
/*     */     {
/* 235 */       super(paramIterator);
/*     */     }
/*     */     
/*     */     protected ListIterator baseListIterator() {
/* 239 */       return (ListIterator)this.baseIterator_;
/*     */     }
/*     */     
/*     */     public boolean hasPrevious() {
/* 243 */       boolean bool1 = SyncList.this.beforeRead();
/*     */       try {
/* 245 */         return baseListIterator().hasPrevious();
/*     */       }
/*     */       finally {
/* 248 */         SyncList.this.afterRead(bool1);
/*     */       }
/*     */     }
/*     */     
/*     */     public Object previous() {
/* 253 */       boolean bool = SyncList.this.beforeRead();
/*     */       try {
/* 255 */         return baseListIterator().previous();
/*     */       }
/*     */       finally {
/* 258 */         SyncList.this.afterRead(bool);
/*     */       }
/*     */     }
/*     */     
/*     */     public int nextIndex() {
/* 263 */       boolean bool = SyncList.this.beforeRead();
/*     */       try {
/* 265 */         return baseListIterator().nextIndex();
/*     */       }
/*     */       finally {
/* 268 */         SyncList.this.afterRead(bool);
/*     */       }
/*     */     }
/*     */     
/*     */     public int previousIndex()
/*     */     {
/* 274 */       boolean bool = SyncList.this.beforeRead();
/*     */       try {
/* 276 */         return baseListIterator().previousIndex();
/*     */       }
/*     */       finally {
/* 279 */         SyncList.this.afterRead(bool);
/*     */       }
/*     */     }
/*     */     
/*     */     public void set(Object paramObject)
/*     */     {
/*     */       try {
/* 286 */         SyncList.this.wr_.acquire();
/*     */         try {
/* 288 */           baseListIterator().set(paramObject);
/*     */         }
/*     */         finally {
/* 291 */           SyncList.this.wr_.release();
/*     */         }
/*     */       }
/*     */       catch (InterruptedException localInterruptedException) {
/* 295 */         Thread.currentThread().interrupt();
/* 296 */         throw new UnsupportedOperationException();
/*     */       }
/*     */     }
/*     */     
/*     */     public void add(Object paramObject) {
/*     */       try {
/* 302 */         SyncList.this.wr_.acquire();
/*     */         try {
/* 304 */           baseListIterator().add(paramObject);
/*     */         }
/*     */         finally {
/* 307 */           SyncList.this.wr_.release();
/*     */         }
/*     */       }
/*     */       catch (InterruptedException localInterruptedException) {
/* 311 */         Thread.currentThread().interrupt();
/* 312 */         throw new UnsupportedOperationException();
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/SyncList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */