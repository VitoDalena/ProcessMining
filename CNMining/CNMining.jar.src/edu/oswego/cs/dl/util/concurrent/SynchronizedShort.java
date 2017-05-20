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
/*     */ public class SynchronizedShort
/*     */   extends SynchronizedVariable
/*     */   implements Comparable, Cloneable
/*     */ {
/*     */   protected short value_;
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
/*     */   public SynchronizedShort(short paramShort)
/*     */   {
/*  32 */     this.value_ = paramShort;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SynchronizedShort(short paramShort, Object paramObject)
/*     */   {
/*  40 */     super(paramObject);
/*  41 */     this.value_ = paramShort;
/*     */   }
/*     */   
/*     */ 
/*     */   public final short get()
/*     */   {
/*  47 */     synchronized (this.lock_) { short s = this.value_;return s;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public short set(short paramShort)
/*     */   {
/*  55 */     synchronized (this.lock_) {
/*  56 */       short s1 = this.value_;
/*  57 */       this.value_ = paramShort;
/*  58 */       short s2 = s1;return s2;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean commit(short paramShort1, short paramShort2)
/*     */   {
/*  67 */     synchronized (this.lock_) {
/*  68 */       boolean bool1 = paramShort1 == this.value_;
/*  69 */       if (bool1) this.value_ = paramShort2;
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
/*     */ 
/*     */   public short swap(SynchronizedShort paramSynchronizedShort)
/*     */   {
/*  86 */     if (paramSynchronizedShort == this) return get();
/*  87 */     SynchronizedShort localSynchronizedShort1 = this;
/*  88 */     SynchronizedShort localSynchronizedShort2 = paramSynchronizedShort;
/*  89 */     if (System.identityHashCode(localSynchronizedShort1) > System.identityHashCode(localSynchronizedShort2)) {
/*  90 */       localSynchronizedShort1 = paramSynchronizedShort;
/*  91 */       localSynchronizedShort2 = this;
/*     */     }
/*  93 */     synchronized (localSynchronizedShort1.lock_) {
/*  94 */       synchronized (localSynchronizedShort2.lock_) {
/*  95 */         localSynchronizedShort1.set(localSynchronizedShort2.set(localSynchronizedShort1.get()));
/*  96 */         short s = get();return s;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public short increment()
/*     */   {
/* 107 */     synchronized (this.lock_) {
/* 108 */       short s = this.value_ = (short)(this.value_ + 1);return s;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public short decrement()
/*     */   {
/* 117 */     synchronized (this.lock_) {
/* 118 */       short s = this.value_ = (short)(this.value_ - 1);return s;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public short add(short paramShort)
/*     */   {
/* 127 */     synchronized (this.lock_) {
/* 128 */       short s = this.value_ = (short)(this.value_ + paramShort);return s;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public short subtract(short paramShort)
/*     */   {
/* 137 */     synchronized (this.lock_) {
/* 138 */       short s = this.value_ = (short)(this.value_ - paramShort);return s;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized short multiply(short paramShort)
/*     */   {
/* 147 */     synchronized (this.lock_) {
/* 148 */       short s = this.value_ = (short)(this.value_ * paramShort);return s;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public short divide(short paramShort)
/*     */   {
/* 157 */     synchronized (this.lock_) {
/* 158 */       short s = this.value_ = (short)(this.value_ / paramShort);return s;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public short negate()
/*     */   {
/* 167 */     synchronized (this.lock_) {
/* 168 */       this.value_ = ((short)-this.value_);
/* 169 */       short s = this.value_;return s;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public short complement()
/*     */   {
/* 178 */     synchronized (this.lock_) {
/* 179 */       this.value_ = ((short)(this.value_ ^ 0xFFFFFFFF));
/* 180 */       short s = this.value_;return s;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public short and(short paramShort)
/*     */   {
/* 189 */     synchronized (this.lock_) {
/* 190 */       this.value_ = ((short)(this.value_ & paramShort));
/* 191 */       short s = this.value_;return s;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public short or(short paramShort)
/*     */   {
/* 200 */     synchronized (this.lock_) {
/* 201 */       this.value_ = ((short)(this.value_ | paramShort));
/* 202 */       short s = this.value_;return s;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public short xor(short paramShort)
/*     */   {
/* 212 */     synchronized (this.lock_) {
/* 213 */       this.value_ = ((short)(this.value_ ^ paramShort));
/* 214 */       short s = this.value_;return s;
/*     */     }
/*     */   }
/*     */   
/*     */   public int compareTo(short paramShort) {
/* 219 */     short s = get();
/* 220 */     return s == paramShort ? 0 : s < paramShort ? -1 : 1;
/*     */   }
/*     */   
/*     */   public int compareTo(SynchronizedShort paramSynchronizedShort) {
/* 224 */     return compareTo(paramSynchronizedShort.get());
/*     */   }
/*     */   
/*     */   public int compareTo(Object paramObject) {
/* 228 */     return compareTo((SynchronizedShort)paramObject);
/*     */   }
/*     */   
/*     */   public boolean equals(Object paramObject) {
/* 232 */     if ((paramObject != null) && ((paramObject instanceof SynchronizedShort)))
/*     */     {
/* 234 */       return get() == ((SynchronizedShort)paramObject).get();
/*     */     }
/* 236 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 240 */     return get();
/*     */   }
/*     */   
/* 243 */   public String toString() { return String.valueOf(get()); }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/SynchronizedShort.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */