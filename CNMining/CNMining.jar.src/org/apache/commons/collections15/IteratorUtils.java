/*     */ package org.apache.commons.collections15;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.Dictionary;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections15.iterators.ArrayIterator;
/*     */ import org.apache.commons.collections15.iterators.ArrayListIterator;
/*     */ import org.apache.commons.collections15.iterators.CollatingIterator;
/*     */ import org.apache.commons.collections15.iterators.EmptyIterator;
/*     */ import org.apache.commons.collections15.iterators.EmptyListIterator;
/*     */ import org.apache.commons.collections15.iterators.EmptyMapIterator;
/*     */ import org.apache.commons.collections15.iterators.EmptyOrderedIterator;
/*     */ import org.apache.commons.collections15.iterators.EmptyOrderedMapIterator;
/*     */ import org.apache.commons.collections15.iterators.EnumerationIterator;
/*     */ import org.apache.commons.collections15.iterators.FilterIterator;
/*     */ import org.apache.commons.collections15.iterators.FilterListIterator;
/*     */ import org.apache.commons.collections15.iterators.IteratorChain;
/*     */ import org.apache.commons.collections15.iterators.IteratorEnumeration;
/*     */ import org.apache.commons.collections15.iterators.ListIteratorWrapper;
/*     */ import org.apache.commons.collections15.iterators.LoopingIterator;
/*     */ import org.apache.commons.collections15.iterators.ObjectArrayIterator;
/*     */ import org.apache.commons.collections15.iterators.ObjectArrayListIterator;
/*     */ import org.apache.commons.collections15.iterators.ObjectGraphIterator;
/*     */ import org.apache.commons.collections15.iterators.SingletonIterator;
/*     */ import org.apache.commons.collections15.iterators.SingletonListIterator;
/*     */ import org.apache.commons.collections15.iterators.TransformIterator;
/*     */ import org.apache.commons.collections15.iterators.UnmodifiableIterator;
/*     */ import org.apache.commons.collections15.iterators.UnmodifiableListIterator;
/*     */ import org.apache.commons.collections15.iterators.UnmodifiableMapIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IteratorUtils
/*     */ {
/*  51 */   public static final ResettableIterator EMPTY_ITERATOR = EmptyIterator.RESETTABLE_INSTANCE;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  58 */   public static final ResettableListIterator EMPTY_LIST_ITERATOR = EmptyListIterator.RESETTABLE_INSTANCE;
/*     */   
/*     */ 
/*     */ 
/*  62 */   public static final OrderedIterator EMPTY_ORDERED_ITERATOR = EmptyOrderedIterator.INSTANCE;
/*     */   
/*     */ 
/*     */ 
/*  66 */   public static final MapIterator EMPTY_MAP_ITERATOR = EmptyMapIterator.INSTANCE;
/*     */   
/*     */ 
/*     */ 
/*  70 */   public static final OrderedMapIterator EMPTY_ORDERED_MAP_ITERATOR = EmptyOrderedMapIterator.INSTANCE;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ResettableIterator emptyIterator()
/*     */   {
/*  92 */     return EMPTY_ITERATOR;
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
/*     */   public static ResettableListIterator emptyListIterator()
/*     */   {
/* 107 */     return EMPTY_LIST_ITERATOR;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static OrderedIterator emptyOrderedIterator()
/*     */   {
/* 119 */     return EMPTY_ORDERED_ITERATOR;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static MapIterator emptyMapIterator()
/*     */   {
/* 131 */     return EMPTY_MAP_ITERATOR;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static OrderedMapIterator emptyOrderedMapIterator()
/*     */   {
/* 143 */     return EMPTY_ORDERED_MAP_ITERATOR;
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
/*     */   public static <E> ResettableIterator<E> singletonIterator(E object)
/*     */   {
/* 161 */     return new SingletonIterator(object);
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
/*     */   public static <E> ListIterator<E> singletonListIterator(E object)
/*     */   {
/* 174 */     return new SingletonListIterator(object);
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
/*     */   public static <E> ResettableIterator<E> arrayIterator(E[] array)
/*     */   {
/* 190 */     return new ObjectArrayIterator(array);
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
/*     */   public static <E> ResettableIterator<E> arrayIterator(Object array)
/*     */   {
/* 205 */     return new ArrayIterator(array);
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
/*     */   public static <E> ResettableIterator<E> arrayIterator(E[] array, int start)
/*     */   {
/* 222 */     return new ObjectArrayIterator(array, start);
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
/*     */   public static <E> ResettableIterator<E> arrayIterator(Object array, int start)
/*     */   {
/* 240 */     return new ArrayIterator(array, start);
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
/*     */   public static <E> ResettableIterator<E> arrayIterator(E[] array, int start, int end)
/*     */   {
/* 258 */     return new ObjectArrayIterator(array, start, end);
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
/*     */ 
/*     */   public static <E> ResettableIterator<E> arrayIterator(Object array, int start, int end)
/*     */   {
/* 277 */     return new ArrayIterator(array, start, end);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <E> ResettableListIterator<E> arrayListIterator(E[] array)
/*     */   {
/* 289 */     return new ObjectArrayListIterator(array);
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
/*     */   public static <E> ResettableListIterator<E> arrayListIterator(Object array)
/*     */   {
/* 304 */     return new ArrayListIterator(array);
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
/*     */   public static <E> ResettableListIterator<E> arrayListIterator(E[] array, int start)
/*     */   {
/* 317 */     return new ObjectArrayListIterator(array, start);
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
/*     */   public static <E> ResettableListIterator<E> arrayListIterator(Object array, int start)
/*     */   {
/* 334 */     return new ArrayListIterator(array, start);
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
/*     */   public static <E> ResettableListIterator<E> arrayListIterator(E[] array, int start, int end)
/*     */   {
/* 349 */     return new ObjectArrayListIterator(array, start, end);
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
/*     */ 
/*     */   public static <E> ResettableListIterator<E> arrayListIterator(Object array, int start, int end)
/*     */   {
/* 368 */     return new ArrayListIterator(array, start, end);
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
/*     */   public static <E> Iterator<E> unmodifiableIterator(Iterator<E> iterator)
/*     */   {
/* 382 */     return UnmodifiableIterator.decorate(iterator);
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
/*     */   public static <E> ListIterator<E> unmodifiableListIterator(ListIterator<E> listIterator)
/*     */   {
/* 395 */     return UnmodifiableListIterator.decorate(listIterator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <K, V> MapIterator<K, V> unmodifiableMapIterator(MapIterator<K, V> mapIterator)
/*     */   {
/* 407 */     return UnmodifiableMapIterator.decorate(mapIterator);
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
/*     */   public static <E> Iterator<E> chainedIterator(Iterator<E> iterator1, Iterator<E> iterator2)
/*     */   {
/* 422 */     return new IteratorChain(iterator1, iterator2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <E> Iterator<E> chainedIterator(Iterator<E>[] iterators)
/*     */   {
/* 434 */     return new IteratorChain(iterators);
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
/*     */   public static <E> Iterator<E> chainedIterator(Collection<Iterator<? extends E>> iterators)
/*     */   {
/* 447 */     return new IteratorChain(iterators);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <E> Iterator<E> collatedIterator(Comparator<? super E> comparator, Iterator<? extends E> iterator1, Iterator<? extends E> iterator2)
/*     */   {
/* 469 */     return new CollatingIterator(comparator, iterator1, iterator2);
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
/*     */ 
/*     */   public static <E> Iterator<E> collatedIterator(Comparator<? super E> comparator, Iterator<? extends E>[] iterators)
/*     */   {
/* 488 */     return new CollatingIterator(comparator, iterators);
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
/*     */ 
/*     */ 
/*     */   public static <E> Iterator<E> collatedIterator(Comparator<? super E> comparator, Collection<Iterator<? extends E>> iterators)
/*     */   {
/* 508 */     return new CollatingIterator(comparator, iterators);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Iterator objectGraphIterator(Object root, Transformer transformer)
/*     */   {
/* 567 */     return new ObjectGraphIterator(root, transformer);
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
/*     */   public static <I, O> Iterator<O> transformedIterator(Iterator<I> iterator, Transformer<I, O> transform)
/*     */   {
/* 584 */     if (iterator == null) {
/* 585 */       throw new NullPointerException("Iterator must not be null");
/*     */     }
/* 587 */     if (transform == null) {
/* 588 */       throw new NullPointerException("Transformer must not be null");
/*     */     }
/* 590 */     return new TransformIterator(iterator, transform);
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
/*     */   public static <E> Iterator<E> filteredIterator(Iterator<E> iterator, Predicate<? super E> predicate)
/*     */   {
/* 607 */     if (iterator == null) {
/* 608 */       throw new NullPointerException("Iterator must not be null");
/*     */     }
/* 610 */     if (predicate == null) {
/* 611 */       throw new NullPointerException("Predicate must not be null");
/*     */     }
/* 613 */     return new FilterIterator(iterator, predicate);
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
/*     */   public static <E> ListIterator<E> filteredListIterator(ListIterator<E> listIterator, Predicate<? super E> predicate)
/*     */   {
/* 628 */     if (listIterator == null) {
/* 629 */       throw new NullPointerException("ListIterator must not be null");
/*     */     }
/* 631 */     if (predicate == null) {
/* 632 */       throw new NullPointerException("Predicate must not be null");
/*     */     }
/* 634 */     return new FilterListIterator(listIterator, predicate);
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
/*     */   public static <E> ResettableIterator<E> loopingIterator(Collection<E> coll)
/*     */   {
/* 651 */     if (coll == null) {
/* 652 */       throw new NullPointerException("Collection must not be null");
/*     */     }
/* 654 */     return new LoopingIterator(coll);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <E> Iterator<E> asIterator(Enumeration<E> enumeration)
/*     */   {
/* 666 */     if (enumeration == null) {
/* 667 */       throw new NullPointerException("Enumeration must not be null");
/*     */     }
/* 669 */     return new EnumerationIterator(enumeration);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <E> Iterator<E> asIterator(Enumeration<E> enumeration, Collection<E> removeCollection)
/*     */   {
/* 681 */     if (enumeration == null) {
/* 682 */       throw new NullPointerException("Enumeration must not be null");
/*     */     }
/* 684 */     if (removeCollection == null) {
/* 685 */       throw new NullPointerException("Collection must not be null");
/*     */     }
/* 687 */     return new EnumerationIterator(enumeration, removeCollection);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <E> Enumeration<E> asEnumeration(Iterator<E> iterator)
/*     */   {
/* 698 */     if (iterator == null) {
/* 699 */       throw new NullPointerException("Iterator must not be null");
/*     */     }
/* 701 */     return new IteratorEnumeration(iterator);
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
/*     */   public static <E> ListIterator<E> toListIterator(Iterator<E> iterator)
/*     */   {
/* 715 */     if (iterator == null) {
/* 716 */       throw new NullPointerException("Iterator must not be null");
/*     */     }
/* 718 */     return new ListIteratorWrapper(iterator);
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
/*     */   public static <E> E[] toArray(Iterator<E> iterator)
/*     */   {
/* 732 */     if (iterator == null) {
/* 733 */       throw new NullPointerException("Iterator must not be null");
/*     */     }
/* 735 */     List<E> list = toList(iterator, 100);
/* 736 */     return (Object[])list.toArray();
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
/*     */   public static <E> E[] toArray(Iterator<E> iterator, Class<E> arrayClass)
/*     */   {
/* 753 */     if (iterator == null) {
/* 754 */       throw new NullPointerException("Iterator must not be null");
/*     */     }
/* 756 */     if (arrayClass == null) {
/* 757 */       throw new NullPointerException("Array class must not be null");
/*     */     }
/* 759 */     List<E> list = toList(iterator, 100);
/* 760 */     return list.toArray((Object[])Array.newInstance(arrayClass, list.size()));
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
/*     */   public static <E> List<E> toList(Iterator<E> iterator)
/*     */   {
/* 774 */     return toList(iterator, 10);
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
/*     */   public static <E> List<E> toList(Iterator<E> iterator, int estimatedSize)
/*     */   {
/* 790 */     if (iterator == null) {
/* 791 */       throw new NullPointerException("Iterator must not be null");
/*     */     }
/* 793 */     if (estimatedSize < 1) {
/* 794 */       throw new IllegalArgumentException("Estimated size must be greater than 0");
/*     */     }
/* 796 */     List<E> list = new ArrayList(estimatedSize);
/* 797 */     while (iterator.hasNext()) {
/* 798 */       list.add(iterator.next());
/*     */     }
/* 800 */     return list;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Iterator getIterator(Object obj)
/*     */   {
/* 823 */     if (obj == null) {
/* 824 */       return emptyIterator();
/*     */     }
/* 826 */     if ((obj instanceof Iterator)) {
/* 827 */       return (Iterator)obj;
/*     */     }
/* 829 */     if ((obj instanceof Collection)) {
/* 830 */       return ((Collection)obj).iterator();
/*     */     }
/* 832 */     if ((obj instanceof Object[])) {
/* 833 */       return new ObjectArrayIterator((Object[])obj);
/*     */     }
/* 835 */     if ((obj instanceof Enumeration)) {
/* 836 */       return new EnumerationIterator((Enumeration)obj);
/*     */     }
/* 838 */     if ((obj instanceof Map)) {
/* 839 */       return ((Map)obj).values().iterator();
/*     */     }
/* 841 */     if ((obj instanceof Dictionary)) {
/* 842 */       return new EnumerationIterator(((Dictionary)obj).elements());
/*     */     }
/* 844 */     if ((obj != null) && (obj.getClass().isArray())) {
/* 845 */       return new ArrayIterator(obj);
/*     */     }
/*     */     try
/*     */     {
/* 849 */       Method method = obj.getClass().getMethod("iterator", null);
/* 850 */       if (Iterator.class.isAssignableFrom(method.getReturnType())) {
/* 851 */         Iterator it = (Iterator)method.invoke(obj, null);
/* 852 */         if (it != null) {
/* 853 */           return it;
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception ex) {}
/*     */     
/* 859 */     return singletonIterator(obj);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/IteratorUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */