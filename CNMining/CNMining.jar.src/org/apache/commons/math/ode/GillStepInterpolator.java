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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class GillStepInterpolator
/*     */   extends RungeKuttaStepInterpolator
/*     */ {
/*     */   public GillStepInterpolator() {}
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public GillStepInterpolator(GillStepInterpolator interpolator)
/*     */   {
/*  65 */     super(interpolator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected StepInterpolator doCopy()
/*     */   {
/*  72 */     return new GillStepInterpolator(this);
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
/*  90 */     double fourTheta = 4.0D * theta;
/*  91 */     double s = oneMinusThetaH / 6.0D;
/*  92 */     double soMt = s * (1.0D - theta);
/*  93 */     double c23 = soMt * (1.0D + 2.0D * theta);
/*  94 */     double coeff1 = soMt * (1.0D - fourTheta);
/*  95 */     double coeff2 = c23 * tMq;
/*  96 */     double coeff3 = c23 * tPq;
/*  97 */     double coeff4 = s * (1.0D + theta * (1.0D + fourTheta));
/*     */     
/*  99 */     for (int i = 0; i < this.interpolatedState.length; i++) {
/* 100 */       this.interpolatedState[i] = (this.currentState[i] - coeff1 * this.yDotK[0][i] - coeff2 * this.yDotK[1][i] - coeff3 * this.yDotK[2][i] - coeff4 * this.yDotK[3][i]);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 108 */   private static final double tMq = 2.0D - Math.sqrt(2.0D);
/*     */   
/*     */ 
/* 111 */   private static final double tPq = 2.0D + Math.sqrt(2.0D);
/*     */   private static final long serialVersionUID = -107804074496313322L;
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/GillStepInterpolator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */