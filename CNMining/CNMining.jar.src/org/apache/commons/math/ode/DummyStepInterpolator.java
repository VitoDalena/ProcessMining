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
/*     */ public class DummyStepInterpolator
/*     */   extends AbstractStepInterpolator
/*     */ {
/*     */   private static final long serialVersionUID = 1708010296707839488L;
/*     */   
/*     */   public DummyStepInterpolator() {}
/*     */   
/*     */   protected DummyStepInterpolator(double[] y, boolean forward)
/*     */   {
/*  61 */     super(y, forward);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DummyStepInterpolator(DummyStepInterpolator interpolator)
/*     */   {
/*  70 */     super(interpolator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected StepInterpolator doCopy()
/*     */   {
/*  77 */     return new DummyStepInterpolator(this);
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
/*     */   protected void computeInterpolatedState(double theta, double oneMinusThetaH)
/*     */     throws DerivativeException
/*     */   {
/*  92 */     System.arraycopy(this.currentState, 0, this.interpolatedState, 0, this.currentState.length);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void writeExternal(ObjectOutput out)
/*     */     throws IOException
/*     */   {
/* 102 */     writeBaseExternal(out);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void readExternal(ObjectInput in)
/*     */     throws IOException
/*     */   {
/* 113 */     double t = readBaseExternal(in);
/*     */     
/*     */     try
/*     */     {
/* 117 */       setInterpolatedTime(t);
/*     */     } catch (DerivativeException e) {
/* 119 */       throw new IOException(e.getMessage());
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/DummyStepInterpolator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */