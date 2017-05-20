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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FactoryTransformer<I, T>
/*    */   implements Transformer<I, T>, Serializable
/*    */ {
/*    */   static final long serialVersionUID = -6817674502475353160L;
/*    */   private final Factory<T> iFactory;
/*    */   
/*    */   public static <K, T> Transformer<K, T> getInstance(Factory<T> factory)
/*    */   {
/* 51 */     if (factory == null) {
/* 52 */       throw new IllegalArgumentException("Factory must not be null");
/*    */     }
/* 54 */     return new FactoryTransformer(factory);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public FactoryTransformer(Factory<T> factory)
/*    */   {
/* 65 */     this.iFactory = factory;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public T transform(I input)
/*    */   {
/* 76 */     return (T)this.iFactory.create();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Factory<T> getFactory()
/*    */   {
/* 86 */     return this.iFactory;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/FactoryTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */