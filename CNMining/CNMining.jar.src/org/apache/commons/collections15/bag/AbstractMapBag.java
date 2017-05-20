/*     */ package org.apache.commons.collections15.bag;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.Bag;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractMapBag<E>
/*     */   implements Bag<E>
/*     */ {
/*     */   private transient Map<E, MutableInteger> map;
/*     */   private int size;
/*     */   private transient int modCount;
/*     */   private transient Set<E> uniqueSet;
/*     */   
/*     */   protected AbstractMapBag() {}
/*     */   
/*     */   protected AbstractMapBag(Map<E, MutableInteger> map)
/*     */   {
/*  77 */     this.map = map;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Map<E, MutableInteger> getMap()
/*     */   {
/*  87 */     return this.map;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/*  97 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 106 */     return this.map.isEmpty();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getCount(E object)
/*     */   {
/* 117 */     MutableInteger count = (MutableInteger)this.map.get(object);
/* 118 */     if (count != null) {
/* 119 */       return count.value;
/*     */     }
/* 121 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean contains(Object object)
/*     */   {
/* 133 */     return this.map.containsKey(object);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsAll(Collection<?> coll)
/*     */   {
/* 143 */     if ((coll instanceof Bag)) {
/* 144 */       return containsAll((Bag)coll);
/*     */     }
/* 146 */     return containsAll(new HashBag(coll));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   boolean containsAll(Bag<E> other)
/*     */   {
/* 157 */     boolean result = true;
/* 158 */     Iterator<E> it = other.uniqueSet().iterator();
/* 159 */     while (it.hasNext()) {
/* 160 */       E current = it.next();
/* 161 */       boolean contains = getCount(current) >= other.getCount(current);
/* 162 */       result = (result) && (contains);
/*     */     }
/* 164 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator<E> iterator()
/*     */   {
/* 175 */     return new BagIterator(this);
/*     */   }
/*     */   
/*     */ 
/*     */   static class BagIterator<E>
/*     */     implements Iterator<E>
/*     */   {
/*     */     private AbstractMapBag<E> parent;
/*     */     
/*     */     private Iterator<Map.Entry<E, AbstractMapBag.MutableInteger>> entryIterator;
/*     */     
/*     */     private Map.Entry<E, AbstractMapBag.MutableInteger> current;
/*     */     
/*     */     private int itemCount;
/*     */     
/*     */     private final int mods;
/*     */     private boolean canRemove;
/*     */     
/*     */     public BagIterator(AbstractMapBag<E> parent)
/*     */     {
/* 195 */       this.parent = parent;
/* 196 */       this.entryIterator = parent.map.entrySet().iterator();
/* 197 */       this.current = null;
/* 198 */       this.mods = parent.modCount;
/* 199 */       this.canRemove = false;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 203 */       return (this.itemCount > 0) || (this.entryIterator.hasNext());
/*     */     }
/*     */     
/*     */     public E next() {
/* 207 */       if (this.parent.modCount != this.mods) {
/* 208 */         throw new ConcurrentModificationException();
/*     */       }
/* 210 */       if (this.itemCount == 0) {
/* 211 */         this.current = ((Map.Entry)this.entryIterator.next());
/* 212 */         this.itemCount = ((AbstractMapBag.MutableInteger)this.current.getValue()).value;
/*     */       }
/* 214 */       this.canRemove = true;
/* 215 */       this.itemCount -= 1;
/* 216 */       return (E)this.current.getKey();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 220 */       if (this.parent.modCount != this.mods) {
/* 221 */         throw new ConcurrentModificationException();
/*     */       }
/* 223 */       if (!this.canRemove) {
/* 224 */         throw new IllegalStateException();
/*     */       }
/* 226 */       AbstractMapBag.MutableInteger mut = (AbstractMapBag.MutableInteger)this.current.getValue();
/* 227 */       if (mut.value > 0) {
/* 228 */         mut.value -= 1;
/* 229 */         AbstractMapBag.access$210(this.parent);
/*     */       } else {
/* 231 */         this.entryIterator.remove();
/*     */       }
/* 233 */       this.canRemove = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean add(E object)
/*     */   {
/* 245 */     return add(object, 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean add(E object, int nCopies)
/*     */   {
/* 256 */     this.modCount += 1;
/* 257 */     if (nCopies > 0) {
/* 258 */       MutableInteger mut = (MutableInteger)this.map.get(object);
/* 259 */       this.size += nCopies;
/* 260 */       if (mut == null) {
/* 261 */         this.map.put(object, new MutableInteger(nCopies));
/* 262 */         return true;
/*     */       }
/* 264 */       mut.value += nCopies;
/* 265 */       return false;
/*     */     }
/*     */     
/* 268 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean addAll(Collection<? extends E> coll)
/*     */   {
/* 279 */     boolean changed = false;
/* 280 */     Iterator<? extends E> i = coll.iterator();
/* 281 */     while (i.hasNext()) {
/* 282 */       boolean added = add(i.next());
/* 283 */       changed = (changed) || (added);
/*     */     }
/* 285 */     return changed;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 293 */     this.modCount += 1;
/* 294 */     this.map.clear();
/* 295 */     this.size = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean remove(Object object)
/*     */   {
/* 305 */     MutableInteger mut = (MutableInteger)this.map.get(object);
/* 306 */     if (mut == null) {
/* 307 */       return false;
/*     */     }
/* 309 */     this.modCount += 1;
/* 310 */     this.map.remove(object);
/* 311 */     this.size -= mut.value;
/* 312 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean remove(E object, int nCopies)
/*     */   {
/* 323 */     MutableInteger mut = (MutableInteger)this.map.get(object);
/* 324 */     if (mut == null) {
/* 325 */       return false;
/*     */     }
/* 327 */     if (nCopies <= 0) {
/* 328 */       return false;
/*     */     }
/* 330 */     this.modCount += 1;
/* 331 */     if (nCopies < mut.value) {
/* 332 */       mut.value -= nCopies;
/* 333 */       this.map.put(object, mut);
/* 334 */       this.size -= nCopies;
/*     */     } else {
/* 336 */       this.map.remove(object);
/* 337 */       this.size -= mut.value;
/*     */     }
/* 339 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean removeAll(Collection<?> coll)
/*     */   {
/* 349 */     boolean result = false;
/* 350 */     if (coll != null) {
/* 351 */       Iterator i = coll.iterator();
/* 352 */       while (i.hasNext()) {
/* 353 */         boolean changed = remove(i.next(), 1);
/* 354 */         result = (result) || (changed);
/*     */       }
/*     */     }
/* 357 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean retainAll(Collection<?> coll)
/*     */   {
/* 368 */     if ((coll instanceof Bag)) {
/* 369 */       return retainAll((Bag)coll);
/*     */     }
/* 371 */     return retainAll(new HashBag(coll));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   boolean retainAll(Bag<E> other)
/*     */   {
/* 383 */     boolean result = false;
/* 384 */     Bag excess = new HashBag();
/* 385 */     Iterator<E> i = uniqueSet().iterator();
/* 386 */     while (i.hasNext()) {
/* 387 */       E current = i.next();
/* 388 */       int myCount = getCount(current);
/* 389 */       int otherCount = other.getCount(current);
/* 390 */       if ((1 <= otherCount) && (otherCount <= myCount)) {
/* 391 */         excess.add(current, myCount - otherCount);
/*     */       } else {
/* 393 */         excess.add(current, myCount);
/*     */       }
/*     */     }
/* 396 */     if (!excess.isEmpty()) {
/* 397 */       result = removeAll(excess);
/*     */     }
/* 399 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static class MutableInteger
/*     */   {
/*     */     protected int value;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     MutableInteger(int value)
/*     */     {
/* 419 */       this.value = value;
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/* 423 */       if (!(obj instanceof MutableInteger)) {
/* 424 */         return false;
/*     */       }
/* 426 */       return ((MutableInteger)obj).value == this.value;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 430 */       return this.value;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object[] toArray()
/*     */   {
/* 440 */     Object[] result = new Object[size()];
/* 441 */     int i = 0;
/* 442 */     Iterator<E> it = this.map.keySet().iterator();
/* 443 */     while (it.hasNext()) {
/* 444 */       E current = it.next();
/* 445 */       for (int index = getCount(current); index > 0; index--) {
/* 446 */         result[(i++)] = current;
/*     */       }
/*     */     }
/* 449 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object[] toArray(Object[] array)
/*     */   {
/* 459 */     int size = size();
/* 460 */     if (array.length < size) {
/* 461 */       array = (Object[])Array.newInstance(array.getClass().getComponentType(), size);
/*     */     }
/*     */     
/* 464 */     int i = 0;
/* 465 */     Iterator<E> it = this.map.keySet().iterator();
/* 466 */     while (it.hasNext()) {
/* 467 */       E current = it.next();
/* 468 */       for (int index = getCount(current); index > 0; index--) {
/* 469 */         array[(i++)] = current;
/*     */       }
/*     */     }
/* 472 */     if (array.length > size) {
/* 473 */       array[size] = null;
/*     */     }
/* 475 */     return array;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<E> uniqueSet()
/*     */   {
/* 484 */     if (this.uniqueSet == null) {
/* 485 */       this.uniqueSet = UnmodifiableSet.decorate(this.map.keySet());
/*     */     }
/* 487 */     return this.uniqueSet;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void doWriteObject(ObjectOutputStream out)
/*     */     throws IOException
/*     */   {
/* 498 */     out.writeInt(this.map.size());
/* 499 */     for (Iterator<Map.Entry<E, MutableInteger>> it = this.map.entrySet().iterator(); it.hasNext();) {
/* 500 */       Map.Entry<E, MutableInteger> entry = (Map.Entry)it.next();
/* 501 */       out.writeObject(entry.getKey());
/* 502 */       out.writeInt(((MutableInteger)entry.getValue()).value);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void doReadObject(Map<E, MutableInteger> map, ObjectInputStream in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 515 */     this.map = map;
/* 516 */     int entrySize = in.readInt();
/* 517 */     for (int i = 0; i < entrySize; i++) {
/* 518 */       E obj = in.readObject();
/* 519 */       int count = in.readInt();
/* 520 */       map.put(obj, new MutableInteger(count));
/* 521 */       this.size += count;
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
/*     */   public boolean equals(Object object)
/*     */   {
/* 535 */     if (object == this) {
/* 536 */       return true;
/*     */     }
/* 538 */     if (!(object instanceof Bag)) {
/* 539 */       return false;
/*     */     }
/* 541 */     Bag other = (Bag)object;
/* 542 */     if (other.size() != size()) {
/* 543 */       return false;
/*     */     }
/* 545 */     for (Iterator<E> it = this.map.keySet().iterator(); it.hasNext();) {
/* 546 */       E element = it.next();
/* 547 */       if (other.getCount(element) != getCount(element)) {
/* 548 */         return false;
/*     */       }
/*     */     }
/* 551 */     return true;
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
/*     */   public int hashCode()
/*     */   {
/* 564 */     int total = 0;
/* 565 */     for (Iterator<Map.Entry<E, MutableInteger>> it = this.map.entrySet().iterator(); it.hasNext();) {
/* 566 */       Map.Entry<E, MutableInteger> entry = (Map.Entry)it.next();
/* 567 */       Object element = entry.getKey();
/* 568 */       Integer count = Integer.valueOf(((MutableInteger)entry.getValue()).value);
/* 569 */       total += ((element == null ? 0 : element.hashCode()) ^ count.intValue());
/*     */     }
/* 571 */     return total;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 580 */     if (size() == 0) {
/* 581 */       return "[]";
/*     */     }
/* 583 */     StringBuffer buf = new StringBuffer();
/* 584 */     buf.append('[');
/* 585 */     Iterator<E> it = uniqueSet().iterator();
/* 586 */     while (it.hasNext()) {
/* 587 */       E current = it.next();
/* 588 */       int count = getCount(current);
/* 589 */       buf.append(count);
/* 590 */       buf.append(':');
/* 591 */       buf.append(current);
/* 592 */       if (it.hasNext()) {
/* 593 */         buf.append(',');
/*     */       }
/*     */     }
/* 596 */     buf.append(']');
/* 597 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/bag/AbstractMapBag.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */