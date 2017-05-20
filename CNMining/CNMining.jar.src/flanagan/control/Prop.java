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
/*     */ public class Prop
/*     */   extends BlackBox
/*     */ {
/*  43 */   private double kp = 1.0D;
/*     */   
/*     */   public Prop()
/*     */   {
/*  47 */     super("P");
/*  48 */     super.setSnumer(new ComplexPoly(1.0D));
/*  49 */     super.setSdenom(new ComplexPoly(1.0D));
/*  50 */     super.setZtransformMethod(1);
/*  51 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public Prop(double kp)
/*     */   {
/*  56 */     super("P");
/*  57 */     this.kp = kp;
/*  58 */     super.setSnumer(new ComplexPoly(this.kp));
/*  59 */     super.setSdenom(new ComplexPoly(1.0D));
/*  60 */     super.setZtransformMethod(1);
/*  61 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setKp(double kp)
/*     */   {
/*  66 */     this.kp = kp;
/*  67 */     Complex num = new Complex(this.kp, 0.0D);
/*  68 */     this.sNumer.resetCoeff(0, num);
/*  69 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public double getKp()
/*     */   {
/*  74 */     return this.kp;
/*     */   }
/*     */   
/*     */   public void zTransform()
/*     */   {
/*  79 */     this.zNumerDeg = 0;
/*  80 */     this.zDenomDeg = 0;
/*  81 */     this.zNumer = new ComplexPoly(this.kp);
/*  82 */     this.zDenom = new ComplexPoly(1.0D);
/*     */   }
/*     */   
/*     */   public void zTransform(double deltaT)
/*     */   {
/*  87 */     this.deltaT = deltaT;
/*  88 */     zTransform();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void stepInput(double stepMag, double finalTime)
/*     */   {
/*  95 */     int n = 50;
/*  96 */     double incrT = finalTime / (n - 1);
/*  97 */     double[][] cdata = new double[2][n];
/*     */     
/*  99 */     cdata[0][0] = 0.0D;
/* 100 */     for (int i = 1; i < n; i++) {
/* 101 */       cdata[0][i] = (cdata[0][(i - 1)] + incrT);
/*     */     }
/* 103 */     double kpterm = this.kp * stepMag;
/* 104 */     for (int i = 0; i < n; i++) {
/* 105 */       cdata[1][i] = kpterm;
/*     */     }
/* 107 */     if (this.deadTime != 0.0D) { for (int i = 0; i < n; i++) { cdata[0][i] += this.deadTime;
/*     */       }
/*     */     }
/* 110 */     PlotGraph pg = new PlotGraph(cdata);
/*     */     
/* 112 */     pg.setGraphTitle("Step Input Transient:   Step magnitude = " + stepMag);
/* 113 */     pg.setGraphTitle2(getName());
/* 114 */     pg.setXaxisLegend("Time");
/* 115 */     pg.setXaxisUnitsName("s");
/* 116 */     pg.setYaxisLegend("Output");
/* 117 */     pg.setPoint(0);
/* 118 */     pg.plot();
/*     */   }
/*     */   
/*     */   public void stepInput(double finalTime)
/*     */   {
/* 123 */     stepInput(1.0D, finalTime);
/*     */   }
/*     */   
/*     */ 
/*     */   public void rampInput(double rampGradient, int rampOrder, double finalTime)
/*     */   {
/* 129 */     if (rampOrder == 0)
/*     */     {
/* 131 */       stepInput(rampGradient, finalTime);
/*     */     }
/*     */     else
/*     */     {
/* 135 */       int n = 50;
/* 136 */       double incrT = finalTime / (n - 1);
/* 137 */       double[][] cdata = new double[2][n];
/* 138 */       double sum = 0.0D;
/*     */       
/* 140 */       cdata[0][0] = 0.0D;
/* 141 */       cdata[1][0] = 0.0D;
/* 142 */       for (int i = 1; i < n; i++) {
/* 143 */         cdata[0][i] = (cdata[0][(i - 1)] + incrT);
/* 144 */         cdata[1][i] = (rampGradient * Math.pow(cdata[0][i], rampOrder) * this.kp);
/*     */       }
/* 146 */       if (this.deadTime != 0.0D) { for (int i = 0; i < n; i++) { cdata[0][i] += this.deadTime;
/*     */         }
/*     */       }
/* 149 */       PlotGraph pg = new PlotGraph(cdata);
/*     */       
/* 151 */       pg.setGraphTitle("Ramp (a.t^n) Input Transient:   ramp gradient (a) = " + rampGradient + " ramp order (n) = " + rampOrder);
/* 152 */       pg.setGraphTitle2(getName());
/* 153 */       pg.setXaxisLegend("Time");
/* 154 */       pg.setXaxisUnitsName("s");
/* 155 */       pg.setYaxisLegend("Output");
/* 156 */       pg.setPoint(0);
/* 157 */       pg.plot();
/*     */     }
/*     */   }
/*     */   
/*     */   public void rampInput(int rampOrder, double finalTime)
/*     */   {
/* 163 */     double rampGradient = 1.0D;
/* 164 */     rampInput(rampGradient, rampOrder, finalTime);
/*     */   }
/*     */   
/*     */   public void rampInput(double rampGradient, double finalTime)
/*     */   {
/* 169 */     int rampOrder = 1;
/* 170 */     rampInput(rampGradient, rampOrder, finalTime);
/*     */   }
/*     */   
/*     */   public void rampInput(double finalTime)
/*     */   {
/* 175 */     double rampGradient = 1.0D;
/* 176 */     int rampOrder = 1;
/* 177 */     rampInput(rampGradient, rampOrder, finalTime);
/*     */   }
/*     */   
/*     */   public Complex getOutputS(Complex sValue, Complex iinput)
/*     */   {
/* 182 */     this.sValue = sValue;
/* 183 */     this.inputS = iinput;
/* 184 */     this.outputS = this.inputS.times(this.kp);
/* 185 */     if (this.deadTime != 0.0D) this.outputS = this.outputS.times(Complex.exp(this.sValue.times(-this.deadTime)));
/* 186 */     return this.outputS;
/*     */   }
/*     */   
/*     */   public Complex getOutputS()
/*     */   {
/* 191 */     this.outputS = this.inputS.times(this.kp);
/* 192 */     if (this.deadTime != 0.0D) this.outputS = this.outputS.times(Complex.exp(this.sValue.times(-this.deadTime)));
/* 193 */     return this.outputS;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void calcOutputT(double ttime, double inp)
/*     */   {
/* 200 */     if (ttime <= this.time[(this.sampLen - 1)]) throw new IllegalArgumentException("Current time equals or is less than previous time");
/* 201 */     this.deltaT = (ttime - this.time[(this.sampLen - 1)]);
/* 202 */     this.sampFreq = (1.0D / this.deltaT);
/* 203 */     super.deadTimeWarning("calcOutputT(time, input)");
/* 204 */     for (int i = 0; i < this.sampLen - 2; i++) {
/* 205 */       this.time[i] = this.time[(i + 1)];
/* 206 */       this.inputT[i] = this.inputT[(i + 1)];
/* 207 */       this.outputT[i] = this.outputT[(i + 1)];
/*     */     }
/* 209 */     this.time[(this.sampLen - 1)] = ttime;
/* 210 */     this.inputT[(this.sampLen - 1)] = inp;
/* 211 */     this.outputT[(this.sampLen - 1)] = NaN.0D;
/* 212 */     calcOutputT();
/*     */   }
/*     */   
/*     */ 
/*     */   public void calcOutputT()
/*     */   {
/* 218 */     this.outputT[(this.sampLen - 1)] = (this.kp * this.inputT[(this.sampLen - 1)]);
/*     */   }
/*     */   
/*     */   public Complex[] getZerosS()
/*     */   {
/* 223 */     System.out.println("Proportional gain controller has no s-domain zeros");
/* 224 */     return null;
/*     */   }
/*     */   
/*     */   public Complex[] getPolesS()
/*     */   {
/* 229 */     System.out.println("Proportional gain controller has no s-domain poles");
/* 230 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public Prop copy()
/*     */   {
/* 236 */     if (this == null) {
/* 237 */       return null;
/*     */     }
/*     */     
/* 240 */     Prop bb = new Prop();
/*     */     
/* 242 */     bb.kp = this.kp;
/*     */     
/* 244 */     bb.sampLen = this.sampLen;
/* 245 */     bb.inputT = ((double[])this.inputT.clone());
/* 246 */     bb.outputT = ((double[])this.outputT.clone());
/* 247 */     bb.time = ((double[])this.time.clone());
/* 248 */     bb.forgetFactor = this.forgetFactor;
/* 249 */     bb.deltaT = this.deltaT;
/* 250 */     bb.sampFreq = this.sampFreq;
/* 251 */     bb.inputS = this.inputS.copy();
/* 252 */     bb.outputS = this.outputS.copy();
/* 253 */     bb.sValue = this.sValue.copy();
/* 254 */     bb.zValue = this.zValue.copy();
/* 255 */     bb.sNumer = this.sNumer.copy();
/* 256 */     bb.sDenom = this.sDenom.copy();
/* 257 */     bb.zNumer = this.zNumer.copy();
/* 258 */     bb.zDenom = this.zDenom.copy();
/* 259 */     bb.sPoles = Complex.copy(this.sPoles);
/* 260 */     bb.sZeros = Complex.copy(this.sZeros);
/* 261 */     bb.zPoles = Complex.copy(this.zPoles);
/* 262 */     bb.zZeros = Complex.copy(this.zZeros);
/* 263 */     bb.sNumerDeg = this.sNumerDeg;
/* 264 */     bb.sDenomDeg = this.sDenomDeg;
/* 265 */     bb.zNumerDeg = this.zNumerDeg;
/* 266 */     bb.zDenomDeg = this.zDenomDeg;
/* 267 */     bb.deadTime = this.deadTime;
/* 268 */     bb.orderPade = this.orderPade;
/* 269 */     bb.sNumerPade = this.sNumerPade.copy();
/* 270 */     bb.sDenomPade = this.sDenomPade.copy();
/* 271 */     bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 272 */     bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 273 */     bb.sNumerDegPade = this.sNumerDegPade;
/* 274 */     bb.sDenomDegPade = this.sDenomDegPade;
/* 275 */     bb.maptozero = this.maptozero;
/* 276 */     bb.padeAdded = this.padeAdded;
/* 277 */     bb.integrationSum = this.integrationSum;
/* 278 */     bb.integMethod = this.integMethod;
/* 279 */     bb.ztransMethod = this.ztransMethod;
/* 280 */     bb.name = this.name;
/* 281 */     bb.fixedName = this.fixedName;
/* 282 */     bb.nPlotPoints = this.nPlotPoints;
/*     */     
/* 284 */     return bb;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 291 */     Object ret = null;
/*     */     
/* 293 */     if (this != null) {
/* 294 */       Prop bb = new Prop();
/*     */       
/* 296 */       bb.kp = this.kp;
/*     */       
/* 298 */       bb.sampLen = this.sampLen;
/* 299 */       bb.inputT = ((double[])this.inputT.clone());
/* 300 */       bb.outputT = ((double[])this.outputT.clone());
/* 301 */       bb.time = ((double[])this.time.clone());
/* 302 */       bb.forgetFactor = this.forgetFactor;
/* 303 */       bb.deltaT = this.deltaT;
/* 304 */       bb.sampFreq = this.sampFreq;
/* 305 */       bb.inputS = this.inputS.copy();
/* 306 */       bb.outputS = this.outputS.copy();
/* 307 */       bb.sValue = this.sValue.copy();
/* 308 */       bb.zValue = this.zValue.copy();
/* 309 */       bb.sNumer = this.sNumer.copy();
/* 310 */       bb.sDenom = this.sDenom.copy();
/* 311 */       bb.zNumer = this.zNumer.copy();
/* 312 */       bb.zDenom = this.zDenom.copy();
/* 313 */       bb.sPoles = Complex.copy(this.sPoles);
/* 314 */       bb.sZeros = Complex.copy(this.sZeros);
/* 315 */       bb.zPoles = Complex.copy(this.zPoles);
/* 316 */       bb.zZeros = Complex.copy(this.zZeros);
/* 317 */       bb.sNumerDeg = this.sNumerDeg;
/* 318 */       bb.sDenomDeg = this.sDenomDeg;
/* 319 */       bb.zNumerDeg = this.zNumerDeg;
/* 320 */       bb.zDenomDeg = this.zDenomDeg;
/* 321 */       bb.deadTime = this.deadTime;
/* 322 */       bb.orderPade = this.orderPade;
/* 323 */       bb.sNumerPade = this.sNumerPade.copy();
/* 324 */       bb.sDenomPade = this.sDenomPade.copy();
/* 325 */       bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 326 */       bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 327 */       bb.sNumerDegPade = this.sNumerDegPade;
/* 328 */       bb.sDenomDegPade = this.sDenomDegPade;
/* 329 */       bb.maptozero = this.maptozero;
/* 330 */       bb.padeAdded = this.padeAdded;
/* 331 */       bb.integrationSum = this.integrationSum;
/* 332 */       bb.integMethod = this.integMethod;
/* 333 */       bb.ztransMethod = this.ztransMethod;
/* 334 */       bb.name = this.name;
/* 335 */       bb.fixedName = this.fixedName;
/* 336 */       bb.nPlotPoints = this.nPlotPoints;
/*     */       
/* 338 */       ret = bb;
/*     */     }
/* 340 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/control/Prop.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */