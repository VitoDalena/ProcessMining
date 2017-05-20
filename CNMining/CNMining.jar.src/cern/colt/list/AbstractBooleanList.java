/*     */ package cern.colt.list;
/*     */ 
/*     */ import cern.colt.Arrays;
/*     */ import cern.colt.function.BooleanProcedure;
/*     */ import cern.jet.random.Uniform;
/*     */ import cern.jet.random.engine.DRand;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractBooleanList
/*     */   extends AbstractList
/*     */ {
/*     */   protected int size;
/*     */   
/*     */   public void add(boolean paramBoolean)
/*     */   {
/*  34 */     beforeInsert(this.size, paramBoolean);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addAllOfFromTo(AbstractBooleanList paramAbstractBooleanList, int paramInt1, int paramInt2)
/*     */   {
/*  45 */     beforeInsertAllOfFromTo(this.size, paramAbstractBooleanList, paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void beforeInsert(int paramInt, boolean paramBoolean)
/*     */   {
/*  57 */     beforeInsertDummies(paramInt, 1);
/*  58 */     set(paramInt, paramBoolean);
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
/*     */   public void beforeInsertAllOfFromTo(int paramInt1, AbstractBooleanList paramAbstractBooleanList, int paramInt2, int paramInt3)
/*     */   {
/*  73 */     int i = paramInt3 - paramInt2 + 1;
/*  74 */     beforeInsertDummies(paramInt1, i);
/*  75 */     replaceFromToWithFrom(paramInt1, paramInt1 + i - 1, paramAbstractBooleanList, paramInt2);
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
/*     */   protected void beforeInsertDummies(int paramInt1, int paramInt2)
/*     */   {
/*  88 */     if ((paramInt1 > this.size) || (paramInt1 < 0))
/*  89 */       throw new IndexOutOfBoundsException("Index: " + paramInt1 + ", Size: " + this.size);
/*  90 */     if (paramInt2 > 0) {
/*  91 */       ensureCapacity(this.size + paramInt2);
/*  92 */       setSizeRaw(this.size + paramInt2);
/*  93 */       replaceFromToWithFrom(paramInt1 + paramInt2, this.size - 1, this, paramInt1);
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
/*     */ 
/*     */ 
/*     */   public int binarySearch(boolean paramBoolean)
/*     */   {
/* 117 */     return binarySearchFromTo(paramBoolean, 0, this.size - 1);
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
/*     */   public int binarySearchFromTo(boolean paramBoolean, int paramInt1, int paramInt2)
/*     */   {
/* 142 */     int i = paramInt1;
/* 143 */     int j = paramInt2;
/* 144 */     int k = toInt(paramBoolean);
/* 145 */     while (i <= j) {
/* 146 */       int m = (i + j) / 2;
/* 147 */       boolean bool = get(m);
/*     */       
/* 149 */       if (toInt(bool) < k) { i = m + 1;
/* 150 */       } else if (toInt(bool) > k) j = m - 1; else
/* 151 */         return m;
/*     */     }
/* 153 */     return -(i + 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 161 */     return partFromTo(0, this.size - 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean contains(boolean paramBoolean)
/*     */   {
/* 169 */     return indexOfFromTo(paramBoolean, 0, this.size - 1) >= 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void delete(boolean paramBoolean)
/*     */   {
/* 178 */     int i = indexOfFromTo(paramBoolean, 0, this.size - 1);
/* 179 */     if (i >= 0) { remove(i);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean[] elements()
/*     */   {
/* 190 */     boolean[] arrayOfBoolean = new boolean[this.size];
/* 191 */     int i = this.size; do { arrayOfBoolean[i] = getQuick(i);i--; } while (i >= 0);
/* 192 */     return arrayOfBoolean;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractBooleanList elements(boolean[] paramArrayOfBoolean)
/*     */   {
/* 204 */     clear();
/* 205 */     addAllOfFromTo(new BooleanArrayList(paramArrayOfBoolean), 0, paramArrayOfBoolean.length - 1);
/* 206 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract void ensureCapacity(int paramInt);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 226 */     if (!(paramObject instanceof AbstractBooleanList)) return false;
/* 227 */     if (this == paramObject) return true;
/* 228 */     if (paramObject == null) return false;
/* 229 */     AbstractBooleanList localAbstractBooleanList = (AbstractBooleanList)paramObject;
/* 230 */     if (size() != localAbstractBooleanList.size()) { return false;
/*     */     }
/* 232 */     int i = size();
/* 233 */     do { if (getQuick(i) != localAbstractBooleanList.getQuick(i)) return false;
/* 232 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/* 235 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void fillFromToWith(int paramInt1, int paramInt2, boolean paramBoolean)
/*     */   {
/* 245 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/* 246 */     for (int i = paramInt1; i <= paramInt2; setQuick(i++, paramBoolean)) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean forEach(BooleanProcedure paramBooleanProcedure)
/*     */   {
/* 255 */     for (int i = 0; i < this.size;) if (!paramBooleanProcedure.apply(get(i++))) return false;
/* 256 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean get(int paramInt)
/*     */   {
/* 266 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 267 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/* 268 */     return getQuick(paramInt);
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
/*     */   protected abstract boolean getQuick(int paramInt);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int indexOf(boolean paramBoolean)
/*     */   {
/* 290 */     return indexOfFromTo(paramBoolean, 0, this.size - 1);
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
/*     */   public int indexOfFromTo(boolean paramBoolean, int paramInt1, int paramInt2)
/*     */   {
/* 305 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 307 */     for (int i = paramInt1; i <= paramInt2; i++) {
/* 308 */       if (paramBoolean == getQuick(i)) return i;
/*     */     }
/* 310 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int lastIndexOf(boolean paramBoolean)
/*     */   {
/* 320 */     return lastIndexOfFromTo(paramBoolean, 0, this.size - 1);
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
/*     */   public int lastIndexOfFromTo(boolean paramBoolean, int paramInt1, int paramInt2)
/*     */   {
/* 335 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, size());
/*     */     
/* 337 */     for (int i = paramInt2; i >= paramInt1; i--) {
/* 338 */       if (paramBoolean == getQuick(i)) return i;
/*     */     }
/* 340 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractBooleanList partFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 350 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 352 */     int i = paramInt2 - paramInt1 + 1;
/* 353 */     BooleanArrayList localBooleanArrayList = new BooleanArrayList(i);
/* 354 */     localBooleanArrayList.addAllOfFromTo(this, paramInt1, paramInt2);
/* 355 */     return localBooleanArrayList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean removeAll(AbstractBooleanList paramAbstractBooleanList)
/*     */   {
/* 365 */     if (paramAbstractBooleanList.size() == 0) return false;
/* 366 */     int i = paramAbstractBooleanList.size() - 1;
/* 367 */     int j = 0;
/*     */     
/* 369 */     for (int k = 0; k < this.size; k++) {
/* 370 */       if (paramAbstractBooleanList.indexOfFromTo(getQuick(k), 0, i) < 0) { setQuick(j++, getQuick(k));
/*     */       }
/*     */     }
/* 373 */     boolean bool = j != this.size;
/* 374 */     setSize(j);
/* 375 */     return bool;
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
/*     */   public void removeFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 388 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/* 389 */     int i = this.size - paramInt2 - 1;
/* 390 */     if (i > 0) {
/* 391 */       replaceFromToWithFrom(paramInt1, paramInt1 - 1 + i, this, paramInt2 + 1);
/*     */     }
/*     */     
/* 394 */     int j = paramInt2 - paramInt1 + 1;
/* 395 */     if (j > 0) { setSizeRaw(this.size - j);
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
/*     */   public void replaceFromToWithFrom(int paramInt1, int paramInt2, AbstractBooleanList paramAbstractBooleanList, int paramInt3)
/*     */   {
/* 408 */     int i = paramInt2 - paramInt1 + 1;
/* 409 */     if (i > 0) {
/* 410 */       AbstractList.checkRangeFromTo(paramInt1, paramInt2, size());
/* 411 */       AbstractList.checkRangeFromTo(paramInt3, paramInt3 + i - 1, paramAbstractBooleanList.size());
/*     */       
/*     */ 
/* 414 */       if (paramInt1 <= paramInt3) {
/* 415 */         do { setQuick(paramInt1++, paramAbstractBooleanList.getQuick(paramInt3++));i--; } while (i >= 0);
/*     */       }
/*     */       else {
/* 418 */         int j = paramInt3 + i - 1;
/* 419 */         do { setQuick(paramInt2--, paramAbstractBooleanList.getQuick(j--));i--; } while (i >= 0);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void replaceFromToWithFromTo(int paramInt1, int paramInt2, AbstractBooleanList paramAbstractBooleanList, int paramInt3, int paramInt4)
/*     */   {
/* 469 */     if (paramInt3 > paramInt4) {
/* 470 */       throw new IndexOutOfBoundsException("otherFrom: " + paramInt3 + ", otherTo: " + paramInt4);
/*     */     }
/*     */     
/* 473 */     if ((this == paramAbstractBooleanList) && (paramInt2 - paramInt1 != paramInt4 - paramInt3)) {
/* 474 */       replaceFromToWithFromTo(paramInt1, paramInt2, partFromTo(paramInt3, paramInt4), 0, paramInt4 - paramInt3);
/* 475 */       return;
/*     */     }
/*     */     
/* 478 */     int i = paramInt4 - paramInt3 + 1;
/* 479 */     int j = i;
/* 480 */     int k = paramInt1 - 1;
/*     */     
/* 482 */     if (paramInt2 >= paramInt1) {
/* 483 */       j -= paramInt2 - paramInt1 + 1;
/* 484 */       k = paramInt2;
/*     */     }
/*     */     
/* 487 */     if (j > 0) {
/* 488 */       beforeInsertDummies(k + 1, j);
/*     */ 
/*     */     }
/* 491 */     else if (j < 0) {
/* 492 */       removeFromTo(k + j, k - 1);
/*     */     }
/*     */     
/*     */ 
/* 496 */     if (i > 0) {
/* 497 */       replaceFromToWithFrom(paramInt1, paramInt1 + i - 1, paramAbstractBooleanList, paramInt3);
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
/*     */   public void replaceFromWith(int paramInt, Collection paramCollection)
/*     */   {
/* 510 */     AbstractList.checkRange(paramInt, size());
/* 511 */     Iterator localIterator = paramCollection.iterator();
/* 512 */     int i = paramInt;
/* 513 */     int j = Math.min(size() - paramInt, paramCollection.size());
/* 514 */     for (int k = 0; k < j; k++) {
/* 515 */       set(i++, ((Boolean)localIterator.next()).booleanValue());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean retainAll(AbstractBooleanList paramAbstractBooleanList)
/*     */   {
/* 525 */     if (paramAbstractBooleanList.size() == 0) {
/* 526 */       if (this.size == 0) return false;
/* 527 */       setSize(0);
/* 528 */       return true;
/*     */     }
/*     */     
/* 531 */     int i = paramAbstractBooleanList.size() - 1;
/* 532 */     int j = 0;
/* 533 */     for (int k = 0; k < this.size; k++) {
/* 534 */       if (paramAbstractBooleanList.indexOfFromTo(getQuick(k), 0, i) >= 0) { setQuick(j++, getQuick(k));
/*     */       }
/*     */     }
/* 537 */     boolean bool = j != this.size;
/* 538 */     setSize(j);
/* 539 */     return bool;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reverse()
/*     */   {
/* 547 */     int i = size() / 2;
/* 548 */     int j = size() - 1;
/*     */     
/* 550 */     for (int k = 0; k < i;) {
/* 551 */       boolean bool = getQuick(k);
/* 552 */       setQuick(k++, getQuick(j));
/* 553 */       setQuick(j--, bool);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void set(int paramInt, boolean paramBoolean)
/*     */   {
/* 564 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 565 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/* 566 */     setQuick(paramInt, paramBoolean);
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
/*     */   protected abstract void setQuick(int paramInt, boolean paramBoolean);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setSizeRaw(int paramInt)
/*     */   {
/* 597 */     this.size = paramInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void shuffleFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 606 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, size());
/*     */     
/* 608 */     Uniform localUniform = new Uniform(new DRand(new Date()));
/* 609 */     for (int i = paramInt1; i < paramInt2; i++) {
/* 610 */       int j = localUniform.nextIntFromTo(i, paramInt2);
/*     */       
/*     */ 
/* 613 */       boolean bool = getQuick(j);
/* 614 */       setQuick(j, getQuick(i));
/* 615 */       setQuick(i, bool);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 624 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public AbstractBooleanList times(int paramInt)
/*     */   {
/* 631 */     BooleanArrayList localBooleanArrayList = new BooleanArrayList(paramInt * size());
/* 632 */     int i = paramInt;
/* 633 */     do { localBooleanArrayList.addAllOfFromTo(this, 0, size() - 1);i--;
/* 632 */     } while (i >= 0);
/*     */     
/*     */ 
/* 635 */     return localBooleanArrayList;
/*     */   }
/*     */   
/*     */ 
/*     */   protected static int toInt(boolean paramBoolean)
/*     */   {
/* 641 */     return paramBoolean ? 1 : 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public ArrayList toList()
/*     */   {
/* 647 */     int i = size();
/* 648 */     ArrayList localArrayList = new ArrayList(i);
/* 649 */     for (int j = 0; j < i; j++) localArrayList.add(new Boolean(get(j)));
/* 650 */     return localArrayList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 657 */     return Arrays.toString(partFromTo(0, size() - 1).elements());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/list/AbstractBooleanList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */