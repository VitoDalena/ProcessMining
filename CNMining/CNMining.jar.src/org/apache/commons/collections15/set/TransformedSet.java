/*    */ package org.apache.commons.collections15.set;
/*    */ 
/*    */ import java.util.Set;
/*    */ import org.apache.commons.collections15.Transformer;
/*    */ import org.apache.commons.collections15.collection.TransformedCollection;
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
/*    */ public class TransformedSet
/*    */   extends TransformedCollection
/*    */   implements Set
/*    */ {
/*    */   private static final long serialVersionUID = 306127383500410386L;
/*    */   
/*    */   public static <I, O> Set<O> decorate(Set<I> set, Transformer<? super I, ? extends O> transformer)
/*    */   {
/* 58 */     return new TransformedSet(set, transformer);
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
/*    */   protected TransformedSet(Set set, Transformer transformer)
/*    */   {
/* 73 */     super(set, transformer);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/set/TransformedSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */