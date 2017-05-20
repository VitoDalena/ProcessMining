/*      */ package edu.oswego.cs.dl.util.concurrent;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.lang.reflect.Array;
/*      */ import java.util.AbstractList;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import java.util.NoSuchElementException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CopyOnWriteArrayList
/*      */   implements List, Cloneable, Serializable
/*      */ {
/*      */   protected transient Object[] array_;
/*      */   
/*      */   protected synchronized Object[] array()
/*      */   {
/*   93 */     return this.array_;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public CopyOnWriteArrayList()
/*      */   {
/*  100 */     this.array_ = new Object[0];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CopyOnWriteArrayList(Collection paramCollection)
/*      */   {
/*  109 */     this.array_ = new Object[paramCollection.size()];
/*  110 */     Iterator localIterator = paramCollection.iterator();
/*  111 */     int i = 0;
/*  112 */     while (localIterator.hasNext()) {
/*  113 */       this.array_[(i++)] = localIterator.next();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public CopyOnWriteArrayList(Object[] paramArrayOfObject)
/*      */   {
/*  122 */     copyIn(paramArrayOfObject, 0, paramArrayOfObject.length);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void copyIn(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
/*      */   {
/*  138 */     this.array_ = new Object[paramInt2];
/*  139 */     System.arraycopy(paramArrayOfObject, paramInt1, this.array_, 0, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int size()
/*      */   {
/*  148 */     return array().length;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isEmpty()
/*      */   {
/*  158 */     return size() == 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean contains(Object paramObject)
/*      */   {
/*  167 */     Object[] arrayOfObject = array();
/*  168 */     int i = arrayOfObject.length;
/*  169 */     return indexOf(paramObject, arrayOfObject, i) >= 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int indexOf(Object paramObject)
/*      */   {
/*  182 */     Object[] arrayOfObject = array();
/*  183 */     int i = arrayOfObject.length;
/*  184 */     return indexOf(paramObject, arrayOfObject, i);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected static int indexOf(Object paramObject, Object[] paramArrayOfObject, int paramInt)
/*      */   {
/*      */     int i;
/*      */     
/*      */ 
/*      */ 
/*  196 */     if (paramObject == null) {
/*  197 */       for (i = 0; i < paramInt; i++)
/*  198 */         if (paramArrayOfObject[i] == null)
/*  199 */           return i;
/*      */     } else {
/*  201 */       for (i = 0; i < paramInt; i++)
/*  202 */         if (paramObject.equals(paramArrayOfObject[i]))
/*  203 */           return i;
/*      */     }
/*  205 */     return -1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int indexOf(Object paramObject, int paramInt)
/*      */   {
/*  223 */     Object[] arrayOfObject = array();
/*  224 */     int i = arrayOfObject.length;
/*      */     int j;
/*  226 */     if (paramObject == null) {
/*  227 */       for (j = paramInt; j < i; j++)
/*  228 */         if (arrayOfObject[j] == null)
/*  229 */           return j;
/*      */     } else {
/*  231 */       for (j = paramInt; j < i; j++)
/*  232 */         if (paramObject.equals(arrayOfObject[j]))
/*  233 */           return j;
/*      */     }
/*  235 */     return -1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int lastIndexOf(Object paramObject)
/*      */   {
/*  247 */     Object[] arrayOfObject = array();
/*  248 */     int i = arrayOfObject.length;
/*  249 */     return lastIndexOf(paramObject, arrayOfObject, i);
/*      */   }
/*      */   
/*      */   protected static int lastIndexOf(Object paramObject, Object[] paramArrayOfObject, int paramInt)
/*      */   {
/*      */     int i;
/*  255 */     if (paramObject == null) {
/*  256 */       for (i = paramInt - 1; i >= 0; i--)
/*  257 */         if (paramArrayOfObject[i] == null)
/*  258 */           return i;
/*      */     } else {
/*  260 */       for (i = paramInt - 1; i >= 0; i--)
/*  261 */         if (paramObject.equals(paramArrayOfObject[i]))
/*  262 */           return i;
/*      */     }
/*  264 */     return -1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int lastIndexOf(Object paramObject, int paramInt)
/*      */   {
/*  280 */     Object[] arrayOfObject = array();
/*  281 */     int i; if (paramObject == null) {
/*  282 */       for (i = paramInt; i >= 0; i--)
/*  283 */         if (arrayOfObject[i] == null)
/*  284 */           return i;
/*      */     } else {
/*  286 */       for (i = paramInt; i >= 0; i--)
/*  287 */         if (paramObject.equals(arrayOfObject[i]))
/*  288 */           return i;
/*      */     }
/*  290 */     return -1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object clone()
/*      */   {
/*      */     try
/*      */     {
/*  301 */       Object[] arrayOfObject = array();
/*  302 */       CopyOnWriteArrayList localCopyOnWriteArrayList = (CopyOnWriteArrayList)super.clone();
/*  303 */       localCopyOnWriteArrayList.array_ = new Object[arrayOfObject.length];
/*  304 */       System.arraycopy(arrayOfObject, 0, localCopyOnWriteArrayList.array_, 0, arrayOfObject.length);
/*  305 */       return localCopyOnWriteArrayList;
/*      */     }
/*      */     catch (CloneNotSupportedException localCloneNotSupportedException) {
/*  308 */       throw new InternalError();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object[] toArray()
/*      */   {
/*  317 */     Object[] arrayOfObject1 = array();
/*  318 */     Object[] arrayOfObject2 = new Object[arrayOfObject1.length];
/*  319 */     System.arraycopy(arrayOfObject1, 0, arrayOfObject2, 0, arrayOfObject1.length);
/*  320 */     return arrayOfObject2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object[] toArray(Object[] paramArrayOfObject)
/*      */   {
/*  345 */     Object[] arrayOfObject = array();
/*      */     
/*  347 */     if (paramArrayOfObject.length < arrayOfObject.length) {
/*  348 */       paramArrayOfObject = (Object[])Array.newInstance(paramArrayOfObject.getClass().getComponentType(), arrayOfObject.length);
/*      */     }
/*      */     
/*      */ 
/*  352 */     System.arraycopy(arrayOfObject, 0, paramArrayOfObject, 0, arrayOfObject.length);
/*      */     
/*  354 */     if (paramArrayOfObject.length > arrayOfObject.length) {
/*  355 */       paramArrayOfObject[arrayOfObject.length] = null;
/*      */     }
/*  357 */     return paramArrayOfObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object get(int paramInt)
/*      */   {
/*  370 */     Object[] arrayOfObject = array();
/*  371 */     rangeCheck(paramInt, arrayOfObject.length);
/*  372 */     return arrayOfObject[paramInt];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized Object set(int paramInt, Object paramObject)
/*      */   {
/*  386 */     int i = this.array_.length;
/*  387 */     rangeCheck(paramInt, i);
/*  388 */     Object localObject = this.array_[paramInt];
/*      */     
/*  390 */     int j = (localObject == paramObject) || ((paramObject != null) && (paramObject.equals(localObject))) ? 1 : 0;
/*      */     
/*  392 */     if (j == 0) {
/*  393 */       Object[] arrayOfObject = new Object[i];
/*  394 */       System.arraycopy(this.array_, 0, arrayOfObject, 0, i);
/*  395 */       arrayOfObject[paramInt] = paramObject;
/*  396 */       this.array_ = arrayOfObject;
/*      */     }
/*  398 */     return localObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized boolean add(Object paramObject)
/*      */   {
/*  408 */     int i = this.array_.length;
/*  409 */     Object[] arrayOfObject = new Object[i + 1];
/*  410 */     System.arraycopy(this.array_, 0, arrayOfObject, 0, i);
/*  411 */     arrayOfObject[i] = paramObject;
/*  412 */     this.array_ = arrayOfObject;
/*  413 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void add(int paramInt, Object paramObject)
/*      */   {
/*  427 */     int i = this.array_.length;
/*  428 */     if ((paramInt > i) || (paramInt < 0)) {
/*  429 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + i);
/*      */     }
/*  431 */     Object[] arrayOfObject = new Object[i + 1];
/*  432 */     System.arraycopy(this.array_, 0, arrayOfObject, 0, paramInt);
/*  433 */     arrayOfObject[paramInt] = paramObject;
/*  434 */     System.arraycopy(this.array_, paramInt, arrayOfObject, paramInt + 1, i - paramInt);
/*  435 */     this.array_ = arrayOfObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized Object remove(int paramInt)
/*      */   {
/*  448 */     int i = this.array_.length;
/*  449 */     rangeCheck(paramInt, i);
/*  450 */     Object localObject = this.array_[paramInt];
/*  451 */     Object[] arrayOfObject = new Object[i - 1];
/*  452 */     System.arraycopy(this.array_, 0, arrayOfObject, 0, paramInt);
/*  453 */     int j = i - paramInt - 1;
/*  454 */     if (j > 0)
/*  455 */       System.arraycopy(this.array_, paramInt + 1, arrayOfObject, paramInt, j);
/*  456 */     this.array_ = arrayOfObject;
/*  457 */     return localObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized boolean remove(Object paramObject)
/*      */   {
/*  473 */     int i = this.array_.length;
/*  474 */     if (i == 0) { return false;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  479 */     int j = i - 1;
/*  480 */     Object[] arrayOfObject = new Object[j];
/*      */     
/*  482 */     for (int k = 0; k < j; k++) {
/*  483 */       if ((paramObject == this.array_[k]) || ((paramObject != null) && (paramObject.equals(this.array_[k]))))
/*      */       {
/*      */ 
/*  486 */         for (int m = k + 1; m < i; m++) arrayOfObject[(m - 1)] = this.array_[m];
/*  487 */         this.array_ = arrayOfObject;
/*  488 */         return true;
/*      */       }
/*      */       
/*  491 */       arrayOfObject[k] = this.array_[k];
/*      */     }
/*      */     
/*      */ 
/*  495 */     if ((paramObject == this.array_[j]) || ((paramObject != null) && (paramObject.equals(this.array_[j]))))
/*      */     {
/*  497 */       this.array_ = arrayOfObject;
/*  498 */       return true;
/*      */     }
/*      */     
/*  501 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void removeRange(int paramInt1, int paramInt2)
/*      */   {
/*  520 */     int i = this.array_.length;
/*      */     
/*  522 */     if ((paramInt1 < 0) || (paramInt1 >= i) || (paramInt2 > i) || (paramInt2 < paramInt1))
/*      */     {
/*  524 */       throw new IndexOutOfBoundsException();
/*      */     }
/*  526 */     int j = i - paramInt2;
/*  527 */     int k = i - (paramInt2 - paramInt1);
/*  528 */     Object[] arrayOfObject = new Object[k];
/*  529 */     System.arraycopy(this.array_, 0, arrayOfObject, 0, paramInt1);
/*  530 */     System.arraycopy(this.array_, paramInt2, arrayOfObject, paramInt1, j);
/*  531 */     this.array_ = arrayOfObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized boolean addIfAbsent(Object paramObject)
/*      */   {
/*  545 */     int i = this.array_.length;
/*  546 */     Object[] arrayOfObject = new Object[i + 1];
/*  547 */     for (int j = 0; j < i; j++) {
/*  548 */       if ((paramObject == this.array_[j]) || ((paramObject != null) && (paramObject.equals(this.array_[j]))))
/*      */       {
/*  550 */         return false;
/*      */       }
/*  552 */       arrayOfObject[j] = this.array_[j];
/*      */     }
/*  554 */     arrayOfObject[i] = paramObject;
/*  555 */     this.array_ = arrayOfObject;
/*  556 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean containsAll(Collection paramCollection)
/*      */   {
/*  570 */     Object[] arrayOfObject = array();
/*  571 */     int i = arrayOfObject.length;
/*  572 */     Iterator localIterator = paramCollection.iterator();
/*  573 */     while (localIterator.hasNext()) {
/*  574 */       if (indexOf(localIterator.next(), arrayOfObject, i) < 0)
/*  575 */         return false;
/*      */     }
/*  577 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized boolean removeAll(Collection paramCollection)
/*      */   {
/*  590 */     Object[] arrayOfObject1 = this.array_;
/*  591 */     int i = arrayOfObject1.length;
/*  592 */     if (i == 0) { return false;
/*      */     }
/*      */     
/*  595 */     Object[] arrayOfObject2 = new Object[i];
/*  596 */     int j = 0;
/*  597 */     for (int k = 0; k < i; k++) {
/*  598 */       localObject = arrayOfObject1[k];
/*  599 */       if (!paramCollection.contains(localObject)) {
/*  600 */         arrayOfObject2[(j++)] = localObject;
/*      */       }
/*      */     }
/*      */     
/*  604 */     if (j == i) { return false;
/*      */     }
/*      */     
/*  607 */     Object localObject = new Object[j];
/*  608 */     System.arraycopy(arrayOfObject2, 0, localObject, 0, j);
/*  609 */     this.array_ = ((Object[])localObject);
/*  610 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized boolean retainAll(Collection paramCollection)
/*      */   {
/*  621 */     Object[] arrayOfObject1 = this.array_;
/*  622 */     int i = arrayOfObject1.length;
/*  623 */     if (i == 0) { return false;
/*      */     }
/*  625 */     Object[] arrayOfObject2 = new Object[i];
/*  626 */     int j = 0;
/*  627 */     for (int k = 0; k < i; k++) {
/*  628 */       localObject = arrayOfObject1[k];
/*  629 */       if (paramCollection.contains(localObject)) {
/*  630 */         arrayOfObject2[(j++)] = localObject;
/*      */       }
/*      */     }
/*      */     
/*  634 */     if (j == i) { return false;
/*      */     }
/*  636 */     Object localObject = new Object[j];
/*  637 */     System.arraycopy(arrayOfObject2, 0, localObject, 0, j);
/*  638 */     this.array_ = ((Object[])localObject);
/*  639 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized int addAllAbsent(Collection paramCollection)
/*      */   {
/*  653 */     int i = paramCollection.size();
/*  654 */     if (i == 0) { return 0;
/*      */     }
/*  656 */     Object[] arrayOfObject1 = this.array_;
/*  657 */     int j = arrayOfObject1.length;
/*      */     
/*  659 */     Object[] arrayOfObject2 = new Object[i];
/*  660 */     int k = 0;
/*  661 */     Iterator localIterator = paramCollection.iterator();
/*  662 */     while (localIterator.hasNext()) {
/*  663 */       localObject = localIterator.next();
/*  664 */       if ((indexOf(localObject, arrayOfObject1, j) < 0) && 
/*  665 */         (indexOf(localObject, arrayOfObject2, k) < 0)) {
/*  666 */         arrayOfObject2[(k++)] = localObject;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  671 */     if (k == 0) { return 0;
/*      */     }
/*  673 */     Object localObject = new Object[j + k];
/*  674 */     System.arraycopy(arrayOfObject1, 0, localObject, 0, j);
/*  675 */     System.arraycopy(arrayOfObject2, 0, localObject, j, k);
/*  676 */     this.array_ = ((Object[])localObject);
/*  677 */     return k;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void clear()
/*      */   {
/*  685 */     this.array_ = new Object[0];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized boolean addAll(Collection paramCollection)
/*      */   {
/*  696 */     int i = paramCollection.size();
/*  697 */     if (i == 0) { return false;
/*      */     }
/*  699 */     int j = this.array_.length;
/*  700 */     Object[] arrayOfObject = new Object[j + i];
/*  701 */     System.arraycopy(this.array_, 0, arrayOfObject, 0, j);
/*  702 */     Iterator localIterator = paramCollection.iterator();
/*  703 */     for (int k = 0; k < i; k++)
/*  704 */       arrayOfObject[(j++)] = localIterator.next();
/*  705 */     this.array_ = arrayOfObject;
/*      */     
/*  707 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized boolean addAll(int paramInt, Collection paramCollection)
/*      */   {
/*  725 */     int i = this.array_.length;
/*  726 */     if ((paramInt > i) || (paramInt < 0)) {
/*  727 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + i);
/*      */     }
/*  729 */     int j = paramCollection.size();
/*  730 */     if (j == 0) { return false;
/*      */     }
/*  732 */     Object[] arrayOfObject = new Object[i + j];
/*  733 */     System.arraycopy(this.array_, 0, arrayOfObject, 0, i);
/*  734 */     int k = i - paramInt;
/*  735 */     if (k > 0)
/*  736 */       System.arraycopy(this.array_, paramInt, arrayOfObject, paramInt + j, k);
/*  737 */     Iterator localIterator = paramCollection.iterator();
/*  738 */     for (int m = 0; m < j; m++)
/*  739 */       arrayOfObject[(paramInt++)] = localIterator.next();
/*  740 */     this.array_ = arrayOfObject;
/*      */     
/*  742 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void rangeCheck(int paramInt1, int paramInt2)
/*      */   {
/*  750 */     if ((paramInt1 >= paramInt2) || (paramInt1 < 0)) {
/*  751 */       throw new IndexOutOfBoundsException("Index: " + paramInt1 + ", Size: " + paramInt2);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void writeObject(ObjectOutputStream paramObjectOutputStream)
/*      */     throws IOException
/*      */   {
/*  763 */     paramObjectOutputStream.defaultWriteObject();
/*      */     
/*  765 */     Object[] arrayOfObject = array();
/*      */     
/*  767 */     paramObjectOutputStream.writeInt(arrayOfObject.length);
/*      */     
/*      */ 
/*  770 */     for (int i = 0; i < arrayOfObject.length; i++) {
/*  771 */       paramObjectOutputStream.writeObject(arrayOfObject[i]);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private synchronized void readObject(ObjectInputStream paramObjectInputStream)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/*  780 */     paramObjectInputStream.defaultReadObject();
/*      */     
/*      */ 
/*  783 */     int i = paramObjectInputStream.readInt();
/*  784 */     Object[] arrayOfObject = new Object[i];
/*      */     
/*      */ 
/*  787 */     for (int j = 0; j < arrayOfObject.length; j++)
/*  788 */       arrayOfObject[j] = paramObjectInputStream.readObject();
/*  789 */     this.array_ = arrayOfObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/*  797 */     StringBuffer localStringBuffer = new StringBuffer();
/*  798 */     Iterator localIterator = iterator();
/*  799 */     localStringBuffer.append("[");
/*  800 */     int i = size() - 1;
/*  801 */     for (int j = 0; j <= i; j++) {
/*  802 */       localStringBuffer.append(String.valueOf(localIterator.next()));
/*  803 */       if (j < i)
/*  804 */         localStringBuffer.append(", ");
/*      */     }
/*  806 */     localStringBuffer.append("]");
/*  807 */     return localStringBuffer.toString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Object paramObject)
/*      */   {
/*  833 */     if (paramObject == this)
/*  834 */       return true;
/*  835 */     if (!(paramObject instanceof List)) {
/*  836 */       return false;
/*      */     }
/*  838 */     List localList = (List)paramObject;
/*  839 */     if (size() != localList.size()) {
/*  840 */       return false;
/*      */     }
/*  842 */     ListIterator localListIterator1 = listIterator();
/*  843 */     ListIterator localListIterator2 = localList.listIterator();
/*  844 */     while (localListIterator1.hasNext()) {
/*  845 */       Object localObject1 = localListIterator1.next();
/*  846 */       Object localObject2 = localListIterator2.next();
/*  847 */       if (!(localObject1 == null ? false : localObject2 == null ? true : localObject1.equals(localObject2)))
/*  848 */         return false;
/*      */     }
/*  850 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int hashCode()
/*      */   {
/*  860 */     int i = 1;
/*  861 */     Iterator localIterator = iterator();
/*  862 */     while (localIterator.hasNext()) {
/*  863 */       Object localObject = localIterator.next();
/*  864 */       i = 31 * i + (localObject == null ? 0 : localObject.hashCode());
/*      */     }
/*  866 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Iterator iterator()
/*      */   {
/*  877 */     return new COWIterator(array(), 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ListIterator listIterator()
/*      */   {
/*  891 */     return new COWIterator(array(), 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ListIterator listIterator(int paramInt)
/*      */   {
/*  910 */     Object[] arrayOfObject = array();
/*  911 */     int i = arrayOfObject.length;
/*  912 */     if ((paramInt < 0) || (paramInt > i)) {
/*  913 */       throw new IndexOutOfBoundsException("Index: " + paramInt);
/*      */     }
/*  915 */     return new COWIterator(array(), paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */   protected static class COWIterator
/*      */     implements ListIterator
/*      */   {
/*      */     protected final Object[] array;
/*      */     
/*      */     protected int cursor;
/*      */     
/*      */ 
/*      */     protected COWIterator(Object[] paramArrayOfObject, int paramInt)
/*      */     {
/*  929 */       this.array = paramArrayOfObject;
/*  930 */       this.cursor = paramInt;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  934 */       return this.cursor < this.array.length;
/*      */     }
/*      */     
/*      */     public boolean hasPrevious() {
/*  938 */       return this.cursor > 0;
/*      */     }
/*      */     
/*      */     public Object next() {
/*      */       try {
/*  943 */         return this.array[(this.cursor++)];
/*      */       }
/*      */       catch (IndexOutOfBoundsException localIndexOutOfBoundsException) {
/*  946 */         throw new NoSuchElementException();
/*      */       }
/*      */     }
/*      */     
/*      */     public Object previous() {
/*      */       try {
/*  952 */         return this.array[(--this.cursor)];
/*      */       } catch (IndexOutOfBoundsException localIndexOutOfBoundsException) {
/*  954 */         throw new NoSuchElementException();
/*      */       }
/*      */     }
/*      */     
/*      */     public int nextIndex() {
/*  959 */       return this.cursor;
/*      */     }
/*      */     
/*      */     public int previousIndex() {
/*  963 */       return this.cursor - 1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public void remove()
/*      */     {
/*  973 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public void set(Object paramObject)
/*      */     {
/*  982 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public void add(Object paramObject)
/*      */     {
/*  991 */       throw new UnsupportedOperationException();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized List subList(int paramInt1, int paramInt2)
/*      */   {
/* 1017 */     int i = this.array_.length;
/* 1018 */     if ((paramInt1 < 0) || (paramInt2 > i) || (paramInt1 > paramInt2))
/* 1019 */       throw new IndexOutOfBoundsException();
/* 1020 */     return new COWSubList(this, paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected static class COWSubList
/*      */     extends AbstractList
/*      */   {
/*      */     protected final CopyOnWriteArrayList l;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     protected final int offset;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     protected int size;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     protected Object[] expectedArray;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     protected COWSubList(CopyOnWriteArrayList paramCopyOnWriteArrayList, int paramInt1, int paramInt2)
/*      */     {
/* 1052 */       this.l = paramCopyOnWriteArrayList;
/* 1053 */       this.expectedArray = this.l.array();
/* 1054 */       this.offset = paramInt1;
/* 1055 */       this.size = (paramInt2 - paramInt1);
/*      */     }
/*      */     
/*      */     protected void checkForComodification()
/*      */     {
/* 1060 */       if (this.l.array_ != this.expectedArray) {
/* 1061 */         throw new ConcurrentModificationException();
/*      */       }
/*      */     }
/*      */     
/*      */     protected void rangeCheck(int paramInt) {
/* 1066 */       if ((paramInt < 0) || (paramInt >= this.size)) {
/* 1067 */         throw new IndexOutOfBoundsException("Index: " + paramInt + ",Size: " + this.size);
/*      */       }
/*      */     }
/*      */     
/*      */     public Object set(int paramInt, Object paramObject)
/*      */     {
/* 1073 */       synchronized (this.l) {
/* 1074 */         rangeCheck(paramInt);
/* 1075 */         checkForComodification();
/* 1076 */         Object localObject1 = this.l.set(paramInt + this.offset, paramObject);
/* 1077 */         this.expectedArray = this.l.array_;
/* 1078 */         Object localObject2 = localObject1;return localObject2;
/*      */       }
/*      */     }
/*      */     
/*      */     public Object get(int paramInt) {
/* 1083 */       synchronized (this.l) {
/* 1084 */         rangeCheck(paramInt);
/* 1085 */         checkForComodification();
/* 1086 */         Object localObject1 = this.l.get(paramInt + this.offset);return localObject1;
/*      */       }
/*      */     }
/*      */     
/*      */     public int size() {
/* 1091 */       synchronized (this.l) {
/* 1092 */         checkForComodification();
/* 1093 */         int i = this.size;return i;
/*      */       }
/*      */     }
/*      */     
/*      */     public void add(int paramInt, Object paramObject) {
/* 1098 */       synchronized (this.l) {
/* 1099 */         checkForComodification();
/* 1100 */         if ((paramInt < 0) || (paramInt > this.size))
/* 1101 */           throw new IndexOutOfBoundsException();
/* 1102 */         this.l.add(paramInt + this.offset, paramObject);
/* 1103 */         this.expectedArray = this.l.array_;
/* 1104 */         this.size += 1;
/*      */       }
/*      */     }
/*      */     
/*      */     public Object remove(int paramInt) {
/* 1109 */       synchronized (this.l) {
/* 1110 */         rangeCheck(paramInt);
/* 1111 */         checkForComodification();
/* 1112 */         Object localObject1 = this.l.remove(paramInt + this.offset);
/* 1113 */         this.expectedArray = this.l.array_;
/* 1114 */         this.size -= 1;
/* 1115 */         Object localObject2 = localObject1;return localObject2;
/*      */       }
/*      */     }
/*      */     
/*      */     public Iterator iterator() {
/* 1120 */       synchronized (this.l) {
/* 1121 */         checkForComodification();
/* 1122 */         COWSubListIterator localCOWSubListIterator = new COWSubListIterator(0);return localCOWSubListIterator;
/*      */       }
/*      */     }
/*      */     
/*      */     public ListIterator listIterator(int paramInt) {
/* 1127 */       synchronized (this.l) {
/* 1128 */         checkForComodification();
/* 1129 */         if ((paramInt < 0) || (paramInt > this.size))
/* 1130 */           throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/* 1131 */         COWSubListIterator localCOWSubListIterator = new COWSubListIterator(paramInt);return localCOWSubListIterator;
/*      */       }
/*      */     }
/*      */     
/*      */     protected class COWSubListIterator implements ListIterator {
/*      */       protected final ListIterator i;
/*      */       protected final int index;
/*      */       
/* 1139 */       protected COWSubListIterator(int paramInt) { this.index = paramInt;
/* 1140 */         this.i = CopyOnWriteArrayList.COWSubList.this.l.listIterator(paramInt + CopyOnWriteArrayList.COWSubList.this.offset);
/*      */       }
/*      */       
/*      */       public boolean hasNext() {
/* 1144 */         return nextIndex() < CopyOnWriteArrayList.COWSubList.this.size;
/*      */       }
/*      */       
/*      */       public Object next() {
/* 1148 */         if (hasNext()) {
/* 1149 */           return this.i.next();
/*      */         }
/* 1151 */         throw new NoSuchElementException();
/*      */       }
/*      */       
/*      */       public boolean hasPrevious() {
/* 1155 */         return previousIndex() >= 0;
/*      */       }
/*      */       
/*      */       public Object previous() {
/* 1159 */         if (hasPrevious()) {
/* 1160 */           return this.i.previous();
/*      */         }
/* 1162 */         throw new NoSuchElementException();
/*      */       }
/*      */       
/*      */       public int nextIndex() {
/* 1166 */         return this.i.nextIndex() - CopyOnWriteArrayList.COWSubList.this.offset;
/*      */       }
/*      */       
/*      */       public int previousIndex() {
/* 1170 */         return this.i.previousIndex() - CopyOnWriteArrayList.COWSubList.this.offset;
/*      */       }
/*      */       
/*      */       public void remove() {
/* 1174 */         throw new UnsupportedOperationException();
/*      */       }
/*      */       
/*      */       public void set(Object paramObject) {
/* 1178 */         throw new UnsupportedOperationException();
/*      */       }
/*      */       
/*      */       public void add(Object paramObject) {
/* 1182 */         throw new UnsupportedOperationException();
/*      */       }
/*      */     }
/*      */     
/*      */     public List subList(int paramInt1, int paramInt2)
/*      */     {
/* 1188 */       synchronized (this.l) {
/* 1189 */         checkForComodification();
/* 1190 */         if ((paramInt1 < 0) || (paramInt2 > this.size))
/* 1191 */           throw new IndexOutOfBoundsException();
/* 1192 */         COWSubList localCOWSubList = new COWSubList(this.l, paramInt1 + this.offset, paramInt2 + this.offset);return localCOWSubList;
/*      */       }
/*      */     }
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/CopyOnWriteArrayList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */