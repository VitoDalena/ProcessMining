/*     */ package org.apache.commons.math.ode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StepNormalizer
/*     */   implements StepHandler
/*     */ {
/*     */   private double h;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private FixedStepHandler handler;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double lastTime;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double[] lastState;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean forward;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StepNormalizer(double h, FixedStepHandler handler)
/*     */   {
/*  56 */     this.h = Math.abs(h);
/*  57 */     this.handler = handler;
/*  58 */     reset();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean requiresDenseOutput()
/*     */   {
/*  68 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reset()
/*     */   {
/*  76 */     this.lastTime = NaN.0D;
/*  77 */     this.lastState = null;
/*  78 */     this.forward = true;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public void handleStep(StepInterpolator interpolator, boolean isLast)
/*     */     throws DerivativeException
/*     */   {
/*  99 */     if (this.lastState == null)
/*     */     {
/* 101 */       this.lastTime = interpolator.getPreviousTime();
/* 102 */       interpolator.setInterpolatedTime(this.lastTime);
/*     */       
/* 104 */       double[] state = interpolator.getInterpolatedState();
/* 105 */       this.lastState = ((double[])state.clone());
/*     */       
/*     */ 
/* 108 */       this.forward = (interpolator.getCurrentTime() >= this.lastTime);
/* 109 */       if (!this.forward) {
/* 110 */         this.h = (-this.h);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 115 */     double nextTime = this.lastTime + this.h;
/* 116 */     boolean nextInStep = this.forward ^ nextTime > interpolator.getCurrentTime();
/* 117 */     while (nextInStep)
/*     */     {
/*     */ 
/* 120 */       this.handler.handleStep(this.lastTime, this.lastState, false);
/*     */       
/*     */ 
/* 123 */       this.lastTime = nextTime;
/* 124 */       interpolator.setInterpolatedTime(this.lastTime);
/* 125 */       System.arraycopy(interpolator.getInterpolatedState(), 0, this.lastState, 0, this.lastState.length);
/*     */       
/*     */ 
/* 128 */       nextTime += this.h;
/* 129 */       nextInStep = this.forward ^ nextTime > interpolator.getCurrentTime();
/*     */     }
/*     */     
/*     */ 
/* 133 */     if (isLast)
/*     */     {
/*     */ 
/* 136 */       this.handler.handleStep(this.lastTime, this.lastState, true);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/StepNormalizer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */