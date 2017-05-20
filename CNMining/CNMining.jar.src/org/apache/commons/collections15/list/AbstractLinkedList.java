/*      */ package org.apache.commons.collections15.list;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.lang.reflect.Array;
/*      */ import java.util.AbstractList;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import java.util.NoSuchElementException;
/*      */ import org.apache.commons.collections15.OrderedIterator;
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
/*      */ public abstract class AbstractLinkedList<E>
/*      */   implements List<E>
/*      */ {
/*      */   protected transient Node<E> header;
/*      */   protected transient int size;
/*      */   protected transient int modCount;
/*      */   
/*      */   protected AbstractLinkedList() {}
/*      */   
/*      */   protected AbstractLinkedList(Collection<E> coll)
/*      */   {
/*   86 */     init();
/*   87 */     addAll(coll);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void init()
/*      */   {
/*   97 */     this.header = createHeaderNode();
/*      */   }
/*      */   
/*      */   public int size()
/*      */   {
/*  102 */     return this.size;
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/*  106 */     return size() == 0;
/*      */   }
/*      */   
/*      */   public E get(int index) {
/*  110 */     Node<E> node = getNode(index, false);
/*  111 */     return (E)node.getValue();
/*      */   }
/*      */   
/*      */   public Iterator<E> iterator()
/*      */   {
/*  116 */     return listIterator();
/*      */   }
/*      */   
/*      */   public ListIterator<E> listIterator() {
/*  120 */     return new LinkedListIterator(this, 0);
/*      */   }
/*      */   
/*      */   public ListIterator<E> listIterator(int fromIndex) {
/*  124 */     return new LinkedListIterator(this, fromIndex);
/*      */   }
/*      */   
/*      */   public int indexOf(Object value)
/*      */   {
/*  129 */     int i = 0;
/*  130 */     for (Node<E> node = this.header.next; node != this.header; node = node.next) {
/*  131 */       if (isEqualValue(node.getValue(), value)) {
/*  132 */         return i;
/*      */       }
/*  134 */       i++;
/*      */     }
/*  136 */     return -1;
/*      */   }
/*      */   
/*      */   public int lastIndexOf(Object value) {
/*  140 */     int i = this.size - 1;
/*  141 */     for (Node<E> node = this.header.previous; node != this.header; node = node.previous) {
/*  142 */       if (isEqualValue(node.getValue(), value)) {
/*  143 */         return i;
/*      */       }
/*  145 */       i--;
/*      */     }
/*  147 */     return -1;
/*      */   }
/*      */   
/*      */   public boolean contains(Object value) {
/*  151 */     return indexOf(value) != -1;
/*      */   }
/*      */   
/*      */   public boolean containsAll(Collection<?> coll) {
/*  155 */     Iterator it = coll.iterator();
/*  156 */     while (it.hasNext()) {
/*  157 */       if (!contains(it.next())) {
/*  158 */         return false;
/*      */       }
/*      */     }
/*  161 */     return true;
/*      */   }
/*      */   
/*      */   public Object[] toArray()
/*      */   {
/*  166 */     return toArray(new Object[this.size]);
/*      */   }
/*      */   
/*      */   public <T> T[] toArray(T[] array)
/*      */   {
/*  171 */     if (array.length < this.size) {
/*  172 */       Class componentType = array.getClass().getComponentType();
/*  173 */       array = (Object[])Array.newInstance(componentType, this.size);
/*      */     }
/*      */     
/*  176 */     int i = 0;
/*  177 */     for (Node<E> node = this.header.next; node != this.header; i++)
/*      */     {
/*  179 */       array[i] = node.getValue();node = node.next;
/*      */     }
/*      */     
/*  182 */     if (array.length > this.size) {
/*  183 */       array[this.size] = null;
/*      */     }
/*  185 */     return array;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public List<E> subList(int fromIndexInclusive, int toIndexExclusive)
/*      */   {
/*  196 */     return new LinkedSubList(this, fromIndexInclusive, toIndexExclusive);
/*      */   }
/*      */   
/*      */   public boolean add(E value)
/*      */   {
/*  201 */     addLast(value);
/*  202 */     return true;
/*      */   }
/*      */   
/*      */   public void add(int index, E value) {
/*  206 */     Node<E> node = getNode(index, true);
/*  207 */     addNodeBefore(node, value);
/*      */   }
/*      */   
/*      */   public boolean addAll(Collection<? extends E> coll) {
/*  211 */     return addAll(this.size, coll);
/*      */   }
/*      */   
/*      */   public boolean addAll(int index, Collection<? extends E> coll) {
/*  215 */     Node<E> node = getNode(index, true);
/*  216 */     for (Iterator itr = coll.iterator(); itr.hasNext();) {
/*  217 */       Object value = itr.next();
/*  218 */       addNodeBefore(node, value);
/*      */     }
/*  220 */     return true;
/*      */   }
/*      */   
/*      */   public E remove(int index)
/*      */   {
/*  225 */     Node<E> node = getNode(index, false);
/*  226 */     E oldValue = node.getValue();
/*  227 */     removeNode(node);
/*  228 */     return oldValue;
/*      */   }
/*      */   
/*      */   public boolean remove(Object value) {
/*  232 */     for (Node<E> node = this.header.next; node != this.header; node = node.next) {
/*  233 */       if (isEqualValue(node.getValue(), value)) {
/*  234 */         removeNode(node);
/*  235 */         return true;
/*      */       }
/*      */     }
/*  238 */     return false;
/*      */   }
/*      */   
/*      */   public boolean removeAll(Collection<?> coll) {
/*  242 */     boolean modified = false;
/*  243 */     Iterator it = iterator();
/*  244 */     while (it.hasNext()) {
/*  245 */       if (coll.contains(it.next())) {
/*  246 */         it.remove();
/*  247 */         modified = true;
/*      */       }
/*      */     }
/*  250 */     return modified;
/*      */   }
/*      */   
/*      */   public boolean retainAll(Collection<?> coll)
/*      */   {
/*  255 */     boolean modified = false;
/*  256 */     Iterator it = iterator();
/*  257 */     while (it.hasNext()) {
/*  258 */       if (!coll.contains(it.next())) {
/*  259 */         it.remove();
/*  260 */         modified = true;
/*      */       }
/*      */     }
/*  263 */     return modified;
/*      */   }
/*      */   
/*      */   public E set(int index, E value) {
/*  267 */     Node<E> node = getNode(index, false);
/*  268 */     E oldValue = node.getValue();
/*  269 */     updateNode(node, value);
/*  270 */     return oldValue;
/*      */   }
/*      */   
/*      */   public void clear() {
/*  274 */     removeAllNodes();
/*      */   }
/*      */   
/*      */   public E getFirst()
/*      */   {
/*  279 */     Node<E> node = this.header.next;
/*  280 */     if (node == this.header) {
/*  281 */       throw new NoSuchElementException();
/*      */     }
/*  283 */     return (E)node.getValue();
/*      */   }
/*      */   
/*      */   public E getLast() {
/*  287 */     Node<E> node = this.header.previous;
/*  288 */     if (node == this.header) {
/*  289 */       throw new NoSuchElementException();
/*      */     }
/*  291 */     return (E)node.getValue();
/*      */   }
/*      */   
/*      */   public boolean addFirst(E o) {
/*  295 */     addNodeAfter(this.header, o);
/*  296 */     return true;
/*      */   }
/*      */   
/*      */   public boolean addLast(E o) {
/*  300 */     addNodeBefore(this.header, o);
/*  301 */     return true;
/*      */   }
/*      */   
/*      */   public E removeFirst() {
/*  305 */     Node<E> node = this.header.next;
/*  306 */     if (node == this.header) {
/*  307 */       throw new NoSuchElementException();
/*      */     }
/*  309 */     E oldValue = node.getValue();
/*  310 */     removeNode(node);
/*  311 */     return oldValue;
/*      */   }
/*      */   
/*      */   public E removeLast() {
/*  315 */     Node<E> node = this.header.previous;
/*  316 */     if (node == this.header) {
/*  317 */       throw new NoSuchElementException();
/*      */     }
/*  319 */     E oldValue = node.getValue();
/*  320 */     removeNode(node);
/*  321 */     return oldValue;
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj)
/*      */   {
/*  326 */     if (obj == this) {
/*  327 */       return true;
/*      */     }
/*  329 */     if (!(obj instanceof List)) {
/*  330 */       return false;
/*      */     }
/*  332 */     List other = (List)obj;
/*  333 */     if (other.size() != size()) {
/*  334 */       return false;
/*      */     }
/*  336 */     ListIterator it1 = listIterator();
/*  337 */     ListIterator it2 = other.listIterator();
/*  338 */     while ((it1.hasNext()) && (it2.hasNext())) {
/*  339 */       Object o1 = it1.next();
/*  340 */       Object o2 = it2.next();
/*  341 */       if (o1 == null ? o2 != null : !o1.equals(o2))
/*  342 */         return false;
/*      */     }
/*  344 */     return (!it1.hasNext()) && (!it2.hasNext());
/*      */   }
/*      */   
/*      */   public int hashCode() {
/*  348 */     int hashCode = 1;
/*  349 */     Iterator it = iterator();
/*  350 */     while (it.hasNext()) {
/*  351 */       Object obj = it.next();
/*  352 */       hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
/*      */     }
/*  354 */     return hashCode;
/*      */   }
/*      */   
/*      */   public String toString() {
/*  358 */     if (size() == 0) {
/*  359 */       return "[]";
/*      */     }
/*  361 */     StringBuffer buf = new StringBuffer(16 * size());
/*  362 */     buf.append("[");
/*      */     
/*  364 */     Iterator it = iterator();
/*  365 */     boolean hasNext = it.hasNext();
/*  366 */     while (hasNext) {
/*  367 */       Object value = it.next();
/*  368 */       buf.append(value == this ? "(this Collection)" : value);
/*  369 */       hasNext = it.hasNext();
/*  370 */       if (hasNext) {
/*  371 */         buf.append(", ");
/*      */       }
/*      */     }
/*  374 */     buf.append("]");
/*  375 */     return buf.toString();
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
/*      */   protected boolean isEqualValue(E value1, E value2)
/*      */   {
/*  389 */     return (value1 == value2) || ((value1 != null) && (value1.equals(value2)));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void updateNode(Node<E> node, E value)
/*      */   {
/*  401 */     node.setValue(value);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Node<E> createHeaderNode()
/*      */   {
/*  412 */     return new Node();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Node<E> createNode(E value)
/*      */   {
/*  423 */     return new Node(value);
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
/*      */   protected void addNodeBefore(Node<E> node, E value)
/*      */   {
/*  438 */     Node newNode = createNode(value);
/*  439 */     addNode(newNode, node);
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
/*      */   protected void addNodeAfter(Node<E> node, E value)
/*      */   {
/*  454 */     Node<E> newNode = createNode(value);
/*  455 */     addNode(newNode, node.next);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void addNode(Node<E> nodeToInsert, Node<E> insertBeforeNode)
/*      */   {
/*  466 */     nodeToInsert.next = insertBeforeNode;
/*  467 */     nodeToInsert.previous = insertBeforeNode.previous;
/*  468 */     insertBeforeNode.previous.next = nodeToInsert;
/*  469 */     insertBeforeNode.previous = nodeToInsert;
/*  470 */     this.size += 1;
/*  471 */     this.modCount += 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void removeNode(Node<E> node)
/*      */   {
/*  481 */     node.previous.next = node.next;
/*  482 */     node.next.previous = node.previous;
/*  483 */     this.size -= 1;
/*  484 */     this.modCount += 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected void removeAllNodes()
/*      */   {
/*  491 */     this.header.next = this.header;
/*  492 */     this.header.previous = this.header;
/*  493 */     this.size = 0;
/*  494 */     this.modCount += 1;
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
/*      */   protected Node<E> getNode(int index, boolean endMarkerAllowed)
/*      */     throws IndexOutOfBoundsException
/*      */   {
/*  509 */     if (index < 0) {
/*  510 */       throw new IndexOutOfBoundsException("Couldn't get the node: index (" + index + ") less than zero.");
/*      */     }
/*  512 */     if ((!endMarkerAllowed) && (index == this.size)) {
/*  513 */       throw new IndexOutOfBoundsException("Couldn't get the node: index (" + index + ") is the size of the list.");
/*      */     }
/*  515 */     if (index > this.size) {
/*  516 */       throw new IndexOutOfBoundsException("Couldn't get the node: index (" + index + ") greater than the size of the " + "list (" + this.size + ").");
/*      */     }
/*      */     
/*      */     Node<E> node;
/*  520 */     if (index < this.size / 2)
/*      */     {
/*  522 */       Node<E> node = this.header.next;
/*  523 */       for (int currentIndex = 0; currentIndex < index; currentIndex++) {
/*  524 */         node = node.next;
/*      */       }
/*      */     }
/*      */     else {
/*  528 */       node = this.header;
/*  529 */       for (int currentIndex = this.size; currentIndex > index; currentIndex--) {
/*  530 */         node = node.previous;
/*      */       }
/*      */     }
/*  533 */     return node;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Iterator<E> createSubListIterator(LinkedSubList<E> subList)
/*      */   {
/*  543 */     return createSubListListIterator(subList, 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected ListIterator<E> createSubListListIterator(LinkedSubList<E> subList, int fromIndex)
/*      */   {
/*  553 */     return new LinkedSubListIterator(subList, fromIndex);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void doWriteObject(ObjectOutputStream outputStream)
/*      */     throws IOException
/*      */   {
/*  565 */     outputStream.writeInt(size());
/*  566 */     for (Iterator itr = iterator(); itr.hasNext();) {
/*  567 */       outputStream.writeObject(itr.next());
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void doReadObject(ObjectInputStream inputStream)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/*  578 */     init();
/*  579 */     int size = inputStream.readInt();
/*  580 */     for (int i = 0; i < size; i++) {
/*  581 */       add(inputStream.readObject());
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected static class Node<T>
/*      */   {
/*      */     protected Node<T> previous;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected Node<T> next;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected T value;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected Node()
/*      */     {
/*  612 */       this.previous = this;
/*  613 */       this.next = this;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected Node(T value)
/*      */     {
/*  623 */       this.value = value;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected Node(Node<T> previous, Node<T> next, T value)
/*      */     {
/*  635 */       this.previous = previous;
/*  636 */       this.next = next;
/*  637 */       this.value = value;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected T getValue()
/*      */     {
/*  647 */       return (T)this.value;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected void setValue(T value)
/*      */     {
/*  657 */       this.value = value;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected Node<T> getPreviousNode()
/*      */     {
/*  667 */       return this.previous;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected void setPreviousNode(Node<T> previous)
/*      */     {
/*  677 */       this.previous = previous;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected Node<T> getNextNode()
/*      */     {
/*  687 */       return this.next;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected void setNextNode(Node<T> next)
/*      */     {
/*  697 */       this.next = next;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected static class LinkedListIterator<E>
/*      */     implements ListIterator<E>, OrderedIterator<E>
/*      */   {
/*      */     protected final AbstractLinkedList<E> parent;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected AbstractLinkedList.Node<E> next;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected int nextIndex;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected AbstractLinkedList.Node<E> current;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected int expectedModCount;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected LinkedListIterator(AbstractLinkedList<E> parent, int fromIndex)
/*      */       throws IndexOutOfBoundsException
/*      */     {
/*  749 */       this.parent = parent;
/*  750 */       this.expectedModCount = parent.modCount;
/*  751 */       this.next = parent.getNode(fromIndex, true);
/*  752 */       this.nextIndex = fromIndex;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected void checkModCount()
/*      */     {
/*  764 */       if (this.parent.modCount != this.expectedModCount) {
/*  765 */         throw new ConcurrentModificationException();
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected AbstractLinkedList.Node<E> getLastNodeReturned()
/*      */       throws IllegalStateException
/*      */     {
/*  777 */       if (this.current == null) {
/*  778 */         throw new IllegalStateException();
/*      */       }
/*  780 */       return this.current;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  784 */       return this.next != this.parent.header;
/*      */     }
/*      */     
/*      */     public E next() {
/*  788 */       checkModCount();
/*  789 */       if (!hasNext()) {
/*  790 */         throw new NoSuchElementException("No element at index " + this.nextIndex + ".");
/*      */       }
/*  792 */       E value = this.next.getValue();
/*  793 */       this.current = this.next;
/*  794 */       this.next = this.next.next;
/*  795 */       this.nextIndex += 1;
/*  796 */       return value;
/*      */     }
/*      */     
/*      */     public boolean hasPrevious() {
/*  800 */       return this.next.previous != this.parent.header;
/*      */     }
/*      */     
/*      */     public E previous() {
/*  804 */       checkModCount();
/*  805 */       if (!hasPrevious()) {
/*  806 */         throw new NoSuchElementException("Already at start of list.");
/*      */       }
/*  808 */       this.next = this.next.previous;
/*  809 */       E value = this.next.getValue();
/*  810 */       this.current = this.next;
/*  811 */       this.nextIndex -= 1;
/*  812 */       return value;
/*      */     }
/*      */     
/*      */     public int nextIndex() {
/*  816 */       return this.nextIndex;
/*      */     }
/*      */     
/*      */     public int previousIndex()
/*      */     {
/*  821 */       return nextIndex() - 1;
/*      */     }
/*      */     
/*      */     public void remove() {
/*  825 */       checkModCount();
/*  826 */       this.parent.removeNode(getLastNodeReturned());
/*  827 */       this.current = null;
/*  828 */       this.nextIndex -= 1;
/*  829 */       this.expectedModCount += 1;
/*      */     }
/*      */     
/*      */     public void set(E obj) {
/*  833 */       checkModCount();
/*  834 */       getLastNodeReturned().setValue(obj);
/*      */     }
/*      */     
/*      */     public void add(E obj) {
/*  838 */       checkModCount();
/*  839 */       this.parent.addNodeBefore(this.next, obj);
/*  840 */       this.current = null;
/*  841 */       this.nextIndex += 1;
/*  842 */       this.expectedModCount += 1;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected static class LinkedSubListIterator<E>
/*      */     extends AbstractLinkedList.LinkedListIterator<E>
/*      */   {
/*      */     protected final AbstractLinkedList.LinkedSubList<E> sub;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     protected LinkedSubListIterator(AbstractLinkedList.LinkedSubList<E> sub, int startIndex)
/*      */     {
/*  859 */       super(startIndex + sub.offset);
/*  860 */       this.sub = sub;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  864 */       return nextIndex() < this.sub.size;
/*      */     }
/*      */     
/*      */     public boolean hasPrevious() {
/*  868 */       return previousIndex() >= 0;
/*      */     }
/*      */     
/*      */     public int nextIndex() {
/*  872 */       return super.nextIndex() - this.sub.offset;
/*      */     }
/*      */     
/*      */     public void add(E obj) {
/*  876 */       super.add(obj);
/*  877 */       this.sub.expectedModCount = this.parent.modCount;
/*  878 */       AbstractLinkedList.LinkedSubList.access$208(this.sub);
/*      */     }
/*      */     
/*      */     public void remove() {
/*  882 */       super.remove();
/*  883 */       this.sub.expectedModCount = this.parent.modCount;
/*  884 */       AbstractLinkedList.LinkedSubList.access$210(this.sub);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected static class LinkedSubList<E>
/*      */     extends AbstractList<E>
/*      */   {
/*      */     private AbstractLinkedList<E> parent;
/*      */     
/*      */ 
/*      */ 
/*      */     private int offset;
/*      */     
/*      */ 
/*      */ 
/*      */     private int size;
/*      */     
/*      */ 
/*      */     private int expectedModCount;
/*      */     
/*      */ 
/*      */ 
/*      */     protected LinkedSubList(AbstractLinkedList<E> parent, int fromIndex, int toIndex)
/*      */     {
/*  911 */       if (fromIndex < 0) {
/*  912 */         throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
/*      */       }
/*  914 */       if (toIndex > parent.size()) {
/*  915 */         throw new IndexOutOfBoundsException("toIndex = " + toIndex);
/*      */       }
/*  917 */       if (fromIndex > toIndex) {
/*  918 */         throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
/*      */       }
/*  920 */       this.parent = parent;
/*  921 */       this.offset = fromIndex;
/*  922 */       this.size = (toIndex - fromIndex);
/*  923 */       this.expectedModCount = parent.modCount;
/*      */     }
/*      */     
/*      */     public int size() {
/*  927 */       checkModCount();
/*  928 */       return this.size;
/*      */     }
/*      */     
/*      */     public E get(int index) {
/*  932 */       rangeCheck(index, this.size);
/*  933 */       checkModCount();
/*  934 */       return (E)this.parent.get(index + this.offset);
/*      */     }
/*      */     
/*      */     public void add(int index, E obj) {
/*  938 */       rangeCheck(index, this.size + 1);
/*  939 */       checkModCount();
/*  940 */       this.parent.add(index + this.offset, obj);
/*  941 */       this.expectedModCount = this.parent.modCount;
/*  942 */       this.size += 1;
/*  943 */       this.modCount += 1;
/*      */     }
/*      */     
/*      */     public E remove(int index) {
/*  947 */       rangeCheck(index, this.size);
/*  948 */       checkModCount();
/*  949 */       E result = this.parent.remove(index + this.offset);
/*  950 */       this.expectedModCount = this.parent.modCount;
/*  951 */       this.size -= 1;
/*  952 */       this.modCount += 1;
/*  953 */       return result;
/*      */     }
/*      */     
/*      */     public boolean addAll(Collection<? extends E> coll) {
/*  957 */       return addAll(this.size, coll);
/*      */     }
/*      */     
/*      */     public boolean addAll(int index, Collection<? extends E> coll) {
/*  961 */       rangeCheck(index, this.size + 1);
/*  962 */       int cSize = coll.size();
/*  963 */       if (cSize == 0) {
/*  964 */         return false;
/*      */       }
/*      */       
/*  967 */       checkModCount();
/*  968 */       this.parent.addAll(this.offset + index, coll);
/*  969 */       this.expectedModCount = this.parent.modCount;
/*  970 */       this.size += cSize;
/*  971 */       this.modCount += 1;
/*  972 */       return true;
/*      */     }
/*      */     
/*      */     public E set(int index, E obj) {
/*  976 */       rangeCheck(index, this.size);
/*  977 */       checkModCount();
/*  978 */       return (E)this.parent.set(index + this.offset, obj);
/*      */     }
/*      */     
/*      */     public void clear() {
/*  982 */       checkModCount();
/*  983 */       Iterator it = iterator();
/*  984 */       while (it.hasNext()) {
/*  985 */         it.next();
/*  986 */         it.remove();
/*      */       }
/*      */     }
/*      */     
/*      */     public Iterator<E> iterator() {
/*  991 */       checkModCount();
/*  992 */       return this.parent.createSubListIterator(this);
/*      */     }
/*      */     
/*      */     public ListIterator<E> listIterator(int index) {
/*  996 */       rangeCheck(index, this.size + 1);
/*  997 */       checkModCount();
/*  998 */       return this.parent.createSubListListIterator(this, index);
/*      */     }
/*      */     
/*      */     public List subList(int fromIndexInclusive, int toIndexExclusive) {
/* 1002 */       return new LinkedSubList(this.parent, fromIndexInclusive + this.offset, toIndexExclusive + this.offset);
/*      */     }
/*      */     
/*      */     protected void rangeCheck(int index, int beyond) {
/* 1006 */       if ((index < 0) || (index >= beyond)) {
/* 1007 */         throw new IndexOutOfBoundsException("Index '" + index + "' out of bounds for size '" + this.size + "'");
/*      */       }
/*      */     }
/*      */     
/*      */     protected void checkModCount() {
/* 1012 */       if (this.parent.modCount != this.expectedModCount) {
/* 1013 */         throw new ConcurrentModificationException();
/*      */       }
/*      */     }
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/list/AbstractLinkedList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */