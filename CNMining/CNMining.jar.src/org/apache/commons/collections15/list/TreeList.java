/*     */ package org.apache.commons.collections15.list;
/*     */ 
/*     */ import java.util.AbstractList;
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections15.OrderedIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TreeList<E>
/*     */   extends AbstractList<E>
/*     */ {
/*     */   private AVLNode<E> root;
/*     */   private int size;
/*     */   
/*     */   public TreeList() {}
/*     */   
/*     */   public TreeList(Collection<? extends E> coll)
/*     */   {
/*  85 */     addAll(coll);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E get(int index)
/*     */   {
/*  96 */     checkInterval(index, 0, size() - 1);
/*  97 */     return (E)this.root.get(index).getValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 106 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator<E> iterator()
/*     */   {
/* 116 */     return listIterator(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ListIterator<E> listIterator()
/*     */   {
/* 126 */     return listIterator(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ListIterator<E> listIterator(int fromIndex)
/*     */   {
/* 138 */     checkInterval(fromIndex, 0, size());
/* 139 */     return new TreeListIterator(this, fromIndex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int indexOf(Object object)
/*     */   {
/* 149 */     if (this.root == null) {
/* 150 */       return -1;
/*     */     }
/* 152 */     return this.root.indexOf(object, this.root.relativePosition);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean contains(Object object)
/*     */   {
/* 161 */     return indexOf(object) >= 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object[] toArray()
/*     */   {
/* 171 */     Object[] array = new Object[size()];
/* 172 */     if (this.root != null) {
/* 173 */       this.root.toArray((Object[])array, this.root.relativePosition);
/*     */     }
/* 175 */     return array;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(int index, E obj)
/*     */   {
/* 186 */     this.modCount += 1;
/* 187 */     checkInterval(index, 0, size());
/* 188 */     if (this.root == null) {
/* 189 */       this.root = new AVLNode(index, obj, null, null, null);
/*     */     } else {
/* 191 */       this.root = this.root.insert(index, obj);
/*     */     }
/* 193 */     this.size += 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E set(int index, E obj)
/*     */   {
/* 205 */     checkInterval(index, 0, size() - 1);
/* 206 */     AVLNode<E> node = this.root.get(index);
/* 207 */     E result = node.value;
/* 208 */     node.setValue(obj);
/* 209 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E remove(int index)
/*     */   {
/* 219 */     this.modCount += 1;
/* 220 */     checkInterval(index, 0, size() - 1);
/* 221 */     E result = get(index);
/* 222 */     this.root = this.root.remove(index);
/* 223 */     this.size -= 1;
/* 224 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 231 */     this.modCount += 1;
/* 232 */     this.root = null;
/* 233 */     this.size = 0;
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
/*     */   private void checkInterval(int index, int startIndex, int endIndex)
/*     */   {
/* 246 */     if ((index < startIndex) || (index > endIndex)) {
/* 247 */       throw new IndexOutOfBoundsException("Invalid index:" + index + ", size=" + size());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static class AVLNode<T>
/*     */   {
/*     */     private AVLNode<T> left;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private boolean leftIsPrevious;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private AVLNode<T> right;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private boolean rightIsNext;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private int height;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private int relativePosition;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private T value;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private AVLNode(int relativePosition, T obj, AVLNode<T> rightFollower, AVLNode<T> leftFollower)
/*     */     {
/* 303 */       this.relativePosition = relativePosition;
/* 304 */       this.value = obj;
/* 305 */       this.rightIsNext = true;
/* 306 */       this.leftIsPrevious = true;
/* 307 */       this.right = rightFollower;
/* 308 */       this.left = leftFollower;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     T getValue()
/*     */     {
/* 317 */       return (T)this.value;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     void setValue(T obj)
/*     */     {
/* 326 */       this.value = obj;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     AVLNode<T> get(int index)
/*     */     {
/* 334 */       int indexRelativeToMe = index - this.relativePosition;
/*     */       
/* 336 */       if (indexRelativeToMe == 0) {
/* 337 */         return this;
/*     */       }
/*     */       
/* 340 */       AVLNode<T> nextNode = indexRelativeToMe < 0 ? getLeftSubTree() : getRightSubTree();
/* 341 */       if (nextNode == null) {
/* 342 */         return null;
/*     */       }
/* 344 */       return nextNode.get(indexRelativeToMe);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     int indexOf(T object, int index)
/*     */     {
/* 351 */       if (getLeftSubTree() != null) {
/* 352 */         int result = this.left.indexOf(object, index + this.left.relativePosition);
/* 353 */         if (result != -1) {
/* 354 */           return result;
/*     */         }
/*     */       }
/* 357 */       if (this.value == null ? this.value == object : this.value.equals(object)) {
/* 358 */         return index;
/*     */       }
/* 360 */       if (getRightSubTree() != null) {
/* 361 */         return this.right.indexOf(object, index + this.right.relativePosition);
/*     */       }
/* 363 */       return -1;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     void toArray(T[] array, int index)
/*     */     {
/* 373 */       array[index] = this.value;
/* 374 */       if (getLeftSubTree() != null) {
/* 375 */         this.left.toArray(array, index + this.left.relativePosition);
/*     */       }
/* 377 */       if (getRightSubTree() != null) {
/* 378 */         this.right.toArray(array, index + this.right.relativePosition);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     AVLNode<T> next()
/*     */     {
/* 388 */       if ((this.rightIsNext) || (this.right == null)) {
/* 389 */         return this.right;
/*     */       }
/* 391 */       return this.right.min();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     AVLNode<T> previous()
/*     */     {
/* 400 */       if ((this.leftIsPrevious) || (this.left == null)) {
/* 401 */         return this.left;
/*     */       }
/* 403 */       return this.left.max();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     AVLNode<T> insert(int index, T obj)
/*     */     {
/* 414 */       int indexRelativeToMe = index - this.relativePosition;
/*     */       
/* 416 */       if (indexRelativeToMe <= 0) {
/* 417 */         return insertOnLeft(indexRelativeToMe, obj);
/*     */       }
/* 419 */       return insertOnRight(indexRelativeToMe, obj);
/*     */     }
/*     */     
/*     */     private AVLNode<T> insertOnLeft(int indexRelativeToMe, T obj)
/*     */     {
/* 424 */       AVLNode<T> ret = this;
/*     */       
/* 426 */       if (getLeftSubTree() == null) {
/* 427 */         setLeft(new AVLNode(-1, obj, this, this.left), null);
/*     */       } else {
/* 429 */         setLeft(this.left.insert(indexRelativeToMe, obj), null);
/*     */       }
/*     */       
/* 432 */       if (this.relativePosition >= 0) {
/* 433 */         this.relativePosition += 1;
/*     */       }
/* 435 */       ret = balance();
/* 436 */       recalcHeight();
/* 437 */       return ret;
/*     */     }
/*     */     
/*     */     private AVLNode<T> insertOnRight(int indexRelativeToMe, T obj) {
/* 441 */       AVLNode<T> ret = this;
/*     */       
/* 443 */       if (getRightSubTree() == null) {
/* 444 */         setRight(new AVLNode(1, obj, this.right, this), null);
/*     */       } else {
/* 446 */         setRight(this.right.insert(indexRelativeToMe, obj), null);
/*     */       }
/* 448 */       if (this.relativePosition < 0) {
/* 449 */         this.relativePosition -= 1;
/*     */       }
/* 451 */       ret = balance();
/* 452 */       recalcHeight();
/* 453 */       return ret;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     private AVLNode<T> getLeftSubTree()
/*     */     {
/* 461 */       return this.leftIsPrevious ? null : this.left;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     private AVLNode<T> getRightSubTree()
/*     */     {
/* 468 */       return this.rightIsNext ? null : this.right;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private AVLNode<T> max()
/*     */     {
/* 477 */       return getRightSubTree() == null ? this : this.right.max();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private AVLNode<T> min()
/*     */     {
/* 486 */       return getLeftSubTree() == null ? this : this.left.min();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     AVLNode<T> remove(int index)
/*     */     {
/* 496 */       int indexRelativeToMe = index - this.relativePosition;
/*     */       
/* 498 */       if (indexRelativeToMe == 0) {
/* 499 */         return removeSelf();
/*     */       }
/* 501 */       if (indexRelativeToMe > 0) {
/* 502 */         setRight(this.right.remove(indexRelativeToMe), this.right.right);
/* 503 */         if (this.relativePosition < 0) {
/* 504 */           this.relativePosition += 1;
/*     */         }
/*     */       } else {
/* 507 */         setLeft(this.left.remove(indexRelativeToMe), this.left.left);
/* 508 */         if (this.relativePosition > 0) {
/* 509 */           this.relativePosition -= 1;
/*     */         }
/*     */       }
/* 512 */       recalcHeight();
/* 513 */       return balance();
/*     */     }
/*     */     
/*     */     private AVLNode<T> removeMax() {
/* 517 */       if (getRightSubTree() == null) {
/* 518 */         return removeSelf();
/*     */       }
/* 520 */       setRight(this.right.removeMax(), this.right.right);
/* 521 */       if (this.relativePosition < 0) {
/* 522 */         this.relativePosition += 1;
/*     */       }
/* 524 */       recalcHeight();
/* 525 */       return balance();
/*     */     }
/*     */     
/*     */     private AVLNode<T> removeMin() {
/* 529 */       if (getLeftSubTree() == null) {
/* 530 */         return removeSelf();
/*     */       }
/* 532 */       setLeft(this.left.removeMin(), this.left.left);
/* 533 */       if (this.relativePosition > 0) {
/* 534 */         this.relativePosition -= 1;
/*     */       }
/* 536 */       recalcHeight();
/* 537 */       return balance();
/*     */     }
/*     */     
/*     */     private AVLNode<T> removeSelf() {
/* 541 */       if ((getRightSubTree() == null) && (getLeftSubTree() == null))
/* 542 */         return null;
/* 543 */       if (getRightSubTree() == null) {
/* 544 */         if (this.relativePosition > 0) {
/* 545 */           this.left.relativePosition += this.relativePosition + (this.relativePosition > 0 ? 0 : 1);
/*     */         }
/* 547 */         this.left.max().setRight(null, this.right);
/* 548 */         return this.left;
/*     */       }
/* 550 */       if (getLeftSubTree() == null) {
/* 551 */         this.right.relativePosition += this.relativePosition - (this.relativePosition < 0 ? 0 : 1);
/* 552 */         this.right.min().setLeft(null, this.left);
/* 553 */         return this.right;
/*     */       }
/*     */       
/* 556 */       if (heightRightMinusLeft() > 0) {
/* 557 */         AVLNode<T> rightMin = this.right.min();
/* 558 */         this.value = rightMin.value;
/* 559 */         if (this.leftIsPrevious) {
/* 560 */           this.left = rightMin.left;
/*     */         }
/* 562 */         this.right = this.right.removeMin();
/* 563 */         if (this.relativePosition < 0) {
/* 564 */           this.relativePosition += 1;
/*     */         }
/*     */       } else {
/* 567 */         AVLNode<T> leftMax = this.left.max();
/* 568 */         this.value = leftMax.value;
/* 569 */         if (this.rightIsNext) {
/* 570 */           this.right = leftMax.right;
/*     */         }
/* 572 */         this.left = this.left.removeMax();
/* 573 */         if (this.relativePosition > 0) {
/* 574 */           this.relativePosition -= 1;
/*     */         }
/*     */       }
/* 577 */       recalcHeight();
/* 578 */       return this;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     private AVLNode<T> balance()
/*     */     {
/* 586 */       switch (heightRightMinusLeft()) {
/*     */       case -1: 
/*     */       case 0: 
/*     */       case 1: 
/* 590 */         return this;
/*     */       case -2: 
/* 592 */         if (this.left.heightRightMinusLeft() > 0) {
/* 593 */           setLeft(this.left.rotateLeft(), null);
/*     */         }
/* 595 */         return rotateRight();
/*     */       case 2: 
/* 597 */         if (this.right.heightRightMinusLeft() < 0) {
/* 598 */           setRight(this.right.rotateRight(), null);
/*     */         }
/* 600 */         return rotateLeft();
/*     */       }
/* 602 */       throw new RuntimeException("tree inconsistent!");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     private int getOffset(AVLNode<T> node)
/*     */     {
/* 610 */       if (node == null) {
/* 611 */         return 0;
/*     */       }
/* 613 */       return node.relativePosition;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     private int setOffset(AVLNode<T> node, int newOffest)
/*     */     {
/* 620 */       if (node == null) {
/* 621 */         return 0;
/*     */       }
/* 623 */       int oldOffset = getOffset(node);
/* 624 */       node.relativePosition = newOffest;
/* 625 */       return oldOffset;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     private void recalcHeight()
/*     */     {
/* 632 */       this.height = (Math.max(getLeftSubTree() == null ? -1 : getLeftSubTree().height, getRightSubTree() == null ? -1 : getRightSubTree().height) + 1);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     private int getHeight(AVLNode<T> node)
/*     */     {
/* 639 */       return node == null ? -1 : node.height;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     private int heightRightMinusLeft()
/*     */     {
/* 646 */       return getHeight(getRightSubTree()) - getHeight(getLeftSubTree());
/*     */     }
/*     */     
/*     */     private AVLNode<T> rotateLeft() {
/* 650 */       AVLNode<T> newTop = this.right;
/* 651 */       AVLNode<T> movedNode = getRightSubTree().getLeftSubTree();
/*     */       
/* 653 */       int newTopPosition = this.relativePosition + getOffset(newTop);
/* 654 */       int myNewPosition = -newTop.relativePosition;
/* 655 */       int movedPosition = getOffset(newTop) + getOffset(movedNode);
/*     */       
/* 657 */       setRight(movedNode, newTop);
/* 658 */       newTop.setLeft(this, null);
/*     */       
/* 660 */       setOffset(newTop, newTopPosition);
/* 661 */       setOffset(this, myNewPosition);
/* 662 */       setOffset(movedNode, movedPosition);
/* 663 */       return newTop;
/*     */     }
/*     */     
/*     */     private AVLNode<T> rotateRight() {
/* 667 */       AVLNode<T> newTop = this.left;
/* 668 */       AVLNode<T> movedNode = getLeftSubTree().getRightSubTree();
/*     */       
/* 670 */       int newTopPosition = this.relativePosition + getOffset(newTop);
/* 671 */       int myNewPosition = -newTop.relativePosition;
/* 672 */       int movedPosition = getOffset(newTop) + getOffset(movedNode);
/*     */       
/* 674 */       setLeft(movedNode, newTop);
/* 675 */       newTop.setRight(this, null);
/*     */       
/* 677 */       setOffset(newTop, newTopPosition);
/* 678 */       setOffset(this, myNewPosition);
/* 679 */       setOffset(movedNode, movedPosition);
/* 680 */       return newTop;
/*     */     }
/*     */     
/*     */     private void setLeft(AVLNode<T> node, AVLNode<T> previous) {
/* 684 */       this.leftIsPrevious = (node == null);
/* 685 */       this.left = (this.leftIsPrevious ? previous : node);
/* 686 */       recalcHeight();
/*     */     }
/*     */     
/*     */     private void setRight(AVLNode<T> node, AVLNode<T> next) {
/* 690 */       this.rightIsNext = (node == null);
/* 691 */       this.right = (this.rightIsNext ? next : node);
/* 692 */       recalcHeight();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public String toString()
/*     */     {
/* 751 */       return "AVLNode(" + this.relativePosition + "," + (this.left != null) + "," + this.value + "," + (getRightSubTree() != null) + ", faedelung " + this.rightIsNext + " )";
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static class TreeListIterator<E>
/*     */     implements ListIterator<E>, OrderedIterator<E>
/*     */   {
/*     */     protected final TreeList<E> parent;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected TreeList.AVLNode<E> next;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected int nextIndex;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected TreeList.AVLNode<E> current;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected int currentIndex;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     protected int expectedModCount;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected TreeListIterator(TreeList<E> parent, int fromIndex)
/*     */       throws IndexOutOfBoundsException
/*     */     {
/* 801 */       this.parent = parent;
/* 802 */       this.expectedModCount = parent.modCount;
/* 803 */       this.next = (parent.root == null ? null : parent.root.get(fromIndex));
/* 804 */       this.nextIndex = fromIndex;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected void checkModCount()
/*     */     {
/* 816 */       if (this.parent.modCount != this.expectedModCount) {
/* 817 */         throw new ConcurrentModificationException();
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 822 */       return this.nextIndex < this.parent.size();
/*     */     }
/*     */     
/*     */     public E next() {
/* 826 */       checkModCount();
/* 827 */       if (!hasNext()) {
/* 828 */         throw new NoSuchElementException("No element at index " + this.nextIndex + ".");
/*     */       }
/* 830 */       if (this.next == null) {
/* 831 */         this.next = this.parent.root.get(this.nextIndex);
/*     */       }
/* 833 */       E value = this.next.getValue();
/* 834 */       this.current = this.next;
/* 835 */       this.currentIndex = (this.nextIndex++);
/* 836 */       this.next = this.next.next();
/* 837 */       return value;
/*     */     }
/*     */     
/*     */     public boolean hasPrevious() {
/* 841 */       return this.nextIndex > 0;
/*     */     }
/*     */     
/*     */     public E previous() {
/* 845 */       checkModCount();
/* 846 */       if (!hasPrevious()) {
/* 847 */         throw new NoSuchElementException("Already at start of list.");
/*     */       }
/* 849 */       if (this.next == null) {
/* 850 */         this.next = this.parent.root.get(this.nextIndex - 1);
/*     */       } else {
/* 852 */         this.next = this.next.previous();
/*     */       }
/* 854 */       E value = this.next.getValue();
/* 855 */       this.current = this.next;
/* 856 */       this.currentIndex = (--this.nextIndex);
/* 857 */       return value;
/*     */     }
/*     */     
/*     */     public int nextIndex() {
/* 861 */       return this.nextIndex;
/*     */     }
/*     */     
/*     */     public int previousIndex() {
/* 865 */       return nextIndex() - 1;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 869 */       checkModCount();
/* 870 */       if (this.current == null) {
/* 871 */         throw new IllegalStateException();
/*     */       }
/* 873 */       this.parent.remove(this.currentIndex);
/* 874 */       this.current = null;
/* 875 */       this.currentIndex = -1;
/* 876 */       this.nextIndex -= 1;
/* 877 */       this.expectedModCount += 1;
/*     */     }
/*     */     
/*     */     public void set(E obj) {
/* 881 */       checkModCount();
/* 882 */       if (this.current == null) {
/* 883 */         throw new IllegalStateException();
/*     */       }
/* 885 */       this.current.setValue(obj);
/*     */     }
/*     */     
/*     */     public void add(E obj) {
/* 889 */       checkModCount();
/* 890 */       this.parent.add(this.nextIndex, obj);
/* 891 */       this.current = null;
/* 892 */       this.currentIndex = -1;
/* 893 */       this.nextIndex += 1;
/* 894 */       this.expectedModCount += 1;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/list/TreeList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */