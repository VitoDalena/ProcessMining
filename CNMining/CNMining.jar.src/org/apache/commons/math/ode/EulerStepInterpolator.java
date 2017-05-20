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
/*    */ class EulerStepInterpolator
/*    */   extends RungeKuttaStepInterpolator
/*    */ {
/*    */   private static final long serialVersionUID = -7179861704951334960L;
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
/*    */   public EulerStepInterpolator() {}
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
/*    */   public EulerStepInterpolator(EulerStepInterpolator interpolator)
/*    */   {
/* 60 */     super(interpolator);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected StepInterpolator doCopy()
/*    */   {
/* 67 */     return new EulerStepInterpolator(this);
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
/*    */ 
/*    */   protected void computeInterpolatedState(double theta, double oneMinusThetaH)
/*    */     throws DerivativeException
/*    */   {
/* 85 */     for (int i = 0; i < this.interpolatedState.length; i++) {
/* 86 */       this.interpolatedState[i] = (this.currentState[i] - oneMinusThetaH * this.yDotK[0][i]);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/EulerStepInterpolator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */