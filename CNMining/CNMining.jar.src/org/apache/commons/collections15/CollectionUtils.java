/*      */ package org.apache.commons.collections15;
/*      */ 
/*      */ import java.lang.reflect.Array;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.collections15.collection.PredicatedCollection;
/*      */ import org.apache.commons.collections15.collection.SynchronizedCollection;
/*      */ import org.apache.commons.collections15.collection.TransformedCollection;
/*      */ import org.apache.commons.collections15.collection.TypedCollection;
/*      */ import org.apache.commons.collections15.collection.UnmodifiableBoundedCollection;
/*      */ import org.apache.commons.collections15.collection.UnmodifiableCollection;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CollectionUtils
/*      */ {
/*   46 */   private static Integer INTEGER_ONE = new Integer(1);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   54 */   public static final Collection EMPTY_COLLECTION = UnmodifiableCollection.decorate(new ArrayList());
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <E> Collection<E> union(Collection<? extends E> a, Collection<? extends E> b)
/*      */   {
/*   76 */     ArrayList<E> list = new ArrayList();
/*   77 */     Map mapa = getCardinalityMap(a);
/*   78 */     Map mapb = getCardinalityMap(b);
/*   79 */     Set<E> elts = new HashSet(a);
/*   80 */     elts.addAll(b);
/*   81 */     Iterator<E> it = elts.iterator();
/*   82 */     while (it.hasNext()) {
/*   83 */       E obj = it.next();
/*   84 */       int i = 0; for (int m = Math.max(getFreq(obj, mapa), getFreq(obj, mapb)); i < m; i++) {
/*   85 */         list.add(obj);
/*      */       }
/*      */     }
/*   88 */     return list;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <E> Collection<E> intersection(Collection<? extends E> a, Collection<? extends E> b)
/*      */   {
/*  106 */     ArrayList<E> list = new ArrayList();
/*  107 */     Map mapa = getCardinalityMap(a);
/*  108 */     Map mapb = getCardinalityMap(b);
/*  109 */     Set<E> elts = new HashSet(a);
/*  110 */     elts.addAll(b);
/*  111 */     Iterator<E> it = elts.iterator();
/*  112 */     while (it.hasNext()) {
/*  113 */       E obj = it.next();
/*  114 */       int i = 0; for (int m = Math.min(getFreq(obj, mapa), getFreq(obj, mapb)); i < m; i++) {
/*  115 */         list.add(obj);
/*      */       }
/*      */     }
/*  118 */     return list;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <E> Collection<E> disjunction(Collection<E> a, Collection<E> b)
/*      */   {
/*  139 */     ArrayList<E> list = new ArrayList();
/*  140 */     Map mapa = getCardinalityMap(a);
/*  141 */     Map mapb = getCardinalityMap(b);
/*  142 */     Set<E> elts = new HashSet(a);
/*  143 */     elts.addAll(b);
/*  144 */     Iterator<E> it = elts.iterator();
/*  145 */     while (it.hasNext()) {
/*  146 */       E obj = it.next();
/*  147 */       int i = 0; for (int m = Math.max(getFreq(obj, mapa), getFreq(obj, mapb)) - Math.min(getFreq(obj, mapa), getFreq(obj, mapb)); i < m; i++) {
/*  148 */         list.add(obj);
/*      */       }
/*      */     }
/*  151 */     return list;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <E> Collection<E> subtract(Collection<? extends E> a, Iterable<? extends E> b)
/*      */   {
/*  166 */     ArrayList<E> list = new ArrayList(a);
/*  167 */     for (E e : b) {
/*  168 */       list.remove(e);
/*      */     }
/*  170 */     return list;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <E> boolean containsAny(Collection<? extends E> coll1, Collection<? extends E> coll2)
/*      */   {
/*      */     Iterator it;
/*      */     
/*      */ 
/*      */ 
/*      */     Iterator it;
/*      */     
/*      */ 
/*      */ 
/*  186 */     if (coll1.size() < coll2.size()) {
/*  187 */       for (it = coll1.iterator(); it.hasNext();) {
/*  188 */         if (coll2.contains(it.next())) {
/*  189 */           return true;
/*      */         }
/*      */       }
/*      */     } else {
/*  193 */       for (it = coll2.iterator(); it.hasNext();) {
/*  194 */         if (coll1.contains(it.next())) {
/*  195 */           return true;
/*      */         }
/*      */       }
/*      */     }
/*  199 */     return false;
/*      */   }
/*      */   
/*      */   public static void main(String[] args) {
/*  203 */     List<String> l1 = new ArrayList();
/*  204 */     l1.add("Test");
/*  205 */     List<Integer> l2 = new ArrayList();
/*  206 */     l2.add(Integer.valueOf(1));
/*  207 */     containsAny(l1, l2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <E> Map<E, Integer> getCardinalityMap(Iterable<E> iterable)
/*      */   {
/*  222 */     Map<E, Integer> count = new HashMap();
/*  223 */     for (Iterator<E> it = iterable.iterator(); it.hasNext();) {
/*  224 */       E obj = it.next();
/*  225 */       Integer c = (Integer)count.get(obj);
/*  226 */       if (c == null) {
/*  227 */         count.put(obj, INTEGER_ONE);
/*      */       } else {
/*  229 */         count.put(obj, new Integer(c.intValue() + 1));
/*      */       }
/*      */     }
/*  232 */     return count;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <E> boolean isSubCollection(Iterable<? extends E> a, Iterable<? extends E> b)
/*      */   {
/*  248 */     Map mapa = getCardinalityMap(a);
/*  249 */     Map mapb = getCardinalityMap(b);
/*  250 */     for (E obj : a) {
/*  251 */       if (getFreq(obj, mapa) > getFreq(obj, mapb)) {
/*  252 */         return false;
/*      */       }
/*      */     }
/*  255 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <E> boolean isProperSubCollection(Collection<? extends E> a, Collection<? extends E> b)
/*      */   {
/*  280 */     return (a.size() < b.size()) && (isSubCollection(a, b));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <E> boolean isEqualCollection(Collection<? extends E> a, Collection<? extends E> b)
/*      */   {
/*  296 */     if (a.size() != b.size()) {
/*  297 */       return false;
/*      */     }
/*  299 */     Map mapa = getCardinalityMap(a);
/*  300 */     Map mapb = getCardinalityMap(b);
/*  301 */     if (mapa.size() != mapb.size()) {
/*  302 */       return false;
/*      */     }
/*  304 */     Iterator it = mapa.keySet().iterator();
/*  305 */     while (it.hasNext()) {
/*  306 */       Object obj = it.next();
/*  307 */       if (getFreq(obj, mapa) != getFreq(obj, mapb)) {
/*  308 */         return false;
/*      */       }
/*      */     }
/*  311 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <E> int cardinality(E obj, Collection<? super E> coll)
/*      */   {
/*  324 */     if ((coll instanceof Set)) {
/*  325 */       return coll.contains(obj) ? 1 : 0;
/*      */     }
/*  327 */     if ((coll instanceof Bag)) {
/*  328 */       return ((Bag)coll).getCount(obj);
/*      */     }
/*  330 */     int count = 0;
/*  331 */     Iterator it; Iterator it; if (obj == null) {
/*  332 */       for (it = coll.iterator(); it.hasNext();) {
/*  333 */         if (it.next() == null) {
/*  334 */           count++;
/*      */         }
/*      */       }
/*      */     } else {
/*  338 */       for (it = coll.iterator(); it.hasNext();) {
/*  339 */         if (obj.equals(it.next())) {
/*  340 */           count++;
/*      */         }
/*      */       }
/*      */     }
/*  344 */     return count;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <E> E find(Iterable<E> iterable, Predicate<? super E> predicate)
/*      */   {
/*      */     Iterator<E> iter;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  358 */     if ((iterable != null) && (predicate != null)) {
/*  359 */       for (iter = iterable.iterator(); iter.hasNext();) {
/*  360 */         E item = iter.next();
/*  361 */         if (predicate.evaluate(item)) {
/*  362 */           return item;
/*      */         }
/*      */       }
/*      */     }
/*  366 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <E> void forAllDo(Iterable<E> iterable, Closure<? super E> closure)
/*      */   {
/*      */     Iterator<E> it;
/*      */     
/*      */ 
/*      */ 
/*  378 */     if ((iterable != null) && (closure != null)) {
/*  379 */       for (it = iterable.iterator(); it.hasNext();) {
/*  380 */         closure.execute(it.next());
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <E> void filter(Iterable<E> iterable, Predicate<? super E> predicate)
/*      */   {
/*      */     Iterator<E> it;
/*      */     
/*      */ 
/*      */ 
/*  395 */     if ((iterable != null) && (predicate != null)) {
/*  396 */       for (it = iterable.iterator(); it.hasNext();) {
/*  397 */         if (!predicate.evaluate(it.next())) {
/*  398 */           it.remove();
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <E> void transform(Collection<E> collection, Transformer<? super E, ? extends E> transformer)
/*      */   {
/*  421 */     if ((collection != null) && (transformer != null)) { ListIterator<E> it;
/*  422 */       if ((collection instanceof List)) {
/*  423 */         List<E> list = (List)collection;
/*  424 */         for (it = list.listIterator(); it.hasNext();) {
/*  425 */           it.set(transformer.transform(it.next()));
/*      */         }
/*      */       } else {
/*  428 */         Collection<E> resultCollection = collect(collection, transformer);
/*  429 */         collection.clear();
/*  430 */         collection.addAll(resultCollection);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <E> int countMatches(Iterable<E> inputIterable, Predicate<? super E> predicate)
/*      */   {
/*  445 */     int count = 0;
/*  446 */     Iterator<E> it; if ((inputIterable != null) && (predicate != null)) {
/*  447 */       for (it = inputIterable.iterator(); it.hasNext();) {
/*  448 */         if (predicate.evaluate(it.next())) {
/*  449 */           count++;
/*      */         }
/*      */       }
/*      */     }
/*  453 */     return count;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <E> boolean exists(Iterable<E> iterable, Predicate<? super E> predicate)
/*      */   {
/*      */     Iterator<E> it;
/*      */     
/*      */ 
/*      */ 
/*  466 */     if ((iterable != null) && (predicate != null)) {
/*  467 */       for (it = iterable.iterator(); it.hasNext();) {
/*  468 */         if (predicate.evaluate(it.next())) {
/*  469 */           return true;
/*      */         }
/*      */       }
/*      */     }
/*  473 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <E> Collection<E> select(Collection<E> inputCollection, Predicate<? super E> predicate)
/*      */   {
/*  488 */     return select(inputCollection, predicate, new ArrayList(inputCollection.size()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <E, C extends Collection<? super E>> C select(Iterable<E> inputCollection, Predicate<? super E> predicate, C outputCollection)
/*      */   {
/*      */     Iterator<E> iter;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  503 */     if ((inputCollection != null) && (predicate != null)) {
/*  504 */       for (iter = inputCollection.iterator(); iter.hasNext();) {
/*  505 */         E item = iter.next();
/*  506 */         if (predicate.evaluate(item)) {
/*  507 */           outputCollection.add(item);
/*      */         }
/*      */       }
/*      */     }
/*  511 */     return outputCollection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <E> Collection<E> selectRejected(Collection<E> inputCollection, Predicate<? super E> predicate)
/*      */   {
/*  526 */     ArrayList<E> answer = new ArrayList(inputCollection.size());
/*  527 */     selectRejected(inputCollection, predicate, answer);
/*  528 */     return answer;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <E> void selectRejected(Iterable<E> inputIterable, Predicate<? super E> predicate, Collection<? super E> outputCollection)
/*      */   {
/*      */     Iterator<E> iter;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  542 */     if ((inputIterable != null) && (predicate != null)) {
/*  543 */       for (iter = inputIterable.iterator(); iter.hasNext();) {
/*  544 */         E item = iter.next();
/*  545 */         if (!predicate.evaluate(item)) {
/*  546 */           outputCollection.add(item);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <I, O> Collection<O> collect(Collection<I> inputCollection, Transformer<? super I, ? extends O> transformer)
/*      */   {
/*  564 */     ArrayList<O> answer = new ArrayList(inputCollection.size());
/*  565 */     collect(inputCollection, transformer, answer);
/*  566 */     return answer;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <I, O> Collection<O> collect(Iterator<I> inputIterator, Transformer<? super I, ? extends O> transformer)
/*      */   {
/*  580 */     ArrayList<O> answer = new ArrayList();
/*  581 */     collect(inputIterator, transformer, answer);
/*  582 */     return answer;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <I, O, C extends Collection<O>> C collect(Iterable<I> inputCollection, Transformer<? super I, ? extends O> transformer, C outputCollection)
/*      */   {
/*  599 */     if (inputCollection != null) {
/*  600 */       return collect(inputCollection.iterator(), transformer, outputCollection);
/*      */     }
/*  602 */     return outputCollection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <I, O, C extends Collection<O>> C collect(Iterator<I> inputIterator, Transformer<? super I, ? extends O> transformer, C outputCollection)
/*      */   {
/*  619 */     if ((inputIterator != null) && (transformer != null)) {
/*  620 */       while (inputIterator.hasNext()) {
/*  621 */         I item = inputIterator.next();
/*  622 */         O value = transformer.transform(item);
/*  623 */         outputCollection.add(value);
/*      */       }
/*      */     }
/*  626 */     return outputCollection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public static <E> void addAll(Collection<E> collection, Iterator<? extends E> iterator)
/*      */   {
/*  638 */     while (iterator.hasNext()) {
/*  639 */       collection.add(iterator.next());
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public static <E> void addAll(Collection<E> collection, Enumeration<? extends E> enumeration)
/*      */   {
/*  652 */     while (enumeration.hasMoreElements()) {
/*  653 */       collection.add(enumeration.nextElement());
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <E, T extends E> void addAll(Collection<E> collection, T... elements)
/*      */   {
/*  665 */     int i = 0; for (int size = elements.length; i < size; i++) {
/*  666 */       collection.add(elements[i]);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public static Object index(Object obj, int idx)
/*      */   {
/*  692 */     return index(obj, new Integer(idx));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public static Object index(Object obj, Object index)
/*      */   {
/*  718 */     if ((obj instanceof Map)) {
/*  719 */       Map map = (Map)obj;
/*  720 */       if (map.containsKey(index)) {
/*  721 */         return map.get(index);
/*      */       }
/*      */     }
/*  724 */     int idx = -1;
/*  725 */     if ((index instanceof Integer)) {
/*  726 */       idx = ((Integer)index).intValue();
/*      */     }
/*  728 */     if (idx < 0)
/*  729 */       return obj;
/*  730 */     if ((obj instanceof Map)) {
/*  731 */       Map map = (Map)obj;
/*  732 */       Iterator iterator = map.keySet().iterator();
/*  733 */       return index(iterator, idx); }
/*  734 */     if ((obj instanceof List))
/*  735 */       return ((List)obj).get(idx);
/*  736 */     if ((obj instanceof Object[]))
/*  737 */       return ((Object[])(Object[])obj)[idx];
/*  738 */     if ((obj instanceof Enumeration)) {
/*  739 */       Enumeration it = (Enumeration)obj;
/*  740 */       while (it.hasMoreElements()) {
/*  741 */         idx--;
/*  742 */         if (idx == -1) {
/*  743 */           return it.nextElement();
/*      */         }
/*  745 */         it.nextElement();
/*      */       }
/*      */     } else {
/*  748 */       if ((obj instanceof Iterator))
/*  749 */         return index((Iterator)obj, idx);
/*  750 */       if ((obj instanceof Collection)) {
/*  751 */         Iterator iterator = ((Collection)obj).iterator();
/*  752 */         return index(iterator, idx);
/*      */       } }
/*  754 */     return obj;
/*      */   }
/*      */   
/*      */   private static Object index(Iterator iterator, int idx) {
/*  758 */     while (iterator.hasNext()) {
/*  759 */       idx--;
/*  760 */       if (idx == -1) {
/*  761 */         return iterator.next();
/*      */       }
/*  763 */       iterator.next();
/*      */     }
/*      */     
/*  766 */     return iterator;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Object get(Object object, int index)
/*      */   {
/*  800 */     if (index < 0) {
/*  801 */       throw new IndexOutOfBoundsException("Index cannot be negative: " + index);
/*      */     }
/*  803 */     if ((object instanceof Map)) {
/*  804 */       Map map = (Map)object;
/*  805 */       Iterator iterator = map.entrySet().iterator();
/*  806 */       return get(iterator, index); }
/*  807 */     if ((object instanceof List))
/*  808 */       return ((List)object).get(index);
/*  809 */     if ((object instanceof Object[]))
/*  810 */       return ((Object[])(Object[])object)[index];
/*  811 */     if ((object instanceof Iterator)) {
/*  812 */       Iterator it = (Iterator)object;
/*  813 */       while (it.hasNext()) {
/*  814 */         index--;
/*  815 */         if (index == -1) {
/*  816 */           return it.next();
/*      */         }
/*  818 */         it.next();
/*      */       }
/*      */       
/*  821 */       throw new IndexOutOfBoundsException("Entry does not exist: " + index); }
/*  822 */     if ((object instanceof Collection)) {
/*  823 */       Iterator iterator = ((Collection)object).iterator();
/*  824 */       return get(iterator, index); }
/*  825 */     if ((object instanceof Enumeration)) {
/*  826 */       Enumeration it = (Enumeration)object;
/*  827 */       while (it.hasMoreElements()) {
/*  828 */         index--;
/*  829 */         if (index == -1) {
/*  830 */           return it.nextElement();
/*      */         }
/*  832 */         it.nextElement();
/*      */       }
/*      */       
/*  835 */       throw new IndexOutOfBoundsException("Entry does not exist: " + index); }
/*  836 */     if (object == null) {
/*  837 */       throw new IllegalArgumentException("Unsupported object type: null");
/*      */     }
/*      */     try {
/*  840 */       return Array.get(object, index);
/*      */     } catch (IllegalArgumentException ex) {
/*  842 */       throw new IllegalArgumentException("Unsupported object type: " + object.getClass().getName());
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int size(Object object)
/*      */   {
/*  865 */     int total = 0;
/*  866 */     if ((object instanceof Map)) {
/*  867 */       total = ((Map)object).size();
/*  868 */     } else if ((object instanceof Collection)) {
/*  869 */       total = ((Collection)object).size();
/*  870 */     } else if ((object instanceof Object[])) {
/*  871 */       total = ((Object[])object).length;
/*  872 */     } else if ((object instanceof Iterator)) {
/*  873 */       Iterator it = (Iterator)object;
/*  874 */       while (it.hasNext()) {
/*  875 */         total++;
/*  876 */         it.next();
/*      */       }
/*  878 */     } else if ((object instanceof Enumeration)) {
/*  879 */       Enumeration it = (Enumeration)object;
/*  880 */       while (it.hasMoreElements()) {
/*  881 */         total++;
/*  882 */         it.nextElement();
/*      */       }
/*  884 */     } else { if (object == null) {
/*  885 */         throw new IllegalArgumentException("Unsupported object type: null");
/*      */       }
/*      */       try {
/*  888 */         total = Array.getLength(object);
/*      */       } catch (IllegalArgumentException ex) {
/*  890 */         throw new IllegalArgumentException("Unsupported object type: " + object.getClass().getName());
/*      */       }
/*      */     }
/*  893 */     return total;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void reverseArray(Object[] array)
/*      */   {
/*  902 */     int i = 0;
/*  903 */     int j = array.length - 1;
/*      */     
/*      */ 
/*  906 */     while (j > i) {
/*  907 */       Object tmp = array[j];
/*  908 */       array[j] = array[i];
/*  909 */       array[i] = tmp;
/*  910 */       j--;
/*  911 */       i++;
/*      */     }
/*      */   }
/*      */   
/*      */   private static final int getFreq(Object obj, Map freqMap) {
/*  916 */     Integer count = (Integer)freqMap.get(obj);
/*  917 */     if (count != null) {
/*  918 */       return count.intValue();
/*      */     }
/*  920 */     return 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isFull(Collection coll)
/*      */   {
/*  939 */     if (coll == null) {
/*  940 */       throw new NullPointerException("The collection must not be null");
/*      */     }
/*  942 */     if ((coll instanceof BoundedCollection)) {
/*  943 */       return ((BoundedCollection)coll).isFull();
/*      */     }
/*      */     try {
/*  946 */       BoundedCollection bcoll = UnmodifiableBoundedCollection.decorateUsing(coll);
/*  947 */       return bcoll.isFull();
/*      */     }
/*      */     catch (IllegalArgumentException ex) {}
/*  950 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int maxSize(Collection coll)
/*      */   {
/*  970 */     if (coll == null) {
/*  971 */       throw new NullPointerException("The collection must not be null");
/*      */     }
/*  973 */     if ((coll instanceof BoundedCollection)) {
/*  974 */       return ((BoundedCollection)coll).maxSize();
/*      */     }
/*      */     try {
/*  977 */       BoundedCollection bcoll = UnmodifiableBoundedCollection.decorateUsing(coll);
/*  978 */       return bcoll.maxSize();
/*      */     }
/*      */     catch (IllegalArgumentException ex) {}
/*  981 */     return -1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <E> Collection<E> synchronizedCollection(Collection<E> collection)
/*      */   {
/* 1009 */     return SynchronizedCollection.decorate(collection);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <E> Collection<E> unmodifiableCollection(Collection<E> collection)
/*      */   {
/* 1022 */     return UnmodifiableCollection.decorate(collection);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <E> Collection<E> predicatedCollection(Collection<E> collection, Predicate<? super E> predicate)
/*      */   {
/* 1039 */     return PredicatedCollection.decorate(collection, predicate);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public static <E> Collection<E> typedCollection(Collection<E> collection, Class<E> type)
/*      */   {
/* 1053 */     return TypedCollection.decorate(collection, type);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <I, O> Collection<O> transformedCollection(Collection<I> collection, Transformer<? super I, ? extends O> transformer)
/*      */   {
/* 1069 */     return TransformedCollection.decorate(collection, transformer);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/CollectionUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */