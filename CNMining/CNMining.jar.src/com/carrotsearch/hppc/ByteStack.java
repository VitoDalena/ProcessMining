/*     */ package com.carrotsearch.hppc;
/*     */ 
/*     */ import com.carrotsearch.hppc.cursors.ByteCursor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ByteStack
/*     */   extends ByteArrayList
/*     */ {
/*     */   public ByteStack() {}
/*     */   
/*     */   public ByteStack(int initialCapacity)
/*     */   {
/*  32 */     super(initialCapacity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ByteStack(int initialCapacity, ArraySizingStrategy resizer)
/*     */   {
/*  40 */     super(initialCapacity, resizer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ByteStack(ByteContainer container)
/*     */   {
/*  48 */     super(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void push(byte e1)
/*     */   {
/*  56 */     ensureBufferSpace(1);
/*  57 */     this.buffer[(this.elementsCount++)] = e1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void push(byte e1, byte e2)
/*     */   {
/*  65 */     ensureBufferSpace(2);
/*  66 */     this.buffer[(this.elementsCount++)] = e1;
/*  67 */     this.buffer[(this.elementsCount++)] = e2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void push(byte e1, byte e2, byte e3)
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
/*     */   public void push(byte e1, byte e2, byte e3, byte e4)
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
/*     */   public void push(byte[] elements, int start, int len)
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
/*     */   public void push(byte... elements)
/*     */   {
/* 112 */     push(elements, 0, elements.length);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int pushAll(ByteContainer container)
/*     */   {
/* 120 */     return addAll(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int pushAll(Iterable<? extends ByteCursor> iterable)
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
/*     */   public byte pop()
/*     */   {
/* 158 */     assert (this.elementsCount > 0);
/*     */     
/* 160 */     byte v = this.buffer[(--this.elementsCount)];
/*     */     
/* 162 */     return v;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte peek()
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
/*     */   public static ByteStack newInstance()
/*     */   {
/* 181 */     return new ByteStack();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ByteStack newInstanceWithCapacity(int initialCapacity)
/*     */   {
/* 190 */     return new ByteStack(initialCapacity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ByteStack from(byte... elements)
/*     */   {
/* 198 */     ByteStack stack = new ByteStack(elements.length);
/* 199 */     stack.push(elements);
/* 200 */     return stack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ByteStack from(ByteContainer container)
/*     */   {
/* 208 */     return new ByteStack(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ByteStack clone()
/*     */   {
/* 217 */     return (ByteStack)super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ByteStack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */