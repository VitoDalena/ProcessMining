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
/*     */ public class SynchronizedLong
/*     */   extends SynchronizedVariable
/*     */   implements Comparable, Cloneable
/*     */ {
/*     */   protected long value_;
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
/*     */   public SynchronizedLong(long paramLong)
/*     */   {
/*  32 */     this.value_ = paramLong;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SynchronizedLong(long paramLong, Object paramObject)
/*     */   {
/*  40 */     super(paramObject);
/*  41 */     this.value_ = paramLong;
/*     */   }
/*     */   
/*     */ 
/*     */   public final long get()
/*     */   {
/*  47 */     synchronized (this.lock_) { long l = this.value_;return l;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public long set(long paramLong)
/*     */   {
/*  55 */     synchronized (this.lock_) {
/*  56 */       long l1 = this.value_;
/*  57 */       this.value_ = paramLong;
/*  58 */       long l2 = l1;return l2;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean commit(long paramLong1, long paramLong2)
/*     */   {
/*  67 */     synchronized (this.lock_) {
/*  68 */       boolean bool1 = paramLong1 == this.value_;
/*  69 */       if (bool1) this.value_ = paramLong2;
/*  70 */       boolean bool2 = bool1;return bool2;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long swap(SynchronizedLong paramSynchronizedLong)
/*     */   {
/*  82 */     if (paramSynchronizedLong != this) {
/*  83 */       SynchronizedLong localSynchronizedLong1 = this;
/*  84 */       SynchronizedLong localSynchronizedLong2 = paramSynchronizedLong;
/*  85 */       if (System.identityHashCode(localSynchronizedLong1) > System.identityHashCode(localSynchronizedLong2)) {
/*  86 */         localSynchronizedLong1 = paramSynchronizedLong;
/*  87 */         localSynchronizedLong2 = this;
/*     */       }
/*  89 */       synchronized (localSynchronizedLong1.lock_) {
/*  90 */         synchronized (localSynchronizedLong2.lock_) {
/*  91 */           localSynchronizedLong1.set(localSynchronizedLong2.set(localSynchronizedLong1.get()));
/*     */         }
/*     */       }
/*     */     }
/*  95 */     return this.value_;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long increment()
/*     */   {
/* 103 */     synchronized (this.lock_) {
/* 104 */       long l = ++this.value_;return l;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long decrement()
/*     */   {
/* 113 */     synchronized (this.lock_) {
/* 114 */       long l = --this.value_;return l;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long add(long paramLong)
/*     */   {
/* 123 */     synchronized (this.lock_) {
/* 124 */       long l = this.value_ += paramLong;return l;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long subtract(long paramLong)
/*     */   {
/* 133 */     synchronized (this.lock_) {
/* 134 */       long l = this.value_ -= paramLong;return l;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized long multiply(long paramLong)
/*     */   {
/* 143 */     synchronized (this.lock_) {
/* 144 */       long l = this.value_ *= paramLong;return l;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long divide(long paramLong)
/*     */   {
/* 153 */     synchronized (this.lock_) {
/* 154 */       long l = this.value_ /= paramLong;return l;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long negate()
/*     */   {
/* 163 */     synchronized (this.lock_) {
/* 164 */       this.value_ = (-this.value_);
/* 165 */       long l = this.value_;return l;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long complement()
/*     */   {
/* 174 */     synchronized (this.lock_) {
/* 175 */       this.value_ ^= 0xFFFFFFFFFFFFFFFF;
/* 176 */       long l = this.value_;return l;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long and(long paramLong)
/*     */   {
/* 185 */     synchronized (this.lock_) {
/* 186 */       this.value_ &= paramLong;
/* 187 */       long l = this.value_;return l;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long or(long paramLong)
/*     */   {
/* 196 */     synchronized (this.lock_) {
/* 197 */       this.value_ |= paramLong;
/* 198 */       long l = this.value_;return l;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long xor(long paramLong)
/*     */   {
/* 208 */     synchronized (this.lock_) {
/* 209 */       this.value_ ^= paramLong;
/* 210 */       long l = this.value_;return l;
/*     */     }
/*     */   }
/*     */   
/*     */   public int compareTo(long paramLong)
/*     */   {
/* 216 */     long l = get();
/* 217 */     return l == paramLong ? 0 : l < paramLong ? -1 : 1;
/*     */   }
/*     */   
/*     */   public int compareTo(SynchronizedLong paramSynchronizedLong) {
/* 221 */     return compareTo(paramSynchronizedLong.get());
/*     */   }
/*     */   
/*     */   public int compareTo(Object paramObject) {
/* 225 */     return compareTo((SynchronizedLong)paramObject);
/*     */   }
/*     */   
/*     */   public boolean equals(Object paramObject) {
/* 229 */     if ((paramObject != null) && ((paramObject instanceof SynchronizedLong)))
/*     */     {
/* 231 */       return get() == ((SynchronizedLong)paramObject).get();
/*     */     }
/* 233 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 237 */     long l = get();
/* 238 */     return (int)(l ^ l >> 32);
/*     */   }
/*     */   
/* 241 */   public String toString() { return String.valueOf(get()); }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/SynchronizedLong.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */