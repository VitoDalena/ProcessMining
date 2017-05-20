/*     */ package com.carrotsearch.hppc;
/*     */ 
/*     */ import com.carrotsearch.hppc.cursors.ObjectCursor;
/*     */ import com.carrotsearch.hppc.predicates.ObjectPredicate;
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
/*     */ abstract class AbstractObjectCollection<KType>
/*     */   implements ObjectCollection<KType>
/*     */ {
/*     */   public int removeAll(ObjectLookupContainer<? extends KType> c)
/*     */   {
/*  24 */     final ObjectContainer<KType> c2 = c;
/*  25 */     removeAll(new ObjectPredicate()
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
/*     */   public int retainAll(ObjectLookupContainer<? extends KType> c)
/*     */   {
/*  44 */     final ObjectContainer<KType> c2 = c;
/*  45 */     removeAll(new ObjectPredicate()
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
/*     */   public int retainAll(final ObjectPredicate<? super KType> predicate)
/*     */   {
/*  61 */     removeAll(new ObjectPredicate()
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
/*     */   public KType[] toArray(Class<? super KType> clazz)
/*     */   {
/*  79 */     int size = size();
/*  80 */     KType[] array = (Object[])Array.newInstance(clazz, size);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  85 */     int i = 0;
/*  86 */     for (ObjectCursor<KType> c : this)
/*     */     {
/*  88 */       array[(i++)] = c.value;
/*     */     }
/*  90 */     return array;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object[] toArray()
/*     */   {
/*  97 */     Object[] array = new Object[size()];
/*  98 */     int i = 0;
/*  99 */     for (ObjectCursor<KType> c : this)
/*     */     {
/* 101 */       array[(i++)] = c.value;
/*     */     }
/* 103 */     return array;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 113 */     return Arrays.toString(toArray());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/AbstractObjectCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */