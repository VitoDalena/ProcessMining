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
/*     */ public class PropInt
/*     */   extends BlackBox
/*     */ {
/*  43 */   private double kp = 1.0D;
/*  44 */   private double ti = Double.POSITIVE_INFINITY;
/*  45 */   private double ki = 0.0D;
/*     */   
/*     */   public PropInt()
/*     */   {
/*  49 */     super("PI");
/*  50 */     super.setSnumer(new ComplexPoly(0.0D, 1.0D));
/*  51 */     super.setSdenom(new ComplexPoly(0.0D, 1.0D));
/*  52 */     super.setZtransformMethod(1);
/*  53 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setKp(double kp)
/*     */   {
/*  58 */     this.sNumer.resetCoeff(1, new Complex(kp, 0.0D));
/*  59 */     super.calcPolesZerosS();
/*  60 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setKi(double ki)
/*     */   {
/*  65 */     this.ki = ki;
/*  66 */     this.ti = (this.kp / ki);
/*  67 */     this.sNumer.resetCoeff(0, new Complex(ki, 0.0D));
/*  68 */     super.calcPolesZerosS();
/*  69 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */ 
/*     */   public void setTi(double ti)
/*     */   {
/*  75 */     this.ti = ti;
/*  76 */     this.ki = (this.kp / ti);
/*  77 */     this.sNumer.resetCoeff(0, new Complex(this.ki, 0.0D));
/*  78 */     super.calcPolesZerosS();
/*  79 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public double getKp()
/*     */   {
/*  84 */     return this.kp;
/*     */   }
/*     */   
/*     */   public double getKi()
/*     */   {
/*  89 */     return this.ki;
/*     */   }
/*     */   
/*     */   public double getTi()
/*     */   {
/*  94 */     return this.ti;
/*     */   }
/*     */   
/*     */ 
/*     */   public void zTransform()
/*     */   {
/* 100 */     super.deadTimeWarning("zTransform");
/* 101 */     if (this.deltaT == 0.0D) System.out.println("z-transform attempted in PropInt with a zero sampling period");
/* 102 */     if (this.ztransMethod == 0) {
/* 103 */       mapstozAdHoc();
/*     */     }
/*     */     else {
/* 106 */       this.zDenom = new ComplexPoly(1);
/* 107 */       Complex[] coef = Complex.oneDarray(2);
/* 108 */       coef[0].reset(-1.0D, 0.0D);
/* 109 */       coef[1].reset(1.0D, 0.0D);
/* 110 */       this.zDenom.resetPoly(coef);
/* 111 */       Complex[] zPoles = Complex.oneDarray(1);
/* 112 */       zPoles[0].reset(1.0D, 0.0D);
/* 113 */       this.zNumer = new ComplexPoly(1);
/* 114 */       Complex[] zZeros = Complex.oneDarray(1);
/* 115 */       double kit = this.ki * this.deltaT;
/* 116 */       switch (this.integMethod) {
/*     */       case 0: 
/* 118 */         coef[0].reset(kit / 2.0D - this.kp, 0.0D);
/* 119 */         coef[1].reset(kit / 2.0D + this.kp, 0.0D);
/* 120 */         this.zNumer.resetPoly(coef);
/* 121 */         zZeros[0].reset((this.kp - kit / 2.0D) / (this.kp + kit / 2.0D), 0.0D);
/* 122 */         break;
/*     */       case 1: 
/* 124 */         coef[0].reset(-this.kp, 0.0D);
/* 125 */         coef[1].reset(kit + this.kp, 0.0D);
/* 126 */         this.zNumer.resetPoly(coef);
/* 127 */         zZeros[0].reset(this.kp / (this.kp + kit), 0.0D);
/* 128 */         break;
/*     */       case 2: 
/* 130 */         coef[0].reset(this.kp - kit, 0.0D);
/* 131 */         coef[1].reset(this.kp, 0.0D);
/* 132 */         this.zNumer.resetPoly(coef);
/* 133 */         zZeros[0].reset((this.kp - kit) / this.kp, 0.0D);
/* 134 */         break;
/* 135 */       default:  System.out.println("Integration method option in PropInt must be 0,1 or 2");
/* 136 */         System.out.println("It was set at " + this.integMethod);
/* 137 */         System.out.println("z-transform not performed");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void zTransform(double deltaT)
/*     */   {
/* 144 */     this.deltaT = deltaT;
/* 145 */     zTransform();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void stepInput(double stepMag, double finalTime)
/*     */   {
/* 152 */     int n = 50;
/* 153 */     double incrT = finalTime / (n - 1);
/* 154 */     double[][] cdata = new double[2][n];
/* 155 */     double sum = 0.0D;
/*     */     
/* 157 */     cdata[0][0] = 0.0D;
/* 158 */     for (int i = 1; i < n; i++) {
/* 159 */       cdata[0][i] = (cdata[0][(i - 1)] + incrT);
/*     */     }
/* 161 */     double kpterm = this.kp * stepMag;
/* 162 */     for (int i = 0; i < n; i++) {
/* 163 */       sum += this.ki * incrT * stepMag;
/* 164 */       cdata[1][i] = (kpterm + sum);
/* 165 */       cdata[0][i] += this.deadTime;
/*     */     }
/*     */     
/*     */ 
/* 169 */     PlotGraph pg = new PlotGraph(cdata);
/*     */     
/* 171 */     pg.setGraphTitle("Step Input Transient:   Step magnitude = " + stepMag);
/* 172 */     pg.setGraphTitle2(getName());
/* 173 */     pg.setXaxisLegend("Time");
/* 174 */     pg.setXaxisUnitsName("s");
/* 175 */     pg.setYaxisLegend("Output");
/* 176 */     pg.setPoint(0);
/* 177 */     pg.plot();
/*     */   }
/*     */   
/*     */   public void stepInput(double finalTime)
/*     */   {
/* 182 */     stepInput(1.0D, finalTime);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void rampInput(double rampGradient, int rampOrder, double finalTime)
/*     */   {
/* 189 */     int n = 50;
/* 190 */     double incrT = finalTime / (n - 1);
/* 191 */     double[][] cdata = new double[2][n];
/* 192 */     double sum = 0.0D;
/*     */     
/* 194 */     cdata[0][0] = 0.0D;
/* 195 */     cdata[1][0] = 0.0D;
/* 196 */     for (int i = 1; i < n; i++) {
/* 197 */       cdata[0][i] = (cdata[0][(i - 1)] + incrT);
/* 198 */       sum += rampGradient * (Math.pow(cdata[0][i], rampOrder + 1) - Math.pow(cdata[0][(i - 1)], rampOrder + 1)) / (rampOrder + 1);
/* 199 */       cdata[1][i] = (this.kp * rampGradient * Math.pow(cdata[0][i], rampOrder) + sum);
/*     */     }
/* 201 */     for (int i = 0; i < n; i++) {
/* 202 */       cdata[0][i] += this.deadTime;
/*     */     }
/*     */     
/*     */ 
/* 206 */     PlotGraph pg = new PlotGraph(cdata);
/*     */     
/* 208 */     pg.setGraphTitle("Ramp (a.t^n) Input Transient:   ramp gradient (a) = " + rampGradient + " ramp order (n) = " + rampOrder);
/* 209 */     pg.setGraphTitle2(getName());
/* 210 */     pg.setXaxisLegend("Time");
/* 211 */     pg.setXaxisUnitsName("s");
/* 212 */     pg.setYaxisLegend("Output");
/* 213 */     pg.setPoint(0);
/* 214 */     pg.plot();
/*     */   }
/*     */   
/*     */   public void rampInput(int rampOrder, double finalTime)
/*     */   {
/* 219 */     double rampGradient = 1.0D;
/* 220 */     rampInput(rampGradient, rampOrder, finalTime);
/*     */   }
/*     */   
/*     */   public void rampInput(double rampGradient, double finalTime)
/*     */   {
/* 225 */     int rampOrder = 1;
/* 226 */     rampInput(rampGradient, rampOrder, finalTime);
/*     */   }
/*     */   
/*     */   public void rampInput(double finalTime)
/*     */   {
/* 231 */     double rampGradient = 1.0D;
/* 232 */     int rampOrder = 1;
/* 233 */     rampInput(rampGradient, rampOrder, finalTime);
/*     */   }
/*     */   
/*     */   public Complex getOutputS(Complex sValue, Complex iinput)
/*     */   {
/* 238 */     this.sValue = sValue;
/* 239 */     this.inputS = iinput;
/* 240 */     Complex term = this.sValue.times(this.kp);
/* 241 */     term = term.plus(this.ki);
/* 242 */     term = term.over(this.sValue);
/* 243 */     this.outputS = term.times(this.inputS);
/* 244 */     if (this.deadTime != 0.0D) this.outputS = this.outputS.times(Complex.exp(this.sValue.times(-this.deadTime)));
/* 245 */     return this.outputS;
/*     */   }
/*     */   
/*     */   public Complex getOutputS()
/*     */   {
/* 250 */     Complex term = this.sValue.times(this.kp);
/* 251 */     term = term.plus(this.ki);
/* 252 */     term = term.over(this.sValue);
/* 253 */     this.outputS = term.times(this.inputS);
/* 254 */     if (this.deadTime != 0.0D) this.outputS = this.outputS.times(Complex.exp(this.sValue.times(-this.deadTime)));
/* 255 */     return this.outputS;
/*     */   }
/*     */   
/*     */ 
/*     */   public void calcOutputT(double ttime, double inp)
/*     */   {
/* 261 */     if (ttime <= this.time[(this.sampLen - 1)]) throw new IllegalArgumentException("Current time equals or is less than previous time");
/* 262 */     this.deltaT = (ttime - this.time[(this.sampLen - 1)]);
/* 263 */     this.sampFreq = (1.0D / this.deltaT);
/* 264 */     super.deadTimeWarning("calcOutputT(time, input)");
/* 265 */     for (int i = 0; i < this.sampLen - 2; i++) {
/* 266 */       this.time[i] = this.time[(i + 1)];
/* 267 */       this.inputT[i] = this.inputT[(i + 1)];
/* 268 */       this.outputT[i] = this.outputT[(i + 1)];
/*     */     }
/* 270 */     this.time[(this.sampLen - 1)] = ttime;
/* 271 */     this.inputT[(this.sampLen - 1)] = inp;
/* 272 */     this.outputT[(this.sampLen - 1)] = NaN.0D;
/* 273 */     calcOutputT();
/*     */   }
/*     */   
/*     */   public void calcOutputT()
/*     */   {
/* 278 */     super.deadTimeWarning("calcOutputT()");
/*     */     
/* 280 */     this.outputT[(this.sampLen - 1)] = (this.kp * this.inputT[(this.sampLen - 1)]);
/*     */     
/* 282 */     if (this.forgetFactor == 1.0D)
/* 283 */       switch (this.integMethod) {
/*     */       case 0: 
/* 285 */         this.integrationSum += (this.inputT[(this.sampLen - 1)] + this.inputT[(this.sampLen - 2)]) * this.deltaT / 2.0D;
/* 286 */         break;
/*     */       case 1: 
/* 288 */         this.integrationSum += this.inputT[(this.sampLen - 1)] * this.deltaT;
/* 289 */         break;
/*     */       case 2: 
/* 291 */         this.integrationSum += this.inputT[(this.sampLen - 2)] * this.deltaT;
/* 292 */         break;
/* 293 */       default:  System.out.println("Integration method option in PropInt must be 0,1 or 2");
/* 294 */         System.out.println("It was set at " + this.integMethod);
/* 295 */         System.out.println("getOutput not performed");
/*     */         
/* 297 */         break;
/*     */       } else {
/* 299 */       switch (this.integMethod) {
/*     */       case 0: 
/* 301 */         this.integrationSum = 0.0D;
/* 302 */         for (int i = 1; i < this.sampLen; i++) {
/* 303 */           this.integrationSum += Math.pow(this.forgetFactor, this.sampLen - 1 - i) * (this.inputT[(i - 1)] + this.inputT[i]) * this.deltaT / 2.0D;
/*     */         }
/* 305 */         break;
/*     */       case 1: 
/* 307 */         this.integrationSum = 0.0D;
/* 308 */         for (int i = 1; i < this.sampLen; i++) {
/* 309 */           this.integrationSum += Math.pow(this.forgetFactor, this.sampLen - 1 - i) * this.inputT[i] * this.deltaT;
/*     */         }
/* 311 */         break;
/*     */       case 2: 
/* 313 */         this.integrationSum = 0.0D;
/* 314 */         for (int i = 1; i < this.sampLen; i++) {
/* 315 */           this.integrationSum += Math.pow(this.forgetFactor, this.sampLen - 1 - i) * this.inputT[(i - 1)] * this.deltaT;
/*     */         }
/* 317 */         break;
/* 318 */       default:  System.out.println("Integration method option in PropInt must be 0,1 or 2");
/* 319 */         System.out.println("It was set at " + this.integMethod);
/* 320 */         System.out.println("getOutput not performed");
/*     */       }
/*     */     }
/* 323 */     this.outputT[(this.sampLen - 1)] += this.ki * this.integrationSum;
/*     */   }
/*     */   
/*     */ 
/*     */   public PropInt copy()
/*     */   {
/* 329 */     if (this == null) {
/* 330 */       return null;
/*     */     }
/*     */     
/* 333 */     PropInt bb = new PropInt();
/*     */     
/* 335 */     bb.kp = this.kp;
/* 336 */     bb.ti = this.ti;
/* 337 */     bb.ki = this.ki;
/*     */     
/* 339 */     bb.sampLen = this.sampLen;
/* 340 */     bb.inputT = ((double[])this.inputT.clone());
/* 341 */     bb.outputT = ((double[])this.outputT.clone());
/* 342 */     bb.time = ((double[])this.time.clone());
/* 343 */     bb.forgetFactor = this.forgetFactor;
/* 344 */     bb.deltaT = this.deltaT;
/* 345 */     bb.sampFreq = this.sampFreq;
/* 346 */     bb.inputS = this.inputS.copy();
/* 347 */     bb.outputS = this.outputS.copy();
/* 348 */     bb.sValue = this.sValue.copy();
/* 349 */     bb.zValue = this.zValue.copy();
/* 350 */     bb.sNumer = this.sNumer.copy();
/* 351 */     bb.sDenom = this.sDenom.copy();
/* 352 */     bb.zNumer = this.zNumer.copy();
/* 353 */     bb.zDenom = this.zDenom.copy();
/* 354 */     bb.sPoles = Complex.copy(this.sPoles);
/* 355 */     bb.sZeros = Complex.copy(this.sZeros);
/* 356 */     bb.zPoles = Complex.copy(this.zPoles);
/* 357 */     bb.zZeros = Complex.copy(this.zZeros);
/* 358 */     bb.sNumerDeg = this.sNumerDeg;
/* 359 */     bb.sDenomDeg = this.sDenomDeg;
/* 360 */     bb.zNumerDeg = this.zNumerDeg;
/* 361 */     bb.zDenomDeg = this.zDenomDeg;
/* 362 */     bb.deadTime = this.deadTime;
/* 363 */     bb.orderPade = this.orderPade;
/* 364 */     bb.sNumerPade = this.sNumerPade.copy();
/* 365 */     bb.sDenomPade = this.sDenomPade.copy();
/* 366 */     bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 367 */     bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 368 */     bb.sNumerDegPade = this.sNumerDegPade;
/* 369 */     bb.sDenomDegPade = this.sDenomDegPade;
/* 370 */     bb.maptozero = this.maptozero;
/* 371 */     bb.padeAdded = this.padeAdded;
/* 372 */     bb.integrationSum = this.integrationSum;
/* 373 */     bb.integMethod = this.integMethod;
/* 374 */     bb.ztransMethod = this.ztransMethod;
/* 375 */     bb.name = this.name;
/* 376 */     bb.fixedName = this.fixedName;
/* 377 */     bb.nPlotPoints = this.nPlotPoints;
/*     */     
/* 379 */     return bb;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 386 */     Object ret = null;
/*     */     
/* 388 */     if (this != null) {
/* 389 */       PropInt bb = new PropInt();
/*     */       
/* 391 */       bb.kp = this.kp;
/* 392 */       bb.ti = this.ti;
/* 393 */       bb.ki = this.ki;
/*     */       
/* 395 */       bb.sampLen = this.sampLen;
/* 396 */       bb.inputT = ((double[])this.inputT.clone());
/* 397 */       bb.outputT = ((double[])this.outputT.clone());
/* 398 */       bb.time = ((double[])this.time.clone());
/* 399 */       bb.forgetFactor = this.forgetFactor;
/* 400 */       bb.deltaT = this.deltaT;
/* 401 */       bb.sampFreq = this.sampFreq;
/* 402 */       bb.inputS = this.inputS.copy();
/* 403 */       bb.outputS = this.outputS.copy();
/* 404 */       bb.sValue = this.sValue.copy();
/* 405 */       bb.zValue = this.zValue.copy();
/* 406 */       bb.sNumer = this.sNumer.copy();
/* 407 */       bb.sDenom = this.sDenom.copy();
/* 408 */       bb.zNumer = this.zNumer.copy();
/* 409 */       bb.zDenom = this.zDenom.copy();
/* 410 */       bb.sPoles = Complex.copy(this.sPoles);
/* 411 */       bb.sZeros = Complex.copy(this.sZeros);
/* 412 */       bb.zPoles = Complex.copy(this.zPoles);
/* 413 */       bb.zZeros = Complex.copy(this.zZeros);
/* 414 */       bb.sNumerDeg = this.sNumerDeg;
/* 415 */       bb.sDenomDeg = this.sDenomDeg;
/* 416 */       bb.zNumerDeg = this.zNumerDeg;
/* 417 */       bb.zDenomDeg = this.zDenomDeg;
/* 418 */       bb.deadTime = this.deadTime;
/* 419 */       bb.orderPade = this.orderPade;
/* 420 */       bb.sNumerPade = this.sNumerPade.copy();
/* 421 */       bb.sDenomPade = this.sDenomPade.copy();
/* 422 */       bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 423 */       bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 424 */       bb.sNumerDegPade = this.sNumerDegPade;
/* 425 */       bb.sDenomDegPade = this.sDenomDegPade;
/* 426 */       bb.maptozero = this.maptozero;
/* 427 */       bb.padeAdded = this.padeAdded;
/* 428 */       bb.integrationSum = this.integrationSum;
/* 429 */       bb.integMethod = this.integMethod;
/* 430 */       bb.ztransMethod = this.ztransMethod;
/* 431 */       bb.name = this.name;
/* 432 */       bb.fixedName = this.fixedName;
/* 433 */       bb.nPlotPoints = this.nPlotPoints;
/*     */       
/* 435 */       ret = bb;
/*     */     }
/* 437 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/control/PropInt.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */