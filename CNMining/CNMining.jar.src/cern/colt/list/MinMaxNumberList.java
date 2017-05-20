/*     */ package cern.colt.list;
/*     */ 
/*     */ import cern.colt.bitvector.BitVector;
/*     */ import cern.colt.bitvector.QuickBitVector;
/*     */ import cern.jet.math.Arithmetic;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MinMaxNumberList
/*     */   extends AbstractLongList
/*     */ {
/*     */   protected long minValue;
/*     */   protected int bitsPerElement;
/*     */   protected long[] bits;
/*     */   protected int capacity;
/*     */   
/*     */   public MinMaxNumberList(long paramLong1, long paramLong2, int paramInt)
/*     */   {
/*  81 */     setUp(paramLong1, paramLong2, paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(long paramLong)
/*     */   {
/*  90 */     if (this.size == this.capacity) {
/*  91 */       ensureCapacity(this.size + 1);
/*     */     }
/*  93 */     int i = this.size * this.bitsPerElement;
/*  94 */     QuickBitVector.putLongFromTo(this.bits, paramLong - this.minValue, i, i + this.bitsPerElement - 1);
/*  95 */     this.size += 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addAllOfFromTo(long[] paramArrayOfLong, int paramInt1, int paramInt2)
/*     */   {
/* 105 */     int i = this.bitsPerElement;
/* 106 */     int j = i - 1;
/* 107 */     long l = this.minValue;
/* 108 */     long[] arrayOfLong = this.bits;
/*     */     
/*     */ 
/* 111 */     ensureCapacity(this.size + paramInt2 - paramInt1 + 1);
/* 112 */     int k = this.size * i;
/* 113 */     int m = paramInt1;
/* 114 */     int n = paramInt2 - paramInt1 + 1;
/* 115 */     do { QuickBitVector.putLongFromTo(arrayOfLong, paramArrayOfLong[(m++)] - l, k, k + j);
/* 116 */       k += i;n--;
/* 114 */     } while (n >= 0);
/*     */     
/*     */ 
/*     */ 
/* 118 */     this.size += paramInt2 - paramInt1 + 1;
/*     */   }
/*     */   
/*     */ 
/*     */   public int bitsPerElement()
/*     */   {
/* 124 */     return this.bitsPerElement;
/*     */   }
/*     */   
/*     */ 
/*     */   public static int bitsPerElement(long paramLong1, long paramLong2)
/*     */   {
/*     */     int i;
/* 131 */     if (1L + paramLong2 - paramLong1 > 0L) {
/* 132 */       i = (int)Math.round(Math.ceil(Arithmetic.log(2.0D, 1L + paramLong2 - paramLong1)));
/*     */ 
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/* 139 */       i = 64;
/*     */     }
/* 141 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void ensureCapacity(int paramInt)
/*     */   {
/* 150 */     int i = this.capacity;
/* 151 */     if (paramInt > i) {
/* 152 */       int j = i * 3 / 2 + 1;
/* 153 */       if (j < paramInt) j = paramInt;
/* 154 */       BitVector localBitVector = toBitVector();
/* 155 */       localBitVector.setSize(j * this.bitsPerElement);
/* 156 */       this.bits = localBitVector.elements();
/* 157 */       this.capacity = j;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getQuick(int paramInt)
/*     */   {
/* 169 */     int i = paramInt * this.bitsPerElement;
/* 170 */     return this.minValue + QuickBitVector.getLongFromTo(this.bits, i, i + this.bitsPerElement - 1);
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
/*     */   public void partFromTo(int paramInt1, int paramInt2, BitVector paramBitVector, int paramInt3, long[] paramArrayOfLong, int paramInt4)
/*     */   {
/* 184 */     int i = paramInt2 - paramInt1 + 1;
/* 185 */     if ((paramInt1 < 0) || (paramInt1 > paramInt2) || (paramInt2 >= this.size) || (paramInt3 < 0) || ((paramBitVector != null) && (paramInt3 + i > paramBitVector.size()))) {
/* 186 */       throw new IndexOutOfBoundsException();
/*     */     }
/* 188 */     if ((paramInt4 < 0) || (paramInt4 + i > paramArrayOfLong.length)) {
/* 189 */       throw new IndexOutOfBoundsException();
/*     */     }
/*     */     
/* 192 */     long l = this.minValue;
/* 193 */     int j = this.bitsPerElement;
/* 194 */     long[] arrayOfLong = this.bits;
/*     */     
/* 196 */     int k = paramInt3;
/* 197 */     int m = paramInt4;
/* 198 */     int n = paramInt1 * j;
/*     */     
/*     */ 
/* 201 */     for (int i1 = paramInt1; i1 <= paramInt2; n += j) {
/* 202 */       if ((paramBitVector == null) || (paramBitVector.get(k)))
/*     */       {
/* 204 */         paramArrayOfLong[m] = (l + QuickBitVector.getLongFromTo(arrayOfLong, n, n + j - 1));
/*     */       }
/* 201 */       i1++;k++;m++;
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
/*     */ 
/*     */   public void setQuick(int paramInt, long paramLong)
/*     */   {
/* 218 */     int i = paramInt * this.bitsPerElement;
/* 219 */     QuickBitVector.putLongFromTo(this.bits, paramLong - this.minValue, i, i + this.bitsPerElement - 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void setSizeRaw(int paramInt)
/*     */   {
/* 226 */     super.setSizeRaw(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setUp(long paramLong1, long paramLong2, int paramInt)
/*     */   {
/* 236 */     setUpBitsPerEntry(paramLong1, paramLong2);
/*     */     
/*     */ 
/* 239 */     this.bits = QuickBitVector.makeBitVector(paramInt, this.bitsPerElement);
/* 240 */     this.capacity = paramInt;
/* 241 */     this.size = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setUpBitsPerEntry(long paramLong1, long paramLong2)
/*     */   {
/* 250 */     this.bitsPerElement = bitsPerElement(paramLong1, paramLong2);
/* 251 */     if (this.bitsPerElement != 64) {
/* 252 */       this.minValue = paramLong1;
/*     */ 
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/* 259 */       this.minValue = 0L;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public BitVector toBitVector()
/*     */   {
/* 267 */     return new BitVector(this.bits, this.capacity * this.bitsPerElement);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void trimToSize()
/*     */   {
/* 275 */     int i = this.capacity;
/* 276 */     if (this.size < i) {
/* 277 */       BitVector localBitVector = toBitVector();
/* 278 */       localBitVector.setSize(this.size);
/* 279 */       this.bits = localBitVector.elements();
/* 280 */       this.capacity = this.size;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public long xminimum()
/*     */   {
/* 290 */     return this.minValue;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/list/MinMaxNumberList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */