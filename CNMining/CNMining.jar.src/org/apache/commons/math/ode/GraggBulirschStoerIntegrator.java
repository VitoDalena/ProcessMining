/*     */ package org.apache.commons.math.ode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GraggBulirschStoerIntegrator
/*     */   extends AdaptiveStepsizeIntegrator
/*     */ {
/*     */   private static final String methodName = "Gragg-Bulirsch-Stoer";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private int maxOrder;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private int[] sequence;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private int[] costPerStep;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private double[] costPerTimeUnit;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private double[] optimalStep;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private double[][] coeff;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean performTest;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private int maxChecks;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private int maxIter;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private double stabilityReduction;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private double stepControl1;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private double stepControl2;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private double stepControl3;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private double stepControl4;
/*     */   
/*     */ 
/*     */ 
/*     */   private double orderControl1;
/*     */   
/*     */ 
/*     */ 
/*     */   private double orderControl2;
/*     */   
/*     */ 
/*     */ 
/*     */   private boolean denseOutput;
/*     */   
/*     */ 
/*     */ 
/*     */   private boolean useInterpolationError;
/*     */   
/*     */ 
/*     */ 
/*     */   private int mudif;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public GraggBulirschStoerIntegrator(double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance)
/*     */   {
/* 107 */     super(minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
/* 108 */     this.denseOutput = ((this.handler.requiresDenseOutput()) || (!this.switchesHandler.isEmpty()));
/* 109 */     setStabilityCheck(true, -1, -1, -1.0D);
/* 110 */     setStepsizeControl(-1.0D, -1.0D, -1.0D, -1.0D);
/* 111 */     setOrderControl(-1, -1.0D, -1.0D);
/* 112 */     setInterpolationControl(true, -1);
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
/*     */   public GraggBulirschStoerIntegrator(double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance)
/*     */   {
/* 129 */     super(minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
/* 130 */     this.denseOutput = ((this.handler.requiresDenseOutput()) || (!this.switchesHandler.isEmpty()));
/* 131 */     setStabilityCheck(true, -1, -1, -1.0D);
/* 132 */     setStepsizeControl(-1.0D, -1.0D, -1.0D, -1.0D);
/* 133 */     setOrderControl(-1, -1.0D, -1.0D);
/* 134 */     setInterpolationControl(true, -1);
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
/*     */   public void setStabilityCheck(boolean performTest, int maxIter, int maxChecks, double stabilityReduction)
/*     */   {
/* 159 */     this.performTest = performTest;
/* 160 */     this.maxIter = (maxIter <= 0 ? 2 : maxIter);
/* 161 */     this.maxChecks = (maxChecks <= 0 ? 1 : maxChecks);
/*     */     
/* 163 */     if ((stabilityReduction < 1.0E-4D) || (stabilityReduction > 0.9999D)) {
/* 164 */       this.stabilityReduction = 0.5D;
/*     */     } else {
/* 166 */       this.stabilityReduction = stabilityReduction;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStepsizeControl(double stepControl1, double stepControl2, double stepControl3, double stepControl4)
/*     */   {
/* 198 */     if ((stepControl1 < 1.0E-4D) || (stepControl1 > 0.9999D)) {
/* 199 */       this.stepControl1 = 0.65D;
/*     */     } else {
/* 201 */       this.stepControl1 = stepControl1;
/*     */     }
/*     */     
/* 204 */     if ((stepControl2 < 1.0E-4D) || (stepControl2 > 0.9999D)) {
/* 205 */       this.stepControl2 = 0.94D;
/*     */     } else {
/* 207 */       this.stepControl2 = stepControl2;
/*     */     }
/*     */     
/* 210 */     if ((stepControl3 < 1.0E-4D) || (stepControl3 > 0.9999D)) {
/* 211 */       this.stepControl3 = 0.02D;
/*     */     } else {
/* 213 */       this.stepControl3 = stepControl3;
/*     */     }
/*     */     
/* 216 */     if ((stepControl4 < 1.0001D) || (stepControl4 > 999.9D)) {
/* 217 */       this.stepControl4 = 4.0D;
/*     */     } else {
/* 219 */       this.stepControl4 = stepControl4;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOrderControl(int maxOrder, double orderControl1, double orderControl2)
/*     */   {
/* 250 */     if ((maxOrder <= 6) || (maxOrder % 2 != 0)) {
/* 251 */       this.maxOrder = 18;
/*     */     }
/*     */     
/* 254 */     if ((orderControl1 < 1.0E-4D) || (orderControl1 > 0.9999D)) {
/* 255 */       this.orderControl1 = 0.8D;
/*     */     } else {
/* 257 */       this.orderControl1 = orderControl1;
/*     */     }
/*     */     
/* 260 */     if ((orderControl2 < 1.0E-4D) || (orderControl2 > 0.9999D)) {
/* 261 */       this.orderControl2 = 0.9D;
/*     */     } else {
/* 263 */       this.orderControl2 = orderControl2;
/*     */     }
/*     */     
/*     */ 
/* 267 */     initializeArrays();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStepHandler(StepHandler handler)
/*     */   {
/* 278 */     super.setStepHandler(handler);
/* 279 */     this.denseOutput = ((handler.requiresDenseOutput()) || (!this.switchesHandler.isEmpty()));
/*     */     
/*     */ 
/* 282 */     initializeArrays();
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
/*     */   public void addSwitchingFunction(SwitchingFunction function, double maxCheckInterval, double convergence, int maxIterationCount)
/*     */   {
/* 299 */     super.addSwitchingFunction(function, maxCheckInterval, convergence, maxIterationCount);
/* 300 */     this.denseOutput = ((this.handler.requiresDenseOutput()) || (!this.switchesHandler.isEmpty()));
/*     */     
/*     */ 
/* 303 */     initializeArrays();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void initializeArrays()
/*     */   {
/* 310 */     int size = this.maxOrder / 2;
/*     */     
/* 312 */     if ((this.sequence == null) || (this.sequence.length != size))
/*     */     {
/* 314 */       this.sequence = new int[size];
/* 315 */       this.costPerStep = new int[size];
/* 316 */       this.coeff = new double[size][];
/* 317 */       this.costPerTimeUnit = new double[size];
/* 318 */       this.optimalStep = new double[size];
/*     */     }
/*     */     
/* 321 */     if (this.denseOutput)
/*     */     {
/* 323 */       for (int k = 0; k < size; k++) {
/* 324 */         this.sequence[k] = (4 * k + 2);
/*     */       }
/*     */       
/*     */     } else {
/* 328 */       for (int k = 0; k < size; k++) {
/* 329 */         this.sequence[k] = (2 * (k + 1));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 335 */     this.costPerStep[0] = (this.sequence[0] + 1);
/* 336 */     for (int k = 1; k < size; k++) {
/* 337 */       this.costPerStep[k] = (this.costPerStep[(k - 1)] + this.sequence[k]);
/*     */     }
/*     */     
/*     */ 
/* 341 */     for (int k = 0; k < size; k++) {
/* 342 */       this.coeff[k] = (k > 0 ? new double[k] : null);
/* 343 */       for (int l = 0; l < k; l++) {
/* 344 */         double ratio = this.sequence[k] / this.sequence[(k - l - 1)];
/* 345 */         this.coeff[k][l] = (1.0D / (ratio * ratio - 1.0D));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setInterpolationControl(boolean useInterpolationError, int mudif)
/*     */   {
/* 364 */     this.useInterpolationError = useInterpolationError;
/*     */     
/* 366 */     if ((mudif <= 0) || (mudif >= 7)) {
/* 367 */       this.mudif = 4;
/*     */     } else {
/* 369 */       this.mudif = mudif;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/* 378 */     return "Gragg-Bulirsch-Stoer";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void rescale(double[] y1, double[] y2, double[] scale)
/*     */   {
/* 387 */     if (this.vecAbsoluteTolerance == null) {
/* 388 */       for (int i = 0; i < scale.length; i++) {
/* 389 */         double yi = Math.max(Math.abs(y1[i]), Math.abs(y2[i]));
/* 390 */         scale[i] = (this.scalAbsoluteTolerance + this.scalRelativeTolerance * yi);
/*     */       }
/*     */     } else {
/* 393 */       for (int i = 0; i < scale.length; i++) {
/* 394 */         double yi = Math.max(Math.abs(y1[i]), Math.abs(y2[i]));
/* 395 */         scale[i] = (this.vecAbsoluteTolerance[i] + this.vecRelativeTolerance[i] * yi);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean tryStep(FirstOrderDifferentialEquations equations, double t0, double[] y0, double step, int k, double[] scale, double[][] f, double[] yMiddle, double[] yEnd, double[] yTmp)
/*     */     throws DerivativeException
/*     */   {
/* 425 */     int n = this.sequence[k];
/* 426 */     double subStep = step / n;
/* 427 */     double subStep2 = 2.0D * subStep;
/*     */     
/*     */ 
/* 430 */     double t = t0 + subStep;
/* 431 */     for (int i = 0; i < y0.length; i++) {
/* 432 */       yTmp[i] = y0[i];
/* 433 */       y0[i] += subStep * f[0][i];
/*     */     }
/* 435 */     equations.computeDerivatives(t, yEnd, f[1]);
/*     */     
/*     */ 
/* 438 */     for (int j = 1; j < n; j++)
/*     */     {
/* 440 */       if (2 * j == n)
/*     */       {
/* 442 */         System.arraycopy(yEnd, 0, yMiddle, 0, y0.length);
/*     */       }
/*     */       
/* 445 */       t += subStep;
/* 446 */       for (int i = 0; i < y0.length; i++) {
/* 447 */         double middle = yEnd[i];
/* 448 */         yTmp[i] += subStep2 * f[j][i];
/* 449 */         yTmp[i] = middle;
/*     */       }
/*     */       
/* 452 */       equations.computeDerivatives(t, yEnd, f[(j + 1)]);
/*     */       
/*     */ 
/* 455 */       if ((this.performTest) && (j <= this.maxChecks) && (k < this.maxIter)) {
/* 456 */         double initialNorm = 0.0D;
/* 457 */         for (int l = 0; l < y0.length; l++) {
/* 458 */           double ratio = f[0][l] / scale[l];
/* 459 */           initialNorm += ratio * ratio;
/*     */         }
/* 461 */         double deltaNorm = 0.0D;
/* 462 */         for (int l = 0; l < y0.length; l++) {
/* 463 */           double ratio = (f[(j + 1)][l] - f[0][l]) / scale[l];
/* 464 */           deltaNorm += ratio * ratio;
/*     */         }
/* 466 */         if (deltaNorm > 4.0D * Math.max(1.0E-15D, initialNorm)) {
/* 467 */           return false;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 474 */     for (int i = 0; i < y0.length; i++) {
/* 475 */       yEnd[i] = (0.5D * (yTmp[i] + yEnd[i] + subStep * f[n][i]));
/*     */     }
/*     */     
/* 478 */     return true;
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
/*     */   private void extrapolate(int offset, int k, double[][] diag, double[] last)
/*     */   {
/* 492 */     for (int j = 1; j < k; j++) {
/* 493 */       for (int i = 0; i < last.length; i++)
/*     */       {
/* 495 */         diag[(k - j - 1)][i] = (diag[(k - j)][i] + this.coeff[(k + offset)][(j - 1)] * (diag[(k - j)][i] - diag[(k - j - 1)][i]));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 501 */     for (int i = 0; i < last.length; i++)
/*     */     {
/* 503 */       last[i] = (diag[0][i] + this.coeff[(k + offset)][(k - 1)] * (diag[0][i] - last[i]));
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
/*     */   public void integrate(FirstOrderDifferentialEquations equations, double t0, double[] y0, double t, double[] y)
/*     */     throws DerivativeException, IntegratorException
/*     */   {
/* 527 */     sanityChecks(equations, t0, y0, t, y);
/* 528 */     boolean forward = t > t0;
/*     */     
/*     */ 
/* 531 */     double[] yDot0 = new double[y0.length];
/* 532 */     double[] y1 = new double[y0.length];
/* 533 */     double[] yTmp = new double[y0.length];
/* 534 */     double[] yTmpDot = new double[y0.length];
/*     */     
/* 536 */     double[][] diagonal = new double[this.sequence.length - 1][];
/* 537 */     double[][] y1Diag = new double[this.sequence.length - 1][];
/* 538 */     for (int k = 0; k < this.sequence.length - 1; k++) {
/* 539 */       diagonal[k] = new double[y0.length];
/* 540 */       y1Diag[k] = new double[y0.length];
/*     */     }
/*     */     
/* 543 */     double[][][] fk = new double[this.sequence.length][][];
/* 544 */     for (int k = 0; k < this.sequence.length; k++)
/*     */     {
/* 546 */       fk[k] = new double[this.sequence[k] + 1][];
/*     */       
/*     */ 
/* 549 */       fk[k][0] = yDot0;
/*     */       
/* 551 */       for (int l = 0; l < this.sequence[k]; l++) {
/* 552 */         fk[k][(l + 1)] = new double[y0.length];
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 557 */     if (y != y0) {
/* 558 */       System.arraycopy(y0, 0, y, 0, y0.length);
/*     */     }
/*     */     
/* 561 */     double[] yDot1 = null;
/* 562 */     double[][] yMidDots = (double[][])null;
/* 563 */     if (this.denseOutput) {
/* 564 */       yDot1 = new double[y0.length];
/* 565 */       yMidDots = new double[1 + 2 * this.sequence.length][];
/* 566 */       for (int j = 0; j < yMidDots.length; j++) {
/* 567 */         yMidDots[j] = new double[y0.length];
/*     */       }
/*     */     } else {
/* 570 */       yMidDots = new double[1][];
/* 571 */       yMidDots[0] = new double[y0.length];
/*     */     }
/*     */     
/*     */ 
/* 575 */     double[] scale = new double[y0.length];
/* 576 */     rescale(y, y, scale);
/*     */     
/*     */ 
/* 579 */     double tol = this.vecRelativeTolerance == null ? this.scalRelativeTolerance : this.vecRelativeTolerance[0];
/*     */     
/* 581 */     double log10R = Math.log(Math.max(1.0E-10D, tol)) / Math.log(10.0D);
/* 582 */     int targetIter = Math.max(1, Math.min(this.sequence.length - 2, (int)Math.floor(0.5D - 0.6D * log10R)));
/*     */     
/*     */ 
/*     */ 
/* 586 */     AbstractStepInterpolator interpolator = null;
/* 587 */     if ((this.denseOutput) || (!this.switchesHandler.isEmpty())) {
/* 588 */       interpolator = new GraggBulirschStoerStepInterpolator(y, yDot0, y1, yDot1, yMidDots, forward);
/*     */     }
/*     */     else
/*     */     {
/* 592 */       interpolator = new DummyStepInterpolator(y, forward);
/*     */     }
/* 594 */     interpolator.storeTime(t0);
/*     */     
/* 596 */     this.stepStart = t0;
/* 597 */     double hNew = 0.0D;
/* 598 */     double maxError = Double.MAX_VALUE;
/* 599 */     boolean previousRejected = false;
/* 600 */     boolean firstTime = true;
/* 601 */     boolean newStep = true;
/* 602 */     boolean lastStep = false;
/* 603 */     boolean firstStepAlreadyComputed = false;
/* 604 */     this.handler.reset();
/* 605 */     this.costPerTimeUnit[0] = 0.0D;
/* 606 */     while (!lastStep)
/*     */     {
/*     */ 
/* 609 */       boolean reject = false;
/*     */       
/* 611 */       if (newStep)
/*     */       {
/* 613 */         interpolator.shift();
/*     */         
/*     */ 
/* 616 */         if (!firstStepAlreadyComputed) {
/* 617 */           equations.computeDerivatives(this.stepStart, y, yDot0);
/*     */         }
/*     */         
/* 620 */         if (firstTime)
/*     */         {
/* 622 */           hNew = initializeStep(equations, forward, 2 * targetIter + 1, scale, this.stepStart, y, yDot0, yTmp, yTmpDot);
/*     */           
/*     */ 
/*     */ 
/* 626 */           if (!forward) {
/* 627 */             hNew = -hNew;
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 632 */         newStep = false;
/*     */       }
/*     */       
/*     */ 
/* 636 */       this.stepSize = hNew;
/*     */       
/*     */ 
/* 639 */       if (((forward) && (this.stepStart + this.stepSize > t)) || ((!forward) && (this.stepStart + this.stepSize < t)))
/*     */       {
/* 641 */         this.stepSize = (t - this.stepStart);
/*     */       }
/* 643 */       double nextT = this.stepStart + this.stepSize;
/* 644 */       lastStep = nextT >= t;
/*     */       
/*     */ 
/* 647 */       int k = -1;
/* 648 */       for (boolean loop = true; loop;)
/*     */       {
/* 650 */         k++;
/*     */         
/*     */ 
/* 653 */         if (!tryStep(equations, this.stepStart, y, this.stepSize, k, scale, fk[k], k == 0 ? yMidDots[0] : diagonal[(k - 1)], k == 0 ? y1 : y1Diag[(k - 1)], yTmp))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 659 */           hNew = Math.abs(filterStep(this.stepSize * this.stabilityReduction, false));
/* 660 */           reject = true;
/* 661 */           loop = false;
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/* 666 */         else if (k > 0)
/*     */         {
/*     */ 
/*     */ 
/* 670 */           extrapolate(0, k, y1Diag, y1);
/* 671 */           rescale(y, y1, scale);
/*     */           
/*     */ 
/* 674 */           double error = 0.0D;
/* 675 */           for (int j = 0; j < y0.length; j++) {
/* 676 */             double e = Math.abs(y1[j] - y1Diag[0][j]) / scale[j];
/* 677 */             error += e * e;
/*     */           }
/* 679 */           error = Math.sqrt(error / y0.length);
/*     */           
/* 681 */           if ((error > 1.0E15D) || ((k > 1) && (error > maxError)))
/*     */           {
/* 683 */             hNew = Math.abs(filterStep(this.stepSize * this.stabilityReduction, false));
/* 684 */             reject = true;
/* 685 */             loop = false;
/*     */           }
/*     */           else {
/* 688 */             maxError = Math.max(4.0D * error, 1.0D);
/*     */             
/*     */ 
/* 691 */             double exp = 1.0D / (2 * k + 1);
/* 692 */             double fac = this.stepControl2 / Math.pow(error / this.stepControl1, exp);
/* 693 */             double pow = Math.pow(this.stepControl3, exp);
/* 694 */             fac = Math.max(pow / this.stepControl4, Math.min(1.0D / pow, fac));
/* 695 */             this.optimalStep[k] = Math.abs(filterStep(this.stepSize * fac, true));
/* 696 */             this.costPerTimeUnit[k] = (this.costPerStep[k] / this.optimalStep[k]);
/*     */             
/*     */ 
/* 699 */             switch (k - targetIter)
/*     */             {
/*     */             case -1: 
/* 702 */               if ((targetIter > 1) && (!previousRejected))
/*     */               {
/*     */ 
/* 705 */                 if (error <= 1.0D)
/*     */                 {
/* 707 */                   loop = false;
/*     */ 
/*     */                 }
/*     */                 else
/*     */                 {
/* 712 */                   double ratio = this.sequence[k] * this.sequence[(k + 1)] / (this.sequence[0] * this.sequence[0]);
/*     */                   
/* 714 */                   if (error > ratio * ratio)
/*     */                   {
/*     */ 
/* 717 */                     reject = true;
/* 718 */                     loop = false;
/* 719 */                     targetIter = k;
/* 720 */                     if ((targetIter > 1) && (this.costPerTimeUnit[(targetIter - 1)] < this.orderControl1 * this.costPerTimeUnit[targetIter]))
/*     */                     {
/*     */ 
/* 723 */                       targetIter--;
/*     */                     }
/* 725 */                     hNew = this.optimalStep[targetIter];
/*     */                   } } }
/* 727 */               break;
/*     */             
/*     */ 
/*     */ 
/*     */             case 0: 
/* 732 */               if (error <= 1.0D)
/*     */               {
/* 734 */                 loop = false;
/*     */ 
/*     */               }
/*     */               else
/*     */               {
/* 739 */                 double ratio = this.sequence[(k + 1)] / this.sequence[0];
/* 740 */                 if (error > ratio * ratio)
/*     */                 {
/*     */ 
/* 743 */                   reject = true;
/* 744 */                   loop = false;
/* 745 */                   if ((targetIter > 1) && (this.costPerTimeUnit[(targetIter - 1)] < this.orderControl1 * this.costPerTimeUnit[targetIter]))
/*     */                   {
/*     */ 
/* 748 */                     targetIter--;
/*     */                   }
/* 750 */                   hNew = this.optimalStep[targetIter];
/*     */                 }
/*     */               }
/* 753 */               break;
/*     */             
/*     */             case 1: 
/* 756 */               if (error > 1.0D) {
/* 757 */                 reject = true;
/* 758 */                 if ((targetIter > 1) && (this.costPerTimeUnit[(targetIter - 1)] < this.orderControl1 * this.costPerTimeUnit[targetIter]))
/*     */                 {
/*     */ 
/* 761 */                   targetIter--;
/*     */                 }
/* 763 */                 hNew = this.optimalStep[targetIter];
/*     */               }
/* 765 */               loop = false;
/* 766 */               break;
/*     */             
/*     */             default: 
/* 769 */               if (((firstTime) || (lastStep)) && (error <= 1.0D)) {
/* 770 */                 loop = false;
/*     */               }
/*     */               
/*     */ 
/*     */               break;
/*     */             }
/*     */             
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 782 */       double hInt = getMaxStep();
/* 783 */       if ((this.denseOutput) && (!reject))
/*     */       {
/*     */ 
/* 786 */         for (int j = 1; j <= k; j++) {
/* 787 */           extrapolate(0, j, diagonal, yMidDots[0]);
/*     */         }
/*     */         
/*     */ 
/* 791 */         equations.computeDerivatives(this.stepStart + this.stepSize, y1, yDot1);
/*     */         
/* 793 */         int mu = 2 * k - this.mudif + 3;
/*     */         
/* 795 */         for (int l = 0; l < mu; l++)
/*     */         {
/*     */ 
/* 798 */           int l2 = l / 2;
/* 799 */           double factor = Math.pow(0.5D * this.sequence[l2], l);
/* 800 */           int middleIndex = fk[l2].length / 2;
/* 801 */           for (int i = 0; i < y0.length; i++) {
/* 802 */             yMidDots[(l + 1)][i] = (factor * fk[l2][(middleIndex + l)][i]);
/*     */           }
/* 804 */           for (int j = 1; j <= k - l2; j++) {
/* 805 */             factor = Math.pow(0.5D * this.sequence[(j + l2)], l);
/* 806 */             middleIndex = fk[(l2 + j)].length / 2;
/* 807 */             for (int i = 0; i < y0.length; i++) {
/* 808 */               diagonal[(j - 1)][i] = (factor * fk[(l2 + j)][(middleIndex + l)][i]);
/*     */             }
/* 810 */             extrapolate(l2, j, diagonal, yMidDots[(l + 1)]);
/*     */           }
/* 812 */           for (int i = 0; i < y0.length; i++) {
/* 813 */             yMidDots[(l + 1)][i] *= this.stepSize;
/*     */           }
/*     */           
/*     */ 
/* 817 */           for (int j = (l + 1) / 2; j <= k; j++) {
/* 818 */             for (int m = fk[j].length - 1; m >= 2 * (l + 1); m--) {
/* 819 */               for (int i = 0; i < y0.length; i++) {
/* 820 */                 fk[j][m][i] -= fk[j][(m - 2)][i];
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 827 */         if (mu >= 0)
/*     */         {
/*     */ 
/* 830 */           GraggBulirschStoerStepInterpolator gbsInterpolator = (GraggBulirschStoerStepInterpolator)interpolator;
/*     */           
/* 832 */           gbsInterpolator.computeCoefficients(mu, this.stepSize);
/*     */           
/* 834 */           if (this.useInterpolationError)
/*     */           {
/* 836 */             double interpError = gbsInterpolator.estimateError(scale);
/* 837 */             hInt = Math.abs(this.stepSize / Math.max(Math.pow(interpError, 1.0D / (mu + 4)), 0.01D));
/*     */             
/* 839 */             if (interpError > 10.0D) {
/* 840 */               hNew = hInt;
/* 841 */               reject = true;
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 846 */           if (!reject) {
/* 847 */             interpolator.storeTime(this.stepStart + this.stepSize);
/* 848 */             if (this.switchesHandler.evaluateStep(interpolator)) {
/* 849 */               reject = true;
/* 850 */               hNew = Math.abs(this.switchesHandler.getEventTime() - this.stepStart);
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 856 */         if (!reject)
/*     */         {
/* 858 */           firstStepAlreadyComputed = true;
/* 859 */           System.arraycopy(yDot1, 0, yDot0, 0, y0.length);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 864 */       if (!reject)
/*     */       {
/*     */ 
/* 867 */         double nextStep = this.stepStart + this.stepSize;
/* 868 */         System.arraycopy(y1, 0, y, 0, y0.length);
/*     */         
/* 870 */         this.switchesHandler.stepAccepted(nextStep, y);
/* 871 */         if (this.switchesHandler.stop()) {
/* 872 */           lastStep = true;
/*     */         }
/*     */         
/*     */ 
/* 876 */         interpolator.storeTime(nextStep);
/* 877 */         this.handler.handleStep(interpolator, lastStep);
/* 878 */         this.stepStart = nextStep;
/*     */         
/* 880 */         if ((this.switchesHandler.reset(this.stepStart, y)) && (!lastStep))
/*     */         {
/*     */ 
/* 883 */           firstStepAlreadyComputed = false;
/*     */         }
/*     */         
/*     */         int optimalIter;
/* 887 */         if (k == 1) {
/* 888 */           int optimalIter = 2;
/* 889 */           if (previousRejected) {
/* 890 */             optimalIter = 1;
/*     */           }
/* 892 */         } else if (k <= targetIter) {
/* 893 */           int optimalIter = k;
/* 894 */           if (this.costPerTimeUnit[(k - 1)] < this.orderControl1 * this.costPerTimeUnit[k]) {
/* 895 */             optimalIter = k - 1;
/* 896 */           } else if (this.costPerTimeUnit[k] < this.orderControl2 * this.costPerTimeUnit[(k - 1)]) {
/* 897 */             optimalIter = Math.min(k + 1, this.sequence.length - 2);
/*     */           }
/*     */         } else {
/* 900 */           optimalIter = k - 1;
/* 901 */           if ((k > 2) && (this.costPerTimeUnit[(k - 2)] < this.orderControl1 * this.costPerTimeUnit[(k - 1)]))
/*     */           {
/* 903 */             optimalIter = k - 2;
/*     */           }
/* 905 */           if (this.costPerTimeUnit[k] < this.orderControl2 * this.costPerTimeUnit[optimalIter]) {
/* 906 */             optimalIter = Math.min(k, this.sequence.length - 2);
/*     */           }
/*     */         }
/*     */         
/* 910 */         if (previousRejected)
/*     */         {
/*     */ 
/* 913 */           targetIter = Math.min(optimalIter, k);
/* 914 */           hNew = Math.min(Math.abs(this.stepSize), this.optimalStep[targetIter]);
/*     */         }
/*     */         else {
/* 917 */           if (optimalIter <= k) {
/* 918 */             hNew = this.optimalStep[optimalIter];
/*     */           }
/* 920 */           else if ((k < targetIter) && (this.costPerTimeUnit[k] < this.orderControl2 * this.costPerTimeUnit[(k - 1)]))
/*     */           {
/* 922 */             hNew = filterStep(this.optimalStep[k] * this.costPerStep[(optimalIter + 1)] / this.costPerStep[k], false);
/*     */           }
/*     */           else
/*     */           {
/* 926 */             hNew = filterStep(this.optimalStep[k] * this.costPerStep[optimalIter] / this.costPerStep[k], false);
/*     */           }
/*     */           
/*     */ 
/*     */ 
/*     */ 
/* 932 */           targetIter = optimalIter;
/*     */         }
/*     */         
/*     */ 
/* 936 */         newStep = true;
/*     */       }
/*     */       
/*     */ 
/* 940 */       hNew = Math.min(hNew, hInt);
/* 941 */       if (!forward) {
/* 942 */         hNew = -hNew;
/*     */       }
/*     */       
/* 945 */       firstTime = false;
/*     */       
/* 947 */       if (reject) {
/* 948 */         lastStep = false;
/* 949 */         previousRejected = true;
/*     */       } else {
/* 951 */         previousRejected = false;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/GraggBulirschStoerIntegrator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */