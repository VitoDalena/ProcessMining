/*     */ package org.apache.commons.collections15.set;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.iterators.AbstractIteratorDecorator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ListOrderedSet<E>
/*     */   extends AbstractSerializableSetDecorator<E>
/*     */   implements Set<E>
/*     */ {
/*     */   private static final long serialVersionUID = -228664372470420141L;
/*     */   protected final List<E> setOrder;
/*     */   
/*     */   public static <E> ListOrderedSet<E> decorate(Set<E> set, List<E> list)
/*     */   {
/*  69 */     if (set == null) {
/*  70 */       throw new IllegalArgumentException("Set must not be null");
/*     */     }
/*  72 */     if (list == null) {
/*  73 */       throw new IllegalArgumentException("List must not be null");
/*     */     }
/*  75 */     if ((set.size() > 0) || (list.size() > 0)) {
/*  76 */       throw new IllegalArgumentException("Set and List must be empty");
/*     */     }
/*  78 */     return new ListOrderedSet(set, list);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <E> ListOrderedSet<E> decorate(Set<E> set)
/*     */   {
/*  90 */     return new ListOrderedSet(set);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <E> ListOrderedSet<E> decorate(List<E> list)
/*     */   {
/* 102 */     if (list == null) {
/* 103 */       throw new IllegalArgumentException("List must not be null");
/*     */     }
/* 105 */     Set<E> set = new HashSet(list);
/* 106 */     list.retainAll(set);
/*     */     
/* 108 */     return new ListOrderedSet(set, list);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ListOrderedSet()
/*     */   {
/* 119 */     super(new HashSet());
/* 120 */     this.setOrder = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected ListOrderedSet(Set<E> set)
/*     */   {
/* 130 */     super(set);
/* 131 */     this.setOrder = new ArrayList(set);
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
/*     */   protected ListOrderedSet(Set<E> set, List<E> list)
/*     */   {
/* 144 */     super(set);
/* 145 */     if (list == null) {
/* 146 */       throw new IllegalArgumentException("List must not be null");
/*     */     }
/* 148 */     this.setOrder = list;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<E> asList()
/*     */   {
/* 158 */     return UnmodifiableList.decorate(this.setOrder);
/*     */   }
/*     */   
/*     */   public void clear()
/*     */   {
/* 163 */     this.collection.clear();
/* 164 */     this.setOrder.clear();
/*     */   }
/*     */   
/*     */   public Iterator<E> iterator() {
/* 168 */     return new OrderedSetIterator(this.setOrder.iterator(), this.collection, null);
/*     */   }
/*     */   
/*     */   public boolean add(E object) {
/* 172 */     if (this.collection.contains(object))
/*     */     {
/* 174 */       return this.collection.add(object);
/*     */     }
/*     */     
/* 177 */     boolean result = this.collection.add(object);
/* 178 */     this.setOrder.add(object);
/* 179 */     return result;
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends E> coll)
/*     */   {
/* 184 */     boolean result = false;
/* 185 */     for (Iterator<? extends E> it = coll.iterator(); it.hasNext();) {
/* 186 */       E object = it.next();
/* 187 */       result |= add(object);
/*     */     }
/* 189 */     return result;
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 193 */     boolean result = this.collection.remove(object);
/* 194 */     this.setOrder.remove(object);
/* 195 */     return result;
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> coll) {
/* 199 */     boolean result = false;
/* 200 */     for (Iterator it = coll.iterator(); it.hasNext();) {
/* 201 */       Object object = it.next();
/* 202 */       result |= remove(object);
/*     */     }
/* 204 */     return result;
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> coll) {
/* 208 */     boolean result = this.collection.retainAll(coll);
/* 209 */     if (!result)
/* 210 */       return false;
/* 211 */     Iterator it; if (this.collection.size() == 0) {
/* 212 */       this.setOrder.clear();
/*     */     } else {
/* 214 */       for (it = this.setOrder.iterator(); it.hasNext();) {
/* 215 */         Object object = it.next();
/* 216 */         if (!this.collection.contains(object)) {
/* 217 */           it.remove();
/*     */         }
/*     */       }
/*     */     }
/* 221 */     return result;
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 225 */     return this.setOrder.toArray();
/*     */   }
/*     */   
/*     */   public <T> T[] toArray(T[] a) {
/* 229 */     return this.setOrder.toArray(a);
/*     */   }
/*     */   
/*     */   public E get(int index)
/*     */   {
/* 234 */     return (E)this.setOrder.get(index);
/*     */   }
/*     */   
/*     */   public int indexOf(E object) {
/* 238 */     return this.setOrder.indexOf(object);
/*     */   }
/*     */   
/*     */   public void add(int index, E object) {
/* 242 */     if (!contains(object)) {
/* 243 */       this.collection.add(object);
/* 244 */       this.setOrder.add(index, object);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection<? extends E> coll) {
/* 249 */     boolean changed = false;
/* 250 */     for (Iterator<? extends E> it = coll.iterator(); it.hasNext();) {
/* 251 */       E object = it.next();
/* 252 */       if (!contains(object)) {
/* 253 */         this.collection.add(object);
/* 254 */         this.setOrder.add(index, object);
/* 255 */         index++;
/* 256 */         changed = true;
/*     */       }
/*     */     }
/* 259 */     return changed;
/*     */   }
/*     */   
/*     */   public E remove(int index) {
/* 263 */     E obj = this.setOrder.remove(index);
/* 264 */     remove(obj);
/* 265 */     return obj;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 275 */     return this.setOrder.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   static class OrderedSetIterator<E>
/*     */     extends AbstractIteratorDecorator<E>
/*     */   {
/*     */     protected final Collection<E> set;
/*     */     
/*     */ 
/*     */ 
/*     */     protected E last;
/*     */     
/*     */ 
/*     */ 
/*     */     private OrderedSetIterator(Iterator<E> iterator, Collection<E> set)
/*     */     {
/* 294 */       super();
/* 295 */       this.set = set;
/*     */     }
/*     */     
/*     */     public E next() {
/* 299 */       this.last = this.iterator.next();
/* 300 */       return (E)this.last;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 304 */       this.set.remove(this.last);
/* 305 */       this.iterator.remove();
/* 306 */       this.last = null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/set/ListOrderedSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */