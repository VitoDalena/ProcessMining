/*     */ package com.thoughtworks.xstream.core.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.SortedMap;
/*     */ 
/*     */ public class PresortedMap implements SortedMap
/*     */ {
/*     */   private final ArraySet set;
/*     */   private final Comparator comparator;
/*     */   
/*     */   private static class ArraySet extends ArrayList implements Set
/*     */   {
/*     */     private ArraySet() {}
/*     */     
/*     */     ArraySet(PresortedMap.1 x0)
/*     */     {
/*  22 */       this();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public PresortedMap()
/*     */   {
/*  29 */     this(null, new ArraySet(null));
/*     */   }
/*     */   
/*     */   public PresortedMap(Comparator comparator) {
/*  33 */     this(comparator, new ArraySet(null));
/*     */   }
/*     */   
/*     */   private PresortedMap(Comparator comparator, ArraySet set) {
/*  37 */     this.comparator = (comparator != null ? comparator : new ArraySetComparator(set));
/*  38 */     this.set = set;
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/*  42 */     return this.comparator;
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/*  46 */     return this.set;
/*     */   }
/*     */   
/*     */   public Object firstKey() {
/*  50 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public SortedMap headMap(Object toKey) {
/*  54 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Set keySet() {
/*  58 */     Set keySet = new ArraySet(null);
/*  59 */     for (Iterator iterator = this.set.iterator(); iterator.hasNext();) {
/*  60 */       Map.Entry entry = (Map.Entry)iterator.next();
/*  61 */       keySet.add(entry.getKey());
/*     */     }
/*  63 */     return keySet;
/*     */   }
/*     */   
/*     */   public Object lastKey() {
/*  67 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public SortedMap subMap(Object fromKey, Object toKey) {
/*  71 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public SortedMap tailMap(Object fromKey) {
/*  75 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public java.util.Collection values() {
/*  79 */     Set values = new ArraySet(null);
/*  80 */     for (Iterator iterator = this.set.iterator(); iterator.hasNext();) {
/*  81 */       Map.Entry entry = (Map.Entry)iterator.next();
/*  82 */       values.add(entry.getValue());
/*     */     }
/*  84 */     return values;
/*     */   }
/*     */   
/*     */   public void clear() {
/*  88 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/*  92 */     return false;
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/*  96 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/* 100 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 104 */     return this.set.isEmpty();
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 108 */     this.set.add(new Map.Entry() { private final Object val$key;
/*     */       private final Object val$value;
/*     */       
/* 111 */       public Object getKey() { return this.val$key; }
/*     */       
/*     */       public Object getValue()
/*     */       {
/* 115 */         return this.val$value;
/*     */       }
/*     */       
/*     */ 
/* 119 */       public Object setValue(Object value) { throw new UnsupportedOperationException(); }
/* 120 */     });
/* 121 */     return null;
/*     */   }
/*     */   
/*     */   public void putAll(Map m) {
/* 125 */     for (Iterator iter = m.entrySet().iterator(); iter.hasNext();) {
/* 126 */       this.set.add(iter.next());
/*     */     }
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 131 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public int size() {
/* 135 */     return this.set.size();
/*     */   }
/*     */   
/*     */   private static class ArraySetComparator implements Comparator
/*     */   {
/*     */     private final ArrayList list;
/*     */     private Map.Entry[] array;
/*     */     
/*     */     ArraySetComparator(ArrayList list) {
/* 144 */       this.list = list;
/*     */     }
/*     */     
/*     */     public int compare(Object object1, Object object2) {
/* 148 */       if ((this.array == null) || (this.list.size() != this.array.length)) {
/* 149 */         Map.Entry[] a = new Map.Entry[this.list.size()];
/* 150 */         if (this.array != null) {
/* 151 */           System.arraycopy(this.array, 0, a, 0, this.array.length);
/*     */         }
/* 153 */         for (int i = this.array == null ? 0 : this.array.length; i < this.list.size(); i++) {
/* 154 */           a[i] = ((Map.Entry)this.list.get(i));
/*     */         }
/* 156 */         this.array = a;
/*     */       }
/* 158 */       int idx1 = Integer.MAX_VALUE;int idx2 = Integer.MAX_VALUE;
/* 159 */       for (int i = 0; (i < this.array.length) && ((idx1 >= Integer.MAX_VALUE) || (idx2 >= Integer.MAX_VALUE)); i++) {
/* 160 */         if ((idx1 == Integer.MAX_VALUE) && (object1 == this.array[i].getKey())) {
/* 161 */           idx1 = i;
/*     */         }
/* 163 */         if ((idx2 == Integer.MAX_VALUE) && (object2 == this.array[i].getKey())) {
/* 164 */           idx2 = i;
/*     */         }
/*     */       }
/* 167 */       return idx1 - idx2;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/util/PresortedMap.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */