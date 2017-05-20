/*     */ package cern.colt.bitvector;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.function.IntProcedure;
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
/*     */ public class BitVector
/*     */   extends PersistentObject
/*     */ {
/*     */   protected long[] bits;
/*     */   protected int nbits;
/*     */   
/*     */   private class IndexProcedure
/*     */     implements IntProcedure
/*     */   {
/*  70 */     IndexProcedure(BitVector.1 param1) { this(); }
/*  71 */     private int foundPos = -1;
/*     */     
/*  73 */     public boolean apply(int paramInt) { this.foundPos = paramInt;
/*  74 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private IndexProcedure() {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BitVector(long[] paramArrayOfLong, int paramInt)
/*     */   {
/*  93 */     elements(paramArrayOfLong, paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BitVector(int paramInt)
/*     */   {
/* 102 */     this(QuickBitVector.makeBitVector(paramInt, 1), paramInt);
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
/*     */   public void and(BitVector paramBitVector)
/*     */   {
/* 115 */     if (this == paramBitVector) return;
/* 116 */     checkSize(paramBitVector);
/* 117 */     long[] arrayOfLong1 = this.bits;
/* 118 */     long[] arrayOfLong2 = paramBitVector.bits;
/* 119 */     int i = arrayOfLong1.length; do { arrayOfLong1[i] &= arrayOfLong2[i];i--; } while (i >= 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void andNot(BitVector paramBitVector)
/*     */   {
/* 130 */     checkSize(paramBitVector);
/* 131 */     long[] arrayOfLong1 = this.bits;
/* 132 */     long[] arrayOfLong2 = paramBitVector.bits;
/* 133 */     int i = arrayOfLong1.length; do { arrayOfLong1[i] &= (arrayOfLong2[i] ^ 0xFFFFFFFFFFFFFFFF);i--; } while (i >= 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int cardinality()
/*     */   {
/* 140 */     int i = 0;
/* 141 */     int j = numberOfFullUnits();
/*     */     
/*     */ 
/*     */ 
/* 145 */     long[] arrayOfLong = this.bits;
/* 146 */     int k = j;
/* 147 */     do { long l = arrayOfLong[k];
/* 148 */       if (l == -1L) {
/* 149 */         i += 64;
/*     */       }
/* 151 */       else if (l != 0L) {
/* 152 */         int n = 64;
/* 153 */         do { if ((l & 1L << n) != 0L) i++;
/* 152 */           n--; } while (n >= 0);
/*     */       }
/* 146 */       k--; } while (k >= 0);
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
/* 159 */     int m = numberOfBitsInPartialUnit();
/* 160 */     do { if ((arrayOfLong[j] & 1L << m) != 0L) i++;
/* 159 */       m--; } while (m >= 0);
/*     */     
/*     */ 
/*     */ 
/* 163 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */   protected static void checkRangeFromTo(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 169 */     if ((paramInt1 < 0) || (paramInt1 > paramInt2) || (paramInt2 >= paramInt3)) {
/* 170 */       throw new IndexOutOfBoundsException("from: " + paramInt1 + ", to: " + paramInt2 + ", size=" + paramInt3);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void checkSize(BitVector paramBitVector)
/*     */   {
/* 176 */     if (this.nbits > paramBitVector.size()) { throw new IllegalArgumentException("Incompatible sizes: size=" + this.nbits + ", other.size()=" + paramBitVector.size());
/*     */     }
/*     */   }
/*     */   
/*     */   public void clear()
/*     */   {
/* 182 */     long[] arrayOfLong = this.bits;
/* 183 */     int i = arrayOfLong.length; do { arrayOfLong[i] = 0L;i--; } while (i >= 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear(int paramInt)
/*     */   {
/* 194 */     if ((paramInt < 0) || (paramInt >= this.nbits)) throw new IndexOutOfBoundsException(String.valueOf(paramInt));
/* 195 */     QuickBitVector.clear(this.bits, paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 207 */     BitVector localBitVector = (BitVector)super.clone();
/* 208 */     if (this.bits != null) localBitVector.bits = ((long[])this.bits.clone());
/* 209 */     return localBitVector;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public BitVector copy()
/*     */   {
/* 217 */     return (BitVector)clone();
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
/*     */   public long[] elements()
/*     */   {
/* 232 */     return this.bits;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void elements(long[] paramArrayOfLong, int paramInt)
/*     */   {
/* 251 */     if ((paramInt < 0) || (paramInt > paramArrayOfLong.length * 64)) throw new IllegalArgumentException();
/* 252 */     this.bits = paramArrayOfLong;
/* 253 */     this.nbits = paramInt;
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
/*     */ 
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 270 */     if ((paramObject == null) || (!(paramObject instanceof BitVector)))
/* 271 */       return false;
/* 272 */     if (this == paramObject) {
/* 273 */       return true;
/*     */     }
/* 275 */     BitVector localBitVector = (BitVector)paramObject;
/* 276 */     if (size() != localBitVector.size()) { return false;
/*     */     }
/* 278 */     int i = numberOfFullUnits();
/*     */     
/* 280 */     int j = i;
/* 281 */     do { if (this.bits[j] != localBitVector.bits[j]) return false;
/* 280 */       j--; } while (j >= 0);
/*     */     
/*     */ 
/*     */ 
/* 284 */     int k = i * 64;
/* 285 */     int m = numberOfBitsInPartialUnit();
/* 286 */     do { if (get(k) != localBitVector.get(k)) return false;
/* 287 */       k++;m--;
/* 285 */     } while (m >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 290 */     return true;
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
/*     */   public boolean forEachIndexFromToInState(int paramInt1, int paramInt2, boolean paramBoolean, IntProcedure paramIntProcedure)
/*     */   {
/* 335 */     if (this.nbits == 0) return true;
/* 336 */     checkRangeFromTo(paramInt1, paramInt2, this.nbits);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 342 */     long[] arrayOfLong = this.bits;
/*     */     
/*     */ 
/*     */ 
/* 346 */     int i = QuickBitVector.unit(paramInt1);
/* 347 */     int j = QuickBitVector.unit(paramInt2);
/* 348 */     int k = paramInt1;
/*     */     
/*     */ 
/* 351 */     int m = QuickBitVector.offset(paramInt1);
/*     */     int n;
/* 353 */     if (m > 0) {
/* 354 */       n = Math.min(paramInt2 - paramInt1 + 1, 64 - m);
/*     */       do
/*     */       {
/* 357 */         if ((QuickBitVector.get(arrayOfLong, k) == paramBoolean) && 
/* 358 */           (!paramIntProcedure.apply(k))) return false;
/* 356 */         k++;n--; } while (n >= 0);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 361 */       i++;
/*     */     }
/*     */     
/* 364 */     if (k > paramInt2) { return true;
/*     */     }
/*     */     
/* 367 */     m = QuickBitVector.offset(paramInt2);
/* 368 */     if (m < 63) {
/* 369 */       j--;
/* 370 */       n = m + 1;
/*     */     }
/*     */     else {
/* 373 */       n = 0;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     long l1;
/*     */     
/*     */ 
/* 381 */     if (paramBoolean) l1 = 0L; else {
/* 382 */       l1 = -1L;
/*     */     }
/*     */     
/* 385 */     int i1 = i;
/* 386 */     for (;;) { long l2 = arrayOfLong[i1];
/* 387 */       if (l2 != l1) {
/*     */         int i2;
/*     */         int i3;
/* 390 */         if (paramBoolean) {
/* 391 */           i2 = 0;i3 = 64;
/* 392 */           do { if (((l2 & 1L << i2++) != 0L) && 
/* 393 */               (!paramIntProcedure.apply(k))) return false;
/* 391 */             k++;i3--; } while (i3 >= 0);
/*     */ 
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/*     */ 
/* 398 */           i2 = 0;i3 = 64;
/* 399 */           do { if (((l2 & 1L << i2++) == 0L) && 
/* 400 */               (!paramIntProcedure.apply(k))) return false;
/* 398 */             k++;i3--; } while (i3 >= 0);
/*     */         }
/*     */         
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/* 406 */         k += 64;
/*     */       }
/* 385 */       i1++; if (i1 > j) {
/*     */         break;
/*     */       }
/*     */     }
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
/*     */     do
/*     */     {
/* 414 */       if ((QuickBitVector.get(arrayOfLong, k) == paramBoolean) && 
/* 415 */         (!paramIntProcedure.apply(k))) return false;
/* 413 */       k++;n--; } while (n >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 419 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean get(int paramInt)
/*     */   {
/* 431 */     if ((paramInt < 0) || (paramInt >= this.nbits)) throw new IndexOutOfBoundsException(String.valueOf(paramInt));
/* 432 */     return QuickBitVector.get(this.bits, paramInt);
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
/*     */   public long getLongFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 445 */     int i = paramInt2 - paramInt1 + 1;
/* 446 */     if (i == 0) return 0L;
/* 447 */     if ((paramInt1 < 0) || (paramInt1 >= this.nbits) || (paramInt2 < 0) || (paramInt2 >= this.nbits) || (i < 0) || (i > 64)) throw new IndexOutOfBoundsException("from:" + paramInt1 + ", to:" + paramInt2);
/* 448 */     return QuickBitVector.getLongFromTo(this.bits, paramInt1, paramInt2);
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
/*     */   public boolean getQuick(int paramInt)
/*     */   {
/* 463 */     return QuickBitVector.get(this.bits, paramInt);
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
/*     */   public int hashCode()
/*     */   {
/* 491 */     long l = 1234L;
/* 492 */     int i = this.bits.length; do { l ^= this.bits[i] * (i + 1);i--; } while (i >= 0);
/*     */     
/* 494 */     return (int)(l >> 32 ^ l);
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
/*     */ 
/*     */   public int indexOfFromTo(int paramInt1, int paramInt2, boolean paramBoolean)
/*     */   {
/* 510 */     IndexProcedure localIndexProcedure = new IndexProcedure(null);
/* 511 */     forEachIndexFromToInState(paramInt1, paramInt2, paramBoolean, localIndexProcedure);
/* 512 */     return localIndexProcedure.foundPos;
/*     */   }
/*     */   
/*     */ 
/*     */   public void not()
/*     */   {
/* 518 */     long[] arrayOfLong = this.bits;
/* 519 */     int i = arrayOfLong.length; do { arrayOfLong[i] ^= 0xFFFFFFFFFFFFFFFF;i--; } while (i >= 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected int numberOfBitsInPartialUnit()
/*     */   {
/* 526 */     return QuickBitVector.offset(this.nbits);
/*     */   }
/*     */   
/*     */ 
/*     */   protected int numberOfFullUnits()
/*     */   {
/* 532 */     return QuickBitVector.unit(this.nbits);
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
/*     */   public void or(BitVector paramBitVector)
/*     */   {
/* 545 */     if (this == paramBitVector) return;
/* 546 */     checkSize(paramBitVector);
/* 547 */     long[] arrayOfLong1 = this.bits;
/* 548 */     long[] arrayOfLong2 = paramBitVector.bits;
/* 549 */     int i = arrayOfLong1.length; do { arrayOfLong1[i] |= arrayOfLong2[i];i--; } while (i >= 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BitVector partFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 560 */     if ((this.nbits == 0) || (paramInt2 == paramInt1 - 1)) return new BitVector(0);
/* 561 */     checkRangeFromTo(paramInt1, paramInt2, this.nbits);
/*     */     
/* 563 */     int i = paramInt2 - paramInt1 + 1;
/* 564 */     BitVector localBitVector = new BitVector(i);
/* 565 */     localBitVector.replaceFromToWith(0, i - 1, this, paramInt1);
/* 566 */     return localBitVector;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void put(int paramInt, boolean paramBoolean)
/*     */   {
/* 576 */     if ((paramInt < 0) || (paramInt >= this.nbits)) throw new IndexOutOfBoundsException(String.valueOf(paramInt));
/* 577 */     if (paramBoolean) {
/* 578 */       QuickBitVector.set(this.bits, paramInt);
/*     */     } else {
/* 580 */       QuickBitVector.clear(this.bits, paramInt);
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
/*     */   public void putLongFromTo(long paramLong, int paramInt1, int paramInt2)
/*     */   {
/* 593 */     int i = paramInt2 - paramInt1 + 1;
/* 594 */     if (i == 0) return;
/* 595 */     if ((paramInt1 < 0) || (paramInt1 >= this.nbits) || (paramInt2 < 0) || (paramInt2 >= this.nbits) || (i < 0) || (i > 64)) throw new IndexOutOfBoundsException("from:" + paramInt1 + ", to:" + paramInt2);
/* 596 */     QuickBitVector.putLongFromTo(this.bits, paramLong, paramInt1, paramInt2);
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
/*     */   public void putQuick(int paramInt, boolean paramBoolean)
/*     */   {
/* 609 */     if (paramBoolean) {
/* 610 */       QuickBitVector.set(this.bits, paramInt);
/*     */     } else {
/* 612 */       QuickBitVector.clear(this.bits, paramInt);
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
/*     */   public void replaceFromToWith(int paramInt1, int paramInt2, BitVector paramBitVector, int paramInt3)
/*     */   {
/* 628 */     if ((this.nbits == 0) || (paramInt2 == paramInt1 - 1)) return;
/* 629 */     checkRangeFromTo(paramInt1, paramInt2, this.nbits);
/* 630 */     int i = paramInt2 - paramInt1 + 1;
/* 631 */     if ((paramInt3 < 0) || (paramInt3 + i > paramBitVector.size())) {
/* 632 */       throw new IndexOutOfBoundsException();
/*     */     }
/*     */     
/* 635 */     if ((paramBitVector.bits == this.bits) && (paramInt1 <= paramInt3) && (paramInt3 <= paramInt2)) {
/* 636 */       paramBitVector = paramBitVector.copy();
/*     */     }
/*     */     
/* 639 */     long[] arrayOfLong1 = this.bits;
/* 640 */     long[] arrayOfLong2 = paramBitVector.bits;
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
/* 651 */     int j = paramInt2 - paramInt1 + 1;
/* 652 */     int k = QuickBitVector.unit(j);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 657 */     int m = k;
/* 658 */     do { long l1 = QuickBitVector.getLongFromTo(arrayOfLong2, paramInt3, paramInt3 + 63);
/* 659 */       QuickBitVector.putLongFromTo(arrayOfLong1, l1, paramInt1, paramInt1 + 63);
/* 660 */       paramInt3 += 64;
/* 661 */       paramInt1 += 64;m--;
/* 657 */     } while (m >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 665 */     int n = QuickBitVector.offset(j);
/* 666 */     long l2 = QuickBitVector.getLongFromTo(arrayOfLong2, paramInt3, paramInt3 + n - 1);
/* 667 */     QuickBitVector.putLongFromTo(arrayOfLong1, l2, paramInt1, paramInt1 + n - 1);
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
/*     */   public void replaceFromToWith(int paramInt1, int paramInt2, boolean paramBoolean)
/*     */   {
/* 680 */     if ((this.nbits == 0) || (paramInt2 == paramInt1 - 1)) return;
/* 681 */     checkRangeFromTo(paramInt1, paramInt2, this.nbits);
/* 682 */     long[] arrayOfLong = this.bits;
/*     */     
/* 684 */     int i = QuickBitVector.unit(paramInt1);
/* 685 */     int j = QuickBitVector.offset(paramInt1);
/* 686 */     int k = QuickBitVector.unit(paramInt2);
/* 687 */     int m = QuickBitVector.offset(paramInt2);
/* 688 */     int n = 64;
/*     */     
/*     */     long l;
/* 691 */     if (paramBoolean) l = -1L; else {
/* 692 */       l = 0L;
/*     */     }
/* 694 */     int i1 = paramInt1;
/* 695 */     if (i == k) {
/* 696 */       QuickBitVector.putLongFromTo(arrayOfLong, l, i1, i1 + paramInt2 - paramInt1);
/*     */       
/* 698 */       return;
/*     */     }
/*     */     
/*     */ 
/* 702 */     if (j > 0) {
/* 703 */       QuickBitVector.putLongFromTo(arrayOfLong, l, i1, i1 + n - j);
/* 704 */       i1 += n - j + 1;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 709 */       i++;
/*     */     }
/* 711 */     if (m < n - 1) { k--;
/*     */     }
/*     */     
/* 714 */     for (int i2 = i; i2 <= k; arrayOfLong[(i2++)] = l) {}
/* 715 */     if (i <= k) { i1 += (k - i + 1) * n;
/*     */     }
/*     */     
/* 718 */     if (m < n - 1) {
/* 719 */       QuickBitVector.putLongFromTo(arrayOfLong, l, i1, paramInt2);
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
/*     */   public void set(int paramInt)
/*     */   {
/* 733 */     if ((paramInt < 0) || (paramInt >= this.nbits)) throw new IndexOutOfBoundsException(String.valueOf(paramInt));
/* 734 */     QuickBitVector.set(this.bits, paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSize(int paramInt)
/*     */   {
/* 746 */     if (paramInt != size()) {
/* 747 */       BitVector localBitVector = new BitVector(paramInt);
/* 748 */       localBitVector.replaceFromToWith(0, Math.min(size(), paramInt) - 1, this, 0);
/* 749 */       elements(localBitVector.elements(), paramInt);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public int size()
/*     */   {
/* 756 */     return this.nbits;
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
/*     */   public String toString()
/*     */   {
/* 769 */     StringBuffer localStringBuffer = new StringBuffer(this.nbits);
/* 770 */     String str = "";
/* 771 */     localStringBuffer.append('{');
/*     */     
/* 773 */     for (int i = 0; i < this.nbits; i++) {
/* 774 */       if (get(i)) {
/* 775 */         localStringBuffer.append(str);
/* 776 */         str = ", ";
/* 777 */         localStringBuffer.append(i);
/*     */       }
/*     */     }
/*     */     
/* 781 */     localStringBuffer.append('}');
/* 782 */     return localStringBuffer.toString();
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
/*     */ 
/*     */ 
/*     */   public void xor(BitVector paramBitVector)
/*     */   {
/* 799 */     checkSize(paramBitVector);
/* 800 */     long[] arrayOfLong1 = this.bits;
/* 801 */     long[] arrayOfLong2 = paramBitVector.bits;
/* 802 */     int i = arrayOfLong1.length; do { arrayOfLong1[i] ^= arrayOfLong2[i];i--; } while (i >= 0);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/bitvector/BitVector.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */