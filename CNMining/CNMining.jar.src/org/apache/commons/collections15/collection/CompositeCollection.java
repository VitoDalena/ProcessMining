/*     */ package org.apache.commons.collections15.collection;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections15.iterators.EmptyIterator;
/*     */ import org.apache.commons.collections15.iterators.IteratorChain;
/*     */ import org.apache.commons.collections15.list.UnmodifiableList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CompositeCollection<E>
/*     */   implements Collection<E>
/*     */ {
/*     */   protected CollectionMutator<E> mutator;
/*     */   protected Collection<E>[] all;
/*     */   
/*     */   public CompositeCollection()
/*     */   {
/*  59 */     this.all = new Collection[0];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CompositeCollection(Collection<E> coll)
/*     */   {
/*  68 */     this();
/*  69 */     addComposited(coll);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CompositeCollection(Collection<E>... colls)
/*     */   {
/*  79 */     this();
/*  80 */     addComposited(colls);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/*  92 */     int size = 0;
/*  93 */     for (int i = this.all.length - 1; i >= 0; i--) {
/*  94 */       size += this.all[i].size();
/*     */     }
/*  96 */     return size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 107 */     for (int i = this.all.length - 1; i >= 0; i--) {
/* 108 */       if (!this.all[i].isEmpty()) {
/* 109 */         return false;
/*     */       }
/*     */     }
/* 112 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean contains(Object obj)
/*     */   {
/* 124 */     for (int i = this.all.length - 1; i >= 0; i--) {
/* 125 */       if (this.all[i].contains(obj)) {
/* 126 */         return true;
/*     */       }
/*     */     }
/* 129 */     return false;
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
/*     */   public Iterator<E> iterator()
/*     */   {
/* 143 */     if (this.all.length == 0) {
/* 144 */       return EmptyIterator.INSTANCE;
/*     */     }
/* 146 */     IteratorChain<E> chain = new IteratorChain();
/* 147 */     for (int i = 0; i < this.all.length; i++) {
/* 148 */       chain.addIterator(this.all[i].iterator());
/*     */     }
/* 150 */     return chain;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E[] toArray()
/*     */   {
/* 159 */     E[] result = (Object[])new Object[size()];
/* 160 */     int i = 0;
/* 161 */     for (Iterator<E> it = iterator(); it.hasNext(); i++) {
/* 162 */       result[i] = it.next();
/*     */     }
/* 164 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <E> E[] toArray(E[] array)
/*     */   {
/* 176 */     int size = size();
/* 177 */     E[] result = null;
/* 178 */     if (array.length >= size) {
/* 179 */       result = array;
/*     */     } else {
/* 181 */       result = (Object[])Array.newInstance(array.getClass().getComponentType(), size);
/*     */     }
/*     */     
/* 184 */     int offset = 0;
/* 185 */     Iterator it; for (int i = 0; i < this.all.length; i++) {
/* 186 */       for (it = this.all[i].iterator(); it.hasNext();) {
/* 187 */         result[(offset++)] = it.next();
/*     */       }
/*     */     }
/* 190 */     if (result.length > size) {
/* 191 */       result[size] = null;
/*     */     }
/* 193 */     return result;
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
/*     */   public boolean add(E obj)
/*     */   {
/* 209 */     if (this.mutator == null) {
/* 210 */       throw new UnsupportedOperationException("add() is not supported on CompositeCollection without a CollectionMutator strategy");
/*     */     }
/* 212 */     return this.mutator.add(this, this.all, obj);
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
/*     */   public boolean remove(Object obj)
/*     */   {
/* 227 */     if (this.mutator == null) {
/* 228 */       throw new UnsupportedOperationException("remove() is not supported on CompositeCollection without a CollectionMutator strategy");
/*     */     }
/* 230 */     return this.mutator.remove(this, this.all, obj);
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
/*     */   public boolean containsAll(Collection<?> coll)
/*     */   {
/* 243 */     for (Iterator it = coll.iterator(); it.hasNext();) {
/* 244 */       if (!contains(it.next())) {
/* 245 */         return false;
/*     */       }
/*     */     }
/* 248 */     return true;
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
/*     */   public boolean addAll(Collection<? extends E> coll)
/*     */   {
/* 264 */     if (this.mutator == null) {
/* 265 */       throw new UnsupportedOperationException("addAll() is not supported on CompositeCollection without a CollectionMutator strategy");
/*     */     }
/* 267 */     return this.mutator.addAll(this, this.all, coll);
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
/*     */   public boolean removeAll(Collection<?> coll)
/*     */   {
/* 280 */     if (coll.size() == 0) {
/* 281 */       return false;
/*     */     }
/* 283 */     boolean changed = false;
/* 284 */     for (int i = this.all.length - 1; i >= 0; i--) {
/* 285 */       changed = (this.all[i].removeAll(coll)) || (changed);
/*     */     }
/* 287 */     return changed;
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
/*     */   public boolean retainAll(Collection<?> coll)
/*     */   {
/* 301 */     boolean changed = false;
/* 302 */     for (int i = this.all.length - 1; i >= 0; i--) {
/* 303 */       changed = (this.all[i].retainAll(coll)) || (changed);
/*     */     }
/* 305 */     return changed;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 316 */     for (int i = 0; i < this.all.length; i++) {
/* 317 */       this.all[i].clear();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMutator(CollectionMutator<E> mutator)
/*     */   {
/* 328 */     this.mutator = mutator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addComposited(Collection<? extends E>... comps)
/*     */   {
/* 337 */     ArrayList list = new ArrayList(Arrays.asList(this.all));
/* 338 */     list.addAll(Arrays.asList(comps));
/* 339 */     this.all = ((Collection[])list.toArray(new Collection[list.size()]));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addComposited(Collection<? extends E> c)
/*     */   {
/* 348 */     addComposited(new Collection[] { c });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public void addComposited(Collection<? extends E> c, Collection<? extends E> d)
/*     */   {
/* 359 */     addComposited(new Collection[] { c, d });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeComposited(Collection<? extends E> coll)
/*     */   {
/* 368 */     ArrayList list = new ArrayList(this.all.length);
/* 369 */     list.addAll(Arrays.asList(this.all));
/* 370 */     list.remove(coll);
/* 371 */     this.all = ((Collection[])list.toArray(new Collection[list.size()]));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<E> toCollection()
/*     */   {
/* 381 */     return new ArrayList(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<Collection<E>> getCollections()
/*     */   {
/* 390 */     return UnmodifiableList.decorate(Arrays.asList(this.all));
/*     */   }
/*     */   
/*     */   public static abstract interface CollectionMutator<E>
/*     */   {
/*     */     public abstract boolean add(CompositeCollection<? extends E> paramCompositeCollection, Collection<? extends E>[] paramArrayOfCollection, Object paramObject);
/*     */     
/*     */     public abstract boolean addAll(CompositeCollection<? extends E> paramCompositeCollection, Collection<? extends E>[] paramArrayOfCollection, Collection<? extends E> paramCollection);
/*     */     
/*     */     public abstract boolean remove(CompositeCollection<? extends E> paramCompositeCollection, Collection<? extends E>[] paramArrayOfCollection, Object paramObject);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/collection/CompositeCollection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */