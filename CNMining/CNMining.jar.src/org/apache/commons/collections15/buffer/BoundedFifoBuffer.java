/*     */ package org.apache.commons.collections15.buffer;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections15.BoundedCollection;
/*     */ import org.apache.commons.collections15.Buffer;
/*     */ import org.apache.commons.collections15.BufferOverflowException;
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
/*     */ public class BoundedFifoBuffer<E>
/*     */   extends AbstractCollection<E>
/*     */   implements Buffer<E>, BoundedCollection<E>, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 5603722811189451017L;
/*     */   private transient E[] elements;
/*  68 */   private transient int start = 0;
/*  69 */   private transient int end = 0;
/*  70 */   private transient boolean full = false;
/*     */   
/*     */ 
/*     */   private final int maxElements;
/*     */   
/*     */ 
/*     */   public BoundedFifoBuffer()
/*     */   {
/*  78 */     this(32);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BoundedFifoBuffer(int size)
/*     */   {
/*  89 */     if (size <= 0) {
/*  90 */       throw new IllegalArgumentException("The size must be greater than 0");
/*     */     }
/*  92 */     this.elements = ((Object[])new Object[size]);
/*  93 */     this.maxElements = this.elements.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BoundedFifoBuffer(Collection<E> coll)
/*     */   {
/* 105 */     this(coll.size());
/* 106 */     addAll(coll);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void writeObject(ObjectOutputStream out)
/*     */     throws IOException
/*     */   {
/* 117 */     out.defaultWriteObject();
/* 118 */     out.writeInt(size());
/* 119 */     for (Iterator it = iterator(); it.hasNext();) {
/* 120 */       out.writeObject(it.next());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void readObject(ObjectInputStream in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 132 */     in.defaultReadObject();
/* 133 */     this.elements = ((Object[])new Object[this.maxElements]);
/* 134 */     int size = in.readInt();
/* 135 */     for (int i = 0; i < size; i++) {
/* 136 */       this.elements[i] = in.readObject();
/*     */     }
/* 138 */     this.start = 0;
/* 139 */     this.full = (size == this.maxElements);
/* 140 */     if (this.full) {
/* 141 */       this.end = 0;
/*     */     } else {
/* 143 */       this.end = size;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 154 */     int size = 0;
/*     */     
/* 156 */     if (this.end < this.start) {
/* 157 */       size = this.maxElements - this.start + this.end;
/* 158 */     } else if (this.end == this.start) {
/* 159 */       size = this.full ? this.maxElements : 0;
/*     */     } else {
/* 161 */       size = this.end - this.start;
/*     */     }
/*     */     
/* 164 */     return size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 173 */     return size() == 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isFull()
/*     */   {
/* 182 */     return size() == this.maxElements;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int maxSize()
/*     */   {
/* 191 */     return this.maxElements;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 198 */     this.full = false;
/* 199 */     this.start = 0;
/* 200 */     this.end = 0;
/* 201 */     Arrays.fill(this.elements, null);
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
/* 213 */     if (null == element) {
/* 214 */       throw new NullPointerException("Attempted to add null object to buffer");
/*     */     }
/*     */     
/* 217 */     if (this.full) {
/* 218 */       throw new BufferOverflowException("The buffer cannot hold more than " + this.maxElements + " objects.");
/*     */     }
/*     */     
/* 221 */     this.elements[(this.end++)] = element;
/*     */     
/* 223 */     if (this.end >= this.maxElements) {
/* 224 */       this.end = 0;
/*     */     }
/*     */     
/* 227 */     if (this.end == this.start) {
/* 228 */       this.full = true;
/*     */     }
/*     */     
/* 231 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E get()
/*     */   {
/* 241 */     if (isEmpty()) {
/* 242 */       throw new BufferUnderflowException("The buffer is already empty");
/*     */     }
/*     */     
/* 245 */     return (E)this.elements[this.start];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E remove()
/*     */   {
/* 255 */     if (isEmpty()) {
/* 256 */       throw new BufferUnderflowException("The buffer is already empty");
/*     */     }
/*     */     
/* 259 */     E element = this.elements[this.start];
/*     */     
/* 261 */     if (null != element) {
/* 262 */       this.elements[(this.start++)] = null;
/*     */       
/* 264 */       if (this.start >= this.maxElements) {
/* 265 */         this.start = 0;
/*     */       }
/*     */       
/* 268 */       this.full = false;
/*     */     }
/*     */     
/* 271 */     return element;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private int increment(int index)
/*     */   {
/*     */     
/*     */     
/*     */ 
/* 282 */     if (index >= this.maxElements) {
/* 283 */       index = 0;
/*     */     }
/* 285 */     return index;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private int decrement(int index)
/*     */   {
/*     */     
/*     */     
/*     */ 
/* 296 */     if (index < 0) {
/* 297 */       index = this.maxElements - 1;
/*     */     }
/* 299 */     return index;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator<E> iterator()
/*     */   {
/* 308 */     new Iterator()
/*     */     {
/* 310 */       private int index = BoundedFifoBuffer.this.start;
/* 311 */       private int lastReturnedIndex = -1;
/* 312 */       private boolean isFirst = BoundedFifoBuffer.this.full;
/*     */       
/*     */       public boolean hasNext() {
/* 315 */         return (this.isFirst) || (this.index != BoundedFifoBuffer.this.end);
/*     */       }
/*     */       
/*     */       public E next()
/*     */       {
/* 320 */         if (!hasNext()) {
/* 321 */           throw new NoSuchElementException();
/*     */         }
/* 323 */         this.isFirst = false;
/* 324 */         this.lastReturnedIndex = this.index;
/* 325 */         this.index = BoundedFifoBuffer.this.increment(this.index);
/* 326 */         return (E)BoundedFifoBuffer.this.elements[this.lastReturnedIndex];
/*     */       }
/*     */       
/*     */       public void remove() {
/* 330 */         if (this.lastReturnedIndex == -1) {
/* 331 */           throw new IllegalStateException();
/*     */         }
/*     */         
/*     */ 
/* 335 */         if (this.lastReturnedIndex == BoundedFifoBuffer.this.start) {
/* 336 */           BoundedFifoBuffer.this.remove();
/* 337 */           this.lastReturnedIndex = -1;
/* 338 */           return;
/*     */         }
/*     */         
/*     */ 
/* 342 */         int i = this.lastReturnedIndex + 1;
/* 343 */         while (i != BoundedFifoBuffer.this.end) {
/* 344 */           if (i >= BoundedFifoBuffer.this.maxElements) {
/* 345 */             BoundedFifoBuffer.this.elements[(i - 1)] = BoundedFifoBuffer.this.elements[0];
/* 346 */             i = 0;
/*     */           } else {
/* 348 */             BoundedFifoBuffer.this.elements[(i - 1)] = BoundedFifoBuffer.this.elements[i];
/* 349 */             i++;
/*     */           }
/*     */         }
/*     */         
/* 353 */         this.lastReturnedIndex = -1;
/* 354 */         BoundedFifoBuffer.this.end = BoundedFifoBuffer.this.decrement(BoundedFifoBuffer.this.end);
/* 355 */         BoundedFifoBuffer.this.elements[BoundedFifoBuffer.this.end] = null;
/* 356 */         BoundedFifoBuffer.this.full = false;
/* 357 */         this.index = BoundedFifoBuffer.this.decrement(this.index);
/*     */       }
/*     */     };
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/buffer/BoundedFifoBuffer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */