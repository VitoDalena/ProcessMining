/*    */ package org.apache.commons.collections15.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections15.Factory;
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
/*    */ public class CloneTransformer<T>
/*    */   implements Transformer<T, T>, Serializable
/*    */ {
/*    */   static final long serialVersionUID = -8188742709499652567L;
/* 42 */   public static final Transformer INSTANCE = new CloneTransformer();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static <T> Transformer<T, T> getInstance()
/*    */   {
/* 51 */     return INSTANCE;
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
/*    */ 
/*    */   public T transform(T input)
/*    */   {
/* 68 */     if (input == null) {
/* 69 */       return null;
/*    */     }
/* 71 */     return (T)PrototypeFactory.getInstance(input).create();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/CloneTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */