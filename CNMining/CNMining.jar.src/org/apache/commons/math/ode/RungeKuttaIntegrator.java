/*     */ package org.apache.commons.math.ode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class RungeKuttaIntegrator
/*     */   implements FirstOrderIntegrator
/*     */ {
/*     */   private double[] c;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double[][] a;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private double[] b;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private RungeKuttaStepInterpolator prototype;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private double step;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private StepHandler handler;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SwitchingFunctionsHandler switchesHandler;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private double stepStart;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private double stepSize;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected RungeKuttaIntegrator(double[] c, double[][] a, double[] b, RungeKuttaStepInterpolator prototype, double step)
/*     */   {
/*  60 */     this.c = c;
/*  61 */     this.a = a;
/*  62 */     this.b = b;
/*  63 */     this.prototype = prototype;
/*  64 */     this.step = step;
/*  65 */     this.handler = DummyStepHandler.getInstance();
/*  66 */     this.switchesHandler = new SwitchingFunctionsHandler();
/*  67 */     resetInternalState();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract String getName();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStepHandler(StepHandler handler)
/*     */   {
/*  81 */     this.handler = handler;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public StepHandler getStepHandler()
/*     */   {
/*  88 */     return this.handler;
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
/* 104 */     this.switchesHandler.add(function, maxCheckInterval, convergence, maxIterationCount);
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
/*     */   private void sanityChecks(FirstOrderDifferentialEquations equations, double t0, double[] y0, double t, double[] y)
/*     */     throws IntegratorException
/*     */   {
/* 118 */     if (equations.getDimension() != y0.length) {
/* 119 */       throw new IntegratorException("dimensions mismatch: ODE problem has dimension {0}, initial state vector has dimension {1}", new Object[] { new Integer(equations.getDimension()), new Integer(y0.length) });
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 126 */     if (equations.getDimension() != y.length) {
/* 127 */       throw new IntegratorException("dimensions mismatch: ODE problem has dimension {0}, final state vector has dimension {1}", new Object[] { new Integer(equations.getDimension()), new Integer(y.length) });
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 134 */     if (Math.abs(t - t0) <= 1.0E-12D * Math.max(Math.abs(t0), Math.abs(t))) {
/* 135 */       throw new IntegratorException("too small integration interval: length = {0}", new Object[] { new Double(Math.abs(t - t0)) });
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
/*     */   public void integrate(FirstOrderDifferentialEquations equations, double t0, double[] y0, double t, double[] y)
/*     */     throws DerivativeException, IntegratorException
/*     */   {
/* 161 */     sanityChecks(equations, t0, y0, t, y);
/* 162 */     boolean forward = t > t0;
/*     */     
/*     */ 
/* 165 */     int stages = this.c.length + 1;
/* 166 */     if (y != y0) {
/* 167 */       System.arraycopy(y0, 0, y, 0, y0.length);
/*     */     }
/* 169 */     double[][] yDotK = new double[stages][];
/* 170 */     for (int i = 0; i < stages; i++) {
/* 171 */       yDotK[i] = new double[y0.length];
/*     */     }
/* 173 */     double[] yTmp = new double[y0.length];
/*     */     
/*     */     AbstractStepInterpolator interpolator;
/*     */     AbstractStepInterpolator interpolator;
/* 177 */     if ((this.handler.requiresDenseOutput()) || (!this.switchesHandler.isEmpty())) {
/* 178 */       RungeKuttaStepInterpolator rki = (RungeKuttaStepInterpolator)this.prototype.copy();
/* 179 */       rki.reinitialize(equations, yTmp, yDotK, forward);
/* 180 */       interpolator = rki;
/*     */     } else {
/* 182 */       interpolator = new DummyStepInterpolator(yTmp, forward);
/*     */     }
/* 184 */     interpolator.storeTime(t0);
/*     */     
/*     */ 
/* 187 */     long nbStep = Math.max(1L, Math.abs(Math.round((t - t0) / this.step)));
/* 188 */     boolean lastStep = false;
/* 189 */     this.stepStart = t0;
/* 190 */     this.stepSize = ((t - t0) / nbStep);
/* 191 */     this.handler.reset();
/* 192 */     for (long i = 0L; !lastStep; i += 1L)
/*     */     {
/* 194 */       interpolator.shift();
/*     */       
/* 196 */       boolean needUpdate = false;
/* 197 */       for (boolean loop = true; loop;)
/*     */       {
/*     */ 
/* 200 */         equations.computeDerivatives(this.stepStart, y, yDotK[0]);
/*     */         
/*     */ 
/* 203 */         for (int k = 1; k < stages; k++)
/*     */         {
/* 205 */           for (int j = 0; j < y0.length; j++) {
/* 206 */             double sum = this.a[(k - 1)][0] * yDotK[0][j];
/* 207 */             for (int l = 1; l < k; l++) {
/* 208 */               sum += this.a[(k - 1)][l] * yDotK[l][j];
/*     */             }
/* 210 */             y[j] += this.stepSize * sum;
/*     */           }
/*     */           
/* 213 */           equations.computeDerivatives(this.stepStart + this.c[(k - 1)] * this.stepSize, yTmp, yDotK[k]);
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 218 */         for (int j = 0; j < y0.length; j++) {
/* 219 */           double sum = this.b[0] * yDotK[0][j];
/* 220 */           for (int l = 1; l < stages; l++) {
/* 221 */             sum += this.b[l] * yDotK[l][j];
/*     */           }
/* 223 */           y[j] += this.stepSize * sum;
/*     */         }
/*     */         
/*     */ 
/* 227 */         interpolator.storeTime(this.stepStart + this.stepSize);
/* 228 */         if (this.switchesHandler.evaluateStep(interpolator)) {
/* 229 */           needUpdate = true;
/* 230 */           this.stepSize = (this.switchesHandler.getEventTime() - this.stepStart);
/*     */         } else {
/* 232 */           loop = false;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 238 */       double nextStep = this.stepStart + this.stepSize;
/* 239 */       System.arraycopy(yTmp, 0, y, 0, y0.length);
/* 240 */       this.switchesHandler.stepAccepted(nextStep, y);
/* 241 */       if (this.switchesHandler.stop()) {
/* 242 */         lastStep = true;
/*     */       } else {
/* 244 */         lastStep = i == nbStep - 1L;
/*     */       }
/*     */       
/*     */ 
/* 248 */       interpolator.storeTime(nextStep);
/* 249 */       this.handler.handleStep(interpolator, lastStep);
/* 250 */       this.stepStart = nextStep;
/*     */       
/* 252 */       if ((this.switchesHandler.reset(this.stepStart, y)) && (!lastStep))
/*     */       {
/*     */ 
/* 255 */         equations.computeDerivatives(this.stepStart, y, yDotK[0]);
/*     */       }
/*     */       
/* 258 */       if (needUpdate)
/*     */       {
/*     */ 
/* 261 */         nbStep = Math.max(1L, Math.abs(Math.round((t - this.stepStart) / this.step)));
/* 262 */         this.stepSize = ((t - this.stepStart) / nbStep);
/* 263 */         i = -1L;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 268 */     resetInternalState();
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
/*     */   public double getCurrentStepStart()
/*     */   {
/* 282 */     return this.stepStart;
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
/* 295 */     return this.stepSize;
/*     */   }
/*     */   
/*     */   private void resetInternalState()
/*     */   {
/* 300 */     this.stepStart = NaN.0D;
/* 301 */     this.stepSize = NaN.0D;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/RungeKuttaIntegrator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */