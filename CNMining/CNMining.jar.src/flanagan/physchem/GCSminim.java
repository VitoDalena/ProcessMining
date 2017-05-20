/*    */ package flanagan.physchem;
/*    */ 
/*    */ import flanagan.math.MinimisationFunction;
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
/*    */ class GCSminim
/*    */   implements MinimisationFunction
/*    */ {
/* 35 */   public double psiDelta = 0.0D;
/* 36 */   public double tempK = 298.15D;
/* 37 */   public double surfaceSiteDensity = 0.0D;
/* 38 */   public double surfaceArea = 0.0D;
/* 39 */   public double volume = 0.0D;
/* 40 */   public int nonZeroAssocK = 0;
/* 41 */   public double[] assocK = null;
/* 42 */   public double[] initConcn = null;
/* 43 */   public double[] charges = null;
/* 44 */   public int[] indexK = null;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public double function(double[] x)
/*    */   {
/* 54 */     double gFunction = 0.0D;
/* 55 */     double arg = 0.0D;
/* 56 */     int ii = 0;
/* 57 */     double convFac = this.surfaceArea / this.volume;
/* 58 */     double expTerm = this.psiDelta * -1.60217646263E-19D / (1.380650324E-23D * this.tempK);
/* 59 */     double innerSumTerm = 0.0D;
/*    */     
/* 61 */     for (int i = 0; i < this.nonZeroAssocK; i++) {
/* 62 */       innerSumTerm += x[0];
/*    */     }
/* 64 */     innerSumTerm = this.surfaceSiteDensity - innerSumTerm;
/*    */     
/* 66 */     for (int i = 0; i < this.nonZeroAssocK; i++) {
/* 67 */       ii = this.indexK[i];
/* 68 */       arg = this.assocK[ii] * (this.initConcn[ii] - x[i] * convFac) * Math.exp(expTerm * this.charges[ii]) * innerSumTerm - x[0];
/* 69 */       gFunction += arg * arg;
/*    */     }
/* 71 */     return gFunction;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/physchem/GCSminim.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */