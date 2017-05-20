/*    */ package org.apache.commons.math;
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
/*    */ public class ConvergenceException
/*    */   extends MathException
/*    */ {
/*    */   private static final long serialVersionUID = 4380655778005469702L;
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
/*    */   public ConvergenceException()
/*    */   {
/* 34 */     super("Convergence failed", new Object[0]);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public ConvergenceException(String pattern, Object[] arguments)
/*    */   {
/* 45 */     super(pattern, arguments);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public ConvergenceException(Throwable cause)
/*    */   {
/* 53 */     super(cause);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public ConvergenceException(String pattern, Object[] arguments, Throwable cause)
/*    */   {
/* 65 */     super(pattern, arguments, cause);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public ConvergenceException(String msg, Throwable rootCause)
/*    */   {
/* 79 */     super(msg, rootCause);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public ConvergenceException(String msg)
/*    */   {
/* 91 */     super(msg);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ConvergenceException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */