/*     */ package org.apache.commons.collections15.iterators;
/*     */ 
/*     */ import java.lang.reflect.Array;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArrayIterator<E>
/*     */   implements ResettableIterator<E>
/*     */ {
/*     */   protected Object array;
/*  52 */   protected int startIndex = 0;
/*     */   
/*     */ 
/*     */ 
/*  56 */   protected int endIndex = 0;
/*     */   
/*     */ 
/*     */ 
/*  60 */   protected int index = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ArrayIterator() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ArrayIterator(Object array)
/*     */   {
/*  84 */     setArray(array);
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
/*     */   public ArrayIterator(Object array, int startIndex)
/*     */   {
/*  99 */     setArray(array);
/* 100 */     checkBound(startIndex, "start");
/* 101 */     this.startIndex = startIndex;
/* 102 */     this.index = startIndex;
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
/*     */   public ArrayIterator(Object array, int startIndex, int endIndex)
/*     */   {
/* 118 */     setArray(array);
/* 119 */     checkBound(startIndex, "start");
/* 120 */     checkBound(endIndex, "end");
/* 121 */     if (endIndex < startIndex) {
/* 122 */       throw new IllegalArgumentException("End index must not be less than start index.");
/*     */     }
/* 124 */     this.startIndex = startIndex;
/* 125 */     this.endIndex = endIndex;
/* 126 */     this.index = startIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void checkBound(int bound, String type)
/*     */   {
/* 137 */     if (bound > this.endIndex) {
/* 138 */       throw new ArrayIndexOutOfBoundsException("Attempt to make an ArrayIterator that " + type + "s beyond the end of the array. ");
/*     */     }
/* 140 */     if (bound < 0) {
/* 141 */       throw new ArrayIndexOutOfBoundsException("Attempt to make an ArrayIterator that " + type + "s before the start of the array. ");
/*     */     }
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
/* 153 */     return this.index < this.endIndex;
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
/* 164 */     if (!hasNext()) {
/* 165 */       throw new NoSuchElementException();
/*     */     }
/* 167 */     return (E)Array.get(this.array, this.index++);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void remove()
/*     */   {
/* 176 */     throw new UnsupportedOperationException("remove() method is not supported");
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
/*     */   public Object getArray()
/*     */   {
/* 189 */     return this.array;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setArray(Object array)
/*     */   {
/* 211 */     this.endIndex = Array.getLength(array);
/* 212 */     this.startIndex = 0;
/* 213 */     this.array = array;
/* 214 */     this.index = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void reset()
/*     */   {
/* 221 */     this.index = this.startIndex;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/ArrayIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */