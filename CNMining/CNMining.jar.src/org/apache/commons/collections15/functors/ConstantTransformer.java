/*    */ package org.apache.commons.collections15.functors;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ public class ConstantTransformer<T>
/*    */   implements Transformer<Object, T>, Serializable
/*    */ {
/*    */   static final long serialVersionUID = 6374440726369055124L;
/* 44 */   public static final Transformer NULL_INSTANCE = new ConstantTransformer(null);
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private final T iConstant;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static <T> Transformer<Object, T> getInstance(T constantToReturn)
/*    */   {
/* 58 */     if (constantToReturn == null) {
/* 59 */       return NULL_INSTANCE;
/*    */     }
/* 61 */     return new ConstantTransformer(constantToReturn);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public ConstantTransformer(T constantToReturn)
/*    */   {
/* 72 */     this.iConstant = constantToReturn;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public T transform(Object input)
/*    */   {
/* 82 */     return (T)this.iConstant;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public T getConstant()
/*    */   {
/* 92 */     return (T)this.iConstant;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/ConstantTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */