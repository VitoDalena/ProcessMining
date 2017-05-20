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
/*    */ public class ClosureTransformer<T>
/*    */   implements Transformer<T, T>, Serializable
/*    */ {
/*    */   static final long serialVersionUID = 478466901448617286L;
/*    */   private final Closure<T> iClosure;
/*    */   
/*    */   public static <T> Transformer<T, T> getInstance(Closure<T> closure)
/*    */   {
/* 52 */     if (closure == null) {
/* 53 */       throw new IllegalArgumentException("Closure must not be null");
/*    */     }
/* 55 */     return new ClosureTransformer(closure);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public ClosureTransformer(Closure<T> closure)
/*    */   {
/* 66 */     this.iClosure = closure;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public T transform(T input)
/*    */   {
/* 76 */     this.iClosure.execute(input);
/* 77 */     return input;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Closure<T> getClosure()
/*    */   {
/* 87 */     return this.iClosure;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/ClosureTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */