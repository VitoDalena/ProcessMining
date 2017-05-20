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
/*    */ public class MaxIterationsExceededException
/*    */   extends ConvergenceException
/*    */ {
/*    */   private static final long serialVersionUID = -2154780004193976271L;
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
/*    */   private int maxIterations;
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
/*    */   public MaxIterationsExceededException(int maxIterations)
/*    */   {
/* 43 */     super("Maximal number of iterations ({0}) exceeded", new Object[] { new Integer(maxIterations) });
/*    */     
/* 45 */     this.maxIterations = maxIterations;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public MaxIterationsExceededException(int maxIterations, String pattern, Object[] arguments)
/*    */   {
/* 57 */     super(pattern, arguments);
/* 58 */     this.maxIterations = maxIterations;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public int getMaxIterations()
/*    */   {
/* 65 */     return this.maxIterations;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/MaxIterationsExceededException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */