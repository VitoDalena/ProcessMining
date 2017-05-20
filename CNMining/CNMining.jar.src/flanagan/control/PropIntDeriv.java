/*     */ package flanagan.control;
/*     */ 
/*     */ import flanagan.complex.Complex;
/*     */ import flanagan.complex.ComplexPoly;
/*     */ import flanagan.plot.PlotGraph;
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
/*     */ public class PropIntDeriv
/*     */   extends BlackBox
/*     */ {
/*  44 */   private double kp = 1.0D;
/*  45 */   private double ti = Double.POSITIVE_INFINITY;
/*  46 */   private double ki = 0.0D;
/*  47 */   private double td = 0.0D;
/*  48 */   private double kd = 0.0D;
/*     */   
/*     */   public PropIntDeriv()
/*     */   {
/*  52 */     super("PID");
/*  53 */     super.setSnumer(new ComplexPoly(0.0D, 1.0D, 0.0D));
/*  54 */     super.setSdenom(new ComplexPoly(0.0D, 1.0D));
/*  55 */     super.setZtransformMethod(1);
/*  56 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setKp(double kp)
/*     */   {
/*  61 */     this.kp = kp;
/*  62 */     this.sNumer.resetCoeff(1, new Complex(kp, 0.0D));
/*  63 */     super.calcPolesZerosS();
/*  64 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setKi(double ki)
/*     */   {
/*  69 */     this.ki = ki;
/*  70 */     this.ti = (this.kp / ki);
/*  71 */     this.sNumer.resetCoeff(0, new Complex(ki, 0.0D));
/*  72 */     super.calcPolesZerosS();
/*  73 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setTi(double ti)
/*     */   {
/*  78 */     this.ti = ti;
/*  79 */     this.ki = (this.kp / ti);
/*  80 */     this.sNumer.resetCoeff(0, new Complex(this.ki, 0.0D));
/*  81 */     super.calcPolesZerosS();
/*  82 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setKd(double kd)
/*     */   {
/*  87 */     this.kd = kd;
/*  88 */     this.td = (kd / this.kp);
/*  89 */     this.sNumer.resetCoeff(2, new Complex(kd, 0.0D));
/*  90 */     super.calcPolesZerosS();
/*  91 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setTd(double td)
/*     */   {
/*  96 */     this.td = td;
/*  97 */     this.kd = (this.kp * td);
/*  98 */     this.sNumer.resetCoeff(2, new Complex(this.kd, 0.0D));
/*  99 */     super.calcPolesZerosS();
/* 100 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public double getKp()
/*     */   {
/* 105 */     return this.kp;
/*     */   }
/*     */   
/*     */   public double getKi()
/*     */   {
/* 110 */     return this.ki;
/*     */   }
/*     */   
/*     */   public double getTi()
/*     */   {
/* 115 */     return this.ti;
/*     */   }
/*     */   
/*     */   public double getKd()
/*     */   {
/* 120 */     return this.kd;
/*     */   }
/*     */   
/*     */   public double getTd()
/*     */   {
/* 125 */     return this.td;
/*     */   }
/*     */   
/*     */   public void zTransform()
/*     */   {
/* 130 */     if (this.deltaT == 0.0D) System.out.println("z-transform attempted in PropIntDeriv with a zero sampling period");
/* 131 */     super.deadTimeWarning("zTransform");
/* 132 */     if (this.ztransMethod == 0) {
/* 133 */       mapstozAdHoc();
/*     */     }
/*     */     else {
/* 136 */       double kit = this.ki * this.deltaT;
/* 137 */       double kdt = this.kd / this.deltaT;
/* 138 */       Complex[] coef = Complex.oneDarray(3);
/* 139 */       coef[0].reset(0.0D, 0.0D);
/* 140 */       coef[1].reset(-1.0D, 0.0D);
/* 141 */       coef[2].reset(1.0D, 0.0D);
/* 142 */       this.zDenom.resetPoly(coef);
/* 143 */       switch (this.integMethod) {
/*     */       case 0: 
/* 145 */         coef[0].reset(kdt, 0.0D);
/* 146 */         coef[1].reset(kit / 2.0D - 2.0D * kdt - this.kp, 0.0D);
/* 147 */         coef[2].reset(this.kp + kit / 2.0D + kdt, 0.0D);
/* 148 */         this.zNumer.resetPoly(coef);
/* 149 */         break;
/*     */       case 1: 
/* 151 */         coef[0].reset(kdt, 0.0D);
/* 152 */         coef[1].reset(-2.0D * kdt - this.kp, 0.0D);
/* 153 */         coef[2].reset(this.kp + kit + kdt, 0.0D);
/* 154 */         this.zNumer.resetPoly(coef);
/* 155 */         break;
/*     */       case 2: 
/* 157 */         coef[0].reset(kdt, 0.0D);
/* 158 */         coef[1].reset(kit - 2.0D * kdt - this.kp, 0.0D);
/* 159 */         coef[2].reset(this.kp + kdt, 0.0D);
/* 160 */         this.zNumer.resetPoly(coef);
/* 161 */         break;
/* 162 */       default:  System.out.println("Integration method option in PropIntDeriv must be 0,1 or 2");
/* 163 */         System.out.println("It was set at " + this.integMethod);
/* 164 */         System.out.println("z-transform not performed");
/*     */       }
/*     */     }
/* 167 */     this.zZeros = this.zNumer.roots();
/* 168 */     this.zPoles = this.zDenom.roots();
/*     */   }
/*     */   
/*     */   public void zTransform(double deltaT)
/*     */   {
/* 173 */     this.deltaT = deltaT;
/* 174 */     zTransform();
/*     */   }
/*     */   
/*     */   public void calcPolesZerosZ()
/*     */   {
/* 179 */     if (this.deltaT == 0.0D) System.out.println("z-pole and z-zero calculation attempted in PropIntDeriv.calcPolesZerosZ( with a zero sampling period");
/* 180 */     zTransform();
/* 181 */     this.zPoles[0].reset(0.0D, 0.0D);
/* 182 */     this.zPoles[1].reset(1.0D, 0.0D);
/* 183 */     this.zZeros = this.zNumer.roots();
/*     */   }
/*     */   
/*     */   public void calcPolesZerosZ(double deltaT)
/*     */   {
/* 188 */     this.deltaT = deltaT;
/* 189 */     calcPolesZerosZ();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void stepInput(double stepMag, double finalTime)
/*     */   {
/* 196 */     int n = 50;
/* 197 */     double incrT = finalTime / (n - 1);
/* 198 */     double[][] cdata = new double[2][n];
/* 199 */     double sum = 0.0D;
/*     */     
/* 201 */     cdata[0][0] = 0.0D;
/* 202 */     for (int i = 1; i < n; i++) {
/* 203 */       cdata[0][i] = (cdata[0][(i - 1)] + incrT);
/*     */     }
/* 205 */     double kpterm = this.kp * stepMag;
/* 206 */     for (int i = 0; i < n; i++) {
/* 207 */       sum += this.ki * incrT * stepMag;
/* 208 */       cdata[1][i] = (kpterm + sum);
/*     */     }
/* 210 */     if (this.deadTime != 0.0D) { for (int i = 0; i < n; i++) { cdata[0][i] += this.deadTime;
/*     */       }
/*     */     }
/* 213 */     PlotGraph pg = new PlotGraph(cdata);
/*     */     
/* 215 */     pg.setGraphTitle("Step Input Transient:   Step magnitude = " + stepMag);
/* 216 */     pg.setGraphTitle2(getName());
/* 217 */     pg.setXaxisLegend("Time");
/* 218 */     pg.setXaxisUnitsName("s");
/* 219 */     pg.setYaxisLegend("Output");
/* 220 */     pg.setPoint(0);
/* 221 */     pg.plot();
/*     */   }
/*     */   
/*     */   public void stepInput(double finalTime)
/*     */   {
/* 226 */     stepInput(1.0D, finalTime);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void rampInput(double rampGradient, int rampOrder, double finalTime)
/*     */   {
/* 233 */     if (rampOrder == 0) {
/* 234 */       stepInput(rampGradient, finalTime);
/*     */     }
/*     */     else
/*     */     {
/* 238 */       int n = 50;
/* 239 */       double incrT = finalTime / (n - 1);
/* 240 */       double[][] cdata = new double[2][n];
/* 241 */       double sum = 0.0D;
/*     */       
/* 243 */       cdata[0][0] = 0.0D;
/* 244 */       cdata[1][0] = 0.0D;
/* 245 */       for (int i = 1; i < n; i++) {
/* 246 */         cdata[0][i] = (cdata[0][(i - 1)] + incrT);
/* 247 */         sum += this.ki * rampGradient * (Math.pow(cdata[0][i], rampOrder + 1) - Math.pow(cdata[0][(i - 1)], rampOrder + 1)) / (rampOrder + 1);
/* 248 */         cdata[1][i] = (this.kp * rampGradient * Math.pow(cdata[0][i], rampOrder) + sum);
/*     */       }
/* 250 */       if (this.deadTime != 0.0D) { for (int i = 0; i < n; i++) { cdata[0][i] += this.deadTime;
/*     */         }
/*     */       }
/* 253 */       PlotGraph pg = new PlotGraph(cdata);
/*     */       
/* 255 */       pg.setGraphTitle("Ramp (a.t^n) Input Transient:   ramp gradient (a) = " + rampGradient + " ramp order (n) = " + rampOrder);
/* 256 */       pg.setGraphTitle2(getName());
/* 257 */       pg.setXaxisLegend("Time");
/* 258 */       pg.setXaxisUnitsName("s");
/* 259 */       pg.setYaxisLegend("Output");
/* 260 */       pg.setPoint(0);
/* 261 */       pg.plot();
/*     */     }
/*     */   }
/*     */   
/*     */   public void rampInput(int rampOrder, double finalTime)
/*     */   {
/* 267 */     double rampGradient = 1.0D;
/* 268 */     rampInput(rampGradient, rampOrder, finalTime);
/*     */   }
/*     */   
/*     */   public void rampInput(double rampGradient, double finalTime)
/*     */   {
/* 273 */     int rampOrder = 1;
/* 274 */     rampInput(rampGradient, rampOrder, finalTime);
/*     */   }
/*     */   
/*     */   public void rampInput(double finalTime)
/*     */   {
/* 279 */     double rampGradient = 1.0D;
/* 280 */     int rampOrder = 1;
/* 281 */     rampInput(rampGradient, rampOrder, finalTime);
/*     */   }
/*     */   
/*     */   public Complex getOutputS(Complex sValue, Complex iinput)
/*     */   {
/* 286 */     this.sValue = sValue;
/* 287 */     this.inputS = iinput;
/* 288 */     Complex term1 = Complex.plusOne();
/* 289 */     Complex term2 = Complex.plusOne();
/* 290 */     Complex term3 = Complex.plusOne();
/* 291 */     term1 = term1.times(this.kp);
/* 292 */     term2 = term2.times(this.ki);
/* 293 */     term2 = term2.over(this.sValue);
/* 294 */     term3 = term3.times(this.kd);
/* 295 */     term3 = term3.times(this.sValue);
/* 296 */     Complex term = term1.plus(term2.plus(term3));
/* 297 */     this.outputS = term.times(this.inputS);
/* 298 */     if (this.deadTime != 0.0D) this.outputS = this.outputS.times(Complex.exp(this.sValue.times(-this.deadTime)));
/* 299 */     return this.outputS;
/*     */   }
/*     */   
/*     */   public Complex getOutputS() {
/* 303 */     Complex term1 = Complex.plusOne();
/* 304 */     Complex term2 = Complex.plusOne();
/* 305 */     Complex term3 = Complex.plusOne();
/* 306 */     term1 = term1.times(this.kp);
/* 307 */     term2 = term2.times(this.ki);
/* 308 */     term2 = term2.over(this.sValue);
/* 309 */     term3 = term3.times(this.kd);
/* 310 */     term3 = term3.times(this.sValue);
/* 311 */     Complex term = term1.plus(term2.plus(term3));
/* 312 */     this.outputS = term.times(this.inputS);
/* 313 */     if (this.deadTime != 0.0D) this.outputS = this.outputS.times(Complex.exp(this.sValue.times(-this.deadTime)));
/* 314 */     return this.outputS;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void calcOutputT(double ttime, double inp)
/*     */   {
/* 321 */     if (ttime <= this.time[(this.sampLen - 1)]) throw new IllegalArgumentException("Current time equals or is less than previous time");
/* 322 */     this.deltaT = (ttime - this.time[(this.sampLen - 1)]);
/* 323 */     this.sampFreq = (1.0D / this.deltaT);
/* 324 */     super.deadTimeWarning("zTransform");
/* 325 */     for (int i = 0; i < this.sampLen - 2; i++) {
/* 326 */       this.time[i] = this.time[(i + 1)];
/* 327 */       this.inputT[i] = this.inputT[(i + 1)];
/* 328 */       this.outputT[i] = this.outputT[(i + 1)];
/*     */     }
/* 330 */     this.time[(this.sampLen - 1)] = ttime;
/* 331 */     this.inputT[(this.sampLen - 1)] = inp;
/* 332 */     this.outputT[(this.sampLen - 1)] = NaN.0D;
/* 333 */     calcOutputT();
/*     */   }
/*     */   
/*     */   public void calcOutputT()
/*     */   {
/* 338 */     super.deadTimeWarning("zTransform");
/*     */     
/* 340 */     this.outputT[(this.sampLen - 1)] = (this.kp * this.inputT[(this.sampLen - 1)]);
/*     */     
/* 342 */     if (this.forgetFactor == 1.0D)
/* 343 */       switch (this.integMethod) {
/*     */       case 0: 
/* 345 */         this.integrationSum += (this.inputT[(this.sampLen - 1)] + this.inputT[(this.sampLen - 2)]) * this.deltaT / 2.0D;
/* 346 */         break;
/*     */       case 1: 
/* 348 */         this.integrationSum += this.inputT[(this.sampLen - 1)] * this.deltaT;
/* 349 */         break;
/*     */       case 2: 
/* 351 */         this.integrationSum += this.inputT[(this.sampLen - 2)] * this.deltaT;
/* 352 */         break;
/* 353 */       default:  System.out.println("Integration method option in PropInt must be 0,1 or 2");
/* 354 */         System.out.println("It was set at " + this.integMethod);
/* 355 */         System.out.println("getOutput not performed");
/*     */         
/* 357 */         break;
/*     */       } else {
/* 359 */       switch (this.integMethod) {
/*     */       case 0: 
/* 361 */         this.integrationSum = 0.0D;
/* 362 */         for (int i = 1; i < this.sampLen; i++) {
/* 363 */           this.integrationSum += Math.pow(this.forgetFactor, this.sampLen - 1 - i) * (this.inputT[(i - 1)] + this.inputT[i]) * this.deltaT / 2.0D;
/*     */         }
/* 365 */         break;
/*     */       case 1: 
/* 367 */         this.integrationSum = 0.0D;
/* 368 */         for (int i = 1; i < this.sampLen; i++) {
/* 369 */           this.integrationSum += Math.pow(this.forgetFactor, this.sampLen - 1 - i) * this.inputT[i] * this.deltaT;
/*     */         }
/* 371 */         break;
/*     */       case 2: 
/* 373 */         this.integrationSum = 0.0D;
/* 374 */         for (int i = 1; i < this.sampLen; i++) {
/* 375 */           this.integrationSum += Math.pow(this.forgetFactor, this.sampLen - 1 - i) * this.inputT[(i - 1)] * this.deltaT;
/*     */         }
/* 377 */         break;
/* 378 */       default:  System.out.println("Integration method option in PropInt must be 0,1 or 2");
/* 379 */         System.out.println("It was set at " + this.integMethod);
/* 380 */         System.out.println("getOutput not performed");
/*     */       }
/*     */     }
/* 383 */     this.outputT[(this.sampLen - 1)] += this.ki * this.integrationSum;
/*     */     
/* 385 */     this.outputT[(this.sampLen - 1)] += this.kd * (this.inputT[(this.sampLen - 1)] - this.inputT[(this.sampLen - 2)]) / this.deltaT;
/*     */   }
/*     */   
/*     */ 
/*     */   public PropIntDeriv copy()
/*     */   {
/* 391 */     if (this == null) {
/* 392 */       return null;
/*     */     }
/*     */     
/* 395 */     PropIntDeriv bb = new PropIntDeriv();
/*     */     
/* 397 */     bb.kp = this.kp;
/* 398 */     bb.ti = this.ti;
/* 399 */     bb.td = this.td;
/* 400 */     bb.kd = this.kd;
/*     */     
/* 402 */     bb.sampLen = this.sampLen;
/* 403 */     bb.inputT = ((double[])this.inputT.clone());
/* 404 */     bb.outputT = ((double[])this.outputT.clone());
/* 405 */     bb.time = ((double[])this.time.clone());
/* 406 */     bb.forgetFactor = this.forgetFactor;
/* 407 */     bb.deltaT = this.deltaT;
/* 408 */     bb.sampFreq = this.sampFreq;
/* 409 */     bb.inputS = this.inputS.copy();
/* 410 */     bb.outputS = this.outputS.copy();
/* 411 */     bb.sValue = this.sValue.copy();
/* 412 */     bb.zValue = this.zValue.copy();
/* 413 */     bb.sNumer = this.sNumer.copy();
/* 414 */     bb.sDenom = this.sDenom.copy();
/* 415 */     bb.zNumer = this.zNumer.copy();
/* 416 */     bb.zDenom = this.zDenom.copy();
/* 417 */     bb.sPoles = Complex.copy(this.sPoles);
/* 418 */     bb.sZeros = Complex.copy(this.sZeros);
/* 419 */     bb.zPoles = Complex.copy(this.zPoles);
/* 420 */     bb.zZeros = Complex.copy(this.zZeros);
/* 421 */     bb.sNumerDeg = this.sNumerDeg;
/* 422 */     bb.sDenomDeg = this.sDenomDeg;
/* 423 */     bb.zNumerDeg = this.zNumerDeg;
/* 424 */     bb.zDenomDeg = this.zDenomDeg;
/* 425 */     bb.deadTime = this.deadTime;
/* 426 */     bb.orderPade = this.orderPade;
/* 427 */     bb.sNumerPade = this.sNumerPade.copy();
/* 428 */     bb.sDenomPade = this.sDenomPade.copy();
/* 429 */     bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 430 */     bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 431 */     bb.sNumerDegPade = this.sNumerDegPade;
/* 432 */     bb.sDenomDegPade = this.sDenomDegPade;
/* 433 */     bb.maptozero = this.maptozero;
/* 434 */     bb.padeAdded = this.padeAdded;
/* 435 */     bb.integrationSum = this.integrationSum;
/* 436 */     bb.integMethod = this.integMethod;
/* 437 */     bb.ztransMethod = this.ztransMethod;
/* 438 */     bb.name = this.name;
/* 439 */     bb.fixedName = this.fixedName;
/* 440 */     bb.nPlotPoints = this.nPlotPoints;
/*     */     
/* 442 */     return bb;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 449 */     Object ret = null;
/*     */     
/* 451 */     if (this != null) {
/* 452 */       PropIntDeriv bb = new PropIntDeriv();
/*     */       
/* 454 */       bb.kp = this.kp;
/* 455 */       bb.ti = this.ti;
/* 456 */       bb.td = this.td;
/* 457 */       bb.kd = this.kd;
/*     */       
/* 459 */       bb.sampLen = this.sampLen;
/* 460 */       bb.inputT = ((double[])this.inputT.clone());
/* 461 */       bb.outputT = ((double[])this.outputT.clone());
/* 462 */       bb.time = ((double[])this.time.clone());
/* 463 */       bb.forgetFactor = this.forgetFactor;
/* 464 */       bb.deltaT = this.deltaT;
/* 465 */       bb.sampFreq = this.sampFreq;
/* 466 */       bb.inputS = this.inputS.copy();
/* 467 */       bb.outputS = this.outputS.copy();
/* 468 */       bb.sValue = this.sValue.copy();
/* 469 */       bb.zValue = this.zValue.copy();
/* 470 */       bb.sNumer = this.sNumer.copy();
/* 471 */       bb.sDenom = this.sDenom.copy();
/* 472 */       bb.zNumer = this.zNumer.copy();
/* 473 */       bb.zDenom = this.zDenom.copy();
/* 474 */       bb.sPoles = Complex.copy(this.sPoles);
/* 475 */       bb.sZeros = Complex.copy(this.sZeros);
/* 476 */       bb.zPoles = Complex.copy(this.zPoles);
/* 477 */       bb.zZeros = Complex.copy(this.zZeros);
/* 478 */       bb.sNumerDeg = this.sNumerDeg;
/* 479 */       bb.sDenomDeg = this.sDenomDeg;
/* 480 */       bb.zNumerDeg = this.zNumerDeg;
/* 481 */       bb.zDenomDeg = this.zDenomDeg;
/* 482 */       bb.deadTime = this.deadTime;
/* 483 */       bb.orderPade = this.orderPade;
/* 484 */       bb.sNumerPade = this.sNumerPade.copy();
/* 485 */       bb.sDenomPade = this.sDenomPade.copy();
/* 486 */       bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 487 */       bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 488 */       bb.sNumerDegPade = this.sNumerDegPade;
/* 489 */       bb.sDenomDegPade = this.sDenomDegPade;
/* 490 */       bb.maptozero = this.maptozero;
/* 491 */       bb.padeAdded = this.padeAdded;
/* 492 */       bb.integrationSum = this.integrationSum;
/* 493 */       bb.integMethod = this.integMethod;
/* 494 */       bb.ztransMethod = this.ztransMethod;
/* 495 */       bb.name = this.name;
/* 496 */       bb.fixedName = this.fixedName;
/* 497 */       bb.nPlotPoints = this.nPlotPoints;
/*     */       
/* 499 */       ret = bb;
/*     */     }
/* 501 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/control/PropIntDeriv.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */