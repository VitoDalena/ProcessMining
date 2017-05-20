/*    */ package org.apache.commons.math.stat.inference;
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
/*    */ /**
/*    */  * @deprecated
/*    */  */
/*    */ public abstract class TestFactory
/*    */ {
/*    */   public static TestFactory newInstance()
/*    */   {
/* 42 */     TestFactory factory = null;
/*    */     try {
/* 44 */       DiscoverClass dc = new DiscoverClass();
/* 45 */       factory = (TestFactory)dc.newInstance(TestFactory.class, "org.apache.commons.math.stat.inference.TestFactoryImpl");
/*    */     }
/*    */     catch (Throwable t)
/*    */     {
/* 49 */       return new TestFactoryImpl();
/*    */     }
/* 51 */     return factory;
/*    */   }
/*    */   
/*    */   public abstract TTest createTTest();
/*    */   
/*    */   public abstract ChiSquareTest createChiSquareTest();
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/inference/TestFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */