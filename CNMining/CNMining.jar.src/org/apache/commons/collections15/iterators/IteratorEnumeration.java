/*    */ package org.apache.commons.collections15.iterators;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import java.util.Iterator;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IteratorEnumeration<E>
/*    */   implements Enumeration<E>
/*    */ {
/*    */   private Iterator<E> iterator;
/*    */   
/*    */   public IteratorEnumeration() {}
/*    */   
/*    */   public IteratorEnumeration(Iterator<E> iterator)
/*    */   {
/* 54 */     this.iterator = iterator;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean hasMoreElements()
/*    */   {
/* 66 */     return this.iterator.hasNext();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public E nextElement()
/*    */   {
/* 78 */     return (E)this.iterator.next();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Iterator<E> getIterator()
/*    */   {
/* 90 */     return this.iterator;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setIterator(Iterator<E> iterator)
/*    */   {
/* 99 */     this.iterator = iterator;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/IteratorEnumeration.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */