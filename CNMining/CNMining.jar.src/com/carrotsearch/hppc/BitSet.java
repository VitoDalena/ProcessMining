/*     */ package com.carrotsearch.hppc;
/*     */ 
/*     */ import com.carrotsearch.hppc.cursors.IntCursor;
/*     */ import com.carrotsearch.hppc.cursors.LongCursor;
/*     */ import com.carrotsearch.hppc.predicates.IntPredicate;
/*     */ import com.carrotsearch.hppc.predicates.LongPredicate;
/*     */ import com.carrotsearch.hppc.procedures.IntProcedure;
/*     */ import com.carrotsearch.hppc.procedures.LongProcedure;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
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
/*     */ 
/*     */ public class BitSet
/*     */   implements Cloneable
/*     */ {
/*     */   private static final long DEFAULT_NUM_BITS = 64L;
/*     */   public long[] bits;
/*     */   public int wlen;
/*     */   
/*     */   public BitSet()
/*     */   {
/*  78 */     this(64L);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public BitSet(long numBits)
/*     */   {
/*  86 */     this.bits = new long[bits2words(numBits)];
/*  87 */     this.wlen = this.bits.length;
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
/*     */   public BitSet(long[] bits, int numWords)
/*     */   {
/* 102 */     this.bits = bits;
/* 103 */     this.wlen = numWords;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static BitSet newInstance()
/*     */   {
/* 111 */     return new BitSet();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BitSetIterator iterator()
/*     */   {
/* 120 */     return new BitSetIterator(this.bits, this.wlen);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long capacity()
/*     */   {
/* 128 */     return this.bits.length << 6;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long size()
/*     */   {
/* 140 */     return capacity();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long length()
/*     */   {
/* 148 */     trimTrailingZeros();
/* 149 */     if (this.wlen == 0) return 0L;
/* 150 */     return (this.wlen - 1L << 6) + (64 - Long.numberOfLeadingZeros(this.bits[(this.wlen - 1)]));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 159 */     return cardinality() == 0L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean get(int index)
/*     */   {
/* 167 */     int i = index >> 6;
/*     */     
/*     */ 
/* 170 */     if (i >= this.bits.length) { return false;
/*     */     }
/* 172 */     int bit = index & 0x3F;
/* 173 */     long bitmask = 1L << bit;
/* 174 */     return (this.bits[i] & bitmask) != 0L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean get(long index)
/*     */   {
/* 182 */     int i = (int)(index >> 6);
/* 183 */     if (i >= this.bits.length) return false;
/* 184 */     int bit = (int)index & 0x3F;
/* 185 */     long bitmask = 1L << bit;
/* 186 */     return (this.bits[i] & bitmask) != 0L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void set(long index)
/*     */   {
/* 194 */     int wordNum = expandingWordNum(index);
/* 195 */     int bit = (int)index & 0x3F;
/* 196 */     long bitmask = 1L << bit;
/* 197 */     this.bits[wordNum] |= bitmask;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void set(long startIndex, long endIndex)
/*     */   {
/* 208 */     if (endIndex <= startIndex) { return;
/*     */     }
/* 210 */     int startWord = (int)(startIndex >> 6);
/*     */     
/*     */ 
/*     */ 
/* 214 */     int endWord = expandingWordNum(endIndex - 1L);
/*     */     
/* 216 */     long startmask = -1L << (int)startIndex;
/* 217 */     long endmask = -1L >>> (int)-endIndex;
/*     */     
/*     */ 
/* 220 */     if (startWord == endWord)
/*     */     {
/* 222 */       this.bits[startWord] |= startmask & endmask;
/* 223 */       return;
/*     */     }
/*     */     
/* 226 */     this.bits[startWord] |= startmask;
/* 227 */     Arrays.fill(this.bits, startWord + 1, endWord, -1L);
/* 228 */     this.bits[endWord] |= endmask;
/*     */   }
/*     */   
/*     */   protected int expandingWordNum(long index)
/*     */   {
/* 233 */     int wordNum = (int)(index >> 6);
/* 234 */     if (wordNum >= this.wlen)
/*     */     {
/* 236 */       ensureCapacity(index + 1L);
/* 237 */       this.wlen = (wordNum + 1);
/*     */     }
/* 239 */     return wordNum;
/*     */   }
/*     */   
/*     */ 
/*     */   public void clear()
/*     */   {
/* 245 */     Arrays.fill(this.bits, 0L);
/* 246 */     this.wlen = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear(long index)
/*     */   {
/* 255 */     int wordNum = (int)(index >> 6);
/* 256 */     if (wordNum >= this.wlen) return;
/* 257 */     int bit = (int)index & 0x3F;
/* 258 */     long bitmask = 1L << bit;
/* 259 */     this.bits[wordNum] &= (bitmask ^ 0xFFFFFFFFFFFFFFFF);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear(int startIndex, int endIndex)
/*     */   {
/* 270 */     if (endIndex <= startIndex) { return;
/*     */     }
/* 272 */     int startWord = startIndex >> 6;
/* 273 */     if (startWord >= this.wlen) { return;
/*     */     }
/*     */     
/*     */ 
/* 277 */     int endWord = endIndex - 1 >> 6;
/*     */     
/* 279 */     long startmask = -1L << startIndex;
/* 280 */     long endmask = -1L >>> -endIndex;
/*     */     
/*     */ 
/*     */ 
/* 284 */     startmask ^= 0xFFFFFFFFFFFFFFFF;
/* 285 */     endmask ^= 0xFFFFFFFFFFFFFFFF;
/*     */     
/* 287 */     if (startWord == endWord)
/*     */     {
/* 289 */       this.bits[startWord] &= (startmask | endmask);
/* 290 */       return;
/*     */     }
/*     */     
/* 293 */     this.bits[startWord] &= startmask;
/*     */     
/* 295 */     int middle = Math.min(this.wlen, endWord);
/* 296 */     Arrays.fill(this.bits, startWord + 1, middle, 0L);
/* 297 */     if (endWord < this.wlen)
/*     */     {
/* 299 */       this.bits[endWord] &= endmask;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear(long startIndex, long endIndex)
/*     */   {
/* 311 */     if (endIndex <= startIndex) { return;
/*     */     }
/* 313 */     int startWord = (int)(startIndex >> 6);
/* 314 */     if (startWord >= this.wlen) { return;
/*     */     }
/*     */     
/*     */ 
/* 318 */     int endWord = (int)(endIndex - 1L >> 6);
/*     */     
/* 320 */     long startmask = -1L << (int)startIndex;
/* 321 */     long endmask = -1L >>> (int)-endIndex;
/*     */     
/*     */ 
/*     */ 
/* 325 */     startmask ^= 0xFFFFFFFFFFFFFFFF;
/* 326 */     endmask ^= 0xFFFFFFFFFFFFFFFF;
/*     */     
/* 328 */     if (startWord == endWord)
/*     */     {
/* 330 */       this.bits[startWord] &= (startmask | endmask);
/* 331 */       return;
/*     */     }
/*     */     
/* 334 */     this.bits[startWord] &= startmask;
/*     */     
/* 336 */     int middle = Math.min(this.wlen, endWord);
/* 337 */     Arrays.fill(this.bits, startWord + 1, middle, 0L);
/* 338 */     if (endWord < this.wlen)
/*     */     {
/* 340 */       this.bits[endWord] &= endmask;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getAndSet(int index)
/*     */   {
/* 350 */     int wordNum = index >> 6;
/* 351 */     int bit = index & 0x3F;
/* 352 */     long bitmask = 1L << bit;
/* 353 */     boolean val = (this.bits[wordNum] & bitmask) != 0L;
/* 354 */     this.bits[wordNum] |= bitmask;
/* 355 */     return val;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getAndSet(long index)
/*     */   {
/* 364 */     int wordNum = (int)(index >> 6);
/* 365 */     int bit = (int)index & 0x3F;
/* 366 */     long bitmask = 1L << bit;
/* 367 */     boolean val = (this.bits[wordNum] & bitmask) != 0L;
/* 368 */     this.bits[wordNum] |= bitmask;
/* 369 */     return val;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void flip(long index)
/*     */   {
/* 377 */     int wordNum = expandingWordNum(index);
/* 378 */     int bit = (int)index & 0x3F;
/* 379 */     long bitmask = 1L << bit;
/* 380 */     this.bits[wordNum] ^= bitmask;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean flipAndGet(int index)
/*     */   {
/* 389 */     int wordNum = index >> 6;
/* 390 */     int bit = index & 0x3F;
/* 391 */     long bitmask = 1L << bit;
/* 392 */     this.bits[wordNum] ^= bitmask;
/* 393 */     return (this.bits[wordNum] & bitmask) != 0L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean flipAndGet(long index)
/*     */   {
/* 402 */     int wordNum = (int)(index >> 6);
/* 403 */     int bit = (int)index & 0x3F;
/* 404 */     long bitmask = 1L << bit;
/* 405 */     this.bits[wordNum] ^= bitmask;
/* 406 */     return (this.bits[wordNum] & bitmask) != 0L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void flip(long startIndex, long endIndex)
/*     */   {
/* 417 */     if (endIndex <= startIndex) return;
/* 418 */     int startWord = (int)(startIndex >> 6);
/*     */     
/*     */ 
/*     */ 
/* 422 */     int endWord = expandingWordNum(endIndex - 1L);
/*     */     
/* 424 */     long startmask = -1L << (int)startIndex;
/* 425 */     long endmask = -1L >>> (int)-endIndex;
/*     */     
/*     */ 
/* 428 */     if (startWord == endWord)
/*     */     {
/* 430 */       this.bits[startWord] ^= startmask & endmask;
/* 431 */       return;
/*     */     }
/*     */     
/* 434 */     this.bits[startWord] ^= startmask;
/*     */     
/* 436 */     for (int i = startWord + 1; i < endWord; i++)
/*     */     {
/* 438 */       this.bits[i] ^= 0xFFFFFFFFFFFFFFFF;
/*     */     }
/*     */     
/* 441 */     this.bits[endWord] ^= endmask;
/*     */   }
/*     */   
/*     */ 
/*     */   public long cardinality()
/*     */   {
/* 447 */     return BitUtil.pop_array(this.bits, 0, this.wlen);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static long intersectionCount(BitSet a, BitSet b)
/*     */   {
/* 456 */     return BitUtil.pop_intersect(a.bits, b.bits, 0, Math.min(a.wlen, b.wlen));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static long unionCount(BitSet a, BitSet b)
/*     */   {
/* 465 */     long tot = BitUtil.pop_union(a.bits, b.bits, 0, Math.min(a.wlen, b.wlen));
/* 466 */     if (a.wlen < b.wlen)
/*     */     {
/* 468 */       tot += BitUtil.pop_array(b.bits, a.wlen, b.wlen - a.wlen);
/*     */     }
/* 470 */     else if (a.wlen > b.wlen)
/*     */     {
/* 472 */       tot += BitUtil.pop_array(a.bits, b.wlen, a.wlen - b.wlen);
/*     */     }
/* 474 */     return tot;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static long andNotCount(BitSet a, BitSet b)
/*     */   {
/* 483 */     long tot = BitUtil.pop_andnot(a.bits, b.bits, 0, Math.min(a.wlen, b.wlen));
/* 484 */     if (a.wlen > b.wlen)
/*     */     {
/* 486 */       tot += BitUtil.pop_array(a.bits, b.wlen, a.wlen - b.wlen);
/*     */     }
/* 488 */     return tot;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static long xorCount(BitSet a, BitSet b)
/*     */   {
/* 497 */     long tot = BitUtil.pop_xor(a.bits, b.bits, 0, Math.min(a.wlen, b.wlen));
/* 498 */     if (a.wlen < b.wlen)
/*     */     {
/* 500 */       tot += BitUtil.pop_array(b.bits, a.wlen, b.wlen - a.wlen);
/*     */     }
/* 502 */     else if (a.wlen > b.wlen)
/*     */     {
/* 504 */       tot += BitUtil.pop_array(a.bits, b.wlen, a.wlen - b.wlen);
/*     */     }
/* 506 */     return tot;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int nextSetBit(int index)
/*     */   {
/* 515 */     int i = index >> 6;
/* 516 */     if (i >= this.wlen) return -1;
/* 517 */     int subIndex = index & 0x3F;
/* 518 */     long word = this.bits[i] >> subIndex;
/*     */     
/* 520 */     if (word != 0L)
/*     */     {
/* 522 */       return (i << 6) + subIndex + Long.numberOfTrailingZeros(word);
/*     */     }
/*     */     do {
/* 525 */       i++; if (i >= this.wlen)
/*     */         break;
/* 527 */       word = this.bits[i];
/* 528 */     } while (word == 0L); return (i << 6) + Long.numberOfTrailingZeros(word);
/*     */     
/*     */ 
/* 531 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long nextSetBit(long index)
/*     */   {
/* 540 */     int i = (int)(index >>> 6);
/* 541 */     if (i >= this.wlen) return -1L;
/* 542 */     int subIndex = (int)index & 0x3F;
/* 543 */     long word = this.bits[i] >>> subIndex;
/*     */     
/* 545 */     if (word != 0L)
/*     */     {
/* 547 */       return (i << 6) + (subIndex + Long.numberOfTrailingZeros(word));
/*     */     }
/*     */     do {
/* 550 */       i++; if (i >= this.wlen)
/*     */         break;
/* 552 */       word = this.bits[i];
/* 553 */     } while (word == 0L); return (i << 6) + Long.numberOfTrailingZeros(word);
/*     */     
/*     */ 
/* 556 */     return -1L;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object clone()
/*     */   {
/*     */     try
/*     */     {
/* 564 */       BitSet obs = (BitSet)super.clone();
/* 565 */       obs.bits = ((long[])obs.bits.clone());
/*     */       
/* 567 */       return obs;
/*     */     }
/*     */     catch (CloneNotSupportedException e)
/*     */     {
/* 571 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void intersect(BitSet other)
/*     */   {
/* 578 */     int newLen = Math.min(this.wlen, other.wlen);
/* 579 */     long[] thisArr = this.bits;
/* 580 */     long[] otherArr = other.bits;
/*     */     
/* 582 */     int pos = newLen;
/* 583 */     for (;;) { pos--; if (pos < 0)
/*     */         break;
/* 585 */       thisArr[pos] &= otherArr[pos];
/*     */     }
/* 587 */     if (this.wlen > newLen)
/*     */     {
/*     */ 
/* 590 */       Arrays.fill(this.bits, newLen, this.wlen, 0L);
/*     */     }
/* 592 */     this.wlen = newLen;
/*     */   }
/*     */   
/*     */ 
/*     */   public void union(BitSet other)
/*     */   {
/* 598 */     int newLen = Math.max(this.wlen, other.wlen);
/* 599 */     ensureCapacityWords(newLen);
/*     */     
/* 601 */     long[] thisArr = this.bits;
/* 602 */     long[] otherArr = other.bits;
/* 603 */     int pos = Math.min(this.wlen, other.wlen);
/* 604 */     for (;;) { pos--; if (pos < 0)
/*     */         break;
/* 606 */       thisArr[pos] |= otherArr[pos];
/*     */     }
/* 608 */     if (this.wlen < newLen)
/*     */     {
/* 610 */       System.arraycopy(otherArr, this.wlen, thisArr, this.wlen, newLen - this.wlen);
/*     */     }
/* 612 */     this.wlen = newLen;
/*     */   }
/*     */   
/*     */ 
/*     */   public void remove(BitSet other)
/*     */   {
/* 618 */     int idx = Math.min(this.wlen, other.wlen);
/* 619 */     long[] thisArr = this.bits;
/* 620 */     long[] otherArr = other.bits;
/* 621 */     for (;;) { idx--; if (idx < 0)
/*     */         break;
/* 623 */       thisArr[idx] &= (otherArr[idx] ^ 0xFFFFFFFFFFFFFFFF);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void xor(BitSet other)
/*     */   {
/* 630 */     int newLen = Math.max(this.wlen, other.wlen);
/* 631 */     ensureCapacityWords(newLen);
/*     */     
/* 633 */     long[] thisArr = this.bits;
/* 634 */     long[] otherArr = other.bits;
/* 635 */     int pos = Math.min(this.wlen, other.wlen);
/* 636 */     for (;;) { pos--; if (pos < 0)
/*     */         break;
/* 638 */       thisArr[pos] ^= otherArr[pos];
/*     */     }
/* 640 */     if (this.wlen < newLen)
/*     */     {
/* 642 */       System.arraycopy(otherArr, this.wlen, thisArr, this.wlen, newLen - this.wlen);
/*     */     }
/* 644 */     this.wlen = newLen;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void and(BitSet other)
/*     */   {
/* 652 */     intersect(other);
/*     */   }
/*     */   
/*     */ 
/*     */   public void or(BitSet other)
/*     */   {
/* 658 */     union(other);
/*     */   }
/*     */   
/*     */ 
/*     */   public void andNot(BitSet other)
/*     */   {
/* 664 */     remove(other);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean intersects(BitSet other)
/*     */   {
/* 670 */     int pos = Math.min(this.wlen, other.wlen);
/* 671 */     long[] thisArr = this.bits;
/* 672 */     long[] otherArr = other.bits;
/* 673 */     do { pos--; if (pos < 0)
/*     */         break;
/* 675 */     } while ((thisArr[pos] & otherArr[pos]) == 0L); return true;
/*     */     
/* 677 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void ensureCapacityWords(int numWords)
/*     */   {
/* 686 */     if (this.bits.length < numWords)
/*     */     {
/* 688 */       this.bits = grow(this.bits, numWords);
/*     */     }
/*     */   }
/*     */   
/*     */   public static long[] grow(long[] array, int minSize)
/*     */   {
/* 694 */     if (array.length < minSize)
/*     */     {
/* 696 */       long[] newArray = new long[getNextSize(minSize)];
/* 697 */       System.arraycopy(array, 0, newArray, 0, array.length);
/* 698 */       return newArray;
/*     */     }
/* 700 */     return array;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int getNextSize(int targetSize)
/*     */   {
/* 712 */     return (targetSize >> 3) + (targetSize < 9 ? 3 : 6) + targetSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void ensureCapacity(long numBits)
/*     */   {
/* 721 */     ensureCapacityWords(bits2words(numBits));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void trimTrailingZeros()
/*     */   {
/* 730 */     int idx = this.wlen - 1;
/* 731 */     while ((idx >= 0) && (this.bits[idx] == 0L))
/* 732 */       idx--;
/* 733 */     this.wlen = (idx + 1);
/*     */   }
/*     */   
/*     */ 
/*     */   public static int bits2words(long numBits)
/*     */   {
/* 739 */     return (int)((numBits - 1L >>> 6) + 1L);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 746 */     if (this == o) return true;
/* 747 */     if (!(o instanceof BitSet)) { return false;
/*     */     }
/*     */     
/* 750 */     BitSet b = (BitSet)o;
/*     */     
/*     */     BitSet a;
/* 753 */     if (b.wlen > this.wlen)
/*     */     {
/* 755 */       BitSet a = b;
/* 756 */       b = this;
/*     */     }
/*     */     else
/*     */     {
/* 760 */       a = this;
/*     */     }
/*     */     
/*     */ 
/* 764 */     for (int i = a.wlen - 1; i >= b.wlen; i--)
/*     */     {
/* 766 */       if (a.bits[i] != 0L) { return false;
/*     */       }
/*     */     }
/* 769 */     for (int i = b.wlen - 1; i >= 0; i--)
/*     */     {
/* 771 */       if (a.bits[i] != b.bits[i]) { return false;
/*     */       }
/*     */     }
/* 774 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 782 */     long h = 0L;
/* 783 */     int i = this.bits.length; for (;;) { i--; if (i < 0)
/*     */         break;
/* 785 */       h ^= this.bits[i];
/* 786 */       h = h << 1 | h >>> 63;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 791 */     return (int)(h >> 32 ^ h) + -1737092556;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 797 */     long bit = nextSetBit(0);
/* 798 */     if (bit < 0L)
/*     */     {
/* 800 */       return "{}";
/*     */     }
/*     */     
/* 803 */     StringBuilder builder = new StringBuilder();
/* 804 */     builder.append("{");
/*     */     
/* 806 */     builder.append(Long.toString(bit));
/* 807 */     while ((bit = nextSetBit(bit + 1L)) >= 0L)
/*     */     {
/* 809 */       builder.append(", ");
/* 810 */       builder.append(Long.toString(bit));
/*     */     }
/* 812 */     builder.append("}");
/*     */     
/* 814 */     return builder.toString();
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
/*     */   public IntLookupContainer asIntLookupContainer()
/*     */   {
/* 827 */     new IntLookupContainer()
/*     */     {
/*     */ 
/*     */       public int size()
/*     */       {
/* 832 */         return getCurrentCardinality();
/*     */       }
/*     */       
/*     */ 
/*     */       public boolean isEmpty()
/*     */       {
/* 838 */         return BitSet.this.isEmpty();
/*     */       }
/*     */       
/*     */ 
/*     */       public Iterator<IntCursor> iterator()
/*     */       {
/* 844 */         new Iterator() {
/* 845 */           private long nextBitSet = BitSet.this.nextSetBit(0);
/* 846 */           private final IntCursor cursor = new IntCursor();
/*     */           
/*     */ 
/*     */           public boolean hasNext()
/*     */           {
/* 851 */             return this.nextBitSet >= 0L;
/*     */           }
/*     */           
/*     */ 
/*     */           public IntCursor next()
/*     */           {
/* 857 */             long value = this.nextBitSet;
/* 858 */             if (value < 0L) throw new NoSuchElementException();
/* 859 */             if (value > 2147483647L) {
/* 860 */               throw new RuntimeException("BitSet range larger than maximum positive integer.");
/*     */             }
/* 862 */             this.nextBitSet = BitSet.this.nextSetBit(value + 1L);
/* 863 */             this.cursor.index = (this.cursor.value = (int)value);
/* 864 */             return this.cursor;
/*     */           }
/*     */           
/*     */ 
/*     */           public void remove()
/*     */           {
/* 870 */             throw new UnsupportedOperationException();
/*     */           }
/*     */         };
/*     */       }
/*     */       
/*     */ 
/*     */       public int[] toArray()
/*     */       {
/* 878 */         int[] data = new int[getCurrentCardinality()];
/* 879 */         BitSetIterator i = BitSet.this.iterator();
/* 880 */         int j = 0; for (int bit = i.nextSetBit(); bit >= 0; bit = i.nextSetBit())
/*     */         {
/* 882 */           data[(j++)] = bit;
/*     */         }
/* 884 */         return data;
/*     */       }
/*     */       
/*     */ 
/*     */       public <T extends IntPredicate> T forEach(T predicate)
/*     */       {
/* 890 */         BitSetIterator i = BitSet.this.iterator();
/* 891 */         for (int bit = i.nextSetBit(); bit >= 0; bit = i.nextSetBit())
/*     */         {
/* 893 */           if (!predicate.apply(bit)) {
/*     */             break;
/*     */           }
/*     */         }
/* 897 */         return predicate;
/*     */       }
/*     */       
/*     */ 
/*     */       public <T extends IntProcedure> T forEach(T procedure)
/*     */       {
/* 903 */         BitSetIterator i = BitSet.this.iterator();
/* 904 */         for (int bit = i.nextSetBit(); bit >= 0; bit = i.nextSetBit())
/*     */         {
/* 906 */           procedure.apply(bit);
/*     */         }
/*     */         
/* 909 */         return procedure;
/*     */       }
/*     */       
/*     */ 
/*     */       public boolean contains(int index)
/*     */       {
/* 915 */         return (index < 0) || (BitSet.this.get(index));
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       private int getCurrentCardinality()
/*     */       {
/* 924 */         long cardinality = BitSet.this.cardinality();
/* 925 */         if (cardinality > 2147483647L) {
/* 926 */           throw new RuntimeException("Bitset is larger than maximum positive integer: " + cardinality);
/*     */         }
/* 928 */         return (int)cardinality;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LongLookupContainer asLongLookupContainer()
/*     */   {
/* 940 */     new LongLookupContainer()
/*     */     {
/*     */ 
/*     */       public int size()
/*     */       {
/* 945 */         return getCurrentCardinality();
/*     */       }
/*     */       
/*     */ 
/*     */       public boolean isEmpty()
/*     */       {
/* 951 */         return BitSet.this.isEmpty();
/*     */       }
/*     */       
/*     */ 
/*     */       public Iterator<LongCursor> iterator()
/*     */       {
/* 957 */         new Iterator() {
/* 958 */           private long nextBitSet = BitSet.this.nextSetBit(0);
/* 959 */           private final LongCursor cursor = new LongCursor();
/*     */           
/*     */ 
/*     */           public boolean hasNext()
/*     */           {
/* 964 */             return this.nextBitSet >= 0L;
/*     */           }
/*     */           
/*     */ 
/*     */           public LongCursor next()
/*     */           {
/* 970 */             long value = this.nextBitSet;
/* 971 */             if (value < 0L) { throw new NoSuchElementException();
/*     */             }
/* 973 */             this.nextBitSet = BitSet.this.nextSetBit(value + 1L);
/* 974 */             this.cursor.index = ((int)value);
/* 975 */             this.cursor.value = value;
/* 976 */             return this.cursor;
/*     */           }
/*     */           
/*     */ 
/*     */           public void remove()
/*     */           {
/* 982 */             throw new UnsupportedOperationException();
/*     */           }
/*     */         };
/*     */       }
/*     */       
/*     */ 
/*     */       public long[] toArray()
/*     */       {
/* 990 */         long[] data = new long[getCurrentCardinality()];
/* 991 */         BitSet bset = BitSet.this;
/* 992 */         int j = 0;
/* 993 */         for (long bit = bset.nextSetBit(0L); bit >= 0L; bit = bset.nextSetBit(bit + 1L))
/*     */         {
/* 995 */           data[(j++)] = bit;
/*     */         }
/* 997 */         return data;
/*     */       }
/*     */       
/*     */ 
/*     */       public <T extends LongPredicate> T forEach(T predicate)
/*     */       {
/* :03 */         BitSet bset = BitSet.this;
/* :04 */         for (long bit = bset.nextSetBit(0L); bit >= 0L; bit = bset.nextSetBit(bit + 1L))
/*     */         {
/* :06 */           if (!predicate.apply(bit)) {
/*     */             break;
/*     */           }
/*     */         }
/* :10 */         return predicate;
/*     */       }
/*     */       
/*     */ 
/*     */       public <T extends LongProcedure> T forEach(T procedure)
/*     */       {
/* :16 */         BitSet bset = BitSet.this;
/* :17 */         for (long bit = bset.nextSetBit(0L); bit >= 0L; bit = bset.nextSetBit(bit + 1L))
/*     */         {
/* :19 */           procedure.apply(bit);
/*     */         }
/*     */         
/* :22 */         return procedure;
/*     */       }
/*     */       
/*     */ 
/*     */       public boolean contains(long index)
/*     */       {
/* :28 */         return (index < 0L) || (BitSet.this.get(index));
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       private int getCurrentCardinality()
/*     */       {
/* :37 */         long cardinality = BitSet.this.cardinality();
/* :38 */         if (cardinality > 2147483647L) {
/* :39 */           throw new RuntimeException("Bitset is larger than maximum positive integer: " + cardinality);
/*     */         }
/* :41 */         return (int)cardinality;
/*     */       }
/*     */     };
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/BitSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */