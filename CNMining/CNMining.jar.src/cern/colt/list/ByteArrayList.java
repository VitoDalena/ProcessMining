/*     */ package cern.colt.list;
/*     */ 
/*     */ import cern.colt.Arrays;
/*     */ import cern.colt.Sorting;
/*     */ import cern.colt.function.ByteProcedure;
/*     */ import cern.jet.math.Arithmetic;
/*     */ import cern.jet.random.Uniform;
/*     */ import cern.jet.random.engine.DRand;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ByteArrayList
/*     */   extends AbstractByteList
/*     */ {
/*     */   protected byte[] elements;
/*     */   
/*     */   public ByteArrayList()
/*     */   {
/*  27 */     this(10);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ByteArrayList(byte[] paramArrayOfByte)
/*     */   {
/*  39 */     elements(paramArrayOfByte);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ByteArrayList(int paramInt)
/*     */   {
/*  47 */     this(new byte[paramInt]);
/*  48 */     setSizeRaw(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(byte paramByte)
/*     */   {
/*  57 */     if (this.size == this.elements.length) {
/*  58 */       ensureCapacity(this.size + 1);
/*     */     }
/*  60 */     this.elements[(this.size++)] = paramByte;
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
/*     */   public void beforeInsert(int paramInt, byte paramByte)
/*     */   {
/*  73 */     if ((paramInt > this.size) || (paramInt < 0))
/*  74 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/*  75 */     ensureCapacity(this.size + 1);
/*  76 */     System.arraycopy(this.elements, paramInt, this.elements, paramInt + 1, this.size - paramInt);
/*  77 */     this.elements[paramInt] = paramByte;
/*  78 */     this.size += 1;
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
/*     */   public int binarySearchFromTo(byte paramByte, int paramInt1, int paramInt2)
/*     */   {
/* 104 */     return Sorting.binarySearchFromTo(this.elements, paramByte, paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 113 */     ByteArrayList localByteArrayList = new ByteArrayList((byte[])this.elements.clone());
/* 114 */     localByteArrayList.setSizeRaw(this.size);
/* 115 */     return localByteArrayList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ByteArrayList copy()
/*     */   {
/* 123 */     return (ByteArrayList)clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void countSortFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 135 */     if (this.size == 0) return;
/* 136 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/*     */ 
/*     */ 
/* 140 */     byte[] arrayOfByte = this.elements;
/* 141 */     int[] arrayOfInt = new int['Ā'];
/*     */     
/* 143 */     for (int i = paramInt1; i <= paramInt2; i++) { arrayOfInt[(arrayOfByte[i] + 128)] += 1;
/*     */     }
/* 145 */     int j = paramInt1;
/* 146 */     byte b = Byte.MIN_VALUE;
/* 147 */     for (int k = 0; k < 256; b = (byte)(b + 1)) {
/* 148 */       int m = arrayOfInt[k];
/* 149 */       if (m > 0) {
/* 150 */         if (m == 1) { arrayOfByte[(j++)] = b;
/*     */         } else {
/* 152 */           int n = j + m - 1;
/* 153 */           fillFromToWith(j, n, b);
/* 154 */           j = n + 1;
/*     */         }
/*     */       }
/* 147 */       k++;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void countSortFromTo(int paramInt1, int paramInt2, byte paramByte1, byte paramByte2)
/*     */   {
/* 174 */     if (this.size == 0) return;
/* 175 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 177 */     int i = paramByte2 - paramByte1 + 1;
/*     */     
/* 179 */     int[] arrayOfInt = new int[i];
/* 180 */     byte[] arrayOfByte = this.elements;
/* 181 */     for (int j = paramInt1; j <= paramInt2; arrayOfInt[(arrayOfByte[(j++)] - paramByte1)] += 1) {}
/*     */     
/* 183 */     int k = paramInt1;
/* 184 */     byte b = paramByte1;
/* 185 */     for (int m = 0; m < i; b = (byte)(b + 1)) {
/* 186 */       int n = arrayOfInt[m];
/* 187 */       if (n > 0) {
/* 188 */         if (n == 1) { arrayOfByte[(k++)] = b;
/*     */         } else {
/* 190 */           int i1 = k + n - 1;
/* 191 */           fillFromToWith(k, i1, b);
/* 192 */           k = i1 + 1;
/*     */         }
/*     */       }
/* 185 */       m++;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte[] elements()
/*     */   {
/* 206 */     return this.elements;
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
/*     */   public AbstractByteList elements(byte[] paramArrayOfByte)
/*     */   {
/* 219 */     this.elements = paramArrayOfByte;
/* 220 */     this.size = paramArrayOfByte.length;
/* 221 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void ensureCapacity(int paramInt)
/*     */   {
/* 230 */     this.elements = Arrays.ensureCapacity(this.elements, paramInt);
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
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 244 */     if (!(paramObject instanceof ByteArrayList)) return super.equals(paramObject);
/* 245 */     if (this == paramObject) return true;
/* 246 */     if (paramObject == null) return false;
/* 247 */     ByteArrayList localByteArrayList = (ByteArrayList)paramObject;
/* 248 */     if (size() != localByteArrayList.size()) { return false;
/*     */     }
/* 250 */     byte[] arrayOfByte1 = elements();
/* 251 */     byte[] arrayOfByte2 = localByteArrayList.elements();
/* 252 */     int i = size();
/* 253 */     do { if (arrayOfByte1[i] != arrayOfByte2[i]) return false;
/* 252 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/* 255 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean forEach(ByteProcedure paramByteProcedure)
/*     */   {
/* 265 */     byte[] arrayOfByte = this.elements;
/* 266 */     int i = this.size;
/*     */     
/* 268 */     for (int j = 0; j < i;) if (!paramByteProcedure.apply(arrayOfByte[(j++)])) return false;
/* 269 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte get(int paramInt)
/*     */   {
/* 280 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 281 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/* 282 */     return this.elements[paramInt];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte getQuick(int paramInt)
/*     */   {
/* 293 */     return this.elements[paramInt];
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
/*     */   public int indexOfFromTo(byte paramByte, int paramInt1, int paramInt2)
/*     */   {
/* 309 */     if (this.size == 0) return -1;
/* 310 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 312 */     byte[] arrayOfByte = this.elements;
/* 313 */     for (int i = paramInt1; i <= paramInt2; i++) {
/* 314 */       if (paramByte == arrayOfByte[i]) return i;
/*     */     }
/* 316 */     return -1;
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
/*     */   public int lastIndexOfFromTo(byte paramByte, int paramInt1, int paramInt2)
/*     */   {
/* 332 */     if (this.size == 0) return -1;
/* 333 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 335 */     byte[] arrayOfByte = this.elements;
/* 336 */     for (int i = paramInt2; i >= paramInt1; i--) {
/* 337 */       if (paramByte == arrayOfByte[i]) return i;
/*     */     }
/* 339 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractByteList partFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 349 */     if (this.size == 0) { return new ByteArrayList(0);
/*     */     }
/* 351 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 353 */     byte[] arrayOfByte = new byte[paramInt2 - paramInt1 + 1];
/* 354 */     System.arraycopy(this.elements, paramInt1, arrayOfByte, 0, paramInt2 - paramInt1 + 1);
/* 355 */     return new ByteArrayList(arrayOfByte);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean removeAll(AbstractByteList paramAbstractByteList)
/*     */   {
/* 366 */     if (!(paramAbstractByteList instanceof ByteArrayList)) { return super.removeAll(paramAbstractByteList);
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
/* 378 */     if (paramAbstractByteList.size() == 0) return false;
/* 379 */     int i = paramAbstractByteList.size() - 1;
/* 380 */     int j = 0;
/* 381 */     byte[] arrayOfByte = this.elements;
/* 382 */     int k = size();
/*     */     
/* 384 */     double d1 = paramAbstractByteList.size();
/* 385 */     double d2 = k;
/* 386 */     if ((d1 + d2) * Arithmetic.log2(d1) < d2 * d1)
/*     */     {
/* 388 */       ByteArrayList localByteArrayList = (ByteArrayList)paramAbstractByteList.clone();
/* 389 */       localByteArrayList.quickSort();
/*     */       
/* 391 */       for (int n = 0; n < k; n++) {
/* 392 */         if (localByteArrayList.binarySearchFromTo(arrayOfByte[n], 0, i) < 0) arrayOfByte[(j++)] = arrayOfByte[n];
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 397 */       for (m = 0; m < k; m++) {
/* 398 */         if (paramAbstractByteList.indexOfFromTo(arrayOfByte[m], 0, i) < 0) { arrayOfByte[(j++)] = arrayOfByte[m];
/*     */         }
/*     */       }
/*     */     }
/* 402 */     int m = j != k ? 1 : 0;
/* 403 */     setSize(j);
/* 404 */     return m;
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
/*     */   public void replaceFromToWithFrom(int paramInt1, int paramInt2, AbstractByteList paramAbstractByteList, int paramInt3)
/*     */   {
/* 418 */     if (!(paramAbstractByteList instanceof ByteArrayList))
/*     */     {
/* 420 */       super.replaceFromToWithFrom(paramInt1, paramInt2, paramAbstractByteList, paramInt3);
/* 421 */       return;
/*     */     }
/* 423 */     int i = paramInt2 - paramInt1 + 1;
/* 424 */     if (i > 0) {
/* 425 */       AbstractList.checkRangeFromTo(paramInt1, paramInt2, size());
/* 426 */       AbstractList.checkRangeFromTo(paramInt3, paramInt3 + i - 1, paramAbstractByteList.size());
/* 427 */       System.arraycopy(((ByteArrayList)paramAbstractByteList).elements, paramInt3, this.elements, paramInt1, i);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean retainAll(AbstractByteList paramAbstractByteList)
/*     */   {
/* 439 */     if (!(paramAbstractByteList instanceof ByteArrayList)) { return super.retainAll(paramAbstractByteList);
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
/* 451 */     int i = paramAbstractByteList.size() - 1;
/* 452 */     int j = 0;
/* 453 */     byte[] arrayOfByte = this.elements;
/* 454 */     int k = size();
/*     */     
/* 456 */     double d1 = paramAbstractByteList.size();
/* 457 */     double d2 = k;
/* 458 */     if ((d1 + d2) * Arithmetic.log2(d1) < d2 * d1)
/*     */     {
/* 460 */       ByteArrayList localByteArrayList = (ByteArrayList)paramAbstractByteList.clone();
/* 461 */       localByteArrayList.quickSort();
/*     */       
/* 463 */       for (int n = 0; n < k; n++) {
/* 464 */         if (localByteArrayList.binarySearchFromTo(arrayOfByte[n], 0, i) >= 0) arrayOfByte[(j++)] = arrayOfByte[n];
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 469 */       for (m = 0; m < k; m++) {
/* 470 */         if (paramAbstractByteList.indexOfFromTo(arrayOfByte[m], 0, i) >= 0) { arrayOfByte[(j++)] = arrayOfByte[m];
/*     */         }
/*     */       }
/*     */     }
/* 474 */     int m = j != k ? 1 : 0;
/* 475 */     setSize(j);
/* 476 */     return m;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reverse()
/*     */   {
/* 485 */     int j = this.size / 2;
/* 486 */     int k = this.size - 1;
/*     */     
/* 488 */     byte[] arrayOfByte = this.elements;
/* 489 */     for (int m = 0; m < j;) {
/* 490 */       int i = arrayOfByte[m];
/* 491 */       arrayOfByte[(m++)] = arrayOfByte[k];
/* 492 */       arrayOfByte[(k--)] = i;
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
/*     */   public void set(int paramInt, byte paramByte)
/*     */   {
/* 505 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 506 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/* 507 */     this.elements[paramInt] = paramByte;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setQuick(int paramInt, byte paramByte)
/*     */   {
/* 519 */     this.elements[paramInt] = paramByte;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void shuffleFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 529 */     if (this.size == 0) return;
/* 530 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 532 */     Uniform localUniform = new Uniform(new DRand(new Date()));
/*     */     
/* 534 */     byte[] arrayOfByte = this.elements;
/*     */     
/* 536 */     for (int k = paramInt1; k < paramInt2; k++) {
/* 537 */       int j = localUniform.nextIntFromTo(k, paramInt2);
/*     */       
/*     */ 
/* 540 */       int i = arrayOfByte[j];
/* 541 */       arrayOfByte[j] = arrayOfByte[k];
/* 542 */       arrayOfByte[k] = i;
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
/*     */   public void sortFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 556 */     double d1 = paramInt2 - paramInt1 + 1;
/* 557 */     double d2 = d1 * Math.log(d1) / 0.6931471805599453D;
/*     */     
/* 559 */     double d3 = 256.0D;
/* 560 */     double d4 = Math.max(d3, d1);
/*     */     
/* 562 */     if (d4 < d2) {
/* 563 */       countSortFromTo(paramInt1, paramInt2);
/*     */     }
/*     */     else {
/* 566 */       quickSortFromTo(paramInt1, paramInt2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void trimToSize()
/*     */   {
/* 575 */     this.elements = Arrays.trimToCapacity(this.elements, size());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/list/ByteArrayList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */