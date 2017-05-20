/*    */ package org.apache.commons.math.ode;
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
/*    */ class ClassicalRungeKuttaStepInterpolator
/*    */   extends RungeKuttaStepInterpolator
/*    */ {
/*    */   private static final long serialVersionUID = -6576285612589783992L;
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
/*    */   public ClassicalRungeKuttaStepInterpolator() {}
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
/*    */   public ClassicalRungeKuttaStepInterpolator(ClassicalRungeKuttaStepInterpolator interpolator)
/*    */   {
/* 67 */     super(interpolator);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected StepInterpolator doCopy()
/*    */   {
/* 74 */     return new ClassicalRungeKuttaStepInterpolator(this);
/*    */   }
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
/*    */   protected void computeInterpolatedState(double theta, double oneMinusThetaH)
/*    */     throws DerivativeException
/*    */   {
/* 91 */     double fourTheta = 4.0D * theta;
/* 92 */     double s = oneMinusThetaH / 6.0D;
/* 93 */     double coeff1 = s * ((-fourTheta + 5.0D) * theta - 1.0D);
/* 94 */     double coeff23 = s * ((fourTheta - 2.0D) * theta - 2.0D);
/* 95 */     double coeff4 = s * ((-fourTheta - 1.0D) * theta - 1.0D);
/*    */     
/* 97 */     for (int i = 0; i < this.interpolatedState.length; i++) {
/* 98 */       this.interpolatedState[i] = (this.currentState[i] + coeff1 * this.yDotK[0][i] + coeff23 * (this.yDotK[1][i] + this.yDotK[2][i]) + coeff4 * this.yDotK[3][i]);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/ClassicalRungeKuttaStepInterpolator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */