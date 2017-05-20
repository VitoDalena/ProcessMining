/*     */ package com.carrotsearch.hppc;
/*     */ 
/*     */ import com.carrotsearch.hppc.cursors.KTypeCursor;
/*     */ import com.carrotsearch.hppc.predicates.KTypePredicate;
/*     */ import java.lang.reflect.Array;
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
/*     */ abstract class AbstractKTypeCollection<KType>
/*     */   implements KTypeCollection<KType>
/*     */ {
/*     */   public int removeAll(KTypeLookupContainer<? extends KType> c)
/*     */   {
/*  24 */     final KTypeContainer<KType> c2 = c;
/*  25 */     removeAll(new KTypePredicate()
/*     */     {
/*     */       public boolean apply(KType k)
/*     */       {
/*  29 */         return c2.contains(k);
/*     */       }
/*     */     });
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
/*     */   public int retainAll(KTypeLookupContainer<? extends KType> c)
/*     */   {
/*  44 */     final KTypeContainer<KType> c2 = c;
/*  45 */     removeAll(new KTypePredicate()
/*     */     {
/*     */       public boolean apply(KType k)
/*     */       {
/*  49 */         return !c2.contains(k);
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int retainAll(final KTypePredicate<? super KType> predicate)
/*     */   {
/*  61 */     removeAll(new KTypePredicate()
/*     */     {
/*     */       public boolean apply(KType value)
/*     */       {
/*  65 */         return !predicate.apply(value);
/*     */       }
/*     */     });
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
/*     */   public KType[] toArray(Class<? super KType> clazz)
/*     */   {
/*  81 */     int size = size();
/*  82 */     KType[] array = (Object[])Array.newInstance(clazz, size);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  89 */     int i = 0;
/*  90 */     for (KTypeCursor<KType> c : this)
/*     */     {
/*  92 */       array[(i++)] = c.value;
/*     */     }
/*  94 */     return array;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object[] toArray()
/*     */   {
/* 101 */     Object[] array = new Object[size()];
/* 102 */     int i = 0;
/* 103 */     for (KTypeCursor<KType> c : this)
/*     */     {
/* 105 */       array[(i++)] = c.value;
/*     */     }
/* 107 */     return array;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 117 */     return Arrays.toString(toArray());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/AbstractKTypeCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */