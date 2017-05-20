/*     */ package org.apache.commons.collections15.list;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.apache.commons.collections15.Factory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LazyList<E>
/*     */   extends AbstractSerializableListDecorator<E>
/*     */ {
/*     */   private static final long serialVersionUID = -1708388017160694542L;
/*     */   protected final Factory<? extends E> factory;
/*     */   
/*     */   public static <E> List<E> decorate(List<E> list, Factory<? extends E> factory)
/*     */   {
/*  78 */     return new LazyList(list, factory);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected LazyList(List<E> list, Factory<? extends E> factory)
/*     */   {
/*  90 */     super(list);
/*  91 */     if (factory == null) {
/*  92 */       throw new IllegalArgumentException("Factory must not be null");
/*     */     }
/*  94 */     this.factory = factory;
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
/*     */   public E get(int index)
/*     */   {
/* 109 */     int size = getList().size();
/* 110 */     if (index < size)
/*     */     {
/* 112 */       E object = getList().get(index);
/* 113 */       if (object == null)
/*     */       {
/* 115 */         object = this.factory.create();
/* 116 */         getList().set(index, object);
/* 117 */         return object;
/*     */       }
/*     */       
/* 120 */       return object;
/*     */     }
/*     */     
/*     */ 
/* 124 */     for (int i = size; i < index; i++) {
/* 125 */       getList().add(null);
/*     */     }
/*     */     
/* 128 */     E object = this.factory.create();
/* 129 */     getList().add(object);
/* 130 */     return object;
/*     */   }
/*     */   
/*     */ 
/*     */   public List<E> subList(int fromIndex, int toIndex)
/*     */   {
/* 136 */     List<E> sub = getList().subList(fromIndex, toIndex);
/* 137 */     return new LazyList(sub, this.factory);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/list/LazyList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */