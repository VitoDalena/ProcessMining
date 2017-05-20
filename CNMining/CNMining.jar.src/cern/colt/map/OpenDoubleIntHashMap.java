/*     */ package cern.colt.map;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.function.DoubleIntProcedure;
/*     */ import cern.colt.function.DoubleProcedure;
/*     */ import cern.colt.list.AbstractByteList;
/*     */ import cern.colt.list.AbstractList;
/*     */ import cern.colt.list.ByteArrayList;
/*     */ import cern.colt.list.DoubleArrayList;
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
/*     */ public class OpenDoubleIntHashMap
/*     */   extends AbstractDoubleIntMap
/*     */ {
/*     */   protected double[] table;
/*     */   protected int[] values;
/*     */   protected byte[] state;
/*     */   protected int freeEntries;
/*     */   protected static final byte FREE = 0;
/*     */   protected static final byte FULL = 1;
/*     */   protected static final byte REMOVED = 2;
/*     */   
/*     */   public OpenDoubleIntHashMap()
/*     */   {
/*  57 */     this(277);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public OpenDoubleIntHashMap(int paramInt)
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
/*     */   public OpenDoubleIntHashMap(int paramInt, double paramDouble1, double paramDouble2)
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
/*  99 */     OpenDoubleIntHashMap localOpenDoubleIntHashMap = (OpenDoubleIntHashMap)super.clone();
/* 100 */     localOpenDoubleIntHashMap.table = ((double[])localOpenDoubleIntHashMap.table.clone());
/* 101 */     localOpenDoubleIntHashMap.values = ((int[])localOpenDoubleIntHashMap.values.clone());
/* 102 */     localOpenDoubleIntHashMap.state = ((byte[])localOpenDoubleIntHashMap.state.clone());
/* 103 */     return localOpenDoubleIntHashMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsKey(double paramDouble)
/*     */   {
/* 111 */     return indexOfKey(paramDouble) >= 0;
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
/*     */   public boolean forEachKey(DoubleProcedure paramDoubleProcedure)
/*     */   {
/*     */     label36:
/*     */     
/*     */ 
/*     */ 
/* 148 */     for (int i = this.table.length; i-- > 0; 
/* 149 */         return false) if ((this.state[i] != 1) || (paramDoubleProcedure.apply(this.table[i])))
/*     */         break label36;
/* 151 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean forEachPair(DoubleIntProcedure paramDoubleIntProcedure)
/*     */   {
/*     */     label42:
/*     */     
/*     */ 
/* 161 */     for (int i = this.table.length; i-- > 0; 
/* 162 */         return false) if ((this.state[i] != 1) || (paramDoubleIntProcedure.apply(this.table[i], this.values[i])))
/*     */         break label42;
/* 164 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int get(double paramDouble)
/*     */   {
/* 174 */     int i = indexOfKey(paramDouble);
/* 175 */     if (i < 0) return 0;
/* 176 */     return this.values[i];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int indexOfInsertion(double paramDouble)
/*     */   {
/* 186 */     double[] arrayOfDouble = this.table;
/* 187 */     byte[] arrayOfByte = this.state;
/* 188 */     int i = arrayOfDouble.length;
/*     */     
/* 190 */     int j = HashFunctions.hash(paramDouble) & 0x7FFFFFFF;
/* 191 */     int k = j % i;
/* 192 */     int m = j % (i - 2);
/*     */     
/* 194 */     if (m == 0) { m = 1;
/*     */     }
/*     */     
/*     */ 
/* 198 */     while ((arrayOfByte[k] == 1) && (arrayOfDouble[k] != paramDouble)) {
/* 199 */       k -= m;
/*     */       
/* 201 */       if (k < 0) { k += i;
/*     */       }
/*     */     }
/* 204 */     if (arrayOfByte[k] == 2)
/*     */     {
/*     */ 
/*     */ 
/* 208 */       int n = k;
/* 209 */       while ((arrayOfByte[k] != 0) && ((arrayOfByte[k] == 2) || (arrayOfDouble[k] != paramDouble))) {
/* 210 */         k -= m;
/*     */         
/* 212 */         if (k < 0) k += i;
/*     */       }
/* 214 */       if (arrayOfByte[k] == 0) { k = n;
/*     */       }
/*     */     }
/*     */     
/* 218 */     if (arrayOfByte[k] == 1)
/*     */     {
/*     */ 
/* 221 */       return -k - 1;
/*     */     }
/*     */     
/*     */ 
/* 225 */     return k;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected int indexOfKey(double paramDouble)
/*     */   {
/* 232 */     double[] arrayOfDouble = this.table;
/* 233 */     byte[] arrayOfByte = this.state;
/* 234 */     int i = arrayOfDouble.length;
/*     */     
/* 236 */     int j = HashFunctions.hash(paramDouble) & 0x7FFFFFFF;
/* 237 */     int k = j % i;
/* 238 */     int m = j % (i - 2);
/*     */     
/* 240 */     if (m == 0) { m = 1;
/*     */     }
/*     */     
/*     */ 
/* 244 */     while ((arrayOfByte[k] != 0) && ((arrayOfByte[k] == 2) || (arrayOfDouble[k] != paramDouble))) {
/* 245 */       k -= m;
/*     */       
/* 247 */       if (k < 0) { k += i;
/*     */       }
/*     */     }
/* 250 */     if (arrayOfByte[k] == 0) return -1;
/* 251 */     return k;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected int indexOfValue(int paramInt)
/*     */   {
/* 258 */     int[] arrayOfInt = this.values;
/* 259 */     byte[] arrayOfByte = this.state;
/*     */     
/* 261 */     int i = arrayOfByte.length;
/* 262 */     do { if ((arrayOfByte[i] == 1) && (arrayOfInt[i] == paramInt)) return i;
/* 261 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/* 265 */     return -1;
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
/*     */   public double keyOf(int paramInt)
/*     */   {
/* 278 */     int i = indexOfValue(paramInt);
/* 279 */     if (i < 0) return NaN.0D;
/* 280 */     return this.table[i];
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
/*     */   public void keys(DoubleArrayList paramDoubleArrayList)
/*     */   {
/* 293 */     paramDoubleArrayList.setSize(this.distinct);
/* 294 */     double[] arrayOfDouble1 = paramDoubleArrayList.elements();
/*     */     
/* 296 */     double[] arrayOfDouble2 = this.table;
/* 297 */     byte[] arrayOfByte = this.state;
/*     */     
/* 299 */     int i = 0;
/* 300 */     for (int j = arrayOfDouble2.length; j-- > 0;) {
/* 301 */       if (arrayOfByte[j] == 1) { arrayOfDouble1[(i++)] = arrayOfDouble2[j];
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
/*     */   public void pairsMatching(DoubleIntProcedure paramDoubleIntProcedure, DoubleArrayList paramDoubleArrayList, IntArrayList paramIntArrayList)
/*     */   {
/* 324 */     paramDoubleArrayList.clear();
/* 325 */     paramIntArrayList.clear();
/*     */     
/* 327 */     for (int i = this.table.length; i-- > 0;) {
/* 328 */       if ((this.state[i] == 1) && (paramDoubleIntProcedure.apply(this.table[i], this.values[i]))) {
/* 329 */         paramDoubleArrayList.add(this.table[i]);
/* 330 */         paramIntArrayList.add(this.values[i]);
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
/*     */   public boolean put(double paramDouble, int paramInt)
/*     */   {
/* 344 */     int i = indexOfInsertion(paramDouble);
/* 345 */     if (i < 0) {
/* 346 */       i = -i - 1;
/* 347 */       this.values[i] = paramInt;
/* 348 */       return false;
/*     */     }
/*     */     int j;
/* 351 */     if (this.distinct > this.highWaterMark) {
/* 352 */       j = chooseGrowCapacity(this.distinct + 1, this.minLoadFactor, this.maxLoadFactor);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 357 */       rehash(j);
/* 358 */       return put(paramDouble, paramInt);
/*     */     }
/*     */     
/* 361 */     this.table[i] = paramDouble;
/* 362 */     this.values[i] = paramInt;
/* 363 */     if (this.state[i] == 0) this.freeEntries -= 1;
/* 364 */     this.state[i] = 1;
/* 365 */     this.distinct += 1;
/*     */     
/* 367 */     if (this.freeEntries < 1) {
/* 368 */       j = chooseGrowCapacity(this.distinct + 1, this.minLoadFactor, this.maxLoadFactor);
/* 369 */       rehash(j);
/*     */     }
/*     */     
/* 372 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void rehash(int paramInt)
/*     */   {
/* 381 */     int i = this.table.length;
/*     */     
/*     */ 
/* 384 */     double[] arrayOfDouble1 = this.table;
/* 385 */     int[] arrayOfInt1 = this.values;
/* 386 */     byte[] arrayOfByte1 = this.state;
/*     */     
/* 388 */     double[] arrayOfDouble2 = new double[paramInt];
/* 389 */     int[] arrayOfInt2 = new int[paramInt];
/* 390 */     byte[] arrayOfByte2 = new byte[paramInt];
/*     */     
/* 392 */     this.lowWaterMark = chooseLowWaterMark(paramInt, this.minLoadFactor);
/* 393 */     this.highWaterMark = chooseHighWaterMark(paramInt, this.maxLoadFactor);
/*     */     
/* 395 */     this.table = arrayOfDouble2;
/* 396 */     this.values = arrayOfInt2;
/* 397 */     this.state = arrayOfByte2;
/* 398 */     this.freeEntries = (paramInt - this.distinct);
/*     */     
/* 400 */     for (int j = i; j-- > 0;) {
/* 401 */       if (arrayOfByte1[j] == 1) {
/* 402 */         double d = arrayOfDouble1[j];
/* 403 */         int k = indexOfInsertion(d);
/* 404 */         arrayOfDouble2[k] = d;
/* 405 */         arrayOfInt2[k] = arrayOfInt1[j];
/* 406 */         arrayOfByte2[k] = 1;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean removeKey(double paramDouble)
/*     */   {
/* 417 */     int i = indexOfKey(paramDouble);
/* 418 */     if (i < 0) { return false;
/*     */     }
/* 420 */     this.state[i] = 2;
/*     */     
/* 422 */     this.distinct -= 1;
/*     */     
/* 424 */     if (this.distinct < this.lowWaterMark) {
/* 425 */       int j = chooseShrinkCapacity(this.distinct, this.minLoadFactor, this.maxLoadFactor);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 432 */       rehash(j);
/*     */     }
/*     */     
/* 435 */     return true;
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
/* 446 */     int i = paramInt;
/* 447 */     super.setUp(i, paramDouble1, paramDouble2);
/* 448 */     i = nextPrime(i);
/* 449 */     if (i == 0) { i = 1;
/*     */     }
/* 451 */     this.table = new double[i];
/* 452 */     this.values = new int[i];
/* 453 */     this.state = new byte[i];
/*     */     
/*     */ 
/* 456 */     this.minLoadFactor = paramDouble1;
/* 457 */     if (i == Integer.MAX_VALUE) this.maxLoadFactor = 1.0D; else {
/* 458 */       this.maxLoadFactor = paramDouble2;
/*     */     }
/* 460 */     this.distinct = 0;
/* 461 */     this.freeEntries = i;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 467 */     this.lowWaterMark = 0;
/* 468 */     this.highWaterMark = chooseHighWaterMark(i, this.maxLoadFactor);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void trimToSize()
/*     */   {
/* 478 */     int i = nextPrime((int)(1.0D + 1.2D * size()));
/* 479 */     if (this.table.length > i) {
/* 480 */       rehash(i);
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
/* 494 */     paramIntArrayList.setSize(this.distinct);
/* 495 */     int[] arrayOfInt1 = paramIntArrayList.elements();
/*     */     
/* 497 */     int[] arrayOfInt2 = this.values;
/* 498 */     byte[] arrayOfByte = this.state;
/*     */     
/* 500 */     int i = 0;
/* 501 */     for (int j = arrayOfByte.length; j-- > 0;) {
/* 502 */       if (arrayOfByte[j] == 1) arrayOfInt1[(i++)] = arrayOfInt2[j];
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/map/OpenDoubleIntHashMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */