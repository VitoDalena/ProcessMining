/*     */ package cern.colt.map;
/*     */ 
/*     */ import cern.colt.GenericSorting;
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.Swapper;
/*     */ import cern.colt.function.DoubleIntProcedure;
/*     */ import cern.colt.function.DoubleProcedure;
/*     */ import cern.colt.function.IntComparator;
/*     */ import cern.colt.list.AbstractDoubleList;
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
/*     */ 
/*     */ public abstract class AbstractDoubleIntMap
/*     */   extends AbstractMap
/*     */ {
/*     */   public boolean containsKey(double paramDouble)
/*     */   {
/*  38 */     !forEachKey(new DoubleProcedure() {
/*     */       private final double val$key;
/*     */       
/*  41 */       public boolean apply(double paramAnonymousDouble) { return this.val$key != paramAnonymousDouble; }
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
/*  52 */     !forEachPair(new DoubleIntProcedure() {
/*     */       private final int val$value;
/*     */       
/*  55 */       public boolean apply(double paramAnonymousDouble, int paramAnonymousInt) { return this.val$value != paramAnonymousInt; }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractDoubleIntMap copy()
/*     */   {
/*  66 */     return (AbstractDoubleIntMap)clone();
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
/* 102 */     if (!(paramObject instanceof AbstractDoubleIntMap)) return false;
/* 103 */     AbstractDoubleIntMap localAbstractDoubleIntMap = (AbstractDoubleIntMap)paramObject;
/* 104 */     if (localAbstractDoubleIntMap.size() != size()) { return false;
/*     */     }
/* 106 */     (forEachPair(new DoubleIntProcedure() {
/*     */       private final AbstractDoubleIntMap val$other;
/*     */       
/*     */ 
/* 110 */       public boolean apply(double paramAnonymousDouble, int paramAnonymousInt) { return (this.val$other.containsKey(paramAnonymousDouble)) && (this.val$other.get(paramAnonymousDouble) == paramAnonymousInt); } })) && (localAbstractDoubleIntMap.forEachPair(new DoubleIntProcedure()
/*     */     {
/*     */ 
/*     */ 
/*     */       public boolean apply(double paramAnonymousDouble, int paramAnonymousInt)
/*     */       {
/*     */ 
/*     */ 
/* 118 */         return (AbstractDoubleIntMap.this.containsKey(paramAnonymousDouble)) && (AbstractDoubleIntMap.this.get(paramAnonymousDouble) == paramAnonymousInt);
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
/*     */   public abstract boolean forEachKey(DoubleProcedure paramDoubleProcedure);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean forEachPair(DoubleIntProcedure paramDoubleIntProcedure)
/*     */   {
/* 142 */     forEachKey(new DoubleProcedure() {
/*     */       private final DoubleIntProcedure val$procedure;
/*     */       
/* 145 */       public boolean apply(double paramAnonymousDouble) { return this.val$procedure.apply(paramAnonymousDouble, AbstractDoubleIntMap.this.get(paramAnonymousDouble)); }
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
/*     */   public abstract int get(double paramDouble);
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
/* 168 */     double[] arrayOfDouble = new double[1];
/* 169 */     boolean bool = forEachPair(new DoubleIntProcedure() { private final int val$value;
/*     */       private final double[] val$foundKey;
/*     */       
/* 172 */       public boolean apply(double paramAnonymousDouble, int paramAnonymousInt) { int i = this.val$value == paramAnonymousInt ? 1 : 0;
/* 173 */         if (i != 0) this.val$foundKey[0] = paramAnonymousDouble;
/* 174 */         return i == 0;
/*     */       }
/*     */     });
/*     */     
/* 178 */     if (bool) return NaN.0D;
/* 179 */     return arrayOfDouble[0];
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
/*     */   public DoubleArrayList keys()
/*     */   {
/* 192 */     DoubleArrayList localDoubleArrayList = new DoubleArrayList(size());
/* 193 */     keys(localDoubleArrayList);
/* 194 */     return localDoubleArrayList;
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
/* 207 */     paramDoubleArrayList.clear();
/* 208 */     forEachKey(new DoubleProcedure() {
/*     */       private final DoubleArrayList val$list;
/*     */       
/* 211 */       public boolean apply(double paramAnonymousDouble) { this.val$list.add(paramAnonymousDouble);
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
/*     */   public void keysSortedByValue(DoubleArrayList paramDoubleArrayList)
/*     */   {
/* 231 */     pairsSortedByValue(paramDoubleArrayList, new IntArrayList(size()));
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
/*     */   public void pairsMatching(DoubleIntProcedure paramDoubleIntProcedure, DoubleArrayList paramDoubleArrayList, IntArrayList paramIntArrayList)
/*     */   {
/* 253 */     paramDoubleArrayList.clear();
/* 254 */     paramIntArrayList.clear();
/*     */     
/* 256 */     forEachPair(new DoubleIntProcedure() {
/*     */       private final DoubleIntProcedure val$condition;
/*     */       
/* 259 */       public boolean apply(double paramAnonymousDouble, int paramAnonymousInt) { if (this.val$condition.apply(paramAnonymousDouble, paramAnonymousInt)) {
/* 260 */           this.val$keyList.add(paramAnonymousDouble);
/* 261 */           this.val$valueList.add(paramAnonymousInt);
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
/*     */ 
/*     */   private final DoubleArrayList val$keyList;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final IntArrayList val$valueList;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void pairsSortedByKey(DoubleArrayList paramDoubleArrayList, IntArrayList paramIntArrayList)
/*     */   {
/* 308 */     keys(paramDoubleArrayList);
/* 309 */     paramDoubleArrayList.sort();
/* 310 */     paramIntArrayList.setSize(paramDoubleArrayList.size());
/* 311 */     int i = paramDoubleArrayList.size();
/* 312 */     do { paramIntArrayList.setQuick(i, get(paramDoubleArrayList.getQuick(i)));i--;
/* 311 */     } while (i >= 0);
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
/*     */   public void pairsSortedByValue(DoubleArrayList paramDoubleArrayList, IntArrayList paramIntArrayList)
/*     */   {
/* 332 */     keys(paramDoubleArrayList);
/* 333 */     values(paramIntArrayList);
/*     */     
/* 335 */     double[] arrayOfDouble = paramDoubleArrayList.elements();
/* 336 */     int[] arrayOfInt = paramIntArrayList.elements();
/* 337 */     Swapper local9 = new Swapper() { private final int[] val$v;
/*     */       private final double[] val$k;
/*     */       
/* 340 */       public void swap(int paramAnonymousInt1, int paramAnonymousInt2) { int i = this.val$v[paramAnonymousInt1];this.val$v[paramAnonymousInt1] = this.val$v[paramAnonymousInt2];this.val$v[paramAnonymousInt2] = i;
/* 341 */         double d = this.val$k[paramAnonymousInt1];this.val$k[paramAnonymousInt1] = this.val$k[paramAnonymousInt2];this.val$k[paramAnonymousInt2] = d;
/*     */       }
/*     */       
/* 344 */     };
/* 345 */     IntComparator local10 = new IntComparator() { private final int[] val$v;
/*     */       
/* 347 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { return this.val$k[paramAnonymousInt1] == this.val$k[paramAnonymousInt2] ? 0 : this.val$k[paramAnonymousInt1] < this.val$k[paramAnonymousInt2] ? -1 : this.val$v[paramAnonymousInt1] > this.val$v[paramAnonymousInt2] ? 1 : this.val$v[paramAnonymousInt1] < this.val$v[paramAnonymousInt2] ? -1 : 1;
/*     */       }
/*     */       
/*     */ 
/* 351 */     };
/* 352 */     GenericSorting.quickSort(0, paramDoubleArrayList.size(), local10, local9);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final double[] val$k;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract boolean put(double paramDouble, int paramInt);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract boolean removeKey(double paramDouble);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 377 */     DoubleArrayList localDoubleArrayList = keys();
/* 378 */     localDoubleArrayList.sort();
/*     */     
/* 380 */     StringBuffer localStringBuffer = new StringBuffer();
/* 381 */     localStringBuffer.append("[");
/* 382 */     int i = localDoubleArrayList.size() - 1;
/* 383 */     for (int j = 0; j <= i; j++) {
/* 384 */       double d = localDoubleArrayList.get(j);
/* 385 */       localStringBuffer.append(String.valueOf(d));
/* 386 */       localStringBuffer.append("->");
/* 387 */       localStringBuffer.append(String.valueOf(get(d)));
/* 388 */       if (j < i) localStringBuffer.append(", ");
/*     */     }
/* 390 */     localStringBuffer.append("]");
/* 391 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toStringByValue()
/*     */   {
/* 398 */     DoubleArrayList localDoubleArrayList = new DoubleArrayList();
/* 399 */     keysSortedByValue(localDoubleArrayList);
/*     */     
/* 401 */     StringBuffer localStringBuffer = new StringBuffer();
/* 402 */     localStringBuffer.append("[");
/* 403 */     int i = localDoubleArrayList.size() - 1;
/* 404 */     for (int j = 0; j <= i; j++) {
/* 405 */       double d = localDoubleArrayList.get(j);
/* 406 */       localStringBuffer.append(String.valueOf(d));
/* 407 */       localStringBuffer.append("->");
/* 408 */       localStringBuffer.append(String.valueOf(get(d)));
/* 409 */       if (j < i) localStringBuffer.append(", ");
/*     */     }
/* 411 */     localStringBuffer.append("]");
/* 412 */     return localStringBuffer.toString();
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
/* 424 */     IntArrayList localIntArrayList = new IntArrayList(size());
/* 425 */     values(localIntArrayList);
/* 426 */     return localIntArrayList;
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
/* 439 */     paramIntArrayList.clear();
/* 440 */     forEachKey(new DoubleProcedure() {
/*     */       private final IntArrayList val$list;
/*     */       
/* 443 */       public boolean apply(double paramAnonymousDouble) { this.val$list.add(AbstractDoubleIntMap.this.get(paramAnonymousDouble));
/* 444 */         return true;
/*     */       }
/*     */     });
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/map/AbstractDoubleIntMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */