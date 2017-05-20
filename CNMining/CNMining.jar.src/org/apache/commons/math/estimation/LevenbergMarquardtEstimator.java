/*     */ package org.apache.commons.math.estimation;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LevenbergMarquardtEstimator
/*     */   extends AbstractEstimator
/*     */   implements Serializable
/*     */ {
/*     */   private int solvedCols;
/*     */   private double[] diagR;
/*     */   private double[] jacNorm;
/*     */   private double[] beta;
/*     */   private int[] permutation;
/*     */   private int rank;
/*     */   private double lmPar;
/*     */   private double[] lmDir;
/*     */   private double initialStepBoundFactor;
/*     */   private double costRelativeTolerance;
/*     */   private double parRelativeTolerance;
/*     */   private double orthoTolerance;
/*     */   private static final long serialVersionUID = -5705952631533171019L;
/*     */   
/*     */   public LevenbergMarquardtEstimator()
/*     */   {
/* 117 */     setMaxCostEval(1000);
/*     */     
/*     */ 
/* 120 */     setInitialStepBoundFactor(100.0D);
/* 121 */     setCostRelativeTolerance(1.0E-10D);
/* 122 */     setParRelativeTolerance(1.0E-10D);
/* 123 */     setOrthoTolerance(1.0E-10D);
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
/*     */   public void setInitialStepBoundFactor(double initialStepBoundFactor)
/*     */   {
/* 137 */     this.initialStepBoundFactor = initialStepBoundFactor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCostRelativeTolerance(double costRelativeTolerance)
/*     */   {
/* 147 */     this.costRelativeTolerance = costRelativeTolerance;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setParRelativeTolerance(double parRelativeTolerance)
/*     */   {
/* 158 */     this.parRelativeTolerance = parRelativeTolerance;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOrthoTolerance(double orthoTolerance)
/*     */   {
/* 169 */     this.orthoTolerance = orthoTolerance;
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
/*     */   public void estimate(EstimationProblem problem)
/*     */     throws EstimationException
/*     */   {
/* 203 */     initializeEstimate(problem);
/*     */     
/*     */ 
/* 206 */     this.solvedCols = Math.min(this.rows, this.cols);
/* 207 */     this.diagR = new double[this.cols];
/* 208 */     this.jacNorm = new double[this.cols];
/* 209 */     this.beta = new double[this.cols];
/* 210 */     this.permutation = new int[this.cols];
/* 211 */     this.lmDir = new double[this.cols];
/*     */     
/*     */ 
/* 214 */     double delta = 0.0D;double xNorm = 0.0D;
/* 215 */     double[] diag = new double[this.cols];
/* 216 */     double[] oldX = new double[this.cols];
/* 217 */     double[] oldRes = new double[this.rows];
/* 218 */     double[] work1 = new double[this.cols];
/* 219 */     double[] work2 = new double[this.cols];
/* 220 */     double[] work3 = new double[this.cols];
/*     */     
/*     */ 
/* 223 */     updateResidualsAndCost();
/*     */     
/*     */ 
/* 226 */     this.lmPar = 0.0D;
/* 227 */     boolean firstIteration = true;
/*     */     double maxCosine;
/*     */     double ratio;
/*     */     for (;;) {
/* 231 */       updateJacobian();
/* 232 */       qrDecomposition();
/*     */       
/*     */ 
/* 235 */       qTy(this.residuals);
/*     */       
/*     */ 
/*     */ 
/* 239 */       for (int k = 0; k < this.solvedCols; k++) {
/* 240 */         int pk = this.permutation[k];
/* 241 */         this.jacobian[(k * this.cols + pk)] = this.diagR[pk];
/*     */       }
/*     */       
/* 244 */       if (firstIteration)
/*     */       {
/*     */ 
/*     */ 
/* 248 */         xNorm = 0.0D;
/* 249 */         for (int k = 0; k < this.cols; k++) {
/* 250 */           double dk = this.jacNorm[k];
/* 251 */           if (dk == 0.0D) {
/* 252 */             dk = 1.0D;
/*     */           }
/* 254 */           double xk = dk * this.parameters[k].getEstimate();
/* 255 */           xNorm += xk * xk;
/* 256 */           diag[k] = dk;
/*     */         }
/* 258 */         xNorm = Math.sqrt(xNorm);
/*     */         
/*     */ 
/* 261 */         delta = xNorm == 0.0D ? this.initialStepBoundFactor : this.initialStepBoundFactor * xNorm;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 266 */       maxCosine = 0.0D;
/* 267 */       if (this.cost != 0.0D) {
/* 268 */         for (int j = 0; j < this.solvedCols; j++) {
/* 269 */           int pj = this.permutation[j];
/* 270 */           double s = this.jacNorm[pj];
/* 271 */           if (s != 0.0D) {
/* 272 */             double sum = 0.0D;
/* 273 */             int i = 0; for (int index = pj; i <= j; index += this.cols) {
/* 274 */               sum += this.jacobian[index] * this.residuals[i];i++;
/*     */             }
/* 276 */             maxCosine = Math.max(maxCosine, Math.abs(sum) / (s * this.cost));
/*     */           }
/*     */         }
/*     */       }
/* 280 */       if (maxCosine <= this.orthoTolerance) {
/* 281 */         return;
/*     */       }
/*     */       
/*     */ 
/* 285 */       for (int j = 0; j < this.cols; j++) {
/* 286 */         diag[j] = Math.max(diag[j], this.jacNorm[j]);
/*     */       }
/*     */       
/*     */ 
/* 290 */       for (ratio = 0.0D; ratio < 1.0E-4D;)
/*     */       {
/*     */ 
/* 293 */         for (int j = 0; j < this.solvedCols; j++) {
/* 294 */           int pj = this.permutation[j];
/* 295 */           oldX[pj] = this.parameters[pj].getEstimate();
/*     */         }
/* 297 */         double previousCost = this.cost;
/* 298 */         double[] tmpVec = this.residuals;
/* 299 */         this.residuals = oldRes;
/* 300 */         oldRes = tmpVec;
/*     */         
/*     */ 
/* 303 */         determineLMParameter(oldRes, delta, diag, work1, work2, work3);
/*     */         
/*     */ 
/* 306 */         double lmNorm = 0.0D;
/* 307 */         for (int j = 0; j < this.solvedCols; j++) {
/* 308 */           int pj = this.permutation[j];
/* 309 */           this.lmDir[pj] = (-this.lmDir[pj]);
/* 310 */           this.parameters[pj].setEstimate(oldX[pj] + this.lmDir[pj]);
/* 311 */           double s = diag[pj] * this.lmDir[pj];
/* 312 */           lmNorm += s * s;
/*     */         }
/* 314 */         lmNorm = Math.sqrt(lmNorm);
/*     */         
/*     */ 
/* 317 */         if (firstIteration) {
/* 318 */           delta = Math.min(delta, lmNorm);
/*     */         }
/*     */         
/*     */ 
/* 322 */         updateResidualsAndCost();
/*     */         
/*     */ 
/* 325 */         double actRed = -1.0D;
/* 326 */         if (0.1D * this.cost < previousCost) {
/* 327 */           double r = this.cost / previousCost;
/* 328 */           actRed = 1.0D - r * r;
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 333 */         for (int j = 0; j < this.solvedCols; j++) {
/* 334 */           int pj = this.permutation[j];
/* 335 */           double dirJ = this.lmDir[pj];
/* 336 */           work1[j] = 0.0D;
/* 337 */           int i = 0; for (int index = pj; i <= j; index += this.cols) {
/* 338 */             work1[i] += this.jacobian[index] * dirJ;i++;
/*     */           }
/*     */         }
/* 341 */         double coeff1 = 0.0D;
/* 342 */         for (int j = 0; j < this.solvedCols; j++) {
/* 343 */           coeff1 += work1[j] * work1[j];
/*     */         }
/* 345 */         double pc2 = previousCost * previousCost;
/* 346 */         coeff1 /= pc2;
/* 347 */         double coeff2 = this.lmPar * lmNorm * lmNorm / pc2;
/* 348 */         double preRed = coeff1 + 2.0D * coeff2;
/* 349 */         double dirDer = -(coeff1 + coeff2);
/*     */         
/*     */ 
/* 352 */         ratio = preRed == 0.0D ? 0.0D : actRed / preRed;
/*     */         
/*     */ 
/* 355 */         if (ratio <= 0.25D) {
/* 356 */           double tmp = actRed < 0.0D ? 0.5D * dirDer / (dirDer + 0.5D * actRed) : 0.5D;
/*     */           
/* 358 */           if ((0.1D * this.cost >= previousCost) || (tmp < 0.1D)) {
/* 359 */             tmp = 0.1D;
/*     */           }
/* 361 */           delta = tmp * Math.min(delta, 10.0D * lmNorm);
/* 362 */           this.lmPar /= tmp;
/* 363 */         } else if ((this.lmPar == 0.0D) || (ratio >= 0.75D)) {
/* 364 */           delta = 2.0D * lmNorm;
/* 365 */           this.lmPar *= 0.5D;
/*     */         }
/*     */         
/*     */ 
/* 369 */         if (ratio >= 1.0E-4D)
/*     */         {
/* 371 */           firstIteration = false;
/* 372 */           xNorm = 0.0D;
/* 373 */           for (int k = 0; k < this.cols; k++) {
/* 374 */             double xK = diag[k] * this.parameters[k].getEstimate();
/* 375 */             xNorm += xK * xK;
/*     */           }
/* 377 */           xNorm = Math.sqrt(xNorm);
/*     */         }
/*     */         else {
/* 380 */           this.cost = previousCost;
/* 381 */           for (int j = 0; j < this.solvedCols; j++) {
/* 382 */             int pj = this.permutation[j];
/* 383 */             this.parameters[pj].setEstimate(oldX[pj]);
/*     */           }
/* 385 */           tmpVec = this.residuals;
/* 386 */           this.residuals = oldRes;
/* 387 */           oldRes = tmpVec;
/*     */         }
/*     */         
/*     */ 
/* 391 */         if (((Math.abs(actRed) <= this.costRelativeTolerance) && (preRed <= this.costRelativeTolerance) && (ratio <= 2.0D)) || (delta <= this.parRelativeTolerance * xNorm))
/*     */         {
/*     */ 
/*     */ 
/* 395 */           return;
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 400 */         if ((Math.abs(actRed) <= 2.2204E-16D) && (preRed <= 2.2204E-16D) && (ratio <= 2.0D)) {
/* 401 */           throw new EstimationException("cost relative tolerance is too small ({0}), no further reduction in the sum of squares is possible", new Object[] { new Double(this.costRelativeTolerance) });
/*     */         }
/*     */         
/*     */ 
/* 405 */         if (delta <= 2.2204E-16D * xNorm) {
/* 406 */           throw new EstimationException("parameters relative tolerance is too small ({0}), no further improvement in the approximate solution is possible", new Object[] { new Double(this.parRelativeTolerance) });
/*     */         }
/*     */         
/*     */ 
/* 410 */         if (maxCosine <= 2.2204E-16D) {
/* 411 */           throw new EstimationException("orthogonality tolerance is too small ({0}), solution is orthogonal to the jacobian", new Object[] { new Double(this.orthoTolerance) });
/*     */         }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void determineLMParameter(double[] qy, double delta, double[] diag, double[] work1, double[] work2, double[] work3)
/*     */   {
/* 449 */     for (int j = 0; j < this.rank; j++) {
/* 450 */       this.lmDir[this.permutation[j]] = qy[j];
/*     */     }
/* 452 */     for (int j = this.rank; j < this.cols; j++) {
/* 453 */       this.lmDir[this.permutation[j]] = 0.0D;
/*     */     }
/* 455 */     for (int k = this.rank - 1; k >= 0; k--) {
/* 456 */       int pk = this.permutation[k];
/* 457 */       double ypk = this.lmDir[pk] / this.diagR[pk];
/* 458 */       int i = 0; for (int index = pk; i < k; index += this.cols) {
/* 459 */         this.lmDir[this.permutation[i]] -= ypk * this.jacobian[index];i++;
/*     */       }
/* 461 */       this.lmDir[pk] = ypk;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 466 */     double dxNorm = 0.0D;
/* 467 */     for (int j = 0; j < this.solvedCols; j++) {
/* 468 */       int pj = this.permutation[j];
/* 469 */       double s = diag[pj] * this.lmDir[pj];
/* 470 */       work1[pj] = s;
/* 471 */       dxNorm += s * s;
/*     */     }
/* 473 */     dxNorm = Math.sqrt(dxNorm);
/* 474 */     double fp = dxNorm - delta;
/* 475 */     if (fp <= 0.1D * delta) {
/* 476 */       this.lmPar = 0.0D;
/* 477 */       return;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 483 */     double parl = 0.0D;
/* 484 */     if (this.rank == this.solvedCols) {
/* 485 */       for (int j = 0; j < this.solvedCols; j++) {
/* 486 */         int pj = this.permutation[j];
/* 487 */         work1[pj] *= diag[pj] / dxNorm;
/*     */       }
/* 489 */       double sum2 = 0.0D;
/* 490 */       for (int j = 0; j < this.solvedCols; j++) {
/* 491 */         int pj = this.permutation[j];
/* 492 */         double sum = 0.0D;
/* 493 */         int i = 0; for (int index = pj; i < j; index += this.cols) {
/* 494 */           sum += this.jacobian[index] * work1[this.permutation[i]];i++;
/*     */         }
/* 496 */         double s = (work1[pj] - sum) / this.diagR[pj];
/* 497 */         work1[pj] = s;
/* 498 */         sum2 += s * s;
/*     */       }
/* 500 */       parl = fp / (delta * sum2);
/*     */     }
/*     */     
/*     */ 
/* 504 */     double sum2 = 0.0D;
/* 505 */     for (int j = 0; j < this.solvedCols; j++) {
/* 506 */       int pj = this.permutation[j];
/* 507 */       double sum = 0.0D;
/* 508 */       int i = 0; for (int index = pj; i <= j; index += this.cols) {
/* 509 */         sum += this.jacobian[index] * qy[i];i++;
/*     */       }
/* 511 */       sum /= diag[pj];
/* 512 */       sum2 += sum * sum;
/*     */     }
/* 514 */     double gNorm = Math.sqrt(sum2);
/* 515 */     double paru = gNorm / delta;
/* 516 */     if (paru == 0.0D)
/*     */     {
/* 518 */       paru = 2.2251E-308D / Math.min(delta, 0.1D);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 523 */     this.lmPar = Math.min(paru, Math.max(this.lmPar, parl));
/* 524 */     if (this.lmPar == 0.0D) {
/* 525 */       this.lmPar = (gNorm / dxNorm);
/*     */     }
/*     */     
/* 528 */     for (int countdown = 10; countdown >= 0; countdown--)
/*     */     {
/*     */ 
/* 531 */       if (this.lmPar == 0.0D) {
/* 532 */         this.lmPar = Math.max(2.2251E-308D, 0.001D * paru);
/*     */       }
/* 534 */       double sPar = Math.sqrt(this.lmPar);
/* 535 */       for (int j = 0; j < this.solvedCols; j++) {
/* 536 */         int pj = this.permutation[j];
/* 537 */         work1[pj] = (sPar * diag[pj]);
/*     */       }
/* 539 */       determineLMDirection(qy, work1, work2, work3);
/*     */       
/* 541 */       dxNorm = 0.0D;
/* 542 */       for (int j = 0; j < this.solvedCols; j++) {
/* 543 */         int pj = this.permutation[j];
/* 544 */         double s = diag[pj] * this.lmDir[pj];
/* 545 */         work3[pj] = s;
/* 546 */         dxNorm += s * s;
/*     */       }
/* 548 */       dxNorm = Math.sqrt(dxNorm);
/* 549 */       double previousFP = fp;
/* 550 */       fp = dxNorm - delta;
/*     */       
/*     */ 
/*     */ 
/* 554 */       if ((Math.abs(fp) <= 0.1D * delta) || ((parl == 0.0D) && (fp <= previousFP) && (previousFP < 0.0D)))
/*     */       {
/* 556 */         return;
/*     */       }
/*     */       
/*     */ 
/* 560 */       for (int j = 0; j < this.solvedCols; j++) {
/* 561 */         int pj = this.permutation[j];
/* 562 */         work1[pj] = (work3[pj] * diag[pj] / dxNorm);
/*     */       }
/* 564 */       for (int j = 0; j < this.solvedCols; j++) {
/* 565 */         int pj = this.permutation[j];
/* 566 */         work1[pj] /= work2[j];
/* 567 */         double tmp = work1[pj];
/* 568 */         for (int i = j + 1; i < this.solvedCols; i++) {
/* 569 */           work1[this.permutation[i]] -= this.jacobian[(i * this.cols + pj)] * tmp;
/*     */         }
/*     */       }
/* 572 */       sum2 = 0.0D;
/* 573 */       for (int j = 0; j < this.solvedCols; j++) {
/* 574 */         double s = work1[this.permutation[j]];
/* 575 */         sum2 += s * s;
/*     */       }
/* 577 */       double correction = fp / (delta * sum2);
/*     */       
/*     */ 
/* 580 */       if (fp > 0.0D) {
/* 581 */         parl = Math.max(parl, this.lmPar);
/* 582 */       } else if (fp < 0.0D) {
/* 583 */         paru = Math.min(paru, this.lmPar);
/*     */       }
/*     */       
/*     */ 
/* 587 */       this.lmPar = Math.max(parl, this.lmPar + correction);
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
/*     */   private void determineLMDirection(double[] qy, double[] diag, double[] lmDiag, double[] work)
/*     */   {
/* 617 */     for (int j = 0; j < this.solvedCols; j++) {
/* 618 */       int pj = this.permutation[j];
/* 619 */       for (int i = j + 1; i < this.solvedCols; i++) {
/* 620 */         this.jacobian[(i * this.cols + pj)] = this.jacobian[(j * this.cols + this.permutation[i])];
/*     */       }
/* 622 */       this.lmDir[j] = this.diagR[pj];
/* 623 */       work[j] = qy[j];
/*     */     }
/*     */     
/*     */ 
/* 627 */     for (int j = 0; j < this.solvedCols; j++)
/*     */     {
/*     */ 
/*     */ 
/* 631 */       int pj = this.permutation[j];
/* 632 */       double dpj = diag[pj];
/* 633 */       if (dpj != 0.0D) {
/* 634 */         Arrays.fill(lmDiag, j + 1, lmDiag.length, 0.0D);
/*     */       }
/* 636 */       lmDiag[j] = dpj;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 641 */       double qtbpj = 0.0D;
/* 642 */       for (int k = j; k < this.solvedCols; k++) {
/* 643 */         int pk = this.permutation[k];
/*     */         
/*     */ 
/*     */ 
/* 647 */         if (lmDiag[k] != 0.0D)
/*     */         {
/*     */ 
/* 650 */           double rkk = this.jacobian[(k * this.cols + pk)];
/* 651 */           double cos; double cos; double sin; if (Math.abs(rkk) < Math.abs(lmDiag[k])) {
/* 652 */             double cotan = rkk / lmDiag[k];
/* 653 */             double sin = 1.0D / Math.sqrt(1.0D + cotan * cotan);
/* 654 */             cos = sin * cotan;
/*     */           } else {
/* 656 */             double tan = lmDiag[k] / rkk;
/* 657 */             cos = 1.0D / Math.sqrt(1.0D + tan * tan);
/* 658 */             sin = cos * tan;
/*     */           }
/*     */           
/*     */ 
/*     */ 
/* 663 */           this.jacobian[(k * this.cols + pk)] = (cos * rkk + sin * lmDiag[k]);
/* 664 */           double temp = cos * work[k] + sin * qtbpj;
/* 665 */           qtbpj = -sin * work[k] + cos * qtbpj;
/* 666 */           work[k] = temp;
/*     */           
/*     */ 
/* 669 */           for (int i = k + 1; i < this.solvedCols; i++) {
/* 670 */             double rik = this.jacobian[(i * this.cols + pk)];
/* 671 */             temp = cos * rik + sin * lmDiag[i];
/* 672 */             lmDiag[i] = (-sin * rik + cos * lmDiag[i]);
/* 673 */             this.jacobian[(i * this.cols + pk)] = temp;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 681 */       int index = j * this.cols + this.permutation[j];
/* 682 */       lmDiag[j] = this.jacobian[index];
/* 683 */       this.jacobian[index] = this.lmDir[j];
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 689 */     int nSing = this.solvedCols;
/* 690 */     for (int j = 0; j < this.solvedCols; j++) {
/* 691 */       if ((lmDiag[j] == 0.0D) && (nSing == this.solvedCols)) {
/* 692 */         nSing = j;
/*     */       }
/* 694 */       if (nSing < this.solvedCols) {
/* 695 */         work[j] = 0.0D;
/*     */       }
/*     */     }
/* 698 */     if (nSing > 0) {
/* 699 */       for (int j = nSing - 1; j >= 0; j--) {
/* 700 */         int pj = this.permutation[j];
/* 701 */         double sum = 0.0D;
/* 702 */         for (int i = j + 1; i < nSing; i++) {
/* 703 */           sum += this.jacobian[(i * this.cols + pj)] * work[i];
/*     */         }
/* 705 */         work[j] = ((work[j] - sum) / lmDiag[j]);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 710 */     for (int j = 0; j < this.lmDir.length; j++) {
/* 711 */       this.lmDir[this.permutation[j]] = work[j];
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
/*     */   private void qrDecomposition()
/*     */   {
/* 740 */     for (int k = 0; k < this.cols; k++) {
/* 741 */       this.permutation[k] = k;
/* 742 */       double norm2 = 0.0D;
/* 743 */       for (int index = k; index < this.jacobian.length; index += this.cols) {
/* 744 */         double akk = this.jacobian[index];
/* 745 */         norm2 += akk * akk;
/*     */       }
/* 747 */       this.jacNorm[k] = Math.sqrt(norm2);
/*     */     }
/*     */     
/*     */ 
/* 751 */     for (int k = 0; k < this.cols; k++)
/*     */     {
/*     */ 
/* 754 */       int nextColumn = -1;
/* 755 */       double ak2 = Double.NEGATIVE_INFINITY;
/* 756 */       for (int i = k; i < this.cols; i++) {
/* 757 */         double norm2 = 0.0D;
/* 758 */         int iDiag = k * this.cols + this.permutation[i];
/* 759 */         for (int index = iDiag; index < this.jacobian.length; index += this.cols) {
/* 760 */           double aki = this.jacobian[index];
/* 761 */           norm2 += aki * aki;
/*     */         }
/* 763 */         if (norm2 > ak2) {
/* 764 */           nextColumn = i;
/* 765 */           ak2 = norm2;
/*     */         }
/*     */       }
/* 768 */       if (ak2 == 0.0D) {
/* 769 */         this.rank = k;
/* 770 */         return;
/*     */       }
/* 772 */       int pk = this.permutation[nextColumn];
/* 773 */       this.permutation[nextColumn] = this.permutation[k];
/* 774 */       this.permutation[k] = pk;
/*     */       
/*     */ 
/* 777 */       int kDiag = k * this.cols + pk;
/* 778 */       double akk = this.jacobian[kDiag];
/* 779 */       double alpha = akk > 0.0D ? -Math.sqrt(ak2) : Math.sqrt(ak2);
/* 780 */       double betak = 1.0D / (ak2 - akk * alpha);
/* 781 */       this.beta[pk] = betak;
/*     */       
/*     */ 
/* 784 */       this.diagR[pk] = alpha;
/* 785 */       this.jacobian[kDiag] -= alpha;
/*     */       
/*     */ 
/* 788 */       for (int dk = this.cols - 1 - k; dk > 0; dk--) {
/* 789 */         int dkp = this.permutation[(k + dk)] - pk;
/* 790 */         double gamma = 0.0D;
/* 791 */         for (int index = kDiag; index < this.jacobian.length; index += this.cols) {
/* 792 */           gamma += this.jacobian[index] * this.jacobian[(index + dkp)];
/*     */         }
/* 794 */         gamma *= betak;
/* 795 */         for (int index = kDiag; index < this.jacobian.length; index += this.cols) {
/* 796 */           this.jacobian[(index + dkp)] -= gamma * this.jacobian[index];
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 802 */     this.rank = this.solvedCols;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void qTy(double[] y)
/*     */   {
/* 812 */     for (int k = 0; k < this.cols; k++) {
/* 813 */       int pk = this.permutation[k];
/* 814 */       int kDiag = k * this.cols + pk;
/* 815 */       double gamma = 0.0D;
/* 816 */       int i = k; for (int index = kDiag; i < this.rows; index += this.cols) {
/* 817 */         gamma += this.jacobian[index] * y[i];i++;
/*     */       }
/* 819 */       gamma *= this.beta[pk];
/* 820 */       int i = k; for (int index = kDiag; i < this.rows; index += this.cols) {
/* 821 */         y[i] -= gamma * this.jacobian[index];i++;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/estimation/LevenbergMarquardtEstimator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */