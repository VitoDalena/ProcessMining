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
/*    */ class MidpointStepInterpolator
/*    */   extends RungeKuttaStepInterpolator
/*    */ {
/*    */   private static final long serialVersionUID = -865524111506042509L;
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
/*    */   public MidpointStepInterpolator() {}
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
/*    */   public MidpointStepInterpolator(MidpointStepInterpolator interpolator)
/*    */   {
/* 62 */     super(interpolator);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected StepInterpolator doCopy()
/*    */   {
/* 69 */     return new MidpointStepInterpolator(this);
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
/* 87 */     double coeff1 = oneMinusThetaH * theta;
/* 88 */     double coeff2 = oneMinusThetaH * (1.0D + theta);
/*    */     
/* 90 */     for (int i = 0; i < this.interpolatedState.length; i++) {
/* 91 */       this.interpolatedState[i] = (this.currentState[i] + coeff1 * this.yDotK[0][i] - coeff2 * this.yDotK[1][i]);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/MidpointStepInterpolator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */