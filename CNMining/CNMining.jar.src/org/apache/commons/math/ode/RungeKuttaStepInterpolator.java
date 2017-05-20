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
/*     */ abstract class RungeKuttaStepInterpolator
/*     */   extends AbstractStepInterpolator
/*     */ {
/*     */   protected double[][] yDotK;
/*     */   protected FirstOrderDifferentialEquations equations;
/*     */   
/*     */   protected RungeKuttaStepInterpolator()
/*     */   {
/*  49 */     this.yDotK = ((double[][])null);
/*  50 */     this.equations = null;
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
/*     */   public RungeKuttaStepInterpolator(RungeKuttaStepInterpolator interpolator)
/*     */   {
/*  72 */     super(interpolator);
/*     */     
/*  74 */     if (interpolator.currentState != null) {
/*  75 */       int dimension = this.currentState.length;
/*     */       
/*  77 */       this.yDotK = new double[interpolator.yDotK.length][];
/*  78 */       for (int k = 0; k < interpolator.yDotK.length; k++) {
/*  79 */         this.yDotK[k] = new double[dimension];
/*  80 */         System.arraycopy(interpolator.yDotK[k], 0, this.yDotK[k], 0, dimension);
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  85 */       this.yDotK = ((double[][])null);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  90 */     this.equations = null;
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
/*     */   public void reinitialize(FirstOrderDifferentialEquations equations, double[] y, double[][] yDotK, boolean forward)
/*     */   {
/* 117 */     reinitialize(y, forward);
/* 118 */     this.yDotK = yDotK;
/* 119 */     this.equations = equations;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void writeExternal(ObjectOutput out)
/*     */     throws IOException
/*     */   {
/* 130 */     writeBaseExternal(out);
/*     */     
/*     */ 
/* 133 */     out.writeInt(this.yDotK.length);
/* 134 */     for (int k = 0; k < this.yDotK.length; k++) {
/* 135 */       for (int i = 0; i < this.currentState.length; i++) {
/* 136 */         out.writeDouble(this.yDotK[k][i]);
/*     */       }
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
/*     */   public void readExternal(ObjectInput in)
/*     */     throws IOException
/*     */   {
/* 152 */     double t = readBaseExternal(in);
/*     */     
/*     */ 
/* 155 */     int kMax = in.readInt();
/* 156 */     this.yDotK = new double[kMax][];
/* 157 */     for (int k = 0; k < kMax; k++) {
/* 158 */       this.yDotK[k] = new double[this.currentState.length];
/* 159 */       for (int i = 0; i < this.currentState.length; i++) {
/* 160 */         this.yDotK[k][i] = in.readDouble();
/*     */       }
/*     */     }
/*     */     
/* 164 */     this.equations = null;
/*     */     
/*     */     try
/*     */     {
/* 168 */       setInterpolatedTime(t);
/*     */     } catch (DerivativeException e) {
/* 170 */       throw new IOException(e.getMessage());
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/RungeKuttaStepInterpolator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */