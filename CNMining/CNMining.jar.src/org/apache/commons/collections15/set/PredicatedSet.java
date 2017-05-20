/*    */ package org.apache.commons.collections15.set;
/*    */ 
/*    */ import java.util.Set;
/*    */ import org.apache.commons.collections15.Predicate;
/*    */ import org.apache.commons.collections15.collection.PredicatedCollection;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PredicatedSet<E>
/*    */   extends PredicatedCollection<E>
/*    */   implements Set<E>
/*    */ {
/*    */   private static final long serialVersionUID = -684521469108685117L;
/*    */   
/*    */   public static <E> Set<E> decorate(Set<E> set, Predicate<? super E> predicate)
/*    */   {
/* 61 */     return new PredicatedSet(set, predicate);
/*    */   }
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
/*    */   protected PredicatedSet(Set<E> set, Predicate<? super E> predicate)
/*    */   {
/* 77 */     super(set, predicate);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected Set<E> getSet()
/*    */   {
/* 86 */     return (Set)getCollection();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/set/PredicatedSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */