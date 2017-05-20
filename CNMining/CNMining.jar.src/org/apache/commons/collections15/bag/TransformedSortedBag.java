/*    */ package org.apache.commons.collections15.bag;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.apache.commons.collections15.SortedBag;
/*    */ import org.apache.commons.collections15.Transformer;
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
/*    */ public class TransformedSortedBag
/*    */   extends TransformedBag
/*    */   implements SortedBag
/*    */ {
/*    */   private static final long serialVersionUID = -251737742649401930L;
/*    */   
/*    */   public static <I, O> SortedBag<O> decorate(SortedBag<I> bag, Transformer<? super I, ? extends O> transformer)
/*    */   {
/* 59 */     return new TransformedSortedBag(bag, transformer);
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
/*    */   protected TransformedSortedBag(SortedBag bag, Transformer transformer)
/*    */   {
/* 74 */     super(bag, transformer);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected SortedBag getSortedBag()
/*    */   {
/* 83 */     return (SortedBag)this.collection;
/*    */   }
/*    */   
/*    */   public Object first()
/*    */   {
/* 88 */     return getSortedBag().first();
/*    */   }
/*    */   
/*    */   public Object last() {
/* 92 */     return getSortedBag().last();
/*    */   }
/*    */   
/*    */   public Comparator comparator() {
/* 96 */     return getSortedBag().comparator();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/bag/TransformedSortedBag.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */