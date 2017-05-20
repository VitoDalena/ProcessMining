/*     */ package cern.colt.map;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.function.DoubleFunction;
/*     */ import cern.colt.function.IntDoubleProcedure;
/*     */ import cern.colt.function.IntProcedure;
/*     */ import cern.colt.list.AbstractByteList;
/*     */ import cern.colt.list.AbstractList;
/*     */ import cern.colt.list.ByteArrayList;
/*     */ import cern.colt.list.DoubleArrayList;
/*     */ import cern.colt.list.IntArrayList;
/*     */ import cern.jet.math.Mult;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OpenIntDoubleHashMap
/*     */   extends AbstractIntDoubleMap
/*     */ {
/*     */   protected int[] table;
/*     */   protected double[] values;
/*     */   protected byte[] state;
/*     */   protected int freeEntries;
/*     */   protected static final byte FREE = 0;
/*     */   protected static final byte FULL = 1;
/*     */   protected static final byte REMOVED = 2;
/*     */   
/*     */   public OpenIntDoubleHashMap()
/*     */   {
/*  58 */     this(277);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public OpenIntDoubleHashMap(int paramInt)
/*     */   {
/*  68 */     this(paramInt, 0.2D, 0.5D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public OpenIntDoubleHashMap(int paramInt, double paramDouble1, double paramDouble2)
/*     */   {
/*  80 */     setUp(paramInt, paramDouble1, paramDouble2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void assign(DoubleFunction paramDoubleFunction)
/*     */   {
/*  89 */     if ((paramDoubleFunction instanceof Mult)) {
/*  90 */       double d = ((Mult)paramDoubleFunction).multiplicator;
/*  91 */       if (d == 1.0D) return;
/*  92 */       if (d == 0.0D) {
/*  93 */         clear();
/*  94 */         return;
/*     */       }
/*  96 */       for (int j = this.table.length; j-- > 0;) {
/*  97 */         if (this.state[j] == 1) this.values[j] *= d;
/*     */       }
/*     */     }
/*     */     else {
/* 101 */       for (int i = this.table.length; i-- > 0;) {
/* 102 */         if (this.state[i] == 1) { this.values[i] = paramDoubleFunction.apply(this.values[i]);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void assign(AbstractIntDoubleMap paramAbstractIntDoubleMap)
/*     */   {
/* 112 */     if (!(paramAbstractIntDoubleMap instanceof OpenIntDoubleHashMap)) {
/* 113 */       super.assign(paramAbstractIntDoubleMap);
/* 114 */       return;
/*     */     }
/* 116 */     OpenIntDoubleHashMap localOpenIntDoubleHashMap1 = (OpenIntDoubleHashMap)paramAbstractIntDoubleMap;
/* 117 */     OpenIntDoubleHashMap localOpenIntDoubleHashMap2 = (OpenIntDoubleHashMap)localOpenIntDoubleHashMap1.copy();
/* 118 */     this.values = localOpenIntDoubleHashMap2.values;
/* 119 */     this.table = localOpenIntDoubleHashMap2.table;
/* 120 */     this.state = localOpenIntDoubleHashMap2.state;
/* 121 */     this.freeEntries = localOpenIntDoubleHashMap2.freeEntries;
/* 122 */     this.distinct = localOpenIntDoubleHashMap2.distinct;
/* 123 */     this.lowWaterMark = localOpenIntDoubleHashMap2.lowWaterMark;
/* 124 */     this.highWaterMark = localOpenIntDoubleHashMap2.highWaterMark;
/* 125 */     this.minLoadFactor = localOpenIntDoubleHashMap2.minLoadFactor;
/* 126 */     this.maxLoadFactor = localOpenIntDoubleHashMap2.maxLoadFactor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 133 */     new ByteArrayList(this.state).fillFromToWith(0, this.state.length - 1, (byte)0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 146 */     this.distinct = 0;
/* 147 */     this.freeEntries = this.table.length;
/* 148 */     trimToSize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 156 */     OpenIntDoubleHashMap localOpenIntDoubleHashMap = (OpenIntDoubleHashMap)super.clone();
/* 157 */     localOpenIntDoubleHashMap.table = ((int[])localOpenIntDoubleHashMap.table.clone());
/* 158 */     localOpenIntDoubleHashMap.values = ((double[])localOpenIntDoubleHashMap.values.clone());
/* 159 */     localOpenIntDoubleHashMap.state = ((byte[])localOpenIntDoubleHashMap.state.clone());
/* 160 */     return localOpenIntDoubleHashMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsKey(int paramInt)
/*     */   {
/* 168 */     return indexOfKey(paramInt) >= 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsValue(double paramDouble)
/*     */   {
/* 176 */     return indexOfValue(paramDouble) >= 0;
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
/*     */   public void ensureCapacity(int paramInt)
/*     */   {
/* 189 */     if (this.table.length < paramInt) {
/* 190 */       int i = nextPrime(paramInt);
/* 191 */       rehash(i);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean forEachKey(IntProcedure paramIntProcedure)
/*     */   {
/*     */     label36:
/*     */     
/*     */ 
/*     */ 
/* 205 */     for (int i = this.table.length; i-- > 0; 
/* 206 */         return false) if ((this.state[i] != 1) || (paramIntProcedure.apply(this.table[i])))
/*     */         break label36;
/* 208 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean forEachPair(IntDoubleProcedure paramIntDoubleProcedure)
/*     */   {
/*     */     label42:
/*     */     
/*     */ 
/* 218 */     for (int i = this.table.length; i-- > 0; 
/* 219 */         return false) if ((this.state[i] != 1) || (paramIntDoubleProcedure.apply(this.table[i], this.values[i])))
/*     */         break label42;
/* 221 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double get(int paramInt)
/*     */   {
/* 231 */     int i = indexOfKey(paramInt);
/* 232 */     if (i < 0) return 0.0D;
/* 233 */     return this.values[i];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int indexOfInsertion(int paramInt)
/*     */   {
/* 243 */     int[] arrayOfInt = this.table;
/* 244 */     byte[] arrayOfByte = this.state;
/* 245 */     int i = arrayOfInt.length;
/*     */     
/* 247 */     int j = HashFunctions.hash(paramInt) & 0x7FFFFFFF;
/* 248 */     int k = j % i;
/* 249 */     int m = j % (i - 2);
/*     */     
/* 251 */     if (m == 0) { m = 1;
/*     */     }
/*     */     
/*     */ 
/* 255 */     while ((arrayOfByte[k] == 1) && (arrayOfInt[k] != paramInt)) {
/* 256 */       k -= m;
/*     */       
/* 258 */       if (k < 0) { k += i;
/*     */       }
/*     */     }
/* 261 */     if (arrayOfByte[k] == 2)
/*     */     {
/*     */ 
/*     */ 
/* 265 */       int n = k;
/* 266 */       while ((arrayOfByte[k] != 0) && ((arrayOfByte[k] == 2) || (arrayOfInt[k] != paramInt))) {
/* 267 */         k -= m;
/*     */         
/* 269 */         if (k < 0) k += i;
/*     */       }
/* 271 */       if (arrayOfByte[k] == 0) { k = n;
/*     */       }
/*     */     }
/*     */     
/* 275 */     if (arrayOfByte[k] == 1)
/*     */     {
/*     */ 
/* 278 */       return -k - 1;
/*     */     }
/*     */     
/*     */ 
/* 282 */     return k;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected int indexOfKey(int paramInt)
/*     */   {
/* 289 */     int[] arrayOfInt = this.table;
/* 290 */     byte[] arrayOfByte = this.state;
/* 291 */     int i = arrayOfInt.length;
/*     */     
/* 293 */     int j = HashFunctions.hash(paramInt) & 0x7FFFFFFF;
/* 294 */     int k = j % i;
/* 295 */     int m = j % (i - 2);
/*     */     
/* 297 */     if (m == 0) { m = 1;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 302 */     while ((arrayOfByte[k] != 0) && ((arrayOfByte[k] == 2) || (arrayOfInt[k] != paramInt))) {
/* 303 */       k -= m;
/*     */       
/* 305 */       if (k < 0) { k += i;
/*     */       }
/*     */     }
/* 308 */     if (arrayOfByte[k] == 0) return -1;
/* 309 */     return k;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected int indexOfValue(double paramDouble)
/*     */   {
/* 316 */     double[] arrayOfDouble = this.values;
/* 317 */     byte[] arrayOfByte = this.state;
/*     */     
/* 319 */     int i = arrayOfByte.length;
/* 320 */     do { if ((arrayOfByte[i] == 1) && (arrayOfDouble[i] == paramDouble)) return i;
/* 319 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/* 323 */     return -1;
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
/*     */   public int keyOf(double paramDouble)
/*     */   {
/* 336 */     int i = indexOfValue(paramDouble);
/* 337 */     if (i < 0) return Integer.MIN_VALUE;
/* 338 */     return this.table[i];
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
/*     */   public void keys(IntArrayList paramIntArrayList)
/*     */   {
/* 351 */     paramIntArrayList.setSize(this.distinct);
/* 352 */     int[] arrayOfInt1 = paramIntArrayList.elements();
/*     */     
/* 354 */     int[] arrayOfInt2 = this.table;
/* 355 */     byte[] arrayOfByte = this.state;
/*     */     
/* 357 */     int i = 0;
/* 358 */     for (int j = arrayOfInt2.length; j-- > 0;) {
/* 359 */       if (arrayOfByte[j] == 1) { arrayOfInt1[(i++)] = arrayOfInt2[j];
/*     */       }
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
/*     */   public void pairsMatching(IntDoubleProcedure paramIntDoubleProcedure, IntArrayList paramIntArrayList, DoubleArrayList paramDoubleArrayList)
/*     */   {
/* 382 */     paramIntArrayList.clear();
/* 383 */     paramDoubleArrayList.clear();
/*     */     
/* 385 */     for (int i = this.table.length; i-- > 0;) {
/* 386 */       if ((this.state[i] == 1) && (paramIntDoubleProcedure.apply(this.table[i], this.values[i]))) {
/* 387 */         paramIntArrayList.add(this.table[i]);
/* 388 */         paramDoubleArrayList.add(this.values[i]);
/*     */       }
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
/*     */   public boolean put(int paramInt, double paramDouble)
/*     */   {
/* 402 */     int i = indexOfInsertion(paramInt);
/* 403 */     if (i < 0) {
/* 404 */       i = -i - 1;
/*     */       
/*     */ 
/* 407 */       this.values[i] = paramDouble;
/* 408 */       return false;
/*     */     }
/*     */     int j;
/* 411 */     if (this.distinct > this.highWaterMark) {
/* 412 */       j = chooseGrowCapacity(this.distinct + 1, this.minLoadFactor, this.maxLoadFactor);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 417 */       rehash(j);
/* 418 */       return put(paramInt, paramDouble);
/*     */     }
/*     */     
/* 421 */     this.table[i] = paramInt;
/* 422 */     this.values[i] = paramDouble;
/* 423 */     if (this.state[i] == 0) this.freeEntries -= 1;
/* 424 */     this.state[i] = 1;
/* 425 */     this.distinct += 1;
/*     */     
/* 427 */     if (this.freeEntries < 1) {
/* 428 */       j = chooseGrowCapacity(this.distinct + 1, this.minLoadFactor, this.maxLoadFactor);
/* 429 */       rehash(j);
/*     */     }
/*     */     
/* 432 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void rehash(int paramInt)
/*     */   {
/* 441 */     int i = this.table.length;
/*     */     
/*     */ 
/* 444 */     if (paramInt <= this.distinct) { throw new InternalError();
/*     */     }
/*     */     
/* 447 */     int[] arrayOfInt1 = this.table;
/* 448 */     double[] arrayOfDouble1 = this.values;
/* 449 */     byte[] arrayOfByte1 = this.state;
/*     */     
/* 451 */     int[] arrayOfInt2 = new int[paramInt];
/* 452 */     double[] arrayOfDouble2 = new double[paramInt];
/* 453 */     byte[] arrayOfByte2 = new byte[paramInt];
/*     */     
/* 455 */     this.lowWaterMark = chooseLowWaterMark(paramInt, this.minLoadFactor);
/* 456 */     this.highWaterMark = chooseHighWaterMark(paramInt, this.maxLoadFactor);
/*     */     
/* 458 */     this.table = arrayOfInt2;
/* 459 */     this.values = arrayOfDouble2;
/* 460 */     this.state = arrayOfByte2;
/* 461 */     this.freeEntries = (paramInt - this.distinct);
/*     */     
/* 463 */     for (int j = i; j-- > 0;) {
/* 464 */       if (arrayOfByte1[j] == 1) {
/* 465 */         int k = arrayOfInt1[j];
/* 466 */         int m = indexOfInsertion(k);
/* 467 */         arrayOfInt2[m] = k;
/* 468 */         arrayOfDouble2[m] = arrayOfDouble1[j];
/* 469 */         arrayOfByte2[m] = 1;
/*     */       }
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
/*     */   public boolean removeKey(int paramInt)
/*     */   {
/* 483 */     int i = indexOfKey(paramInt);
/* 484 */     if (i < 0) { return false;
/*     */     }
/*     */     
/*     */ 
/* 488 */     this.state[i] = 2;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 493 */     this.distinct -= 1;
/*     */     
/* 495 */     if (this.distinct < this.lowWaterMark) {
/* 496 */       int j = chooseShrinkCapacity(this.distinct, this.minLoadFactor, this.maxLoadFactor);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 503 */       rehash(j);
/*     */     }
/*     */     
/* 506 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setUp(int paramInt, double paramDouble1, double paramDouble2)
/*     */   {
/* 517 */     int i = paramInt;
/* 518 */     super.setUp(i, paramDouble1, paramDouble2);
/* 519 */     i = nextPrime(i);
/* 520 */     if (i == 0) { i = 1;
/*     */     }
/* 522 */     this.table = new int[i];
/* 523 */     this.values = new double[i];
/* 524 */     this.state = new byte[i];
/*     */     
/*     */ 
/* 527 */     this.minLoadFactor = paramDouble1;
/* 528 */     if (i == Integer.MAX_VALUE) this.maxLoadFactor = 1.0D; else {
/* 529 */       this.maxLoadFactor = paramDouble2;
/*     */     }
/* 531 */     this.distinct = 0;
/* 532 */     this.freeEntries = i;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 538 */     this.lowWaterMark = 0;
/* 539 */     this.highWaterMark = chooseHighWaterMark(i, this.maxLoadFactor);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void trimToSize()
/*     */   {
/* 549 */     int i = nextPrime((int)(1.0D + 1.2D * size()));
/* 550 */     if (this.table.length > i) {
/* 551 */       rehash(i);
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
/*     */   public void values(DoubleArrayList paramDoubleArrayList)
/*     */   {
/* 565 */     paramDoubleArrayList.setSize(this.distinct);
/* 566 */     double[] arrayOfDouble1 = paramDoubleArrayList.elements();
/*     */     
/* 568 */     double[] arrayOfDouble2 = this.values;
/* 569 */     byte[] arrayOfByte = this.state;
/*     */     
/* 571 */     int i = 0;
/* 572 */     for (int j = arrayOfByte.length; j-- > 0;) {
/* 573 */       if (arrayOfByte[j] == 1) arrayOfDouble1[(i++)] = arrayOfDouble2[j];
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/map/OpenIntDoubleHashMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */