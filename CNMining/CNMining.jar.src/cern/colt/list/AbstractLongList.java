/*     */ package cern.colt.list;
/*     */ 
/*     */ import cern.colt.Sorting;
/*     */ import cern.colt.function.LongComparator;
/*     */ import cern.colt.function.LongProcedure;
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
/*     */ public abstract class AbstractLongList
/*     */   extends AbstractList
/*     */ {
/*     */   protected int size;
/*     */   
/*     */   public void add(long paramLong)
/*     */   {
/*  34 */     beforeInsert(this.size, paramLong);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addAllOfFromTo(AbstractLongList paramAbstractLongList, int paramInt1, int paramInt2)
/*     */   {
/*  45 */     beforeInsertAllOfFromTo(this.size, paramAbstractLongList, paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void beforeInsert(int paramInt, long paramLong)
/*     */   {
/*  57 */     beforeInsertDummies(paramInt, 1);
/*  58 */     set(paramInt, paramLong);
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
/*     */   public void beforeInsertAllOfFromTo(int paramInt1, AbstractLongList paramAbstractLongList, int paramInt2, int paramInt3)
/*     */   {
/*  73 */     int i = paramInt3 - paramInt2 + 1;
/*  74 */     beforeInsertDummies(paramInt1, i);
/*  75 */     replaceFromToWithFrom(paramInt1, paramInt1 + i - 1, paramAbstractLongList, paramInt2);
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
/*     */   public int binarySearch(long paramLong)
/*     */   {
/* 117 */     return binarySearchFromTo(paramLong, 0, this.size - 1);
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
/*     */   public int binarySearchFromTo(long paramLong, int paramInt1, int paramInt2)
/*     */   {
/* 142 */     int i = paramInt1;
/* 143 */     int j = paramInt2;
/* 144 */     while (i <= j) {
/* 145 */       int k = (i + j) / 2;
/* 146 */       long l = get(k);
/*     */       
/* 148 */       if (l < paramLong) { i = k + 1;
/* 149 */       } else if (l > paramLong) j = k - 1; else
/* 150 */         return k;
/*     */     }
/* 152 */     return -(i + 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 160 */     return partFromTo(0, this.size - 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean contains(long paramLong)
/*     */   {
/* 168 */     return indexOfFromTo(paramLong, 0, this.size - 1) >= 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void delete(long paramLong)
/*     */   {
/* 177 */     int i = indexOfFromTo(paramLong, 0, this.size - 1);
/* 178 */     if (i >= 0) { remove(i);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long[] elements()
/*     */   {
/* 189 */     long[] arrayOfLong = new long[this.size];
/* 190 */     int i = this.size; do { arrayOfLong[i] = getQuick(i);i--; } while (i >= 0);
/* 191 */     return arrayOfLong;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractLongList elements(long[] paramArrayOfLong)
/*     */   {
/* 203 */     clear();
/* 204 */     addAllOfFromTo(new LongArrayList(paramArrayOfLong), 0, paramArrayOfLong.length - 1);
/* 205 */     return this;
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
/* 225 */     if (!(paramObject instanceof AbstractLongList)) return false;
/* 226 */     if (this == paramObject) return true;
/* 227 */     if (paramObject == null) return false;
/* 228 */     AbstractLongList localAbstractLongList = (AbstractLongList)paramObject;
/* 229 */     if (size() != localAbstractLongList.size()) { return false;
/*     */     }
/* 231 */     int i = size();
/* 232 */     do { if (getQuick(i) != localAbstractLongList.getQuick(i)) return false;
/* 231 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/* 234 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void fillFromToWith(int paramInt1, int paramInt2, long paramLong)
/*     */   {
/* 244 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/* 245 */     for (int i = paramInt1; i <= paramInt2; setQuick(i++, paramLong)) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean forEach(LongProcedure paramLongProcedure)
/*     */   {
/* 254 */     for (int i = 0; i < this.size;) if (!paramLongProcedure.apply(get(i++))) return false;
/* 255 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long get(int paramInt)
/*     */   {
/* 265 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 266 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/* 267 */     return getQuick(paramInt);
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
/*     */   protected abstract long getQuick(int paramInt);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int indexOf(long paramLong)
/*     */   {
/* 289 */     return indexOfFromTo(paramLong, 0, this.size - 1);
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
/*     */   public int indexOfFromTo(long paramLong, int paramInt1, int paramInt2)
/*     */   {
/* 304 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 306 */     for (int i = paramInt1; i <= paramInt2; i++) {
/* 307 */       if (paramLong == getQuick(i)) return i;
/*     */     }
/* 309 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int lastIndexOf(long paramLong)
/*     */   {
/* 319 */     return lastIndexOfFromTo(paramLong, 0, this.size - 1);
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
/*     */   public int lastIndexOfFromTo(long paramLong, int paramInt1, int paramInt2)
/*     */   {
/* 334 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, size());
/*     */     
/* 336 */     for (int i = paramInt2; i >= paramInt1; i--) {
/* 337 */       if (paramLong == getQuick(i)) return i;
/*     */     }
/* 339 */     return -1;
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
/* 358 */     int i = size();
/* 359 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, i);
/*     */     
/* 361 */     long[] arrayOfLong = elements();
/* 362 */     Sorting.mergeSort(arrayOfLong, paramInt1, paramInt2 + 1);
/* 363 */     elements(arrayOfLong);
/* 364 */     setSizeRaw(i);
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
/*     */   public void mergeSortFromTo(int paramInt1, int paramInt2, LongComparator paramLongComparator)
/*     */   {
/* 396 */     int i = size();
/* 397 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, i);
/*     */     
/* 399 */     long[] arrayOfLong = elements();
/* 400 */     Sorting.mergeSort(arrayOfLong, paramInt1, paramInt2 + 1, paramLongComparator);
/* 401 */     elements(arrayOfLong);
/* 402 */     setSizeRaw(i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractLongList partFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 412 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 414 */     int i = paramInt2 - paramInt1 + 1;
/* 415 */     LongArrayList localLongArrayList = new LongArrayList(i);
/* 416 */     localLongArrayList.addAllOfFromTo(this, paramInt1, paramInt2);
/* 417 */     return localLongArrayList;
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
/* 436 */     int i = size();
/* 437 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, i);
/*     */     
/* 439 */     long[] arrayOfLong = elements();
/* 440 */     java.util.Arrays.sort(arrayOfLong, paramInt1, paramInt2 + 1);
/* 441 */     elements(arrayOfLong);
/* 442 */     setSizeRaw(i);
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
/*     */   public void quickSortFromTo(int paramInt1, int paramInt2, LongComparator paramLongComparator)
/*     */   {
/* 472 */     int i = size();
/* 473 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, i);
/*     */     
/* 475 */     long[] arrayOfLong = elements();
/* 476 */     Sorting.quickSort(arrayOfLong, paramInt1, paramInt2 + 1, paramLongComparator);
/* 477 */     elements(arrayOfLong);
/* 478 */     setSizeRaw(i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean removeAll(AbstractLongList paramAbstractLongList)
/*     */   {
/* 488 */     if (paramAbstractLongList.size() == 0) return false;
/* 489 */     int i = paramAbstractLongList.size() - 1;
/* 490 */     int j = 0;
/*     */     
/* 492 */     for (int k = 0; k < this.size; k++) {
/* 493 */       if (paramAbstractLongList.indexOfFromTo(getQuick(k), 0, i) < 0) { setQuick(j++, getQuick(k));
/*     */       }
/*     */     }
/* 496 */     boolean bool = j != this.size;
/* 497 */     setSize(j);
/* 498 */     return bool;
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
/* 511 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/* 512 */     int i = this.size - paramInt2 - 1;
/* 513 */     if (i > 0) {
/* 514 */       replaceFromToWithFrom(paramInt1, paramInt1 - 1 + i, this, paramInt2 + 1);
/*     */     }
/*     */     
/* 517 */     int j = paramInt2 - paramInt1 + 1;
/* 518 */     if (j > 0) { setSizeRaw(this.size - j);
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
/*     */   public void replaceFromToWithFrom(int paramInt1, int paramInt2, AbstractLongList paramAbstractLongList, int paramInt3)
/*     */   {
/* 531 */     int i = paramInt2 - paramInt1 + 1;
/* 532 */     if (i > 0) {
/* 533 */       AbstractList.checkRangeFromTo(paramInt1, paramInt2, size());
/* 534 */       AbstractList.checkRangeFromTo(paramInt3, paramInt3 + i - 1, paramAbstractLongList.size());
/*     */       
/*     */ 
/* 537 */       if (paramInt1 <= paramInt3) {
/* 538 */         do { setQuick(paramInt1++, paramAbstractLongList.getQuick(paramInt3++));i--; } while (i >= 0);
/*     */       }
/*     */       else {
/* 541 */         int j = paramInt3 + i - 1;
/* 542 */         do { setQuick(paramInt2--, paramAbstractLongList.getQuick(j--));i--; } while (i >= 0);
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
/*     */   public void replaceFromToWithFromTo(int paramInt1, int paramInt2, AbstractLongList paramAbstractLongList, int paramInt3, int paramInt4)
/*     */   {
/* 592 */     if (paramInt3 > paramInt4) {
/* 593 */       throw new IndexOutOfBoundsException("otherFrom: " + paramInt3 + ", otherTo: " + paramInt4);
/*     */     }
/*     */     
/* 596 */     if ((this == paramAbstractLongList) && (paramInt2 - paramInt1 != paramInt4 - paramInt3)) {
/* 597 */       replaceFromToWithFromTo(paramInt1, paramInt2, partFromTo(paramInt3, paramInt4), 0, paramInt4 - paramInt3);
/* 598 */       return;
/*     */     }
/*     */     
/* 601 */     int i = paramInt4 - paramInt3 + 1;
/* 602 */     int j = i;
/* 603 */     int k = paramInt1 - 1;
/*     */     
/* 605 */     if (paramInt2 >= paramInt1) {
/* 606 */       j -= paramInt2 - paramInt1 + 1;
/* 607 */       k = paramInt2;
/*     */     }
/*     */     
/* 610 */     if (j > 0) {
/* 611 */       beforeInsertDummies(k + 1, j);
/*     */ 
/*     */     }
/* 614 */     else if (j < 0) {
/* 615 */       removeFromTo(k + j, k - 1);
/*     */     }
/*     */     
/*     */ 
/* 619 */     if (i > 0) {
/* 620 */       replaceFromToWithFrom(paramInt1, paramInt1 + i - 1, paramAbstractLongList, paramInt3);
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
/* 633 */     AbstractList.checkRange(paramInt, size());
/* 634 */     Iterator localIterator = paramCollection.iterator();
/* 635 */     int i = paramInt;
/* 636 */     int j = Math.min(size() - paramInt, paramCollection.size());
/* 637 */     for (int k = 0; k < j; k++) {
/* 638 */       set(i++, ((Number)localIterator.next()).longValue());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean retainAll(AbstractLongList paramAbstractLongList)
/*     */   {
/* 648 */     if (paramAbstractLongList.size() == 0) {
/* 649 */       if (this.size == 0) return false;
/* 650 */       setSize(0);
/* 651 */       return true;
/*     */     }
/*     */     
/* 654 */     int i = paramAbstractLongList.size() - 1;
/* 655 */     int j = 0;
/* 656 */     for (int k = 0; k < this.size; k++) {
/* 657 */       if (paramAbstractLongList.indexOfFromTo(getQuick(k), 0, i) >= 0) { setQuick(j++, getQuick(k));
/*     */       }
/*     */     }
/* 660 */     boolean bool = j != this.size;
/* 661 */     setSize(j);
/* 662 */     return bool;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reverse()
/*     */   {
/* 670 */     int i = size() / 2;
/* 671 */     int j = size() - 1;
/*     */     
/* 673 */     for (int k = 0; k < i;) {
/* 674 */       long l = getQuick(k);
/* 675 */       setQuick(k++, getQuick(j));
/* 676 */       setQuick(j--, l);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void set(int paramInt, long paramLong)
/*     */   {
/* 687 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 688 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/* 689 */     setQuick(paramInt, paramLong);
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
/*     */   protected abstract void setQuick(int paramInt, long paramLong);
/*     */   
/*     */ 
/*     */ 
/*     */ 
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
/* 720 */     this.size = paramInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void shuffleFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 729 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, size());
/*     */     
/* 731 */     Uniform localUniform = new Uniform(new DRand(new Date()));
/* 732 */     for (int i = paramInt1; i < paramInt2; i++) {
/* 733 */       int j = localUniform.nextIntFromTo(i, paramInt2);
/*     */       
/*     */ 
/* 736 */       long l = getQuick(j);
/* 737 */       setQuick(j, getQuick(i));
/* 738 */       setQuick(i, l);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 747 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public AbstractLongList times(int paramInt)
/*     */   {
/* 754 */     LongArrayList localLongArrayList = new LongArrayList(paramInt * size());
/* 755 */     int i = paramInt;
/* 756 */     do { localLongArrayList.addAllOfFromTo(this, 0, size() - 1);i--;
/* 755 */     } while (i >= 0);
/*     */     
/*     */ 
/* 758 */     return localLongArrayList;
/*     */   }
/*     */   
/*     */ 
/*     */   public ArrayList toList()
/*     */   {
/* 764 */     int i = size();
/* 765 */     ArrayList localArrayList = new ArrayList(i);
/* 766 */     for (int j = 0; j < i; j++) localArrayList.add(new Long(get(j)));
/* 767 */     return localArrayList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 774 */     return cern.colt.Arrays.toString(partFromTo(0, size() - 1).elements());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/list/AbstractLongList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */