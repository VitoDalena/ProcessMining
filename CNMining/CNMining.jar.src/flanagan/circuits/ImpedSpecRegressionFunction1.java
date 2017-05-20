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
/*    */ public class ImpedSpecRegressionFunction1
/*    */   extends Impedance
/*    */   implements RegressionFunction2
/*    */ {
/* 42 */   public int numberOfFrequencies = 0;
/* 43 */   public int modelNumber = 0;
/*    */   
/*    */   public double function(double[] parameters, double[] omega, int pointN)
/*    */   {
/* 47 */     Complex zVal = Impedance.modelImpedance(parameters, omega[0], this.modelNumber);
/*    */     
/* 49 */     if (pointN < this.numberOfFrequencies) {
/* 50 */       return zVal.getReal();
/*    */     }
/*    */     
/* 53 */     return zVal.getImag();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/circuits/ImpedSpecRegressionFunction1.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */