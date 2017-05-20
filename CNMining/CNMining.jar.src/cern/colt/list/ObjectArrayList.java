/*     */ package cern.colt.list;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.Sorting;
/*     */ import cern.colt.function.ObjectProcedure;
/*     */ import cern.jet.random.Uniform;
/*     */ import cern.jet.random.engine.DRand;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
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
/*     */ public class ObjectArrayList
/*     */   extends AbstractList
/*     */ {
/*     */   protected Object[] elements;
/*     */   protected int size;
/*     */   
/*     */   public ObjectArrayList()
/*     */   {
/*  33 */     this(10);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectArrayList(Object[] paramArrayOfObject)
/*     */   {
/*  45 */     elements(paramArrayOfObject);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectArrayList(int paramInt)
/*     */   {
/*  53 */     this(new Object[paramInt]);
/*  54 */     this.size = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(Object paramObject)
/*     */   {
/*  62 */     if (this.size == this.elements.length) ensureCapacity(this.size + 1);
/*  63 */     this.elements[(this.size++)] = paramObject;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addAllOfFromTo(ObjectArrayList paramObjectArrayList, int paramInt1, int paramInt2)
/*     */   {
/*  74 */     beforeInsertAllOfFromTo(this.size, paramObjectArrayList, paramInt1, paramInt2);
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
/*     */   public void beforeInsert(int paramInt, Object paramObject)
/*     */   {
/*  87 */     if ((paramInt > this.size) || (paramInt < 0))
/*  88 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/*  89 */     ensureCapacity(this.size + 1);
/*  90 */     System.arraycopy(this.elements, paramInt, this.elements, paramInt + 1, this.size - paramInt);
/*  91 */     this.elements[paramInt] = paramObject;
/*  92 */     this.size += 1;
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
/*     */   public void beforeInsertAllOfFromTo(int paramInt1, ObjectArrayList paramObjectArrayList, int paramInt2, int paramInt3)
/*     */   {
/* 107 */     int i = paramInt3 - paramInt2 + 1;
/* 108 */     beforeInsertDummies(paramInt1, i);
/* 109 */     replaceFromToWithFrom(paramInt1, paramInt1 + i - 1, paramObjectArrayList, paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void beforeInsertDummies(int paramInt1, int paramInt2)
/*     */   {
/* 120 */     if ((paramInt1 > this.size) || (paramInt1 < 0))
/* 121 */       throw new IndexOutOfBoundsException("Index: " + paramInt1 + ", Size: " + this.size);
/* 122 */     if (paramInt2 > 0) {
/* 123 */       ensureCapacity(this.size + paramInt2);
/* 124 */       System.arraycopy(this.elements, paramInt1, this.elements, paramInt1 + paramInt2, this.size - paramInt1);
/* 125 */       this.size += paramInt2;
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
/*     */   public int binarySearch(Object paramObject)
/*     */   {
/* 151 */     return binarySearchFromTo(paramObject, 0, this.size - 1);
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
/*     */   public int binarySearchFromTo(Object paramObject, int paramInt1, int paramInt2)
/*     */   {
/* 179 */     int i = paramInt1;
/* 180 */     int j = paramInt2;
/*     */     
/* 182 */     while (i <= j) {
/* 183 */       int k = (i + j) / 2;
/* 184 */       Object localObject = this.elements[k];
/* 185 */       int m = ((Comparable)localObject).compareTo(paramObject);
/*     */       
/* 187 */       if (m < 0) { i = k + 1;
/* 188 */       } else if (m > 0) j = k - 1; else
/* 189 */         return k;
/*     */     }
/* 191 */     return -(i + 1);
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
/*     */   public int binarySearchFromTo(Object paramObject, int paramInt1, int paramInt2, Comparator paramComparator)
/*     */   {
/* 227 */     return Sorting.binarySearchFromTo(this.elements, paramObject, paramInt1, paramInt2, paramComparator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 237 */     ObjectArrayList localObjectArrayList = (ObjectArrayList)super.clone();
/* 238 */     localObjectArrayList.elements = ((Object[])this.elements.clone());
/* 239 */     return localObjectArrayList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean contains(Object paramObject, boolean paramBoolean)
/*     */   {
/* 249 */     return indexOfFromTo(paramObject, 0, this.size - 1, paramBoolean) >= 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectArrayList copy()
/*     */   {
/* 260 */     return (ObjectArrayList)clone();
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
/*     */   public void delete(Object paramObject, boolean paramBoolean)
/*     */   {
/* 275 */     int i = indexOfFromTo(paramObject, 0, this.size - 1, paramBoolean);
/* 276 */     if (i >= 0) { removeFromTo(i, i);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object[] elements()
/*     */   {
/* 287 */     return this.elements;
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
/*     */   public ObjectArrayList elements(Object[] paramArrayOfObject)
/*     */   {
/* 300 */     this.elements = paramArrayOfObject;
/* 301 */     this.size = paramArrayOfObject.length;
/* 302 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void ensureCapacity(int paramInt)
/*     */   {
/* 311 */     this.elements = cern.colt.Arrays.ensureCapacity(this.elements, paramInt);
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
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 327 */     return equals(paramObject, true);
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
/* 345 */     if (!(paramObject instanceof ObjectArrayList)) return false;
/* 346 */     if (this == paramObject) return true;
/* 347 */     if (paramObject == null) return false;
/* 348 */     ObjectArrayList localObjectArrayList = (ObjectArrayList)paramObject;
/* 349 */     if (this.elements == localObjectArrayList.elements()) return true;
/* 350 */     if (this.size != localObjectArrayList.size()) { return false;
/*     */     }
/* 352 */     Object[] arrayOfObject1 = localObjectArrayList.elements();
/* 353 */     Object[] arrayOfObject2 = this.elements;
/* 354 */     int i; if (!paramBoolean) {
/* 355 */       i = this.size;
/* 356 */       do { if (arrayOfObject2[i] != arrayOfObject1[i]) return false;
/* 355 */         i--; } while (i >= 0);
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 360 */       i = this.size;
/* 361 */       do { if (!(arrayOfObject2[i] == null ? false : arrayOfObject1[i] == null ? true : arrayOfObject2[i].equals(arrayOfObject1[i]))) return false;
/* 360 */         i--; } while (i >= 0);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 365 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void fillFromToWith(int paramInt1, int paramInt2, Object paramObject)
/*     */   {
/* 376 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/* 377 */     for (int i = paramInt1; i <= paramInt2; setQuick(i++, paramObject)) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean forEach(ObjectProcedure paramObjectProcedure)
/*     */   {
/* 386 */     Object[] arrayOfObject = this.elements;
/* 387 */     int i = this.size;
/*     */     
/* 389 */     for (int j = 0; j < i;) if (!paramObjectProcedure.apply(arrayOfObject[(j++)])) return false;
/* 390 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object get(int paramInt)
/*     */   {
/* 399 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 400 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/* 401 */     return this.elements[paramInt];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object getQuick(int paramInt)
/*     */   {
/* 412 */     return this.elements[paramInt];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int indexOf(Object paramObject, boolean paramBoolean)
/*     */   {
/* 424 */     return indexOfFromTo(paramObject, 0, this.size - 1, paramBoolean);
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
/*     */   public int indexOfFromTo(Object paramObject, int paramInt1, int paramInt2, boolean paramBoolean)
/*     */   {
/* 441 */     if (this.size == 0) return -1;
/* 442 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 444 */     Object[] arrayOfObject = this.elements;
/* 445 */     int i; if ((paramBoolean) && (paramObject != null)) {
/* 446 */       for (i = paramInt1; i <= paramInt2; i++) {
/* 447 */         if (paramObject.equals(arrayOfObject[i])) { return i;
/*     */         }
/*     */         
/*     */       }
/*     */     } else {
/* 452 */       for (i = paramInt1; i <= paramInt2; i++) {
/* 453 */         if (paramObject == arrayOfObject[i]) return i;
/*     */       }
/*     */     }
/* 456 */     return -1;
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
/*     */   public boolean isSortedFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 472 */     if (this.size == 0) return true;
/* 473 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 475 */     Object[] arrayOfObject = this.elements;
/* 476 */     for (int i = paramInt1 + 1; i <= paramInt2; i++) {
/* 477 */       if (((Comparable)arrayOfObject[i]).compareTo((Comparable)arrayOfObject[(i - 1)]) < 0) return false;
/*     */     }
/* 479 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int lastIndexOf(Object paramObject, boolean paramBoolean)
/*     */   {
/* 491 */     return lastIndexOfFromTo(paramObject, 0, this.size - 1, paramBoolean);
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
/*     */   public int lastIndexOfFromTo(Object paramObject, int paramInt1, int paramInt2, boolean paramBoolean)
/*     */   {
/* 507 */     if (this.size == 0) return -1;
/* 508 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 510 */     Object[] arrayOfObject = this.elements;
/* 511 */     int i; if ((paramBoolean) && (paramObject != null)) {
/* 512 */       for (i = paramInt2; i >= paramInt1; i--) {
/* 513 */         if (paramObject.equals(arrayOfObject[i])) { return i;
/*     */         }
/*     */         
/*     */       }
/*     */     } else {
/* 518 */       for (i = paramInt2; i >= paramInt1; i--) {
/* 519 */         if (paramObject == arrayOfObject[i]) return i;
/*     */       }
/*     */     }
/* 522 */     return -1;
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
/*     */   public void mergeSortFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 550 */     if (this.size == 0) return;
/* 551 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/* 552 */     java.util.Arrays.sort(this.elements, paramInt1, paramInt2 + 1);
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
/*     */   public void mergeSortFromTo(int paramInt1, int paramInt2, Comparator paramComparator)
/*     */   {
/* 584 */     if (this.size == 0) return;
/* 585 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/* 586 */     java.util.Arrays.sort(this.elements, paramInt1, paramInt2 + 1, paramComparator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectArrayList partFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 596 */     if (this.size == 0) { return new ObjectArrayList(0);
/*     */     }
/* 598 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 600 */     Object[] arrayOfObject = new Object[paramInt2 - paramInt1 + 1];
/* 601 */     System.arraycopy(this.elements, paramInt1, arrayOfObject, 0, paramInt2 - paramInt1 + 1);
/* 602 */     return new ObjectArrayList(arrayOfObject);
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
/*     */   public void quickSortFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 628 */     if (this.size == 0) return;
/* 629 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/* 630 */     Sorting.quickSort(this.elements, paramInt1, paramInt2 + 1);
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
/*     */   public void quickSortFromTo(int paramInt1, int paramInt2, Comparator paramComparator)
/*     */   {
/* 659 */     if (this.size == 0) return;
/* 660 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/* 661 */     Sorting.quickSort(this.elements, paramInt1, paramInt2 + 1, paramComparator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean removeAll(ObjectArrayList paramObjectArrayList, boolean paramBoolean)
/*     */   {
/* 672 */     if (paramObjectArrayList.size == 0) return false;
/* 673 */     int i = paramObjectArrayList.size - 1;
/* 674 */     int j = 0;
/* 675 */     Object[] arrayOfObject = this.elements;
/* 676 */     for (int k = 0; k < this.size; k++) {
/* 677 */       if (paramObjectArrayList.indexOfFromTo(arrayOfObject[k], 0, i, paramBoolean) < 0) { arrayOfObject[(j++)] = arrayOfObject[k];
/*     */       }
/*     */     }
/* 680 */     boolean bool = j != this.size;
/* 681 */     setSize(j);
/* 682 */     return bool;
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
/* 695 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/* 696 */     int i = this.size - paramInt2 - 1;
/* 697 */     if (i >= 0) {
/* 698 */       System.arraycopy(this.elements, paramInt2 + 1, this.elements, paramInt1, i);
/* 699 */       fillFromToWith(paramInt1 + i, this.size - 1, null);
/*     */     }
/* 701 */     int j = paramInt2 - paramInt1 + 1;
/* 702 */     if (j > 0) { this.size -= j;
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
/*     */   public void replaceFromToWithFrom(int paramInt1, int paramInt2, ObjectArrayList paramObjectArrayList, int paramInt3)
/*     */   {
/* 715 */     int i = paramInt2 - paramInt1 + 1;
/* 716 */     if (i > 0) {
/* 717 */       AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/* 718 */       AbstractList.checkRangeFromTo(paramInt3, paramInt3 + i - 1, paramObjectArrayList.size);
/* 719 */       System.arraycopy(paramObjectArrayList.elements, paramInt3, this.elements, paramInt1, i);
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
/*     */   public void replaceFromToWithFromTo(int paramInt1, int paramInt2, ObjectArrayList paramObjectArrayList, int paramInt3, int paramInt4)
/*     */   {
/* 766 */     if (paramInt3 > paramInt4) {
/* 767 */       throw new IndexOutOfBoundsException("otherFrom: " + paramInt3 + ", otherTo: " + paramInt4);
/*     */     }
/* 769 */     if ((this == paramObjectArrayList) && (paramInt2 - paramInt1 != paramInt4 - paramInt3)) {
/* 770 */       replaceFromToWithFromTo(paramInt1, paramInt2, partFromTo(paramInt3, paramInt4), 0, paramInt4 - paramInt3);
/* 771 */       return;
/*     */     }
/*     */     
/* 774 */     int i = paramInt4 - paramInt3 + 1;
/* 775 */     int j = i;
/* 776 */     int k = paramInt1 - 1;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 782 */     if (paramInt2 >= paramInt1) {
/* 783 */       j -= paramInt2 - paramInt1 + 1;
/* 784 */       k = paramInt2;
/*     */     }
/*     */     
/* 787 */     if (j > 0) {
/* 788 */       beforeInsertDummies(k + 1, j);
/*     */ 
/*     */     }
/* 791 */     else if (j < 0) {
/* 792 */       removeFromTo(k + j, k - 1);
/*     */     }
/*     */     
/*     */ 
/* 796 */     if (i > 0) {
/* 797 */       System.arraycopy(paramObjectArrayList.elements, paramInt3, this.elements, paramInt1, i);
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
/* 810 */     AbstractList.checkRange(paramInt, this.size);
/* 811 */     Iterator localIterator = paramCollection.iterator();
/* 812 */     int i = paramInt;
/* 813 */     int j = Math.min(this.size - paramInt, paramCollection.size());
/* 814 */     for (int k = 0; k < j; k++) {
/* 815 */       this.elements[(i++)] = localIterator.next();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean retainAll(ObjectArrayList paramObjectArrayList, boolean paramBoolean)
/*     */   {
/* 827 */     if (paramObjectArrayList.size == 0) {
/* 828 */       if (this.size == 0) return false;
/* 829 */       setSize(0);
/* 830 */       return true;
/*     */     }
/*     */     
/* 833 */     int i = paramObjectArrayList.size - 1;
/* 834 */     int j = 0;
/* 835 */     Object[] arrayOfObject = this.elements;
/*     */     
/* 837 */     for (int k = 0; k < this.size; k++) {
/* 838 */       if (paramObjectArrayList.indexOfFromTo(arrayOfObject[k], 0, i, paramBoolean) >= 0) { arrayOfObject[(j++)] = arrayOfObject[k];
/*     */       }
/*     */     }
/* 841 */     boolean bool = j != this.size;
/* 842 */     setSize(j);
/* 843 */     return bool;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reverse()
/*     */   {
/* 851 */     int i = this.size / 2;
/* 852 */     int j = this.size - 1;
/*     */     
/* 854 */     Object[] arrayOfObject = this.elements;
/* 855 */     for (int k = 0; k < i;) {
/* 856 */       Object localObject = arrayOfObject[k];
/* 857 */       arrayOfObject[(k++)] = arrayOfObject[j];
/* 858 */       arrayOfObject[(j--)] = localObject;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void set(int paramInt, Object paramObject)
/*     */   {
/* 870 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 871 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/* 872 */     this.elements[paramInt] = paramObject;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setQuick(int paramInt, Object paramObject)
/*     */   {
/* 884 */     this.elements[paramInt] = paramObject;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void shuffleFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 893 */     if (this.size == 0) return;
/* 894 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 896 */     Uniform localUniform = new Uniform(new DRand(new Date()));
/*     */     
/* 898 */     Object[] arrayOfObject = this.elements;
/*     */     
/* 900 */     for (int j = paramInt1; j < paramInt2; j++) {
/* 901 */       int i = localUniform.nextIntFromTo(j, paramInt2);
/*     */       
/*     */ 
/* 904 */       Object localObject = arrayOfObject[i];
/* 905 */       arrayOfObject[i] = arrayOfObject[j];
/* 906 */       arrayOfObject[j] = localObject;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 915 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ObjectArrayList times(int paramInt)
/*     */   {
/* 922 */     ObjectArrayList localObjectArrayList = new ObjectArrayList(paramInt * this.size);
/* 923 */     int i = paramInt;
/* 924 */     do { localObjectArrayList.addAllOfFromTo(this, 0, size() - 1);i--;
/* 923 */     } while (i >= 0);
/*     */     
/*     */ 
/* 926 */     return localObjectArrayList;
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
/*     */   public Object[] toArray(Object[] paramArrayOfObject)
/*     */   {
/* 950 */     if (paramArrayOfObject.length < this.size) {
/* 951 */       paramArrayOfObject = (Object[])Array.newInstance(paramArrayOfObject.getClass().getComponentType(), this.size);
/*     */     }
/* 953 */     Object[] arrayOfObject = this.elements;
/* 954 */     int i = this.size; do { paramArrayOfObject[i] = arrayOfObject[i];i--; } while (i >= 0);
/*     */     
/* 956 */     if (paramArrayOfObject.length > this.size) { paramArrayOfObject[this.size] = null;
/*     */     }
/* 958 */     return paramArrayOfObject;
/*     */   }
/*     */   
/*     */ 
/*     */   public ArrayList toList()
/*     */   {
/* 964 */     int i = size();
/* 965 */     Object[] arrayOfObject = this.elements;
/* 966 */     ArrayList localArrayList = new ArrayList(i);
/* 967 */     for (int j = 0; j < i; j++) localArrayList.add(arrayOfObject[j]);
/* 968 */     return localArrayList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 975 */     return cern.colt.Arrays.toString(partFromTo(0, size() - 1).elements());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void trimToSize()
/*     */   {
/* 983 */     this.elements = cern.colt.Arrays.trimToCapacity(this.elements, size());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/list/ObjectArrayList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */