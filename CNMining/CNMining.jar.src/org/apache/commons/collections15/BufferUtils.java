/*     */ package org.apache.commons.collections15;
/*     */ 
/*     */ import org.apache.commons.collections15.buffer.BlockingBuffer;
/*     */ import org.apache.commons.collections15.buffer.PredicatedBuffer;
/*     */ import org.apache.commons.collections15.buffer.SynchronizedBuffer;
/*     */ import org.apache.commons.collections15.buffer.TransformedBuffer;
/*     */ import org.apache.commons.collections15.buffer.TypedBuffer;
/*     */ import org.apache.commons.collections15.buffer.UnmodifiableBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BufferUtils
/*     */ {
/*  34 */   public static final Buffer EMPTY_BUFFER = UnmodifiableBuffer.decorate(new ArrayStack(1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <E> Buffer<E> synchronizedBuffer(Buffer<E> buffer)
/*     */   {
/*  64 */     return SynchronizedBuffer.decorate(buffer);
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
/*     */   public static <E> Buffer<E> blockingBuffer(Buffer<E> buffer)
/*     */   {
/*  80 */     return BlockingBuffer.decorate(buffer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <E> Buffer<E> unmodifiableBuffer(Buffer<E> buffer)
/*     */   {
/*  91 */     return UnmodifiableBuffer.decorate(buffer);
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
/*     */   public static <E> Buffer<E> predicatedBuffer(Buffer<E> buffer, Predicate<E> predicate)
/*     */   {
/* 108 */     return PredicatedBuffer.decorate(buffer, predicate);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static <E> Buffer<E> typedBuffer(Buffer<E> buffer, Class<E> type)
/*     */   {
/* 123 */     return TypedBuffer.decorate(buffer, type);
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
/*     */   public static <I, O> Buffer<O> transformedBuffer(Buffer<I> buffer, Transformer<I, O> transformer)
/*     */   {
/* 139 */     return TransformedBuffer.decorate(buffer, transformer);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/BufferUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */