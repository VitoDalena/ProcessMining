/*    */ package org.apache.commons.math.stat.inference;
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
/*    */ /**
/*    */  * @deprecated
/*    */  */
/*    */ public class TestFactoryImpl
/*    */   extends TestFactory
/*    */ {
/*    */   public TTest createTTest()
/*    */   {
/* 43 */     return new TTestImpl();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public ChiSquareTest createChiSquareTest()
/*    */   {
/* 52 */     return new ChiSquareTestImpl();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/inference/TestFactoryImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */