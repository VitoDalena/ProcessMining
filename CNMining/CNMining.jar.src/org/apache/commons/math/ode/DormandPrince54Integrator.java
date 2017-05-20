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
/*     */ public class DormandPrince54Integrator
/*     */   extends EmbeddedRungeKuttaIntegrator
/*     */ {
/*     */   private static final String methodName = "Dormand-Prince 5(4)";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  53 */   private static final double[] staticC = { 0.2D, 0.3D, 0.8D, 0.8888888888888888D, 1.0D, 1.0D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  58 */   private static final double[][] staticA = { { 0.2D }, { 0.075D, 0.225D }, { 0.9777777777777777D, -3.7333333333333334D, 3.5555555555555554D }, { 2.9525986892242035D, -11.595793324188385D, 9.822892851699436D, -0.2908093278463649D }, { 2.8462752525252526D, -10.757575757575758D, 8.906422717743473D, 0.2784090909090909D, -0.2735313036020583D }, { 0.09114583333333333D, 0.0D, 0.44923629829290207D, 0.6510416666666666D, -0.322376179245283D, 0.13095238095238096D } };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  68 */   private static final double[] staticB = { 0.09114583333333333D, 0.0D, 0.44923629829290207D, 0.6510416666666666D, -0.322376179245283D, 0.13095238095238096D, 0.0D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double e1 = 0.0012326388888888888D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double e3 = -0.0042527702905061394D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double e4 = 0.03697916666666667D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double e5 = -0.05086379716981132D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double e6 = 0.0419047619047619D;
/*     */   
/*     */ 
/*     */ 
/*     */   private static final double e7 = -0.025D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DormandPrince54Integrator(double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance)
/*     */   {
/* 104 */     super(true, staticC, staticA, staticB, new DormandPrince54StepInterpolator(), minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
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
/*     */   public DormandPrince54Integrator(double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance)
/*     */   {
/* 120 */     super(true, staticC, staticA, staticB, new DormandPrince54StepInterpolator(), minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/* 128 */     return "Dormand-Prince 5(4)";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getOrder()
/*     */   {
/* 135 */     return 5;
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
/* 149 */     double error = 0.0D;
/*     */     
/* 151 */     for (int j = 0; j < y0.length; j++) {
/* 152 */       double errSum = 0.0012326388888888888D * yDotK[0][j] + -0.0042527702905061394D * yDotK[2][j] + 0.03697916666666667D * yDotK[3][j] + -0.05086379716981132D * yDotK[4][j] + 0.0419047619047619D * yDotK[5][j] + -0.025D * yDotK[6][j];
/*     */       
/*     */ 
/*     */ 
/* 156 */       double yScale = Math.max(Math.abs(y0[j]), Math.abs(y1[j]));
/* 157 */       double tol = this.vecAbsoluteTolerance == null ? this.scalAbsoluteTolerance + this.scalRelativeTolerance * yScale : this.vecAbsoluteTolerance[j] + this.vecRelativeTolerance[j] * yScale;
/*     */       
/*     */ 
/* 160 */       double ratio = h * errSum / tol;
/* 161 */       error += ratio * ratio;
/*     */     }
/*     */     
/*     */ 
/* 165 */     return Math.sqrt(error / y0.length);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/DormandPrince54Integrator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */