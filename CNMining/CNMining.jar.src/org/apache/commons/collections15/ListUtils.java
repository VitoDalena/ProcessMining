/*     */ package org.apache.commons.collections15;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.collections15.list.FixedSizeList;
/*     */ import org.apache.commons.collections15.list.LazyList;
/*     */ import org.apache.commons.collections15.list.PredicatedList;
/*     */ import org.apache.commons.collections15.list.SynchronizedList;
/*     */ import org.apache.commons.collections15.list.TransformedList;
/*     */ import org.apache.commons.collections15.list.TypedList;
/*     */ import org.apache.commons.collections15.list.UnmodifiableList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ListUtils
/*     */ {
/*  42 */   public static final List EMPTY_LIST = Collections.EMPTY_LIST;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <E> List<E> intersection(List<? extends E> list1, List<? extends E> list2)
/*     */   {
/*  61 */     ArrayList<E> result = new ArrayList();
/*  62 */     Iterator<? extends E> iterator = list2.iterator();
/*     */     
/*  64 */     while (iterator.hasNext()) {
/*  65 */       E o = iterator.next();
/*     */       
/*  67 */       if (list1.contains(o)) {
/*  68 */         result.add(o);
/*     */       }
/*     */     }
/*     */     
/*  72 */     return result;
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
/*     */   public static <E> List<E> subtract(List<? extends E> list1, List<? extends E> list2)
/*     */   {
/*  91 */     ArrayList<E> result = new ArrayList(list1);
/*  92 */     Iterator<? extends E> iterator = list2.iterator();
/*     */     
/*  94 */     while (iterator.hasNext()) {
/*  95 */       result.remove(iterator.next());
/*     */     }
/*     */     
/*  98 */     return result;
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
/*     */   public static <E> List<E> sum(List<? extends E> list1, List<? extends E> list2)
/*     */   {
/* 111 */     return subtract(union(list1, list2), intersection(list1, list2));
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
/*     */   public static <E> List<E> union(List<? extends E> list1, List<? extends E> list2)
/*     */   {
/* 125 */     ArrayList<E> result = new ArrayList(list1);
/* 126 */     result.addAll(list2);
/* 127 */     return result;
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
/*     */   public static <E> boolean isEqualList(Collection<? extends E> list1, Collection<? extends E> list2)
/*     */   {
/* 160 */     if (list1 == list2) {
/* 161 */       return true;
/*     */     }
/* 163 */     if ((list1 == null) || (list2 == null) || (list1.size() != list2.size())) {
/* 164 */       return false;
/*     */     }
/*     */     
/* 167 */     Iterator<? extends E> it1 = list1.iterator();
/* 168 */     Iterator<? extends E> it2 = list2.iterator();
/* 169 */     E obj1 = null;
/* 170 */     E obj2 = null;
/*     */     
/* 172 */     while ((it1.hasNext()) && (it2.hasNext())) {
/* 173 */       obj1 = it1.next();
/* 174 */       obj2 = it2.next();
/*     */       
/* 176 */       if (obj1 == null ? obj2 != null : !obj1.equals(obj2)) {
/* 177 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 181 */     return (!it1.hasNext()) && (!it2.hasNext());
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
/*     */   public static int hashCodeForList(Collection list)
/*     */   {
/* 197 */     if (list == null) {
/* 198 */       return 0;
/*     */     }
/* 200 */     int hashCode = 1;
/* 201 */     Iterator it = list.iterator();
/* 202 */     Object obj = null;
/*     */     
/* 204 */     while (it.hasNext()) {
/* 205 */       obj = it.next();
/* 206 */       hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
/*     */     }
/* 208 */     return hashCode;
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
/*     */   public static <E> List<E> synchronizedList(List<E> list)
/*     */   {
/* 235 */     return SynchronizedList.decorate(list);
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
/*     */   public static <E> List<E> unmodifiableList(List<E> list)
/*     */   {
/* 248 */     return UnmodifiableList.decorate(list);
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
/*     */   public static <E> List<E> predicatedList(List<E> list, Predicate<? super E> predicate)
/*     */   {
/* 265 */     return PredicatedList.decorate(list, predicate);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static <E> List<E> typedList(List<E> list, Class<E> type)
/*     */   {
/* 279 */     return TypedList.decorate(list, type);
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
/*     */   public static <I, O> List<O> transformedList(List<I> list, Transformer<? super I, ? extends O> transformer)
/*     */   {
/* 295 */     return TransformedList.decorate(list, transformer);
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
/*     */   public static <E> List<E> lazyList(List<E> list, Factory<? extends E> factory)
/*     */   {
/* 328 */     return LazyList.decorate(list, factory);
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
/*     */   public static <E> List<E> fixedSizeList(List<E> list)
/*     */   {
/* 342 */     return FixedSizeList.decorate(list);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/ListUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */