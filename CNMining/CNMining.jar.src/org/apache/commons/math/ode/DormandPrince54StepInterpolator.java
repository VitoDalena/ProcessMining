/*     */ package org.apache.commons.math.ode;
/*     */ 
/*     */ 
/*     */ class DormandPrince54StepInterpolator
/*     */   extends RungeKuttaStepInterpolator
/*     */ {
/*     */   private double[] v1;
/*     */   
/*     */   private double[] v2;
/*     */   
/*     */   private double[] v3;
/*     */   
/*     */   private double[] v4;
/*     */   
/*     */   private boolean vectorsInitialized;
/*     */   
/*     */   private static final double a70 = 0.09114583333333333D;
/*     */   
/*     */   private static final double a72 = 0.44923629829290207D;
/*     */   
/*     */   private static final double a73 = 0.6510416666666666D;
/*     */   
/*     */   private static final double a74 = -0.322376179245283D;
/*     */   
/*     */   private static final double a75 = 0.13095238095238096D;
/*     */   
/*     */   private static final double d0 = -1.1270175653862835D;
/*     */   
/*     */   private static final double d2 = 2.675424484351598D;
/*     */   
/*     */   private static final double d3 = -5.685526961588504D;
/*     */   
/*     */   private static final double d4 = 3.5219323679207912D;
/*     */   
/*     */   private static final double d5 = -1.7672812570757455D;
/*     */   
/*     */   private static final double d6 = 2.382468931778144D;
/*     */   
/*     */   private static final long serialVersionUID = 4104157279605906956L;
/*     */   
/*     */ 
/*     */   public DormandPrince54StepInterpolator()
/*     */   {
/*  44 */     this.v1 = null;
/*  45 */     this.v2 = null;
/*  46 */     this.v3 = null;
/*  47 */     this.v4 = null;
/*  48 */     this.vectorsInitialized = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DormandPrince54StepInterpolator(DormandPrince54StepInterpolator interpolator)
/*     */   {
/*  58 */     super(interpolator);
/*     */     
/*  60 */     if (interpolator.v1 == null)
/*     */     {
/*  62 */       this.v1 = null;
/*  63 */       this.v2 = null;
/*  64 */       this.v3 = null;
/*  65 */       this.v4 = null;
/*  66 */       this.vectorsInitialized = false;
/*     */     }
/*     */     else
/*     */     {
/*  70 */       this.v1 = ((double[])interpolator.v1.clone());
/*  71 */       this.v2 = ((double[])interpolator.v2.clone());
/*  72 */       this.v3 = ((double[])interpolator.v3.clone());
/*  73 */       this.v4 = ((double[])interpolator.v4.clone());
/*  74 */       this.vectorsInitialized = interpolator.vectorsInitialized;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected StepInterpolator doCopy()
/*     */   {
/*  84 */     return new DormandPrince54StepInterpolator(this);
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
/*     */   public void reinitialize(FirstOrderDifferentialEquations equations, double[] y, double[][] yDotK, boolean forward)
/*     */   {
/*  98 */     super.reinitialize(equations, y, yDotK, forward);
/*  99 */     this.v1 = null;
/* 100 */     this.v2 = null;
/* 101 */     this.v3 = null;
/* 102 */     this.v4 = null;
/* 103 */     this.vectorsInitialized = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void storeTime(double t)
/*     */   {
/* 110 */     super.storeTime(t);
/* 111 */     this.vectorsInitialized = false;
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
/* 126 */     if (!this.vectorsInitialized)
/*     */     {
/* 128 */       if (this.v1 == null) {
/* 129 */         this.v1 = new double[this.interpolatedState.length];
/* 130 */         this.v2 = new double[this.interpolatedState.length];
/* 131 */         this.v3 = new double[this.interpolatedState.length];
/* 132 */         this.v4 = new double[this.interpolatedState.length];
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 138 */       for (int i = 0; i < this.interpolatedState.length; i++) {
/* 139 */         this.v1[i] = (this.h * (0.09114583333333333D * this.yDotK[0][i] + 0.44923629829290207D * this.yDotK[2][i] + 0.6510416666666666D * this.yDotK[3][i] + -0.322376179245283D * this.yDotK[4][i] + 0.13095238095238096D * this.yDotK[5][i]));
/*     */         
/* 141 */         this.v2[i] = (this.h * this.yDotK[0][i] - this.v1[i]);
/* 142 */         this.v3[i] = (this.v1[i] - this.v2[i] - this.h * this.yDotK[6][i]);
/* 143 */         this.v4[i] = (this.h * (-1.1270175653862835D * this.yDotK[0][i] + 2.675424484351598D * this.yDotK[2][i] + -5.685526961588504D * this.yDotK[3][i] + 3.5219323679207912D * this.yDotK[4][i] + -1.7672812570757455D * this.yDotK[5][i] + 2.382468931778144D * this.yDotK[6][i]));
/*     */       }
/*     */       
/*     */ 
/* 147 */       this.vectorsInitialized = true;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 152 */     double eta = oneMinusThetaH / this.h;
/* 153 */     for (int i = 0; i < this.interpolatedState.length; i++) {
/* 154 */       this.interpolatedState[i] = (this.currentState[i] - eta * (this.v1[i] - theta * (this.v2[i] + theta * (this.v3[i] + eta * this.v4[i]))));
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/DormandPrince54StepInterpolator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */