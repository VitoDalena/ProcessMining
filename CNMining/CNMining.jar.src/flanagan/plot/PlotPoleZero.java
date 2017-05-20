/*     */ package flanagan.plot;
/*     */ 
/*     */ import flanagan.complex.Complex;
/*     */ import flanagan.complex.ComplexPoly;
/*     */ import flanagan.io.FileOutput;
/*     */ import flanagan.math.Fmath;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlotPoleZero
/*     */ {
/*  44 */   private ComplexPoly numerPoly = null;
/*  45 */   private ComplexPoly denomPoly = null;
/*  46 */   private Complex[] numerRoots = null;
/*  47 */   private Complex[] denomRoots = null;
/*  48 */   private double[][] data = (double[][])null;
/*  49 */   private int nDeg = 0;
/*  50 */   private int dDeg = 0;
/*  51 */   private int mDeg = 0;
/*  52 */   private int sORz = 0;
/*  53 */   private boolean zerosSet = false;
/*  54 */   private boolean polesSet = false;
/*  55 */   private boolean zCircle = false;
/*  56 */   private boolean noImag = true;
/*  57 */   private boolean noReal = true;
/*  58 */   private boolean noZeros = true;
/*  59 */   private boolean noPoles = true;
/*  60 */   private boolean setUnitAxes = false;
/*     */   
/*  62 */   private boolean setEqualAxes = false;
/*  63 */   private double scaleFactor = 1.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PlotPoleZero() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public PlotPoleZero(ComplexPoly numer, ComplexPoly denom)
/*     */   {
/*  76 */     if (numer != null) {
/*  77 */       this.nDeg = numer.getDeg();
/*  78 */       if (this.nDeg > 0) {
/*  79 */         this.numerPoly = ComplexPoly.copy(numer);
/*  80 */         this.numerRoots = Complex.oneDarray(this.nDeg);
/*  81 */         this.mDeg = this.nDeg;
/*  82 */         this.noZeros = false;
/*     */       }
/*     */     }
/*     */     
/*  86 */     if (denom != null) {
/*  87 */       this.dDeg = denom.getDeg();
/*  88 */       if (this.dDeg > 0) {
/*  89 */         this.denomPoly = ComplexPoly.copy(denom);
/*  90 */         this.denomRoots = Complex.oneDarray(this.dDeg);
/*  91 */         if (!this.noZeros) {
/*  92 */           this.mDeg = Math.max(this.nDeg, this.dDeg);
/*     */         }
/*     */         else {
/*  95 */           this.mDeg = this.dDeg;
/*     */         }
/*  97 */         this.noPoles = false;
/*     */       }
/*     */     }
/* 100 */     if ((this.noZeros) && (this.noPoles)) { throw new IllegalArgumentException("No poles or zeros entered");
/*     */     }
/*     */   }
/*     */   
/*     */   public PlotPoleZero(Complex[] numer, Complex[] denom)
/*     */   {
/* 106 */     if (numer != null) {
/* 107 */       this.nDeg = (numer.length - 1);
/* 108 */       if (this.nDeg > 0) {
/* 109 */         this.numerPoly = new ComplexPoly(numer);
/* 110 */         this.numerRoots = Complex.oneDarray(this.nDeg);
/* 111 */         this.mDeg = this.nDeg;
/* 112 */         this.noZeros = false;
/*     */       }
/*     */     }
/*     */     
/* 116 */     if (denom != null) {
/* 117 */       this.dDeg = (denom.length - 1);
/* 118 */       if (this.dDeg > 0) {
/* 119 */         this.denomPoly = new ComplexPoly(denom);
/* 120 */         this.denomRoots = Complex.oneDarray(this.dDeg);
/* 121 */         if (!this.noZeros) {
/* 122 */           this.mDeg = Math.max(this.nDeg, this.dDeg);
/*     */         }
/*     */         else {
/* 125 */           this.mDeg = this.dDeg;
/*     */         }
/* 127 */         this.noPoles = false;
/*     */       }
/* 129 */       if ((this.noZeros) && (this.noPoles)) { throw new IllegalArgumentException("No poles or zeros entered");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public PlotPoleZero(double[] numer, double[] denom)
/*     */   {
/* 137 */     if (numer != null) {
/* 138 */       this.nDeg = (numer.length - 1);
/* 139 */       if (this.nDeg > 0) {
/* 140 */         this.numerPoly = new ComplexPoly(numer);
/* 141 */         this.numerRoots = Complex.oneDarray(this.nDeg);
/* 142 */         this.mDeg = this.nDeg;
/* 143 */         this.noZeros = false;
/*     */       }
/*     */     }
/*     */     
/* 147 */     if (denom != null) {
/* 148 */       this.dDeg = (denom.length - 1);
/* 149 */       if (this.dDeg > 0) {
/* 150 */         this.denomPoly = new ComplexPoly(denom);
/* 151 */         this.denomRoots = Complex.oneDarray(this.dDeg);
/* 152 */         if (!this.noZeros) {
/* 153 */           this.mDeg = Math.max(this.nDeg, this.dDeg);
/*     */         }
/*     */         else {
/* 156 */           this.mDeg = this.dDeg;
/*     */         }
/* 158 */         this.noPoles = false;
/*     */       }
/* 160 */       if ((this.noZeros) && (this.noPoles)) { throw new IllegalArgumentException("No poles or zeros entered");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void setNumerator(ComplexPoly numer)
/*     */   {
/* 167 */     if (numer != null) {
/* 168 */       this.nDeg = numer.getDeg();
/* 169 */       if (this.nDeg > 0) {
/* 170 */         this.numerPoly = ComplexPoly.copy(numer);
/* 171 */         this.numerRoots = Complex.oneDarray(this.nDeg);
/* 172 */         if (!this.noPoles) {
/* 173 */           this.mDeg = Math.max(this.nDeg, this.dDeg);
/*     */         }
/*     */         else {
/* 176 */           this.mDeg = this.nDeg;
/*     */         }
/* 178 */         this.noZeros = false;
/*     */       }
/*     */     }
/*     */     else {
/* 182 */       this.noZeros = true;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void setNumerator(Complex[] numer)
/*     */   {
/* 189 */     if (numer != null) {
/* 190 */       this.nDeg = (numer.length - 1);
/* 191 */       if (this.nDeg > 0) {
/* 192 */         this.numerPoly = new ComplexPoly(numer);
/* 193 */         this.numerRoots = Complex.oneDarray(this.nDeg);
/* 194 */         if (!this.noPoles) {
/* 195 */           this.mDeg = Math.max(this.nDeg, this.dDeg);
/*     */         }
/*     */         else {
/* 198 */           this.mDeg = this.nDeg;
/*     */         }
/* 200 */         this.noZeros = false;
/*     */       }
/*     */     }
/*     */     else {
/* 204 */       this.noZeros = true;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setNumerator(double[] numer)
/*     */   {
/* 210 */     if (numer != null) {
/* 211 */       this.nDeg = (numer.length - 1);
/* 212 */       if (this.nDeg > 0) {
/* 213 */         this.numerPoly = new ComplexPoly(numer);
/* 214 */         this.numerRoots = Complex.oneDarray(this.nDeg);
/* 215 */         if (!this.noPoles) {
/* 216 */           this.mDeg = Math.max(this.nDeg, this.dDeg);
/*     */         }
/*     */         else {
/* 219 */           this.mDeg = this.nDeg;
/*     */         }
/* 221 */         this.noZeros = false;
/*     */       }
/*     */     }
/*     */     else {
/* 225 */       this.noZeros = true;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setZeros(Complex[] zeros)
/*     */   {
/* 231 */     if (zeros != null) {
/* 232 */       this.nDeg = zeros.length;
/* 233 */       if (this.nDeg > 0) {
/* 234 */         this.numerRoots = zeros;
/* 235 */         this.numerPoly = ComplexPoly.rootsToPoly(zeros);
/* 236 */         if (!this.noPoles) {
/* 237 */           this.mDeg = Math.max(this.nDeg, this.dDeg);
/*     */         }
/*     */         else {
/* 240 */           this.mDeg = this.nDeg;
/*     */         }
/* 242 */         this.noZeros = false;
/*     */       }
/* 244 */       this.zerosSet = true;
/*     */     }
/*     */     else {
/* 247 */       this.noZeros = true;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setZeros(double[] zeros)
/*     */   {
/* 253 */     int n = zeros.length;
/* 254 */     Complex[] czeros = Complex.oneDarray(n);
/* 255 */     for (int i = 0; i < n; i++) czeros[i] = new Complex(zeros[i], 0.0D);
/* 256 */     setZeros(czeros);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setDenominator(ComplexPoly denom)
/*     */   {
/* 263 */     if (denom != null) {
/* 264 */       this.dDeg = denom.getDeg();
/* 265 */       if (this.dDeg > 0) {
/* 266 */         this.denomPoly = ComplexPoly.copy(denom);
/* 267 */         this.denomRoots = Complex.oneDarray(this.dDeg);
/* 268 */         if (!this.noZeros) {
/* 269 */           this.mDeg = Math.max(this.nDeg, this.dDeg);
/*     */         }
/*     */         else {
/* 272 */           this.mDeg = this.dDeg;
/*     */         }
/* 274 */         this.noPoles = false;
/*     */       }
/*     */     }
/*     */     else {
/* 278 */       this.noPoles = true;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setDenominator(Complex[] denom)
/*     */   {
/* 286 */     if (denom != null) {
/* 287 */       this.dDeg = (denom.length - 1);
/* 288 */       if (this.dDeg > 0) {
/* 289 */         this.denomPoly = new ComplexPoly(denom);
/* 290 */         this.denomRoots = Complex.oneDarray(this.dDeg);
/* 291 */         if (!this.noZeros) {
/* 292 */           this.mDeg = Math.max(this.nDeg, this.dDeg);
/*     */         }
/*     */         else {
/* 295 */           this.mDeg = this.dDeg;
/*     */         }
/* 297 */         this.noPoles = false;
/*     */       }
/*     */     }
/*     */     else {
/* 301 */       this.noPoles = true;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setDenominator(double[] denom)
/*     */   {
/* 309 */     if (denom != null) {
/* 310 */       this.dDeg = (denom.length - 1);
/* 311 */       if (this.dDeg > 0) {
/* 312 */         this.denomPoly = new ComplexPoly(denom);
/* 313 */         this.denomRoots = Complex.oneDarray(this.dDeg);
/* 314 */         if (!this.noZeros) {
/* 315 */           this.mDeg = Math.max(this.nDeg, this.dDeg);
/*     */         }
/*     */         else {
/* 318 */           this.mDeg = this.dDeg;
/*     */         }
/* 320 */         this.noPoles = false;
/*     */       }
/*     */     }
/*     */     else {
/* 324 */       this.noPoles = true;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setPoles(Complex[] poles)
/*     */   {
/* 330 */     if (poles != null) {
/* 331 */       this.dDeg = poles.length;
/* 332 */       if (this.dDeg > 0) {
/* 333 */         this.denomRoots = poles;
/* 334 */         this.denomPoly = ComplexPoly.rootsToPoly(poles);
/* 335 */         if (!this.noZeros) {
/* 336 */           this.mDeg = Math.max(this.nDeg, this.dDeg);
/*     */         }
/*     */         else {
/* 339 */           this.mDeg = this.dDeg;
/*     */         }
/* 341 */         this.noPoles = false;
/*     */       }
/* 343 */       this.polesSet = true;
/*     */     }
/*     */     else {
/* 346 */       this.noPoles = true;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setPoles(double[] poles)
/*     */   {
/* 352 */     int n = poles.length;
/* 353 */     Complex[] cpoles = Complex.oneDarray(n);
/* 354 */     for (int i = 0; i < n; i++) cpoles[i] = new Complex(poles[i], 0.0D);
/* 355 */     setPoles(cpoles);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setScaleFactor(double scale)
/*     */   {
/* 361 */     this.scaleFactor = scale;
/*     */   }
/*     */   
/*     */   public void setS()
/*     */   {
/* 366 */     this.sORz = 1;
/*     */   }
/*     */   
/*     */   public void setZ()
/*     */   {
/* 371 */     this.sORz = 2;
/* 372 */     this.zCircle = true;
/*     */   }
/*     */   
/*     */   public void setUnitAxes()
/*     */   {
/* 377 */     this.setUnitAxes = true;
/* 378 */     this.setEqualAxes = false;
/*     */   }
/*     */   
/*     */   public void setEqualAxes()
/*     */   {
/* 383 */     this.setEqualAxes = true;
/* 384 */     this.setUnitAxes = false;
/*     */   }
/*     */   
/*     */   public void setCircle()
/*     */   {
/* 389 */     this.zCircle = true;
/* 390 */     if (this.sORz != 2) this.sORz = 2;
/*     */   }
/*     */   
/*     */   public void unsetCircle()
/*     */   {
/* 395 */     this.zCircle = false;
/*     */   }
/*     */   
/*     */ 
/*     */   public Complex[][] pzPlot(String title)
/*     */   {
/* 401 */     if ((this.noPoles) && (this.noZeros)) { throw new IllegalArgumentException("No poles or zeros have been entered");
/*     */     }
/* 403 */     double absReal = 0.0D;
/* 404 */     double absImag = 0.0D;
/* 405 */     double zeroLimit = 1.0E-5D;
/* 406 */     double minall = 0.0D;
/* 407 */     double maxall = 0.0D;
/* 408 */     int ncirc = 600;
/* 409 */     double stp = 2.0D / (ncirc - 1);
/* 410 */     int maxPoints = 0;
/* 411 */     double[] zerosReal = null;
/* 412 */     double[] zerosImag = null;
/* 413 */     double[] polesReal = null;
/* 414 */     double[] polesImag = null;
/* 415 */     double[] xAxisIfRealZero = null;
/* 416 */     double[] yAxisIfRealZero = null;
/* 417 */     double[] xAxisIfImagZero = null;
/* 418 */     double[] yAxisIfImagZero = null;
/* 419 */     double[] xAxisCircle1 = new double[ncirc];
/* 420 */     double[] yAxisCircle1 = new double[ncirc];
/* 421 */     double[] xAxisCircle2 = new double[ncirc];
/* 422 */     double[] yAxisCircle2 = new double[ncirc];
/*     */     
/* 424 */     Complex[][] zerosAndPoles = { null, null };
/*     */     
/* 426 */     int mm = 0;
/* 427 */     if (this.nDeg > 0) {
/* 428 */       mm++;
/* 429 */       zerosReal = new double[this.nDeg];
/* 430 */       zerosImag = new double[this.nDeg];
/* 431 */       if (!this.zerosSet) this.numerRoots = this.numerPoly.roots();
/* 432 */       zerosAndPoles[0] = this.numerRoots;
/* 433 */       for (int i = 0; i < this.nDeg; i++) {
/* 434 */         zerosReal[i] = this.numerRoots[i].getReal();
/* 435 */         zerosImag[i] = this.numerRoots[i].getImag();
/* 436 */         if (!this.numerRoots[i].isZero()) {
/* 437 */           absReal = Math.abs(zerosReal[i]);
/* 438 */           absImag = Math.abs(zerosImag[i]);
/* 439 */           if (absReal > absImag) {
/* 440 */             if (absImag < zeroLimit * absReal) { zerosImag[i] = 0.0D;
/*     */             }
/*     */           }
/* 443 */           else if (absReal < zeroLimit * absImag) { zerosReal[i] = 0.0D;
/*     */           }
/*     */         }
/* 446 */         if (zerosReal[i] != 0.0D) this.noReal = false;
/* 447 */         if (zerosImag[i] != 0.0D) this.noImag = false;
/*     */       }
/* 449 */       maxPoints = this.nDeg;
/*     */     }
/*     */     
/* 452 */     if (this.dDeg > 0) {
/* 453 */       mm++;
/* 454 */       polesReal = new double[this.dDeg];
/* 455 */       polesImag = new double[this.dDeg];
/* 456 */       if (!this.polesSet) this.denomRoots = this.denomPoly.roots();
/* 457 */       zerosAndPoles[1] = this.denomRoots;
/* 458 */       for (int i = 0; i < this.dDeg; i++) {
/* 459 */         polesReal[i] = this.denomRoots[i].getReal();
/* 460 */         polesImag[i] = this.denomRoots[i].getImag();
/* 461 */         if (!this.denomRoots[i].isZero()) {
/* 462 */           absReal = Math.abs(polesReal[i]);
/* 463 */           absImag = Math.abs(polesImag[i]);
/* 464 */           if (absReal > absImag) {
/* 465 */             if (absImag < zeroLimit * absReal) { polesImag[i] = 0.0D;
/*     */             }
/*     */           }
/* 468 */           else if (absReal < zeroLimit * absImag) { polesReal[i] = 0.0D;
/*     */           }
/*     */         }
/* 471 */         if (polesReal[i] != 0.0D) this.noReal = false;
/* 472 */         if (polesImag[i] != 0.0D) this.noImag = false;
/*     */       }
/* 474 */       if (this.dDeg > maxPoints) { maxPoints = this.dDeg;
/*     */       }
/*     */     }
/* 477 */     if (this.noReal) {
/* 478 */       mm++;
/* 479 */       xAxisIfRealZero = new double[2];
/* 480 */       xAxisIfRealZero[0] = 1.0D;
/* 481 */       xAxisIfRealZero[1] = -1.0D;
/* 482 */       yAxisIfRealZero = new double[2];
/* 483 */       yAxisIfRealZero[0] = 0.0D;
/* 484 */       yAxisIfRealZero[1] = 0.0D;
/* 485 */       if (2 > maxPoints) { maxPoints = 2;
/*     */       }
/*     */     }
/* 488 */     if (this.noImag) {
/* 489 */       mm++;
/* 490 */       xAxisIfImagZero = new double[2];
/* 491 */       xAxisIfImagZero[0] = 0.0D;
/* 492 */       xAxisIfImagZero[1] = 0.0D;
/* 493 */       yAxisIfImagZero = new double[2];
/* 494 */       yAxisIfImagZero[0] = 1.0D;
/* 495 */       yAxisIfImagZero[1] = -1.0D;
/* 496 */       if (2 > maxPoints) { maxPoints = 2;
/*     */       }
/*     */     }
/* 499 */     if (this.zCircle) {
/* 500 */       mm += 2;
/* 501 */       xAxisCircle1[0] = -1.0D;
/* 502 */       yAxisCircle1[0] = 0.0D;
/* 503 */       xAxisCircle2[0] = -1.0D;
/* 504 */       yAxisCircle2[0] = 0.0D;
/* 505 */       for (int i = 1; i < ncirc; i++) {
/* 506 */         xAxisCircle1[i] = (xAxisCircle1[(i - 1)] + stp);
/* 507 */         yAxisCircle1[i] = Math.sqrt(1.0D - xAxisCircle1[i] * xAxisCircle1[i]);
/* 508 */         xAxisCircle2[i] = (xAxisCircle2[(i - 1)] + stp);
/* 509 */         yAxisCircle2[i] = (-yAxisCircle1[i]);
/*     */       }
/* 511 */       if (ncirc > maxPoints) { maxPoints = ncirc;
/*     */       }
/*     */     }
/* 514 */     if (this.setEqualAxes) {
/* 515 */       mm++;
/* 516 */       double maxpr = Fmath.maximum(polesReal);
/* 517 */       double maxzr = Fmath.maximum(zerosReal);
/* 518 */       double maxr = Math.max(maxpr, maxzr);
/* 519 */       double maxpi = Fmath.maximum(polesImag);
/* 520 */       double maxzi = Fmath.maximum(zerosImag);
/* 521 */       double maxi = Math.max(maxpi, maxzi);
/* 522 */       maxall = Math.max(maxr, maxi);
/*     */       
/* 524 */       double minpr = Fmath.minimum(polesReal);
/* 525 */       double minzr = Fmath.minimum(zerosReal);
/* 526 */       double minr = Math.min(minpr, minzr);
/* 527 */       double minpi = Fmath.minimum(polesImag);
/* 528 */       double minzi = Fmath.minimum(zerosImag);
/* 529 */       double mini = Math.min(minpi, minzi);
/* 530 */       minall = Math.min(minr, mini);
/*     */     }
/*     */     
/* 533 */     int ii = 0;
/*     */     
/*     */ 
/* 536 */     double[][] data = PlotGraph.data(mm, maxPoints);
/* 537 */     boolean[] trim = new boolean[mm];
/* 538 */     boolean[] minmax = new boolean[mm];
/* 539 */     int[] line = new int[mm];
/* 540 */     int[] point = new int[mm];
/*     */     
/*     */ 
/* 543 */     ii = 0;
/* 544 */     if (this.nDeg > 0) {
/* 545 */       line[ii] = 0;
/* 546 */       point[ii] = 1;
/* 547 */       trim[ii] = false;
/* 548 */       minmax[ii] = true;
/* 549 */       for (int i = 0; i < this.nDeg; i++) {
/* 550 */         data[(2 * ii)][i] = zerosReal[i];
/* 551 */         data[(2 * ii + 1)][i] = zerosImag[i];
/*     */       }
/* 553 */       ii++;
/*     */     }
/* 555 */     if (this.dDeg > 0) {
/* 556 */       line[ii] = 0;
/* 557 */       point[ii] = 7;
/* 558 */       trim[ii] = false;
/* 559 */       minmax[ii] = true;
/* 560 */       for (int i = 0; i < this.dDeg; i++) {
/* 561 */         data[(2 * ii)][i] = polesReal[i];
/* 562 */         data[(2 * ii + 1)][i] = polesImag[i];
/*     */       }
/* 564 */       ii++;
/*     */     }
/* 566 */     if (this.zCircle) {
/* 567 */       line[ii] = 3;
/* 568 */       point[ii] = 0;
/* 569 */       trim[ii] = true;
/* 570 */       minmax[ii] = false;
/* 571 */       if (this.setUnitAxes) minmax[ii] = true;
/* 572 */       for (int i = 0; i < ncirc; i++) {
/* 573 */         data[(2 * ii)][i] = xAxisCircle1[i];
/* 574 */         data[(2 * ii + 1)][i] = yAxisCircle1[i];
/*     */       }
/*     */       
/* 577 */       ii++;
/* 578 */       line[ii] = 3;
/* 579 */       point[ii] = 0;
/* 580 */       trim[ii] = true;
/* 581 */       minmax[ii] = false;
/* 582 */       if (this.setUnitAxes) minmax[ii] = true;
/* 583 */       for (int i = 0; i < ncirc; i++) {
/* 584 */         data[(2 * ii)][i] = xAxisCircle2[i];
/* 585 */         data[(2 * ii + 1)][i] = yAxisCircle2[i];
/*     */       }
/* 587 */       ii++;
/*     */     }
/* 589 */     if (this.noReal) {
/* 590 */       line[ii] = 0;
/* 591 */       point[ii] = 0;
/* 592 */       trim[ii] = false;
/* 593 */       minmax[ii] = true;
/* 594 */       for (int i = 0; i < 2; i++) {
/* 595 */         data[(2 * ii)][i] = xAxisIfRealZero[i];
/* 596 */         data[(2 * ii + 1)][i] = yAxisIfRealZero[i];
/*     */       }
/* 598 */       ii++;
/*     */     }
/* 600 */     if (this.noImag) {
/* 601 */       line[ii] = 0;
/* 602 */       point[ii] = 0;
/* 603 */       trim[ii] = false;
/* 604 */       minmax[ii] = true;
/*     */       
/* 606 */       for (int i = 0; i < 2; i++) {
/* 607 */         data[(2 * ii)][i] = xAxisIfImagZero[i];
/* 608 */         data[(2 * ii + 1)][i] = yAxisIfImagZero[i];
/*     */       }
/* 610 */       ii++;
/*     */     }
/* 612 */     if (this.setEqualAxes) {
/* 613 */       line[ii] = 0;
/* 614 */       point[ii] = 0;
/* 615 */       trim[ii] = false;
/* 616 */       minmax[ii] = true;
/*     */       
/* 618 */       data[(2 * ii)][0] = minall;
/* 619 */       data[(2 * ii + 1)][0] = minall;
/* 620 */       data[(2 * ii)][1] = maxall;
/* 621 */       data[(2 * ii + 1)][1] = maxall;
/* 622 */       ii++;
/*     */     }
/*     */     
/*     */ 
/* 626 */     PlotGraph pg = new PlotGraph(data);
/* 627 */     pg.setLine(line);
/* 628 */     pg.setPoint(point);
/* 629 */     pg.setTrimOpt(trim);
/* 630 */     pg.setMinMaxOpt(minmax);
/* 631 */     pg.setXlowFac(0.0D);
/* 632 */     pg.setYlowFac(0.0D);
/* 633 */     pg.setGraphWidth((int)(this.scaleFactor * 760.0D));
/* 634 */     pg.setGraphHeight((int)(this.scaleFactor * 700.0D));
/* 635 */     pg.setXaxisLen((int)(this.scaleFactor * 560.0D));
/* 636 */     pg.setYaxisLen((int)(this.scaleFactor * 560.0D));
/* 637 */     pg.setYhigh((int)(this.scaleFactor * 80.0D));
/* 638 */     pg.setNoOffset(true);
/*     */     
/* 640 */     switch (this.sORz) {
/*     */     case 0: 
/* 642 */       pg.setGraphTitle("Pole Zero Plot: " + title);
/* 643 */       pg.setXaxisLegend("Real part of s or z");
/* 644 */       pg.setYaxisLegend("Imaginary part of s or z");
/* 645 */       break;
/*     */     case 1: 
/* 647 */       pg.setGraphTitle("Pole Zero Plot (s-plane): " + title);
/* 648 */       pg.setXaxisLegend("Real part of s");
/* 649 */       pg.setYaxisLegend("Imaginary part of s");
/* 650 */       break;
/*     */     case 2: 
/* 652 */       pg.setGraphTitle("Pole Zero Plot (z-plane): " + title);
/* 653 */       pg.setXaxisLegend("Real part of z");
/* 654 */       pg.setYaxisLegend("Imaginary part of z");
/*     */     }
/*     */     
/*     */     
/*     */ 
/* 659 */     pg.plot();
/*     */     
/*     */ 
/*     */ 
/* 663 */     Complex[] numval = null;
/* 664 */     Complex[] denval = null;
/*     */     
/* 666 */     FileOutput fout = new FileOutput("PoleZeroOutput.txt");
/*     */     
/* 668 */     fout.println("Output File for Program PlotPoleZero");
/* 669 */     if (this.sORz == 1) fout.println("An s-plane plot");
/* 670 */     if (this.sORz == 2) fout.println("A z-plane plot");
/* 671 */     fout.dateAndTimeln(title);
/* 672 */     fout.println();
/*     */     
/* 674 */     if (!this.noZeros) {
/* 675 */       numval = this.numerPoly.polyNomCopy();
/* 676 */       fout.println("Numerator polynomial coefficients");
/* 677 */       for (int i = 0; i <= this.nDeg; i++) {
/* 678 */         fout.print(numval[i].toString());
/* 679 */         if (i < this.nDeg) {
/* 680 */           fout.printcomma();
/* 681 */           fout.printsp();
/*     */         }
/*     */       }
/* 684 */       fout.println();
/* 685 */       fout.println();
/*     */     }
/*     */     
/* 688 */     if (!this.noPoles) {
/* 689 */       denval = this.denomPoly.polyNomCopy();
/* 690 */       fout.println("Denominator polynomial coefficients");
/* 691 */       for (int i = 0; i <= this.dDeg; i++) {
/* 692 */         fout.print(denval[i].toString());
/* 693 */         if (i < this.dDeg) {
/* 694 */           fout.printcomma();
/* 695 */           fout.printsp();
/*     */         }
/*     */       }
/* 698 */       fout.println();
/* 699 */       fout.println();
/*     */     }
/*     */     
/* 702 */     fout.println("Numerator roots (zeros)");
/* 703 */     if (this.nDeg < 1) {
/* 704 */       fout.println("No zeros");
/*     */     }
/*     */     else {
/* 707 */       for (int i = 0; i < this.nDeg; i++) {
/* 708 */         fout.print(Complex.truncate(this.numerRoots[i], 6));
/* 709 */         if (i < this.nDeg - 1) {
/* 710 */           fout.printcomma();
/* 711 */           fout.printsp();
/*     */         }
/*     */       }
/* 714 */       fout.println();
/* 715 */       fout.println();
/*     */     }
/*     */     
/* 718 */     fout.println("Denominator roots (poles)");
/* 719 */     if (this.dDeg < 1) {
/* 720 */       fout.println("No poles");
/*     */     }
/*     */     else {
/* 723 */       for (int i = 0; i < this.dDeg; i++) {
/* 724 */         fout.print(Complex.truncate(this.denomRoots[i], 6));
/* 725 */         if (i < this.dDeg - 1) {
/* 726 */           fout.printcomma();
/* 727 */           fout.printsp();
/*     */         }
/*     */       }
/* 730 */       fout.println();
/* 731 */       fout.println();
/*     */     }
/*     */     
/* 734 */     if (this.sORz == 2) {
/* 735 */       fout.println("Denominator pole radial distances on the z-plane");
/* 736 */       if (this.dDeg < 1) {
/* 737 */         fout.println("No poles");
/*     */       }
/*     */       else {
/* 740 */         for (int i = 0; i < this.dDeg; i++) {
/* 741 */           fout.print(Fmath.truncate(this.denomRoots[i].abs(), 6));
/* 742 */           if (i < this.dDeg - 1) {
/* 743 */             fout.printcomma();
/* 744 */             fout.printsp();
/*     */           }
/*     */         }
/*     */       }
/* 748 */       fout.println();
/* 749 */       fout.println();
/*     */     }
/*     */     
/* 752 */     boolean testroots = true;
/* 753 */     if (this.sORz == 1) {
/* 754 */       for (int i = 0; i < this.dDeg; i++) {
/* 755 */         if (this.denomRoots[i].getReal() > 0.0D) testroots = false;
/*     */       }
/* 757 */       if (testroots) {
/* 758 */         fout.println("All pole real parts are less than or equal to zero - stable system");
/*     */       }
/*     */       else {
/* 761 */         fout.println("At least one pole real part is greater than zero - unstable system");
/*     */       }
/*     */     }
/*     */     
/* 765 */     if (this.sORz == 2) {
/* 766 */       for (int i = 0; i < this.dDeg; i++) {
/* 767 */         if (Fmath.truncate(this.denomRoots[i].abs(), 6) > 1.0D) testroots = false;
/*     */       }
/* 769 */       if (testroots) {
/* 770 */         fout.println("All pole distances from the z-plane zero are less than or equal to one - stable system");
/*     */       }
/*     */       else {
/* 773 */         fout.println("At least one pole distance from the z-plane zero is greater than one - unstable system");
/*     */       }
/*     */     }
/*     */     
/* 777 */     fout.println();
/* 778 */     fout.println("End of file");
/* 779 */     fout.close();
/*     */     
/* 781 */     return zerosAndPoles;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Complex[][] pzPlot()
/*     */   {
/* 788 */     String title = "no file title provided";
/* 789 */     return pzPlot(title);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/plot/PlotPoleZero.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */