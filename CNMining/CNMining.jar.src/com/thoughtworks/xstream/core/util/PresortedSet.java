/*     */ package com.thoughtworks.xstream.core.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.SortedSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PresortedSet
/*     */   implements SortedSet
/*     */ {
/*  20 */   private final List list = new ArrayList();
/*     */   private final Comparator comparator;
/*     */   
/*     */   public PresortedSet() {
/*  24 */     this(null);
/*     */   }
/*     */   
/*     */   public PresortedSet(Comparator comparator) {
/*  28 */     this(comparator, null);
/*     */   }
/*     */   
/*     */   public PresortedSet(Comparator comparator, Collection c) {
/*  32 */     this.comparator = comparator;
/*  33 */     if (c != null) {
/*  34 */       addAll(c);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean add(Object e) {
/*  39 */     return this.list.add(e);
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection c) {
/*  43 */     return this.list.addAll(c);
/*     */   }
/*     */   
/*     */   public void clear() {
/*  47 */     this.list.clear();
/*     */   }
/*     */   
/*     */   public boolean contains(Object o) {
/*  51 */     return this.list.contains(o);
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection c) {
/*  55 */     return this.list.containsAll(c);
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  59 */     return this.list.equals(o);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  63 */     return this.list.hashCode();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  67 */     return this.list.isEmpty();
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/*  71 */     return this.list.iterator();
/*     */   }
/*     */   
/*     */   public boolean remove(Object o) {
/*  75 */     return this.list.remove(o);
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection c) {
/*  79 */     return this.list.removeAll(c);
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection c) {
/*  83 */     return this.list.retainAll(c);
/*     */   }
/*     */   
/*     */   public int size() {
/*  87 */     return this.list.size();
/*     */   }
/*     */   
/*     */   public List subList(int fromIndex, int toIndex) {
/*  91 */     return this.list.subList(fromIndex, toIndex);
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/*  95 */     return this.list.toArray();
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] a) {
/*  99 */     return this.list.toArray(a);
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/* 103 */     return this.comparator;
/*     */   }
/*     */   
/*     */   public Object first() {
/* 107 */     return this.list.isEmpty() ? null : this.list.get(0);
/*     */   }
/*     */   
/*     */   public SortedSet headSet(Object toElement) {
/* 111 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object last() {
/* 115 */     return this.list.isEmpty() ? null : this.list.get(this.list.size() - 1);
/*     */   }
/*     */   
/*     */   public SortedSet subSet(Object fromElement, Object toElement) {
/* 119 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public SortedSet tailSet(Object fromElement) {
/* 123 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/util/PresortedSet.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */