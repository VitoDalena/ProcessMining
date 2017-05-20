/*     */ package org.apache.commons.collections15.iterators;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IteratorChain<E>
/*     */   implements Iterator<E>
/*     */ {
/*  56 */   protected final List<Iterator<? extends E>> iteratorChain = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*  60 */   protected int currentIteratorIndex = 0;
/*     */   
/*     */ 
/*     */ 
/*  64 */   protected Iterator<? extends E> currentIterator = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  70 */   protected Iterator<? extends E> lastUsedIterator = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  75 */   protected boolean isLocked = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IteratorChain() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IteratorChain(Iterator<? extends E> iterator)
/*     */   {
/*  96 */     addIterator(iterator);
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
/*     */   public IteratorChain(Iterator<? extends E> a, Iterator<? extends E> b)
/*     */   {
/* 109 */     addIterator(a);
/* 110 */     addIterator(b);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IteratorChain(Iterator<? extends E>[] iterators)
/*     */   {
/* 122 */     for (int i = 0; i < iterators.length; i++) {
/* 123 */       addIterator(iterators[i]);
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
/*     */   public IteratorChain(Collection<Iterator<? extends E>> iterators)
/*     */   {
/* 137 */     for (Iterator<? extends E> iterator : iterators) {
/* 138 */       addIterator(iterator);
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
/*     */   public void addIterator(Iterator<? extends E> iterator)
/*     */   {
/* 151 */     checkLocked();
/* 152 */     if (iterator == null) {
/* 153 */       throw new NullPointerException("Iterator must not be null");
/*     */     }
/* 155 */     this.iteratorChain.add(iterator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setIterator(int index, Iterator<? extends E> iterator)
/*     */     throws IndexOutOfBoundsException
/*     */   {
/* 168 */     checkLocked();
/* 169 */     if (iterator == null) {
/* 170 */       throw new NullPointerException("Iterator must not be null");
/*     */     }
/* 172 */     this.iteratorChain.set(index, iterator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<Iterator<? extends E>> getIterators()
/*     */   {
/* 181 */     return UnmodifiableList.decorate(this.iteratorChain);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 190 */     return this.iteratorChain.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLocked()
/*     */   {
/* 201 */     return this.isLocked;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void checkLocked()
/*     */   {
/* 208 */     if (this.isLocked == true) {
/* 209 */       throw new UnsupportedOperationException("IteratorChain cannot be changed after the first use of a method from the Iterator interface");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void lockChain()
/*     */   {
/* 218 */     if (!this.isLocked) {
/* 219 */       this.isLocked = true;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void updateCurrentIterator()
/*     */   {
/* 228 */     if (this.currentIterator == null) {
/* 229 */       if (this.iteratorChain.isEmpty()) {
/* 230 */         this.currentIterator = EmptyIterator.INSTANCE;
/*     */       } else {
/* 232 */         this.currentIterator = ((Iterator)this.iteratorChain.get(0));
/*     */       }
/*     */       
/*     */ 
/* 236 */       this.lastUsedIterator = this.currentIterator;
/*     */     }
/*     */     
/* 239 */     while ((!this.currentIterator.hasNext()) && (this.currentIteratorIndex < this.iteratorChain.size() - 1)) {
/* 240 */       this.currentIteratorIndex += 1;
/* 241 */       this.currentIterator = ((Iterator)this.iteratorChain.get(this.currentIteratorIndex));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean hasNext()
/*     */   {
/* 252 */     lockChain();
/* 253 */     updateCurrentIterator();
/* 254 */     this.lastUsedIterator = this.currentIterator;
/*     */     
/* 256 */     return this.currentIterator.hasNext();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E next()
/*     */   {
/* 267 */     lockChain();
/* 268 */     updateCurrentIterator();
/* 269 */     this.lastUsedIterator = this.currentIterator;
/*     */     
/* 271 */     return (E)this.currentIterator.next();
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
/*     */   public void remove()
/*     */   {
/* 287 */     lockChain();
/* 288 */     updateCurrentIterator();
/*     */     
/* 290 */     this.lastUsedIterator.remove();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/IteratorChain.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */