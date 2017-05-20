/*    */ package org.apache.commons.math.optimization;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CostException
/*    */   extends MathException
/*    */ {
/*    */   private static final long serialVersionUID = 467695563268795689L;
/*    */   
/*    */   public CostException(String pattern, Object[] arguments)
/*    */   {
/* 43 */     super(pattern, arguments);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public CostException(Throwable rootCause)
/*    */   {
/* 54 */     super(rootCause);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/optimization/CostException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */