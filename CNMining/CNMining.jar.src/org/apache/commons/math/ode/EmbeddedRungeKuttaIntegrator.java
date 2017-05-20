/*     */ package org.apache.commons.math.ode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class EmbeddedRungeKuttaIntegrator
/*     */   extends AdaptiveStepsizeIntegrator
/*     */ {
/*     */   private boolean fsal;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double[] c;
/*     */   
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
/*     */ 
/*     */ 
/*     */   private double[] b;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private RungeKuttaStepInterpolator prototype;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double exp;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double safety;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double minReduction;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double maxGrowth;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected EmbeddedRungeKuttaIntegrator(boolean fsal, double[] c, double[][] a, double[] b, RungeKuttaStepInterpolator prototype, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance)
/*     */   {
/*  80 */     super(minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
/*     */     
/*  82 */     this.fsal = fsal;
/*  83 */     this.c = c;
/*  84 */     this.a = a;
/*  85 */     this.b = b;
/*  86 */     this.prototype = prototype;
/*     */     
/*  88 */     this.exp = (-1.0D / getOrder());
/*     */     
/*     */ 
/*  91 */     setSafety(0.9D);
/*  92 */     setMinReduction(0.2D);
/*  93 */     setMaxGrowth(10.0D);
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
/*     */   protected EmbeddedRungeKuttaIntegrator(boolean fsal, double[] c, double[][] a, double[] b, RungeKuttaStepInterpolator prototype, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance)
/*     */   {
/* 117 */     super(minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
/*     */     
/* 119 */     this.fsal = fsal;
/* 120 */     this.c = c;
/* 121 */     this.a = a;
/* 122 */     this.b = b;
/* 123 */     this.prototype = prototype;
/*     */     
/* 125 */     this.exp = (-1.0D / getOrder());
/*     */     
/*     */ 
/* 128 */     setSafety(0.9D);
/* 129 */     setMinReduction(0.2D);
/* 130 */     setMaxGrowth(10.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract String getName();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract int getOrder();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getSafety()
/*     */   {
/* 148 */     return this.safety;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setSafety(double safety)
/*     */   {
/* 155 */     this.safety = safety;
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
/*     */   public void integrate(FirstOrderDifferentialEquations equations, double t0, double[] y0, double t, double[] y)
/*     */     throws DerivativeException, IntegratorException
/*     */   {
/* 179 */     sanityChecks(equations, t0, y0, t, y);
/* 180 */     boolean forward = t > t0;
/*     */     
/*     */ 
/* 183 */     int stages = this.c.length + 1;
/* 184 */     if (y != y0) {
/* 185 */       System.arraycopy(y0, 0, y, 0, y0.length);
/*     */     }
/* 187 */     double[][] yDotK = new double[stages][];
/* 188 */     for (int i = 0; i < stages; i++) {
/* 189 */       yDotK[i] = new double[y0.length];
/*     */     }
/* 191 */     double[] yTmp = new double[y0.length];
/*     */     
/*     */     AbstractStepInterpolator interpolator;
/*     */     AbstractStepInterpolator interpolator;
/* 195 */     if ((this.handler.requiresDenseOutput()) || (!this.switchesHandler.isEmpty())) {
/* 196 */       RungeKuttaStepInterpolator rki = (RungeKuttaStepInterpolator)this.prototype.copy();
/* 197 */       rki.reinitialize(equations, yTmp, yDotK, forward);
/* 198 */       interpolator = rki;
/*     */     } else {
/* 200 */       interpolator = new DummyStepInterpolator(yTmp, forward);
/*     */     }
/* 202 */     interpolator.storeTime(t0);
/*     */     
/* 204 */     this.stepStart = t0;
/* 205 */     double hNew = 0.0D;
/* 206 */     boolean firstTime = true;
/*     */     
/* 208 */     this.handler.reset();
/*     */     boolean lastStep;
/*     */     do {
/* 211 */       interpolator.shift();
/*     */       
/* 213 */       double error = 0.0D;
/* 214 */       for (boolean loop = true; loop;)
/*     */       {
/* 216 */         if ((firstTime) || (!this.fsal))
/*     */         {
/* 218 */           equations.computeDerivatives(this.stepStart, y, yDotK[0]);
/*     */         }
/*     */         
/* 221 */         if (firstTime) { double[] scale;
/*     */           double[] scale;
/* 223 */           if (this.vecAbsoluteTolerance != null) {
/* 224 */             scale = this.vecAbsoluteTolerance;
/*     */           } else {
/* 226 */             scale = new double[y0.length];
/* 227 */             for (int i = 0; i < scale.length; i++) {
/* 228 */               scale[i] = this.scalAbsoluteTolerance;
/*     */             }
/*     */           }
/* 231 */           hNew = initializeStep(equations, forward, getOrder(), scale, this.stepStart, y, yDotK[0], yTmp, yDotK[1]);
/*     */           
/* 233 */           firstTime = false;
/*     */         }
/*     */         
/* 236 */         this.stepSize = hNew;
/*     */         
/*     */ 
/* 239 */         if (((forward) && (this.stepStart + this.stepSize > t)) || ((!forward) && (this.stepStart + this.stepSize < t)))
/*     */         {
/* 241 */           this.stepSize = (t - this.stepStart);
/*     */         }
/*     */         
/*     */ 
/* 245 */         for (int k = 1; k < stages; k++)
/*     */         {
/* 247 */           for (int j = 0; j < y0.length; j++) {
/* 248 */             double sum = this.a[(k - 1)][0] * yDotK[0][j];
/* 249 */             for (int l = 1; l < k; l++) {
/* 250 */               sum += this.a[(k - 1)][l] * yDotK[l][j];
/*     */             }
/* 252 */             y[j] += this.stepSize * sum;
/*     */           }
/*     */           
/* 255 */           equations.computeDerivatives(this.stepStart + this.c[(k - 1)] * this.stepSize, yTmp, yDotK[k]);
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 260 */         for (int j = 0; j < y0.length; j++) {
/* 261 */           double sum = this.b[0] * yDotK[0][j];
/* 262 */           for (int l = 1; l < stages; l++) {
/* 263 */             sum += this.b[l] * yDotK[l][j];
/*     */           }
/* 265 */           y[j] += this.stepSize * sum;
/*     */         }
/*     */         
/*     */ 
/* 269 */         error = estimateError(yDotK, y, yTmp, this.stepSize);
/* 270 */         if (error <= 1.0D)
/*     */         {
/*     */ 
/* 273 */           interpolator.storeTime(this.stepStart + this.stepSize);
/* 274 */           if (this.switchesHandler.evaluateStep(interpolator))
/*     */           {
/* 276 */             hNew = this.switchesHandler.getEventTime() - this.stepStart;
/*     */           }
/*     */           else {
/* 279 */             loop = false;
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 284 */           double factor = Math.min(this.maxGrowth, Math.max(this.minReduction, this.safety * Math.pow(error, this.exp)));
/*     */           
/*     */ 
/* 287 */           hNew = filterStep(this.stepSize * factor, false);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 293 */       double nextStep = this.stepStart + this.stepSize;
/* 294 */       System.arraycopy(yTmp, 0, y, 0, y0.length);
/* 295 */       this.switchesHandler.stepAccepted(nextStep, y);
/* 296 */       boolean lastStep; if (this.switchesHandler.stop()) {
/* 297 */         lastStep = true;
/*     */       } else {
/* 299 */         lastStep = nextStep >= t;
/*     */       }
/*     */       
/*     */ 
/* 303 */       interpolator.storeTime(nextStep);
/* 304 */       this.handler.handleStep(interpolator, lastStep);
/* 305 */       this.stepStart = nextStep;
/*     */       
/* 307 */       if (this.fsal)
/*     */       {
/* 309 */         System.arraycopy(yDotK[(stages - 1)], 0, yDotK[0], 0, y0.length);
/*     */       }
/*     */       
/* 312 */       if ((this.switchesHandler.reset(this.stepStart, y)) && (!lastStep))
/*     */       {
/*     */ 
/* 315 */         equations.computeDerivatives(this.stepStart, y, yDotK[0]);
/*     */       }
/*     */       
/* 318 */       if (!lastStep)
/*     */       {
/* 320 */         double factor = Math.min(this.maxGrowth, Math.max(this.minReduction, this.safety * Math.pow(error, this.exp)));
/*     */         
/*     */ 
/* 323 */         double scaledH = this.stepSize * factor;
/* 324 */         double nextT = this.stepStart + scaledH;
/* 325 */         boolean nextIsLast = nextT >= t;
/* 326 */         hNew = filterStep(scaledH, nextIsLast);
/*     */       }
/*     */       
/* 329 */     } while (!lastStep);
/*     */     
/* 331 */     resetInternalState();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMinReduction()
/*     */   {
/* 339 */     return this.minReduction;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setMinReduction(double minReduction)
/*     */   {
/* 346 */     this.minReduction = minReduction;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getMaxGrowth()
/*     */   {
/* 353 */     return this.maxGrowth;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setMaxGrowth(double maxGrowth)
/*     */   {
/* 360 */     this.maxGrowth = maxGrowth;
/*     */   }
/*     */   
/*     */   protected abstract double estimateError(double[][] paramArrayOfDouble, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double paramDouble);
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/EmbeddedRungeKuttaIntegrator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */