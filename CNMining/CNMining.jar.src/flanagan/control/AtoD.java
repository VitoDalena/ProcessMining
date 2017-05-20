/*     */ package flanagan.control;
/*     */ 
/*     */ import flanagan.complex.Complex;
/*     */ import flanagan.complex.ComplexPoly;
/*     */ import flanagan.math.Fmath;
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
/*     */ public class AtoD
/*     */   extends BlackBox
/*     */ {
/*  48 */   private int nBits = 0;
/*  49 */   private long maximumDecimal = 0L;
/*  50 */   private double vRef = 0.0D;
/*  51 */   private int[] vBinary = null;
/*  52 */   private boolean trueAtoD = true;
/*     */   
/*  54 */   private boolean range = true;
/*     */   
/*  56 */   private double voltageOutput = 0.0D;
/*     */   
/*  58 */   private String binaryOutput = "";
/*  59 */   private long decimalOutput = 0L;
/*  60 */   private double sqnr = 0.0D;
/*  61 */   private double input = 0.0D;
/*  62 */   private double inputC = 0.0D;
/*  63 */   private double shift = 0.0D;
/*  64 */   private long decimalShift = 0L;
/*  65 */   private boolean decCalcDone = false;
/*  66 */   private boolean binCalcDone = false;
/*  67 */   private boolean inputSet = false;
/*     */   
/*     */ 
/*     */   public AtoD(int nBits, double vRef)
/*     */   {
/*  72 */     super("AtoD");
/*  73 */     if (nBits > 63) throw new IllegalArgumentException("This program cannot accomadate an ADC simulation with a number of bits greater than 63");
/*  74 */     this.nBits = nBits;
/*  75 */     this.maximumDecimal = (Math.pow(2.0D, this.nBits) - 1L);
/*  76 */     this.vRef = vRef;
/*  77 */     this.vBinary = new int[nBits + 1];
/*  78 */     this.trueAtoD = true;
/*  79 */     super.setSnumer(new ComplexPoly(1.0D));
/*  80 */     super.setSdenom(new ComplexPoly(1.0D));
/*  81 */     super.setZtransformMethod(1);
/*     */   }
/*     */   
/*     */ 
/*     */   public AtoD()
/*     */   {
/*  87 */     super("AtoD");
/*  88 */     this.fixedName = "AtoD";
/*  89 */     this.sNumerDeg = 0;
/*  90 */     this.sDenomDeg = 0;
/*  91 */     super.setSnumer(new ComplexPoly(1.0D));
/*  92 */     super.setSdenom(new ComplexPoly(1.0D));
/*  93 */     this.ztransMethod = 1;
/*  94 */     super.setZtransformMethod(1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setRangeOption(int opt)
/*     */   {
/* 101 */     if ((opt < 0) || (opt > 2)) throw new IllegalArgumentException("argument must be either 0 or 1");
/* 102 */     if (opt == 0) this.range = true;
/* 103 */     if (opt == 1) {
/* 104 */       this.range = false;
/* 105 */       this.shift = (this.vRef / 2.0D);
/* 106 */       this.decimalShift = (this.maximumDecimal / 2L);
/*     */     }
/* 108 */     if (this.inputSet) checkInput();
/* 109 */     this.decCalcDone = false;
/*     */   }
/*     */   
/*     */   public String getRange()
/*     */   {
/* 114 */     String ran = null;
/* 115 */     if (this.trueAtoD) {
/* 116 */       if (this.range) {
/* 117 */         ran = "0 to " + this.vRef;
/*     */       }
/*     */       else {
/* 120 */         ran = "-" + this.vRef / 2.0D + " to " + this.vRef / 2.0D;
/*     */       }
/*     */     }
/*     */     else {
/* 124 */       System.out.println("Class AtoD; method getRange()");
/* 125 */       System.out.println("No range option set - this instance of AtoD is an 'ADC marker' only");
/* 126 */       System.out.println("getRangeOption has returned 'ADC marker only'");
/* 127 */       ran = "ADC marker only";
/*     */     }
/* 129 */     return ran;
/*     */   }
/*     */   
/*     */   public boolean getTrueAtoDoption()
/*     */   {
/* 134 */     if (this.trueAtoD) {
/* 135 */       System.out.println("This instance of AtoD is a true simulation of an ADC");
/* 136 */       System.out.println("getTrueAtoDoption has returned 'true'");
/*     */     }
/*     */     else {
/* 139 */       System.out.println("This instance of AtoD is not a true simulation of an ADC");
/* 140 */       System.out.println("It is simple an 'A to D marker'");
/* 141 */       System.out.println("getTrueAtoDoption has returned 'false'");
/*     */     }
/* 143 */     return this.trueAtoD;
/*     */   }
/*     */   
/*     */   public double getVref()
/*     */   {
/* 148 */     if (!this.trueAtoD) {
/* 149 */       System.out.println("No reference voltage set - this instance of AtoD is an 'ADC marker' only");
/* 150 */       System.out.println("getVref has returned 0.0 V");
/*     */     }
/* 152 */     return this.vRef;
/*     */   }
/*     */   
/*     */   public void setInput(double input)
/*     */   {
/* 157 */     this.input = input;
/* 158 */     checkInput();
/* 159 */     this.inputSet = true;
/*     */   }
/*     */   
/*     */   public void checkInput()
/*     */   {
/* 164 */     this.inputC = this.input;
/* 165 */     if (this.trueAtoD) {
/* 166 */       if (this.range) {
/* 167 */         if (this.input < 0.0D) {
/* 168 */           System.out.println("lower limit of the ADC range exceeded");
/* 169 */           System.out.println("input voltage set to zero");
/* 170 */           this.inputC = 0.0D;
/*     */         }
/* 172 */         if (this.input > this.vRef) {
/* 173 */           System.out.println("upper limit of the ADC range exceeded");
/* 174 */           System.out.println("input voltage set to " + this.vRef);
/* 175 */           this.inputC = this.vRef;
/*     */         }
/*     */       }
/*     */       else {
/* 179 */         if (this.input < -this.vRef) {
/* 180 */           System.out.println("lower limit of the ADC range exceeded");
/* 181 */           System.out.println("input voltage set to " + -this.vRef / 2.0D);
/* 182 */           this.inputC = (-this.vRef / 2.0D);
/*     */         }
/* 184 */         if (this.input > this.vRef) {
/* 185 */           System.out.println("upper limit of the ADC range exceeded");
/* 186 */           System.out.println("input voltage set to " + this.vRef / 2.0D);
/* 187 */           this.inputC = (this.vRef / 2.0D);
/*     */         }
/*     */       }
/*     */     }
/* 191 */     this.inputC += this.shift;
/* 192 */     this.decCalcDone = false;
/* 193 */     this.binCalcDone = false;
/*     */   }
/*     */   
/*     */ 
/*     */   public long getMaximumDecimal()
/*     */   {
/* 199 */     if (!this.trueAtoD) {
/* 200 */       System.out.println("This instance of AtoD is not a true simulation of an ADC");
/* 201 */       System.out.println("It is simple an 'A to D marker'");
/* 202 */       System.out.println("getTrueAtoDoption has returned 0");
/*     */     }
/* 204 */     return this.maximumDecimal;
/*     */   }
/*     */   
/*     */   public double maximumQuantizationError()
/*     */   {
/* 209 */     double error = 0.0D;
/* 210 */     if (this.trueAtoD) {
/* 211 */       error = this.vRef / this.maximumDecimal;
/*     */     }
/*     */     else {
/* 214 */       System.out.println("This instance of AtoD is not a true simulation of an ADC");
/* 215 */       System.out.println("It is simple an 'A to D marker'");
/* 216 */       System.out.println("getMaxQuantizationError returns zero");
/*     */     }
/* 218 */     return error;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void calcOutput()
/*     */   {
/* 227 */     if (this.trueAtoD) {
/* 228 */       this.decimalOutput = (Math.floor(this.inputC / this.vRef * this.maximumDecimal) - this.decimalShift);
/* 229 */       this.voltageOutput = (this.vRef * this.decimalOutput / this.maximumDecimal);
/* 230 */       this.sqnr = (20.0D * Fmath.log10(Math.abs((this.inputC - this.shift) / (this.inputC - this.shift - this.voltageOutput))));
/*     */     }
/*     */     else {
/* 233 */       this.voltageOutput = this.input;
/* 234 */       this.sqnr = Double.POSITIVE_INFINITY;
/*     */     }
/*     */     
/* 237 */     this.sNumer.resetCoeff(0, new Complex(this.voltageOutput / this.input, 0.0D));
/*     */     
/* 239 */     this.decCalcDone = true;
/*     */   }
/*     */   
/*     */   public double getSQNR()
/*     */   {
/* 244 */     if (!this.decCalcDone) calcOutput();
/* 245 */     if (!this.trueAtoD) {
/* 246 */       System.out.println("This instance of AtoD is not a true simulation of an ADC");
/* 247 */       System.out.println("It is simple an 'A to D marker'");
/* 248 */       System.out.println("getSQNR returned INFINITY");
/*     */     }
/* 250 */     return this.sqnr;
/*     */   }
/*     */   
/*     */ 
/*     */   public double voltageOutput()
/*     */   {
/* 256 */     if (!this.decCalcDone) calcOutput();
/* 257 */     return this.voltageOutput;
/*     */   }
/*     */   
/*     */   public long decimalOutput()
/*     */   {
/* 262 */     if (!this.decCalcDone) calcOutput();
/* 263 */     if (!this.trueAtoD) {
/* 264 */       System.out.println("No formal A to D conversion performed - this instance of AtoD is an 'ADC marker' only");
/* 265 */       System.out.println("decimalOutput has returned 0");
/*     */     }
/*     */     
/* 268 */     return this.decimalOutput;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static int[] decimalToBinary(long decimal, int nBits)
/*     */   {
/* 275 */     long decSign = 1L;
/* 276 */     if (decimal < 0L) {
/* 277 */       decSign = -1L;
/* 278 */       decimal *= decSign;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 283 */     long len = Math.ceil(Math.log(decimal) / Math.log(2.0D));
/* 284 */     if (nBits < len) {
/* 285 */       boolean test = true;
/* 286 */       int ii = 2;
/* 287 */       while (test) {
/* 288 */         if (Math.pow(2.0D, ii) > len) {
/* 289 */           nBits = ii;
/* 290 */           test = false;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 296 */     int[] binary = new int[nBits];
/* 297 */     for (int i = 0; i < nBits; i++) binary[i] = 0;
/* 298 */     boolean test = true;
/* 299 */     int ii = 0;
/* 300 */     while (test) {
/* 301 */       binary[ii] = ((int)(decimal % 2L));
/* 302 */       decimal /= 2L;
/* 303 */       ii++;
/* 304 */       if (decimal == 0L) { test = false;
/*     */       }
/*     */     }
/*     */     
/* 308 */     if (decSign == -1L) { binary = negateBinary(binary);
/*     */     }
/* 310 */     return binary;
/*     */   }
/*     */   
/*     */ 
/*     */   public static int[] negateBinary(int[] binary)
/*     */   {
/* 316 */     int nBinary = binary.length;
/* 317 */     int nBin = nBinary;
/*     */     
/*     */ 
/* 320 */     if (binary[(nBinary - 1)] == 1) nBin += nBin;
/* 321 */     int[] negate = new int[nBin];
/* 322 */     int[] one = new int[nBin];
/* 323 */     for (int i = 0; i < nBin; i++) {
/* 324 */       one[i] = 0;
/* 325 */       negate[i] = 1;
/*     */     }
/* 327 */     one[0] = 1;
/*     */     
/* 329 */     for (int i = 0; i < nBinary; i++) {
/* 330 */       if (binary[i] == 1) { negate[i] = 0;
/*     */       }
/*     */     }
/* 333 */     negate = addBinary(negate, one);
/*     */     
/* 335 */     return negate;
/*     */   }
/*     */   
/*     */   public static int[] addBinary(int[] aa, int[] bb)
/*     */   {
/* 340 */     int n = aa.length;
/* 341 */     int m = bb.length;
/* 342 */     int lenMax = n;
/* 343 */     int lenMin = m;
/* 344 */     if (m > n) {
/* 345 */       lenMax = m;
/* 346 */       lenMin = n;
/*     */     }
/* 348 */     int[] addition = new int[lenMax];
/* 349 */     int carry = 0;
/* 350 */     int sum = 0;
/* 351 */     for (int i = 0; i < lenMin; i++) {
/* 352 */       sum = aa[i] + bb[i] + carry;
/* 353 */       switch (sum) {
/* 354 */       case 0:  addition[i] = 0;
/* 355 */         carry = 0;
/* 356 */         break;
/* 357 */       case 1:  addition[i] = 1;
/* 358 */         carry = 0;
/* 359 */         break;
/* 360 */       case 2:  addition[i] = 0;
/* 361 */         carry = 1;
/* 362 */         break;
/* 363 */       case 3:  addition[i] = 1;
/* 364 */         carry = 1;
/*     */       }
/*     */       
/*     */     }
/*     */     
/* 369 */     return addition;
/*     */   }
/*     */   
/*     */   public String binaryOutput()
/*     */   {
/* 374 */     if (!this.decCalcDone) calcOutput();
/* 375 */     if (this.trueAtoD) {
/* 376 */       int nBit = this.nBits + 1;
/*     */       
/* 378 */       long absDecOut = this.decimalOutput + this.decimalShift;
/* 379 */       this.vBinary = decimalToBinary(absDecOut, nBit);
/*     */       
/* 381 */       if (this.shift > 0.0D)
/*     */       {
/* 383 */         int[] binaryShift = decimalToBinary(this.decimalShift, nBit);
/*     */         
/*     */ 
/* 386 */         binaryShift = negateBinary(binaryShift);
/*     */         
/*     */ 
/* 389 */         this.vBinary = addBinary(this.vBinary, binaryShift);
/*     */       }
/*     */       
/*     */ 
/* 393 */       this.binaryOutput = "";
/* 394 */       for (int i = nBit - 1; i >= 0; i--) {
/* 395 */         this.binaryOutput += this.vBinary[i];
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 400 */       System.out.println("No formal A to D conversion performed - this instance of AtoD is an 'ADC marker' only");
/* 401 */       System.out.println("binaryOutput has returned 'null'");
/*     */     }
/*     */     
/* 404 */     this.binCalcDone = true;
/* 405 */     return this.binaryOutput;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int[] binaryArray()
/*     */   {
/* 412 */     if (this.trueAtoD) {
/* 413 */       if (!this.binCalcDone) binaryOutput();
/*     */     }
/*     */     else {
/* 416 */       System.out.println("No formal A to D conversion performed - this instance of AtoD is an 'ADC marker' only");
/* 417 */       System.out.println("binaryOutput has returned 'null'");
/*     */     }
/*     */     
/* 420 */     return this.vBinary;
/*     */   }
/*     */   
/*     */ 
/*     */   public double quantizationError()
/*     */   {
/* 426 */     if (!this.decCalcDone) calcOutput();
/* 427 */     double error = 0.0D;
/* 428 */     if (this.trueAtoD) {
/* 429 */       error = this.inputC - this.voltageOutput;
/*     */     }
/*     */     else {
/* 432 */       System.out.println("This instance of AtoD is not a true simulation of an ADC");
/* 433 */       System.out.println("It is simple an 'A to D marker'");
/* 434 */       System.out.println("getQuantizationError returns zero");
/*     */     }
/* 436 */     return error;
/*     */   }
/*     */   
/*     */ 
/*     */   public double clippingError()
/*     */   {
/* 442 */     return this.inputC - this.input;
/*     */   }
/*     */   
/*     */   public AtoD copy()
/*     */   {
/* 447 */     if (this == null) {
/* 448 */       return null;
/*     */     }
/*     */     
/* 451 */     AtoD bb = new AtoD();
/*     */     
/* 453 */     bb.nBits = this.nBits;
/* 454 */     bb.maximumDecimal = this.maximumDecimal;
/* 455 */     bb.vRef = this.vRef;
/* 456 */     bb.vBinary = ((int[])this.vBinary.clone());
/* 457 */     bb.trueAtoD = this.trueAtoD;
/* 458 */     bb.range = this.range;
/* 459 */     bb.voltageOutput = this.voltageOutput;
/* 460 */     bb.binaryOutput = this.binaryOutput;
/* 461 */     bb.decimalOutput = this.decimalOutput;
/* 462 */     bb.sqnr = this.sqnr;
/* 463 */     bb.input = this.input;
/* 464 */     bb.inputC = this.inputC;
/* 465 */     bb.shift = this.shift;
/* 466 */     bb.decimalShift = this.decimalShift;
/* 467 */     bb.decCalcDone = this.decCalcDone;
/* 468 */     bb.binCalcDone = this.binCalcDone;
/* 469 */     bb.inputSet = this.inputSet;
/*     */     
/* 471 */     bb.sampLen = this.sampLen;
/* 472 */     bb.inputT = ((double[])this.inputT.clone());
/* 473 */     bb.outputT = ((double[])this.outputT.clone());
/* 474 */     bb.time = ((double[])this.time.clone());
/* 475 */     bb.forgetFactor = this.forgetFactor;
/* 476 */     bb.deltaT = this.deltaT;
/* 477 */     bb.sampFreq = this.sampFreq;
/* 478 */     bb.inputS = this.inputS.copy();
/* 479 */     bb.outputS = this.outputS.copy();
/* 480 */     bb.sValue = this.sValue.copy();
/* 481 */     bb.zValue = this.zValue.copy();
/* 482 */     bb.sNumer = this.sNumer.copy();
/* 483 */     bb.sDenom = this.sDenom.copy();
/* 484 */     bb.zNumer = this.zNumer.copy();
/* 485 */     bb.zDenom = this.zDenom.copy();
/* 486 */     bb.sPoles = Complex.copy(this.sPoles);
/* 487 */     bb.sZeros = Complex.copy(this.sZeros);
/* 488 */     bb.zPoles = Complex.copy(this.zPoles);
/* 489 */     bb.zZeros = Complex.copy(this.zZeros);
/* 490 */     bb.sNumerDeg = this.sNumerDeg;
/* 491 */     bb.sDenomDeg = this.sDenomDeg;
/* 492 */     bb.zNumerDeg = this.zNumerDeg;
/* 493 */     bb.zDenomDeg = this.zDenomDeg;
/* 494 */     bb.deadTime = this.deadTime;
/* 495 */     bb.orderPade = this.orderPade;
/* 496 */     bb.sNumerPade = this.sNumerPade.copy();
/* 497 */     bb.sDenomPade = this.sDenomPade.copy();
/* 498 */     bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 499 */     bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 500 */     bb.sNumerDegPade = this.sNumerDegPade;
/* 501 */     bb.sDenomDegPade = this.sDenomDegPade;
/* 502 */     bb.maptozero = this.maptozero;
/* 503 */     bb.padeAdded = this.padeAdded;
/* 504 */     bb.integrationSum = this.integrationSum;
/* 505 */     bb.integMethod = this.integMethod;
/* 506 */     bb.ztransMethod = this.ztransMethod;
/* 507 */     bb.name = this.name;
/* 508 */     bb.fixedName = this.fixedName;
/* 509 */     bb.nPlotPoints = this.nPlotPoints;
/*     */     
/* 511 */     return bb;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 517 */     Object ret = null;
/*     */     
/* 519 */     if (this != null) {
/* 520 */       AtoD bb = new AtoD();
/*     */       
/* 522 */       bb.nBits = this.nBits;
/* 523 */       bb.maximumDecimal = this.maximumDecimal;
/* 524 */       bb.vRef = this.vRef;
/* 525 */       bb.vBinary = ((int[])this.vBinary.clone());
/* 526 */       bb.trueAtoD = this.trueAtoD;
/* 527 */       bb.range = this.range;
/* 528 */       bb.voltageOutput = this.voltageOutput;
/* 529 */       bb.binaryOutput = this.binaryOutput;
/* 530 */       bb.decimalOutput = this.decimalOutput;
/* 531 */       bb.sqnr = this.sqnr;
/* 532 */       bb.input = this.input;
/* 533 */       bb.inputC = this.inputC;
/* 534 */       bb.shift = this.shift;
/* 535 */       bb.decimalShift = this.decimalShift;
/* 536 */       bb.decCalcDone = this.decCalcDone;
/* 537 */       bb.binCalcDone = this.binCalcDone;
/* 538 */       bb.inputSet = this.inputSet;
/*     */       
/* 540 */       bb.sampLen = this.sampLen;
/* 541 */       bb.inputT = ((double[])this.inputT.clone());
/* 542 */       bb.outputT = ((double[])this.outputT.clone());
/* 543 */       bb.time = ((double[])this.time.clone());
/* 544 */       bb.forgetFactor = this.forgetFactor;
/* 545 */       bb.deltaT = this.deltaT;
/* 546 */       bb.sampFreq = this.sampFreq;
/* 547 */       bb.inputS = this.inputS.copy();
/* 548 */       bb.outputS = this.outputS.copy();
/* 549 */       bb.sValue = this.sValue.copy();
/* 550 */       bb.zValue = this.zValue.copy();
/* 551 */       bb.sNumer = this.sNumer.copy();
/* 552 */       bb.sDenom = this.sDenom.copy();
/* 553 */       bb.zNumer = this.zNumer.copy();
/* 554 */       bb.zDenom = this.zDenom.copy();
/* 555 */       bb.sPoles = Complex.copy(this.sPoles);
/* 556 */       bb.sZeros = Complex.copy(this.sZeros);
/* 557 */       bb.zPoles = Complex.copy(this.zPoles);
/* 558 */       bb.zZeros = Complex.copy(this.zZeros);
/* 559 */       bb.sNumerDeg = this.sNumerDeg;
/* 560 */       bb.sDenomDeg = this.sDenomDeg;
/* 561 */       bb.zNumerDeg = this.zNumerDeg;
/* 562 */       bb.zDenomDeg = this.zDenomDeg;
/* 563 */       bb.deadTime = this.deadTime;
/* 564 */       bb.orderPade = this.orderPade;
/* 565 */       bb.sNumerPade = this.sNumerPade.copy();
/* 566 */       bb.sDenomPade = this.sDenomPade.copy();
/* 567 */       bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 568 */       bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 569 */       bb.sNumerDegPade = this.sNumerDegPade;
/* 570 */       bb.sDenomDegPade = this.sDenomDegPade;
/* 571 */       bb.maptozero = this.maptozero;
/* 572 */       bb.padeAdded = this.padeAdded;
/* 573 */       bb.integrationSum = this.integrationSum;
/* 574 */       bb.integMethod = this.integMethod;
/* 575 */       bb.ztransMethod = this.ztransMethod;
/* 576 */       bb.name = this.name;
/* 577 */       bb.fixedName = this.fixedName;
/* 578 */       bb.nPlotPoints = this.nPlotPoints;
/* 579 */       ret = bb;
/*     */     }
/* 581 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/control/AtoD.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */