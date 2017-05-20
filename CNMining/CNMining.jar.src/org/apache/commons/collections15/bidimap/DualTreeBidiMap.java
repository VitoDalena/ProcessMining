/*     */ package org.apache.commons.collections15.bidimap;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.SortedMap;
/*     */ import java.util.TreeMap;
/*     */ import org.apache.commons.collections15.BidiMap;
/*     */ import org.apache.commons.collections15.OrderedBidiMap;
/*     */ import org.apache.commons.collections15.OrderedMap;
/*     */ import org.apache.commons.collections15.OrderedMapIterator;
/*     */ import org.apache.commons.collections15.ResettableIterator;
/*     */ import org.apache.commons.collections15.SortedBidiMap;
/*     */ import org.apache.commons.collections15.map.AbstractSortedMapDecorator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DualTreeBidiMap<K, V>
/*     */   extends AbstractDualBidiMap<K, V>
/*     */   implements SortedBidiMap<K, V>, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 721969328361809L;
/*     */   protected final Comparator<? super K> comparator;
/*     */   
/*     */   public DualTreeBidiMap()
/*     */   {
/*  61 */     super(new TreeMap(), new TreeMap());
/*  62 */     this.comparator = null;
/*     */   }
/*     */   
/*     */   public static <E> DualTreeBidiMap<E, E> createTwoWayBidiMap(Comparator<? super E> comparator) {
/*  66 */     return new DualTreeBidiMap(comparator, comparator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DualTreeBidiMap(Map<? extends K, ? extends V> map)
/*     */   {
/*  76 */     super(new TreeMap(), new TreeMap());
/*  77 */     putAll(map);
/*  78 */     this.comparator = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DualTreeBidiMap(Comparator<? super K> comparator, Comparator<? super V> inverseComparator)
/*     */   {
/*  87 */     super(new TreeMap(comparator), new TreeMap(inverseComparator));
/*  88 */     this.comparator = comparator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected DualTreeBidiMap(Map<K, V> normalMap, Map<V, K> reverseMap, BidiMap<V, K> inverseBidiMap)
/*     */   {
/*  99 */     super(normalMap, reverseMap, inverseBidiMap);
/* 100 */     this.comparator = ((SortedMap)normalMap).comparator();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected <K, V> BidiMap<K, V> createBidiMap(Map<K, V> normalMap, Map<V, K> reverseMap, BidiMap<V, K> inverseMap)
/*     */   {
/* 112 */     return new DualTreeBidiMap(normalMap, reverseMap, inverseMap);
/*     */   }
/*     */   
/*     */   public Comparator<? super K> comparator()
/*     */   {
/* 117 */     return ((SortedMap)this.forwardMap).comparator();
/*     */   }
/*     */   
/*     */   public K firstKey() {
/* 121 */     return (K)((SortedMap)this.forwardMap).firstKey();
/*     */   }
/*     */   
/*     */   public K lastKey() {
/* 125 */     return (K)((SortedMap)this.forwardMap).lastKey();
/*     */   }
/*     */   
/*     */   public K nextKey(K key) {
/* 129 */     if (isEmpty()) {
/* 130 */       return null;
/*     */     }
/* 132 */     if ((this.forwardMap instanceof OrderedMap)) {
/* 133 */       return (K)((OrderedMap)this.forwardMap).nextKey(key);
/*     */     }
/* 135 */     SortedMap sm = (SortedMap)this.forwardMap;
/* 136 */     Iterator<K> it = sm.tailMap(key).keySet().iterator();
/* 137 */     it.next();
/* 138 */     if (it.hasNext()) {
/* 139 */       return (K)it.next();
/*     */     }
/* 141 */     return null;
/*     */   }
/*     */   
/*     */   public K previousKey(K key) {
/* 145 */     if (isEmpty()) {
/* 146 */       return null;
/*     */     }
/* 148 */     if ((this.forwardMap instanceof OrderedMap)) {
/* 149 */       return (K)((OrderedMap)this.forwardMap).previousKey(key);
/*     */     }
/* 151 */     SortedMap<K, V> sm = (SortedMap)this.forwardMap;
/* 152 */     SortedMap<K, V> hm = sm.headMap(key);
/* 153 */     if (hm.isEmpty()) {
/* 154 */       return null;
/*     */     }
/* 156 */     return (K)hm.lastKey();
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
/*     */   public OrderedMapIterator<K, V> orderedMapIterator()
/*     */   {
/* 169 */     return new BidiOrderedMapIterator(this);
/*     */   }
/*     */   
/*     */   public SortedBidiMap<V, K> inverseSortedBidiMap() {
/* 173 */     return (SortedBidiMap)inverseBidiMap();
/*     */   }
/*     */   
/*     */   public OrderedBidiMap<V, K> inverseOrderedBidiMap() {
/* 177 */     return (OrderedBidiMap)inverseBidiMap();
/*     */   }
/*     */   
/*     */   public SortedMap<K, V> headMap(K toKey)
/*     */   {
/* 182 */     SortedMap<K, V> sub = ((SortedMap)this.forwardMap).headMap(toKey);
/* 183 */     return new ViewMap(this, sub);
/*     */   }
/*     */   
/*     */   public SortedMap<K, V> tailMap(K fromKey) {
/* 187 */     SortedMap<K, V> sub = ((SortedMap)this.forwardMap).tailMap(fromKey);
/* 188 */     return new ViewMap(this, sub);
/*     */   }
/*     */   
/*     */   public SortedMap<K, V> subMap(K fromKey, K toKey) {
/* 192 */     SortedMap<K, V> sub = ((SortedMap)this.forwardMap).subMap(fromKey, toKey);
/* 193 */     return new ViewMap(this, sub);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static class ViewMap<K, V>
/*     */     extends AbstractSortedMapDecorator<K, V>
/*     */   {
/*     */     final DualTreeBidiMap<K, V> bidi;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected ViewMap(DualTreeBidiMap<K, V> bidi, SortedMap<K, V> sm)
/*     */     {
/* 216 */       super();
/* 217 */       this.bidi = ((DualTreeBidiMap)this.map);
/*     */     }
/*     */     
/*     */     public boolean containsValue(Object value)
/*     */     {
/* 222 */       return this.bidi.forwardMap.containsValue(value);
/*     */     }
/*     */     
/*     */     public void clear()
/*     */     {
/* 227 */       for (Iterator it = keySet().iterator(); it.hasNext();) {
/* 228 */         it.next();
/* 229 */         it.remove();
/*     */       }
/*     */     }
/*     */     
/*     */     public SortedMap<K, V> headMap(K toKey) {
/* 234 */       return new ViewMap(this.bidi, super.headMap(toKey));
/*     */     }
/*     */     
/*     */     public SortedMap<K, V> tailMap(K fromKey) {
/* 238 */       return new ViewMap(this.bidi, super.tailMap(fromKey));
/*     */     }
/*     */     
/*     */     public SortedMap<K, V> subMap(K fromKey, K toKey) {
/* 242 */       return new ViewMap(this.bidi, super.subMap(fromKey, toKey));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static class BidiOrderedMapIterator<K, V>
/*     */     implements OrderedMapIterator<K, V>, ResettableIterator<K>
/*     */   {
/*     */     protected final AbstractDualBidiMap<K, V> parent;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     protected ListIterator<Map.Entry<K, V>> iterator;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 263 */     private Map.Entry<K, V> last = null;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected BidiOrderedMapIterator(AbstractDualBidiMap<K, V> parent)
/*     */     {
/* 272 */       this.parent = parent;
/* 273 */       this.iterator = new ArrayList(parent.entrySet()).listIterator();
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 277 */       return this.iterator.hasNext();
/*     */     }
/*     */     
/*     */     public K next() {
/* 281 */       this.last = ((Map.Entry)this.iterator.next());
/* 282 */       return (K)this.last.getKey();
/*     */     }
/*     */     
/*     */     public boolean hasPrevious() {
/* 286 */       return this.iterator.hasPrevious();
/*     */     }
/*     */     
/*     */     public K previous() {
/* 290 */       this.last = ((Map.Entry)this.iterator.previous());
/* 291 */       return (K)this.last.getKey();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 295 */       this.iterator.remove();
/* 296 */       this.parent.remove(this.last.getKey());
/* 297 */       this.last = null;
/*     */     }
/*     */     
/*     */     public K getKey() {
/* 301 */       if (this.last == null) {
/* 302 */         throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
/*     */       }
/* 304 */       return (K)this.last.getKey();
/*     */     }
/*     */     
/*     */     public V getValue() {
/* 308 */       if (this.last == null) {
/* 309 */         throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
/*     */       }
/* 311 */       return (V)this.last.getValue();
/*     */     }
/*     */     
/*     */     public V setValue(V value) {
/* 315 */       if (this.last == null) {
/* 316 */         throw new IllegalStateException("Iterator setValue() can only be called after next() and before remove()");
/*     */       }
/* 318 */       if ((this.parent.inverseMap.containsKey(value)) && (this.parent.inverseMap.get(value) != this.last.getKey())) {
/* 319 */         throw new IllegalArgumentException("Cannot use setValue() when the object being set is already in the map");
/*     */       }
/* 321 */       return (V)this.parent.put(this.last.getKey(), value);
/*     */     }
/*     */     
/*     */     public void reset() {
/* 325 */       this.iterator = new ArrayList(this.parent.entrySet()).listIterator();
/* 326 */       this.last = null;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 330 */       if (this.last != null) {
/* 331 */         return "MapIterator[" + getKey() + "=" + getValue() + "]";
/*     */       }
/* 333 */       return "MapIterator[]";
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void writeObject(ObjectOutputStream out)
/*     */     throws IOException
/*     */   {
/* 341 */     out.defaultWriteObject();
/* 342 */     out.writeObject(this.forwardMap);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 346 */     in.defaultReadObject();
/* 347 */     this.forwardMap = new TreeMap(this.comparator);
/* 348 */     this.inverseMap = new TreeMap();
/* 349 */     Map<K, V> map = (Map)in.readObject();
/* 350 */     putAll(map);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/bidimap/DualTreeBidiMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */