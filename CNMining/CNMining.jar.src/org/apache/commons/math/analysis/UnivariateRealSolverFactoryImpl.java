/*    */ package org.apache.commons.math.analysis;
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
/*    */ public class UnivariateRealSolverFactoryImpl
/*    */   extends UnivariateRealSolverFactory
/*    */ {
/*    */   public UnivariateRealSolver newDefaultSolver(UnivariateRealFunction f)
/*    */   {
/* 45 */     return newBrentSolver(f);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public UnivariateRealSolver newBisectionSolver(UnivariateRealFunction f)
/*    */   {
/* 55 */     return new BisectionSolver(f);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public UnivariateRealSolver newBrentSolver(UnivariateRealFunction f)
/*    */   {
/* 65 */     return new BrentSolver(f);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public UnivariateRealSolver newNewtonSolver(DifferentiableUnivariateRealFunction f)
/*    */   {
/* 77 */     return new NewtonSolver(f);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public UnivariateRealSolver newSecantSolver(UnivariateRealFunction f)
/*    */   {
/* 87 */     return new SecantSolver(f);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/UnivariateRealSolverFactoryImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */