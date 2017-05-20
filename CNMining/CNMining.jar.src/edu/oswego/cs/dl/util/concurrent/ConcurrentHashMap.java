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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ConcurrentHashMap
/*      */   extends AbstractMap
/*      */   implements Map, Cloneable, Serializable
/*      */ {
/*      */   protected transient Entry[] table;
/*      */   protected static final int CONCURRENCY_LEVEL = 32;
/*      */   protected static final int SEGMENT_MASK = 31;
/*      */   
/*      */   protected static final class Segment
/*      */   {
/*      */     protected int count;
/*      */     
/*      */     protected synchronized int getCount()
/*      */     {
/*  204 */       return this.count;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     protected synchronized void synch() {}
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  216 */   protected final Segment[] segments = new Segment[32];
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  223 */   public static int DEFAULT_INITIAL_CAPACITY = 32;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int MINIMUM_CAPACITY = 32;
/*      */   
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
/*      */ 
/*      */   public static final float DEFAULT_LOAD_FACTOR = 0.75F;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected final float loadFactor;
/*      */   
/*      */ 
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
/*      */ 
/*      */ 
/*      */   protected volatile transient int votesForResize;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected static int bitcount(int paramInt)
/*      */   {
/*  281 */     paramInt -= ((0xAAAAAAAA & paramInt) >>> 1);
/*  282 */     paramInt = (paramInt & 0x33333333) + (paramInt >>> 2 & 0x33333333);
/*  283 */     paramInt = paramInt + (paramInt >>> 4) & 0xF0F0F0F;
/*  284 */     paramInt += (paramInt >>> 8);
/*  285 */     paramInt += (paramInt >>> 16);
/*  286 */     return paramInt & 0xFF;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private int p2capacity(int paramInt)
/*      */   {
/*  294 */     int i = paramInt;
/*      */     
/*      */     int j;
/*      */     
/*  298 */     if ((i > 1073741824) || (i < 0)) {
/*  299 */       j = 1073741824;
/*      */     } else {
/*  301 */       j = 32;
/*  302 */       while (j < i)
/*  303 */         j <<= 1;
/*      */     }
/*  305 */     return j;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected static int hash(Object paramObject)
/*      */   {
/*  314 */     int i = paramObject.hashCode();
/*      */     
/*      */ 
/*      */ 
/*  318 */     return (i << 7) - i + (i >>> 9) + (i >>> 17);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected boolean eq(Object paramObject1, Object paramObject2)
/*      */   {
/*  326 */     return (paramObject1 == paramObject2) || (paramObject1.equals(paramObject2));
/*      */   }
/*      */   
/*      */   protected Entry[] newTable(int paramInt)
/*      */   {
/*  331 */     this.threshold = ((int)(paramInt * this.loadFactor / 32.0F) + 1);
/*  332 */     return new Entry[paramInt];
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
/*      */   public ConcurrentHashMap(int paramInt, float paramFloat)
/*      */   {
/*  354 */     if (paramFloat <= 0.0F) {
/*  355 */       throw new IllegalArgumentException("Illegal Load factor: " + paramFloat);
/*      */     }
/*  357 */     this.loadFactor = paramFloat;
/*  358 */     for (int i = 0; i < this.segments.length; i++)
/*  359 */       this.segments[i] = new Segment();
/*  360 */     int j = p2capacity(paramInt);
/*  361 */     this.table = newTable(j);
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
/*      */   public ConcurrentHashMap(int paramInt)
/*      */   {
/*  375 */     this(paramInt, 0.75F);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ConcurrentHashMap()
/*      */   {
/*  383 */     this(DEFAULT_INITIAL_CAPACITY, 0.75F);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ConcurrentHashMap(Map paramMap)
/*      */   {
/*  392 */     this(Math.max((int)(paramMap.size() / 0.75F) + 1, 32), 0.75F);
/*      */     
/*      */ 
/*  395 */     putAll(paramMap);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int size()
/*      */   {
/*  404 */     int i = 0;
/*  405 */     for (int j = 0; j < this.segments.length; j++)
/*  406 */       i += this.segments[j].getCount();
/*  407 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isEmpty()
/*      */   {
/*  416 */     for (int i = 0; i < this.segments.length; i++)
/*  417 */       if (this.segments[i].getCount() != 0)
/*  418 */         return false;
/*  419 */     return true;
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
/*      */   public Object get(Object paramObject)
/*      */   {
/*  435 */     int i = hash(paramObject);
/*      */     
/*      */ 
/*  438 */     Entry[] arrayOfEntry = this.table;
/*  439 */     int j = i & arrayOfEntry.length - 1;
/*  440 */     Entry localEntry1 = arrayOfEntry[j];
/*      */     
/*      */ 
/*  443 */     for (Object localObject1 = localEntry1; localObject1 != null; localObject1 = ((Entry)localObject1).next) {
/*  444 */       if ((((Entry)localObject1).hash == i) && (eq(paramObject, ((Entry)localObject1).key))) {
/*  445 */         localObject2 = ((Entry)localObject1).value;
/*  446 */         if (localObject2 == null) break;
/*  447 */         return localObject2;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  454 */     Object localObject2 = this.segments[(i & 0x1F)];
/*  455 */     synchronized (localObject2) {
/*  456 */       arrayOfEntry = this.table;
/*  457 */       j = i & arrayOfEntry.length - 1;
/*  458 */       Entry localEntry2 = arrayOfEntry[j];
/*  459 */       if ((localObject1 != null) || (localEntry1 != localEntry2)) {
/*  460 */         for (localObject1 = localEntry2; localObject1 != null; localObject1 = ((Entry)localObject1).next)
/*  461 */           if ((((Entry)localObject1).hash == i) && (eq(paramObject, ((Entry)localObject1).key))) {
/*  462 */             localObject4 = ((Entry)localObject1).value;return localObject4;
/*      */           }
/*      */       }
/*  465 */       Object localObject4 = null;return localObject4;
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
/*      */   public boolean containsKey(Object paramObject)
/*      */   {
/*  482 */     return get(paramObject) != null;
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
/*      */   public Object put(Object paramObject1, Object paramObject2)
/*      */   {
/*  506 */     if (paramObject2 == null) {
/*  507 */       throw new NullPointerException();
/*      */     }
/*  509 */     int i = hash(paramObject1);
/*  510 */     Segment localSegment1 = this.segments[(i & 0x1F)];
/*      */     
/*      */     Entry[] arrayOfEntry;
/*      */     int j;
/*      */     int k;
/*  515 */     synchronized (localSegment1) {
/*  516 */       arrayOfEntry = this.table;
/*  517 */       int m = i & arrayOfEntry.length - 1;
/*  518 */       Entry localEntry1 = arrayOfEntry[m];
/*      */       Object localObject2;
/*  520 */       for (Entry localEntry2 = localEntry1; localEntry2 != null; localEntry2 = localEntry2.next) {
/*  521 */         if ((localEntry2.hash == i) && (eq(paramObject1, localEntry2.key))) {
/*  522 */           localObject1 = localEntry2.value;
/*  523 */           localEntry2.value = paramObject2;
/*  524 */           localObject2 = localObject1;return localObject2;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  529 */       Object localObject1 = new Entry(i, paramObject1, paramObject2, localEntry1);
/*  530 */       arrayOfEntry[m] = localObject1;
/*      */       
/*  532 */       if ((j = ++localSegment1.count) < this.threshold) {
/*  533 */         localObject2 = null;return localObject2;
/*      */       }
/*  535 */       int n = 1 << (i & 0x1F);
/*  536 */       k = this.votesForResize;
/*  537 */       if ((k & n) == 0) {
/*  538 */         k = this.votesForResize |= n;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  544 */     if ((bitcount(k) >= 8) || (j > this.threshold * 32))
/*      */     {
/*  546 */       resize(0, arrayOfEntry);
/*      */     }
/*  548 */     return null;
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
/*      */   protected void resize(int paramInt, Entry[] paramArrayOfEntry)
/*      */   {
/*  561 */     Segment localSegment1 = this.segments[paramInt];
/*  562 */     synchronized (localSegment1) {
/*  563 */       if (paramArrayOfEntry == this.table) {
/*  564 */         int i = paramInt + 1;
/*  565 */         if (i < this.segments.length) {
/*  566 */           resize(i, paramArrayOfEntry);
/*      */         } else {
/*  568 */           rehash();
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected void rehash()
/*      */   {
/*  578 */     this.votesForResize = 0;
/*      */     
/*  580 */     Entry[] arrayOfEntry1 = this.table;
/*  581 */     int i = arrayOfEntry1.length;
/*      */     
/*  583 */     if (i >= 1073741824) {
/*  584 */       this.threshold = Integer.MAX_VALUE;
/*  585 */       return;
/*      */     }
/*      */     
/*  588 */     int j = i << 1;
/*  589 */     Entry[] arrayOfEntry2 = newTable(j);
/*  590 */     int k = j - 1;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  606 */     for (int m = 0; m < i; m++)
/*      */     {
/*      */ 
/*  609 */       Entry localEntry1 = arrayOfEntry1[m];
/*      */       
/*  611 */       if (localEntry1 != null) {
/*  612 */         int n = localEntry1.hash & k;
/*  613 */         Entry localEntry2 = localEntry1.next;
/*      */         
/*      */ 
/*  616 */         if (localEntry2 == null) {
/*  617 */           arrayOfEntry2[n] = localEntry1;
/*      */         }
/*      */         else
/*      */         {
/*  621 */           Object localObject = localEntry1;
/*  622 */           int i1 = n;
/*  623 */           for (Entry localEntry3 = localEntry2; localEntry3 != null; localEntry3 = localEntry3.next) {
/*  624 */             int i2 = localEntry3.hash & k;
/*  625 */             if (i2 != i1) {
/*  626 */               i1 = i2;
/*  627 */               localObject = localEntry3;
/*      */             }
/*      */           }
/*  630 */           arrayOfEntry2[i1] = localObject;
/*      */           
/*      */ 
/*  633 */           for (Entry localEntry4 = localEntry1; localEntry4 != localObject; localEntry4 = localEntry4.next) {
/*  634 */             int i3 = localEntry4.hash & k;
/*  635 */             arrayOfEntry2[i3] = new Entry(localEntry4.hash, localEntry4.key, localEntry4.value, arrayOfEntry2[i3]);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  642 */     this.table = arrayOfEntry2;
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
/*      */   public Object remove(Object paramObject)
/*      */   {
/*  657 */     return remove(paramObject, null);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Object remove(Object paramObject1, Object paramObject2)
/*      */   {
/*  687 */     int i = hash(paramObject1);
/*  688 */     Segment localSegment1 = this.segments[(i & 0x1F)];
/*      */     
/*  690 */     synchronized (localSegment1) {
/*  691 */       Entry[] arrayOfEntry = this.table;
/*  692 */       int j = i & arrayOfEntry.length - 1;
/*  693 */       Entry localEntry1 = arrayOfEntry[j];
/*  694 */       Entry localEntry2 = localEntry1;
/*      */       for (;;)
/*      */       {
/*  697 */         if (localEntry2 == null) {
/*  698 */           localObject1 = null;return localObject1; }
/*  699 */         if ((localEntry2.hash == i) && (eq(paramObject1, localEntry2.key)))
/*      */           break;
/*  701 */         localEntry2 = localEntry2.next;
/*      */       }
/*      */       
/*  704 */       Object localObject1 = localEntry2.value;
/*  705 */       if ((paramObject2 != null) && (!paramObject2.equals(localObject1))) {
/*  706 */         Object localObject2 = null;return localObject2;
/*      */       }
/*  708 */       localEntry2.value = null;
/*      */       
/*  710 */       Entry localEntry3 = localEntry2.next;
/*  711 */       for (Entry localEntry4 = localEntry1; localEntry4 != localEntry2; localEntry4 = localEntry4.next)
/*  712 */         localEntry3 = new Entry(localEntry4.hash, localEntry4.key, localEntry4.value, localEntry3);
/*  713 */       arrayOfEntry[j] = localEntry3;
/*  714 */       localSegment1.count -= 1;
/*  715 */       Object localObject3 = localObject1;return localObject3;
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
/*      */   public boolean containsValue(Object paramObject)
/*      */   {
/*  733 */     if (paramObject == null) { throw new NullPointerException();
/*      */     }
/*  735 */     for (int i = 0; i < this.segments.length; i++) {
/*  736 */       Segment localSegment1 = this.segments[i];
/*      */       Entry[] arrayOfEntry;
/*  738 */       synchronized (localSegment1) { arrayOfEntry = this.table; }
/*  739 */       int j; for (localObject = i; j < arrayOfEntry.length; localObject += this.segments.length) {
/*  740 */         for (Entry localEntry = arrayOfEntry[localObject]; localEntry != null; localEntry = localEntry.next)
/*  741 */           if (paramObject.equals(localEntry.value))
/*  742 */             return true;
/*      */       }
/*      */     }
/*  745 */     return false;
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
/*  768 */     return containsValue(paramObject);
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
/*      */   public void putAll(Map paramMap)
/*      */   {
/*  781 */     int i = paramMap.size();
/*  782 */     if (i == 0) {
/*      */       return;
/*      */     }
/*      */     
/*      */ 
/*      */     for (;;)
/*      */     {
/*      */       int j;
/*      */       
/*  791 */       synchronized (this.segments[0]) {
/*  792 */         localObject1 = this.table;
/*  793 */         j = this.threshold * 32;
/*      */       }
/*  795 */       if (i < j)
/*      */         break;
/*  797 */       resize(0, (Entry[])localObject1);
/*      */     }
/*      */     
/*  800 */     for (Object localObject1 = paramMap.entrySet().iterator(); ((Iterator)localObject1).hasNext();) {
/*  801 */       Map.Entry localEntry = (Map.Entry)((Iterator)localObject1).next();
/*  802 */       put(localEntry.getKey(), localEntry.getValue());
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clear()
/*      */   {
/*  813 */     for (int i = 0; i < this.segments.length; i++) {
/*  814 */       Segment localSegment1 = this.segments[i];
/*  815 */       synchronized (localSegment1) {
/*  816 */         Entry[] arrayOfEntry = this.table;
/*  817 */         for (int j = i; j < arrayOfEntry.length; j += this.segments.length) {
/*  818 */           for (Entry localEntry = arrayOfEntry[j]; localEntry != null; localEntry = localEntry.next)
/*  819 */             localEntry.value = null;
/*  820 */           arrayOfEntry[j] = null;
/*  821 */           localSegment1.count = 0;
/*      */         }
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
/*      */   public Object clone()
/*      */   {
/*  838 */     return new ConcurrentHashMap(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*  843 */   protected transient Set keySet = null;
/*  844 */   protected transient Set entrySet = null;
/*  845 */   protected transient Collection values = null;
/*      */   
/*      */ 
/*      */ 
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
/*  860 */     Set localSet = this.keySet;
/*  861 */     return localSet != null ? localSet : (this.keySet = new KeySet(null));
/*      */   }
/*      */   
/*  864 */   private class KeySet extends AbstractSet { KeySet(ConcurrentHashMap.1 param1) { this(); }
/*      */     
/*  866 */     public Iterator iterator() { return new ConcurrentHashMap.KeyIterator(ConcurrentHashMap.this); }
/*      */     
/*      */     public int size() {
/*  869 */       return ConcurrentHashMap.this.size();
/*      */     }
/*      */     
/*  872 */     public boolean contains(Object paramObject) { return ConcurrentHashMap.this.containsKey(paramObject); }
/*      */     
/*      */     public boolean remove(Object paramObject) {
/*  875 */       return ConcurrentHashMap.this.remove(paramObject) != null;
/*      */     }
/*      */     
/*  878 */     public void clear() { ConcurrentHashMap.this.clear(); }
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
/*  895 */     Collection localCollection = this.values;
/*  896 */     return localCollection != null ? localCollection : (this.values = new Values(null));
/*      */   }
/*      */   
/*  899 */   private class Values extends AbstractCollection { Values(ConcurrentHashMap.1 param1) { this(); }
/*      */     
/*  901 */     public Iterator iterator() { return new ConcurrentHashMap.ValueIterator(ConcurrentHashMap.this); }
/*      */     
/*      */     public int size() {
/*  904 */       return ConcurrentHashMap.this.size();
/*      */     }
/*      */     
/*  907 */     public boolean contains(Object paramObject) { return ConcurrentHashMap.this.containsValue(paramObject); }
/*      */     
/*      */     public void clear() {
/*  910 */       ConcurrentHashMap.this.clear();
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
/*  928 */     Set localSet = this.entrySet;
/*  929 */     return localSet != null ? localSet : (this.entrySet = new EntrySet(null));
/*      */   }
/*      */   
/*  932 */   private class EntrySet extends AbstractSet { EntrySet(ConcurrentHashMap.1 param1) { this(); }
/*      */     
/*  934 */     public Iterator iterator() { return new ConcurrentHashMap.HashIterator(ConcurrentHashMap.this); }
/*      */     
/*      */     public boolean contains(Object paramObject) {
/*  937 */       if (!(paramObject instanceof Map.Entry))
/*  938 */         return false;
/*  939 */       Map.Entry localEntry = (Map.Entry)paramObject;
/*  940 */       Object localObject = ConcurrentHashMap.this.get(localEntry.getKey());
/*  941 */       return (localObject != null) && (localObject.equals(localEntry.getValue()));
/*      */     }
/*      */     
/*  944 */     public boolean remove(Object paramObject) { if (!(paramObject instanceof Map.Entry))
/*  945 */         return false;
/*  946 */       Map.Entry localEntry = (Map.Entry)paramObject;
/*  947 */       return ConcurrentHashMap.this.remove(localEntry.getKey(), localEntry.getValue()) != null;
/*      */     }
/*      */     
/*  950 */     public int size() { return ConcurrentHashMap.this.size(); }
/*      */     
/*      */     public void clear() {
/*  953 */       ConcurrentHashMap.this.clear();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     private EntrySet() {}
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Enumeration keys()
/*      */   {
/*  967 */     return new KeyIterator();
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
/*  983 */     return new ValueIterator();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected static class Entry
/*      */     implements Map.Entry
/*      */   {
/*      */     protected final Object key;
/*      */     
/*      */ 
/*      */     protected volatile Object value;
/*      */     
/*      */ 
/*      */     protected final int hash;
/*      */     
/*      */     protected final Entry next;
/*      */     
/*      */ 
/*      */     Entry(int paramInt, Object paramObject1, Object paramObject2, Entry paramEntry)
/*      */     {
/* 1004 */       this.value = paramObject2;
/* 1005 */       this.hash = paramInt;
/* 1006 */       this.key = paramObject1;
/* 1007 */       this.next = paramEntry;
/*      */     }
/*      */     
/*      */ 
/*      */     public Object getKey()
/*      */     {
/* 1013 */       return this.key;
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
/*      */     public Object getValue()
/*      */     {
/* 1028 */       return this.value;
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
/*      */     public Object setValue(Object paramObject)
/*      */     {
/* 1052 */       if (paramObject == null)
/* 1053 */         throw new NullPointerException();
/* 1054 */       Object localObject = this.value;
/* 1055 */       this.value = paramObject;
/* 1056 */       return localObject;
/*      */     }
/*      */     
/*      */     public boolean equals(Object paramObject) {
/* 1060 */       if (!(paramObject instanceof Map.Entry))
/* 1061 */         return false;
/* 1062 */       Map.Entry localEntry = (Map.Entry)paramObject;
/* 1063 */       return (this.key.equals(localEntry.getKey())) && (this.value.equals(localEntry.getValue()));
/*      */     }
/*      */     
/*      */     public int hashCode() {
/* 1067 */       return this.key.hashCode() ^ this.value.hashCode();
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1071 */       return this.key + "=" + this.value;
/*      */     }
/*      */   }
/*      */   
/*      */   protected class HashIterator implements Iterator, Enumeration
/*      */   {
/*      */     protected final ConcurrentHashMap.Entry[] tab;
/*      */     protected int index;
/* 1079 */     protected ConcurrentHashMap.Entry entry = null;
/*      */     protected Object currentKey;
/*      */     protected Object currentValue;
/* 1082 */     protected ConcurrentHashMap.Entry lastReturned = null;
/*      */     
/*      */     protected HashIterator()
/*      */     {
/* 1086 */       synchronized (ConcurrentHashMap.this.segments[0]) { this.tab = ConcurrentHashMap.this.table; }
/* 1087 */       for (int i = 1; i < ConcurrentHashMap.this.segments.length; i++) ConcurrentHashMap.this.segments[i].synch();
/* 1088 */       this.index = (this.tab.length - 1);
/*      */     }
/*      */     
/* 1091 */     public boolean hasMoreElements() { return hasNext(); }
/* 1092 */     public Object nextElement() { return next(); }
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
/* 1104 */         if (this.entry != null) {
/* 1105 */           Object localObject = this.entry.value;
/* 1106 */           if (localObject != null) {
/* 1107 */             this.currentKey = this.entry.key;
/* 1108 */             this.currentValue = localObject;
/* 1109 */             return true;
/*      */           }
/*      */           
/* 1112 */           this.entry = this.entry.next;
/*      */         }
/*      */         
/* 1115 */         while ((this.entry == null) && (this.index >= 0)) {
/* 1116 */           this.entry = this.tab[(this.index--)];
/*      */         }
/* 1118 */       } while (this.entry != null);
/* 1119 */       this.currentKey = (this.currentValue = null);
/* 1120 */       return false;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1125 */     protected Object returnValueOfNext() { return this.entry; }
/*      */     
/*      */     public Object next() {
/* 1128 */       if ((this.currentKey == null) && (!hasNext())) {
/* 1129 */         throw new NoSuchElementException();
/*      */       }
/* 1131 */       Object localObject = returnValueOfNext();
/* 1132 */       this.lastReturned = this.entry;
/* 1133 */       this.currentKey = (this.currentValue = null);
/* 1134 */       this.entry = this.entry.next;
/* 1135 */       return localObject;
/*      */     }
/*      */     
/*      */     public void remove() {
/* 1139 */       if (this.lastReturned == null)
/* 1140 */         throw new IllegalStateException();
/* 1141 */       ConcurrentHashMap.this.remove(this.lastReturned.key);
/* 1142 */       this.lastReturned = null;
/*      */     }
/*      */   }
/*      */   
/*      */   protected class KeyIterator extends ConcurrentHashMap.HashIterator {
/* 1147 */     protected KeyIterator() { super(); }
/* 1148 */     protected Object returnValueOfNext() { return this.currentKey; }
/*      */   }
/*      */   
/* 1151 */   protected class ValueIterator extends ConcurrentHashMap.HashIterator { protected ValueIterator() { super(); }
/* 1152 */     protected Object returnValueOfNext() { return this.currentValue; }
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
/*      */   private void writeObject(ObjectOutputStream paramObjectOutputStream)
/*      */     throws IOException
/*      */   {
/* 1170 */     paramObjectOutputStream.defaultWriteObject();
/*      */     
/*      */ 
/*      */ 
/*      */     int i;
/*      */     
/*      */ 
/* 1177 */     synchronized (this.segments[0]) { i = this.table.length; }
/* 1178 */     paramObjectOutputStream.writeInt(i);
/*      */     
/*      */ 
/* 1181 */     for (int j = 0; j < this.segments.length; j++) {
/* 1182 */       Segment localSegment2 = this.segments[j];
/*      */       Entry[] arrayOfEntry;
/* 1184 */       synchronized (localSegment2) { arrayOfEntry = this.table; }
/* 1185 */       int k; for (localObject2 = j; k < arrayOfEntry.length; localObject2 += this.segments.length) {
/* 1186 */         for (Entry localEntry = arrayOfEntry[localObject2]; localEntry != null; localEntry = localEntry.next) {
/* 1187 */           paramObjectOutputStream.writeObject(localEntry.key);
/* 1188 */           paramObjectOutputStream.writeObject(localEntry.value);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1193 */     paramObjectOutputStream.writeObject(null);
/* 1194 */     paramObjectOutputStream.writeObject(null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void readObject(ObjectInputStream paramObjectInputStream)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/* 1205 */     paramObjectInputStream.defaultReadObject();
/*      */     
/* 1207 */     int i = paramObjectInputStream.readInt();
/* 1208 */     this.table = newTable(i);
/* 1209 */     int j = 0;
/* 1210 */     for (;;) { this.segments[j] = new Segment();j++;
/* 1209 */       if (j >= this.segments.length) {
/*      */         break;
/*      */       }
/*      */     }
/*      */     for (;;)
/*      */     {
/* 1215 */       Object localObject1 = paramObjectInputStream.readObject();
/* 1216 */       Object localObject2 = paramObjectInputStream.readObject();
/* 1217 */       if (localObject1 == null)
/*      */         break;
/* 1219 */       put(localObject1, localObject2);
/*      */     }
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/ConcurrentHashMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */