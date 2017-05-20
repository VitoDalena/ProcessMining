/*     */ package org.apache.commons.math.ode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AdaptiveStepsizeIntegrator
/*     */   implements FirstOrderIntegrator
/*     */ {
/*     */   private double minStep;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private double maxStep;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private double initialStep;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double scalAbsoluteTolerance;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double scalRelativeTolerance;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double[] vecAbsoluteTolerance;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double[] vecRelativeTolerance;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected StepHandler handler;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SwitchingFunctionsHandler switchesHandler;
/*     */   
/*     */ 
/*     */ 
/*     */   protected double stepStart;
/*     */   
/*     */ 
/*     */ 
/*     */   protected double stepSize;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public AdaptiveStepsizeIntegrator(double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance)
/*     */   {
/*  65 */     this.minStep = minStep;
/*  66 */     this.maxStep = maxStep;
/*  67 */     this.initialStep = -1.0D;
/*     */     
/*  69 */     this.scalAbsoluteTolerance = scalAbsoluteTolerance;
/*  70 */     this.scalRelativeTolerance = scalRelativeTolerance;
/*  71 */     this.vecAbsoluteTolerance = null;
/*  72 */     this.vecRelativeTolerance = null;
/*     */     
/*     */ 
/*  75 */     this.handler = DummyStepHandler.getInstance();
/*     */     
/*  77 */     this.switchesHandler = new SwitchingFunctionsHandler();
/*     */     
/*  79 */     resetInternalState();
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
/*     */   public AdaptiveStepsizeIntegrator(double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance)
/*     */   {
/*  96 */     this.minStep = minStep;
/*  97 */     this.maxStep = maxStep;
/*  98 */     this.initialStep = -1.0D;
/*     */     
/* 100 */     this.scalAbsoluteTolerance = 0.0D;
/* 101 */     this.scalRelativeTolerance = 0.0D;
/* 102 */     this.vecAbsoluteTolerance = vecAbsoluteTolerance;
/* 103 */     this.vecRelativeTolerance = vecRelativeTolerance;
/*     */     
/*     */ 
/* 106 */     this.handler = DummyStepHandler.getInstance();
/*     */     
/* 108 */     this.switchesHandler = new SwitchingFunctionsHandler();
/*     */     
/* 110 */     resetInternalState();
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
/*     */   public void setInitialStepSize(double initialStepSize)
/*     */   {
/* 126 */     if ((initialStepSize < this.minStep) || (initialStepSize > this.maxStep)) {
/* 127 */       this.initialStep = -1.0D;
/*     */     } else {
/* 129 */       this.initialStep = initialStepSize;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStepHandler(StepHandler handler)
/*     */   {
/* 139 */     this.handler = handler;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public StepHandler getStepHandler()
/*     */   {
/* 146 */     return this.handler;
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
/*     */   public void addSwitchingFunction(SwitchingFunction function, double maxCheckInterval, double convergence, int maxIterationCount)
/*     */   {
/* 162 */     this.switchesHandler.add(function, maxCheckInterval, convergence, maxIterationCount);
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
/*     */   protected void sanityChecks(FirstOrderDifferentialEquations equations, double t0, double[] y0, double t, double[] y)
/*     */     throws IntegratorException
/*     */   {
/* 176 */     if (equations.getDimension() != y0.length) {
/* 177 */       throw new IntegratorException("dimensions mismatch: ODE problem has dimension {0}, initial state vector has dimension {1}", new Object[] { new Integer(equations.getDimension()), new Integer(y0.length) });
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 184 */     if (equations.getDimension() != y.length) {
/* 185 */       throw new IntegratorException("dimensions mismatch: ODE problem has dimension {0}, final state vector has dimension {1}", new Object[] { new Integer(equations.getDimension()), new Integer(y.length) });
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 192 */     if ((this.vecAbsoluteTolerance != null) && (this.vecAbsoluteTolerance.length != y0.length)) {
/* 193 */       throw new IntegratorException("dimensions mismatch: state vector has dimension {0}, absolute tolerance vector has dimension {1}", new Object[] { new Integer(y0.length), new Integer(this.vecAbsoluteTolerance.length) });
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 200 */     if ((this.vecRelativeTolerance != null) && (this.vecRelativeTolerance.length != y0.length)) {
/* 201 */       throw new IntegratorException("dimensions mismatch: state vector has dimension {0}, relative tolerance vector has dimension {1}", new Object[] { new Integer(y0.length), new Integer(this.vecRelativeTolerance.length) });
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 208 */     if (Math.abs(t - t0) <= 1.0E-12D * Math.max(Math.abs(t0), Math.abs(t))) {
/* 209 */       throw new IntegratorException("too small integration interval: length = {0}", new Object[] { new Double(Math.abs(t - t0)) });
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double initializeStep(FirstOrderDifferentialEquations equations, boolean forward, int order, double[] scale, double t0, double[] y0, double[] yDot0, double[] y1, double[] yDot1)
/*     */     throws DerivativeException
/*     */   {
/* 235 */     if (this.initialStep > 0.0D)
/*     */     {
/* 237 */       return forward ? this.initialStep : -this.initialStep;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 243 */     double yOnScale2 = 0.0D;
/* 244 */     double yDotOnScale2 = 0.0D;
/* 245 */     for (int j = 0; j < y0.length; j++) {
/* 246 */       double ratio = y0[j] / scale[j];
/* 247 */       yOnScale2 += ratio * ratio;
/* 248 */       ratio = yDot0[j] / scale[j];
/* 249 */       yDotOnScale2 += ratio * ratio;
/*     */     }
/*     */     
/* 252 */     double h = (yOnScale2 < 1.0E-10D) || (yDotOnScale2 < 1.0E-10D) ? 1.0E-6D : 0.01D * Math.sqrt(yOnScale2 / yDotOnScale2);
/*     */     
/* 254 */     if (!forward) {
/* 255 */       h = -h;
/*     */     }
/*     */     
/*     */ 
/* 259 */     for (int j = 0; j < y0.length; j++) {
/* 260 */       y0[j] += h * yDot0[j];
/*     */     }
/* 262 */     equations.computeDerivatives(t0 + h, y1, yDot1);
/*     */     
/*     */ 
/* 265 */     double yDDotOnScale = 0.0D;
/* 266 */     for (int j = 0; j < y0.length; j++) {
/* 267 */       double ratio = (yDot1[j] - yDot0[j]) / scale[j];
/* 268 */       yDDotOnScale += ratio * ratio;
/*     */     }
/* 270 */     yDDotOnScale = Math.sqrt(yDDotOnScale) / h;
/*     */     
/*     */ 
/*     */ 
/* 274 */     double maxInv2 = Math.max(Math.sqrt(yDotOnScale2), yDDotOnScale);
/* 275 */     double h1 = maxInv2 < 1.0E-15D ? Math.max(1.0E-6D, 0.001D * Math.abs(h)) : Math.pow(0.01D / maxInv2, 1.0D / order);
/*     */     
/*     */ 
/* 278 */     h = Math.min(100.0D * Math.abs(h), h1);
/* 279 */     h = Math.max(h, 1.0E-12D * Math.abs(t0));
/* 280 */     if (h < getMinStep()) {
/* 281 */       h = getMinStep();
/*     */     }
/* 283 */     if (h > getMaxStep()) {
/* 284 */       h = getMaxStep();
/*     */     }
/* 286 */     if (!forward) {
/* 287 */       h = -h;
/*     */     }
/*     */     
/* 290 */     return h;
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
/*     */   protected double filterStep(double h, boolean acceptSmall)
/*     */     throws IntegratorException
/*     */   {
/* 305 */     if (Math.abs(h) < this.minStep) {
/* 306 */       if (acceptSmall) {
/* 307 */         h = h < 0.0D ? -this.minStep : this.minStep;
/*     */       } else {
/* 309 */         throw new IntegratorException("minimal step size ({0}) reached, integration needs {1}", new Object[] { new Double(this.minStep), new Double(Math.abs(h)) });
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 318 */     if (h > this.maxStep) {
/* 319 */       h = this.maxStep;
/* 320 */     } else if (h < -this.maxStep) {
/* 321 */       h = -this.maxStep;
/*     */     }
/*     */     
/* 324 */     return h;
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
/*     */   public abstract void integrate(FirstOrderDifferentialEquations paramFirstOrderDifferentialEquations, double paramDouble1, double[] paramArrayOfDouble1, double paramDouble2, double[] paramArrayOfDouble2)
/*     */     throws DerivativeException, IntegratorException;
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
/*     */   public double getCurrentStepStart()
/*     */   {
/* 359 */     return this.stepStart;
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
/*     */   public double getCurrentSignedStepsize()
/*     */   {
/* 372 */     return this.stepSize;
/*     */   }
/*     */   
/*     */   protected void resetInternalState()
/*     */   {
/* 377 */     this.stepStart = NaN.0D;
/* 378 */     this.stepSize = Math.sqrt(this.minStep * this.maxStep);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getMinStep()
/*     */   {
/* 385 */     return this.minStep;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getMaxStep()
/*     */   {
/* 392 */     return this.maxStep;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/AdaptiveStepsizeIntegrator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */