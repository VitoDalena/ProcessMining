/*    */ package flanagan.physchem;
/*    */ 
/*    */ import flanagan.integration.IntegralFunction;
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
/*    */ class FunctionPatX
/*    */   implements IntegralFunction
/*    */ {
/* 52 */   public int numOfIons = 0;
/* 53 */   public double termOne = 0.0D;
/* 54 */   public double expTerm = 0.0D;
/* 55 */   public double[] bulkConcn = null;
/* 56 */   public double[] charges = null;
/*    */   
/*    */   public double function(double x) {
/* 59 */     double sigma = 0.0D;
/* 60 */     for (int i = 0; i < this.numOfIons; i++) {
/* 61 */       sigma += this.bulkConcn[i] * this.termOne * (Math.exp(-this.expTerm * this.charges[i] * x) - 1.0D);
/*    */     }
/* 63 */     return 1.0D / Math.sqrt(sigma);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/physchem/FunctionPatX.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */