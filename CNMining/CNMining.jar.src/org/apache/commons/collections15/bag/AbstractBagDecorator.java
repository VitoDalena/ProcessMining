/*    */ package org.apache.commons.collections15.bag;
/*    */ 
/*    */ import java.util.Set;
/*    */ import org.apache.commons.collections15.Bag;
/*    */ import org.apache.commons.collections15.collection.AbstractCollectionDecorator;
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
/*    */ 
/*    */ public abstract class AbstractBagDecorator<E>
/*    */   extends AbstractCollectionDecorator<E>
/*    */   implements Bag<E>
/*    */ {
/*    */   protected AbstractBagDecorator() {}
/*    */   
/*    */   protected AbstractBagDecorator(Bag<E> bag)
/*    */   {
/* 51 */     super(bag);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected Bag<E> getBag()
/*    */   {
/* 60 */     return (Bag)getCollection();
/*    */   }
/*    */   
/*    */   public int getCount(E object)
/*    */   {
/* 65 */     return getBag().getCount(object);
/*    */   }
/*    */   
/*    */   public boolean add(E object, int count) {
/* 69 */     return getBag().add(object, count);
/*    */   }
/*    */   
/*    */   public boolean remove(E object, int count) {
/* 73 */     return getBag().remove(object, count);
/*    */   }
/*    */   
/*    */   public Set<E> uniqueSet() {
/* 77 */     return getBag().uniqueSet();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/bag/AbstractBagDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */