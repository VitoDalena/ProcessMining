/*    */ package org.apache.commons.math.linear;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InvalidMatrixException
/*    */   extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 5318837237354354107L;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public InvalidMatrixException()
/*    */   {
/* 35 */     this(null);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public InvalidMatrixException(String message)
/*    */   {
/* 43 */     super(message);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/linear/InvalidMatrixException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */