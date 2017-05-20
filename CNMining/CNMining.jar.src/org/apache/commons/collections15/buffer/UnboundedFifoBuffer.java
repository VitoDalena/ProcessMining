/*     */ package org.apache.commons.collections15.buffer;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractCollection;
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
/*     */ public class UnboundedFifoBuffer<E>
/*     */   extends AbstractCollection<E>
/*     */   implements Buffer<E>, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3482960336579541419L;
/*     */   protected transient E[] buffer;
/*     */   protected transient int head;
/*     */   protected transient int tail;
/*     */   
/*     */   public UnboundedFifoBuffer()
/*     */   {
/*  90 */     this(32);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public UnboundedFifoBuffer(int initialSize)
/*     */   {
/* 101 */     if (initialSize <= 0) {
/* 102 */       throw new IllegalArgumentException("The size must be greater than 0");
/*     */     }
/* 104 */     this.buffer = ((Object[])new Object[initialSize + 1]);
/* 105 */     this.head = 0;
/* 106 */     this.tail = 0;
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
/* 133 */     int size = in.readInt();
/* 134 */     this.buffer = ((Object[])new Object[size]);
/* 135 */     for (int i = 0; i < size; i++) {
/* 136 */       this.buffer[i] = in.readObject();
/*     */     }
/* 138 */     this.head = 0;
/* 139 */     this.tail = size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 149 */     int size = 0;
/*     */     
/* 151 */     if (this.tail < this.head) {
/* 152 */       size = this.buffer.length - this.head + this.tail;
/*     */     } else {
/* 154 */       size = this.tail - this.head;
/*     */     }
/*     */     
/* 157 */     return size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 166 */     return size() == 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean add(E obj)
/*     */   {
/* 177 */     if (obj == null) {
/* 178 */       throw new NullPointerException("Attempted to add null object to buffer");
/*     */     }
/*     */     
/* 181 */     if (size() + 1 >= this.buffer.length) {
/* 182 */       E[] tmp = (Object[])new Object[(this.buffer.length - 1) * 2 + 1];
/*     */       
/* 184 */       int j = 0;
/* 185 */       for (int i = this.head; i != this.tail;) {
/* 186 */         tmp[j] = this.buffer[i];
/* 187 */         this.buffer[i] = null;
/*     */         
/* 189 */         j++;
/* 190 */         i++;
/* 191 */         if (i == this.buffer.length) {
/* 192 */           i = 0;
/*     */         }
/*     */       }
/*     */       
/* 196 */       this.buffer = tmp;
/* 197 */       this.head = 0;
/* 198 */       this.tail = j;
/*     */     }
/*     */     
/* 201 */     this.buffer[this.tail] = obj;
/* 202 */     this.tail += 1;
/* 203 */     if (this.tail >= this.buffer.length) {
/* 204 */       this.tail = 0;
/*     */     }
/* 206 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E get()
/*     */   {
/* 216 */     if (isEmpty()) {
/* 217 */       throw new BufferUnderflowException("The buffer is already empty");
/*     */     }
/*     */     
/* 220 */     return (E)this.buffer[this.head];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E remove()
/*     */   {
/* 230 */     if (isEmpty()) {
/* 231 */       throw new BufferUnderflowException("The buffer is already empty");
/*     */     }
/*     */     
/* 234 */     E element = this.buffer[this.head];
/*     */     
/* 236 */     if (null != element) {
/* 237 */       this.buffer[this.head] = null;
/*     */       
/* 239 */       this.head += 1;
/* 240 */       if (this.head >= this.buffer.length) {
/* 241 */         this.head = 0;
/*     */       }
/*     */     }
/*     */     
/* 245 */     return element;
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
/* 256 */     if (index >= this.buffer.length) {
/* 257 */       index = 0;
/*     */     }
/* 259 */     return index;
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
/* 270 */     if (index < 0) {
/* 271 */       index = this.buffer.length - 1;
/*     */     }
/* 273 */     return index;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator<E> iterator()
/*     */   {
/* 282 */     new Iterator()
/*     */     {
/* 284 */       private int index = UnboundedFifoBuffer.this.head;
/* 285 */       private int lastReturnedIndex = -1;
/*     */       
/*     */       public boolean hasNext() {
/* 288 */         return this.index != UnboundedFifoBuffer.this.tail;
/*     */       }
/*     */       
/*     */       public E next()
/*     */       {
/* 293 */         if (!hasNext()) {
/* 294 */           throw new NoSuchElementException();
/*     */         }
/* 296 */         this.lastReturnedIndex = this.index;
/* 297 */         this.index = UnboundedFifoBuffer.this.increment(this.index);
/* 298 */         return (E)UnboundedFifoBuffer.this.buffer[this.lastReturnedIndex];
/*     */       }
/*     */       
/*     */       public void remove() {
/* 302 */         if (this.lastReturnedIndex == -1) {
/* 303 */           throw new IllegalStateException();
/*     */         }
/*     */         
/*     */ 
/* 307 */         if (this.lastReturnedIndex == UnboundedFifoBuffer.this.head) {
/* 308 */           UnboundedFifoBuffer.this.remove();
/* 309 */           this.lastReturnedIndex = -1;
/* 310 */           return;
/*     */         }
/*     */         
/*     */ 
/* 314 */         int i = this.lastReturnedIndex + 1;
/* 315 */         while (i != UnboundedFifoBuffer.this.tail) {
/* 316 */           if (i >= UnboundedFifoBuffer.this.buffer.length) {
/* 317 */             UnboundedFifoBuffer.this.buffer[(i - 1)] = UnboundedFifoBuffer.this.buffer[0];
/* 318 */             i = 0;
/*     */           } else {
/* 320 */             UnboundedFifoBuffer.this.buffer[(i - 1)] = UnboundedFifoBuffer.this.buffer[i];
/* 321 */             i++;
/*     */           }
/*     */         }
/*     */         
/* 325 */         this.lastReturnedIndex = -1;
/* 326 */         UnboundedFifoBuffer.this.tail = UnboundedFifoBuffer.this.decrement(UnboundedFifoBuffer.this.tail);
/* 327 */         UnboundedFifoBuffer.this.buffer[UnboundedFifoBuffer.this.tail] = null;
/* 328 */         this.index = UnboundedFifoBuffer.this.decrement(this.index);
/*     */       }
/*     */     };
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/buffer/UnboundedFifoBuffer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */