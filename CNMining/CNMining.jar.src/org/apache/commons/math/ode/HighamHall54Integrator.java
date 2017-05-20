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
/*     */ public class HighamHall54Integrator
/*     */   extends EmbeddedRungeKuttaIntegrator
/*     */ {
/*     */   private static final String methodName = "Higham-Hall 5(4)";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  41 */   private static final double[] staticC = { 0.2222222222222222D, 0.3333333333333333D, 0.5D, 0.6D, 1.0D, 1.0D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  46 */   private static final double[][] staticA = { { 0.2222222222222222D }, { 0.08333333333333333D, 0.25D }, { 0.125D, 0.0D, 0.375D }, { 0.182D, -0.27D, 0.624D, 0.064D }, { -0.55D, 1.35D, 2.4D, -7.2D, 5.0D }, { 0.08333333333333333D, 0.0D, 0.84375D, -1.3333333333333333D, 1.3020833333333333D, 0.10416666666666667D } };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  56 */   private static final double[] staticB = { 0.08333333333333333D, 0.0D, 0.84375D, -1.3333333333333333D, 1.3020833333333333D, 0.10416666666666667D, 0.0D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  61 */   private static final double[] staticE = { -0.05D, 0.0D, 0.50625D, -1.2D, 0.78125D, 0.0625D, -0.1D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public HighamHall54Integrator(double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance)
/*     */   {
/*  77 */     super(false, staticC, staticA, staticB, new HighamHall54StepInterpolator(), minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
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
/*     */   public HighamHall54Integrator(double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance)
/*     */   {
/*  93 */     super(false, staticC, staticA, staticB, new HighamHall54StepInterpolator(), minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/* 101 */     return "Higham-Hall 5(4)";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getOrder()
/*     */   {
/* 108 */     return 5;
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
/*     */   protected double estimateError(double[][] yDotK, double[] y0, double[] y1, double h)
/*     */   {
/* 122 */     double error = 0.0D;
/*     */     
/* 124 */     for (int j = 0; j < y0.length; j++) {
/* 125 */       double errSum = staticE[0] * yDotK[0][j];
/* 126 */       for (int l = 1; l < staticE.length; l++) {
/* 127 */         errSum += staticE[l] * yDotK[l][j];
/*     */       }
/*     */       
/* 130 */       double yScale = Math.max(Math.abs(y0[j]), Math.abs(y1[j]));
/* 131 */       double tol = this.vecAbsoluteTolerance == null ? this.scalAbsoluteTolerance + this.scalRelativeTolerance * yScale : this.vecAbsoluteTolerance[j] + this.vecRelativeTolerance[j] * yScale;
/*     */       
/*     */ 
/* 134 */       double ratio = h * errSum / tol;
/* 135 */       error += ratio * ratio;
/*     */     }
/*     */     
/*     */ 
/* 139 */     return Math.sqrt(error / y0.length);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/HighamHall54Integrator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */