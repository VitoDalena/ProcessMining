/*    */ package org.apache.commons.collections15.set;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.Set;
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
/*    */ public final class UnmodifiableSet<E>
/*    */   extends AbstractSerializableSetDecorator<E>
/*    */   implements Unmodifiable
/*    */ {
/*    */   private static final long serialVersionUID = 6499119872185240161L;
/*    */   
/*    */   public static <E> Set<E> decorate(Set<E> set)
/*    */   {
/* 49 */     if ((set instanceof Unmodifiable)) {
/* 50 */       return set;
/*    */     }
/* 52 */     return new UnmodifiableSet(set);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private UnmodifiableSet(Set<E> set)
/*    */   {
/* 63 */     super(set);
/*    */   }
/*    */   
/*    */   public Iterator<E> iterator()
/*    */   {
/* 68 */     return UnmodifiableIterator.decorate(getCollection().iterator());
/*    */   }
/*    */   
/*    */   public boolean add(E object) {
/* 72 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public boolean addAll(Collection<? extends E> coll) {
/* 76 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public void clear() {
/* 80 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public boolean remove(Object object) {
/* 84 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public boolean removeAll(Collection<?> coll) {
/* 88 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public boolean retainAll(Collection<?> coll) {
/* 92 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/set/UnmodifiableSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */