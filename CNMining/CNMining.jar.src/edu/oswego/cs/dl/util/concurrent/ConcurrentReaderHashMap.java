/*      */ package edu.oswego.cs.dl.util.concurrent;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.AbstractCollection;
/*      */ import java.util.AbstractMap;
/*      */ import java.util.AbstractSet;
/*      */ import java.util.Collection;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
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
/*      */ public class ConcurrentReaderHashMap
/*      */   extends AbstractMap
/*      */   implements Map, Cloneable, Serializable
/*      */ {
/*  182 */   protected final Object barrierLock = new Object();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected transient Object lastWrite;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected final void recordModification(Object paramObject)
/*      */   {
/*  196 */     synchronized (this.barrierLock) {
/*  197 */       this.lastWrite = paramObject;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected final Entry[] getTableForReading()
/*      */   {
/*  207 */     synchronized (this.barrierLock) {
/*  208 */       Entry[] arrayOfEntry = this.table;return arrayOfEntry;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  217 */   public static int DEFAULT_INITIAL_CAPACITY = 32;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int MINIMUM_CAPACITY = 4;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int MAXIMUM_CAPACITY = 1073741824;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final float DEFAULT_LOAD_FACTOR = 0.75F;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected transient Entry[] table;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected transient int count;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int threshold;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected float loadFactor;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private int p2capacity(int paramInt)
/*      */   {
/*  272 */     int i = paramInt;
/*      */     
/*      */     int j;
/*      */     
/*  276 */     if ((i > 1073741824) || (i < 0)) {
/*  277 */       j = 1073741824;
/*      */     } else {
/*  279 */       j = 4;
/*  280 */       while (j < i)
/*  281 */         j <<= 1;
/*      */     }
/*  283 */     return j;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static int hash(Object paramObject)
/*      */   {
/*  292 */     int i = paramObject.hashCode();
/*      */     
/*      */ 
/*      */ 
/*  296 */     return (i << 7) - i + (i >>> 9) + (i >>> 17);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected boolean eq(Object paramObject1, Object paramObject2)
/*      */   {
/*  303 */     return (paramObject1 == paramObject2) || (paramObject1.equals(paramObject2));
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
/*      */   public ConcurrentReaderHashMap(int paramInt, float paramFloat)
/*      */   {
/*  319 */     if (paramFloat <= 0.0F) {
/*  320 */       throw new IllegalArgumentException("Illegal Load factor: " + paramFloat);
/*      */     }
/*  322 */     this.loadFactor = paramFloat;
/*      */     
/*  324 */     int i = p2capacity(paramInt);
/*      */     
/*  326 */     this.table = new Entry[i];
/*  327 */     this.threshold = ((int)(i * paramFloat));
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
/*      */   public ConcurrentReaderHashMap(int paramInt)
/*      */   {
/*  342 */     this(paramInt, 0.75F);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ConcurrentReaderHashMap()
/*      */   {
/*  351 */     this(DEFAULT_INITIAL_CAPACITY, 0.75F);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ConcurrentReaderHashMap(Map paramMap)
/*      */   {
/*  361 */     this(Math.max((int)(paramMap.size() / 0.75F) + 1, 16), 0.75F);
/*      */     
/*  363 */     putAll(paramMap);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized int size()
/*      */   {
/*  373 */     return this.count;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized boolean isEmpty()
/*      */   {
/*  383 */     return this.count == 0;
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
/*      */   public Object get(Object paramObject)
/*      */   {
/*  404 */     int i = hash(paramObject);
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
/*  415 */     Entry[] arrayOfEntry = this.table;
/*  416 */     int j = i & arrayOfEntry.length - 1;
/*  417 */     Entry localEntry1 = arrayOfEntry[j];
/*  418 */     Entry localEntry2 = localEntry1;
/*      */     for (;;)
/*      */     {
/*  421 */       if (localEntry2 == null)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  426 */         arrayOfEntry = getTableForReading();
/*  427 */         if (localEntry1 == arrayOfEntry[j]) {
/*  428 */           return null;
/*      */         }
/*      */         
/*  431 */         localEntry2 = localEntry1 = arrayOfEntry[(j = i & arrayOfEntry.length - 1)];
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/*  436 */       else if ((localEntry2.hash == i) && (eq(paramObject, localEntry2.key))) {
/*  437 */         Object localObject1 = localEntry2.value;
/*  438 */         if (localObject1 != null) {
/*  439 */           return localObject1;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  446 */         synchronized (this) {
/*  447 */           arrayOfEntry = this.table;
/*      */         }
/*  449 */         localEntry2 = localEntry1 = arrayOfEntry[(j = i & arrayOfEntry.length - 1)];
/*      */       }
/*      */       else
/*      */       {
/*  453 */         localEntry2 = localEntry2.next;
/*      */       }
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
/*      */   public boolean containsKey(Object paramObject)
/*      */   {
/*  472 */     return get(paramObject) != null;
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
/*      */   public Object put(Object paramObject1, Object paramObject2)
/*      */   {
/*  494 */     if (paramObject2 == null) {
/*  495 */       throw new NullPointerException();
/*      */     }
/*  497 */     int i = hash(paramObject1);
/*  498 */     Entry[] arrayOfEntry = this.table;
/*  499 */     int j = i & arrayOfEntry.length - 1;
/*  500 */     Entry localEntry1 = arrayOfEntry[j];
/*      */     
/*      */ 
/*  503 */     for (Entry localEntry2 = localEntry1; localEntry2 != null; localEntry2 = localEntry2.next) {
/*  504 */       if ((localEntry2.hash == i) && (eq(paramObject1, localEntry2.key)))
/*      */         break;
/*      */     }
/*  507 */     synchronized (this) {
/*  508 */       arrayOfEntry = this.table;
/*  509 */       Object localObject2; if (localEntry2 == null)
/*      */       {
/*  511 */         if (localEntry1 == arrayOfEntry[j])
/*      */         {
/*  513 */           localObject1 = new Entry(i, paramObject1, paramObject2, localEntry1);
/*  514 */           arrayOfEntry[j] = localObject1;
/*  515 */           if (++this.count >= this.threshold) rehash(); else
/*  516 */             recordModification(localObject1);
/*  517 */           localObject2 = null;return localObject2;
/*      */         }
/*      */       }
/*      */       else {
/*  521 */         localObject1 = localEntry2.value;
/*  522 */         if ((localEntry1 == arrayOfEntry[j]) && (localObject1 != null)) {
/*  523 */           localEntry2.value = paramObject2;
/*  524 */           localObject2 = localObject1;return localObject2;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  529 */       Object localObject1 = sput(paramObject1, paramObject2, i);return localObject1;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Object sput(Object paramObject1, Object paramObject2, int paramInt)
/*      */   {
/*  540 */     Entry[] arrayOfEntry = this.table;
/*  541 */     int i = paramInt & arrayOfEntry.length - 1;
/*  542 */     Entry localEntry1 = arrayOfEntry[i];
/*  543 */     Entry localEntry2 = localEntry1;
/*      */     for (;;) {
/*      */       Object localObject;
/*  546 */       if (localEntry2 == null) {
/*  547 */         localObject = new Entry(paramInt, paramObject1, paramObject2, localEntry1);
/*  548 */         arrayOfEntry[i] = localObject;
/*  549 */         if (++this.count >= this.threshold) rehash(); else
/*  550 */           recordModification(localObject);
/*  551 */         return null;
/*      */       }
/*  553 */       if ((localEntry2.hash == paramInt) && (eq(paramObject1, localEntry2.key))) {
/*  554 */         localObject = localEntry2.value;
/*  555 */         localEntry2.value = paramObject2;
/*  556 */         return localObject;
/*      */       }
/*      */       
/*  559 */       localEntry2 = localEntry2.next;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void rehash()
/*      */   {
/*  570 */     Entry[] arrayOfEntry1 = this.table;
/*  571 */     int i = arrayOfEntry1.length;
/*  572 */     if (i >= 1073741824) {
/*  573 */       this.threshold = Integer.MAX_VALUE;
/*  574 */       return;
/*      */     }
/*      */     
/*  577 */     int j = i << 1;
/*  578 */     int k = j - 1;
/*  579 */     this.threshold = ((int)(j * this.loadFactor));
/*      */     
/*  581 */     Entry[] arrayOfEntry2 = new Entry[j];
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
/*  596 */     for (int m = 0; m < i; m++)
/*      */     {
/*      */ 
/*  599 */       Entry localEntry1 = arrayOfEntry1[m];
/*      */       
/*  601 */       if (localEntry1 != null) {
/*  602 */         int n = localEntry1.hash & k;
/*  603 */         Entry localEntry2 = localEntry1.next;
/*      */         
/*      */ 
/*  606 */         if (localEntry2 == null) {
/*  607 */           arrayOfEntry2[n] = localEntry1;
/*      */         }
/*      */         else
/*      */         {
/*  611 */           Object localObject = localEntry1;
/*  612 */           int i1 = n;
/*  613 */           for (Entry localEntry3 = localEntry2; localEntry3 != null; localEntry3 = localEntry3.next) {
/*  614 */             int i2 = localEntry3.hash & k;
/*  615 */             if (i2 != i1) {
/*  616 */               i1 = i2;
/*  617 */               localObject = localEntry3;
/*      */             }
/*      */           }
/*  620 */           arrayOfEntry2[i1] = localObject;
/*      */           
/*      */ 
/*  623 */           for (Entry localEntry4 = localEntry1; localEntry4 != localObject; localEntry4 = localEntry4.next) {
/*  624 */             int i3 = localEntry4.hash & k;
/*  625 */             arrayOfEntry2[i3] = new Entry(localEntry4.hash, localEntry4.key, localEntry4.value, arrayOfEntry2[i3]);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  632 */     this.table = arrayOfEntry2;
/*  633 */     recordModification(arrayOfEntry2);
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
/*      */   public Object remove(Object paramObject)
/*      */   {
/*  659 */     int i = hash(paramObject);
/*  660 */     Entry[] arrayOfEntry = this.table;
/*  661 */     int j = i & arrayOfEntry.length - 1;
/*  662 */     Entry localEntry1 = arrayOfEntry[j];
/*  663 */     Entry localEntry2 = localEntry1;
/*      */     
/*  665 */     for (localEntry2 = localEntry1; localEntry2 != null; localEntry2 = localEntry2.next) {
/*  666 */       if ((localEntry2.hash == i) && (eq(paramObject, localEntry2.key))) {
/*      */         break;
/*      */       }
/*      */     }
/*  670 */     synchronized (this) {
/*  671 */       arrayOfEntry = this.table;
/*  672 */       if (localEntry2 == null) {
/*  673 */         if (localEntry1 == arrayOfEntry[j]) {
/*  674 */           localObject1 = null;return localObject1;
/*      */         }
/*      */       } else {
/*  677 */         localObject1 = localEntry2.value;
/*  678 */         if ((localEntry1 == arrayOfEntry[j]) && (localObject1 != null)) {
/*  679 */           localEntry2.value = null;
/*  680 */           this.count -= 1;
/*      */           
/*  682 */           Entry localEntry3 = localEntry2.next;
/*  683 */           for (Entry localEntry4 = localEntry1; localEntry4 != localEntry2; localEntry4 = localEntry4.next) {
/*  684 */             localEntry3 = new Entry(localEntry4.hash, localEntry4.key, localEntry4.value, localEntry3);
/*      */           }
/*  686 */           arrayOfEntry[j] = localEntry3;
/*  687 */           recordModification(localEntry3);
/*  688 */           Object localObject2 = localObject1;return localObject2;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  693 */       Object localObject1 = sremove(paramObject, i);return localObject1;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Object sremove(Object paramObject, int paramInt)
/*      */   {
/*  703 */     Entry[] arrayOfEntry = this.table;
/*  704 */     int i = paramInt & arrayOfEntry.length - 1;
/*  705 */     Entry localEntry1 = arrayOfEntry[i];
/*      */     
/*  707 */     for (Entry localEntry2 = localEntry1; localEntry2 != null; localEntry2 = localEntry2.next) {
/*  708 */       if ((localEntry2.hash == paramInt) && (eq(paramObject, localEntry2.key))) {
/*  709 */         Object localObject = localEntry2.value;
/*  710 */         localEntry2.value = null;
/*  711 */         this.count -= 1;
/*  712 */         Entry localEntry3 = localEntry2.next;
/*  713 */         for (Entry localEntry4 = localEntry1; localEntry4 != localEntry2; localEntry4 = localEntry4.next) {
/*  714 */           localEntry3 = new Entry(localEntry4.hash, localEntry4.key, localEntry4.value, localEntry3);
/*      */         }
/*  716 */         arrayOfEntry[i] = localEntry3;
/*  717 */         recordModification(localEntry3);
/*  718 */         return localObject;
/*      */       }
/*      */     }
/*  721 */     return null;
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
/*      */   public boolean containsValue(Object paramObject)
/*      */   {
/*  738 */     if (paramObject == null) { throw new NullPointerException();
/*      */     }
/*  740 */     Entry[] arrayOfEntry = getTableForReading();
/*      */     
/*  742 */     for (int i = 0; i < arrayOfEntry.length; i++) {
/*  743 */       for (Entry localEntry = arrayOfEntry[i]; localEntry != null; localEntry = localEntry.next) {
/*  744 */         if (paramObject.equals(localEntry.value))
/*  745 */           return true;
/*      */       }
/*      */     }
/*  748 */     return false;
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
/*      */   public boolean contains(Object paramObject)
/*      */   {
/*  771 */     return containsValue(paramObject);
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
/*      */   public synchronized void putAll(Map paramMap)
/*      */   {
/*  785 */     int i = paramMap.size();
/*  786 */     if (i == 0) {
/*  787 */       return;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  792 */     while (i >= this.threshold) {
/*  793 */       rehash();
/*      */     }
/*  795 */     for (Iterator localIterator = paramMap.entrySet().iterator(); localIterator.hasNext();) {
/*  796 */       Map.Entry localEntry = (Map.Entry)localIterator.next();
/*  797 */       Object localObject1 = localEntry.getKey();
/*  798 */       Object localObject2 = localEntry.getValue();
/*  799 */       put(localObject1, localObject2);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void clear()
/*      */   {
/*  808 */     Entry[] arrayOfEntry = this.table;
/*  809 */     for (int i = 0; i < arrayOfEntry.length; i++)
/*      */     {
/*      */ 
/*  812 */       for (Entry localEntry = arrayOfEntry[i]; localEntry != null; localEntry = localEntry.next) {
/*  813 */         localEntry.value = null;
/*      */       }
/*  815 */       arrayOfEntry[i] = null;
/*      */     }
/*  817 */     this.count = 0;
/*  818 */     recordModification(arrayOfEntry);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized Object clone()
/*      */   {
/*      */     try
/*      */     {
/*  831 */       ConcurrentReaderHashMap localConcurrentReaderHashMap = (ConcurrentReaderHashMap)super.clone();
/*      */       
/*  833 */       localConcurrentReaderHashMap.keySet = null;
/*  834 */       localConcurrentReaderHashMap.entrySet = null;
/*  835 */       localConcurrentReaderHashMap.values = null;
/*      */       
/*  837 */       Entry[] arrayOfEntry1 = this.table;
/*  838 */       localConcurrentReaderHashMap.table = new Entry[arrayOfEntry1.length];
/*  839 */       Entry[] arrayOfEntry2 = localConcurrentReaderHashMap.table;
/*      */       
/*  841 */       for (int i = 0; i < arrayOfEntry1.length; i++) {
/*  842 */         Entry localEntry1 = null;
/*  843 */         for (Entry localEntry2 = arrayOfEntry1[i]; localEntry2 != null; localEntry2 = localEntry2.next)
/*  844 */           localEntry1 = new Entry(localEntry2.hash, localEntry2.key, localEntry2.value, localEntry1);
/*  845 */         arrayOfEntry2[i] = localEntry1;
/*      */       }
/*      */       
/*  848 */       return localConcurrentReaderHashMap;
/*      */     }
/*      */     catch (CloneNotSupportedException localCloneNotSupportedException)
/*      */     {
/*  852 */       throw new InternalError();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*  858 */   protected transient Set keySet = null;
/*  859 */   protected transient Set entrySet = null;
/*  860 */   protected transient Collection values = null;
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
/*      */   public Set keySet()
/*      */   {
/*  875 */     Set localSet = this.keySet;
/*  876 */     return localSet != null ? localSet : (this.keySet = new KeySet(null));
/*      */   }
/*      */   
/*  879 */   private class KeySet extends AbstractSet { KeySet(ConcurrentReaderHashMap.1 param1) { this(); }
/*      */     
/*  881 */     public Iterator iterator() { return new ConcurrentReaderHashMap.KeyIterator(ConcurrentReaderHashMap.this); }
/*      */     
/*      */     public int size() {
/*  884 */       return ConcurrentReaderHashMap.this.size();
/*      */     }
/*      */     
/*  887 */     public boolean contains(Object paramObject) { return ConcurrentReaderHashMap.this.containsKey(paramObject); }
/*      */     
/*      */     public boolean remove(Object paramObject) {
/*  890 */       return ConcurrentReaderHashMap.this.remove(paramObject) != null;
/*      */     }
/*      */     
/*  893 */     public void clear() { ConcurrentReaderHashMap.this.clear(); }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private KeySet() {}
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Collection values()
/*      */   {
/*  910 */     Collection localCollection = this.values;
/*  911 */     return localCollection != null ? localCollection : (this.values = new Values(null));
/*      */   }
/*      */   
/*  914 */   private class Values extends AbstractCollection { Values(ConcurrentReaderHashMap.1 param1) { this(); }
/*      */     
/*  916 */     public Iterator iterator() { return new ConcurrentReaderHashMap.ValueIterator(ConcurrentReaderHashMap.this); }
/*      */     
/*      */     public int size() {
/*  919 */       return ConcurrentReaderHashMap.this.size();
/*      */     }
/*      */     
/*  922 */     public boolean contains(Object paramObject) { return ConcurrentReaderHashMap.this.containsValue(paramObject); }
/*      */     
/*      */     public void clear() {
/*  925 */       ConcurrentReaderHashMap.this.clear();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private Values() {}
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Set entrySet()
/*      */   {
/*  943 */     Set localSet = this.entrySet;
/*  944 */     return localSet != null ? localSet : (this.entrySet = new EntrySet(null));
/*      */   }
/*      */   
/*  947 */   private class EntrySet extends AbstractSet { EntrySet(ConcurrentReaderHashMap.1 param1) { this(); }
/*      */     
/*  949 */     public Iterator iterator() { return new ConcurrentReaderHashMap.HashIterator(ConcurrentReaderHashMap.this); }
/*      */     
/*      */     public boolean contains(Object paramObject) {
/*  952 */       if (!(paramObject instanceof Map.Entry))
/*  953 */         return false;
/*  954 */       Map.Entry localEntry = (Map.Entry)paramObject;
/*  955 */       Object localObject = ConcurrentReaderHashMap.this.get(localEntry.getKey());
/*  956 */       return (localObject != null) && (localObject.equals(localEntry.getValue()));
/*      */     }
/*      */     
/*  959 */     public boolean remove(Object paramObject) { if (!(paramObject instanceof Map.Entry))
/*  960 */         return false;
/*  961 */       return ConcurrentReaderHashMap.this.findAndRemoveEntry((Map.Entry)paramObject);
/*      */     }
/*      */     
/*  964 */     public int size() { return ConcurrentReaderHashMap.this.size(); }
/*      */     
/*      */     public void clear() {
/*  967 */       ConcurrentReaderHashMap.this.clear();
/*      */     }
/*      */     
/*      */     private EntrySet() {}
/*      */   }
/*      */   
/*      */   protected synchronized boolean findAndRemoveEntry(Map.Entry paramEntry)
/*      */   {
/*  975 */     Object localObject1 = paramEntry.getKey();
/*  976 */     Object localObject2 = get(localObject1);
/*  977 */     if ((localObject2 != null) && (localObject2.equals(paramEntry.getValue()))) {
/*  978 */       remove(localObject1);
/*  979 */       return true;
/*      */     }
/*      */     
/*  982 */     return false;
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
/*      */   public Enumeration keys()
/*      */   {
/*  995 */     return new KeyIterator();
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
/*      */   public Enumeration elements()
/*      */   {
/* 1011 */     return new ValueIterator();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected static class Entry
/*      */     implements Map.Entry
/*      */   {
/*      */     protected final int hash;
/*      */     
/*      */ 
/*      */     protected final Object key;
/*      */     
/*      */ 
/*      */     protected final Entry next;
/*      */     
/*      */ 
/*      */     protected volatile Object value;
/*      */     
/*      */ 
/*      */ 
/*      */     Entry(int paramInt, Object paramObject1, Object paramObject2, Entry paramEntry)
/*      */     {
/* 1034 */       this.hash = paramInt;
/* 1035 */       this.key = paramObject1;
/* 1036 */       this.next = paramEntry;
/* 1037 */       this.value = paramObject2;
/*      */     }
/*      */     
/*      */ 
/*      */     public Object getKey()
/*      */     {
/* 1043 */       return this.key;
/*      */     }
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
/*      */     public Object getValue()
/*      */     {
/* 1059 */       return this.value;
/*      */     }
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
/*      */     public Object setValue(Object paramObject)
/*      */     {
/* 1084 */       if (paramObject == null)
/* 1085 */         throw new NullPointerException();
/* 1086 */       Object localObject = this.value;
/* 1087 */       this.value = paramObject;
/* 1088 */       return localObject;
/*      */     }
/*      */     
/*      */     public boolean equals(Object paramObject) {
/* 1092 */       if (!(paramObject instanceof Map.Entry))
/* 1093 */         return false;
/* 1094 */       Map.Entry localEntry = (Map.Entry)paramObject;
/* 1095 */       return (this.key.equals(localEntry.getKey())) && (this.value.equals(localEntry.getValue()));
/*      */     }
/*      */     
/*      */     public int hashCode() {
/* 1099 */       return this.key.hashCode() ^ this.value.hashCode();
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1103 */       return this.key + "=" + this.value;
/*      */     }
/*      */   }
/*      */   
/*      */   protected class HashIterator implements Iterator, Enumeration
/*      */   {
/*      */     protected final ConcurrentReaderHashMap.Entry[] tab;
/*      */     protected int index;
/* 1111 */     protected ConcurrentReaderHashMap.Entry entry = null;
/*      */     protected Object currentKey;
/*      */     protected Object currentValue;
/* 1114 */     protected ConcurrentReaderHashMap.Entry lastReturned = null;
/*      */     
/*      */     protected HashIterator() {
/* 1117 */       this.tab = ConcurrentReaderHashMap.this.getTableForReading();
/* 1118 */       this.index = (this.tab.length - 1);
/*      */     }
/*      */     
/* 1121 */     public boolean hasMoreElements() { return hasNext(); }
/* 1122 */     public Object nextElement() { return next(); }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public boolean hasNext()
/*      */     {
/*      */       do
/*      */       {
/* 1136 */         if (this.entry != null) {
/* 1137 */           Object localObject = this.entry.value;
/* 1138 */           if (localObject != null) {
/* 1139 */             this.currentKey = this.entry.key;
/* 1140 */             this.currentValue = localObject;
/* 1141 */             return true;
/*      */           }
/*      */           
/* 1144 */           this.entry = this.entry.next;
/*      */         }
/*      */         
/* 1147 */         while ((this.entry == null) && (this.index >= 0)) {
/* 1148 */           this.entry = this.tab[(this.index--)];
/*      */         }
/* 1150 */       } while (this.entry != null);
/* 1151 */       this.currentKey = (this.currentValue = null);
/* 1152 */       return false;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1157 */     protected Object returnValueOfNext() { return this.entry; }
/*      */     
/*      */     public Object next() {
/* 1160 */       if ((this.currentKey == null) && (!hasNext())) {
/* 1161 */         throw new NoSuchElementException();
/*      */       }
/* 1163 */       Object localObject = returnValueOfNext();
/* 1164 */       this.lastReturned = this.entry;
/* 1165 */       this.currentKey = (this.currentValue = null);
/* 1166 */       this.entry = this.entry.next;
/* 1167 */       return localObject;
/*      */     }
/*      */     
/*      */     public void remove() {
/* 1171 */       if (this.lastReturned == null)
/* 1172 */         throw new IllegalStateException();
/* 1173 */       ConcurrentReaderHashMap.this.remove(this.lastReturned.key);
/* 1174 */       this.lastReturned = null;
/*      */     }
/*      */   }
/*      */   
/*      */   protected class KeyIterator
/*      */     extends ConcurrentReaderHashMap.HashIterator {
/* 1180 */     protected KeyIterator() { super(); }
/* 1181 */     protected Object returnValueOfNext() { return this.currentKey; }
/*      */   }
/*      */   
/* 1184 */   protected class ValueIterator extends ConcurrentReaderHashMap.HashIterator { protected ValueIterator() { super(); }
/* 1185 */     protected Object returnValueOfNext() { return this.currentValue; }
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
/*      */   private synchronized void writeObject(ObjectOutputStream paramObjectOutputStream)
/*      */     throws IOException
/*      */   {
/* 1207 */     paramObjectOutputStream.defaultWriteObject();
/*      */     
/*      */ 
/* 1210 */     paramObjectOutputStream.writeInt(this.table.length);
/*      */     
/*      */ 
/* 1213 */     paramObjectOutputStream.writeInt(this.count);
/*      */     
/*      */ 
/* 1216 */     for (int i = this.table.length - 1; i >= 0; i--) {
/* 1217 */       Entry localEntry = this.table[i];
/*      */       
/* 1219 */       while (localEntry != null) {
/* 1220 */         paramObjectOutputStream.writeObject(localEntry.key);
/* 1221 */         paramObjectOutputStream.writeObject(localEntry.value);
/* 1222 */         localEntry = localEntry.next;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private synchronized void readObject(ObjectInputStream paramObjectInputStream)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/* 1235 */     paramObjectInputStream.defaultReadObject();
/*      */     
/*      */ 
/* 1238 */     int i = paramObjectInputStream.readInt();
/* 1239 */     this.table = new Entry[i];
/*      */     
/*      */ 
/* 1242 */     int j = paramObjectInputStream.readInt();
/*      */     
/*      */ 
/* 1245 */     for (int k = 0; k < j; k++) {
/* 1246 */       Object localObject1 = paramObjectInputStream.readObject();
/* 1247 */       Object localObject2 = paramObjectInputStream.readObject();
/* 1248 */       put(localObject1, localObject2);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized int capacity()
/*      */   {
/* 1257 */     return this.table.length;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public float loadFactor()
/*      */   {
/* 1264 */     return this.loadFactor;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/ConcurrentReaderHashMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */