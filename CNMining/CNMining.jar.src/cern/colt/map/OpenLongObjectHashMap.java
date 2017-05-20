/*     */ package cern.colt.map;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.function.LongObjectProcedure;
/*     */ import cern.colt.function.LongProcedure;
/*     */ import cern.colt.list.AbstractByteList;
/*     */ import cern.colt.list.AbstractList;
/*     */ import cern.colt.list.ByteArrayList;
/*     */ import cern.colt.list.LongArrayList;
/*     */ import cern.colt.list.ObjectArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OpenLongObjectHashMap
/*     */   extends AbstractLongObjectMap
/*     */ {
/*     */   protected long[] table;
/*     */   protected Object[] values;
/*     */   protected byte[] state;
/*     */   protected int freeEntries;
/*     */   protected static final byte FREE = 0;
/*     */   protected static final byte FULL = 1;
/*     */   protected static final byte REMOVED = 2;
/*     */   
/*     */   public OpenLongObjectHashMap()
/*     */   {
/*  57 */     this(277);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public OpenLongObjectHashMap(int paramInt)
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
/*     */   public OpenLongObjectHashMap(int paramInt, double paramDouble1, double paramDouble2)
/*     */   {
/*  79 */     setUp(paramInt, paramDouble1, paramDouble2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/*  86 */     new ByteArrayList(this.state).fillFromToWith(0, this.state.length - 1, (byte)0);
/*  87 */     new ObjectArrayList(this.values).fillFromToWith(0, this.state.length - 1, null);
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
/*  99 */     OpenLongObjectHashMap localOpenLongObjectHashMap = (OpenLongObjectHashMap)super.clone();
/* 100 */     localOpenLongObjectHashMap.table = ((long[])localOpenLongObjectHashMap.table.clone());
/* 101 */     localOpenLongObjectHashMap.values = ((Object[])localOpenLongObjectHashMap.values.clone());
/* 102 */     localOpenLongObjectHashMap.state = ((byte[])localOpenLongObjectHashMap.state.clone());
/* 103 */     return localOpenLongObjectHashMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsKey(long paramLong)
/*     */   {
/* 111 */     return indexOfKey(paramLong) >= 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsValue(Object paramObject)
/*     */   {
/* 119 */     return indexOfValue(paramObject) >= 0;
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
/*     */   public boolean forEachKey(LongProcedure paramLongProcedure)
/*     */   {
/*     */     label36:
/*     */     
/*     */ 
/*     */ 
/* 148 */     for (int i = this.table.length; i-- > 0; 
/* 149 */         return false) if ((this.state[i] != 1) || (paramLongProcedure.apply(this.table[i])))
/*     */         break label36;
/* 151 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean forEachPair(LongObjectProcedure paramLongObjectProcedure)
/*     */   {
/*     */     label42:
/*     */     
/*     */ 
/* 161 */     for (int i = this.table.length; i-- > 0; 
/* 162 */         return false) if ((this.state[i] != 1) || (paramLongObjectProcedure.apply(this.table[i], this.values[i])))
/*     */         break label42;
/* 164 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object get(long paramLong)
/*     */   {
/* 174 */     int i = indexOfKey(paramLong);
/* 175 */     if (i < 0) return null;
/* 176 */     return this.values[i];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int indexOfInsertion(long paramLong)
/*     */   {
/* 186 */     long[] arrayOfLong = this.table;
/* 187 */     byte[] arrayOfByte = this.state;
/* 188 */     int i = arrayOfLong.length;
/*     */     
/* 190 */     int j = HashFunctions.hash(paramLong) & 0x7FFFFFFF;
/* 191 */     int k = j % i;
/* 192 */     int m = j % (i - 2);
/*     */     
/* 194 */     if (m == 0) { m = 1;
/*     */     }
/*     */     
/*     */ 
/* 198 */     while ((arrayOfByte[k] == 1) && (arrayOfLong[k] != paramLong)) {
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
/* 209 */       while ((arrayOfByte[k] != 0) && ((arrayOfByte[k] == 2) || (arrayOfLong[k] != paramLong))) {
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
/*     */   protected int indexOfKey(long paramLong)
/*     */   {
/* 232 */     long[] arrayOfLong = this.table;
/* 233 */     byte[] arrayOfByte = this.state;
/* 234 */     int i = arrayOfLong.length;
/*     */     
/* 236 */     int j = HashFunctions.hash(paramLong) & 0x7FFFFFFF;
/* 237 */     int k = j % i;
/* 238 */     int m = j % (i - 2);
/*     */     
/* 240 */     if (m == 0) { m = 1;
/*     */     }
/*     */     
/*     */ 
/* 244 */     while ((arrayOfByte[k] != 0) && ((arrayOfByte[k] == 2) || (arrayOfLong[k] != paramLong))) {
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
/*     */   protected int indexOfValue(Object paramObject)
/*     */   {
/* 258 */     Object[] arrayOfObject = this.values;
/* 259 */     byte[] arrayOfByte = this.state;
/*     */     
/* 261 */     int i = arrayOfByte.length;
/* 262 */     do { if ((arrayOfByte[i] == 1) && (arrayOfObject[i] == paramObject)) return i;
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
/*     */   public long keyOf(Object paramObject)
/*     */   {
/* 278 */     int i = indexOfValue(paramObject);
/* 279 */     if (i < 0) return Long.MIN_VALUE;
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
/*     */   public void keys(LongArrayList paramLongArrayList)
/*     */   {
/* 293 */     paramLongArrayList.setSize(this.distinct);
/* 294 */     long[] arrayOfLong1 = paramLongArrayList.elements();
/*     */     
/* 296 */     long[] arrayOfLong2 = this.table;
/* 297 */     byte[] arrayOfByte = this.state;
/*     */     
/* 299 */     int i = 0;
/* 300 */     for (int j = arrayOfLong2.length; j-- > 0;) {
/* 301 */       if (arrayOfByte[j] == 1) { arrayOfLong1[(i++)] = arrayOfLong2[j];
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
/*     */   public void pairsMatching(LongObjectProcedure paramLongObjectProcedure, LongArrayList paramLongArrayList, ObjectArrayList paramObjectArrayList)
/*     */   {
/* 324 */     paramLongArrayList.clear();
/* 325 */     paramObjectArrayList.clear();
/*     */     
/* 327 */     for (int i = this.table.length; i-- > 0;) {
/* 328 */       if ((this.state[i] == 1) && (paramLongObjectProcedure.apply(this.table[i], this.values[i]))) {
/* 329 */         paramLongArrayList.add(this.table[i]);
/* 330 */         paramObjectArrayList.add(this.values[i]);
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
/*     */   public boolean put(long paramLong, Object paramObject)
/*     */   {
/* 344 */     int i = indexOfInsertion(paramLong);
/* 345 */     if (i < 0) {
/* 346 */       i = -i - 1;
/* 347 */       this.values[i] = paramObject;
/* 348 */       return false;
/*     */     }
/*     */     int j;
/* 351 */     if (this.distinct > this.highWaterMark) {
/* 352 */       j = chooseGrowCapacity(this.distinct + 1, this.minLoadFactor, this.maxLoadFactor);
/* 353 */       rehash(j);
/* 354 */       return put(paramLong, paramObject);
/*     */     }
/*     */     
/* 357 */     this.table[i] = paramLong;
/* 358 */     this.values[i] = paramObject;
/* 359 */     if (this.state[i] == 0) this.freeEntries -= 1;
/* 360 */     this.state[i] = 1;
/* 361 */     this.distinct += 1;
/*     */     
/* 363 */     if (this.freeEntries < 1) {
/* 364 */       j = chooseGrowCapacity(this.distinct + 1, this.minLoadFactor, this.maxLoadFactor);
/* 365 */       rehash(j);
/*     */     }
/*     */     
/* 368 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void rehash(int paramInt)
/*     */   {
/* 377 */     int i = this.table.length;
/*     */     
/*     */ 
/* 380 */     long[] arrayOfLong1 = this.table;
/* 381 */     Object[] arrayOfObject1 = this.values;
/* 382 */     byte[] arrayOfByte1 = this.state;
/*     */     
/* 384 */     long[] arrayOfLong2 = new long[paramInt];
/* 385 */     Object[] arrayOfObject2 = new Object[paramInt];
/* 386 */     byte[] arrayOfByte2 = new byte[paramInt];
/*     */     
/* 388 */     this.lowWaterMark = chooseLowWaterMark(paramInt, this.minLoadFactor);
/* 389 */     this.highWaterMark = chooseHighWaterMark(paramInt, this.maxLoadFactor);
/*     */     
/* 391 */     this.table = arrayOfLong2;
/* 392 */     this.values = arrayOfObject2;
/* 393 */     this.state = arrayOfByte2;
/* 394 */     this.freeEntries = (paramInt - this.distinct);
/*     */     
/* 396 */     for (int j = i; j-- > 0;) {
/* 397 */       if (arrayOfByte1[j] == 1) {
/* 398 */         long l = arrayOfLong1[j];
/* 399 */         int k = indexOfInsertion(l);
/* 400 */         arrayOfLong2[k] = l;
/* 401 */         arrayOfObject2[k] = arrayOfObject1[j];
/* 402 */         arrayOfByte2[k] = 1;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean removeKey(long paramLong)
/*     */   {
/* 413 */     int i = indexOfKey(paramLong);
/* 414 */     if (i < 0) { return false;
/*     */     }
/* 416 */     this.state[i] = 2;
/* 417 */     this.values[i] = null;
/* 418 */     this.distinct -= 1;
/*     */     
/* 420 */     if (this.distinct < this.lowWaterMark) {
/* 421 */       int j = chooseShrinkCapacity(this.distinct, this.minLoadFactor, this.maxLoadFactor);
/* 422 */       rehash(j);
/*     */     }
/*     */     
/* 425 */     return true;
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
/* 436 */     int i = paramInt;
/* 437 */     super.setUp(i, paramDouble1, paramDouble2);
/* 438 */     i = nextPrime(i);
/* 439 */     if (i == 0) { i = 1;
/*     */     }
/* 441 */     this.table = new long[i];
/* 442 */     this.values = new Object[i];
/* 443 */     this.state = new byte[i];
/*     */     
/*     */ 
/* 446 */     this.minLoadFactor = paramDouble1;
/* 447 */     if (i == Integer.MAX_VALUE) this.maxLoadFactor = 1.0D; else {
/* 448 */       this.maxLoadFactor = paramDouble2;
/*     */     }
/* 450 */     this.distinct = 0;
/* 451 */     this.freeEntries = i;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 457 */     this.lowWaterMark = 0;
/* 458 */     this.highWaterMark = chooseHighWaterMark(i, this.maxLoadFactor);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void trimToSize()
/*     */   {
/* 468 */     int i = nextPrime((int)(1.0D + 1.2D * size()));
/* 469 */     if (this.table.length > i) {
/* 470 */       rehash(i);
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
/*     */   public void values(ObjectArrayList paramObjectArrayList)
/*     */   {
/* 484 */     paramObjectArrayList.setSize(this.distinct);
/* 485 */     Object[] arrayOfObject1 = paramObjectArrayList.elements();
/*     */     
/* 487 */     Object[] arrayOfObject2 = this.values;
/* 488 */     byte[] arrayOfByte = this.state;
/*     */     
/* 490 */     int i = 0;
/* 491 */     for (int j = arrayOfByte.length; j-- > 0;) {
/* 492 */       if (arrayOfByte[j] == 1) arrayOfObject1[(i++)] = arrayOfObject2[j];
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/map/OpenLongObjectHashMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */