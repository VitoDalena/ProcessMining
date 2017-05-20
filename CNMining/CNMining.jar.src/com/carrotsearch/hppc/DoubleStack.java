/*     */ package com.carrotsearch.hppc;
/*     */ 
/*     */ import com.carrotsearch.hppc.cursors.DoubleCursor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DoubleStack
/*     */   extends DoubleArrayList
/*     */ {
/*     */   public DoubleStack() {}
/*     */   
/*     */   public DoubleStack(int initialCapacity)
/*     */   {
/*  32 */     super(initialCapacity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleStack(int initialCapacity, ArraySizingStrategy resizer)
/*     */   {
/*  40 */     super(initialCapacity, resizer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleStack(DoubleContainer container)
/*     */   {
/*  48 */     super(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void push(double e1)
/*     */   {
/*  56 */     ensureBufferSpace(1);
/*  57 */     this.buffer[(this.elementsCount++)] = e1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void push(double e1, double e2)
/*     */   {
/*  65 */     ensureBufferSpace(2);
/*  66 */     this.buffer[(this.elementsCount++)] = e1;
/*  67 */     this.buffer[(this.elementsCount++)] = e2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void push(double e1, double e2, double e3)
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
/*     */   public void push(double e1, double e2, double e3, double e4)
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
/*     */   public void push(double[] elements, int start, int len)
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
/*     */   public void push(double... elements)
/*     */   {
/* 112 */     push(elements, 0, elements.length);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int pushAll(DoubleContainer container)
/*     */   {
/* 120 */     return addAll(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int pushAll(Iterable<? extends DoubleCursor> iterable)
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
/*     */   public double pop()
/*     */   {
/* 158 */     assert (this.elementsCount > 0);
/*     */     
/* 160 */     double v = this.buffer[(--this.elementsCount)];
/*     */     
/* 162 */     return v;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double peek()
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
/*     */   public static DoubleStack newInstance()
/*     */   {
/* 181 */     return new DoubleStack();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleStack newInstanceWithCapacity(int initialCapacity)
/*     */   {
/* 190 */     return new DoubleStack(initialCapacity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleStack from(double... elements)
/*     */   {
/* 198 */     DoubleStack stack = new DoubleStack(elements.length);
/* 199 */     stack.push(elements);
/* 200 */     return stack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleStack from(DoubleContainer container)
/*     */   {
/* 208 */     return new DoubleStack(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleStack clone()
/*     */   {
/* 217 */     return (DoubleStack)super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/DoubleStack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */