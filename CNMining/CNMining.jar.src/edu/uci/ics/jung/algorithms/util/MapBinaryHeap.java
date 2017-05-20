/*     */ package edu.uci.ics.jung.algorithms.util;
/*     */ 
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Queue;
/*     */ import java.util.Vector;
/*     */ import org.apache.commons.collections15.IteratorUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MapBinaryHeap<T>
/*     */   extends AbstractCollection<T>
/*     */   implements Queue<T>
/*     */ {
/*  43 */   private Vector<T> heap = new Vector();
/*  44 */   private Map<T, Integer> object_indices = new HashMap();
/*     */   
/*     */ 
/*     */   private Comparator<T> comp;
/*     */   
/*     */   private static final int TOP = 0;
/*     */   
/*     */ 
/*     */   public MapBinaryHeap(Comparator<T> comp)
/*     */   {
/*  54 */     initialize(comp);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MapBinaryHeap()
/*     */   {
/*  64 */     initialize(new ComparableComparator(null));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MapBinaryHeap(Collection<T> c)
/*     */   {
/*  75 */     this();
/*  76 */     addAll(c);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MapBinaryHeap(Collection<T> c, Comparator<T> comp)
/*     */   {
/*  86 */     this(comp);
/*  87 */     addAll(c);
/*     */   }
/*     */   
/*     */   private void initialize(Comparator<T> comp)
/*     */   {
/*  92 */     this.comp = comp;
/*  93 */     clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 102 */     this.object_indices.clear();
/* 103 */     this.heap.clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean add(T o)
/*     */   {
/* 112 */     int i = this.heap.size();
/* 113 */     this.heap.setSize(i + 1);
/* 114 */     percolateUp(i, o);
/* 115 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 125 */     return this.heap.isEmpty();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public T peek()
/*     */   {
/* 134 */     if (this.heap.size() > 0) {
/* 135 */       return (T)this.heap.elementAt(0);
/*     */     }
/* 137 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @Deprecated
/*     */   public T pop()
/*     */     throws NoSuchElementException
/*     */   {
/* 148 */     return (T)remove();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 157 */     return this.heap.size();
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
/*     */   public void update(T o)
/*     */   {
/* 172 */     int cur = ((Integer)this.object_indices.get(o)).intValue();
/* 173 */     int new_idx = percolateUp(cur, o);
/* 174 */     percolateDown(new_idx);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean contains(Object o)
/*     */   {
/* 183 */     return this.object_indices.containsKey(o);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void percolateDown(int cur)
/*     */   {
/* 194 */     int left = lChild(cur);
/* 195 */     int right = rChild(cur);
/*     */     int smallest;
/*     */     int smallest;
/* 198 */     if ((left < this.heap.size()) && (this.comp.compare(this.heap.elementAt(left), this.heap.elementAt(cur)) < 0))
/*     */     {
/* 200 */       smallest = left;
/*     */     } else {
/* 202 */       smallest = cur;
/*     */     }
/*     */     
/* 205 */     if ((right < this.heap.size()) && (this.comp.compare(this.heap.elementAt(right), this.heap.elementAt(smallest)) < 0))
/*     */     {
/* 207 */       smallest = right;
/*     */     }
/*     */     
/* 210 */     if (cur != smallest)
/*     */     {
/* 212 */       swap(cur, smallest);
/* 213 */       percolateDown(smallest);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int percolateUp(int cur, T o)
/*     */   {
/* 224 */     int i = cur;
/*     */     
/* 226 */     while ((i > 0) && (this.comp.compare(this.heap.elementAt(parent(i)), o) > 0))
/*     */     {
/* 228 */       T parentElt = this.heap.elementAt(parent(i));
/* 229 */       this.heap.setElementAt(parentElt, i);
/* 230 */       this.object_indices.put(parentElt, new Integer(i));
/* 231 */       i = parent(i);
/*     */     }
/*     */     
/*     */ 
/* 235 */     this.object_indices.put(o, new Integer(i));
/* 236 */     this.heap.setElementAt(o, i);
/*     */     
/* 238 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int lChild(int i)
/*     */   {
/* 250 */     return (i << 1) + 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int rChild(int i)
/*     */   {
/* 262 */     return (i << 1) + 2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int parent(int i)
/*     */   {
/* 273 */     return i - 1 >> 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void swap(int i, int j)
/*     */   {
/* 284 */     T iElt = this.heap.elementAt(i);
/* 285 */     T jElt = this.heap.elementAt(j);
/*     */     
/* 287 */     this.heap.setElementAt(jElt, i);
/* 288 */     this.object_indices.put(jElt, new Integer(i));
/*     */     
/* 290 */     this.heap.setElementAt(iElt, j);
/* 291 */     this.object_indices.put(iElt, new Integer(j));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private class ComparableComparator
/*     */     implements Comparator<T>
/*     */   {
/*     */     private ComparableComparator() {}
/*     */     
/*     */ 
/*     */ 
/*     */     public int compare(T arg0, T arg1)
/*     */     {
/* 306 */       if ((!(arg0 instanceof Comparable)) || (!(arg1 instanceof Comparable))) {
/* 307 */         throw new IllegalArgumentException("Arguments must be Comparable");
/*     */       }
/* 309 */       return ((Comparable)arg0).compareTo(arg1);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator<T> iterator()
/*     */   {
/* 320 */     return IteratorUtils.unmodifiableIterator(this.heap.iterator());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean remove(Object o)
/*     */   {
/* 329 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean removeAll(Collection<?> c)
/*     */   {
/* 338 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean retainAll(Collection<?> c)
/*     */   {
/* 347 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public T element() throws NoSuchElementException
/*     */   {
/* 352 */     T top = peek();
/* 353 */     if (top == null)
/* 354 */       throw new NoSuchElementException();
/* 355 */     return top;
/*     */   }
/*     */   
/*     */   public boolean offer(T o)
/*     */   {
/* 360 */     return add(o);
/*     */   }
/*     */   
/*     */   public T poll()
/*     */   {
/* 365 */     T top = peek();
/* 366 */     if (top != null)
/*     */     {
/* 368 */       T bottom_elt = this.heap.lastElement();
/* 369 */       this.heap.setElementAt(bottom_elt, 0);
/* 370 */       this.object_indices.put(bottom_elt, new Integer(0));
/*     */       
/* 372 */       this.heap.setSize(this.heap.size() - 1);
/* 373 */       if (this.heap.size() > 1) {
/* 374 */         percolateDown(0);
/*     */       }
/* 376 */       this.object_indices.remove(top);
/*     */     }
/* 378 */     return top;
/*     */   }
/*     */   
/*     */   public T remove()
/*     */   {
/* 383 */     T top = poll();
/* 384 */     if (top == null)
/* 385 */       throw new NoSuchElementException();
/* 386 */     return top;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/util/MapBinaryHeap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */