/*     */ package cern.colt.map;
/*     */ 
/*     */ import cern.colt.GenericSorting;
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.Swapper;
/*     */ import cern.colt.function.IntComparator;
/*     */ import cern.colt.function.IntIntProcedure;
/*     */ import cern.colt.function.IntProcedure;
/*     */ import cern.colt.list.AbstractIntList;
/*     */ import cern.colt.list.AbstractList;
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
/*     */ public abstract class AbstractIntIntMap
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
/*     */   public boolean containsValue(int paramInt)
/*     */   {
/*  52 */     !forEachPair(new IntIntProcedure() {
/*     */       private final int val$value;
/*     */       
/*  55 */       public boolean apply(int paramAnonymousInt1, int paramAnonymousInt2) { return this.val$value != paramAnonymousInt2; }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractIntIntMap copy()
/*     */   {
/*  66 */     return (AbstractIntIntMap)clone();
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
/* 100 */     if (paramObject == this) { return true;
/*     */     }
/* 102 */     if (!(paramObject instanceof AbstractIntIntMap)) return false;
/* 103 */     AbstractIntIntMap localAbstractIntIntMap = (AbstractIntIntMap)paramObject;
/* 104 */     if (localAbstractIntIntMap.size() != size()) { return false;
/*     */     }
/* 106 */     (forEachPair(new IntIntProcedure() {
/*     */       private final AbstractIntIntMap val$other;
/*     */       
/*     */ 
/* 110 */       public boolean apply(int paramAnonymousInt1, int paramAnonymousInt2) { return (this.val$other.containsKey(paramAnonymousInt1)) && (this.val$other.get(paramAnonymousInt1) == paramAnonymousInt2); } })) && (localAbstractIntIntMap.forEachPair(new IntIntProcedure()
/*     */     {
/*     */ 
/*     */ 
/*     */       public boolean apply(int paramAnonymousInt1, int paramAnonymousInt2)
/*     */       {
/*     */ 
/*     */ 
/* 118 */         return (AbstractIntIntMap.this.containsKey(paramAnonymousInt1)) && (AbstractIntIntMap.this.get(paramAnonymousInt1) == paramAnonymousInt2);
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
/*     */   public boolean forEachPair(IntIntProcedure paramIntIntProcedure)
/*     */   {
/* 142 */     forEachKey(new IntProcedure() {
/*     */       private final IntIntProcedure val$procedure;
/*     */       
/* 145 */       public boolean apply(int paramAnonymousInt) { return this.val$procedure.apply(paramAnonymousInt, AbstractIntIntMap.this.get(paramAnonymousInt)); }
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
/*     */   public abstract int get(int paramInt);
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
/* 168 */     int[] arrayOfInt = new int[1];
/* 169 */     boolean bool = forEachPair(new IntIntProcedure() { private final int val$value;
/*     */       private final int[] val$foundKey;
/*     */       
/* 172 */       public boolean apply(int paramAnonymousInt1, int paramAnonymousInt2) { int i = this.val$value == paramAnonymousInt2 ? 1 : 0;
/* 173 */         if (i != 0) this.val$foundKey[0] = paramAnonymousInt1;
/* 174 */         return i == 0;
/*     */       }
/*     */     });
/*     */     
/* 178 */     if (bool) return Integer.MIN_VALUE;
/* 179 */     return arrayOfInt[0];
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
/* 191 */     IntArrayList localIntArrayList = new IntArrayList(size());
/* 192 */     keys(localIntArrayList);
/* 193 */     return localIntArrayList;
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
/* 206 */     paramIntArrayList.clear();
/* 207 */     forEachKey(new IntProcedure() {
/*     */       private final IntArrayList val$list;
/*     */       
/* 210 */       public boolean apply(int paramAnonymousInt) { this.val$list.add(paramAnonymousInt);
/* 211 */         return true;
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
/* 230 */     pairsSortedByValue(paramIntArrayList, new IntArrayList(size()));
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
/*     */   public void pairsMatching(IntIntProcedure paramIntIntProcedure, IntArrayList paramIntArrayList1, IntArrayList paramIntArrayList2)
/*     */   {
/* 252 */     paramIntArrayList1.clear();
/* 253 */     paramIntArrayList2.clear();
/*     */     
/* 255 */     forEachPair(new IntIntProcedure() {
/*     */       private final IntIntProcedure val$condition;
/*     */       
/* 258 */       public boolean apply(int paramAnonymousInt1, int paramAnonymousInt2) { if (this.val$condition.apply(paramAnonymousInt1, paramAnonymousInt2)) {
/* 259 */           this.val$keyList.add(paramAnonymousInt1);
/* 260 */           this.val$valueList.add(paramAnonymousInt2);
/*     */         }
/* 262 */         return true;
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
/*     */   public void pairsSortedByKey(IntArrayList paramIntArrayList1, IntArrayList paramIntArrayList2)
/*     */   {
/* 280 */     keys(paramIntArrayList1);
/* 281 */     paramIntArrayList1.sort();
/* 282 */     paramIntArrayList2.setSize(paramIntArrayList1.size());
/* 283 */     int i = paramIntArrayList1.size();
/* 284 */     do { paramIntArrayList2.setQuick(i, get(paramIntArrayList1.getQuick(i)));i--;
/* 283 */     } while (i >= 0);
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
/*     */   private final IntArrayList val$valueList;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void pairsSortedByValue(IntArrayList paramIntArrayList1, IntArrayList paramIntArrayList2)
/*     */   {
/* 302 */     keys(paramIntArrayList1);
/* 303 */     values(paramIntArrayList2);
/*     */     
/* 305 */     int[] arrayOfInt1 = paramIntArrayList1.elements();
/* 306 */     int[] arrayOfInt2 = paramIntArrayList2.elements();
/* 307 */     Swapper local9 = new Swapper() { private final int[] val$v;
/*     */       private final int[] val$k;
/*     */       
/* 310 */       public void swap(int paramAnonymousInt1, int paramAnonymousInt2) { int j = this.val$v[paramAnonymousInt1];this.val$v[paramAnonymousInt1] = this.val$v[paramAnonymousInt2];this.val$v[paramAnonymousInt2] = j;
/* 311 */         int i = this.val$k[paramAnonymousInt1];this.val$k[paramAnonymousInt1] = this.val$k[paramAnonymousInt2];this.val$k[paramAnonymousInt2] = i;
/*     */       }
/*     */       
/* 314 */     };
/* 315 */     IntComparator local10 = new IntComparator() { private final int[] val$v;
/*     */       
/* 317 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { return this.val$k[paramAnonymousInt1] == this.val$k[paramAnonymousInt2] ? 0 : this.val$k[paramAnonymousInt1] < this.val$k[paramAnonymousInt2] ? -1 : this.val$v[paramAnonymousInt1] > this.val$v[paramAnonymousInt2] ? 1 : this.val$v[paramAnonymousInt1] < this.val$v[paramAnonymousInt2] ? -1 : 1;
/*     */       }
/*     */ 
/* 320 */     };
/* 321 */     GenericSorting.quickSort(0, paramIntArrayList1.size(), local10, local9);
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
/*     */   public abstract boolean put(int paramInt1, int paramInt2);
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
/* 345 */     IntArrayList localIntArrayList = keys();
/*     */     
/*     */ 
/* 348 */     StringBuffer localStringBuffer = new StringBuffer();
/* 349 */     localStringBuffer.append("[");
/* 350 */     int i = localIntArrayList.size() - 1;
/* 351 */     for (int j = 0; j <= i; j++) {
/* 352 */       int k = localIntArrayList.get(j);
/* 353 */       localStringBuffer.append(String.valueOf(k));
/* 354 */       localStringBuffer.append("->");
/* 355 */       localStringBuffer.append(String.valueOf(get(k)));
/* 356 */       if (j < i) localStringBuffer.append(", ");
/*     */     }
/* 358 */     localStringBuffer.append("]");
/* 359 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toStringByValue()
/*     */   {
/* 366 */     IntArrayList localIntArrayList = new IntArrayList();
/* 367 */     keysSortedByValue(localIntArrayList);
/*     */     
/* 369 */     StringBuffer localStringBuffer = new StringBuffer();
/* 370 */     localStringBuffer.append("[");
/* 371 */     int i = localIntArrayList.size() - 1;
/* 372 */     for (int j = 0; j <= i; j++) {
/* 373 */       int k = localIntArrayList.get(j);
/* 374 */       localStringBuffer.append(String.valueOf(k));
/* 375 */       localStringBuffer.append("->");
/* 376 */       localStringBuffer.append(String.valueOf(get(k)));
/* 377 */       if (j < i) localStringBuffer.append(", ");
/*     */     }
/* 379 */     localStringBuffer.append("]");
/* 380 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IntArrayList values()
/*     */   {
/* 392 */     IntArrayList localIntArrayList = new IntArrayList(size());
/* 393 */     values(localIntArrayList);
/* 394 */     return localIntArrayList;
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
/* 407 */     paramIntArrayList.clear();
/* 408 */     forEachKey(new IntProcedure() {
/*     */       private final IntArrayList val$list;
/*     */       
/* 411 */       public boolean apply(int paramAnonymousInt) { this.val$list.add(AbstractIntIntMap.this.get(paramAnonymousInt));
/* 412 */         return true;
/*     */       }
/*     */     });
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/map/AbstractIntIntMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */