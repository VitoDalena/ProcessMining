/*    */ package org.apache.commons.collections15.collection;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import org.apache.commons.collections15.Unmodifiable;
/*    */ import org.apache.commons.collections15.iterators.UnmodifiableIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class UnmodifiableCollection<E>
/*    */   extends AbstractSerializableCollectionDecorator<E>
/*    */   implements Unmodifiable
/*    */ {
/*    */   private static final long serialVersionUID = -239892006883819945L;
/*    */   
/*    */   public static <E> Collection<E> decorate(Collection<E> coll)
/*    */   {
/* 51 */     if ((coll instanceof Unmodifiable)) {
/* 52 */       return coll;
/*    */     }
/* 54 */     return new UnmodifiableCollection(coll);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private UnmodifiableCollection(Collection<E> coll)
/*    */   {
/* 65 */     super(coll);
/*    */   }
/*    */   
/*    */   public Iterator<E> iterator()
/*    */   {
/* 70 */     return UnmodifiableIterator.decorate(getCollection().iterator());
/*    */   }
/*    */   
/*    */   public boolean add(E object) {
/* 74 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public boolean addAll(Collection<? extends E> coll) {
/* 78 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public void clear() {
/* 82 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public boolean remove(Object object) {
/* 86 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public boolean removeAll(Collection<?> coll) {
/* 90 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public boolean retainAll(Collection<?> coll) {
/* 94 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/collection/UnmodifiableCollection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */