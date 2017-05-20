/*     */ package com.carrotsearch.hppc;
/*     */ 
/*     */ import com.carrotsearch.hppc.cursors.LongCursor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LongStack
/*     */   extends LongArrayList
/*     */ {
/*     */   public LongStack() {}
/*     */   
/*     */   public LongStack(int initialCapacity)
/*     */   {
/*  32 */     super(initialCapacity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public LongStack(int initialCapacity, ArraySizingStrategy resizer)
/*     */   {
/*  40 */     super(initialCapacity, resizer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public LongStack(LongContainer container)
/*     */   {
/*  48 */     super(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void push(long e1)
/*     */   {
/*  56 */     ensureBufferSpace(1);
/*  57 */     this.buffer[(this.elementsCount++)] = e1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void push(long e1, long e2)
/*     */   {
/*  65 */     ensureBufferSpace(2);
/*  66 */     this.buffer[(this.elementsCount++)] = e1;
/*  67 */     this.buffer[(this.elementsCount++)] = e2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void push(long e1, long e2, long e3)
/*     */   {
/*  75 */     ensureBufferSpace(3);
/*  76 */     this.buffer[(this.elementsCount++)] = e1;
/*  77 */     this.buffer[(this.elementsCount++)] = e2;
/*  78 */     this.buffer[(this.elementsCount++)] = e3;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void push(long e1, long e2, long e3, long e4)
/*     */   {
/*  86 */     ensureBufferSpace(4);
/*  87 */     this.buffer[(this.elementsCount++)] = e1;
/*  88 */     this.buffer[(this.elementsCount++)] = e2;
/*  89 */     this.buffer[(this.elementsCount++)] = e3;
/*  90 */     this.buffer[(this.elementsCount++)] = e4;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void push(long[] elements, int start, int len)
/*     */   {
/*  98 */     assert ((start >= 0) && (len >= 0));
/*     */     
/* 100 */     ensureBufferSpace(len);
/* 101 */     System.arraycopy(elements, start, this.buffer, this.elementsCount, len);
/* 102 */     this.elementsCount += len;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void push(long... elements)
/*     */   {
/* 112 */     push(elements, 0, elements.length);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int pushAll(LongContainer container)
/*     */   {
/* 120 */     return addAll(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int pushAll(Iterable<? extends LongCursor> iterable)
/*     */   {
/* 128 */     return addAll(iterable);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void discard(int count)
/*     */   {
/* 136 */     assert (this.elementsCount >= count);
/*     */     
/* 138 */     this.elementsCount -= count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void discard()
/*     */   {
/* 147 */     assert (this.elementsCount > 0);
/*     */     
/* 149 */     this.elementsCount -= 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long pop()
/*     */   {
/* 158 */     assert (this.elementsCount > 0);
/*     */     
/* 160 */     long v = this.buffer[(--this.elementsCount)];
/*     */     
/* 162 */     return v;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long peek()
/*     */   {
/* 170 */     assert (this.elementsCount > 0);
/*     */     
/* 172 */     return this.buffer[(this.elementsCount - 1)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static LongStack newInstance()
/*     */   {
/* 181 */     return new LongStack();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static LongStack newInstanceWithCapacity(int initialCapacity)
/*     */   {
/* 190 */     return new LongStack(initialCapacity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static LongStack from(long... elements)
/*     */   {
/* 198 */     LongStack stack = new LongStack(elements.length);
/* 199 */     stack.push(elements);
/* 200 */     return stack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static LongStack from(LongContainer container)
/*     */   {
/* 208 */     return new LongStack(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LongStack clone()
/*     */   {
/* 217 */     return (LongStack)super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/LongStack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */