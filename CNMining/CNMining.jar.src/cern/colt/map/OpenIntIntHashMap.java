/*     */ package cern.colt.map;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.function.IntIntProcedure;
/*     */ import cern.colt.function.IntProcedure;
/*     */ import cern.colt.list.AbstractByteList;
/*     */ import cern.colt.list.AbstractList;
/*     */ import cern.colt.list.ByteArrayList;
/*     */ import cern.colt.list.IntArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OpenIntIntHashMap
/*     */   extends AbstractIntIntMap
/*     */ {
/*     */   protected int[] table;
/*     */   protected int[] values;
/*     */   protected byte[] state;
/*     */   protected int freeEntries;
/*     */   protected static final byte FREE = 0;
/*     */   protected static final byte FULL = 1;
/*     */   protected static final byte REMOVED = 2;
/*     */   
/*     */   public OpenIntIntHashMap()
/*     */   {
/*  57 */     this(277);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public OpenIntIntHashMap(int paramInt)
/*     */   {
/*  67 */     this(paramInt, 0.2D, 0.5D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public OpenIntIntHashMap(int paramInt, double paramDouble1, double paramDouble2)
/*     */   {
/*  79 */     setUp(paramInt, paramDouble1, paramDouble2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/*  86 */     new ByteArrayList(this.state).fillFromToWith(0, this.state.length - 1, (byte)0);
/*     */     
/*     */ 
/*  89 */     this.distinct = 0;
/*  90 */     this.freeEntries = this.table.length;
/*  91 */     trimToSize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/*  99 */     OpenIntIntHashMap localOpenIntIntHashMap = (OpenIntIntHashMap)super.clone();
/* 100 */     localOpenIntIntHashMap.table = ((int[])localOpenIntIntHashMap.table.clone());
/* 101 */     localOpenIntIntHashMap.values = ((int[])localOpenIntIntHashMap.values.clone());
/* 102 */     localOpenIntIntHashMap.state = ((byte[])localOpenIntIntHashMap.state.clone());
/* 103 */     return localOpenIntIntHashMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsKey(int paramInt)
/*     */   {
/* 111 */     return indexOfKey(paramInt) >= 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsValue(int paramInt)
/*     */   {
/* 119 */     return indexOfValue(paramInt) >= 0;
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
/* 132 */     if (this.table.length < paramInt) {
/* 133 */       int i = nextPrime(paramInt);
/* 134 */       rehash(i);
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
/* 148 */     for (int i = this.table.length; i-- > 0; 
/* 149 */         return false) if ((this.state[i] != 1) || (paramIntProcedure.apply(this.table[i])))
/*     */         break label36;
/* 151 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean forEachPair(IntIntProcedure paramIntIntProcedure)
/*     */   {
/*     */     label42:
/*     */     
/*     */ 
/* 161 */     for (int i = this.table.length; i-- > 0; 
/* 162 */         return false) if ((this.state[i] != 1) || (paramIntIntProcedure.apply(this.table[i], this.values[i])))
/*     */         break label42;
/* 164 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int get(int paramInt)
/*     */   {
/* 174 */     int i = indexOfKey(paramInt);
/* 175 */     if (i < 0) return 0;
/* 176 */     return this.values[i];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int indexOfInsertion(int paramInt)
/*     */   {
/* 187 */     int[] arrayOfInt = this.table;
/* 188 */     byte[] arrayOfByte = this.state;
/* 189 */     int i = arrayOfInt.length;
/*     */     
/* 191 */     int j = HashFunctions.hash(paramInt) & 0x7FFFFFFF;
/* 192 */     int k = j % i;
/* 193 */     int m = j % (i - 2);
/*     */     
/* 195 */     if (m == 0) { m = 1;
/*     */     }
/*     */     
/*     */ 
/* 199 */     while ((arrayOfByte[k] == 1) && (arrayOfInt[k] != paramInt)) {
/* 200 */       k -= m;
/*     */       
/* 202 */       if (k < 0) { k += i;
/*     */       }
/*     */     }
/* 205 */     if (arrayOfByte[k] == 2)
/*     */     {
/*     */ 
/*     */ 
/* 209 */       int n = k;
/* 210 */       while ((arrayOfByte[k] != 0) && ((arrayOfByte[k] == 2) || (arrayOfInt[k] != paramInt))) {
/* 211 */         k -= m;
/*     */         
/* 213 */         if (k < 0) k += i;
/*     */       }
/* 215 */       if (arrayOfByte[k] == 0) { k = n;
/*     */       }
/*     */     }
/*     */     
/* 219 */     if (arrayOfByte[k] == 1)
/*     */     {
/*     */ 
/* 222 */       return -k - 1;
/*     */     }
/*     */     
/*     */ 
/* 226 */     return k;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected int indexOfKey(int paramInt)
/*     */   {
/* 233 */     int[] arrayOfInt = this.table;
/* 234 */     byte[] arrayOfByte = this.state;
/* 235 */     int i = arrayOfInt.length;
/*     */     
/* 237 */     int j = HashFunctions.hash(paramInt) & 0x7FFFFFFF;
/* 238 */     int k = j % i;
/* 239 */     int m = j % (i - 2);
/*     */     
/* 241 */     if (m == 0) { m = 1;
/*     */     }
/*     */     
/*     */ 
/* 245 */     while ((arrayOfByte[k] != 0) && ((arrayOfByte[k] == 2) || (arrayOfInt[k] != paramInt))) {
/* 246 */       k -= m;
/*     */       
/* 248 */       if (k < 0) { k += i;
/*     */       }
/*     */     }
/* 251 */     if (arrayOfByte[k] == 0) return -1;
/* 252 */     return k;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected int indexOfValue(int paramInt)
/*     */   {
/* 259 */     int[] arrayOfInt = this.values;
/* 260 */     byte[] arrayOfByte = this.state;
/*     */     
/* 262 */     int i = arrayOfByte.length;
/* 263 */     do { if ((arrayOfByte[i] == 1) && (arrayOfInt[i] == paramInt)) return i;
/* 262 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/* 266 */     return -1;
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
/*     */   public int keyOf(int paramInt)
/*     */   {
/* 279 */     int i = indexOfValue(paramInt);
/* 280 */     if (i < 0) return Integer.MIN_VALUE;
/* 281 */     return this.table[i];
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
/* 294 */     paramIntArrayList.setSize(this.distinct);
/* 295 */     int[] arrayOfInt1 = paramIntArrayList.elements();
/*     */     
/* 297 */     int[] arrayOfInt2 = this.table;
/* 298 */     byte[] arrayOfByte = this.state;
/*     */     
/* 300 */     int i = 0;
/* 301 */     for (int j = arrayOfInt2.length; j-- > 0;) {
/* 302 */       if (arrayOfByte[j] == 1) { arrayOfInt1[(i++)] = arrayOfInt2[j];
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
/*     */   public void pairsMatching(IntIntProcedure paramIntIntProcedure, IntArrayList paramIntArrayList1, IntArrayList paramIntArrayList2)
/*     */   {
/* 325 */     paramIntArrayList1.clear();
/* 326 */     paramIntArrayList2.clear();
/*     */     
/* 328 */     for (int i = this.table.length; i-- > 0;) {
/* 329 */       if ((this.state[i] == 1) && (paramIntIntProcedure.apply(this.table[i], this.values[i]))) {
/* 330 */         paramIntArrayList1.add(this.table[i]);
/* 331 */         paramIntArrayList2.add(this.values[i]);
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
/*     */   public boolean put(int paramInt1, int paramInt2)
/*     */   {
/* 345 */     int i = indexOfInsertion(paramInt1);
/* 346 */     if (i < 0) {
/* 347 */       i = -i - 1;
/* 348 */       this.values[i] = paramInt2;
/* 349 */       return false;
/*     */     }
/*     */     int j;
/* 352 */     if (this.distinct > this.highWaterMark) {
/* 353 */       j = chooseGrowCapacity(this.distinct + 1, this.minLoadFactor, this.maxLoadFactor);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 358 */       rehash(j);
/* 359 */       return put(paramInt1, paramInt2);
/*     */     }
/*     */     
/* 362 */     this.table[i] = paramInt1;
/* 363 */     this.values[i] = paramInt2;
/* 364 */     if (this.state[i] == 0) this.freeEntries -= 1;
/* 365 */     this.state[i] = 1;
/* 366 */     this.distinct += 1;
/*     */     
/* 368 */     if (this.freeEntries < 1) {
/* 369 */       j = chooseGrowCapacity(this.distinct + 1, this.minLoadFactor, this.maxLoadFactor);
/* 370 */       rehash(j);
/*     */     }
/*     */     
/* 373 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void rehash(int paramInt)
/*     */   {
/* 382 */     int i = this.table.length;
/*     */     
/*     */ 
/* 385 */     int[] arrayOfInt1 = this.table;
/* 386 */     int[] arrayOfInt2 = this.values;
/* 387 */     byte[] arrayOfByte1 = this.state;
/*     */     
/* 389 */     int[] arrayOfInt3 = new int[paramInt];
/* 390 */     int[] arrayOfInt4 = new int[paramInt];
/* 391 */     byte[] arrayOfByte2 = new byte[paramInt];
/*     */     
/* 393 */     this.lowWaterMark = chooseLowWaterMark(paramInt, this.minLoadFactor);
/* 394 */     this.highWaterMark = chooseHighWaterMark(paramInt, this.maxLoadFactor);
/*     */     
/* 396 */     this.table = arrayOfInt3;
/* 397 */     this.values = arrayOfInt4;
/* 398 */     this.state = arrayOfByte2;
/* 399 */     this.freeEntries = (paramInt - this.distinct);
/*     */     
/* 401 */     for (int j = i; j-- > 0;) {
/* 402 */       if (arrayOfByte1[j] == 1) {
/* 403 */         int k = arrayOfInt1[j];
/* 404 */         int m = indexOfInsertion(k);
/* 405 */         arrayOfInt3[m] = k;
/* 406 */         arrayOfInt4[m] = arrayOfInt2[j];
/* 407 */         arrayOfByte2[m] = 1;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean removeKey(int paramInt)
/*     */   {
/* 418 */     int i = indexOfKey(paramInt);
/* 419 */     if (i < 0) { return false;
/*     */     }
/* 421 */     this.state[i] = 2;
/*     */     
/* 423 */     this.distinct -= 1;
/*     */     
/* 425 */     if (this.distinct < this.lowWaterMark) {
/* 426 */       int j = chooseShrinkCapacity(this.distinct, this.minLoadFactor, this.maxLoadFactor);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 433 */       rehash(j);
/*     */     }
/*     */     
/* 436 */     return true;
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
/* 447 */     int i = paramInt;
/* 448 */     super.setUp(i, paramDouble1, paramDouble2);
/* 449 */     i = nextPrime(i);
/* 450 */     if (i == 0) { i = 1;
/*     */     }
/* 452 */     this.table = new int[i];
/* 453 */     this.values = new int[i];
/* 454 */     this.state = new byte[i];
/*     */     
/*     */ 
/* 457 */     this.minLoadFactor = paramDouble1;
/* 458 */     if (i == Integer.MAX_VALUE) this.maxLoadFactor = 1.0D; else {
/* 459 */       this.maxLoadFactor = paramDouble2;
/*     */     }
/* 461 */     this.distinct = 0;
/* 462 */     this.freeEntries = i;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 468 */     this.lowWaterMark = 0;
/* 469 */     this.highWaterMark = chooseHighWaterMark(i, this.maxLoadFactor);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void trimToSize()
/*     */   {
/* 479 */     int i = nextPrime((int)(1.0D + 1.2D * size()));
/* 480 */     if (this.table.length > i) {
/* 481 */       rehash(i);
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
/*     */   public void values(IntArrayList paramIntArrayList)
/*     */   {
/* 495 */     paramIntArrayList.setSize(this.distinct);
/* 496 */     int[] arrayOfInt1 = paramIntArrayList.elements();
/*     */     
/* 498 */     int[] arrayOfInt2 = this.values;
/* 499 */     byte[] arrayOfByte = this.state;
/*     */     
/* 501 */     int i = 0;
/* 502 */     for (int j = arrayOfByte.length; j-- > 0;) {
/* 503 */       if (arrayOfByte[j] == 1) arrayOfInt1[(i++)] = arrayOfInt2[j];
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/map/OpenIntIntHashMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */