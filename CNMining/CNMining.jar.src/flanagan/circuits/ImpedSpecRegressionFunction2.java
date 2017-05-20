/*    */ package flanagan.circuits;
/*    */ 
/*    */ import flanagan.analysis.RegressionFunction2;
/*    */ import flanagan.complex.Complex;
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
/*    */ public class ImpedSpecRegressionFunction2
/*    */   implements RegressionFunction2
/*    */ {
/* 43 */   public int numberOfFrequencies = 0;
/* 44 */   protected ImpedSpecModel isModel = null;
/*    */   
/*    */   public double function(double[] parameters, double[] omega, int pointN)
/*    */   {
/* 48 */     Complex zVal = this.isModel.modelImpedance(parameters, omega[0]);
/*    */     
/* 50 */     if (pointN < this.numberOfFrequencies) {
/* 51 */       return zVal.getReal();
/*    */     }
/*    */     
/* 54 */     return zVal.getImag();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/circuits/ImpedSpecRegressionFunction2.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */