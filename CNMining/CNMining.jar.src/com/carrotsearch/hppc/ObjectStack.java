/*     */ package com.carrotsearch.hppc;
/*     */ 
/*     */ import com.carrotsearch.hppc.cursors.ObjectCursor;
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
/*     */ public class ObjectStack<KType>
/*     */   extends ObjectArrayList<KType>
/*     */ {
/*     */   public ObjectStack() {}
/*     */   
/*     */   public ObjectStack(int initialCapacity)
/*     */   {
/*  50 */     super(initialCapacity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectStack(int initialCapacity, ArraySizingStrategy resizer)
/*     */   {
/*  58 */     super(initialCapacity, resizer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectStack(ObjectContainer<KType> container)
/*     */   {
/*  66 */     super(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void push(KType e1)
/*     */   {
/*  74 */     ensureBufferSpace(1);
/*  75 */     this.buffer[(this.elementsCount++)] = e1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void push(KType e1, KType e2)
/*     */   {
/*  83 */     ensureBufferSpace(2);
/*  84 */     this.buffer[(this.elementsCount++)] = e1;
/*  85 */     this.buffer[(this.elementsCount++)] = e2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void push(KType e1, KType e2, KType e3)
/*     */   {
/*  93 */     ensureBufferSpace(3);
/*  94 */     this.buffer[(this.elementsCount++)] = e1;
/*  95 */     this.buffer[(this.elementsCount++)] = e2;
/*  96 */     this.buffer[(this.elementsCount++)] = e3;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void push(KType e1, KType e2, KType e3, KType e4)
/*     */   {
/* 104 */     ensureBufferSpace(4);
/* 105 */     this.buffer[(this.elementsCount++)] = e1;
/* 106 */     this.buffer[(this.elementsCount++)] = e2;
/* 107 */     this.buffer[(this.elementsCount++)] = e3;
/* 108 */     this.buffer[(this.elementsCount++)] = e4;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void push(KType[] elements, int start, int len)
/*     */   {
/* 116 */     assert ((start >= 0) && (len >= 0));
/*     */     
/* 118 */     ensureBufferSpace(len);
/* 119 */     System.arraycopy(elements, start, this.buffer, this.elementsCount, len);
/* 120 */     this.elementsCount += len;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void push(KType... elements)
/*     */   {
/* 130 */     push(elements, 0, elements.length);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int pushAll(ObjectContainer<? extends KType> container)
/*     */   {
/* 138 */     return addAll(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int pushAll(Iterable<? extends ObjectCursor<? extends KType>> iterable)
/*     */   {
/* 146 */     return addAll(iterable);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void discard(int count)
/*     */   {
/* 154 */     assert (this.elementsCount >= count);
/*     */     
/* 156 */     this.elementsCount -= count;
/*     */     
/* 158 */     Arrays.fill(this.buffer, this.elementsCount, this.elementsCount + count, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void discard()
/*     */   {
/* 167 */     assert (this.elementsCount > 0);
/*     */     
/* 169 */     this.elementsCount -= 1;
/*     */     
/* 171 */     this.buffer[this.elementsCount] = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KType pop()
/*     */   {
/* 180 */     assert (this.elementsCount > 0);
/*     */     
/* 182 */     KType v = this.buffer[(--this.elementsCount)];
/*     */     
/* 184 */     this.buffer[this.elementsCount] = null;
/*     */     
/* 186 */     return v;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public KType peek()
/*     */   {
/* 194 */     assert (this.elementsCount > 0);
/*     */     
/* 196 */     return (KType)this.buffer[(this.elementsCount - 1)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> ObjectStack<KType> newInstance()
/*     */   {
/* 205 */     return new ObjectStack();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> ObjectStack<KType> newInstanceWithCapacity(int initialCapacity)
/*     */   {
/* 214 */     return new ObjectStack(initialCapacity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> ObjectStack<KType> from(KType... elements)
/*     */   {
/* 222 */     ObjectStack<KType> stack = new ObjectStack(elements.length);
/* 223 */     stack.push(elements);
/* 224 */     return stack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> ObjectStack<KType> from(ObjectContainer<KType> container)
/*     */   {
/* 232 */     return new ObjectStack(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectStack<KType> clone()
/*     */   {
/* 241 */     return (ObjectStack)super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ObjectStack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */