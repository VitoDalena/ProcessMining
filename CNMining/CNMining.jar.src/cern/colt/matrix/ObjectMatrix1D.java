/*     */ package cern.colt.matrix;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.function.ObjectFunction;
/*     */ import cern.colt.function.ObjectObjectFunction;
/*     */ import cern.colt.function.ObjectProcedure;
/*     */ import cern.colt.list.AbstractList;
/*     */ import cern.colt.list.IntArrayList;
/*     */ import cern.colt.list.ObjectArrayList;
/*     */ import cern.colt.matrix.impl.AbstractMatrix1D;
/*     */ import cern.colt.matrix.objectalgo.Formatter;
/*     */ import cern.colt.matrix.objectalgo.Sorting;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ObjectMatrix1D
/*     */   extends AbstractMatrix1D
/*     */ {
/*     */   public Object aggregate(ObjectObjectFunction paramObjectObjectFunction, ObjectFunction paramObjectFunction)
/*     */   {
/*  51 */     if (this.size == 0) return null;
/*  52 */     Object localObject = paramObjectFunction.apply(getQuick(this.size - 1));
/*  53 */     int i = this.size - 1;
/*  54 */     do { localObject = paramObjectObjectFunction.apply(localObject, paramObjectFunction.apply(getQuick(i)));i--;
/*  53 */     } while (i >= 0);
/*     */     
/*     */ 
/*  56 */     return localObject;
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
/*     */   public Object aggregate(ObjectMatrix1D paramObjectMatrix1D, ObjectObjectFunction paramObjectObjectFunction1, ObjectObjectFunction paramObjectObjectFunction2)
/*     */   {
/*  84 */     checkSize(paramObjectMatrix1D);
/*  85 */     if (this.size == 0) return null;
/*  86 */     Object localObject = paramObjectObjectFunction2.apply(getQuick(this.size - 1), paramObjectMatrix1D.getQuick(this.size - 1));
/*  87 */     int i = this.size - 1;
/*  88 */     do { localObject = paramObjectObjectFunction1.apply(localObject, paramObjectObjectFunction2.apply(getQuick(i), paramObjectMatrix1D.getQuick(i)));i--;
/*  87 */     } while (i >= 0);
/*     */     
/*     */ 
/*  90 */     return localObject;
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
/*     */   public ObjectMatrix1D assign(Object[] paramArrayOfObject)
/*     */   {
/* 103 */     if (paramArrayOfObject.length != this.size) throw new IllegalArgumentException("Must have same number of cells: length=" + paramArrayOfObject.length + ", size()=" + size());
/* 104 */     int i = this.size;
/* 105 */     do { setQuick(i, paramArrayOfObject[i]);i--;
/* 104 */     } while (i >= 0);
/*     */     
/*     */ 
/* 107 */     return this;
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
/*     */   public ObjectMatrix1D assign(ObjectFunction paramObjectFunction)
/*     */   {
/* 128 */     int i = this.size;
/* 129 */     do { setQuick(i, paramObjectFunction.apply(getQuick(i)));i--;
/* 128 */     } while (i >= 0);
/*     */     
/*     */ 
/* 131 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix1D assign(ObjectMatrix1D paramObjectMatrix1D)
/*     */   {
/* 143 */     if (paramObjectMatrix1D == this) return this;
/* 144 */     checkSize(paramObjectMatrix1D);
/* 145 */     if (haveSharedCells(paramObjectMatrix1D)) { paramObjectMatrix1D = paramObjectMatrix1D.copy();
/*     */     }
/* 147 */     int i = this.size;
/* 148 */     do { setQuick(i, paramObjectMatrix1D.getQuick(i));i--;
/* 147 */     } while (i >= 0);
/*     */     
/*     */ 
/* 150 */     return this;
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
/*     */   public ObjectMatrix1D assign(ObjectMatrix1D paramObjectMatrix1D, ObjectObjectFunction paramObjectObjectFunction)
/*     */   {
/* 174 */     checkSize(paramObjectMatrix1D);
/* 175 */     int i = this.size;
/* 176 */     do { setQuick(i, paramObjectObjectFunction.apply(getQuick(i), paramObjectMatrix1D.getQuick(i)));i--;
/* 175 */     } while (i >= 0);
/*     */     
/*     */ 
/* 178 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix1D assign(Object paramObject)
/*     */   {
/* 186 */     int i = this.size;
/* 187 */     do { setQuick(i, paramObject);i--;
/* 186 */     } while (i >= 0);
/*     */     
/*     */ 
/* 189 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public int cardinality()
/*     */   {
/* 195 */     int i = 0;
/* 196 */     int j = this.size;
/* 197 */     do { if (getQuick(j) != null) i++;
/* 196 */       j--; } while (j >= 0);
/*     */     
/*     */ 
/* 199 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix1D copy()
/*     */   {
/* 210 */     ObjectMatrix1D localObjectMatrix1D = like();
/* 211 */     localObjectMatrix1D.assign(this);
/* 212 */     return localObjectMatrix1D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 222 */     return equals(paramObject, true);
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
/*     */   public boolean equals(Object paramObject, boolean paramBoolean)
/*     */   {
/* 240 */     if (!(paramObject instanceof ObjectMatrix1D)) return false;
/* 241 */     if (this == paramObject) return true;
/* 242 */     if (paramObject == null) return false;
/* 243 */     ObjectMatrix1D localObjectMatrix1D = (ObjectMatrix1D)paramObject;
/* 244 */     if (this.size != localObjectMatrix1D.size()) return false;
/*     */     int i;
/* 246 */     if (!paramBoolean) {
/* 247 */       i = this.size;
/* 248 */       do { if (getQuick(i) != localObjectMatrix1D.getQuick(i)) return false;
/* 247 */         i--; } while (i >= 0);
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 252 */       i = this.size;
/* 253 */       do { if (!(getQuick(i) == null ? false : localObjectMatrix1D.getQuick(i) == null ? true : getQuick(i).equals(localObjectMatrix1D.getQuick(i)))) return false;
/* 252 */         i--; } while (i >= 0);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 257 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object get(int paramInt)
/*     */   {
/* 268 */     if ((paramInt < 0) || (paramInt >= this.size)) checkIndex(paramInt);
/* 269 */     return getQuick(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected ObjectMatrix1D getContent()
/*     */   {
/* 276 */     return this;
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
/*     */   public void getNonZeros(IntArrayList paramIntArrayList, ObjectArrayList paramObjectArrayList)
/*     */   {
/* 302 */     int i = paramIntArrayList != null ? 1 : 0;
/* 303 */     int j = paramObjectArrayList != null ? 1 : 0;
/* 304 */     if (i != 0) paramIntArrayList.clear();
/* 305 */     if (j != 0) paramObjectArrayList.clear();
/* 306 */     int k = this.size;
/* 307 */     for (int m = 0; m < k; m++) {
/* 308 */       Object localObject = getQuick(m);
/* 309 */       if (localObject != null) {
/* 310 */         if (i != 0) paramIntArrayList.add(m);
/* 311 */         if (j != 0) { paramObjectArrayList.add(localObject);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract Object getQuick(int paramInt);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean haveSharedCells(ObjectMatrix1D paramObjectMatrix1D)
/*     */   {
/* 330 */     if (paramObjectMatrix1D == null) return false;
/* 331 */     if (this == paramObjectMatrix1D) return true;
/* 332 */     return getContent().haveSharedCellsRaw(paramObjectMatrix1D.getContent());
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean haveSharedCellsRaw(ObjectMatrix1D paramObjectMatrix1D)
/*     */   {
/* 338 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix1D like()
/*     */   {
/* 349 */     return like(this.size);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract ObjectMatrix1D like(int paramInt);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract ObjectMatrix2D like2D(int paramInt1, int paramInt2);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void set(int paramInt, Object paramObject)
/*     */   {
/* 379 */     if ((paramInt < 0) || (paramInt >= this.size)) checkIndex(paramInt);
/* 380 */     setQuick(paramInt, paramObject);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract void setQuick(int paramInt, Object paramObject);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void swap(ObjectMatrix1D paramObjectMatrix1D)
/*     */   {
/* 398 */     checkSize(paramObjectMatrix1D);
/* 399 */     int i = this.size;
/* 400 */     do { Object localObject = getQuick(i);
/* 401 */       setQuick(i, paramObjectMatrix1D.getQuick(i));
/* 402 */       paramObjectMatrix1D.setQuick(i, localObject);i--;
/* 399 */     } while (i >= 0);
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
/*     */   public Object[] toArray()
/*     */   {
/* 416 */     Object[] arrayOfObject = new Object[this.size];
/* 417 */     toArray(arrayOfObject);
/* 418 */     return arrayOfObject;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void toArray(Object[] paramArrayOfObject)
/*     */   {
/* 430 */     if (paramArrayOfObject.length < this.size) throw new IllegalArgumentException("values too small");
/* 431 */     int i = this.size;
/* 432 */     do { paramArrayOfObject[i] = getQuick(i);i--;
/* 431 */     } while (i >= 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 440 */     return new Formatter().toString(this);
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
/*     */   protected ObjectMatrix1D view()
/*     */   {
/* 454 */     return (ObjectMatrix1D)clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix1D viewFlip()
/*     */   {
/* 464 */     return (ObjectMatrix1D)view().vFlip();
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
/*     */   public ObjectMatrix1D viewPart(int paramInt1, int paramInt2)
/*     */   {
/* 488 */     return (ObjectMatrix1D)view().vPart(paramInt1, paramInt2);
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
/*     */   public ObjectMatrix1D viewSelection(int[] paramArrayOfInt)
/*     */   {
/* 512 */     if (paramArrayOfInt == null) {
/* 513 */       paramArrayOfInt = new int[this.size];
/* 514 */       int i = this.size; do { paramArrayOfInt[i] = i;i--; } while (i >= 0);
/*     */     }
/*     */     
/* 517 */     checkIndexes(paramArrayOfInt);
/* 518 */     int[] arrayOfInt = new int[paramArrayOfInt.length];
/* 519 */     int j = paramArrayOfInt.length;
/* 520 */     do { arrayOfInt[j] = index(paramArrayOfInt[j]);j--;
/* 519 */     } while (j >= 0);
/*     */     
/*     */ 
/* 522 */     return viewSelectionLike(arrayOfInt);
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
/*     */   public ObjectMatrix1D viewSelection(ObjectProcedure paramObjectProcedure)
/*     */   {
/* 548 */     IntArrayList localIntArrayList = new IntArrayList();
/* 549 */     for (int i = 0; i < this.size; i++) {
/* 550 */       if (paramObjectProcedure.apply(getQuick(i))) localIntArrayList.add(i);
/*     */     }
/* 552 */     localIntArrayList.trimToSize();
/* 553 */     return viewSelection(localIntArrayList.elements());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract ObjectMatrix1D viewSelectionLike(int[] paramArrayOfInt);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix1D viewSorted()
/*     */   {
/* 570 */     return Sorting.mergeSort.sort(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix1D viewStrides(int paramInt)
/*     */   {
/* 582 */     return (ObjectMatrix1D)view().vStrides(paramInt);
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
/*     */   private boolean xforEach(ObjectProcedure paramObjectProcedure)
/*     */   {
/* 600 */     int i = this.size;
/* 601 */     do { if (!paramObjectProcedure.apply(getQuick(i))) return false;
/* 600 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/* 603 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/ObjectMatrix1D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */