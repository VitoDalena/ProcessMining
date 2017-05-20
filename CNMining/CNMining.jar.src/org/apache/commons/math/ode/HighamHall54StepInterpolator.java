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
/*    */ class HighamHall54StepInterpolator
/*    */   extends RungeKuttaStepInterpolator
/*    */ {
/*    */   private static final long serialVersionUID = -3583240427587318654L;
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
/*    */   public HighamHall54StepInterpolator() {}
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
/*    */   public HighamHall54StepInterpolator(HighamHall54StepInterpolator interpolator)
/*    */   {
/* 52 */     super(interpolator);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected StepInterpolator doCopy()
/*    */   {
/* 59 */     return new HighamHall54StepInterpolator(this);
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
/*    */   protected void computeInterpolatedState(double theta, double oneMinusThetaH)
/*    */     throws DerivativeException
/*    */   {
/* 75 */     double theta2 = theta * theta;
/*    */     
/* 77 */     double b0 = this.h * (-0.08333333333333333D + theta * (1.0D + theta * (-3.75D + theta * (5.333333333333333D + theta * -5.0D / 2.0D))));
/* 78 */     double b2 = this.h * (-0.84375D + theta2 * (14.34375D + theta * (-30.375D + theta * 135.0D / 8.0D)));
/* 79 */     double b3 = this.h * (1.3333333333333333D + theta2 * (-22.0D + theta * (50.666666666666664D + theta * -30.0D)));
/* 80 */     double b4 = this.h * (-1.3020833333333333D + theta2 * (11.71875D + theta * (-26.041666666666668D + theta * 125.0D / 8.0D)));
/* 81 */     double b5 = this.h * (-0.10416666666666667D + theta2 * (-0.3125D + theta * 5.0D / 12.0D));
/*    */     
/* 83 */     for (int i = 0; i < this.interpolatedState.length; i++) {
/* 84 */       this.interpolatedState[i] = (this.currentState[i] + b0 * this.yDotK[0][i] + b2 * this.yDotK[2][i] + b3 * this.yDotK[3][i] + b4 * this.yDotK[4][i] + b5 * this.yDotK[5][i]);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/HighamHall54StepInterpolator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */