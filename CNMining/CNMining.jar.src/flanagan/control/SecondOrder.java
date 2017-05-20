/*     */ package flanagan.control;
/*     */ 
/*     */ import flanagan.complex.Complex;
/*     */ import flanagan.complex.ComplexPoly;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SecondOrder
/*     */   extends BlackBox
/*     */ {
/*  49 */   private double aConst = 1.0D;
/*  50 */   private double bConst = 1.0D;
/*  51 */   private double cConst = 1.0D;
/*  52 */   private double dConst = 1.0D;
/*  53 */   private double omegaN = 1.0D;
/*  54 */   private double zeta = 1.0D;
/*  55 */   private double kConst = 1.0D;
/*  56 */   private double sigma = 1.0D;
/*     */   
/*     */ 
/*     */   public SecondOrder()
/*     */   {
/*  61 */     super("Second Order Process");
/*  62 */     super.setSnumer(new ComplexPoly(1.0D));
/*  63 */     super.setSdenom(new ComplexPoly(1.0D, 1.0D, 1.0D));
/*  64 */     super.setZtransformMethod(1);
/*  65 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */ 
/*     */   public SecondOrder(double aa, double bb, double cc, double dd)
/*     */   {
/*  71 */     super("Second Order Process");
/*  72 */     this.aConst = aa;
/*  73 */     this.bConst = bb;
/*  74 */     this.cConst = cc;
/*  75 */     this.dConst = dd;
/*  76 */     if (this.cConst > 0.0D) standardForm();
/*  77 */     super.setSnumer(new ComplexPoly(this.dConst));
/*  78 */     super.setSdenom(new ComplexPoly(this.cConst, this.bConst, this.aConst));
/*  79 */     super.setZtransformMethod(1);
/*  80 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setCoeff(double aa, double bb, double cc, double dd)
/*     */   {
/*  85 */     this.aConst = aa;
/*  86 */     this.bConst = bb;
/*  87 */     this.cConst = cc;
/*  88 */     this.dConst = dd;
/*  89 */     if (this.cConst > 0.0D) standardForm();
/*  90 */     Complex[] num = Complex.oneDarray(1);
/*  91 */     num[0].reset(this.dConst, 0.0D);
/*  92 */     this.sNumer.resetPoly(num);
/*  93 */     Complex[] den = Complex.oneDarray(3);
/*  94 */     den[0].reset(this.cConst, 0.0D);
/*  95 */     den[1].reset(this.bConst, 0.0D);
/*  96 */     den[2].reset(this.aConst, 0.0D);
/*  97 */     this.sDenom.resetPoly(den);
/*  98 */     this.fixedName = "Second Order Process";
/*  99 */     calcPolesZerosS();
/* 100 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   private void standardForm()
/*     */   {
/* 105 */     this.omegaN = Math.sqrt(this.cConst / this.aConst);
/* 106 */     this.zeta = (this.bConst / (2.0D * this.aConst * this.omegaN));
/* 107 */     this.kConst = (this.dConst / this.cConst);
/* 108 */     this.sigma = (this.zeta * this.omegaN);
/*     */   }
/*     */   
/*     */   public void setA(double aa) {
/* 112 */     this.aConst = aa;
/* 113 */     Complex co = new Complex(this.aConst, 0.0D);
/* 114 */     this.sDenom.resetCoeff(2, co);
/* 115 */     if (this.cConst > 0.0D) standardForm();
/* 116 */     calcPolesZerosS();
/* 117 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setB(double bb) {
/* 121 */     this.bConst = bb;
/* 122 */     Complex co = new Complex(this.bConst, 0.0D);
/* 123 */     this.sDenom.resetCoeff(1, co);
/* 124 */     if (this.cConst > 0.0D) standardForm();
/* 125 */     calcPolesZerosS();
/* 126 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setC(double cc) {
/* 130 */     this.cConst = cc;
/* 131 */     Complex co = new Complex(this.cConst, 0.0D);
/* 132 */     this.sDenom.resetCoeff(0, co);
/* 133 */     if (this.cConst > 0.0D) standardForm();
/* 134 */     calcPolesZerosS();
/* 135 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setD(double dd) {
/* 139 */     this.dConst = dd;
/* 140 */     Complex co = new Complex(this.dConst, 0.0D);
/* 141 */     this.sNumer.resetCoeff(0, co);
/* 142 */     if (this.cConst > 0.0D) standardForm();
/* 143 */     calcPolesZerosS();
/* 144 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setStandardForm(double zet, double omega, double kk) {
/* 148 */     if (omega <= 0.0D) throw new IllegalArgumentException("zero or negative natural frequency");
/* 149 */     if (zet < 0.0D) throw new IllegalArgumentException("negative damping ratio");
/* 150 */     this.zeta = zet;
/* 151 */     this.omegaN = omega;
/* 152 */     this.kConst = kk;
/* 153 */     this.sigma = (this.omegaN * this.zeta);
/* 154 */     reverseStandard();
/* 155 */     calcPolesZerosS();
/* 156 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setZeta(double zet) {
/* 160 */     if (zet < 0.0D) throw new IllegalArgumentException("negative damping ratio");
/* 161 */     this.zeta = zet;
/* 162 */     this.sigma = (this.omegaN * this.zeta);
/* 163 */     reverseStandard();
/* 164 */     calcPolesZerosS();
/* 165 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setOmegaN(double omega) {
/* 169 */     if (omega <= 0.0D) throw new IllegalArgumentException("zero or negative natural frequency");
/* 170 */     this.omegaN = omega;
/* 171 */     this.sigma = (this.omegaN * this.zeta);
/* 172 */     reverseStandard();
/* 173 */     calcPolesZerosS();
/* 174 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setK(double kk) {
/* 178 */     this.kConst = kk;
/* 179 */     reverseStandard();
/* 180 */     calcPolesZerosS();
/* 181 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   private void reverseStandard()
/*     */   {
/* 186 */     this.aConst = (this.omegaN * this.omegaN);
/* 187 */     this.bConst = (2.0D * this.zeta * this.omegaN);
/* 188 */     this.cConst = 1.0D;
/* 189 */     this.dConst = (this.kConst * this.aConst);
/* 190 */     Complex[] num = Complex.oneDarray(1);
/* 191 */     num[0].reset(this.dConst, 0.0D);
/* 192 */     this.sNumer.resetPoly(num);
/* 193 */     Complex[] den = Complex.oneDarray(3);
/* 194 */     den[0].reset(this.cConst, 0.0D);
/* 195 */     den[1].reset(this.bConst, 0.0D);
/* 196 */     den[2].reset(this.aConst, 0.0D);
/* 197 */     this.sDenom.resetPoly(den);
/*     */   }
/*     */   
/*     */   public double getA() {
/* 201 */     return this.aConst;
/*     */   }
/*     */   
/*     */   public double getB() {
/* 205 */     return this.bConst;
/*     */   }
/*     */   
/*     */   public double getC() {
/* 209 */     return this.cConst;
/*     */   }
/*     */   
/*     */   public double getD() {
/* 213 */     return this.dConst;
/*     */   }
/*     */   
/*     */   public double getOmegaN() {
/* 217 */     return this.omegaN;
/*     */   }
/*     */   
/*     */   public double getZeta() {
/* 221 */     return this.zeta;
/*     */   }
/*     */   
/*     */   public double getK() {
/* 225 */     return this.kConst;
/*     */   }
/*     */   
/*     */   public double getAttenuation() {
/* 229 */     return this.sigma;
/*     */   }
/*     */   
/*     */   protected void calcPolesZerosS()
/*     */   {
/* 234 */     this.sPoles = this.sDenom.roots();
/*     */   }
/*     */   
/*     */   public Complex getOutputS(Complex sValue, Complex iinput)
/*     */   {
/* 239 */     this.sValue = sValue;
/* 240 */     this.inputS = iinput;
/* 241 */     return getOutputS();
/*     */   }
/*     */   
/*     */   public Complex getOutputS()
/*     */   {
/* 246 */     Complex num = Complex.plusOne();
/* 247 */     num = num.times(this.dConst);
/* 248 */     Complex den = new Complex();
/* 249 */     den = this.sValue.times(this.sValue.times(this.aConst));
/* 250 */     den = den.plus(this.sValue.times(this.aConst));
/* 251 */     den = den.plus(this.cConst);
/* 252 */     Complex term = new Complex();
/* 253 */     term = num.over(den);
/* 254 */     this.outputS = term.times(this.inputS);
/* 255 */     if (this.deadTime != 0.0D) this.outputS = this.outputS.times(Complex.exp(this.sValue.times(-this.deadTime)));
/* 256 */     return this.outputS;
/*     */   }
/*     */   
/*     */   public void zTransform()
/*     */   {
/* 261 */     if (this.deltaT == 0.0D) System.out.println("z-transform attempted in SecondOrder with a zero sampling period");
/* 262 */     if (this.ztransMethod == 0) {
/* 263 */       mapstozAdHoc();
/*     */     }
/*     */     else {
/* 266 */       Complex[] ncoef = null;
/* 267 */       Complex[] dcoef = null;
/* 268 */       double bT = this.bConst * this.deltaT;
/* 269 */       double t2 = this.deltaT * this.deltaT;
/* 270 */       double cT2 = this.cConst * t2;
/* 271 */       double dT2 = this.dConst * t2;
/* 272 */       switch (this.integMethod) {
/*     */       case 0: 
/* 274 */         ncoef = Complex.oneDarray(3);
/* 275 */         ncoef[0].reset(dT2 / 4.0D, 0.0D);
/* 276 */         ncoef[1].reset(dT2 / 2.0D, 0.0D);
/* 277 */         ncoef[2].reset(dT2 / 4.0D, 0.0D);
/* 278 */         this.zNumer = new ComplexPoly(2);
/* 279 */         this.zNumer.resetPoly(ncoef);
/* 280 */         this.zNumerDeg = 2;
/* 281 */         dcoef = Complex.oneDarray(3);
/* 282 */         dcoef[0].reset(this.aConst - bT + cT2 / 4.0D, 0.0D);
/* 283 */         dcoef[1].reset(-2.0D * this.aConst + bT + cT2 / 2.0D, 0.0D);
/* 284 */         dcoef[2].reset(this.aConst + cT2 / 4.0D, 0.0D);
/* 285 */         this.zDenom = new ComplexPoly(2);
/* 286 */         this.zDenom.resetPoly(dcoef);
/* 287 */         this.zDenomDeg = 2;
/* 288 */         this.zZeros = this.zNumer.roots();
/* 289 */         this.zPoles = this.zDenom.roots();
/* 290 */         break;
/*     */       case 1: 
/* 292 */         ncoef = Complex.oneDarray(3);
/* 293 */         ncoef[0].reset(0.0D, 0.0D);
/* 294 */         ncoef[1].reset(0.0D, 0.0D);
/* 295 */         ncoef[2].reset(dT2, 0.0D);
/* 296 */         this.zNumer = new ComplexPoly(2);
/* 297 */         this.zNumer.resetPoly(ncoef);
/* 298 */         this.zNumerDeg = 2;
/* 299 */         dcoef = Complex.oneDarray(3);
/* 300 */         dcoef[0].reset(this.aConst - bT, 0.0D);
/* 301 */         dcoef[1].reset(-2.0D * this.aConst, 0.0D);
/* 302 */         dcoef[2].reset(this.aConst + bT + cT2, 0.0D);
/* 303 */         this.zDenom = new ComplexPoly(2);
/* 304 */         this.zDenom.resetPoly(dcoef);
/* 305 */         this.zDenomDeg = 2;
/* 306 */         this.zPoles = this.zDenom.roots();
/* 307 */         this.zZeros = Complex.oneDarray(2);
/* 308 */         this.zZeros[0].reset(0.0D, 0.0D);
/* 309 */         this.zZeros[1].reset(0.0D, 0.0D);
/* 310 */         break;
/*     */       case 2: 
/* 312 */         ncoef = Complex.oneDarray(3);
/* 313 */         ncoef[0].reset(0.0D, 0.0D);
/* 314 */         ncoef[1].reset(0.0D, 0.0D);
/* 315 */         ncoef[2].reset(dT2, 0.0D);
/* 316 */         this.zNumer = new ComplexPoly(2);
/* 317 */         this.zNumer.resetPoly(ncoef);
/* 318 */         this.zNumerDeg = 2;
/* 319 */         dcoef = Complex.oneDarray(3);
/* 320 */         dcoef[0].reset(this.aConst - bT + cT2, 0.0D);
/* 321 */         dcoef[1].reset(-2.0D * this.aConst + bT, 0.0D);
/* 322 */         dcoef[2].reset(this.aConst, 0.0D);
/* 323 */         this.zDenom = new ComplexPoly(2);
/* 324 */         this.zDenom.resetPoly(dcoef);
/* 325 */         this.zDenomDeg = 2;
/* 326 */         this.zPoles = this.zDenom.roots();
/* 327 */         this.zZeros = Complex.oneDarray(2);
/* 328 */         this.zZeros[0].reset(0.0D, 0.0D);
/* 329 */         this.zZeros[1].reset(0.0D, 0.0D);
/* 330 */         break;
/* 331 */       default:  System.out.println("Integration method option in SecondOrder must be 0,1 or 2");
/* 332 */         System.out.println("It was set at " + this.integMethod);
/* 333 */         System.out.println("z-transform not performed");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void zTransform(double deltaT)
/*     */   {
/* 340 */     this.deltaT = deltaT;
/* 341 */     super.deadTimeWarning("zTransform");
/* 342 */     zTransform();
/*     */   }
/*     */   
/*     */ 
/*     */   public void calcOutputT(double ttime, double inp)
/*     */   {
/* 348 */     if (ttime <= this.time[(this.sampLen - 1)]) throw new IllegalArgumentException("Current time equals or is less than previous time");
/* 349 */     this.deltaT = (ttime - this.time[(this.sampLen - 1)]);
/* 350 */     this.sampFreq = (1.0D / this.deltaT);
/* 351 */     super.deadTimeWarning("calcOutputT(time, input)");
/* 352 */     for (int i = 0; i < this.sampLen - 2; i++) {
/* 353 */       this.time[i] = this.time[(i + 1)];
/* 354 */       this.inputT[i] = this.inputT[(i + 1)];
/* 355 */       this.outputT[i] = this.outputT[(i + 1)];
/*     */     }
/* 357 */     this.time[(this.sampLen - 1)] = ttime;
/* 358 */     this.inputT[(this.sampLen - 1)] = inp;
/* 359 */     this.outputT[(this.sampLen - 1)] = NaN.0D;
/* 360 */     calcOutputT();
/*     */   }
/*     */   
/*     */   public void calcOutputT()
/*     */   {
/* 365 */     this.outputT[(this.sampLen - 1)] = ((this.cConst * this.inputT[(this.sampLen - 1)] + this.bConst * (this.inputT[(this.sampLen - 1)] - this.inputT[(this.sampLen - 3)]) / this.deltaT + this.cConst * (this.inputT[(this.sampLen - 1)] - 2.0D * this.inputT[(this.sampLen - 2)] + this.inputT[(this.sampLen - 3)]) / (this.deltaT * this.deltaT)) / this.dConst);
/*     */   }
/*     */   
/*     */   public Complex[] getSzeros()
/*     */   {
/* 370 */     System.out.println("This standard second order process (class SecondOrder) has no s-domain zeros");
/* 371 */     return null;
/*     */   }
/*     */   
/*     */   public SecondOrder copy()
/*     */   {
/* 376 */     if (this == null) {
/* 377 */       return null;
/*     */     }
/*     */     
/* 380 */     SecondOrder bb = new SecondOrder();
/*     */     
/* 382 */     bb.aConst = this.aConst;
/* 383 */     bb.bConst = this.bConst;
/* 384 */     bb.cConst = this.cConst;
/* 385 */     bb.dConst = this.dConst;
/* 386 */     bb.omegaN = this.omegaN;
/* 387 */     bb.zeta = this.zeta;
/* 388 */     bb.kConst = this.kConst;
/* 389 */     bb.sigma = this.sigma;
/*     */     
/* 391 */     bb.sampLen = this.sampLen;
/* 392 */     bb.inputT = ((double[])this.inputT.clone());
/* 393 */     bb.outputT = ((double[])this.outputT.clone());
/* 394 */     bb.time = ((double[])this.time.clone());
/* 395 */     bb.forgetFactor = this.forgetFactor;
/* 396 */     bb.deltaT = this.deltaT;
/* 397 */     bb.sampFreq = this.sampFreq;
/* 398 */     bb.inputS = this.inputS.copy();
/* 399 */     bb.outputS = this.outputS.copy();
/* 400 */     bb.sValue = this.sValue.copy();
/* 401 */     bb.zValue = this.zValue.copy();
/* 402 */     bb.sNumer = this.sNumer.copy();
/* 403 */     bb.sDenom = this.sDenom.copy();
/* 404 */     bb.zNumer = this.zNumer.copy();
/* 405 */     bb.zDenom = this.zDenom.copy();
/* 406 */     bb.sPoles = Complex.copy(this.sPoles);
/* 407 */     bb.sZeros = Complex.copy(this.sZeros);
/* 408 */     bb.zPoles = Complex.copy(this.zPoles);
/* 409 */     bb.zZeros = Complex.copy(this.zZeros);
/* 410 */     bb.sNumerDeg = this.sNumerDeg;
/* 411 */     bb.sDenomDeg = this.sDenomDeg;
/* 412 */     bb.zNumerDeg = this.zNumerDeg;
/* 413 */     bb.zDenomDeg = this.zDenomDeg;
/* 414 */     bb.deadTime = this.deadTime;
/* 415 */     bb.orderPade = this.orderPade;
/* 416 */     bb.sNumerPade = this.sNumerPade.copy();
/* 417 */     bb.sDenomPade = this.sDenomPade.copy();
/* 418 */     bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 419 */     bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 420 */     bb.sNumerDegPade = this.sNumerDegPade;
/* 421 */     bb.sDenomDegPade = this.sDenomDegPade;
/* 422 */     bb.maptozero = this.maptozero;
/* 423 */     bb.padeAdded = this.padeAdded;
/* 424 */     bb.integrationSum = this.integrationSum;
/* 425 */     bb.integMethod = this.integMethod;
/* 426 */     bb.ztransMethod = this.ztransMethod;
/* 427 */     bb.name = this.name;
/* 428 */     bb.fixedName = this.fixedName;
/* 429 */     bb.nPlotPoints = this.nPlotPoints;
/*     */     
/* 431 */     return bb;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 437 */     Object ret = null;
/*     */     
/* 439 */     if (this != null) {
/* 440 */       SecondOrder bb = new SecondOrder();
/*     */       
/* 442 */       bb.aConst = this.aConst;
/* 443 */       bb.bConst = this.bConst;
/* 444 */       bb.cConst = this.cConst;
/* 445 */       bb.dConst = this.dConst;
/* 446 */       bb.omegaN = this.omegaN;
/* 447 */       bb.zeta = this.zeta;
/* 448 */       bb.kConst = this.kConst;
/* 449 */       bb.sigma = this.sigma;
/*     */       
/* 451 */       bb.sampLen = this.sampLen;
/* 452 */       bb.inputT = ((double[])this.inputT.clone());
/* 453 */       bb.outputT = ((double[])this.outputT.clone());
/* 454 */       bb.time = ((double[])this.time.clone());
/* 455 */       bb.forgetFactor = this.forgetFactor;
/* 456 */       bb.deltaT = this.deltaT;
/* 457 */       bb.sampFreq = this.sampFreq;
/* 458 */       bb.inputS = this.inputS.copy();
/* 459 */       bb.outputS = this.outputS.copy();
/* 460 */       bb.sValue = this.sValue.copy();
/* 461 */       bb.zValue = this.zValue.copy();
/* 462 */       bb.sNumer = this.sNumer.copy();
/* 463 */       bb.sDenom = this.sDenom.copy();
/* 464 */       bb.zNumer = this.zNumer.copy();
/* 465 */       bb.zDenom = this.zDenom.copy();
/* 466 */       bb.sPoles = Complex.copy(this.sPoles);
/* 467 */       bb.sZeros = Complex.copy(this.sZeros);
/* 468 */       bb.zPoles = Complex.copy(this.zPoles);
/* 469 */       bb.zZeros = Complex.copy(this.zZeros);
/* 470 */       bb.sNumerDeg = this.sNumerDeg;
/* 471 */       bb.sDenomDeg = this.sDenomDeg;
/* 472 */       bb.zNumerDeg = this.zNumerDeg;
/* 473 */       bb.zDenomDeg = this.zDenomDeg;
/* 474 */       bb.deadTime = this.deadTime;
/* 475 */       bb.orderPade = this.orderPade;
/* 476 */       bb.sNumerPade = this.sNumerPade.copy();
/* 477 */       bb.sDenomPade = this.sDenomPade.copy();
/* 478 */       bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 479 */       bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 480 */       bb.sNumerDegPade = this.sNumerDegPade;
/* 481 */       bb.sDenomDegPade = this.sDenomDegPade;
/* 482 */       bb.maptozero = this.maptozero;
/* 483 */       bb.padeAdded = this.padeAdded;
/* 484 */       bb.integrationSum = this.integrationSum;
/* 485 */       bb.integMethod = this.integMethod;
/* 486 */       bb.ztransMethod = this.ztransMethod;
/* 487 */       bb.name = this.name;
/* 488 */       bb.fixedName = this.fixedName;
/* 489 */       bb.nPlotPoints = this.nPlotPoints;
/*     */       
/* 491 */       ret = bb;
/*     */     }
/* 493 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/control/SecondOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */