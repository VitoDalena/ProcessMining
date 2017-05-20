/*    */ package cern.jet.stat.quantile;
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
/*    */ class Utils
/*    */ {
/*    */   protected Utils()
/*    */   {
/* 20 */     throw new RuntimeException("Non instantiable");
/*    */   }
/*    */   
/*    */ 
/*    */   public static long epsilonCeiling(double paramDouble)
/*    */   {
/* 26 */     double d = 1.0E-7D;
/* 27 */     return Math.ceil(paramDouble - d);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/stat/quantile/Utils.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */