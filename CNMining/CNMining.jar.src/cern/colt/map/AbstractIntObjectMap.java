/*     */ package cern.colt.map;
/*     */ 
/*     */ import cern.colt.GenericSorting;
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.Swapper;
/*     */ import cern.colt.function.IntComparator;
/*     */ import cern.colt.function.IntObjectProcedure;
/*     */ import cern.colt.function.IntProcedure;
/*     */ import cern.colt.list.AbstractIntList;
/*     */ import cern.colt.list.AbstractList;
/*     */ import cern.colt.list.IntArrayList;
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
/*     */ public abstract class AbstractIntObjectMap
/*     */   extends AbstractMap
/*     */ {
/*     */   public boolean containsKey(int paramInt)
/*     */   {
/*  38 */     !forEachKey(new IntProcedure() {
/*     */       private final int val$key;
/*     */       
/*  41 */       public boolean apply(int paramAnonymousInt) { return this.val$key != paramAnonymousInt; }
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
/*  53 */     !forEachPair(new IntObjectProcedure() {
/*     */       private final Object val$value;
/*     */       
/*  56 */       public boolean apply(int paramAnonymousInt, Object paramAnonymousObject) { return this.val$value != paramAnonymousObject; }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractIntObjectMap copy()
/*     */   {
/*  67 */     return (AbstractIntObjectMap)clone();
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
/* 103 */     if (!(paramObject instanceof AbstractIntObjectMap)) return false;
/* 104 */     AbstractIntObjectMap localAbstractIntObjectMap = (AbstractIntObjectMap)paramObject;
/* 105 */     if (localAbstractIntObjectMap.size() != size()) { return false;
/*     */     }
/* 107 */     (forEachPair(new IntObjectProcedure() {
/*     */       private final AbstractIntObjectMap val$other;
/*     */       
/*     */ 
/* 111 */       public boolean apply(int paramAnonymousInt, Object paramAnonymousObject) { return (this.val$other.containsKey(paramAnonymousInt)) && (this.val$other.get(paramAnonymousInt) == paramAnonymousObject); } })) && (localAbstractIntObjectMap.forEachPair(new IntObjectProcedure()
/*     */     {
/*     */ 
/*     */ 
/*     */       public boolean apply(int paramAnonymousInt, Object paramAnonymousObject)
/*     */       {
/*     */ 
/*     */ 
/* 119 */         return (AbstractIntObjectMap.this.containsKey(paramAnonymousInt)) && (AbstractIntObjectMap.this.get(paramAnonymousInt) == paramAnonymousObject);
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
/*     */   public abstract boolean forEachKey(IntProcedure paramIntProcedure);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean forEachPair(IntObjectProcedure paramIntObjectProcedure)
/*     */   {
/* 143 */     forEachKey(new IntProcedure() {
/*     */       private final IntObjectProcedure val$procedure;
/*     */       
/* 146 */       public boolean apply(int paramAnonymousInt) { return this.val$procedure.apply(paramAnonymousInt, AbstractIntObjectMap.this.get(paramAnonymousInt)); }
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
/*     */   public abstract Object get(int paramInt);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int keyOf(Object paramObject)
/*     */   {
/* 169 */     int[] arrayOfInt = new int[1];
/* 170 */     boolean bool = forEachPair(new IntObjectProcedure() { private final Object val$value;
/*     */       private final int[] val$foundKey;
/*     */       
/* 173 */       public boolean apply(int paramAnonymousInt, Object paramAnonymousObject) { int i = this.val$value == paramAnonymousObject ? 1 : 0;
/* 174 */         if (i != 0) this.val$foundKey[0] = paramAnonymousInt;
/* 175 */         return i == 0;
/*     */       }
/*     */     });
/*     */     
/* 179 */     if (bool) return Integer.MIN_VALUE;
/* 180 */     return arrayOfInt[0];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IntArrayList keys()
/*     */   {
/* 192 */     IntArrayList localIntArrayList = new IntArrayList(size());
/* 193 */     keys(localIntArrayList);
/* 194 */     return localIntArrayList;
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
/* 207 */     paramIntArrayList.clear();
/* 208 */     forEachKey(new IntProcedure() {
/*     */       private final IntArrayList val$list;
/*     */       
/* 211 */       public boolean apply(int paramAnonymousInt) { this.val$list.add(paramAnonymousInt);
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
/*     */   public void keysSortedByValue(IntArrayList paramIntArrayList)
/*     */   {
/* 231 */     pairsSortedByValue(paramIntArrayList, new ObjectArrayList(size()));
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
/*     */   public void pairsMatching(IntObjectProcedure paramIntObjectProcedure, IntArrayList paramIntArrayList, ObjectArrayList paramObjectArrayList)
/*     */   {
/* 253 */     paramIntArrayList.clear();
/* 254 */     paramObjectArrayList.clear();
/*     */     
/* 256 */     forEachPair(new IntObjectProcedure() {
/*     */       private final IntObjectProcedure val$condition;
/*     */       
/* 259 */       public boolean apply(int paramAnonymousInt, Object paramAnonymousObject) { if (this.val$condition.apply(paramAnonymousInt, paramAnonymousObject)) {
/* 260 */           this.val$keyList.add(paramAnonymousInt);
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
/*     */   public void pairsSortedByKey(IntArrayList paramIntArrayList, ObjectArrayList paramObjectArrayList)
/*     */   {
/* 281 */     keys(paramIntArrayList);
/* 282 */     paramIntArrayList.sort();
/* 283 */     paramObjectArrayList.setSize(paramIntArrayList.size());
/* 284 */     int i = paramIntArrayList.size();
/* 285 */     do { paramObjectArrayList.setQuick(i, get(paramIntArrayList.getQuick(i)));i--;
/* 284 */     } while (i >= 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final IntArrayList val$keyList;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private final ObjectArrayList val$valueList;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void pairsSortedByValue(IntArrayList paramIntArrayList, ObjectArrayList paramObjectArrayList)
/*     */   {
/* 303 */     keys(paramIntArrayList);
/* 304 */     values(paramObjectArrayList);
/*     */     
/* 306 */     int[] arrayOfInt = paramIntArrayList.elements();
/* 307 */     Object[] arrayOfObject = paramObjectArrayList.elements();
/* 308 */     Swapper local9 = new Swapper() { private final Object[] val$v;
/*     */       private final int[] val$k;
/*     */       
/* 311 */       public void swap(int paramAnonymousInt1, int paramAnonymousInt2) { Object localObject = this.val$v[paramAnonymousInt1];this.val$v[paramAnonymousInt1] = this.val$v[paramAnonymousInt2];this.val$v[paramAnonymousInt2] = localObject;
/* 312 */         int i = this.val$k[paramAnonymousInt1];this.val$k[paramAnonymousInt1] = this.val$k[paramAnonymousInt2];this.val$k[paramAnonymousInt2] = i;
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
/* 324 */     GenericSorting.quickSort(0, paramIntArrayList.size(), local10, local9);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final int[] val$k;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract boolean put(int paramInt, Object paramObject);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract boolean removeKey(int paramInt);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 348 */     IntArrayList localIntArrayList = keys();
/* 349 */     localIntArrayList.sort();
/*     */     
/* 351 */     StringBuffer localStringBuffer = new StringBuffer();
/* 352 */     localStringBuffer.append("[");
/* 353 */     int i = localIntArrayList.size() - 1;
/* 354 */     for (int j = 0; j <= i; j++) {
/* 355 */       int k = localIntArrayList.get(j);
/* 356 */       localStringBuffer.append(String.valueOf(k));
/* 357 */       localStringBuffer.append("->");
/* 358 */       localStringBuffer.append(String.valueOf(get(k)));
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
/* 369 */     IntArrayList localIntArrayList = new IntArrayList();
/* 370 */     keysSortedByValue(localIntArrayList);
/*     */     
/* 372 */     StringBuffer localStringBuffer = new StringBuffer();
/* 373 */     localStringBuffer.append("[");
/* 374 */     int i = localIntArrayList.size() - 1;
/* 375 */     for (int j = 0; j <= i; j++) {
/* 376 */       int k = localIntArrayList.get(j);
/* 377 */       localStringBuffer.append(String.valueOf(k));
/* 378 */       localStringBuffer.append("->");
/* 379 */       localStringBuffer.append(String.valueOf(get(k)));
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
/* 411 */     forEachKey(new IntProcedure() {
/*     */       private final ObjectArrayList val$list;
/*     */       
/* 414 */       public boolean apply(int paramAnonymousInt) { this.val$list.add(AbstractIntObjectMap.this.get(paramAnonymousInt));
/* 415 */         return true;
/*     */       }
/*     */     });
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/map/AbstractIntObjectMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */