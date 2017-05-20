/*     */ package org.apache.commons.collections15;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.EmptyStackException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArrayStack<E>
/*     */   extends ArrayList<E>
/*     */   implements Buffer<E>
/*     */ {
/*     */   private static final long serialVersionUID = 2130079159931574599L;
/*     */   
/*     */   public ArrayStack() {}
/*     */   
/*     */   public ArrayStack(int initialSize)
/*     */   {
/*  67 */     super(initialSize);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean empty()
/*     */   {
/*  79 */     return isEmpty();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E peek()
/*     */     throws EmptyStackException
/*     */   {
/*  89 */     int n = size();
/*  90 */     if (n <= 0) {
/*  91 */       throw new EmptyStackException();
/*     */     }
/*  93 */     return (E)get(n - 1);
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
/*     */   public E peek(int n)
/*     */     throws EmptyStackException
/*     */   {
/* 107 */     int m = size() - n - 1;
/* 108 */     if (m < 0) {
/* 109 */       throw new EmptyStackException();
/*     */     }
/* 111 */     return (E)get(m);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E pop()
/*     */     throws EmptyStackException
/*     */   {
/* 122 */     int n = size();
/* 123 */     if (n <= 0) {
/* 124 */       throw new EmptyStackException();
/*     */     }
/* 126 */     return (E)remove(n - 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E push(E item)
/*     */   {
/* 138 */     add(item);
/* 139 */     return item;
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
/*     */   public int search(E object)
/*     */   {
/* 154 */     int i = size() - 1;
/* 155 */     int n = 1;
/* 156 */     while (i >= 0) {
/* 157 */       E current = get(i);
/* 158 */       if (((object == null) && (current == null)) || ((object != null) && (object.equals(current)))) {
/* 159 */         return n;
/*     */       }
/* 161 */       i--;
/* 162 */       n++;
/*     */     }
/* 164 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E get()
/*     */   {
/* 174 */     int size = size();
/* 175 */     if (size == 0) {
/* 176 */       throw new BufferUnderflowException();
/*     */     }
/* 178 */     return (E)get(size - 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E remove()
/*     */   {
/* 188 */     int size = size();
/* 189 */     if (size == 0) {
/* 190 */       throw new BufferUnderflowException();
/*     */     }
/* 192 */     return (E)remove(size - 1);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/ArrayStack.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */