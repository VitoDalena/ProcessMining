/*     */ package com.carrotsearch.hppc;
/*     */ 
/*     */ import com.carrotsearch.hppc.cursors.KTypeCursor;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KTypeStack<KType>
/*     */   extends KTypeArrayList<KType>
/*     */ {
/*     */   public KTypeStack() {}
/*     */   
/*     */   public KTypeStack(int initialCapacity)
/*     */   {
/*  55 */     super(initialCapacity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public KTypeStack(int initialCapacity, ArraySizingStrategy resizer)
/*     */   {
/*  63 */     super(initialCapacity, resizer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public KTypeStack(KTypeContainer<KType> container)
/*     */   {
/*  71 */     super(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void push(KType e1)
/*     */   {
/*  79 */     ensureBufferSpace(1);
/*  80 */     this.buffer[(this.elementsCount++)] = e1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void push(KType e1, KType e2)
/*     */   {
/*  88 */     ensureBufferSpace(2);
/*  89 */     this.buffer[(this.elementsCount++)] = e1;
/*  90 */     this.buffer[(this.elementsCount++)] = e2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void push(KType e1, KType e2, KType e3)
/*     */   {
/*  98 */     ensureBufferSpace(3);
/*  99 */     this.buffer[(this.elementsCount++)] = e1;
/* 100 */     this.buffer[(this.elementsCount++)] = e2;
/* 101 */     this.buffer[(this.elementsCount++)] = e3;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void push(KType e1, KType e2, KType e3, KType e4)
/*     */   {
/* 109 */     ensureBufferSpace(4);
/* 110 */     this.buffer[(this.elementsCount++)] = e1;
/* 111 */     this.buffer[(this.elementsCount++)] = e2;
/* 112 */     this.buffer[(this.elementsCount++)] = e3;
/* 113 */     this.buffer[(this.elementsCount++)] = e4;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void push(KType[] elements, int start, int len)
/*     */   {
/* 121 */     assert ((start >= 0) && (len >= 0));
/*     */     
/* 123 */     ensureBufferSpace(len);
/* 124 */     System.arraycopy(elements, start, this.buffer, this.elementsCount, len);
/* 125 */     this.elementsCount += len;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void push(KType... elements)
/*     */   {
/* 135 */     push(elements, 0, elements.length);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int pushAll(KTypeContainer<? extends KType> container)
/*     */   {
/* 143 */     return addAll(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int pushAll(Iterable<? extends KTypeCursor<? extends KType>> iterable)
/*     */   {
/* 151 */     return addAll(iterable);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void discard(int count)
/*     */   {
/* 159 */     assert (this.elementsCount >= count);
/*     */     
/* 161 */     this.elementsCount -= count;
/*     */     
/* 163 */     Arrays.fill(this.buffer, this.elementsCount, this.elementsCount + count, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void discard()
/*     */   {
/* 172 */     assert (this.elementsCount > 0);
/*     */     
/* 174 */     this.elementsCount -= 1;
/*     */     
/* 176 */     this.buffer[this.elementsCount] = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KType pop()
/*     */   {
/* 185 */     assert (this.elementsCount > 0);
/*     */     
/* 187 */     KType v = this.buffer[(--this.elementsCount)];
/*     */     
/* 189 */     this.buffer[this.elementsCount] = null;
/*     */     
/* 191 */     return v;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public KType peek()
/*     */   {
/* 199 */     assert (this.elementsCount > 0);
/*     */     
/* 201 */     return (KType)this.buffer[(this.elementsCount - 1)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> KTypeStack<KType> newInstance()
/*     */   {
/* 210 */     return new KTypeStack();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> KTypeStack<KType> newInstanceWithCapacity(int initialCapacity)
/*     */   {
/* 219 */     return new KTypeStack(initialCapacity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> KTypeStack<KType> from(KType... elements)
/*     */   {
/* 227 */     KTypeStack<KType> stack = new KTypeStack(elements.length);
/* 228 */     stack.push(elements);
/* 229 */     return stack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> KTypeStack<KType> from(KTypeContainer<KType> container)
/*     */   {
/* 237 */     return new KTypeStack(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KTypeStack<KType> clone()
/*     */   {
/* 246 */     return (KTypeStack)super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/KTypeStack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */