/*     */ package edu.uci.ics.jung.graph.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Array;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Pair<T>
/*     */   implements Collection<T>, Serializable
/*     */ {
/*     */   private T first;
/*     */   private T second;
/*     */   
/*     */   public Pair(T value1, T value2)
/*     */   {
/*  41 */     if ((value1 == null) || (value2 == null))
/*  42 */       throw new IllegalArgumentException("Pair cannot contain null values");
/*  43 */     this.first = value1;
/*  44 */     this.second = value2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Pair(Collection<? extends T> values)
/*     */   {
/*  56 */     if (values == null)
/*  57 */       throw new IllegalArgumentException("Input collection cannot be null");
/*  58 */     if (values.size() == 2)
/*     */     {
/*  60 */       if (values.contains(null))
/*  61 */         throw new IllegalArgumentException("Pair cannot contain null values");
/*  62 */       Iterator<? extends T> iter = values.iterator();
/*  63 */       this.first = iter.next();
/*  64 */       this.second = iter.next();
/*     */     }
/*     */     else {
/*  67 */       throw new IllegalArgumentException("Pair may only be created from a Collection of exactly 2 elements");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Pair(T[] values)
/*     */   {
/*  79 */     if (values == null)
/*  80 */       throw new IllegalArgumentException("Input array cannot be null");
/*  81 */     if (values.length == 2)
/*     */     {
/*  83 */       if ((values[0] == null) || (values[1] == null))
/*  84 */         throw new IllegalArgumentException("Pair cannot contain null values");
/*  85 */       this.first = values[0];
/*  86 */       this.second = values[1];
/*     */     }
/*     */     else {
/*  89 */       throw new IllegalArgumentException("Pair may only be created from an array of 2 elements");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public T getFirst()
/*     */   {
/*  98 */     return (T)this.first;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public T getSecond()
/*     */   {
/* 106 */     return (T)this.second;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 112 */     if (o == this) {
/* 113 */       return true;
/*     */     }
/* 115 */     if ((o instanceof Pair)) {
/* 116 */       Pair otherPair = (Pair)o;
/* 117 */       Object otherFirst = otherPair.getFirst();
/* 118 */       Object otherSecond = otherPair.getSecond();
/* 119 */       return ((this.first == otherFirst) || ((this.first != null) && (this.first.equals(otherFirst)))) && ((this.second == otherSecond) || ((this.second != null) && (this.second.equals(otherSecond))));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 126 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 133 */     int hashCode = 1;
/* 134 */     hashCode = 31 * hashCode + (this.first == null ? 0 : this.first.hashCode());
/* 135 */     hashCode = 31 * hashCode + (this.second == null ? 0 : this.second.hashCode());
/* 136 */     return hashCode;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 142 */     return "<" + this.first.toString() + ", " + this.second.toString() + ">";
/*     */   }
/*     */   
/*     */   public boolean add(T o) {
/* 146 */     throw new UnsupportedOperationException("Pairs cannot be mutated");
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends T> c) {
/* 150 */     throw new UnsupportedOperationException("Pairs cannot be mutated");
/*     */   }
/*     */   
/*     */   public void clear() {
/* 154 */     throw new UnsupportedOperationException("Pairs cannot be mutated");
/*     */   }
/*     */   
/*     */   public boolean contains(Object o) {
/* 158 */     return (this.first == o) || (this.first.equals(o)) || (this.second == o) || (this.second.equals(o));
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> c) {
/* 162 */     if (c.size() > 2)
/* 163 */       return false;
/* 164 */     Iterator<?> iter = c.iterator();
/* 165 */     Object c_first = iter.next();
/* 166 */     Object c_second = iter.next();
/* 167 */     return (contains(c_first)) && (contains(c_second));
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 171 */     return false;
/*     */   }
/*     */   
/*     */   public Iterator<T> iterator() {
/* 175 */     return new PairIterator(null);
/*     */   }
/*     */   
/*     */   public boolean remove(Object o) {
/* 179 */     throw new UnsupportedOperationException("Pairs cannot be mutated");
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> c) {
/* 183 */     throw new UnsupportedOperationException("Pairs cannot be mutated");
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> c) {
/* 187 */     throw new UnsupportedOperationException("Pairs cannot be mutated");
/*     */   }
/*     */   
/*     */   public int size() {
/* 191 */     return 2;
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 195 */     Object[] to_return = new Object[2];
/* 196 */     to_return[0] = this.first;
/* 197 */     to_return[1] = this.second;
/* 198 */     return to_return;
/*     */   }
/*     */   
/*     */   public <S> S[] toArray(S[] a)
/*     */   {
/* 203 */     S[] to_return = a;
/* 204 */     Class<?> type = a.getClass().getComponentType();
/* 205 */     if (a.length < 2)
/* 206 */       to_return = (Object[])Array.newInstance(type, 2);
/* 207 */     to_return[0] = this.first;
/* 208 */     to_return[1] = this.second;
/*     */     
/* 210 */     if (to_return.length > 2)
/* 211 */       to_return[2] = null;
/* 212 */     return to_return;
/*     */   }
/*     */   
/*     */   private class PairIterator implements Iterator<T>
/*     */   {
/*     */     int position;
/*     */     
/*     */     private PairIterator()
/*     */     {
/* 221 */       this.position = 0;
/*     */     }
/*     */     
/*     */     public boolean hasNext()
/*     */     {
/* 226 */       return this.position < 2;
/*     */     }
/*     */     
/*     */     public T next()
/*     */     {
/* 231 */       this.position += 1;
/* 232 */       if (this.position == 1)
/* 233 */         return (T)Pair.this.first;
/* 234 */       if (this.position == 2) {
/* 235 */         return (T)Pair.this.second;
/*     */       }
/* 237 */       return null;
/*     */     }
/*     */     
/*     */     public void remove()
/*     */     {
/* 242 */       throw new UnsupportedOperationException("Pairs cannot be mutated");
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/util/Pair.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */