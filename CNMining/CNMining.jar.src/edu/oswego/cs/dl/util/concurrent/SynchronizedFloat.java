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
/*     */ public class SynchronizedFloat
/*     */   extends SynchronizedVariable
/*     */   implements Comparable, Cloneable
/*     */ {
/*     */   protected float value_;
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
/*     */   public SynchronizedFloat(float paramFloat)
/*     */   {
/*  32 */     this.value_ = paramFloat;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SynchronizedFloat(float paramFloat, Object paramObject)
/*     */   {
/*  40 */     super(paramObject);
/*  41 */     this.value_ = paramFloat;
/*     */   }
/*     */   
/*     */ 
/*     */   public final float get()
/*     */   {
/*  47 */     synchronized (this.lock_) { float f = this.value_;return f;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public float set(float paramFloat)
/*     */   {
/*  55 */     synchronized (this.lock_) {
/*  56 */       float f1 = this.value_;
/*  57 */       this.value_ = paramFloat;
/*  58 */       float f2 = f1;return f2;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean commit(float paramFloat1, float paramFloat2)
/*     */   {
/*  67 */     synchronized (this.lock_) {
/*  68 */       boolean bool1 = paramFloat1 == this.value_;
/*  69 */       if (bool1) this.value_ = paramFloat2;
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
/*     */   public float swap(SynchronizedFloat paramSynchronizedFloat)
/*     */   {
/*  86 */     if (paramSynchronizedFloat == this) return get();
/*  87 */     SynchronizedFloat localSynchronizedFloat1 = this;
/*  88 */     SynchronizedFloat localSynchronizedFloat2 = paramSynchronizedFloat;
/*  89 */     if (System.identityHashCode(localSynchronizedFloat1) > System.identityHashCode(localSynchronizedFloat2)) {
/*  90 */       localSynchronizedFloat1 = paramSynchronizedFloat;
/*  91 */       localSynchronizedFloat2 = this;
/*     */     }
/*  93 */     synchronized (localSynchronizedFloat1.lock_) {
/*  94 */       synchronized (localSynchronizedFloat2.lock_) {
/*  95 */         localSynchronizedFloat1.set(localSynchronizedFloat2.set(localSynchronizedFloat1.get()));
/*  96 */         float f = get();return f;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float add(float paramFloat)
/*     */   {
/* 107 */     synchronized (this.lock_) {
/* 108 */       float f = this.value_ += paramFloat;return f;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public float subtract(float paramFloat)
/*     */   {
/* 117 */     synchronized (this.lock_) {
/* 118 */       float f = this.value_ -= paramFloat;return f;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized float multiply(float paramFloat)
/*     */   {
/* 127 */     synchronized (this.lock_) {
/* 128 */       float f = this.value_ *= paramFloat;return f;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public float divide(float paramFloat)
/*     */   {
/* 137 */     synchronized (this.lock_) {
/* 138 */       float f = this.value_ /= paramFloat;return f;
/*     */     }
/*     */   }
/*     */   
/*     */   public int compareTo(float paramFloat) {
/* 143 */     float f = get();
/* 144 */     return f == paramFloat ? 0 : f < paramFloat ? -1 : 1;
/*     */   }
/*     */   
/*     */   public int compareTo(SynchronizedFloat paramSynchronizedFloat) {
/* 148 */     return compareTo(paramSynchronizedFloat.get());
/*     */   }
/*     */   
/*     */   public int compareTo(Object paramObject) {
/* 152 */     return compareTo((SynchronizedFloat)paramObject);
/*     */   }
/*     */   
/*     */   public boolean equals(Object paramObject) {
/* 156 */     if ((paramObject != null) && ((paramObject instanceof SynchronizedFloat)))
/*     */     {
/* 158 */       return get() == ((SynchronizedFloat)paramObject).get();
/*     */     }
/* 160 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 164 */     return Float.floatToIntBits(get());
/*     */   }
/*     */   
/* 167 */   public String toString() { return String.valueOf(get()); }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/SynchronizedFloat.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */