/*    */ package org.apache.commons.collections15.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections15.Closure;
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
/*    */ public class TransformerClosure<I, O>
/*    */   implements Closure<I>, Serializable
/*    */ {
/*    */   static final long serialVersionUID = -5194992589193388969L;
/*    */   private final Transformer<? super I, O> iTransformer;
/*    */   
/*    */   public static <I, O> Closure<I> getInstance(Transformer<? super I, O> transformer)
/*    */   {
/* 53 */     if (transformer == null) {
/* 54 */       return NOPClosure.INSTANCE;
/*    */     }
/* 56 */     return new TransformerClosure(transformer);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public TransformerClosure(Transformer<? super I, O> transformer)
/*    */   {
/* 67 */     this.iTransformer = transformer;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void execute(I input)
/*    */   {
/* 76 */     this.iTransformer.transform(input);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Transformer<? super I, O> getTransformer()
/*    */   {
/* 86 */     return this.iTransformer;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/TransformerClosure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */