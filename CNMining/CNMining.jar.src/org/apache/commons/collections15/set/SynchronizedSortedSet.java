/*     */ package org.apache.commons.collections15.set;
/*     */ 
/*     */ import java.util.SortedSet;
/*     */ import org.apache.commons.collections15.collection.SynchronizedCollection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SynchronizedSortedSet<E>
/*     */   extends SynchronizedCollection<E>
/*     */   implements SortedSet<E>
/*     */ {
/*     */   private static final long serialVersionUID = 2775582861954500111L;
/*     */   
/*     */   public static <E> SortedSet<E> decorate(SortedSet<E> set)
/*     */   {
/*  50 */     return new SynchronizedSortedSet(set);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SynchronizedSortedSet(SortedSet<E> set)
/*     */   {
/*  61 */     super(set);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SynchronizedSortedSet(SortedSet<E> set, Object lock)
/*     */   {
/*  72 */     super(set, lock);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SortedSet<E> getSortedSet()
/*     */   {
/*  81 */     return (SortedSet)this.collection;
/*     */   }
/*     */   
/*     */   public SortedSet<E> subSet(E fromElement, E toElement)
/*     */   {
/*  86 */     synchronized (this.lock) {
/*  87 */       SortedSet set = getSortedSet().subSet(fromElement, toElement);
/*     */       
/*     */ 
/*  90 */       return new SynchronizedSortedSet(set, this.lock);
/*     */     }
/*     */   }
/*     */   
/*     */   public SortedSet<E> headSet(E toElement) {
/*  95 */     synchronized (this.lock) {
/*  96 */       SortedSet set = getSortedSet().headSet(toElement);
/*     */       
/*     */ 
/*  99 */       return new SynchronizedSortedSet(set, this.lock);
/*     */     }
/*     */   }
/*     */   
/*     */   public SortedSet<E> tailSet(E fromElement) {
/* 104 */     synchronized (this.lock) {
/* 105 */       SortedSet<E> set = getSortedSet().tailSet(fromElement);
/*     */       
/*     */ 
/* 108 */       return new SynchronizedSortedSet(set, this.lock);
/*     */     }
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public E first()
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 7	org/apache/commons/collections15/set/SynchronizedSortedSet:lock	Ljava/lang/Object;
/*     */     //   4: dup
/*     */     //   5: astore_1
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: invokevirtual 8	org/apache/commons/collections15/set/SynchronizedSortedSet:getSortedSet	()Ljava/util/SortedSet;
/*     */     //   11: invokeinterface 13 1 0
/*     */     //   16: aload_1
/*     */     //   17: monitorexit
/*     */     //   18: areturn
/*     */     //   19: astore_2
/*     */     //   20: aload_1
/*     */     //   21: monitorexit
/*     */     //   22: aload_2
/*     */     //   23: athrow
/*     */     // Line number table:
/*     */     //   Java source line #113	-> byte code offset #0
/*     */     //   Java source line #114	-> byte code offset #7
/*     */     //   Java source line #115	-> byte code offset #19
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	24	0	this	SynchronizedSortedSet<E>
/*     */     //   5	16	1	Ljava/lang/Object;	Object
/*     */     //   19	4	2	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	18	19	finally
/*     */     //   19	22	19	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public E last()
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 7	org/apache/commons/collections15/set/SynchronizedSortedSet:lock	Ljava/lang/Object;
/*     */     //   4: dup
/*     */     //   5: astore_1
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: invokevirtual 8	org/apache/commons/collections15/set/SynchronizedSortedSet:getSortedSet	()Ljava/util/SortedSet;
/*     */     //   11: invokeinterface 14 1 0
/*     */     //   16: aload_1
/*     */     //   17: monitorexit
/*     */     //   18: areturn
/*     */     //   19: astore_2
/*     */     //   20: aload_1
/*     */     //   21: monitorexit
/*     */     //   22: aload_2
/*     */     //   23: athrow
/*     */     // Line number table:
/*     */     //   Java source line #119	-> byte code offset #0
/*     */     //   Java source line #120	-> byte code offset #7
/*     */     //   Java source line #121	-> byte code offset #19
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	24	0	this	SynchronizedSortedSet<E>
/*     */     //   5	16	1	Ljava/lang/Object;	Object
/*     */     //   19	4	2	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	18	19	finally
/*     */     //   19	22	19	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public java.util.Comparator<? super E> comparator()
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 7	org/apache/commons/collections15/set/SynchronizedSortedSet:lock	Ljava/lang/Object;
/*     */     //   4: dup
/*     */     //   5: astore_1
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: invokevirtual 8	org/apache/commons/collections15/set/SynchronizedSortedSet:getSortedSet	()Ljava/util/SortedSet;
/*     */     //   11: invokeinterface 15 1 0
/*     */     //   16: aload_1
/*     */     //   17: monitorexit
/*     */     //   18: areturn
/*     */     //   19: astore_2
/*     */     //   20: aload_1
/*     */     //   21: monitorexit
/*     */     //   22: aload_2
/*     */     //   23: athrow
/*     */     // Line number table:
/*     */     //   Java source line #125	-> byte code offset #0
/*     */     //   Java source line #126	-> byte code offset #7
/*     */     //   Java source line #127	-> byte code offset #19
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	24	0	this	SynchronizedSortedSet<E>
/*     */     //   5	16	1	Ljava/lang/Object;	Object
/*     */     //   19	4	2	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	18	19	finally
/*     */     //   19	22	19	finally
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/set/SynchronizedSortedSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */