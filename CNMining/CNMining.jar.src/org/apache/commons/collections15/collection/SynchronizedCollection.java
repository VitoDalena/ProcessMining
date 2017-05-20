/*     */ package org.apache.commons.collections15.collection;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SynchronizedCollection<E>
/*     */   implements Collection<E>, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2412805092710877986L;
/*     */   protected final Collection<E> collection;
/*     */   protected final Object lock;
/*     */   
/*     */   public static <E> Collection<E> decorate(Collection<E> coll)
/*     */   {
/*  65 */     return new SynchronizedCollection(coll);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SynchronizedCollection(Collection<E> collection)
/*     */   {
/*  76 */     if (collection == null) {
/*  77 */       throw new IllegalArgumentException("Collection must not be null");
/*     */     }
/*  79 */     this.collection = collection;
/*  80 */     this.lock = this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SynchronizedCollection(Collection<E> collection, Object lock)
/*     */   {
/*  91 */     if (collection == null) {
/*  92 */       throw new IllegalArgumentException("Collection must not be null");
/*     */     }
/*  94 */     this.collection = collection;
/*  95 */     this.lock = lock;
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public boolean add(E object)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 8	org/apache/commons/collections15/collection/SynchronizedCollection:lock	Ljava/lang/Object;
/*     */     //   4: dup
/*     */     //   5: astore_2
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: getfield 7	org/apache/commons/collections15/collection/SynchronizedCollection:collection	Ljava/util/Collection;
/*     */     //   11: aload_1
/*     */     //   12: invokeinterface 9 2 0
/*     */     //   17: aload_2
/*     */     //   18: monitorexit
/*     */     //   19: ireturn
/*     */     //   20: astore_3
/*     */     //   21: aload_2
/*     */     //   22: monitorexit
/*     */     //   23: aload_3
/*     */     //   24: athrow
/*     */     // Line number table:
/*     */     //   Java source line #100	-> byte code offset #0
/*     */     //   Java source line #101	-> byte code offset #7
/*     */     //   Java source line #102	-> byte code offset #20
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	25	0	this	SynchronizedCollection<E>
/*     */     //   0	25	1	object	E
/*     */     //   5	17	2	Ljava/lang/Object;	Object
/*     */     //   20	4	3	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	19	20	finally
/*     */     //   20	23	20	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public boolean addAll(Collection<? extends E> coll)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 8	org/apache/commons/collections15/collection/SynchronizedCollection:lock	Ljava/lang/Object;
/*     */     //   4: dup
/*     */     //   5: astore_2
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: getfield 7	org/apache/commons/collections15/collection/SynchronizedCollection:collection	Ljava/util/Collection;
/*     */     //   11: aload_1
/*     */     //   12: invokeinterface 10 2 0
/*     */     //   17: aload_2
/*     */     //   18: monitorexit
/*     */     //   19: ireturn
/*     */     //   20: astore_3
/*     */     //   21: aload_2
/*     */     //   22: monitorexit
/*     */     //   23: aload_3
/*     */     //   24: athrow
/*     */     // Line number table:
/*     */     //   Java source line #106	-> byte code offset #0
/*     */     //   Java source line #107	-> byte code offset #7
/*     */     //   Java source line #108	-> byte code offset #20
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	25	0	this	SynchronizedCollection<E>
/*     */     //   0	25	1	coll	Collection<? extends E>
/*     */     //   5	17	2	Ljava/lang/Object;	Object
/*     */     //   20	4	3	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	19	20	finally
/*     */     //   20	23	20	finally
/*     */   }
/*     */   
/*     */   public void clear()
/*     */   {
/* 112 */     synchronized (this.lock) {
/* 113 */       this.collection.clear();
/*     */     }
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public boolean contains(Object object)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 8	org/apache/commons/collections15/collection/SynchronizedCollection:lock	Ljava/lang/Object;
/*     */     //   4: dup
/*     */     //   5: astore_2
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: getfield 7	org/apache/commons/collections15/collection/SynchronizedCollection:collection	Ljava/util/Collection;
/*     */     //   11: aload_1
/*     */     //   12: invokeinterface 12 2 0
/*     */     //   17: aload_2
/*     */     //   18: monitorexit
/*     */     //   19: ireturn
/*     */     //   20: astore_3
/*     */     //   21: aload_2
/*     */     //   22: monitorexit
/*     */     //   23: aload_3
/*     */     //   24: athrow
/*     */     // Line number table:
/*     */     //   Java source line #118	-> byte code offset #0
/*     */     //   Java source line #119	-> byte code offset #7
/*     */     //   Java source line #120	-> byte code offset #20
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	25	0	this	SynchronizedCollection<E>
/*     */     //   0	25	1	object	Object
/*     */     //   5	17	2	Ljava/lang/Object;	Object
/*     */     //   20	4	3	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	19	20	finally
/*     */     //   20	23	20	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public boolean containsAll(Collection<?> coll)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 8	org/apache/commons/collections15/collection/SynchronizedCollection:lock	Ljava/lang/Object;
/*     */     //   4: dup
/*     */     //   5: astore_2
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: getfield 7	org/apache/commons/collections15/collection/SynchronizedCollection:collection	Ljava/util/Collection;
/*     */     //   11: aload_1
/*     */     //   12: invokeinterface 13 2 0
/*     */     //   17: aload_2
/*     */     //   18: monitorexit
/*     */     //   19: ireturn
/*     */     //   20: astore_3
/*     */     //   21: aload_2
/*     */     //   22: monitorexit
/*     */     //   23: aload_3
/*     */     //   24: athrow
/*     */     // Line number table:
/*     */     //   Java source line #124	-> byte code offset #0
/*     */     //   Java source line #125	-> byte code offset #7
/*     */     //   Java source line #126	-> byte code offset #20
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	25	0	this	SynchronizedCollection<E>
/*     */     //   0	25	1	coll	Collection<?>
/*     */     //   5	17	2	Ljava/lang/Object;	Object
/*     */     //   20	4	3	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	19	20	finally
/*     */     //   20	23	20	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public boolean isEmpty()
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 8	org/apache/commons/collections15/collection/SynchronizedCollection:lock	Ljava/lang/Object;
/*     */     //   4: dup
/*     */     //   5: astore_1
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: getfield 7	org/apache/commons/collections15/collection/SynchronizedCollection:collection	Ljava/util/Collection;
/*     */     //   11: invokeinterface 14 1 0
/*     */     //   16: aload_1
/*     */     //   17: monitorexit
/*     */     //   18: ireturn
/*     */     //   19: astore_2
/*     */     //   20: aload_1
/*     */     //   21: monitorexit
/*     */     //   22: aload_2
/*     */     //   23: athrow
/*     */     // Line number table:
/*     */     //   Java source line #130	-> byte code offset #0
/*     */     //   Java source line #131	-> byte code offset #7
/*     */     //   Java source line #132	-> byte code offset #19
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	24	0	this	SynchronizedCollection<E>
/*     */     //   5	16	1	Ljava/lang/Object;	Object
/*     */     //   19	4	2	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	18	19	finally
/*     */     //   19	22	19	finally
/*     */   }
/*     */   
/*     */   public Iterator<E> iterator()
/*     */   {
/* 146 */     return this.collection.iterator();
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public Object[] toArray()
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 8	org/apache/commons/collections15/collection/SynchronizedCollection:lock	Ljava/lang/Object;
/*     */     //   4: dup
/*     */     //   5: astore_1
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: getfield 7	org/apache/commons/collections15/collection/SynchronizedCollection:collection	Ljava/util/Collection;
/*     */     //   11: invokeinterface 16 1 0
/*     */     //   16: aload_1
/*     */     //   17: monitorexit
/*     */     //   18: areturn
/*     */     //   19: astore_2
/*     */     //   20: aload_1
/*     */     //   21: monitorexit
/*     */     //   22: aload_2
/*     */     //   23: athrow
/*     */     // Line number table:
/*     */     //   Java source line #150	-> byte code offset #0
/*     */     //   Java source line #151	-> byte code offset #7
/*     */     //   Java source line #152	-> byte code offset #19
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	24	0	this	SynchronizedCollection<E>
/*     */     //   5	16	1	Ljava/lang/Object;	Object
/*     */     //   19	4	2	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	18	19	finally
/*     */     //   19	22	19	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public <T> T[] toArray(T[] object)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 8	org/apache/commons/collections15/collection/SynchronizedCollection:lock	Ljava/lang/Object;
/*     */     //   4: dup
/*     */     //   5: astore_2
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: getfield 7	org/apache/commons/collections15/collection/SynchronizedCollection:collection	Ljava/util/Collection;
/*     */     //   11: aload_1
/*     */     //   12: invokeinterface 17 2 0
/*     */     //   17: aload_2
/*     */     //   18: monitorexit
/*     */     //   19: areturn
/*     */     //   20: astore_3
/*     */     //   21: aload_2
/*     */     //   22: monitorexit
/*     */     //   23: aload_3
/*     */     //   24: athrow
/*     */     // Line number table:
/*     */     //   Java source line #156	-> byte code offset #0
/*     */     //   Java source line #157	-> byte code offset #7
/*     */     //   Java source line #158	-> byte code offset #20
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	25	0	this	SynchronizedCollection<E>
/*     */     //   0	25	1	object	T[]
/*     */     //   5	17	2	Ljava/lang/Object;	Object
/*     */     //   20	4	3	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	19	20	finally
/*     */     //   20	23	20	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public boolean remove(Object object)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 8	org/apache/commons/collections15/collection/SynchronizedCollection:lock	Ljava/lang/Object;
/*     */     //   4: dup
/*     */     //   5: astore_2
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: getfield 7	org/apache/commons/collections15/collection/SynchronizedCollection:collection	Ljava/util/Collection;
/*     */     //   11: aload_1
/*     */     //   12: invokeinterface 18 2 0
/*     */     //   17: aload_2
/*     */     //   18: monitorexit
/*     */     //   19: ireturn
/*     */     //   20: astore_3
/*     */     //   21: aload_2
/*     */     //   22: monitorexit
/*     */     //   23: aload_3
/*     */     //   24: athrow
/*     */     // Line number table:
/*     */     //   Java source line #162	-> byte code offset #0
/*     */     //   Java source line #163	-> byte code offset #7
/*     */     //   Java source line #164	-> byte code offset #20
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	25	0	this	SynchronizedCollection<E>
/*     */     //   0	25	1	object	Object
/*     */     //   5	17	2	Ljava/lang/Object;	Object
/*     */     //   20	4	3	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	19	20	finally
/*     */     //   20	23	20	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public boolean removeAll(Collection<?> coll)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 8	org/apache/commons/collections15/collection/SynchronizedCollection:lock	Ljava/lang/Object;
/*     */     //   4: dup
/*     */     //   5: astore_2
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: getfield 7	org/apache/commons/collections15/collection/SynchronizedCollection:collection	Ljava/util/Collection;
/*     */     //   11: aload_1
/*     */     //   12: invokeinterface 19 2 0
/*     */     //   17: aload_2
/*     */     //   18: monitorexit
/*     */     //   19: ireturn
/*     */     //   20: astore_3
/*     */     //   21: aload_2
/*     */     //   22: monitorexit
/*     */     //   23: aload_3
/*     */     //   24: athrow
/*     */     // Line number table:
/*     */     //   Java source line #168	-> byte code offset #0
/*     */     //   Java source line #169	-> byte code offset #7
/*     */     //   Java source line #170	-> byte code offset #20
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	25	0	this	SynchronizedCollection<E>
/*     */     //   0	25	1	coll	Collection<?>
/*     */     //   5	17	2	Ljava/lang/Object;	Object
/*     */     //   20	4	3	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	19	20	finally
/*     */     //   20	23	20	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public boolean retainAll(Collection<?> coll)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 8	org/apache/commons/collections15/collection/SynchronizedCollection:lock	Ljava/lang/Object;
/*     */     //   4: dup
/*     */     //   5: astore_2
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: getfield 7	org/apache/commons/collections15/collection/SynchronizedCollection:collection	Ljava/util/Collection;
/*     */     //   11: aload_1
/*     */     //   12: invokeinterface 20 2 0
/*     */     //   17: aload_2
/*     */     //   18: monitorexit
/*     */     //   19: ireturn
/*     */     //   20: astore_3
/*     */     //   21: aload_2
/*     */     //   22: monitorexit
/*     */     //   23: aload_3
/*     */     //   24: athrow
/*     */     // Line number table:
/*     */     //   Java source line #174	-> byte code offset #0
/*     */     //   Java source line #175	-> byte code offset #7
/*     */     //   Java source line #176	-> byte code offset #20
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	25	0	this	SynchronizedCollection<E>
/*     */     //   0	25	1	coll	Collection<?>
/*     */     //   5	17	2	Ljava/lang/Object;	Object
/*     */     //   20	4	3	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	19	20	finally
/*     */     //   20	23	20	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public int size()
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 8	org/apache/commons/collections15/collection/SynchronizedCollection:lock	Ljava/lang/Object;
/*     */     //   4: dup
/*     */     //   5: astore_1
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: getfield 7	org/apache/commons/collections15/collection/SynchronizedCollection:collection	Ljava/util/Collection;
/*     */     //   11: invokeinterface 21 1 0
/*     */     //   16: aload_1
/*     */     //   17: monitorexit
/*     */     //   18: ireturn
/*     */     //   19: astore_2
/*     */     //   20: aload_1
/*     */     //   21: monitorexit
/*     */     //   22: aload_2
/*     */     //   23: athrow
/*     */     // Line number table:
/*     */     //   Java source line #180	-> byte code offset #0
/*     */     //   Java source line #181	-> byte code offset #7
/*     */     //   Java source line #182	-> byte code offset #19
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	24	0	this	SynchronizedCollection<E>
/*     */     //   5	16	1	Ljava/lang/Object;	Object
/*     */     //   19	4	2	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	18	19	finally
/*     */     //   19	22	19	finally
/*     */   }
/*     */   
/*     */   public boolean equals(Object object)
/*     */   {
/* 186 */     synchronized (this.lock) {
/* 187 */       if (object == this) {
/* 188 */         return true;
/*     */       }
/* 190 */       return this.collection.equals(object);
/*     */     }
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public int hashCode()
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 8	org/apache/commons/collections15/collection/SynchronizedCollection:lock	Ljava/lang/Object;
/*     */     //   4: dup
/*     */     //   5: astore_1
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: getfield 7	org/apache/commons/collections15/collection/SynchronizedCollection:collection	Ljava/util/Collection;
/*     */     //   11: invokevirtual 23	java/lang/Object:hashCode	()I
/*     */     //   14: aload_1
/*     */     //   15: monitorexit
/*     */     //   16: ireturn
/*     */     //   17: astore_2
/*     */     //   18: aload_1
/*     */     //   19: monitorexit
/*     */     //   20: aload_2
/*     */     //   21: athrow
/*     */     // Line number table:
/*     */     //   Java source line #195	-> byte code offset #0
/*     */     //   Java source line #196	-> byte code offset #7
/*     */     //   Java source line #197	-> byte code offset #17
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	22	0	this	SynchronizedCollection<E>
/*     */     //   5	14	1	Ljava/lang/Object;	Object
/*     */     //   17	4	2	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	16	17	finally
/*     */     //   17	20	17	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public String toString()
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 8	org/apache/commons/collections15/collection/SynchronizedCollection:lock	Ljava/lang/Object;
/*     */     //   4: dup
/*     */     //   5: astore_1
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: getfield 7	org/apache/commons/collections15/collection/SynchronizedCollection:collection	Ljava/util/Collection;
/*     */     //   11: invokevirtual 24	java/lang/Object:toString	()Ljava/lang/String;
/*     */     //   14: aload_1
/*     */     //   15: monitorexit
/*     */     //   16: areturn
/*     */     //   17: astore_2
/*     */     //   18: aload_1
/*     */     //   19: monitorexit
/*     */     //   20: aload_2
/*     */     //   21: athrow
/*     */     // Line number table:
/*     */     //   Java source line #201	-> byte code offset #0
/*     */     //   Java source line #202	-> byte code offset #7
/*     */     //   Java source line #203	-> byte code offset #17
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	22	0	this	SynchronizedCollection<E>
/*     */     //   5	14	1	Ljava/lang/Object;	Object
/*     */     //   17	4	2	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	16	17	finally
/*     */     //   17	20	17	finally
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/collection/SynchronizedCollection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */