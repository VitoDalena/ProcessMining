/*    */ package org.apache.commons.collections15.comparators;
/*    */ 
/*    */ import java.util.Comparator;
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
/*    */ public class TransformingComparator<I, O>
/*    */   implements Comparator<I>
/*    */ {
/*    */   protected Comparator<O> decorated;
/*    */   protected Transformer<I, O> transformer;
/*    */   
/*    */   public TransformingComparator(Transformer<I, O> transformer, Comparator<O> decorated)
/*    */   {
/* 51 */     this.decorated = decorated;
/* 52 */     this.transformer = transformer;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int compare(I obj1, I obj2)
/*    */   {
/* 64 */     O value1 = this.transformer.transform(obj1);
/* 65 */     O value2 = this.transformer.transform(obj2);
/* 66 */     return this.decorated.compare(value1, value2);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/comparators/TransformingComparator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */