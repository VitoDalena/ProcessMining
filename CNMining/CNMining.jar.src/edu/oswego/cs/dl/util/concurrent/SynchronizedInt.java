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
/*     */ public class SynchronizedInt
/*     */   extends SynchronizedVariable
/*     */   implements Comparable, Cloneable
/*     */ {
/*     */   protected int value_;
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
/*     */   public SynchronizedInt(int paramInt)
/*     */   {
/*  32 */     this.value_ = paramInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SynchronizedInt(int paramInt, Object paramObject)
/*     */   {
/*  40 */     super(paramObject);
/*  41 */     this.value_ = paramInt;
/*     */   }
/*     */   
/*     */ 
/*     */   public final int get()
/*     */   {
/*  47 */     synchronized (this.lock_) { int i = this.value_;return i;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int set(int paramInt)
/*     */   {
/*  55 */     synchronized (this.lock_) {
/*  56 */       int i = this.value_;
/*  57 */       this.value_ = paramInt;
/*  58 */       int j = i;return j;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean commit(int paramInt1, int paramInt2)
/*     */   {
/*  67 */     synchronized (this.lock_) {
/*  68 */       boolean bool1 = paramInt1 == this.value_;
/*  69 */       if (bool1) this.value_ = paramInt2;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public int swap(SynchronizedInt paramSynchronizedInt)
/*     */   {
/*  85 */     if (paramSynchronizedInt == this) return get();
/*  86 */     SynchronizedInt localSynchronizedInt1 = this;
/*  87 */     SynchronizedInt localSynchronizedInt2 = paramSynchronizedInt;
/*  88 */     if (System.identityHashCode(localSynchronizedInt1) > System.identityHashCode(localSynchronizedInt2)) {
/*  89 */       localSynchronizedInt1 = paramSynchronizedInt;
/*  90 */       localSynchronizedInt2 = this;
/*     */     }
/*  92 */     synchronized (localSynchronizedInt1.lock_) {
/*  93 */       synchronized (localSynchronizedInt2.lock_) {
/*  94 */         localSynchronizedInt1.set(localSynchronizedInt2.set(localSynchronizedInt1.get()));
/*  95 */         int i = get();return i;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int increment()
/*     */   {
/* 105 */     synchronized (this.lock_) {
/* 106 */       int i = ++this.value_;return i;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int decrement()
/*     */   {
/* 115 */     synchronized (this.lock_) {
/* 116 */       int i = --this.value_;return i;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int add(int paramInt)
/*     */   {
/* 125 */     synchronized (this.lock_) {
/* 126 */       int i = this.value_ += paramInt;return i;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int subtract(int paramInt)
/*     */   {
/* 135 */     synchronized (this.lock_) {
/* 136 */       int i = this.value_ -= paramInt;return i;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized int multiply(int paramInt)
/*     */   {
/* 145 */     synchronized (this.lock_) {
/* 146 */       int i = this.value_ *= paramInt;return i;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int divide(int paramInt)
/*     */   {
/* 155 */     synchronized (this.lock_) {
/* 156 */       int i = this.value_ /= paramInt;return i;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int negate()
/*     */   {
/* 165 */     synchronized (this.lock_) {
/* 166 */       this.value_ = (-this.value_);
/* 167 */       int i = this.value_;return i;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int complement()
/*     */   {
/* 176 */     synchronized (this.lock_) {
/* 177 */       this.value_ ^= 0xFFFFFFFF;
/* 178 */       int i = this.value_;return i;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int and(int paramInt)
/*     */   {
/* 187 */     synchronized (this.lock_) {
/* 188 */       this.value_ &= paramInt;
/* 189 */       int i = this.value_;return i;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int or(int paramInt)
/*     */   {
/* 198 */     synchronized (this.lock_) {
/* 199 */       this.value_ |= paramInt;
/* 200 */       int i = this.value_;return i;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int xor(int paramInt)
/*     */   {
/* 210 */     synchronized (this.lock_) {
/* 211 */       this.value_ ^= paramInt;
/* 212 */       int i = this.value_;return i;
/*     */     }
/*     */   }
/*     */   
/*     */   public int compareTo(int paramInt) {
/* 217 */     int i = get();
/* 218 */     return i == paramInt ? 0 : i < paramInt ? -1 : 1;
/*     */   }
/*     */   
/*     */   public int compareTo(SynchronizedInt paramSynchronizedInt) {
/* 222 */     return compareTo(paramSynchronizedInt.get());
/*     */   }
/*     */   
/*     */   public int compareTo(Object paramObject) {
/* 226 */     return compareTo((SynchronizedInt)paramObject);
/*     */   }
/*     */   
/*     */   public boolean equals(Object paramObject) {
/* 230 */     if ((paramObject != null) && ((paramObject instanceof SynchronizedInt)))
/*     */     {
/* 232 */       return get() == ((SynchronizedInt)paramObject).get();
/*     */     }
/* 234 */     return false;
/*     */   }
/*     */   
/* 237 */   public int hashCode() { return get(); }
/*     */   
/* 239 */   public String toString() { return String.valueOf(get()); }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/SynchronizedInt.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */