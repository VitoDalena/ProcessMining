/*     */ package cern.colt.list;
/*     */ 
/*     */ import cern.colt.Sorting;
/*     */ import cern.colt.buffer.DoubleBufferConsumer;
/*     */ import cern.colt.function.DoubleComparator;
/*     */ import cern.colt.function.DoubleProcedure;
/*     */ import cern.jet.random.AbstractDistribution;
/*     */ import cern.jet.random.Uniform;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
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
/*     */ public abstract class AbstractDoubleList
/*     */   extends AbstractList
/*     */   implements DoubleBufferConsumer
/*     */ {
/*     */   protected int size;
/*     */   
/*     */   public void add(double paramDouble)
/*     */   {
/*  35 */     beforeInsert(this.size, paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void addAllOf(DoubleArrayList paramDoubleArrayList)
/*     */   {
/*  42 */     addAllOfFromTo(paramDoubleArrayList, 0, paramDoubleArrayList.size() - 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addAllOfFromTo(AbstractDoubleList paramAbstractDoubleList, int paramInt1, int paramInt2)
/*     */   {
/*  53 */     beforeInsertAllOfFromTo(this.size, paramAbstractDoubleList, paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void beforeInsert(int paramInt, double paramDouble)
/*     */   {
/*  65 */     beforeInsertDummies(paramInt, 1);
/*  66 */     set(paramInt, paramDouble);
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
/*     */   public void beforeInsertAllOfFromTo(int paramInt1, AbstractDoubleList paramAbstractDoubleList, int paramInt2, int paramInt3)
/*     */   {
/*  81 */     int i = paramInt3 - paramInt2 + 1;
/*  82 */     beforeInsertDummies(paramInt1, i);
/*  83 */     replaceFromToWithFrom(paramInt1, paramInt1 + i - 1, paramAbstractDoubleList, paramInt2);
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
/*  96 */     if ((paramInt1 > this.size) || (paramInt1 < 0))
/*  97 */       throw new IndexOutOfBoundsException("Index: " + paramInt1 + ", Size: " + this.size);
/*  98 */     if (paramInt2 > 0) {
/*  99 */       ensureCapacity(this.size + paramInt2);
/* 100 */       setSizeRaw(this.size + paramInt2);
/* 101 */       replaceFromToWithFrom(paramInt1 + paramInt2, this.size - 1, this, paramInt1);
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
/*     */   public int binarySearch(double paramDouble)
/*     */   {
/* 125 */     return binarySearchFromTo(paramDouble, 0, this.size - 1);
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
/*     */   public int binarySearchFromTo(double paramDouble, int paramInt1, int paramInt2)
/*     */   {
/* 150 */     int i = paramInt1;
/* 151 */     int j = paramInt2;
/* 152 */     while (i <= j) {
/* 153 */       int k = (i + j) / 2;
/* 154 */       double d = get(k);
/*     */       
/* 156 */       if (d < paramDouble) { i = k + 1;
/* 157 */       } else if (d > paramDouble) j = k - 1; else
/* 158 */         return k;
/*     */     }
/* 160 */     return -(i + 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 168 */     return partFromTo(0, this.size - 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean contains(double paramDouble)
/*     */   {
/* 176 */     return indexOfFromTo(paramDouble, 0, this.size - 1) >= 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void delete(double paramDouble)
/*     */   {
/* 185 */     int i = indexOfFromTo(paramDouble, 0, this.size - 1);
/* 186 */     if (i >= 0) { remove(i);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[] elements()
/*     */   {
/* 197 */     double[] arrayOfDouble = new double[this.size];
/* 198 */     int i = this.size; do { arrayOfDouble[i] = getQuick(i);i--; } while (i >= 0);
/* 199 */     return arrayOfDouble;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractDoubleList elements(double[] paramArrayOfDouble)
/*     */   {
/* 211 */     clear();
/* 212 */     addAllOfFromTo(new DoubleArrayList(paramArrayOfDouble), 0, paramArrayOfDouble.length - 1);
/* 213 */     return this;
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
/* 233 */     if (!(paramObject instanceof AbstractDoubleList)) return false;
/* 234 */     if (this == paramObject) return true;
/* 235 */     if (paramObject == null) return false;
/* 236 */     AbstractDoubleList localAbstractDoubleList = (AbstractDoubleList)paramObject;
/* 237 */     if (size() != localAbstractDoubleList.size()) { return false;
/*     */     }
/* 239 */     int i = size();
/* 240 */     do { if (getQuick(i) != localAbstractDoubleList.getQuick(i)) return false;
/* 239 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/* 242 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void fillFromToWith(int paramInt1, int paramInt2, double paramDouble)
/*     */   {
/* 252 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/* 253 */     for (int i = paramInt1; i <= paramInt2; setQuick(i++, paramDouble)) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean forEach(DoubleProcedure paramDoubleProcedure)
/*     */   {
/* 262 */     for (int i = 0; i < this.size;) if (!paramDoubleProcedure.apply(get(i++))) return false;
/* 263 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double get(int paramInt)
/*     */   {
/* 273 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 274 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/* 275 */     return getQuick(paramInt);
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
/*     */   protected abstract double getQuick(int paramInt);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int indexOf(double paramDouble)
/*     */   {
/* 297 */     return indexOfFromTo(paramDouble, 0, this.size - 1);
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
/*     */   public int indexOfFromTo(double paramDouble, int paramInt1, int paramInt2)
/*     */   {
/* 312 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 314 */     for (int i = paramInt1; i <= paramInt2; i++) {
/* 315 */       if (paramDouble == getQuick(i)) return i;
/*     */     }
/* 317 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int lastIndexOf(double paramDouble)
/*     */   {
/* 327 */     return lastIndexOfFromTo(paramDouble, 0, this.size - 1);
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
/*     */   public int lastIndexOfFromTo(double paramDouble, int paramInt1, int paramInt2)
/*     */   {
/* 342 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, size());
/*     */     
/* 344 */     for (int i = paramInt2; i >= paramInt1; i--) {
/* 345 */       if (paramDouble == getQuick(i)) return i;
/*     */     }
/* 347 */     return -1;
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
/* 366 */     int i = size();
/* 367 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, i);
/*     */     
/* 369 */     double[] arrayOfDouble = elements();
/* 370 */     Sorting.mergeSort(arrayOfDouble, paramInt1, paramInt2 + 1);
/* 371 */     elements(arrayOfDouble);
/* 372 */     setSizeRaw(i);
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
/*     */   public void mergeSortFromTo(int paramInt1, int paramInt2, DoubleComparator paramDoubleComparator)
/*     */   {
/* 404 */     int i = size();
/* 405 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, i);
/*     */     
/* 407 */     double[] arrayOfDouble = elements();
/* 408 */     Sorting.mergeSort(arrayOfDouble, paramInt1, paramInt2 + 1, paramDoubleComparator);
/* 409 */     elements(arrayOfDouble);
/* 410 */     setSizeRaw(i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractDoubleList partFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 420 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 422 */     int i = paramInt2 - paramInt1 + 1;
/* 423 */     DoubleArrayList localDoubleArrayList = new DoubleArrayList(i);
/* 424 */     localDoubleArrayList.addAllOfFromTo(this, paramInt1, paramInt2);
/* 425 */     return localDoubleArrayList;
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
/* 444 */     int i = size();
/* 445 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, i);
/*     */     
/* 447 */     double[] arrayOfDouble = elements();
/* 448 */     java.util.Arrays.sort(arrayOfDouble, paramInt1, paramInt2 + 1);
/*     */     
/*     */ 
/* 451 */     elements(arrayOfDouble);
/* 452 */     setSizeRaw(i);
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
/*     */   public void quickSortFromTo(int paramInt1, int paramInt2, DoubleComparator paramDoubleComparator)
/*     */   {
/* 482 */     int i = size();
/* 483 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, i);
/*     */     
/* 485 */     double[] arrayOfDouble = elements();
/* 486 */     Sorting.quickSort(arrayOfDouble, paramInt1, paramInt2 + 1, paramDoubleComparator);
/* 487 */     elements(arrayOfDouble);
/* 488 */     setSizeRaw(i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean removeAll(AbstractDoubleList paramAbstractDoubleList)
/*     */   {
/* 498 */     if (paramAbstractDoubleList.size() == 0) return false;
/* 499 */     int i = paramAbstractDoubleList.size() - 1;
/* 500 */     int j = 0;
/*     */     
/* 502 */     for (int k = 0; k < this.size; k++) {
/* 503 */       if (paramAbstractDoubleList.indexOfFromTo(getQuick(k), 0, i) < 0) { setQuick(j++, getQuick(k));
/*     */       }
/*     */     }
/* 506 */     boolean bool = j != this.size;
/* 507 */     setSize(j);
/* 508 */     return bool;
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
/* 521 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/* 522 */     int i = this.size - paramInt2 - 1;
/* 523 */     if (i > 0) {
/* 524 */       replaceFromToWithFrom(paramInt1, paramInt1 - 1 + i, this, paramInt2 + 1);
/*     */     }
/*     */     
/* 527 */     int j = paramInt2 - paramInt1 + 1;
/* 528 */     if (j > 0) { setSizeRaw(this.size - j);
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
/*     */   public void replaceFromToWithFrom(int paramInt1, int paramInt2, AbstractDoubleList paramAbstractDoubleList, int paramInt3)
/*     */   {
/* 541 */     int i = paramInt2 - paramInt1 + 1;
/* 542 */     if (i > 0) {
/* 543 */       AbstractList.checkRangeFromTo(paramInt1, paramInt2, size());
/* 544 */       AbstractList.checkRangeFromTo(paramInt3, paramInt3 + i - 1, paramAbstractDoubleList.size());
/*     */       
/*     */ 
/* 547 */       if (paramInt1 <= paramInt3) {
/* 548 */         do { setQuick(paramInt1++, paramAbstractDoubleList.getQuick(paramInt3++));i--; } while (i >= 0);
/*     */       }
/*     */       else {
/* 551 */         int j = paramInt3 + i - 1;
/* 552 */         do { setQuick(paramInt2--, paramAbstractDoubleList.getQuick(j--));i--; } while (i >= 0);
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
/*     */   public void replaceFromToWithFromTo(int paramInt1, int paramInt2, AbstractDoubleList paramAbstractDoubleList, int paramInt3, int paramInt4)
/*     */   {
/* 602 */     if (paramInt3 > paramInt4) {
/* 603 */       throw new IndexOutOfBoundsException("otherFrom: " + paramInt3 + ", otherTo: " + paramInt4);
/*     */     }
/*     */     
/* 606 */     if ((this == paramAbstractDoubleList) && (paramInt2 - paramInt1 != paramInt4 - paramInt3)) {
/* 607 */       replaceFromToWithFromTo(paramInt1, paramInt2, partFromTo(paramInt3, paramInt4), 0, paramInt4 - paramInt3);
/* 608 */       return;
/*     */     }
/*     */     
/* 611 */     int i = paramInt4 - paramInt3 + 1;
/* 612 */     int j = i;
/* 613 */     int k = paramInt1 - 1;
/*     */     
/* 615 */     if (paramInt2 >= paramInt1) {
/* 616 */       j -= paramInt2 - paramInt1 + 1;
/* 617 */       k = paramInt2;
/*     */     }
/*     */     
/* 620 */     if (j > 0) {
/* 621 */       beforeInsertDummies(k + 1, j);
/*     */ 
/*     */     }
/* 624 */     else if (j < 0) {
/* 625 */       removeFromTo(k + j, k - 1);
/*     */     }
/*     */     
/*     */ 
/* 629 */     if (i > 0) {
/* 630 */       replaceFromToWithFrom(paramInt1, paramInt1 + i - 1, paramAbstractDoubleList, paramInt3);
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
/* 643 */     AbstractList.checkRange(paramInt, size());
/* 644 */     Iterator localIterator = paramCollection.iterator();
/* 645 */     int i = paramInt;
/* 646 */     int j = Math.min(size() - paramInt, paramCollection.size());
/* 647 */     for (int k = 0; k < j; k++) {
/* 648 */       set(i++, ((Number)localIterator.next()).doubleValue());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean retainAll(AbstractDoubleList paramAbstractDoubleList)
/*     */   {
/* 658 */     if (paramAbstractDoubleList.size() == 0) {
/* 659 */       if (this.size == 0) return false;
/* 660 */       setSize(0);
/* 661 */       return true;
/*     */     }
/*     */     
/* 664 */     int i = paramAbstractDoubleList.size() - 1;
/* 665 */     int j = 0;
/* 666 */     for (int k = 0; k < this.size; k++) {
/* 667 */       if (paramAbstractDoubleList.indexOfFromTo(getQuick(k), 0, i) >= 0) { setQuick(j++, getQuick(k));
/*     */       }
/*     */     }
/* 670 */     boolean bool = j != this.size;
/* 671 */     setSize(j);
/* 672 */     return bool;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reverse()
/*     */   {
/* 680 */     int i = size() / 2;
/* 681 */     int j = size() - 1;
/*     */     
/* 683 */     for (int k = 0; k < i;) {
/* 684 */       double d = getQuick(k);
/* 685 */       setQuick(k++, getQuick(j));
/* 686 */       setQuick(j--, d);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void set(int paramInt, double paramDouble)
/*     */   {
/* 697 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 698 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/* 699 */     setQuick(paramInt, paramDouble);
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
/*     */   protected abstract void setQuick(int paramInt, double paramDouble);
/*     */   
/*     */ 
/*     */ 
/*     */ 
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
/* 730 */     this.size = paramInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void shuffleFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 739 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, size());
/*     */     
/* 741 */     Uniform localUniform = new Uniform(AbstractDistribution.makeDefaultGenerator());
/* 742 */     for (int i = paramInt1; i < paramInt2; i++) {
/* 743 */       int j = localUniform.nextIntFromTo(i, paramInt2);
/*     */       
/*     */ 
/* 746 */       double d = getQuick(j);
/* 747 */       setQuick(j, getQuick(i));
/* 748 */       setQuick(i, d);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 757 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public AbstractDoubleList times(int paramInt)
/*     */   {
/* 764 */     DoubleArrayList localDoubleArrayList = new DoubleArrayList(paramInt * size());
/* 765 */     int i = paramInt;
/* 766 */     do { localDoubleArrayList.addAllOfFromTo(this, 0, size() - 1);i--;
/* 765 */     } while (i >= 0);
/*     */     
/*     */ 
/* 768 */     return localDoubleArrayList;
/*     */   }
/*     */   
/*     */ 
/*     */   public ArrayList toList()
/*     */   {
/* 774 */     int i = size();
/* 775 */     ArrayList localArrayList = new ArrayList(i);
/* 776 */     for (int j = 0; j < i; j++) localArrayList.add(new Double(get(j)));
/* 777 */     return localArrayList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 784 */     return cern.colt.Arrays.toString(partFromTo(0, size() - 1).elements());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/list/AbstractDoubleList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */