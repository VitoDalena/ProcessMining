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
/*     */ public class PropDeriv
/*     */   extends BlackBox
/*     */ {
/*  43 */   private double kp = 1.0D;
/*  44 */   private double td = 0.0D;
/*  45 */   private double kd = 0.0D;
/*     */   
/*     */   public PropDeriv()
/*     */   {
/*  49 */     super("PD");
/*     */     
/*  51 */     this.sNumerDeg = 1;
/*  52 */     this.sDenomDeg = 0;
/*  53 */     super.setSnumer(new ComplexPoly(1.0D, 0.0D));
/*  54 */     super.setSdenom(new ComplexPoly(1.0D));
/*  55 */     super.setZtransformMethod(1);
/*  56 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setKp(double kp)
/*     */   {
/*  61 */     this.kp = kp;
/*  62 */     this.sNumer.resetCoeff(0, new Complex(this.kp, 0.0D));
/*  63 */     this.sZeros[0].reset(-this.kp / this.kd, 0.0D);
/*  64 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setKd(double kd)
/*     */   {
/*  69 */     this.kd = kd;
/*  70 */     this.td = (kd / this.kp);
/*  71 */     this.sNumer.resetCoeff(1, new Complex(this.kd, 0.0D));
/*  72 */     this.sZeros[0].reset(-this.kp / this.kd, 0.0D);
/*  73 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setTd(double td)
/*     */   {
/*  78 */     this.td = td;
/*  79 */     this.kd = (this.td * this.kp);
/*  80 */     this.sNumer.resetCoeff(1, new Complex(this.kd, 0.0D));
/*  81 */     this.sZeros[0].reset(-this.kp / this.kd, 0.0D);
/*  82 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public double getKp()
/*     */   {
/*  87 */     return this.kp;
/*     */   }
/*     */   
/*     */   public double getKd()
/*     */   {
/*  92 */     return this.kd;
/*     */   }
/*     */   
/*     */   public double getTd()
/*     */   {
/*  97 */     return this.td;
/*     */   }
/*     */   
/*     */   public void zTransform()
/*     */   {
/* 102 */     if (this.deltaT == 0.0D) System.out.println("z-transform attempted in PropDeriv with a zero sampling period");
/* 103 */     super.deadTimeWarning("zTransform");
/* 104 */     if (this.ztransMethod == 0) {
/* 105 */       mapstozAdHoc();
/*     */     }
/*     */     else {
/* 108 */       this.zNumerDeg = 1;
/* 109 */       this.zDenomDeg = 1;
/* 110 */       this.zNumer = new ComplexPoly(-this.kd, this.kp * this.deltaT + this.kd);
/* 111 */       this.zDenom = new ComplexPoly(0.0D, this.deltaT);
/* 112 */       this.zZeros = Complex.oneDarray(1);
/* 113 */       this.zZeros[0].reset(this.kd / (this.kp * this.deltaT + this.kd), 0.0D);
/* 114 */       this.zPoles = Complex.oneDarray(1);
/* 115 */       this.zPoles[0].reset(0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   public void zTransform(double deltaT)
/*     */   {
/* 121 */     this.deltaT = deltaT;
/* 122 */     super.deadTimeWarning("zTransform");
/* 123 */     zTransform();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void stepInput(double stepMag, double finalTime)
/*     */   {
/* 130 */     int n = 50;
/* 131 */     double incrT = finalTime / (n - 1);
/* 132 */     double[][] cdata = new double[2][n];
/*     */     
/* 134 */     cdata[0][0] = 0.0D;
/* 135 */     for (int i = 1; i < n; i++) {
/* 136 */       cdata[0][i] = (cdata[0][(i - 1)] + incrT);
/*     */     }
/* 138 */     double kpterm = this.kp * stepMag;
/* 139 */     for (int i = 0; i < n; i++) {
/* 140 */       cdata[1][i] = kpterm;
/*     */     }
/* 142 */     if (this.deadTime != 0.0D) { for (int i = 0; i < n; i++) { cdata[0][i] += this.deadTime;
/*     */       }
/*     */     }
/* 145 */     PlotGraph pg = new PlotGraph(cdata);
/*     */     
/* 147 */     pg.setGraphTitle("Step Input Transient:   Step magnitude = " + stepMag);
/* 148 */     pg.setGraphTitle2(getName());
/* 149 */     pg.setXaxisLegend("Time");
/* 150 */     pg.setXaxisUnitsName("s");
/* 151 */     pg.setYaxisLegend("Output");
/* 152 */     pg.setPoint(0);
/* 153 */     pg.plot();
/*     */   }
/*     */   
/*     */   public void stepInput(double finalTime)
/*     */   {
/* 158 */     stepInput(1.0D, finalTime);
/*     */   }
/*     */   
/*     */ 
/*     */   public void rampInput(double rampGradient, int rampOrder, double finalTime)
/*     */   {
/* 164 */     if (rampOrder == 0)
/*     */     {
/* 166 */       stepInput(rampGradient, finalTime);
/*     */     }
/*     */     else
/*     */     {
/* 170 */       int n = 50;
/* 171 */       double incrT = finalTime / (n - 1);
/* 172 */       double[][] cdata = new double[2][n];
/* 173 */       double sum = 0.0D;
/*     */       
/* 175 */       cdata[0][0] = 0.0D;
/* 176 */       cdata[1][0] = 0.0D;
/* 177 */       for (int i = 1; i < n; i++) {
/* 178 */         cdata[0][i] = (cdata[0][(i - 1)] + incrT);
/* 179 */         cdata[1][i] = (rampGradient * Math.pow(cdata[0][i], rampOrder - 1) * (this.kp * cdata[0][i] + this.kd));
/*     */       }
/* 181 */       if (this.deadTime != 0.0D) { for (int i = 0; i < n; i++) { cdata[0][i] += this.deadTime;
/*     */         }
/*     */       }
/*     */       
/* 185 */       PlotGraph pg = new PlotGraph(cdata);
/*     */       
/* 187 */       pg.setGraphTitle("Ramp (a.t^n) Input Transient:   ramp gradient (a) = " + rampGradient + " ramp order (n) = " + rampOrder);
/* 188 */       pg.setGraphTitle2(getName());
/* 189 */       pg.setXaxisLegend("Time");
/* 190 */       pg.setXaxisUnitsName("s");
/* 191 */       pg.setYaxisLegend("Output");
/* 192 */       pg.setPoint(0);
/* 193 */       pg.plot();
/*     */     }
/*     */   }
/*     */   
/*     */   public void rampInput(int rampOrder, double finalTime)
/*     */   {
/* 199 */     double rampGradient = 1.0D;
/* 200 */     rampInput(rampGradient, rampOrder, finalTime);
/*     */   }
/*     */   
/*     */   public void rampInput(double rampGradient, double finalTime)
/*     */   {
/* 205 */     int rampOrder = 1;
/* 206 */     rampInput(rampGradient, rampOrder, finalTime);
/*     */   }
/*     */   
/*     */   public void rampInput(double finalTime)
/*     */   {
/* 211 */     double rampGradient = 1.0D;
/* 212 */     int rampOrder = 1;
/* 213 */     rampInput(rampGradient, rampOrder, finalTime);
/*     */   }
/*     */   
/*     */   public Complex getOutputS(Complex sValue, Complex iinput)
/*     */   {
/* 218 */     this.sValue = sValue;
/* 219 */     this.inputS = iinput;
/* 220 */     Complex term = this.sValue.times(this.kd);
/* 221 */     term = term.plus(this.kp);
/* 222 */     this.outputS = term.times(this.inputS);
/* 223 */     if (this.deadTime != 0.0D) this.outputS = this.outputS.times(Complex.exp(this.sValue.times(-this.deadTime)));
/* 224 */     return this.outputS;
/*     */   }
/*     */   
/*     */   public Complex getOutputS()
/*     */   {
/* 229 */     Complex term = this.sValue.times(this.kd);
/* 230 */     term = term.plus(this.kp);
/* 231 */     this.outputS = term.times(this.inputS);
/* 232 */     if (this.deadTime != 0.0D) this.outputS = this.outputS.times(Complex.exp(this.sValue.times(-this.deadTime)));
/* 233 */     return this.outputS;
/*     */   }
/*     */   
/*     */ 
/*     */   public void calcOutputT(double ttime, double inp)
/*     */   {
/* 239 */     if (ttime <= this.time[(this.sampLen - 1)]) throw new IllegalArgumentException("Current time equals or is less than previous time");
/* 240 */     this.deltaT = (ttime - this.time[(this.sampLen - 1)]);
/* 241 */     this.sampFreq = (1.0D / this.deltaT);
/* 242 */     super.deadTimeWarning("calcOutputT(time, input)");
/* 243 */     for (int i = 0; i < this.sampLen - 2; i++) {
/* 244 */       this.time[i] = this.time[(i + 1)];
/* 245 */       this.inputT[i] = this.inputT[(i + 1)];
/* 246 */       this.outputT[i] = this.outputT[(i + 1)];
/*     */     }
/* 248 */     this.time[(this.sampLen - 1)] = ttime;
/* 249 */     this.inputT[(this.sampLen - 1)] = inp;
/* 250 */     this.outputT[(this.sampLen - 1)] = NaN.0D;
/* 251 */     calcOutputT();
/*     */   }
/*     */   
/*     */ 
/*     */   public void calcOutputT()
/*     */   {
/* 257 */     this.outputT[(this.sampLen - 1)] = (this.kp * this.inputT[(this.sampLen - 1)]);
/*     */     
/* 259 */     this.outputT[(this.sampLen - 1)] += this.kd * (this.inputT[(this.sampLen - 1)] - this.inputT[(this.sampLen - 2)]) / this.deltaT;
/*     */   }
/*     */   
/*     */   public Complex[] getSpoles()
/*     */   {
/* 264 */     System.out.println("PD controller has no s-domain poles");
/* 265 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public PropDeriv copy()
/*     */   {
/* 271 */     if (this == null) {
/* 272 */       return null;
/*     */     }
/*     */     
/* 275 */     PropDeriv bb = new PropDeriv();
/*     */     
/* 277 */     bb.kp = this.kp;
/* 278 */     bb.td = this.td;
/* 279 */     bb.kd = this.kd;
/*     */     
/* 281 */     bb.sampLen = this.sampLen;
/* 282 */     bb.inputT = ((double[])this.inputT.clone());
/* 283 */     bb.outputT = ((double[])this.outputT.clone());
/* 284 */     bb.time = ((double[])this.time.clone());
/* 285 */     bb.forgetFactor = this.forgetFactor;
/* 286 */     bb.deltaT = this.deltaT;
/* 287 */     bb.sampFreq = this.sampFreq;
/* 288 */     bb.inputS = this.inputS.copy();
/* 289 */     bb.outputS = this.outputS.copy();
/* 290 */     bb.sValue = this.sValue.copy();
/* 291 */     bb.zValue = this.zValue.copy();
/* 292 */     bb.sNumer = this.sNumer.copy();
/* 293 */     bb.sDenom = this.sDenom.copy();
/* 294 */     bb.zNumer = this.zNumer.copy();
/* 295 */     bb.zDenom = this.zDenom.copy();
/* 296 */     bb.sPoles = Complex.copy(this.sPoles);
/* 297 */     bb.sZeros = Complex.copy(this.sZeros);
/* 298 */     bb.zPoles = Complex.copy(this.zPoles);
/* 299 */     bb.zZeros = Complex.copy(this.zZeros);
/* 300 */     bb.sNumerDeg = this.sNumerDeg;
/* 301 */     bb.sDenomDeg = this.sDenomDeg;
/* 302 */     bb.zNumerDeg = this.zNumerDeg;
/* 303 */     bb.zDenomDeg = this.zDenomDeg;
/* 304 */     bb.deadTime = this.deadTime;
/* 305 */     bb.orderPade = this.orderPade;
/* 306 */     bb.sNumerPade = this.sNumerPade.copy();
/* 307 */     bb.sDenomPade = this.sDenomPade.copy();
/* 308 */     bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 309 */     bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 310 */     bb.sNumerDegPade = this.sNumerDegPade;
/* 311 */     bb.sDenomDegPade = this.sDenomDegPade;
/* 312 */     bb.maptozero = this.maptozero;
/* 313 */     bb.padeAdded = this.padeAdded;
/* 314 */     bb.integrationSum = this.integrationSum;
/* 315 */     bb.integMethod = this.integMethod;
/* 316 */     bb.ztransMethod = this.ztransMethod;
/* 317 */     bb.name = this.name;
/* 318 */     bb.fixedName = this.fixedName;
/* 319 */     bb.nPlotPoints = this.nPlotPoints;
/*     */     
/* 321 */     return bb;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 328 */     Object ret = null;
/*     */     
/* 330 */     if (this != null) {
/* 331 */       PropDeriv bb = new PropDeriv();
/*     */       
/* 333 */       bb.kp = this.kp;
/* 334 */       bb.td = this.td;
/* 335 */       bb.kd = this.kd;
/*     */       
/* 337 */       bb.sampLen = this.sampLen;
/* 338 */       bb.inputT = ((double[])this.inputT.clone());
/* 339 */       bb.outputT = ((double[])this.outputT.clone());
/* 340 */       bb.time = ((double[])this.time.clone());
/* 341 */       bb.forgetFactor = this.forgetFactor;
/* 342 */       bb.deltaT = this.deltaT;
/* 343 */       bb.sampFreq = this.sampFreq;
/* 344 */       bb.inputS = this.inputS.copy();
/* 345 */       bb.outputS = this.outputS.copy();
/* 346 */       bb.sValue = this.sValue.copy();
/* 347 */       bb.zValue = this.zValue.copy();
/* 348 */       bb.sNumer = this.sNumer.copy();
/* 349 */       bb.sDenom = this.sDenom.copy();
/* 350 */       bb.zNumer = this.zNumer.copy();
/* 351 */       bb.zDenom = this.zDenom.copy();
/* 352 */       bb.sPoles = Complex.copy(this.sPoles);
/* 353 */       bb.sZeros = Complex.copy(this.sZeros);
/* 354 */       bb.zPoles = Complex.copy(this.zPoles);
/* 355 */       bb.zZeros = Complex.copy(this.zZeros);
/* 356 */       bb.sNumerDeg = this.sNumerDeg;
/* 357 */       bb.sDenomDeg = this.sDenomDeg;
/* 358 */       bb.zNumerDeg = this.zNumerDeg;
/* 359 */       bb.zDenomDeg = this.zDenomDeg;
/* 360 */       bb.deadTime = this.deadTime;
/* 361 */       bb.orderPade = this.orderPade;
/* 362 */       bb.sNumerPade = this.sNumerPade.copy();
/* 363 */       bb.sDenomPade = this.sDenomPade.copy();
/* 364 */       bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 365 */       bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 366 */       bb.sNumerDegPade = this.sNumerDegPade;
/* 367 */       bb.sDenomDegPade = this.sDenomDegPade;
/* 368 */       bb.maptozero = this.maptozero;
/* 369 */       bb.padeAdded = this.padeAdded;
/* 370 */       bb.integrationSum = this.integrationSum;
/* 371 */       bb.integMethod = this.integMethod;
/* 372 */       bb.ztransMethod = this.ztransMethod;
/* 373 */       bb.name = this.name;
/* 374 */       bb.fixedName = this.fixedName;
/* 375 */       bb.nPlotPoints = this.nPlotPoints;
/*     */       
/* 377 */       ret = bb;
/*     */     }
/* 379 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/control/PropDeriv.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */