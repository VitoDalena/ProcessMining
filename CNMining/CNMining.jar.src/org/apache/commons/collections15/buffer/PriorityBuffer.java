/*     */ package org.apache.commons.collections15.buffer;
/*     */ 
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections15.Buffer;
/*     */ import org.apache.commons.collections15.BufferUnderflowException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PriorityBuffer<E>
/*     */   extends AbstractCollection<E>
/*     */   implements Buffer<E>
/*     */ {
/*     */   private static final int DEFAULT_CAPACITY = 13;
/*     */   protected E[] elements;
/*     */   protected int size;
/*     */   protected boolean ascendingOrder;
/*     */   protected Comparator<? super E> comparator;
/*     */   
/*     */   public PriorityBuffer()
/*     */   {
/*  93 */     this(13, true, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PriorityBuffer(Comparator<? super E> comparator)
/*     */   {
/* 104 */     this(13, true, comparator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PriorityBuffer(boolean ascendingOrder)
/*     */   {
/* 115 */     this(13, ascendingOrder, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PriorityBuffer(boolean ascendingOrder, Comparator<? super E> comparator)
/*     */   {
/* 127 */     this(13, ascendingOrder, comparator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PriorityBuffer(int capacity)
/*     */   {
/* 138 */     this(capacity, true, null);
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
/*     */   public PriorityBuffer(int capacity, Comparator<? super E> comparator)
/*     */   {
/* 151 */     this(capacity, true, comparator);
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
/*     */   public PriorityBuffer(int capacity, boolean ascendingOrder)
/*     */   {
/* 164 */     this(capacity, ascendingOrder, null);
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
/*     */   public PriorityBuffer(int capacity, boolean ascendingOrder, Comparator<? super E> comparator)
/*     */   {
/* 180 */     if (capacity <= 0) {
/* 181 */       throw new IllegalArgumentException("invalid capacity");
/*     */     }
/* 183 */     this.ascendingOrder = ascendingOrder;
/*     */     
/*     */ 
/* 186 */     this.elements = ((Object[])new Object[capacity + 1]);
/* 187 */     this.comparator = comparator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAscendingOrder()
/*     */   {
/* 197 */     return this.ascendingOrder;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparator<? super E> comparator()
/*     */   {
/* 206 */     return this.comparator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 216 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 223 */     this.elements = ((Object[])new Object[this.elements.length]);
/* 224 */     this.size = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean add(E element)
/*     */   {
/* 236 */     if (isAtCapacity()) {
/* 237 */       grow();
/*     */     }
/*     */     
/* 240 */     if (this.ascendingOrder) {
/* 241 */       percolateUpMinHeap(element);
/*     */     } else {
/* 243 */       percolateUpMaxHeap(element);
/*     */     }
/* 245 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E get()
/*     */   {
/* 255 */     if (isEmpty()) {
/* 256 */       throw new BufferUnderflowException();
/*     */     }
/* 258 */     return (E)this.elements[1];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E remove()
/*     */   {
/* 269 */     E result = get();
/* 270 */     this.elements[1] = this.elements[(this.size--)];
/*     */     
/*     */ 
/*     */ 
/* 274 */     this.elements[(this.size + 1)] = null;
/*     */     
/* 276 */     if (this.size != 0)
/*     */     {
/* 278 */       if (this.ascendingOrder) {
/* 279 */         percolateDownMinHeap(1);
/*     */       } else {
/* 281 */         percolateDownMaxHeap(1);
/*     */       }
/*     */     }
/*     */     
/* 285 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean isAtCapacity()
/*     */   {
/* 296 */     return this.elements.length == this.size + 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void percolateDownMinHeap(int index)
/*     */   {
/* 308 */     E element = this.elements[index];
/* 309 */     int hole = index;
/*     */     
/* 311 */     while (hole * 2 <= this.size) {
/* 312 */       int child = hole * 2;
/*     */       
/*     */ 
/*     */ 
/* 316 */       if ((child != this.size) && (compare(this.elements[(child + 1)], this.elements[child]) < 0)) {
/* 317 */         child++;
/*     */       }
/*     */       
/*     */ 
/* 321 */       if (compare(this.elements[child], element) >= 0) {
/*     */         break;
/*     */       }
/*     */       
/* 325 */       this.elements[hole] = this.elements[child];
/* 326 */       hole = child;
/*     */     }
/*     */     
/* 329 */     this.elements[hole] = element;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void percolateDownMaxHeap(int index)
/*     */   {
/* 340 */     E element = this.elements[index];
/* 341 */     int hole = index;
/*     */     
/* 343 */     while (hole * 2 <= this.size) {
/* 344 */       int child = hole * 2;
/*     */       
/*     */ 
/*     */ 
/* 348 */       if ((child != this.size) && (compare(this.elements[(child + 1)], this.elements[child]) > 0)) {
/* 349 */         child++;
/*     */       }
/*     */       
/*     */ 
/* 353 */       if (compare(this.elements[child], element) <= 0) {
/*     */         break;
/*     */       }
/*     */       
/* 357 */       this.elements[hole] = this.elements[child];
/* 358 */       hole = child;
/*     */     }
/*     */     
/* 361 */     this.elements[hole] = element;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void percolateUpMinHeap(int index)
/*     */   {
/* 372 */     int hole = index;
/* 373 */     E element = this.elements[hole];
/* 374 */     while ((hole > 1) && (compare(element, this.elements[(hole / 2)]) < 0))
/*     */     {
/*     */ 
/* 377 */       int next = hole / 2;
/* 378 */       this.elements[hole] = this.elements[next];
/* 379 */       hole = next;
/*     */     }
/* 381 */     this.elements[hole] = element;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void percolateUpMinHeap(E element)
/*     */   {
/* 392 */     this.elements[(++this.size)] = element;
/* 393 */     percolateUpMinHeap(this.size);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void percolateUpMaxHeap(int index)
/*     */   {
/* 404 */     int hole = index;
/* 405 */     E element = this.elements[hole];
/*     */     
/* 407 */     while ((hole > 1) && (compare(element, this.elements[(hole / 2)]) > 0))
/*     */     {
/*     */ 
/* 410 */       int next = hole / 2;
/* 411 */       this.elements[hole] = this.elements[next];
/* 412 */       hole = next;
/*     */     }
/*     */     
/* 415 */     this.elements[hole] = element;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void percolateUpMaxHeap(E element)
/*     */   {
/* 426 */     this.elements[(++this.size)] = element;
/* 427 */     percolateUpMaxHeap(this.size);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int compare(E a, E b)
/*     */   {
/* 439 */     if (this.comparator != null) {
/* 440 */       return this.comparator.compare(a, b);
/*     */     }
/* 442 */     return ((Comparable)a).compareTo(b);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void grow()
/*     */   {
/* 450 */     E[] array = (Object[])new Object[this.elements.length * 2];
/* 451 */     System.arraycopy(this.elements, 0, array, 0, this.elements.length);
/* 452 */     this.elements = array;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator<E> iterator()
/*     */   {
/* 462 */     new Iterator()
/*     */     {
/* 464 */       private int index = 1;
/* 465 */       private int lastReturnedIndex = -1;
/*     */       
/*     */       public boolean hasNext() {
/* 468 */         return this.index <= PriorityBuffer.this.size;
/*     */       }
/*     */       
/*     */       public E next() {
/* 472 */         if (!hasNext()) {
/* 473 */           throw new NoSuchElementException();
/*     */         }
/* 475 */         this.lastReturnedIndex = this.index;
/* 476 */         this.index += 1;
/* 477 */         return (E)PriorityBuffer.this.elements[this.lastReturnedIndex];
/*     */       }
/*     */       
/*     */       public void remove() {
/* 481 */         if (this.lastReturnedIndex == -1) {
/* 482 */           throw new IllegalStateException();
/*     */         }
/* 484 */         PriorityBuffer.this.elements[this.lastReturnedIndex] = PriorityBuffer.this.elements[PriorityBuffer.this.size];
/* 485 */         PriorityBuffer.this.elements[PriorityBuffer.this.size] = null;
/* 486 */         PriorityBuffer.this.size -= 1;
/* 487 */         if ((PriorityBuffer.this.size != 0) && (this.lastReturnedIndex <= PriorityBuffer.this.size)) {
/* 488 */           int compareToParent = 0;
/* 489 */           if (this.lastReturnedIndex > 1) {
/* 490 */             compareToParent = PriorityBuffer.this.compare(PriorityBuffer.this.elements[this.lastReturnedIndex], PriorityBuffer.this.elements[(this.lastReturnedIndex / 2)]);
/*     */           }
/* 492 */           if (PriorityBuffer.this.ascendingOrder) {
/* 493 */             if ((this.lastReturnedIndex > 1) && (compareToParent < 0)) {
/* 494 */               PriorityBuffer.this.percolateUpMinHeap(this.lastReturnedIndex);
/*     */             } else {
/* 496 */               PriorityBuffer.this.percolateDownMinHeap(this.lastReturnedIndex);
/*     */             }
/*     */           }
/* 499 */           else if ((this.lastReturnedIndex > 1) && (compareToParent > 0)) {
/* 500 */             PriorityBuffer.this.percolateUpMaxHeap(this.lastReturnedIndex);
/*     */           } else {
/* 502 */             PriorityBuffer.this.percolateDownMaxHeap(this.lastReturnedIndex);
/*     */           }
/*     */         }
/*     */         
/* 506 */         this.index -= 1;
/* 507 */         this.lastReturnedIndex = -1;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 520 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 522 */     sb.append("[ ");
/*     */     
/* 524 */     for (int i = 1; i < this.size + 1; i++) {
/* 525 */       if (i != 1) {
/* 526 */         sb.append(", ");
/*     */       }
/* 528 */       sb.append(this.elements[i]);
/*     */     }
/*     */     
/* 531 */     sb.append(" ]");
/*     */     
/* 533 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/buffer/PriorityBuffer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */