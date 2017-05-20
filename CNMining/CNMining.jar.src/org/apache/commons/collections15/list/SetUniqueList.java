/*     */ package org.apache.commons.collections15.list;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.iterators.AbstractIteratorDecorator;
/*     */ import org.apache.commons.collections15.iterators.AbstractListIteratorDecorator;
/*     */ import org.apache.commons.collections15.set.UnmodifiableSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SetUniqueList<E>
/*     */   extends AbstractSerializableListDecorator<E>
/*     */ {
/*     */   private static final long serialVersionUID = 7196982186153478694L;
/*     */   protected final Set<E> set;
/*     */   
/*     */   public static <E> SetUniqueList<E> decorate(List<E> list)
/*     */   {
/*  67 */     if (list == null) {
/*  68 */       throw new IllegalArgumentException("List must not be null");
/*     */     }
/*  70 */     if (list.isEmpty()) {
/*  71 */       return new SetUniqueList(list, new HashSet());
/*     */     }
/*  73 */     List temp = new ArrayList(list);
/*  74 */     list.clear();
/*  75 */     SetUniqueList<E> sl = new SetUniqueList(list, new HashSet());
/*  76 */     sl.addAll(temp);
/*  77 */     return sl;
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
/*     */   protected SetUniqueList(List<E> list, Set<E> set)
/*     */   {
/*  92 */     super(list);
/*  93 */     if (set == null) {
/*  94 */       throw new IllegalArgumentException("Set must not be null");
/*     */     }
/*  96 */     this.set = set;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<E> asSet()
/*     */   {
/* 106 */     return UnmodifiableSet.decorate(this.set);
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
/*     */   public boolean add(E object)
/*     */   {
/* 123 */     int sizeBefore = size();
/*     */     
/*     */ 
/* 126 */     add(size(), object);
/*     */     
/*     */ 
/* 129 */     return sizeBefore != size();
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
/*     */   public void add(int index, E object)
/*     */   {
/* 144 */     if (!this.set.contains(object)) {
/* 145 */       super.add(index, object);
/* 146 */       this.set.add(object);
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
/*     */   public boolean addAll(Collection<? extends E> coll)
/*     */   {
/* 160 */     return addAll(size(), coll);
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
/*     */   public boolean addAll(int index, Collection<? extends E> coll)
/*     */   {
/* 179 */     int sizeBefore = size();
/*     */     
/*     */ 
/* 182 */     for (Iterator it = coll.iterator(); it.hasNext();) {
/* 183 */       add(it.next());
/*     */     }
/*     */     
/*     */ 
/* 187 */     return sizeBefore != size();
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
/*     */   public E set(int index, E object)
/*     */   {
/* 204 */     int pos = indexOf(object);
/* 205 */     E result = super.set(index, object);
/* 206 */     if ((pos == -1) || (pos == index)) {
/* 207 */       return result;
/*     */     }
/* 209 */     return (E)remove(pos);
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 213 */     boolean result = super.remove(object);
/* 214 */     this.set.remove(object);
/* 215 */     return result;
/*     */   }
/*     */   
/*     */   public E remove(int index) {
/* 219 */     E result = super.remove(index);
/* 220 */     this.set.remove(result);
/* 221 */     return result;
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> coll) {
/* 225 */     boolean result = super.removeAll(coll);
/* 226 */     this.set.removeAll(coll);
/* 227 */     return result;
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> coll) {
/* 231 */     boolean result = super.retainAll(coll);
/* 232 */     this.set.retainAll(coll);
/* 233 */     return result;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 237 */     super.clear();
/* 238 */     this.set.clear();
/*     */   }
/*     */   
/*     */   public boolean contains(Object object) {
/* 242 */     return this.set.contains(object);
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> coll) {
/* 246 */     return this.set.containsAll(coll);
/*     */   }
/*     */   
/*     */   public Iterator<E> iterator() {
/* 250 */     return new SetListIterator(super.iterator(), this.set);
/*     */   }
/*     */   
/*     */   public ListIterator<E> listIterator() {
/* 254 */     return new SetListListIterator(super.listIterator(), this.set);
/*     */   }
/*     */   
/*     */   public ListIterator<E> listIterator(int index) {
/* 258 */     return new SetListListIterator(super.listIterator(index), this.set);
/*     */   }
/*     */   
/*     */   public List<E> subList(int fromIndex, int toIndex) {
/* 262 */     return new SetUniqueList(super.subList(fromIndex, toIndex), this.set);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   static class SetListIterator<E>
/*     */     extends AbstractIteratorDecorator<E>
/*     */   {
/*     */     protected final Set<E> set;
/*     */     
/* 272 */     protected E last = null;
/*     */     
/*     */     protected SetListIterator(Iterator<E> it, Set<E> set) {
/* 275 */       super();
/* 276 */       this.set = set;
/*     */     }
/*     */     
/*     */     public E next() {
/* 280 */       this.last = super.next();
/* 281 */       return (E)this.last;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 285 */       super.remove();
/* 286 */       this.set.remove(this.last);
/* 287 */       this.last = null;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   static class SetListListIterator<E>
/*     */     extends AbstractListIteratorDecorator<E>
/*     */   {
/*     */     protected final Set<E> set;
/*     */     
/* 297 */     protected E last = null;
/*     */     
/*     */     protected SetListListIterator(ListIterator<E> it, Set<E> set) {
/* 300 */       super();
/* 301 */       this.set = set;
/*     */     }
/*     */     
/*     */     public E next() {
/* 305 */       this.last = super.next();
/* 306 */       return (E)this.last;
/*     */     }
/*     */     
/*     */     public E previous() {
/* 310 */       this.last = super.previous();
/* 311 */       return (E)this.last;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 315 */       super.remove();
/* 316 */       this.set.remove(this.last);
/* 317 */       this.last = null;
/*     */     }
/*     */     
/*     */     public void add(E object) {
/* 321 */       if (!this.set.contains(object)) {
/* 322 */         super.add(object);
/* 323 */         this.set.add(object);
/*     */       }
/*     */     }
/*     */     
/*     */     public void set(E object) {
/* 328 */       throw new UnsupportedOperationException("ListIterator does not support set");
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/list/SetUniqueList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */