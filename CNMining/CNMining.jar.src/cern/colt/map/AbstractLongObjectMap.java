/*     */ package cern.colt.map;
/*     */ 
/*     */ import cern.colt.GenericSorting;
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.Swapper;
/*     */ import cern.colt.function.IntComparator;
/*     */ import cern.colt.function.LongObjectProcedure;
/*     */ import cern.colt.function.LongProcedure;
/*     */ import cern.colt.list.AbstractList;
/*     */ import cern.colt.list.AbstractLongList;
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
/*     */ public abstract class AbstractLongObjectMap
/*     */   extends AbstractMap
/*     */ {
/*     */   public boolean containsKey(long paramLong)
/*     */   {
/*  38 */     !forEachKey(new LongProcedure() {
/*     */       private final long val$key;
/*     */       
/*  41 */       public boolean apply(long paramAnonymousLong) { return this.val$key != paramAnonymousLong; }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsValue(Object paramObject)
/*     */   {
/*  53 */     !forEachPair(new LongObjectProcedure() {
/*     */       private final Object val$value;
/*     */       
/*  56 */       public boolean apply(long paramAnonymousLong, Object paramAnonymousObject) { return this.val$value != paramAnonymousObject; }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractLongObjectMap copy()
/*     */   {
/*  67 */     return (AbstractLongObjectMap)clone();
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
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 101 */     if (paramObject == this) { return true;
/*     */     }
/* 103 */     if (!(paramObject instanceof AbstractLongObjectMap)) return false;
/* 104 */     AbstractLongObjectMap localAbstractLongObjectMap = (AbstractLongObjectMap)paramObject;
/* 105 */     if (localAbstractLongObjectMap.size() != size()) { return false;
/*     */     }
/* 107 */     (forEachPair(new LongObjectProcedure() {
/*     */       private final AbstractLongObjectMap val$other;
/*     */       
/*     */ 
/* 111 */       public boolean apply(long paramAnonymousLong, Object paramAnonymousObject) { return (this.val$other.containsKey(paramAnonymousLong)) && (this.val$other.get(paramAnonymousLong) == paramAnonymousObject); } })) && (localAbstractLongObjectMap.forEachPair(new LongObjectProcedure()
/*     */     {
/*     */ 
/*     */ 
/*     */       public boolean apply(long paramAnonymousLong, Object paramAnonymousObject)
/*     */       {
/*     */ 
/*     */ 
/* 119 */         return (AbstractLongObjectMap.this.containsKey(paramAnonymousLong)) && (AbstractLongObjectMap.this.get(paramAnonymousLong) == paramAnonymousObject);
/*     */       }
/*     */     }));
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
/*     */   public abstract boolean forEachKey(LongProcedure paramLongProcedure);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean forEachPair(LongObjectProcedure paramLongObjectProcedure)
/*     */   {
/* 143 */     forEachKey(new LongProcedure() {
/*     */       private final LongObjectProcedure val$procedure;
/*     */       
/* 146 */       public boolean apply(long paramAnonymousLong) { return this.val$procedure.apply(paramAnonymousLong, AbstractLongObjectMap.this.get(paramAnonymousLong)); }
/*     */     });
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
/*     */   public abstract Object get(long paramLong);
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
/* 169 */     long[] arrayOfLong = new long[1];
/* 170 */     boolean bool = forEachPair(new LongObjectProcedure() { private final Object val$value;
/*     */       private final long[] val$foundKey;
/*     */       
/* 173 */       public boolean apply(long paramAnonymousLong, Object paramAnonymousObject) { int i = this.val$value == paramAnonymousObject ? 1 : 0;
/* 174 */         if (i != 0) this.val$foundKey[0] = paramAnonymousLong;
/* 175 */         return i == 0;
/*     */       }
/*     */     });
/*     */     
/* 179 */     if (bool) return Long.MIN_VALUE;
/* 180 */     return arrayOfLong[0];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LongArrayList keys()
/*     */   {
/* 192 */     LongArrayList localLongArrayList = new LongArrayList(size());
/* 193 */     keys(localLongArrayList);
/* 194 */     return localLongArrayList;
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
/* 207 */     paramLongArrayList.clear();
/* 208 */     forEachKey(new LongProcedure() {
/*     */       private final LongArrayList val$list;
/*     */       
/* 211 */       public boolean apply(long paramAnonymousLong) { this.val$list.add(paramAnonymousLong);
/* 212 */         return true;
/*     */       }
/*     */     });
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
/*     */   public void keysSortedByValue(LongArrayList paramLongArrayList)
/*     */   {
/* 231 */     pairsSortedByValue(paramLongArrayList, new ObjectArrayList(size()));
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
/*     */   public void pairsMatching(LongObjectProcedure paramLongObjectProcedure, LongArrayList paramLongArrayList, ObjectArrayList paramObjectArrayList)
/*     */   {
/* 253 */     paramLongArrayList.clear();
/* 254 */     paramObjectArrayList.clear();
/*     */     
/* 256 */     forEachPair(new LongObjectProcedure() {
/*     */       private final LongObjectProcedure val$condition;
/*     */       
/* 259 */       public boolean apply(long paramAnonymousLong, Object paramAnonymousObject) { if (this.val$condition.apply(paramAnonymousLong, paramAnonymousObject)) {
/* 260 */           this.val$keyList.add(paramAnonymousLong);
/* 261 */           this.val$valueList.add(paramAnonymousObject);
/*     */         }
/* 263 */         return true;
/*     */       }
/*     */     });
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
/*     */   public void pairsSortedByKey(LongArrayList paramLongArrayList, ObjectArrayList paramObjectArrayList)
/*     */   {
/* 281 */     keys(paramLongArrayList);
/* 282 */     paramLongArrayList.sort();
/* 283 */     paramObjectArrayList.setSize(paramLongArrayList.size());
/* 284 */     int i = paramLongArrayList.size();
/* 285 */     do { paramObjectArrayList.setQuick(i, get(paramLongArrayList.getQuick(i)));i--;
/* 284 */     } while (i >= 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final LongArrayList val$keyList;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private final ObjectArrayList val$valueList;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void pairsSortedByValue(LongArrayList paramLongArrayList, ObjectArrayList paramObjectArrayList)
/*     */   {
/* 303 */     keys(paramLongArrayList);
/* 304 */     values(paramObjectArrayList);
/*     */     
/* 306 */     long[] arrayOfLong = paramLongArrayList.elements();
/* 307 */     Object[] arrayOfObject = paramObjectArrayList.elements();
/* 308 */     Swapper local9 = new Swapper() { private final Object[] val$v;
/*     */       private final long[] val$k;
/*     */       
/* 311 */       public void swap(int paramAnonymousInt1, int paramAnonymousInt2) { Object localObject = this.val$v[paramAnonymousInt1];this.val$v[paramAnonymousInt1] = this.val$v[paramAnonymousInt2];this.val$v[paramAnonymousInt2] = localObject;
/* 312 */         long l = this.val$k[paramAnonymousInt1];this.val$k[paramAnonymousInt1] = this.val$k[paramAnonymousInt2];this.val$k[paramAnonymousInt2] = l;
/*     */       }
/*     */       
/* 315 */     };
/* 316 */     IntComparator local10 = new IntComparator() { private final Object[] val$v;
/*     */       
/* 318 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { int i = ((Comparable)this.val$v[paramAnonymousInt1]).compareTo((Comparable)this.val$v[paramAnonymousInt2]);
/* 319 */         return this.val$k[paramAnonymousInt1] == this.val$k[paramAnonymousInt2] ? 0 : this.val$k[paramAnonymousInt1] < this.val$k[paramAnonymousInt2] ? -1 : i > 0 ? 1 : i < 0 ? -1 : 1;
/*     */       }
/*     */       
/*     */ 
/* 323 */     };
/* 324 */     GenericSorting.quickSort(0, paramLongArrayList.size(), local10, local9);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final long[] val$k;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract boolean put(long paramLong, Object paramObject);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract boolean removeKey(long paramLong);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 348 */     LongArrayList localLongArrayList = keys();
/* 349 */     localLongArrayList.sort();
/*     */     
/* 351 */     StringBuffer localStringBuffer = new StringBuffer();
/* 352 */     localStringBuffer.append("[");
/* 353 */     int i = localLongArrayList.size() - 1;
/* 354 */     for (int j = 0; j <= i; j++) {
/* 355 */       long l = localLongArrayList.get(j);
/* 356 */       localStringBuffer.append(String.valueOf(l));
/* 357 */       localStringBuffer.append("->");
/* 358 */       localStringBuffer.append(String.valueOf(get(l)));
/* 359 */       if (j < i) localStringBuffer.append(", ");
/*     */     }
/* 361 */     localStringBuffer.append("]");
/* 362 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toStringByValue()
/*     */   {
/* 369 */     LongArrayList localLongArrayList = new LongArrayList();
/* 370 */     keysSortedByValue(localLongArrayList);
/*     */     
/* 372 */     StringBuffer localStringBuffer = new StringBuffer();
/* 373 */     localStringBuffer.append("[");
/* 374 */     int i = localLongArrayList.size() - 1;
/* 375 */     for (int j = 0; j <= i; j++) {
/* 376 */       long l = localLongArrayList.get(j);
/* 377 */       localStringBuffer.append(String.valueOf(l));
/* 378 */       localStringBuffer.append("->");
/* 379 */       localStringBuffer.append(String.valueOf(get(l)));
/* 380 */       if (j < i) localStringBuffer.append(", ");
/*     */     }
/* 382 */     localStringBuffer.append("]");
/* 383 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectArrayList values()
/*     */   {
/* 395 */     ObjectArrayList localObjectArrayList = new ObjectArrayList(size());
/* 396 */     values(localObjectArrayList);
/* 397 */     return localObjectArrayList;
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
/* 410 */     paramObjectArrayList.clear();
/* 411 */     forEachKey(new LongProcedure() {
/*     */       private final ObjectArrayList val$list;
/*     */       
/* 414 */       public boolean apply(long paramAnonymousLong) { this.val$list.add(AbstractLongObjectMap.this.get(paramAnonymousLong));
/* 415 */         return true;
/*     */       }
/*     */     });
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/map/AbstractLongObjectMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */