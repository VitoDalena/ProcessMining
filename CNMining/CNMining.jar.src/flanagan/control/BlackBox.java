/*      */ package flanagan.control;
/*      */ 
/*      */ import flanagan.complex.Complex;
/*      */ import flanagan.complex.ComplexMatrix;
/*      */ import flanagan.complex.ComplexPoly;
/*      */ import flanagan.math.Fmath;
/*      */ import flanagan.plot.PlotGraph;
/*      */ import flanagan.plot.PlotPoleZero;
/*      */ import java.io.PrintStream;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class BlackBox
/*      */ {
/*   56 */   protected int sampLen = 3;
/*   57 */   protected double[] inputT = new double[this.sampLen];
/*   58 */   protected double[] outputT = new double[this.sampLen];
/*   59 */   protected double[] time = new double[this.sampLen];
/*   60 */   protected double forgetFactor = 1.0D;
/*   61 */   protected double deltaT = 0.0D;
/*   62 */   protected double sampFreq = 0.0D;
/*   63 */   protected Complex inputS = new Complex();
/*   64 */   protected Complex outputS = new Complex();
/*   65 */   protected Complex sValue = new Complex();
/*   66 */   protected Complex zValue = new Complex();
/*   67 */   protected ComplexPoly sNumer = new ComplexPoly(1.0D);
/*   68 */   protected ComplexPoly sDenom = new ComplexPoly(1.0D);
/*   69 */   protected ComplexPoly zNumer = new ComplexPoly(1.0D);
/*   70 */   protected ComplexPoly zDenom = new ComplexPoly(1.0D);
/*   71 */   protected Complex[] sPoles = null;
/*   72 */   protected Complex[] sZeros = null;
/*   73 */   protected Complex[] zPoles = null;
/*   74 */   protected Complex[] zZeros = null;
/*   75 */   protected int sNumerDeg = 0;
/*   76 */   protected int sDenomDeg = 0;
/*   77 */   protected int zNumerDeg = 0;
/*   78 */   protected int zDenomDeg = 0;
/*   79 */   protected double deadTime = 0.0D;
/*   80 */   protected int orderPade = 2;
/*      */   
/*   82 */   protected ComplexPoly sNumerPade = new ComplexPoly(1.0D);
/*   83 */   protected ComplexPoly sDenomPade = new ComplexPoly(1.0D);
/*   84 */   protected Complex[] sPolesPade = null;
/*   85 */   protected Complex[] sZerosPade = null;
/*   86 */   protected int sNumerDegPade = 0;
/*   87 */   protected int sDenomDegPade = 0;
/*   88 */   protected boolean maptozero = true;
/*      */   
/*   90 */   protected boolean padeAdded = false;
/*      */   
/*   92 */   protected double integrationSum = 0.0D;
/*   93 */   protected int integMethod = 0;
/*      */   
/*      */ 
/*      */ 
/*   97 */   protected int ztransMethod = 0;
/*      */   
/*      */ 
/*  100 */   protected String name = "BlackBox";
/*      */   
/*  102 */   protected String fixedName = "BlackBox";
/*      */   
/*      */ 
/*  105 */   protected int nPlotPoints = 100;
/*      */   
/*      */ 
/*      */   public BlackBox() {}
/*      */   
/*      */ 
/*      */   public BlackBox(String name)
/*      */   {
/*  113 */     this.name = name;
/*  114 */     this.fixedName = name;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setSnumer(double[] coeff)
/*      */   {
/*  120 */     this.sNumerDeg = (coeff.length - 1);
/*  121 */     this.sNumer = new ComplexPoly(coeff);
/*  122 */     calcPolesZerosS();
/*  123 */     addDeadTimeExtras();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected void addDeadTimeExtras()
/*      */   {
/*  130 */     this.sNumerDegPade = this.sNumerDeg;
/*  131 */     this.sNumerPade = this.sNumer.copy();
/*  132 */     this.sDenomDegPade = this.sDenomDeg;
/*  133 */     this.sDenomPade = this.sDenom.copy();
/*  134 */     if (this.deadTime == 0.0D) {
/*  135 */       transferPolesZeros();
/*      */     }
/*      */     else {
/*  138 */       pade();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setSnumer(Complex[] coeff)
/*      */   {
/*  145 */     this.sNumerDeg = (coeff.length - 1);
/*  146 */     this.sNumer = new ComplexPoly(coeff);
/*  147 */     calcPolesZerosS();
/*  148 */     addDeadTimeExtras();
/*      */   }
/*      */   
/*      */ 
/*      */   public void setSnumer(ComplexPoly coeff)
/*      */   {
/*  154 */     this.sNumerDeg = coeff.getDeg();
/*  155 */     this.sNumer = ComplexPoly.copy(coeff);
/*  156 */     calcPolesZerosS();
/*  157 */     addDeadTimeExtras();
/*      */   }
/*      */   
/*      */ 
/*      */   public void setSdenom(double[] coeff)
/*      */   {
/*  163 */     this.sDenomDeg = (coeff.length - 1);
/*  164 */     this.sDenom = new ComplexPoly(coeff);
/*  165 */     calcPolesZerosS();
/*  166 */     addDeadTimeExtras();
/*      */   }
/*      */   
/*      */ 
/*      */   public void setSdenom(Complex[] coeff)
/*      */   {
/*  172 */     this.sDenomDeg = (coeff.length - 1);
/*  173 */     this.sDenom = new ComplexPoly(coeff);
/*  174 */     calcPolesZerosS();
/*  175 */     addDeadTimeExtras();
/*      */   }
/*      */   
/*      */ 
/*      */   public void setSdenom(ComplexPoly coeff)
/*      */   {
/*  181 */     this.sDenomDeg = coeff.getDeg();
/*  182 */     this.sDenom = coeff.copy();
/*  183 */     calcPolesZerosS();
/*  184 */     addDeadTimeExtras();
/*      */   }
/*      */   
/*      */   public void setDeadTime(double deadtime)
/*      */   {
/*  189 */     this.deadTime = deadtime;
/*  190 */     pade();
/*      */   }
/*      */   
/*      */   public void setDeadTime(double deadtime, int orderPade)
/*      */   {
/*  195 */     this.deadTime = deadtime;
/*  196 */     if (orderPade > 5) {
/*  197 */       orderPade = 4;
/*  198 */       System.out.println("BlackBox does not support Pade approximations above an order of 4");
/*  199 */       System.out.println("The order has been set to 4");
/*      */     }
/*  201 */     if (orderPade < 1) {
/*  202 */       orderPade = 1;
/*  203 */       System.out.println("Pade approximation order was less than 1");
/*  204 */       System.out.println("The order has been set to 1");
/*      */     }
/*  206 */     this.orderPade = orderPade;
/*  207 */     pade();
/*      */   }
/*      */   
/*      */   public void setPadeOrder(int orderPade)
/*      */   {
/*  212 */     if (orderPade > 5) {
/*  213 */       orderPade = 4;
/*  214 */       System.out.println("BlackBox does not support Pade approximations above an order of 4");
/*  215 */       System.out.println("The order has been set to 4");
/*      */     }
/*  217 */     if (orderPade < 1) {
/*  218 */       orderPade = 2;
/*  219 */       System.out.println("Pade approximation order was less than 1");
/*  220 */       System.out.println("The order has been set to 2");
/*      */     }
/*  222 */     this.orderPade = orderPade;
/*  223 */     pade();
/*      */   }
/*      */   
/*      */   public double getDeadTime()
/*      */   {
/*  228 */     return this.deadTime;
/*      */   }
/*      */   
/*      */   public int getPadeOrder()
/*      */   {
/*  233 */     return this.orderPade;
/*      */   }
/*      */   
/*      */ 
/*      */   protected void pade()
/*      */   {
/*  239 */     ComplexPoly sNumerExtra = null;
/*  240 */     ComplexPoly sDenomExtra = null;
/*  241 */     Complex[] newZeros = null;
/*  242 */     Complex[] newPoles = null;
/*  243 */     switch (this.orderPade) {
/*  244 */     case 1:  this.sNumerDegPade = (this.sNumerDeg + 1);
/*  245 */       this.sDenomDegPade = (this.sDenomDeg + 1);
/*  246 */       this.sNumerPade = new ComplexPoly(this.sNumerDegPade);
/*  247 */       this.sDenomPade = new ComplexPoly(this.sDenomDegPade);
/*  248 */       sNumerExtra = new ComplexPoly(1.0D, -this.deadTime / 2.0D);
/*  249 */       sDenomExtra = new ComplexPoly(1.0D, this.deadTime / 2.0D);
/*  250 */       this.sNumerPade = this.sNumer.times(sNumerExtra);
/*  251 */       this.sDenomPade = this.sDenom.times(sDenomExtra);
/*  252 */       newZeros = Complex.oneDarray(1);
/*  253 */       newZeros[0].reset(2.0D / this.deadTime, 0.0D);
/*  254 */       newPoles = Complex.oneDarray(1);
/*  255 */       newPoles[0].reset(-2.0D / this.deadTime, 0.0D);
/*  256 */       break;
/*  257 */     case 2:  this.sNumerDegPade = (this.sNumerDeg + 2);
/*  258 */       this.sDenomDegPade = (this.sDenomDeg + 2);
/*  259 */       this.sNumerPade = new ComplexPoly(this.sNumerDegPade);
/*  260 */       this.sDenomPade = new ComplexPoly(this.sDenomDegPade);
/*  261 */       sNumerExtra = new ComplexPoly(1.0D, -this.deadTime / 2.0D, Math.pow(this.deadTime, 2.0D) / 12.0D);
/*  262 */       sDenomExtra = new ComplexPoly(1.0D, this.deadTime / 2.0D, Math.pow(this.deadTime, 2.0D) / 12.0D);
/*  263 */       this.sNumerPade = this.sNumer.times(sNumerExtra);
/*  264 */       this.sDenomPade = this.sDenom.times(sDenomExtra);
/*  265 */       newZeros = sNumerExtra.roots();
/*  266 */       newPoles = sDenomExtra.roots();
/*  267 */       break;
/*  268 */     case 3:  this.sNumerDegPade = (this.sNumerDeg + 3);
/*  269 */       this.sDenomDegPade = (this.sDenomDeg + 3);
/*  270 */       this.sNumerPade = new ComplexPoly(this.sNumerDegPade);
/*  271 */       this.sDenomPade = new ComplexPoly(this.sDenomDegPade);
/*  272 */       double[] termn3 = new double[4];
/*  273 */       termn3[0] = 1.0D;
/*  274 */       termn3[1] = (-this.deadTime / 2.0D);
/*  275 */       termn3[2] = (Math.pow(this.deadTime, 2.0D) / 10.0D);
/*  276 */       termn3[3] = (-Math.pow(this.deadTime, 3.0D) / 120.0D);
/*  277 */       sNumerExtra = new ComplexPoly(termn3);
/*  278 */       this.sNumerPade = this.sNumer.times(sNumerExtra);
/*  279 */       newZeros = sNumerExtra.roots();
/*  280 */       double[] termd3 = new double[4];
/*  281 */       termd3[0] = 1.0D;
/*  282 */       termd3[1] = (this.deadTime / 2.0D);
/*  283 */       termd3[2] = (Math.pow(this.deadTime, 2.0D) / 10.0D);
/*  284 */       termd3[3] = (Math.pow(this.deadTime, 3.0D) / 120.0D);
/*  285 */       sDenomExtra = new ComplexPoly(termd3);
/*  286 */       this.sDenomPade = this.sDenom.times(sDenomExtra);
/*  287 */       newPoles = sDenomExtra.roots();
/*  288 */       break;
/*  289 */     case 4:  this.sNumerDegPade = (this.sNumerDeg + 4);
/*  290 */       this.sDenomDegPade = (this.sDenomDeg + 4);
/*  291 */       this.sNumerPade = new ComplexPoly(this.sNumerDegPade);
/*  292 */       this.sDenomPade = new ComplexPoly(this.sDenomDegPade);
/*  293 */       double[] termn4 = new double[5];
/*  294 */       termn4[0] = 1.0D;
/*  295 */       termn4[1] = (-this.deadTime / 2.0D);
/*  296 */       termn4[2] = (3.0D * Math.pow(this.deadTime, 2.0D) / 28.0D);
/*  297 */       termn4[3] = (-Math.pow(this.deadTime, 3.0D) / 84.0D);
/*  298 */       termn4[4] = (Math.pow(this.deadTime, 4.0D) / 1680.0D);
/*  299 */       sNumerExtra = new ComplexPoly(termn4);
/*  300 */       this.sNumerPade = this.sNumer.times(sNumerExtra);
/*  301 */       newZeros = sNumerExtra.roots();
/*  302 */       double[] termd4 = new double[5];
/*  303 */       termd4[0] = 1.0D;
/*  304 */       termd4[1] = (this.deadTime / 2.0D);
/*  305 */       termd4[2] = (3.0D * Math.pow(this.deadTime, 2.0D) / 28.0D);
/*  306 */       termd4[3] = (Math.pow(this.deadTime, 3.0D) / 84.0D);
/*  307 */       termd4[4] = (Math.pow(this.deadTime, 4.0D) / 1680.0D);
/*  308 */       sDenomExtra = new ComplexPoly(termd4);
/*  309 */       this.sDenomPade = this.sDenom.times(sDenomExtra);
/*  310 */       newPoles = sDenomExtra.roots();
/*  311 */       break;
/*  312 */     default:  this.orderPade = 2;
/*  313 */       this.sNumerDegPade = (this.sNumerDeg + 2);
/*  314 */       this.sDenomDegPade = (this.sDenomDeg + 2);
/*  315 */       this.sNumerPade = new ComplexPoly(this.sNumerDegPade);
/*  316 */       this.sDenomPade = new ComplexPoly(this.sDenomDegPade);
/*  317 */       sNumerExtra = new ComplexPoly(1.0D, -this.deadTime / 2.0D, Math.pow(this.deadTime, 2.0D) / 12.0D);
/*  318 */       sDenomExtra = new ComplexPoly(1.0D, this.deadTime / 2.0D, Math.pow(this.deadTime, 2.0D) / 12.0D);
/*  319 */       this.sNumerPade = this.sNumer.times(sNumerExtra);
/*  320 */       this.sDenomPade = this.sDenom.times(sDenomExtra);
/*  321 */       newZeros = sNumerExtra.roots();
/*  322 */       newPoles = sDenomExtra.roots();
/*      */     }
/*      */     
/*      */     
/*      */ 
/*  327 */     if ((this.sNumerPade != null) && (this.sNumerDegPade > 0)) {
/*  328 */       this.sZerosPade = Complex.oneDarray(this.sNumerDegPade);
/*  329 */       for (int i = 0; i < this.sNumerDeg; i++) {
/*  330 */         this.sZerosPade[i] = this.sZeros[i].copy();
/*      */       }
/*  332 */       for (int i = 0; i < this.orderPade; i++) {
/*  333 */         this.sZerosPade[(i + this.sNumerDeg)] = newZeros[i].copy();
/*      */       }
/*      */     }
/*      */     
/*  337 */     if ((this.sDenomPade != null) && (this.sDenomDegPade > 0)) {
/*  338 */       this.sPolesPade = Complex.oneDarray(this.sDenomDegPade);
/*  339 */       for (int i = 0; i < this.sDenomDeg; i++) {
/*  340 */         this.sPolesPade[i] = this.sPoles[i].copy();
/*      */       }
/*  342 */       for (int i = 0; i < this.orderPade; i++) {
/*  343 */         this.sPolesPade[(i + this.sDenomDeg)] = newPoles[i].copy();
/*      */       }
/*      */     }
/*  346 */     zeroPoleCancellation();
/*  347 */     this.padeAdded = true;
/*      */   }
/*      */   
/*      */ 
/*      */   protected void transferPolesZeros()
/*      */   {
/*  353 */     this.sNumerDegPade = this.sNumerDeg;
/*  354 */     this.sNumerPade = this.sNumer.copy();
/*  355 */     if (this.sNumerDeg > 0) {
/*  356 */       this.sZerosPade = Complex.oneDarray(this.sNumerDeg);
/*  357 */       for (int i = 0; i < this.sNumerDeg; i++) { this.sZerosPade[i] = this.sZeros[i].copy();
/*      */       }
/*      */     }
/*  360 */     this.sDenomDegPade = this.sDenomDeg;
/*  361 */     this.sDenomPade = this.sDenom.copy();
/*  362 */     if (this.sDenomDeg > 0) {
/*  363 */       this.sPolesPade = Complex.oneDarray(this.sDenomDeg);
/*  364 */       for (int i = 0; i < this.sDenomDeg; i++) this.sPolesPade[i] = this.sPoles[i].copy();
/*      */     }
/*  366 */     zeroPoleCancellation();
/*  367 */     this.padeAdded = true;
/*      */   }
/*      */   
/*      */   public int orderPade()
/*      */   {
/*  372 */     return this.orderPade;
/*      */   }
/*      */   
/*      */   protected boolean deadTimeWarning(String method)
/*      */   {
/*  377 */     boolean warning = false;
/*      */     
/*  379 */     if (this.deadTime > this.deltaT) {
/*  380 */       System.out.println(this.name + "." + method + ": The dead time is greater than the sampling period");
/*  381 */       System.out.println("Dead time:       " + this.deadTime);
/*  382 */       System.out.println("Sampling period: " + this.deltaT);
/*  383 */       System.out.println("!!! The results of this program may not be physically meaningful !!!");
/*  384 */       warning = true;
/*      */     }
/*  386 */     return warning;
/*      */   }
/*      */   
/*      */ 
/*      */   public void zTransform(double deltat)
/*      */   {
/*  392 */     mapstozAdHoc(deltat);
/*      */   }
/*      */   
/*      */ 
/*      */   public void zTransform()
/*      */   {
/*  398 */     mapstozAdHoc();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void mapstozAdHoc(double deltaT)
/*      */   {
/*  407 */     this.deltaT = deltaT;
/*  408 */     mapstozAdHoc();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void mapstozAdHoc()
/*      */   {
/*  418 */     deadTimeWarning("mapstozAdHoc");
/*  419 */     if (!this.padeAdded) { transferPolesZeros();
/*      */     }
/*      */     
/*  422 */     this.zDenomDeg = this.sDenomDegPade;
/*  423 */     ComplexPoly root = new ComplexPoly(1);
/*  424 */     this.zDenom = new ComplexPoly(this.zDenomDeg);
/*  425 */     if (this.zDenomDeg > 0) {
/*  426 */       this.zPoles = Complex.oneDarray(this.zDenomDeg);
/*  427 */       for (int i = 0; i < this.zDenomDeg; i++) {
/*  428 */         this.zPoles[i] = Complex.exp(this.sPolesPade[i].times(this.deltaT));
/*      */       }
/*  430 */       this.zDenom = ComplexPoly.rootsToPoly(this.zPoles);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  435 */     int infZeros = this.sDenomDegPade;
/*      */     
/*  437 */     if (infZeros + this.sNumerDegPade > this.sDenomDegPade) { infZeros = this.sDenomDegPade - this.sNumerDegPade;
/*      */     }
/*  439 */     this.zNumerDeg = (this.sNumerDegPade + infZeros);
/*  440 */     this.zNumer = new ComplexPoly(this.zNumerDeg);
/*  441 */     this.zZeros = Complex.oneDarray(this.zNumerDeg);
/*      */     
/*  443 */     if (this.zNumerDeg > 0) {
/*  444 */       for (int i = 0; i < this.sNumerDegPade; i++) {
/*  445 */         this.zZeros[i] = Complex.exp(this.sZerosPade[i].times(this.deltaT));
/*      */       }
/*  447 */       if (infZeros > 0) {
/*  448 */         if (this.maptozero) {
/*  449 */           for (int i = this.sNumerDegPade; i < this.zNumerDeg; i++) {
/*  450 */             this.zZeros[i] = Complex.zero();
/*      */           }
/*      */           
/*      */         } else {
/*  454 */           for (int i = this.sNumerDegPade; i < this.zNumerDeg; i++) {
/*  455 */             this.zZeros[i] = Complex.minusOne();
/*      */           }
/*      */         }
/*      */       }
/*  459 */       this.zNumer = ComplexPoly.rootsToPoly(this.zZeros);
/*      */     }
/*      */     
/*      */ 
/*  463 */     this.sValue = Complex.zero();
/*  464 */     this.zValue = Complex.plusOne();
/*  465 */     boolean testzeros = true;
/*  466 */     while (testzeros) {
/*  467 */       testzeros = false;
/*  468 */       if (this.sDenomDegPade > 0) {
/*  469 */         for (int i = 0; i < this.sDenomDegPade; i++) {
/*  470 */           if (this.sPolesPade[i].truncate(3).equals(this.sValue.truncate(3))) testzeros = true;
/*      */         }
/*      */       }
/*  473 */       if ((!testzeros) && (this.sNumerDegPade > 0)) {
/*  474 */         for (int i = 0; i < this.sDenomDegPade; i++) {
/*  475 */           if (this.sZerosPade[i].truncate(3).equals(this.sValue.truncate(3))) testzeros = true;
/*      */         }
/*      */       }
/*  478 */       if ((!testzeros) && (this.zDenomDeg > 0)) {
/*  479 */         for (int i = 0; i < this.zDenomDeg; i++) {
/*  480 */           if (this.zPoles[i].truncate(3).equals(this.zValue.truncate(3))) testzeros = true;
/*      */         }
/*      */       }
/*  483 */       if ((!testzeros) && (this.zNumerDeg > 0)) {
/*  484 */         for (int i = 0; i < this.zDenomDeg; i++) {
/*  485 */           if (this.zZeros[i].truncate(3).equals(this.zValue.truncate(3))) testzeros = true;
/*      */         }
/*      */       }
/*  488 */       if (testzeros) {
/*  489 */         this.sValue = this.sValue.plus(Complex.plusJay()).truncate(3);
/*  490 */         this.zValue = Complex.exp(this.sValue.times(this.deltaT).truncate(3));
/*      */       }
/*      */     }
/*  493 */     Complex gs = evalTransFunctS(this.sValue);
/*  494 */     Complex gz = evalTransFunctZ(this.zValue);
/*  495 */     Complex constant = gs.over(gz);
/*  496 */     ComplexPoly constantPoly = new ComplexPoly(constant);
/*  497 */     this.zNumer = this.zNumer.times(constantPoly);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMaptozero(boolean maptozero)
/*      */   {
/*  505 */     this.maptozero = maptozero;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setZnumer(double[] coeff)
/*      */   {
/*  511 */     this.zNumerDeg = (coeff.length - 1);
/*  512 */     this.zNumer = new ComplexPoly(coeff);
/*  513 */     this.zZeros = this.zNumer.roots();
/*      */   }
/*      */   
/*      */ 
/*      */   public void setZnumer(Complex[] coeff)
/*      */   {
/*  519 */     this.zNumerDeg = (coeff.length - 1);
/*  520 */     this.zNumer = new ComplexPoly(coeff);
/*  521 */     this.zZeros = this.zNumer.roots();
/*      */   }
/*      */   
/*      */ 
/*      */   public void setZnumer(ComplexPoly coeff)
/*      */   {
/*  527 */     this.zNumerDeg = coeff.getDeg();
/*  528 */     this.zNumer = ComplexPoly.copy(coeff);
/*  529 */     this.zZeros = this.zNumer.roots();
/*      */   }
/*      */   
/*      */ 
/*      */   public void setZdenom(double[] coeff)
/*      */   {
/*  535 */     this.zDenomDeg = (coeff.length - 1);
/*  536 */     this.zDenom = new ComplexPoly(coeff);
/*  537 */     this.zPoles = this.zDenom.roots();
/*      */   }
/*      */   
/*      */ 
/*      */   public void setZdenom(Complex[] coeff)
/*      */   {
/*  543 */     this.zDenomDeg = (coeff.length - 1);
/*  544 */     this.zDenom = new ComplexPoly(coeff);
/*  545 */     this.zPoles = this.zDenom.roots();
/*      */   }
/*      */   
/*      */ 
/*      */   public void setZdenom(ComplexPoly coeff)
/*      */   {
/*  551 */     this.zDenomDeg = coeff.getDeg();
/*  552 */     this.zDenom = ComplexPoly.copy(coeff);
/*  553 */     this.zPoles = this.zDenom.roots();
/*      */   }
/*      */   
/*      */   public void setDeltaT(double deltaT)
/*      */   {
/*  558 */     this.deltaT = deltaT;
/*  559 */     this.sampFreq = (1.0D / this.deltaT);
/*  560 */     deadTimeWarning("setDeltaT");
/*      */   }
/*      */   
/*      */   public void setForgetFactor(double forget)
/*      */   {
/*  565 */     this.forgetFactor = forget;
/*      */   }
/*      */   
/*      */   public void setSampFreq(double sfreq)
/*      */   {
/*  570 */     this.sampFreq = sfreq;
/*  571 */     this.deltaT = (1.0D / this.sampFreq);
/*  572 */     deadTimeWarning("setSampFreq");
/*      */   }
/*      */   
/*      */   public void setS(Complex s)
/*      */   {
/*  577 */     this.sValue = Complex.copy(s);
/*      */   }
/*      */   
/*      */   public void setS(double sr, double si)
/*      */   {
/*  582 */     this.sValue.reset(sr, si);
/*      */   }
/*      */   
/*      */   public void setS(double si)
/*      */   {
/*  587 */     this.sValue.reset(0.0D, si);
/*      */   }
/*      */   
/*      */   public void setZ(Complex z)
/*      */   {
/*  592 */     this.zValue = Complex.copy(z);
/*      */   }
/*      */   
/*      */   public void setZ(double zr, double zi)
/*      */   {
/*  597 */     this.zValue.reset(zr, zi);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setZtransformMethod(int ztransMethod)
/*      */   {
/*  604 */     if ((ztransMethod < 0) || (ztransMethod > 1)) {
/*  605 */       System.out.println("z transform method option number " + ztransMethod + " not recognised");
/*  606 */       System.out.println("z tr methodansform option number set in BlackBox to the default value of 0 (s -> z ad hoc mapping)");
/*  607 */       this.integMethod = 0;
/*      */     }
/*      */     else {
/*  610 */       this.ztransMethod = ztransMethod;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setIntegrateOption(int integMethod)
/*      */   {
/*  617 */     if ((integMethod < 0) || (integMethod > 2)) {
/*  618 */       System.out.println("integration method option number " + integMethod + " not recognised");
/*  619 */       System.out.println("integration method option number set in BlackBox to the default value of 0 (trapezium rule)");
/*  620 */       this.integMethod = 0;
/*      */     }
/*      */     else {
/*  623 */       this.integMethod = integMethod;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setIntegrateOption(String integMethodS)
/*      */   {
/*  631 */     if ((integMethodS.equals("trapezium")) || (integMethodS.equals("Trapezium")) || (integMethodS.equals("tutin")) || (integMethodS.equals("Tutin"))) {
/*  632 */       this.integMethod = 0;
/*      */ 
/*      */     }
/*  635 */     else if ((integMethodS.equals("backward")) || (integMethodS.equals("Backward")) || (integMethodS.equals("back")) || (integMethodS.equals("Back"))) {
/*  636 */       this.integMethod = 1;
/*      */ 
/*      */     }
/*  639 */     else if ((integMethodS.equals("foreward")) || (integMethodS.equals("Foreward")) || (integMethodS.equals("fore")) || (integMethodS.equals("Fore"))) {
/*  640 */       this.integMethod = 2;
/*      */     }
/*      */     else {
/*  643 */       System.out.println("integration method option  " + integMethodS + " not recognised");
/*  644 */       System.out.println("integration method option number set in PID to the default value of 0 (trapezium rule)");
/*  645 */       this.integMethod = 0;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setSampleLength(int samplen)
/*      */   {
/*  653 */     this.sampLen = samplen;
/*  654 */     this.time = new double[samplen];
/*  655 */     this.inputT = new double[samplen];
/*  656 */     this.outputT = new double[samplen];
/*      */   }
/*      */   
/*      */   public void setName(String name)
/*      */   {
/*  661 */     this.name = name;
/*      */   }
/*      */   
/*      */   public void setInputT(double ttime, double inputt)
/*      */   {
/*  666 */     for (int i = 0; i < this.sampLen - 2; i++) {
/*  667 */       this.time[i] = this.time[(i + 1)];
/*  668 */       this.inputT[i] = this.inputT[(i + 1)];
/*      */     }
/*  670 */     this.time[(this.sampLen - 1)] = ttime;
/*  671 */     this.inputT[(this.sampLen - 1)] = inputt;
/*      */   }
/*      */   
/*      */   public void setInputS(Complex input)
/*      */   {
/*  676 */     this.inputS = input;
/*      */   }
/*      */   
/*      */   public void resetZero()
/*      */   {
/*  681 */     for (int i = 0; i < this.sampLen - 1; i++) {
/*  682 */       this.outputT[i] = 0.0D;
/*  683 */       this.inputT[i] = 0.0D;
/*  684 */       this.time[i] = 0.0D;
/*      */     }
/*  686 */     this.outputS = Complex.zero();
/*  687 */     this.inputS = Complex.zero();
/*      */   }
/*      */   
/*      */ 
/*      */   protected void calcPolesZerosS()
/*      */   {
/*  693 */     if ((this.sNumer != null) && 
/*  694 */       (this.sNumer.getDeg() > 0)) { this.sZeros = this.sNumer.roots();
/*      */     }
/*  696 */     if ((this.sDenom != null) && 
/*  697 */       (this.sDenom.getDeg() > 0)) { this.sPoles = this.sDenom.roots();
/*      */     }
/*      */   }
/*      */   
/*      */   protected void zeroPoleCancellation()
/*      */   {
/*  703 */     boolean check = false;
/*  704 */     boolean testI = true;
/*  705 */     boolean testJ = true;
/*  706 */     int i = 0;
/*  707 */     int j = 0;
/*  708 */     if ((this.sNumerDegPade == 0) || (this.sDenomDegPade == 0)) testI = false;
/*  709 */     while (testI) {
/*  710 */       j = 0;
/*  711 */       while (testJ) {
/*  712 */         if (this.sZerosPade[i].isEqual(this.sPolesPade[j])) {
/*  713 */           for (int k = j + 1; k < this.sDenomDegPade; k++) this.sPolesPade[(k - 1)] = this.sPolesPade[k].copy();
/*  714 */           this.sDenomDegPade -= 1;
/*  715 */           for (int k = i + 1; k < this.sNumerDegPade; k++) this.sZerosPade[(k - 1)] = this.sZerosPade[k].copy();
/*  716 */           this.sNumerDegPade -= 1;
/*  717 */           check = true;
/*  718 */           testJ = false;
/*  719 */           i--;
/*      */         }
/*      */         else {
/*  722 */           j++;
/*  723 */           if (j > this.sDenomDegPade - 1) testJ = false;
/*      */         }
/*      */       }
/*  726 */       i++;
/*  727 */       if (i > this.sNumerDegPade - 1) testI = false;
/*      */     }
/*  729 */     if (check) {
/*  730 */       if (this.sNumerDegPade == 0) {
/*  731 */         this.sNumerPade = new ComplexPoly(1.0D);
/*      */       }
/*      */       else {
/*  734 */         this.sNumerPade = ComplexPoly.rootsToPoly(this.sZerosPade);
/*      */       }
/*  736 */       if (this.sDenomDegPade == 0) {
/*  737 */         this.sDenomPade = new ComplexPoly(1.0D);
/*      */       }
/*      */       else {
/*  740 */         this.sDenomPade = ComplexPoly.rootsToPoly(this.sPolesPade);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public Complex evalTransFunctS()
/*      */   {
/*  747 */     if (!this.padeAdded) transferPolesZeros();
/*  748 */     Complex num = this.sNumerPade.evaluate(this.sValue);
/*  749 */     Complex den = this.sDenomPade.evaluate(this.sValue);
/*  750 */     Complex lagterm = Complex.plusOne();
/*  751 */     if (this.deadTime != 0.0D) lagterm = Complex.exp(this.sValue.times(-this.deadTime));
/*  752 */     return num.over(den).times(lagterm);
/*      */   }
/*      */   
/*      */   public Complex evalTransFunctS(Complex sValue)
/*      */   {
/*  757 */     if (!this.padeAdded) transferPolesZeros();
/*  758 */     this.sValue = Complex.copy(sValue);
/*  759 */     Complex num = this.sNumerPade.evaluate(sValue);
/*  760 */     Complex den = this.sDenomPade.evaluate(sValue);
/*  761 */     Complex lagterm = Complex.plusOne();
/*  762 */     if (this.deadTime != 0.0D) lagterm = Complex.exp(this.sValue.times(-this.deadTime));
/*  763 */     return num.over(den).times(lagterm);
/*      */   }
/*      */   
/*      */   public Complex evalTransFunctS(double freq)
/*      */   {
/*  768 */     if (!this.padeAdded) transferPolesZeros();
/*  769 */     this.sValue.reset(0.0D, 6.283185307179586D * freq);
/*  770 */     Complex num = this.sNumerPade.evaluate(this.sValue);
/*  771 */     Complex den = this.sDenomPade.evaluate(this.sValue);
/*  772 */     Complex lagterm = Complex.plusOne();
/*  773 */     if (this.deadTime != 0.0D) lagterm = Complex.exp(this.sValue.times(-this.deadTime));
/*  774 */     return num.over(den).times(lagterm);
/*      */   }
/*      */   
/*      */   public double evalMagTransFunctS()
/*      */   {
/*  779 */     if (!this.padeAdded) transferPolesZeros();
/*  780 */     Complex num = this.sNumerPade.evaluate(this.sValue);
/*  781 */     Complex den = this.sDenomPade.evaluate(this.sValue);
/*  782 */     Complex lagterm = Complex.plusOne();
/*  783 */     if (this.deadTime != 0.0D) lagterm = Complex.exp(this.sValue.times(-this.deadTime));
/*  784 */     return num.over(den).times(lagterm).abs();
/*      */   }
/*      */   
/*      */   public double evalMagTransFunctS(Complex sValue)
/*      */   {
/*  789 */     if (!this.padeAdded) transferPolesZeros();
/*  790 */     this.sValue = Complex.copy(sValue);
/*  791 */     Complex num = this.sNumerPade.evaluate(sValue);
/*  792 */     Complex den = this.sDenomPade.evaluate(sValue);
/*  793 */     Complex lagterm = Complex.plusOne();
/*  794 */     if (this.deadTime != 0.0D) lagterm = Complex.exp(this.sValue.times(-this.deadTime));
/*  795 */     return num.over(den).times(lagterm).abs();
/*      */   }
/*      */   
/*      */   public double evalMagTransFunctS(double freq)
/*      */   {
/*  800 */     if (!this.padeAdded) transferPolesZeros();
/*  801 */     this.sValue.reset(0.0D, 6.283185307179586D * freq);
/*  802 */     Complex num = this.sNumerPade.evaluate(this.sValue);
/*  803 */     Complex den = this.sDenomPade.evaluate(this.sValue);
/*  804 */     Complex lagterm = Complex.plusOne();
/*  805 */     if (this.deadTime != 0.0D) lagterm = Complex.exp(this.sValue.times(-this.deadTime));
/*  806 */     return num.over(den).times(lagterm).abs();
/*      */   }
/*      */   
/*      */   public double evalPhaseTransFunctS()
/*      */   {
/*  811 */     if (!this.padeAdded) transferPolesZeros();
/*  812 */     Complex num = this.sNumerPade.evaluate(this.sValue);
/*  813 */     Complex den = this.sDenomPade.evaluate(this.sValue);
/*  814 */     Complex lagterm = Complex.plusOne();
/*  815 */     if (this.deadTime != 0.0D) lagterm = Complex.exp(this.sValue.times(-this.deadTime));
/*  816 */     return num.over(den).times(lagterm).arg();
/*      */   }
/*      */   
/*      */   public double evalPhaseTransFunctS(Complex sValue)
/*      */   {
/*  821 */     if (!this.padeAdded) transferPolesZeros();
/*  822 */     this.sValue = Complex.copy(sValue);
/*  823 */     Complex num = this.sNumerPade.evaluate(sValue);
/*  824 */     Complex den = this.sDenomPade.evaluate(sValue);
/*  825 */     Complex lagterm = Complex.plusOne();
/*  826 */     if (this.deadTime != 0.0D) lagterm = Complex.exp(this.sValue.times(-this.deadTime));
/*  827 */     return num.over(den).times(lagterm).arg();
/*      */   }
/*      */   
/*      */   public double evalPhaseTransFunctS(double freq)
/*      */   {
/*  832 */     if (!this.padeAdded) transferPolesZeros();
/*  833 */     this.sValue.reset(0.0D, 6.283185307179586D * freq);
/*  834 */     Complex num = this.sNumerPade.evaluate(this.sValue);
/*  835 */     Complex den = this.sDenomPade.evaluate(this.sValue);
/*  836 */     Complex lagterm = Complex.plusOne();
/*  837 */     if (this.deadTime != 0.0D) lagterm = Complex.exp(this.sValue.times(-this.deadTime));
/*  838 */     return num.over(den).times(lagterm).arg();
/*      */   }
/*      */   
/*      */   public Complex evalTransFunctZ()
/*      */   {
/*  843 */     Complex num = this.zNumer.evaluate(this.zValue);
/*  844 */     Complex den = this.zDenom.evaluate(this.zValue);
/*  845 */     return num.over(den);
/*      */   }
/*      */   
/*      */   public Complex evalTransFunctZ(Complex zValue)
/*      */   {
/*  850 */     this.zValue = Complex.copy(zValue);
/*  851 */     Complex num = this.zNumer.evaluate(zValue);
/*  852 */     Complex den = this.zDenom.evaluate(zValue);
/*  853 */     return num.over(den);
/*      */   }
/*      */   
/*      */   public double evalMagTransFunctZ()
/*      */   {
/*  858 */     Complex num = this.zNumer.evaluate(this.zValue);
/*  859 */     Complex den = this.zDenom.evaluate(this.zValue);
/*  860 */     return num.over(den).abs();
/*      */   }
/*      */   
/*      */   public double evalMagTransFunctZ(Complex zValue)
/*      */   {
/*  865 */     this.zValue = Complex.copy(zValue);
/*  866 */     Complex num = this.zNumer.evaluate(zValue);
/*  867 */     Complex den = this.zDenom.evaluate(zValue);
/*  868 */     return num.over(den).abs();
/*      */   }
/*      */   
/*      */   public double evalPhaseTransFunctZ()
/*      */   {
/*  873 */     Complex num = this.zNumer.evaluate(this.zValue);
/*  874 */     Complex den = this.zDenom.evaluate(this.zValue);
/*  875 */     return num.over(den).arg();
/*      */   }
/*      */   
/*      */   public double evalPhaseTransFunctZ(Complex zValue)
/*      */   {
/*  880 */     this.zValue = Complex.copy(zValue);
/*  881 */     Complex num = this.zNumer.evaluate(zValue);
/*  882 */     Complex den = this.zDenom.evaluate(zValue);
/*  883 */     return num.over(den).arg();
/*      */   }
/*      */   
/*      */   public int getIntegMethod()
/*      */   {
/*  888 */     return this.integMethod;
/*      */   }
/*      */   
/*      */   public int getZtransformMethod()
/*      */   {
/*  893 */     return this.ztransMethod;
/*      */   }
/*      */   
/*      */   public int getSampleLength()
/*      */   {
/*  898 */     return this.sampLen;
/*      */   }
/*      */   
/*      */   public double getForgetFactor()
/*      */   {
/*  903 */     return this.forgetFactor;
/*      */   }
/*      */   
/*      */   public double getCurrentTime()
/*      */   {
/*  908 */     return this.time[(this.sampLen - 1)];
/*      */   }
/*      */   
/*      */   public double[] getTime()
/*      */   {
/*  913 */     return this.time;
/*      */   }
/*      */   
/*      */   public double getCurrentInputT()
/*      */   {
/*  918 */     return this.inputT[(this.sampLen - 1)];
/*      */   }
/*      */   
/*      */   public double[] getInputT()
/*      */   {
/*  923 */     return this.inputT;
/*      */   }
/*      */   
/*      */   public Complex getInputS()
/*      */   {
/*  928 */     return this.inputS;
/*      */   }
/*      */   
/*      */   public double getDeltaT()
/*      */   {
/*  933 */     return this.deltaT;
/*      */   }
/*      */   
/*      */   public double getSampFreq()
/*      */   {
/*  938 */     return this.sampFreq;
/*      */   }
/*      */   
/*      */   public Complex getS()
/*      */   {
/*  943 */     return this.sValue;
/*      */   }
/*      */   
/*      */   public Complex getZ()
/*      */   {
/*  948 */     return this.zValue;
/*      */   }
/*      */   
/*      */   public int getSnumerDeg()
/*      */   {
/*  953 */     if (this.padeAdded) {
/*  954 */       return this.sNumerDegPade;
/*      */     }
/*      */     
/*  957 */     return this.sNumerDeg;
/*      */   }
/*      */   
/*      */ 
/*      */   public int getSdenomDeg()
/*      */   {
/*  963 */     if (this.padeAdded) {
/*  964 */       return this.sDenomDegPade;
/*      */     }
/*      */     
/*  967 */     return this.sDenomDeg;
/*      */   }
/*      */   
/*      */ 
/*      */   public ComplexPoly getSnumer()
/*      */   {
/*  973 */     if (this.padeAdded) {
/*  974 */       return this.sNumerPade;
/*      */     }
/*      */     
/*  977 */     return this.sNumer;
/*      */   }
/*      */   
/*      */ 
/*      */   public ComplexPoly getSdenom()
/*      */   {
/*  983 */     if (this.padeAdded) {
/*  984 */       return this.sDenomPade;
/*      */     }
/*      */     
/*  987 */     return this.sDenom;
/*      */   }
/*      */   
/*      */ 
/*      */   public int getZnumerDeg()
/*      */   {
/*  993 */     return this.zNumerDeg;
/*      */   }
/*      */   
/*      */   public int getZdenomDeg()
/*      */   {
/*  998 */     return this.zDenomDeg;
/*      */   }
/*      */   
/*      */   public ComplexPoly getZnumer()
/*      */   {
/* 1003 */     return this.zNumer;
/*      */   }
/*      */   
/*      */   public ComplexPoly getZdenom()
/*      */   {
/* 1008 */     return this.zDenom;
/*      */   }
/*      */   
/*      */   public Complex[] getZerosS()
/*      */   {
/* 1013 */     if (!this.padeAdded) transferPolesZeros();
/* 1014 */     if (this.sZerosPade == null) {
/* 1015 */       System.out.println("Method BlackBox.getZerosS:");
/* 1016 */       System.out.println("There are either no s-domain zeros for this transfer function");
/* 1017 */       System.out.println("or the s-domain numerator polynomial has not been set");
/* 1018 */       System.out.println("null returned");
/* 1019 */       return null;
/*      */     }
/*      */     
/* 1022 */     return this.sZerosPade;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Complex[] getPolesS()
/*      */   {
/* 1029 */     if (!this.padeAdded) transferPolesZeros();
/* 1030 */     if (this.sPolesPade == null) {
/* 1031 */       System.out.println("Method BlackBox.getPolesS:");
/* 1032 */       System.out.println("There are either no s-domain poles for this transfer function");
/* 1033 */       System.out.println("or the s-domain denominator polynomial has not been set");
/* 1034 */       System.out.println("null returned");
/* 1035 */       return null;
/*      */     }
/*      */     
/* 1038 */     return this.sPolesPade;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex[] getZerosZ()
/*      */   {
/* 1044 */     if (this.zZeros == null) {
/* 1045 */       System.out.println("Method BlackBox.getZerosZ:");
/* 1046 */       System.out.println("There are either no z-domain zeros for this transfer function");
/* 1047 */       System.out.println("or the z-domain numerator polynomial has not been set");
/* 1048 */       System.out.println("null returned");
/* 1049 */       return null;
/*      */     }
/*      */     
/* 1052 */     return this.zZeros;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex[] getPolesZ()
/*      */   {
/* 1058 */     if (this.zPoles == null) {
/* 1059 */       System.out.println("Method BlackBox.getPolesZ:");
/* 1060 */       System.out.println("There are either no z-domain poles for this transfer function");
/* 1061 */       System.out.println("or the z-domain denominator polynomial has not been set");
/* 1062 */       System.out.println("null returned");
/* 1063 */       return null;
/*      */     }
/*      */     
/* 1066 */     return this.zPoles;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getMaptozero()
/*      */   {
/* 1074 */     return this.maptozero;
/*      */   }
/*      */   
/*      */   public String getName()
/*      */   {
/* 1079 */     return this.name;
/*      */   }
/*      */   
/*      */   public void plotPoleZeroS()
/*      */   {
/* 1084 */     if (!this.padeAdded) transferPolesZeros();
/* 1085 */     if (this.sNumerPade == null) throw new IllegalArgumentException("s domain numerator has not been set");
/* 1086 */     if (this.sDenomPade == null) throw new IllegalArgumentException("s domain denominator has not been set");
/* 1087 */     PlotPoleZero ppz = new PlotPoleZero(this.sNumerPade, this.sDenomPade);
/* 1088 */     ppz.setS();
/* 1089 */     ppz.pzPlot(this.name);
/*      */   }
/*      */   
/*      */   public void plotPoleZeroZ()
/*      */   {
/* 1094 */     PlotPoleZero ppz = new PlotPoleZero(this.zNumer, this.zDenom);
/* 1095 */     if (this.zNumer == null) throw new IllegalArgumentException("z domain numerator has not been set");
/* 1096 */     if (this.zDenom == null) throw new IllegalArgumentException("z domain denominator has not been set");
/* 1097 */     ppz.setZ();
/* 1098 */     ppz.pzPlot(this.name);
/*      */   }
/*      */   
/*      */   public void plotBode(double lowFreq, double highFreq)
/*      */   {
/* 1103 */     if (!this.padeAdded) transferPolesZeros();
/* 1104 */     int nPoints = 100;
/* 1105 */     double[][] cdata = new double[2][nPoints];
/* 1106 */     double[] logFreqArray = new double[nPoints + 1];
/* 1107 */     double logLow = Fmath.log10(6.283185307179586D * lowFreq);
/* 1108 */     double logHigh = Fmath.log10(6.283185307179586D * highFreq);
/* 1109 */     double incr = (logHigh - logLow) / (nPoints - 1.0D);
/* 1110 */     double freqArray = lowFreq;
/* 1111 */     logFreqArray[0] = logLow;
/* 1112 */     for (int i = 0; i < nPoints; i++) {
/* 1113 */       freqArray = Math.pow(10.0D, logFreqArray[i]);
/* 1114 */       cdata[0][i] = logFreqArray[i];
/* 1115 */       cdata[1][i] = (20.0D * Fmath.log10(evalMagTransFunctS(freqArray / 6.283185307179586D)));
/* 1116 */       logFreqArray[(i + 1)] = (logFreqArray[i] + incr);
/*      */     }
/*      */     
/* 1119 */     PlotGraph pgmag = new PlotGraph(cdata);
/* 1120 */     pgmag.setGraphTitle("Bode Plot = magnitude versus log10[radial frequency]");
/* 1121 */     pgmag.setGraphTitle2(this.name);
/* 1122 */     pgmag.setXaxisLegend("Log10[radial frequency]");
/* 1123 */     pgmag.setYaxisLegend("Magnitude[Transfer Function]");
/* 1124 */     pgmag.setYaxisUnitsName("dB");
/* 1125 */     pgmag.setPoint(0);
/* 1126 */     pgmag.plot();
/* 1127 */     for (int i = 0; i < nPoints; i++) {
/* 1128 */       freqArray = Math.pow(10.0D, logFreqArray[i]);
/* 1129 */       cdata[0][i] = logFreqArray[i];
/* 1130 */       cdata[1][i] = (evalPhaseTransFunctS(freqArray) * 180.0D / 3.141592653589793D);
/*      */     }
/* 1132 */     PlotGraph pgphase = new PlotGraph(cdata);
/* 1133 */     pgphase.setGraphTitle("Bode Plot = phase versus log10[radial frequency]");
/* 1134 */     pgphase.setGraphTitle2(this.name);
/* 1135 */     pgphase.setXaxisLegend("Log10[radial frequency]");
/* 1136 */     pgphase.setYaxisLegend("Phase[Transfer Function]");
/* 1137 */     pgphase.setYaxisUnitsName("degrees");
/* 1138 */     pgphase.setPoint(0);
/* 1139 */     pgphase.plot();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double getCurrentOutputT(double ttime, double inp)
/*      */   {
/* 1146 */     if (ttime <= this.time[(this.sampLen - 1)]) throw new IllegalArgumentException("Current time equals or is less than previous time");
/* 1147 */     this.deltaT = (ttime - this.time[(this.sampLen - 1)]);
/* 1148 */     this.sampFreq = (1.0D / this.deltaT);
/* 1149 */     deadTimeWarning("getCurrentOutputT(time,input)");
/* 1150 */     for (int i = 0; i < this.sampLen - 2; i++) {
/* 1151 */       this.time[i] = this.time[(i + 1)];
/* 1152 */       this.inputT[i] = this.inputT[(i + 1)];
/*      */     }
/* 1154 */     this.time[(this.sampLen - 1)] = ttime;
/* 1155 */     this.inputT[(this.sampLen - 1)] = inp;
/* 1156 */     return getCurrentOutputT();
/*      */   }
/*      */   
/*      */   public double getCurrentOutputT()
/*      */   {
/* 1161 */     if (!this.padeAdded) { transferPolesZeros();
/*      */     }
/* 1163 */     Complex[][] coeffT = inverseTransform(this.sNumerPade, this.sDenomPade);
/* 1164 */     Complex tempc = Complex.zero();
/* 1165 */     for (int j = 0; j < coeffT[0].length; j++) {
/* 1166 */       tempc.plusEquals(timeTerm(this.time[(this.sampLen - 1)], coeffT[0][j], coeffT[1][j], coeffT[2][j]));
/*      */     }
/* 1168 */     double outReal = tempc.getReal();
/* 1169 */     double outImag = tempc.getImag();
/*      */     
/* 1171 */     boolean outTest = true;
/* 1172 */     if (outImag == 0.0D) outTest = false;
/* 1173 */     if (outTest) {
/* 1174 */       double temp = Math.max(Math.abs(outReal), Math.abs(outImag));
/* 1175 */       if (Math.abs((outReal - outImag) / temp) > 1.0E-5D) {
/* 1176 */         outTest = false;
/*      */       }
/*      */       else {
/* 1179 */         System.out.println("output in Blackbox.getCurrentOutputT() has a significant imaginary part");
/* 1180 */         System.out.println("time = " + this.time[(this.sampLen - 1)] + "    real = " + outReal + "   imag = " + outImag);
/* 1181 */         System.out.println("Output equated to the real part");
/*      */       }
/*      */     }
/* 1184 */     for (int i = 0; i < this.sampLen - 2; i++) this.outputT[i] = this.outputT[(i + 1)];
/* 1185 */     this.outputT[(this.sampLen - 1)] = (outReal * this.inputT[(this.sampLen - 1)]);
/* 1186 */     return this.outputT[(this.sampLen - 1)];
/*      */   }
/*      */   
/*      */   public double[] getOutputT()
/*      */   {
/* 1191 */     return this.outputT;
/*      */   }
/*      */   
/*      */   public Complex getOutputS()
/*      */   {
/* 1196 */     if (!this.padeAdded) transferPolesZeros();
/* 1197 */     Complex num = this.sNumerPade.evaluate(this.sValue);
/* 1198 */     Complex den = this.sDenomPade.evaluate(this.sValue);
/* 1199 */     this.outputS = num.over(den).times(this.inputS);
/* 1200 */     if (this.deadTime != 0.0D) this.outputS = this.outputS.times(Complex.exp(this.sValue.times(-this.deadTime)));
/* 1201 */     return this.outputS;
/*      */   }
/*      */   
/*      */   public Complex getOutputS(Complex svalue, Complex inputs)
/*      */   {
/* 1206 */     if (!this.padeAdded) transferPolesZeros();
/* 1207 */     this.inputS = inputs;
/* 1208 */     this.sValue = svalue;
/* 1209 */     Complex num = this.sNumerPade.evaluate(this.sValue);
/* 1210 */     Complex den = this.sDenomPade.evaluate(this.sValue);
/* 1211 */     this.outputS = num.over(den).times(this.inputS);
/* 1212 */     if (this.deadTime != 0.0D) this.outputS = this.outputS.times(Complex.exp(this.sValue.times(-this.deadTime)));
/* 1213 */     return this.outputS;
/*      */   }
/*      */   
/*      */   public void setNplotPoints(int nPoints)
/*      */   {
/* 1218 */     this.nPlotPoints = nPoints;
/*      */   }
/*      */   
/*      */   public int getNplotPoints()
/*      */   {
/* 1223 */     return this.nPlotPoints;
/*      */   }
/*      */   
/*      */   public void impulseInput(double impulseMag, double finalTime)
/*      */   {
/* 1228 */     if (!this.padeAdded) { transferPolesZeros();
/*      */     }
/*      */     
/* 1231 */     ComplexPoly impulseN = new ComplexPoly(0);
/* 1232 */     impulseN.resetCoeff(0, Complex.plusOne().times(impulseMag));
/* 1233 */     ComplexPoly numerT = this.sNumerPade.times(impulseN);
/* 1234 */     ComplexPoly denomT = this.sDenomPade.copy();
/* 1235 */     String graphtitle1 = "Impulse Input Transient:   Impulse magnitude = " + impulseMag;
/* 1236 */     String graphtitle2 = getName();
/*      */     
/* 1238 */     transientResponse(this.nPlotPoints, finalTime, this.deadTime, numerT, denomT, graphtitle1, graphtitle2);
/*      */   }
/*      */   
/*      */   public void impulseInput(double finalTime)
/*      */   {
/* 1243 */     impulseInput(1.0D, finalTime);
/*      */   }
/*      */   
/*      */ 
/*      */   public void stepInput(double stepMag, double finalTime)
/*      */   {
/* 1249 */     if (!this.padeAdded) { transferPolesZeros();
/*      */     }
/* 1251 */     ComplexPoly stepN = new ComplexPoly(0);
/* 1252 */     stepN.resetCoeff(0, Complex.plusOne().times(stepMag));
/* 1253 */     ComplexPoly numerT = this.sNumerPade.times(stepN);
/* 1254 */     ComplexPoly stepD = new ComplexPoly(1);
/* 1255 */     stepD.resetCoeff(0, Complex.zero());
/* 1256 */     stepD.resetCoeff(1, Complex.plusOne());
/* 1257 */     ComplexPoly denomT = this.sDenomPade.times(stepD);
/* 1258 */     String graphtitle1 = "Step Input Transient:   Step magnitude = " + stepMag;
/* 1259 */     String graphtitle2 = getName();
/*      */     
/* 1261 */     transientResponse(this.nPlotPoints, finalTime, this.deadTime, numerT, denomT, graphtitle1, graphtitle2);
/*      */   }
/*      */   
/*      */   public void stepInput(double finalTime)
/*      */   {
/* 1266 */     stepInput(1.0D, finalTime);
/*      */   }
/*      */   
/*      */   public void rampInput(double rampGradient, int rampOrder, double finalTime)
/*      */   {
/* 1271 */     if (!this.padeAdded) { transferPolesZeros();
/*      */     }
/*      */     
/* 1274 */     ComplexPoly rampN = new ComplexPoly(0);
/* 1275 */     rampN.resetCoeff(0, Complex.plusOne().times(rampGradient * Fmath.factorial(rampOrder)));
/* 1276 */     ComplexPoly numerT = this.sNumerPade.times(rampN);
/* 1277 */     Complex[] ramp = Complex.oneDarray(rampOrder + 1);
/* 1278 */     ComplexPoly rampD = ComplexPoly.rootsToPoly(ramp);
/* 1279 */     ComplexPoly denomT = this.sDenomPade.times(rampD);
/* 1280 */     String graphtitle1 = "";
/* 1281 */     if (rampGradient != 1.0D) {
/* 1282 */       if (rampOrder != 1) {
/* 1283 */         graphtitle1 = graphtitle1 + "nth order ramp (at^n) input transient:   a = " + rampGradient + "    n = " + rampOrder;
/*      */       }
/*      */       else {
/* 1286 */         graphtitle1 = graphtitle1 + "First order ramp (at) input transient:   a = " + rampGradient;
/*      */       }
/*      */       
/*      */     }
/* 1290 */     else if (rampOrder != 1) {
/* 1291 */       graphtitle1 = graphtitle1 + "Unit ramp (t) input transient";
/*      */     }
/*      */     else {
/* 1294 */       graphtitle1 = graphtitle1 + "nth order ramp (t^n) input transient:   n = " + rampOrder;
/*      */     }
/*      */     
/* 1297 */     String graphtitle2 = getName();
/*      */     
/* 1299 */     transientResponse(this.nPlotPoints, finalTime, this.deadTime, numerT, denomT, graphtitle1, graphtitle2);
/*      */   }
/*      */   
/*      */   public void rampInput(int rampOrder, double finalTime)
/*      */   {
/* 1304 */     double rampGradient = 1.0D;
/* 1305 */     rampInput(rampGradient, rampOrder, finalTime);
/*      */   }
/*      */   
/*      */   public void rampInput(double rampGradient, double finalTime)
/*      */   {
/* 1310 */     int rampOrder = 1;
/* 1311 */     rampInput(rampGradient, rampOrder, finalTime);
/*      */   }
/*      */   
/*      */   public void rampInput(double finalTime)
/*      */   {
/* 1316 */     double rampGradient = 1.0D;
/* 1317 */     int rampOrder = 1;
/* 1318 */     rampInput(rampGradient, rampOrder, finalTime);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static void transientResponse(int nPoints, double finalTime, double deadTime, ComplexPoly numerT, ComplexPoly denomT, String graphtitle1, String graphtitle2)
/*      */   {
/* 1325 */     Complex[][] coeffT = inverseTransform(numerT, denomT);
/*      */     
/*      */ 
/* 1328 */     int m = denomT.getDeg();
/* 1329 */     double incrT = finalTime / (nPoints - 1);
/* 1330 */     double[][] cdata = new double[2][nPoints];
/* 1331 */     double temp = 0.0D;
/* 1332 */     Complex tempc = new Complex();
/* 1333 */     double outReal = 0.0D;
/* 1334 */     double outImag = 0.0D;
/* 1335 */     boolean outTest = true;
/*      */     
/* 1337 */     cdata[0][0] = 0.0D;
/* 1338 */     for (int i = 1; i < nPoints; i++) {
/* 1339 */       cdata[0][i] = (cdata[0][(i - 1)] + incrT);
/*      */     }
/* 1341 */     for (int i = 0; i < nPoints; i++) {
/* 1342 */       outTest = true;
/* 1343 */       tempc = Complex.zero();
/* 1344 */       for (int j = 0; j < m; j++) {
/* 1345 */         tempc.plusEquals(timeTerm(cdata[0][i], coeffT[0][j], coeffT[1][j], coeffT[2][j]));
/*      */       }
/* 1347 */       outReal = tempc.getReal();
/* 1348 */       outImag = tempc.getImag();
/* 1349 */       if (outImag == 0.0D) outTest = false;
/* 1350 */       if (outTest) {
/* 1351 */         temp = Math.max(Math.abs(outReal), Math.abs(outImag));
/* 1352 */         if (Math.abs((outReal - outImag) / temp) > 1.0E-5D) {
/* 1353 */           outTest = false;
/*      */         }
/*      */         else {
/* 1356 */           System.out.println("output in Blackbox.stepInput has a significant imaginary part");
/* 1357 */           System.out.println("time = " + cdata[0][i] + "    real = " + outReal + "   imag = " + outImag);
/* 1358 */           System.out.println("Output equated to the real part");
/*      */         }
/*      */       }
/* 1361 */       cdata[1][i] = outReal;
/* 1362 */       cdata[0][i] += deadTime;
/*      */     }
/*      */     
/*      */ 
/* 1366 */     PlotGraph pg = new PlotGraph(cdata);
/*      */     
/* 1368 */     pg.setGraphTitle(graphtitle1);
/* 1369 */     pg.setGraphTitle2(graphtitle2);
/* 1370 */     pg.setXaxisLegend("Time");
/* 1371 */     pg.setXaxisUnitsName("s");
/* 1372 */     pg.setYaxisLegend("Output");
/* 1373 */     pg.setPoint(0);
/* 1374 */     pg.setNoYoffset(true);
/* 1375 */     if (deadTime < cdata[0][(nPoints - 1)] - cdata[0][0]) pg.setNoXoffset(true);
/* 1376 */     pg.setXlowFac(0.0D);
/* 1377 */     pg.setYlowFac(0.0D);
/* 1378 */     pg.plot();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static Complex timeTerm(double ttime, Complex coeff, Complex constant, Complex power)
/*      */   {
/* 1385 */     Complex ret = new Complex();
/* 1386 */     int n = (int)power.getReal() - 1;
/* 1387 */     ret = coeff.times(Math.pow(ttime, n));
/* 1388 */     ret = ret.over(Fmath.factorial(n));
/* 1389 */     ret = ret.times(Complex.exp(constant.times(ttime)));
/* 1390 */     return ret;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex[][] inverseTransform(ComplexPoly numer, ComplexPoly denom)
/*      */   {
/* 1399 */     int polesN = denom.getDeg();
/* 1400 */     int zerosN = numer.getDeg();
/* 1401 */     if (zerosN >= polesN) { throw new IllegalArgumentException("The degree of the numerator is equal to or greater than the degree of the denominator");
/*      */     }
/* 1403 */     Complex[][] ret = Complex.twoDarray(3, polesN);
/* 1404 */     Complex[] coeff = Complex.oneDarray(polesN);
/* 1405 */     Complex[] poles = denom.roots();
/* 1406 */     int[] polePower = new int[polesN];
/* 1407 */     int[] poleIdent = new int[polesN];
/* 1408 */     boolean[] poleSet = new boolean[polesN];
/* 1409 */     boolean[] termSet = new boolean[polesN];
/* 1410 */     double identicalRootLimit = 0.01D;
/*      */     
/*      */ 
/* 1413 */     int power = 0;
/* 1414 */     Complex identPoleAverage = new Complex();
/* 1415 */     for (int i = 0; i < polesN; i++) poleSet[i] = false;
/* 1416 */     for (int i = 0; i < polesN; i++) termSet[i] = true;
/* 1417 */     for (int i = 0; i < polesN; i++) {
/* 1418 */       if (poleSet[i] == 0) {
/* 1419 */         power = 1;
/* 1420 */         identPoleAverage = Complex.zero();
/* 1421 */         polePower[i] = 1;
/* 1422 */         for (int j = i + 1; j < polesN; j++) {
/* 1423 */           if (poleSet[j] == 0) {
/* 1424 */             if (poles[i].isEqualWithinLimits(poles[j], identicalRootLimit)) {
/* 1425 */               poleIdent[j] = i;
/* 1426 */               polePower[j] = (++power);
/* 1427 */               poleSet[j] = true;
/* 1428 */               poleSet[i] = true;
/*      */             }
/*      */             else {
/* 1431 */               poleIdent[j] = j;
/* 1432 */               polePower[j] = 1;
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*      */ 
/* 1438 */         if (power > 1) {
/* 1439 */           for (int k = power - 1; k > 0; k--) {
/* 1440 */             for (int j = 0; j < polesN; j++) {
/* 1441 */               if ((poleIdent[j] == i) && (polePower[j] == k)) { termSet[j] = false;
/*      */               }
/*      */             }
/*      */           }
/*      */           
/* 1446 */           int kk = 0;
/* 1447 */           for (int j = 0; j < polesN; j++) {
/* 1448 */             if (poleIdent[j] == i) {
/* 1449 */               identPoleAverage.plusEquals(poles[j]);
/* 1450 */               kk++;
/*      */             }
/*      */           }
/* 1453 */           identPoleAverage.overEquals(kk);
/* 1454 */           for (int j = 0; j < polesN; j++) {
/* 1455 */             if (poleIdent[j] == i) { poles[j] = identPoleAverage;
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1463 */     int ndone = 0;
/* 1464 */     for (int i = 0; i < polesN; i++) {
/* 1465 */       if (termSet[i] != 0) {
/* 1466 */         coeff[i] = numer.evaluate(poles[i]);
/* 1467 */         for (int j = 0; j < polesN; j++) {
/* 1468 */           if ((i != j) && (termSet[j] != 0)) {
/* 1469 */             coeff[i] = coeff[i].over(Complex.pow(poles[i].minus(poles[j]), polePower[j]));
/*      */           }
/*      */         }
/* 1472 */         ndone++;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1477 */     Complex denhold = new Complex();
/* 1478 */     if (ndone != polesN)
/*      */     {
/*      */ 
/* 1481 */       double poleAv = 0.0D;
/* 1482 */       for (int i = 0; i < polesN; i++) poleAv += poles[i].abs();
/* 1483 */       poleAv /= polesN;
/* 1484 */       poleAv = Math.pow(10.0D, Math.floor(Fmath.log10(poleAv)));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1489 */       int nsimul = polesN - ndone;
/* 1490 */       Complex[][] mat = Complex.twoDarray(nsimul, nsimul);
/* 1491 */       Complex[] vec = Complex.oneDarray(nsimul);
/* 1492 */       Complex sValue = Complex.zero();
/* 1493 */       double sValueReal = 0.0D;
/* 1494 */       boolean testpole1 = true;
/* 1495 */       boolean testpole2 = true;
/* 1496 */       Complex temp = new Complex();
/*      */       
/*      */ 
/* 1499 */       for (int i = 0; i < nsimul; i++)
/*      */       {
/* 1501 */         testpole1 = true;
/* 1502 */         while (testpole1) {
/* 1503 */           testpole2 = true;
/* 1504 */           for (int j = 0; j < polesN; j++) {
/* 1505 */             if (sValue.isEqualWithinLimits(poles[j], 1.0E-5D)) testpole2 = false;
/*      */           }
/* 1507 */           if (!testpole2) {
/* 1508 */             sValueReal += 1.0D;
/* 1509 */             sValue.reset(sValueReal, 0.0D);
/*      */           }
/*      */           else {
/* 1512 */             testpole1 = false;
/*      */           }
/*      */         }
/*      */         
/*      */ 
/* 1517 */         vec[i] = numer.evaluate(sValue);
/* 1518 */         denhold = denom.evaluate(sValue);
/* 1519 */         for (int j = 0; j < polesN; j++) {
/* 1520 */           if (termSet[j] != 0) {
/* 1521 */             temp = coeff[j].times(denhold.over(Complex.pow(sValue.minus(poles[j]), polePower[j])));
/* 1522 */             vec[i] = vec[i].minus(temp);
/*      */           }
/*      */         }
/*      */         
/*      */ 
/* 1527 */         int k = 0;
/* 1528 */         for (int j = 0; j < polesN; j++) {
/* 1529 */           temp = Complex.plusOne();
/* 1530 */           if (termSet[j] == 0) {
/* 1531 */             temp = temp.times(denhold.over(Complex.pow(sValue.minus(poles[j]), polePower[j])));
/* 1532 */             mat[j][i] = temp;
/*      */           }
/*      */         }
/* 1535 */         sValueReal += 1.0D;
/* 1536 */         sValue.setReal(sValueReal);
/*      */       }
/*      */       
/*      */ 
/* 1540 */       if (nsimul == 1) {
/* 1541 */         for (int i = 0; i < polesN; i++) {
/* 1542 */           if (termSet[i] == 0) {
/* 1543 */             coeff[i] = vec[0].over(mat[0][0]);
/*      */           }
/*      */         }
/*      */       }
/*      */       else {
/* 1548 */         ComplexMatrix cmat = new ComplexMatrix(mat);
/* 1549 */         Complex[] terms = cmat.solveLinearSet(vec);
/* 1550 */         int j = -1;
/* 1551 */         for (int i = 0; i < polesN; i++) {
/* 1552 */           if (termSet[i] == 0) { coeff[i] = terms[(++j)];
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1559 */     Complex mean = new Complex(0.0D, 0.0D);
/* 1560 */     for (int i = 0; i < polesN; i++) mean = mean.plus(poles[i]);
/* 1561 */     mean = mean.over(polesN);
/*      */     
/*      */ 
/* 1564 */     boolean test = true;
/* 1565 */     int ii = 0;
/* 1566 */     while (test) {
/* 1567 */       if (mean.isEqual(poles[ii])) {
/* 1568 */         mean = mean.times(1.5D);
/* 1569 */         ii = 0;
/*      */       }
/*      */       else {
/* 1572 */         ii++;
/* 1573 */         if (ii > polesN - 1) { test = false;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1578 */     Complex product = new Complex(1.0D, 0.0D);
/* 1579 */     for (int i = 0; i < polesN; i++) { product = product.times(mean.minus(poles[i]));
/*      */     }
/*      */     
/* 1582 */     Complex eval = denom.evaluate(mean);
/*      */     
/*      */ 
/* 1585 */     Complex conversionConstant = product.over(eval);
/*      */     
/*      */ 
/* 1588 */     for (int i = 0; i < polesN; i++) {
/* 1589 */       ret[0][i] = coeff[i].times(conversionConstant);
/* 1590 */       ret[1][i] = poles[i];
/* 1591 */       ret[2][i].reset(polePower[i], 0.0D);
/*      */     }
/* 1593 */     return ret;
/*      */   }
/*      */   
/*      */   public BlackBox copy()
/*      */   {
/* 1598 */     if (this == null) {
/* 1599 */       return null;
/*      */     }
/*      */     
/* 1602 */     BlackBox bb = new BlackBox();
/*      */     
/* 1604 */     bb.sampLen = this.sampLen;
/* 1605 */     bb.inputT = ((double[])this.inputT.clone());
/* 1606 */     bb.outputT = ((double[])this.outputT.clone());
/* 1607 */     bb.time = ((double[])this.time.clone());
/* 1608 */     bb.forgetFactor = this.forgetFactor;
/* 1609 */     bb.deltaT = this.deltaT;
/* 1610 */     bb.sampFreq = this.sampFreq;
/* 1611 */     bb.inputS = this.inputS.copy();
/* 1612 */     bb.outputS = this.outputS.copy();
/* 1613 */     bb.sValue = this.sValue.copy();
/* 1614 */     bb.zValue = this.zValue.copy();
/* 1615 */     bb.sNumer = this.sNumer.copy();
/* 1616 */     bb.sDenom = this.sDenom.copy();
/* 1617 */     bb.zNumer = this.zNumer.copy();
/* 1618 */     bb.zDenom = this.zDenom.copy();
/* 1619 */     bb.sPoles = Complex.copy(this.sPoles);
/* 1620 */     bb.sZeros = Complex.copy(this.sZeros);
/* 1621 */     bb.zPoles = Complex.copy(this.zPoles);
/* 1622 */     bb.zZeros = Complex.copy(this.zZeros);
/* 1623 */     bb.sNumerDeg = this.sNumerDeg;
/* 1624 */     bb.sDenomDeg = this.sDenomDeg;
/* 1625 */     bb.zNumerDeg = this.zNumerDeg;
/* 1626 */     bb.zDenomDeg = this.zDenomDeg;
/* 1627 */     bb.deadTime = this.deadTime;
/* 1628 */     bb.orderPade = this.orderPade;
/* 1629 */     bb.sNumerPade = this.sNumerPade.copy();
/* 1630 */     bb.sDenomPade = this.sDenomPade.copy();
/* 1631 */     bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 1632 */     bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 1633 */     bb.sNumerDegPade = this.sNumerDegPade;
/* 1634 */     bb.sDenomDegPade = this.sDenomDegPade;
/* 1635 */     bb.maptozero = this.maptozero;
/* 1636 */     bb.padeAdded = this.padeAdded;
/* 1637 */     bb.integrationSum = this.integrationSum;
/* 1638 */     bb.integMethod = this.integMethod;
/* 1639 */     bb.ztransMethod = this.ztransMethod;
/* 1640 */     bb.name = this.name;
/* 1641 */     bb.fixedName = this.fixedName;
/* 1642 */     bb.nPlotPoints = this.nPlotPoints;
/*      */     
/* 1644 */     return bb;
/*      */   }
/*      */   
/*      */ 
/*      */   public Object clone()
/*      */   {
/* 1650 */     Object ret = null;
/*      */     
/* 1652 */     if (this != null) {
/* 1653 */       BlackBox bb = new BlackBox();
/*      */       
/* 1655 */       bb.sampLen = this.sampLen;
/* 1656 */       bb.inputT = ((double[])this.inputT.clone());
/* 1657 */       bb.outputT = ((double[])this.outputT.clone());
/* 1658 */       bb.time = ((double[])this.time.clone());
/* 1659 */       bb.forgetFactor = this.forgetFactor;
/* 1660 */       bb.deltaT = this.deltaT;
/* 1661 */       bb.sampFreq = this.sampFreq;
/* 1662 */       bb.inputS = this.inputS.copy();
/* 1663 */       bb.outputS = this.outputS.copy();
/* 1664 */       bb.sValue = this.sValue.copy();
/* 1665 */       bb.zValue = this.zValue.copy();
/* 1666 */       bb.sNumer = this.sNumer.copy();
/* 1667 */       bb.sDenom = this.sDenom.copy();
/* 1668 */       bb.zNumer = this.zNumer.copy();
/* 1669 */       bb.zDenom = this.zDenom.copy();
/* 1670 */       bb.sPoles = Complex.copy(this.sPoles);
/* 1671 */       bb.sZeros = Complex.copy(this.sZeros);
/* 1672 */       bb.zPoles = Complex.copy(this.zPoles);
/* 1673 */       bb.zZeros = Complex.copy(this.zZeros);
/* 1674 */       bb.sNumerDeg = this.sNumerDeg;
/* 1675 */       bb.sDenomDeg = this.sDenomDeg;
/* 1676 */       bb.zNumerDeg = this.zNumerDeg;
/* 1677 */       bb.zDenomDeg = this.zDenomDeg;
/* 1678 */       bb.deadTime = this.deadTime;
/* 1679 */       bb.orderPade = this.orderPade;
/* 1680 */       bb.sNumerPade = this.sNumerPade.copy();
/* 1681 */       bb.sDenomPade = this.sDenomPade.copy();
/* 1682 */       bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 1683 */       bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 1684 */       bb.sNumerDegPade = this.sNumerDegPade;
/* 1685 */       bb.sDenomDegPade = this.sDenomDegPade;
/* 1686 */       bb.maptozero = this.maptozero;
/* 1687 */       bb.padeAdded = this.padeAdded;
/* 1688 */       bb.integrationSum = this.integrationSum;
/* 1689 */       bb.integMethod = this.integMethod;
/* 1690 */       bb.ztransMethod = this.ztransMethod;
/* 1691 */       bb.name = this.name;
/* 1692 */       bb.fixedName = this.fixedName;
/* 1693 */       bb.nPlotPoints = this.nPlotPoints;
/*      */       
/* 1695 */       ret = bb;
/*      */     }
/* 1697 */     return ret;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/control/BlackBox.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */