/*     */ package org.apache.commons.collections15.buffer;
/*     */ 
/*     */ import java.util.Collection;
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
/*     */ public class BlockingBuffer<E>
/*     */   extends SynchronizedBuffer<E>
/*     */ {
/*     */   private static final long serialVersionUID = 1719328905017860541L;
/*     */   
/*     */   public static <E> Buffer<E> decorate(Buffer<E> buffer)
/*     */   {
/*  61 */     return new BlockingBuffer(buffer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected BlockingBuffer(Buffer<E> buffer)
/*     */   {
/*  72 */     super(buffer);
/*     */   }
/*     */   
/*     */   public boolean add(E o)
/*     */   {
/*  77 */     synchronized (this.lock) {
/*  78 */       boolean result = this.collection.add(o);
/*  79 */       notifyAll();
/*  80 */       return result;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends E> c) {
/*  85 */     synchronized (this.lock) {
/*  86 */       boolean result = this.collection.addAll(c);
/*  87 */       notifyAll();
/*  88 */       return result;
/*     */     }
/*     */   }
/*     */   
/*     */   public E get() {
/*  93 */     synchronized (this.lock) {
/*  94 */       while (this.collection.isEmpty()) {
/*     */         try {
/*  96 */           wait();
/*     */         } catch (InterruptedException e) {
/*  98 */           throw new BufferUnderflowException();
/*     */         }
/*     */       }
/* 101 */       return (E)getBuffer().get();
/*     */     }
/*     */   }
/*     */   
/*     */   public E remove() {
/* 106 */     synchronized (this.lock) {
/* 107 */       while (this.collection.isEmpty()) {
/*     */         try {
/* 109 */           wait();
/*     */         } catch (InterruptedException e) {
/* 111 */           throw new BufferUnderflowException();
/*     */         }
/*     */       }
/* 114 */       return (E)getBuffer().remove();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/buffer/BlockingBuffer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */