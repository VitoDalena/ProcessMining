/*     */ package flanagan.complex;
/*     */ 
/*     */ import flanagan.analysis.ErrorProp;
/*     */ import flanagan.math.Fmath;
/*     */ import flanagan.math.PsRandom;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ComplexErrorProp
/*     */ {
/*  40 */   private ErrorProp eReal = new ErrorProp();
/*  41 */   private ErrorProp eImag = new ErrorProp();
/*  42 */   private double corrCoeff = 0.0D;
/*  43 */   private static int monteCarloLength = 10000;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ComplexErrorProp()
/*     */   {
/*  52 */     this.eReal.reset(0.0D, 0.0D);
/*  53 */     this.eImag.reset(0.0D, 0.0D);
/*  54 */     this.corrCoeff = 0.0D;
/*     */   }
/*     */   
/*     */ 
/*     */   public ComplexErrorProp(ErrorProp eReal, ErrorProp eImag)
/*     */   {
/*  60 */     this.eReal = eReal.copy();
/*  61 */     this.eImag = eImag.copy();
/*  62 */     this.corrCoeff = 0.0D;
/*     */   }
/*     */   
/*     */   public ComplexErrorProp(ErrorProp eReal, ErrorProp eImag, double corrCoeff)
/*     */   {
/*  67 */     this.eReal = eReal.copy();
/*  68 */     this.eImag = eImag.copy();
/*  69 */     this.corrCoeff = corrCoeff;
/*     */   }
/*     */   
/*     */ 
/*     */   public ComplexErrorProp(double eRealValue, double eRealError, double eImagValue, double eImagError)
/*     */   {
/*  75 */     this.eReal.reset(eRealValue, eRealError);
/*  76 */     this.eImag.reset(eImagValue, eImagError);
/*  77 */     this.corrCoeff = 0.0D;
/*     */   }
/*     */   
/*     */ 
/*     */   public ComplexErrorProp(double eRealValue, double eRealError, double eImagValue, double eImagError, double corrCoeff)
/*     */   {
/*  83 */     this.eReal.reset(eRealValue, eRealError);
/*  84 */     this.eImag.reset(eImagValue, eImagError);
/*  85 */     this.corrCoeff = corrCoeff;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reset(ErrorProp eReal, ErrorProp eImag)
/*     */   {
/*  95 */     this.eReal = eReal.copy();
/*  96 */     this.eImag = eImag.copy();
/*  97 */     this.corrCoeff = 0.0D;
/*     */   }
/*     */   
/*     */   public void reset(ErrorProp eReal, ErrorProp eImag, double corrCoeff)
/*     */   {
/* 102 */     this.eReal = eReal.copy();
/* 103 */     this.eImag = eImag.copy();
/* 104 */     this.corrCoeff = corrCoeff;
/*     */   }
/*     */   
/*     */   public void reset(double eRealValue, double eRealError, double eImagValue, double eImagError)
/*     */   {
/* 109 */     this.eReal.setValue(eRealValue);
/* 110 */     this.eReal.setError(eRealError);
/* 111 */     this.eImag.setValue(eImagValue);
/* 112 */     this.eImag.setError(eImagError);
/* 113 */     this.corrCoeff = 0.0D;
/*     */   }
/*     */   
/*     */ 
/*     */   public void reset(double eRealValue, double eRealError, double eImagValue, double eImagError, double corrCoeff)
/*     */   {
/* 119 */     this.eReal.setValue(eRealValue);
/* 120 */     this.eReal.setError(eRealError);
/* 121 */     this.eImag.setValue(eImagValue);
/* 122 */     this.eImag.setError(eImagError);
/* 123 */     this.corrCoeff = corrCoeff;
/*     */   }
/*     */   
/*     */   public void polar(ErrorProp eMag, ErrorProp ePhase)
/*     */   {
/* 128 */     polar(eMag, ePhase, 0.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void polar(ErrorProp eMag, ErrorProp ePhase, double corrCoeff)
/*     */   {
/* 135 */     ErrorProp a = new ErrorProp();
/* 136 */     a = eMag.times(ErrorProp.cos(ePhase), corrCoeff);
/* 137 */     this.eReal = a;
/* 138 */     a = eMag.times(ErrorProp.sin(ePhase), corrCoeff);
/* 139 */     this.eImag = a;
/*     */     
/*     */ 
/* 142 */     PsRandom rr = new PsRandom();
/* 143 */     double[][] ran = rr.correlatedGaussianArrays(eMag.getValue(), ePhase.getValue(), eMag.getError(), ePhase.getError(), corrCoeff, monteCarloLength);
/*     */     
/* 145 */     double[] rV = new double[monteCarloLength];
/* 146 */     double[] iV = new double[monteCarloLength];
/* 147 */     for (int i = 0; i < monteCarloLength; i++) {
/* 148 */       rV[i] = (ran[0][i] * Math.cos(ran[1][i]));
/* 149 */       iV[i] = (ran[0][i] * Math.sin(ran[1][i]));
/*     */     }
/*     */     
/* 152 */     this.corrCoeff = calcRho(rV, iV);
/*     */   }
/*     */   
/*     */   public static double calcRho(double[] x, double[] y)
/*     */   {
/* 157 */     int n = x.length;
/* 158 */     if (n != y.length) { throw new IllegalArgumentException("length of x and y must be the same");
/*     */     }
/* 160 */     double meanX = 0.0D;
/* 161 */     double meanY = 0.0D;
/* 162 */     for (int i = 0; i < n; i++) {
/* 163 */       meanX += x[i];
/* 164 */       meanY += y[i];
/*     */     }
/* 166 */     meanX /= n;
/* 167 */     meanY /= n;
/* 168 */     double varX = 0.0D;
/* 169 */     double varY = 0.0D;
/* 170 */     double covarXY = 0.0D;
/* 171 */     for (int i = 0; i < n; i++) {
/* 172 */       varX += Fmath.square(x[i] - meanX);
/* 173 */       varY += Fmath.square(y[i] - meanY);
/* 174 */       covarXY += (x[i] - meanX) * (y[i] - meanY);
/*     */     }
/* 176 */     varX = Math.sqrt(varX / (n - 1));
/* 177 */     varY = Math.sqrt(varY / (n - 1));
/* 178 */     covarXY /= (n - 1);
/*     */     
/* 180 */     return covarXY / (varX * varY);
/*     */   }
/*     */   
/*     */   public void polar(double eMagValue, double eMagError, double ePhaseValue, double ePhaseError)
/*     */   {
/* 185 */     ErrorProp eMag = new ErrorProp(eMagValue, eMagError);
/* 186 */     ErrorProp ePhase = new ErrorProp(ePhaseValue, ePhaseError);
/* 187 */     polar(eMag, ePhase, 0.0D);
/*     */   }
/*     */   
/*     */   public void polar(double eMagValue, double eMagError, double ePhaseValue, double ePhaseError, double corrCoeff)
/*     */   {
/* 192 */     ErrorProp eMag = new ErrorProp(eMagValue, eMagError);
/* 193 */     ErrorProp ePhase = new ErrorProp(ePhaseValue, ePhaseError);
/* 194 */     polar(eMag, ePhase, corrCoeff);
/*     */   }
/*     */   
/*     */   public void setReal(ErrorProp eReal)
/*     */   {
/* 199 */     this.eReal = eReal.copy();
/*     */   }
/*     */   
/*     */   public void setReal(double eRealValue, double eRealError)
/*     */   {
/* 204 */     this.eReal.setValue(eRealValue);
/* 205 */     this.eReal.setError(eRealError);
/*     */   }
/*     */   
/*     */   public void setImag(ErrorProp eImag)
/*     */   {
/* 210 */     this.eImag = eImag.copy();
/*     */   }
/*     */   
/*     */   public void setImag(double eImagValue, double eImagError)
/*     */   {
/* 215 */     this.eImag.setValue(eImagValue);
/* 216 */     this.eImag.setError(eImagError);
/*     */   }
/*     */   
/*     */   public void setDouble(double errorFree)
/*     */   {
/* 221 */     this.eReal.reset(errorFree, 0.0D);
/* 222 */     this.eImag.reset(0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public void setCorrCoeff(double corrCoeff)
/*     */   {
/* 227 */     this.corrCoeff = corrCoeff;
/*     */   }
/*     */   
/*     */   public static void setMonteCarloLength(int length)
/*     */   {
/* 232 */     monteCarloLength = length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ErrorProp getReal()
/*     */   {
/* 239 */     return this.eReal.copy();
/*     */   }
/*     */   
/*     */   public double getRealValue()
/*     */   {
/* 244 */     return this.eReal.getValue();
/*     */   }
/*     */   
/*     */   public double getRealError()
/*     */   {
/* 249 */     return this.eReal.getError();
/*     */   }
/*     */   
/*     */   public ErrorProp getImag()
/*     */   {
/* 254 */     return this.eImag.copy();
/*     */   }
/*     */   
/*     */   public double getImagValue()
/*     */   {
/* 259 */     return this.eImag.getValue();
/*     */   }
/*     */   
/*     */   public double getImagError()
/*     */   {
/* 264 */     return this.eImag.getError();
/*     */   }
/*     */   
/*     */   public double getCorrCoeff()
/*     */   {
/* 269 */     return this.corrCoeff;
/*     */   }
/*     */   
/*     */   public static int getMonteCarloLength()
/*     */   {
/* 274 */     return monteCarloLength;
/*     */   }
/*     */   
/*     */ 
/*     */   public static ComplexErrorProp copy(ComplexErrorProp a)
/*     */   {
/* 280 */     if (a == null) {
/* 281 */       return null;
/*     */     }
/*     */     
/* 284 */     ComplexErrorProp b = new ComplexErrorProp();
/* 285 */     b.eReal = a.eReal.copy();
/* 286 */     b.eImag = a.eImag.copy();
/* 287 */     return b;
/*     */   }
/*     */   
/*     */ 
/*     */   public ComplexErrorProp copy()
/*     */   {
/* 293 */     if (this == null) {
/* 294 */       return null;
/*     */     }
/*     */     
/* 297 */     ComplexErrorProp b = new ComplexErrorProp();
/* 298 */     b.eReal = this.eReal.copy();
/* 299 */     b.eImag = this.eImag.copy();
/* 300 */     return b;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 307 */     if (this == null) {
/* 308 */       return null;
/*     */     }
/*     */     
/* 311 */     ComplexErrorProp b = new ComplexErrorProp();
/* 312 */     b.eReal = this.eReal.copy();
/* 313 */     b.eImag = this.eImag.copy();
/* 314 */     return b;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static ComplexErrorProp plus(ComplexErrorProp a, ComplexErrorProp b)
/*     */   {
/* 321 */     ComplexErrorProp c = new ComplexErrorProp();
/* 322 */     c.eReal = a.eReal.plus(b.eReal);
/* 323 */     c.eImag = a.eImag.plus(b.eImag);
/* 324 */     return c;
/*     */   }
/*     */   
/*     */ 
/*     */   public ComplexErrorProp plus(ComplexErrorProp a)
/*     */   {
/* 330 */     ComplexErrorProp b = new ComplexErrorProp();
/* 331 */     b.eReal = this.eReal.plus(a.eReal);
/* 332 */     b.eImag = this.eImag.plus(a.eImag);
/* 333 */     return b;
/*     */   }
/*     */   
/*     */ 
/*     */   public static ComplexErrorProp minus(ComplexErrorProp a, ComplexErrorProp b)
/*     */   {
/* 339 */     ComplexErrorProp c = new ComplexErrorProp();
/* 340 */     c.eReal = a.eReal.minus(b.eReal);
/* 341 */     c.eImag = a.eImag.minus(b.eImag);
/* 342 */     return c;
/*     */   }
/*     */   
/*     */ 
/*     */   public ComplexErrorProp minus(ComplexErrorProp a)
/*     */   {
/* 348 */     ComplexErrorProp b = new ComplexErrorProp();
/* 349 */     b.eReal = this.eReal.minus(a.eReal);
/* 350 */     b.eImag = this.eImag.minus(a.eImag);
/* 351 */     return b;
/*     */   }
/*     */   
/*     */ 
/*     */   public static ComplexErrorProp times(ComplexErrorProp a, ComplexErrorProp b)
/*     */   {
/* 357 */     ComplexErrorProp c = new ComplexErrorProp();
/* 358 */     c.eReal = a.eReal.times(b.eReal).minus(a.eImag.times(b.eImag));
/* 359 */     c.eImag = a.eReal.times(b.eImag).plus(a.eImag.times(b.eReal));
/* 360 */     return c;
/*     */   }
/*     */   
/*     */   public ComplexErrorProp times(ComplexErrorProp b)
/*     */   {
/* 365 */     ComplexErrorProp c = new ComplexErrorProp();
/* 366 */     c.eReal = this.eReal.times(b.eReal).minus(this.eImag.times(b.eImag));
/* 367 */     c.eImag = this.eReal.times(b.eImag).plus(this.eImag.times(b.eReal));
/* 368 */     return c;
/*     */   }
/*     */   
/*     */   public void timesEquals(ComplexErrorProp a)
/*     */   {
/* 373 */     ComplexErrorProp b = new ComplexErrorProp();
/* 374 */     b.eReal = a.eReal.times(this.eReal).minus(a.eImag.times(this.eImag));
/* 375 */     b.eImag = a.eReal.times(this.eImag).plus(a.eImag.times(this.eReal));
/* 376 */     this.eReal = b.eReal.copy();
/* 377 */     this.eImag = b.eImag.copy();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static ComplexErrorProp over(ComplexErrorProp a, ComplexErrorProp b)
/*     */   {
/* 384 */     ComplexErrorProp c = new ComplexErrorProp();
/* 385 */     PsRandom ran = new PsRandom();
/* 386 */     double[] aReal = ran.gaussianArray(a.eReal.getValue(), a.eReal.getError(), monteCarloLength);
/* 387 */     double[] aImag = ran.gaussianArray(a.eImag.getValue(), a.eImag.getError(), monteCarloLength);
/* 388 */     double[] bReal = ran.gaussianArray(b.eReal.getValue(), b.eReal.getError(), monteCarloLength);
/* 389 */     double[] bImag = ran.gaussianArray(b.eImag.getValue(), b.eImag.getError(), monteCarloLength);
/* 390 */     double[] rat = new double[monteCarloLength];
/* 391 */     double[] denm = new double[monteCarloLength];
/* 392 */     double[] cReal = new double[monteCarloLength];
/* 393 */     double[] cImag = new double[monteCarloLength];
/*     */     
/* 395 */     for (int i = 0; i < monteCarloLength; i++)
/*     */     {
/* 397 */       if (Math.abs(bReal[i]) >= Math.abs(bImag[i])) {
/* 398 */         bImag[i] /= bReal[i];
/* 399 */         bReal[i] += bImag[i] * rat[i];
/* 400 */         cReal[i] = ((aReal[i] + aImag[i] * rat[i]) / denm[i]);
/* 401 */         cImag[i] = ((aImag[i] - aReal[i] * rat[i]) / denm[i]);
/*     */       }
/*     */       else {
/* 404 */         bReal[i] /= bImag[i];
/* 405 */         denm[i] = (bReal[i] * rat[i] + bImag[i]);
/* 406 */         cReal[i] = ((aReal[i] * rat[i] + aImag[i]) / denm[i]);
/* 407 */         cImag[i] = ((aImag[i] * rat[i] - aReal[i]) / denm[i]);
/*     */       }
/*     */     }
/* 410 */     double cRealSum = 0.0D;
/* 411 */     double cImagSum = 0.0D;
/* 412 */     double cRealErrorSum = 0.0D;
/* 413 */     double cImagErrorSum = 0.0D;
/* 414 */     for (int i = 0; i < monteCarloLength; i++) {
/* 415 */       cRealSum += cReal[i];
/* 416 */       cImagSum += cImag[i];
/*     */     }
/* 418 */     cRealSum /= monteCarloLength;
/* 419 */     cImagSum /= monteCarloLength;
/* 420 */     for (int i = 0; i < monteCarloLength; i++) {
/* 421 */       cRealErrorSum += Fmath.square(cRealSum - cReal[i]);
/* 422 */       cImagErrorSum += Fmath.square(cImagSum - cImag[i]);
/*     */     }
/* 424 */     cRealErrorSum = Math.sqrt(cRealErrorSum / (monteCarloLength - 1));
/* 425 */     cImagErrorSum = Math.sqrt(cImagErrorSum / (monteCarloLength - 1));
/* 426 */     c.eReal.setError(cRealErrorSum);
/* 427 */     c.eImag.setError(cImagErrorSum);
/*     */     
/* 429 */     double denom = 0.0D;
/* 430 */     double ratio = 0.0D;
/* 431 */     if (Math.abs(b.eReal.getValue()) >= Math.abs(b.eImag.getValue())) {
/* 432 */       ratio = b.eImag.getValue() / b.eReal.getValue();
/* 433 */       denom = b.eReal.getValue() + b.eImag.getValue() * ratio;
/* 434 */       c.eReal.setValue((a.eReal.getValue() + a.eImag.getValue() * ratio) / denom);
/* 435 */       c.eImag.setValue((a.eImag.getValue() - a.eReal.getValue() * ratio) / denom);
/*     */     }
/*     */     else {
/* 438 */       ratio = b.eReal.getValue() / b.eImag.getValue();
/* 439 */       denom = b.eReal.getValue() * ratio + b.eImag.getValue();
/* 440 */       c.eReal.setValue((a.eReal.getValue() * ratio + a.eImag.getValue()) / denom);
/* 441 */       c.eImag.setValue((a.eImag.getValue() * ratio - a.eReal.getValue()) / denom);
/*     */     }
/* 443 */     return c;
/*     */   }
/*     */   
/*     */   public ComplexErrorProp over(ComplexErrorProp b)
/*     */   {
/* 448 */     ComplexErrorProp c = new ComplexErrorProp();
/* 449 */     PsRandom ran = new PsRandom();
/* 450 */     double[] aReal = ran.gaussianArray(this.eReal.getValue(), this.eReal.getError(), monteCarloLength);
/* 451 */     double[] aImag = ran.gaussianArray(this.eImag.getValue(), this.eImag.getError(), monteCarloLength);
/* 452 */     double[] bReal = ran.gaussianArray(b.eReal.getValue(), b.eReal.getError(), monteCarloLength);
/* 453 */     double[] bImag = ran.gaussianArray(b.eImag.getValue(), b.eImag.getError(), monteCarloLength);
/* 454 */     double[] rat = new double[monteCarloLength];
/* 455 */     double[] denm = new double[monteCarloLength];
/* 456 */     double[] cReal = new double[monteCarloLength];
/* 457 */     double[] cImag = new double[monteCarloLength];
/*     */     
/* 459 */     for (int i = 0; i < monteCarloLength; i++)
/*     */     {
/* 461 */       if (Math.abs(bReal[i]) >= Math.abs(bImag[i])) {
/* 462 */         bImag[i] /= bReal[i];
/* 463 */         bReal[i] += bImag[i] * rat[i];
/* 464 */         cReal[i] = ((aReal[i] + aImag[i] * rat[i]) / denm[i]);
/* 465 */         cImag[i] = ((aImag[i] - aReal[i] * rat[i]) / denm[i]);
/*     */       }
/*     */       else {
/* 468 */         bReal[i] /= bImag[i];
/* 469 */         denm[i] = (bReal[i] * rat[i] + bImag[i]);
/* 470 */         cReal[i] = ((aReal[i] * rat[i] + aImag[i]) / denm[i]);
/* 471 */         cImag[i] = ((aImag[i] * rat[i] - aReal[i]) / denm[i]);
/*     */       }
/*     */     }
/* 474 */     double cRealSum = 0.0D;
/* 475 */     double cImagSum = 0.0D;
/* 476 */     double cRealErrorSum = 0.0D;
/* 477 */     double cImagErrorSum = 0.0D;
/* 478 */     for (int i = 0; i < monteCarloLength; i++) {
/* 479 */       cRealSum += cReal[i];
/* 480 */       cImagSum += cImag[i];
/*     */     }
/* 482 */     cRealSum /= monteCarloLength;
/* 483 */     cImagSum /= monteCarloLength;
/* 484 */     for (int i = 0; i < monteCarloLength; i++) {
/* 485 */       cRealErrorSum += Fmath.square(cRealSum - cReal[i]);
/* 486 */       cImagErrorSum += Fmath.square(cImagSum - cImag[i]);
/*     */     }
/* 488 */     cRealErrorSum = Math.sqrt(cRealErrorSum / (monteCarloLength - 1));
/* 489 */     cImagErrorSum = Math.sqrt(cImagErrorSum / (monteCarloLength - 1));
/* 490 */     c.eReal.setError(cRealErrorSum);
/* 491 */     c.eImag.setError(cImagErrorSum);
/*     */     
/* 493 */     double denom = 0.0D;
/* 494 */     double ratio = 0.0D;
/* 495 */     if (Math.abs(b.eReal.getValue()) >= Math.abs(b.eImag.getValue())) {
/* 496 */       ratio = b.eImag.getValue() / b.eReal.getValue();
/* 497 */       denom = b.eReal.getValue() + b.eImag.getValue() * ratio;
/* 498 */       c.eReal.setValue((this.eReal.getValue() + this.eImag.getValue() * ratio) / denom);
/* 499 */       c.eImag.setValue((this.eImag.getValue() - this.eReal.getValue() * ratio) / denom);
/*     */     }
/*     */     else {
/* 502 */       ratio = b.eReal.getValue() / b.eImag.getValue();
/* 503 */       denom = b.eReal.getValue() * ratio + b.eImag.getValue();
/* 504 */       c.eReal.setValue((this.eReal.getValue() * ratio + this.eImag.getValue()) / denom);
/* 505 */       c.eImag.setValue((this.eImag.getValue() * ratio - this.eReal.getValue()) / denom);
/*     */     }
/* 507 */     return c;
/*     */   }
/*     */   
/*     */   public static ComplexErrorProp exp(ComplexErrorProp aa)
/*     */   {
/* 512 */     ComplexErrorProp bb = new ComplexErrorProp();
/* 513 */     ErrorProp pre = ErrorProp.exp(aa.eReal);
/* 514 */     bb.eReal = pre.times(ErrorProp.cos(aa.eImag), aa.corrCoeff);
/* 515 */     bb.eImag = pre.times(ErrorProp.sin(aa.eImag), aa.corrCoeff);
/* 516 */     return bb;
/*     */   }
/*     */   
/*     */   public ComplexErrorProp exp()
/*     */   {
/* 521 */     ComplexErrorProp bb = new ComplexErrorProp();
/* 522 */     ErrorProp pre = ErrorProp.exp(this.eReal);
/* 523 */     bb.eReal = pre.times(ErrorProp.cos(this.eImag), this.corrCoeff);
/* 524 */     bb.eImag = pre.times(ErrorProp.sin(this.eImag), this.corrCoeff);
/* 525 */     return bb;
/*     */   }
/*     */   
/*     */ 
/*     */   public static ErrorProp abs(ComplexErrorProp aa)
/*     */   {
/* 531 */     ErrorProp bb = new ErrorProp();
/* 532 */     double realV = aa.eReal.getValue();
/* 533 */     double imagV = aa.eImag.getValue();
/*     */     
/* 535 */     double rmod = Math.abs(realV);
/* 536 */     double imod = Math.abs(imagV);
/* 537 */     double ratio = 0.0D;
/* 538 */     double res = 0.0D;
/*     */     
/* 540 */     if (rmod == 0.0D) {
/* 541 */       res = imod;
/*     */     }
/*     */     else {
/* 544 */       if (imod == 0.0D) {
/* 545 */         res = rmod;
/*     */       }
/* 547 */       if (rmod >= imod) {
/* 548 */         ratio = imagV / realV;
/* 549 */         res = rmod * Math.sqrt(1.0D + ratio * ratio);
/*     */       }
/*     */       else
/*     */       {
/* 553 */         ratio = realV / imagV;
/* 554 */         res = imod * Math.sqrt(1.0D + ratio * ratio);
/*     */       }
/*     */     }
/* 557 */     bb.setValue(res);
/*     */     
/* 559 */     double realE = aa.eReal.getError();
/* 560 */     double imagE = aa.eImag.getError();
/* 561 */     res = hypotWithRho(2.0D * realE * realV, 2.0D * imagE * imagV, aa.corrCoeff);
/* 562 */     bb.setError(res);
/*     */     
/* 564 */     return bb;
/*     */   }
/*     */   
/*     */   public ErrorProp abs()
/*     */   {
/* 569 */     ErrorProp aa = new ErrorProp();
/* 570 */     double realV = this.eReal.getValue();
/* 571 */     double imagV = this.eImag.getValue();
/*     */     
/* 573 */     double rmod = Math.abs(realV);
/* 574 */     double imod = Math.abs(imagV);
/* 575 */     double ratio = 0.0D;
/* 576 */     double res = 0.0D;
/*     */     
/* 578 */     if (rmod == 0.0D) {
/* 579 */       res = imod;
/*     */     }
/*     */     else {
/* 582 */       if (imod == 0.0D) {
/* 583 */         res = rmod;
/*     */       }
/* 585 */       if (rmod >= imod) {
/* 586 */         ratio = imagV / realV;
/* 587 */         res = rmod * Math.sqrt(1.0D + ratio * ratio);
/*     */       }
/*     */       else
/*     */       {
/* 591 */         ratio = realV / imagV;
/* 592 */         res = imod * Math.sqrt(1.0D + ratio * ratio);
/*     */       }
/*     */     }
/* 595 */     aa.setValue(res);
/*     */     
/* 597 */     double realE = this.eReal.getError();
/* 598 */     double imagE = this.eImag.getError();
/* 599 */     res = hypotWithRho(2.0D * realE * realV, 2.0D * imagE * imagV, this.corrCoeff);
/* 600 */     aa.setError(res);
/*     */     
/* 602 */     return aa;
/*     */   }
/*     */   
/*     */   public static ErrorProp arg(ComplexErrorProp a)
/*     */   {
/* 607 */     ErrorProp b = new ErrorProp();
/* 608 */     b = ErrorProp.atan2(a.eReal, a.eImag, a.corrCoeff);
/* 609 */     return b;
/*     */   }
/*     */   
/*     */   public ErrorProp arg(double rho)
/*     */   {
/* 614 */     ErrorProp a = new ErrorProp();
/* 615 */     a = ErrorProp.atan2(this.eReal, this.eImag, this.corrCoeff);
/* 616 */     return a;
/*     */   }
/*     */   
/*     */   public static double hypotWithRho(double aa, double bb, double rho)
/*     */   {
/* 621 */     double amod = Math.abs(aa);
/* 622 */     double bmod = Math.abs(bb);
/* 623 */     double cc = 0.0D;double ratio = 0.0D;
/* 624 */     if (amod == 0.0D) {
/* 625 */       cc = bmod;
/*     */ 
/*     */     }
/* 628 */     else if (bmod == 0.0D) {
/* 629 */       cc = amod;
/*     */ 
/*     */     }
/* 632 */     else if (amod >= bmod) {
/* 633 */       ratio = bmod / amod;
/* 634 */       cc = amod * Math.sqrt(1.0D + ratio * ratio + 2.0D * rho * ratio);
/*     */     }
/*     */     else {
/* 637 */       ratio = amod / bmod;
/* 638 */       cc = bmod * Math.sqrt(1.0D + ratio * ratio + 2.0D * rho * ratio);
/*     */     }
/*     */     
/*     */ 
/* 642 */     return cc;
/*     */   }
/*     */   
/*     */ 
/*     */   public static ComplexErrorProp truncate(ComplexErrorProp x, int prec)
/*     */   {
/* 648 */     if (prec < 0) { return x;
/*     */     }
/* 650 */     double rV = x.eReal.getValue();
/* 651 */     double rE = x.eReal.getError();
/* 652 */     double iV = x.eImag.getValue();
/* 653 */     double iE = x.eImag.getError();
/* 654 */     ComplexErrorProp y = new ComplexErrorProp();
/*     */     
/* 656 */     rV = Fmath.truncate(rV, prec);
/* 657 */     rE = Fmath.truncate(rE, prec);
/* 658 */     iV = Fmath.truncate(iV, prec);
/* 659 */     iE = Fmath.truncate(iE, prec);
/*     */     
/* 661 */     y.reset(rV, rE, iV, iE);
/* 662 */     return y;
/*     */   }
/*     */   
/*     */   public ComplexErrorProp truncate(int prec)
/*     */   {
/* 667 */     if (prec < 0) { return this;
/*     */     }
/* 669 */     double rV = this.eReal.getValue();
/* 670 */     double rE = this.eReal.getError();
/* 671 */     double iV = this.eImag.getValue();
/* 672 */     double iE = this.eImag.getError();
/*     */     
/* 674 */     ComplexErrorProp y = new ComplexErrorProp();
/*     */     
/* 676 */     rV = Fmath.truncate(rV, prec);
/* 677 */     rE = Fmath.truncate(rE, prec);
/* 678 */     iV = Fmath.truncate(iV, prec);
/* 679 */     iE = Fmath.truncate(iE, prec);
/*     */     
/* 681 */     y.reset(rV, rE, iV, iE);
/* 682 */     return y;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 689 */     return "Real part: " + this.eReal.getValue() + ", error = " + this.eReal.getError() + "; Imaginary part: " + this.eImag.getValue() + ", error = " + this.eImag.getError();
/*     */   }
/*     */   
/*     */ 
/*     */   public static String toString(ComplexErrorProp aa)
/*     */   {
/* 695 */     return "Real part: " + aa.eReal.getValue() + ", error = " + aa.eReal.getError() + "; Imaginary part: " + aa.eImag.getValue() + ", error = " + aa.eImag.getError();
/*     */   }
/*     */   
/*     */ 
/*     */   public void println(String message)
/*     */   {
/* 701 */     System.out.println(message + " " + toString());
/*     */   }
/*     */   
/*     */   public void println()
/*     */   {
/* 706 */     System.out.println(" " + toString());
/*     */   }
/*     */   
/*     */   public void print(String message)
/*     */   {
/* 711 */     System.out.print(message + " " + toString());
/*     */   }
/*     */   
/*     */   public void print()
/*     */   {
/* 716 */     System.out.print(" " + toString());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/complex/ComplexErrorProp.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */