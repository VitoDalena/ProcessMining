/*     */ package org.apache.commons.collections15.buffer;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections15.Buffer;
/*     */ import org.apache.commons.collections15.Unmodifiable;
/*     */ import org.apache.commons.collections15.iterators.UnmodifiableIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class UnmodifiableBuffer<E>
/*     */   extends AbstractBufferDecorator<E>
/*     */   implements Unmodifiable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1832948656215393357L;
/*     */   
/*     */   public static <E> Buffer<E> decorate(Buffer<E> buffer)
/*     */   {
/*  56 */     if ((buffer instanceof Unmodifiable)) {
/*  57 */       return buffer;
/*     */     }
/*  59 */     return new UnmodifiableBuffer(buffer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private UnmodifiableBuffer(Buffer<E> buffer)
/*     */   {
/*  70 */     super(buffer);
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
/*  81 */     out.defaultWriteObject();
/*  82 */     out.writeObject(this.collection);
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
/*  93 */     in.defaultReadObject();
/*  94 */     this.collection = ((Collection)in.readObject());
/*     */   }
/*     */   
/*     */   public Iterator<E> iterator()
/*     */   {
/*  99 */     return UnmodifiableIterator.decorate(getCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean add(E object) {
/* 103 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends E> coll) {
/* 107 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 111 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 115 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> coll) {
/* 119 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> coll) {
/* 123 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public E remove()
/*     */   {
/* 128 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/buffer/UnmodifiableBuffer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */