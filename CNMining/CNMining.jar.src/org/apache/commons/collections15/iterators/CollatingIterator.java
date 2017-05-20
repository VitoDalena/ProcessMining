/*     */ package org.apache.commons.collections15.iterators;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.BitSet;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
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
/*     */ public class CollatingIterator<E>
/*     */   implements Iterator<E>
/*     */ {
/*  41 */   private Comparator<? super E> comparator = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  46 */   private ArrayList<Iterator<? extends E>> iterators = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  51 */   private ArrayList<E> values = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  56 */   private BitSet valueSet = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  61 */   private int lastReturned = -1;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CollatingIterator()
/*     */   {
/*  71 */     this(null, 2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CollatingIterator(Comparator<? super E> comp)
/*     */   {
/*  82 */     this(comp, 2);
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
/*     */   public CollatingIterator(Comparator<? super E> comp, int initIterCapacity)
/*     */   {
/*  96 */     this.iterators = new ArrayList(initIterCapacity);
/*  97 */     setComparator(comp);
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
/*     */   public CollatingIterator(Comparator<? super E> comp, Iterator<? extends E> a, Iterator<? extends E> b)
/*     */   {
/* 111 */     this(comp, 2);
/* 112 */     addIterator(a);
/* 113 */     addIterator(b);
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
/*     */   public CollatingIterator(Comparator<? super E> comp, Iterator<? extends E>[] iterators)
/*     */   {
/* 126 */     this(comp, iterators.length);
/* 127 */     for (int i = 0; i < iterators.length; i++) {
/* 128 */       addIterator(iterators[i]);
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
/*     */   public CollatingIterator(Comparator<? super E> comp, Collection<Iterator<? extends E>> iterators)
/*     */   {
/* 144 */     this(comp, iterators.size());
/* 145 */     for (Iterator<Iterator<? extends E>> it = iterators.iterator(); it.hasNext();) {
/* 146 */       Iterator<? extends E> item = (Iterator)it.next();
/* 147 */       addIterator(item);
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
/*     */   public void addIterator(Iterator<? extends E> iterator)
/*     */   {
/* 161 */     checkNotStarted();
/* 162 */     if (iterator == null) {
/* 163 */       throw new NullPointerException("Iterator must not be null");
/*     */     }
/* 165 */     this.iterators.add(iterator);
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
/*     */   public void setIterator(int index, Iterator<? extends E> iterator)
/*     */   {
/* 178 */     checkNotStarted();
/* 179 */     if (iterator == null) {
/* 180 */       throw new NullPointerException("Iterator must not be null");
/*     */     }
/* 182 */     this.iterators.set(index, iterator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<Iterator<? extends E>> getIterators()
/*     */   {
/* 191 */     return UnmodifiableList.decorate(this.iterators);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Comparator<? super E> getComparator()
/*     */   {
/* 198 */     return this.comparator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setComparator(Comparator<? super E> comp)
/*     */   {
/* 207 */     checkNotStarted();
/* 208 */     this.comparator = comp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean hasNext()
/*     */   {
/* 219 */     start();
/* 220 */     return (anyValueSet(this.valueSet)) || (anyHasNext(this.iterators));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E next()
/*     */     throws NoSuchElementException
/*     */   {
/* 230 */     if (!hasNext()) {
/* 231 */       throw new NoSuchElementException();
/*     */     }
/* 233 */     int leastIndex = least();
/* 234 */     if (leastIndex == -1) {
/* 235 */       throw new NoSuchElementException();
/*     */     }
/* 237 */     E val = this.values.get(leastIndex);
/* 238 */     clear(leastIndex);
/* 239 */     this.lastReturned = leastIndex;
/* 240 */     return val;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void remove()
/*     */   {
/* 252 */     if (this.lastReturned == -1) {
/* 253 */       throw new IllegalStateException("No value can be removed at present");
/*     */     }
/* 255 */     Iterator<? extends E> it = (Iterator)this.iterators.get(this.lastReturned);
/* 256 */     it.remove();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void start()
/*     */   {
/* 265 */     if (this.values == null) {
/* 266 */       this.values = new ArrayList(this.iterators.size());
/* 267 */       this.valueSet = new BitSet(this.iterators.size());
/* 268 */       for (int i = 0; i < this.iterators.size(); i++) {
/* 269 */         this.values.add(null);
/* 270 */         this.valueSet.clear(i);
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
/*     */   private boolean set(int i)
/*     */   {
/* 285 */     Iterator<? extends E> it = (Iterator)this.iterators.get(i);
/* 286 */     if (it.hasNext()) {
/* 287 */       this.values.set(i, it.next());
/* 288 */       this.valueSet.set(i);
/* 289 */       return true;
/*     */     }
/* 291 */     this.values.set(i, null);
/* 292 */     this.valueSet.clear(i);
/* 293 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void clear(int i)
/*     */   {
/* 302 */     this.values.set(i, null);
/* 303 */     this.valueSet.clear(i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void checkNotStarted()
/*     */     throws IllegalStateException
/*     */   {
/* 313 */     if (this.values != null) {
/* 314 */       throw new IllegalStateException("Can't do that after next or hasNext has been called.");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int least()
/*     */   {
/* 325 */     int leastIndex = -1;
/* 326 */     E leastObject = null;
/* 327 */     for (int i = 0; i < this.values.size(); i++) {
/* 328 */       if (!this.valueSet.get(i)) {
/* 329 */         set(i);
/*     */       }
/* 331 */       if (this.valueSet.get(i)) {
/* 332 */         if (leastIndex == -1) {
/* 333 */           leastIndex = i;
/* 334 */           leastObject = this.values.get(i);
/*     */         } else {
/* 336 */           E curObject = this.values.get(i);
/* 337 */           if (this.comparator.compare(curObject, leastObject) < 0) {
/* 338 */             leastObject = curObject;
/* 339 */             leastIndex = i;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 344 */     return leastIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean anyValueSet(BitSet set)
/*     */   {
/* 352 */     for (int i = 0; i < set.size(); i++) {
/* 353 */       if (set.get(i)) {
/* 354 */         return true;
/*     */       }
/*     */     }
/* 357 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean anyHasNext(ArrayList<Iterator<? extends E>> iters)
/*     */   {
/* 365 */     for (int i = 0; i < iters.size(); i++) {
/* 366 */       Iterator<? extends E> it = (Iterator)iters.get(i);
/* 367 */       if (it.hasNext()) {
/* 368 */         return true;
/*     */       }
/*     */     }
/* 371 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/CollatingIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */