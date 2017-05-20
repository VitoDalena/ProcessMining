/*    */ package org.apache.commons.collections15.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections15.Predicate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class InstanceofPredicate
/*    */   implements Predicate, Serializable
/*    */ {
/*    */   static final long serialVersionUID = -6682656911025165584L;
/*    */   private final Class iType;
/*    */   
/*    */   public static Predicate getInstance(Class type)
/*    */   {
/* 53 */     if (type == null) {
/* 54 */       throw new IllegalArgumentException("The type to check instanceof must not be null");
/*    */     }
/* 56 */     return new InstanceofPredicate(type);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public InstanceofPredicate(Class type)
/*    */   {
/* 67 */     this.iType = type;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean evaluate(Object object)
/*    */   {
/* 77 */     return this.iType.isInstance(object);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Class getType()
/*    */   {
/* 87 */     return this.iType;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/InstanceofPredicate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */