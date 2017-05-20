/*      */ package org.apache.commons.collections15.bidimap;
/*      */ 
/*      */ import java.util.AbstractSet;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.collections15.BidiMap;
/*      */ import org.apache.commons.collections15.KeyValue;
/*      */ import org.apache.commons.collections15.MapIterator;
/*      */ import org.apache.commons.collections15.OrderedBidiMap;
/*      */ import org.apache.commons.collections15.OrderedIterator;
/*      */ import org.apache.commons.collections15.OrderedMapIterator;
/*      */ import org.apache.commons.collections15.iterators.EmptyOrderedMapIterator;
/*      */ import org.apache.commons.collections15.keyvalue.UnmodifiableMapEntry;
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
/*      */ public class TreeBidiMap<K extends Comparable, V extends Comparable>
/*      */   implements OrderedBidiMap<K, V>
/*      */ {
/*      */   private static final int KEY = 0;
/*      */   private static final int VALUE = 1;
/*      */   private static final int MAPENTRY = 2;
/*      */   private static final int INVERSEMAPENTRY = 3;
/*      */   private static final int SUM_OF_INDICES = 1;
/*      */   private static final int FIRST_INDEX = 0;
/*      */   private static final int NUMBER_OF_INDICES = 2;
/*   73 */   private static final String[] dataName = { "key", "value" };
/*      */   
/*   75 */   private Node<K, V>[] rootNode = new Node[2];
/*   76 */   private int nodeCount = 0;
/*   77 */   private int modifications = 0;
/*      */   private Set<K> keySet;
/*      */   private Set<V> valuesSet;
/*      */   private Set entrySet;
/*   81 */   private Inverse<K, V> inverse = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TreeBidiMap() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TreeBidiMap(Map map)
/*      */   {
/*  101 */     putAll(map);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int size()
/*      */   {
/*  111 */     return this.nodeCount;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isEmpty()
/*      */   {
/*  120 */     return this.nodeCount == 0;
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
/*      */   public boolean containsKey(Object key)
/*      */   {
/*  134 */     checkKey(key);
/*  135 */     return lookup((Comparable)key, 0) != null;
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
/*      */   public boolean containsValue(Object value)
/*      */   {
/*  149 */     checkValue(value);
/*  150 */     return lookup((Comparable)value, 1) != null;
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
/*      */   public V get(Object key)
/*      */   {
/*  166 */     return doGetValue(key);
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
/*      */   public V put(K key, V value)
/*      */   {
/*  194 */     return doPutByKey(key, value);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void putAll(Map<? extends K, ? extends V> map)
/*      */   {
/*  205 */     Iterator it = map.entrySet().iterator();
/*  206 */     while (it.hasNext()) {
/*  207 */       Map.Entry entry = (Map.Entry)it.next();
/*  208 */       put((Comparable)entry.getKey(), (Comparable)entry.getValue());
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
/*      */   public V remove(Object key)
/*      */   {
/*  224 */     return doRemoveByKey((Comparable)key);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void clear()
/*      */   {
/*  231 */     modify();
/*      */     
/*  233 */     this.nodeCount = 0;
/*  234 */     this.rootNode[0] = null;
/*  235 */     this.rootNode[1] = null;
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
/*      */   public K getKey(Object value)
/*      */   {
/*  252 */     return doGetKey((Comparable)value);
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
/*      */   public K removeValue(Object value)
/*      */   {
/*  267 */     return doRemoveByValue((Comparable)value);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public K firstKey()
/*      */   {
/*  278 */     if (this.nodeCount == 0) {
/*  279 */       throw new NoSuchElementException("Map is empty");
/*      */     }
/*  281 */     return leastNode(this.rootNode[0], 0).getKey();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public K lastKey()
/*      */   {
/*  291 */     if (this.nodeCount == 0) {
/*  292 */       throw new NoSuchElementException("Map is empty");
/*      */     }
/*  294 */     return greatestNode(this.rootNode[0], 0).getKey();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public K nextKey(K key)
/*      */   {
/*  306 */     checkKey(key);
/*  307 */     Node<K, V> node = nextGreater(lookup(key, 0), 0);
/*  308 */     return node == null ? null : node.getKey();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public K previousKey(K key)
/*      */   {
/*  320 */     checkKey(key);
/*  321 */     Node<K, V> node = nextSmaller(lookup(key, 0), 0);
/*  322 */     return node == null ? null : node.getKey();
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
/*      */   public Set<K> keySet()
/*      */   {
/*  339 */     if (this.keySet == null) {
/*  340 */       this.keySet = new View(this, 0, 0);
/*      */     }
/*  342 */     return this.keySet;
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
/*      */   public Set<V> values()
/*      */   {
/*  360 */     if (this.valuesSet == null) {
/*  361 */       this.valuesSet = new View(this, 0, 1);
/*      */     }
/*  363 */     return this.valuesSet;
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
/*      */   public Set<Map.Entry<K, V>> entrySet()
/*      */   {
/*  382 */     if (this.entrySet == null) {
/*  383 */       return new EntryView(this, 0, 2);
/*      */     }
/*  385 */     return this.entrySet;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MapIterator<K, V> mapIterator()
/*      */   {
/*  397 */     if (isEmpty()) {
/*  398 */       return EmptyOrderedMapIterator.INSTANCE;
/*      */     }
/*  400 */     return new ViewMapIterator(this, 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OrderedMapIterator<K, V> orderedMapIterator()
/*      */   {
/*  411 */     if (isEmpty()) {
/*  412 */       return EmptyOrderedMapIterator.INSTANCE;
/*      */     }
/*  414 */     return new ViewMapIterator(this, 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BidiMap<V, K> inverseBidiMap()
/*      */   {
/*  424 */     return inverseOrderedBidiMap();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OrderedBidiMap<V, K> inverseOrderedBidiMap()
/*      */   {
/*  433 */     if (this.inverse == null) {
/*  434 */       this.inverse = new Inverse(this);
/*      */     }
/*  436 */     return this.inverse;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Object obj)
/*      */   {
/*  447 */     return doEquals(obj, 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int hashCode()
/*      */   {
/*  456 */     return doHashCode(0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/*  465 */     return doToString(0);
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
/*      */   private K doGetKey(Comparable value)
/*      */   {
/*  479 */     checkNonNullComparable(value);
/*  480 */     Node<K, V> node = lookup(value, 1);
/*  481 */     if (node == null) {
/*  482 */       return null;
/*      */     }
/*  484 */     return node.getKey();
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
/*      */   private V doGetValue(Object key)
/*      */   {
/*  497 */     checkNonNullComparable(key);
/*  498 */     Node<K, V> node = lookup((Comparable)key, 0);
/*  499 */     if (node == null) {
/*  500 */       return null;
/*      */     }
/*  502 */     return node.getValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private V doPutByKey(K key, V value)
/*      */   {
/*  514 */     checkKeyAndValue(key, value);
/*      */     
/*      */ 
/*  517 */     V prev = doGetValue(key);
/*      */     
/*  519 */     doPut(key, value);
/*  520 */     return prev;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private K doPutByValue(K key, V value)
/*      */   {
/*  531 */     checkKeyAndValue(key, value);
/*      */     
/*      */ 
/*  534 */     K prev = doGetKey(value);
/*      */     
/*  536 */     doPut(key, value);
/*  537 */     return prev;
/*      */   }
/*      */   
/*      */   private void doPut(K key, V value) {
/*  541 */     doRemoveByKey(key);
/*  542 */     doRemoveByValue(value);
/*  543 */     Node<K, V> node = this.rootNode[0];
/*  544 */     if (node == null)
/*      */     {
/*  546 */       Node<K, V> root = new Node(key, value);
/*  547 */       this.rootNode[0] = root;
/*  548 */       this.rootNode[1] = root;
/*  549 */       grow();
/*      */     }
/*      */     else
/*      */     {
/*      */       for (;;) {
/*  554 */         int cmp = compare(key, node.getKey());
/*      */         
/*  556 */         if (cmp == 0)
/*      */         {
/*  558 */           throw new IllegalArgumentException("Cannot store a duplicate key (\"" + key + "\") in this Map"); }
/*  559 */         if (cmp < 0) {
/*  560 */           if (node.getLeft(0) != null) {
/*  561 */             node = node.getLeft(0);
/*      */           } else {
/*  563 */             Node<K, V> newNode = new Node(key, value);
/*      */             
/*  565 */             insertValue(newNode);
/*  566 */             node.setLeft(newNode, 0);
/*  567 */             newNode.setParent(node, 0);
/*  568 */             doRedBlackInsert(newNode, 0);
/*  569 */             grow();
/*      */             
/*  571 */             break;
/*      */           }
/*      */         }
/*  574 */         else if (node.getRight(0) != null) {
/*  575 */           node = node.getRight(0);
/*      */         } else {
/*  577 */           Node<K, V> newNode = new Node(key, value);
/*      */           
/*  579 */           insertValue(newNode);
/*  580 */           node.setRight(newNode, 0);
/*  581 */           newNode.setParent(node, 0);
/*  582 */           doRedBlackInsert(newNode, 0);
/*  583 */           grow();
/*      */           
/*  585 */           break;
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
/*      */   private V doRemoveByKey(K key)
/*      */   {
/*  601 */     Node<K, V> node = lookup(key, 0);
/*  602 */     V rval = null;
/*  603 */     if (node != null) {
/*  604 */       rval = node.getValue();
/*  605 */       doRedBlackDelete(node);
/*      */     }
/*  607 */     return rval;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private K doRemoveByValue(Comparable value)
/*      */   {
/*  619 */     Node<K, V> node = lookup(value, 1);
/*  620 */     K rval = null;
/*  621 */     if (node != null) {
/*  622 */       rval = node.getKey();
/*  623 */       doRedBlackDelete(node);
/*      */     }
/*  625 */     return rval;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Node<K, V> lookup(Comparable data, int index)
/*      */   {
/*  637 */     Node<K, V> rval = null;
/*  638 */     Node<K, V> node = this.rootNode[index];
/*      */     
/*  640 */     while (node != null) {
/*  641 */       int cmp = compare(data, node.getData(index));
/*  642 */       if (cmp == 0) {
/*  643 */         rval = node;
/*  644 */         break;
/*      */       }
/*  646 */       node = cmp < 0 ? node.getLeft(index) : node.getRight(index);
/*      */     }
/*      */     
/*      */ 
/*  650 */     return rval;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Node<K, V> nextGreater(Node<K, V> node, int index)
/*      */   {
/*  661 */     Node<K, V> rval = null;
/*  662 */     if (node == null) {
/*  663 */       rval = null;
/*  664 */     } else if (node.getRight(index) != null)
/*      */     {
/*      */ 
/*  667 */       rval = leastNode(node.getRight(index), index);
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*      */ 
/*  675 */       Node<K, V> parent = node.getParent(index);
/*  676 */       Node<K, V> child = node;
/*      */       
/*  678 */       while ((parent != null) && (child == parent.getRight(index))) {
/*  679 */         child = parent;
/*  680 */         parent = parent.getParent(index);
/*      */       }
/*  682 */       rval = parent;
/*      */     }
/*  684 */     return rval;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Node<K, V> nextSmaller(Node<K, V> node, int index)
/*      */   {
/*  695 */     Node<K, V> rval = null;
/*  696 */     if (node == null) {
/*  697 */       rval = null;
/*  698 */     } else if (node.getLeft(index) != null)
/*      */     {
/*      */ 
/*  701 */       rval = greatestNode(node.getLeft(index), index);
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*      */ 
/*  709 */       Node<K, V> parent = node.getParent(index);
/*  710 */       Node<K, V> child = node;
/*      */       
/*  712 */       while ((parent != null) && (child == parent.getLeft(index))) {
/*  713 */         child = parent;
/*  714 */         parent = parent.getParent(index);
/*      */       }
/*  716 */       rval = parent;
/*      */     }
/*  718 */     return rval;
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
/*      */   private static int oppositeIndex(int index)
/*      */   {
/*  732 */     return 1 - index;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static int compare(Comparable o1, Comparable o2)
/*      */   {
/*  744 */     return o1.compareTo(o2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static <K extends Comparable, V extends Comparable> Node<K, V> leastNode(Node<K, V> node, int index)
/*      */   {
/*  756 */     Node<K, V> rval = node;
/*  757 */     if (rval != null) {
/*  758 */       while (rval.getLeft(index) != null) {
/*  759 */         rval = rval.getLeft(index);
/*      */       }
/*      */     }
/*  762 */     return rval;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static <K extends Comparable, V extends Comparable> Node<K, V> greatestNode(Node<K, V> node, int index)
/*      */   {
/*  773 */     Node<K, V> rval = node;
/*  774 */     if (rval != null) {
/*  775 */       while (rval.getRight(index) != null) {
/*  776 */         rval = rval.getRight(index);
/*      */       }
/*      */     }
/*  779 */     return rval;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void copyColor(Node from, Node to, int index)
/*      */   {
/*  791 */     if (to != null) {
/*  792 */       if (from == null)
/*      */       {
/*  794 */         to.setBlack(index);
/*      */       } else {
/*  796 */         to.copyColor(from, index);
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
/*      */   private static boolean isRed(Node node, int index)
/*      */   {
/*  809 */     return node == null ? false : node.isRed(index);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static boolean isBlack(Node node, int index)
/*      */   {
/*  820 */     return node == null ? true : node.isBlack(index);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void makeRed(Node node, int index)
/*      */   {
/*  830 */     if (node != null) {
/*  831 */       node.setRed(index);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static <K extends Comparable, V extends Comparable> void makeBlack(Node<K, V> node, int index)
/*      */   {
/*  842 */     if (node != null) {
/*  843 */       node.setBlack(index);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static <K extends Comparable, V extends Comparable> Node<K, V> getGrandParent(Node<K, V> node, int index)
/*      */   {
/*  855 */     return getParent(getParent(node, index), index);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static <K extends Comparable, V extends Comparable> Node<K, V> getParent(Node<K, V> node, int index)
/*      */   {
/*  866 */     return node == null ? null : node.getParent(index);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static <K extends Comparable, V extends Comparable> Node<K, V> getRightChild(Node<K, V> node, int index)
/*      */   {
/*  877 */     return node == null ? null : node.getRight(index);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static <K extends Comparable, V extends Comparable> Node<K, V> getLeftChild(Node<K, V> node, int index)
/*      */   {
/*  888 */     return node == null ? null : node.getLeft(index);
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
/*      */   private static <K extends Comparable, V extends Comparable> boolean isLeftChild(Node<K, V> node, int index)
/*      */   {
/*  903 */     return node == null;
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
/*      */   private static <K extends Comparable, V extends Comparable> boolean isRightChild(Node<K, V> node, int index)
/*      */   {
/*  918 */     return node == null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void rotateLeft(Node<K, V> node, int index)
/*      */   {
/*  928 */     Node<K, V> rightChild = node.getRight(index);
/*  929 */     node.setRight(Node.access$000(rightChild, index), index);
/*      */     
/*  931 */     if (rightChild.getLeft(index) != null) {
/*  932 */       Node.access$000(rightChild, index).setParent(node, index);
/*      */     }
/*  934 */     rightChild.setParent(Node.access$600(node, index), index);
/*      */     
/*  936 */     if (node.getParent(index) == null)
/*      */     {
/*  938 */       this.rootNode[index] = rightChild;
/*  939 */     } else if (Node.access$600(node, index).getLeft(index) == node) {
/*  940 */       Node.access$600(node, index).setLeft(rightChild, index);
/*      */     } else {
/*  942 */       Node.access$600(node, index).setRight(rightChild, index);
/*      */     }
/*      */     
/*  945 */     rightChild.setLeft(node, index);
/*  946 */     node.setParent(rightChild, index);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void rotateRight(Node<K, V> node, int index)
/*      */   {
/*  956 */     Node<K, V> leftChild = node.getLeft(index);
/*  957 */     node.setLeft(Node.access$300(leftChild, index), index);
/*  958 */     if (leftChild.getRight(index) != null) {
/*  959 */       Node.access$300(leftChild, index).setParent(node, index);
/*      */     }
/*  961 */     leftChild.setParent(Node.access$600(node, index), index);
/*      */     
/*  963 */     if (node.getParent(index) == null)
/*      */     {
/*  965 */       this.rootNode[index] = leftChild;
/*  966 */     } else if (Node.access$600(node, index).getRight(index) == node) {
/*  967 */       Node.access$600(node, index).setRight(leftChild, index);
/*      */     } else {
/*  969 */       Node.access$600(node, index).setLeft(leftChild, index);
/*      */     }
/*      */     
/*  972 */     leftChild.setRight(node, index);
/*  973 */     node.setParent(leftChild, index);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void doRedBlackInsert(Node<K, V> insertedNode, int index)
/*      */   {
/*  984 */     Node<K, V> currentNode = insertedNode;
/*  985 */     makeRed(currentNode, index);
/*      */     
/*  987 */     while ((currentNode != null) && (currentNode != this.rootNode[index]) && (isRed(currentNode.getParent(index), index))) {
/*  988 */       if (isLeftChild(getParent(currentNode, index), index)) {
/*  989 */         Node<K, V> y = getRightChild(getGrandParent(currentNode, index), index);
/*      */         
/*  991 */         if (isRed(y, index)) {
/*  992 */           makeBlack(getParent(currentNode, index), index);
/*  993 */           makeBlack(y, index);
/*  994 */           makeRed(getGrandParent(currentNode, index), index);
/*      */           
/*  996 */           currentNode = getGrandParent(currentNode, index);
/*      */         } else {
/*  998 */           if (isRightChild(currentNode, index)) {
/*  999 */             currentNode = getParent(currentNode, index);
/*      */             
/* 1001 */             rotateLeft(currentNode, index);
/*      */           }
/*      */           
/* 1004 */           makeBlack(getParent(currentNode, index), index);
/* 1005 */           makeRed(getGrandParent(currentNode, index), index);
/*      */           
/* 1007 */           if (getGrandParent(currentNode, index) != null) {
/* 1008 */             rotateRight(getGrandParent(currentNode, index), index);
/*      */           }
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 1014 */         Node<K, V> y = getLeftChild(getGrandParent(currentNode, index), index);
/*      */         
/* 1016 */         if (isRed(y, index)) {
/* 1017 */           makeBlack(getParent(currentNode, index), index);
/* 1018 */           makeBlack(y, index);
/* 1019 */           makeRed(getGrandParent(currentNode, index), index);
/*      */           
/* 1021 */           currentNode = getGrandParent(currentNode, index);
/*      */         } else {
/* 1023 */           if (isLeftChild(currentNode, index)) {
/* 1024 */             currentNode = getParent(currentNode, index);
/*      */             
/* 1026 */             rotateRight(currentNode, index);
/*      */           }
/*      */           
/* 1029 */           makeBlack(getParent(currentNode, index), index);
/* 1030 */           makeRed(getGrandParent(currentNode, index), index);
/*      */           
/* 1032 */           if (getGrandParent(currentNode, index) != null) {
/* 1033 */             rotateLeft(getGrandParent(currentNode, index), index);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1039 */     makeBlack(this.rootNode[index], index);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void doRedBlackDelete(Node<K, V> deletedNode)
/*      */   {
/* 1049 */     for (int index = 0; index < 2; index++)
/*      */     {
/*      */ 
/* 1052 */       if ((deletedNode.getLeft(index) != null) && (deletedNode.getRight(index) != null)) {
/* 1053 */         swapPosition(nextGreater(deletedNode, index), deletedNode, index);
/*      */       }
/*      */       
/* 1056 */       Node<K, V> replacement = deletedNode.getLeft(index) != null ? deletedNode.getLeft(index) : deletedNode.getRight(index);
/*      */       
/* 1058 */       if (replacement != null) {
/* 1059 */         replacement.setParent(Node.access$600(deletedNode, index), index);
/*      */         
/* 1061 */         if (deletedNode.getParent(index) == null) {
/* 1062 */           this.rootNode[index] = replacement;
/* 1063 */         } else if (deletedNode == Node.access$600(deletedNode, index).getLeft(index)) {
/* 1064 */           Node.access$600(deletedNode, index).setLeft(replacement, index);
/*      */         } else {
/* 1066 */           Node.access$600(deletedNode, index).setRight(replacement, index);
/*      */         }
/*      */         
/* 1069 */         deletedNode.setLeft(null, index);
/* 1070 */         deletedNode.setRight(null, index);
/* 1071 */         deletedNode.setParent(null, index);
/*      */         
/* 1073 */         if (isBlack(deletedNode, index)) {
/* 1074 */           doRedBlackDeleteFixup(replacement, index);
/*      */         }
/*      */         
/*      */ 
/*      */       }
/* 1079 */       else if (deletedNode.getParent(index) == null)
/*      */       {
/*      */ 
/* 1082 */         this.rootNode[index] = null;
/*      */       }
/*      */       else
/*      */       {
/* 1086 */         if (isBlack(deletedNode, index)) {
/* 1087 */           doRedBlackDeleteFixup(deletedNode, index);
/*      */         }
/*      */         
/* 1090 */         if (deletedNode.getParent(index) != null) {
/* 1091 */           if (deletedNode == Node.access$600(deletedNode, index).getLeft(index)) {
/* 1092 */             Node.access$600(deletedNode, index).setLeft(null, index);
/*      */           } else {
/* 1094 */             Node.access$600(deletedNode, index).setRight(null, index);
/*      */           }
/*      */           
/* 1097 */           deletedNode.setParent(null, index);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1102 */     shrink();
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
/*      */   private void doRedBlackDeleteFixup(Node<K, V> replacementNode, int index)
/*      */   {
/* 1115 */     Node<K, V> currentNode = replacementNode;
/*      */     
/* 1117 */     while ((currentNode != this.rootNode[index]) && (isBlack(currentNode, index))) {
/* 1118 */       if (isLeftChild(currentNode, index)) {
/* 1119 */         Node<K, V> siblingNode = getRightChild(getParent(currentNode, index), index);
/*      */         
/* 1121 */         if (isRed(siblingNode, index)) {
/* 1122 */           makeBlack(siblingNode, index);
/* 1123 */           makeRed(getParent(currentNode, index), index);
/* 1124 */           rotateLeft(getParent(currentNode, index), index);
/*      */           
/* 1126 */           siblingNode = getRightChild(getParent(currentNode, index), index);
/*      */         }
/*      */         
/* 1129 */         if ((isBlack(getLeftChild(siblingNode, index), index)) && (isBlack(getRightChild(siblingNode, index), index))) {
/* 1130 */           makeRed(siblingNode, index);
/*      */           
/* 1132 */           currentNode = getParent(currentNode, index);
/*      */         } else {
/* 1134 */           if (isBlack(getRightChild(siblingNode, index), index)) {
/* 1135 */             makeBlack(getLeftChild(siblingNode, index), index);
/* 1136 */             makeRed(siblingNode, index);
/* 1137 */             rotateRight(siblingNode, index);
/*      */             
/* 1139 */             siblingNode = getRightChild(getParent(currentNode, index), index);
/*      */           }
/*      */           
/* 1142 */           copyColor(getParent(currentNode, index), siblingNode, index);
/* 1143 */           makeBlack(getParent(currentNode, index), index);
/* 1144 */           makeBlack(getRightChild(siblingNode, index), index);
/* 1145 */           rotateLeft(getParent(currentNode, index), index);
/*      */           
/* 1147 */           currentNode = this.rootNode[index];
/*      */         }
/*      */       } else {
/* 1150 */         Node<K, V> siblingNode = getLeftChild(getParent(currentNode, index), index);
/*      */         
/* 1152 */         if (isRed(siblingNode, index)) {
/* 1153 */           makeBlack(siblingNode, index);
/* 1154 */           makeRed(getParent(currentNode, index), index);
/* 1155 */           rotateRight(getParent(currentNode, index), index);
/*      */           
/* 1157 */           siblingNode = getLeftChild(getParent(currentNode, index), index);
/*      */         }
/*      */         
/* 1160 */         if ((isBlack(getRightChild(siblingNode, index), index)) && (isBlack(getLeftChild(siblingNode, index), index))) {
/* 1161 */           makeRed(siblingNode, index);
/*      */           
/* 1163 */           currentNode = getParent(currentNode, index);
/*      */         } else {
/* 1165 */           if (isBlack(getLeftChild(siblingNode, index), index)) {
/* 1166 */             makeBlack(getRightChild(siblingNode, index), index);
/* 1167 */             makeRed(siblingNode, index);
/* 1168 */             rotateLeft(siblingNode, index);
/*      */             
/* 1170 */             siblingNode = getLeftChild(getParent(currentNode, index), index);
/*      */           }
/*      */           
/* 1173 */           copyColor(getParent(currentNode, index), siblingNode, index);
/* 1174 */           makeBlack(getParent(currentNode, index), index);
/* 1175 */           makeBlack(getLeftChild(siblingNode, index), index);
/* 1176 */           rotateRight(getParent(currentNode, index), index);
/*      */           
/* 1178 */           currentNode = this.rootNode[index];
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1183 */     makeBlack(currentNode, index);
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
/*      */   private void swapPosition(Node<K, V> x, Node<K, V> y, int index)
/*      */   {
/* 1197 */     Node<K, V> xFormerParent = x.getParent(index);
/* 1198 */     Node<K, V> xFormerLeftChild = x.getLeft(index);
/* 1199 */     Node<K, V> xFormerRightChild = x.getRight(index);
/* 1200 */     Node<K, V> yFormerParent = y.getParent(index);
/* 1201 */     Node<K, V> yFormerLeftChild = y.getLeft(index);
/* 1202 */     Node<K, V> yFormerRightChild = y.getRight(index);
/* 1203 */     boolean xWasLeftChild = (x.getParent(index) != null) && (x == Node.access$600(x, index).getLeft(index));
/* 1204 */     boolean yWasLeftChild = (y.getParent(index) != null) && (y == Node.access$600(y, index).getLeft(index));
/*      */     
/*      */ 
/* 1207 */     if (x == yFormerParent) {
/* 1208 */       x.setParent(y, index);
/*      */       
/* 1210 */       if (yWasLeftChild) {
/* 1211 */         y.setLeft(x, index);
/* 1212 */         y.setRight(xFormerRightChild, index);
/*      */       } else {
/* 1214 */         y.setRight(x, index);
/* 1215 */         y.setLeft(xFormerLeftChild, index);
/*      */       }
/*      */     } else {
/* 1218 */       x.setParent(yFormerParent, index);
/*      */       
/* 1220 */       if (yFormerParent != null) {
/* 1221 */         if (yWasLeftChild) {
/* 1222 */           yFormerParent.setLeft(x, index);
/*      */         } else {
/* 1224 */           yFormerParent.setRight(x, index);
/*      */         }
/*      */       }
/*      */       
/* 1228 */       y.setLeft(xFormerLeftChild, index);
/* 1229 */       y.setRight(xFormerRightChild, index);
/*      */     }
/*      */     
/* 1232 */     if (y == xFormerParent) {
/* 1233 */       y.setParent(x, index);
/*      */       
/* 1235 */       if (xWasLeftChild) {
/* 1236 */         x.setLeft(y, index);
/* 1237 */         x.setRight(yFormerRightChild, index);
/*      */       } else {
/* 1239 */         x.setRight(y, index);
/* 1240 */         x.setLeft(yFormerLeftChild, index);
/*      */       }
/*      */     } else {
/* 1243 */       y.setParent(xFormerParent, index);
/*      */       
/* 1245 */       if (xFormerParent != null) {
/* 1246 */         if (xWasLeftChild) {
/* 1247 */           xFormerParent.setLeft(y, index);
/*      */         } else {
/* 1249 */           xFormerParent.setRight(y, index);
/*      */         }
/*      */       }
/*      */       
/* 1253 */       x.setLeft(yFormerLeftChild, index);
/* 1254 */       x.setRight(yFormerRightChild, index);
/*      */     }
/*      */     
/*      */ 
/* 1258 */     if (x.getLeft(index) != null) {
/* 1259 */       Node.access$000(x, index).setParent(x, index);
/*      */     }
/*      */     
/* 1262 */     if (x.getRight(index) != null) {
/* 1263 */       Node.access$300(x, index).setParent(x, index);
/*      */     }
/*      */     
/* 1266 */     if (y.getLeft(index) != null) {
/* 1267 */       Node.access$000(y, index).setParent(y, index);
/*      */     }
/*      */     
/* 1270 */     if (y.getRight(index) != null) {
/* 1271 */       Node.access$300(y, index).setParent(y, index);
/*      */     }
/*      */     
/* 1274 */     x.swapColors(y, index);
/*      */     
/*      */ 
/* 1277 */     if (this.rootNode[index] == x) {
/* 1278 */       this.rootNode[index] = y;
/* 1279 */     } else if (this.rootNode[index] == y) {
/* 1280 */       this.rootNode[index] = x;
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
/*      */   private static void checkNonNullComparable(Object o)
/*      */   {
/* 1293 */     if (o == null) {
/* 1294 */       throw new NullPointerException("Cannot be null");
/*      */     }
/* 1296 */     if (!(o instanceof Comparable)) {
/* 1297 */       throw new ClassCastException("Must be Comparable");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void checkKey(Object key)
/*      */   {
/* 1309 */     checkNonNullComparable(key);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void checkValue(Object value)
/*      */   {
/* 1320 */     checkNonNullComparable(value);
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
/*      */   private static void checkKeyAndValue(Object key, Object value)
/*      */   {
/* 1333 */     checkKey(key);
/* 1334 */     checkValue(value);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void modify()
/*      */   {
/* 1343 */     this.modifications += 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private void grow()
/*      */   {
/* 1350 */     modify();
/* 1351 */     this.nodeCount += 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private void shrink()
/*      */   {
/* 1358 */     modify();
/* 1359 */     this.nodeCount -= 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void insertValue(Node<K, V> newNode)
/*      */     throws IllegalArgumentException
/*      */   {
/* 1370 */     Node<K, V> node = this.rootNode[1];
/*      */     for (;;)
/*      */     {
/* 1373 */       int cmp = compare(newNode.getData(1), node.getData(1));
/*      */       
/* 1375 */       if (cmp == 0)
/* 1376 */         throw new IllegalArgumentException("Cannot store a duplicate value (\"" + newNode.getData(1) + "\") in this Map");
/* 1377 */       if (cmp < 0) {
/* 1378 */         if (node.getLeft(1) != null) {
/* 1379 */           node = node.getLeft(1);
/*      */         } else {
/* 1381 */           node.setLeft(newNode, 1);
/* 1382 */           newNode.setParent(node, 1);
/* 1383 */           doRedBlackInsert(newNode, 1);
/*      */           
/* 1385 */           break;
/*      */         }
/*      */       }
/* 1388 */       else if (node.getRight(1) != null) {
/* 1389 */         node = node.getRight(1);
/*      */       } else {
/* 1391 */         node.setRight(newNode, 1);
/* 1392 */         newNode.setParent(node, 1);
/* 1393 */         doRedBlackInsert(newNode, 1);
/*      */         
/* 1395 */         break;
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
/*      */   private boolean doEquals(Object obj, int type)
/*      */   {
/* 1409 */     if (obj == this) {
/* 1410 */       return true;
/*      */     }
/* 1412 */     if (!(obj instanceof Map)) {
/* 1413 */       return false;
/*      */     }
/* 1415 */     Map other = (Map)obj;
/* 1416 */     if (other.size() != size()) {
/* 1417 */       return false;
/*      */     }
/*      */     
/* 1420 */     if (this.nodeCount > 0) {
/*      */       try {
/* 1422 */         for (it = new ViewMapIterator(this, type); it.hasNext();) {
/* 1423 */           Object key = it.next();
/* 1424 */           Object value = it.getValue();
/* 1425 */           if (!value.equals(other.get(key)))
/* 1426 */             return false;
/*      */         }
/*      */       } catch (ClassCastException ex) {
/*      */         MapIterator it;
/* 1430 */         return false;
/*      */       } catch (NullPointerException ex) {
/* 1432 */         return false;
/*      */       }
/*      */     }
/* 1435 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private int doHashCode(int type)
/*      */   {
/* 1444 */     int total = 0;
/* 1445 */     MapIterator it; if (this.nodeCount > 0) {
/* 1446 */       for (it = new ViewMapIterator(this, type); it.hasNext();) {
/* 1447 */         Object key = it.next();
/* 1448 */         Object value = it.getValue();
/* 1449 */         total += (key.hashCode() ^ value.hashCode());
/*      */       }
/*      */     }
/* 1452 */     return total;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private String doToString(int type)
/*      */   {
/* 1461 */     if (this.nodeCount == 0) {
/* 1462 */       return "{}";
/*      */     }
/* 1464 */     StringBuffer buf = new StringBuffer(this.nodeCount * 32);
/* 1465 */     buf.append('{');
/* 1466 */     MapIterator it = new ViewMapIterator(this, type);
/* 1467 */     boolean hasNext = it.hasNext();
/* 1468 */     while (hasNext) {
/* 1469 */       Object key = it.next();
/* 1470 */       Object value = it.getValue();
/* 1471 */       buf.append(key == this ? "(this Map)" : key).append('=').append(value == this ? "(this Map)" : value);
/*      */       
/* 1473 */       hasNext = it.hasNext();
/* 1474 */       if (hasNext) {
/* 1475 */         buf.append(", ");
/*      */       }
/*      */     }
/*      */     
/* 1479 */     buf.append('}');
/* 1480 */     return buf.toString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static class View<K extends Comparable, V extends Comparable>
/*      */     extends AbstractSet
/*      */   {
/*      */     protected final TreeBidiMap main;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected final int orderType;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected final int dataType;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     View(TreeBidiMap main, int orderType, int dataType)
/*      */     {
/* 1511 */       this.main = main;
/* 1512 */       this.orderType = orderType;
/* 1513 */       this.dataType = dataType;
/*      */     }
/*      */     
/*      */     public Iterator iterator() {
/* 1517 */       return new TreeBidiMap.ViewIterator(this.main, this.orderType, this.dataType);
/*      */     }
/*      */     
/*      */     public int size() {
/* 1521 */       return this.main.size();
/*      */     }
/*      */     
/*      */     public boolean contains(Object obj) {
/* 1525 */       TreeBidiMap.checkNonNullComparable(obj);
/* 1526 */       return this.main.lookup((Comparable)obj, this.dataType) != null;
/*      */     }
/*      */     
/*      */     public boolean remove(Object obj) {
/* 1530 */       if (this.dataType == 0) {
/* 1531 */         return this.main.doRemoveByKey((Comparable)obj) != null;
/*      */       }
/* 1533 */       return this.main.doRemoveByValue((Comparable)obj) != null;
/*      */     }
/*      */     
/*      */     public void clear()
/*      */     {
/* 1538 */       this.main.clear();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static class ViewIterator
/*      */     implements OrderedIterator
/*      */   {
/*      */     protected final TreeBidiMap main;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     protected final int orderType;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     protected final int dataType;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     protected TreeBidiMap.Node lastReturnedNode;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     protected TreeBidiMap.Node nextNode;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     protected TreeBidiMap.Node previousNode;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     private int expectedModifications;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     ViewIterator(TreeBidiMap main, int orderType, int dataType)
/*      */     {
/* 1586 */       this.main = main;
/* 1587 */       this.orderType = orderType;
/* 1588 */       this.dataType = dataType;
/* 1589 */       this.expectedModifications = main.modifications;
/* 1590 */       this.nextNode = TreeBidiMap.leastNode(main.rootNode[orderType], orderType);
/* 1591 */       this.lastReturnedNode = null;
/* 1592 */       this.previousNode = null;
/*      */     }
/*      */     
/*      */     public final boolean hasNext() {
/* 1596 */       return this.nextNode != null;
/*      */     }
/*      */     
/*      */     public final Object next() {
/* 1600 */       if (this.nextNode == null) {
/* 1601 */         throw new NoSuchElementException();
/*      */       }
/* 1603 */       if (this.main.modifications != this.expectedModifications) {
/* 1604 */         throw new ConcurrentModificationException();
/*      */       }
/* 1606 */       this.lastReturnedNode = this.nextNode;
/* 1607 */       this.previousNode = this.nextNode;
/* 1608 */       this.nextNode = this.main.nextGreater(this.nextNode, this.orderType);
/* 1609 */       return doGetData();
/*      */     }
/*      */     
/*      */     public boolean hasPrevious() {
/* 1613 */       return this.previousNode != null;
/*      */     }
/*      */     
/*      */     public Object previous() {
/* 1617 */       if (this.previousNode == null) {
/* 1618 */         throw new NoSuchElementException();
/*      */       }
/* 1620 */       if (this.main.modifications != this.expectedModifications) {
/* 1621 */         throw new ConcurrentModificationException();
/*      */       }
/* 1623 */       this.nextNode = this.lastReturnedNode;
/* 1624 */       if (this.nextNode == null) {
/* 1625 */         this.nextNode = this.main.nextGreater(this.previousNode, this.orderType);
/*      */       }
/* 1627 */       this.lastReturnedNode = this.previousNode;
/* 1628 */       this.previousNode = this.main.nextSmaller(this.previousNode, this.orderType);
/* 1629 */       return doGetData();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected Object doGetData()
/*      */     {
/* 1638 */       switch (this.dataType) {
/*      */       case 0: 
/* 1640 */         return this.lastReturnedNode.getKey();
/*      */       case 1: 
/* 1642 */         return this.lastReturnedNode.getValue();
/*      */       case 2: 
/* 1644 */         return this.lastReturnedNode;
/*      */       case 3: 
/* 1646 */         return new UnmodifiableMapEntry(this.lastReturnedNode.getValue(), this.lastReturnedNode.getKey());
/*      */       }
/* 1648 */       return null;
/*      */     }
/*      */     
/*      */     public final void remove() {
/* 1652 */       if (this.lastReturnedNode == null) {
/* 1653 */         throw new IllegalStateException();
/*      */       }
/* 1655 */       if (this.main.modifications != this.expectedModifications) {
/* 1656 */         throw new ConcurrentModificationException();
/*      */       }
/* 1658 */       this.main.doRedBlackDelete(this.lastReturnedNode);
/* 1659 */       this.expectedModifications += 1;
/* 1660 */       this.lastReturnedNode = null;
/* 1661 */       if (this.nextNode == null) {
/* 1662 */         this.previousNode = TreeBidiMap.greatestNode(this.main.rootNode[this.orderType], this.orderType);
/*      */       } else {
/* 1664 */         this.previousNode = this.main.nextSmaller(this.nextNode, this.orderType);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static class ViewMapIterator
/*      */     extends TreeBidiMap.ViewIterator
/*      */     implements OrderedMapIterator
/*      */   {
/*      */     private final int oppositeType;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     ViewMapIterator(TreeBidiMap main, int orderType)
/*      */     {
/* 1684 */       super(orderType, orderType);
/* 1685 */       this.oppositeType = TreeBidiMap.oppositeIndex(this.dataType);
/*      */     }
/*      */     
/*      */     public Object getKey() {
/* 1689 */       if (this.lastReturnedNode == null) {
/* 1690 */         throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
/*      */       }
/* 1692 */       return this.lastReturnedNode.getData(this.dataType);
/*      */     }
/*      */     
/*      */     public Object getValue() {
/* 1696 */       if (this.lastReturnedNode == null) {
/* 1697 */         throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
/*      */       }
/* 1699 */       return this.lastReturnedNode.getData(this.oppositeType);
/*      */     }
/*      */     
/*      */     public Object setValue(Object obj) {
/* 1703 */       throw new UnsupportedOperationException();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static class EntryView
/*      */     extends TreeBidiMap.View
/*      */   {
/*      */     private final int oppositeType;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     EntryView(TreeBidiMap main, int orderType, int dataType)
/*      */     {
/* 1723 */       super(orderType, dataType);
/* 1724 */       this.oppositeType = TreeBidiMap.oppositeIndex(orderType);
/*      */     }
/*      */     
/*      */     public boolean contains(Object obj) {
/* 1728 */       if (!(obj instanceof Map.Entry)) {
/* 1729 */         return false;
/*      */       }
/* 1731 */       Map.Entry entry = (Map.Entry)obj;
/* 1732 */       Object value = entry.getValue();
/* 1733 */       TreeBidiMap.Node node = this.main.lookup((Comparable)entry.getKey(), this.orderType);
/* 1734 */       return (node != null) && (node.getData(this.oppositeType).equals(value));
/*      */     }
/*      */     
/*      */     public boolean remove(Object obj) {
/* 1738 */       if (!(obj instanceof Map.Entry)) {
/* 1739 */         return false;
/*      */       }
/* 1741 */       Map.Entry entry = (Map.Entry)obj;
/* 1742 */       Object value = entry.getValue();
/* 1743 */       TreeBidiMap.Node node = this.main.lookup((Comparable)entry.getKey(), this.orderType);
/* 1744 */       if ((node != null) && (node.getData(this.oppositeType).equals(value))) {
/* 1745 */         this.main.doRedBlackDelete(node);
/* 1746 */         return true;
/*      */       }
/* 1748 */       return false;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   static class Node<K extends Comparable, V extends Comparable>
/*      */     implements Map.Entry<K, V>, KeyValue<K, V>
/*      */   {
/*      */     private K key;
/*      */     
/*      */     private V value;
/*      */     
/*      */     private Node<K, V>[] leftNode;
/*      */     
/*      */     private Node<K, V>[] rightNode;
/*      */     
/*      */     private Node<K, V>[] parentNode;
/*      */     
/*      */     private boolean[] blackColor;
/*      */     
/*      */     private int hashcodeValue;
/*      */     
/*      */     private boolean calculatedHashCode;
/*      */     
/*      */ 
/*      */     Node(K key, V value)
/*      */     {
/* 1776 */       this.key = key;
/* 1777 */       this.value = value;
/* 1778 */       this.leftNode = new Node[2];
/* 1779 */       this.rightNode = new Node[2];
/* 1780 */       this.parentNode = new Node[2];
/* 1781 */       this.blackColor = new boolean[] { true, true };
/* 1782 */       this.calculatedHashCode = false;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private Comparable getData(int index)
/*      */     {
/* 1792 */       if (index == 0) {
/* 1793 */         return this.key;
/*      */       }
/* 1795 */       return this.value;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private void setLeft(Node<K, V> node, int index)
/*      */     {
/* 1806 */       this.leftNode[index] = node;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private Node<K, V> getLeft(int index)
/*      */     {
/* 1816 */       return this.leftNode[index];
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private void setRight(Node<K, V> node, int index)
/*      */     {
/* 1826 */       this.rightNode[index] = node;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private Node<K, V> getRight(int index)
/*      */     {
/* 1836 */       return this.rightNode[index];
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private void setParent(Node<K, V> node, int index)
/*      */     {
/* 1846 */       this.parentNode[index] = node;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private Node<K, V> getParent(int index)
/*      */     {
/* 1856 */       return this.parentNode[index];
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private void swapColors(Node<K, V> node, int index)
/*      */     {
/* 1867 */       this.blackColor[index] ^= node.blackColor[index];
/* 1868 */       node.blackColor[index] ^= this.blackColor[index];
/* 1869 */       this.blackColor[index] ^= node.blackColor[index];
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private boolean isBlack(int index)
/*      */     {
/* 1879 */       return this.blackColor[index];
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private boolean isRed(int index)
/*      */     {
/* 1889 */       return this.blackColor[index] == 0;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private void setBlack(int index)
/*      */     {
/* 1898 */       this.blackColor[index] = true;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private void setRed(int index)
/*      */     {
/* 1907 */       this.blackColor[index] = false;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private void copyColor(Node<K, V> node, int index)
/*      */     {
/* 1917 */       this.blackColor[index] = node.blackColor[index];
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public K getKey()
/*      */     {
/* 1927 */       return this.key;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public V getValue()
/*      */     {
/* 1936 */       return this.value;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public V setValue(V ignored)
/*      */       throws UnsupportedOperationException
/*      */     {
/* 1947 */       throw new UnsupportedOperationException("Map.Entry.setValue is not supported");
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public boolean equals(Object obj)
/*      */     {
/* 1959 */       if (obj == this) {
/* 1960 */         return true;
/*      */       }
/* 1962 */       if (!(obj instanceof Map.Entry)) {
/* 1963 */         return false;
/*      */       }
/* 1965 */       Map.Entry e = (Map.Entry)obj;
/* 1966 */       return (this.key.equals(e.getKey())) && (this.value.equals(e.getValue()));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     public int hashCode()
/*      */     {
/* 1973 */       if (!this.calculatedHashCode) {
/* 1974 */         this.hashcodeValue = (this.key.hashCode() ^ this.value.hashCode());
/* 1975 */         this.calculatedHashCode = true;
/*      */       }
/* 1977 */       return this.hashcodeValue;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static class Inverse<K extends Comparable, V extends Comparable>
/*      */     implements OrderedBidiMap<V, K>
/*      */   {
/*      */     private final TreeBidiMap<K, V> main;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     private Set keySet;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     private Set valuesSet;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     private Set entrySet;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     Inverse(TreeBidiMap<K, V> main)
/*      */     {
/* 2011 */       this.main = main;
/*      */     }
/*      */     
/*      */     public int size() {
/* 2015 */       return this.main.size();
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/* 2019 */       return this.main.isEmpty();
/*      */     }
/*      */     
/*      */     public K get(Object key) {
/* 2023 */       return this.main.getKey((Comparable)key);
/*      */     }
/*      */     
/*      */     public V getKey(Object value) {
/* 2027 */       return this.main.get(value);
/*      */     }
/*      */     
/*      */     public boolean containsKey(Object key) {
/* 2031 */       return this.main.containsValue(key);
/*      */     }
/*      */     
/*      */     public boolean containsValue(Object value) {
/* 2035 */       return this.main.containsKey(value);
/*      */     }
/*      */     
/*      */     public V firstKey() {
/* 2039 */       if (this.main.nodeCount == 0) {
/* 2040 */         throw new NoSuchElementException("Map is empty");
/*      */       }
/* 2042 */       return TreeBidiMap.leastNode(this.main.rootNode[1], 1).getValue();
/*      */     }
/*      */     
/*      */     public V lastKey() {
/* 2046 */       if (this.main.nodeCount == 0) {
/* 2047 */         throw new NoSuchElementException("Map is empty");
/*      */       }
/* 2049 */       return TreeBidiMap.greatestNode(this.main.rootNode[1], 1).getValue();
/*      */     }
/*      */     
/*      */     public V nextKey(V key) {
/* 2053 */       TreeBidiMap.checkKey(key);
/* 2054 */       TreeBidiMap.Node<K, V> node = this.main.nextGreater(TreeBidiMap.access$1400(this.main, key, 1), 1);
/* 2055 */       return node == null ? null : node.getValue();
/*      */     }
/*      */     
/*      */     public V previousKey(V key) {
/* 2059 */       TreeBidiMap.checkKey(key);
/* 2060 */       TreeBidiMap.Node<K, V> node = this.main.nextSmaller(TreeBidiMap.access$1400(this.main, key, 1), 1);
/* 2061 */       return node == null ? null : node.getValue();
/*      */     }
/*      */     
/*      */     public K put(V key, K value) {
/* 2065 */       return this.main.doPutByValue(value, key);
/*      */     }
/*      */     
/*      */     public void putAll(Map<? extends V, ? extends K> map) {
/* 2069 */       Iterator it = map.entrySet().iterator();
/* 2070 */       while (it.hasNext()) {
/* 2071 */         Map.Entry entry = (Map.Entry)it.next();
/* 2072 */         put((Comparable)entry.getKey(), (Comparable)entry.getValue());
/*      */       }
/*      */     }
/*      */     
/*      */     public K remove(Object key) {
/* 2077 */       return this.main.removeValue((Comparable)key);
/*      */     }
/*      */     
/*      */     public V removeValue(Object value) {
/* 2081 */       return this.main.remove(value);
/*      */     }
/*      */     
/*      */     public void clear() {
/* 2085 */       this.main.clear();
/*      */     }
/*      */     
/*      */     public Set<V> keySet() {
/* 2089 */       if (this.keySet == null) {
/* 2090 */         this.keySet = new TreeBidiMap.View(this.main, 1, 1);
/*      */       }
/* 2092 */       return this.keySet;
/*      */     }
/*      */     
/*      */     public Set<K> values() {
/* 2096 */       if (this.valuesSet == null) {
/* 2097 */         this.valuesSet = new TreeBidiMap.View(this.main, 1, 0);
/*      */       }
/* 2099 */       return this.valuesSet;
/*      */     }
/*      */     
/*      */     public Set<Map.Entry<V, K>> entrySet() {
/* 2103 */       if (this.entrySet == null) {
/* 2104 */         return new TreeBidiMap.EntryView(this.main, 1, 3);
/*      */       }
/* 2106 */       return this.entrySet;
/*      */     }
/*      */     
/*      */     public MapIterator<V, K> mapIterator() {
/* 2110 */       if (isEmpty()) {
/* 2111 */         return EmptyOrderedMapIterator.INSTANCE;
/*      */       }
/* 2113 */       return new TreeBidiMap.ViewMapIterator(this.main, 1);
/*      */     }
/*      */     
/*      */     public OrderedMapIterator<V, K> orderedMapIterator() {
/* 2117 */       if (isEmpty()) {
/* 2118 */         return EmptyOrderedMapIterator.INSTANCE;
/*      */       }
/* 2120 */       return new TreeBidiMap.ViewMapIterator(this.main, 1);
/*      */     }
/*      */     
/*      */     public BidiMap<K, V> inverseBidiMap() {
/* 2124 */       return this.main;
/*      */     }
/*      */     
/*      */     public OrderedBidiMap<K, V> inverseOrderedBidiMap() {
/* 2128 */       return this.main;
/*      */     }
/*      */     
/*      */     public boolean equals(Object obj) {
/* 2132 */       return this.main.doEquals(obj, 1);
/*      */     }
/*      */     
/*      */     public int hashCode() {
/* 2136 */       return this.main.doHashCode(1);
/*      */     }
/*      */     
/*      */     public String toString() {
/* 2140 */       return this.main.doToString(1);
/*      */     }
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/bidimap/TreeBidiMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */