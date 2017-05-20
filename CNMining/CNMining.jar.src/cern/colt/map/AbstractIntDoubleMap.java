/*     */ package cern.colt.map;
/*     */ 
/*     */ import cern.colt.GenericSorting;
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.Swapper;
/*     */ import cern.colt.function.DoubleFunction;
/*     */ import cern.colt.function.IntComparator;
/*     */ import cern.colt.function.IntDoubleProcedure;
/*     */ import cern.colt.function.IntProcedure;
/*     */ import cern.colt.list.AbstractIntList;
/*     */ import cern.colt.list.AbstractList;
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
/*     */ public abstract class AbstractIntDoubleMap
/*     */   extends AbstractMap
/*     */ {
/*     */   public void assign(DoubleFunction paramDoubleFunction)
/*     */   {
/*  38 */     copy().forEachPair(new IntDoubleProcedure() {
/*     */       private final DoubleFunction val$function;
/*     */       
/*  41 */       public boolean apply(int paramAnonymousInt, double paramAnonymousDouble) { AbstractIntDoubleMap.this.put(paramAnonymousInt, this.val$function.apply(paramAnonymousDouble));
/*  42 */         return true;
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void assign(AbstractIntDoubleMap paramAbstractIntDoubleMap)
/*     */   {
/*  53 */     clear();
/*  54 */     paramAbstractIntDoubleMap.forEachPair(new IntDoubleProcedure()
/*     */     {
/*     */       public boolean apply(int paramAnonymousInt, double paramAnonymousDouble) {
/*  57 */         AbstractIntDoubleMap.this.put(paramAnonymousInt, paramAnonymousDouble);
/*  58 */         return true;
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsKey(int paramInt)
/*     */   {
/*  69 */     !forEachKey(new IntProcedure() {
/*     */       private final int val$key;
/*     */       
/*  72 */       public boolean apply(int paramAnonymousInt) { return this.val$key != paramAnonymousInt; }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsValue(double paramDouble)
/*     */   {
/*  83 */     !forEachPair(new IntDoubleProcedure() {
/*     */       private final double val$value;
/*     */       
/*  86 */       public boolean apply(int paramAnonymousInt, double paramAnonymousDouble) { return this.val$value != paramAnonymousDouble; }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractIntDoubleMap copy()
/*     */   {
/*  97 */     return (AbstractIntDoubleMap)clone();
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
/* 131 */     if (paramObject == this) { return true;
/*     */     }
/* 133 */     if (!(paramObject instanceof AbstractIntDoubleMap)) return false;
/* 134 */     AbstractIntDoubleMap localAbstractIntDoubleMap = (AbstractIntDoubleMap)paramObject;
/* 135 */     if (localAbstractIntDoubleMap.size() != size()) { return false;
/*     */     }
/* 137 */     (forEachPair(new IntDoubleProcedure() {
/*     */       private final AbstractIntDoubleMap val$other;
/*     */       
/*     */ 
/* 141 */       public boolean apply(int paramAnonymousInt, double paramAnonymousDouble) { return (this.val$other.containsKey(paramAnonymousInt)) && (this.val$other.get(paramAnonymousInt) == paramAnonymousDouble); } })) && (localAbstractIntDoubleMap.forEachPair(new IntDoubleProcedure()
/*     */     {
/*     */ 
/*     */ 
/*     */       public boolean apply(int paramAnonymousInt, double paramAnonymousDouble)
/*     */       {
/*     */ 
/*     */ 
/* 149 */         return (AbstractIntDoubleMap.this.containsKey(paramAnonymousInt)) && (AbstractIntDoubleMap.this.get(paramAnonymousInt) == paramAnonymousDouble);
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
/*     */   public boolean forEachPair(IntDoubleProcedure paramIntDoubleProcedure)
/*     */   {
/* 173 */     forEachKey(new IntProcedure() {
/*     */       private final IntDoubleProcedure val$procedure;
/*     */       
/* 176 */       public boolean apply(int paramAnonymousInt) { return this.val$procedure.apply(paramAnonymousInt, AbstractIntDoubleMap.this.get(paramAnonymousInt)); }
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
/*     */   public abstract double get(int paramInt);
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
/* 199 */     int[] arrayOfInt = new int[1];
/* 200 */     boolean bool = forEachPair(new IntDoubleProcedure() { private final double val$value;
/*     */       private final int[] val$foundKey;
/*     */       
/* 203 */       public boolean apply(int paramAnonymousInt, double paramAnonymousDouble) { int i = this.val$value == paramAnonymousDouble ? 1 : 0;
/* 204 */         if (i != 0) this.val$foundKey[0] = paramAnonymousInt;
/* 205 */         return i == 0;
/*     */       }
/*     */     });
/*     */     
/* 209 */     if (bool) return Integer.MIN_VALUE;
/* 210 */     return arrayOfInt[0];
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
/* 222 */     IntArrayList localIntArrayList = new IntArrayList(size());
/* 223 */     keys(localIntArrayList);
/* 224 */     return localIntArrayList;
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
/* 237 */     paramIntArrayList.clear();
/* 238 */     forEachKey(new IntProcedure() {
/*     */       private final IntArrayList val$list;
/*     */       
/* 241 */       public boolean apply(int paramAnonymousInt) { this.val$list.add(paramAnonymousInt);
/* 242 */         return true;
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
/* 261 */     pairsSortedByValue(paramIntArrayList, new DoubleArrayList(size()));
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
/*     */   public void pairsMatching(IntDoubleProcedure paramIntDoubleProcedure, IntArrayList paramIntArrayList, DoubleArrayList paramDoubleArrayList)
/*     */   {
/* 283 */     paramIntArrayList.clear();
/* 284 */     paramDoubleArrayList.clear();
/*     */     
/* 286 */     forEachPair(new IntDoubleProcedure() {
/*     */       private final IntDoubleProcedure val$condition;
/*     */       
/* 289 */       public boolean apply(int paramAnonymousInt, double paramAnonymousDouble) { if (this.val$condition.apply(paramAnonymousInt, paramAnonymousDouble)) {
/* 290 */           this.val$keyList.add(paramAnonymousInt);
/* 291 */           this.val$valueList.add(paramAnonymousDouble);
/*     */         }
/* 293 */         return true;
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
/*     */   public void pairsSortedByKey(IntArrayList paramIntArrayList, DoubleArrayList paramDoubleArrayList)
/*     */   {
/* 311 */     keys(paramIntArrayList);
/* 312 */     paramIntArrayList.sort();
/* 313 */     paramDoubleArrayList.setSize(paramIntArrayList.size());
/* 314 */     int i = paramIntArrayList.size();
/* 315 */     do { paramDoubleArrayList.setQuick(i, get(paramIntArrayList.getQuick(i)));i--;
/* 314 */     } while (i >= 0);
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
/*     */   private final DoubleArrayList val$valueList;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void pairsSortedByValue(IntArrayList paramIntArrayList, DoubleArrayList paramDoubleArrayList)
/*     */   {
/* 333 */     keys(paramIntArrayList);
/* 334 */     values(paramDoubleArrayList);
/*     */     
/* 336 */     int[] arrayOfInt = paramIntArrayList.elements();
/* 337 */     double[] arrayOfDouble = paramDoubleArrayList.elements();
/* 338 */     Swapper local11 = new Swapper() { private final double[] val$v;
/*     */       private final int[] val$k;
/*     */       
/* 341 */       public void swap(int paramAnonymousInt1, int paramAnonymousInt2) { double d = this.val$v[paramAnonymousInt1];this.val$v[paramAnonymousInt1] = this.val$v[paramAnonymousInt2];this.val$v[paramAnonymousInt2] = d;
/* 342 */         int i = this.val$k[paramAnonymousInt1];this.val$k[paramAnonymousInt1] = this.val$k[paramAnonymousInt2];this.val$k[paramAnonymousInt2] = i;
/*     */       }
/*     */       
/* 345 */     };
/* 346 */     IntComparator local12 = new IntComparator() { private final double[] val$v;
/*     */       
/* 348 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { return this.val$k[paramAnonymousInt1] == this.val$k[paramAnonymousInt2] ? 0 : this.val$k[paramAnonymousInt1] < this.val$k[paramAnonymousInt2] ? -1 : this.val$v[paramAnonymousInt1] > this.val$v[paramAnonymousInt2] ? 1 : this.val$v[paramAnonymousInt1] < this.val$v[paramAnonymousInt2] ? -1 : 1;
/*     */       }
/*     */ 
/* 351 */     };
/* 352 */     GenericSorting.quickSort(0, paramIntArrayList.size(), local12, local11);
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
/*     */   public abstract boolean put(int paramInt, double paramDouble);
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
/* 376 */     IntArrayList localIntArrayList = keys();
/* 377 */     String str = localIntArrayList.toString() + "\n";
/* 378 */     localIntArrayList.sort();
/*     */     
/* 380 */     StringBuffer localStringBuffer = new StringBuffer(str);
/*     */     
/* 382 */     localStringBuffer.append("[");
/* 383 */     int i = localIntArrayList.size() - 1;
/* 384 */     for (int j = 0; j <= i; j++) {
/* 385 */       int k = localIntArrayList.get(j);
/* 386 */       localStringBuffer.append(String.valueOf(k));
/* 387 */       localStringBuffer.append("->");
/* 388 */       localStringBuffer.append(String.valueOf(get(k)));
/* 389 */       if (j < i) localStringBuffer.append(", ");
/*     */     }
/* 391 */     localStringBuffer.append("]");
/* 392 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toStringByValue()
/*     */   {
/* 399 */     IntArrayList localIntArrayList = new IntArrayList();
/* 400 */     keysSortedByValue(localIntArrayList);
/*     */     
/* 402 */     StringBuffer localStringBuffer = new StringBuffer();
/* 403 */     localStringBuffer.append("[");
/* 404 */     int i = localIntArrayList.size() - 1;
/* 405 */     for (int j = 0; j <= i; j++) {
/* 406 */       int k = localIntArrayList.get(j);
/* 407 */       localStringBuffer.append(String.valueOf(k));
/* 408 */       localStringBuffer.append("->");
/* 409 */       localStringBuffer.append(String.valueOf(get(k)));
/* 410 */       if (j < i) localStringBuffer.append(", ");
/*     */     }
/* 412 */     localStringBuffer.append("]");
/* 413 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleArrayList values()
/*     */   {
/* 425 */     DoubleArrayList localDoubleArrayList = new DoubleArrayList(size());
/* 426 */     values(localDoubleArrayList);
/* 427 */     return localDoubleArrayList;
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
/* 440 */     paramDoubleArrayList.clear();
/* 441 */     forEachKey(new IntProcedure() {
/*     */       private final DoubleArrayList val$list;
/*     */       
/* 444 */       public boolean apply(int paramAnonymousInt) { this.val$list.add(AbstractIntDoubleMap.this.get(paramAnonymousInt));
/* 445 */         return true;
/*     */       }
/*     */     });
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/map/AbstractIntDoubleMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */