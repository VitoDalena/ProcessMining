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
/*     */ public class FirstOrder
/*     */   extends BlackBox
/*     */ {
/*  49 */   private double aConst = 1.0D;
/*  50 */   private double bConst = 1.0D;
/*  51 */   private double cConst = 1.0D;
/*     */   
/*     */ 
/*     */   public FirstOrder()
/*     */   {
/*  56 */     super("First Order Process");
/*  57 */     this.sPoles = Complex.oneDarray(1);
/*  58 */     super.setSnumer(new ComplexPoly(1.0D));
/*  59 */     super.setSdenom(new ComplexPoly(1.0D, 1.0D));
/*  60 */     super.setZtransformMethod(1);
/*  61 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */ 
/*     */   public FirstOrder(double aa, double bb, double cc)
/*     */   {
/*  67 */     super("First Order Process");
/*  68 */     this.aConst = aa;
/*  69 */     this.bConst = bb;
/*  70 */     this.cConst = cc;
/*  71 */     this.sPoles = Complex.oneDarray(1);
/*  72 */     super.setSnumer(new ComplexPoly(this.cConst));
/*  73 */     super.setSdenom(new ComplexPoly(this.bConst, this.aConst));
/*  74 */     super.setZtransformMethod(1);
/*  75 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setCoeff(double aa, double bb, double cc)
/*     */   {
/*  80 */     this.aConst = aa;
/*  81 */     this.bConst = bb;
/*  82 */     this.cConst = cc;
/*  83 */     Complex[] num = Complex.oneDarray(1);
/*  84 */     num[0].reset(this.cConst, 0.0D);
/*  85 */     this.sNumer.resetPoly(num);
/*  86 */     Complex[] den = Complex.oneDarray(2);
/*  87 */     den[0].reset(this.bConst, 0.0D);
/*  88 */     den[1].reset(this.aConst, 0.0D);
/*  89 */     this.sDenom.resetPoly(den);
/*  90 */     calcPolesZerosS();
/*  91 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setA(double aa) {
/*  95 */     this.aConst = aa;
/*  96 */     Complex co = new Complex(this.aConst, 0.0D);
/*  97 */     this.sDenom.resetCoeff(1, co);
/*  98 */     calcPolesZerosS();
/*  99 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setB(double bb) {
/* 103 */     this.bConst = bb;
/* 104 */     Complex co = new Complex(this.bConst, 0.0D);
/* 105 */     this.sDenom.resetCoeff(0, co);
/* 106 */     calcPolesZerosS();
/* 107 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public void setC(double cc) {
/* 111 */     this.cConst = cc;
/* 112 */     Complex co = new Complex(this.cConst, 0.0D);
/* 113 */     this.sNumer.resetCoeff(0, co);
/* 114 */     calcPolesZerosS();
/* 115 */     super.addDeadTimeExtras();
/*     */   }
/*     */   
/*     */   public double getA()
/*     */   {
/* 120 */     return this.aConst;
/*     */   }
/*     */   
/*     */   public double getB() {
/* 124 */     return this.bConst;
/*     */   }
/*     */   
/*     */   public double getC() {
/* 128 */     return this.cConst;
/*     */   }
/*     */   
/*     */   public double getTimeConstant()
/*     */   {
/* 133 */     return this.aConst / this.bConst;
/*     */   }
/*     */   
/*     */   protected void calcPolesZerosS()
/*     */   {
/* 138 */     this.sPoles = Complex.oneDarray(1);
/* 139 */     this.sPoles[0].setReal(-this.bConst / this.aConst);
/*     */   }
/*     */   
/*     */   public void zTransform()
/*     */   {
/* 144 */     if (this.deltaT == 0.0D) System.out.println("z-transform attempted in FirstOrder with a zero sampling period");
/* 145 */     super.deadTimeWarning("zTransform");
/* 146 */     if (this.ztransMethod == 0) {
/* 147 */       mapstozAdHoc();
/*     */     }
/*     */     else {
/* 150 */       Complex[] ncoef = null;
/* 151 */       Complex[] dcoef = null;
/* 152 */       switch (this.integMethod) {
/*     */       case 0: 
/* 154 */         ncoef = Complex.oneDarray(2);
/* 155 */         ncoef[0].reset(this.deltaT * this.cConst, 0.0D);
/* 156 */         ncoef[1].reset(this.deltaT * this.cConst, 0.0D);
/* 157 */         this.zNumer = new ComplexPoly(1);
/* 158 */         this.zNumer.resetPoly(ncoef);
/* 159 */         this.zNumerDeg = 1;
/* 160 */         dcoef = Complex.oneDarray(2);
/* 161 */         dcoef[0].reset(this.bConst * this.deltaT - 2.0D * this.aConst, 0.0D);
/* 162 */         dcoef[1].reset(this.bConst * this.deltaT + 2.0D * this.aConst, 0.0D);
/* 163 */         this.zDenom = new ComplexPoly(1);
/* 164 */         this.zDenom.resetPoly(dcoef);
/* 165 */         this.zDenomDeg = 1;
/* 166 */         this.zZeros = Complex.oneDarray(1);
/* 167 */         this.zZeros[0].reset(-1.0D, 0.0D);
/* 168 */         this.zPoles = Complex.oneDarray(1);
/* 169 */         this.zPoles[0].reset((2.0D * this.aConst - this.deltaT * this.bConst) / (2.0D * this.aConst + this.deltaT * this.bConst), 0.0D);
/* 170 */         break;
/*     */       case 1: 
/* 172 */         ncoef = Complex.oneDarray(2);
/* 173 */         ncoef[0].reset(0.0D, 0.0D);
/* 174 */         ncoef[1].reset(this.cConst * this.deltaT, 0.0D);
/* 175 */         this.zNumer = new ComplexPoly(1);
/* 176 */         this.zNumer.resetPoly(ncoef);
/* 177 */         this.zNumerDeg = 1;
/* 178 */         dcoef = Complex.oneDarray(2);
/* 179 */         dcoef[0].reset(this.bConst * this.deltaT + this.aConst, 0.0D);
/* 180 */         dcoef[1].reset(this.aConst, 0.0D);
/* 181 */         this.zDenom = new ComplexPoly(1);
/* 182 */         this.zDenom.resetPoly(dcoef);
/* 183 */         this.zDenomDeg = 1;
/* 184 */         this.zZeros = Complex.oneDarray(1);
/* 185 */         this.zZeros[0].reset(0.0D, 0.0D);
/* 186 */         this.zPoles = Complex.oneDarray(1);
/* 187 */         this.zPoles[0].reset(this.aConst / (this.deltaT * this.bConst + this.aConst), 0.0D);
/* 188 */         break;
/*     */       case 2: 
/* 190 */         ncoef = Complex.oneDarray(1);
/* 191 */         ncoef[0].reset(this.cConst * this.deltaT, 0.0D);
/* 192 */         this.zNumer = new ComplexPoly(0);
/* 193 */         this.zNumer.resetPoly(ncoef);
/* 194 */         this.zNumerDeg = 0;
/* 195 */         dcoef = Complex.oneDarray(2);
/* 196 */         dcoef[0].reset(-this.aConst, 0.0D);
/* 197 */         dcoef[1].reset(this.bConst * this.deltaT - this.aConst, 0.0D);
/* 198 */         this.zDenom = new ComplexPoly(1);
/* 199 */         this.zDenom.resetPoly(dcoef);
/* 200 */         this.zDenomDeg = 1;
/* 201 */         this.zPoles = Complex.oneDarray(1);
/* 202 */         this.zPoles[0].reset(this.aConst / (this.deltaT * this.bConst - this.aConst), 0.0D);
/* 203 */         break;
/* 204 */       default:  System.out.println("Integration method option in FirstOrder must be 0,1 or 2");
/* 205 */         System.out.println("It was set at " + this.integMethod);
/* 206 */         System.out.println("z-transform not performed");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void zTransform(double deltaT)
/*     */   {
/* 213 */     this.deltaT = deltaT;
/* 214 */     zTransform();
/*     */   }
/*     */   
/*     */   public Complex getOutputS(Complex sValue, Complex iinput)
/*     */   {
/* 219 */     this.sValue = sValue;
/* 220 */     this.inputS = iinput;
/* 221 */     return getOutputS();
/*     */   }
/*     */   
/*     */   public Complex getOutputS()
/*     */   {
/* 226 */     Complex num = Complex.plusOne();
/* 227 */     num = num.times(this.cConst);
/* 228 */     Complex den = new Complex();
/* 229 */     den = this.sValue.times(this.aConst);
/* 230 */     den = den.plus(this.bConst);
/* 231 */     Complex term = new Complex();
/* 232 */     term = num.over(den);
/* 233 */     this.outputS = term.times(this.inputS);
/* 234 */     if (this.deadTime != 0.0D) this.outputS = this.outputS.times(Complex.exp(this.sValue.times(-this.deadTime)));
/* 235 */     return this.outputS;
/*     */   }
/*     */   
/*     */ 
/*     */   public void calcOutputT(double ttime, double inp)
/*     */   {
/* 241 */     if (ttime <= this.time[(this.sampLen - 1)]) throw new IllegalArgumentException("Current time equals or is less than previous time");
/* 242 */     this.deltaT = (ttime - this.time[(this.sampLen - 1)]);
/* 243 */     this.sampFreq = (1.0D / this.deltaT);
/* 244 */     super.deadTimeWarning("calcOutputT(time, input)");
/* 245 */     for (int i = 0; i < this.sampLen - 2; i++) {
/* 246 */       this.time[i] = this.time[(i + 1)];
/* 247 */       this.inputT[i] = this.inputT[(i + 1)];
/* 248 */       this.outputT[i] = this.outputT[(i + 1)];
/*     */     }
/* 250 */     this.time[(this.sampLen - 1)] = ttime;
/* 251 */     this.inputT[(this.sampLen - 1)] = inp;
/* 252 */     this.outputT[(this.sampLen - 1)] = NaN.0D;
/* 253 */     calcOutputT();
/*     */   }
/*     */   
/*     */   public void calcOutputT()
/*     */   {
/* 258 */     super.deadTimeWarning("calcOutputT()");
/* 259 */     this.outputT[(this.sampLen - 1)] = ((this.bConst * this.inputT[(this.sampLen - 1)] + this.aConst * (this.inputT[(this.sampLen - 1)] - this.inputT[(this.sampLen - 2)]) / this.deltaT) / this.cConst);
/*     */   }
/*     */   
/*     */ 
/*     */   public Complex[] getSzeros()
/*     */   {
/* 265 */     System.out.println("This standard first order process (class FirstOrder) has no s-domain zeros");
/* 266 */     return null;
/*     */   }
/*     */   
/*     */   public FirstOrder copy()
/*     */   {
/* 271 */     if (this == null) {
/* 272 */       return null;
/*     */     }
/*     */     
/* 275 */     FirstOrder bb = new FirstOrder();
/*     */     
/* 277 */     bb.aConst = this.aConst;
/* 278 */     bb.bConst = this.bConst;
/* 279 */     bb.cConst = this.cConst;
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
/*     */   public Object clone()
/*     */   {
/* 327 */     Object ret = null;
/*     */     
/* 329 */     if (this != null) {
/* 330 */       FirstOrder bb = new FirstOrder();
/*     */       
/* 332 */       bb.aConst = this.aConst;
/* 333 */       bb.bConst = this.bConst;
/* 334 */       bb.cConst = this.cConst;
/*     */       
/* 336 */       bb.sampLen = this.sampLen;
/* 337 */       bb.inputT = ((double[])this.inputT.clone());
/* 338 */       bb.outputT = ((double[])this.outputT.clone());
/* 339 */       bb.time = ((double[])this.time.clone());
/* 340 */       bb.forgetFactor = this.forgetFactor;
/* 341 */       bb.deltaT = this.deltaT;
/* 342 */       bb.sampFreq = this.sampFreq;
/* 343 */       bb.inputS = this.inputS.copy();
/* 344 */       bb.outputS = this.outputS.copy();
/* 345 */       bb.sValue = this.sValue.copy();
/* 346 */       bb.zValue = this.zValue.copy();
/* 347 */       bb.sNumer = this.sNumer.copy();
/* 348 */       bb.sDenom = this.sDenom.copy();
/* 349 */       bb.zNumer = this.zNumer.copy();
/* 350 */       bb.zDenom = this.zDenom.copy();
/* 351 */       bb.sPoles = Complex.copy(this.sPoles);
/* 352 */       bb.sZeros = Complex.copy(this.sZeros);
/* 353 */       bb.zPoles = Complex.copy(this.zPoles);
/* 354 */       bb.zZeros = Complex.copy(this.zZeros);
/* 355 */       bb.sNumerDeg = this.sNumerDeg;
/* 356 */       bb.sDenomDeg = this.sDenomDeg;
/* 357 */       bb.zNumerDeg = this.zNumerDeg;
/* 358 */       bb.zDenomDeg = this.zDenomDeg;
/* 359 */       bb.deadTime = this.deadTime;
/* 360 */       bb.orderPade = this.orderPade;
/* 361 */       bb.sNumerPade = this.sNumerPade.copy();
/* 362 */       bb.sDenomPade = this.sDenomPade.copy();
/* 363 */       bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 364 */       bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 365 */       bb.sNumerDegPade = this.sNumerDegPade;
/* 366 */       bb.sDenomDegPade = this.sDenomDegPade;
/* 367 */       bb.maptozero = this.maptozero;
/* 368 */       bb.padeAdded = this.padeAdded;
/* 369 */       bb.integrationSum = this.integrationSum;
/* 370 */       bb.integMethod = this.integMethod;
/* 371 */       bb.ztransMethod = this.ztransMethod;
/* 372 */       bb.name = this.name;
/* 373 */       bb.fixedName = this.fixedName;
/* 374 */       bb.nPlotPoints = this.nPlotPoints;
/*     */       
/* 376 */       ret = bb;
/*     */     }
/* 378 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/control/FirstOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */