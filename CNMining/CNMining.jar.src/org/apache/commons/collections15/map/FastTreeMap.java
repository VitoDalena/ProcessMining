/*     */ package org.apache.commons.collections15.map;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.SortedMap;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FastTreeMap<K, V>
/*     */   extends TreeMap<K, V>
/*     */ {
/*  65 */   protected TreeMap<K, V> map = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  70 */   protected boolean fast = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FastTreeMap()
/*     */   {
/*  81 */     this.map = new TreeMap();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FastTreeMap(Comparator<K> comparator)
/*     */   {
/*  91 */     this.map = new TreeMap(comparator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FastTreeMap(Map<K, V> map)
/*     */   {
/* 102 */     this.map = new TreeMap(map);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FastTreeMap(SortedMap<K, V> map)
/*     */   {
/* 113 */     this.map = new TreeMap(map);
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
/*     */   public boolean getFast()
/*     */   {
/* 126 */     return this.fast;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFast(boolean fast)
/*     */   {
/* 135 */     this.fast = fast;
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public V get(Object key)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 3	org/apache/commons/collections15/map/FastTreeMap:fast	Z
/*     */     //   4: ifeq +12 -> 16
/*     */     //   7: aload_0
/*     */     //   8: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   11: aload_1
/*     */     //   12: invokevirtual 8	java/util/TreeMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   15: areturn
/*     */     //   16: aload_0
/*     */     //   17: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   20: dup
/*     */     //   21: astore_2
/*     */     //   22: monitorenter
/*     */     //   23: aload_0
/*     */     //   24: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   27: aload_1
/*     */     //   28: invokevirtual 8	java/util/TreeMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   31: aload_2
/*     */     //   32: monitorexit
/*     */     //   33: areturn
/*     */     //   34: astore_3
/*     */     //   35: aload_2
/*     */     //   36: monitorexit
/*     */     //   37: aload_3
/*     */     //   38: athrow
/*     */     // Line number table:
/*     */     //   Java source line #154	-> byte code offset #0
/*     */     //   Java source line #155	-> byte code offset #7
/*     */     //   Java source line #157	-> byte code offset #16
/*     */     //   Java source line #158	-> byte code offset #23
/*     */     //   Java source line #159	-> byte code offset #34
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	39	0	this	FastTreeMap<K, V>
/*     */     //   0	39	1	key	Object
/*     */     //   21	15	2	Ljava/lang/Object;	Object
/*     */     //   34	4	3	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   23	33	34	finally
/*     */     //   34	37	34	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public int size()
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 3	org/apache/commons/collections15/map/FastTreeMap:fast	Z
/*     */     //   4: ifeq +11 -> 15
/*     */     //   7: aload_0
/*     */     //   8: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   11: invokevirtual 9	java/util/TreeMap:size	()I
/*     */     //   14: ireturn
/*     */     //   15: aload_0
/*     */     //   16: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   19: dup
/*     */     //   20: astore_1
/*     */     //   21: monitorenter
/*     */     //   22: aload_0
/*     */     //   23: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   26: invokevirtual 9	java/util/TreeMap:size	()I
/*     */     //   29: aload_1
/*     */     //   30: monitorexit
/*     */     //   31: ireturn
/*     */     //   32: astore_2
/*     */     //   33: aload_1
/*     */     //   34: monitorexit
/*     */     //   35: aload_2
/*     */     //   36: athrow
/*     */     // Line number table:
/*     */     //   Java source line #169	-> byte code offset #0
/*     */     //   Java source line #170	-> byte code offset #7
/*     */     //   Java source line #172	-> byte code offset #15
/*     */     //   Java source line #173	-> byte code offset #22
/*     */     //   Java source line #174	-> byte code offset #32
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	37	0	this	FastTreeMap<K, V>
/*     */     //   20	14	1	Ljava/lang/Object;	Object
/*     */     //   32	4	2	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   22	31	32	finally
/*     */     //   32	35	32	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public boolean isEmpty()
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 3	org/apache/commons/collections15/map/FastTreeMap:fast	Z
/*     */     //   4: ifeq +11 -> 15
/*     */     //   7: aload_0
/*     */     //   8: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   11: invokevirtual 10	java/util/TreeMap:isEmpty	()Z
/*     */     //   14: ireturn
/*     */     //   15: aload_0
/*     */     //   16: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   19: dup
/*     */     //   20: astore_1
/*     */     //   21: monitorenter
/*     */     //   22: aload_0
/*     */     //   23: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   26: invokevirtual 10	java/util/TreeMap:isEmpty	()Z
/*     */     //   29: aload_1
/*     */     //   30: monitorexit
/*     */     //   31: ireturn
/*     */     //   32: astore_2
/*     */     //   33: aload_1
/*     */     //   34: monitorexit
/*     */     //   35: aload_2
/*     */     //   36: athrow
/*     */     // Line number table:
/*     */     //   Java source line #184	-> byte code offset #0
/*     */     //   Java source line #185	-> byte code offset #7
/*     */     //   Java source line #187	-> byte code offset #15
/*     */     //   Java source line #188	-> byte code offset #22
/*     */     //   Java source line #189	-> byte code offset #32
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	37	0	this	FastTreeMap<K, V>
/*     */     //   20	14	1	Ljava/lang/Object;	Object
/*     */     //   32	4	2	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   22	31	32	finally
/*     */     //   32	35	32	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public boolean containsKey(Object key)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 3	org/apache/commons/collections15/map/FastTreeMap:fast	Z
/*     */     //   4: ifeq +12 -> 16
/*     */     //   7: aload_0
/*     */     //   8: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   11: aload_1
/*     */     //   12: invokevirtual 11	java/util/TreeMap:containsKey	(Ljava/lang/Object;)Z
/*     */     //   15: ireturn
/*     */     //   16: aload_0
/*     */     //   17: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   20: dup
/*     */     //   21: astore_2
/*     */     //   22: monitorenter
/*     */     //   23: aload_0
/*     */     //   24: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   27: aload_1
/*     */     //   28: invokevirtual 11	java/util/TreeMap:containsKey	(Ljava/lang/Object;)Z
/*     */     //   31: aload_2
/*     */     //   32: monitorexit
/*     */     //   33: ireturn
/*     */     //   34: astore_3
/*     */     //   35: aload_2
/*     */     //   36: monitorexit
/*     */     //   37: aload_3
/*     */     //   38: athrow
/*     */     // Line number table:
/*     */     //   Java source line #201	-> byte code offset #0
/*     */     //   Java source line #202	-> byte code offset #7
/*     */     //   Java source line #204	-> byte code offset #16
/*     */     //   Java source line #205	-> byte code offset #23
/*     */     //   Java source line #206	-> byte code offset #34
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	39	0	this	FastTreeMap<K, V>
/*     */     //   0	39	1	key	Object
/*     */     //   21	15	2	Ljava/lang/Object;	Object
/*     */     //   34	4	3	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   23	33	34	finally
/*     */     //   34	37	34	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public boolean containsValue(Object value)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 3	org/apache/commons/collections15/map/FastTreeMap:fast	Z
/*     */     //   4: ifeq +12 -> 16
/*     */     //   7: aload_0
/*     */     //   8: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   11: aload_1
/*     */     //   12: invokevirtual 12	java/util/TreeMap:containsValue	(Ljava/lang/Object;)Z
/*     */     //   15: ireturn
/*     */     //   16: aload_0
/*     */     //   17: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   20: dup
/*     */     //   21: astore_2
/*     */     //   22: monitorenter
/*     */     //   23: aload_0
/*     */     //   24: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   27: aload_1
/*     */     //   28: invokevirtual 12	java/util/TreeMap:containsValue	(Ljava/lang/Object;)Z
/*     */     //   31: aload_2
/*     */     //   32: monitorexit
/*     */     //   33: ireturn
/*     */     //   34: astore_3
/*     */     //   35: aload_2
/*     */     //   36: monitorexit
/*     */     //   37: aload_3
/*     */     //   38: athrow
/*     */     // Line number table:
/*     */     //   Java source line #218	-> byte code offset #0
/*     */     //   Java source line #219	-> byte code offset #7
/*     */     //   Java source line #221	-> byte code offset #16
/*     */     //   Java source line #222	-> byte code offset #23
/*     */     //   Java source line #223	-> byte code offset #34
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	39	0	this	FastTreeMap<K, V>
/*     */     //   0	39	1	value	Object
/*     */     //   21	15	2	Ljava/lang/Object;	Object
/*     */     //   34	4	3	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   23	33	34	finally
/*     */     //   34	37	34	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public Comparator<? super K> comparator()
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 3	org/apache/commons/collections15/map/FastTreeMap:fast	Z
/*     */     //   4: ifeq +11 -> 15
/*     */     //   7: aload_0
/*     */     //   8: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   11: invokevirtual 13	java/util/TreeMap:comparator	()Ljava/util/Comparator;
/*     */     //   14: areturn
/*     */     //   15: aload_0
/*     */     //   16: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   19: dup
/*     */     //   20: astore_1
/*     */     //   21: monitorenter
/*     */     //   22: aload_0
/*     */     //   23: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   26: invokevirtual 13	java/util/TreeMap:comparator	()Ljava/util/Comparator;
/*     */     //   29: aload_1
/*     */     //   30: monitorexit
/*     */     //   31: areturn
/*     */     //   32: astore_2
/*     */     //   33: aload_1
/*     */     //   34: monitorexit
/*     */     //   35: aload_2
/*     */     //   36: athrow
/*     */     // Line number table:
/*     */     //   Java source line #234	-> byte code offset #0
/*     */     //   Java source line #235	-> byte code offset #7
/*     */     //   Java source line #237	-> byte code offset #15
/*     */     //   Java source line #238	-> byte code offset #22
/*     */     //   Java source line #239	-> byte code offset #32
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	37	0	this	FastTreeMap<K, V>
/*     */     //   20	14	1	Ljava/lang/Object;	Object
/*     */     //   32	4	2	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   22	31	32	finally
/*     */     //   32	35	32	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public K firstKey()
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 3	org/apache/commons/collections15/map/FastTreeMap:fast	Z
/*     */     //   4: ifeq +11 -> 15
/*     */     //   7: aload_0
/*     */     //   8: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   11: invokevirtual 14	java/util/TreeMap:firstKey	()Ljava/lang/Object;
/*     */     //   14: areturn
/*     */     //   15: aload_0
/*     */     //   16: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   19: dup
/*     */     //   20: astore_1
/*     */     //   21: monitorenter
/*     */     //   22: aload_0
/*     */     //   23: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   26: invokevirtual 14	java/util/TreeMap:firstKey	()Ljava/lang/Object;
/*     */     //   29: aload_1
/*     */     //   30: monitorexit
/*     */     //   31: areturn
/*     */     //   32: astore_2
/*     */     //   33: aload_1
/*     */     //   34: monitorexit
/*     */     //   35: aload_2
/*     */     //   36: athrow
/*     */     // Line number table:
/*     */     //   Java source line #249	-> byte code offset #0
/*     */     //   Java source line #250	-> byte code offset #7
/*     */     //   Java source line #252	-> byte code offset #15
/*     */     //   Java source line #253	-> byte code offset #22
/*     */     //   Java source line #254	-> byte code offset #32
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	37	0	this	FastTreeMap<K, V>
/*     */     //   20	14	1	Ljava/lang/Object;	Object
/*     */     //   32	4	2	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   22	31	32	finally
/*     */     //   32	35	32	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public K lastKey()
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 3	org/apache/commons/collections15/map/FastTreeMap:fast	Z
/*     */     //   4: ifeq +11 -> 15
/*     */     //   7: aload_0
/*     */     //   8: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   11: invokevirtual 15	java/util/TreeMap:lastKey	()Ljava/lang/Object;
/*     */     //   14: areturn
/*     */     //   15: aload_0
/*     */     //   16: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   19: dup
/*     */     //   20: astore_1
/*     */     //   21: monitorenter
/*     */     //   22: aload_0
/*     */     //   23: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   26: invokevirtual 15	java/util/TreeMap:lastKey	()Ljava/lang/Object;
/*     */     //   29: aload_1
/*     */     //   30: monitorexit
/*     */     //   31: areturn
/*     */     //   32: astore_2
/*     */     //   33: aload_1
/*     */     //   34: monitorexit
/*     */     //   35: aload_2
/*     */     //   36: athrow
/*     */     // Line number table:
/*     */     //   Java source line #264	-> byte code offset #0
/*     */     //   Java source line #265	-> byte code offset #7
/*     */     //   Java source line #267	-> byte code offset #15
/*     */     //   Java source line #268	-> byte code offset #22
/*     */     //   Java source line #269	-> byte code offset #32
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	37	0	this	FastTreeMap<K, V>
/*     */     //   20	14	1	Ljava/lang/Object;	Object
/*     */     //   32	4	2	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   22	31	32	finally
/*     */     //   32	35	32	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public V put(K key, V value)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 3	org/apache/commons/collections15/map/FastTreeMap:fast	Z
/*     */     //   4: ifeq +46 -> 50
/*     */     //   7: aload_0
/*     */     //   8: dup
/*     */     //   9: astore_3
/*     */     //   10: monitorenter
/*     */     //   11: aload_0
/*     */     //   12: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   15: invokevirtual 16	java/util/TreeMap:clone	()Ljava/lang/Object;
/*     */     //   18: checkcast 4	java/util/TreeMap
/*     */     //   21: astore 4
/*     */     //   23: aload 4
/*     */     //   25: aload_1
/*     */     //   26: aload_2
/*     */     //   27: invokevirtual 17	java/util/TreeMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   30: astore 5
/*     */     //   32: aload_0
/*     */     //   33: aload 4
/*     */     //   35: putfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   38: aload 5
/*     */     //   40: aload_3
/*     */     //   41: monitorexit
/*     */     //   42: areturn
/*     */     //   43: astore 6
/*     */     //   45: aload_3
/*     */     //   46: monitorexit
/*     */     //   47: aload 6
/*     */     //   49: athrow
/*     */     //   50: aload_0
/*     */     //   51: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   54: dup
/*     */     //   55: astore_3
/*     */     //   56: monitorenter
/*     */     //   57: aload_0
/*     */     //   58: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   61: aload_1
/*     */     //   62: aload_2
/*     */     //   63: invokevirtual 17	java/util/TreeMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   66: aload_3
/*     */     //   67: monitorexit
/*     */     //   68: areturn
/*     */     //   69: astore 7
/*     */     //   71: aload_3
/*     */     //   72: monitorexit
/*     */     //   73: aload 7
/*     */     //   75: athrow
/*     */     // Line number table:
/*     */     //   Java source line #290	-> byte code offset #0
/*     */     //   Java source line #291	-> byte code offset #7
/*     */     //   Java source line #292	-> byte code offset #11
/*     */     //   Java source line #293	-> byte code offset #23
/*     */     //   Java source line #294	-> byte code offset #32
/*     */     //   Java source line #295	-> byte code offset #38
/*     */     //   Java source line #296	-> byte code offset #43
/*     */     //   Java source line #298	-> byte code offset #50
/*     */     //   Java source line #299	-> byte code offset #57
/*     */     //   Java source line #300	-> byte code offset #69
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	76	0	this	FastTreeMap<K, V>
/*     */     //   0	76	1	key	K
/*     */     //   0	76	2	value	V
/*     */     //   9	37	3	Ljava/lang/Object;	Object
/*     */     //   55	17	3	Ljava/lang/Object;	Object
/*     */     //   21	13	4	temp	TreeMap<K, V>
/*     */     //   30	9	5	result	V
/*     */     //   43	5	6	localObject1	Object
/*     */     //   69	5	7	localObject2	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   11	42	43	finally
/*     */     //   43	47	43	finally
/*     */     //   57	68	69	finally
/*     */     //   69	73	69	finally
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends V> in)
/*     */   {
/* 311 */     if (this.fast) {
/* 312 */       synchronized (this) {
/* 313 */         TreeMap<K, V> temp = (TreeMap)this.map.clone();
/* 314 */         temp.putAll(in);
/* 315 */         this.map = temp;
/*     */       }
/*     */     } else {
/* 318 */       synchronized (this.map) {
/* 319 */         this.map.putAll(in);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public V remove(Object key)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 3	org/apache/commons/collections15/map/FastTreeMap:fast	Z
/*     */     //   4: ifeq +42 -> 46
/*     */     //   7: aload_0
/*     */     //   8: dup
/*     */     //   9: astore_2
/*     */     //   10: monitorenter
/*     */     //   11: aload_0
/*     */     //   12: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   15: invokevirtual 16	java/util/TreeMap:clone	()Ljava/lang/Object;
/*     */     //   18: checkcast 4	java/util/TreeMap
/*     */     //   21: astore_3
/*     */     //   22: aload_3
/*     */     //   23: aload_1
/*     */     //   24: invokevirtual 19	java/util/TreeMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   27: astore 4
/*     */     //   29: aload_0
/*     */     //   30: aload_3
/*     */     //   31: putfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   34: aload 4
/*     */     //   36: aload_2
/*     */     //   37: monitorexit
/*     */     //   38: areturn
/*     */     //   39: astore 5
/*     */     //   41: aload_2
/*     */     //   42: monitorexit
/*     */     //   43: aload 5
/*     */     //   45: athrow
/*     */     //   46: aload_0
/*     */     //   47: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   50: dup
/*     */     //   51: astore_2
/*     */     //   52: monitorenter
/*     */     //   53: aload_0
/*     */     //   54: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   57: aload_1
/*     */     //   58: invokevirtual 19	java/util/TreeMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   61: aload_2
/*     */     //   62: monitorexit
/*     */     //   63: areturn
/*     */     //   64: astore 6
/*     */     //   66: aload_2
/*     */     //   67: monitorexit
/*     */     //   68: aload 6
/*     */     //   70: athrow
/*     */     // Line number table:
/*     */     //   Java source line #332	-> byte code offset #0
/*     */     //   Java source line #333	-> byte code offset #7
/*     */     //   Java source line #334	-> byte code offset #11
/*     */     //   Java source line #335	-> byte code offset #22
/*     */     //   Java source line #336	-> byte code offset #29
/*     */     //   Java source line #337	-> byte code offset #34
/*     */     //   Java source line #338	-> byte code offset #39
/*     */     //   Java source line #340	-> byte code offset #46
/*     */     //   Java source line #341	-> byte code offset #53
/*     */     //   Java source line #342	-> byte code offset #64
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	71	0	this	FastTreeMap<K, V>
/*     */     //   0	71	1	key	Object
/*     */     //   9	33	2	Ljava/lang/Object;	Object
/*     */     //   51	16	2	Ljava/lang/Object;	Object
/*     */     //   21	10	3	temp	TreeMap<K, V>
/*     */     //   27	8	4	result	V
/*     */     //   39	5	5	localObject1	Object
/*     */     //   64	5	6	localObject2	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   11	38	39	finally
/*     */     //   39	43	39	finally
/*     */     //   53	63	64	finally
/*     */     //   64	68	64	finally
/*     */   }
/*     */   
/*     */   public void clear()
/*     */   {
/* 350 */     if (this.fast) {
/* 351 */       synchronized (this) {
/* 352 */         this.map = new TreeMap();
/*     */       }
/*     */     } else {
/* 355 */       synchronized (this.map) {
/* 356 */         this.map.clear();
/*     */       }
/*     */     }
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
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 376 */     if (o == this)
/* 377 */       return true;
/* 378 */     if (!(o instanceof Map)) {
/* 379 */       return false;
/*     */     }
/* 381 */     Map mo = (Map)o;
/*     */     
/*     */ 
/* 384 */     if (this.fast) {
/* 385 */       if (mo.size() != this.map.size()) {
/* 386 */         return false;
/*     */       }
/* 388 */       Iterator i = this.map.entrySet().iterator();
/* 389 */       while (i.hasNext()) {
/* 390 */         Map.Entry e = (Map.Entry)i.next();
/* 391 */         Object key = e.getKey();
/* 392 */         Object value = e.getValue();
/* 393 */         if (value == null) {
/* 394 */           if ((mo.get(key) != null) || (!mo.containsKey(key))) {
/* 395 */             return false;
/*     */           }
/*     */         }
/* 398 */         else if (!value.equals(mo.get(key))) {
/* 399 */           return false;
/*     */         }
/*     */       }
/*     */       
/* 403 */       return true;
/*     */     }
/* 405 */     synchronized (this.map) {
/* 406 */       if (mo.size() != this.map.size()) {
/* 407 */         return false;
/*     */       }
/* 409 */       Iterator i = this.map.entrySet().iterator();
/* 410 */       while (i.hasNext()) {
/* 411 */         Map.Entry e = (Map.Entry)i.next();
/* 412 */         Object key = e.getKey();
/* 413 */         Object value = e.getValue();
/* 414 */         if (value == null) {
/* 415 */           if ((mo.get(key) != null) || (!mo.containsKey(key))) {
/* 416 */             return false;
/*     */           }
/*     */         }
/* 419 */         else if (!value.equals(mo.get(key))) {
/* 420 */           return false;
/*     */         }
/*     */       }
/*     */       
/* 424 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 437 */     if (this.fast) {
/* 438 */       int h = 0;
/* 439 */       Iterator i = this.map.entrySet().iterator();
/* 440 */       while (i.hasNext()) {
/* 441 */         h += i.next().hashCode();
/*     */       }
/* 443 */       return h;
/*     */     }
/* 445 */     synchronized (this.map) {
/* 446 */       int h = 0;
/* 447 */       Iterator i = this.map.entrySet().iterator();
/* 448 */       while (i.hasNext()) {
/* 449 */         h += i.next().hashCode();
/*     */       }
/* 451 */       return h;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FastTreeMap<K, V> clone()
/*     */   {
/* 463 */     FastTreeMap results = null;
/* 464 */     if (this.fast) {
/* 465 */       results = new FastTreeMap(this.map);
/*     */     } else {
/* 467 */       synchronized (this.map) {
/* 468 */         results = new FastTreeMap(this.map);
/*     */       }
/*     */     }
/* 471 */     results.setFast(getFast());
/* 472 */     return results;
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public SortedMap<K, V> headMap(K key)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 3	org/apache/commons/collections15/map/FastTreeMap:fast	Z
/*     */     //   4: ifeq +12 -> 16
/*     */     //   7: aload_0
/*     */     //   8: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   11: aload_1
/*     */     //   12: invokevirtual 38	java/util/TreeMap:headMap	(Ljava/lang/Object;)Ljava/util/SortedMap;
/*     */     //   15: areturn
/*     */     //   16: aload_0
/*     */     //   17: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   20: dup
/*     */     //   21: astore_2
/*     */     //   22: monitorenter
/*     */     //   23: aload_0
/*     */     //   24: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   27: aload_1
/*     */     //   28: invokevirtual 38	java/util/TreeMap:headMap	(Ljava/lang/Object;)Ljava/util/SortedMap;
/*     */     //   31: aload_2
/*     */     //   32: monitorexit
/*     */     //   33: areturn
/*     */     //   34: astore_3
/*     */     //   35: aload_2
/*     */     //   36: monitorexit
/*     */     //   37: aload_3
/*     */     //   38: athrow
/*     */     // Line number table:
/*     */     //   Java source line #487	-> byte code offset #0
/*     */     //   Java source line #488	-> byte code offset #7
/*     */     //   Java source line #490	-> byte code offset #16
/*     */     //   Java source line #491	-> byte code offset #23
/*     */     //   Java source line #492	-> byte code offset #34
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	39	0	this	FastTreeMap<K, V>
/*     */     //   0	39	1	key	K
/*     */     //   21	15	2	Ljava/lang/Object;	Object
/*     */     //   34	4	3	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   23	33	34	finally
/*     */     //   34	37	34	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public SortedMap<K, V> subMap(K fromKey, K toKey)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 3	org/apache/commons/collections15/map/FastTreeMap:fast	Z
/*     */     //   4: ifeq +13 -> 17
/*     */     //   7: aload_0
/*     */     //   8: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   11: aload_1
/*     */     //   12: aload_2
/*     */     //   13: invokevirtual 39	java/util/TreeMap:subMap	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/SortedMap;
/*     */     //   16: areturn
/*     */     //   17: aload_0
/*     */     //   18: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   21: dup
/*     */     //   22: astore_3
/*     */     //   23: monitorenter
/*     */     //   24: aload_0
/*     */     //   25: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   28: aload_1
/*     */     //   29: aload_2
/*     */     //   30: invokevirtual 39	java/util/TreeMap:subMap	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/SortedMap;
/*     */     //   33: aload_3
/*     */     //   34: monitorexit
/*     */     //   35: areturn
/*     */     //   36: astore 4
/*     */     //   38: aload_3
/*     */     //   39: monitorexit
/*     */     //   40: aload 4
/*     */     //   42: athrow
/*     */     // Line number table:
/*     */     //   Java source line #505	-> byte code offset #0
/*     */     //   Java source line #506	-> byte code offset #7
/*     */     //   Java source line #508	-> byte code offset #17
/*     */     //   Java source line #509	-> byte code offset #24
/*     */     //   Java source line #510	-> byte code offset #36
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	43	0	this	FastTreeMap<K, V>
/*     */     //   0	43	1	fromKey	K
/*     */     //   0	43	2	toKey	K
/*     */     //   22	17	3	Ljava/lang/Object;	Object
/*     */     //   36	5	4	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   24	35	36	finally
/*     */     //   36	40	36	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public SortedMap<K, V> tailMap(K key)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 3	org/apache/commons/collections15/map/FastTreeMap:fast	Z
/*     */     //   4: ifeq +12 -> 16
/*     */     //   7: aload_0
/*     */     //   8: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   11: aload_1
/*     */     //   12: invokevirtual 40	java/util/TreeMap:tailMap	(Ljava/lang/Object;)Ljava/util/SortedMap;
/*     */     //   15: areturn
/*     */     //   16: aload_0
/*     */     //   17: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   20: dup
/*     */     //   21: astore_2
/*     */     //   22: monitorenter
/*     */     //   23: aload_0
/*     */     //   24: getfield 2	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */     //   27: aload_1
/*     */     //   28: invokevirtual 40	java/util/TreeMap:tailMap	(Ljava/lang/Object;)Ljava/util/SortedMap;
/*     */     //   31: aload_2
/*     */     //   32: monitorexit
/*     */     //   33: areturn
/*     */     //   34: astore_3
/*     */     //   35: aload_2
/*     */     //   36: monitorexit
/*     */     //   37: aload_3
/*     */     //   38: athrow
/*     */     // Line number table:
/*     */     //   Java source line #522	-> byte code offset #0
/*     */     //   Java source line #523	-> byte code offset #7
/*     */     //   Java source line #525	-> byte code offset #16
/*     */     //   Java source line #526	-> byte code offset #23
/*     */     //   Java source line #527	-> byte code offset #34
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	39	0	this	FastTreeMap<K, V>
/*     */     //   0	39	1	key	K
/*     */     //   21	15	2	Ljava/lang/Object;	Object
/*     */     //   34	4	3	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   23	33	34	finally
/*     */     //   34	37	34	finally
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<K, V>> entrySet()
/*     */   {
/* 540 */     return new EntrySet(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Set<K> keySet()
/*     */   {
/* 547 */     return new KeySet(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<V> values()
/*     */   {
/* 554 */     return new Values(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private abstract class CollectionView<E>
/*     */     implements Collection<E>
/*     */   {
/*     */     public CollectionView() {}
/*     */     
/*     */ 
/*     */ 
/*     */     protected abstract Collection<E> get(Map<K, V> paramMap);
/*     */     
/*     */ 
/*     */     protected abstract E iteratorNext(Map.Entry<K, V> paramEntry);
/*     */     
/*     */ 
/*     */     public void clear()
/*     */     {
/* 574 */       if (FastTreeMap.this.fast) {
/* 575 */         synchronized (FastTreeMap.this) {
/* 576 */           FastTreeMap.this.map = new TreeMap();
/*     */         }
/*     */       } else {
/* 579 */         synchronized (FastTreeMap.this.map) {
/* 580 */           get(FastTreeMap.this.map).clear();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     /* Error */
/*     */     public boolean remove(Object o)
/*     */     {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   4: getfield 3	org/apache/commons/collections15/map/FastTreeMap:fast	Z
/*     */       //   7: ifeq +57 -> 64
/*     */       //   10: aload_0
/*     */       //   11: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   14: dup
/*     */       //   15: astore_2
/*     */       //   16: monitorenter
/*     */       //   17: aload_0
/*     */       //   18: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   21: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   24: invokevirtual 9	java/util/TreeMap:clone	()Ljava/lang/Object;
/*     */       //   27: checkcast 4	java/util/TreeMap
/*     */       //   30: astore_3
/*     */       //   31: aload_0
/*     */       //   32: aload_3
/*     */       //   33: invokevirtual 7	org/apache/commons/collections15/map/FastTreeMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   36: aload_1
/*     */       //   37: invokeinterface 10 2 0
/*     */       //   42: istore 4
/*     */       //   44: aload_0
/*     */       //   45: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   48: aload_3
/*     */       //   49: putfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   52: iload 4
/*     */       //   54: aload_2
/*     */       //   55: monitorexit
/*     */       //   56: ireturn
/*     */       //   57: astore 5
/*     */       //   59: aload_2
/*     */       //   60: monitorexit
/*     */       //   61: aload 5
/*     */       //   63: athrow
/*     */       //   64: aload_0
/*     */       //   65: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   68: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   71: dup
/*     */       //   72: astore_2
/*     */       //   73: monitorenter
/*     */       //   74: aload_0
/*     */       //   75: aload_0
/*     */       //   76: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   79: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   82: invokevirtual 7	org/apache/commons/collections15/map/FastTreeMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   85: aload_1
/*     */       //   86: invokeinterface 10 2 0
/*     */       //   91: aload_2
/*     */       //   92: monitorexit
/*     */       //   93: ireturn
/*     */       //   94: astore 6
/*     */       //   96: aload_2
/*     */       //   97: monitorexit
/*     */       //   98: aload 6
/*     */       //   100: athrow
/*     */       // Line number table:
/*     */       //   Java source line #586	-> byte code offset #0
/*     */       //   Java source line #587	-> byte code offset #10
/*     */       //   Java source line #588	-> byte code offset #17
/*     */       //   Java source line #589	-> byte code offset #31
/*     */       //   Java source line #590	-> byte code offset #44
/*     */       //   Java source line #591	-> byte code offset #52
/*     */       //   Java source line #592	-> byte code offset #57
/*     */       //   Java source line #594	-> byte code offset #64
/*     */       //   Java source line #595	-> byte code offset #74
/*     */       //   Java source line #596	-> byte code offset #94
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	101	0	this	FastTreeMap<K, V>.CollectionView<E>
/*     */       //   0	101	1	o	Object
/*     */       //   15	45	2	Ljava/lang/Object;	Object
/*     */       //   72	25	2	Ljava/lang/Object;	Object
/*     */       //   30	19	3	temp	TreeMap<K, V>
/*     */       //   42	11	4	r	boolean
/*     */       //   57	5	5	localObject1	Object
/*     */       //   94	5	6	localObject2	Object
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   17	56	57	finally
/*     */       //   57	61	57	finally
/*     */       //   74	93	94	finally
/*     */       //   94	98	94	finally
/*     */     }
/*     */     
/*     */     /* Error */
/*     */     public boolean removeAll(Collection<?> o)
/*     */     {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   4: getfield 3	org/apache/commons/collections15/map/FastTreeMap:fast	Z
/*     */       //   7: ifeq +57 -> 64
/*     */       //   10: aload_0
/*     */       //   11: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   14: dup
/*     */       //   15: astore_2
/*     */       //   16: monitorenter
/*     */       //   17: aload_0
/*     */       //   18: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   21: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   24: invokevirtual 9	java/util/TreeMap:clone	()Ljava/lang/Object;
/*     */       //   27: checkcast 4	java/util/TreeMap
/*     */       //   30: astore_3
/*     */       //   31: aload_0
/*     */       //   32: aload_3
/*     */       //   33: invokevirtual 7	org/apache/commons/collections15/map/FastTreeMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   36: aload_1
/*     */       //   37: invokeinterface 11 2 0
/*     */       //   42: istore 4
/*     */       //   44: aload_0
/*     */       //   45: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   48: aload_3
/*     */       //   49: putfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   52: iload 4
/*     */       //   54: aload_2
/*     */       //   55: monitorexit
/*     */       //   56: ireturn
/*     */       //   57: astore 5
/*     */       //   59: aload_2
/*     */       //   60: monitorexit
/*     */       //   61: aload 5
/*     */       //   63: athrow
/*     */       //   64: aload_0
/*     */       //   65: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   68: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   71: dup
/*     */       //   72: astore_2
/*     */       //   73: monitorenter
/*     */       //   74: aload_0
/*     */       //   75: aload_0
/*     */       //   76: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   79: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   82: invokevirtual 7	org/apache/commons/collections15/map/FastTreeMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   85: aload_1
/*     */       //   86: invokeinterface 11 2 0
/*     */       //   91: aload_2
/*     */       //   92: monitorexit
/*     */       //   93: ireturn
/*     */       //   94: astore 6
/*     */       //   96: aload_2
/*     */       //   97: monitorexit
/*     */       //   98: aload 6
/*     */       //   100: athrow
/*     */       // Line number table:
/*     */       //   Java source line #601	-> byte code offset #0
/*     */       //   Java source line #602	-> byte code offset #10
/*     */       //   Java source line #603	-> byte code offset #17
/*     */       //   Java source line #604	-> byte code offset #31
/*     */       //   Java source line #605	-> byte code offset #44
/*     */       //   Java source line #606	-> byte code offset #52
/*     */       //   Java source line #607	-> byte code offset #57
/*     */       //   Java source line #609	-> byte code offset #64
/*     */       //   Java source line #610	-> byte code offset #74
/*     */       //   Java source line #611	-> byte code offset #94
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	101	0	this	FastTreeMap<K, V>.CollectionView<E>
/*     */       //   0	101	1	o	Collection<?>
/*     */       //   15	45	2	Ljava/lang/Object;	Object
/*     */       //   72	25	2	Ljava/lang/Object;	Object
/*     */       //   30	19	3	temp	TreeMap
/*     */       //   42	11	4	r	boolean
/*     */       //   57	5	5	localObject1	Object
/*     */       //   94	5	6	localObject2	Object
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   17	56	57	finally
/*     */       //   57	61	57	finally
/*     */       //   74	93	94	finally
/*     */       //   94	98	94	finally
/*     */     }
/*     */     
/*     */     /* Error */
/*     */     public boolean retainAll(Collection<?> o)
/*     */     {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   4: getfield 3	org/apache/commons/collections15/map/FastTreeMap:fast	Z
/*     */       //   7: ifeq +57 -> 64
/*     */       //   10: aload_0
/*     */       //   11: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   14: dup
/*     */       //   15: astore_2
/*     */       //   16: monitorenter
/*     */       //   17: aload_0
/*     */       //   18: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   21: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   24: invokevirtual 9	java/util/TreeMap:clone	()Ljava/lang/Object;
/*     */       //   27: checkcast 4	java/util/TreeMap
/*     */       //   30: astore_3
/*     */       //   31: aload_0
/*     */       //   32: aload_3
/*     */       //   33: invokevirtual 7	org/apache/commons/collections15/map/FastTreeMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   36: aload_1
/*     */       //   37: invokeinterface 12 2 0
/*     */       //   42: istore 4
/*     */       //   44: aload_0
/*     */       //   45: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   48: aload_3
/*     */       //   49: putfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   52: iload 4
/*     */       //   54: aload_2
/*     */       //   55: monitorexit
/*     */       //   56: ireturn
/*     */       //   57: astore 5
/*     */       //   59: aload_2
/*     */       //   60: monitorexit
/*     */       //   61: aload 5
/*     */       //   63: athrow
/*     */       //   64: aload_0
/*     */       //   65: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   68: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   71: dup
/*     */       //   72: astore_2
/*     */       //   73: monitorenter
/*     */       //   74: aload_0
/*     */       //   75: aload_0
/*     */       //   76: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   79: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   82: invokevirtual 7	org/apache/commons/collections15/map/FastTreeMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   85: aload_1
/*     */       //   86: invokeinterface 12 2 0
/*     */       //   91: aload_2
/*     */       //   92: monitorexit
/*     */       //   93: ireturn
/*     */       //   94: astore 6
/*     */       //   96: aload_2
/*     */       //   97: monitorexit
/*     */       //   98: aload 6
/*     */       //   100: athrow
/*     */       // Line number table:
/*     */       //   Java source line #616	-> byte code offset #0
/*     */       //   Java source line #617	-> byte code offset #10
/*     */       //   Java source line #618	-> byte code offset #17
/*     */       //   Java source line #619	-> byte code offset #31
/*     */       //   Java source line #620	-> byte code offset #44
/*     */       //   Java source line #621	-> byte code offset #52
/*     */       //   Java source line #622	-> byte code offset #57
/*     */       //   Java source line #624	-> byte code offset #64
/*     */       //   Java source line #625	-> byte code offset #74
/*     */       //   Java source line #626	-> byte code offset #94
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	101	0	this	FastTreeMap<K, V>.CollectionView<E>
/*     */       //   0	101	1	o	Collection<?>
/*     */       //   15	45	2	Ljava/lang/Object;	Object
/*     */       //   72	25	2	Ljava/lang/Object;	Object
/*     */       //   30	19	3	temp	TreeMap
/*     */       //   42	11	4	r	boolean
/*     */       //   57	5	5	localObject1	Object
/*     */       //   94	5	6	localObject2	Object
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   17	56	57	finally
/*     */       //   57	61	57	finally
/*     */       //   74	93	94	finally
/*     */       //   94	98	94	finally
/*     */     }
/*     */     
/*     */     /* Error */
/*     */     public int size()
/*     */     {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   4: getfield 3	org/apache/commons/collections15/map/FastTreeMap:fast	Z
/*     */       //   7: ifeq +20 -> 27
/*     */       //   10: aload_0
/*     */       //   11: aload_0
/*     */       //   12: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   15: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   18: invokevirtual 7	org/apache/commons/collections15/map/FastTreeMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   21: invokeinterface 13 1 0
/*     */       //   26: ireturn
/*     */       //   27: aload_0
/*     */       //   28: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   31: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   34: dup
/*     */       //   35: astore_1
/*     */       //   36: monitorenter
/*     */       //   37: aload_0
/*     */       //   38: aload_0
/*     */       //   39: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   42: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   45: invokevirtual 7	org/apache/commons/collections15/map/FastTreeMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   48: invokeinterface 13 1 0
/*     */       //   53: aload_1
/*     */       //   54: monitorexit
/*     */       //   55: ireturn
/*     */       //   56: astore_2
/*     */       //   57: aload_1
/*     */       //   58: monitorexit
/*     */       //   59: aload_2
/*     */       //   60: athrow
/*     */       // Line number table:
/*     */       //   Java source line #631	-> byte code offset #0
/*     */       //   Java source line #632	-> byte code offset #10
/*     */       //   Java source line #634	-> byte code offset #27
/*     */       //   Java source line #635	-> byte code offset #37
/*     */       //   Java source line #636	-> byte code offset #56
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	61	0	this	FastTreeMap<K, V>.CollectionView<E>
/*     */       //   35	23	1	Ljava/lang/Object;	Object
/*     */       //   56	4	2	localObject1	Object
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   37	55	56	finally
/*     */       //   56	59	56	finally
/*     */     }
/*     */     
/*     */     /* Error */
/*     */     public boolean isEmpty()
/*     */     {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   4: getfield 3	org/apache/commons/collections15/map/FastTreeMap:fast	Z
/*     */       //   7: ifeq +20 -> 27
/*     */       //   10: aload_0
/*     */       //   11: aload_0
/*     */       //   12: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   15: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   18: invokevirtual 7	org/apache/commons/collections15/map/FastTreeMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   21: invokeinterface 14 1 0
/*     */       //   26: ireturn
/*     */       //   27: aload_0
/*     */       //   28: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   31: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   34: dup
/*     */       //   35: astore_1
/*     */       //   36: monitorenter
/*     */       //   37: aload_0
/*     */       //   38: aload_0
/*     */       //   39: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   42: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   45: invokevirtual 7	org/apache/commons/collections15/map/FastTreeMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   48: invokeinterface 14 1 0
/*     */       //   53: aload_1
/*     */       //   54: monitorexit
/*     */       //   55: ireturn
/*     */       //   56: astore_2
/*     */       //   57: aload_1
/*     */       //   58: monitorexit
/*     */       //   59: aload_2
/*     */       //   60: athrow
/*     */       // Line number table:
/*     */       //   Java source line #642	-> byte code offset #0
/*     */       //   Java source line #643	-> byte code offset #10
/*     */       //   Java source line #645	-> byte code offset #27
/*     */       //   Java source line #646	-> byte code offset #37
/*     */       //   Java source line #647	-> byte code offset #56
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	61	0	this	FastTreeMap<K, V>.CollectionView<E>
/*     */       //   35	23	1	Ljava/lang/Object;	Object
/*     */       //   56	4	2	localObject1	Object
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   37	55	56	finally
/*     */       //   56	59	56	finally
/*     */     }
/*     */     
/*     */     /* Error */
/*     */     public boolean contains(Object o)
/*     */     {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   4: getfield 3	org/apache/commons/collections15/map/FastTreeMap:fast	Z
/*     */       //   7: ifeq +21 -> 28
/*     */       //   10: aload_0
/*     */       //   11: aload_0
/*     */       //   12: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   15: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   18: invokevirtual 7	org/apache/commons/collections15/map/FastTreeMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   21: aload_1
/*     */       //   22: invokeinterface 15 2 0
/*     */       //   27: ireturn
/*     */       //   28: aload_0
/*     */       //   29: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   32: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   35: dup
/*     */       //   36: astore_2
/*     */       //   37: monitorenter
/*     */       //   38: aload_0
/*     */       //   39: aload_0
/*     */       //   40: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   43: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   46: invokevirtual 7	org/apache/commons/collections15/map/FastTreeMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   49: aload_1
/*     */       //   50: invokeinterface 15 2 0
/*     */       //   55: aload_2
/*     */       //   56: monitorexit
/*     */       //   57: ireturn
/*     */       //   58: astore_3
/*     */       //   59: aload_2
/*     */       //   60: monitorexit
/*     */       //   61: aload_3
/*     */       //   62: athrow
/*     */       // Line number table:
/*     */       //   Java source line #652	-> byte code offset #0
/*     */       //   Java source line #653	-> byte code offset #10
/*     */       //   Java source line #655	-> byte code offset #28
/*     */       //   Java source line #656	-> byte code offset #38
/*     */       //   Java source line #657	-> byte code offset #58
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	63	0	this	FastTreeMap<K, V>.CollectionView<E>
/*     */       //   0	63	1	o	Object
/*     */       //   36	24	2	Ljava/lang/Object;	Object
/*     */       //   58	4	3	localObject1	Object
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   38	57	58	finally
/*     */       //   58	61	58	finally
/*     */     }
/*     */     
/*     */     /* Error */
/*     */     public boolean containsAll(Collection<?> o)
/*     */     {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   4: getfield 3	org/apache/commons/collections15/map/FastTreeMap:fast	Z
/*     */       //   7: ifeq +21 -> 28
/*     */       //   10: aload_0
/*     */       //   11: aload_0
/*     */       //   12: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   15: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   18: invokevirtual 7	org/apache/commons/collections15/map/FastTreeMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   21: aload_1
/*     */       //   22: invokeinterface 16 2 0
/*     */       //   27: ireturn
/*     */       //   28: aload_0
/*     */       //   29: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   32: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   35: dup
/*     */       //   36: astore_2
/*     */       //   37: monitorenter
/*     */       //   38: aload_0
/*     */       //   39: aload_0
/*     */       //   40: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   43: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   46: invokevirtual 7	org/apache/commons/collections15/map/FastTreeMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   49: aload_1
/*     */       //   50: invokeinterface 16 2 0
/*     */       //   55: aload_2
/*     */       //   56: monitorexit
/*     */       //   57: ireturn
/*     */       //   58: astore_3
/*     */       //   59: aload_2
/*     */       //   60: monitorexit
/*     */       //   61: aload_3
/*     */       //   62: athrow
/*     */       // Line number table:
/*     */       //   Java source line #662	-> byte code offset #0
/*     */       //   Java source line #663	-> byte code offset #10
/*     */       //   Java source line #665	-> byte code offset #28
/*     */       //   Java source line #666	-> byte code offset #38
/*     */       //   Java source line #667	-> byte code offset #58
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	63	0	this	FastTreeMap<K, V>.CollectionView<E>
/*     */       //   0	63	1	o	Collection<?>
/*     */       //   36	24	2	Ljava/lang/Object;	Object
/*     */       //   58	4	3	localObject1	Object
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   38	57	58	finally
/*     */       //   58	61	58	finally
/*     */     }
/*     */     
/*     */     /* Error */
/*     */     public <T> T[] toArray(T[] o)
/*     */     {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   4: getfield 3	org/apache/commons/collections15/map/FastTreeMap:fast	Z
/*     */       //   7: ifeq +21 -> 28
/*     */       //   10: aload_0
/*     */       //   11: aload_0
/*     */       //   12: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   15: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   18: invokevirtual 7	org/apache/commons/collections15/map/FastTreeMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   21: aload_1
/*     */       //   22: invokeinterface 17 2 0
/*     */       //   27: areturn
/*     */       //   28: aload_0
/*     */       //   29: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   32: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   35: dup
/*     */       //   36: astore_2
/*     */       //   37: monitorenter
/*     */       //   38: aload_0
/*     */       //   39: aload_0
/*     */       //   40: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   43: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   46: invokevirtual 7	org/apache/commons/collections15/map/FastTreeMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   49: aload_1
/*     */       //   50: invokeinterface 17 2 0
/*     */       //   55: aload_2
/*     */       //   56: monitorexit
/*     */       //   57: areturn
/*     */       //   58: astore_3
/*     */       //   59: aload_2
/*     */       //   60: monitorexit
/*     */       //   61: aload_3
/*     */       //   62: athrow
/*     */       // Line number table:
/*     */       //   Java source line #672	-> byte code offset #0
/*     */       //   Java source line #673	-> byte code offset #10
/*     */       //   Java source line #675	-> byte code offset #28
/*     */       //   Java source line #676	-> byte code offset #38
/*     */       //   Java source line #677	-> byte code offset #58
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	63	0	this	FastTreeMap<K, V>.CollectionView<E>
/*     */       //   0	63	1	o	T[]
/*     */       //   36	24	2	Ljava/lang/Object;	Object
/*     */       //   58	4	3	localObject1	Object
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   38	57	58	finally
/*     */       //   58	61	58	finally
/*     */     }
/*     */     
/*     */     /* Error */
/*     */     public Object[] toArray()
/*     */     {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   4: getfield 3	org/apache/commons/collections15/map/FastTreeMap:fast	Z
/*     */       //   7: ifeq +20 -> 27
/*     */       //   10: aload_0
/*     */       //   11: aload_0
/*     */       //   12: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   15: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   18: invokevirtual 7	org/apache/commons/collections15/map/FastTreeMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   21: invokeinterface 18 1 0
/*     */       //   26: areturn
/*     */       //   27: aload_0
/*     */       //   28: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   31: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   34: dup
/*     */       //   35: astore_1
/*     */       //   36: monitorenter
/*     */       //   37: aload_0
/*     */       //   38: aload_0
/*     */       //   39: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   42: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   45: invokevirtual 7	org/apache/commons/collections15/map/FastTreeMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   48: invokeinterface 18 1 0
/*     */       //   53: aload_1
/*     */       //   54: monitorexit
/*     */       //   55: areturn
/*     */       //   56: astore_2
/*     */       //   57: aload_1
/*     */       //   58: monitorexit
/*     */       //   59: aload_2
/*     */       //   60: athrow
/*     */       // Line number table:
/*     */       //   Java source line #682	-> byte code offset #0
/*     */       //   Java source line #683	-> byte code offset #10
/*     */       //   Java source line #685	-> byte code offset #27
/*     */       //   Java source line #686	-> byte code offset #37
/*     */       //   Java source line #687	-> byte code offset #56
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	61	0	this	FastTreeMap<K, V>.CollectionView<E>
/*     */       //   35	23	1	Ljava/lang/Object;	Object
/*     */       //   56	4	2	localObject1	Object
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   37	55	56	finally
/*     */       //   56	59	56	finally
/*     */     }
/*     */     
/*     */     /* Error */
/*     */     public boolean equals(Object o)
/*     */     {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: aload_0
/*     */       //   2: if_acmpne +5 -> 7
/*     */       //   5: iconst_1
/*     */       //   6: ireturn
/*     */       //   7: aload_0
/*     */       //   8: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   11: getfield 3	org/apache/commons/collections15/map/FastTreeMap:fast	Z
/*     */       //   14: ifeq +19 -> 33
/*     */       //   17: aload_0
/*     */       //   18: aload_0
/*     */       //   19: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   22: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   25: invokevirtual 7	org/apache/commons/collections15/map/FastTreeMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   28: aload_1
/*     */       //   29: invokevirtual 19	java/lang/Object:equals	(Ljava/lang/Object;)Z
/*     */       //   32: ireturn
/*     */       //   33: aload_0
/*     */       //   34: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   37: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   40: dup
/*     */       //   41: astore_2
/*     */       //   42: monitorenter
/*     */       //   43: aload_0
/*     */       //   44: aload_0
/*     */       //   45: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   48: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   51: invokevirtual 7	org/apache/commons/collections15/map/FastTreeMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   54: aload_1
/*     */       //   55: invokevirtual 19	java/lang/Object:equals	(Ljava/lang/Object;)Z
/*     */       //   58: aload_2
/*     */       //   59: monitorexit
/*     */       //   60: ireturn
/*     */       //   61: astore_3
/*     */       //   62: aload_2
/*     */       //   63: monitorexit
/*     */       //   64: aload_3
/*     */       //   65: athrow
/*     */       // Line number table:
/*     */       //   Java source line #693	-> byte code offset #0
/*     */       //   Java source line #694	-> byte code offset #7
/*     */       //   Java source line #695	-> byte code offset #17
/*     */       //   Java source line #697	-> byte code offset #33
/*     */       //   Java source line #698	-> byte code offset #43
/*     */       //   Java source line #699	-> byte code offset #61
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	66	0	this	FastTreeMap<K, V>.CollectionView<E>
/*     */       //   0	66	1	o	Object
/*     */       //   41	22	2	Ljava/lang/Object;	Object
/*     */       //   61	4	3	localObject1	Object
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   43	60	61	finally
/*     */       //   61	64	61	finally
/*     */     }
/*     */     
/*     */     /* Error */
/*     */     public int hashCode()
/*     */     {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   4: getfield 3	org/apache/commons/collections15/map/FastTreeMap:fast	Z
/*     */       //   7: ifeq +18 -> 25
/*     */       //   10: aload_0
/*     */       //   11: aload_0
/*     */       //   12: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   15: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   18: invokevirtual 7	org/apache/commons/collections15/map/FastTreeMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   21: invokevirtual 20	java/lang/Object:hashCode	()I
/*     */       //   24: ireturn
/*     */       //   25: aload_0
/*     */       //   26: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   29: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   32: dup
/*     */       //   33: astore_1
/*     */       //   34: monitorenter
/*     */       //   35: aload_0
/*     */       //   36: aload_0
/*     */       //   37: getfield 1	org/apache/commons/collections15/map/FastTreeMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastTreeMap;
/*     */       //   40: getfield 6	org/apache/commons/collections15/map/FastTreeMap:map	Ljava/util/TreeMap;
/*     */       //   43: invokevirtual 7	org/apache/commons/collections15/map/FastTreeMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   46: invokevirtual 20	java/lang/Object:hashCode	()I
/*     */       //   49: aload_1
/*     */       //   50: monitorexit
/*     */       //   51: ireturn
/*     */       //   52: astore_2
/*     */       //   53: aload_1
/*     */       //   54: monitorexit
/*     */       //   55: aload_2
/*     */       //   56: athrow
/*     */       // Line number table:
/*     */       //   Java source line #704	-> byte code offset #0
/*     */       //   Java source line #705	-> byte code offset #10
/*     */       //   Java source line #707	-> byte code offset #25
/*     */       //   Java source line #708	-> byte code offset #35
/*     */       //   Java source line #709	-> byte code offset #52
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	57	0	this	FastTreeMap<K, V>.CollectionView<E>
/*     */       //   33	21	1	Ljava/lang/Object;	Object
/*     */       //   52	4	2	localObject1	Object
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   35	51	52	finally
/*     */       //   52	55	52	finally
/*     */     }
/*     */     
/*     */     public boolean add(E o)
/*     */     {
/* 714 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public boolean addAll(Collection<? extends E> c) {
/* 718 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public Iterator<E> iterator() {
/* 722 */       return new CollectionViewIterator();
/*     */     }
/*     */     
/*     */     private class CollectionViewIterator implements Iterator<E>
/*     */     {
/*     */       private Map<K, V> expected;
/* 728 */       private Map.Entry<K, V> lastReturned = null;
/*     */       private Iterator<Map.Entry<K, V>> iterator;
/*     */       
/*     */       public CollectionViewIterator() {
/* 732 */         this.expected = FastTreeMap.this.map;
/* 733 */         this.iterator = this.expected.entrySet().iterator();
/*     */       }
/*     */       
/*     */       public boolean hasNext() {
/* 737 */         if (this.expected != FastTreeMap.this.map) {
/* 738 */           throw new ConcurrentModificationException();
/*     */         }
/* 740 */         return this.iterator.hasNext();
/*     */       }
/*     */       
/*     */       public E next() {
/* 744 */         if (this.expected != FastTreeMap.this.map) {
/* 745 */           throw new ConcurrentModificationException();
/*     */         }
/* 747 */         this.lastReturned = ((Map.Entry)this.iterator.next());
/* 748 */         return (E)FastTreeMap.CollectionView.this.iteratorNext(this.lastReturned);
/*     */       }
/*     */       
/*     */       public void remove() {
/* 752 */         if (this.lastReturned == null) {
/* 753 */           throw new IllegalStateException();
/*     */         }
/* 755 */         if (FastTreeMap.this.fast) {
/* 756 */           synchronized (FastTreeMap.this) {
/* 757 */             if (this.expected != FastTreeMap.this.map) {
/* 758 */               throw new ConcurrentModificationException();
/*     */             }
/* 760 */             FastTreeMap.this.remove(this.lastReturned.getKey());
/* 761 */             this.lastReturned = null;
/* 762 */             this.expected = FastTreeMap.this.map;
/*     */           }
/*     */         } else {
/* 765 */           this.iterator.remove();
/* 766 */           this.lastReturned = null;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private class KeySet extends FastTreeMap<K, V>.CollectionView<K> implements Set<K>
/*     */   {
/*     */     private KeySet() {
/* 775 */       super();
/*     */     }
/*     */     
/* 778 */     protected Collection<K> get(Map<K, V> map) { return map.keySet(); }
/*     */     
/*     */     protected K iteratorNext(Map.Entry<K, V> entry)
/*     */     {
/* 782 */       return (K)entry.getKey();
/*     */     }
/*     */   }
/*     */   
/*     */   private class Values extends FastTreeMap<K, V>.CollectionView<V>
/*     */   {
/*     */     private Values()
/*     */     {
/* 790 */       super();
/*     */     }
/*     */     
/* 793 */     protected Collection<V> get(Map<K, V> map) { return map.values(); }
/*     */     
/*     */     protected V iteratorNext(Map.Entry<K, V> entry)
/*     */     {
/* 797 */       return (V)entry.getValue();
/*     */     }
/*     */   }
/*     */   
/*     */   private class EntrySet extends FastTreeMap<K, V>.CollectionView<Map.Entry<K, V>> implements Set<Map.Entry<K, V>>
/*     */   {
/*     */     private EntrySet() {
/* 804 */       super();
/*     */     }
/*     */     
/* 807 */     protected Collection<Map.Entry<K, V>> get(Map<K, V> map) { return map.entrySet(); }
/*     */     
/*     */ 
/*     */     protected Map.Entry<K, V> iteratorNext(Map.Entry<K, V> entry)
/*     */     {
/* 812 */       return entry;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/FastTreeMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */