/*    */ package org.apache.commons.math.analysis;
/*    */ 
/*    */ import org.apache.commons.discovery.tools.DiscoverClass;
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
/*    */ public abstract class UnivariateRealSolverFactory
/*    */ {
/*    */   public static UnivariateRealSolverFactory newInstance()
/*    */   {
/* 59 */     UnivariateRealSolverFactory factory = null;
/*    */     try {
/* 61 */       DiscoverClass dc = new DiscoverClass();
/* 62 */       factory = (UnivariateRealSolverFactory)dc.newInstance(UnivariateRealSolverFactory.class, "org.apache.commons.math.analysis.UnivariateRealSolverFactoryImpl");
/*    */     }
/*    */     catch (Throwable t)
/*    */     {
/* 66 */       return new UnivariateRealSolverFactoryImpl();
/*    */     }
/* 68 */     return factory;
/*    */   }
/*    */   
/*    */   public abstract UnivariateRealSolver newDefaultSolver(UnivariateRealFunction paramUnivariateRealFunction);
/*    */   
/*    */   public abstract UnivariateRealSolver newBisectionSolver(UnivariateRealFunction paramUnivariateRealFunction);
/*    */   
/*    */   public abstract UnivariateRealSolver newBrentSolver(UnivariateRealFunction paramUnivariateRealFunction);
/*    */   
/*    */   public abstract UnivariateRealSolver newNewtonSolver(DifferentiableUnivariateRealFunction paramDifferentiableUnivariateRealFunction);
/*    */   
/*    */   public abstract UnivariateRealSolver newSecantSolver(UnivariateRealFunction paramUnivariateRealFunction);
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/UnivariateRealSolverFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */