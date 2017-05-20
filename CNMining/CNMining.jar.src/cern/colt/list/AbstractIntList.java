/*     */ package cern.colt.list;
/*     */ 
/*     */ import cern.colt.Sorting;
/*     */ import cern.colt.buffer.IntBufferConsumer;
/*     */ import cern.colt.function.IntComparator;
/*     */ import cern.colt.function.IntProcedure;
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
/*     */ public abstract class AbstractIntList
/*     */   extends AbstractList
/*     */   implements IntBufferConsumer
/*     */ {
/*     */   protected int size;
/*     */   
/*     */   public void add(int paramInt)
/*     */   {
/*  34 */     beforeInsert(this.size, paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void addAllOf(IntArrayList paramIntArrayList)
/*     */   {
/*  41 */     addAllOfFromTo(paramIntArrayList, 0, paramIntArrayList.size() - 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addAllOfFromTo(AbstractIntList paramAbstractIntList, int paramInt1, int paramInt2)
/*     */   {
/*  52 */     beforeInsertAllOfFromTo(this.size, paramAbstractIntList, paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void beforeInsert(int paramInt1, int paramInt2)
/*     */   {
/*  64 */     beforeInsertDummies(paramInt1, 1);
/*  65 */     set(paramInt1, paramInt2);
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
/*     */   public void beforeInsertAllOfFromTo(int paramInt1, AbstractIntList paramAbstractIntList, int paramInt2, int paramInt3)
/*     */   {
/*  80 */     int i = paramInt3 - paramInt2 + 1;
/*  81 */     beforeInsertDummies(paramInt1, i);
/*  82 */     replaceFromToWithFrom(paramInt1, paramInt1 + i - 1, paramAbstractIntList, paramInt2);
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
/*  95 */     if ((paramInt1 > this.size) || (paramInt1 < 0))
/*  96 */       throw new IndexOutOfBoundsException("Index: " + paramInt1 + ", Size: " + this.size);
/*  97 */     if (paramInt2 > 0) {
/*  98 */       ensureCapacity(this.size + paramInt2);
/*  99 */       setSizeRaw(this.size + paramInt2);
/* 100 */       replaceFromToWithFrom(paramInt1 + paramInt2, this.size - 1, this, paramInt1);
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
/*     */   public int binarySearch(int paramInt)
/*     */   {
/* 124 */     return binarySearchFromTo(paramInt, 0, this.size - 1);
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
/*     */   public int binarySearchFromTo(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 149 */     int i = paramInt2;
/* 150 */     int j = paramInt3;
/* 151 */     while (i <= j) {
/* 152 */       int k = (i + j) / 2;
/* 153 */       int m = get(k);
/*     */       
/* 155 */       if (m < paramInt1) { i = k + 1;
/* 156 */       } else if (m > paramInt1) j = k - 1; else
/* 157 */         return k;
/*     */     }
/* 159 */     return -(i + 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 167 */     return partFromTo(0, this.size - 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean contains(int paramInt)
/*     */   {
/* 175 */     return indexOfFromTo(paramInt, 0, this.size - 1) >= 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void delete(int paramInt)
/*     */   {
/* 184 */     int i = indexOfFromTo(paramInt, 0, this.size - 1);
/* 185 */     if (i >= 0) { remove(i);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int[] elements()
/*     */   {
/* 196 */     int[] arrayOfInt = new int[this.size];
/* 197 */     int i = this.size; do { arrayOfInt[i] = getQuick(i);i--; } while (i >= 0);
/* 198 */     return arrayOfInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractIntList elements(int[] paramArrayOfInt)
/*     */   {
/* 210 */     clear();
/* 211 */     addAllOfFromTo(new IntArrayList(paramArrayOfInt), 0, paramArrayOfInt.length - 1);
/* 212 */     return this;
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
/* 232 */     if (!(paramObject instanceof AbstractIntList)) return false;
/* 233 */     if (this == paramObject) return true;
/* 234 */     if (paramObject == null) return false;
/* 235 */     AbstractIntList localAbstractIntList = (AbstractIntList)paramObject;
/* 236 */     if (size() != localAbstractIntList.size()) { return false;
/*     */     }
/* 238 */     int i = size();
/* 239 */     do { if (getQuick(i) != localAbstractIntList.getQuick(i)) return false;
/* 238 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/* 241 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void fillFromToWith(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 251 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/* 252 */     for (int i = paramInt1; i <= paramInt2; setQuick(i++, paramInt3)) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean forEach(IntProcedure paramIntProcedure)
/*     */   {
/* 261 */     for (int i = 0; i < this.size;) if (!paramIntProcedure.apply(get(i++))) return false;
/* 262 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int get(int paramInt)
/*     */   {
/* 272 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 273 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/* 274 */     return getQuick(paramInt);
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
/*     */   protected abstract int getQuick(int paramInt);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int indexOf(int paramInt)
/*     */   {
/* 296 */     return indexOfFromTo(paramInt, 0, this.size - 1);
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
/*     */   public int indexOfFromTo(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 311 */     AbstractList.checkRangeFromTo(paramInt2, paramInt3, this.size);
/*     */     
/* 313 */     for (int i = paramInt2; i <= paramInt3; i++) {
/* 314 */       if (paramInt1 == getQuick(i)) return i;
/*     */     }
/* 316 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int lastIndexOf(int paramInt)
/*     */   {
/* 326 */     return lastIndexOfFromTo(paramInt, 0, this.size - 1);
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
/*     */   public int lastIndexOfFromTo(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 341 */     AbstractList.checkRangeFromTo(paramInt2, paramInt3, size());
/*     */     
/* 343 */     for (int i = paramInt3; i >= paramInt2; i--) {
/* 344 */       if (paramInt1 == getQuick(i)) return i;
/*     */     }
/* 346 */     return -1;
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
/*     */   public void mergeSortFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 365 */     int i = size();
/* 366 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, i);
/*     */     
/* 368 */     int[] arrayOfInt = elements();
/* 369 */     Sorting.mergeSort(arrayOfInt, paramInt1, paramInt2 + 1);
/* 370 */     elements(arrayOfInt);
/* 371 */     setSizeRaw(i);
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
/*     */   public void mergeSortFromTo(int paramInt1, int paramInt2, IntComparator paramIntComparator)
/*     */   {
/* 403 */     int i = size();
/* 404 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, i);
/*     */     
/* 406 */     int[] arrayOfInt = elements();
/* 407 */     Sorting.mergeSort(arrayOfInt, paramInt1, paramInt2 + 1, paramIntComparator);
/* 408 */     elements(arrayOfInt);
/* 409 */     setSizeRaw(i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractIntList partFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 419 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 421 */     int i = paramInt2 - paramInt1 + 1;
/* 422 */     IntArrayList localIntArrayList = new IntArrayList(i);
/* 423 */     localIntArrayList.addAllOfFromTo(this, paramInt1, paramInt2);
/* 424 */     return localIntArrayList;
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
/*     */   public void quickSortFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 443 */     int i = size();
/* 444 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, i);
/*     */     
/* 446 */     int[] arrayOfInt = elements();
/* 447 */     java.util.Arrays.sort(arrayOfInt, paramInt1, paramInt2 + 1);
/*     */     
/* 449 */     elements(arrayOfInt);
/* 450 */     setSizeRaw(i);
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
/*     */   public void quickSortFromTo(int paramInt1, int paramInt2, IntComparator paramIntComparator)
/*     */   {
/* 480 */     int i = size();
/* 481 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, i);
/*     */     
/* 483 */     int[] arrayOfInt = elements();
/* 484 */     Sorting.quickSort(arrayOfInt, paramInt1, paramInt2 + 1, paramIntComparator);
/* 485 */     elements(arrayOfInt);
/* 486 */     setSizeRaw(i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean removeAll(AbstractIntList paramAbstractIntList)
/*     */   {
/* 496 */     if (paramAbstractIntList.size() == 0) return false;
/* 497 */     int i = paramAbstractIntList.size() - 1;
/* 498 */     int j = 0;
/*     */     
/* 500 */     for (int k = 0; k < this.size; k++) {
/* 501 */       if (paramAbstractIntList.indexOfFromTo(getQuick(k), 0, i) < 0) { setQuick(j++, getQuick(k));
/*     */       }
/*     */     }
/* 504 */     boolean bool = j != this.size;
/* 505 */     setSize(j);
/* 506 */     return bool;
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
/* 519 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/* 520 */     int i = this.size - paramInt2 - 1;
/* 521 */     if (i > 0) {
/* 522 */       replaceFromToWithFrom(paramInt1, paramInt1 - 1 + i, this, paramInt2 + 1);
/*     */     }
/*     */     
/* 525 */     int j = paramInt2 - paramInt1 + 1;
/* 526 */     if (j > 0) { setSizeRaw(this.size - j);
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
/*     */   public void replaceFromToWithFrom(int paramInt1, int paramInt2, AbstractIntList paramAbstractIntList, int paramInt3)
/*     */   {
/* 539 */     int i = paramInt2 - paramInt1 + 1;
/* 540 */     if (i > 0) {
/* 541 */       AbstractList.checkRangeFromTo(paramInt1, paramInt2, size());
/* 542 */       AbstractList.checkRangeFromTo(paramInt3, paramInt3 + i - 1, paramAbstractIntList.size());
/*     */       
/*     */ 
/* 545 */       if (paramInt1 <= paramInt3) {
/* 546 */         do { setQuick(paramInt1++, paramAbstractIntList.getQuick(paramInt3++));i--; } while (i >= 0);
/*     */       }
/*     */       else {
/* 549 */         int j = paramInt3 + i - 1;
/* 550 */         do { setQuick(paramInt2--, paramAbstractIntList.getQuick(j--));i--; } while (i >= 0);
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
/*     */   public void replaceFromToWithFromTo(int paramInt1, int paramInt2, AbstractIntList paramAbstractIntList, int paramInt3, int paramInt4)
/*     */   {
/* 600 */     if (paramInt3 > paramInt4) {
/* 601 */       throw new IndexOutOfBoundsException("otherFrom: " + paramInt3 + ", otherTo: " + paramInt4);
/*     */     }
/*     */     
/* 604 */     if ((this == paramAbstractIntList) && (paramInt2 - paramInt1 != paramInt4 - paramInt3)) {
/* 605 */       replaceFromToWithFromTo(paramInt1, paramInt2, partFromTo(paramInt3, paramInt4), 0, paramInt4 - paramInt3);
/* 606 */       return;
/*     */     }
/*     */     
/* 609 */     int i = paramInt4 - paramInt3 + 1;
/* 610 */     int j = i;
/* 611 */     int k = paramInt1 - 1;
/*     */     
/* 613 */     if (paramInt2 >= paramInt1) {
/* 614 */       j -= paramInt2 - paramInt1 + 1;
/* 615 */       k = paramInt2;
/*     */     }
/*     */     
/* 618 */     if (j > 0) {
/* 619 */       beforeInsertDummies(k + 1, j);
/*     */ 
/*     */     }
/* 622 */     else if (j < 0) {
/* 623 */       removeFromTo(k + j, k - 1);
/*     */     }
/*     */     
/*     */ 
/* 627 */     if (i > 0) {
/* 628 */       replaceFromToWithFrom(paramInt1, paramInt1 + i - 1, paramAbstractIntList, paramInt3);
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
/* 641 */     AbstractList.checkRange(paramInt, size());
/* 642 */     Iterator localIterator = paramCollection.iterator();
/* 643 */     int i = paramInt;
/* 644 */     int j = Math.min(size() - paramInt, paramCollection.size());
/* 645 */     for (int k = 0; k < j; k++) {
/* 646 */       set(i++, ((Number)localIterator.next()).intValue());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean retainAll(AbstractIntList paramAbstractIntList)
/*     */   {
/* 656 */     if (paramAbstractIntList.size() == 0) {
/* 657 */       if (this.size == 0) return false;
/* 658 */       setSize(0);
/* 659 */       return true;
/*     */     }
/*     */     
/* 662 */     int i = paramAbstractIntList.size() - 1;
/* 663 */     int j = 0;
/* 664 */     for (int k = 0; k < this.size; k++) {
/* 665 */       if (paramAbstractIntList.indexOfFromTo(getQuick(k), 0, i) >= 0) { setQuick(j++, getQuick(k));
/*     */       }
/*     */     }
/* 668 */     boolean bool = j != this.size;
/* 669 */     setSize(j);
/* 670 */     return bool;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reverse()
/*     */   {
/* 678 */     int j = size() / 2;
/* 679 */     int k = size() - 1;
/*     */     
/* 681 */     for (int m = 0; m < j;) {
/* 682 */       int i = getQuick(m);
/* 683 */       setQuick(m++, getQuick(k));
/* 684 */       setQuick(k--, i);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void set(int paramInt1, int paramInt2)
/*     */   {
/* 695 */     if ((paramInt1 >= this.size) || (paramInt1 < 0))
/* 696 */       throw new IndexOutOfBoundsException("Index: " + paramInt1 + ", Size: " + this.size);
/* 697 */     setQuick(paramInt1, paramInt2);
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
/*     */   protected abstract void setQuick(int paramInt1, int paramInt2);
/*     */   
/*     */ 
/*     */ 
/*     */ 
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
/* 728 */     this.size = paramInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void shuffleFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 737 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, size());
/*     */     
/* 739 */     Uniform localUniform = new Uniform(new DRand(new Date()));
/* 740 */     for (int i = paramInt1; i < paramInt2; i++) {
/* 741 */       int j = localUniform.nextIntFromTo(i, paramInt2);
/*     */       
/*     */ 
/* 744 */       int k = getQuick(j);
/* 745 */       setQuick(j, getQuick(i));
/* 746 */       setQuick(i, k);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 755 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public AbstractIntList times(int paramInt)
/*     */   {
/* 762 */     IntArrayList localIntArrayList = new IntArrayList(paramInt * size());
/* 763 */     int i = paramInt;
/* 764 */     do { localIntArrayList.addAllOfFromTo(this, 0, size() - 1);i--;
/* 763 */     } while (i >= 0);
/*     */     
/*     */ 
/* 766 */     return localIntArrayList;
/*     */   }
/*     */   
/*     */ 
/*     */   public ArrayList toList()
/*     */   {
/* 772 */     int i = size();
/* 773 */     ArrayList localArrayList = new ArrayList(i);
/* 774 */     for (int j = 0; j < i; j++) localArrayList.add(new Integer(get(j)));
/* 775 */     return localArrayList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 782 */     return cern.colt.Arrays.toString(partFromTo(0, size() - 1).elements());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/list/AbstractIntList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */