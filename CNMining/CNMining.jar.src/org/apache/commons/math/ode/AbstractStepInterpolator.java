/*     */ package org.apache.commons.math.ode;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
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
/*     */ 
/*     */ public abstract class AbstractStepInterpolator
/*     */   implements StepInterpolator
/*     */ {
/*     */   protected double previousTime;
/*     */   protected double currentTime;
/*     */   protected double h;
/*     */   protected double[] currentState;
/*     */   protected double interpolatedTime;
/*     */   protected double[] interpolatedState;
/*     */   private boolean finalized;
/*     */   private boolean forward;
/*     */   
/*     */   protected AbstractStepInterpolator()
/*     */   {
/*  79 */     this.previousTime = NaN.0D;
/*  80 */     this.currentTime = NaN.0D;
/*  81 */     this.h = NaN.0D;
/*  82 */     this.interpolatedTime = NaN.0D;
/*  83 */     this.currentState = null;
/*  84 */     this.interpolatedState = null;
/*  85 */     this.finalized = false;
/*  86 */     this.forward = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected AbstractStepInterpolator(double[] y, boolean forward)
/*     */   {
/*  96 */     this.previousTime = NaN.0D;
/*  97 */     this.currentTime = NaN.0D;
/*  98 */     this.h = NaN.0D;
/*  99 */     this.interpolatedTime = NaN.0D;
/*     */     
/* 101 */     this.currentState = y;
/* 102 */     this.interpolatedState = new double[y.length];
/*     */     
/* 104 */     this.finalized = false;
/* 105 */     this.forward = forward;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   protected AbstractStepInterpolator(AbstractStepInterpolator interpolator)
/*     */   {
/* 128 */     this.previousTime = interpolator.previousTime;
/* 129 */     this.currentTime = interpolator.currentTime;
/* 130 */     this.h = interpolator.h;
/* 131 */     this.interpolatedTime = interpolator.interpolatedTime;
/*     */     
/* 133 */     if (interpolator.currentState != null) {
/* 134 */       this.currentState = ((double[])interpolator.currentState.clone());
/* 135 */       this.interpolatedState = ((double[])interpolator.interpolatedState.clone());
/*     */     } else {
/* 137 */       this.currentState = null;
/* 138 */       this.interpolatedState = null;
/*     */     }
/*     */     
/* 141 */     this.finalized = interpolator.finalized;
/* 142 */     this.forward = interpolator.forward;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void reinitialize(double[] y, boolean forward)
/*     */   {
/* 153 */     this.previousTime = NaN.0D;
/* 154 */     this.currentTime = NaN.0D;
/* 155 */     this.h = NaN.0D;
/* 156 */     this.interpolatedTime = NaN.0D;
/*     */     
/* 158 */     this.currentState = y;
/* 159 */     this.interpolatedState = new double[y.length];
/*     */     
/* 161 */     this.finalized = false;
/* 162 */     this.forward = forward;
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
/*     */   public StepInterpolator copy()
/*     */     throws DerivativeException
/*     */   {
/* 178 */     finalizeStep();
/*     */     
/*     */ 
/* 181 */     return doCopy();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract StepInterpolator doCopy();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void shift()
/*     */   {
/* 199 */     this.previousTime = this.currentTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void storeTime(double t)
/*     */   {
/* 207 */     this.currentTime = t;
/* 208 */     this.h = (this.currentTime - this.previousTime);
/* 209 */     this.interpolatedTime = t;
/* 210 */     System.arraycopy(this.currentState, 0, this.interpolatedState, 0, this.currentState.length);
/*     */     
/*     */ 
/*     */ 
/* 214 */     this.finalized = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getPreviousTime()
/*     */   {
/* 223 */     return this.previousTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getCurrentTime()
/*     */   {
/* 231 */     return this.currentTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getInterpolatedTime()
/*     */   {
/* 241 */     return this.interpolatedTime;
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
/*     */   public void setInterpolatedTime(double time)
/*     */     throws DerivativeException
/*     */   {
/* 258 */     this.interpolatedTime = time;
/* 259 */     double oneMinusThetaH = this.currentTime - this.interpolatedTime;
/* 260 */     computeInterpolatedState((this.h - oneMinusThetaH) / this.h, oneMinusThetaH);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isForward()
/*     */   {
/* 272 */     return this.forward;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract void computeInterpolatedState(double paramDouble1, double paramDouble2)
/*     */     throws DerivativeException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[] getInterpolatedState()
/*     */   {
/* 294 */     return (double[])this.interpolatedState.clone();
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
/*     */   public final void finalizeStep()
/*     */     throws DerivativeException
/*     */   {
/* 342 */     if (!this.finalized) {
/* 343 */       doFinalize();
/* 344 */       this.finalized = true;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void doFinalize()
/*     */     throws DerivativeException
/*     */   {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract void writeExternal(ObjectOutput paramObjectOutput)
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract void readExternal(ObjectInput paramObjectInput)
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void writeBaseExternal(ObjectOutput out)
/*     */     throws IOException
/*     */   {
/* 381 */     out.writeInt(this.currentState.length);
/* 382 */     out.writeDouble(this.previousTime);
/* 383 */     out.writeDouble(this.currentTime);
/* 384 */     out.writeDouble(this.h);
/* 385 */     out.writeBoolean(this.forward);
/*     */     
/* 387 */     for (int i = 0; i < this.currentState.length; i++) {
/* 388 */       out.writeDouble(this.currentState[i]);
/*     */     }
/*     */     
/* 391 */     out.writeDouble(this.interpolatedTime);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     try
/*     */     {
/* 398 */       finalizeStep();
/*     */     } catch (DerivativeException e) {
/* 400 */       throw new IOException(e.getMessage());
/*     */     }
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
/*     */   protected double readBaseExternal(ObjectInput in)
/*     */     throws IOException
/*     */   {
/* 417 */     int dimension = in.readInt();
/* 418 */     this.previousTime = in.readDouble();
/* 419 */     this.currentTime = in.readDouble();
/* 420 */     this.h = in.readDouble();
/* 421 */     this.forward = in.readBoolean();
/*     */     
/* 423 */     this.currentState = new double[dimension];
/* 424 */     for (int i = 0; i < this.currentState.length; i++) {
/* 425 */       this.currentState[i] = in.readDouble();
/*     */     }
/*     */     
/*     */ 
/* 429 */     this.interpolatedTime = NaN.0D;
/* 430 */     this.interpolatedState = new double[dimension];
/*     */     
/* 432 */     this.finalized = true;
/*     */     
/* 434 */     return in.readDouble();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/AbstractStepInterpolator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */