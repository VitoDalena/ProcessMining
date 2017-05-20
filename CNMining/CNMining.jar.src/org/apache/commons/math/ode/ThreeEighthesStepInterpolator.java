/*     */ package org.apache.commons.math.ode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ThreeEighthesStepInterpolator
/*     */   extends RungeKuttaStepInterpolator
/*     */ {
/*     */   private static final long serialVersionUID = -3345024435978721931L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ThreeEighthesStepInterpolator() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ThreeEighthesStepInterpolator(ThreeEighthesStepInterpolator interpolator)
/*     */   {
/*  67 */     super(interpolator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected StepInterpolator doCopy()
/*     */   {
/*  74 */     return new ThreeEighthesStepInterpolator(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void computeInterpolatedState(double theta, double oneMinusThetaH)
/*     */     throws DerivativeException
/*     */   {
/*  92 */     double fourTheta2 = 4.0D * theta * theta;
/*  93 */     double s = oneMinusThetaH / 8.0D;
/*  94 */     double coeff1 = s * (1.0D - 7.0D * theta + 2.0D * fourTheta2);
/*  95 */     double coeff2 = 3.0D * s * (1.0D + theta - fourTheta2);
/*  96 */     double coeff3 = 3.0D * s * (1.0D + theta);
/*  97 */     double coeff4 = s * (1.0D + theta + fourTheta2);
/*     */     
/*  99 */     for (int i = 0; i < this.interpolatedState.length; i++) {
/* 100 */       this.interpolatedState[i] = (this.currentState[i] - coeff1 * this.yDotK[0][i] - coeff2 * this.yDotK[1][i] - coeff3 * this.yDotK[2][i] - coeff4 * this.yDotK[3][i]);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/ThreeEighthesStepInterpolator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */