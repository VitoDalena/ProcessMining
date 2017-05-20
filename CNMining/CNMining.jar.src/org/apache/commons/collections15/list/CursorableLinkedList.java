/*     */ package org.apache.commons.collections15.list;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CursorableLinkedList<E>
/*     */   extends AbstractLinkedList<E>
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8836393098519411393L;
/*  67 */   protected transient List<WeakReference<Cursor<E>>> cursors = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CursorableLinkedList()
/*     */   {
/*  75 */     init();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CursorableLinkedList(Collection<E> coll)
/*     */   {
/*  84 */     super(coll);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void init()
/*     */   {
/*  92 */     super.init();
/*  93 */     this.cursors = new ArrayList();
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
/*     */   public Iterator<E> iterator()
/*     */   {
/* 107 */     return super.listIterator(0);
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
/*     */   public ListIterator<E> listIterator()
/*     */   {
/* 126 */     return cursor(0);
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
/*     */   public ListIterator<E> listIterator(int fromIndex)
/*     */   {
/* 146 */     return cursor(fromIndex);
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
/*     */   public Cursor<E> cursor()
/*     */   {
/* 173 */     return cursor(0);
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
/*     */   public Cursor<E> cursor(int fromIndex)
/*     */   {
/* 204 */     Cursor<E> cursor = new Cursor(this, fromIndex);
/* 205 */     registerCursor(cursor);
/* 206 */     return cursor;
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
/*     */   protected void updateNode(AbstractLinkedList.Node<E> node, E value)
/*     */   {
/* 219 */     super.updateNode(node, value);
/* 220 */     broadcastNodeChanged(node);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void addNode(AbstractLinkedList.Node<E> nodeToInsert, AbstractLinkedList.Node<E> insertBeforeNode)
/*     */   {
/* 231 */     super.addNode(nodeToInsert, insertBeforeNode);
/* 232 */     broadcastNodeInserted(nodeToInsert);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void removeNode(AbstractLinkedList.Node<E> node)
/*     */   {
/* 242 */     super.removeNode(node);
/* 243 */     broadcastNodeRemoved(node);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void removeAllNodes()
/*     */   {
/* 250 */     if (size() > 0)
/*     */     {
/* 252 */       Iterator it = iterator();
/* 253 */       while (it.hasNext()) {
/* 254 */         it.next();
/* 255 */         it.remove();
/*     */       }
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
/*     */   protected void registerCursor(Cursor<E> cursor)
/*     */   {
/* 269 */     for (Iterator<WeakReference<Cursor<E>>> it = this.cursors.iterator(); it.hasNext();) {
/* 270 */       WeakReference<Cursor<E>> ref = (WeakReference)it.next();
/* 271 */       if (ref.get() == null) {
/* 272 */         it.remove();
/*     */       }
/*     */     }
/* 275 */     this.cursors.add(new WeakReference(cursor));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void unregisterCursor(Cursor<E> cursor)
/*     */   {
/* 284 */     for (Iterator it = this.cursors.iterator(); it.hasNext();) {
/* 285 */       WeakReference ref = (WeakReference)it.next();
/* 286 */       Cursor cur = (Cursor)ref.get();
/* 287 */       if (cur == null)
/*     */       {
/*     */ 
/*     */ 
/* 291 */         it.remove();
/*     */       }
/* 293 */       else if (cur == cursor) {
/* 294 */         ref.clear();
/* 295 */         it.remove();
/* 296 */         break;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void broadcastNodeChanged(AbstractLinkedList.Node<E> node)
/*     */   {
/* 309 */     Iterator it = this.cursors.iterator();
/* 310 */     while (it.hasNext()) {
/* 311 */       WeakReference ref = (WeakReference)it.next();
/* 312 */       Cursor cursor = (Cursor)ref.get();
/* 313 */       if (cursor == null) {
/* 314 */         it.remove();
/*     */       } else {
/* 316 */         cursor.nodeChanged(node);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void broadcastNodeRemoved(AbstractLinkedList.Node<E> node)
/*     */   {
/* 328 */     Iterator it = this.cursors.iterator();
/* 329 */     while (it.hasNext()) {
/* 330 */       WeakReference ref = (WeakReference)it.next();
/* 331 */       Cursor cursor = (Cursor)ref.get();
/* 332 */       if (cursor == null) {
/* 333 */         it.remove();
/*     */       } else {
/* 335 */         cursor.nodeRemoved(node);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void broadcastNodeInserted(AbstractLinkedList.Node<E> node)
/*     */   {
/* 347 */     Iterator it = this.cursors.iterator();
/* 348 */     while (it.hasNext()) {
/* 349 */       WeakReference ref = (WeakReference)it.next();
/* 350 */       Cursor cursor = (Cursor)ref.get();
/* 351 */       if (cursor == null) {
/* 352 */         it.remove();
/*     */       } else {
/* 354 */         cursor.nodeInserted(node);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void writeObject(ObjectOutputStream out)
/*     */     throws IOException
/*     */   {
/* 364 */     out.defaultWriteObject();
/* 365 */     doWriteObject(out);
/*     */   }
/*     */   
/*     */ 
/*     */   private void readObject(ObjectInputStream in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 372 */     in.defaultReadObject();
/* 373 */     doReadObject(in);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static class Cursor<E>
/*     */     extends AbstractLinkedList.LinkedListIterator<E>
/*     */   {
/* 385 */     boolean valid = true;
/*     */     
/*     */ 
/*     */ 
/* 389 */     boolean nextIndexValid = true;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected Cursor(CursorableLinkedList<E> parent, int index)
/*     */     {
/* 397 */       super(index);
/* 398 */       this.valid = true;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void add(E obj)
/*     */     {
/* 408 */       super.add(obj);
/*     */       
/* 410 */       this.next = this.next.next;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public int nextIndex()
/*     */     {
/* 419 */       if (!this.nextIndexValid) {
/* 420 */         if (this.next == this.parent.header) {
/* 421 */           this.nextIndex = this.parent.size();
/*     */         } else {
/* 423 */           int pos = 0;
/* 424 */           AbstractLinkedList.Node temp = this.parent.header.next;
/* 425 */           while (temp != this.next) {
/* 426 */             pos++;
/* 427 */             temp = temp.next;
/*     */           }
/* 429 */           this.nextIndex = pos;
/*     */         }
/* 431 */         this.nextIndexValid = true;
/*     */       }
/* 433 */       return this.nextIndex;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected void nodeChanged(AbstractLinkedList.Node<E> node) {}
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected void nodeRemoved(AbstractLinkedList.Node<E> node)
/*     */     {
/* 451 */       if (node == this.next) {
/* 452 */         this.next = node.next;
/* 453 */       } else if (node == this.current) {
/* 454 */         this.current = null;
/* 455 */         this.nextIndex -= 1;
/*     */       } else {
/* 457 */         this.nextIndexValid = false;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected void nodeInserted(AbstractLinkedList.Node<E> node)
/*     */     {
/* 467 */       if (node.previous == this.current) {
/* 468 */         this.next = node;
/* 469 */       } else if (this.next.previous == node) {
/* 470 */         this.next = node;
/*     */       } else {
/* 472 */         this.nextIndexValid = false;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     protected void checkModCount()
/*     */     {
/* 480 */       if (!this.valid) {
/* 481 */         throw new ConcurrentModificationException("Cursor closed");
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void close()
/*     */     {
/* 494 */       if (this.valid) {
/* 495 */         ((CursorableLinkedList)this.parent).unregisterCursor(this);
/* 496 */         this.valid = false;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/list/CursorableLinkedList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */