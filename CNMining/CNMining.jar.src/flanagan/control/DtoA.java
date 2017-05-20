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
/*     */ public class DtoA
/*     */   extends BlackBox
/*     */ {
/*  42 */   private int nBits = 0;
/*  43 */   private long maximumDecimal = 0L;
/*  44 */   private double vRef = 0.0D;
/*  45 */   private int[] vBinary = null;
/*  46 */   private boolean trueDtoA = true;
/*     */   
/*  48 */   private double outputVoltage = 0.0D;
/*  49 */   private double voltageInput = 0.0D;
/*  50 */   private String binaryInput = "";
/*  51 */   private long decimalInput = 0L;
/*  52 */   private boolean inputSet = false;
/*     */   
/*     */ 
/*     */   public DtoA(int nBits, double vRef)
/*     */   {
/*  57 */     super("DtoA");
/*  58 */     super.setSnumer(new ComplexPoly(1.0D));
/*  59 */     super.setSdenom(new ComplexPoly(1.0D));
/*  60 */     super.setZtransformMethod(1);
/*  61 */     this.nBits = nBits;
/*  62 */     this.vBinary = new int[nBits + 1];
/*  63 */     this.maximumDecimal = (Math.pow(2.0D, this.nBits) - 1L);
/*  64 */     this.vRef = vRef;
/*  65 */     this.trueDtoA = true;
/*     */   }
/*     */   
/*     */ 
/*     */   public DtoA()
/*     */   {
/*  71 */     super("DtoA");
/*  72 */     this.trueDtoA = false;
/*  73 */     this.sNumerDeg = 0;
/*  74 */     this.sDenomDeg = 0;
/*  75 */     super.setSnumer(new ComplexPoly(1.0D));
/*  76 */     super.setSdenom(new ComplexPoly(1.0D));
/*  77 */     super.setZtransformMethod(1);
/*     */   }
/*     */   
/*     */   public boolean getTrueDtoAoption()
/*     */   {
/*  82 */     if (this.trueDtoA) {
/*  83 */       System.out.println("This instance of DtoA is a true simulation of an ADC");
/*  84 */       System.out.println("getTrueDtoAoption has returned 'true'");
/*     */     }
/*     */     else {
/*  87 */       System.out.println("This instance of DtoA is not a true simulation of an ADC");
/*  88 */       System.out.println("It is simple an 'D to A marker'");
/*  89 */       System.out.println("getTrueDtoAoption has returned 'false'");
/*     */     }
/*  91 */     return this.trueDtoA;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setInput(String input)
/*     */   {
/*  97 */     this.binaryInput = input.trim();
/*  98 */     int len = this.binaryInput.length();
/*  99 */     if (len > this.nBits + 1) throw new IllegalArgumentException("length of input String is greater than the DAC bit number plus one");
/* 100 */     if (len < this.nBits + 1) {
/* 101 */       System.out.println("Class - DtoA;  method - setInput(String)");
/* 102 */       System.out.println("The input String is less than DAC number of bits plus one");
/* 103 */       System.out.println("String assumed to represent a postive unsigned binary number");
/* 104 */       System.out.println("unfilled bits assigned zeros");
/* 105 */       for (int i = len; i < this.nBits + 1; i++) this.binaryInput = ('0' + this.binaryInput);
/* 106 */       len = this.nBits + 1;
/*     */     }
/*     */     
/*     */ 
/* 110 */     int ii = 0;
/* 111 */     int jj = 0;
/* 112 */     char c = ' ';
/* 113 */     for (int i = len - 1; i >= 0; i--) {
/* 114 */       c = this.binaryInput.charAt(i);
/* 115 */       if (c == '1') {
/* 116 */         ii = 1;
/*     */ 
/*     */       }
/* 119 */       else if (c == '0') {
/* 120 */         ii = 0;
/*     */       }
/*     */       else {
/* 123 */         throw new IllegalArgumentException("String input must be '0's or '1's");
/*     */       }
/*     */       
/* 126 */       jj = len - i - 1;
/* 127 */       this.vBinary[jj] = ii;
/*     */     }
/*     */     
/*     */ 
/* 131 */     long sign = 1L;
/* 132 */     int[] vPosBinary = (int[])this.vBinary.clone();
/* 133 */     if (this.vBinary[(len - 1)] == 1) {
/* 134 */       sign = -1L;
/* 135 */       vPosBinary = negateNegativeBinary(vPosBinary);
/*     */     }
/*     */     
/*     */ 
/* 139 */     this.decimalInput = binaryToDecimal(vPosBinary);
/*     */     
/*     */ 
/* 142 */     if (sign == -1L) { this.decimalInput = (-this.decimalInput);
/*     */     }
/*     */     
/* 145 */     this.outputVoltage = (this.decimalInput * this.vRef / (this.maximumDecimal + 1L));
/*     */     
/* 147 */     this.inputSet = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setInput(int[] input)
/*     */   {
/* 154 */     int len = input.length;
/* 155 */     if (len > this.nBits + 1) throw new IllegalArgumentException("length of input array is greater than the DAC bit number plus  one");
/* 156 */     for (int i = 0; i < len; i++) this.vBinary[i] = input[i];
/* 157 */     if (len < this.nBits + 1) {
/* 158 */       System.out.println("Class - DtoA;  method - setInput(String)");
/* 159 */       System.out.println("The input array is less than DAC number of bits plus one");
/* 160 */       System.out.println("Array assumed to represent a postive unsigned binary number");
/* 161 */       System.out.println("unfilled bits assigned zeros");
/* 162 */       for (int i = len; i < this.nBits + 1; i++) this.vBinary[i] = 0;
/* 163 */       len = this.nBits + 1;
/*     */     }
/*     */     
/*     */ 
/* 167 */     this.binaryInput = "";
/* 168 */     for (int i = this.nBits; i >= 0; i--) {
/* 169 */       this.binaryInput += this.vBinary[i];
/*     */     }
/*     */     
/*     */ 
/* 173 */     long sign = 1L;
/* 174 */     int[] vPosBinary = (int[])this.vBinary.clone();
/* 175 */     if (this.vBinary[(len - 1)] == 1) {
/* 176 */       sign = -1L;
/* 177 */       vPosBinary = negateNegativeBinary(this.vBinary);
/*     */     }
/*     */     
/*     */ 
/* 181 */     this.decimalInput = binaryToDecimal(vPosBinary);
/*     */     
/*     */ 
/* 184 */     if (sign == -1L) { this.decimalInput = (-this.decimalInput);
/*     */     }
/*     */     
/* 187 */     this.outputVoltage = (this.decimalInput * this.vRef / (this.maximumDecimal + 1L));
/*     */     
/* 189 */     this.inputSet = true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setInput(long input)
/*     */   {
/* 195 */     if (Math.abs(input) > this.maximumDecimal) throw new IllegalArgumentException("abs(input), " + input + ", is greater than the maximum decimal representation, " + this.maximumDecimal + ", allowed by the set number of bits, " + this.nBits);
/* 196 */     this.decimalInput = input;
/*     */     
/*     */ 
/* 199 */     this.outputVoltage = (input * this.vRef / (this.maximumDecimal + 1L));
/*     */     
/*     */ 
/* 202 */     long dec = this.decimalInput;
/* 203 */     int sign = 1;
/* 204 */     if (dec < 0L) {
/* 205 */       sign = -1;
/* 206 */       dec = -dec;
/*     */     }
/*     */     
/* 209 */     for (int i = 0; i < this.nBits + 1; i++) this.vBinary[i] = 0;
/* 210 */     boolean test = true;
/* 211 */     int ii = 0;
/* 212 */     while (test) {
/* 213 */       this.vBinary[ii] = ((int)(dec % 2L));
/* 214 */       dec /= 2L;
/* 215 */       ii++;
/* 216 */       if (dec == 0L) { test = false;
/*     */       }
/*     */     }
/*     */     
/* 220 */     if (sign == -1L) { this.vBinary = AtoD.negateBinary(this.vBinary);
/*     */     }
/*     */     
/* 223 */     this.binaryInput = "";
/* 224 */     for (int i = this.nBits; i >= 0; i--) {
/* 225 */       this.binaryInput += this.vBinary[i];
/*     */     }
/*     */     
/* 228 */     this.inputSet = true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setInput(double input)
/*     */   {
/* 234 */     if (this.trueDtoA) {
/* 235 */       if (Math.abs(input) > this.vRef) {
/* 236 */         throw new IllegalArgumentException("The input voltage in this simulation of a DAC must be less than nor equal to the reference voltage\nIf you choose the constructor without an argument list, i.e. an instance of DtoA that is simply a DAC marker\nyou may imput any voltage and the output will be made equal to that voltage");
/*     */       }
/*     */       
/* 239 */       this.voltageInput = input;
/* 240 */       AtoD adc = new AtoD(this.nBits, this.vRef);
/* 241 */       adc.setInput(input);
/* 242 */       this.decimalInput = adc.decimalOutput();
/* 243 */       this.binaryInput = adc.binaryOutput();
/* 244 */       this.vBinary = adc.binaryArray();
/*     */     }
/*     */     else
/*     */     {
/* 248 */       this.outputVoltage = input;
/*     */     }
/* 250 */     this.sNumer.resetCoeff(0, new Complex(this.outputVoltage / this.voltageInput, 0.0D));
/*     */     
/* 252 */     this.inputSet = true;
/*     */   }
/*     */   
/*     */   private static long binaryToDecimal(int[] binary)
/*     */   {
/* 257 */     long decimal = 0L;
/* 258 */     for (int i = 0; i < binary.length; i++) {
/* 259 */       decimal += (Math.pow(2.0D, i) * binary[i]);
/*     */     }
/* 261 */     return decimal;
/*     */   }
/*     */   
/*     */ 
/*     */   private static int[] negateNegativeBinary(int[] binary)
/*     */   {
/* 267 */     int nBin = binary.length;
/* 268 */     int[] negate = new int[nBin];
/* 269 */     int[] minusOne = new int[nBin];
/* 270 */     for (int i = 0; i < nBin; i++) {
/* 271 */       minusOne[i] = 1;
/* 272 */       negate[i] = 0;
/*     */     }
/*     */     
/* 275 */     negate = addBinary(negate, minusOne);
/*     */     
/* 277 */     for (int i = 0; i < nBin; i++) {
/* 278 */       if (binary[i] == 0) { negate[i] = 1;
/*     */       }
/*     */     }
/* 281 */     return negate;
/*     */   }
/*     */   
/*     */   private static int[] addBinary(int[] aa, int[] bb)
/*     */   {
/* 286 */     int n = aa.length;
/* 287 */     int m = bb.length;
/* 288 */     int lenMax = n;
/* 289 */     int lenMin = m;
/* 290 */     if (m > n) {
/* 291 */       lenMax = m;
/* 292 */       lenMin = n;
/*     */     }
/* 294 */     int[] addition = new int[lenMax];
/* 295 */     int carry = 0;
/* 296 */     int sum = 0;
/* 297 */     for (int i = 0; i < lenMin; i++) {
/* 298 */       sum = aa[i] + bb[i] + carry;
/* 299 */       switch (sum) {
/* 300 */       case 0:  addition[i] = 0;
/* 301 */         carry = 0;
/* 302 */         break;
/* 303 */       case 1:  addition[i] = 1;
/* 304 */         carry = 0;
/* 305 */         break;
/* 306 */       case 2:  addition[i] = 0;
/* 307 */         carry = 1;
/* 308 */         break;
/* 309 */       case 3:  addition[i] = 1;
/* 310 */         carry = 1;
/*     */       }
/*     */       
/*     */     }
/*     */     
/* 315 */     return addition;
/*     */   }
/*     */   
/*     */   public double getOutput()
/*     */   {
/* 320 */     if (!this.inputSet) throw new IllegalArgumentException("No input has been entered");
/* 321 */     return this.outputVoltage;
/*     */   }
/*     */   
/*     */   public long getDecimalInput()
/*     */   {
/* 326 */     if (!this.inputSet) throw new IllegalArgumentException("No input has been entered");
/* 327 */     if (!this.trueDtoA) {
/* 328 */       System.out.println("Class - DtoA;  method - getDecimalInput");
/* 329 */       System.out.println("This instance of DtoA is not a true simulation of an DAC");
/* 330 */       System.out.println("It is simple an 'D to A marker'");
/* 331 */       System.out.println("getDecimalInput has returned 0L");
/* 332 */       this.decimalInput = 0L;
/*     */     }
/*     */     
/* 335 */     return this.decimalInput;
/*     */   }
/*     */   
/*     */   public String getBinaryInput()
/*     */   {
/* 340 */     if (!this.inputSet) throw new IllegalArgumentException("No input has been entered");
/* 341 */     if (!this.trueDtoA) {
/* 342 */       System.out.println("Class - DtoA;  method - getBinaryInput");
/* 343 */       System.out.println("This instance of DtoA is not a true simulation of an DAC");
/* 344 */       System.out.println("It is simple an 'D to A marker'");
/* 345 */       System.out.println("getBinaryInput has returned null");
/* 346 */       this.binaryInput = null;
/*     */     }
/*     */     
/* 349 */     return this.binaryInput;
/*     */   }
/*     */   
/*     */   public int[] getBinaryArray()
/*     */   {
/* 354 */     if (!this.inputSet) throw new IllegalArgumentException("No input has been entered");
/* 355 */     if (!this.trueDtoA) {
/* 356 */       System.out.println("Class - DtoA;  method - getBinaryInput");
/* 357 */       System.out.println("This instance of DtoA is not a true simulation of an DAC");
/* 358 */       System.out.println("It is simple an 'D to A marker'");
/* 359 */       System.out.println("getBinaryArray has returned null");
/* 360 */       this.vBinary = null;
/*     */     }
/*     */     
/* 363 */     return this.vBinary;
/*     */   }
/*     */   
/*     */   public DtoA copy()
/*     */   {
/* 368 */     if (this == null) {
/* 369 */       return null;
/*     */     }
/*     */     
/* 372 */     DtoA bb = new DtoA();
/*     */     
/* 374 */     bb.nBits = this.nBits;
/* 375 */     bb.maximumDecimal = this.maximumDecimal;
/* 376 */     bb.vRef = this.vRef;
/* 377 */     bb.vBinary = ((int[])this.vBinary.clone());
/* 378 */     bb.trueDtoA = this.trueDtoA;
/* 379 */     bb.outputVoltage = this.outputVoltage;
/* 380 */     bb.voltageInput = this.voltageInput;
/* 381 */     bb.binaryInput = this.binaryInput;
/* 382 */     bb.decimalInput = this.decimalInput;
/* 383 */     bb.inputSet = this.inputSet;
/*     */     
/* 385 */     bb.sampLen = this.sampLen;
/* 386 */     bb.inputT = ((double[])this.inputT.clone());
/* 387 */     bb.outputT = ((double[])this.outputT.clone());
/* 388 */     bb.time = ((double[])this.time.clone());
/* 389 */     bb.forgetFactor = this.forgetFactor;
/* 390 */     bb.deltaT = this.deltaT;
/* 391 */     bb.sampFreq = this.sampFreq;
/* 392 */     bb.inputS = this.inputS.copy();
/* 393 */     bb.outputS = this.outputS.copy();
/* 394 */     bb.sValue = this.sValue.copy();
/* 395 */     bb.zValue = this.zValue.copy();
/* 396 */     bb.sNumer = this.sNumer.copy();
/* 397 */     bb.sDenom = this.sDenom.copy();
/* 398 */     bb.zNumer = this.zNumer.copy();
/* 399 */     bb.zDenom = this.zDenom.copy();
/* 400 */     bb.sPoles = Complex.copy(this.sPoles);
/* 401 */     bb.sZeros = Complex.copy(this.sZeros);
/* 402 */     bb.zPoles = Complex.copy(this.zPoles);
/* 403 */     bb.zZeros = Complex.copy(this.zZeros);
/* 404 */     bb.sNumerDeg = this.sNumerDeg;
/* 405 */     bb.sDenomDeg = this.sDenomDeg;
/* 406 */     bb.zNumerDeg = this.zNumerDeg;
/* 407 */     bb.zDenomDeg = this.zDenomDeg;
/* 408 */     bb.deadTime = this.deadTime;
/* 409 */     bb.orderPade = this.orderPade;
/* 410 */     bb.sNumerPade = this.sNumerPade.copy();
/* 411 */     bb.sDenomPade = this.sDenomPade.copy();
/* 412 */     bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 413 */     bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 414 */     bb.sNumerDegPade = this.sNumerDegPade;
/* 415 */     bb.sDenomDegPade = this.sDenomDegPade;
/* 416 */     bb.maptozero = this.maptozero;
/* 417 */     bb.padeAdded = this.padeAdded;
/* 418 */     bb.integrationSum = this.integrationSum;
/* 419 */     bb.integMethod = this.integMethod;
/* 420 */     bb.ztransMethod = this.ztransMethod;
/* 421 */     bb.name = this.name;
/* 422 */     bb.fixedName = this.fixedName;
/* 423 */     bb.nPlotPoints = this.nPlotPoints;
/*     */     
/* 425 */     return bb;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 432 */     Object ret = null;
/*     */     
/* 434 */     if (this != null) {
/* 435 */       DtoA bb = new DtoA();
/*     */       
/* 437 */       bb.nBits = this.nBits;
/* 438 */       bb.maximumDecimal = this.maximumDecimal;
/* 439 */       bb.vRef = this.vRef;
/* 440 */       bb.vBinary = ((int[])this.vBinary.clone());
/* 441 */       bb.trueDtoA = this.trueDtoA;
/* 442 */       bb.outputVoltage = this.outputVoltage;
/* 443 */       bb.voltageInput = this.voltageInput;
/* 444 */       bb.binaryInput = this.binaryInput;
/* 445 */       bb.decimalInput = this.decimalInput;
/* 446 */       bb.inputSet = this.inputSet;
/*     */       
/* 448 */       bb.sampLen = this.sampLen;
/* 449 */       bb.inputT = ((double[])this.inputT.clone());
/* 450 */       bb.outputT = ((double[])this.outputT.clone());
/* 451 */       bb.time = ((double[])this.time.clone());
/* 452 */       bb.forgetFactor = this.forgetFactor;
/* 453 */       bb.deltaT = this.deltaT;
/* 454 */       bb.sampFreq = this.sampFreq;
/* 455 */       bb.inputS = this.inputS.copy();
/* 456 */       bb.outputS = this.outputS.copy();
/* 457 */       bb.sValue = this.sValue.copy();
/* 458 */       bb.zValue = this.zValue.copy();
/* 459 */       bb.sNumer = this.sNumer.copy();
/* 460 */       bb.sDenom = this.sDenom.copy();
/* 461 */       bb.zNumer = this.zNumer.copy();
/* 462 */       bb.zDenom = this.zDenom.copy();
/* 463 */       bb.sPoles = Complex.copy(this.sPoles);
/* 464 */       bb.sZeros = Complex.copy(this.sZeros);
/* 465 */       bb.zPoles = Complex.copy(this.zPoles);
/* 466 */       bb.zZeros = Complex.copy(this.zZeros);
/* 467 */       bb.sNumerDeg = this.sNumerDeg;
/* 468 */       bb.sDenomDeg = this.sDenomDeg;
/* 469 */       bb.zNumerDeg = this.zNumerDeg;
/* 470 */       bb.zDenomDeg = this.zDenomDeg;
/* 471 */       bb.deadTime = this.deadTime;
/* 472 */       bb.orderPade = this.orderPade;
/* 473 */       bb.sNumerPade = this.sNumerPade.copy();
/* 474 */       bb.sDenomPade = this.sDenomPade.copy();
/* 475 */       bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 476 */       bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 477 */       bb.sNumerDegPade = this.sNumerDegPade;
/* 478 */       bb.sDenomDegPade = this.sDenomDegPade;
/* 479 */       bb.maptozero = this.maptozero;
/* 480 */       bb.padeAdded = this.padeAdded;
/* 481 */       bb.integrationSum = this.integrationSum;
/* 482 */       bb.integMethod = this.integMethod;
/* 483 */       bb.ztransMethod = this.ztransMethod;
/* 484 */       bb.name = this.name;
/* 485 */       bb.fixedName = this.fixedName;
/* 486 */       bb.nPlotPoints = this.nPlotPoints;
/*     */       
/* 488 */       ret = bb;
/*     */     }
/* 490 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/control/DtoA.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */