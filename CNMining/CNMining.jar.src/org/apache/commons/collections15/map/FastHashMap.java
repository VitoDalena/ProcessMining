/*     */ package org.apache.commons.collections15.map;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FastHashMap<K, V>
/*     */   extends HashMap<K, V>
/*     */ {
/*  65 */   protected HashMap<K, V> map = null;
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
/*     */   public FastHashMap()
/*     */   {
/*  80 */     this.map = new HashMap();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FastHashMap(int capacity)
/*     */   {
/*  90 */     this.map = new HashMap(capacity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FastHashMap(int capacity, float factor)
/*     */   {
/* 101 */     this.map = new HashMap(capacity, factor);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FastHashMap(Map map)
/*     */   {
/* 111 */     this.map = new HashMap(map);
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
/* 124 */     return this.fast;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFast(boolean fast)
/*     */   {
/* 133 */     this.fast = fast;
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public V get(Object key)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 3	org/apache/commons/collections15/map/FastHashMap:fast	Z
/*     */     //   4: ifeq +12 -> 16
/*     */     //   7: aload_0
/*     */     //   8: getfield 2	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */     //   11: aload_1
/*     */     //   12: invokevirtual 8	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   15: areturn
/*     */     //   16: aload_0
/*     */     //   17: getfield 2	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */     //   20: dup
/*     */     //   21: astore_2
/*     */     //   22: monitorenter
/*     */     //   23: aload_0
/*     */     //   24: getfield 2	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */     //   27: aload_1
/*     */     //   28: invokevirtual 8	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   31: aload_2
/*     */     //   32: monitorexit
/*     */     //   33: areturn
/*     */     //   34: astore_3
/*     */     //   35: aload_2
/*     */     //   36: monitorexit
/*     */     //   37: aload_3
/*     */     //   38: athrow
/*     */     // Line number table:
/*     */     //   Java source line #152	-> byte code offset #0
/*     */     //   Java source line #153	-> byte code offset #7
/*     */     //   Java source line #155	-> byte code offset #16
/*     */     //   Java source line #156	-> byte code offset #23
/*     */     //   Java source line #157	-> byte code offset #34
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	39	0	this	FastHashMap<K, V>
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
/*     */     //   1: getfield 3	org/apache/commons/collections15/map/FastHashMap:fast	Z
/*     */     //   4: ifeq +11 -> 15
/*     */     //   7: aload_0
/*     */     //   8: getfield 2	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */     //   11: invokevirtual 9	java/util/HashMap:size	()I
/*     */     //   14: ireturn
/*     */     //   15: aload_0
/*     */     //   16: getfield 2	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */     //   19: dup
/*     */     //   20: astore_1
/*     */     //   21: monitorenter
/*     */     //   22: aload_0
/*     */     //   23: getfield 2	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */     //   26: invokevirtual 9	java/util/HashMap:size	()I
/*     */     //   29: aload_1
/*     */     //   30: monitorexit
/*     */     //   31: ireturn
/*     */     //   32: astore_2
/*     */     //   33: aload_1
/*     */     //   34: monitorexit
/*     */     //   35: aload_2
/*     */     //   36: athrow
/*     */     // Line number table:
/*     */     //   Java source line #167	-> byte code offset #0
/*     */     //   Java source line #168	-> byte code offset #7
/*     */     //   Java source line #170	-> byte code offset #15
/*     */     //   Java source line #171	-> byte code offset #22
/*     */     //   Java source line #172	-> byte code offset #32
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	37	0	this	FastHashMap<K, V>
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
/*     */     //   1: getfield 3	org/apache/commons/collections15/map/FastHashMap:fast	Z
/*     */     //   4: ifeq +11 -> 15
/*     */     //   7: aload_0
/*     */     //   8: getfield 2	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */     //   11: invokevirtual 10	java/util/HashMap:isEmpty	()Z
/*     */     //   14: ireturn
/*     */     //   15: aload_0
/*     */     //   16: getfield 2	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */     //   19: dup
/*     */     //   20: astore_1
/*     */     //   21: monitorenter
/*     */     //   22: aload_0
/*     */     //   23: getfield 2	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */     //   26: invokevirtual 10	java/util/HashMap:isEmpty	()Z
/*     */     //   29: aload_1
/*     */     //   30: monitorexit
/*     */     //   31: ireturn
/*     */     //   32: astore_2
/*     */     //   33: aload_1
/*     */     //   34: monitorexit
/*     */     //   35: aload_2
/*     */     //   36: athrow
/*     */     // Line number table:
/*     */     //   Java source line #182	-> byte code offset #0
/*     */     //   Java source line #183	-> byte code offset #7
/*     */     //   Java source line #185	-> byte code offset #15
/*     */     //   Java source line #186	-> byte code offset #22
/*     */     //   Java source line #187	-> byte code offset #32
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	37	0	this	FastHashMap<K, V>
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
/*     */     //   1: getfield 3	org/apache/commons/collections15/map/FastHashMap:fast	Z
/*     */     //   4: ifeq +12 -> 16
/*     */     //   7: aload_0
/*     */     //   8: getfield 2	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */     //   11: aload_1
/*     */     //   12: invokevirtual 11	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
/*     */     //   15: ireturn
/*     */     //   16: aload_0
/*     */     //   17: getfield 2	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */     //   20: dup
/*     */     //   21: astore_2
/*     */     //   22: monitorenter
/*     */     //   23: aload_0
/*     */     //   24: getfield 2	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */     //   27: aload_1
/*     */     //   28: invokevirtual 11	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
/*     */     //   31: aload_2
/*     */     //   32: monitorexit
/*     */     //   33: ireturn
/*     */     //   34: astore_3
/*     */     //   35: aload_2
/*     */     //   36: monitorexit
/*     */     //   37: aload_3
/*     */     //   38: athrow
/*     */     // Line number table:
/*     */     //   Java source line #199	-> byte code offset #0
/*     */     //   Java source line #200	-> byte code offset #7
/*     */     //   Java source line #202	-> byte code offset #16
/*     */     //   Java source line #203	-> byte code offset #23
/*     */     //   Java source line #204	-> byte code offset #34
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	39	0	this	FastHashMap<K, V>
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
/*     */     //   1: getfield 3	org/apache/commons/collections15/map/FastHashMap:fast	Z
/*     */     //   4: ifeq +12 -> 16
/*     */     //   7: aload_0
/*     */     //   8: getfield 2	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */     //   11: aload_1
/*     */     //   12: invokevirtual 12	java/util/HashMap:containsValue	(Ljava/lang/Object;)Z
/*     */     //   15: ireturn
/*     */     //   16: aload_0
/*     */     //   17: getfield 2	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */     //   20: dup
/*     */     //   21: astore_2
/*     */     //   22: monitorenter
/*     */     //   23: aload_0
/*     */     //   24: getfield 2	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */     //   27: aload_1
/*     */     //   28: invokevirtual 12	java/util/HashMap:containsValue	(Ljava/lang/Object;)Z
/*     */     //   31: aload_2
/*     */     //   32: monitorexit
/*     */     //   33: ireturn
/*     */     //   34: astore_3
/*     */     //   35: aload_2
/*     */     //   36: monitorexit
/*     */     //   37: aload_3
/*     */     //   38: athrow
/*     */     // Line number table:
/*     */     //   Java source line #216	-> byte code offset #0
/*     */     //   Java source line #217	-> byte code offset #7
/*     */     //   Java source line #219	-> byte code offset #16
/*     */     //   Java source line #220	-> byte code offset #23
/*     */     //   Java source line #221	-> byte code offset #34
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	39	0	this	FastHashMap<K, V>
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
/*     */   public V put(K key, V value)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 3	org/apache/commons/collections15/map/FastHashMap:fast	Z
/*     */     //   4: ifeq +46 -> 50
/*     */     //   7: aload_0
/*     */     //   8: dup
/*     */     //   9: astore_3
/*     */     //   10: monitorenter
/*     */     //   11: aload_0
/*     */     //   12: getfield 2	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */     //   15: invokevirtual 13	java/util/HashMap:clone	()Ljava/lang/Object;
/*     */     //   18: checkcast 4	java/util/HashMap
/*     */     //   21: astore 4
/*     */     //   23: aload 4
/*     */     //   25: aload_1
/*     */     //   26: aload_2
/*     */     //   27: invokevirtual 14	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   30: astore 5
/*     */     //   32: aload_0
/*     */     //   33: aload 4
/*     */     //   35: putfield 2	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
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
/*     */     //   51: getfield 2	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */     //   54: dup
/*     */     //   55: astore_3
/*     */     //   56: monitorenter
/*     */     //   57: aload_0
/*     */     //   58: getfield 2	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */     //   61: aload_1
/*     */     //   62: aload_2
/*     */     //   63: invokevirtual 14	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   66: aload_3
/*     */     //   67: monitorexit
/*     */     //   68: areturn
/*     */     //   69: astore 7
/*     */     //   71: aload_3
/*     */     //   72: monitorexit
/*     */     //   73: aload 7
/*     */     //   75: athrow
/*     */     // Line number table:
/*     */     //   Java source line #241	-> byte code offset #0
/*     */     //   Java source line #242	-> byte code offset #7
/*     */     //   Java source line #243	-> byte code offset #11
/*     */     //   Java source line #244	-> byte code offset #23
/*     */     //   Java source line #245	-> byte code offset #32
/*     */     //   Java source line #246	-> byte code offset #38
/*     */     //   Java source line #247	-> byte code offset #43
/*     */     //   Java source line #249	-> byte code offset #50
/*     */     //   Java source line #250	-> byte code offset #57
/*     */     //   Java source line #251	-> byte code offset #69
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	76	0	this	FastHashMap<K, V>
/*     */     //   0	76	1	key	K
/*     */     //   0	76	2	value	V
/*     */     //   9	37	3	Ljava/lang/Object;	Object
/*     */     //   55	17	3	Ljava/lang/Object;	Object
/*     */     //   21	13	4	temp	HashMap<K, V>
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
/* 262 */     if (this.fast) {
/* 263 */       synchronized (this) {
/* 264 */         HashMap<K, V> temp = (HashMap)this.map.clone();
/* 265 */         temp.putAll(in);
/* 266 */         this.map = temp;
/*     */       }
/*     */     } else {
/* 269 */       synchronized (this.map) {
/* 270 */         this.map.putAll(in);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public V remove(Object key)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 3	org/apache/commons/collections15/map/FastHashMap:fast	Z
/*     */     //   4: ifeq +42 -> 46
/*     */     //   7: aload_0
/*     */     //   8: dup
/*     */     //   9: astore_2
/*     */     //   10: monitorenter
/*     */     //   11: aload_0
/*     */     //   12: getfield 2	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */     //   15: invokevirtual 13	java/util/HashMap:clone	()Ljava/lang/Object;
/*     */     //   18: checkcast 4	java/util/HashMap
/*     */     //   21: astore_3
/*     */     //   22: aload_3
/*     */     //   23: aload_1
/*     */     //   24: invokevirtual 16	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   27: astore 4
/*     */     //   29: aload_0
/*     */     //   30: aload_3
/*     */     //   31: putfield 2	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
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
/*     */     //   47: getfield 2	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */     //   50: dup
/*     */     //   51: astore_2
/*     */     //   52: monitorenter
/*     */     //   53: aload_0
/*     */     //   54: getfield 2	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */     //   57: aload_1
/*     */     //   58: invokevirtual 16	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   61: aload_2
/*     */     //   62: monitorexit
/*     */     //   63: areturn
/*     */     //   64: astore 6
/*     */     //   66: aload_2
/*     */     //   67: monitorexit
/*     */     //   68: aload 6
/*     */     //   70: athrow
/*     */     // Line number table:
/*     */     //   Java source line #283	-> byte code offset #0
/*     */     //   Java source line #284	-> byte code offset #7
/*     */     //   Java source line #285	-> byte code offset #11
/*     */     //   Java source line #286	-> byte code offset #22
/*     */     //   Java source line #287	-> byte code offset #29
/*     */     //   Java source line #288	-> byte code offset #34
/*     */     //   Java source line #289	-> byte code offset #39
/*     */     //   Java source line #291	-> byte code offset #46
/*     */     //   Java source line #292	-> byte code offset #53
/*     */     //   Java source line #293	-> byte code offset #64
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	71	0	this	FastHashMap<K, V>
/*     */     //   0	71	1	key	Object
/*     */     //   9	33	2	Ljava/lang/Object;	Object
/*     */     //   51	16	2	Ljava/lang/Object;	Object
/*     */     //   21	10	3	temp	HashMap<K, V>
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
/* 301 */     if (this.fast) {
/* 302 */       synchronized (this) {
/* 303 */         this.map = new HashMap();
/*     */       }
/*     */     } else {
/* 306 */       synchronized (this.map) {
/* 307 */         this.map.clear();
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
/*     */   public boolean equals(Object o)
/*     */   {
/* 326 */     if (o == this)
/* 327 */       return true;
/* 328 */     if (!(o instanceof Map)) {
/* 329 */       return false;
/*     */     }
/* 331 */     Map mo = (Map)o;
/*     */     
/*     */ 
/* 334 */     if (this.fast) {
/* 335 */       if (mo.size() != this.map.size()) {
/* 336 */         return false;
/*     */       }
/* 338 */       Iterator i = this.map.entrySet().iterator();
/* 339 */       while (i.hasNext()) {
/* 340 */         Map.Entry e = (Map.Entry)i.next();
/* 341 */         Object key = e.getKey();
/* 342 */         Object value = e.getValue();
/* 343 */         if (value == null) {
/* 344 */           if ((mo.get(key) != null) || (!mo.containsKey(key))) {
/* 345 */             return false;
/*     */           }
/*     */         }
/* 348 */         else if (!value.equals(mo.get(key))) {
/* 349 */           return false;
/*     */         }
/*     */       }
/*     */       
/* 353 */       return true;
/*     */     }
/*     */     
/* 356 */     synchronized (this.map) {
/* 357 */       if (mo.size() != this.map.size()) {
/* 358 */         return false;
/*     */       }
/* 360 */       Iterator i = this.map.entrySet().iterator();
/* 361 */       while (i.hasNext()) {
/* 362 */         Map.Entry e = (Map.Entry)i.next();
/* 363 */         Object key = e.getKey();
/* 364 */         Object value = e.getValue();
/* 365 */         if (value == null) {
/* 366 */           if ((mo.get(key) != null) || (!mo.containsKey(key))) {
/* 367 */             return false;
/*     */           }
/*     */         }
/* 370 */         else if (!value.equals(mo.get(key))) {
/* 371 */           return false;
/*     */         }
/*     */       }
/*     */       
/* 375 */       return true;
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
/* 388 */     if (this.fast) {
/* 389 */       int h = 0;
/* 390 */       Iterator<Map.Entry<K, V>> i = this.map.entrySet().iterator();
/* 391 */       while (i.hasNext()) {
/* 392 */         h += ((Map.Entry)i.next()).hashCode();
/*     */       }
/* 394 */       return h;
/*     */     }
/* 396 */     synchronized (this.map) {
/* 397 */       int h = 0;
/* 398 */       Iterator<Map.Entry<K, V>> i = this.map.entrySet().iterator();
/* 399 */       while (i.hasNext()) {
/* 400 */         h += ((Map.Entry)i.next()).hashCode();
/*     */       }
/* 402 */       return h;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FastHashMap<K, V> clone()
/*     */   {
/* 414 */     FastHashMap<K, V> results = null;
/* 415 */     if (this.fast) {
/* 416 */       results = new FastHashMap(this.map);
/*     */     } else {
/* 418 */       synchronized (this.map) {
/* 419 */         results = new FastHashMap(this.map);
/*     */       }
/*     */     }
/* 422 */     results.setFast(getFast());
/* 423 */     return results;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<Map.Entry<K, V>> entrySet()
/*     */   {
/* 434 */     return new EntrySet(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Set<K> keySet()
/*     */   {
/* 441 */     return new KeySet(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<V> values()
/*     */   {
/* 448 */     return new Values(null);
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
/* 468 */       if (FastHashMap.this.fast) {
/* 469 */         synchronized (FastHashMap.this) {
/* 470 */           FastHashMap.this.map = new HashMap();
/*     */         }
/*     */       } else {
/* 473 */         synchronized (FastHashMap.this.map) {
/* 474 */           get(FastHashMap.this.map).clear();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     /* Error */
/*     */     public boolean remove(Object o)
/*     */     {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   4: getfield 3	org/apache/commons/collections15/map/FastHashMap:fast	Z
/*     */       //   7: ifeq +57 -> 64
/*     */       //   10: aload_0
/*     */       //   11: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   14: dup
/*     */       //   15: astore_2
/*     */       //   16: monitorenter
/*     */       //   17: aload_0
/*     */       //   18: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   21: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   24: invokevirtual 9	java/util/HashMap:clone	()Ljava/lang/Object;
/*     */       //   27: checkcast 4	java/util/HashMap
/*     */       //   30: astore_3
/*     */       //   31: aload_0
/*     */       //   32: aload_3
/*     */       //   33: invokevirtual 7	org/apache/commons/collections15/map/FastHashMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   36: aload_1
/*     */       //   37: invokeinterface 10 2 0
/*     */       //   42: istore 4
/*     */       //   44: aload_0
/*     */       //   45: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   48: aload_3
/*     */       //   49: putfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
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
/*     */       //   65: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   68: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   71: dup
/*     */       //   72: astore_2
/*     */       //   73: monitorenter
/*     */       //   74: aload_0
/*     */       //   75: aload_0
/*     */       //   76: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   79: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   82: invokevirtual 7	org/apache/commons/collections15/map/FastHashMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
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
/*     */       //   Java source line #480	-> byte code offset #0
/*     */       //   Java source line #481	-> byte code offset #10
/*     */       //   Java source line #482	-> byte code offset #17
/*     */       //   Java source line #483	-> byte code offset #31
/*     */       //   Java source line #484	-> byte code offset #44
/*     */       //   Java source line #485	-> byte code offset #52
/*     */       //   Java source line #486	-> byte code offset #57
/*     */       //   Java source line #488	-> byte code offset #64
/*     */       //   Java source line #489	-> byte code offset #74
/*     */       //   Java source line #490	-> byte code offset #94
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	101	0	this	FastHashMap<K, V>.CollectionView<E>
/*     */       //   0	101	1	o	Object
/*     */       //   15	45	2	Ljava/lang/Object;	Object
/*     */       //   72	25	2	Ljava/lang/Object;	Object
/*     */       //   30	19	3	temp	HashMap
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
/*     */       //   1: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   4: getfield 3	org/apache/commons/collections15/map/FastHashMap:fast	Z
/*     */       //   7: ifeq +57 -> 64
/*     */       //   10: aload_0
/*     */       //   11: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   14: dup
/*     */       //   15: astore_2
/*     */       //   16: monitorenter
/*     */       //   17: aload_0
/*     */       //   18: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   21: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   24: invokevirtual 9	java/util/HashMap:clone	()Ljava/lang/Object;
/*     */       //   27: checkcast 4	java/util/HashMap
/*     */       //   30: astore_3
/*     */       //   31: aload_0
/*     */       //   32: aload_3
/*     */       //   33: invokevirtual 7	org/apache/commons/collections15/map/FastHashMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   36: aload_1
/*     */       //   37: invokeinterface 11 2 0
/*     */       //   42: istore 4
/*     */       //   44: aload_0
/*     */       //   45: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   48: aload_3
/*     */       //   49: putfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
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
/*     */       //   65: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   68: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   71: dup
/*     */       //   72: astore_2
/*     */       //   73: monitorenter
/*     */       //   74: aload_0
/*     */       //   75: aload_0
/*     */       //   76: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   79: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   82: invokevirtual 7	org/apache/commons/collections15/map/FastHashMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
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
/*     */       //   Java source line #495	-> byte code offset #0
/*     */       //   Java source line #496	-> byte code offset #10
/*     */       //   Java source line #497	-> byte code offset #17
/*     */       //   Java source line #498	-> byte code offset #31
/*     */       //   Java source line #499	-> byte code offset #44
/*     */       //   Java source line #500	-> byte code offset #52
/*     */       //   Java source line #501	-> byte code offset #57
/*     */       //   Java source line #503	-> byte code offset #64
/*     */       //   Java source line #504	-> byte code offset #74
/*     */       //   Java source line #505	-> byte code offset #94
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	101	0	this	FastHashMap<K, V>.CollectionView<E>
/*     */       //   0	101	1	o	Collection<?>
/*     */       //   15	45	2	Ljava/lang/Object;	Object
/*     */       //   72	25	2	Ljava/lang/Object;	Object
/*     */       //   30	19	3	temp	HashMap
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
/*     */       //   1: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   4: getfield 3	org/apache/commons/collections15/map/FastHashMap:fast	Z
/*     */       //   7: ifeq +57 -> 64
/*     */       //   10: aload_0
/*     */       //   11: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   14: dup
/*     */       //   15: astore_2
/*     */       //   16: monitorenter
/*     */       //   17: aload_0
/*     */       //   18: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   21: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   24: invokevirtual 9	java/util/HashMap:clone	()Ljava/lang/Object;
/*     */       //   27: checkcast 4	java/util/HashMap
/*     */       //   30: astore_3
/*     */       //   31: aload_0
/*     */       //   32: aload_3
/*     */       //   33: invokevirtual 7	org/apache/commons/collections15/map/FastHashMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   36: aload_1
/*     */       //   37: invokeinterface 12 2 0
/*     */       //   42: istore 4
/*     */       //   44: aload_0
/*     */       //   45: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   48: aload_3
/*     */       //   49: putfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
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
/*     */       //   65: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   68: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   71: dup
/*     */       //   72: astore_2
/*     */       //   73: monitorenter
/*     */       //   74: aload_0
/*     */       //   75: aload_0
/*     */       //   76: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   79: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   82: invokevirtual 7	org/apache/commons/collections15/map/FastHashMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
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
/*     */       //   Java source line #510	-> byte code offset #0
/*     */       //   Java source line #511	-> byte code offset #10
/*     */       //   Java source line #512	-> byte code offset #17
/*     */       //   Java source line #513	-> byte code offset #31
/*     */       //   Java source line #514	-> byte code offset #44
/*     */       //   Java source line #515	-> byte code offset #52
/*     */       //   Java source line #516	-> byte code offset #57
/*     */       //   Java source line #518	-> byte code offset #64
/*     */       //   Java source line #519	-> byte code offset #74
/*     */       //   Java source line #520	-> byte code offset #94
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	101	0	this	FastHashMap<K, V>.CollectionView<E>
/*     */       //   0	101	1	o	Collection<?>
/*     */       //   15	45	2	Ljava/lang/Object;	Object
/*     */       //   72	25	2	Ljava/lang/Object;	Object
/*     */       //   30	19	3	temp	HashMap
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
/*     */       //   1: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   4: getfield 3	org/apache/commons/collections15/map/FastHashMap:fast	Z
/*     */       //   7: ifeq +20 -> 27
/*     */       //   10: aload_0
/*     */       //   11: aload_0
/*     */       //   12: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   15: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   18: invokevirtual 7	org/apache/commons/collections15/map/FastHashMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   21: invokeinterface 13 1 0
/*     */       //   26: ireturn
/*     */       //   27: aload_0
/*     */       //   28: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   31: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   34: dup
/*     */       //   35: astore_1
/*     */       //   36: monitorenter
/*     */       //   37: aload_0
/*     */       //   38: aload_0
/*     */       //   39: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   42: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   45: invokevirtual 7	org/apache/commons/collections15/map/FastHashMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
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
/*     */       //   Java source line #525	-> byte code offset #0
/*     */       //   Java source line #526	-> byte code offset #10
/*     */       //   Java source line #528	-> byte code offset #27
/*     */       //   Java source line #529	-> byte code offset #37
/*     */       //   Java source line #530	-> byte code offset #56
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	61	0	this	FastHashMap<K, V>.CollectionView<E>
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
/*     */       //   1: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   4: getfield 3	org/apache/commons/collections15/map/FastHashMap:fast	Z
/*     */       //   7: ifeq +20 -> 27
/*     */       //   10: aload_0
/*     */       //   11: aload_0
/*     */       //   12: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   15: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   18: invokevirtual 7	org/apache/commons/collections15/map/FastHashMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   21: invokeinterface 14 1 0
/*     */       //   26: ireturn
/*     */       //   27: aload_0
/*     */       //   28: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   31: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   34: dup
/*     */       //   35: astore_1
/*     */       //   36: monitorenter
/*     */       //   37: aload_0
/*     */       //   38: aload_0
/*     */       //   39: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   42: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   45: invokevirtual 7	org/apache/commons/collections15/map/FastHashMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
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
/*     */       //   Java source line #536	-> byte code offset #0
/*     */       //   Java source line #537	-> byte code offset #10
/*     */       //   Java source line #539	-> byte code offset #27
/*     */       //   Java source line #540	-> byte code offset #37
/*     */       //   Java source line #541	-> byte code offset #56
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	61	0	this	FastHashMap<K, V>.CollectionView<E>
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
/*     */       //   1: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   4: getfield 3	org/apache/commons/collections15/map/FastHashMap:fast	Z
/*     */       //   7: ifeq +21 -> 28
/*     */       //   10: aload_0
/*     */       //   11: aload_0
/*     */       //   12: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   15: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   18: invokevirtual 7	org/apache/commons/collections15/map/FastHashMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   21: aload_1
/*     */       //   22: invokeinterface 15 2 0
/*     */       //   27: ireturn
/*     */       //   28: aload_0
/*     */       //   29: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   32: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   35: dup
/*     */       //   36: astore_2
/*     */       //   37: monitorenter
/*     */       //   38: aload_0
/*     */       //   39: aload_0
/*     */       //   40: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   43: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   46: invokevirtual 7	org/apache/commons/collections15/map/FastHashMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
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
/*     */       //   Java source line #546	-> byte code offset #0
/*     */       //   Java source line #547	-> byte code offset #10
/*     */       //   Java source line #549	-> byte code offset #28
/*     */       //   Java source line #550	-> byte code offset #38
/*     */       //   Java source line #551	-> byte code offset #58
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	63	0	this	FastHashMap<K, V>.CollectionView<E>
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
/*     */       //   1: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   4: getfield 3	org/apache/commons/collections15/map/FastHashMap:fast	Z
/*     */       //   7: ifeq +21 -> 28
/*     */       //   10: aload_0
/*     */       //   11: aload_0
/*     */       //   12: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   15: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   18: invokevirtual 7	org/apache/commons/collections15/map/FastHashMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   21: aload_1
/*     */       //   22: invokeinterface 16 2 0
/*     */       //   27: ireturn
/*     */       //   28: aload_0
/*     */       //   29: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   32: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   35: dup
/*     */       //   36: astore_2
/*     */       //   37: monitorenter
/*     */       //   38: aload_0
/*     */       //   39: aload_0
/*     */       //   40: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   43: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   46: invokevirtual 7	org/apache/commons/collections15/map/FastHashMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
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
/*     */       //   Java source line #556	-> byte code offset #0
/*     */       //   Java source line #557	-> byte code offset #10
/*     */       //   Java source line #559	-> byte code offset #28
/*     */       //   Java source line #560	-> byte code offset #38
/*     */       //   Java source line #561	-> byte code offset #58
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	63	0	this	FastHashMap<K, V>.CollectionView<E>
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
/*     */       //   1: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   4: getfield 3	org/apache/commons/collections15/map/FastHashMap:fast	Z
/*     */       //   7: ifeq +21 -> 28
/*     */       //   10: aload_0
/*     */       //   11: aload_0
/*     */       //   12: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   15: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   18: invokevirtual 7	org/apache/commons/collections15/map/FastHashMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   21: aload_1
/*     */       //   22: invokeinterface 17 2 0
/*     */       //   27: areturn
/*     */       //   28: aload_0
/*     */       //   29: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   32: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   35: dup
/*     */       //   36: astore_2
/*     */       //   37: monitorenter
/*     */       //   38: aload_0
/*     */       //   39: aload_0
/*     */       //   40: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   43: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   46: invokevirtual 7	org/apache/commons/collections15/map/FastHashMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
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
/*     */       //   Java source line #566	-> byte code offset #0
/*     */       //   Java source line #567	-> byte code offset #10
/*     */       //   Java source line #569	-> byte code offset #28
/*     */       //   Java source line #570	-> byte code offset #38
/*     */       //   Java source line #571	-> byte code offset #58
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	63	0	this	FastHashMap<K, V>.CollectionView<E>
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
/*     */       //   1: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   4: getfield 3	org/apache/commons/collections15/map/FastHashMap:fast	Z
/*     */       //   7: ifeq +20 -> 27
/*     */       //   10: aload_0
/*     */       //   11: aload_0
/*     */       //   12: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   15: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   18: invokevirtual 7	org/apache/commons/collections15/map/FastHashMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   21: invokeinterface 18 1 0
/*     */       //   26: areturn
/*     */       //   27: aload_0
/*     */       //   28: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   31: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   34: dup
/*     */       //   35: astore_1
/*     */       //   36: monitorenter
/*     */       //   37: aload_0
/*     */       //   38: aload_0
/*     */       //   39: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   42: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   45: invokevirtual 7	org/apache/commons/collections15/map/FastHashMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
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
/*     */       //   Java source line #576	-> byte code offset #0
/*     */       //   Java source line #577	-> byte code offset #10
/*     */       //   Java source line #579	-> byte code offset #27
/*     */       //   Java source line #580	-> byte code offset #37
/*     */       //   Java source line #581	-> byte code offset #56
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	61	0	this	FastHashMap<K, V>.CollectionView<E>
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
/*     */       //   8: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   11: getfield 3	org/apache/commons/collections15/map/FastHashMap:fast	Z
/*     */       //   14: ifeq +19 -> 33
/*     */       //   17: aload_0
/*     */       //   18: aload_0
/*     */       //   19: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   22: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   25: invokevirtual 7	org/apache/commons/collections15/map/FastHashMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   28: aload_1
/*     */       //   29: invokevirtual 19	java/lang/Object:equals	(Ljava/lang/Object;)Z
/*     */       //   32: ireturn
/*     */       //   33: aload_0
/*     */       //   34: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   37: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   40: dup
/*     */       //   41: astore_2
/*     */       //   42: monitorenter
/*     */       //   43: aload_0
/*     */       //   44: aload_0
/*     */       //   45: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   48: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   51: invokevirtual 7	org/apache/commons/collections15/map/FastHashMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
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
/*     */       //   Java source line #587	-> byte code offset #0
/*     */       //   Java source line #588	-> byte code offset #7
/*     */       //   Java source line #589	-> byte code offset #17
/*     */       //   Java source line #591	-> byte code offset #33
/*     */       //   Java source line #592	-> byte code offset #43
/*     */       //   Java source line #593	-> byte code offset #61
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	66	0	this	FastHashMap<K, V>.CollectionView<E>
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
/*     */       //   1: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   4: getfield 3	org/apache/commons/collections15/map/FastHashMap:fast	Z
/*     */       //   7: ifeq +18 -> 25
/*     */       //   10: aload_0
/*     */       //   11: aload_0
/*     */       //   12: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   15: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   18: invokevirtual 7	org/apache/commons/collections15/map/FastHashMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
/*     */       //   21: invokevirtual 20	java/lang/Object:hashCode	()I
/*     */       //   24: ireturn
/*     */       //   25: aload_0
/*     */       //   26: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   29: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   32: dup
/*     */       //   33: astore_1
/*     */       //   34: monitorenter
/*     */       //   35: aload_0
/*     */       //   36: aload_0
/*     */       //   37: getfield 1	org/apache/commons/collections15/map/FastHashMap$CollectionView:this$0	Lorg/apache/commons/collections15/map/FastHashMap;
/*     */       //   40: getfield 6	org/apache/commons/collections15/map/FastHashMap:map	Ljava/util/HashMap;
/*     */       //   43: invokevirtual 7	org/apache/commons/collections15/map/FastHashMap$CollectionView:get	(Ljava/util/Map;)Ljava/util/Collection;
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
/*     */       //   Java source line #598	-> byte code offset #0
/*     */       //   Java source line #599	-> byte code offset #10
/*     */       //   Java source line #601	-> byte code offset #25
/*     */       //   Java source line #602	-> byte code offset #35
/*     */       //   Java source line #603	-> byte code offset #52
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	57	0	this	FastHashMap<K, V>.CollectionView<E>
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
/* 608 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public boolean addAll(Collection<? extends E> c) {
/* 612 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public Iterator<E> iterator() {
/* 616 */       return new CollectionViewIterator();
/*     */     }
/*     */     
/*     */     private class CollectionViewIterator implements Iterator<E>
/*     */     {
/*     */       private Map expected;
/* 622 */       private Map.Entry lastReturned = null;
/*     */       private Iterator iterator;
/*     */       
/*     */       public CollectionViewIterator() {
/* 626 */         this.expected = FastHashMap.this.map;
/* 627 */         this.iterator = this.expected.entrySet().iterator();
/*     */       }
/*     */       
/*     */       public boolean hasNext() {
/* 631 */         if (this.expected != FastHashMap.this.map) {
/* 632 */           throw new ConcurrentModificationException();
/*     */         }
/* 634 */         return this.iterator.hasNext();
/*     */       }
/*     */       
/*     */       public E next() {
/* 638 */         if (this.expected != FastHashMap.this.map) {
/* 639 */           throw new ConcurrentModificationException();
/*     */         }
/* 641 */         this.lastReturned = ((Map.Entry)this.iterator.next());
/* 642 */         return (E)FastHashMap.CollectionView.this.iteratorNext(this.lastReturned);
/*     */       }
/*     */       
/*     */       public void remove() {
/* 646 */         if (this.lastReturned == null) {
/* 647 */           throw new IllegalStateException();
/*     */         }
/* 649 */         if (FastHashMap.this.fast) {
/* 650 */           synchronized (FastHashMap.this) {
/* 651 */             if (this.expected != FastHashMap.this.map) {
/* 652 */               throw new ConcurrentModificationException();
/*     */             }
/* 654 */             FastHashMap.this.remove(this.lastReturned.getKey());
/* 655 */             this.lastReturned = null;
/* 656 */             this.expected = FastHashMap.this.map;
/*     */           }
/*     */         } else {
/* 659 */           this.iterator.remove();
/* 660 */           this.lastReturned = null;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private class KeySet extends FastHashMap<K, V>.CollectionView<K> implements Set<K>
/*     */   {
/*     */     private KeySet() {
/* 669 */       super();
/*     */     }
/*     */     
/* 672 */     protected Collection get(Map<K, V> map) { return map.keySet(); }
/*     */     
/*     */     protected K iteratorNext(Map.Entry<K, V> entry)
/*     */     {
/* 676 */       return (K)entry.getKey();
/*     */     }
/*     */   }
/*     */   
/*     */   private class Values
/*     */     extends FastHashMap<K, V>.CollectionView<V>
/*     */   {
/*     */     private Values()
/*     */     {
/* 685 */       super();
/*     */     }
/*     */     
/* 688 */     protected Collection get(Map<K, V> map) { return map.values(); }
/*     */     
/*     */     protected V iteratorNext(Map.Entry<K, V> entry)
/*     */     {
/* 692 */       return (V)entry.getValue();
/*     */     }
/*     */   }
/*     */   
/*     */   private class EntrySet extends FastHashMap<K, V>.CollectionView<Map.Entry<K, V>> implements Set<Map.Entry<K, V>>
/*     */   {
/*     */     private EntrySet() {
/* 699 */       super();
/*     */     }
/*     */     
/* 702 */     protected Collection get(Map<K, V> map) { return map.entrySet(); }
/*     */     
/*     */     protected Map.Entry<K, V> iteratorNext(Map.Entry<K, V> entry)
/*     */     {
/* 706 */       return entry;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/FastHashMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */