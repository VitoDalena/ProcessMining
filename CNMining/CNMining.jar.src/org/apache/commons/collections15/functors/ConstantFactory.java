/*    */ package org.apache.commons.collections15.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections15.Factory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConstantFactory<T>
/*    */   implements Factory<T>, Serializable
/*    */ {
/*    */   static final long serialVersionUID = -3520677225766901240L;
/* 44 */   public static final Factory NULL_INSTANCE = new ConstantFactory(null);
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
/*    */   public static <T> Factory<T> getInstance(T constantToReturn)
/*    */   {
/* 58 */     if (constantToReturn == null) {
/* 59 */       return NULL_INSTANCE;
/*    */     }
/* 61 */     return new ConstantFactory(constantToReturn);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public ConstantFactory(T constantToReturn)
/*    */   {
/* 72 */     this.iConstant = constantToReturn;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public T create()
/*    */   {
/* 81 */     return (T)this.iConstant;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public T getConstant()
/*    */   {
/* 91 */     return (T)this.iConstant;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/ConstantFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */