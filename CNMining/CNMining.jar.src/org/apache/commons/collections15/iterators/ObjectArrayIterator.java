/*     */ package org.apache.commons.collections15.iterators;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections15.ResettableIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ObjectArrayIterator<E>
/*     */   implements Iterator<E>, ResettableIterator<E>
/*     */ {
/*  47 */   protected E[] array = null;
/*     */   
/*     */ 
/*     */ 
/*  51 */   protected int startIndex = 0;
/*     */   
/*     */ 
/*     */ 
/*  55 */   protected int endIndex = 0;
/*     */   
/*     */ 
/*     */ 
/*  59 */   protected int index = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectArrayIterator() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectArrayIterator(E[] array)
/*     */   {
/*  79 */     this(array, 0, array.length);
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
/*     */   public ObjectArrayIterator(E[] array, int start)
/*     */   {
/*  92 */     this(array, start, array.length);
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
/*     */   public ObjectArrayIterator(E[] array, int start, int end)
/*     */   {
/* 108 */     if (start < 0) {
/* 109 */       throw new ArrayIndexOutOfBoundsException("Start index must not be less than zero");
/*     */     }
/* 111 */     if (end > array.length) {
/* 112 */       throw new ArrayIndexOutOfBoundsException("End index must not be greater than the array length");
/*     */     }
/* 114 */     if (start > array.length) {
/* 115 */       throw new ArrayIndexOutOfBoundsException("Start index must not be greater than the array length");
/*     */     }
/* 117 */     if (end < start) {
/* 118 */       throw new IllegalArgumentException("End index must not be less than start index");
/*     */     }
/* 120 */     this.array = array;
/* 121 */     this.startIndex = start;
/* 122 */     this.endIndex = end;
/* 123 */     this.index = start;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean hasNext()
/*     */   {
/* 135 */     return this.index < this.endIndex;
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
/* 146 */     if (!hasNext()) {
/* 147 */       throw new NoSuchElementException();
/*     */     }
/* 149 */     return (E)this.array[(this.index++)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void remove()
/*     */   {
/* 158 */     throw new UnsupportedOperationException("remove() method is not supported for an ObjectArrayIterator");
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
/*     */   public E[] getArray()
/*     */   {
/* 172 */     return this.array;
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
/*     */   public void setArray(E[] array)
/*     */   {
/* 188 */     if (this.array != null) {
/* 189 */       throw new IllegalStateException("The array to iterate over has already been set");
/*     */     }
/* 191 */     this.array = array;
/* 192 */     this.startIndex = 0;
/* 193 */     this.endIndex = array.length;
/* 194 */     this.index = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getStartIndex()
/*     */   {
/* 203 */     return this.startIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getEndIndex()
/*     */   {
/* 212 */     return this.endIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void reset()
/*     */   {
/* 219 */     this.index = this.startIndex;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/ObjectArrayIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */