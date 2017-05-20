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
/*     */ public class SynchronizedByte
/*     */   extends SynchronizedVariable
/*     */   implements Comparable, Cloneable
/*     */ {
/*     */   protected byte value_;
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
/*     */   public SynchronizedByte(byte paramByte)
/*     */   {
/*  32 */     this.value_ = paramByte;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SynchronizedByte(byte paramByte, Object paramObject)
/*     */   {
/*  40 */     super(paramObject);
/*  41 */     this.value_ = paramByte;
/*     */   }
/*     */   
/*     */ 
/*     */   public final byte get()
/*     */   {
/*  47 */     synchronized (this.lock_) { byte b = this.value_;return b;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public byte set(byte paramByte)
/*     */   {
/*  55 */     synchronized (this.lock_) {
/*  56 */       byte b1 = this.value_;
/*  57 */       this.value_ = paramByte;
/*  58 */       byte b2 = b1;return b2;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean commit(byte paramByte1, byte paramByte2)
/*     */   {
/*  67 */     synchronized (this.lock_) {
/*  68 */       boolean bool1 = paramByte1 == this.value_;
/*  69 */       if (bool1) this.value_ = paramByte2;
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
/*     */   public byte swap(SynchronizedByte paramSynchronizedByte)
/*     */   {
/*  86 */     if (paramSynchronizedByte == this) return get();
/*  87 */     SynchronizedByte localSynchronizedByte1 = this;
/*  88 */     SynchronizedByte localSynchronizedByte2 = paramSynchronizedByte;
/*  89 */     if (System.identityHashCode(localSynchronizedByte1) > System.identityHashCode(localSynchronizedByte2)) {
/*  90 */       localSynchronizedByte1 = paramSynchronizedByte;
/*  91 */       localSynchronizedByte2 = this;
/*     */     }
/*  93 */     synchronized (localSynchronizedByte1.lock_) {
/*  94 */       synchronized (localSynchronizedByte2.lock_) {
/*  95 */         localSynchronizedByte1.set(localSynchronizedByte2.set(localSynchronizedByte1.get()));
/*  96 */         byte b = get();return b;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte increment()
/*     */   {
/* 106 */     synchronized (this.lock_) {
/* 107 */       byte b = this.value_ = (byte)(this.value_ + 1);return b;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte decrement()
/*     */   {
/* 116 */     synchronized (this.lock_) {
/* 117 */       byte b = this.value_ = (byte)(this.value_ - 1);return b;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte add(byte paramByte)
/*     */   {
/* 126 */     synchronized (this.lock_) {
/* 127 */       byte b = this.value_ = (byte)(this.value_ + paramByte);return b;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte subtract(byte paramByte)
/*     */   {
/* 136 */     synchronized (this.lock_) {
/* 137 */       byte b = this.value_ = (byte)(this.value_ - paramByte);return b;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized byte multiply(byte paramByte)
/*     */   {
/* 146 */     synchronized (this.lock_) {
/* 147 */       byte b = this.value_ = (byte)(this.value_ * paramByte);return b;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte divide(byte paramByte)
/*     */   {
/* 156 */     synchronized (this.lock_) {
/* 157 */       byte b = this.value_ = (byte)(this.value_ / paramByte);return b;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte negate()
/*     */   {
/* 166 */     synchronized (this.lock_) {
/* 167 */       this.value_ = ((byte)-this.value_);
/* 168 */       byte b = this.value_;return b;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte complement()
/*     */   {
/* 177 */     synchronized (this.lock_) {
/* 178 */       this.value_ = ((byte)(this.value_ ^ 0xFFFFFFFF));
/* 179 */       byte b = this.value_;return b;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte and(byte paramByte)
/*     */   {
/* 188 */     synchronized (this.lock_) {
/* 189 */       this.value_ = ((byte)(this.value_ & paramByte));
/* 190 */       byte b = this.value_;return b;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte or(byte paramByte)
/*     */   {
/* 199 */     synchronized (this.lock_) {
/* 200 */       this.value_ = ((byte)(this.value_ | paramByte));
/* 201 */       byte b = this.value_;return b;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte xor(byte paramByte)
/*     */   {
/* 211 */     synchronized (this.lock_) {
/* 212 */       this.value_ = ((byte)(this.value_ ^ paramByte));
/* 213 */       byte b = this.value_;return b;
/*     */     }
/*     */   }
/*     */   
/*     */   public int compareTo(byte paramByte) {
/* 218 */     byte b = get();
/* 219 */     return b == paramByte ? 0 : b < paramByte ? -1 : 1;
/*     */   }
/*     */   
/*     */   public int compareTo(SynchronizedByte paramSynchronizedByte) {
/* 223 */     return compareTo(paramSynchronizedByte.get());
/*     */   }
/*     */   
/*     */   public int compareTo(Object paramObject) {
/* 227 */     return compareTo((SynchronizedByte)paramObject);
/*     */   }
/*     */   
/*     */   public boolean equals(Object paramObject) {
/* 231 */     if ((paramObject != null) && ((paramObject instanceof SynchronizedByte)))
/*     */     {
/* 233 */       return get() == ((SynchronizedByte)paramObject).get();
/*     */     }
/* 235 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 239 */     return get();
/*     */   }
/*     */   
/* 242 */   public String toString() { return Byte.toString(get()); }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/SynchronizedByte.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */