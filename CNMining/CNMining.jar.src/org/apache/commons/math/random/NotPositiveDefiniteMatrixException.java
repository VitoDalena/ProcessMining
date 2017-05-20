/*    */ package org.apache.commons.math.random;
/*    */ 
/*    */ import org.apache.commons.math.MathException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NotPositiveDefiniteMatrixException
/*    */   extends MathException
/*    */ {
/*    */   private static final long serialVersionUID = 4122929125438624648L;
/*    */   
/*    */   public NotPositiveDefiniteMatrixException()
/*    */   {
/* 39 */     super("not positive definite matrix", new Object[0]);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/random/NotPositiveDefiniteMatrixException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */